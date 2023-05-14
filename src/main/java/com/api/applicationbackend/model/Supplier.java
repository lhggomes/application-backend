package com.api.applicationbackend.model;

import com.api.applicationbackend.annotation.CnpjCpf;
import com.api.applicationbackend.enums.PersonTypeEnum;
import com.api.applicationbackend.exceptions.RequiredFieldsNotFilledException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CnpjCpf
    @Column(unique = true)
    private String cpfCnpj;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String cep;

    private Date birthDate;
    private String rg;
    private PersonTypeEnum type;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "suppliers")
    @JsonIgnore
    private Set<Company> companies = new HashSet<>();;

    @OneToOne
    private Address address;

    public Supplier()  {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public PersonTypeEnum getType() {
        return type;
    }

    public void setType(PersonTypeEnum type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void checkTypeOption() throws RequiredFieldsNotFilledException {
        if (this.type == PersonTypeEnum.FISICA){
            if(this.birthDate == null || this.rg == null){
                throw new RequiredFieldsNotFilledException("Please provide the required fields");
            }

        }

    }
}
