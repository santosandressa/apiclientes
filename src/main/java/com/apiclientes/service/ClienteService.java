package com.apiclientes.service;

import com.apiclientes.dto.ClienteDTO;
import com.apiclientes.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteService {

    void salvar(ClienteDTO clienteDTO);

    Page<Cliente> buscarTodos(Pageable pageable);

    Optional<ClienteDTO> buscarPorId(Long id);

    ClienteDTO atualizar(ClienteDTO clienteDTO, Long id);

    void deletar(Long id);
}
