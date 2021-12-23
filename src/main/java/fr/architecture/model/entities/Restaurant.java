package fr.architecture.model.entities;

import java.util.ArrayList;
import java.util.List;

import fr.architecture.model.factory.CuisineFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Restaurant {

    @Getter
    @Setter
    List<Serveur> lstServeurs = new ArrayList<>();

    @Getter
    Cuisine cuisine = CuisineFactory.getcuisine();

    @Getter
    @Setter
    int nbServeurs;

    public Restaurant(List<Serveur> lstServeurs) {
        this.lstServeurs = lstServeurs;
    }

    public void ajouterServeur(final Serveur serveur) {
        this.lstServeurs.add(serveur);
    }

    public double chiffreAffaireFranchise(){
        double montantChiffreAffaire = 0;
        for (Serveur server: this.lstServeurs) {
            montantChiffreAffaire += server.getMontantTotalDesCommande();
        }
        return montantChiffreAffaire;
    }
}