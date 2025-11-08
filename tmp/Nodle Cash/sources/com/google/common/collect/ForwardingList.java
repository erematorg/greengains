package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    public void add(int i3, @ParametricNullness E e3) {
        delegate().add(i3, e3);
    }

    @CanIgnoreReturnValue
    public boolean addAll(int i3, Collection<? extends E> collection) {
        return delegate().addAll(i3, collection);
    }

    public abstract List<E> delegate();

    public boolean equals(@CheckForNull Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @ParametricNullness
    public E get(int i3) {
        return delegate().get(i3);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    public int indexOf(@CheckForNull Object obj) {
        return delegate().indexOf(obj);
    }

    public int lastIndexOf(@CheckForNull Object obj) {
        return delegate().lastIndexOf(obj);
    }

    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public E remove(int i3) {
        return delegate().remove(i3);
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public E set(int i3, @ParametricNullness E e3) {
        return delegate().set(i3, e3);
    }

    public boolean standardAdd(@ParametricNullness E e3) {
        add(size(), e3);
        return true;
    }

    public boolean standardAddAll(int i3, Iterable<? extends E> iterable) {
        return Lists.addAllImpl(this, i3, iterable);
    }

    public boolean standardEquals(@CheckForNull Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    public int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }

    public int standardIndexOf(@CheckForNull Object obj) {
        return Lists.indexOfImpl(this, obj);
    }

    public Iterator<E> standardIterator() {
        return listIterator();
    }

    public int standardLastIndexOf(@CheckForNull Object obj) {
        return Lists.lastIndexOfImpl(this, obj);
    }

    public ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    public List<E> standardSubList(int i3, int i4) {
        return Lists.subListImpl(this, i3, i4);
    }

    public List<E> subList(int i3, int i4) {
        return delegate().subList(i3, i4);
    }

    public ListIterator<E> listIterator(int i3) {
        return delegate().listIterator(i3);
    }

    public ListIterator<E> standardListIterator(int i3) {
        return Lists.listIteratorImpl(this, i3);
    }
}
