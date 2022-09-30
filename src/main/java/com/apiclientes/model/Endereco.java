package com.apiclientes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
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