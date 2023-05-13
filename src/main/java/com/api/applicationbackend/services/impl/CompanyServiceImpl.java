package com.api.applicationbackend.services.impl;

import com.api.applicationbackend.model.Company;
import com.api.applicationbackend.repositories.CompanyRepository;
import com.api.applicationbackend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl() {
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public void updateCompany(String id, Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(String id) {
        companyRepository.deleteById(Long.getLong(id));

    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companyList = companyRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        return companyList;
    }
}
