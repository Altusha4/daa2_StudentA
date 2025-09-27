package algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class CrossValidationTest {
    @Test
    void compareWithArraysSort() {
        int[] a = {9, -1, 5, 4, 4, 100, 0, -7};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        InsertionSort.sort(a);
        assertArrayEquals(expected, a);
    }
}
