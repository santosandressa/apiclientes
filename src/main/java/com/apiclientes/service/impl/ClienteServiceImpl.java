package com.apiclientes.service.impl;

import com.apiclientes.dto.ClienteDTO;
import com.apiclientes.model.Cliente;
import com.apiclientes.repository.ClienteRepository;
import com.apiclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final Logger log = Logger.getLogger(ClienteServiceImpl.class.getName());

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente salvar(ClienteDTO clienteDTO) {

        Cliente cliente = new Cliente(clienteDTO);

        log.info("Salvando cliente: " + clienteDTO.getNome());

        Boolean existsByName = this.clienteRepository.existsByCpf(clienteDTO.getCpf());

        if (existsByName) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        cliente.setDataCadastro(LocalDate.now());

        return this.clienteRepository.save(cliente);
    }

    @Override
    public Optional<List<Cliente>> buscarTodos() {

        log.info("Buscando clientes...");

        List<Cliente> clientes = this.clienteRepository.findAll();

        if (clientes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(clientes);
    }

    @Override
    public Optional<ClienteDTO> buscarPorId(Long id) {

        log.info("Buscando cliente por id: " + id);

        Optional<Cliente> clienteId = this.clienteRepository.findById(id);

        if (clienteId.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        ClienteDTO clienteDTO = new ClienteDTO(clienteId.get());
        clienteDTO.setDataCadastro(clienteId.get().getDataCadastro());

        return Optional.of(clienteDTO);
    }

    @Override
    public Cliente atualizar(ClienteDTO clienteDTO, Long id) {

        log.info("Atualizando cliente: " + clienteDTO.getNome());

        Optional<Cliente> clienteId = this.clienteRepository.findById(id);

        if (clienteId.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        Cliente cliente = new Cliente(clienteDTO);

        cliente.setId(id);
        cliente.setCpf(clienteId.get().getCpf());
        cliente.setDataCadastro(clienteId.get().getDataCadastro());

        return this.clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {

        log.info("Deletando cliente por id: " + id);

        Optional<Cliente> clienteId = this.clienteRepository.findById(id);

        if (clienteId.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        this.clienteRepository.deleteById(id);
    }
}
