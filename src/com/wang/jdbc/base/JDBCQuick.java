package com.wang.jdbc.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCQuick {
    public static void main(String[] args) throws Exception{

        //注册驱动
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //DriverManager.registerDriver(new Driver());


        //获取连接对象
        //url:要连接的数据库的地址以及要连接的数据库
        String url = "jdbc:mysql://localhost:3306/atguigu";
        //用户名
        String username = "root";
        //密码
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        //获取执行sql语句的对象(发送sql语句给mysql执行的对象)
        Statement statement = connection.createStatement();

        //编写sql语句,并执行,返回结果
        String sql = "select emp_id, emp_name, emp_salary, emp_age from t_emp";
        ResultSet resultSet = statement.executeQuery(sql);

        //处理结果,遍历resultSet结果集
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }
        //释放资源(先开后关)
        resultSet.close();
        statement.close();
        connection.close();

    }
}
