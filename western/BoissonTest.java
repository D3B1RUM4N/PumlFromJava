package western;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoissonTest
{
    @Test
    void of()
    {
        Boisson eau1 = Boisson.of("eau", Genre.FEMININ);
        Boisson eau2 = Boisson.of("eau", Genre.FEMININ);
        assertTrue(eau1 == eau2);
    }

    @Test
    void ofEau()
    {
        Boisson eau2 = Boisson.of("eau", Genre.FEMININ);
        assertTrue(Boisson.EAU == eau2);
    }
}