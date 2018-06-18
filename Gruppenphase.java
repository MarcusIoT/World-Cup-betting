import java.util.HashMap;
import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * 
 * @author Marcus Schoch
 * @version 17.06.2018
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
        io = new IO();
        ui = new UI();
        ladeGruppen();
    }
    
    /**
     * 
     */
    private void ladeGruppen()
    {
        String daten = "";
        String[] teile;
        try{
            daten = io.ladeDatei("Gruppen", "Gruppen");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        teile = daten.split("/");
        for (int i = 0; i < teile.length; i++) {
            gruppen.put(teile[i], new Gruppe(teile[i]));
        }
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
        if(datenEingabe != null){
            int tore1 = Integer.valueOf(datenEingabe[1]);
            int tore2 = Integer.valueOf(datenEingabe[3]);
            String land1 = schreibeGroß(datenEingabe[0]);
            String land2 = schreibeGroß(datenEingabe[2]);
            boolean check = true;
            int[] punkte = berechnePunkte(tore1, tore2);

            String gruppeDerLänder = prüfeLänderInGruppe(land1, land2);
            if(gruppeDerLänder != null){
                Gruppe gruppe = gruppen.get(gruppeDerLänder);

                if (gruppe.prüfeExistenzSpielergebnis(land1, land2) == false){
                    if(ui.okAbbrechen("Fehler", "Das Ergebnis wurde bereits eingegeben. Möchten sie die alte Eingabe überschreiben?") == false){
                        check = false;
                    }                   
                }
                if(gruppe.prüfeSchreibweiseSpielergebnis(land1, land2) == true && check == true){
                    // hier noch die alten Punkte wieder abziehen
                    updateLand(land1, tore1, punkte[0]); 
                    updateLand(land2, tore2, punkte[1]);
                    String daten = land1 + ":" + land2 + "-" + tore1 + ":" + tore2;
                    updateGruppe(gruppeDerLänder, updateGruppe(gruppeDerLänder, land1, land2, daten));
                    gruppe.ladeGruppeninfo(gruppeDerLänder);
                }
                else if(gruppe.prüfeSchreibweiseSpielergebnis(land2, land1) == true && check == true){
                    // hier noch die alten Punkte wieder abziehen
                    updateLand(land1, tore1, punkte[0]); 
                    updateLand(land2, tore2, punkte[1]);
                    String daten = land2 + ":" + land1 + "-" + tore2 + ":" + tore1;
                    updateGruppe(gruppeDerLänder, updateGruppe(gruppeDerLänder, land2, land1, daten));
                    gruppe.ladeGruppeninfo(gruppeDerLänder);
                }
            }
            else{ui.nachricht("Fehler", "Die Länder sind nicht in einer Gruppe");}
        }
    }    

    /**
     * 
     */
    private void updateLand(String land, int tore, int punkte)
    {

        try{
            io.speichereLand(gibDatenSpielergebnis(land, tore, punkte));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateGruppe(String name, String[] teile)
    {
        try{
            io.speichereGruppe(name, teile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private String[] updateGruppe(String gruppeDerLänder, String land1, String land2, String daten)
    {        
        String gruppenDatenAlt = "";
        try{
            gruppenDatenAlt += io.ladeDatei("Gruppen", gruppeDerLänder);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String[] teileGruppenDatenAlt = gruppenDatenAlt.split("/");
        for (int i = 0; i < teileGruppenDatenAlt.length; i++) {
            String check = teileGruppenDatenAlt[i];
            if(check.contains(land1 + ":" + land2) == true){
                teileGruppenDatenAlt[i] = daten;
            }
        }

        return teileGruppenDatenAlt;
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
            gruppe.ladeGruppeninfo(key);
            daten += "Gruppe " + key + "<br>";
            daten += gruppe.gibSpielergebnisDaten();
            daten += "!";
        }

        ui.erstelleSpielplan(daten);
    }

    /**
     *
     */
    public void löscheAlleEinträge()
    {
        if(ui.okAbbrechen("Bestätigung", "Wollen sie wirklich alle Einträge löschen?") == true){
            entferneAlleEinträge();
        }
    }

    /**
     * 
     */
    private void entferneAlleEinträge()
    {
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
                io.resetGruppe(datenGruppe);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            gruppe.löscheSpiele();

        }
    }

    /**
     * 
     */
    public void entferneLand()
    {
        String check = ui.eingabeAufforderungEntferneLand();
        if(check != null){
            String nameLand = schreibeGroß(check);
            Gruppe gruppe = gibGruppeWennLand(nameLand);
            if(gruppe != null){
                if(ui.okAbbrechen("Bestätigung", "Wollen sie wirklich alle Einträge löschen?") == true){
                    entferneAlleEinträge();
                    String name = gruppe.gibName();
                    String[] teile = gruppe.gibDatenTeile("Gruppen", name);
                    int gruppenGroesse = Integer.valueOf(teile[0]);
                    teile[0] = String.valueOf(gruppenGroesse - 1);

                    ArrayList daten = new ArrayList<String>();
                    for (int i = 0; i < teile.length; i++) {
                        daten.add(teile[i]);
                    }
                    daten.remove(nameLand);
                    String[] datenGruppe = new String[daten.size()];
                    daten.toArray( datenGruppe );
                    updateGruppe(name, datenGruppe);
                    gruppe.löscheLänder();
                    gruppe.ladeGruppeninfo(name);
                    try{
                        io.löscheDatei("Länder", name);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else ui.nachricht("Fehler", "Das Land " + nameLand + " existiert nicht.");
        }
    }

    /**
     * 
     */
    public void neuesLandUI()
    {
        String[] daten = ui.eingabeAufforderungNeuesLand();
        if(daten != null){
            if(ui.okAbbrechen("Bestätigung", "Wollen sie wirklich alle Einträge löschen?") == true){
                String gruppe = daten[0];
                if(gruppen.containsKey(gruppe) == true){
                    String name = daten[1];
                    neuesLand(gruppe, name);
                }
                else ui.nachricht("Fehler", "Die Gruppe " + gruppe + " existiert nicht.");
            }
        }
    }    

    /**
     * 
     */
    private void neuesLand(String nameGruppe, String land)
    {
        entferneAlleEinträge();
        Gruppe gruppe = gruppen.get(schreibeGroß(nameGruppe));
        gruppe.erstelleLand(schreibeGroß(land));
    }

    /**
     * 
     */
    public void neueGruppe()
    {
        String[] datenAlt = ui.eingabeAufforderungNeueGruppe();
        if(datenAlt != null){ 
            ArrayList daten = new ArrayList<String>();
            
            String nameGruppe = String.valueOf((char)(65 + gruppen.size()));
            try{
                io.appendGruppe(nameGruppe);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            String [] teile = new String[] {"0"};
            updateGruppe(nameGruppe, teile);
            gruppen.put(nameGruppe, new Gruppe(nameGruppe));
            for (int i = 0; i < datenAlt.length; i++) {
                neuesLand(nameGruppe, datenAlt[i]);
            }
        }
        else;
    }

}
