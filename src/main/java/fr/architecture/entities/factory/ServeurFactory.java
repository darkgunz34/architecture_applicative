package fr.architecture.entities.factory;

import fr.architecture.entities.model.Serveur;

public final class ServeurFactory {

    private ServeurFactory(){

    }

    public static Serveur getServeur(){
        return new Serveur();
    }
}
