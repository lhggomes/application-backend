package com.api.applicationbackend.services;

import com.api.applicationbackend.model.Address;

public interface CEPService {

    Address searchCEPAtBrazilianProvider(String cep);

}
