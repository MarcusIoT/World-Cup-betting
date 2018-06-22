/**
 * Die Klasse Land beinhaltet alle Informationen, die dem Land zugehörig sind.
 * Objekte der Klasse Land sind dann später in der HashMap Länder der Klasse Gruppe gespeichert.
 * 
 * 
 * @author Marcus Schoch
 * @version 17.06.2018
 */

public class Land
{   
    private String name;
    private int tore;
    private int punkte;

    /**
     * Wird ein Objekt der Klasse Land erzeugt, muss ihm der Name, die Tore und die Punkte übergeben werden.
     * Wichtig ist es eben auch die Tore und Punkte zu übergeben, um bei Neustart des Programmes direkt aus
     * den Ausgelesenen Daten der Klasse IO alle Länder mit aktuellen Werten zu erzeugen.
     */
    public Land(String name, int tore, int punkte)
    {
        setWerte(name, tore, punkte);       
    }

    /**
     * Setzt die Werte der Klasse gleich den übergebenen Werten der Methode. 
     */
    private void setWerte (String name, int tore, int punkte)
    {
        this.name = name;
        this.tore = tore;
        this.punkte = punkte;
    }

    public void subtrahiereWerte (int tore, int punkte)
    {
        this.tore -= tore;
        this.punkte -= punkte;
    }

    /**
     * Gibt den Namen Zurück.
     */
    public String getName ()
    {
        return name;
    }

    /**
     *  Gibt alle Daten dieser Klasse als Array zurück wenn aufgerufen.
     */
    public String[] gibInfo ()
    {
        String[] daten = {name, String.valueOf(tore), String.valueOf(punkte)};
        return daten;
    }

    /**
     *  Aktualisiert erst alle Daten. D.H. addiert übergebene Tore und Punkte zu den bereits existierenden.
     *  Danach werden wieder alle Werte als Array zurück gegeben.
     */
    public String[] gibUpdatedInfo (int tore, int punkte)
    {
        this.tore += tore;
        this.punkte += punkte;
        String[] daten = {name, String.valueOf(this.tore), String.valueOf(this.punkte)};
        return daten;
    }

}
