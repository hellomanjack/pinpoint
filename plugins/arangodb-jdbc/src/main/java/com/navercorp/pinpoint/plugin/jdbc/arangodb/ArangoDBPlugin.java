package com.navercorp.pinpoint.plugin.jdbc.arangodb;

import com.navercorp.pinpoint.bootstrap.instrument.*;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;
import com.navercorp.pinpoint.bootstrap.plugin.jdbc.JdbcUrlParserV2;
import com.navercorp.pinpoint.bootstrap.plugin.util.InstrumentUtils;

import java.security.ProtectionDomain;

/**
 * @program: pinpoint
 * @description:
 * @author: Mr.Wang
 * @create: 2018-04-23 13:18
 **/
public class ArangoDBPlugin implements ProfilerPlugin, TransformTemplateAware {
    private static final String ARANGODB_SCOPE = ArangoDBConstants.ARANGODB_SCOPE;

    private final PLogger logger = PLoggerFactory.getLogger(this.getClass());

    private final JdbcUrlParserV2 jdbcUrlParser = new ArangoDBJdbcUrlParser();

    private TransformTemplate transformTemplate;


    @Override
    public void setup(ProfilerPluginSetupContext context) {
        ArangoDBConfig config = new ArangoDBConfig(context.getConfig());

        if (!config.isPluginEnable()) {
            logger.info("ArangoDB plugin is not executed because plugin enable value is false.");
            return;
        }

        context.addJdbcUrlParser(jdbcUrlParser);

        //addCollectionTransformer(config);
        //addCursorTransformer(config);
        addDBTransformer(config);
        //addExceptionTransformer(config);
        //addDatabaseTransformer(config);
        //addEdgeCollectionTransformer(config);
        //addGraphTransformer(config);
        //addVertexCollectionTransformer(config);
    }

    private void addDBTransformer(final ArangoDBConfig config) {
        TransformCallback transformer = new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                if (!target.isInterceptable()) {
                    return null;
                }
                target.addField("com.navercorp.pinpoint.bootstrap.plugin.jdbc.DatabaseInfoAccessor");

                for (InstrumentMethod m : target.getDeclaredMethods(MethodFilters.name("query"))) {
                    m.addScopedInterceptor("com.navercorp.pinpoint.bootstrap.plugin.jdbc.interceptor.StatementExecuteQueryInterceptor", ARANGODB_SCOPE);
                }


                if (config.isProfileSetAutoCommit()) {
                    InstrumentUtils.findMethod(target, "setAutoCommit", "boolean")
                            .addScopedInterceptor("com.navercorp.pinpoint.bootstrap.plugin.jdbc.interceptor.TransactionSetAutoCommitInterceptor", ARANGODB_SCOPE);
                }

                if (config.isProfileCommit()) {
                    InstrumentUtils.findMethod(target, "commit")
                            .addScopedInterceptor("com.navercorp.pinpoint.bootstrap.plugin.jdbc.interceptor.TransactionCommitInterceptor", ARANGODB_SCOPE);
                }

                if (config.isProfileRollback()) {
                    InstrumentUtils.findMethod(target, "rollback")
                            .addScopedInterceptor("com.navercorp.pinpoint.bootstrap.plugin.jdbc.interceptor.TransactionRollbackInterceptor", ARANGODB_SCOPE);
                }

                return target.toBytecode();
            }
        };
        transformTemplate.transform("com.arangodb.ArangoDatabase", transformer);
    }

    @Override
    public void setTransformTemplate(TransformTemplate transformTemplate) {

    }
}
