package net.anjali.employeeservice.services;

import net.anjali.employeeservice.dto.APIResponseDto;
import net.anjali.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployee(Long id);
}
