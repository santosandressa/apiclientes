package com.apiclientes.dto;


import com.apiclientes.model.Servico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ServicoDTO {

    @NotBlank(message = "Uma descrição é obrigatória")
    private String descricao;

    private ClienteDTO cliente;

    private BigDecimal valor;

    public ServicoDTO(Servico servico){
        this.descricao = servico.getDescricao();
        this.cliente = new ClienteDTO(servico.getCliente());
        this.valor = servico.getValor();
    }
}
