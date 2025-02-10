package com.emre.employeemanagement.services.impl;

import com.emre.employeemanagement.dtos.EmployeeDto;
import com.emre.employeemanagement.entities.Employee;
import com.emre.employeemanagement.repositories.IEmployeeRepository;
import com.emre.employeemanagement.services.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = this.employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
        employeeList.forEach(employee -> employeeDtoList.add(new EmployeeDto(employee.getId(), employee.getName(), employee.getSurname(),
                employee.getEmail())));

        return employeeDtoList;
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        employeeRepository.save(new Employee(employeeDto.getName(),employeeDto.getSurname(),employeeDto.getEmail()));
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail());
            return employeeDto;
        }
        return null;
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId()).get();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeesBySearch(String search) {
        List<Employee> employeesBySearch = employeeRepository.getEmployeesBySearch(search);
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeesBySearch.forEach(employee ->employeeDtoList.add(new EmployeeDto(employee.getId(), employee.getName(),
                employee.getSurname(), employee.getEmail())));
        return employeeDtoList;
    }
}
