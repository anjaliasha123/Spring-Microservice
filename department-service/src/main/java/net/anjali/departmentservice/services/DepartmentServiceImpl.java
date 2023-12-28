package net.anjali.departmentservice.services;

import lombok.AllArgsConstructor;
import net.anjali.departmentservice.dto.DepartmentDto;
import net.anjali.departmentservice.entity.Department;
import net.anjali.departmentservice.mapper.DepartmentMapper;
import net.anjali.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
      //        convert department dto into dept jPA entity
//        Department dept = new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );
        Department dept = DepartmentMapper.INSTANCE.departmentDtoToDepartment(departmentDto);
        Department savedDept = departmentRepository.save(dept);
        System.out.println("saved dept id:"+savedDept.getId());
//        DepartmentDto savedDto = new DepartmentDto(
//                savedDept.getId(),
//                savedDept.getDepartmentName(),
//                savedDept.getDepartmentDescription(),
//                savedDept.getDepartmentCode()
//        );

        DepartmentDto savedDto = DepartmentMapper.INSTANCE.departmentToDepartmentDto(savedDept);
        return savedDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department deptByCode = this.departmentRepository.findByDepartmentCode(code);
        DepartmentDto codeDto = DepartmentMapper.INSTANCE.departmentToDepartmentDto(deptByCode);
        return codeDto;
    }
}
