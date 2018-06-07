import java.io.*;
/**
 * Write a description of class WriteFile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Schreiben
{     

    /**
     * Constructor for objects of class WriteFile
     */
    public Schreiben()
    {

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public  void test() throws IOException
    {
        FileWriter fw = new FileWriter("test.txt", true); // FileWriter(String fileName, boolean append) append = true -> hängt hinten an)
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("test test test");
        bw.newLine();
        bw.write("tset tset tset");

        bw.close();
    }
}
