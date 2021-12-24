package fr.architecture.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Getter
    @Setter
    List<Integer> lstTable = new ArrayList<>();
    
    @Getter
    @Setter
    Map<String, Integer> lstTablePrise = new HashMap<>();
    

    public Restaurant(List<Serveur> lstServeurs, List<Integer> lstTable) {
        this.lstServeurs = lstServeurs;
        this.lstTable = lstTable;
    }

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
    
    public void addClientToTable(String name, int numberTable) {
    	if(this.lstTable.contains((Integer) numberTable)) {
    		this.lstTablePrise.put(name, (Integer) numberTable);
    		this.lstTable.remove((Integer) numberTable);
    	}
    }
    
    public void removeClientToTable(String nameClient) {
    	this.lstTable.add((Integer) this.lstTablePrise.get(nameClient));
    	this.lstTablePrise.remove(nameClient);
    }
    
    public void showFreeTable() {
    	for(Integer i: this.lstTable) {
    		System.out.println(i);
    	}
    }
    
    public boolean findTable(int number) {
    	return this.lstTable.contains((Integer) number);
    }
}