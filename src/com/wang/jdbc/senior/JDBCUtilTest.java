package com.wang.jdbc.senior;

import com.wang.jdbc.senior.util.JDBCUtil;
import com.wang.jdbc.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilTest {

    //测试JDBCUtil工具类
    @Test
    public void testGetConnection() throws SQLException {
        //获取连接池
        Connection connection = JDBCUtil.getConnection();

        System.out.println(connection);
        //CURD

        //回收
        JDBCUtil.release(connection);
    }

    //测试JDBCUtilV2工具类
    @Test
    public void testJDBCUtilV2() throws Exception{

        //使用JDBCUtil多次获取连接的时候,获取的为不同的连接对象
        Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        Connection connection3 = JDBCUtil.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);

        System.out.println("====================================");

        //使用JDBCUtilV2多次获取的连接,为同一个连接对象
        Connection connection4 = JDBCUtilV2.getConnection();
        Connection connection5 = JDBCUtilV2.getConnection();
        Connection connection6= JDBCUtilV2.getConnection();

        System.out.println(connection4);
        System.out.println(connection5);
        System.out.println(connection6);

    }
}
