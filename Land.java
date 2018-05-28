
/**

 */
public class Land
{
    
    private String name;
    private int tore;
    private int punkte;
    private String gruppe;

    /**
     * Konstruktor
     */
    public Land(String name, int tore, int punkte, String gruppe)
    {
        setWerte(name, tore, punkte, gruppe);
    }
    
    /**
     * 
     */
    public void setWerte (String name, int tore, int punkte, String gruppe)
    {
        this.name = name;
        this.tore = tore;
        this.punkte = punkte;
        this.gruppe = gruppe;
    }


    /**
     *
     */
    public String getName ()
    {
       return name;
    }
}
