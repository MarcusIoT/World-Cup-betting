import java.io.*;
/**
 * Write a description of class Auslesen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Auslesen
{


    /**
     * Constructor for objects of class Auslesen
     */
    public Auslesen()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void test() throws IOException
  {
    FileReader fr = new FileReader("test.txt");
    BufferedReader br = new BufferedReader(fr);

    String zeile = "";

    while( (zeile = br.readLine()) != null )
    {
      System.out.println(zeile);
    }

    br.close();
  }
}
