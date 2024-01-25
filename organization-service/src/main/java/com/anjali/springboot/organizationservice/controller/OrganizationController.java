package com.anjali.springboot.organizationservice.controller;

import com.anjali.springboot.organizationservice.dto.OrganizationDto;
import com.anjali.springboot.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
//    REST API to save organization
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto orgDto = this.organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(orgDto, HttpStatus.CREATED);
    }
//REST API to get organization by org code
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String code){
        OrganizationDto dto = organizationService.getOrgByCode(code);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
