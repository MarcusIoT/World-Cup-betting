import java.util.HashMap;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Die Klasse Gruppe beinhaltet eine Hashmap in der als Key ein String vorliegt und als Value ein Objekt der Klasse Land, 
 * sowie eine Hasmap in der als Key ein String in Form "Land1:Land2" udn als Value ein String in Form "Tore1:Tore2" vorliegt.
 * 
 *
 * @author Marcus Schoch
 * @version 24.06.2018
 */
public class Gruppe
{
    private HashMap<String, Land> länder;
    private int gruppenGroesse;
    private IO io;
    private String nameGruppe;
    private HashMap<String, String> spiele;

    /**
     * Kostruktor für Objekte der Klasse Gruppe.
     * Beim Erzeugen eines Objektes der Klasse Guppe muss diesem ein Name übergeben werden, welcher auch global gespeichert wird.
     * Zusätzlich wird ein Objekt der Klasse IO erstellt und die zwei Hashmaps Länder und Spiele erstellt.
     * Des Weiteren wird die Methode ladeGruppeninfo() aufgerufen.
     */
    public Gruppe(String name)
    {
        länder = new HashMap<>();
        spiele = new HashMap<>();
        io = new IO();
        this.nameGruppe = name;
        ladeGruppeninfo(name);
    }

