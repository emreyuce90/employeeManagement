package com.emre.employeemanagement.controller;

import com.emre.employeemanagement.dtos.EmployeeDto;
import com.emre.employeemanagement.entities.Employee;
import com.emre.employeemanagement.services.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    private IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getEmployees(Model model, @RequestParam(name = "searchText", required = false) String searchText) {
        List<EmployeeDto> employeeDtoList;

        if (searchText == null || searchText.isEmpty()) {
                employeeDtoList = employeeService.getAllEmployees();
        }else{
            employeeDtoList = employeeService.getEmployeesBySearch(searchText);
            model.addAttribute("searchText", searchText);
        }

        model.addAttribute("employees", employeeDtoList);
        return "employees";
    }

    @GetMapping("/add-employee")
    public String addEmployeeForm(Model model) {
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee", employee);
        return "add-employee";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute("employee") EmployeeDto employeeDto) {
        employeeService.addEmployee(employeeDto);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        EmployeeDto employeeById = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employeeById);
        return "employee-detail";
    }

    @GetMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable Long id, Model model) {
        EmployeeDto employeeById = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employeeById);
        return "update-employee";
    }

    @PostMapping("/employees/update/")
    public String updateEmployee(@ModelAttribute("employee") EmployeeDto employeeDto) {
        employeeService.updateEmployee(employeeDto);
        return "redirect:/employees";
    }

    @GetMapping("employee/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
