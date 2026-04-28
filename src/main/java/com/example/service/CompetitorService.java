package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.RegisterRequest;
import com.example.exception.EmailAlreadyExistsException;
import com.example.exception.NotAuthorizedException;
import com.example.model.Competitor;
import com.example.repository.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de servicio para la lógica de negocio de Competitor.
 *
 * @Service indica a Spring que esta clase es un componente de servicio
 * y la registra en el contenedor de dependencias (IoC).
 */
@Service
public class CompetitorService {

    // Spring inyecta automáticamente el repositorio
    @Autowired
    private CompetitorRepository competitorRepository;

    /**
     * Retorna todos los competidores ordenados por apellido.
     */
    public List<Competitor> getAll() {
        return competitorRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getSurname().compareToIgnoreCase(b.getSurname()))
                .toList();
    }

    /**
     * Servicio de LOGIN.
     *
     * Pasos:
     * 1. Buscar el competidor por email en la BD
     * 2. Si no existe → lanzar NotAuthorizedException
     * 3. Comparar contraseña (texto plano para simplicidad del laboratorio)
     * 4. Si no coincide → lanzar NotAuthorizedException
     * 5. Si todo es correcto → retornar el objeto Competitor
     *
     * NOTA: En producción se usaría BCrypt para hashear contraseñas.
     */
    public Competitor login(LoginRequest request) {
        // Paso 1 y 2: buscar por email
        Competitor competitor = competitorRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new NotAuthorizedException(
                        "Credenciales incorrectas. Email o contraseña inválidos."));

        // Paso 3 y 4: verificar contraseña
        if (!competitor.getPassword().equals(request.getPassword())) {
            throw new NotAuthorizedException(
                    "Credenciales incorrectas. Email o contraseña inválidos.");
        }

        // Paso 5: retornar competidor autenticado
        return competitor;
    }

    /**
     * Servicio de REGISTRO.
     *
     * Pasos:
     * 1. Verificar si el email ya existe en la BD
     * 2. Si existe → lanzar EmailAlreadyExistsException (HTTP 409)
     * 3. Si no existe → crear y guardar el competidor
     */
    public Competitor register(RegisterRequest request) {
        // Paso 1 y 2: verificar duplicado
        if (competitorRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Paso 3: crear entidad y persistir
        Competitor newCompetitor = new Competitor(
                request.getName(),
                request.getSurname(),
                request.getAge(),
                request.getTelephone(),
                request.getCellphone(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                false,  // winner = false por defecto al registrarse
                request.getEmail(),
                request.getPassword()
        );

        return competitorRepository.save(newCompetitor);
    }
}
