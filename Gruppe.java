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
    public Gruppe(String name)
    {
        länder = new HashMap<>();
        spiele = new HashMap<>();
        io = new IO();
        ladeGruppeninfo(name);
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
        String[] teile = gibDatenTeile("Länder", name);

        String nameLand = teile[0];
        int tore = Integer.valueOf(teile[1]);
        int punkte = Integer.valueOf(teile[2]);

        länder.put(nameLand, new Land(nameLand, tore, punkte));

    }

    /**
     * 
     */
    public void ladeGruppeninfo(String name)
    {
        String[] teile = gibDatenTeile("Gruppen", name);

        gruppenGroesse = Integer.valueOf(teile[0]);
        for (int i = 1; i <= gruppenGroesse; i++) {
            ladeLand(teile[i]);
        }

        if(teile.length >= gruppenGroesse+2){
            for (int i = gruppenGroesse+1; i < teile.length; i++) {
                String[] daten = teile[i].split("-");
                spiele.put(daten[0], daten[1]);
            }
        }
    }

    /**
     * 
     */
    private String[] gibDatenTeile(String ordner, String datei)
    {
        String daten = "";
        try{
            daten = io.ladeDatei(ordner, datei);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String[] teile = daten.split("/");
        return teile;
    }

    private String gibDaten(String ordner, String datei)
    {
        String daten = "";
        try{
            daten = io.ladeDatei(ordner, datei);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return daten; 
    }
    
    /**
     * ToDo
     */
    public String gibSpielergebnisDaten()
    {
        String daten = "";
        daten += "!";
        for (String key : spiele.keySet()) {
            daten += (key + "-" + spiele.get(key) + "/");
        }
        
        return daten;
    }

    /**
     * 
     */
    public boolean prüfeExistenzSpielergebnis (String land1, String land2)
    {
        String spiel = land1 + ":" + land2;
        String spielRück = land2 + ":" + land1;
        if(spiele.containsKey(spiel) == false && spiele.containsKey(spielRück) == false){
            return false;
        }
        else return true;
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
}
