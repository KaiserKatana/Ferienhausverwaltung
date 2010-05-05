/*
 * DBimmobilie.java
 *
 * Created on 23. Mai 2005, 18:37
 */

package DB;
import FK.*;

import java.sql.*;
public class DBimmobilie extends DBzugriff{
    private ResultSet datenmenge;
    /** Creates a new instance of DBimmobilie */
    public DBimmobilie() {
    }
    
    public int Speichern(String typ, int einzelzimmer, int doppelzimmer, int zweibettzimmer, String lage, String strasse, String plz, String ort, float tagespreis){
    	int inr=0;
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("insert into Mietobjekt (typ,einzelzimmerAnzahl,doppelzimmerAnzahl,zweibettzimmerAnzahl,lage,strasse,plz,ort,tagespreis) values ('"+typ+"',"+einzelzimmer+","+doppelzimmer+","+zweibettzimmer+",'"+lage+"','"+strasse+"','"+plz+"','"+ort+"',"+tagespreis +")");
                        
    	}
    	catch (Exception ae) 
    	{
                inr=-1;
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
            if(inr!=-1)
                inr = getMaxImmobilienNr();  
            return inr;
        }
    }
    
   public void loesche(int mNr)
   {
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("DELETE * FROM Mietobjekt WHERE MietobjektNR="+mNr+";");             
    	}
    	catch (Exception ae) 
    	{
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
        }
   }
    
    public int getMaxImmobilienNr()
    {
        verbinden(); //Verbindungsaufbau
    	int max=0;
        try
		{ 
	   		datenmenge = befehl.executeQuery("SELECT  max(MietobjektNR) from Mietobjekt;");
                        datenmenge.next();
                        max = datenmenge.getInt(1);
		} 
    	catch (Exception ae) 
    	{
                max=-1;  //-1 bedeutet Fehler
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
            return max; 
        } 
    }
    
    public MietobjektVerwaltung SuchenImmobilie(int nr)
    {
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from mietobjekt where MietobjektNr = "+nr+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addMietobjekt(Convertieren(datenmenge));
            }
	} 
    	catch (Exception ae) 
    	{
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
           
            return eineVerwaltung; 
        } 
    }
    
    public MietobjektVerwaltung suchenOrt(String ort)
    {
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Mietobjekt where Ort = '"+ort+"';");
            while(datenmenge.next())
            {
                eineVerwaltung.addMietobjekt(Convertieren(datenmenge));
            }
	} 
    	catch (Exception ae) 
    	{
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
           
            return eineVerwaltung; 
        } 
    }
    
    public MietobjektVerwaltung suchenAnzahlSchlaf(int anzahl)
    {
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT Mietobjekt.*, [einzelzimmerAnzahl]+[doppelzimmerAnzahl]+[zweibettzimmerAnzahl] AS anzahl FROM Mietobjekt WHERE ((([einzelzimmerAnzahl]+[doppelzimmerAnzahl]*2+[zweibettzimmerAnzahl]*2)="+anzahl+"));");
            while(datenmenge.next())
            {
                eineVerwaltung.addMietobjekt(Convertieren(datenmenge));
            }
	} 
    	catch (Exception ae) 
    	{
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
           
            return eineVerwaltung; 
        } 
    }
    
    private Mietobjekt Convertieren(ResultSet datenmenge)
    {
        Mietobjekt einMietobjekt = new Mietobjekt();
        try
        {
            einMietobjekt.setIdentNR(datenmenge.getInt("MietobjektNr"));
            einMietobjekt.SetEinzehzimmer(datenmenge.getInt("EinzelzimmerAnzahl"));
            einMietobjekt.setDoppelzimmer(datenmenge.getInt("DoppelzimmerAnzahl"));
            einMietobjekt.setZweibettzimmer(datenmenge.getInt("ZweibettzimmerAnzahl"));
            einMietobjekt.setStrasse(datenmenge.getString("Strasse"));
            einMietobjekt.setPLZ(datenmenge.getString("PLZ"));
            einMietobjekt.setOrt(datenmenge.getString("Ort"));
            einMietobjekt.setTagespreis(datenmenge.getFloat("tagespreis"));

        }
    	catch ( Exception ae) 
    	{
        }
        return einMietobjekt;  
    }
    
}

