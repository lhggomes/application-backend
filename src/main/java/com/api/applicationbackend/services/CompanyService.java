package com.api.applicationbackend.services;

import com.api.applicationbackend.model.Company;

import java.util.Collection;
import java.util.List;

public interface CompanyService {

    Company createCompany(Company company);

    void updateCompany(String id, Company company);

    void deleteCompany(String id);

    List<Company> getCompanies();


}
