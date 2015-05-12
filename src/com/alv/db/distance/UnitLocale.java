package com.alv.db.distance;

import java.util.Locale;

   

public class UnitLocale {
    public static UnitLocale Imperial = new UnitLocale("Yard");
    public static UnitLocale Metric = new UnitLocale("mêtre");
    
    private String unit;
    
    
    public UnitLocale(String unit) {
		// TODO Auto-generated constructor stub
    	this.unit=unit;
	}
    
    public String getUnit(){
    	return this.unit;
    }
	public static UnitLocale getDefault() {
            return getFrom(Locale.getDefault());
    }
    public static UnitLocale getFrom(Locale locale) {
        String countryCode = locale.getCountry();
        if ("US".equals(countryCode)) return Imperial; // USA
        if ("LR".equals(countryCode)) return Imperial; // liberia
        if ("MM".equals(countryCode)) return Imperial; // burma
        return Metric;
    }
}
