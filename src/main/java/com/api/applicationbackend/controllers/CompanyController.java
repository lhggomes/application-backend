package com.api.applicationbackend.controllers;

import com.api.applicationbackend.model.Company;
import com.api.applicationbackend.repositories.CompanyRepository;
import com.api.applicationbackend.services.CompanyService;
import com.api.applicationbackend.services.impl.CompanyServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    CompanyServiceImpl companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> saveCompany(@Validated @RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
