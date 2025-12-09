package com.example.car.repositories;

import com.example.car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Marque cette interface comme un composant Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	// JpaRepository fournit déjà les méthodes CRUD de base:
	// save(), findById(), findAll(), deleteById(), etc.

	/**
	 * Récupère toutes les voitures d'un client spécifique
	 * @param clientId ID du client
	 * @return Liste des voitures du client
	 */
	List<Car> findByClientId(Long clientId);
}

