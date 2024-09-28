package com.wang.jdbc.base;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInjection {
    //演示sql注入攻击

    public static void main(String[] args) throws Exception{
        //1.注册驱动
        DriverManager.registerDriver(new Driver());
        //2.连接数据库
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
        //3.获取执行sql语句的对象
        Statement statement = connection.createStatement();

        /*
        * 当输入这样一段字符串的时候(adad' or '1' ='1)
        * 将会查询出全部的用户信息*/
        System.out.println("请输入要查询的姓名");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        //4.编写sql语句,并执行,返回结果
        String sql = "select emp_id, emp_name, emp_salary, emp_age from t_emp where emp_name = '" + name +"'";
        statement.executeQuery(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        //5.处理结果,遍历resultSet结果集
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }
        //6.释放资源(先开后关)
        resultSet.close();
        statement.close();
        connection.close();


    }
}
