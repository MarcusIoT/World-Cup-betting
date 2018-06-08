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
    
    public String lade() throws IOException
    {
        FileReader fr = new FileReader("test.txt");
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
}
