package fr.architecture.model.entities;

import fr.architecture.model.factory.RestaurantFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class RestaurantTest {

    /* TODO: G2 cas Y a implémenter
    TODO : Ton test passe mais il est inutile car il n'y a pas d'ajout des serveur dans la classe restaurant.
    TODO : Implémentation de ce bloc de code à revoir.
    SCOPE Restaurant
    ÉTANT DONNÉ un restaurant ayant X serveurs
    QUAND tous les serveurs prennent une commande d'un montant Y
    ALORS le chiffre d'affaires de la franchise est X * Y
    CAS(X = 0; X = 1; X = 2; X = 100)
    CAS(Y = 1.0)
     */
	@ParameterizedTest(name = "Ajoute {0} en tant que quantité de serveur et valide le montant du chiffre d'affaire")
    @ValueSource(ints = {0,1,2,100})
    void testCalculChiffreAffaireSuivantNbrServeurs(int serveur){
		// Arrange
        Restaurant restaurant = RestaurantFactory.getRestaurantAvecServeur(serveur);
        
        // act
        for(Serveur s: restaurant.getLstServeurs() ) {
        	s.ajouterCommande(new Commande(10, LocalDate.now()));
        }
        restaurant.SommeWhenSpecial();
        // assert
        Assertions.assertNotNull(restaurant,"L'objet serveur est null");
        Assertions.assertEquals(serveur * 10,restaurant.getChiffreAffaireFranchise(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }
}
