package com.api.applicationbackend.controllers;

import com.api.applicationbackend.model.Company;
import com.api.applicationbackend.model.Supplier;
import com.api.applicationbackend.services.impl.CompanyServiceImpl;
import com.api.applicationbackend.services.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    SupplierServiceImpl supplierService;

    public SupplierController(SupplierServiceImpl supplierServiceImpl) {
        this.supplierService = supplierServiceImpl;
    }

    @PostMapping("/company/{id}/supplier")
    public ResponseEntity<Supplier> saveSupplier(@PathVariable(value = "id") Long companyId,
                                                 @Validated @RequestBody Supplier supplier) {

        Supplier createdSupplier = supplierService.createSupplier(supplier, companyId);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable(value = "id") long id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>("Company successfully deleted", HttpStatus.ACCEPTED);
    }

}
