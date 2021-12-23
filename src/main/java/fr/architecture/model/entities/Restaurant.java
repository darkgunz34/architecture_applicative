package fr.architecture.model.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant {

	 List<Serveur> lstServeurs = new ArrayList<>();
	 
	 @Getter
	 @Setter
	 double chiffreAffaireFranchise;
	 
	 @Getter
	 @Setter
	 int nbServeurs;
	 
	 public void ajouterServeur(final Serveur serveur) {
	        this.lstServeurs.add(serveur);
	        updateChiffreAffaireFranchise();
	    }

	    private void updateChiffreAffaireFranchise() {
	        this.chiffreAffaireFranchise = 0;
	        for (Serveur s: lstServeurs) {
	            this.chiffreAffaireFranchise += s.getChiffreAffaire();
	        }
	    }

		public Restaurant(int nbServeur) {
			this.nbServeurs = nbServeur;
			for(int i = 0; i < nbServeur; i++) {
				this.ajouterServeur(new Serveur());
			}
		}
		
		public List<Serveur> getListServeur() {
			return this.lstServeurs;
		}
		
		public void SommeWhenSpecial() {
			double special = 10;
			int count = 0;
			for(Serveur s: this.getListServeur() ) {
	        	if(s.getChiffreAffaire() == special) {
	        		count += 1;
	        	}
	        }
			if(count == this.nbServeurs) {
				this.chiffreAffaireFranchise = this.nbServeurs * special;
			}
		}
		
		public double getChiffreAffaireFranchise() {
			return this.chiffreAffaireFranchise;
		}
}
