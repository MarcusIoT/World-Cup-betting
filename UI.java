import java.awt.*;
import javax.swing.*;

/**
 * Write a description of class JOptionPane here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI
{
    /**
     * Constructor for objects of class JOptionPane
     */
    public UI()
    {

    }

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

        String[] daten = {mannschaft1.getText(), tore1.getText(), mannschaft2.getText(), tore2.getText()};
        return daten;
    }

    /**
     * ToDo
     */
    public void erstelleSpielplan(String daten)
    {
        String [] teile = daten.split("!");

        
        JPanel panel = new JPanel( new GridLayout(1, teile.length) );
        for (int i = 0; i < teile.length; i++) {
            panel.add( new JLabel("<html>" + teile[i] + "</html>") );
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
        pane.getValue();
        String[] daten = {gruppe.getText(), name.getText()};
        if(JOptionPane.CANCEL_OPTION == 2){return null;}
        else {return daten;}
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
        pane.createDialog(null, "Neues Land").setVisible(true);
        pane.getValue();
        if(JOptionPane.CANCEL_OPTION == 2){return null;}
        else {return name.getText();}
    }
}
