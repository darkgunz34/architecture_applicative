package fr.architecture.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Cuisine {

    @Getter
    List<Nourriture> lstNourriture = new ArrayList<>();

    public void ajouterUneCommandeEnCuisine(Nourriture nourriture){
        this.lstNourriture.add(nourriture);
    }

}
