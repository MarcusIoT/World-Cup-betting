import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 */
public class Gruppe
{

    private ArrayList<Land> länder;
    private HashMap<String, Land> länderHash;
    private int gruppenGroesse;
    private IO io;

    /**
     * Konstructor
     */
    public Gruppe(String identifikation)
    {
        länder = new ArrayList<>();
        länderHash = new HashMap<>();
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
        String nameLand = teile[0];
        int tore = Integer.valueOf(teile[1]);
        int gegenTore = Integer.valueOf(teile[2]);
        int punkte = Integer.valueOf(teile[3]);
        
        länderHash.put(nameLand, new Land(nameLand, tore, gegenTore, punkte));
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
        for (int i = 1; i < teile.length; i++) {
            ladeLand(teile[i]);
        }
    }
    
    /**
     *
     */
    public boolean prüfeLand(String name)
    {
        if(länderHash.containsKey(name)){
            return true;
        }
        else{return false;}
    }

    /**
     *
     */
    public String gibUpdatedInfoLand(String name, int tore, int punkte)
    {
        Land land = länderHash.get(name);
        return land.gibUpdatedInfo(tore, punkte);
    }
    
}
