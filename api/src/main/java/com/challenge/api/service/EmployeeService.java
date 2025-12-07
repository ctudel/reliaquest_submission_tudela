package com.challenge.api.service;

import com.challenge.api.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service layer to secure API busniess logic. No JPA repo or persistence layer was considered durig implementation
 */
@Service
public class EmployeeService {
    // Data structure to imitate backend for this challenge
    Map<UUID, Employee> employees = new HashMap<UUID, Employee>();

    /**
     * @implSpec The implementation uses mock db data structure to simulate output and return values that are similar to if we used a JPA repository and connected persistent layer
     * @return One or more Employees
     */
    public List<Employee> getAll() {
        if (employees.isEmpty()) throw new Error("Could not find any employees");
        return new ArrayList<>(employees.values());
    }

    /**
     * @implSpec Simulates the map structure of a database for object retrieval based on a primary key (uuid in this case)
     * @param uuid Employee UUID
     * @return Found Employee
     */
    public Employee getById(UUID uuid) {
        if (employees.containsKey(uuid)) {
            return employees.get(uuid);
        }
        throw new Error("Employee does not exist");
    }

    /**
     * @implSpec Stores employee in the data structure
     * @param employee Employee to create
     * @return Newly created Employee
     */
    public Employee create(Employee employee) {
        UUID newEmployeeUUID = employee.getUuid();
        if (employees.containsKey(newEmployeeUUID)) throw new Error("Employee already exists");
        Object returnVal = employees.put(newEmployeeUUID, employee); // imitate db insert operation
        if (returnVal == null) throw new Error("Failed to insert new employee"); // simulate any db errors
        return employees.get(newEmployeeUUID); // encapsulation if needed
    }

}
