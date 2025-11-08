package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingSortedMap<K, V> extends ForwardingMap<K, V> implements SortedMap<K, V> {

    public class StandardKeySet extends Maps.SortedKeySet<K, V> {
        public StandardKeySet(ForwardingSortedMap forwardingSortedMap) {
            super(forwardingSortedMap);
        }
    }

    public static int unsafeCompare(@CheckForNull Comparator<?> comparator, @CheckForNull Object obj, @CheckForNull Object obj2) {
        return comparator == null ? ((Comparable) obj).compareTo(obj2) : comparator.compare(obj, obj2);
    }

    @CheckForNull
    public Comparator<? super K> comparator() {
        return delegate().comparator();
    }

    public abstract SortedMap<K, V> delegate();

    @ParametricNullness
    public K firstKey() {
        return delegate().firstKey();
    }

    public SortedMap<K, V> headMap(@ParametricNullness K k2) {
        return delegate().headMap(k2);
    }

    @ParametricNullness
    public K lastKey() {
        return delegate().lastKey();
    }

    public boolean standardContainsKey(@CheckForNull Object obj) {
        try {
            return unsafeCompare(comparator(), tailMap(obj).firstKey(), obj) == 0;
        } catch (ClassCastException | NullPointerException | NoSuchElementException unused) {
            return false;
        }
    }

    public SortedMap<K, V> standardSubMap(K k2, K k3) {
        Preconditions.checkArgument(unsafeCompare(comparator(), k2, k3) <= 0, "fromKey must be <= toKey");
        return tailMap(k2).headMap(k3);
    }

    public SortedMap<K, V> subMap(@ParametricNullness K k2, @ParametricNullness K k3) {
        return delegate().subMap(k2, k3);
    }

    public SortedMap<K, V> tailMap(@ParametricNullness K k2) {
        return delegate().tailMap(k2);
    }
}
