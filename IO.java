import java.io.*;
import java.lang.*;
import java.nio.file.*;

/**
 * Diese Klasse übernimmt das Auslesen und Speichern von Informationen in .txt Dateien.
 *
 * @author Marcus Schoch
 * @version 22.06.2018
 */
public class IO
{
    /**
     * Kostruktor für Objekte der Klasse IO.
     */
    public IO()
    {

    }

    /**
     * Liest alle Zeilen aus einer Textdatei mit dem übergebenen Namen und Ordnerverzeichnis aus und übergibt diese als String.
     * Die Zeilen und damit auch die einzelnen Informationen sind in dem String durch ein "/" getrennt.
     */
    public String ladeDatei(String ordner, String dateiname) throws IOException
    {
        String datei = ordner + "/" + dateiname + ".txt";
        FileReader fr = new FileReader(datei);
        BufferedReader br = new BufferedReader(fr);

        String daten = "";
        String zeile = "";

        while( (zeile = br.readLine()) != null )
        {
            daten += zeile;
            daten += "/";
        }

        br.close();

        return daten;
    }

    /**
     * Speichert übergebenes Array in die Text Datei des Landes, dessen Namen in dem Array mit übergeben wird.
     * Jeder Wert des Arrays wird in einer neuen Zeile gespeichert.
     */
    public  void speichereLand(String[] teile) throws IOException
    {
        String datei = "Länder/" + teile[0] + ".txt";
        FileWriter fw = new FileWriter(datei);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int x = 0; x < teile.length; x++) {
            bw.write(teile[x]);
            if(x<teile.length-1){bw.newLine();};
        }

        bw.close();
    }

    /**
     * Speichert übergebenes Array in die Text Datei der Gruppe, dessen Namen in dem Array mit übergeben wird.
     * Jeder Wert des Arrays wird in einer neuen Zeile gespeichert.
     */
    public  void resetGruppe(String[] teile) throws IOException
    {
        String datei = "Gruppen/" + teile[0] + ".txt";
        FileWriter fw = new FileWriter(datei);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int x = 1; x < teile.length; x++) {
            bw.write(teile[x]);
            if(x<teile.length-1){bw.newLine();};
        }

        bw.close();
    }

    /**
     * Speichert übergebenes Array in die Text Datei der Gruppe, dessen Namen separat übergeben wird.
     * Jeder Wert des Arrays wird in einer neuen Zeile gespeichert.
     */
    public  void speichereGruppe(String name, String[] teile) throws IOException
    {
        String datei = "Gruppen/" + name + ".txt";
        FileWriter fw = new FileWriter(datei);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int x = 0; x < teile.length; x++) {
            bw.write(teile[x]);
            if(x<teile.length-1){bw.newLine();};
        }

        bw.close();
    }

    /**
     * Fügt übergebenen String lediglich der text Datei "Gruppen" hinzu, die vorhandenen Einträge bleiben dabei bestehen.
     */
    public  void appendGruppe(String daten) throws IOException
    {
        String datei = "Gruppen/Gruppen.txt";
        FileWriter fw = new FileWriter(datei, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.newLine();
        bw.write(daten);

        bw.close();
    }

    /**
     * Löscht die Text Datei mit dem übergebenen Namen in dem übergebenen Verzeichnis.
     */
    public  void löscheDatei(String ordner, String name) throws IOException
    {
        String datei = ordner + "/" + name + ".txt";
        Files.deleteIfExists(Paths.get(datei));
    }
}