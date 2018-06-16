import java.util.HashMap;
import java.lang.*;
import java.io.*;
import java.util.ArrayList;
/**
 * 
 */
public class Gruppenphase
{
    private HashMap<String, Gruppe> gruppen; // das muss zur Hashmap gemacht werden oder in die txt geschrieben werden
    private IO io;
    private UI ui;

    /**
     * Constructor for objects of class Gruppen
     */
    public Gruppenphase()
    {
        gruppen = new HashMap<>();
        ladeGruppen();
        io = new IO();
        ui = new UI();
    }

    /**
     * 
     */
    private void ladeGruppen()
    {
        gruppen.put("A", new Gruppe("A"));
        gruppen.put("B", new Gruppe("B"));
        gruppen.put("C", new Gruppe("C"));
        gruppen.put("D", new Gruppe("D"));
        gruppen.put("E", new Gruppe("E"));
        gruppen.put("F", new Gruppe("F"));
        gruppen.put("G", new Gruppe("G"));
        gruppen.put("H", new Gruppe("H"));
    }

    /**
     * 
     */
    private Gruppe gibGruppeWennLand(String land)
    {
        String daten = "";

        for (String key : gruppen.keySet()) {
            Gruppe gruppe = gruppen.get(key);

            if(gruppe.prüfeLand(land) == true){
                return gruppe;
            }
        }

        return null;
    }

    private String[] gibDatenSpielergebnis(String land, int tore, int punkte)
    {
        Gruppe gruppe = gibGruppeWennLand(land);
        return gruppe.gibUpdatedInfoLand(land, tore, punkte);
    }

    /**
     * 
     */
    private String prüfeLänderInGruppe(String land1, String land2)
    {
        String gruppe1 = "";
        String gruppe2 = "";

        for (String key : gruppen.keySet()) {
            Gruppe gruppe = gruppen.get(key);

            if(gruppe.prüfeLand(land1) == true){
                gruppe1 = key;
            }
            if(gruppe.prüfeLand(land2) == true){
                gruppe2 = key;
            }
        }

        if(gruppe1 == gruppe2){
            return gruppe1;
        }
        else{return null;}

    }

    /**
     * 
     */
    private int[] berechnePunkte(int tore1, int tore2)
    {
        int[] punkte = {0, 0};

        if(tore1 > tore2){punkte[0] = 3;}
        if(tore1 < tore2){punkte[1] = 3;}
        if(tore1 == tore2){punkte[0] = 1; punkte[1] = 1;}
        return punkte; // muss noch fertig
    }

    public void updateSpielergebnis()
    {
        String[] datenEingabe = ui.eingabeAufforderungSpielergebnis();

        int tore1 = Integer.valueOf(datenEingabe[1]);
        int tore2 = Integer.valueOf(datenEingabe[3]);
        String land1 = schreibeGroß(datenEingabe[0]);
        String land2 = schreibeGroß(datenEingabe[2]);

        int[] punkte = berechnePunkte(tore1, tore2);

        String daten = land1 + ":" + land2 + "-" + tore1 + ":" + tore2;
        String gruppeDerLänder = prüfeLänderInGruppe(land1, land2);

        if(gruppeDerLänder != null){
            Gruppe gruppe = gruppen.get(gruppeDerLänder);
            if(gruppe.prüfeExistenzSpielergebnis(land1, land2) == false){

                speichereLand(land1, tore1, punkte[0]); 
                speichereLand(land2, tore2, punkte[1]);
                try{
                    io.appendGruppe(gruppeDerLänder, daten);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                gruppe.ladeGruppeninfo(gruppeDerLänder);

            }
            else{System.out.println("Das Ergebnis wurde bereits eingegeben!");}
        }
        else{System.out.println("Die Länder sind nicht in einer Gruppe!");}
    }

    /**
     * 
     */
    private void speichereLand(String land, int tore, int punkte)
    {

        try{
            io.speichereLand(gibDatenSpielergebnis(land, tore, punkte));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private String schreibeGroß(String eingabe)
    {
        String ausgabe = eingabe.substring(0, 1).toUpperCase() + eingabe.substring(1);
        return ausgabe;
    }

    /**
     * ToDo
     */
    public void gibAlleSpielergebnise ()
    {
        String daten = "";

        for (String key : gruppen.keySet()) {
            Gruppe gruppe = gruppen.get(key);
            daten += "Gruppe " + key + "<br>";
            daten += gruppe.gibSpielergebnisDaten();
            daten += "!";
        }

        System.out.println(daten);
        ui.erstelleSpielplan(daten);
    }

    /**
     *
     */
    public void löscheAlleEinträge()
    {
        if(ui.okAbbrechen("Bestätigung", "Wollen sie wirklich alle Einträge löschen?") == true){

            for (String key : gruppen.keySet()) {
                String name = key;
                Gruppe gruppe = gruppen.get(key);
                String[] teile = gruppe.gibLänder();

                ArrayList where = new ArrayList<String>();
                where.add(key);
                where.add(String.valueOf(gruppe.gibGroesse()));

                for (int i = 0; i < teile.length; i++) {
                    String[] datenLand = {teile[i], "0", "0"};
                    where.add(teile[i]);
                    try{
                        io.speichereLand(datenLand);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String[] datenGruppe = new String[where.size()];
                where.toArray( datenGruppe );

                try{
                    io.speichereGruppe(datenGruppe);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                gruppe.ladeGruppeninfo(key);
            }
        }
    }

}
