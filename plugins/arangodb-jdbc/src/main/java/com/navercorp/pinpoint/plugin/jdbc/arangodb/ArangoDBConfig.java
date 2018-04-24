package com.navercorp.pinpoint.plugin.jdbc.arangodb;

import com.navercorp.pinpoint.bootstrap.config.ProfilerConfig;
import com.navercorp.pinpoint.bootstrap.plugin.jdbc.JdbcConfig;

/**
 * @program: pinpoint
 * @description:
 * @author: Mr.Wang
 * @create: 2018-04-23 13:21
 **/
public class ArangoDBConfig extends JdbcConfig {
    private final boolean profileSetAutoCommit;
    private final boolean profileCommit;
    private final boolean profileRollback;

    public ArangoDBConfig(ProfilerConfig config) {
        super(config.readBoolean("profiler.jdbc.arangodb", false),
                config.readBoolean("profiler.jdbc.arangodb.traceaqlbindvalue", config.isTraceAqlBindValue()),
                config.getMaxAqlBindValueSize());
        this.profileSetAutoCommit = config.readBoolean("profiler.jdbc.arangodb.setautocommit", false);
        this.profileCommit = config.readBoolean("profiler.jdbc.arangodb.commit", false);
        this.profileRollback = config.readBoolean("profiler.jdbc.arangodb.rollback", false);
    }

    public boolean isProfileSetAutoCommit() {
        return profileSetAutoCommit;
    }

    public boolean isProfileCommit() {
        return profileCommit;
    }

    public boolean isProfileRollback() {
        return profileRollback;
    }


    @Override
    public String toString() {
        return "ArangoDBConfig [" + super.toString() + ", profileSetAutoCommit=" + profileSetAutoCommit + ", profileCommit=" + profileCommit + ", profileRollback=" + profileRollback + "]";
    }
}
