package com.apiclientes.dto;

import com.apiclientes.model.Cliente;
import com.apiclientes.model.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @CPF
    private String cpf;

    @Valid
    private Endereco endereco;

    private LocalDate dataCadastro;

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }
}
