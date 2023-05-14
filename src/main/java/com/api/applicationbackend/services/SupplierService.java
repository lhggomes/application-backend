package com.api.applicationbackend.services;

import com.api.applicationbackend.exceptions.RequiredFieldsNotFilledException;
import com.api.applicationbackend.model.Supplier;
import java.util.List;

public interface SupplierService {

    Supplier createSupplier(Supplier supplier, Long companyId) throws Exception;

    void updateSupplier(Long id, Supplier supplier) throws RequiredFieldsNotFilledException;

    void deleteSupplier(Long id);

    List<Supplier> getSuppliers();

    Supplier getSupplierById(Long id);


}
