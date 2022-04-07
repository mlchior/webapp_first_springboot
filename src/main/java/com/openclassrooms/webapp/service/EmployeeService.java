package com.openclassrooms.webapp.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;


@Data
@Service
public class EmployeeService {
    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee();
    }
    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }
    public void deleEmployee(final int id) {
        employeeProxy.deleteEmployee(id);
    }
    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;
        //regle de gesion pour mettre le nom de famille en majuscule
        employee.setLastName(employee.getLastName().toUpperCase());

        if(employee.getId() == 0 ) {
            //id = 0 => creation
            savedEmployee = employeeProxy.createEmployee(employee);
        } else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }
        return savedEmployee;
    }
}
