package com.example.car.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity  // Marque cette classe comme une entité JPA
@Data    // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les arguments
public class Car {

	@Id  // Marque ce champ comme clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incrémentation
	private Long id;

	private String marque;       // Marque de la voiture (ex: Toyota, BMW)
	private String modele;       // Modèle de la voiture (ex: Corolla, X5)
	private String couleur;      // Couleur de la voiture
	private Integer annee;       // Année de fabrication
	private Long clientId;       // ID du client propriétaire de la voiture

	// Lombok génère automatiquement les getters, setters, constructeurs, etc.
}

