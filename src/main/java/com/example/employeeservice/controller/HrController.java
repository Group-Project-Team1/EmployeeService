package com.example.employeeservice.controller;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.entity.VisaStatus;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.EmployeeSummary;
import com.example.employeeservice.domain.response.ResponseHandler;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.service.EmployeeProfileService;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.EmployeeSummaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hr")
public class HrController {

    private EmployeeService employeeService;
    private EmployeeProfileService employeeProfileService;
    private EmployeeSummaryService employeeSummaryService;

    @Autowired
    public HrController(EmployeeService employeeService, EmployeeProfileService employeeProfileService, EmployeeSummaryService employeeSummaryService) {
        this.employeeService = employeeService;
        this.employeeProfileService = employeeProfileService;
        this.employeeSummaryService = employeeSummaryService;
    }

    //3. Home Page
    // TODO: exception to be handled
    @GetMapping("/home")
    public ResponseEntity<Object> findAllVisaStatus(@PathParam("page") int page, @PathParam("itemsPerPage") int itemsPerPage) {
        List<VisaStatusResponse> visaStatusResponses = employeeService.findAllVisaStatusPaginated(page, itemsPerPage);
        return ResponseHandler.generateResponse(
                "All active visa status.",
                HttpStatus.OK,
                visaStatusResponses
        );
    }

    @GetMapping("/allVisa")
    public List<VisaStatus> findAllVisaStatus() {
        return employeeService.findAllVisaStatus();
    }

    // get -> find
    // find all employees (and HRs)
    @GetMapping("/findAll")
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/findAllPaginated")
    public List<Employee> getAllEmployeesPaginated(@PathParam("page") String page, @PathParam("itemsPerPage") String itemsPerPage) {
        System.out.println(page);
        System.out.println(itemsPerPage);
        return employeeService.getAllEmployeesPaginated(Integer.parseInt(page), Integer.parseInt(itemsPerPage));
    }

    // paginated and sorted
    @GetMapping("/viewAll")
    public List<EmployeeSummary> viewAllEmployeeSummaries(@PathParam("page") String page, @PathParam("itemsPerPage") String itemsPerPage) {
        List<Employee> employees = employeeService.getAllEmployeesPaginated(Integer.parseInt(page), Integer.parseInt(itemsPerPage));
        return employees.stream().sorted((a, b) -> a.getLastName().compareTo(b.getLastName())).map(e -> new EmployeeSummary(e)).collect(Collectors.toList());
    }

    //4.b. summary view
    @GetMapping("/view/{page}")
    public ResponseEntity<Object> findAllEmployeesSummaries(@PathVariable int page, @PathParam("itemsPerPage") int itemsPerPage) {
        List<EmployeeSummary> employeeSummaries = employeeService.findAllEmployeesSummaries(page, itemsPerPage);
        return ResponseHandler.generateResponse(
                "Page " + page + " of all employees' summary.",
                HttpStatus.OK,
                employeeSummaries
        );
    }

    // 4.b.1. enter the link to open a new tab that display the entire profile of an employee
    @GetMapping("/findByEmail")
    public ResponseEntity<Object> findEmployeeProfileByEmail(@PathParam("email") String email) {
        EmployeeProfile employeeProfile = employeeProfileService.findEmployeeProfileByEmail(email);
        return ResponseHandler.generateResponse(
                "Found the employee profile.",
                HttpStatus.OK,
                employeeProfile
        );
    }


//    @GetMapping("/findByEmail")
//    public EmployeeProfile viewEmployeeProfileByEmail(@PathParam("email") String email) {
//        List<Employee> employees = employeeService.findAllEmployees();
//        return new EmployeeProfile(employees.stream().filter(e -> e.getEmail().equals(email)).findFirst().get());
//    }

