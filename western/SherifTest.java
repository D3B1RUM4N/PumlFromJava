package western;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*

class SherifTest
{
    Sherif sherif;
    HorsLaLoi horsLaLoi;

    @BeforeEach
    void setUp()
    {
        sherif = new Sherif("Pat");
        horsLaLoi = new Brigand("Butch");
    }

    @Test
    void getNom()
    {
        assertEquals("Shérif Pat", sherif.getNom());
    }

    @Test
    void getPseudo()
    {
        assertEquals("Shérif Pat", sherif.getPseudo());
    }

    @Test
    void capturer()
    {
        sherif.rechercher(horsLaLoi);
        sherif.capturer(horsLaLoi);
        assertFalse(sherif.isWanted(horsLaLoi));
        assertTrue(sherif.getWanted().isEmpty());
    }

    @Test
    void rechercher()
    {
        sherif.rechercher(horsLaLoi);
        assertTrue(sherif.isWanted(horsLaLoi));
        assertEquals(List.of(horsLaLoi), sherif.getWanted());
    }

    @Test
    void getWanted()
    {
        assertTrue(sherif.getWanted().isEmpty());
    }

    @Test
    void isWanted()
    {
        assertFalse(sherif.isWanted(horsLaLoi));
    }

    @Test
    void getWanted2()
    {
        try
        {
            List<HorsLaLoi> wanted = (ArrayList<HorsLaLoi>) sherif.getWanted();
            wanted.add(horsLaLoi);
            assertFalse(sherif.isWanted(horsLaLoi));
            assertTrue(sherif.getWanted().isEmpty());
        }
        catch (ClassCastException exception)
        {
        }
    }
}
*/
