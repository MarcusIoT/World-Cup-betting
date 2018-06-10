

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
    public void spielergebnisEingabeRichtig()
    {
        Gruppenphase gruppenp1 = new Gruppenphase();
        gruppenp1.updateSpielergebnis("Argentinien", 2, "Island", 1);
    }

    @Test
    public void spielergebnisEingabeFalsch()
    {
        Gruppenphase gruppenp1 = new Gruppenphase();
        gruppenp1.updateSpielergebnis("Schokolade", 3, "Honig", 4);
    }
}


