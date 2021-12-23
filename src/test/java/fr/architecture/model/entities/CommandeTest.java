package fr.architecture.model.entities;

import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.RestaurantFactory;
import fr.architecture.model.factory.ServeurFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CommandeTest {

    /*
	ÉTANT DONNE un serveur dans un restaurant
	QUAND il prend une commande de nourriture
	ALORS cette commande apparaît dans la liste de tâches de la cuisine de ce restaurant
     */
    @Test
    void testAjouterUneCommandeEnCuisine(){
        Commande commande = CommandeFactory.getCommandeNourritureAvecMontant(10, LocalDate.now());
        Serveur server = ServeurFactory.getServeur();
        Restaurant restaurant = RestaurantFactory.getRestaurant();
        restaurant.ajouterServeur(server);

        server.setCuisineAssocier(restaurant.getCuisine());
        server.ajouterCommande(commande);

        Assertions.assertNotNull(commande,"L'objet commande est null");
        Assertions.assertNotNull(server,"L'objet serveur est null");
        Assertions.assertTrue(server.getLstCommandes().contains(commande),"La commande n'est pas prise en compte dans par le serveur");
        Assertions.assertTrue(restaurant.getCuisine().getLstNourriture().contains(commande),"La commande n'a pas été envoyer à la cuisine");
    }

    /*
    ÉTANT DONNE un serveur dans un restaurant
	QUAND il prend une commande de boissons
	ALORS cette commande n'apparaît pas dans la liste de tâches de la cuisine de ce restaurant
     */
    @Test
    void testAjouterUneBoissonSansLaCuisine(){
        Commande commande = CommandeFactory.getCommandeBoissonAvecMontant(10, LocalDate.now());
        Serveur server = ServeurFactory.getServeur();
        Restaurant restaurant = RestaurantFactory.getRestaurant();
        restaurant.ajouterServeur(server);

        server.setCuisineAssocier(restaurant.getCuisine());
        server.ajouterCommande(commande);

        Assertions.assertNotNull(commande,"L'objet commande est null");
        Assertions.assertNotNull(server,"L'objet serveur est null");
        Assertions.assertTrue(server.getLstCommandes().contains(commande),"La commande n'est pas prise en compte dans par le serveur");
        Assertions.assertFalse(restaurant.getCuisine().getLstNourriture().contains(commande),"La commande n'a pas été envoyer à la cuisine");
    }
}
