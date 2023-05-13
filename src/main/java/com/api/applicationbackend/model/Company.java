package com.api.applicationbackend.model;

import jakarta.persistence.*;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;


    private String cnpjCpf;


}
