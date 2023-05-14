package com.api.applicationbackend.services.impl;

import com.api.applicationbackend.enums.PersonTypeEnum;
import com.api.applicationbackend.exceptions.BirthPRStateInvalidException;
import com.api.applicationbackend.exceptions.RequiredFieldsNotFilledException;
import com.api.applicationbackend.model.Address;
import com.api.applicationbackend.model.Supplier;
import com.api.applicationbackend.repositories.AddressRepository;
import com.api.applicationbackend.repositories.CompanyRepository;
import com.api.applicationbackend.repositories.SupplierRepository;
import com.api.applicationbackend.services.SupplierService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CEPServiceImpl cepService;

    public SupplierServiceImpl() {
    }

    @Override
    public Supplier createSupplier(Supplier supplier, Long companyId) throws RequiredFieldsNotFilledException,
            BirthPRStateInvalidException {

        // Checking the required fields
        supplier.checkTypeOption();
        Address address = cepService.searchCEPAtBrazilianProvider(supplier.getCep());
        validateSupplierSettings(address, supplier);
        addressRepository.save(address);

        supplier.setAddress(address);
        Optional<Supplier> foundSupplier = Optional.ofNullable(companyRepository.findById(companyId).map(company -> {

            Optional<Long> supplierId = Optional.ofNullable(supplier.getId());

            if (supplierId.isPresent()) {
                Supplier _supplier = supplierRepository.findById(supplierId.get())
                        .orElseThrow(() -> new ExecutionException("Not found!"));

                company.getSuppliers().add(_supplier);
                companyRepository.save(company);
                return _supplier;
            }

            company.getSuppliers().add(supplier);
            return supplierRepository.save(supplier);
        }).orElseThrow(() -> new ExecutionException("Resource not found")));

        return foundSupplier.get();
    }

    @Override
    public void updateSupplier(Long id, Supplier supplier) throws RequiredFieldsNotFilledException {
        supplier.checkTypeOption();
        Optional<Supplier> foundSupplier = supplierRepository.findById(id);
        foundSupplier.ifPresent(updateSupplier -> {

            updateSupplier.setBirthDate(supplier.getBirthDate());
            updateSupplier.setCep(supplier.getCep());
            updateSupplier.setEmail(supplier.getEmail());
            updateSupplier.setName(supplier.getName());
            updateSupplier.setRg(supplier.getRg());
            updateSupplier.setType(supplier.getType());

            supplierRepository.save(updateSupplier);

        });
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);

    }

    @Override
    public List<Supplier> getSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        return supplierList;
    }

    @Override
    public Supplier getSupplierById(Long id) {
        Optional<Supplier> foundSupplier = supplierRepository.findById(id);
        if (foundSupplier.isPresent()) {
            return foundSupplier.get();
        }

        return null;
    }

    private void validateSupplierSettings(Address address, Supplier supplier) throws BirthPRStateInvalidException {

        if (supplier.getType() == PersonTypeEnum.FISICA && address.getUf().equalsIgnoreCase("PR")) {

            Calendar birthCalendar = Calendar.getInstance();
            Calendar today = Calendar.getInstance();

            birthCalendar.setTime(supplier.getBirthDate());
            int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

            birthCalendar.add(Calendar.YEAR, age);
            if (today.before(birthCalendar)) {
                age--;
            }

            if (age < 18) {
                throw new BirthPRStateInvalidException("Person is not legal allowed!");
            }

        }

    }
}
