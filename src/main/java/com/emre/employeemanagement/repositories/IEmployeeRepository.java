package com.emre.employeemanagement.repositories;

import com.emre.employeemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee e WHERE e.name LIKE %:keyword% or e.surname LIKE %:keyword%  or e.email " +
            "LIKE %:keyword%" +
            "  "   , nativeQuery = true)
    List<Employee> getEmployeesBySearch(String keyword);
}
