package com.example.department.mapper;

import com.example.department.entity.EmployeeEntity;
import com.example.department.model.EmployeeList;
import com.example.department.model.EmployeeReq;
import com.example.department.model.EmployeeRes;
import com.example.department.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeMapper {
    private final EmployeeRepository repo;

    @Autowired
    public EmployeeMapper(EmployeeRepository repo) {
        this.repo = repo;
    }

    public EmployeeRes mapEmployeeResFromEmployeeEntity(EmployeeEntity from) {
        EmployeeRes to = new EmployeeRes();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setEmailId(from.getEmailId());
        to.setDeptId(from.getDeptId());

        return to;
    }

    public EmployeeList mapEmployeeListFromEmployeeEntities(List<EmployeeEntity> from) {
        EmployeeList to = new EmployeeList();

        from.stream().forEach(employee -> {
            to.add(mapEmployeeResFromEmployeeEntity(employee));
        });

        return to;
    }

    public EmployeeEntity mapEmployeeEntityFromEmployeeReq(EmployeeReq from) {
        EmployeeEntity to = new EmployeeEntity();

        to.setId(UUID.randomUUID().toString());
        to.setName(from.getName());
        to.setEmailId(from.getEmailId());
        to.setDeptId(from.getDeptId());

        return to;
    }

    public EmployeeEntity mapEmployeeEntityFromEmployeeReq(String id, EmployeeReq from) {
        EmployeeEntity to = repo.getById(id);

        to.setName(from.getName());
        to.setEmailId(from.getEmailId());
        to.setDeptId(from.getDeptId());

        return to;
    }
}
