package com.apiclientes.controller;

import com.apiclientes.dto.ClienteDTO;
import com.apiclientes.model.Cliente;
import com.apiclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody ClienteDTO clienteDTO) {
        this.clienteService.salvar(clienteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<List<Cliente>>> buscarTodos() {

        Optional<List<Cliente>> clientes = this.clienteService.buscarTodos();

        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("search/{id}")
    public ResponseEntity<Optional<ClienteDTO>> buscarPorId(@PathVariable Long id) {

        Optional<ClienteDTO> cliente = this.clienteService.buscarPorId(id);

        if (cliente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
        this.clienteService.atualizar(clienteDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
