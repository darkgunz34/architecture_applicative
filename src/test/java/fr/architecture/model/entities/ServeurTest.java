package fr.architecture.model.entities;

import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.ServeurFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServeurTest {

    @Test()
    void testInitServeurChiffreAffaire(){
        Serveur server = ServeurFactory.getServeur();

        Assertions.assertNotNull(server,"L'objet serveur est null");
        Assertions.assertEquals(0,server.getChiffreAffaire(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }

    @ParameterizedTest(name = "Ajoute une commande et calcul le prix du chiffre d'affaire")
    @ValueSource(doubles = {0,2,4,6,-10})
    void testAjoutPrixCommandeAuChiffreAffaire(double montant){

        Serveur server = ServeurFactory.getServeur();
        Commande commande = CommandeFactory.getCommandeAvecMontant(montant);
        server.ajouterCommande(commande);

        Assertions.assertNotNull(server,"L'objet serveur est null");
        Assertions.assertNotNull(commande,"L'objet commande est null");
        Assertions.assertEquals(montant,server.getChiffreAffaire(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }

    @ParameterizedTest(name = "Ajoute de deux commandes et calcul le prix du chiffre d'affaire")
    @ValueSource(doubles = {0,2,4,6,-10})
    void testAjouterPlusieursCommandes(double montant){
        double montantSortie = montant*2;

        Serveur server = ServeurFactory.getServeur();

        Commande commande = CommandeFactory.getCommandeAvecMontant(montant);
        server.ajouterCommande(commande);

        Commande commande2 = CommandeFactory.getCommandeAvecMontant(montant);
        server.ajouterCommande(commande2);

        Assertions.assertNotNull(server,"L'objet serveur est null");
        Assertions.assertNotNull(commande,"L'objet commande est null");
        Assertions.assertNotNull(commande2,"L'objet commande2 est null");
        Assertions.assertEquals(montantSortie,server.getChiffreAffaire(),"Le montant du chiffre d'affaire ne correspond pas au montant des commandes");
    }

}
