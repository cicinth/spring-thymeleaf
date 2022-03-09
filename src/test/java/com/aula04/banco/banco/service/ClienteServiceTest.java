package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.thymeleaf.exceptions.AlreadyInitializedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ClienteServiceTest {

    @Mock
    BancoCliente bancoCliente;

    @InjectMocks
    ClienteService clienteService;

    @Captor
    private ArgumentCaptor<Cliente> clienteArgumentCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarListaClienteVazia(){
        when(bancoCliente.buscaClientes()).thenReturn(new ArrayList<>());

        List<Cliente> clientes = clienteService.buscaTodosClientes();
        Assertions.assertTrue(clientes.isEmpty());

        verify(bancoCliente, times(1)).buscaClientes();
        verifyNoMoreInteractions(bancoCliente);
    }

    @Test
    public void deveRetornarListaClientePreenchida() {
        Conta conta = new Conta(
                UUID.randomUUID(),
                1,
                2,
                TipoConta.CONTA_CORRENTE,
                1000.0
        );

        Cliente cliente = new Cliente(
                UUID.randomUUID(),
                "cinthia",
                "cinthia@email.com",
                "1234",
                Arrays.asList(conta)
        );

        when(bancoCliente.buscaClientes()).thenReturn(Arrays.asList(cliente));

        List<Cliente> clientes = clienteService.buscaTodosClientes();

        assertEquals(Arrays.asList(cliente), clientes);
        verify(bancoCliente, times(1)).buscaClientes();
    }

    @Test
    public void testeSpy() {
        RequestCliente requestClienteSpy = spy(new RequestCliente(
                "cinthia",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
        ));

        when(requestClienteSpy.getNome()).thenReturn("maria");

        assertEquals("cinthia", requestClienteSpy.getNome());
        assertEquals("44934586719", requestClienteSpy.getCpf());
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

            clienteService.cadastraCliente(requestCliente);

            verify(bancoCliente).adiciona(clienteArgumentCaptor.capture());

            assertEquals(requestCliente.getNome(), clienteArgumentCaptor.getValue().getNome());
            assertNotNull(clienteArgumentCaptor.getValue().getId());
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
    void buscaClienteQueNaoExiste() throws Exception {
        UUID id = UUID.randomUUID();
        when(bancoCliente.detalhesCliente(id)).thenThrow(Exception.class);

        Assertions.assertThrows(Exception.class, ()-> clienteService.detalhesCliente(id));
    }

    @Test
    void buscaClienteQueNaoExiste2(){
        UUID id = UUID.randomUUID();
        try{
            when(bancoCliente.detalhesCliente(id)).thenThrow(Exception.class);
            clienteService.detalhesCliente(id);
            fail("Nao lancou a excecao");
        } catch (Exception e){ }
    }

}
