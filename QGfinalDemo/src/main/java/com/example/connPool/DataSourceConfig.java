package com.example.connPool;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;

public class DataSourceConfig {

    private String driver;
    private String url;
    private String username;
    private String password;
    private String initSize="3";
    private String maxSize="6";
    private String health="true";
    private String delay="1000";
    private String period="1000";
    private String waittime="1000";
    private String timeout="500000";

    public DataSourceConfig() {
        try {
            Properties properties = new Properties();
            properties.load(DataSourceConfig.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            Set<Object> keySet = properties.keySet();
            for(Object key : keySet){
                String filedName=key.toString().split("\\.")[1];
                String filedValue=properties.getProperty(key.toString());
                Field field = null;
                field = this.getClass().getDeclaredField(filedName);
                field.setAccessible(true);
                field.set(this,filedValue);
            }
        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }



    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitSize() {
        return initSize;
    }

    public void setInitSize(String initSize) {
        this.initSize = initSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getWaittime() {
        return waittime;
    }

    public void setWaittime(String waittime) {
        this.waittime = waittime;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "DataSourceConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", initSize='" + initSize + '\'' +
                ", maxSize='" + maxSize + '\'' +
                ", health='" + health + '\'' +
                ", delay='" + delay + '\'' +
                ", period='" + period + '\'' +
                ", waittime='" + waittime + '\'' +
                ", timeout='" + timeout + '\'' +
                '}';
    }

    //    private static DataSourceConfig instance = null;
//
//    public static DataSourceConfig getInstance(){
//        synchronized(DataSourceConfig.class){
//            if(instance == null){
//                instance = new DataSourceConfig();
//            }
//        }
//        return instance;
//    }
//
//
//    private DataSourceConfig(){
//
//        try {
//            Properties properties = new Properties();
//            properties.load(DataSourceConfig.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//            Set<Object> keySet = properties.keySet();
//            for(Object key : keySet){
//                String filedName=key.toString().split("\\.")[1];
//                String filedValue=properties.getProperty(key.toString());
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }finally {
//
//        }
//    }


}
