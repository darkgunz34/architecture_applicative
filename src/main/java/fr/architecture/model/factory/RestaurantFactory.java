package fr.architecture.model.factory;

import fr.architecture.model.entities.Restaurant;

public class RestaurantFactory {
	
	private RestaurantFactory(){

    }

    public static Restaurant getRestaurant(){
        return new Restaurant();
    }
    
    public static Restaurant getRestaurantAvecServeur(int nbServeur) {
        return new Restaurant(nbServeur);
    }

}
