package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;

public class TestClock implements Clock {
    private final AtomicLong timestamp;

    public TestClock(long j2) {
        this.timestamp = new AtomicLong(j2);
    }

    public void advance(long j2) {
        if (j2 >= 0) {
            this.timestamp.addAndGet(j2);
            return;
        }
        throw new IllegalArgumentException("cannot advance time backwards.");
    }

    public long getTime() {
        return this.timestamp.get();
    }

    public void tick() {
        advance(1);
    }
}
