package com.wang.jdbc.base;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCOperation {
    /*
     * 编写CURD的一些操作
     * */
    //该全局变量为来连接数据库的一些数据
    String url = "jdbc:mysql:///atguigu";
    String username = "root";
    String password = "123456";

    //查询单行单列的数据
    @Test
    public void testQuerySingleRowAndCol() throws Exception {
        //注册驱动
        DriverManager.registerDriver(new Driver());
        //创建连接数据库的对象
        Connection connection = DriverManager.getConnection(url,username,password);
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as '总人数' from atguigu.t_emp");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int count = resultSet.getInt("总人数");
            System.out.println(count);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    //查询单行多列的数据
    @Test
    public void testQuerySingleRow() throws Exception {
        //注册驱动
        DriverManager.registerDriver(new Driver());
        //获取连接
        Connection connection = DriverManager.getConnection(url,username,password);

        //预编译sql语句,获取preparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id, emp_name, emp_salary, emp_age from atguigu.t_emp where emp_id = ?");

        //为占位符?赋值,执行,并接受结果
        System.out.println("请输入要查询人的id");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        //处理结果
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        //释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //查询多行多列的数据
    @Test
    public void testQueryMoreRow() throws Exception{
        Connection connection = DriverManager.getConnection(url,username,password);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from atguigu.t_emp where emp_age > ?");

        System.out.println("请输入要查询的年龄范围(大于)");
        Scanner sc = new  Scanner(System.in);
        int age = sc.nextInt();
        preparedStatement.setInt(1,age);
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

    //新增数据
    @Test
    public void testInsert() throws Exception{
        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("insert into atguigu.t_emp(emp_name, emp_salary, emp_age) values (?,?,?)");

        preparedStatement.setString(1,"rose");
        preparedStatement.setDouble(2,345.6);
        preparedStatement.setInt(3,24);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }

        preparedStatement.close();
        connection.close();
    }

    //修改数据
    @Test
    public void testUpdate() throws Exception{
        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("update atguigu.t_emp set emp_salary = ? where emp_id = ?");
        System.out.println("请输入要修改的工资金额和对应员工的id");
        Scanner sc = new Scanner(System.in);
        double salary = sc.nextDouble();
        int id = sc.nextInt();
        preparedStatement.setDouble(1,salary);
        preparedStatement.setInt(2,id);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
        preparedStatement.close();
        connection.close();
    }

    //删除数据
    @Test
    public void testDelete() throws Exception {

        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("delete from atguigu.t_emp where emp_id = ?");
        System.out.println("请输入要删除的员工的id");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        preparedStatement.setDouble(1,id);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        preparedStatement.close();
        connection.close();
    }

}
