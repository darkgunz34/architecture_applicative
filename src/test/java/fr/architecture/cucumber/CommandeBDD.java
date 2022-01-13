package fr.architecture.cucumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.architecture.model.entities.Commande;
import fr.architecture.model.entities.Restaurant;
import fr.architecture.model.entities.Serveur;
import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.RestaurantFactory;
import fr.architecture.model.factory.ServeurFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.junit.jupiter.api.Assertions;

public class CommandeBDD {
	
	private List<Serveur> lserveur = new ArrayList<>();
	private Restaurant restaurant;

	@Given("un restaurant avec serveurs")
	public void prepare_serveur() {
		for(int i = 0; i < 8; i++) {
			Serveur server = ServeurFactory.getServeur();
			lserveur.add(server);
		}
	}
	
	@When("toutes les commandes sont prises")
	public void ajouter_commande_au_serveur_et_restaurant() {
		restaurant = RestaurantFactory.getRestaurant();
		for(Serveur s : lserveur) {
			for(int i =0; i < 3; i++) {
				Commande commande = CommandeFactory.getCommandeNourritureAvecMontant(10, LocalDate.now());
				s.setCuisineAssocier(restaurant.getCuisine());
				s.ajouterCommande(commande);
			}
			restaurant.ajouterServeur(s);
		}
		
	}
	
	@Then("elle apparaissent dans la liste des taches de la cuisine")
	public void check_commande_dans_la_liste() {
		for(Serveur s : lserveur) {
			Assertions.assertTrue(restaurant.getCuisine().getLstNourriture().stream().anyMatch(s.getLstCommandes()::contains));
		}
	}
}
