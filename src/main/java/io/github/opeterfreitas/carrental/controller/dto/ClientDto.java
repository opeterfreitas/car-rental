package io.github.opeterfreitas.carrental.controller.dto;

import io.github.opeterfreitas.carrental.model.entities.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ClientDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 11)
    private String cpf;
    @NotBlank
    @Size(max = 130)
    private String name;
    @NotBlank
    @Email
    private String email;
    private String cep;
    private String logradouro;
    private String complemento;
    @NotBlank
    private String numero;
    private String bairro;
    private String localidade;
    private String uf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Client toModel() {
        return new Client(this.cpf, this.name, this.email, this.cep, this.logradouro, this.complemento, this.numero,
                this.bairro, this.localidade, this.uf);
    }
}