    /**
     * Erstellt ein neues Objekt der Klasse Land mit dem übergebenem Namen und leeren Toren und Punktzahl, und fügt es der HashMap Länder hinzu.
     * Zusätzlich wird diese Information in den Textdateien der Länder und der Gruppe gespeichert.
     * Zuetzt wird die Gruppeninfo neu geladen und demnach die Paarungen neu berechnet.
     */
    public void erstelleLand(String name)
    {
        Land land = new Land(name, 0, 0);
        länder.put(name, land);
        gruppenGroesse += 1;
        try{
            io.speichereLand(gibInfoLand(name));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String daten = gibDaten("Gruppen", nameGruppe);
        daten += name + "/";
        String[] teile = daten.split("/");
        teile[0] = String.valueOf(gruppenGroesse);
        try{
            io.speichereGruppe(nameGruppe, teile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ladeGruppeninfo(nameGruppe);
    }

     /**
     * Berechnet alle Paarungen der Länder in der Hashmap Länder in dieser Klasse und speichert diese Information in der Text Datei dieser Gruppe.
     */
    private void berechnePaarungen()
    {
        ArrayList teile = new ArrayList<String>();
        String[] länder = gibLänder();
        teile.add(String.valueOf(gruppenGroesse));

        for (int i = 0; i < länder.length; i++) {
            teile.add(länder[i]);
        }

        for (int i = 0; i < gruppenGroesse; i++) {
            for (int x = i+1; x < gruppenGroesse; x++) {
                teile.add(länder[i] + ":" + länder[x] + "- : ");
            }
        }

        String[] daten = new String[teile.size()];
        teile.toArray( daten );

        try{
            io.speichereGruppe(nameGruppe, daten);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

     /**
     * Lädt die Iformationen eines Landes aus deren Text Datei und fügt dieses Land der Hashmap Länder hinzu.
     */
    private void ladeLand(String name)
    {
        String[] teile = gibDatenTeile("Länder", name);

        String nameLand = teile[0];
        int tore = Integer.valueOf(teile[1]);
        int punkte = Integer.valueOf(teile[2]);

        länder.put(nameLand, new Land(nameLand, tore, punkte));

    }

    /**
     * Lädt alle Länder, die in der Text Datei dieser Gruppe hinterlegt sind und berechtnet die Paarunegen wenn keine vorliegen.
     */
    public void ladeGruppeninfo(String name)
    {
        String[] teile = gibDatenTeile("Gruppen", name);

        gruppenGroesse = Integer.valueOf(teile[0]);
        for (int i = 1; i <= gruppenGroesse; i++) {
            ladeLand(teile[i]);
        }

        if(teile.length <= gruppenGroesse + 1){
            berechnePaarungen();
            teile = gibDatenTeile("Gruppen", name);
        }

        ladeSpiele(teile);
    }
    
    /**
     * Lädt die übergebenen Spiele als Array in die Hashmap spiele in dieser Gruppe.
     */
    private void ladeSpiele(String[] teile)
    {
        if(teile.length >= gruppenGroesse+2){
            for (int i = gruppenGroesse+1; i < teile.length; i++) {
                String[] daten = teile[i].split("-");

                spiele.put(daten[0], daten[1]);

            }
        }
    }

    /**
     * Lädt die Informationen einer Text Datei mit dem übergebenen Ordner und Dateiname, und übergibt diese als Array.
     */
    public String[] gibDatenTeile(String ordner, String datei)
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

    /**
     * Lädt die Informationen einer Text Datei mit dem übergebenen Ordner und Dateiname, und übergibt diese als String.
     */
    public String gibDaten(String ordner, String datei)
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
     * Gibt alle Spielergebnisse, die Paarungen, als String zurück.
     * Sind keine vorhanden gibt diese Methode einen Indikator zurück.
     */
    public String gibSpielergebnisDaten()
    {
        ladeGruppeninfo(nameGruppe);

        String daten = "";
        for (String key : spiele.keySet()) {
            daten += (key + "-" + spiele.get(key) + "<br>");
        }

        if(daten.isEmpty() == true){
            daten += "-------------";
        }

        return daten;
    }

    /**
     * Überprüft ob ein Spielergebnis, egal ob noch leer oder eingetragen, in der Hashmap vorliegt und liefert true zurück sollte dem so sein. 
     */
    public boolean prüfeExistenzSpielergebnis (String land1, String land2)
    {
        String spiel = land1 + ":" + land2;
        String spielRück = land2 + ":" + land1;
        String check = " : ";

        if(check.equals(spiele.get(spiel)) == true || check.equals(spiele.get(spielRück)) == true){
            return true;
        }
        else return false;

    }

    /**
     * Entfernt die Werte eines Spielergebnises aus den Ländern.
     */
    public void löscheTorePunkteSpielergebnis (String land1, String land2)
    {
        String spielVor = land1 + ":" + land2;
        String spielRück = land2 + ":" + land1;
        String spiel = "";
        String daten = "";
        String landEINS = land1;        
        String landZWEI = land2;

        if(spiele.containsKey(spielVor) == true){
            spiel = spielVor;
        }
        if(spiele.containsKey(spielRück) == true){
            spiel = spielRück;
            landEINS = land2;
            landZWEI = land1;
        }

        Land landEins = länder.get(landEINS);
        Land landZwei = länder.get(landZWEI);

        daten = spiele.get(spiel);
        String[] teile = daten.split(":");
        int tore1 = Integer.valueOf(teile[0]);
        int tore2 = Integer.valueOf(teile[1]);
        if(tore1 == tore2){
            landEins.subtrahiereWerte(tore1, 1);
            landZwei.subtrahiereWerte(tore2, 1);
        }
        if(tore1 < tore2){
            landEins.subtrahiereWerte(tore1, 0);
            landZwei.subtrahiereWerte(tore2, 3);
        }
        if(tore1 > tore2){
            landEins.subtrahiereWerte(tore1, 3);
            landZwei.subtrahiereWerte(tore2, 0);
        }

    }

    /**
     * Liefert true zurück, wenn ein Spielergebnis mit genau dieser Schreibweise vorliegt, ansonten false.
     */
    public boolean prüfeSchreibweiseSpielergebnis (String land1, String land2)
    {
        String spiel = land1 + ":" + land2;      
        if(spiele.containsKey(spiel) == true){
            return true;
        }
        else return false;
    }

    /**
     * Prüft ob ein Land in dieser Gruppe mit dem übergebenen Namen existiert und liefert true oder false zurück.
     */
    public boolean prüfeLand(String name)
    {
        if(länder.containsKey(name)){
            return true;
        }
        else{return false;}
    }

    /**
     * Ruft gibUpdatedInfo() in dem Land auf, aktualisiert darin die Daten der Tore und Punkte und liefert dann das Array mit allen Werten des Landes zurück.
     */
    public String[] gibUpdatedInfoLand(String name, int tore, int punkte)
    {
        Land land = länder.get(name);
        return land.gibUpdatedInfo(tore, punkte);
    }

    /**
     * Ruft gibInfo() in dem Land auf und liefert dann das Array mit allen Werten des Landes zurück.
     */
    public String[] gibInfoLand(String name)
    {
        Land land = länder.get(name);
        return land.gibInfo();
    }

    /**
     * Speichert alle Länder dieser Gruppe in einem Array und gibt dieses zurück.
     */
    public String[] gibLänder()
    {
        StringBuffer daten = new StringBuffer();
        for (String key : länder.keySet()) {
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
     * Löscht alle Werte in der Hashmap Spiele.
     */
    public void löscheSpiele()
    {
        spiele.clear();
    }

    /**
     * Löscht alle Werte in der Hashmap Länder.
     */
    public void löscheLänder()
    {
        länder.clear();
    }
    
    /**
     * Gibt die Gruppengröße als Integer zurück.
     */
    public int gibGroesse()
    {
        return gruppenGroesse;
    }

    /**
     * Gibt den Namen der Gruppe als String zurück.
     */
    public String gibName()
    {
        return nameGruppe;
    }
}
