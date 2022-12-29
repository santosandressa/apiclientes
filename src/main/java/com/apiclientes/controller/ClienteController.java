package com.apiclientes.controller;

import com.apiclientes.dto.ClienteDTO;
import com.apiclientes.model.Cliente;
import com.apiclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            this.clienteService.salvar(clienteDTO);
            return new ResponseEntity<>("Cliente criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar cliente: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Cliente>> buscarTodos(Pageable pageable) {

        Page<Cliente> clientes = this.clienteService.buscarTodos(pageable);

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("search/{id}")
    public ResponseEntity<Optional<ClienteDTO>> buscarPorId(@PathVariable Long id) {

        Optional<ClienteDTO> cliente = this.clienteService.buscarPorId(id);

        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {

        ClienteDTO updatedClienteDTO = this.clienteService.atualizar(clienteDTO, id);

        if (updatedClienteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
