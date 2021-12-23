package fr.architecture.model.entities;

import fr.architecture.model.Tools;
import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.FranchiseFactory;
import fr.architecture.model.factory.RestaurantFactory;
import fr.architecture.model.factory.ServeurFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class FranchiseTest {

    /* SCOPE Franchise 1/1 */

    /*
    ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacun
    QUAND tous les serveurs prennent une commande d'un montant Z
    ALORS le chiffre d'affaires de la franchise est X * Y * Z
    CAS(X = 0; X = 1; X = 2; X = 1000)
    CAS(Y = 0; Y = 1; Y = 2; Y = 1000)
    CAS(Z = 1.0)
     */
    @ParameterizedTest(name = "Ajoute {0} en tant que quantité de serveur et valide le montant du chiffre d'affaire d'une franchise")
    @ValueSource(ints = {0,1,2,100})
    void testCalculChiffreAffaireFranchise(int nombreDeServeur){
        int montantMontantCommande = 10;

        List<Serveur> lstServeurs = Tools.getLstServeursAvecCommandeEtDateDuJour(montantMontantCommande,nombreDeServeur);
        Restaurant restaurant = RestaurantFactory.getRestaurantAvecServeur(lstServeurs);

        List<Serveur> lstServeurs2 = Tools.getLstServeursAvecCommandeEtDateDuJour(montantMontantCommande,nombreDeServeur);
        Restaurant restaurant2 = RestaurantFactory.getRestaurantAvecServeur(lstServeurs2);

        List<Restaurant> lstRestaurant = new ArrayList<>();
        lstRestaurant.add(restaurant);
        lstRestaurant.add(restaurant2);

        Franchise franchise = FranchiseFactory.getFranchiseAvecRestaurant(lstRestaurant);

        Assertions.assertNotNull(restaurant,"L'objet restaurant est null");
        Assertions.assertEquals(nombreDeServeur * montantMontantCommande * lstRestaurant.size(),franchise.chiffreAffaireFranchise(),"Le montant du chiffre d'affaire ne correspond pas au montant des commandes");
    }

    /*
        CAS(Y = 1.0)
     */
    @ParameterizedTest(name = "Ajoute d'un montant de commande égale à {0}")
    @ValueSource(doubles = {1})
    void testCalculChiffreAffaireFranchise(double montant){
        Serveur serveur = ServeurFactory.getServeur();
        serveur.ajouterCommande(CommandeFactory.getCommandeBoissonAvecMontant(montant,LocalDate.now()));

        Restaurant restaurant = RestaurantFactory.getRestaurant();
        restaurant.ajouterServeur(serveur);

        Franchise franchise = FranchiseFactory.getFranchise();
        franchise.ajouterRestaurant(restaurant);

        Assertions.assertNotNull(franchise,"L'objet franchise est null");
        Assertions.assertEquals(montant,franchise.chiffreAffaireFranchise(),"Le montant du chiffre d'affaire de la franchise ne correspond pas au montant de la commande");
    }

}
