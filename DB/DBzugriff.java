/*
 * DBzugriff.java
 *
 * Created on 23. Mai 2005, 10:16
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**Diese Klasse baut die Verbindung zur DB auf und ab. 
 *Jede Klasse, die einen DB zugriff braucht muss hievon erben.*/
package DB;
import java.sql.*;  //Wird f�r den DB Zugriff ben�tigt

public class DBzugriff {

//-Datenbank Informationen---------------------------------------------------*
//  private static final String URL = "jdbc:odbc:Verwaltung";
//10.2.176.22
  private static final String URL = "jdbc:mysql://10.2.176.22:3306/Verwaltung";
  private static final String benutzername = "Benutzer";
  private static final String passwort = "Verwaltung";
  protected static Statement befehl = null;
  protected static Connection verbindung = null;

//-Methode verbinden() zum verbinden mit der Datenbank------------------------*
  protected static void verbinden() {
     try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("com.mysql.jdbc.Driver");
      }
      catch (Exception ae){
           System.out.println("JDBC/ODBC-Treiber konnte nicht geladen werden :-(");
          // ae.printStackTrace();
      }

      try {
           verbindung = DriverManager.getConnection (
                                         URL,
                                         benutzername,
                                         passwort);

           befehl = verbindung.createStatement();
      }
      catch ( Exception ae) {
           System.out.println("Verbindung konnte nicht hergestellt werden :-(");
           ae.printStackTrace();
      }
  }
//-Methode schliessen() zum beenden der Verbindung----------------------------*

  protected static void schliessen() {
    try {
      verbindung.close();
    }
    catch (Exception ae) {
        System.out.println("Fehler beim schliessen");
//      ae.printStackTrace();
    }
  }
}