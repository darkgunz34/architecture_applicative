package fr.architecture.model.factory;

import fr.architecture.model.entities.Restaurant;
import fr.architecture.model.entities.Serveur;

import java.util.List;

public final class RestaurantFactory {
	
	private RestaurantFactory(){

    }

    public static Restaurant getRestaurant(){
        return new Restaurant();
    }
    
    public static Restaurant getRestaurantAvecServeur(List<Serveur> lstServeurs) {
        return new Restaurant(lstServeurs);
    }
    
    public static Restaurant getRestaurantAvecServeur(List<Serveur> lstServeurs, List<Integer> lstTable) {
        return new Restaurant(lstServeurs, lstTable);
    }
    
}
