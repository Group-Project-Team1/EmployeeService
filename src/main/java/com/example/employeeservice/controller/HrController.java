package com.example.employeeservice.controller;

import com.example.employeeservice.domain.entity.Employee;
import com.example.employeeservice.domain.response.EmployeeProfile;
import com.example.employeeservice.domain.response.EmployeeSummary;
import com.example.employeeservice.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hr")
public class HrController {

    private final EmployeeService employeeService;

    @Autowired
    public HrController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get -> find
    // find all employees (and HRs)
    @GetMapping("/findAll")
    @ApiOperation(value = "get all employees", response = Iterable.class)
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

    @GetMapping("/viewByEmail")
    public EmployeeProfile viewEmployeeProfileByEmail(@PathParam("email") String email) {
        List<Employee> employees = employeeService.findAllEmployees();
        return new EmployeeProfile(employees.stream().filter(e -> e.getEmail().equals(email)).findFirst().get());
    }
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
                .filter(e -> isSeg(emailSeg, e.getLastName()) || isSeg(emailSeg, e.getFirstName()) || isSeg(emailSeg, e.getMiddleName()) || isSeg(emailSeg, e.getPreferredName()))
                .map(e -> new EmployeeProfile(e))
                .collect(Collectors.toList());
    }

    private boolean isSeg(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        for (int i = 0; i + n1 <= n2; i++) {
            String cur = s2.substring(i, i + n1);
            if (cur.equals(s1)) return true;
        }
        return false;
    }


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
