package com.apiclientes.service.impl;

import com.apiclientes.dto.ClienteDTO;
import com.apiclientes.exception.NotFoundException;
import com.apiclientes.feign.model.Cep;
import com.apiclientes.feign.service.CepService;
import com.apiclientes.model.Cliente;
import com.apiclientes.model.Endereco;
import com.apiclientes.repository.ClienteRepository;
import com.apiclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final Logger log = Logger.getLogger(ClienteServiceImpl.class.getName());

    private final ClienteRepository clienteRepository;

    private final CepService cepService;

    @Override
    public void salvar(ClienteDTO clienteDTO) {

        Cliente cliente = new Cliente(clienteDTO);

        log.info("Salvando cliente: " + clienteDTO.getNome());

        Boolean existsByName = this.clienteRepository.existsByCpf(clienteDTO.getCpf());

        if (existsByName) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        cliente.setDataCadastro(LocalDate.now());

        Cep cep = this.cepService.getEnderecoPorCep(clienteDTO.getEndereco().getCep());

        Endereco endereco = new Endereco();
        endereco.setRua(cep.getLogradouro());
        endereco.setBairro(cep.getBairro());
        endereco.setCep(cep.getCep());
        endereco.setCidade(cep.getLocalidade());
        endereco.setComplemento(cep.getComplemento());
        endereco.setEstado(cep.getUf());
        endereco.setNumero(clienteDTO.getEndereco().getNumero());

        cliente.setEndereco(endereco);

        this.clienteRepository.save(cliente);
    }

    @Override
    public Page<Cliente> buscarTodos(Pageable pageable) {

        log.info("Buscando clientes...");

        return this.clienteRepository.findAll(pageable);
    }

    @Override
    public Optional<ClienteDTO> buscarPorId(Long id) {

        log.info("Buscando cliente por id: " + id);

        Optional<Cliente> clienteId = this.clienteRepository.findById(id);

        if (clienteId.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado");
        }

        return clienteId.map(cliente -> {
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            clienteDTO.setDataCadastro(cliente.getDataCadastro());
            clienteDTO.setEndereco(cliente.getEndereco());
            return clienteDTO;
        });
    }

    @Override
    public ClienteDTO atualizar(ClienteDTO clienteDTO, Long id) {

        log.info("Atualizando cliente: " + clienteDTO.getNome());

        Optional<Cliente> clienteId = this.clienteRepository.findById(id);

        if (clienteId.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado");
        }

        Cliente cliente = new Cliente(clienteDTO);

        cliente.setId(id);
        cliente.setCpf(clienteId.get().getCpf());
        cliente.setDataCadastro(clienteId.get().getDataCadastro());
        cliente.setEndereco(clienteId.get().getEndereco());

        // if endereço is null then set endereço
        if (cliente.getEndereco() == null) {
            cliente.setEndereco(clienteId.get().getEndereco());
        }

        this.clienteRepository.save(cliente);
        return clienteDTO;
    }

    @Override
    public void deletar(Long id) {

        log.info("Deletando cliente por id: " + id);

        Optional<Cliente> clienteId = this.clienteRepository.findById(id);

        if (clienteId.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado");
        }

        this.clienteRepository.deleteById(id);
    }
}
