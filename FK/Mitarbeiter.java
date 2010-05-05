/*
 * Mitarbeiter.java
 *
 * Created on 13. Mai 2005, 14:45
 */

package FK;
import DB.*;

public class Mitarbeiter extends Person
{
    private int personalNR;
    private boolean isTaetig;
    
    //setter-------------------------------------------------------------------
    public void setPersonalNR(int personalNR)   {
        this.personalNR=personalNR;
    }

    public void setIsTaetig(boolean isTaetig)    {
        this.isTaetig=isTaetig;
    }
    //-------------------------------------------------------------setter ende;
    //getter-------------------------------------------------------------------
    public int getPersonalNR()   { return personalNR;  }      
    public boolean getIsTaetig() { return isTaetig;  }
   //--------------------------------------------------------------getter ende;
       
    /** Creates a new instance of Mitarbeiter */
    public Mitarbeiter() {
    }   
    
    public Mitarbeiter(String nachname, String vorname, String geburtsdatum, String strasse,
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
        DBmitarbeiter einDBmitarbeiter = new DBmitarbeiter(); 
        return einDBmitarbeiter.Speichern(nachname, vorname, strasse, plz, ort, geburtsdatum, telefonNR);
    }
}

    