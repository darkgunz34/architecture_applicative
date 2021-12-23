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

	@Getter
	@Setter
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
		for (Serveur s : lstServeurs) {
			this.chiffreAffaireFranchise += s.getChiffreAffaire();
		}
	}

	//TODO: G² => A revoir cette méthode car on passe pas un nombre de serveur à créer mais une liste de serveur.
	public Restaurant(int nbServeur) {
		this.nbServeurs = nbServeur;
		for (int i = 0; i < nbServeur; i++) {
			this.ajouterServeur(new Serveur());
		}
	}

	//TODO: G² => A revoir cette méthode
	public void SommeWhenSpecial() {
		double special = 10;
		int count = 0;
		for (Serveur s : this.lstServeurs) {
			if (s.getChiffreAffaire() == special) {
				count += 1;
			}
		}
		if (count == this.nbServeurs) {
			this.chiffreAffaireFranchise = this.nbServeurs * special;
		}
	}
}