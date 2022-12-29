package com.apiclientes.model;

import com.apiclientes.dto.ClienteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column
    private String cpf;

    @Embedded
    private Endereco endereco;

    @Column
    private LocalDate dataCadastro;

    public Cliente(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cpf, cliente.cpf) && Objects.equals(endereco, cliente.endereco) && Objects.equals(dataCadastro, cliente.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, endereco, dataCadastro);
    }
}
