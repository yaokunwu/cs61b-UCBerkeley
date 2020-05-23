import org.junit.Test;
import static org.junit.Assert.*;
public class FlikTest {
    @Test
    public void testeq(){
        int q=128;
        int p=128;
        assertTrue(Flik.isSameNumber(q,p));
    }

}