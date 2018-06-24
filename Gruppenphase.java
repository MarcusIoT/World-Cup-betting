import java.util.HashMap;
import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * 
 * @author Marcus Schoch; HfG; IoT3
 * @version 24.06.2018
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

    public void neuesSpielergebnis()
    {
        aktualisiereGruppeninfo();
        String[] datenEingabe = ui.eingabeAufforderungSpielergebnis();
        if(datenEingabe != null){ 
            int tore1 = Integer.valueOf(datenEingabe[1]);
            int tore2 = Integer.valueOf(datenEingabe[3]);
            String land1 = schreibeGroß(datenEingabe[0]);
            String land2 = schreibeGroß(datenEingabe[2]);
            boolean check = true;
            int[] punkte = berechnePunkte(tore1, tore2);

            String nameGruppe = prüfeLänderInGruppe(land1, land2);
            if(nameGruppe != null){
                Gruppe gruppe = gruppen.get(nameGruppe);

                if (gruppe.prüfeExistenzSpielergebnis(land1, land2) == false){
                    if(ui.okAbbrechen("Fehler", "Das Ergebnis wurde bereits eingegeben. Möchten sie die alte Eingabe überschreiben?") == false){
                        check = false;
                    }
                    else gruppe.löscheTorePunkteSpielergebnis(land1, land2);
                }
                if(gruppe.prüfeSchreibweiseSpielergebnis(land1, land2) == true && check == true){              
                    speichereLand(land1, tore1, punkte[0]); 
                    speichereLand(land2, tore2, punkte[1]);
                    String daten = land1 + ":" + land2 + "-" + tore1 + ":" + tore2;
                    speichereGruppe(nameGruppe, updateGruppeSpielinfo(nameGruppe, land1, land2, daten));
                    gruppe.ladeGruppeninfo();
                }
                else if(gruppe.prüfeSchreibweiseSpielergebnis(land2, land1) == true && check == true){
                    speichereLand(land1, tore1, punkte[0]); 
                    speichereLand(land2, tore2, punkte[1]);
                    String daten = land2 + ":" + land1 + "-" + tore2 + ":" + tore1;
                    speichereGruppe(nameGruppe, updateGruppeSpielinfo(nameGruppe, land2, land1, daten));
                    gruppe.ladeGruppeninfo();
                }
            }
            else{ui.nachricht("Fehler", "Die Länder existieren nicht oder sind nicht in einer Gruppe.");}
        }
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

    private void speichereGruppe(String name, String[] teile)
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
    private String[] updateGruppeSpielinfo(String nameGruppe, String land1, String land2, String daten)
    {        
        
        Gruppe gruppe = gruppen.get(nameGruppe);
        String gruppenDatenAlt = gruppe.gibDaten("Gruppen", nameGruppe);

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
            gruppe.ladeGruppeninfo();
            daten += "Gruppe " + key + "<br>";
            daten += gruppe.gibSpielergebnisDaten();
            daten += "!";
        }

        ui.erstelleSpielplan(daten);
    }

    /**
     * 
     */
    private void aktualisiereGruppeninfo()
    {
        for (String key : gruppen.keySet()) {
            Gruppe gruppe = gruppen.get(key);
            gruppe.ladeGruppeninfo();
        }
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
        String check = ui.eingabeAufforderungEinFeld("Land Entfernen", "Wenn sie ein Land aus einer Gruppe entfernen werden alle Daten resetted.", "Name des Landes");
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
                    speichereGruppe(name, datenGruppe);
                    gruppe.löscheLänder();
                    gruppe.ladeGruppeninfo();
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
    public void neuesLand()
    {
        String[] daten = ui.eingabeAufforderungNeuesLand();
        if(daten != null){
            if(ui.okAbbrechen("Bestätigung", "Wollen sie wirklich alle Einträge löschen?") == true){
                String gruppe = daten[0];
                if(gruppen.containsKey(gruppe) == true){
                    String name = schreibeGroß(daten[1]);
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
            speichereGruppe(nameGruppe, teile);
            gruppen.put(nameGruppe, new Gruppe(nameGruppe));
            for (int i = 0; i < datenAlt.length; i++) {
                neuesLand(nameGruppe, datenAlt[i]);
            }
        }
        else;
    }

    private String[] gibGruppen()
    {
        StringBuffer daten = new StringBuffer();
        for (String key : gruppen.keySet()) {
            if (daten.length() != 0) {
                daten.append("/");
            }
            daten.append(key);         
        }
        String sDaten = daten.toString();
        String[] teile = sDaten.split("/");
        return teile;
    }

    /**
     * 
     */
    public void entferneGruppe()
    {
        String daten = ui.eingabeAufforderungEinFeld("Gruppe Entfernen", "Wenn sie eine Gruppe entfernen werden deren Länder gelöscht.", "Name der Gruppe");
        String[] gruppenFest = {"A", "B", "C", "D", "E", "F", "G", "H"};
        ArrayList<String> arraylist= new ArrayList<String>(Arrays.asList(gruppenFest));
        if(daten != null){
            String nameGruppe = schreibeGroß(daten);
            if(arraylist.contains(nameGruppe) == false){
                if(gruppen.containsKey(nameGruppe)){
                    Gruppe gruppe = gruppen.get(nameGruppe);
                    String[] länder = gruppe.gibLänder();
                    for (int i = 0; i < länder.length; i++) {
                        try{
                            io.löscheDatei("Länder", länder[i]);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }                
                    gruppen.remove(nameGruppe);

                    String[] datenGruppen = gibGruppen();
                    try{
                        io.speichereGruppe("Gruppen", datenGruppen);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    try{
                        io.löscheDatei("Gruppen", nameGruppe);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else ui.nachricht("Fehler", "Die Gruppe " + nameGruppe + " existiert nicht.");
            }
            else ui.nachricht("Fehler", "Die Gruppe " + nameGruppe + " darf nicht entfernt werden.");
        }
    }

    /**
     * 
     */
    public void zeigeInformationLand()
    {
        String daten = ui.eingabeAufforderungEinFeld("Zeige Land", "", "Name des Landes");
        if(daten != null){
            String nameLand = schreibeGroß(daten);
            Gruppe gruppe = gibGruppeWennLand(nameLand);
            if(gruppe != null){
                String[] torePunkte = gruppe.gibInfoLand(nameLand);
                ui.nachricht("Information", "Das Land " + nameLand + " hat " + torePunkte[1] + " Tore geschossen und derzeit " + torePunkte[2] + " Punkte.");
            }
            else ui.nachricht("Fehler", "Das Land " + nameLand + " existiert nicht.");
        }      
    }
}
