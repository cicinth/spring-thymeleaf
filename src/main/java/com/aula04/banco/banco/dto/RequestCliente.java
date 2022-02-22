package com.aula04.banco.banco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestCliente {
    private String nome;
    private String email;
    private String senha;
    private Integer agencia;
}
