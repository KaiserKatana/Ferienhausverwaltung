/*
 * TabellenModell.java
 *
 * Created on 14. Juni 2005, 12:02
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package GUI;
import javax.swing.table.*;

public class TabellenModell extends DefaultTableModel{

	private Object[][] Daten;  //Variable für den Inhalt der Tabelle
	private String[] titel; //Überschriften
        private boolean[] canEdit; //Spalte Editierbar
        private Class[] types; //Spalten Typen (String, Int, ...)
        private int column, row;
        
	public TabellenModell(int row, String[] titel, boolean[] canEdit, Class[] types)
	{
            this.row = row;
            this.column = titel.length;
            Daten = new Object[row][column];
            this.titel = titel; 
            this.canEdit = canEdit;
            this.types = types;
	}
	
	public int getColumnCount()
	{
		return column;
	}
	
	public int getRowCount()
	{
		return row;
	}
	
	public Object getValueAt(int zeile, int spalte)
	{
		return Daten[zeile][spalte];
	}
        
        public void setValueAt(Object inhalt, int zeile, int spalte)
	{
                Daten[zeile][spalte]=inhalt;
	}
	
        //Wird gebraucht um die Zeilennamen anzuzeigen
	public String getColumnName(int spalte)
	{
		return titel[spalte];
	}
        
        public boolean isCellEditable(int rowIndex, int columnIndex) 
        {
                return canEdit [columnIndex];
        }
        
        public Class getColumnClass(int columnIndex) 
        {
                return types [columnIndex];
        }
}