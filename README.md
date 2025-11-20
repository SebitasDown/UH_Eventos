# Event Management API

API REST para gestión de eventos y lugares con Spring Boot. Implementa MapStruct para mapeo DTO-Entity y documentación con Swagger/OpenAPI.

## Descripción

Sistema de gestión de eventos que permite crear, listar, actualizar y eliminar eventos y lugares (venues). Utiliza patrón DTO, MapStruct para conversiones automáticas y validaciones con Bean Validation.

## Tecnologías

Java 17+ | Spring Boot | Lombok | MapStruct | Swagger/OpenAPI | Bean Validation

## Estructura del Proyecto

    src/main/java/com/UH/OtherLevel/
    ├── controller/
    │   ├── EventController.java
    │   └── VanueController.java
    ├── service/
    │   ├── EventService.java
    │   ├── VenueService.java
    │   └── impl/
    ├── repository/
    │   ├── interfaces/
    │   └── impl/
    ├── model/
    │   ├── Event.java
    │   └── Venue.java
    ├── dto/
    │   ├── EventDTO.java
    │   └── VenueDTO.java
    ├── mapper/
    │   ├── EventMapper.java
    │   └── VanueMapper.java
    └── doc/
        └── OpenAplConfig.java

## Instalación

    mvn spring-boot:run

Acceso: `http://localhost:8080`
Swagger UI: `http://localhost:8080/swagger-ui.html`

## Endpoints

### Eventos

**Crear evento**

    POST /events
    {
        "name": "Concierto de Rock",
        "date": "2025-03-15T19:30:00",
        "venueId": 1
    }

**Listar eventos**

    GET /events

**Obtener por ID**

    GET /events/{id}

**Actualizar evento**

    PUT /events/{id}
    {
        "name": "Concierto Actualizado",
        "date": "2025-03-20T20:00:00",
        "venueId": 1
    }

**Eliminar evento**

    DELETE /events/{id}

### Lugares (Venues)

**Crear lugar**

    POST /venues
    {
        "name": "Auditorio Central",
        "address": "Calle 45 #22-10",
        "capacity": 350
    }

**Listar lugares**

    GET /venues

**Obtener por ID**

    GET /venues/{id}

**Actualizar lugar**

    PUT /venues/{id}

**Eliminar lugar**

    DELETE /venues/{id}

## Modelos de Datos

### Event

    {
        "id": Long,
        "name": String,
        "date": LocalDateTime,
        "venue": Venue
    }

### Venue

    {
        "id": Long,
        "name": String,
        "address": String,
        "capacity": Integer
    }

## DTOs

### EventDTO

    {
        "name": String (required),
        "date": LocalDateTime (required),
        "venueId": Long (required)
    }

### VenueDTO

    {
        "name": String (required),
        "address": String (required),
        "capacity": Integer (required)
    }

## Características

- **MapStruct**: Conversión automática entre Entity y DTO
- **Lombok**: Reduce código boilerplate (@Data, @RequiredArgsConstructor)
- **Bean Validation**: Validaciones con anotaciones (@NotBlank, @NotNull)
- **Swagger/OpenAPI**: Documentación automática de API
- **Almacenamiento en memoria**: ArrayList para desarrollo
- **Relación Event-Venue**: Un evento pertenece a un lugar

## Arquitectura

    Controller → Service → Repository → In-Memory Storage
         ↓
    DTO ↔ MapStruct ↔ Entity

## MapStruct

Conversión automática DTO ↔ Entity:

    @Mapper
    public interface EventMapper {
        EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
        
        EventDTO toDTO(Event event);
        Event toModel(EventDTO eventDTO);
        List<EventDTO> toDTOList(List<Event> events);
    }

## Validaciones

- Nombre de evento/lugar no puede estar vacío
- Fecha del evento es obligatoria
- VenueId debe existir al crear evento
- Capacidad del lugar es obligatoria

## Swagger Configuration

Documentación en: `/swagger-ui.html`

Configuración personalizada con información del desarrollador y descripción de la API.

## Códigos HTTP

| Código | Descripción              |
|--------|--------------------------|
| 200    | OK - Operación exitosa   |
| 404    | Not Found - No existe    |
| 400    | Bad Request - Inválido   |

## Ejemplos de Uso

### Crear Venue y Event

    # 1. Crear lugar
    POST /venues
    {
        "name": "Teatro Principal",
        "address": "Carrera 50 #10-20",
        "capacity": 500
    }
    
    # Respuesta: { venueId: 1 }
    
    # 2. Crear evento
    POST /events
    {
        "name": "Festival de Jazz",
        "date": "2025-04-10T18:00:00",
        "venueId": 1
    }

## Desarrollador

Nombre: Sebastian
Email: mazoosebas@gmail.com
Proyecto: Event Management API

---

Desarrollado con Spring Boot, MapStruct y Swagger
# UH2-Eventos
# UH2-Eventos
# UH_Eventos
