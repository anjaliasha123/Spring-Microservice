package com.anjali.springboot.organizationservice.service;

import com.anjali.springboot.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrgByCode(String code);

}
