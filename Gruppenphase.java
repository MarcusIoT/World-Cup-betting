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
    private String gibDatenSpielergebnis(String land, int tore, int punkte)
    {
        boolean check = false;
        String daten = "";
        for (int i = 0; i < gruppen.size(); i++) {
            Gruppe gruppe = gruppen.get(i);

            if(gruppe.prüfeLand(land) == true){
                check = true;      
                daten = gruppe.gibUpdatedInfoLand(land, tore, punkte);
            }
        }

        if(check == false){
            return null;
        }

        return daten;
    }

    public void updateSpielergebnis(String land1, int tore1, String land2, int tore2)
    {
        Integer gruppe1 = 0;
        Integer gruppe2 = 0;
        int punkte1 = 0;
        int punkte2 = 0;

        if(tore1 > tore2){punkte1 = 3;}
        if(tore1 < tore2){punkte2 = 3;}
        if(tore1 == tore2){punkte1 = 1; punkte2 = 1;}

        for (int i = 0; i < gruppen.size(); i++) {
            Gruppe gruppe = gruppen.get(i);
            if(gruppe.prüfeLand(land1) == true){
                gruppe1 = i;
            }
        }
        for (int i = 0; i < gruppen.size(); i++) {
            Gruppe gruppe = gruppen.get(i);
            if(gruppe.prüfeLand(land2) == true){
                gruppe2 = i;
            }
        }

        if(gruppe1 == gruppe2){
            if(gibDatenSpielergebnis(land1, tore1, punkte1) == null){
                System.out.println("Das Land " + land1 + " existiert nicht");
            }
            else{
                try{
                    io.speichereLand(gibDatenSpielergebnis(land1, tore1, punkte1));}

                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            if(gibDatenSpielergebnis(land2, tore2, punkte2) == null){
                System.out.println("Das Land " + land2 + " existiert nicht");
            }
            else{
                try{
                    io.speichereLand(gibDatenSpielergebnis(land2, tore2, punkte2));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{System.out.println("Die Länder sind nicht in einer Gruppe!");}
    }
}
