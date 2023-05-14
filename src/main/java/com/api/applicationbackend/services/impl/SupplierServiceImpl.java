package com.api.applicationbackend.services.impl;

import com.api.applicationbackend.exceptions.RequiredFieldsNotFilled;
import com.api.applicationbackend.model.Supplier;
import com.api.applicationbackend.repositories.CompanyRepository;
import com.api.applicationbackend.repositories.SupplierRepository;
import com.api.applicationbackend.services.SupplierService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public SupplierServiceImpl() {
    }

    @Override
    public Supplier createSupplier(Supplier supplier, Long companyId) throws RequiredFieldsNotFilled {

        supplier.checkTypeOption();
        Optional<Supplier> foundCompany = Optional.ofNullable(companyRepository.findById(companyId).map(company -> {

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

        return foundCompany.get();
    }

    @Override
    public void updateSupplier(Long id, Supplier supplier) throws RequiredFieldsNotFilled {
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
}
