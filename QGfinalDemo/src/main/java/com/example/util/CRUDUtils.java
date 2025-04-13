package com.example.util;

import com.example.connPool.DataSourceManager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    /**
     * 查询单个对象
     * @param clazz 实体类类型
     * @param sql 查询SQL语句
     * @param params SQL语句参数
     * @param <T> 泛型
     * @return 查询结果
     */
    public static <T> T queryForObject(Class<T> clazz, String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceManager.getConn();
            stmt = prepareStatement(conn, sql, params);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return resultSetToObject(rs, clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, stmt, conn);
        }
        return null;
    }

    /**
     * 查询多个对象
     * @param clazz 实体类类型
     * @param sql 查询SQL语句
     * @param params SQL语句参数
     * @param <T> 泛型
     * @return 查询结果列表
     */
    public static <T> List<T> queryForList(Class<T> clazz, String sql, Object... params) {
        List<T> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceManager.getConn();
            stmt = prepareStatement(conn, sql, params);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(resultSetToObject(rs, clazz));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, stmt, conn);
        }
        return result;
    }

    /**
     * 插入数据
     * @param sql 插入SQL语句
     * @param params SQL语句参数
     * @return 插入的行数
     */
    public static int insert(String sql, Object... params) {
        return update(sql, params);
    }

    /**
     * 更新数据
     * @param sql 更新SQL语句
     * @param params SQL语句参数
     * @return 更新的行数
     */
    public static int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DataSourceManager.getConn();
            stmt = prepareStatement(conn, sql, params);
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(null, stmt, conn);
        }
    }

    /**
     * 删除数据
     * @param sql 删除SQL语句
     * @param params SQL语句参数
     * @return 删除的行数
     */
    public static int delete(String sql, Object... params) {
        return update(sql, params);
    }

    /**
     * 准备PreparedStatement
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param params SQL语句参数
     * @return PreparedStatement
     */
    private static PreparedStatement prepareStatement(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt;
    }

    /**
     * 将ResultSet映射为对象
     * @param rs ResultSet
     * @param clazz 实体类类型
     * @param <T> 泛型
     * @return 映射后的对象
     */
    private static <T> T resultSetToObject(ResultSet rs, Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException {
        T obj = clazz.newInstance();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            try {
                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(obj, rs.getObject(i));
            } catch (NoSuchFieldException e) {
                // 忽略不存在的字段
            }
        }
        return obj;
    }

    /**
     * 关闭资源
     * @param rs ResultSet
     * @param stmt PreparedStatement
     * @param conn Connection
     */
    private static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DataSourceManager.close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}