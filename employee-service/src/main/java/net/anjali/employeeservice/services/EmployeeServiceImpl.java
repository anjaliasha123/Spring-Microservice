package net.anjali.employeeservice.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.anjali.employeeservice.dto.APIResponseDto;
import net.anjali.employeeservice.dto.DepartmentDto;
import net.anjali.employeeservice.dto.EmployeeDto;
import net.anjali.employeeservice.dto.OrganizationDto;
import net.anjali.employeeservice.entity.Employee;
import net.anjali.employeeservice.mapper.EmployeeMapper;
import net.anjali.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WebClient webClient;

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
//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployee(Long id) {
        log.info("iNSIDE getEmployee() METHOD");
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
        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/"+employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class).block();
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);

        return apiResponseDto;
    }
    public APIResponseDto getDefaultDepartment(Long id, Exception exception){
        log.info("iNSIDE FALLBACK METHOD");
        Employee employee = this.employeeRepository.findById(id).get();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("B01");
        departmentDto.setDepartmentName("Training dept");
        departmentDto.setDepartmentDescription("Department for training new employees");

        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
