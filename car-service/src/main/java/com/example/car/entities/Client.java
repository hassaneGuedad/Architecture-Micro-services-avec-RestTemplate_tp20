package com.example.car.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représentation du Client depuis le service CLIENT
 * Cette classe est utilisée pour les modèles de réponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	private Long id;
	private String nom;
	private Float age;
}

