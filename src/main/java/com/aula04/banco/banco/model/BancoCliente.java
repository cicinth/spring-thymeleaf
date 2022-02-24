package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;

import java.util.*;

public class BancoCliente {
    private static List<Cliente> clientes = new ArrayList<>();

    public void adiciona(Cliente cliente){
        BancoCliente.clientes.add(cliente);
    }

    public List<Cliente> buscaClientes(){
        return BancoCliente.clientes;
    }

    public Cliente detalhesCliente(UUID id) throws Exception {
           Optional<Cliente> resultCliente =
                   BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id)).findAny();
           if(resultCliente.isPresent()){
               return resultCliente.get();
           } else {
               throw new Exception("Usuario nao encontrado");
           }
    }

    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id))
                .forEach(cliente -> {
                    cliente.setNome(requestCliente.getNome());
                    cliente.setEmail(requestCliente.getEmail());
                    cliente.setSenha(requestCliente.getSenha());
                });
        return detalhesCliente(id);
    }
}
