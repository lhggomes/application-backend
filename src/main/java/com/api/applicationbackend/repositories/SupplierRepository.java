package com.api.applicationbackend.repositories;

import com.api.applicationbackend.model.Company;
import com.api.applicationbackend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
