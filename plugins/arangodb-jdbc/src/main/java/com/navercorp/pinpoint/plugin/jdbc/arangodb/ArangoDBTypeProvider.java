package com.navercorp.pinpoint.plugin.jdbc.arangodb;

import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.AnnotationKeyMatchers;
import com.navercorp.pinpoint.common.trace.TraceMetadataProvider;
import com.navercorp.pinpoint.common.trace.TraceMetadataSetupContext;

/**
 * @program: pinpoint
 * @description:
 * @author: Mr.Wang
 * @create: 2018-04-23 13:18
 **/
public class ArangoDBTypeProvider implements TraceMetadataProvider {
    @Override
    public void setup(TraceMetadataSetupContext context) {
        context.addServiceType(ArangoDBConstants.ARANGODB, AnnotationKeyMatchers.exact(AnnotationKey.ARGS0));
        context.addServiceType(ArangoDBConstants.ARANGODB_EXECUTE_QUERY,
                AnnotationKeyMatchers.exact(AnnotationKey.ARGS0));
    }
}
