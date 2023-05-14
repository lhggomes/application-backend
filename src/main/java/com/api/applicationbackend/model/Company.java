package com.api.applicationbackend.model;

import com.api.applicationbackend.annotation.CnpjCpf;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    @NotNull
    @CnpjCpf
    private String cnpj;
    @NotNull
    private String nomeFantasia;
    @NotNull
    private String cep;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "company_suppliers",
            joinColumns = {@JoinColumn(name = "company_id")},
            inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
    private Set<Supplier> suppliers = new HashSet<>();

    @OneToOne
    private Address address;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
