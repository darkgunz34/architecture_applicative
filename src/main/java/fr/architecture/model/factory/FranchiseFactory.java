package fr.architecture.model.factory;

import fr.architecture.model.entities.Franchise;
import fr.architecture.model.entities.Restaurant;

import java.util.List;

public final class FranchiseFactory {

	private FranchiseFactory(){

    }

    public static Franchise getFranchise(){
        return new Franchise();
    }
    
    public static Franchise getFranchiseAvecRestaurant(List<Restaurant> lstRestaurant) {
        return new Franchise(lstRestaurant);
    }

}
