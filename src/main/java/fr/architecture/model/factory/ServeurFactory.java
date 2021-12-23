package fr.architecture.model.factory;

import fr.architecture.model.entities.Serveur;

public final class ServeurFactory {

    private ServeurFactory(){

    }

    public static Serveur getServeur(){
        return new Serveur();
    }
}
