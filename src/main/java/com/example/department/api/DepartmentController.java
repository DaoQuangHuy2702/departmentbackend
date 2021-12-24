package com.example.department.api;

import com.example.department.model.DepartmentList;
import com.example.department.model.DepartmentReq;
import com.example.department.model.DepartmentRes;
import com.example.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department/backend/v1")
public class DepartmentController {
    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/departments",
                    produces = {"application/json"},
                    method = RequestMethod.GET)
    public ResponseEntity<DepartmentList> getAllDepartment() {
        DepartmentList response = service.getAllDepartment();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/departments",
                    consumes = {"application/json"},
                    produces = {"application/json"},
                    method = RequestMethod.POST)
    public ResponseEntity<DepartmentRes> addDepartment(@RequestBody DepartmentReq deptReq) {
        DepartmentRes response = service.addDepartment(deptReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/departments/{id}",
                    produces = {"application/json"},
                    method = RequestMethod.GET)
    public ResponseEntity<DepartmentRes> getDepartment(@PathVariable("id") String id) {
        DepartmentRes response = service.getDepartment(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/departments/{id}",
                    consumes = {"application/json"},
                    produces = {"application/json"},
                    method = RequestMethod.PUT)
    public ResponseEntity<DepartmentRes> putDepartment(@PathVariable("id") String id, @RequestBody DepartmentReq deptReq) {
        DepartmentRes response = service.updateDepartment(id, deptReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/departments/{id}",
                    produces = {"application/json"},
                    method = RequestMethod.DELETE)
    public ResponseEntity<DepartmentRes> deleteDepartment(@PathVariable("id") String id) {
        service.deleteDepartment(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
