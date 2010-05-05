/*
 * DBRechnung.java
 *
 * Created on 27. Mai 2005, 10:51
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package DB;
import FK.*;
import java.sql.*;

public class DBRechnung extends DBzugriff{
    
    private ResultSet datenmenge;
    /** Creates a new instance of DBRechnung */
    public DBRechnung() {
    }
    
    public int Speichern(int kundenNr, int reservierungsNr, float rechnungsbetrag)
    {
        int rnr=0;
    	verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("insert into Rechnung (KundenNr, ReservierungsNr, isOffen, rechnungsbetrag, Rechnungsdatum) values ("+kundenNr+","+reservierungsNr+", true,"+rechnungsbetrag+",now())");
                        
    	}
    	catch (Exception ae) 
    	{
                rnr = -1;
   
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
            if(rnr!=-1)
                rnr = getMaxRechnungsNr();  
            return rnr;
        }
    } 
    
    public void update(int rNr, boolean isOffen, boolean endabnahme)
    {
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("update Rechnung SET isOffen="+isOffen+",endabnahmeErfolgt="+endabnahme+" where RechnungsNr="+rNr+";");
                        
    	}
    	catch (Exception ae) 
    	{ 
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
        }
    }            
    
    public int getMaxRechnungsNr()
    {
        verbinden(); //Verbindungsaufbau
    	int max=0;
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  max(RechnungsNR) from Rechnung;");
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
    
    public RechnungsVerwaltung SuchenKnr(int knr)
    {
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Rechnung where KundenNr = "+knr+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addRechnung(Convertieren(datenmenge));
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
    
    public RechnungsVerwaltung SuchenRechnung(int rnr)
    {
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Rechnung where RechnungsNr = "+rnr+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addRechnung(Convertieren(datenmenge));
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
    
    public RechnungsVerwaltung SuchenReservierung(int resnr)
    {
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Rechnung where ReservierungsNr = "+resnr+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addRechnung(Convertieren(datenmenge));
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
    
    public RechnungsVerwaltung SuchenOffen()
    {
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Rechnung where isOffen = "+true+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addRechnung(Convertieren(datenmenge));
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
    
    public RechnungsVerwaltung SuchenEndabnahme()
    {
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Rechnung where endabnahmeErfolgt = "+true+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addRechnung(Convertieren(datenmenge));
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
    
    public RechnungsVerwaltung sucheRechnungsdatum()
    {
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Rechnung where Rechnungsdatum <= (now()+30);");
            while(datenmenge.next())
            {
                eineVerwaltung.addRechnung(Convertieren(datenmenge));
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
    
    private Rechnung Convertieren(ResultSet datenmenge)
    {
        Rechnung eineRechnung = new Rechnung();
        try
        {
            eineRechnung.setRechnungsNR(datenmenge.getInt("RechnungsNr"));
            eineRechnung.setKundenNr(datenmenge.getInt("KundenNr"));
            eineRechnung.setReservierungsNr(datenmenge.getInt("ReservierungsNr"));
            eineRechnung.setAnzahlMahnungen(datenmenge.getInt("isGemahnt"));
            eineRechnung.setIsOffen(datenmenge.getBoolean("isOffen"));
            eineRechnung.setEndabnahmeErfolg(datenmenge.getBoolean("endabnahmeErfolgt"));
            eineRechnung.setRechnungsbetrag(datenmenge.getFloat("rechnungsbetrag"));

        }
    	catch ( Exception ae) 
    	{
        }
        return eineRechnung;
       
    }
}
