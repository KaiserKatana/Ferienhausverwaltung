/*
 * DBkunde.java
 *
 * Created on 23. Mai 2005, 18:26
 */

package DB;
import FK.*;

import java.sql.*;
import javax.swing.*;

public class DBkunde extends DBzugriff{
    private ResultSet datenmenge;

    /** Creates a new instance of DBkunde */
    public DBkunde() {
    }
    
    public int Speichern(String nachname, String vorname, String strasse, String plz, String ort, String geburtsdatum, String telefon){
        int knr=0;
    	verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("insert into Kunden (Vorname,Nachname,Strasse,plz,ort,telefonnr,geburtsdatum) values ('"+vorname+"'," +
                                            "'"+nachname+"','"+strasse+"','"+plz+"','"+ort+"','"+telefon+"','"+geburtsdatum+"')");
                        
    	}
    	catch (Exception ae) 
    	{
                knr = -1;
                ae.printStackTrace();
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
            if(knr!=-1)
                knr = getMaxKundenNr();  
            return knr;
        }
    }
    
    public void update(int kNr,String nachname,String vorname,String gDatum,String strasse,String plz,String ort,String telNr)
    {
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("update Kunden SET Nachname='"+nachname+"',Vorname='"+vorname+"',Geburtsdatum='"+gDatum+"',Strasse='"+strasse+"',PLZ='"+plz+"',Ort='"+ort+"',TelefonNr='"+telNr+"' where KundenNr="+kNr+";");
                        
    	}
    	catch (Exception ae) 
    	{
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
        }
    }
    
    public int loesche(int kNr)
    {
        int wert = -1;
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("DELETE * FROM Kunden WHERE KundenNr="+kNr+";");  
                        wert = 1;
    	}
    	catch (Exception ae) 
    	{  
            wert = -1;
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
            return wert;
        }
    }
    
    public int getMaxKundenNr()
    {
        verbinden(); //Verbindungsaufbau
    	int max=0;
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  max(kundenNR) from Kunden;");
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
    
    public KundenVerwaltung SuchenName(String name)
    {
        KundenVerwaltung eineVerwaltung = new KundenVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT * from Kunden where Nachname = '"+name+"'");

            while(datenmenge.next())
            {
                eineVerwaltung.addKunde(Convertieren(datenmenge));
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
    
     public KundenVerwaltung SuchenKnr(int knr)
     {
        KundenVerwaltung eineVerwaltung = new KundenVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT * from Kunden where KundenNr = "+knr);
            System.out.println ("SELECT * from kunden where KundenNr = "+knr);
            while(datenmenge.next())
            {
                System.out.println("Ein Kunde gefunden");
                eineVerwaltung.addKunde(Convertieren(datenmenge));
            }
	} 
    	catch (Exception ae) 
    	{
    	    ae.printStackTrace();
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
           
            return eineVerwaltung; 
        } 
    }
     
    private Kunde Convertieren(ResultSet datenmenge)
    {
        Kunde einKunde = new Kunde();
        try
        {
                einKunde.setKundenNR(datenmenge.getInt("KundenNr"));
                einKunde.setNachname(datenmenge.getString("Nachname"));
                einKunde.setVorname(datenmenge.getString("Vorname"));
                einKunde.setGDate(datenmenge.getDate("Geburtsdatum").toString());
                einKunde.setPLZ(datenmenge.getString("PLZ"));
                einKunde.setOrt(datenmenge.getString("Ort"));
                einKunde.setStrasse(datenmenge.getString("Strasse"));
                einKunde.setTelefonNR(datenmenge.getString("TelefonNr"));
        }
    	catch ( Exception ae) 
    	{
        }
        return einKunde;
       
    }
}
