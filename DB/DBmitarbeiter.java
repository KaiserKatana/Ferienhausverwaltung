/*
 * DBmitarbeiter.java
 *
 * Created on 23. Mai 2005, 10:22
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package DB;
import FK.*;
import java.sql.*;

public class DBmitarbeiter extends DBzugriff {
    private ResultSet datenmenge;
    
    /** Creates a new instance of DBmitarbeiter */
    public DBmitarbeiter() {
    }
  
        public int Speichern(String nachname, String vorname, String strasse, String plz, String ort, String geburtsdatum, String telefon){
        int mnr = 0;
    	verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("insert into Mitarbeiter (Vorname,Nachname,Strasse,plz,ort,telefonnr,geburtsdatum) values ('"+vorname+"'," +
                                            "'"+nachname+"','"+strasse+"','"+plz+"','"+ort+"','"+telefon+"','"+geburtsdatum+"')");      
    	}
    	catch (Exception ae) 
    	{
    	    ae.printStackTrace();
                mnr = -1;
    	}
        finally
        {  
            schliessen(); //Verbindungsabba
            if(mnr!=-1)
                mnr = getMaxMitarbeiterNr();  
            return mnr;
        }
    }

    public void update(int pNr,String nachname,String vorname,String gDatum,String strasse,String plz,String ort,String telNr, boolean taetig)
    {
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("update Mitarbeiter SET Nachname='"+nachname+"',Vorname='"+vorname+"',Geburtsdatum='"+gDatum+"',Strasse='"+strasse+"',PLZ='"+plz+"',Ort='"+ort+"',TelefonNr='"+telNr+"',isTaetig="+taetig+" where PersonalNr="+pNr+";");
                        
    	}
    	catch (Exception ae) 
    	{
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
        }
    }
    
    public int loesche(int mNr)
    {
        int wert = -1;
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("DELETE * FROM Mitarbeiter WHERE PersonalNr="+mNr+";");  
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

    public int getMaxMitarbeiterNr()
    {
        verbinden(); //Verbindungsaufbau
    	int max=0;
        try
		{ 
	   		datenmenge = befehl.executeQuery("SELECT  max(PersonalNR) from Mitarbeiter");
                        datenmenge.next();
                        max = datenmenge.getInt(1);
		} 
    	catch (Exception ae) 
    	{
    	    ae.printStackTrace();
                max=-1;  //-1 bedeutet Fehler
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
            return max; 
        } 
    } 
    
    public MitarbeiterVerwaltung SuchenName(String name)
    {
        MitarbeiterVerwaltung eineVerwaltung = new MitarbeiterVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Mitarbeiter where Nachname = '"+name+"'");
            while(datenmenge.next())
            {
                System.out.println ("MITARBEITER GEFUNDEN");
                eineVerwaltung.addMitarbeiter(Convertieren(datenmenge));
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
    
     public MitarbeiterVerwaltung SuchenPnr(int pnr)
     {
        MitarbeiterVerwaltung eineVerwaltung = new MitarbeiterVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Mitarbeiter where PersonalNr = "+pnr+"");
            while(datenmenge.next())
            {
                eineVerwaltung.addMitarbeiter(Convertieren(datenmenge));
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
     
    private Mitarbeiter Convertieren(ResultSet datenmenge)
    {
        Mitarbeiter einMitarbeiter = new Mitarbeiter();
        try
        {
                einMitarbeiter.setPersonalNR(datenmenge.getInt("PersonalNr"));
                einMitarbeiter.setNachname(datenmenge.getString("Nachname"));
                einMitarbeiter.setVorname(datenmenge.getString("Vorname"));
                einMitarbeiter.setGDate(datenmenge.getDate("Geburtsdatum").toString());
                einMitarbeiter.setPLZ(datenmenge.getString("PLZ"));
                einMitarbeiter.setOrt(datenmenge.getString("Ort"));
                einMitarbeiter.setStrasse(datenmenge.getString("Strasse"));
                einMitarbeiter.setTelefonNR(datenmenge.getString("TelefonNr"));
                einMitarbeiter.setIsTaetig(datenmenge.getBoolean("isTaetig"));
        }
    	catch ( Exception ae) 
    	{
        }
        return einMitarbeiter;
       
    }

}