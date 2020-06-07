import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    @Test
    public void TestOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('z', 'a'));
        assertFalse(offByOne.equalChars('a', 'a'));
    }
}
