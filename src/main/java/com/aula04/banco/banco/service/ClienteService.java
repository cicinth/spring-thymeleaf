package com.aula04.banco.banco.service;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ClienteService {

    Random random = new Random();

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
        BancoAula04Application.bancoCliente.adiciona(cliente);
        return cliente;
    }

    public List<Cliente> buscaTodosClientes(){
        return BancoAula04Application.bancoCliente.buscaClientes();
    }

    public Cliente detalhesCliente(UUID id) throws Exception {
        return BancoAula04Application.bancoCliente.detalhesCliente(id);
    }
}
