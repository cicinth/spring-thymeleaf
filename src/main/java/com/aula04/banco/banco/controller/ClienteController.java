package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.ResponseCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    BancoCliente  bancoCliente = new BancoCliente();

    @GetMapping
    @ResponseBody
    public List<ResponseCliente> clientes(){
        List<Conta> contas = new ArrayList<>();
        Conta conta = new Conta(UUID.randomUUID(), 343242, 23, TipoConta.CONTA_CORRENTE);
        contas.add(conta);
        Cliente cliente = new Cliente(UUID.randomUUID(), "teste", "teste@teste", "12134", contas);
        Cliente cliente2 = new Cliente(UUID.randomUUID(), "teste2", "teste2@teste", "12134", contas);
        bancoCliente.adiciona(cliente);
        bancoCliente.adiciona(cliente2);

        return bancoCliente.buscaClientes();
    }

    @GetMapping("/cadastra/cliente")
    public String formClientes(){ return  "formCliente"; }

    @PostMapping
    public String cadastraCliente(RequestCliente requestCliente, Model model){
//        Cliente cliente = new Cliente(
//                requestCliente.getNome(),
//                requestCliente.getEmail(),
//                requestCliente.getConta(),
//                requestCliente.getAgencia()
//        );
//        bancoCliente.adiciona(cliente);
//        model.addAttribute("nome", cliente.getNome());
        return  "clienteCadastrado";
    }
}
