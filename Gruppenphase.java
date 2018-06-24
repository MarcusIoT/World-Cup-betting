import java.util.HashMap;
import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Die Klasse Gruppenphase beinhaltet eine Hasmap aller Gruppen und dient als Hauptklasse zur ausführung des ganzen Programmes.
 * 
 * @author Marcus Schoch; HfG; IoT3
 * @version 24.06.2018
 */
public class Gruppenphase
{
    private HashMap<String, Gruppe> gruppen;
    private IO io;
    private UI ui;

    /**
     * Konstruktor für Objekte der Klasse Gruppenphase.
     * Beim Programmstart durch erzeugen eines Objektes der Klasse Gruppenphase werden die Gruppen und deren enthaltene Informationen geladen.
     */
    public Gruppenphase()
    {
        gruppen = new HashMap<>();             
        io = new IO();
        ui = new UI();
        ladeGruppen();
    }

    /**
     * Liest aus der Text Datei Gruppen die Namen aller präsenten Gruppen aus und lädt diese und deren Informationen in das laufende Programm.
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
     * Gibt das Objekt der Klasse Gruppe zurück, welches das übergebene Land enthält.
     * 
     * @param land  Name des Landes von dem die Gruppe ermittelt werden soll
     * 
     * @return Gibt ein Objekt der Klasse Gruppe zurück, wenn sich das Land darin befindet, ansonten null
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

    /**
     * Prüft ob beide Länder in einer Gruppe sind und gibt dann die Gruppe zurück wenn dem so ist.
     * 
     * @param land1 Name eines Landes als String
     * @param land2 Name eines Landes als String
     * 
     * @return Objekt der Klasse Gruppe wenn die Länder beide darin sind, ansonsten null
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
     * Berechnet die Punkte der Länder nach einem Spiel und gibt diese in einem Array zurück.
     * 
     * @param tore1 Tore als Integer
     * @param tore2 Tore als Integer
     * 
     * @return Punkte zugehörig zu den Länder in richtiger Reihenfolge als Array
     */
    private int[] berechnePunkte(int tore1, int tore2)
    {
        int[] punkte = {0, 0};

        if(tore1 > tore2){punkte[0] = 3;}
        if(tore1 < tore2){punkte[1] = 3;}
        if(tore1 == tore2){punkte[0] = 1; punkte[1] = 1;}
        return punkte;
    }

    /**
     * Öffnet ein Fenster zum Eintragen eines neuen Spielergebnisses.
     * Fehlermeldungen erscheinen, wenn die Länder nicht in einer Gruppe sind oder wenn dieses Ergebnis bereits existiert.
     * Die möglichkeit besteht dann diesen Eintrag zu überscheiben.
     * Bei einem erfolgreichen Eintrag werden alle Ergebnise berechnet, und direkt abgespeichert.
     */
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
     * Speichert aktualisierte Informationen zu einem Land.
     * 
     * @param land  Land als String
     * @param tore  Tore als Integer
     * @param punkte    Punkte als Integer
     */
    private void speichereLand(String land, int tore, int punkte)
    {
        Gruppe gruppe = gibGruppeWennLand(land);
        try{
            io.speichereLand(gruppe.gibUpdatedInfoLand(land, tore, punkte));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Speichert übergebene Informationen zu einer Gruppe.
     * 
     * @param name  Name der Gruppe
     * @param teile Array von Informationen zu der Gruppe
     */
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
     * Fügt das Spielergebnis in das richtige Feld des Arrays bestehend aus den Informationen der Gruppe.
     * 
     * @param nameGruppe    Name der Gruppe als String
     * @param land1 Name eines Landes als String
     * @param land2 Name eines Landes als String
     * @param daten Informationen der Gruppe
     * 
     * @return Array mit allen Informationen und dem eingefügten Ergebnis
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
     * Ändert den ersten Buchstaben des übergebenen Strings in einen Großbuchstaben um.
     * 
     * @param eingabe   Irgendeinen String
     * 
     * @return diesen String groß geschrieben
     */
    private String schreibeGroß(String eingabe)
    {
        String ausgabe = eingabe.substring(0, 1).toUpperCase() + eingabe.substring(1);
        return ausgabe;
    }

    /**
     * Öffnet ein Fenster in dem alle Ergebnise aller Gruppen angezeigt werden.
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
     * Aktualisiert alle Informationen jeder Gruppe.
     */
    private void aktualisiereGruppeninfo()
    {
        for (String key : gruppen.keySet()) {
            Gruppe gruppe = gruppen.get(key);
            gruppe.ladeGruppeninfo();
        }
    }

    /**
     *  Öffnet ein Fenster zur versicherung ob wirklich alle Einträge gelöscht werden sollen. Wenn gewünscht wird die Funktion entferneAlleEinträge() aufgerufen.
     */
    public void löscheAlleEinträge()
    {
        if(ui.okAbbrechen("Bestätigung", "Wollen sie wirklich alle Einträge löschen?") == true){
            entferneAlleEinträge();
        }
    }

    /**
     * Entfernt alle Spielergebnis Einträge und löscht die Werte aus der Hashmap Spiele in der jeweiligen Gruppe.
     * Zusätzlich werden alle Tore und Punkte der Länder auf 0 gesetzt.
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
     * Öffnet ein Fenster das eine Eingabe erfordert. Um ein Land zu entfernen müssen alle Daten gelöscht werden.
     * Ist dies Erwünscht wird das Land entfernt, alle Paarungen neu berechnet und Daten aktualisiert und gespeichert.
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
     * Öffnet ein Fenster das eine Eingabe erfordert. Um ein Land zu erstellen müssen alle Daten gelöscht werden.
     * Ist dies Erwünscht wird neuesLand() aufgerufen.
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
     * Ruft in der Gruppe die Methode erstelleLand() auf und fügt somit ein neues Land hinzu.
     * 
     * @param nameGruppe    Name der Gruppe als String
     * @param land  Name des Landes als String
     */
    private void neuesLand(String nameGruppe, String land)
    {
        entferneAlleEinträge();
        Gruppe gruppe = gruppen.get(schreibeGroß(nameGruppe));
        gruppe.erstelleLand(schreibeGroß(land));
    }

    /**
     * Öffnet ein neues Fenster das die Eingabe einer neuen Gruppe und Ländern erfordert.
     * Automatisch wird der Gruppe fortlaufend im Alphabet ein Name zugewiesen.
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

    /**
     * Gibt den Namen aller Gruppen als Array zurück.
     * 
     * @return Array mit den Keys aller Gruppen
     */
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
     * Öffnet ein Fenste in dem der Name der zu entfernenden Gruppe eingegeben werden kann.
     * Die Gruppen A-H sind als Standard nicht zu entfernen. Wenn eine Gruppe entfernt wird werden auch deren Länder gelöscht.
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
     * Öffnet ein Fenster in dem das Land eingegeben werden kann und zeigt dann die derzeitigen Tore und den Punktestand an.
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
