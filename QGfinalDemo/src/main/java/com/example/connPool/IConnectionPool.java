package com.example.connPool;

import java.sql.Connection;

//连接池接口
public interface IConnectionPool {

    //    从连接池中拿到一个连接对象
    Connection getConn();

    //    将连接对象归还连接池
    void releaseConn(Connection connection);

}
