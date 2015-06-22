package com.alv.app.spinchart;

import java.util.ArrayList;
import java.util.HashMap;

import com.alv.app.R;

import android.content.Context;

public class SpinCharteLoader {
    
	
	Context c;
	
	public SpinCharteLoader(Context c){
		
		this.c = c;
	}
	
	public void boot(){
		bootEaston();
		bootCE();
	}
	
	public void bootEaston(){
		
		//charte
		CSVLoader loader = new CSVLoader(c, R.raw.easton1);
		for (int i=0 ; i<loader.result.size() ; i++) {
			HashMap<String,ArrayList<String>> rr = loader.result.get(i);
			ArrayList<String> tailles = rr.get("length");
			ArrayList<String> lows = rr.get("low");
			ArrayList<String> hights = rr.get("hight");
			ArrayList<String> groupes = rr.get("groupname");
			
			String taille = tailles.get(0);
			String low = lows.get(0);
			String hight = hights.get(0);
			String groupe = groupes.get(0);
		}
		
		//groups
		loader = new CSVLoader(c, R.raw.easton3);
		for (int i=0 ; i<loader.result.size() ; i++) {
			HashMap<String,ArrayList<String>> rr = loader.result.get(i);
			ArrayList<String> groups = rr.get("group");
			ArrayList<String> modeles = rr.get("modele");

			
			String groupe = groups.get(0);
			
			for (String modele : modeles){
				
			}
		}
		
		
		//fleches
		loader = new CSVLoader(c, R.raw.easton2);
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

			
			String modele = modeles.get(0);
			String name = names.get(0);
			String surname = surnames.get(0);
			String grain = grains.get(0);
			String spin = spins.get(0);
			String diametreoutside = diametreoutsides.get(0);
			String taille = tailles.get(0);
			String fabricant = fabricants.get(0);
			

		}
		
		
	}
	
	public void bootCE(){
		
	}
}
