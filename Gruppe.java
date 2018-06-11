import java.util.HashMap;
/**
 *
 */
public class Gruppe
{
    private HashMap<String, Land> länder;
    private int gruppenGroesse;
    private IO io;
    private HashMap<String, String> spiele;
    /**
     * Konstructor
     */
    public Gruppe(String identifikation)
    {
        länder = new HashMap<>();
        spiele = new HashMap<>();
        io = new IO();
        ladeGruppeninfo(identifikation);
    }

    /**
     * 
     */
    public void erstelleLand(String name)
    {
        Land land = new Land(name, 0, 0);
        länder.put(name, land);
        gruppenGroesse += 1;
    }
    
    public void ladeLand(String name)
    {
        String daten = "";
        try{
            daten = io.ladeDatei("Länder", name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        String[] teile = daten.split("/");
        String nameLand = teile[0];
        int tore = Integer.valueOf(teile[1]);
        int punkte = Integer.valueOf(teile[2]);
        
        länder.put(nameLand, new Land(nameLand, tore, punkte));
    }

    /**
     * 
     */
    public void ladeGruppeninfo(String identifikation)
    {
        String daten = "";
        try{
            daten = io.ladeDatei("Gruppen", identifikation);
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
        if(länder.containsKey(name)){
            return true;
        }
        else{return false;}
    }

    /**
     *
     */
    public String gibUpdatedInfoLand(String name, int tore, int punkte)
    {
        Land land = länder.get(name);
        return land.gibUpdatedInfo(tore, punkte);
    }
    
    /**
     *
     */
    public String speichereErgebnis(String land1, String land2, int tore1, int tore2)
    {
        String spieler = land1 + ":" + land2;
        String ergebnis = tore1 + ":" + tore2;
        spiele.put(spieler, ergebnis);
        String daten = spieler + "/" + ergebnis;
        return daten;
    }
}
