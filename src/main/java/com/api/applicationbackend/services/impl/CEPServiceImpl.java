package com.api.applicationbackend.services.impl;

import com.api.applicationbackend.services.CEPService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class CEPServiceImpl implements CEPService {

    @Value("${cep.api.url}")
    private String url;


    public CEPServiceImpl() {
    }

    @Override
    public String checkCEPAPI(String cep) {
        return null;
    }


}
