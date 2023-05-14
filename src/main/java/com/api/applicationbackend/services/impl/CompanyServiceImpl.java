package com.api.applicationbackend.services.impl;

import com.api.applicationbackend.model.Company;
import com.api.applicationbackend.repositories.CompanyRepository;
import com.api.applicationbackend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyServiceImpl() {
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);

    }

    @Override
    public void updateCompany(String id, Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);

    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companyList = companyRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        return companyList;
    }
}
