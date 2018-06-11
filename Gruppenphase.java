import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 */
public class Gruppenphase
{
    private ArrayList<Gruppe> gruppen;
    private HashMap<String, Gruppe> gruppenHash; // das muss zur Hashmap gemacht werden oder in die txt geschrieben werden
    private IO io;

    /**
     * Constructor for objects of class Gruppen
     */
    public Gruppenphase()
    {
        gruppen = new ArrayList<>();
        gruppenHash = new HashMap<>();
        ladeGruppen();
        io = new IO();
    }

    /**
     * 
     */
    public void ladeGruppen()
    {
        gruppenHash.put("A", new Gruppe("A"));
        gruppenHash.put("B", new Gruppe("B"));
        gruppenHash.put("C", new Gruppe("C"));
        gruppenHash.put("D", new Gruppe("D"));
        gruppenHash.put("E", new Gruppe("E"));
        gruppenHash.put("F", new Gruppe("F"));
        gruppenHash.put("G", new Gruppe("G"));
        gruppenHash.put("H", new Gruppe("H"));
        // alt: gruppen.add(new Gruppe("A"));
    }

    /**
     * 
     */
    private String gibDatenSpielergebnis(String land, int tore, int punkte)
    {
        boolean check = false;
        String daten = "";

        for (String key : gruppenHash.keySet()) {
            Gruppe gruppe = gruppenHash.get(key);

            if(gruppe.prüfeLand(land) == true){
                check = true;      
                daten = gruppe.gibUpdatedInfoLand(land, tore, punkte);
            }
        }

        if(check == false){
            return null;
        }

        return daten;
    }

    public void updateSpielergebnis(String land1, int tore1, String land2, int tore2)
    {
        String gruppe1 = "";
        String gruppe2 = "";
        int punkte1 = 0;
        int punkte2 = 0;

        if(tore1 > tore2){punkte1 = 3;}
        if(tore1 < tore2){punkte2 = 3;}
        if(tore1 == tore2){punkte1 = 1; punkte2 = 1;}

        for (String key : gruppenHash.keySet()) {
            Gruppe gruppe = gruppenHash.get(key);

            if(gruppe.prüfeLand(land1) == true){
                gruppe1 = key;
            }
        }

        for (String key : gruppenHash.keySet()) {
            Gruppe gruppe = gruppenHash.get(key);

            if(gruppe.prüfeLand(land2) == true){
                gruppe2 = key;
            }
        }
        System.out.println("gruppe1: ");
        System.out.println(gruppe1);

        if(gruppe1 == gruppe2){
            Gruppe gruppeEins = gruppenHash.get(gruppe1);

            if(speichereLand(land1, tore1, punkte1) && speichereLand(land2, tore2, punkte2)== true){
                String daten = land1 + ":" + land2 + "-" + tore1 + ":" + tore2;
                
                try{
                    io.appendGruppe(gruppe1, daten);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                gruppeEins.ladeGruppeninfo(gruppe1);
            }

        }
        else{System.out.println("Die Länder sind nicht in einer Gruppe!");}
    }

    /**
     * 
     */
    public boolean speichereLand(String land, int tore, int punkte)
    {
        if(gibDatenSpielergebnis(land, tore, punkte) == null){
            System.out.println("Das Land " + land + " existiert nicht");
            return false;
        }
        else{
            try{
                io.speichereLand(gibDatenSpielergebnis(land, tore, punkte));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

}
