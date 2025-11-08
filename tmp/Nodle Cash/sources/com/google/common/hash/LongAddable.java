package com.google.common.hash;

@ElementTypesAreNonnullByDefault
interface LongAddable {
    void add(long j2);

    void increment();

    long sum();
}
