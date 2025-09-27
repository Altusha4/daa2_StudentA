package algorithms;

import metrics.PerformanceTracker;
import java.util.Objects;

/** Baseline Insertion Sort (stable, in-place). */
public final class InsertionSort {
    private InsertionSort() {}

    public static void sort(int[] a) {
        sort(a, null);
    }

    public static void sort(int[] a, PerformanceTracker t) {
        Objects.requireNonNull(a, "array is null");
        final int n = a.length;
        if (n <= 1) return;

        for (int i = 1; i < n; i++) {
            int key = a[i];
            if (t != null) { t.arrayAccesses++; } // read a[i]
            int j = i - 1;

            // Shift elements greater than key
            while (j >= 0 && (incCompare(t) && a[j] > key)) {
                if (t != null) { t.arrayAccesses += 2; } // read a[j], write a[j+1]
                a[j + 1] = a[j];
                if (t != null) { t.moves++; }
                j--;
            }
            if (t != null) { t.arrayAccesses++; } // write a[j+1]
            a[j + 1] = key;
            if (t != null) { t.moves++; }
        }
    }

    private static boolean incCompare(PerformanceTracker t) {
        if (t != null) t.comparisons++;
        return true;
    }
}
