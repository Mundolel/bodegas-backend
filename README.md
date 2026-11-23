# bodegas-backend

API REST de bodegas y sus productos (Spring Boot 4.1 + JPA + H2).

## Requisitos

- Java 21

## Ejecución

```bash
./mvnw spring-boot:run        # Linux / macOS / Git Bash
mvnw.cmd spring-boot:run      # Windows (cmd)
```

Corre en `http://localhost:8080`. Al primer arranque carga dos bodegas y tres productos de prueba.

## Endpoints

| Método | Ruta       | Acción                                      |
|--------|------------|---------------------------------------------|
| POST   | `/bodegas` | Registrar bodega (`{ "nombre", "ubicacion" }`) |
| GET    | `/bodegas` | Listar bodegas con sus productos            |

Consola H2: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:file:./data/bodegas`, usuario `sa`, sin contraseña).
