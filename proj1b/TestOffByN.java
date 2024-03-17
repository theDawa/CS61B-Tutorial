import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);
    @Test
    public void testEqualChars(){
        char ch = 'a';
        char bh = 'f';
        char ah = 'h';
        assertTrue(offByN.equalChars(ch, bh));
        assertFalse(offByN.equalChars(bh, ah));
    }
}
