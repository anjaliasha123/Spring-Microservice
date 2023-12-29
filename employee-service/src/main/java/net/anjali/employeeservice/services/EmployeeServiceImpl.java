package net.anjali.employeeservice.services;

import lombok.AllArgsConstructor;
import net.anjali.employeeservice.dto.EmployeeDto;
import net.anjali.employeeservice.entity.Employee;
import net.anjali.employeeservice.mapper.EmployeeMapper;
import net.anjali.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto);
        Employee savedEmployee = this.employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id).get();
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);
        return employeeDto;
    }
}
