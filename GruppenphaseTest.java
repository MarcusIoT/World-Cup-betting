

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GruppenphaseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GruppenphaseTest
{
    /**
     * Default constructor for test class GruppenphaseTest
     */
    public GruppenphaseTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void spielergebnisEingabe()
    {
        Gruppenphase gruppenp1 = new Gruppenphase();
        gruppenp1.updateSpielergebnis("Argentinien", 2, "Island", 1);
    }

     @Test
    public void spielergebnisEingabeRück()
    {
        Gruppenphase gruppenp1 = new Gruppenphase();
        gruppenp1.updateSpielergebnis("Island", 2, "Argentinien", 1);
    }
}




