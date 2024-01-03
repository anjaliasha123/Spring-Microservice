package net.anjali.departmentservice.controllers;

import lombok.AllArgsConstructor;
import net.anjali.departmentservice.dto.DepartmentDto;
import net.anjali.departmentservice.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/departments")
@AllArgsConstructor
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //POST Mapping to save department
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDto = this.departmentService.saveDepartment(departmentDto);
        System.out.println("saved dept dto id:"+savedDto.getId());
        return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
    }
    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("id") String code){
        DepartmentDto foundDto = this.departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(foundDto, HttpStatus.OK);
    }
}
