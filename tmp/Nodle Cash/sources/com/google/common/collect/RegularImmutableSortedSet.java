package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    static final RegularImmutableSortedSet<Comparable> NATURAL_EMPTY_SET = new RegularImmutableSortedSet<>(ImmutableList.of(), Ordering.natural());
    @VisibleForTesting
    final transient ImmutableList<E> elements;

    public RegularImmutableSortedSet(ImmutableList<E> immutableList, Comparator<? super E> comparator) {
        super(comparator);
        this.elements = immutableList;
    }

    private int unsafeBinarySearch(Object obj) throws ClassCastException {
        return Collections.binarySearch(this.elements, obj, unsafeComparator());
    }

    public ImmutableList<E> asList() {
        return this.elements;
    }

    @CheckForNull
    public E ceiling(E e3) {
        int tailIndex = tailIndex(e3, true);
        if (tailIndex == size()) {
            return null;
        }
        return this.elements.get(tailIndex);
    }

    public boolean contains(@CheckForNull Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return unsafeBinarySearch(obj) >= 0;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean containsAll(Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if (!SortedIterables.hasSameComparator(comparator(), collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        UnmodifiableIterator it = iterator();
        Iterator<?> it2 = collection.iterator();
        if (!it.hasNext()) {
            return false;
        }
        Object next = it2.next();
        Object next2 = it.next();
        while (true) {
            try {
                int unsafeCompare = unsafeCompare(next2, next);
                if (unsafeCompare < 0) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    next2 = it.next();
                } else if (unsafeCompare == 0) {
                    if (!it2.hasNext()) {
                        return true;
                    }
                    next = it2.next();
                } else if (unsafeCompare > 0) {
                    break;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int copyIntoArray(Object[] objArr, int i3) {
        return this.elements.copyIntoArray(objArr, i3);
    }

    public ImmutableSortedSet<E> createDescendingSet() {
        Comparator<? super E> reverseOrder = Collections.reverseOrder(this.comparator);
        return isEmpty() ? ImmutableSortedSet.emptySet(reverseOrder) : new RegularImmutableSortedSet(this.elements.reverse(), reverseOrder);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034 A[Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@javax.annotation.CheckForNull java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof java.util.Set
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            java.util.Set r6 = (java.util.Set) r6
            int r1 = r5.size()
            int r3 = r6.size()
            if (r1 == r3) goto L_0x0017
            return r2
        L_0x0017:
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x001e
            return r0
        L_0x001e:
            java.util.Comparator<? super E> r1 = r5.comparator
            boolean r1 = com.google.common.collect.SortedIterables.hasSameComparator(r1, r6)
            if (r1 == 0) goto L_0x0047
            java.util.Iterator r6 = r6.iterator()
            com.google.common.collect.UnmodifiableIterator r1 = r5.iterator()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
        L_0x002e:
            boolean r3 = r1.hasNext()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            if (r3 == 0) goto L_0x0045
            java.lang.Object r3 = r1.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            java.lang.Object r4 = r6.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            if (r4 == 0) goto L_0x0044
            int r3 = r5.unsafeCompare(r3, r4)     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            if (r3 == 0) goto L_0x002e
        L_0x0044:
            return r2
        L_0x0045:
            return r0
        L_0x0046:
            return r2
        L_0x0047:
            boolean r5 = r5.containsAll(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableSortedSet.equals(java.lang.Object):boolean");
    }

    public E first() {
        if (!isEmpty()) {
            return this.elements.get(0);
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public E floor(E e3) {
        int headIndex = headIndex(e3, true) - 1;
        if (headIndex == -1) {
            return null;
        }
        return this.elements.get(headIndex);
    }

    public RegularImmutableSortedSet<E> getSubSet(int i3, int i4) {
        return (i3 == 0 && i4 == size()) ? this : i3 < i4 ? new RegularImmutableSortedSet<>(this.elements.subList(i3, i4), this.comparator) : ImmutableSortedSet.emptySet(this.comparator);
    }

    public int headIndex(E e3, boolean z2) {
        int binarySearch = Collections.binarySearch(this.elements, Preconditions.checkNotNull(e3), comparator());
        return binarySearch >= 0 ? z2 ? binarySearch + 1 : binarySearch : ~binarySearch;
    }

    public ImmutableSortedSet<E> headSetImpl(E e3, boolean z2) {
        return getSubSet(0, headIndex(e3, z2));
    }

    @CheckForNull
    public E higher(E e3) {
        int tailIndex = tailIndex(e3, false);
        if (tailIndex == size()) {
            return null;
        }
        return this.elements.get(tailIndex);
    }

    public int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        try {
            int binarySearch = Collections.binarySearch(this.elements, obj, unsafeComparator());
            if (binarySearch >= 0) {
                return binarySearch;
            }
            return -1;
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @CheckForNull
    public Object[] internalArray() {
        return this.elements.internalArray();
    }

    public int internalArrayEnd() {
        return this.elements.internalArrayEnd();
    }

    public int internalArrayStart() {
        return this.elements.internalArrayStart();
    }

    public boolean isPartialView() {
        return this.elements.isPartialView();
    }

    public E last() {
        if (!isEmpty()) {
            return this.elements.get(size() - 1);
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public E lower(E e3) {
        int headIndex = headIndex(e3, false) - 1;
        if (headIndex == -1) {
            return null;
        }
        return this.elements.get(headIndex);
    }

    public int size() {
        return this.elements.size();
    }

    public ImmutableSortedSet<E> subSetImpl(E e3, boolean z2, E e4, boolean z3) {
        return tailSetImpl(e3, z2).headSetImpl(e4, z3);
    }

    public int tailIndex(E e3, boolean z2) {
        int binarySearch = Collections.binarySearch(this.elements, Preconditions.checkNotNull(e3), comparator());
        return binarySearch >= 0 ? z2 ? binarySearch : binarySearch + 1 : ~binarySearch;
    }

    public ImmutableSortedSet<E> tailSetImpl(E e3, boolean z2) {
        return getSubSet(tailIndex(e3, z2), size());
    }

    public Comparator<Object> unsafeComparator() {
        return this.comparator;
    }

    @GwtIncompatible
    public UnmodifiableIterator<E> descendingIterator() {
        return this.elements.reverse().iterator();
    }

    public UnmodifiableIterator<E> iterator() {
        return this.elements.iterator();
    }
}
