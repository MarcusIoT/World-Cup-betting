/**
 * Die Klasse Land beinhaltet alle Informationen, die dem Land zugehörig sind.
 * Objekte der Klasse Land sind dann später in der HashMap Länder der Klasse Gruppe gespeichert.
 * 
 * 
 * @author Marcus Schoch; HfG; IoT3
 * @version 24.06.2018
 */

public class Land
{   
    private String name;
    private int tore;
    private int punkte;

    /**
     * Kostruktor für Objekte der Klasse Land.
     * Wird ein Objekt der Klasse Land erzeugt, muss ihm der Name, die Tore und die Punkte übergeben werden.
     * Wichtig ist es eben auch die Tore und Punkte zu übergeben, um bei Neustart des Programmes direkt aus
     * den Ausgelesenen Daten der Klasse IO alle Länder mit aktuellen Werten zu erzeugen.
     * 
     * @param name  Name als String
     * @param tore  Tore als Integer
     * @param punkte    Punkte als Integer
     */
    public Land(String name, int tore, int punkte)
    {
        setWerte(name, tore, punkte);       
    }

    /**
     * Setzt die Werte der Klasse gleich den übergebenen Werten der Methode. 
     * 
     * @param name  Name als String
     * @param tore  Tore als Integer
     * @param punkte    Punkte als Integer
     */
    private void setWerte (String name, int tore, int punkte)
    {
        this.name = name;
        this.tore = tore;
        this.punkte = punkte;
    }

    /**
     * Zieht übergebene Tore und Punkte von den existenten ab.
     * 
     * @param tore  Tore als Integer
     * @param punkte    Punkte als Integer
     */
    public void subtrahiereWerte (int tore, int punkte)
    {
        this.tore -= tore;
        this.punkte -= punkte;
    }

    /**
     * Gibt den Namen Zurück.
     * 
     * @return Name des Landes
     */
    public String getName ()
    {
        return name;
    }

    /**
     *  Gibt alle Daten dieser Klasse als Array zurück wenn aufgerufen.
     *  
     *  @return Daten aller Werte des Landes in einem Array
     */
    public String[] gibInfo ()
    {
        String[] daten = {name, String.valueOf(tore), String.valueOf(punkte)};
        return daten;
    }

    /**
     *  Aktualisiert erst alle Daten. D.H. addiert übergebene Tore und Punkte zu den bereits existierenden.
     *  Danach werden wieder alle Werte als Array zurück gegeben.
     *  
     *  @param tore  Tore als Integer
     *  @param punkte    Punkte als Integer
     *  
     *  @return Daten aller Werte des Landes in einem Array
     */
    public String[] gibUpdatedInfo (int tore, int punkte)
    {
        this.tore += tore;
        this.punkte += punkte;
        String[] daten = {name, String.valueOf(this.tore), String.valueOf(this.punkte)};
        return daten;
    }
}
