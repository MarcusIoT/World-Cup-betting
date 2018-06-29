import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.util.ArrayList;
/**
 * Die Klasse UI erstellt über awt und swing ein User Interface, welches über die Hauptklasse Gruppenphase aufgerufen wird.
 *
 * @author Marcus Schoch; HfG; IoT3
 * @version 24.06.2018
 */
public class UI
{
    /**
     * Kostruktor für Objekte der Klasse UI.
     */
    public UI()
    {

    }

    /**
     * Öffnet ein Fenster, dass eine Nachricht und Kopfzeile enthält und die Option die Knöpfe "OK" und "Abbrechen" zu wählen.
     * Bei Auswahl von OK durch den Benutzer wird true zurückgeliefert und bei Abbrechen false.
     * 
     * @param kopfzeile Text der in der Kopfzeile angezeigt wird
     * @param nachricht Nachricht die im Körper des Fensters angezeigt wird
     * 
     * @return true wenn "OK" gewählt wurde, false wenn das Fenster geschlossen oder "Abbrechen" gewählt wurde.
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
     * Öffnet ein Fenster, dass eine Nachricht enthält. Dies kann mit OK oder dem [X] Icon geschlossen werden.
     * 
     * @param kopfzeile Text der in der Kopfzeile angezeigt wird
     * @param nachricht Nachricht die im Körper des Fensters angezeigt wird
     */
    public void nachricht(String kopfzeile, String nachricht)
    {
        JOptionPane.showMessageDialog(null,
            nachricht,
            kopfzeile,                        
            JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Öffnet ein Fenster, in das ein Spielergebnis in vier Felder eingetragen werden kann. 
     * Die Kopfzeile enthält "Spielergebnis".
     * Bei Auswahl von OK durch den Benutzer wird ein Array aller Felder zurückgeliefert und bei Abbrechen null.
     * Es wird zusätzlich die Eingabe von negativen werten der Tore ausgeschlossen und kenntlich gemacht,
     * sowie nur bei ausfüllen aller Felder auch wirklich das Array zurück geliefert.
     * 
     * @return daten wenn alle Bedingungen erfüllt sind, ansonsten null
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
     * Öffnet ein Fenster und zeigt die Gruppen und deren Paarungen und Ergebnise in einem Grid Layout an.
     * Die Große und Anordnung des Layouts wird aufgrund der Gruppenanzahl angepasst.
     * 
     * @param daten Daten aller Spielergebnisse
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
     * Öffnet ein Fenster, in das ein Spielergebnis in vier Felder eingetragen werden kann. 
     * Bei Auswahl von OK durch den Benutzer wird ein Array aller Felder zurückgeliefert und bei Abbrechen null.
     * Nur bei ausfüllen aller Felder auch wirklich das Array zurück geliefert, ansonsten erscheint eine Nachricht.
     * 
     * @return daten wenn alle Bedingungen erfüllt sind, ansonsten null
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

    /**
     * Öffnet ein Fenster mit einer Nachricht und vier Feldern zur Eingabe von möglichen vier Ländern.
     * Bei Auswahl von OK durch den Benutzer wird ein Array aller eingegebenen Felder zurückgeliefert und bei Abbrechen null.
     * Die Eingabe von mindestens zwei Feldern ist eine Bedingung, so liefert diese Methode auch nur ein passendes Array zurück,
     * wenn diese erfüllt ist.
     * 
     * @return daten wenn alle Bedingungen erfüllt sind, ansonsten null
     */
    public String[] eingabeAufforderungNeueGruppe()
    {
        JTextField gruppe = new JTextField();
        JTextField name1 = new JTextField();
        JTextField name2 = new JTextField();
        JTextField name3 = new JTextField();
        JTextField name4 = new JTextField();

        Object[] message = {"Fügen sie bitte mindestens zwei Länder hinzu.", " ", 
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
    
    /**
     * Öffnet ein Fenster mit einer Nachricht und einem Feld zur Eingabe entweder möglicher Länder oder Gruppen je nach verwendung.
     * Deshalb müssen die Nachricht, Kopfzeile und Beschreibung auch übergeben werden um die Variabilität zu gewährleisten.
     * 
     * @return daten wenn alle Bedingungen erfüllt sind, ansonsten null
     */
    public String eingabeAufforderungEinFeld(String kopfzeile, String nachricht, String textfeld)
    {
        JTextField gruppe = new JTextField();
        JTextField name = new JTextField();

        Object[] message = {nachricht, " ", 
                textfeld, name};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, kopfzeile).setVisible(true);

        if(pane.getValue()!= null){
            int value = ((Integer)pane.getValue()).intValue();
            String daten = name.getText();
            if(daten.isEmpty() == false){return daten;}
            if(value == JOptionPane.CANCEL_OPTION){return null;}
            else {nachricht("Eingabefehler", "Sie müssen alle Felder ausfüllen!"); return eingabeAufforderungEinFeld(kopfzeile, nachricht, textfeld);}
        }
        else return null;
    } 
    
    /**
     * Fängt verschiedene Fehlerquellen und Meldungen ab. Bei Erfolg wird im Browser der übergebene Link geöffnet.
     */
    public void openLink(String[] args)
    {
        if(!java.awt.Desktop.isDesktopSupported())
        {
            System.err.println("Desktop wird nicht unterstützt (fatal)");
            System.exit(1);
        }
        if(args.length==0)
        {
            System.out.println( "Benutzung: OpenURI [URI [URI ... ]]" );
            System.exit( 0 );
        }
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if(!desktop.isSupported(java.awt.Desktop.Action.BROWSE))
        {
            System.err.println( "Desktop unterstützt diese Aktion nicht (fatal)" );
            System.exit( 1 );
        }
        for(String arg:args)
        {
            try
            {
                java.net.URI uri = new java.net.URI(arg);
                desktop.browse( uri );
            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
}
