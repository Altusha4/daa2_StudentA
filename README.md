# Assignment 2 — Pair 1 (Student A): Insertion Sort (with optimizations)

This project fulfills the **Part 1 — Individual** requirements:
- Clean Java 17 + Maven + JUnit 5
- **Baseline** and **Optimized** Insertion Sort
- **Metrics collection**: comparisons, moves, array accesses, (approx) memory usage
- **CLI** to run experiments with configurable input sizes & distributions
- **Unit tests** with edge cases + **property-based style** random tests
- **Cross-validation** against `Arrays.sort`
- **Performance tests** scaffolding (10^2..10^5) & distributions: random/sorted/reverse/nearly-sorted
- Optional **JMH** harness stub

## Run CLI
```bash
mvn -q exec:java -Dexec.mainClass=cli.BenchmarkRunner   -Dexec.args="--algo optimized --n 10000 --trials 5 --dist nearly --csv docs/results.csv"
```

Args:
- `--algo baseline|optimized`
- `--n <int>` size
- `--trials <int>` repeats
- `--dist random|sorted|reverse|nearly`
- `--csv <path>` write CSV metrics

## Run tests
```bash
mvn -q test
```

## Files
- `algorithms/InsertionSort.java` — baseline stable in-place version
- `algorithms/InsertionSortOptimized.java` — binary-insertion, sentinel, early-exit, half-exchanges
- `metrics/PerformanceTracker.java` — counters + CSV export helpers
- `util/DataGenerators.java` — input distributions
- `cli/BenchmarkRunner.java` — CLI for experiments

## Branch Strategy (create after pushing to GitHub)
- `main` — releases only (`v0.1`, `v1.0`)
- `feature/algorithm`, `feature/metrics`, `feature/testing`, `feature/cli`, `feature/optimization`
Use `scripts/setup_git.sh` to initialize branches locally (optional).

## JMH (optional)
A minimal setup is included. You can add a `bench` class later if needed:
```bash
mvn -q -DskipTests package
java -jar target/assignment2-insertion-sort-1.0.0-shaded.jar
```
