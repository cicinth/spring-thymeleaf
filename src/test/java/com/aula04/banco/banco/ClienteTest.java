package com.aula04.banco.banco;

import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.service.ClienteService;

import java.util.List;

public class ClienteTest {
    public static void main(String[] args){
        ClienteService clienteService = new ClienteService();
        List<Cliente> clienteList = clienteService.buscaTodosClientes();
        System.out.println("Clientes" + clienteList);
        if(clienteList.isEmpty()){
            System.out.println("Teste 1 passou");
        }
    }
}
