
/**
 * 
 */
public class Main
{

    private Auslesen lesen;

    /**
     * Konstruktor
     */
    public Main()
    {
        lesen = new Auslesen();
    }

    /**
     * 
     */
    public void test()
    {
        System.out.println();
        try{
            System.out.println(lesen.test());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
