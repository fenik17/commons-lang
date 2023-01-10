package org.apache.commons.lang3;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class ToIntBenchmark {
    public static int toIntOld(final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static int toIntNew(final String str, final int defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    @Benchmark
    public int toIntOld_emptyString() {
        return toIntOld("", 1);
    }

    @Benchmark
    public int toIntNew_emptyString() {
        return toIntNew("", 1);
    }

    @Benchmark
    public int toIntOld_number() {
        return toIntOld("123", 1);
    }

    @Benchmark
    public int toIntNew_number() {
        return toIntNew("123", 1);
    }

    @Benchmark
    public int toIntOld_notNumber() {
        return toIntOld("abc", 1);
    }

    @Benchmark
    public int toIntNew_notNumber() {
        return toIntNew("abc", 1);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ToIntBenchmark.class.getSimpleName())
                .warmupIterations(3)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
