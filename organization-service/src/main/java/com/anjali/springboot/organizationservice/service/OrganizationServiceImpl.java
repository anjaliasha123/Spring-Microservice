package com.anjali.springboot.organizationservice.service;

import com.anjali.springboot.organizationservice.dto.OrganizationDto;
import com.anjali.springboot.organizationservice.entity.Organization;
import com.anjali.springboot.organizationservice.mapper.OrganizationMapper;
import com.anjali.springboot.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    @Autowired
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        OrganizationDto saved = OrganizationMapper.mapToOrganizationDto(savedOrganization);
        return saved;
    }

    @Override
    public OrganizationDto getOrgByCode(String code) {
        Organization org = this.organizationRepository.findByOrganizationCode(code);
        OrganizationDto orgDto = OrganizationMapper.mapToOrganizationDto(org);
        return orgDto;
    }
}
