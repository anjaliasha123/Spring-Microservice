package net.anjali.employeeservice.services;

import net.anjali.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT")
public interface APIClient {
    @GetMapping("api/departments/department/{id}")
    DepartmentDto getDepartmentByCode(@PathVariable("id") String code);
}
