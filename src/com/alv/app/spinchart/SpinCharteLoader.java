package com.alv.app.spinchart;

import java.util.ArrayList;
import java.util.HashMap;

import com.alv.app.R;
import com.alv.db.charte.Charte;
import com.alv.db.charte.Fleche;
import com.alv.db.charte.Groupe;
import com.alv.db.charte.CharteDataSource;

import android.content.Context;

public class SpinCharteLoader {
    
	
	Context c;
	CharteDataSource charteDS;
	public SpinCharteLoader(Context c){
		
		this.c = c;
		 charteDS =  new CharteDataSource(c);
		 charteDS.open();
	}
	
	public void boot(){
		
		
		if (charteDS.getAll().size() == 0 ) {
			bootDB(R.raw.easton1,R.raw.easton2,R.raw.easton3);
			bootDB(R.raw.ce1,R.raw.ce2,R.raw.ce3);
		}
		
		charteDS.testFleches();
		 charteDS.close();
	}
	
	
	public void bootDB(int r1,int r2, int r3){
		//charte
		CSVLoader loader = new CSVLoader(c, r1);
		
		System.out.println("charte loader.result.size "+loader.result.size());
		
		
		for (int i=0 ; i<loader.result.size() ; i++) {
			HashMap<String,ArrayList<String>> rr = loader.result.get(i);
			ArrayList<String> tailles = rr.get("length");
			ArrayList<String> lows = rr.get("low");
			ArrayList<String> hights = rr.get("hight");
			ArrayList<String> groupes = rr.get("groupname");
			long taille = 0;
			long low = 0;
			long hight = 0 ;
			try {
		          taille = Long.parseLong(tailles.get(0));
		          low = Long.parseLong(lows.get(0));
		          hight = Long.parseLong(hights.get(0));

		      } catch (NumberFormatException nfe) {
		         System.out.println("NumberFormatException: " + nfe.getMessage());
		      }

			String groupe = groupes.get(0);
			
			System.out.println("charte "+taille+low+hight+groupe);
			
			Charte charteB = new Charte();
			charteB.setLength(taille);
			charteB.setLow(low);
			charteB.setHight(hight);
			
			charteB = charteDS.create(charteB);
			
			
			Groupe groupeB = new Groupe();
			groupeB.setName(groupe);
			groupeB = charteDS.createGroupe(groupeB);

			charteDS.createCharteGroupe(charteB, groupeB);
			
		}
		
		
		//fleches
		loader = new CSVLoader(c,r2);
		for (int i=0 ; i<loader.result.size() ; i++) {
			HashMap<String,ArrayList<String>> rr = loader.result.get(i);
			ArrayList<String> modeles = rr.get("modele");
			ArrayList<String> names = rr.get("name");
			ArrayList<String> surnames = rr.get("surname");
			ArrayList<String> grains = rr.get("grain");
			ArrayList<String> spins = rr.get("spin");
			ArrayList<String> diametreoutsides = rr.get("diametreoutside");
			ArrayList<String> tailles = rr.get("taille");
			ArrayList<String> fabricants = rr.get("fabricant");

			float taille =0.0f;
			float grain =0.0f;
			float diametreoutside =0.0f;
			
			
			String modele = modeles.get(0);
			String name = names.get(0);
			String surname = surnames.get(0);
			String spin = spins.get(0);
			String fabricant = fabricants.get(0);

			
			try {
		          taille = Float.parseFloat(tailles.get(0));
		          grain = Float.parseFloat(grains.get(0));
		          diametreoutside = Float.parseFloat(diametreoutsides.get(0));

		      } catch (NumberFormatException nfe) {
		         System.out.println("NumberFormatException: " + nfe.getMessage());
		      }

			System.out.println("fleche "+modele +name +grain +spin+taille);

			Fleche flecheB = new Fleche();
			flecheB.setModele(modele);
			flecheB.setName(name);
			flecheB.setSurname(surname);
			flecheB.setSpin(spin);
			flecheB.setFabricant(fabricant);
			flecheB.setTaille(taille);
			flecheB.setGrain(grain);
			flecheB.setDiametreoutside(diametreoutside);
			
			
			flecheB = charteDS.createFleche(flecheB);
			
		}
		
		
		//groups
		loader = new CSVLoader(c, r3);
		for (int i=0 ; i<loader.result.size() ; i++) {
			HashMap<String,ArrayList<String>> rr = loader.result.get(i);
			ArrayList<String> groups = rr.get("group");
			ArrayList<String> modeles = rr.get("modele");

			
			String groupe = groups.get(0);
			Groupe groupeB = charteDS.getGroupeByName(groupe);
			
			System.out.println("groupe "+groupe + "nbre fleches " + modeles.size());
			for (String modele : modeles){
				System.out.println("fleche groupe "+modele);
				Fleche  flecheB = charteDS.getFlecheByModele(modele);
				charteDS.createGroupeFleche(groupeB, flecheB);

			}
		}
		
		

		
	}
}
