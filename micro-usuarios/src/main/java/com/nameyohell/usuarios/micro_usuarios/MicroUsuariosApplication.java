package com.nameyohell.usuarios.micro_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroUsuariosApplication.class, args);
	}

}
