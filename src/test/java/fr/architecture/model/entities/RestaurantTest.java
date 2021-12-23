package fr.architecture.model.entities;

import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.RestaurantFactory;
import fr.architecture.model.factory.ServeurFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RestaurantTest {

    /*SCOPE Restaurant 2/2 */

    /*
    SCOPE Restaurant
    ÉTANT DONNÉ un restaurant ayant X serveurs
    QUAND tous les serveurs prennent une commande d'un montant Y
    ALORS le chiffre d'affaires de la franchise est X * Y
    CAS(X = 0; X = 1; X = 2; X = 100)
     */
	@ParameterizedTest(name = "Ajoute {0} en tant que quantité de serveur et valide le montant du chiffre d'affaire")
    @ValueSource(ints = {0,1,2,100})
    void testCalculChiffreAffaireSuivantNbrServeurs(int serveur){
        int montantMontantCommande1 = 10;
        int montantMontantCommande2 = 15;
        int montantTotalDesDeuxCommande = montantMontantCommande1+montantMontantCommande2;

        List<Serveur> lstServeurs = new ArrayList<>();
        for(int i = 0;i<serveur;i++){
            Serveur server1 = ServeurFactory.getServeur();
            server1.ajouterCommande(CommandeFactory.getCommandeAvecMontant(montantMontantCommande1,LocalDate.now()));
            server1.ajouterCommande(CommandeFactory.getCommandeAvecMontant(montantMontantCommande2,LocalDate.now()));
            lstServeurs.add(server1);
        }

        Restaurant restaurant = RestaurantFactory.getRestaurantAvecServeur(lstServeurs);
        
        // assert
        Assertions.assertNotNull(restaurant,"L'objet serveur est null");
        Assertions.assertEquals(serveur * montantTotalDesDeuxCommande,restaurant.chiffreAffaireFranchise(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }

    /*
        CAS(Y = 1.0)
     */
    @ParameterizedTest(name = "Ajoute d'un montant de commande égale à {0}")
    @ValueSource(doubles = {1})
    void testCalculChiffreAffaireSuivantNbrServeurs(double montant){
        Serveur serveur = ServeurFactory.getServeur();
        serveur.ajouterCommande(CommandeFactory.getCommandeAvecMontant(montant,LocalDate.now()));

        Restaurant restaurant = RestaurantFactory.getRestaurant();
        restaurant.ajouterServeur(serveur);

        Assertions.assertNotNull(restaurant,"L'objet restaurant est null");
        Assertions.assertEquals(montant,restaurant.chiffreAffaireFranchise(),"Le montant du chiffre d'affaire ne correspond pas au montant total des commande du restaurant");
    }
}
