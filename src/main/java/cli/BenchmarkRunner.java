package cli;

import algorithms.InsertionSort;
import algorithms.InsertionSortOptimized;
import metrics.PerformanceTracker;
import util.DataGenerators;
import util.DataGenerators.Dist;

import java.io.*;
import java.util.Arrays;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        String algo = "optimized";
        int n = 10000;
        int trials = 5;
        String dist = "nearly";
        String csvPath = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--algo" -> algo = args[++i];
                case "--n" -> n = Integer.parseInt(args[++i]);
                case "--trials" -> trials = Integer.parseInt(args[++i]);
                case "--dist" -> dist = args[++i];
                case "--csv" -> csvPath = args[++i];
            }
        }

        Dist d = switch (dist.toLowerCase()) {
            case "sorted" -> Dist.SORTED;
            case "reverse" -> Dist.REVERSE;
            case "nearly" -> Dist.NEARLY;
            default -> Dist.RANDOM;
        };

        PrintWriter out = null;
        PerformanceTracker t = new PerformanceTracker();
        if (csvPath != null) {
            out = new PrintWriter(new FileWriter(csvPath, true));
            if (new File(csvPath).length() == 0) {
                out.println(t.csvHeader());
            }
        }

        for (int trial = 1; trial <= trials; trial++) {
            int[] a = DataGenerators.generate(n, d, 1234L + trial);
            int[] copy = Arrays.copyOf(a, a.length);

            Runtime rt = Runtime.getRuntime();
            System.gc();
            long before = rt.totalMemory() - rt.freeMemory();
            t.memBeforeBytes = before;

            long start = System.nanoTime();
            if ("baseline".equalsIgnoreCase(algo)) {
                InsertionSort.sort(a, t);
            } else {
                InsertionSortOptimized.sort(a, t);
            }
            long end = System.nanoTime();

            long after = rt.totalMemory() - rt.freeMemory();
            t.memAfterBytes = after;
            t.timeNs = end - start;

            if (!isSorted(a)) {
                throw new AssertionError("Array not sorted for trial " + trial);
            }

            Arrays.sort(copy);
            if (!Arrays.equals(a, copy)) {
                throw new AssertionError("Cross-validation failed for trial " + trial);
            }

            if (out != null) {
                out.println(t.csvRow(algo, n, d.name().toLowerCase(), trial));
                out.flush();
            }
            t.reset();
        }

        if (out != null) out.close();
        System.out.println("Done.");
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i-1] > a[i]) return false;
        return true;
    }
}
