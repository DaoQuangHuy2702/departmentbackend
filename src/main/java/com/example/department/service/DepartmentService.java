package com.example.department.service;

import com.example.department.entity.DepartmentEntity;
import com.example.department.entity.EmployeeEntity;
import com.example.department.mapper.DepartmentMapper;
import com.example.department.model.DepartmentList;
import com.example.department.model.DepartmentReq;
import com.example.department.model.DepartmentRes;
import com.example.department.repository.DepartmentRepository;
import com.example.department.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentMapper mapper;
    private final DepartmentRepository repo;
    private final EmployeeRepository empRepo;

    @Autowired
    public DepartmentService(DepartmentMapper mapper, DepartmentRepository repo, EmployeeRepository empRepo) {
        this.mapper = mapper;
        this.repo = repo;
        this.empRepo = empRepo;
    }

    public DepartmentList getAllDepartment() {
        DepartmentList list = new DepartmentList();

        list = mapper.mapDepartmentListFromDepartmentEntities(repo.findAll());

        return list;
    }

    public DepartmentRes addDepartment(DepartmentReq deptReq) {
        DepartmentEntity deptEntity = mapper.mapDepartmentEntityFromDepartmentReq(deptReq);
        DepartmentEntity saved = repo.save(deptEntity);

        return mapper.mapDepartmentResFromDepartmentEntity(saved);
    }

    public DepartmentRes getDepartment(String id) {
        DepartmentEntity deptEntity = repo.getById(id);
        DepartmentRes deptRes = mapper.mapDepartmentResFromDepartmentEntity(deptEntity);

        return deptRes;
    }

    public DepartmentRes updateDepartment(String id, DepartmentReq deptReq) {
        DepartmentEntity deptEntity = mapper.mapDepartmentEntityFromDepartmentReq(id, deptReq);
        DepartmentEntity saved = repo.save(deptEntity);

        return mapper.mapDepartmentResFromDepartmentEntity(saved);
    }

    public void deleteDepartment(String id) {
        List<EmployeeEntity> employeeEntityList = empRepo.findAllByDeptId(id);
        employeeEntityList.stream().forEach(employeeEntity -> {
            empRepo.delete(employeeEntity);
        });
        DepartmentEntity deptEntity = repo.getById(id);

        repo.delete(deptEntity);
    }
}
