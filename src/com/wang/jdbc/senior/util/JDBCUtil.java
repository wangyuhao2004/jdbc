package com.wang.jdbc.senior.util;

/*
*    JDBC工具类(v1.0)
*       1.维护一个连接池对象
*       2.对外提供在连接池中获取连接的方法
*       3.对外提供回收连接的方法
*    注意:工具类仅对外提供共性的功能代码,所以所有方法均为静态方法
* */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    //创建连接池引用,因为要提供给当前项目全局使用,所以创建为静态的
    //这里采用多态的方法创建
    private static DataSource dataSource;

    //创建静态代码块,在项目启动的时候就创建连接池对象,赋值给dataSource
    //静态代码块不能向外声明异常,这里使用try...catch
    static {
        Properties properties = new Properties();
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //对外提供在连接池中获取连接的方法
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    //对外提供回收连接的方法
    public static void release(Connection connection) throws SQLException {
        connection.close();
    }
}
