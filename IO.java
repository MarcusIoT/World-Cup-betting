import java.io.*;
import java.util.ArrayList;
/**
 * Write a description of class Auslesen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IO
{
    private ArrayList<String> inhalt;
    /**
     * Constructor for objects of class Auslesen
     */
    public IO()
    {
        inhalt = new ArrayList<>();
    }

    /**
     *
     */
    public void test() throws IOException
    {
        FileReader fr = new FileReader("test.txt");
        BufferedReader br = new BufferedReader(fr);

        String zeile = "";

        while( (zeile = br.readLine()) != null )
        {
            inhalt.add(zeile);
        }

        br.close();
    }

    public String ladeGruppe(String dateiname) throws IOException
    {
        String datei = "Gruppen/" + dateiname + ".txt";
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

    public String ladeLand(String dateiname) throws IOException
    {
        String datei = "Länder/" + dateiname + ".txt";
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
     * 
     */
    public  void testSpeichern() throws IOException
    {
        FileWriter fw = new FileWriter("test.txt", true); // FileWriter(String fileName, boolean append) append = true -> hängt hinten an)
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("test test test");
        bw.newLine();
        bw.write("tset tset tset");

        bw.close();
    }

    /**
     * 
     */
    public  void speichereLand(String dateiname, String daten) throws IOException
    {
        String datei = "Länder/" + dateiname + ".txt";
        FileWriter fw = new FileWriter(datei);
        BufferedWriter bw = new BufferedWriter(fw);

        String[] teile = daten.split("/");

        for (int x = 0; x < teile.length; x++) {
            bw.write(teile[x]);
            if(x<teile.length){bw.newLine();};
        }
        
        bw.close();
    }
}
