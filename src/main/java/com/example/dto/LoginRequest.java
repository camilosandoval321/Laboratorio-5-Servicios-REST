package com.example.dto;

/**
 * DTO (Data Transfer Object) para la petición de login.
 * Solo contiene los campos necesarios para autenticar.
 */
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
