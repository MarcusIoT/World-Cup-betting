
/**

 */
public class Land
{

    private String name;
    private int tore;
    private int gegenTore;
    private int punkte;

    /**
     * Konstruktor
     */
    public Land(String name, int tore, int gegenTore, int punkte)
    {
        setWerte(name, tore, gegenTore, punkte);
    }

    /**
     * 
     */
    public void setWerte (String name, int tore, int gegenTore, int punkte)
    {
        this.name = name;
        this.tore = tore;
        this.gegenTore = gegenTore;
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
        return name + "/" + String.valueOf(tore) + "/" + String.valueOf(gegenTore) + "/" + String.valueOf(punkte);
    }

    public String gibUpdatedInfo (int tore, int punkte)
    {
        this.tore = tore;
        this.punkte = punkte;
        return name + "/" + String.valueOf(tore) + "/" + String.valueOf(gegenTore) + "/" + String.valueOf(punkte);
    }
}
