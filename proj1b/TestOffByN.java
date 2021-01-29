import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        OffByN comp = new OffByN(5);
        assertTrue(comp.equalChars('a', 'f'));
        assertTrue(comp.equalChars('f', 'a'));

        assertFalse(comp.equalChars('f', 'h'));
    }
}
