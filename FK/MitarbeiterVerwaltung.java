/*
 * MitarbeiterVerwaltung.java
 *
 * Created on 9. Juni 2005, 22:40
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package FK;
import DB.*;
import java.util.*;
import java.sql.*;

public class MitarbeiterVerwaltung {
private LinkedList mitarbeiter = new LinkedList();    

    /** Creates a new instance of MitarbeiterVerwaltung */
    public MitarbeiterVerwaltung() {
    }
    
    public void addMitarbeiter(Mitarbeiter einMitarbeiter)
    {
        mitarbeiter.add(einMitarbeiter);
    }
    
    public LinkedList getMitarbeiter()
    {
        return mitarbeiter;
    }
    
    public LinkedList SuchenPnr(int value)
    {
        ResultSet datenmenge = null;
        MitarbeiterVerwaltung eineVerwaltung = new MitarbeiterVerwaltung();
        DBmitarbeiter einDBMitarbeiter = new DBmitarbeiter();

        eineVerwaltung = einDBMitarbeiter.SuchenPnr(value);
        return eineVerwaltung.getMitarbeiter();
    }
    
    public LinkedList SuchenName(String value)
    {
        ResultSet datenmenge = null;
        MitarbeiterVerwaltung eineVerwaltung = new MitarbeiterVerwaltung();
        DBmitarbeiter einDBMitarbeiter = new DBmitarbeiter();

        eineVerwaltung = einDBMitarbeiter.SuchenName(value);
        return eineVerwaltung.getMitarbeiter();
    }

    public void update(int pNr,String nachname,String vorname,String gDatum,String strasse,String plz,String ort,String telNr, boolean taetig)
    {
        DBmitarbeiter einDBmitarbeiter = new DBmitarbeiter(); 
        einDBmitarbeiter.update(pNr, nachname, vorname, gDatum, strasse, plz, ort, telNr, taetig);
    }
    
    public int loeschen(int mNr)
    {
        DBmitarbeiter einDBmitarbeiter = new DBmitarbeiter(); 
        return einDBmitarbeiter.loesche(mNr);
    }  
}

