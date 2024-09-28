package com.wang.jdbc.advanced;

import com.wang.jdbc.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCAdvanced {

    String url = "jdbc:mysql:///atguigu";
    String username = "root";
    String password = "123456";


    @Test
    public void testORM() throws Exception{
        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from atguigu.t_emp where emp_id = ?;");
        System.out.println("请输入要查询的员工id");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;
        while (resultSet.next()) {
            //创建学生对象
            employee = new Employee();

            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
        }

        System.out.println(employee);

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testORMList() throws Exception{
        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from atguigu.t_emp;");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = null;
        while (resultSet.next()) {
            //创建学生对象
            employee = new Employee();

            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);

            employeeList.add(employee);
        }

        for (Employee employee1 : employeeList) {
            System.out.println(employee1);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //主键回显
    @Test
    public void testReturnPK() throws Exception {
        //获取连接
        Connection connection = DriverManager.getConnection(url, username, password);

        //预编译sql语句,告诉preparedStatement,返回新增数据的主键列的值
        String sql = "insert into atguigu.t_emp(emp_name, emp_salary, emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //创建对象,将对象的属性值填充在?占位符上
        Employee employee = new Employee(null,"jack" ,123.45 ,23 );
        preparedStatement.setString(1,employee.getEmpName());
        preparedStatement.setDouble(2,employee.getEmpSalary());
        preparedStatement.setInt(3,employee.getEmpAge());

        //执行sql语句,并返回结果
        int result = preparedStatement.executeUpdate();
        System.out.println(result);

        //处理结果,获取当前新增数据主键值的列,回显到java当中employee对象的empId属性上
        ResultSet generatedKeys = null;
        if (result > 0) {
            System.out.println("插入成功");
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int empId = generatedKeys.getInt(1);
                //添加到employee对象中
                employee.setEmpId(empId);
                //打印
                System.out.println(employee);
            }
        } else {
            System.out.println("插入失败");
        }


        //资源释放
        if (generatedKeys != null) {
            generatedKeys.close();
        }
        preparedStatement.close();
        connection.close();
    }

    //批量添加(普通方法.耗时长)
    @Test
    public void testMoreInsert() throws Exception{

        Connection connection = DriverManager.getConnection(url, username, password);

        //编写sql语句
        String sql = "insert into atguigu.t_emp(emp_name, emp_salary, emp_age) value(?,?,?);";

        //创建预编译的PreparedStatement,传入sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1,"marry" + i);
            preparedStatement.setDouble(2,110.1 + i);
            preparedStatement.setInt(3,20 + i);

            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        preparedStatement.close();
        connection.close();
    }

    //批量添加
    @Test
    public void testBatch() throws Exception{

        //获取连接的时候加上rewriteBatchedStatements=true,即可打开批量操作
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?rewriteBatchedStatements=true", username, password);

        //编写sql语句
        /*
        * 想要实现批量操作,需要使用values
        * 并且sql语句后面不能加;
        * 最终会变成   ...values (?,?,?) , (?,?,?) ... (?,?,?) .., (?,?,?);
        * */
        String sql = "insert into atguigu.t_emp(emp_name, emp_salary, emp_age) values(?,?,?)";

        //创建预编译的PreparedStatement,传入sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1,"marry" + i);
            preparedStatement.setDouble(2,110.1 + i);
            preparedStatement.setInt(3,20 + i);

           preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        preparedStatement.close();
        connection.close();
    }
}
