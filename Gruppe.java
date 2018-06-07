import java.util.ArrayList;
/**
 *
 */
public class Gruppe
{
   
    private ArrayList<Land> länder;
    private int gruppenGroesse;

    /**
     * Konstructor
     */
    public Gruppe()
    {
         länder = new ArrayList<>();
    }

    /**
     * 
     */
    public void erstelleLand(String name)
    {
        länder.add(new Land(name, 0, 0, 0));
        gruppenGroesse += 1;
    }
    
    /** kann eigentlich weg
     * 
     */
    public void erstelleLandFertig()
    {
        String name = "Deutschland";
        int tore = 0;
        int gegenTore = 0;
        int punkte = 0;
        
        
        länder.add(new Land(name, tore, gegenTore, punkte));
    }
}
