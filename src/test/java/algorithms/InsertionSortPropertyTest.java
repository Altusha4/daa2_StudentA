package algorithms;

import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class InsertionSortPropertyTest {

    @Test
    void randomSmallArrays_propertyBased() {
        Random rnd = new Random(123);
        for (int t = 0; t < 200; t++) {
            int n = rnd.nextInt(30); // small for quick run
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1000);
            int[] expected = Arrays.copyOf(a, n);
            Arrays.sort(expected);
            InsertionSort.sort(a);
            assertArrayEquals(expected, a, "Mismatch on trial " + t);
        }
    }
}
