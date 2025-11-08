package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@Deprecated
@J2ktIncompatible
public abstract class ForwardingBlockingDeque<E> extends ForwardingDeque<E> implements BlockingDeque<E> {
    public abstract BlockingDeque<E> delegate();

    public int drainTo(Collection<? super E> collection) {
        return delegate().drainTo(collection);
    }

    public boolean offer(E e3, long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().offer(e3, j2, timeUnit);
    }

    public boolean offerFirst(E e3, long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().offerFirst(e3, j2, timeUnit);
    }

    public boolean offerLast(E e3, long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().offerLast(e3, j2, timeUnit);
    }

    @CheckForNull
    public E poll(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().poll(j2, timeUnit);
    }

    @CheckForNull
    public E pollFirst(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().pollFirst(j2, timeUnit);
    }

    @CheckForNull
    public E pollLast(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().pollLast(j2, timeUnit);
    }

    public void put(E e3) throws InterruptedException {
        delegate().put(e3);
    }

    public void putFirst(E e3) throws InterruptedException {
        delegate().putFirst(e3);
    }

    public void putLast(E e3) throws InterruptedException {
        delegate().putLast(e3);
    }

    public int remainingCapacity() {
        return delegate().remainingCapacity();
    }

    public E take() throws InterruptedException {
        return delegate().take();
    }

    public E takeFirst() throws InterruptedException {
        return delegate().takeFirst();
    }

    public E takeLast() throws InterruptedException {
        return delegate().takeLast();
    }

    public int drainTo(Collection<? super E> collection, int i3) {
        return delegate().drainTo(collection, i3);
    }
}
