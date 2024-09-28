package com.wang.jdbc.advanced.pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {

    @Test
    public void resourcesDruid() throws Exception {
        //创建properties集合,用于存储外部配置文件的key和value
        Properties properties = new Properties();

        //读取外部配置文件,获取输入流,加载properties集合里
        InputStream resourceAsStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
        properties.load(resourceAsStream);

        //基于properties集合构建DruidDataSource连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //通过连接池获取连接对象
        Connection connection = dataSource.getConnection();

        //CURD操作

        //回收连接
        connection.close();


    }

}
