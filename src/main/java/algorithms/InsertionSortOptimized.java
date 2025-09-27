package algorithms;

import metrics.PerformanceTracker;
import java.util.Objects;

public final class InsertionSortOptimized {
    private InsertionSortOptimized() {}

    public static void sort(int[] a) { sort(a, null); }

    public static void sort(int[] a, PerformanceTracker t) {
        Objects.requireNonNull(a, "array is null");
        final int n = a.length;
        if (n <= 1) return;

        // Sentinel: move minimum to index 0
        int minIdx = 0;
        for (int i = 1; i < n; i++) {
            if (t != null) t.comparisons++;
            if (a[i] < a[minIdx]) minIdx = i;
        }
        if (minIdx != 0) {
            int tmp = a[0];
            a[0] = a[minIdx];
            a[minIdx] = tmp;
            if (t != null) { t.arrayAccesses += 4; t.moves += 3; }
        }

        // Early check: already sorted?
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (t != null) { t.comparisons++; t.arrayAccesses += 2; }
            if (a[i-1] > a[i]) { sorted = false; break; }
        }
        if (sorted) return;

        // Main loop with binary insertion + block move
        for (int i = 2; i < n; i++) {
            int key = a[i];
            if (t != null) t.arrayAccesses++;

            // If already in order, continue
            if (t != null) { t.comparisons++; t.arrayAccesses += 2; }
            if (a[i-1] <= key) continue;

            int pos = binarySearchForInsertion(a, 0, i, key, t);

            int len = i - pos;
            System.arraycopy(a, pos, a, pos + 1, len);
            if (t != null) { t.moves += len; t.arrayAccesses += 2 * len; }

            a[pos] = key;
            if (t != null) { t.arrayAccesses++; t.moves++; }
        }
    }

    private static int binarySearchForInsertion(int[] a, int lo, int hi, int key, PerformanceTracker t) {
        int l = lo, r = hi;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (t != null) { t.comparisons++; t.arrayAccesses++; }
            if (a[m] <= key) l = m + 1;
            else r = m;
        }
        return l;
    }
}
