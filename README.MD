# Autoevaluación para apoyar los procesos de transformación digital

Herramienta para apoyar procesos de transformación digital.

## Tecnologías Utilizadas

*   Spring Boot
*   Java 17
*   Maven
*   PostgreSQL
*   Docker

## Guia

### Uso

1.  Clonar el repositorio
2.  Ejecutar los comandos de Docker y Docker Compose.
    ```bash
    docker build -t autoevaluacion .
    # Por defecto se ejecuta el perfil dev por lo tanto la configuracion de application-dev.yml

    docker compose up -d
    ```
3.  El backend entonces se ejecuta en el puerto 8080 y la base de datos en 5432. 

4.  Para detener el Docker Compose se ejecuta lo siguiente:
    ```
    docker compose down
    ```

### Apis disponibles

```
localhost:8080/api/health
localhost:8080/api/register
localhost:8080/api/login
```


### Perfiles
Cambiar entre perfiles dev y prod. (WIP)