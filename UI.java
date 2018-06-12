import javax.swing.JOptionPane;
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

}
