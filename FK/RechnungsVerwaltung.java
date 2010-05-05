/*
 * RechnungsVerwaltung.java
 *
 * Created on 27. Mai 2005, 10:48
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package FK;
import DB.*;
import java.util.*;
import java.sql.*;

public class RechnungsVerwaltung {
private LinkedList rechnungen = new LinkedList();    

    /** Creates a new instance of RechnungsVerwaltung */
    public RechnungsVerwaltung() {
    }
    
    public void update(int rNr, boolean isOffen, boolean endabanhme)
    {
        DBRechnung eineDBRechnung = new DBRechnung();
        eineDBRechnung.update(rNr, isOffen, endabanhme);
    }
    
    public void addRechnung(Rechnung eineRechnung)
    {
        rechnungen.add(eineRechnung);
    }
    
    public LinkedList getRechnungen()
    {
        return rechnungen;
    }
    
    public LinkedList SuchenKnr(int value)
    {
        ResultSet datenmenge = null;
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        DBRechnung eineDBRechnung = new DBRechnung();

            eineVerwaltung = eineDBRechnung.SuchenKnr(value);
            return eineVerwaltung.getRechnungen();
    }
    
    public LinkedList SuchenRechnung(int value)
    {
        ResultSet datenmenge = null;
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        DBRechnung eineDBRechnung = new DBRechnung();

            eineVerwaltung = eineDBRechnung.SuchenRechnung(value);
            return eineVerwaltung.getRechnungen();
    }
    
    public LinkedList SuchenReservierung(int value)
    {
        ResultSet datenmenge = null;
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        DBRechnung eineDBRechnung = new DBRechnung();

            eineVerwaltung = eineDBRechnung.SuchenReservierung(value);
            return eineVerwaltung.getRechnungen();
    }
    
    public LinkedList SuchenOffen()
    {
        ResultSet datenmenge = null;
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        DBRechnung eineDBRechnung = new DBRechnung();

            eineVerwaltung = eineDBRechnung.SuchenOffen();
            return eineVerwaltung.getRechnungen();
    }
    
    public LinkedList SuchenEndabnahme()
    {
        ResultSet datenmenge = null;
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        DBRechnung eineDBRechnung = new DBRechnung();

            eineVerwaltung = eineDBRechnung.SuchenEndabnahme();
            return eineVerwaltung.getRechnungen();
    } 
    
    public LinkedList sucheRechnungsdatum()
    {
        ResultSet datenmenge = null;
        RechnungsVerwaltung eineVerwaltung = new RechnungsVerwaltung();
        DBRechnung eineDBRechnung = new DBRechnung();

            eineVerwaltung = eineDBRechnung.sucheRechnungsdatum();
            return eineVerwaltung.getRechnungen();
    }
}
