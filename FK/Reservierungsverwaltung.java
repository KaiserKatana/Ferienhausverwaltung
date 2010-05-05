/*
 * Reservierungsverwaltung.java
 *
 * Created on 26. Mai 2005, 16:24
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package FK;

import java.util.*;
import java.sql.*;
import DB.*;

public class Reservierungsverwaltung {
    
    LinkedList reservierungen= new LinkedList();
    
    public void addReservierung(Reservierung eineReservierung)
    {
        reservierungen.add(eineReservierung);
    }
    
    public LinkedList getReservierungen()
    {
        return reservierungen;
    }    
    
    public LinkedList sucheKunde(int value)
    {
        ResultSet datenmenge = null;
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        DBreservierung eineDBReservierung = new DBreservierung();
            eineVerwaltung = eineDBReservierung.sucheKunde(value);
            return eineVerwaltung.getReservierungen();
    }
    
    public LinkedList sucheReservierung(int value)
    {
        ResultSet datenmenge = null;
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        DBreservierung eineDBReservierung = new DBreservierung();
            eineVerwaltung = eineDBReservierung.sucheReservierung(value);
            return eineVerwaltung.getReservierungen();
    }
    
    public LinkedList sucheMietobjekt(int value)
    {
        ResultSet datenmenge = null;
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        DBreservierung eineDBReservierung = new DBreservierung();
            eineVerwaltung = eineDBReservierung.sucheMietobjekt(value);
            return eineVerwaltung.getReservierungen();
    }
    
    public LinkedList sucheRechnung(int value)
    {
        ResultSet datenmenge = null;
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        DBreservierung eineDBReservierung = new DBreservierung();
            eineVerwaltung = eineDBReservierung.sucheRechnung(value);
            return eineVerwaltung.getReservierungen();
    }
    
    
    public Reservierung suchenResNR(int kundeNr)
    {
        Reservierung tmpReservierung= new Reservierung();
        return tmpReservierung;
    }
    
    public Reservierungsverwaltung() {   
    }
    
    public LinkedList sucheEndDatumAktuell()
    {
        ResultSet datenmenge = null;
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        DBreservierung eineDBReservierung = new DBreservierung();

            eineVerwaltung = eineDBReservierung.sucheEndDatumAktuell();
            return eineVerwaltung.getReservierungen();
    }
    
    public LinkedList sucheRechnungsNr1()
    {
        ResultSet datenmenge = null;
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        DBreservierung eineDBReservierung = new DBreservierung();

            eineVerwaltung = eineDBReservierung.sucheRechnungsNr1(0);
            return eineVerwaltung.getReservierungen();
    }
 
     public LinkedList sucheRechnungsNr2()
    {
        ResultSet datenmenge = null;
        Reservierungsverwaltung eineVerwaltung = new Reservierungsverwaltung();
        DBreservierung eineDBReservierung = new DBreservierung();

        eineVerwaltung = eineDBReservierung.sucheRechnungsNr2(0);
        return eineVerwaltung.getReservierungen();
    }
    
    public int loeschen(int rNr)
    {
        DBreservierung eineDBreservierung = new DBreservierung(); 
        return eineDBreservierung.loesche(rNr);
    } 
     
}
