package fr.architecture.model.factory;

import fr.architecture.model.entities.Cuisine;

public final class CuisineFactory {

    private CuisineFactory(){

    }

    public static Cuisine getcuisine(){
        return new Cuisine();
    }
}
