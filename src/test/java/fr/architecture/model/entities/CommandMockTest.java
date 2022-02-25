package fr.architecture.model.entities;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.architecture.model.factory.CommandeFactory;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
 class CommandMockTest {
	
	@Mock
	private Serveur serveurMock;
	
	@Mock
	private Restaurant restaurantMock;
	
	@Test
	void testCommandeWithMock() {
		Commande commande = CommandeFactory.getCommandeNourritureAvecMontant(10, LocalDate.now());
		this.serveurMock = Mockito.mock(Serveur.class);
		this.restaurantMock = Mockito.mock(Restaurant.class);
		
		
		this.restaurantMock.ajouterServeur(serveurMock);
		this.serveurMock.setCuisineAssocier(restaurantMock.getCuisine());
		this.serveurMock.ajouterCommande(commande);

		Assertions.assertTrue(this.serveurMock.getLstCommandes().contains(commande));
	}
	
	
}
