package com.api.applicationbackend.controllers;

import com.api.applicationbackend.exceptions.BirthPRStateInvalidException;
import com.api.applicationbackend.exceptions.RequiredFieldsNotFilledException;
import com.api.applicationbackend.model.Supplier;
import com.api.applicationbackend.services.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    SupplierServiceImpl supplierService;

    public SupplierController(SupplierServiceImpl supplierServiceImpl) {
        this.supplierService = supplierServiceImpl;
    }

    @PostMapping("/company/{id}/supplier")
    public ResponseEntity<?> saveSupplier(@PathVariable(value = "id") Long companyId,
                                          @Validated @RequestBody Supplier supplier) {
        try {
            Supplier createdSupplier = supplierService.createSupplier(supplier, companyId);
            return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
        } catch (RequiredFieldsNotFilledException requiredFieldsNotFilled) {
            return new ResponseEntity<>(requiredFieldsNotFilled.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BirthPRStateInvalidException birthPRStateInvalidException) {
            return new ResponseEntity<>(birthPRStateInvalidException.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable(value = "id") Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<String> updateSupplier(@PathVariable(value = "id") Long id,
                                                 @Validated @RequestBody Supplier supplier) {
        try {
            supplierService.updateSupplier(id, supplier);
            return new ResponseEntity<>("Updated successfully", HttpStatus.ACCEPTED);
        } catch (RequiredFieldsNotFilledException e) {
            return new ResponseEntity<>("Please provide all required fields", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable(value = "id") long id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>("Company successfully deleted", HttpStatus.ACCEPTED);
    }

}
