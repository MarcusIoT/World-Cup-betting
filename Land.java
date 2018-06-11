/**

 */
public class Land
{   
    private String name;
    private int tore;
    private int punkte;
    /**
     * Konstruktor
     */
    public Land(String name, int tore, int punkte)
    {
        setWerte(name, tore, punkte);       
    }

    /**
     * 
     */
    public void setWerte (String name, int tore, int punkte)
    {
        this.name = name;
        this.tore = tore;
        this.punkte = punkte;
    }

    /**
     *
     */
    public String getName ()
    {
        return name;
    }

    /**
     *
     */
    public String gibInfo ()
    {
        return name + "/" + String.valueOf(tore) + "/" + String.valueOf(punkte);
    }

    public String gibUpdatedInfo (int tore, int punkte)
    {
        this.tore = tore;
        this.punkte = punkte;
        return name + "/" + String.valueOf(tore) + "/" + String.valueOf(punkte);
    }
    
}