    @GetMapping("/viewFilteringName")
    public List<EmployeeProfile> viewEmployeeProfilesFilteringName(@PathParam("nameSeg") String nameSeg) {
        List<Employee> employees = employeeService.findAllEmployees();
        return employees.stream()
                .filter(e -> isSeg(nameSeg, e.getLastName()) || isSeg(nameSeg, e.getFirstName()) || isSeg(nameSeg, e.getMiddleName()) || isSeg(nameSeg, e.getPreferredName()))
                .map(e -> new EmployeeProfile(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/viewFilteringEmail")
    public List<EmployeeProfile> viewEmployeeProfilesFilteringEmail(@PathParam("emailSeg") String emailSeg) {
        List<Employee> employees = employeeService.findAllEmployees();
        return employees.stream()
                .filter(e -> isSeg(emailSeg, e.getEmail()))
                .map(e -> new EmployeeProfile(e))
                .collect(Collectors.toList());
    }

    //4.c filtering employees profiles on email
    @GetMapping("/filterEmail")
    public ResponseEntity<Object> findEmployeeProfilesFilteredOnEmail(@PathParam("emailSeg") String emailSeg) {
        List<EmployeeProfile> employeeProfiles = employeeProfileService.findEmployeeProfilesFilteredOnEmail(emailSeg);
        return ResponseHandler.generateResponse(
                "Found employee profiles filtered on the email segment provided.",
                HttpStatus.OK,
                employeeProfiles
        );
    }

//    @GetMapping("/filterEmail")
//    public List<EmployeeProfile> findEmployeeProfilesFilteringEmail(@PathParam("emailSeg") String emailSeg) {
//        List<Employee> employees = employeeService.findEmployeesFilteringEmail(emailSeg);
//        return employees.stream().map(e -> new EmployeeProfile(e)).collect(Collectors.toList());
//    }


    //4.c filtering employees profiles on name (last name, first name, middle name, preferred name)
    @GetMapping("/filterName")
    public ResponseEntity<Object> findEmployeeProfilesFilteredOnName(@PathParam("nameSeg") String nameSeg) {
        List<EmployeeProfile> employeeProfiles = employeeProfileService.findEmployeeProfilesFilteredOnName(nameSeg);
        return ResponseHandler.generateResponse(
                "Found employee profiles filtered on the name segment provided.",
                HttpStatus.OK,
                employeeProfiles
        );
    }
//    @GetMapping("/filterName")
//    public List<EmployeeProfile> findEmployeeProfilesFilteringName(@PathParam("nameSeg") String nameSeg) {
//        List<Employee> employees = employeeService.findEmployeesFilteringName(nameSeg);
//        return employees.stream().map(e -> new EmployeeProfile(e)).collect(Collectors.toList());
//    }

    private boolean isSeg(String s1, String s2) {
        if (s1 == null) return true;
        if (s2 == null) return false;
        int n1 = s1.length(), n2 = s2.length();
        for (int i = 0; i + n1 <= n2; i++) {
            String cur = s2.substring(i, i + n1);
            if (cur.equals(s1)) return true;
        }
        return false;
    }

    // 6.b.iii
    @GetMapping("/housing")
    public ResponseEntity<Object> findEmployeeSummariesByHouseId(@PathParam("houseId") Integer houseId) {
        List<EmployeeSummary> employeeSummaries = employeeSummaryService.findEmployeeSummariesByHouseId(houseId);
        return ResponseHandler.generateResponse(
                "Found " + employeeSummaries.size() + " employees of houseId " + houseId,
                HttpStatus.OK,
                employeeSummaries
        );
    }

//    @GetMapping("/housing")
//    public List<EmployeeSummary> viewAllEmployeesByHouseId(@PathParam("houseId") Integer houseId) {
//        return employeeService.findEmployeesByHouseId(houseId).stream().map(e -> new EmployeeSummary(e)).collect(Collectors.toList());
//    }


//    public List<Employee> findAllEmployeesPaginated() {
//        return employeeService.findAllEmployees();
//    }
//
//    public List<Employee> findAllEmployeesFiltered() {
//        return employeeService.findAllEmployees();
//    }
//
//    public Employee findEmployeeByName() {

//    }

}
