package com.appsamurai.storyly.exoplayer2.common.util;

import androidx.camera.core.impl.i;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public final class PriorityTaskManager {
    private int highestPriority = Integer.MIN_VALUE;
    private final Object lock = new Object();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());

    public static class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int i3, int i4) {
            super(i.a(i3, i4, "Priority too low [priority=", ", highest=", "]"));
        }
    }

    public void add(int i3) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i3));
            this.highestPriority = Math.max(this.highestPriority, i3);
        }
    }

    public void proceed(int i3) throws InterruptedException {
        synchronized (this.lock) {
            while (this.highestPriority != i3) {
                try {
                    this.lock.wait();
                } finally {
                }
            }
        }
    }

    public boolean proceedNonBlocking(int i3) {
        boolean z2;
        synchronized (this.lock) {
            z2 = this.highestPriority == i3;
        }
        return z2;
    }

    public void proceedOrThrow(int i3) throws PriorityTooLowException {
        synchronized (this.lock) {
            try {
                if (this.highestPriority != i3) {
                    throw new PriorityTooLowException(i3, this.highestPriority);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void remove(int i3) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i3));
            this.highestPriority = this.queue.isEmpty() ? Integer.MIN_VALUE : ((Integer) Util.castNonNull(this.queue.peek())).intValue();
            this.lock.notifyAll();
        }
    }
}
