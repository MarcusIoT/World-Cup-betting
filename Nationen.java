import java.util.ArrayList;
/**
 *
 */
public class Nationen
{
   
    private ArrayList<Land> länder;

    /**
     * Konstructor
     */
    public Nationen()
    {
         länder = new ArrayList<>();
    }

    /**
     * 
     */
    public void erstelleLand(String name, int tore, int punkte, String gruppe)
    {
        länder.add(new Land(name, tore, punkte, gruppe));
    }
    
    /**
     * 
     */
    public void erstelleLandFertig()
    {
        String name = "Deutschland";
        int tore = 0;
        int punkte = 0;
        String gruppe = "H";
        
        länder.add(new Land(name, tore, punkte, gruppe));
    }
}
