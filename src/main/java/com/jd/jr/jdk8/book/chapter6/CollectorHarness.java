package com.jd.jr.jdk8.book.chapter6;

import java.util.function.Consumer;

/**
 * User: 吴海旭
 * Date: 2016-11-22
 * Time: 下午3:08
 */
public class CollectorHarness {

    public static void main(String[] args) {
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimes) + " msecs");
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimesWithCustomCollectors) + " msecs" );
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimesWithInlineCollector) + " msecs" );
    }

    private static long execute(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
            System.out.println("done in " + duration);
        }
        return fastest;
    }
}
