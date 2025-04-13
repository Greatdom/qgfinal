package com.example.connPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

//连接池的实现类
public class ConnectionPool implements IConnectionPool {

    //记录连接数的总数
//    private int connectionCount = 0;
    AtomicInteger connectionCount = new AtomicInteger(0);

//    数据库配置信息
    private DataSourceConfig dataSourceConfig;

    //空闲连接池
    Vector<Connection> freePools = new Vector<Connection>();

    //使用中连接池
    Vector<ConnectionEntity> usePools = new Vector<ConnectionEntity>();

    public ConnectionPool(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;

        init();
    }

    //初始化连接池
    private void init() {
        for(int i = 0; i<Integer.valueOf(dataSourceConfig.getInitSize()); i++){
            Connection connection = createConnection();
            System.out.println("连接池初始化"+connection);
            freePools.add(connection);
        }
        //开启了健康检查,防止连接池连接对象超时导致一直不释放
        if(Boolean.valueOf(dataSourceConfig.getHealth())){
            checkConnectionTimeout();
        }
    }

    //定时检查占用时间超长的连接并关闭
    private void checkConnectionTimeout() {
        Worker worker = new Worker();
        new Timer().schedule(worker,Long.valueOf(dataSourceConfig.getDelay()), Long.valueOf(dataSourceConfig.getPeriod()));
    }

    //局部内部类-任务类
    class Worker extends TimerTask {

        @Override
        public void run() {

            for(int i=0;i<usePools.size();i++){
                ConnectionEntity connectionEntity = usePools.get(i);
                if((System.currentTimeMillis()-connectionEntity.getUseStartTime())>Long.valueOf(dataSourceConfig.getTimeout())){
                    Connection connection = connectionEntity.getConnection();
                    //有一个conn对象使用超过了设置的超时时间
                    if(isAvailable(connection)){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        usePools.remove(i);
                        //连接总数-1
                        connectionCount.decrementAndGet();
                        System.out.println(Thread.currentThread().getName()+"归还了一个超时的连接对象:"+connection+",空闲连接池大小是:"+freePools.size()+",正在使用的连接池大小是:"+usePools.size()+"总连接数:"+connectionCount);
                    }
                }
            }
        }
    }

    private synchronized Connection createConnection() {
        Connection connection = null;

        try {
            Class.forName(dataSourceConfig.getDriver());
            connection = DriverManager.getConnection(dataSourceConfig.getUrl(),dataSourceConfig.getUsername(),dataSourceConfig.getPassword());
            //累加
            connectionCount.incrementAndGet();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public synchronized Connection getConn() {
        Connection connection = null;
        //判断空闲的连接池中是否还有连接
        if(!freePools.isEmpty()){
            //空闲连接池非空
            connection =  freePools.get(0);
            if(isAvailable(connection)){
                freePools.remove(connection);
                ConnectionEntity connectionEntity =new ConnectionEntity(connection,System.currentTimeMillis());
                usePools.add(connectionEntity);
            }



            System.out.println( Thread.currentThread().getName()+ "从空闲连接池获取了一个连接:"+connection+",空闲连接池大小是:"+freePools.size()+",正在使用的连接池大小是:"+usePools.size()+"总连接数:"+connectionCount);
        }else{
            //空闲的连接池里没有连接池了
            if(connectionCount.get()<Integer.valueOf(dataSourceConfig.getMaxSize())){
                connection = createConnection();
                ConnectionEntity connectionEntity =new ConnectionEntity(connection,System.currentTimeMillis());
                usePools.add(connectionEntity);
                System.out.println(Thread.currentThread().getName()+"空闲连接池没有连接了:"+connection+",空闲连接池大小是:"+freePools.size()+",正在使用的连接池大小是:"+usePools.size()+"总连接数:"+connectionCount);

            }else{
                System.out.println(Thread.currentThread().getName()+"连接数超出总大小,进行等待..."+",空闲连接池大小是:"+freePools.size()+",正在使用的连接池大小是:"+usePools.size()+"总连接数:"+connectionCount);
                //等待...
                try {
                    this.wait(Integer.valueOf(dataSourceConfig.getWaittime()));
                    connection = getConn();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return connection;
    }

    //判断这个连接池是否可用
    private boolean isAvailable(Connection connection){
        try {
            if(connection!=null&&!connection.isClosed()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public synchronized void releaseConn(Connection connection) {
        if(isAvailable(connection)){
            //如果这个连接可用,放入空闲连接池中
            freePools.add(connection);
            for(int i=0;i<usePools.size();i++){
                Connection conn =usePools.get(i).getConnection();
                if(conn == connection){
                    usePools.remove(i);
                }
            }
            System.out.println(Thread.currentThread().getName()+"归还了一个连接对象:"+connection+",空闲连接池大小是:"+freePools.size()+",正在使用的连接池大小是:"+usePools.size()+"总连接数:"+connectionCount);

        }
        this.notifyAll();

    }
}
