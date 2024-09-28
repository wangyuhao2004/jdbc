package com.wang.jdbc.senior.dao;

import com.wang.jdbc.senior.util.JDBCUtilV2;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 将sql语句执行的共性方面提取出来进行封装
 */
public class BaseDao {

    /**
     * 通用的增删改的方法
     * @param sql  调用者要执行的sql语句
     * @param params  sql语句当中占位符要赋值的参数
     * @return  受影响的行数
     */
    public int executeUpdate(String sql, Object...params) throws SQLException {
        //1.通过JDBCUtilV2获取数据库连接
        Connection connection = JDBCUtilV2.getConnection();

        //2.预编译sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3.为占位符赋值,执行sql,并返回结果
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        int row = preparedStatement.executeUpdate();
        preparedStatement.close();
        JDBCUtilV2.release();
        return row;
    }

    /**
     * 通用的查询:多行多列,单行单列,单行多列
     * 这里采用泛型的写法,因为为通用查询方法,所以对象的类型是变化的
     * @param clazz  反射对象
     * @param sql   调用者要执行的sql语句
     * @param params  sql语句当中占位符要赋值的参数
     * @return 返回一个集合
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object...params) throws Exception {
        //创建一个集合用于存查询出来的对象
            List<T> list = new ArrayList<>();
        Connection connection = JDBCUtilV2.getConnection();
        //预编译sql
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1,params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        //获取结果集当中的元数据对象
        //包含:列的数量,列的名称
        ResultSetMetaData metaData = resultSet.getMetaData();
        //在metaData当中获取列的数量
        int columnCount = metaData.getColumnCount();

        //处理结果
        while (resultSet.next()) {
            //每循环一次,代表有一行数据,采用反射创建一个泛型类型的对象
            T t = clazz.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                /* 因为每一张表都对应一个pojo类.每个列名,在pojo当中都有对应的属性名
                *  例如表中的name列,在pojo当中对应String name这个属性名
                *  */
                //获取每一个列的值,例如'张三'
                Object value = resultSet.getObject(i);

                //获取当前列的名字,例如 name
                String columnName = metaData.getColumnLabel(i);

                //根据列的名字,采用反射获取要封装对象的属性, 例如:String
                Field field = clazz.getDeclaredField(columnName);
                //暴力反射
                field.setAccessible(true);
                //为当前属性赋值
                field.set(t,value);
            }
            //存到集合当中
            list.add(t);

        }
        //返回集合
        return list;
    }

    /*
      通用查询:在上面查询的结果集合当中获取第一个元素,简化了获取单行单列,单行多列的操作
     */
    public <T> T executeQueryBean(Class<T> clazz, String sql, Object...params) throws Exception {
        List<T> list = this.executeQuery(clazz, sql, params);

        if (list == null || list.size() == 0) {
            return null;
        }
        return list.getFirst();
    }
}
