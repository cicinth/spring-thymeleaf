package com.aula04.banco.banco.service;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestCliente;
<<<<<<< HEAD
import com.aula04.banco.banco.exceptions.NotFoundException;
=======
import com.aula04.banco.banco.model.BancoCliente;
>>>>>>> 8ec1e83cf1c0d209482999e755bb3b019aa10137
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
<<<<<<< HEAD

=======
>>>>>>> 8ec1e83cf1c0d209482999e755bb3b019aa10137

@Service
@Slf4j
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
<<<<<<< HEAD
        log.info("Buscando todos os clientes");
        return BancoAula04Application.bancoCliente.buscaClientes();
    }

    public Cliente detalhesCliente(UUID id) throws NotFoundException {
        Optional<Cliente> resultCliente =  BancoAula04Application.bancoCliente.detalhesCliente(id);
        if(resultCliente.isPresent()){
            return resultCliente.get();
        } else {
            log.error("Usuario nao foi encontrado"+ id);
            throw new NotFoundException("Usuario nao encontrado");
        }
    }

    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws NotFoundException {
        Optional<Cliente> resultCliente = BancoAula04Application.bancoCliente.atualizaCliente(id, requestCliente);
        if(resultCliente.isPresent()){
            return resultCliente.get();
        } else {
            throw new NotFoundException("Usuario nao encontrado");
        }
=======
        return bancoCliente.buscaClientes();
    }

    public Cliente detalhesCliente(UUID id) throws Exception {
        return bancoCliente.detalhesCliente(id);
    }
    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
        return bancoCliente.atualizaCliente(id, requestCliente);
>>>>>>> 8ec1e83cf1c0d209482999e755bb3b019aa10137
    }
}
