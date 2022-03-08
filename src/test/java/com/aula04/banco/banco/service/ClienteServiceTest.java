package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;


public class ClienteServiceTest {

    ClienteService clienteService = new ClienteService();

    @Test
    public void deveRetornarListaClienteVazia(){
        List<Cliente> clientes = clienteService.buscaTodosClientes();
        Assertions.assertTrue(clientes.isEmpty());
    }

    @Test
    public void cadastraCliente(){
        RequestCliente requestCliente = new RequestCliente(
                "cinthia",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
                );

            Cliente cliente = clienteService.cadastraCliente(requestCliente);

            Assertions.assertEquals(requestCliente.getNome(), cliente.getNome());
            Assertions.assertNotNull(cliente.getId());

    }

    @Test
    public void atualizaCliente() throws Exception {
        RequestCliente requestCliente = new RequestCliente(
                "cinthia",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);

        RequestCliente requestClienteAtualiza = new RequestCliente(   "maria",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
        );

        Cliente clienteAtualizado = clienteService.atualizaCliente(cliente.getId(), requestClienteAtualiza);

        Assertions.assertEquals("maria", clienteAtualizado.getNome());
    }
    @Test
    void buscaClienteQueNaoExiste() {
        UUID id = UUID.randomUUID();
        Assertions.assertThrows(Exception.class, ()-> clienteService.detalhesCliente(id));
    }

    @Test
    void buscaClienteQueNaoExiste2(){
        UUID id = UUID.randomUUID();
        try{
            clienteService.detalhesCliente(id);
            fail("Nao lancou a excecao");
        } catch (Exception e){ }
    }

}
