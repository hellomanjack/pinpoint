package com.navercorp.pinpoint.plugin.jdbc.arangodb;

import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeFactory;

import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.INCLUDE_DESTINATION_ID;
import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.RECORD_STATISTICS;
import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.TERMINAL;

/**
 * @program: pinpoint
 * @description:
 * @author: Mr.Wang
 * @create: 2018-04-23 13:21
 **/
public class ArangoDBConstants {
    private ArangoDBConstants() {
    }

    public static final String ARANGODB_SCOPE = "ARANGODB_JDBC";

    public static final ServiceType ARANGODB = ServiceTypeFactory.of(2160, "ARANGODB", TERMINAL, INCLUDE_DESTINATION_ID);
    public static final ServiceType ARANGODB_EXECUTE_QUERY = ServiceTypeFactory.of(2161, "ARANGODB_EXECUTE_QUERY",
            "ARANGODB", TERMINAL, RECORD_STATISTICS, INCLUDE_DESTINATION_ID);
}
