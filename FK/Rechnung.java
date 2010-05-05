/*
 * Rechnung.java
 *
 * Created on 13. Mai 2005, 16:41
 */

package FK;
import DB.*;

public class Rechnung {
    
    private Kunde einKunde;
    private Reservierung eineReservierung;
    private int rechnungsNR, kundenNr, reservierungsNr, anzahlMahnungen;
    private boolean isOffen, endabnahmeErfolg=false;
    private float rechnungsbetrag;
    
    //setter-------------------------------------------------------------------
    public void setKunde(Kunde einKunde)
    {
        this.einKunde=einKunde;
    }
    
    public void setReservierung(Reservierung eineReservierung)
    {
        this.eineReservierung=eineReservierung;
    }
    
    public void setRechnungsNR(int rechnungsNR)
    {
        this.rechnungsNR=rechnungsNR;
    }
    
    public void setIsOffen(boolean isOffen)
    {
        this.isOffen=isOffen;
    }
    
    public void setEndabnahmeErfolg(boolean endabnahmeErfolg)
    {
        this.endabnahmeErfolg=endabnahmeErfolg;
    }
    
    public void setRechnungsbetrag(float rechnungsbetrag)
    {
        this.rechnungsbetrag=rechnungsbetrag;
    }
    
    public void setKundenNr(int knr)
    {
        kundenNr = knr;
    }
    
    public void setReservierungsNr(int rnr)
    {
        reservierungsNr = rnr;
    }
    
    public void setAnzahlMahnungen(int nr)
    {
        anzahlMahnungen = nr;
    }
    
    //-------------------------------------------------------------setter ende;
    //getter-------------------------------------------------------------------
    public Kunde getKunde()                 {  return einKunde;  }
    public Reservierung getReservierung()   {  return eineReservierung;  }   
    public int getRechnungsNR()             {  return rechnungsNR;  }
    public boolean getIsOffen()             {  return isOffen;  }
    public boolean getEndabnahmeErfolg()    {  return endabnahmeErfolg;  }
    public float getRechnungsbetrag()       {  return rechnungsbetrag;  }
    public int getKundenNr()                { return kundenNr; }
    public int getReservierungsNr()         { return reservierungsNr;}
    public int getAnzahlMahnungen()         { return anzahlMahnungen;}
    //-------------------------------------------------------------getter ende;
    
    /** Creates a new instance of Rechnung */
    public Rechnung() {
    }
    
     public Rechnung(int kundenNr, int reservierungsNr, float rechnungsbetrag) {
         this.kundenNr = kundenNr;
         this.reservierungsNr = reservierungsNr;
         this.rechnungsbetrag = rechnungsbetrag;
    }
    
    public void Speichern(int art)
    {
        DBRechnung eineDBRechnung = new DBRechnung(); 
        DBreservierung eineDBReservierung = new DBreservierung();
        int rechnungsNr = eineDBRechnung.Speichern(kundenNr, reservierungsNr, rechnungsbetrag);
        eineDBReservierung.setRechnungsNr(art, reservierungsNr, rechnungsNr);
    } 
    
}