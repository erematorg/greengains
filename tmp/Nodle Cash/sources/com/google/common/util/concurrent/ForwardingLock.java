package com.google.common.util.concurrent;

import com.google.common.annotations.J2ktIncompatible;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
abstract class ForwardingLock implements Lock {
    public abstract Lock delegate();

    public void lock() {
        delegate().lock();
    }

    public void lockInterruptibly() throws InterruptedException {
        delegate().lockInterruptibly();
    }

    public Condition newCondition() {
        return delegate().newCondition();
    }

    public boolean tryLock() {
        return delegate().tryLock();
    }

    public void unlock() {
        delegate().unlock();
    }

    public boolean tryLock(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().tryLock(j2, timeUnit);
    }
}
