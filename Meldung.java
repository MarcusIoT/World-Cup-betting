import javax.swing.JOptionPane;
/**
 * Write a description of class JOptionPane here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Meldung
{
    /**
     * Constructor for objects of class JOptionPane
     */
    public Meldung()
    {

    }

    /**
     * 
     */
    public boolean popUp()
    {
        // Aufruf der statischen Methode showConfirmDialog()
        int eingabe = JOptionPane.showConfirmDialog(null,
                "Geben Sie Ihr Einverständnis?",
                "Einverständnis",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if(eingabe == 0){
            return true;
        }
        else return false;

    }
}
