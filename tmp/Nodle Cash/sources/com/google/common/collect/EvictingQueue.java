package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class EvictingQueue<E> extends ForwardingQueue<E> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Queue<E> delegate;
    @VisibleForTesting
    final int maxSize;

    private EvictingQueue(int i3) {
        Preconditions.checkArgument(i3 >= 0, "maxSize (%s) must >= 0", i3);
        this.delegate = new ArrayDeque(i3);
        this.maxSize = i3;
    }

    public static <E> EvictingQueue<E> create(int i3) {
        return new EvictingQueue<>(i3);
    }

    @CanIgnoreReturnValue
    public boolean add(E e3) {
        Preconditions.checkNotNull(e3);
        if (this.maxSize == 0) {
            return true;
        }
        if (size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(e3);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size < this.maxSize) {
            return standardAddAll(collection);
        }
        clear();
        return Iterables.addAll(this, Iterables.skip(collection, size - this.maxSize));
    }

    @CanIgnoreReturnValue
    public boolean offer(E e3) {
        return add(e3);
    }

    public int remainingCapacity() {
        return this.maxSize - size();
    }

    @J2ktIncompatible
    public Object[] toArray() {
        return super.toArray();
    }

    public Queue<E> delegate() {
        return this.delegate;
    }
}
