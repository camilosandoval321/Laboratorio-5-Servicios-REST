======================================================
  LABORATORIO JPA - Spring Boot + SQLite
======================================================

INTEGRANTES:
  [Escribe aquí tu nombre]

------------------------------------------------------
PREGUNTA DEL LABORATORIO:
¿Qué efecto tiene haber agregado la anotación @OneToMany
en la entidad Competitor, sobre la tabla creada para Producto?
------------------------------------------------------

RESPUESTA:

Al agregar la anotación @OneToMany(mappedBy="competitor") en la entidad Competitor,
JPA establece una relación bidireccional entre las dos entidades. El efecto concreto
sobre la base de datos se manifiesta ÚNICAMENTE en la tabla PRODUCTO: se genera
automáticamente una columna llamada "competitor_id" que actúa como clave foránea
(FOREIGN KEY) apuntando a la llave primaria (id) de la tabla COMPETITOR. Esto significa
que la relación NO se representa con una tabla intermedia (como ocurriría en una
relación ManyToMany), sino directamente en la tabla del lado "muchos" (PRODUCTO).
El parámetro mappedBy="competitor" le indica a JPA que la entidad Producto es la
"dueña" de la relación (es quien tiene la columna FK), y que el atributo que la gestiona
dentro de Producto se llama "competitor". El cascade=ALL garantiza que las operaciones
de persistencia (insertar, borrar, actualizar) aplicadas sobre un Competitor se propaguen
también a todos sus Productos asociados.

======================================================
  CÓMO EJECUTAR CON DOCKER (recomendado)
======================================================

Requisito: tener Docker Desktop instalado.
Descarga: https://www.docker.com/products/docker-desktop/

Pasos:
  1. Abre la terminal en la carpeta del proyecto
  2. Ejecuta:

       docker-compose up --build

  3. Espera a que compile (primera vez ~2-3 minutos)
  4. Abre el navegador en:

       http://localhost:8080/login.html
       http://localhost:8080/register.html

Para detener:
  docker-compose down

Para detener Y borrar los datos de la BD:
  docker-compose down -v

======================================================
  CÓMO EJECUTAR SIN DOCKER (requiere Java 17 + Maven)
======================================================

  mvn spring-boot:run

======================================================
  ENDPOINTS REST
======================================================

  POST /competitors/login
       Body: { "email": "...", "password": "..." }
       200: retorna Competitor | 401: credenciales incorrectas

  POST /competitors/register
       Body: { "name":"...", "surname":"...", "age":20,
               "email":"...", "password":"..." }
       200: registro exitoso | 409: email ya registrado

  GET  /competitors/get
       Lista de todos los competidores (JSON)
