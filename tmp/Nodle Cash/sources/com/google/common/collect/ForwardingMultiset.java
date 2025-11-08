package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingMultiset<E> extends ForwardingCollection<E> implements Multiset<E> {

    public class StandardElementSet extends Multisets.ElementSet<E> {
        public StandardElementSet() {
        }

        public Iterator<E> iterator() {
            return Multisets.elementIterator(multiset().entrySet().iterator());
        }

        public Multiset<E> multiset() {
            return ForwardingMultiset.this;
        }
    }

    @CanIgnoreReturnValue
    public int add(@ParametricNullness E e3, int i3) {
        return delegate().add(e3, i3);
    }

    public int count(@CheckForNull Object obj) {
        return delegate().count(obj);
    }

    public abstract Multiset<E> delegate();

    public Set<E> elementSet() {
        return delegate().elementSet();
    }

    public Set<Multiset.Entry<E>> entrySet() {
        return delegate().entrySet();
    }

    public boolean equals(@CheckForNull Object obj) {
        return obj == this || delegate().equals(obj);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    @CanIgnoreReturnValue
    public int remove(@CheckForNull Object obj, int i3) {
        return delegate().remove(obj, i3);
    }

    @CanIgnoreReturnValue
    public int setCount(@ParametricNullness E e3, int i3) {
        return delegate().setCount(e3, i3);
    }

    public boolean standardAdd(@ParametricNullness E e3) {
        add(e3, 1);
        return true;
    }

    public boolean standardAddAll(Collection<? extends E> collection) {
        return Multisets.addAllImpl(this, collection);
    }

    public void standardClear() {
        Iterators.clear(entrySet().iterator());
    }

    public boolean standardContains(@CheckForNull Object obj) {
        return count(obj) > 0;
    }

    public int standardCount(@CheckForNull Object obj) {
        for (Multiset.Entry entry : entrySet()) {
            if (Objects.equal(entry.getElement(), obj)) {
                return entry.getCount();
            }
        }
        return 0;
    }

    public boolean standardEquals(@CheckForNull Object obj) {
        return Multisets.equalsImpl(this, obj);
    }

    public int standardHashCode() {
        return entrySet().hashCode();
    }

    public Iterator<E> standardIterator() {
        return Multisets.iteratorImpl(this);
    }

    public boolean standardRemove(@CheckForNull Object obj) {
        return remove(obj, 1) > 0;
    }

    public boolean standardRemoveAll(Collection<?> collection) {
        return Multisets.removeAllImpl(this, collection);
    }

    public boolean standardRetainAll(Collection<?> collection) {
        return Multisets.retainAllImpl(this, collection);
    }

    public int standardSetCount(@ParametricNullness E e3, int i3) {
        return Multisets.setCountImpl(this, e3, i3);
    }

    public int standardSize() {
        return Multisets.linearTimeSizeImpl(this);
    }

    public String standardToString() {
        return entrySet().toString();
    }

    @CanIgnoreReturnValue
    public boolean setCount(@ParametricNullness E e3, int i3, int i4) {
        return delegate().setCount(e3, i3, i4);
    }

    public boolean standardSetCount(@ParametricNullness E e3, int i3, int i4) {
        return Multisets.setCountImpl(this, e3, i3, i4);
    }
}
