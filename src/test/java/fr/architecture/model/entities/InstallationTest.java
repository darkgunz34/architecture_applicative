package fr.architecture.model.entities;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.architecture.model.factory.RestaurantFactory;

@SpringBootTest
class InstallationTest {
	List<Serveur> lstServ = new ArrayList<>();
	List<Integer> lstTable = new ArrayList<>();
	
	@BeforeEach
	public void initEach() {
		this.lstServ.add(new Serveur());
		this.lstTable.add(1);
		this.lstTable.add(2);
		this.lstTable.add(3);
		this.lstTable.add(4);
	}
	/*
	 * ÉTANT DONNE une table dans un restaurant ayant débuté son service
	QUAND un client est affecté à une table
	ALORS cette table n'est plus sur la liste des tables libres du restaurant
	*/
	@Test 
	void verificationTableRestaurant() {
		Restaurant res = RestaurantFactory.getRestaurantAvecServeur(this.lstServ, this.lstTable);
	
		res.addClientToTable("bob", 1);
		
		Assertions.assertNotNull(res,"L'objet restaurant est null");
		Assertions.assertEquals(false, res.findTable(1));
	}

	/*ÉTANT DONNE une table occupée par un client
	QUAND la table est libérée
	ALORS cette table appraît sur la liste des tables libres du restaurant
	*/
	@Test 
	void verificationTableLibreRestaurant() {
		Restaurant res = RestaurantFactory.getRestaurantAvecServeur(this.lstServ, this.lstTable);
		
		res.addClientToTable("bob", 3);
		res.removeClientToTable("bob");
		
		Assertions.assertNotNull(res,"L'objet restaurant est null");
		Assertions.assertEquals(true, res.findTable(3));
	}

}
