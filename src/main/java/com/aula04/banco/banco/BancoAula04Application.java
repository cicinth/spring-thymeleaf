package com.aula04.banco.banco;

import com.aula04.banco.banco.model.BancoCliente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BancoAula04Application {
	public static BancoCliente bancoCliente = new BancoCliente();
	public static void main(String[] args) {SpringApplication.run(BancoAula04Application.class, args);}
}
