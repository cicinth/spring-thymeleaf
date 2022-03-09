package com.aula04.banco.banco.service;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClienteService {

    Random random = new Random();
    BancoCliente bancoCliente = BancoAula04Application.bancoCliente;

    public Cliente cadastraCliente(RequestCliente requestCliente){
        List<Conta> contas = new ArrayList<>();
        Conta conta = new Conta(UUID.randomUUID(), random.nextInt(), requestCliente.getAgencia(), TipoConta.CONTA_CORRENTE, 0.0);
        contas.add(conta);

        Cliente cliente = new Cliente(
                UUID.randomUUID(),
                requestCliente.getNome(),
                requestCliente.getEmail(),
                requestCliente.getSenha(),
                contas
        );
        bancoCliente.adiciona(cliente);
        return cliente;
    }

    public List<Cliente> buscaTodosClientes(){
        return bancoCliente.buscaClientes();
    }

    public Cliente detalhesCliente(UUID id) throws Exception {
        return bancoCliente.detalhesCliente(id);
    }
    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
        return bancoCliente.atualizaCliente(id, requestCliente);
    }
}
