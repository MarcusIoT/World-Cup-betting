
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
    public void test()
    {
        System.out.println();
        try{
            System.out.println(io.lade());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
