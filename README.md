# Architecture Microservices avec RestTemplate - TP20

## 📋 Description

Ce projet implémente une architecture microservices avec **Spring Boot 3.5.8** et **Spring Cloud 2025.0.0**, démontrant la communication inter-services via **RestTemplate** et le routage dynamique via **Spring Cloud Gateway**.

## 🏗️ Architecture

Le projet est composé de 3 microservices :

- **Gateway Service** (Port 8888) : API Gateway avec Spring Cloud Gateway
- **Car Service** (Port 8082) : Gestion des voitures avec MySQL
- **Client Service** (Port 8081) : Gestion des clients avec MySQL

Tous les services s'enregistrent auprès d'**Eureka Server** (Port 8761) pour la découverte de services.

## 🛠️ Technologies

- Java 17
- Spring Boot 3.5.8
- Spring Cloud 2025.0.0
- Spring Cloud Gateway
- Netflix Eureka Client
- Spring Data JPA
- MySQL 8.x
- Lombok
- Maven

## 🚀 Installation

### Prérequis

```bash
# Java 17+, Maven 3.6+, MySQL 8.x
```

### Configuration MySQL

```sql
CREATE DATABASE car_db;
CREATE DATABASE client_db;
```

### Démarrage

```bash
# 1. Cloner le projet
git clone https://github.com/hassaneGuedad/Architecture-Micro-services-avec-RestTemplate_tp20.git
cd TP20

# 2. Compiler les services
cd car-service && mvn clean install
cd ../gateway-service && mvn clean install

# 3. Démarrer les services (dans l'ordre)
# Terminal 1 - Eureka Server (http://localhost:8761)
# Terminal 2 - Car Service
cd car-service && mvn spring-boot:run

# Terminal 3 - Gateway Service
cd gateway-service && mvn spring-boot:run
```

## 🔌 API Endpoints

Tous les endpoints sont accessibles via le Gateway sur le port **8888** :

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/car-service/cars` | Liste toutes les voitures |
| GET | `/car-service/cars/{id}` | Récupère une voiture par ID |
| POST | `/car-service/cars` | Crée une nouvelle voiture |
| PUT | `/car-service/cars/{id}` | Met à jour une voiture |
| DELETE | `/car-service/cars/{id}` | Supprime une voiture |

### Exemple de requête

```bash
# Récupérer toutes les voitures
GET http://localhost:8888/car-service/cars

# Créer une voiture
POST http://localhost:8888/car-service/cars
Content-Type: application/json

{
  "marque": "Toyota",
  "modele": "Corolla",
  "couleur": "Rouge",
  "annee": 2020,
  "clientId": 1
}
```

## 📊 Modèle de Données

### Car Entity
```java
@Entity
public class Car {
    private Long id;
    private String marque;
    private String modele;
    private String couleur;
    private Integer annee;
    private Long clientId;
}
```

### Client Entity
```java
@Entity
public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
}
```

## 👥 Auteur

**Hassane Guedad**

---

## 📸 Screenshots

### 1. Eureka Dashboard - Services Enregistrés

<img width="639" height="327" alt="cap1" src="https://github.com/user-attachments/assets/c7897ced-27cb-4f53-9811-343e78b907d2" />


*Dashboard Eureka montrant tous les microservices enregistrés (Gateway, Car Service, Client Service)*

---

### 2. API Gateway - Liste des Voitures

![Cars List API](./screenshots/cars-list.png)

**Endpoint** : `GET http://localhost:8888/car-service/cars`

**Exemple de réponse** :
```json
[
  {
    "id": 1,
    "marque": "Toyota",
    "modele": "Corolla",
    "couleur": "Rouge",
    "annee": 2020,
    "clientId": 1,
    "client": {
      "id": 1,
      "nom": "Dupont",
      "prenom": "Jean",
      "email": "jean.dupont@email.com"
    }
  }
]
---
---

<img width="637" height="284" alt="CAP3" src="https://github.com/user-attachments/assets/e83a18c0-4e29-4164-a809-52371e57b6e4" />

<img width="641" height="308" alt="cap2" src="https://github.com/user-attachments/assets/e4230c1b-a53b-4353-a5a1-0e6222108cb3" />

<img width="640" height="229" alt="CAP4" src="https://github.com/user-attachments/assets/b62bc5a4-5b01-402e-9e20-fb73ed94fb10" />

<img width="611" height="269" alt="CAP5" src="https://github.com/user-attachments/assets/e3a65c05-a402-417a-a935-b1c402ad37c4" />

