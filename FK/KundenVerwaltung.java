/*
 * KundenVerwaltung.java
 *
 * Created on 26. Mai 2005, 14:21
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package FK;
import DB.*;
import java.util.*;
import java.sql.*;

public class KundenVerwaltung {
private LinkedList kunden = new LinkedList();    
    
    /** Creates a new instance of KundenVerwaltung */
    public KundenVerwaltung() {
    }
    
    public void addKunde(Kunde einKunde)
    {
        kunden.add(einKunde);
    }
    
    public LinkedList getKunden()
    {
        return kunden;
    }
    
     public LinkedList SuchenName(String value)
    {
        ResultSet datenmenge = null;
        KundenVerwaltung eineVerwaltung = new KundenVerwaltung();
        DBkunde einDBKunde = new DBkunde();
            eineVerwaltung = einDBKunde.SuchenName(value);
            return eineVerwaltung.getKunden();
    }
     
     public LinkedList SuchenKnr(int value)
    {
        ResultSet datenmenge = null;
        KundenVerwaltung eineVerwaltung = new KundenVerwaltung();
        DBkunde einDBKunde = new DBkunde();

            eineVerwaltung = einDBKunde.SuchenKnr(value);
            return eineVerwaltung.getKunden();
    }
     
    public void update(int kNr,String nachname,String vorname,String gDatum,String strasse,String plz,String ort,String telNr)
    {
        DBkunde einDBkunde = new DBkunde(); 
        einDBkunde.update(kNr, nachname, vorname, gDatum, strasse, plz, ort, telNr);
    }
    
    public int loeschen(int kNr)
    {
        DBkunde einDBkunde = new DBkunde(); 
        return einDBkunde.loesche(kNr);
    }  
}
