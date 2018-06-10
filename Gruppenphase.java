import java.util.ArrayList;
/**
 * 
 */
public class Gruppenphase
{
    private ArrayList<Gruppe> gruppen;
    private IO io;

    /**
     * Constructor for objects of class Gruppen
     */
    public Gruppenphase()
    {
        gruppen = new ArrayList<>();
        ladeGruppen();
        io = new IO();
    }

    /**
     * 
     */
    public void ladeGruppen()
    {
        gruppen.add(new Gruppe("A"));
        gruppen.add(new Gruppe("B"));
        gruppen.add(new Gruppe("C"));
        gruppen.add(new Gruppe("D"));
        gruppen.add(new Gruppe("E"));
        gruppen.add(new Gruppe("F"));
        gruppen.add(new Gruppe("G"));
        gruppen.add(new Gruppe("H"));
    }

    /**
     * 
     */
    public void trageSpielEin(String mannschaft1, int tore1, String Mannschaft2, int tore2)
    {
        boolean check = false;
        String infoMannschaft1 = "";
        for (int i = 0; i < gruppen.size(); i++) {
            Gruppe gruppe = gruppen.get(i);
        
            if(gruppe.prüfeLand(mannschaft1) == true){
                check = true;
                infoMannschaft1 = gruppe.gibInfoLand(mannschaft1);
                String[] teile = infoMannschaft1.split("/");
                teile[1] = String.valueOf(tore1 + Integer.valueOf(teile[1]));
                String daten = "";
                
                for (int x = 0; x < teile.length; x++) {
                    daten += teile[x];
                    if(x<(teile.length-1)){daten += "/";};
                }
                
                try{
                    io.speichereLand(mannschaft1, daten);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        if(check == true){
            System.out.println("Daten erfolgreich hinzugefügt");
        }
        else{System.out.println("Dieses Land existiert nicht!");}
    }

}
