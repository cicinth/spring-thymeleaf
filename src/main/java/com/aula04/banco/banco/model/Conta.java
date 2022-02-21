package com.aula04.banco.banco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class Conta {
    private UUID id;
    private Integer numeroConta;
    private Integer agencia;
    private TipoConta tipoConta;
}
