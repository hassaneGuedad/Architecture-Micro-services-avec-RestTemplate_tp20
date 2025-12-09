# Spring Cloud Gateway Service

Ce service assure la gestion centralisée des requêtes en utilisant Spring Cloud Gateway. Il permet la redirection des requêtes vers les microservices enregistrés dans le registre Eureka.

## Configuration

Le service Gateway est configuré pour :
- Écouter sur le port **8888**
- S'enregistrer auprès d'Eureka à `http://localhost:8761/eureka/`
- Découvrir automatiquement les services disponibles
- Router dynamiquement les requêtes via le pattern : `http://localhost:8888/SERVICE-NAME/chemin`

## Lancement

### Prérequis
- Java 17+
- Maven
- Serveur Eureka actif sur le port 8761

### Commandes

**Compiler et exécuter :**
```bash
mvn clean install
mvn spring-boot:run
```

**Ou directement avec Maven :**
```bash
mvn clean spring-boot:run
```

## Endpoints disponibles

### Actuator
- `GET /actuator/health` - État de santé du Gateway
- `GET /actuator/info` - Informations sur l'application
- `GET /actuator/gateway/routes` - Liste des routes disponibles
- `GET /actuator/gateway/filters` - Filtres configurés

### Services via Gateway
- `http://localhost:8888/SERVICE-CLIENT/api/client` - Accès au service CLIENT
- `http://localhost:8888/SERVICE-VOITURE/api/voiture` - Accès au service VOITURE

## Architecture

```
┌─────────────────────────────────────┐
│   Client HTTP                        │
│   (Navigateur, Postman, etc.)       │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│   Spring Cloud Gateway               │
│   Port: 8888                         │
│   - Route Discovery                 │
│   - Load Balancing                  │
│   - Request/Response Filters        │
└──────────────┬──────────────────────┘
               │
       ┌───────┴────────┐
       ▼                ▼
┌──────────────┐  ┌──────────────┐
│ SERVICE-     │  │ SERVICE-     │
│ CLIENT       │  │ VOITURE      │
│ Port: 8081   │  │ Port: 8082   │
└──────────────┘  └──────────────┘
       │                │
       └────────┬───────┘
              ▼
       ┌──────────────┐
       │   Eureka     │
       │ Port: 8761   │
       └──────────────┘
```

## Logs

Les logs du Gateway sont configurés pour afficher :
- **INFO** : Informations sur le routage et les filtres
- **INFO** : Détails des requêtes HTTP sortantes vers les microservices

## Troubles courants

### Le Gateway ne découvre pas les services
- Vérifier que Eureka est actif sur `http://localhost:8761`
- Vérifier que les microservices sont enregistrés auprès d'Eureka
- Consulter les logs du Gateway avec niveau DEBUG

### Les routes ne fonctionnent pas
- Vérifier que `spring.cloud.gateway.discovery.locator.enabled: true`
- Consulter `/actuator/gateway/routes` pour voir les routes disponibles
- Vérifier la casse du nom du service (utiliser les majuscules si `lower-case-service-id: false`)

## Dépendances principales

- Spring Boot 3.5.8
- Spring Cloud 2025.0.0
- Spring Cloud Gateway
- Eureka Client
- Spring Boot Actuator

