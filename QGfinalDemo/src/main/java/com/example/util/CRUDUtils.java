package com.example.util;

import com.example.connPool.DataSourceManager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
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
    public static int insert(String sql, Object... params) {
        return update(sql, params);
    }

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

    public static int delete(String sql, Object... params) {
        return update(sql, params);
    }


    private static PreparedStatement prepareStatement(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt;
    }


    private static <T> T resultSetToObject(ResultSet rs, Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException {
        T obj = clazz.newInstance();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String fieldName = toCamelCase(columnName); // 转换为驼峰命名格式
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(obj, rs.getObject(i));
            } catch (NoSuchFieldException e) {
                // 忽略不存在的字段
            }
        }
        return obj;
    }

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

    private static String toCamelCase(String str) {
        StringBuilder result = new StringBuilder();
        boolean nextUpperCase = false;
        for (char c : str.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true; // 下一个字符需要大写
            } else {
                if (nextUpperCase) {
                    result.append(Character.toUpperCase(c)); // 大写当前字符
                    nextUpperCase = false;
                } else {
                    result.append(Character.toLowerCase(c)); // 小写当前字符
                }
            }
        }
        return result.toString();
    }

}