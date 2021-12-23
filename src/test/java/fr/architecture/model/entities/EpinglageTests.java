package fr.architecture.model.entities;

import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.RestaurantFactory;
import fr.architecture.model.factory.ServeurFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;


/*
La présence du code pourrait être dans serveur mais déplacer ici pour la relecture de nos tests.
*/
class EpinglageTests {

    /* SCOPE Epinglage : 2/3 */

    /*
    ÉTANT DONNE un serveur ayant pris une commande
	QUAND il la déclare comme non payée
	ALORS cette commande est marquée comme épinglée
     */
    @ParameterizedTest(name = "Commande non payer au serveur supérieur ou égale à 15 Jours")
    @ValueSource(longs = {15,16})
    void testCommandeMoinsDeXJours(long nombreDeJourASoustraire){
        LocalDate date = LocalDate.now().minusDays(nombreDeJourASoustraire);
        Serveur server = ServeurFactory.getServeur();
        Commande commande = CommandeFactory.getCommandeAvecMontant(10,date);

        server.ajouterCommande(commande);
        Assertions.assertTrue(server.getLstCommandesFlagNonPayer().contains(commande),"La commande ne peut pas être envoyer à la gendarmerie");
    }

    //Second test pour valider si une commande est inférieur à 15 jours alors on ne la transmet pas.
    @ParameterizedTest(name = "Commande non payer au serveur inférieur à 15 Jours")
    @ValueSource(longs = {0,14})
    void testCommandeMoinsDeXJoursOk(long nombreDeJourASoustraire){
        LocalDate date = LocalDate.now().minusDays(nombreDeJourASoustraire);
        Serveur server = ServeurFactory.getServeur();
        Commande commande = CommandeFactory.getCommandeAvecMontant(10,date);

        server.ajouterCommande(commande);
        Assertions.assertFalse(server.getLstCommandesFlagNonPayer().contains(commande),"La commande ne peut pas être envoyer à la gendarmerie");
    }

    /*
    ÉTANT DONNE une commande à transmettre gendarmerie
    QUAND on consulte la liste des commandes à transmettre du restaurant
    ALORS elle y figure
     */
    @ParameterizedTest(name = "Commande non payer au serveur")
    @ValueSource(longs = {15,16})
    void testCommandeATransmettreDepuisUnRestaurant(long nombreDeJourASoustraire){
        LocalDate date = LocalDate.now().minusDays(nombreDeJourASoustraire);
        Serveur server = ServeurFactory.getServeur();
        Commande commande = CommandeFactory.getCommandeAvecMontant(10,date);
        Restaurant restaurant = RestaurantFactory.getRestaurant();

        server.ajouterCommande(commande);
        restaurant.ajouterServeur(server);

        Assertions.assertTrue(restaurant.getLstServeurs().get(0).getLstCommandesFlagNonPayer().contains(commande),"La commande ne peut pas être envoyer à la gendarmerie");
    }

    /*
    ÉTANT DONNE une commande à transmettre gendarmerie
    QUAND elle est marquée comme transmise à la gendarmerie
    ALORS elle ne figure plus dans la liste des commandes à transmettre du restaurant
     */
    @ParameterizedTest(name = "Commande non payer au serveur")
    @ValueSource(longs = {15,16})
    void testCommandeTransmisALaGendarmerie(long nombreDeJourASoustraire){
        LocalDate date = LocalDate.now().minusDays(nombreDeJourASoustraire);
        Serveur server = ServeurFactory.getServeur();
        Commande commande = CommandeFactory.getCommandeAvecMontant(10,date);
        Restaurant restaurant = RestaurantFactory.getRestaurant();

        server.ajouterCommande(commande);
        restaurant.ajouterServeur(server);

        Assertions.assertTrue(restaurant.getLstServeurs().get(0).getLstCommandesFlagNonPayer().contains(commande),"La commande ne peut pas être envoyer à la gendarmerie");
        Assertions.assertTrue(restaurant.getLstServeurs().get(0).transmissionALaGendarmerie().contains(commande),"La commande ne peut pas être envoyer à la gendarmerie");
        Assertions.assertEquals(0,restaurant.getLstServeurs().get(0).getLstCommandesFlagNonPayer().size(),"Il reste des commande non payer pour le serveur du restaurant");
        Assertions.assertEquals(0,restaurant.getLstServeurs().get(0).getLstCommandes().size(),"Il reste des commande pour le serveur du restaurant");
    }
}
