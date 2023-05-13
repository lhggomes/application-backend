package com.api.applicationbackend.repositories;

import com.api.applicationbackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
