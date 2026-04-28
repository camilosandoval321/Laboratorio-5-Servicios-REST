package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Spring Boot.
 * Spring Boot incluye Tomcat embebido, por lo que no se necesita
 * configurar ningún servidor externo.
 */
@SpringBootApplication
public class CompetitorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompetitorsApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("  Aplicación iniciada en: http://localhost:8080");
        System.out.println("  Login:    http://localhost:8080/login.html");
        System.out.println("  Registro: http://localhost:8080/register.html");
        System.out.println("========================================\n");
    }
}
