package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class Comparators {
    private Comparators() {
    }

    public static <T> boolean isInOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T> boolean isInStrictOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (comparator.compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T, S extends T> Comparator<Iterable<S>> lexicographical(Comparator<T> comparator) {
        return new LexicographicalOrdering((Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <T extends Comparable<? super T>> T max(T t2, T t3) {
        return t2.compareTo(t3) >= 0 ? t2 : t3;
    }

    public static <T extends Comparable<? super T>> T min(T t2, T t3) {
        return t2.compareTo(t3) <= 0 ? t2 : t3;
    }

    @ParametricNullness
    public static <T> T max(@ParametricNullness T t2, @ParametricNullness T t3, Comparator<T> comparator) {
        return comparator.compare(t2, t3) >= 0 ? t2 : t3;
    }

    @ParametricNullness
    public static <T> T min(@ParametricNullness T t2, @ParametricNullness T t3, Comparator<T> comparator) {
        return comparator.compare(t2, t3) <= 0 ? t2 : t3;
    }
}
