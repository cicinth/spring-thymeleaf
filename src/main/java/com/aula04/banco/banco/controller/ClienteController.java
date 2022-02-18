package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClienteController {
    BancoCliente  bancoCliente = new BancoCliente();

    @GetMapping("/clientes")
    public String clientes(Model model){
        Cliente cliente = new Cliente("Nath", "nath@letscode.com", 123, 0);
        Cliente cliente2 = new Cliente("Raphael", "rapha@letscode.com", 1234, 0);

        bancoCliente.adiciona(cliente);
        bancoCliente.adiciona(cliente2);

        List<Cliente> clientes = bancoCliente.buscaClientes();

        model.addAttribute("clientes", clientes);

        return "clientes";
    }
}
