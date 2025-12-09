package com.example.car.models;

import com.example.car.entities.Car;
import com.example.car.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modèle de réponse qui combine les données de la voiture avec les données du client
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
	private Car car;        // Informations de la voiture
	private Client client;  // Informations du client propriétaire

	/**
	 * Constructeur à partir d'une voiture
	 */
	public CarResponse(Car car) {
		this.car = car;
	}
}

