	 */
	public List<CarResponse> findAllWithClients() {
		return carRepository.findAll().stream()
				.map(car -> {
					CarResponse response = new CarResponse(car);
					try {
						Client client = restTemplate.getForObject(
								"http://SERVICE-CLIENT/api/client/" + car.getClientId(),
								Client.class
						);
						response.setClient(client);
					} catch (Exception e) {
						System.err.println("Erreur lors de la récupération du client: " + e.getMessage());
					}
					return response;
				})
				.collect(Collectors.toList());
	}
}
package com.example.car.services;

import com.example.car.entities.Car;
import com.example.car.entities.Client;
import com.example.car.models.CarResponse;
import com.example.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service  // Marque cette classe comme un composant Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Récupère toutes les voitures de la base de données
	 * @return Liste de toutes les voitures
	 */
	public List<Car> findAll() {
		return carRepository.findAll();
	}

	/**
	 * Récupère une voiture par son identifiant
	 * @param id Identifiant de la voiture
	 * @return La voiture correspondante
	 * @throws Exception Si aucune voiture n'est trouvée avec cet ID
	 */
	public Car findById(Long id) throws Exception {
		return carRepository.findById(id)
				.orElseThrow(() -> new Exception("Voiture non trouvée avec l'ID: " + id));
	}

	/**
	 * Récupère une voiture avec les informations du client associé
	 * @param id Identifiant de la voiture
	 * @return CarResponse avec détails de la voiture et du client
	 * @throws Exception Si la voiture n'existe pas
	 */
	public CarResponse findByIdWithClient(Long id) throws Exception {
		Car car = findById(id);
		CarResponse response = new CarResponse(car);

		try {
			// Appel au service CLIENT via le Gateway
			Client client = restTemplate.getForObject(
					"http://SERVICE-CLIENT/api/client/" + car.getClientId(),
					Client.class
			);
			response.setClient(client);
		} catch (Exception e) {
			// Si le client n'existe pas ou le service est indisponible
			System.err.println("Erreur lors de la récupération du client: " + e.getMessage());
		}

		return response;
	}

	/**
	 * Récupère toutes les voitures d'un client spécifique
	 * @param clientId ID du client
	 * @return Liste des voitures du client
	 */
	public List<Car> findByClientId(Long clientId) {
		return carRepository.findByClientId(clientId);
	}

	/**
	 * Ajoute une nouvelle voiture ou met à jour une voiture existante
	 * @param car La voiture à sauvegarder
	 * @return La voiture sauvegardée avec son ID généré
	 */
	public Car addCar(Car car) {
		return carRepository.save(car);
	}

	/**
	 * Supprime une voiture
	 * @param id Identifiant de la voiture à supprimer
	 * @throws Exception Si la voiture n'existe pas
	 */
	public void deleteCar(Long id) throws Exception {
		if (!carRepository.existsById(id)) {
			throw new Exception("Voiture non trouvée avec l'ID: " + id);
		}
		carRepository.deleteById(id);
	}

	/**
	 * Récupère toutes les voitures avec les informations de leurs clients respectifs
	 * @return Liste de CarResponse avec détails complets

