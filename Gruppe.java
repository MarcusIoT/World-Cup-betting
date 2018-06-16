import java.util.HashMap;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 */
public class Gruppe
{
    private HashMap<String, Land> länder;
    private int gruppenGroesse;
    private IO io;
    private String nameGruppe;
    private HashMap<String, String> spiele;
    /**
     * Konstructor
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
     * 
     */
    public void erstelleLand(String name)
    {
        Land land = new Land(name, 0, 0);
        länder.put(name, land);
        gruppenGroesse += 1;
    }

    public void berechnePaarungen()
    {
        ArrayList teile = new ArrayList<String>();
        String[] länder = gibLänder();
        int größeSpiele = binominalkoeffizient(gruppenGroesse, 2); //Todoo
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
     * 
     */
    public int binominalkoeffizient(int n, int k)
    {        
        if (k > n) return 0;
        else {
            int a = 1;
            for (int i = n-k+1; i <= n; i++) a *= i;
            int b = 1;
            for (int i = 2; i <= k; i++) b *= i;
            return a / b;
        }
    }
    

    public int gibGroesse()
    {
        return gruppenGroesse;
    }

    public void ladeLand(String name)
    {
        String[] teile = gibDatenTeile("Länder", name);

        String nameLand = teile[0];
        int tore = Integer.valueOf(teile[1]);
        int punkte = Integer.valueOf(teile[2]);

        länder.put(nameLand, new Land(nameLand, tore, punkte));

    }

    /**
     * 
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
        }
        
        ladeSpiele(teile);
    }

    public void ladeSpiele(String[] teile)
    {
        if(teile.length >= gruppenGroesse+2){
            for (int i = gruppenGroesse+1; i < teile.length; i++) {
                String[] daten = teile[i].split("-");

                spiele.put(daten[0], daten[1]);

            }
        }
    }

    /**
     * 
     */
    private String[] gibDatenTeile(String ordner, String datei)
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

    private String gibDaten(String ordner, String datei)
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
     * ToDo
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
     * 
     */
    public boolean prüfeExistenzSpielergebnis (String land1, String land2)
    {
        String spiel = land1 + ":" + land2;
        String check = " : ";

        if(check.equals(spiele.get(spiel)) == true){
            return true;
        }
        else return false;

    }

    /**
     *
     */
    public boolean prüfeLand(String name)
    {
        if(länder.containsKey(name)){
            return true;
        }
        else{return false;}
    }

    /**
     *
     */
    public String[] gibUpdatedInfoLand(String name, int tore, int punkte)
    {
        Land land = länder.get(name);
        return land.gibUpdatedInfo(tore, punkte);
    }

    /**
     * 
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
     * 
     */
    public void löscheSpiele()
    {
        spiele.clear();
    }
}
