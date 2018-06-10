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
    private String gibDatenSpielergebnis(String land, int tore)
    {
        boolean check = false;
        String daten = "";
        for (int i = 0; i < gruppen.size(); i++) {
            Gruppe gruppe = gruppen.get(i);

            if(gruppe.prüfeLand(land) == true){
                check = true;
                String infoLand = "";
                infoLand = gruppe.gibInfoLand(land);
                String[] teile = infoLand.split("/");
                teile[1] = String.valueOf(tore + Integer.valueOf(teile[1]));

                for (int x = 0; x < teile.length; x++) {
                    daten += teile[x];
                    if(x<(teile.length-1)){daten += "/";};
                }
            }
        }

        if(check == false){
            System.out.println("Das Land " + land + " existiert nicht");
        }

        return daten;
    }

    public void updateSpielergebnis(String land1, int tore1, String land2, int tore2)
    {
        try{
            io.speichereLand(gibDatenSpielergebnis(land1, tore1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
