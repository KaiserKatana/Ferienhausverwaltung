/*
 * DBreservierung.java
 *
 * Created on 26. Mai 2005, 18:46
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package DB;
import java.sql.*;
import FK.*;

public class DBreservierung extends DBzugriff {
     private ResultSet datenmenge;
     private ResultSet datenmenge2;
    
    /** Creates a new instance of DBreservierung */
    public DBreservierung() {
    }
   
    
    public int Speichern(int MietobjektNr, int KundenNr, String mieteVon, String mieteBis){
        int rnr = 0;
        if (pruefen(MietobjektNr, mieteVon, mieteBis)==false)
        {
            return -2;
        }
    	verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("insert into Reservierung (MitobjektNr, KundenNr, mieteVon, mieteBis) values ("+MietobjektNr+"," +
                                            +KundenNr+",'"+mieteVon+"','"+mieteBis+"')");      
    	}
    	catch (Exception ae) 
    	{
                rnr = -1;
                
    	}
        finally
        {  
            schliessen(); //Verbindungsabbau
            if(rnr!=-1)
                rnr = getMaxReservierungsNr();  
            return rnr;
        }
    }
    
    public boolean pruefen(int mNr, String von, String bis)
    {
        boolean ok = false;
        String vonTag = "";
        String vonMonat = "";
        String vonJahr = "";
        String bisTag = "";
        String bisMonat = ""; 
        String bisJahr = "";
        
        int anzahlPunkte=0;
        for(int i=0; i<von.length();i++)
        {
            if (anzahlPunkte==0)
            {
                if(von.charAt(i)!='.')
                    vonTag=vonTag+von.charAt(i);
                else
                {
                    anzahlPunkte++;
                    continue;
                }
            }
            if (anzahlPunkte==1)
            {
               if(von.charAt(i)!='.')
                    vonMonat=vonMonat+von.charAt(i);
               else
               {
                    anzahlPunkte++;
                    continue;
               }
            }
            if (anzahlPunkte==2)
            {
                if(von.charAt(i)!='.')
                    vonJahr=vonJahr+von.charAt(i);
                else
                {
                    anzahlPunkte++;
                    continue;
                }
            }
        }
        
        anzahlPunkte=0;
        for(int i=0; i<bis.length();i++)
        {
            if (anzahlPunkte==0)
            {
                if(bis.charAt(i)!='.')
                    bisTag=bisTag+bis.charAt(i);
                else
                {
                    anzahlPunkte++;
                    continue;
                }
            }
            if (anzahlPunkte==1)
            {
               if(bis.charAt(i)!='.')
                    bisMonat=bisMonat+bis.charAt(i);
               else
               {
                    anzahlPunkte++;
                    continue;
               }
            }
            if (anzahlPunkte==2)
            {
                if(bis.charAt(i)!='.')
                    bisJahr=bisJahr+bis.charAt(i);
                else
                {
                    anzahlPunkte++;
                    continue;
                }
            }
        }
        
        String abfrage = "SELECT ReservierungsNr, MitobjektNr FROM Reservierung WHERE (((MitobjektNr)="+mNr+") AND ((mieteVon) Between #"+vonMonat+"/"+vonTag+"/"+vonJahr+"# And #"+bisMonat+"/"+bisTag+"/"+bisJahr+"#)) OR (((MitobjektNr)="+mNr+") AND ((mieteBis) Between #"+vonMonat+"/"+vonTag+"/"+vonJahr+"# And #"+bisMonat+"/"+bisTag+"/"+bisJahr+"#));";
    
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery(abfrage);
            datenmenge.next();
            datenmenge.getInt("ReservierungsNr");
	} 
    	catch (Exception ae) 
    	{
      		 ok=true;
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
            return ok; 
        }   
    }
    
    public int loesche(int rNr)
    {
        int wert = -1;
        verbinden(); //Verbindungsaufbau
    	try
		{ //executeUpdate ver�ndert die DB aktiv in Klammern der SQL Befehl als String
	   		befehl.executeUpdate("DELETE * FROM Reservierung WHERE ReservierungsNr="+rNr+";");  
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
    
    public void setRechnungsNr(int art, int reservierungsNr, int rechnungsNr)
    {
        verbinden(); //Verbindungsaufbau
    	try
		{
                if(art==1)
                {
	   		befehl.executeUpdate("update Reservierung SET rechnungsNr1="+rechnungsNr+" where ReservierungsNr="+reservierungsNr+";");
                }
                if(art==2)
                {
                        befehl.executeUpdate("update Reservierung SET rechnungsNr2="+rechnungsNr+" where ReservierungsNr="+reservierungsNr+";");
                }
    	}
    	catch (Exception ae) 
    	{
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
        }
    }

    public int getMaxReservierungsNr()
    {
        verbinden(); //Verbindungsaufbau
    	int max=0;
        try
		{ 
	   		datenmenge = befehl.executeQuery("SELECT  max(ReservierungsNR) from Reservierung;");
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
    
    public Reservierungsverwaltung sucheEndDatumAktuell()
    {
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT * FROM Reservierung WHERE mieteBis<=DateValue(Now());");
            while(datenmenge.next())
            {
                eineVerwaltung.addReservierung(Convertieren(datenmenge));
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
    
    public Reservierungsverwaltung sucheKunde(int value)
    {
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Reservierung where KundenNr = "+value+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addReservierung(Convertieren(datenmenge));
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
    
    public Reservierungsverwaltung sucheReservierung(int value)
    {
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Reservierung where ReservierungsNr = "+value+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addReservierung(Convertieren(datenmenge));
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
    
    
     public Reservierungsverwaltung sucheRechnungsNr1(int value)
    {
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Reservierung where RechnungsNr1 = "+value+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addReservierung(Convertieren(datenmenge));
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
    
    
     public Reservierungsverwaltung sucheRechnungsNr2(int value)
    {
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Reservierung where RechnungsNr2 = "+value+" and mieteBis<=DateValue(Now());");
            while(datenmenge.next())
            {
                eineVerwaltung.addReservierung(Convertieren(datenmenge));
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
         
    public Reservierungsverwaltung sucheMietobjekt(int value)
    {
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Reservierung where MitobjektNr = "+value+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addReservierung(Convertieren(datenmenge));
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
    
    public Reservierungsverwaltung sucheRechnung(int value)
    {
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        verbinden(); //Verbindungsaufbau
        try
	{ 
            datenmenge = befehl.executeQuery("SELECT  * from Reservierung where RechnungsNr1 = "+value+" or RechnungsNr2 = "+value+";");
            while(datenmenge.next())
            {
                eineVerwaltung.addReservierung(Convertieren(datenmenge));
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
    
    private Reservierung Convertieren(ResultSet datenmenge)
    {
        Reservierung eineReservierung = new Reservierung();
        try
        {
             eineReservierung.setResNR(datenmenge.getInt("ReservierungsNr"));
             eineReservierung.setMietobjektNr(datenmenge.getInt("MitobjektNr"));
             eineReservierung.setKundenNr(datenmenge.getInt("KundenNr"));
             eineReservierung.setMieteVon(datenmenge.getDate("mieteVon").toString());
             eineReservierung.setMieteBis(datenmenge.getDate("mieteBis").toString());
             eineReservierung.setRechnungsNr1(datenmenge.getInt("rechnungsNr1"));
             eineReservierung.setRechnungsNr2(datenmenge.getInt("rechnungsNr2")); 
             eineReservierung.setDauer(dauer(eineReservierung.getResNR()));
        }
    	catch ( Exception ae) 
    	{
        }
        return eineReservierung;
    }
    
    //dauerberechnen
    public int dauer(int resNr)
    {
        int dauer = 0;
        verbinden(); //Verbindungsaufbau
        try
	{   
            datenmenge2 = befehl.executeQuery("SELECT [mieteBis]-[mieteVon]+1 FROM Reservierung WHERE ReservierungsNr = "+resNr+";");
            datenmenge2.next();
            dauer = datenmenge2.getInt(1);
	} 
    	catch (Exception ae) 
    	{
      		ae.printStackTrace();
    	}
    	finally
        {  
            schliessen(); //Verbindungsabbau
           
            return dauer; 
        }   
    }
    
}
