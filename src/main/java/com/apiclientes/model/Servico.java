package com.apiclientes.model;

import com.apiclientes.dto.ServicoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column
    private BigDecimal valor;

    public Servico(ServicoDTO servicoDTO){
        this.descricao = servicoDTO.getDescricao();
        this.cliente = new Cliente(servicoDTO.getCliente());
        this.valor = servicoDTO.getValor();
    }


}
