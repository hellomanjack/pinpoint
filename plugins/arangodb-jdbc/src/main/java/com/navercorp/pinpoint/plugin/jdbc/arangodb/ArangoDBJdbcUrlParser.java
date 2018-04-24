package com.navercorp.pinpoint.plugin.jdbc.arangodb;

import com.navercorp.pinpoint.bootstrap.context.DatabaseInfo;
import com.navercorp.pinpoint.bootstrap.plugin.jdbc.JdbcUrlParserV2;
import com.navercorp.pinpoint.common.trace.ServiceType;

/**
 * @program: pinpoint
 * @description:
 * @author: Mr.Wang
 * @create: 2018-04-23 14:14
 **/
public class ArangoDBJdbcUrlParser implements JdbcUrlParserV2 {

    @Override
    public DatabaseInfo parse(String url) {
        return null;
    }

    @Override
    public ServiceType getServiceType() {
        return ArangoDBConstants.ARANGODB;
    }
}
