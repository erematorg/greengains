package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@GwtCompatible(serializable = true)
@ElementTypesAreNonnullByDefault
final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Ordering<? super T> forwardOrder;

    public ReverseOrdering(Ordering<? super T> ordering) {
        this.forwardOrder = (Ordering) Preconditions.checkNotNull(ordering);
    }

    public int compare(@ParametricNullness T t2, @ParametricNullness T t3) {
        return this.forwardOrder.compare(t3, t2);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReverseOrdering) {
            return this.forwardOrder.equals(((ReverseOrdering) obj).forwardOrder);
        }
        return false;
    }

    public int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    public <E extends T> E max(@ParametricNullness E e3, @ParametricNullness E e4) {
        return this.forwardOrder.min(e3, e4);
    }

    public <E extends T> E min(@ParametricNullness E e3, @ParametricNullness E e4) {
        return this.forwardOrder.max(e3, e4);
    }

    public <S extends T> Ordering<S> reverse() {
        return this.forwardOrder;
    }

    public String toString() {
        return this.forwardOrder + ".reverse()";
    }

    public <E extends T> E max(@ParametricNullness E e3, @ParametricNullness E e4, @ParametricNullness E e5, E... eArr) {
        return this.forwardOrder.min(e3, e4, e5, eArr);
    }

    public <E extends T> E min(@ParametricNullness E e3, @ParametricNullness E e4, @ParametricNullness E e5, E... eArr) {
        return this.forwardOrder.max(e3, e4, e5, eArr);
    }

    public <E extends T> E max(Iterator<E> it) {
        return this.forwardOrder.min(it);
    }

    public <E extends T> E min(Iterator<E> it) {
        return this.forwardOrder.max(it);
    }

    public <E extends T> E max(Iterable<E> iterable) {
        return this.forwardOrder.min(iterable);
    }

    public <E extends T> E min(Iterable<E> iterable) {
        return this.forwardOrder.max(iterable);
    }
}
