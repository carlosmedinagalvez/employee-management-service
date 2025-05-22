# 📘 Proyecto: API REST con Spring Boot

Este proyecto es una API REST construida con Spring Boot 3. Permite operaciones CRUD sobre una entidad `employee` y registrando los eventos en una entidad `auditlog`, utilizando Spring Data JPA y DTOs para transferencia de datos.

---

## 🧱 Tecnologías

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- H2 (base de datos en memoria)
- MapStruct (para mapeo entre entidades y DTOs)
- JUnit 5 + Mockito
- Maven

---

## 📦 Estructura del Proyecto

employee-management-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── config/OpenApiCustomizer.java
│   │   │   ├── config/RequestLoggingFilterConfig.java
│   │   │   ├── controller/EmployeeController.java
│   │   │   ├── dto/EmployeeDTO.java
│   │   │   ├── entity/Employee.java
│   │   │   ├── entity/AuditLog.java
│   │   │   ├── exception/GlobalExceptionHandler.java
│   │   │   ├── exception/EmployeeNotFoundException.java
│   │   │   ├── mapper/IEmployeeMapper.java
│   │   │   ├── mapper/IgnoreUnmappedMapperConfig.java
│   │   │   ├── repository/IEmployeeRepository.java
│   │   │   ├── repository/IAuditLogRepository.java
│   │   │   ├── service/EmployeeService.java
│   │   │   ├── service/IEmployeeService.java
│   │   │   ├── service/AuditLogService.java
│   │   │   ├── service/IAuditLogService.java
│   │   │   └── EmployeeServiceApplication.java
│   ├── resources/
│   │   ├── application.yml
├── pom.xml
└── README.md

---

## 🚀 Funcionalidades

- Crear empleados (`POST`)
- Obtener todos o por ID (`GET`)
- Actualizar parcialmente un empleado (`PATCH`)
- Eliminar empleado por ID (`DELETE`)
- Validación de entrada con `@Valid`
- Manejo de errores con `GlobalExceptionHandler`
- Mapeo de DTOs con MapStruct
- Base de datos H2 en memoria
- Bitácora de eventos (acciones exitosas y fallidas)
- Logging con Lombok (`@Slf4j`)
- Muestra en el log cualquier header recibido en el request
- Documentación de contrato
- Documentación de README
- OpenAPI 3
- Pruebas unitarias con Junit 5 y Mockito

## 📂 API Endpoints

| Method | Endpoint             | Description                                  | State code                    	|
|--------|----------------------|----------------------------------------------|--------------------------------|
| GET    | `/employees`     	| Listar todos los empleados                   | 200 OK / 404 Not Found        	|
| GET    | `/employees/{id}`| Obtener un empleado por ID                   | 200 OK / 404 Not Found        	|
| POST   | `/employees/{id}`| Guardar uno o mas empleados                  | 201 CREATED / 404 Not Found		|
| PATCH  | `/employees/{id}`| Actualizar parcialmente un empleado          | 200 OK / 404 Not Found	        |
| DELETE | `/employees/{id}`| Eliminar un empleado por ID                  | 200 OK / 404 Not Found			|

---

## 📘 Bitácora de eventos

Cada acción ejecutada (éxito o error) se registra en la tabla `auditlog` con los siguientes campos:

- `action`: tipo de operación (ej. `LISTING`, `UPDATE`)
- `description`: mensaje descriptivo con estado (`[SUCCESS]` o `[FAILURE]`)
- `timestamp`: fecha y hora del evento

Ejemplo de registros:

| ID | action 		  | description                         	   | dateTime            |
|----|----------------|--------------------------------------------|---------------------|
| 1  | LISTING 		  | Employees retrieved successfully [SUCCESS] | 2025-05-21T10:00:00 |
| 2  | LISTING_FAILED | No employees found [FAILURE]        	   | 2025-05-21T10:01:00 |

---

## 🔍 Acceso a H2 Console

URL: http://localhost:9001/h2-console
JDBC URL: jdbc:h2:file:~/data/employees;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;DB_CLOSE_DELAY=-1;TRACE_LEVEL_FILE=4;MODE=Oracle

