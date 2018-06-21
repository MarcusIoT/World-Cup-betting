import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Die Klasse UI erstellt über awt und swing ein User Interface, welches über die Hauptklasse Gruppenphase aufgerufen wird.
 *
 * @author Marcus Schoch
 * @version 17.06.2018
 */
public class UI
{
    /**
     * Der Konstruktor ist leer, denn die Klasse benötigt keine globalen Werte oder Übergaben bei der Erstellung.
     */
    public UI()
    {

    }

    /**
     * Öffnet ein Fenster, dass eine Nachricht und Kopfzeile enthält und die Option die Knöpfe "OK" und "Abbrechen" zu wählen.
     * Bei Auswahl von OK durch den Benutzer wird true zurückgeliefert und bei Abbrechen false.
     */
    public boolean okAbbrechen(String kopfzeile, String nachricht)
    {
        int eingabe = JOptionPane.showConfirmDialog(null,
                nachricht,
                kopfzeile,
                JOptionPane.OK_CANCEL_OPTION);
        if(eingabe == 0){
            return true;
        }
        else return false;
    }

    /**
     * Öffnet ein Fenster, dass eine Nachricht enthält. Dies kann mit OK oder dem [X] schließen Icon.
     */
    public void nachricht(String kopfzeile, String nachricht)
    {
        JOptionPane.showMessageDialog(null,
            nachricht,
            kopfzeile,                        
            JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 
     */
    public String[] eingabeAufforderungSpielergebnis()
    {
        JTextField mannschaft1 = new JTextField();
        JTextField tore1 = new JTextField();
        JTextField mannschaft2 = new JTextField();
        JTextField tore2 = new JTextField();
        Object[] message = {"Mannschaft", mannschaft1, 
                "Tore", tore1, "Mannschaft", mannschaft2, 
                "Tore", tore2};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Spielergebnis").setVisible(true);
        if(pane.getValue()!= null){
            int value = ((Integer)pane.getValue()).intValue();
            if(value == JOptionPane.CANCEL_OPTION){return null;}
            if(mannschaft1.getText().isEmpty() == false && tore1.getText().isEmpty() == false && mannschaft2.getText().isEmpty() == false && tore2.getText().isEmpty() == false){
                String[] daten = {mannschaft1.getText(), tore1.getText(), mannschaft2.getText(), tore2.getText()};
                if(Integer.valueOf(tore1.getText()) >= 0 && Integer.valueOf(tore2.getText()) >= 0){return daten;}           
                else {nachricht("Eingabefehler", "Sie können nur positive Werte eintragen");  return eingabeAufforderungSpielergebnis();}
            }
            else {nachricht("Eingabefehler", "Sie müssen alle Felder ausfüllen!");  return eingabeAufforderungSpielergebnis();}
        }
        else return null;
    }

    /**
     * ToDo
     */
    public void erstelleSpielplan(String daten)
    {
        String [] teile = daten.split("!");

        JPanel panel = new JPanel( new GridLayout(1, teile.length) );
        for (int i = 0; i < teile.length; i++) {
            JLabel zwischen = new JLabel("<html>" + teile[i] + "</html>");
            zwischen.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            panel.add(zwischen);
        }

        JOptionPane pane = new JOptionPane( panel);
        pane.createDialog(null, "Spielergebnis").setVisible(true);
    }

    /**
     * 
     */
    public String[] eingabeAufforderungNeuesLand()
    {
        JTextField gruppe = new JTextField();
        JTextField name = new JTextField();

        Object[] message = {"Wenn sie ein neues Land zu einer Gruppe hinzu fügen werden alle Daten resetted.", " ", "Gruppe in die das Land soll", gruppe, 
                "Name des Landes", name};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Neues Land").setVisible(true);

        if(pane.getValue()!= null){
            int value = ((Integer)pane.getValue()).intValue();
            if(value == JOptionPane.CANCEL_OPTION){return null;}
            if(gruppe.getText().isEmpty() == false && name.getText().isEmpty() == false){
            String[] daten = {gruppe.getText(), name.getText()};
            return daten;}
            else {nachricht("Eingabefehler", "Sie müssen alle Felder ausfüllen!");  return eingabeAufforderungNeuesLand();}
        }
        else return null;
    }

    public String eingabeAufforderungEntferneLand()
    {
        JTextField gruppe = new JTextField();
        JTextField name = new JTextField();

        Object[] message = {"Wenn sie ein Land aus einer Gruppe entfernen werden alle Daten resetted.", " ", 
                "Name des Landes", name};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Land Entfernen").setVisible(true);

        if(pane.getValue()!= null){
            int value = ((Integer)pane.getValue()).intValue();
            String daten = name.getText();
            if(daten.isEmpty() == false){return daten;}
            if(value == JOptionPane.CANCEL_OPTION){return null;}
            else {nachricht("Eingabefehler", "Sie müssen alle Felder ausfüllen!"); return eingabeAufforderungEntferneLand();}
        }
        else return null;
    }    

    /**
     * TODO
     */
    public String[] eingabeAufforderungNeueGruppe()
    {
        JTextField gruppe = new JTextField();
        JTextField name1 = new JTextField();
        JTextField name2 = new JTextField();
        JTextField name3 = new JTextField();
        JTextField name4 = new JTextField();

        Object[] message = {"Wenn sie eine neue Gruppe hinzu fügen werden alle Daten resetted.", " ", 
                "Namen der Länder", name1, name2, name3, name4};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Neues Land").setVisible(true);

        if(pane.getValue()!= null){
            int value = ((Integer)pane.getValue()).intValue();
            String[] datenAlt = {name1.getText(), name2.getText(), name3.getText(), name4.getText()};
            ArrayList teile = new ArrayList<String>();
            for (int i = 0; i < datenAlt.length; i++) {
                if(datenAlt[i].isEmpty() == false){ teile.add(datenAlt[i]);}
            }
            String[] daten = new String[teile.size()];
            teile.toArray( daten );

            if( daten.length >= 2) {return daten;}
            if(value == JOptionPane.CANCEL_OPTION){return null;}
            else {nachricht("Eingabefehler", "Sie müssen mindestens 2 Länder eingeben!");  return eingabeAufforderungNeueGruppe();}
        }
        else return null;
    }
    
      public String eingabeAufforderungEntferneGruppe()
    {
        JTextField gruppe = new JTextField();
        JTextField name = new JTextField();

        Object[] message = {"Wenn sie eine Gruppe entfernen werden alle Daten resetted.", " ", 
                "Name der Gruppe", name};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Gruppe Entfernen").setVisible(true);

        if(pane.getValue()!= null){
            int value = ((Integer)pane.getValue()).intValue();
            String daten = name.getText();
            if(daten.isEmpty() == false){return daten;}
            if(value == JOptionPane.CANCEL_OPTION){return null;}
            else {nachricht("Eingabefehler", "Sie müssen alle Felder ausfüllen!"); return eingabeAufforderungEntferneLand();}
        }
        else return null;
    }   
}
