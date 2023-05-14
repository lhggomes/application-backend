package com.api.applicationbackend.repositories;

import com.api.applicationbackend.model.Address;
import com.api.applicationbackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
