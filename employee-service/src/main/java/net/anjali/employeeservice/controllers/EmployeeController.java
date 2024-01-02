package net.anjali.employeeservice.controllers;

import lombok.AllArgsConstructor;
import net.anjali.employeeservice.dto.APIResponseDto;
import net.anjali.employeeservice.dto.EmployeeDto;
import net.anjali.employeeservice.entity.Employee;
import net.anjali.employeeservice.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        System.out.println("************ controller"+employeeDto.getDepartmentCode());
        EmployeeDto savedEmployeeDto = this.employeeService.saveEmployee(employeeDto);
        System.out.println("************ controller after "+savedEmployeeDto.getDepartmentCode());
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<APIResponseDto> fetchEmployee(@PathVariable("id") Long id){
        APIResponseDto dto = this.employeeService.getEmployee(id);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }
}
