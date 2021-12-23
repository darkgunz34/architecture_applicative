package fr.architecture.model.entities;

import fr.architecture.model.factory.RestaurantFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestaurantTest {

	@ParameterizedTest(name = "Ajoute 0, 1, 2, 100 en tant que quantité de serveur et valide le montant du chiffre d'affaire")
    @ValueSource(ints = {0,1,2,100})
    void testCalculChiffreAffaireSuivantNbrServeurs(int serveur){
		// Arrange
        Restaurant restaurant = RestaurantFactory.getRestaurantAvecServeur(serveur);
        
        // act
        for(Serveur s: restaurant.getListServeur() ) {
        	s.ajouterCommande(new Commande(10));
        }
        restaurant.SommeWhenSpecial();
        // assert
        Assertions.assertNotNull(restaurant,"L'objet serveur est null");
        Assertions.assertEquals(serveur * 10,restaurant.getChiffreAffaireFranchise(),"Le montant du chiffre d'affaire ne correspond pas au montant de la commande");
    }
}
