/*
 * Reservierung.java
 *
 * Created on 13. Mai 2005, 16:41
 */

package FK;
import java.util.*;
import DB.*;

public class Reservierung {
    private int resNR, dauer, mietobjektNr, kundenNr, rechnungsNr1, rechnungsNr2;
    String mieteVon, mieteBis;
    Rechnung[] rechnungen = new Rechnung[2];
    
    public Reservierung(int objektNr, int kundenNr, String mieteVon, String mieteBis) {
        this.mietobjektNr = objektNr;
        this.kundenNr = kundenNr;
        this.mieteVon = mieteVon;
        this.mieteBis = mieteBis;
    }
    
    public int Speichern()
    {
        DBreservierung einDBreservierung = new DBreservierung(); 
        return einDBreservierung.Speichern(mietobjektNr, kundenNr, mieteVon, mieteBis);
    }
    
    //setter-------------------------------------------------------------------
    public void setResNR(int resNR)
    {
        this.resNR=resNR;
    }
    
    public void setMieteVon(String mieteVon)
    {
        this.mieteVon=mieteVon;
    }
    
    public void setMieteBis(String mieteBis)
    {
        this.mieteBis=mieteBis;
    }
    
    public void setMietobjektNr(int Nr)
    {
        this.mietobjektNr = Nr;
    }
    
    public void setKundenNr(int Nr)
    {
        this.kundenNr = Nr;
    }
    
    public void setRechnungsNr1(int Nr)
    {
        this.rechnungsNr1 = Nr;
    }
    
    public void setRechnungsNr2(int Nr)
    {
        this.rechnungsNr2 = Nr;
    }
    
    public void setDauer(int dauer)
    {
        this.dauer = dauer;
    }
    //-------------------------------------------------------------ende setter;
    //getter-------------------------------------------------------------------
    public int getResNR()               {  return resNR;  }
    public int getDauer()              {  return dauer;  }
    public String getMieteVon()           {  return mieteVon;  }
    public String getMieteBis()           {  return mieteBis;  }
    public Rechnung[] getRechnung()     {  return rechnungen;  }
    public int getMietobjektNr() { return mietobjektNr;}
    public int getKundenNR() { return kundenNr;}
    public int getRechnungsNr1() {return rechnungsNr1;}
    public int getRechnungsNr2() {return rechnungsNr2;}
    
    
    public float getRechnungsbetrag()
    {
        float preis=0;
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        LinkedList eineListe = null;
        eineListe = eineVerwaltung.SuchenImmobilie(mietobjektNr);
        ListIterator einIterator = eineListe.listIterator();
        int i=0;
        
        while(einIterator.hasNext())
        {
            Mietobjekt einMietobjekt = (Mietobjekt)einIterator.next();
            preis = einMietobjekt.getTagespreis();
            i++;
        }
        return (dauer*preis)-(dauer*preis/100*20);
    }
    
    
    public float getAnzahlungsbetrag()
    {
        float preis=0;
        MietobjektVerwaltung eineVerwaltung = new MietobjektVerwaltung();
        LinkedList eineListe = null;
        eineListe = eineVerwaltung.SuchenImmobilie(mietobjektNr);
        ListIterator einIterator = eineListe.listIterator();
        int i=0;
        
        while(einIterator.hasNext())
        {
            Mietobjekt einMietobjekt = (Mietobjekt)einIterator.next();
            preis = einMietobjekt.getTagespreis();
            i++;
        }
        return dauer*preis/100*20;
    }
    //-------------------------------------------------------------getter ende;
    
    /** Creates a new instance of Reservierung */
    public Reservierung() {
    }
    
}