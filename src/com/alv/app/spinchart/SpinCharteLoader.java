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
	
	public boolean isbootneeded(){
		return (charteDS.getAll().size() == 0 );
	}
	
	public void closeDB(){
		 charteDS.close();
	}
	
	public void bootSQL(){
		
		SQLLoader loader =new SQLLoader(c,charteDS);
		if (isbootneeded() ) {
			loader.loadSQL(R.raw.charte);
		}
		charteDS.testFleches();
		charteDS.close();
	}
	public void boot(){
		
		
		if (isbootneeded() ) {
			charteDS.beginTransaction();
			bootDB(R.raw.easton1,R.raw.easton2,R.raw.easton3);
			charteDS.commitTransaction();
			charteDS.beginTransaction();
			bootDB(R.raw.ce1,R.raw.ce2,R.raw.ce3);
			charteDS.commitTransaction();
		}
		
		charteDS.testFleches();
		 charteDS.close();
	}
	
	
	public void bootDB(int r1,int r2, int r3){
		//charte
		CSVLoader loader = new CSVLoader(c, r1);
		
		//System.out.println("charte loader.result.size "+loader.result.size());
		
		
		for (int i=0 ; i<loader.resultAsString.size() ; i++) {
			HashMap<String,String> rr = loader.resultAsString.get(i);
			String taille = rr.get("length");
			String low = rr.get("low");
			String hight = rr.get("hight");
			String groupe = rr.get("groupname");
			long ltaille = 0;
			long llow = 0;
			long lhight = 0 ;
			try {
		          ltaille = Long.parseLong(taille);
		          llow = Long.parseLong(low);
		          lhight = Long.parseLong(hight);

		      } catch (NumberFormatException nfe) {
		         System.out.println("NumberFormatException_b: " + nfe.getMessage());
		      }

			
			//System.out.println("charte "+taille+low+hight+groupes);
			
			Charte charteB = new Charte();
			charteB.setLength(ltaille);
			charteB.setLow(llow);
			charteB.setHight(lhight);
			
			charteB = charteDS.create(charteB);
			
			
			Groupe groupeB = new Groupe();
			groupeB.setName(groupe);
			groupeB = charteDS.createGroupe(groupeB);

			charteDS.createCharteGroupe(charteB, groupeB);
			
		}
		
		
		//fleches
		loader = new CSVLoader(c,r2);
		for (int i=0 ; i<loader.resultAsString.size() ; i++) {
			HashMap<String,String> rr = loader.resultAsString.get(i);
			String modele = rr.get("modele");
			String name = rr.get("name");
			String surname = rr.get("surname");
			String grain = rr.get("grain");
			String spin = rr.get("spin");
			String diametreoutside = rr.get("diametreoutside");
			String taille = rr.get("taille");
			String fabricant = rr.get("fabricant");

			float ftaille =0.0f;
			float fgrain =0.0f;
			float fdiametreoutside =0.0f;
			
			
//			String modele = modeles.get(0);
//			String name = names.get(0);
//			String surname = surnames.get(0);
//			String spin = spins.get(0);
//			String fabricant = fabricants.get(0);
			
//			 System.out.println("taille1: " + taille);
//			 System.out.println("grain1: " + grain);
//			 System.out.println("diametreoutside1: " + diametreoutside);
			 
			 
			 
			char decSeparator='.';
//			NumberFormat nf = NumberFormat.getInstance();
//			if(nf instanceof DecimalFormat) {
//			    DecimalFormatSymbols sym = ((DecimalFormat) nf).getDecimalFormatSymbols();
//			     decSeparator = sym.getDecimalSeparator();
//			}
			try {
				  taille = taille.replace(',', decSeparator);
		          ftaille = Float.parseFloat(taille);
		          
				  grain = grain.replace(',', decSeparator);
		          fgrain = Float.parseFloat(grain);
		          
				  diametreoutside = diametreoutside.replace(',', decSeparator);
		          fdiametreoutside = Float.parseFloat(diametreoutside);
		          
		          
//					 System.out.println("taille2: " + taille);
//					 System.out.println("grain2: " + grain);
//					 System.out.println("diametreoutside2: " + diametreoutside);
//
//					 
//					 
//					 System.out.println("ftaille: " + ftaille);
//					 System.out.println("fgrain: " + fgrain);
//					 System.out.println("diametreoutside: " + fdiametreoutside);

					 

		      } catch (NumberFormatException nfe) {
		         System.out.println("NumberFormatException_b: " + nfe.getMessage());
		      }

			//System.out.println("fleche "+modele +name +grain +spin+taille);

			Fleche flecheB = new Fleche();
			flecheB.setModele(modele);
			flecheB.setName(name);
			flecheB.setSurname(surname);
			flecheB.setSpin(spin);
			flecheB.setFabricant(fabricant);
			flecheB.setTaille(ftaille);
			flecheB.setGrain(fgrain);
			flecheB.setDiametreoutside(fdiametreoutside);
			
			
			flecheB = charteDS.createFleche(flecheB);
			
		}
		
		
		//groups
		loader = new CSVLoader(c, r3);
		for (int i=0 ; i<loader.resultAsArray.size() ; i++) {
			HashMap<String,ArrayList<String>> rr = loader.resultAsArray.get(i);
			ArrayList<String> groups = rr.get("group");
			ArrayList<String> modeles = rr.get("modele");

			
			String groupe = groups.get(0);
			Groupe groupeB = charteDS.getGroupeByName(groupe);
			
			//System.out.println("groupe "+groupe + "nbre fleches " + modeles.size());
			for (String modele : modeles){
				//System.out.println("fleche groupe "+modele);
				Fleche  flecheB = charteDS.getFlecheByModele(modele);
				charteDS.createGroupeFleche(groupeB, flecheB);

			}
		}
		
		

		
	}
}
