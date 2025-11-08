package com.google.common.util.concurrent;

import com.google.common.annotations.J2ktIncompatible;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
abstract class ForwardingCondition implements Condition {
    public void await() throws InterruptedException {
        delegate().await();
    }

    public long awaitNanos(long j2) throws InterruptedException {
        return delegate().awaitNanos(j2);
    }

    public void awaitUninterruptibly() {
        delegate().awaitUninterruptibly();
    }

    public boolean awaitUntil(Date date) throws InterruptedException {
        return delegate().awaitUntil(date);
    }

    public abstract Condition delegate();

    public void signal() {
        delegate().signal();
    }

    public void signalAll() {
        delegate().signalAll();
    }

    public boolean await(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().await(j2, timeUnit);
    }
}
