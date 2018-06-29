import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Diese Klasse übernimmt das Auslesen und Speichern von Informationen in .txt Dateien.
 *
 * @author Marcus Schoch; HfG; IoT3
 * @version 24.06.2018
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
     * 
     * @param ordner    Das Ordner Verzeichnis, entweder "Gruppen" oder "Länder"
     * @param dateiname Der Dateiname der Text Datei als String
     * 
     * @return daten aller Zeilen getrennt durch "/" in einem String
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
     * 
     * @param teile  Informationen, die für jeden Teil in eine neue Zeile geschrieben werden sollen.
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
     * 
     * @param teile  Informationen, die für jeden Teil in eine neue Zeile geschrieben werden sollen.
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
     * 
     * @param DateiName  Der Dateiname der Text Datei als String
     * @param teile  Informationen, die für jeden Teil in eine neue Zeile geschrieben werden sollen.
     */
    public  void speichereGruppe(String dateiName, String[] teile) throws IOException
    {
        String datei = "Gruppen/" + dateiName + ".txt";
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
     * 
     * @param daten Information die der Datei angehängt werden soll
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
     * 
     * @param ordner    Das Ordner Verzeichnis, entweder "Gruppen" oder "Länder"
     * @param dateiname Der Dateiname der Text Datei als String
     */
    public  void löscheDatei(String ordner, String dateiName) throws IOException
    {
        String datei = ordner + "/" + dateiName + ".txt";
        Files.deleteIfExists(Paths.get(datei));
    }
}