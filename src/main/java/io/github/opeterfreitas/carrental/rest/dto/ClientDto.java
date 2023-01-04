package io.github.opeterfreitas.carrental.rest.dto;

import io.github.opeterfreitas.carrental.model.entities.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String numero;
    private String bairro;
    private String localidade;
    private String uf;

    public Client toModel() {
        return new Client(this.cpf, this.name, this.email, this.cep, this.logradouro, this.complemento, this.numero,
                this.bairro, this.localidade, this.uf);
    }
}