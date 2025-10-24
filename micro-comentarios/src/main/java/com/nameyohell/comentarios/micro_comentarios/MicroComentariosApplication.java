package com.nameyohell.comentarios.micro_comentarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroComentariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroComentariosApplication.class, args);
    }

}