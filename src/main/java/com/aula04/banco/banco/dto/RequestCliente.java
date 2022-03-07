package com.aula04.banco.banco.dto;

import com.aula04.banco.banco.utils.CPF;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class RequestCliente {
    @NotNull(message = "nao pode ser null vacilao") @NotEmpty(message = "batata") @Length(min = 2)
    private String nome;
    private String email;
    @CPF()
    private String cpf;
    private String senha;
    private Integer agencia;
}
