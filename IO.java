import java.io.*;
import java.lang.*;
import java.nio.file.*;

/**
 * Write a description of class Auslesen here.
 *
 * @author Marcus Schoch
 * @version 17.06.2018
 */
public class IO
{
    /**
     * Constructor for objects of class Auslesen
     */
    public IO()
    {

    }

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
     * 
     */
    public  void speichereLand(String[] teile) throws IOException
    {
        String datei = "Länder/" + teile[0] + ".txt";
        FileWriter fw = new FileWriter(datei);
        BufferedWriter bw = new BufferedWriter(fw);


        for (int x = 0; x < teile.length; x++) {
            bw.write(teile[x]);
            if(x<teile.length){bw.newLine();};
        }

        bw.close();
    }
    
    /**
     * 
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
     * 
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
     * 
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
     * 
     */
    public  void löscheDatei(String ordner, String name) throws IOException
    {
        String datei = ordner + "/" + name + ".txt";
        Files.deleteIfExists(Paths.get(datei));
    }
}
