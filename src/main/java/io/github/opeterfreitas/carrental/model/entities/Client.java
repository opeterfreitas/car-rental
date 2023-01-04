package io.github.opeterfreitas.carrental.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    private String name;
    private String email;
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    private String localidade;
    private String uf;
    private LocalDateTime registrationDate = LocalDateTime.now();

    public Client(String cpf, String name, String email, String cep, String logradouro, String complemento, String numero, String bairro, String localidade, String uf) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }
}
