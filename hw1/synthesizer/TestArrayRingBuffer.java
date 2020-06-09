package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Character> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.fillCount() == 0);
        arb.enqueue('a');
        arb.enqueue('b');
        arb.enqueue('c');
//        arb.dequeue();
//        arb.dequeue();
//        arb.dequeue();
//        assertTrue(arb.fillCount() == 10);
//        assertTrue(arb.isFull() == true);

        for (Character item : arb) {
            System.out.println(item);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
