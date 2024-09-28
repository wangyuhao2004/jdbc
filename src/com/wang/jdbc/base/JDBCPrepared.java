package com.wang.jdbc.base;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCPrepared {

    public static void main(String[] args) throws Exception{
        DriverManager.registerDriver(new Driver());

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");

        //获取执行sql语句对象
        //采用preparedStatement能够有效阻止sql注入问题
        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id, emp_name, emp_salary, emp_age from atguigu.t_emp where emp_name = ?");

        System.out.println("请输入要查询的姓名");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        //为?占位符赋值,并执行sql语句,接受返回的结果
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
