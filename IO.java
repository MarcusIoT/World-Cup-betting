import java.io.*;
/**
 * Write a description of class Auslesen here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    public  void speichereLand(String daten) throws IOException
    {
        String[] teile = daten.split("/");
        
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
    public  void appendGruppe(String dateiName, String daten) throws IOException
    {
        //hier brauche ich die Info welche Gruppe es ist
        
        String datenAlt = ladeDatei("Gruppen", dateiName);
        
        String datei = "Gruppen/" + dateiName + ".txt";
        FileWriter fw = new FileWriter(datei, true);
        BufferedWriter bw = new BufferedWriter(fw);

        
        if(!datenAlt.contains(daten)){
            bw.newLine();
            bw.write(daten);
        }
       
        
        bw.close();
    }
}
