package com.api.applicationbackend.services.impl;

import com.api.applicationbackend.model.Company;
import com.api.applicationbackend.repositories.CompanyRepository;
import com.api.applicationbackend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public void updateCompany(Long id, Company company) {

        Optional<Company> foundCompany = companyRepository.findById(id);
        foundCompany.ifPresent(updateCompany -> {

            updateCompany.setCep(company.getCep());
            updateCompany.setCnpj(company.getCnpj());
            updateCompany.setNomeFantasia(company.getNomeFantasia());

            companyRepository.save(updateCompany);

        });

    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);

    }

    @Override
    public Company getCompanyByID(Long id) {
        Optional<Company> foundCompany = companyRepository.findById(id);
        if (foundCompany.isPresent()) {
            return foundCompany.get();
        }
        return null;
    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companyList = companyRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        return companyList;
    }
}
