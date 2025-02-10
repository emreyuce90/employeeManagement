package com.emre.employeemanagement.services;

import com.emre.employeemanagement.dtos.EmployeeDto;

import java.util.List;

public interface IEmployeeService {
    public List<EmployeeDto> getAllEmployees();
    public void addEmployee(EmployeeDto employeeDto);
    public EmployeeDto getEmployeeById(Long id);
    public void updateEmployee(EmployeeDto employeeDto);
    public void deleteEmployee(Long id);
    public List<EmployeeDto> getEmployeesBySearch(String search);
}
