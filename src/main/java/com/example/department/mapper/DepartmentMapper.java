package com.example.department.mapper;

import com.example.department.entity.DepartmentEntity;
import com.example.department.model.DepartmentList;
import com.example.department.model.DepartmentReq;
import com.example.department.model.DepartmentRes;
import com.example.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentMapper {
    private final DepartmentRepository repo;

    @Autowired
    public DepartmentMapper(DepartmentRepository repo) {
        this.repo = repo;
    }

    public DepartmentRes mapDepartmentResFromDepartmentEntity(DepartmentEntity from) {
        DepartmentRes to = new DepartmentRes();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setDesc(from.getDesc());

        return to;
    }

    public DepartmentList mapDepartmentListFromDepartmentEntities(List<DepartmentEntity> from) {
        DepartmentList to = new DepartmentList();

        from.stream().forEach(department -> {
            to.add(mapDepartmentResFromDepartmentEntity(department));
        });

        return to;
    }

    public DepartmentEntity mapDepartmentEntityFromDepartmentReq(DepartmentReq from) {
        DepartmentEntity to = new DepartmentEntity();

        to.setId(UUID.randomUUID().toString());
        to.setName(from.getName());
        to.setDesc(from.getDesc());

        return to;
    }

    public DepartmentEntity mapDepartmentEntityFromDepartmentReq(String id, DepartmentReq from) {
        DepartmentEntity to = repo.getById(id);

        to.setName(from.getName());
        to.setDesc(from.getDesc());

        return to;
    }
}
