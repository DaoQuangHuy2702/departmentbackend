package com.example.department.service;

import com.example.department.entity.EmployeeEntity;
import com.example.department.mapper.EmployeeMapper;
import com.example.department.model.EmployeeList;
import com.example.department.model.EmployeeReq;
import com.example.department.model.EmployeeRes;
import com.example.department.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService{
  private final EmployeeMapper mapper;
    private final EmployeeRepository repo;

    @Autowired
    public EmployeeService(EmployeeMapper mapper, EmployeeRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public EmployeeList getAllEmployee() {
        EmployeeList list = new EmployeeList();

        list = mapper.mapEmployeeListFromEmployeeEntities(repo.findAll());

        return list;
    }

    public EmployeeList getAllEmployeeByDepartmentId(String deptId){
        EmployeeList list = new EmployeeList();

        list = mapper.mapEmployeeListFromEmployeeEntities(repo.findAllByDeptId(deptId));

        return list;
    }

    public EmployeeRes addEmployee(EmployeeReq empReq) {
        EmployeeEntity empEntity = mapper.mapEmployeeEntityFromEmployeeReq(empReq);
        EmployeeEntity saved = repo.save(empEntity);

        return mapper.mapEmployeeResFromEmployeeEntity(saved);
    }

    public EmployeeRes getEmployee(String id) {
        EmployeeEntity empEntity = repo.getById(id);
        EmployeeRes empRes = mapper.mapEmployeeResFromEmployeeEntity(empEntity);

        return empRes;
    }

    public EmployeeRes updateEmployee(String id, EmployeeReq empReq) {
        EmployeeEntity empEntity = mapper.mapEmployeeEntityFromEmployeeReq(id, empReq);
        EmployeeEntity saved = repo.save(empEntity);

        return mapper.mapEmployeeResFromEmployeeEntity(saved);
    }

    public void deleteEmployee(String id) {
        EmployeeEntity empEntity = repo.getById(id);

        repo.delete(empEntity);
    }
}
