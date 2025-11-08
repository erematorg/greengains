package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public interface Multiset<E> extends Collection<E> {

    public interface Entry<E> {
        boolean equals(@CheckForNull Object obj);

        int getCount();

        @ParametricNullness
        E getElement();

        int hashCode();

        String toString();
    }

    @CanIgnoreReturnValue
    int add(@ParametricNullness E e3, int i3);

    @CanIgnoreReturnValue
    boolean add(@ParametricNullness E e3);

    boolean contains(@CheckForNull Object obj);

    boolean containsAll(Collection<?> collection);

    int count(@CompatibleWith("E") @CheckForNull Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(@CheckForNull Object obj);

    int hashCode();

    Iterator<E> iterator();

    @CanIgnoreReturnValue
    int remove(@CompatibleWith("E") @CheckForNull Object obj, int i3);

    @CanIgnoreReturnValue
    boolean remove(@CheckForNull Object obj);

    @CanIgnoreReturnValue
    boolean removeAll(Collection<?> collection);

    @CanIgnoreReturnValue
    boolean retainAll(Collection<?> collection);

    @CanIgnoreReturnValue
    int setCount(@ParametricNullness E e3, int i3);

    @CanIgnoreReturnValue
    boolean setCount(@ParametricNullness E e3, int i3, int i4);

    int size();

    String toString();
}
