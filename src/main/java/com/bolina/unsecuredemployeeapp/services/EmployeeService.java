package com.bolina.unsecuredemployeeapp.services;


import com.bolina.unsecuredemployeeapp.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployee(Long id);
    boolean deleteEmployee(Long id);
    Employee updateEmployee(Long id, Employee employee);

}
