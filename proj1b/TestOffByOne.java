import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

     */
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void equalChars(){
        char ch = 'a';
        char bh = 'b';
        char ah = 'a';

        assertTrue(offByOne.equalChars(ah, ch));
        assertFalse(offByOne.equalChars(bh, ah));

    }



}
