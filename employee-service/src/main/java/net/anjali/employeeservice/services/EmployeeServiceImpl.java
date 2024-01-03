package net.anjali.employeeservice.services;

import lombok.AllArgsConstructor;
import net.anjali.employeeservice.dto.APIResponseDto;
import net.anjali.employeeservice.dto.DepartmentDto;
import net.anjali.employeeservice.dto.EmployeeDto;
import net.anjali.employeeservice.entity.Employee;
import net.anjali.employeeservice.mapper.EmployeeMapper;
import net.anjali.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
//    @Autowired
//    private RestTemplate restTemplate;
//
    APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto);
        System.out.println("************ service before save "+employeeDto.getDepartmentCode());
        Employee savedEmployee = this.employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(savedEmployee);
        System.out.println("************ service after save "+savedEmployeeDto.getDepartmentCode());
        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id).get();
//        ResponseEntity<DepartmentDto> responseEntity =  restTemplate.getForEntity(
//                "http://localhost:8080/api/departments/department/"+employee.getDepartmentCode(),
//                DepartmentDto.class
//                );
//        DepartmentDto departmentDto = responseEntity.getBody();
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/department/"+employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
