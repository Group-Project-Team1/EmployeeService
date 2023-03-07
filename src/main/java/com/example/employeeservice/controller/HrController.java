package com.example.employeeservice.controller;
import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.EmployeeSummary;
import com.example.employeeservice.domain.response.ResponseHandler;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/hr")
public class HrController {
    @Autowired
    private HrHomepageService hrHomepageService;

    @Autowired
    private HrEmployeeProfilesService hrEmployeeProfilesService;

    @Autowired
    private HrHousingService hrHousingService;


    //3. Home Page
    // TODO: exception to be handled
    @GetMapping("/home")
    public ResponseEntity<Object> findAllVisaStatus(@PathParam("page") int page, @PathParam("itemsPerPage") int itemsPerPage) {
        List<VisaStatusResponse> visaStatusResponses = hrHomepageService.findAllVisaStatusPaginated(page, itemsPerPage);
        return ResponseHandler.generateResponse(
                "All active visa status.",
                HttpStatus.OK,
                visaStatusResponses
        );
    }

    //4
    // add an employee
    @PostMapping("/add")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        hrEmployeeProfilesService.saveEmployee(employee);
        return ResponseHandler.generateResponse(
                "Added an employee successfully.",
                HttpStatus.OK,
                employee
        );
    }

    //4.b. summary view
    @GetMapping("/view/{page}")
    public ResponseEntity<Object> findAllEmployeesSummaries(@PathVariable int page, @PathParam("itemsPerPage") int itemsPerPage) {
        List<EmployeeSummary> employeeSummaries = hrEmployeeProfilesService.findAllEmployeesSummaries(page, itemsPerPage);
        return ResponseHandler.generateResponse(
                "Page " + page + " of all employees' summary.",
                HttpStatus.OK,
                employeeSummaries
        );
    }


    @GetMapping("/findById")
    public ResponseEntity<Object> findEmployeeProfileById(@PathParam("id") Integer id) {
        EmployeeProfile employeeProfile = hrEmployeeProfilesService.findEmployeeProfileById(id);
        return ResponseHandler.generateResponse(
                "Found the employee profile.",
                HttpStatus.OK,
                employeeProfile
        );
    }


    // 4.b.1. enter the link to open a new tab that display the entire profile of an employee
    @GetMapping("/findByEmail")
    public ResponseEntity<Object> findEmployeeProfileByEmail(@PathParam("email") String email) {
        EmployeeProfile employeeProfile = hrEmployeeProfilesService.findEmployeeProfileByEmail(email);
        return ResponseHandler.generateResponse(
                "Found the employee profile.",
                HttpStatus.OK,
                employeeProfile
        );
    }

    //4.c filtering employees profiles on email
    @GetMapping("/filterEmail")
    public ResponseEntity<Object> findEmployeeProfilesFilteredOnEmail(@PathParam("emailSeg") String emailSeg) {
        List<EmployeeProfile> employeeProfiles = hrEmployeeProfilesService.findEmployeeProfilesFilteredOnEmail(emailSeg);
        return ResponseHandler.generateResponse(
                "Found employee profiles filtered on the email segment provided.",
                HttpStatus.OK,
                employeeProfiles
        );
    }

    //4.c filtering employees profiles on name (last name, first name, middle name, preferred name)
    @GetMapping("/filterName")
    public ResponseEntity<Object> findEmployeeProfilesFilteredOnName(@PathParam("nameSeg") String nameSeg) {
        List<EmployeeProfile> employeeProfiles = hrEmployeeProfilesService.findEmployeeProfilesFilteredOnName(nameSeg);
        return ResponseHandler.generateResponse(
                "Found employee profiles filtered on the name segment provided.",
                HttpStatus.OK,
                employeeProfiles
        );
    }

    // 6.b.iii
    @GetMapping("/housing")
    public ResponseEntity<Object> findEmployeeSummariesByHouseId(@PathParam("houseId") Integer houseId) {
        List<EmployeeSummary> employeeSummaries = hrHousingService.findEmployeeSummariesByHouseId(houseId);
        return ResponseHandler.generateResponse(
                "Found " + employeeSummaries.size() + " employees of houseId " + houseId,
                HttpStatus.OK,
                employeeSummaries
        );
    }
}
