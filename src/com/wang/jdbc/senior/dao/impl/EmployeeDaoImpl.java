package com.wang.jdbc.senior.dao.impl;

import com.wang.jdbc.senior.dao.BaseDao;
import com.wang.jdbc.senior.dao.EmployeeDao;
import com.wang.jdbc.senior.pojo.Employee;
import com.wang.jdbc.senior.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDao接口的实现类
 */
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
    @Override
    public List<Employee> selectAll() throws Exception {
        //编写sql语句
        //注意这里要给列名起别名,不然对应不上pojo里面的属性名
        String sql = "select emp_id empId, emp_name empName, emp_salary empSalary, emp_age empAge from atguigu.t_emp;";
        //调用BaseDao中的通用查询方法,因为这里EmployeeImpl类继承了BaseDao,所以可以直接使用
        return executeQuery(Employee.class, sql, null);
    }

    @Override
    public Employee selectByEmpId(Integer empId) throws Exception {
        //编写sql语句
        String sql = "select emp_id empId, emp_name empName, emp_salary empSalary, emp_age empAge from atguigu.t_emp where emp_id = ?;";
        return executeQueryBean(Employee.class, sql, empId);
    }

    @Override
    public int insert(Employee employee) throws SQLException {
        //编写sql
        String sql = "insert into t_emp(emp_name,emp_salary,emp_age) values (?,?,?);";
        return executeUpdate(sql, employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAge());
    }

    @Override
    public int update(Employee employee) throws SQLException {
        //同上,思路都差不对
        return 0;
    }

    @Override
    public int delete(Integer empId) throws SQLException {
        //同上,思路都差不对
        return 0;
    }
}
