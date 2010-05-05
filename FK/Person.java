/*
 * Person.java
 *
 * Created on 13. Mai 2005, 14:00
 */

package FK;
import java.util.*;
public class Person {
    
    
    protected String nachname, vorname, strasse, plz, ort, geburtsdatum, telefonNR;
    
    //setter-------------------------------------------------------------------    
    public void setGDate(String gdatum)
    {
        geburtsdatum=gdatum;
    }
    public void setVorname(String vorname)
    {
        this.vorname=vorname;
    }
    
    public void setNachname(String nachname)
    {
        this.nachname=nachname;
    }    
    public void setStrasse(String strasse)
    {
        this.strasse=strasse;
    }
    public void setPLZ(String plz)
    {
        this.plz=plz;
    }
    public void setOrt(String ort)
    {
        this.ort=ort;
    }
    public void setTelefonNR(String nr)
    {
        this.telefonNR=nr;
    }
    //--------------------------------------------------------------setter ende; 
    //getter-------------------------------------------------------------------  
    public String getGDate()     { return geburtsdatum;  }
    public String getVorname()   { return vorname;   }
    public String getNachname()  { return nachname;  }
    public String getStrasse()   { return strasse;   }
    public String getPLZ()   { return plz;   }
    public String getOrt()   { return ort;   }
    public String getTelefonNr() {return telefonNR;}
    //-------------------------------------------------------------getter ende;
    
    /** Creates a new instance of Person */
    public Person() 
    {}    
}