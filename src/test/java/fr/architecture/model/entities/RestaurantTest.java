package fr.architecture.model.entities;

import fr.architecture.model.Tools;
import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.RestaurantFactory;
import fr.architecture.model.factory.ServeurFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

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

        List<Serveur> lstServeurs = Tools.getLstServeursAvecCommandeEtDateDuJour(10,serveur);

        Restaurant restaurant = RestaurantFactory.getRestaurantAvecServeur(lstServeurs);

        Assert.assertNotNull(restaurant,"L'objet serveur est null");
        Assert.assertEquals(serveur * montantMontantCommande1,(int) restaurant.chiffreAffaireFranchise(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }

    /*
        CAS(Y = 1.0)
     */
    @ParameterizedTest(name = "Ajoute d'un montant de commande égale à {0}")
    @ValueSource(doubles = {1})
    void testCalculChiffreAffaireSuivantNbrServeurs(double montant){
        Serveur serveur = ServeurFactory.getServeur();
        serveur.ajouterCommande(CommandeFactory.getCommandeBoissonAvecMontant(montant,LocalDate.now()));

        Restaurant restaurant = RestaurantFactory.getRestaurant();
        restaurant.ajouterServeur(serveur);

        Assert.assertNotNull(restaurant,"L'objet restaurant est null");
        Assert.assertEquals(montant,restaurant.chiffreAffaireFranchise(),"Le montant du chiffre d'affaire ne correspond pas au montant total des commande du restaurant");
    }
}
