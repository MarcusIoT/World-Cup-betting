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

    public String ladeGruppe(String text) throws IOException
    {
        String dateiName = "Gruppen/" + text + ".txt";
        FileReader fr = new FileReader(dateiName);
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
    
     public String ladeLand(String text) throws IOException
    {
        String dateiName = "Länder/" + text + ".txt";
        FileReader fr = new FileReader(dateiName);
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
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void print(String text) throws IOException
    {
        String dateiName = text + ".txt";
        FileReader fr = new FileReader(dateiName);
        BufferedReader br = new BufferedReader(fr);

        String zeile = "";

        while( (zeile = br.readLine()) != null )
        {
            System.out.println(zeile);
        }

        br.close();
    }

}
