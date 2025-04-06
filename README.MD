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

Por defecto la aplicacion se abre en:
```
localhost:8080
```

### APIs Disponibles
Todas las apis tienen el prefijo **/api**
```bash
/api/health
/api/register/user
/api/register/company
/api/login
/api/companies # Devuelve todas las compañias en una lista
```

Y la forma en como estan establecidos los usuarios y compañias es la siguiente:

**Usuario**\
*Nota: Para el api de login solo es necesario el email y password*
```json
{
  "firstName": "",
  "lastName": "",
  "email": "",
  "password": "",
  "jobTitle": "",
  "yearsOfExperienceTechnology": "",
  "companyId": 
}
```
**Compañia**
```json
{
  "name": "",
  "organizationType": "",
  "organizationSector": "",
  "country": "",
  "numberOfEmployees": ""
}
```

### Perfiles
Cambiar entre perfiles dev y prod. (WIP)