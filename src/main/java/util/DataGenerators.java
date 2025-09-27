package util;

import java.util.Random;
import java.util.Arrays;

public final class DataGenerators {
    private DataGenerators() {}

    public enum Dist { RANDOM, SORTED, REVERSE, NEARLY }

    public static int[] generate(int n, Dist dist, long seed) {
        Random rnd = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1_000_000);

        switch (dist) {
            case SORTED -> Arrays.sort(a);
            case REVERSE -> {
                Arrays.sort(a);
                reverse(a);
            }
            case NEARLY -> {
                Arrays.sort(a);
                int swaps = Math.max(1, n / 100); // 1% swaps
                for (int i = 0; i < swaps; i++) {
                    int i1 = rnd.nextInt(n);
                    int i2 = Math.min(n-1, Math.max(0, i1 + rnd.nextInt(5) - 2));
                    int tmp = a[i1]; a[i1] = a[i2]; a[i2] = tmp;
                }
            }
            case RANDOM -> { }
        }
        return a;
    }

    private static void reverse(int[] a) {
        for (int i = 0, j = a.length-1; i < j; i++, j--) {
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        }
    }
}
