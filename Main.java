
/**
 * 
 */
public class Main
{

    private IO io;

    /**
     * Konstruktor
     */
    public Main()
    {
        io = new IO();
    }

    /**
     * 
     */
    public void gebeEineDateiAus()
    {
        System.out.println();
        try{
            System.out.println(io.ladeGruppe("A"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
