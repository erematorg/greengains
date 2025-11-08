package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import java.util.Queue;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingQueue<E> extends ForwardingCollection<E> implements Queue<E> {
    public abstract Queue<E> delegate();

    @ParametricNullness
    public E element() {
        return delegate().element();
    }

    @CanIgnoreReturnValue
    public boolean offer(@ParametricNullness E e3) {
        return delegate().offer(e3);
    }

    @CheckForNull
    public E peek() {
        return delegate().peek();
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public E poll() {
        return delegate().poll();
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public E remove() {
        return delegate().remove();
    }

    public boolean standardOffer(@ParametricNullness E e3) {
        try {
            return add(e3);
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    @CheckForNull
    public E standardPeek() {
        try {
            return element();
        } catch (NoSuchElementException unused) {
            return null;
        }
    }

    @CheckForNull
    public E standardPoll() {
        try {
            return remove();
        } catch (NoSuchElementException unused) {
            return null;
        }
    }
}
