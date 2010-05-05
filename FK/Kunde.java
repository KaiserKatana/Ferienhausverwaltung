/*
 * Kunde.java
 *
 * Created on 13. Mai 2005, 14:40
 */

package FK;
import DB.*;
import java.sql.*;
import java.util.*;

public class Kunde extends Person {
    
    private int kundenNR;
    private String zahlungsdaten;
    
    //setter-------------------------------------------------------------------
    public void setKundenNR(int kundenNR)
    {
        this.kundenNR=kundenNR;
    }
    
    public void setZahlungsdaten(String zahlungsdaten)
    {
        this.zahlungsdaten=zahlungsdaten;
    }
    //-------------------------------------------------------------setter ende;
    //getter-------------------------------------------------------------------
    public int getKundenNR()            {  return kundenNR;  } 
    public String getZahlungsdaten()    {  return zahlungsdaten; }
    //-------------------------------------------------------------getter ende;    
    
    /** Creates a new instance of Kunde */
    public Kunde() {
    }
    
    public Kunde(String nachname, String vorname, String geburtsdatum, String strasse,
        String plz, String ort, String telefon)
    {
        this.nachname = nachname;
        this.vorname = vorname; 
        this.geburtsdatum = geburtsdatum;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.telefonNR = telefon;
    }
    
    public int Speichern()
    {
        DBkunde einDBkunde = new DBkunde(); 
        return einDBkunde.Speichern(nachname, vorname, strasse, plz, ort, geburtsdatum, telefonNR);
    } 
}
