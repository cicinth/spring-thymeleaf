package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClienteController {
    BancoCliente  bancoCliente = new BancoCliente();

    @GetMapping("/clientes")
    public String clientes(Model model){
        List<Cliente> clientes = bancoCliente.buscaClientes();
        model.addAttribute("clientes", clientes);

        return "clientes";
    }

    @GetMapping("/cadastra/cliente")
    public String formClientes(){ return  "formCliente"; }

    @PostMapping("/cliente")
    public String cadastraCliente(RequestCliente requestCliente, Model model){
        Cliente cliente = new Cliente(
                requestCliente.getNome(),
                requestCliente.getEmail(),
                requestCliente.getConta(),
                requestCliente.getAgencia()
        );
        bancoCliente.adiciona(cliente);
        model.addAttribute("nome", cliente.getNome());
        return  "clienteCadastrado";
    }
}
