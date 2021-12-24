package com.example.department.validator;

import com.example.department.exception.BadRequestException;
import com.example.department.exception.EntityNotFoundException;
import com.example.department.model.DepartmentReq;
import com.example.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentValidator {
    private static final String DEPARTMENT_DOES_NOT_EXIST = "Department does not exist";
    private static final String NAME_REQUEST = "Department name is required";
    private final DepartmentRepository repo;

    @Autowired
    public DepartmentValidator(DepartmentRepository repo) {
        this.repo = repo;
    }

    private void validateDepartmentExist(String id) {
        if(repo.existsById(id)) {
            return;
        }
        throw new EntityNotFoundException(DEPARTMENT_DOES_NOT_EXIST);
    }

    private void checkRequiredField(String value, String errorMSg) {
        if(value == null || value.trim().isEmpty()) {
            throw new BadRequestException(errorMSg);
        }
    }

    public void validateAddDepartment(DepartmentReq request) {
        checkRequiredField(request.getName(), NAME_REQUEST);
    }

    public void validatedUpdateDepartment(String id, DepartmentReq request) {
        validateDepartmentExist(id);
        checkRequiredField(request.getName(), NAME_REQUEST);
    }
}
