package com.example.connPool;

import java.sql.Connection;

//数据库管理器
public class DataSourceManager {
    static DataSourceConfig dataSourceConfig = new DataSourceConfig();
    static ConnectionPool connectionPool = new ConnectionPool(dataSourceConfig);

    //从连接池中获取一个连接对象
    public static Connection getConn(){
        return connectionPool.getConn();
    }
    public static void close(Connection connection){
        connectionPool.releaseConn(connection);
    }
}
