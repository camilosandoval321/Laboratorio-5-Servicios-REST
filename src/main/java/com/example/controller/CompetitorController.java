package com.example.controller;

import com.example.dto.LoginRequest;
import com.example.dto.RegisterRequest;
import com.example.model.Competitor;
import com.example.service.CompetitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para los endpoints de Competitor.
 *
 * @RestController → combina @Controller + @ResponseBody (respuestas en JSON)
 * @RequestMapping → prefijo base de todas las rutas: /competitors
 * @CrossOrigin    → permite peticiones desde el frontend HTML (CORS)
 */
@RestController
@RequestMapping("/competitors")
@CrossOrigin(origins = "*")
public class CompetitorController {

    @Autowired
    private CompetitorService competitorService;

    /**
     * GET /competitors/get
     * Retorna todos los competidores ordenados por apellido.
     */
    @GetMapping("/get")
    public ResponseEntity<List<Competitor>> getAll() {
        List<Competitor> competitors = competitorService.getAll();
        return ResponseEntity.ok(competitors);
    }

    /**
     * POST /competitors/login
     * Recibe email y password, verifica credenciales.
     *
     * Cuerpo esperado:
     * {
     *   "email": "laura@mail.com",
     *   "password": "1234"
     * }
     *
     * Respuestas:
     *   200 OK         → retorna el objeto Competitor
     *   401 Unauthorized → credenciales incorrectas
     */
    @PostMapping("/login")
    public ResponseEntity<Competitor> login(@RequestBody LoginRequest request) {
        Competitor competitor = competitorService.login(request);
        return ResponseEntity.ok(competitor);
    }

    /**
     * POST /competitors/register
     * Registra un nuevo competidor.
     *
     * Respuestas:
     *   200 OK   → competidor creado, retorna el objeto
     *   409 Conflict → email ya registrado
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        Competitor created = competitorService.register(request);
        return ResponseEntity.ok(Map.of(
                "message", "Registro exitoso",
                "competitor_id", created.getId(),
                "email", created.getEmail()
        ));
    }
}
