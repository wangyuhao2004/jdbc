package com.wang.jdbc.senior;

import com.wang.jdbc.senior.dao.impl.EmployeeDaoImpl;
import com.wang.jdbc.senior.pojo.Employee;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class EmployeeDaoImplTest {

    //测试EmployeeDao接口的实现类EmployeeDaoImpl的功能

    @Test
    public void testSelectAll() throws Exception {
       //查询全部数据
        EmployeeDaoImpl edi = new EmployeeDaoImpl();
        List<Employee> employeeList = edi.selectAll();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void testSelectByEmpId() throws Exception {
        EmployeeDaoImpl edi = new EmployeeDaoImpl();
        System.out.println("请输入你要查询的员工ID");
        Scanner sc = new Scanner(System.in);
        int empId = sc.nextInt();
        Employee employee = edi.selectByEmpId(empId);
        System.out.println(employee);

    }

    @Test
    public void testInsert() throws SQLException {
        EmployeeDaoImpl edi = new EmployeeDaoImpl();
        Employee employee = new Employee(null,"jack",234.5,24);
        int row = edi.insert(employee);
        if (row > 0 ){
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }
    
    @Test
    public void testDelete() throws SQLException {
        EmployeeDaoImpl edi = new EmployeeDaoImpl();
        System.out.println("请输入你要删除的员工信息的id");
        Scanner sc = new Scanner(System.in);
        int empId = sc.nextInt();
        int result = edi.delete(empId);
        if (result > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }

    }

}
