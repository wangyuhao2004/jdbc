package com.wang.jdbc.senior.dao;

import com.wang.jdbc.senior.pojo.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeDao这个类对应的是t_emp这张表对应的增删改查的操作
 */
public interface EmployeeDao {

    /**
     * 数据库对应的查询所有的操作
     * @return 表中所有的数据
     */
    List<Employee> selectAll() throws Exception;

    /**
     * 数据库对应的根据empId查询单个员工信息的操作
     * @param empId 要查询的员工的empId
     * @return 一个员工对象(一行数据)
     */
    Employee selectByEmpId(Integer empId) throws Exception;

    /**
     * 数据库对应的增加操作
     * @param employee ORM思想中一个员工对象
     * @return 受影响的行数
     */
    int insert(Employee employee) throws SQLException;

    /**
     * 数据库中对应的修改操作
     * @param employee ORM思想中一个员工对象
     * @return 受影响的行数
     */
    int update(Employee employee) throws SQLException;

    /**
     * 数据库中对应的删除操作
     * @param empId 要删除的员工信息的empId
     * @return 受影响的行数
     */
    int delete(Integer empId) throws SQLException;
 }
