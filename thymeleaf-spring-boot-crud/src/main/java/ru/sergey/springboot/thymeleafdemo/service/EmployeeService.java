package ru.sergey.springboot.thymeleafdemo.service;

import ru.sergey.springboot.thymeleafdemo.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}
