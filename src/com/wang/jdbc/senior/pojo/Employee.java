package com.wang.jdbc.senior.pojo;

//类名就是数据库中t_emp表t_后面的emp的全拼
public class Employee {

    //每一个属性都对应t_emp表中一个列
    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer empAge;


    public Employee() {
    }

    public Employee(Integer empId, String empName, Double empSalary, Integer empAge) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
    }


    /**
     * 获取
     * @return empId
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置
     * @param empId
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取
     * @return empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置
     * @param empName
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * 获取
     * @return empSalary
     */
    public Double getEmpSalary() {
        return empSalary;
    }

    /**
     * 设置
     * @param empSalary
     */
    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    /**
     * 获取
     * @return empAge
     */
    public Integer getEmpAge() {
        return empAge;
    }

    /**
     * 设置
     * @param empAge
     */
    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public String toString() {
        return "Employee{empId = " + empId + ", empName = " + empName + ", empSalary = " + empSalary + ", empAge = " + empAge + "}";
    }
}
