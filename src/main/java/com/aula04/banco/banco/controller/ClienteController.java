package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.ResponseCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import com.aula04.banco.banco.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Cacheable(value = "listaClientes")
    public List<ResponseCliente> clientes(){
        return ResponseCliente.toResponse(clienteService.buscaTodosClientes());
    }

    @PostMapping
    @CacheEvict(value = "listaClientes", allEntries = true)
    public ResponseEntity<ResponseCliente> cadastraCliente(
            @RequestBody @Valid RequestCliente requestCliente,
            UriComponentsBuilder uriComponentsBuilder
    ){
         Cliente cliente = clienteService.cadastraCliente(requestCliente);
         URI uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
         return ResponseEntity.created(uri).body(new ResponseCliente(cliente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCliente> detalhesCliente(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(new ResponseCliente(clienteService.detalhesCliente(id)));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaClientes", allEntries = true)
    public ResponseEntity<ResponseCliente> atualizaCliente(
            @PathVariable UUID id,
            @RequestBody RequestCliente requestCliente
    ) throws Exception {

        return ResponseEntity.ok(new ResponseCliente(clienteService.atualizaCliente(id, requestCliente)));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaClientes", allEntries = true)
    public ResponseEntity deletaCliente(@PathVariable UUID id) throws Exception {
        BancoAula04Application.bancoCliente.deletaCliente(id);
        return ResponseEntity.ok().build();
    }
}
