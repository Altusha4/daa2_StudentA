package metrics;

public class PerformanceTracker {
    public long comparisons = 0;
    public long moves = 0;
    public long arrayAccesses = 0;
    public long memBeforeBytes = 0;
    public long memAfterBytes = 0;
    public long timeNs = 0;

    public void reset() {
        comparisons = moves = arrayAccesses = 0;
        memBeforeBytes = memAfterBytes = 0;
        timeNs = 0;
    }

    public String csvHeader() {
        return "algo,n,dist,trial,timeNs,comparisons,moves,arrayAccesses,memBeforeBytes,memAfterBytes";
    }

    public String csvRow(String algo, int n, String dist, int trial) {
        return String.format("%s,%d,%s,%d,%d,%d,%d,%d,%d",
                algo, n, dist, trial, timeNs, comparisons, moves, arrayAccesses, memBeforeBytes, memAfterBytes);
    }
}
