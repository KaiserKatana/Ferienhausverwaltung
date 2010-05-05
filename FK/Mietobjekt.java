/*
 * Mietobjekt.java
 *
 * Created on 13. Mai 2005, 15:18
 */

package FK;
import DB.*;
public class Mietobjekt {
    
   private int identNR, einzelzimmer, zweibettzimmer, 
               doppelzimmer, schlafplaetze;
   enum lage{zentral, meerblick, strandnah, ruhig, gebirge};
   private lage dieLage;
   private String lage, typ, strasse, plz, ort;
   private float tagespreis;
      
   private void schlafPlaetzeBerechenen()   {
       schlafplaetze = einzelzimmer + 2*(zweibettzimmer+doppelzimmer);
   }
      
   //setter--------------------------------------------------------------------
   public void setIdentNR(int identNR)
   {
       this.identNR=identNR;
   }
   
   public void SetEinzehzimmer(int einzelzimmer)
   {
       this.einzelzimmer=einzelzimmer;
       schlafPlaetzeBerechenen();
   }
   
   public void setZweibettzimmer(int zweibettzimmer)
   {
       this.zweibettzimmer=zweibettzimmer;
       schlafPlaetzeBerechenen();
   }
   
   public void setDoppelzimmer(int doppelzimmer)
   {
       this.doppelzimmer=doppelzimmer;
       schlafPlaetzeBerechenen();
   }
  
   public void setTagespreis(float tagespreis)
   {
       this.tagespreis=tagespreis;
   }
   
   public void setStrasse(String strasse)       
   {  
       this.strasse = strasse; 
   }
   
   public void setPLZ(String plz)           
   {  
       this.plz = plz; 
   }
   
   public void setOrt(String ort)           
   {  
       this.ort = ort; 
   }
   
   //---------------------------------------------------------------setter ende;
   //getter--------------------------------------------------------------------
   public int getIdentNR()          {  return identNR;  }   
   public int getEinzelzimmer()     {  return einzelzimmer;  }  
   public int getDoppelzimmer()     {  return doppelzimmer;  } 
   public int getZweibettzimmer()   {  return zweibettzimmer;  }  
   public float getTagespreis()     {  return tagespreis;  } 
   public int getSchlafplaetze()    {  return schlafplaetze;  }
   public String getStrasse()       {  return strasse; }
   public String getPLZ()           {  return plz; }
   public String getOrt()           {  return ort; }
   
//---------------------------------------------------------------getter ende;
   
   
   /** Creates a new instance of Mietobjekt */
   public Mietobjekt() {
      
   }
   
   public Mietobjekt(int einzelzimmer, int doppelzimmer, int zweibettzimmerzimmer, String strasse, String plz, String ort, float tagespreis)
    {
       this.einzelzimmer = einzelzimmer;
       this.doppelzimmer = doppelzimmer;
       this.zweibettzimmer = zweibettzimmer;
       this.tagespreis = tagespreis;
       this.lage = lage;
       this.typ = typ;
       this.strasse = strasse;
       this.plz = plz;
       this.ort = ort;
    }
    
    public int Speichern()
    {
        DBimmobilie einDBimmobilie = new DBimmobilie(); 
        return einDBimmobilie.Speichern(typ, einzelzimmer, doppelzimmer, zweibettzimmer, lage, strasse, plz, ort, tagespreis);
    }
    
}