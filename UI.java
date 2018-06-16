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
        
        
        
        JPanel panel = new JPanel( new GridLayout(1, 8) );
        panel.add( new JLabel("<html>" + teile[0] + "</html>") );
        panel.add( new JLabel("<html>" + teile[1] + "</html>") );
        panel.add( new JLabel("<html>" + teile[2] + "</html>") );
        panel.add( new JLabel("<html>" + teile[3] + "</html>") );
        panel.add( new JLabel("<html>" + teile[4] + "</html>") );
        panel.add( new JLabel("<html>" + teile[5] + "</html>") );
        panel.add( new JLabel("<html>" + teile[6] + "</html>") );
        panel.add( new JLabel("<html>" + teile[7] + "</html>") );
        

        JOptionPane pane = new JOptionPane( panel);
        pane.createDialog(null, "Spielergebnis").setVisible(true);
    }

}
