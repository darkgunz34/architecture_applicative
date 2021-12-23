package fr.architecture.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Franchise {

    private List<Restaurant> lstRestaurants = new ArrayList<>();

    public Franchise(List<Restaurant> lstRestaurants) {
        this.lstRestaurants = lstRestaurants;
    }

    public double chiffreAffaireFranchise(){
        double montantChiffreAffaire = 0;
        for (Restaurant restaurant: this.lstRestaurants) {
            montantChiffreAffaire += restaurant.chiffreAffaireFranchise();
        }
        return montantChiffreAffaire;
    }

    public void ajouterRestaurant(Restaurant restaurant) {
        this.lstRestaurants.add(restaurant);
    }
}
