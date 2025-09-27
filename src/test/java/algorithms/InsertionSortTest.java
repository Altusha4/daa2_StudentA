package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class InsertionSortTest {

    @Test
    void emptyArray() {
        int[] a = {};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void singleElement() {
        int[] a = {42};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    void duplicates() {
        int[] a = {3, 1, 2, 3, 2, 3, 1};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{1,1,2,2,3,3,3}, a);
    }

    @Test
    void sortedBestCase() {
        int[] a = {1,2,3,4,5,6};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{1,2,3,4,5,6}, a);
    }

    @Test
    void reverseWorstCase() {
        int[] a = {6,5,4,3,2,1};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{1,2,3,4,5,6}, a);
    }
}
