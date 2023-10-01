package com.bolina.unsecuredemployeeapp.services;

import com.bolina.unsecuredemployeeapp.entities.EmployeeEntity;
import com.bolina.unsecuredemployeeapp.models.Employee;
import com.bolina.unsecuredemployeeapp.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        log.info(employee.toString());
        BeanUtils.copyProperties(employee,employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(
                employeeEntity -> new Employee(employeeEntity.getId(),employeeEntity.getFirstName(),
                        employeeEntity.getLastName(),employeeEntity.getEmail(),employeeEntity.isActive(),
                        employeeEntity.getLevel())
        ).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeRepository.delete( employeeEntity  );
        log.warn("The Employee has been deleted!");
        return true;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        log.info("The Employee with id: {0}, has been updated!", employeeEntity.getId() );
        return employee;
    }
}
