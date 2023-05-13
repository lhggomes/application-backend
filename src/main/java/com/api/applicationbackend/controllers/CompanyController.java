package com.api.applicationbackend.controllers;

import com.api.applicationbackend.model.Company;
import com.api.applicationbackend.services.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyServiceImpl companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> saveCompany(@Validated @RequestBody Company company){
        Company createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Company> getCompanies(){
        return companyService.getCompanies();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable(value = "id") long id){
        companyService.deleteCompany(id);
        return new ResponseEntity<>("Company successfully deleted", HttpStatus.ACCEPTED);
    }

}
