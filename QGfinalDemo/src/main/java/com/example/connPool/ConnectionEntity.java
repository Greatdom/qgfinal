package com.example.connPool;

import java.sql.Connection;

public class ConnectionEntity {
    private Connection connection;
    private Long useStartTime;

    public ConnectionEntity(Connection connection, Long useStartTime) {
        this.connection = connection;
        this.useStartTime = useStartTime;
    }
    public ConnectionEntity(){}

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Long getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Long useStartTime) {
        this.useStartTime = useStartTime;
    }
}
