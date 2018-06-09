import java.util.ArrayList;
/**
 *
 */
public class Gruppe
{

    private ArrayList<Land> länder;
    private int gruppenGroesse;
    private IO io;

    /**
     * Konstructor
     */
    public Gruppe(String identifikation)
    {
        länder = new ArrayList<>();
        io = new IO();
        ladeGruppeninfo(identifikation);
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
    
    public void ladeLand(String name)
    {
        String daten = "";
        try{
            daten = io.ladeLand(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        String[] parts = daten.split("/");
        int tore = Integer.valueOf(parts[1]);
        int gegenTore = Integer.valueOf(parts[2]);
        int punkte = Integer.valueOf(parts[3]);
        
        länder.add(new Land(name, tore, gegenTore, punkte));
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void ladeGruppeninfo(String identifikation)
    {
        String daten = "";
        try{
            daten = io.ladeGruppe(identifikation);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String[] parts = daten.split("/");
        gruppenGroesse = Integer.valueOf(parts[0]);
        ladeLand(parts[1]);
        ladeLand(parts[2]);
        ladeLand(parts[3]);
        ladeLand(parts[4]);
    }

}
