package ru.sergey.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergey.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //sort by last name

    List<Employee> findAllByOrderByLastNameAsc();
}
