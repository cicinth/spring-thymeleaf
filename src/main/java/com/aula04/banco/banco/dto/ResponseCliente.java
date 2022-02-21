package com.aula04.banco.banco.dto;


import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
public class ResponseCliente {
    private UUID id;
    private String nome;
    private String email;
    private List<Conta> contas;

    public ResponseCliente(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.contas = cliente.getContas();
    }

    public static List<ResponseCliente> toResponse(List<Cliente> clientes){
        return  clientes.stream().map(ResponseCliente::new).collect(Collectors.toList());
    }
}
