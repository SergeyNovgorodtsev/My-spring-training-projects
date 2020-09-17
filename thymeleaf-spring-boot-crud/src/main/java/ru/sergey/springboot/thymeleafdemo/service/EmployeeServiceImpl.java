package ru.sergey.springboot.thymeleafdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.springboot.thymeleafdemo.dao.EmployeeRepository;
import ru.sergey.springboot.thymeleafdemo.entity.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee;
        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Employee with id " + id +  " not found");
        }
        return employee;
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
