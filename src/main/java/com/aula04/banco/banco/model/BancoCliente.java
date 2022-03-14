package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;

import java.util.*;

public class BancoCliente {
    private static List<Cliente> clientes = new ArrayList<>();

    public void adiciona(Cliente cliente){
        BancoCliente.clientes.add(cliente);
    }

    public List<Cliente> buscaClientes(){
        return BancoCliente.clientes;
    }

    public Optional<Cliente> detalhesCliente(UUID id) {
           return BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id)).findAny();
    }

    public Optional<Cliente> atualizaCliente(UUID id, RequestCliente requestCliente){
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id))
                .forEach(cliente -> {
                    cliente.setNome(requestCliente.getNome());
                    cliente.setEmail(requestCliente.getEmail());
                    cliente.setSenha(requestCliente.getSenha());
                });
        return detalhesCliente(id);
    }
    public void deletaCliente(UUID id){
        Optional<Cliente> cliente = detalhesCliente(id);
        BancoCliente.clientes.remove(cliente.get());
    }
    public void deposita(UUID id, RequestDeposito requestDeposito) throws Exception{
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id))
                .forEach(cliente -> {
                    Optional<Conta> resultConta = cliente.getContas().stream().filter(conta -> Objects.equals(conta.getId(),requestDeposito.getConta())).findAny();
                   if(resultConta.isPresent()) {
                       Double novoSaldo = resultConta.get().getSaldo() + requestDeposito.getValor();
                       resultConta.get().setSaldo(novoSaldo);
                   } else {
                       try {
                           throw new Exception("Conta não encontrada");
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
                });
    }


}
