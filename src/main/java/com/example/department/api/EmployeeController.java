package com.example.department.api;

import com.example.department.model.EmployeeList;
import com.example.department.model.EmployeeReq;
import com.example.department.model.EmployeeRes;
import com.example.department.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department/backend/v1")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/employees",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<EmployeeList> getAllEmployee() {
        EmployeeList response = service.getAllEmployee();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/departments/{id}/employees",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<EmployeeList> getAllEmployeeByDepartmentId(@PathVariable("id") String id) {
        EmployeeList response = service.getAllEmployeeByDepartmentId(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<EmployeeRes> addEmployee(@RequestBody EmployeeReq empReq) {
        EmployeeRes response = service.addEmployee(empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<EmployeeRes> getEmployee(@PathVariable("id") String id) {
        EmployeeRes response = service.getEmployee(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<EmployeeRes> putEmployee(@PathVariable("id") String id, @RequestBody EmployeeReq empReq) {
        EmployeeRes response = service.updateEmployee(id, empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeRes> deleteEmployee(@PathVariable("id") String id) {
        service.deleteEmployee(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
