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
    
    public void ladeLand(String name)
    {
        String daten = "";
        try{
            daten = io.ladeLand(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        String[] teile = daten.split("/");
        int tore = Integer.valueOf(teile[1]);
        int gegenTore = Integer.valueOf(teile[2]);
        int punkte = Integer.valueOf(teile[3]);
        
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

        String[] teile = daten.split("/");
        gruppenGroesse = Integer.valueOf(teile[0]);
        ladeLand(teile[1]);
        ladeLand(teile[2]);
        ladeLand(teile[3]);
        ladeLand(teile[4]);
    }

}
