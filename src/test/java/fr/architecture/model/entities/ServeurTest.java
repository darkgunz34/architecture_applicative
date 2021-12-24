package fr.architecture.model.entities;

import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.ServeurFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.time.LocalDate;

@SpringBootTest
class ServeurTest {

    /* SCOPE Serveur 3/3 */

    /*
    ÉTANT DONNÉ un nouveau serveur
    QUAND on récupère son chiffre d'affaires
    ALORS celui-ci est à 0
     */
    @Test()
    void testInitServeurChiffreAffaire(){
        Serveur server = ServeurFactory.getServeur();

        Assert.assertNotNull(server,"L'objet serveur est null");
        Assert.assertEquals(0,(int) server.getMontantTotalDesCommande(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }

    /*
    ÉTANT DONNÉ un nouveau serveur
    QUAND il prend une commande
    ALORS son chiffre d'affaires est le montant de celle-ci
     */
    @ParameterizedTest(name = "Ajoute une commande et calcul le prix du chiffre d'affaire")
    @ValueSource(doubles = {0,2,4,6,-10})
    void testAjoutPrixCommandeAuChiffreAffaire(double montant){

        Serveur server = ServeurFactory.getServeur();
        Commande commande = CommandeFactory.getCommandeBoissonAvecMontant(montant, LocalDate.now());
        server.ajouterCommande(commande);

        Assert.assertNotNull(server,"L'objet serveur est null");
        Assert.assertNotNull(commande,"L'objet commande est null");
        Assert.assertEquals(montant,server.getMontantTotalDesCommande(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }

    /*
    ÉTANT DONNÉ un serveur ayant déjà pris une commande
    QUAND il prend une nouvelle commande
    ALORS son chiffre d'affaires est la somme des deux commandes
     */
    @ParameterizedTest(name = "Ajoute de deux commandes et calcul le prix du chiffre d'affaire")
    @ValueSource(doubles = {0,2,4,6,-10})
    void testAjouterPlusieursCommandes(double montant){
        double montantSortie = montant*2;

        Serveur server = ServeurFactory.getServeur();

        Commande commande = CommandeFactory.getCommandeBoissonAvecMontant(montant, LocalDate.now());
        server.ajouterCommande(commande);

        Commande commande2 = CommandeFactory.getCommandeBoissonAvecMontant(montant, LocalDate.now());
        server.ajouterCommande(commande2);

        Assert.assertNotNull(server,"L'objet serveur est null");
        Assert.assertNotNull(commande,"L'objet commande est null");
        Assert.assertNotNull(commande2,"L'objet commande2 est null");
        Assert.assertEquals(montantSortie,server.getMontantTotalDesCommande(),"Le montant du chiffre d'affaire ne correspond pas au montant des commandes");
    }
}
