import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

    /**
     * 
     */
    public String jaNeinAbbrechen(String kopfzeile, String nachricht)
    {
        int eingabe = JOptionPane.showConfirmDialog(null,
                nachricht,
                kopfzeile,
                JOptionPane.YES_NO_CANCEL_OPTION);
        if(eingabe == 0){
            return "Ja";
        }
        if(eingabe == 1){
            return "Nein";
        }
        else return "Abbrechen";
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
    public String eingabeAufforderung(String kopfzeile, String nachricht)
    {
        String eingabe = JOptionPane.showInputDialog(null,"Geben Sie Ihren Namen ein",
                "Eine Eingabeaufforderung",
                JOptionPane.PLAIN_MESSAGE);
        return eingabe;
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

}
