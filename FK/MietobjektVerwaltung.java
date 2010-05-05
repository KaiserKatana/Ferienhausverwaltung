/*
 * MietobjektVerwaltung.java
 *
 * Created on 6. Juni 2005, 10:34
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package FK;
import DB.*;
import java.util.*;
import java.sql.*;

public class MietobjektVerwaltung {
private LinkedList mietobjekte = new LinkedList();   

    /** Creates a new instance of MietobjektVerwaltung */
    public MietobjektVerwaltung() {
    }
    
    public void addMietobjekt(Mietobjekt einMietobjekt)
    {
        mietobjekte.add(einMietobjekt);
    }
    
    public LinkedList getMietobjekte()
    {
        return mietobjekte;
    }
    
    public void loeschen(int mNr)
    {
        DBimmobilie eineDBimmobilie = new DBimmobilie(); 
        eineDBimmobilie.loesche(mNr);
    }
    
    public LinkedList SuchenImmobilie(int value)
    {
        ResultSet datenmenge = null;
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        DBimmobilie einDBImmobilie = new DBimmobilie();
            eineVerwaltung = einDBImmobilie.SuchenImmobilie(value);
            return eineVerwaltung.getMietobjekte();
    }
    
    public LinkedList suchenOrt(String value)
    {
        ResultSet datenmenge = null;
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        DBimmobilie einDBImmobilie = new DBimmobilie();
            eineVerwaltung = einDBImmobilie.suchenOrt(value);
            return eineVerwaltung.getMietobjekte();
    }
     
    public LinkedList suchenAnzahlSchlaf(int value)
    {
        ResultSet datenmenge = null;
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        DBimmobilie einDBImmobilie = new DBimmobilie();
            eineVerwaltung = einDBImmobilie.suchenAnzahlSchlaf(value);
            return eineVerwaltung.getMietobjekte();
    }
    
}
