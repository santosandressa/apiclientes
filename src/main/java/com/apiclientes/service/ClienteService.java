package com.apiclientes.service;

import com.apiclientes.dto.ClienteDTO;
import com.apiclientes.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(ClienteDTO clienteDTO);

    Optional<List<Cliente>> buscarTodos();

    Optional<ClienteDTO> buscarPorId(Long id);

    Cliente atualizar(ClienteDTO clienteDTO, Long id);

    void deletar(Long id);

}
