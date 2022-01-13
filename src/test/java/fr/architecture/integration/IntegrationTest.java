package fr.architecture.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fr.architecture.model.entities.Boissons;
import fr.architecture.model.entities.Nourriture;
import fr.architecture.model.entities.Serveur;

public class IntegrationTest {
	
	@Test
	public void commandIntegrationTest() {
		
		final DecimalFormat df = new DecimalFormat("0.00");
		Serveur serv = new Serveur();
		String path = "C:\\Users\\nicol\\Desktop\\architectureApplicativeDonnees.txt";
    	String text = "";// = "boisson:10.5";
        String res = "";
        double priceDrink = 0;
        double priceFood = 0;
        String SpriceDrink = "";
        String SpriceFood = "";
        
		
    	try
	    {
    		double nbrCommand = (Math.random() * 10) + 1;
    		
    		for (int i = 0; i < nbrCommand; i++) {
    			double typeCommand = (Math.random() * 10) + 1;
    			double price = Math.random() * 10;
    		 	String Sprice = df.format(price).replace(",", ".");
    		 	if(typeCommand < 5)
    		 	{
    		 		priceDrink += Double.parseDouble(Sprice);
    		 	}else
    		 	{
    		 		priceFood += Double.parseDouble(Sprice);
    		 	}
    			text += typeCommand < 5 ? "boisson:"+Sprice+"\n" : "nourriture:"+Sprice+"\n";
    		}    		
    		priceDrink = Double.parseDouble(df.format(priceDrink).replace(",", "."));
    		priceFood = Double.parseDouble(df.format(priceFood).replace(",", "."));
    		
    		/*
    		System.out.println(text);
    		System.out.println("priceDrink "+priceDrink);
    		System.out.println("priceFood "+priceFood);
    		*/
    		
	    	FileOutputStream fos = new FileOutputStream(path);
	    	byte[] bs = text.getBytes();
	    	fos.write(bs) ; 
	    	fos.close();	
	     }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }	
        
	    try
	    {	    	
	      File file = new File(path);  	
	      FileReader fr = new FileReader(file); 	
	      BufferedReader br = new BufferedReader(fr);  
	      StringBuffer sb = new StringBuffer();    
	      LocalDate date = LocalDate.now();   
	      String line;
	      String[] words;
	      
	      while((line = br.readLine()) != null)
	      {  
	    	  //System.out.println(line);
	    	  System.out.println(line.indexOf("boisson"));
	    	if(line.indexOf("boisson") > -1)
	    	{
		    	words = line.split(":");
		    	double dbl = Double.parseDouble(words[1]);
	    		Boissons boisson = new Boissons(dbl,date);
	    		System.out.println(dbl);
	    		System.out.println(date);
	    		System.out.println(boisson);
	    		System.out.println(serv);
	    		System.out.println(serv.getMontantTotalDesCommande());
	    		System.out.println(boisson.getMontantCommande());

	    		serv.ajouterCommande(boisson);
	    	}else
	    	{
		    	words = line.split(":");
		    	double dbl = Double.parseDouble(words[1]);
	    		Nourriture nourriture = new Nourriture(dbl,date);
	    		System.out.println(dbl);
	    		System.out.println(date);
	    		System.out.println(nourriture);
	    		System.out.println(serv);
	    		System.out.println(serv.getMontantTotalDesCommande());
	    		System.out.println(nourriture.getMontantCommande());

	    		
	    		serv.ajouterCommande(nourriture);
	    	}
	        sb.append(line);      
	        res = sb.toString();
	        //System.out.println(sb);
	        sb.append("\n");     
	      }
	      fr.close();    
		    
		  }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }				
	    
	    
		for (int i = 0; i < text.length(); i++) {
			 //System.out.println(text[i]);
		}
		//System.out.println("text "+text);
		//System.out.println("res "+res);
	    
	    //assertEquals(text, res);
	}
}
