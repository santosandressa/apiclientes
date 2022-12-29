package com.apiclientes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Endereco {

    private String rua;

    @NotBlank(message = "O campo cep é obrigatório")
    private String cep;

    @NotBlank(message = "O campo número é obrigatório")
    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;
}