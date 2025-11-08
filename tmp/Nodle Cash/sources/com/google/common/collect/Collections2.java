package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class Collections2 {

    public static class FilteredCollection<E> extends AbstractCollection<E> {
        final Predicate<? super E> predicate;
        final Collection<E> unfiltered;

        public FilteredCollection(Collection<E> collection, Predicate<? super E> predicate2) {
            this.unfiltered = collection;
            this.predicate = predicate2;
        }

        public boolean add(@ParametricNullness E e3) {
            Preconditions.checkArgument(this.predicate.apply(e3));
            return this.unfiltered.add(e3);
        }

        public boolean addAll(Collection<? extends E> collection) {
            for (Object apply : collection) {
                Preconditions.checkArgument(this.predicate.apply(apply));
            }
            return this.unfiltered.addAll(collection);
        }

        public void clear() {
            Iterables.removeIf(this.unfiltered, this.predicate);
        }

        public boolean contains(@CheckForNull Object obj) {
            if (Collections2.safeContains(this.unfiltered, obj)) {
                return this.predicate.apply(obj);
            }
            return false;
        }

        public boolean containsAll(Collection<?> collection) {
            return Collections2.containsAllImpl(this, collection);
        }

        public FilteredCollection<E> createCombined(Predicate<? super E> predicate2) {
            return new FilteredCollection<>(this.unfiltered, Predicates.and(this.predicate, predicate2));
        }

        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered, this.predicate);
        }

        public Iterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        public boolean remove(@CheckForNull Object obj) {
            return contains(obj) && this.unfiltered.remove(obj);
        }

        public boolean removeAll(Collection<?> collection) {
            Iterator<E> it = this.unfiltered.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.predicate.apply(next) && collection.contains(next)) {
                    it.remove();
                    z2 = true;
                }
            }
            return z2;
        }

        public boolean retainAll(Collection<?> collection) {
            Iterator<E> it = this.unfiltered.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.predicate.apply(next) && !collection.contains(next)) {
                    it.remove();
                    z2 = true;
                }
            }
            return z2;
        }

        public int size() {
            int i3 = 0;
            for (E apply : this.unfiltered) {
                if (this.predicate.apply(apply)) {
                    i3++;
                }
            }
            return i3;
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    public static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {
        final Comparator<? super E> comparator;
        final ImmutableList<E> inputList;
        final int size;

        public OrderedPermutationCollection(Iterable<E> iterable, Comparator<? super E> comparator2) {
            ImmutableList<E> sortedCopyOf = ImmutableList.sortedCopyOf(comparator2, iterable);
            this.inputList = sortedCopyOf;
            this.comparator = comparator2;
            this.size = calculateSize(sortedCopyOf, comparator2);
        }

        private static <E> int calculateSize(List<E> list, Comparator<? super E> comparator2) {
            int i3 = 1;
            int i4 = 1;
            int i5 = 1;
            while (i3 < list.size()) {
                if (comparator2.compare(list.get(i3 - 1), list.get(i3)) < 0) {
                    i4 = IntMath.saturatedMultiply(i4, IntMath.binomial(i3, i5));
                    if (i4 == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                    i5 = 0;
                }
                i3++;
                i5++;
            }
            return IntMath.saturatedMultiply(i4, IntMath.binomial(i3, i5));
        }

        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        public boolean isEmpty() {
            return false;
        }

        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.inputList, this.comparator);
        }

        public int size() {
            return this.size;
        }

        public String toString() {
            return "orderedPermutationCollection(" + this.inputList + ")";
        }
    }

    public static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        final Comparator<? super E> comparator;
        @CheckForNull
        List<E> nextPermutation;

        public OrderedPermutationIterator(List<E> list, Comparator<? super E> comparator2) {
            this.nextPermutation = Lists.newArrayList(list);
            this.comparator = comparator2;
        }

        public void calculateNextPermutation() {
            int findNextJ = findNextJ();
            if (findNextJ == -1) {
                this.nextPermutation = null;
                return;
            }
            Objects.requireNonNull(this.nextPermutation);
            Collections.swap(this.nextPermutation, findNextJ, findNextL(findNextJ));
            Collections.reverse(this.nextPermutation.subList(findNextJ + 1, this.nextPermutation.size()));
        }

        public int findNextJ() {
            Objects.requireNonNull(this.nextPermutation);
            for (int size = this.nextPermutation.size() - 2; size >= 0; size--) {
                if (this.comparator.compare(this.nextPermutation.get(size), this.nextPermutation.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }

        public int findNextL(int i3) {
            Objects.requireNonNull(this.nextPermutation);
            E e3 = this.nextPermutation.get(i3);
            for (int size = this.nextPermutation.size() - 1; size > i3; size--) {
                if (this.comparator.compare(e3, this.nextPermutation.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }

        @CheckForNull
        public List<E> computeNext() {
            List<E> list = this.nextPermutation;
            if (list == null) {
                return (List) endOfData();
            }
            ImmutableList<E> copyOf = ImmutableList.copyOf(list);
            calculateNextPermutation();
            return copyOf;
        }
    }

    public static final class PermutationCollection<E> extends AbstractCollection<List<E>> {
        final ImmutableList<E> inputList;

        public PermutationCollection(ImmutableList<E> immutableList) {
            this.inputList = immutableList;
        }

        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        public boolean isEmpty() {
            return false;
        }

        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.inputList);
        }

        public int size() {
            return IntMath.factorial(this.inputList.size());
        }

        public String toString() {
            return "permutations(" + this.inputList + ")";
        }
    }

    public static class PermutationIterator<E> extends AbstractIterator<List<E>> {

        /* renamed from: c  reason: collision with root package name */
        final int[] f6870c;

        /* renamed from: j  reason: collision with root package name */
        int f6871j = Integer.MAX_VALUE;
        final List<E> list;

        /* renamed from: o  reason: collision with root package name */
        final int[] f6872o;

        public PermutationIterator(List<E> list2) {
            this.list = new ArrayList(list2);
            int size = list2.size();
            int[] iArr = new int[size];
            this.f6870c = iArr;
            int[] iArr2 = new int[size];
            this.f6872o = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 1);
        }

        public void calculateNextPermutation() {
            int size = this.list.size() - 1;
            this.f6871j = size;
            if (size != -1) {
                int i3 = 0;
                while (true) {
                    int[] iArr = this.f6870c;
                    int i4 = this.f6871j;
                    int i5 = iArr[i4];
                    int i6 = this.f6872o[i4] + i5;
                    if (i6 < 0) {
                        switchDirection();
                    } else if (i6 != i4 + 1) {
                        Collections.swap(this.list, (i4 - i5) + i3, (i4 - i6) + i3);
                        this.f6870c[this.f6871j] = i6;
                        return;
                    } else if (i4 != 0) {
                        i3++;
                        switchDirection();
                    } else {
                        return;
                    }
                }
            }
        }

        public void switchDirection() {
            int[] iArr = this.f6872o;
            int i3 = this.f6871j;
            iArr[i3] = -iArr[i3];
            this.f6871j = i3 - 1;
        }

        @CheckForNull
        public List<E> computeNext() {
            if (this.f6871j <= 0) {
                return (List) endOfData();
            }
            ImmutableList<E> copyOf = ImmutableList.copyOf(this.list);
            calculateNextPermutation();
            return copyOf;
        }
    }

    public static class TransformedCollection<F, T> extends AbstractCollection<T> {
        final Collection<F> fromCollection;
        final Function<? super F, ? extends T> function;

        public TransformedCollection(Collection<F> collection, Function<? super F, ? extends T> function2) {
            this.fromCollection = (Collection) Preconditions.checkNotNull(collection);
            this.function = (Function) Preconditions.checkNotNull(function2);
        }

        public void clear() {
            this.fromCollection.clear();
        }

        public boolean isEmpty() {
            return this.fromCollection.isEmpty();
        }

        public Iterator<T> iterator() {
            return Iterators.transform(this.fromCollection.iterator(), this.function);
        }

        public int size() {
            return this.fromCollection.size();
        }
    }

    private Collections2() {
    }

    public static boolean containsAllImpl(Collection<?> collection, Collection<?> collection2) {
        for (Object contains : collection2) {
            if (!collection.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    private static <E> ObjectCountHashMap<E> counts(Collection<E> collection) {
        ObjectCountHashMap<E> objectCountHashMap = new ObjectCountHashMap<>();
        for (E next : collection) {
            objectCountHashMap.put(next, objectCountHashMap.get(next) + 1);
        }
        return objectCountHashMap;
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        return collection instanceof FilteredCollection ? ((FilteredCollection) collection).createCombined(predicate) : new FilteredCollection((Collection) Preconditions.checkNotNull(collection), (Predicate) Preconditions.checkNotNull(predicate));
    }

    /* access modifiers changed from: private */
    public static boolean isPermutation(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        ObjectCountHashMap<?> counts = counts(list);
        ObjectCountHashMap<?> counts2 = counts(list2);
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (counts.getValue(i3) != counts2.get(counts.getKey(i3))) {
                return false;
            }
        }
        return true;
    }

    public static StringBuilder newStringBuilderForCollection(int i3) {
        CollectPreconditions.checkNonnegative(i3, "size");
        return new StringBuilder((int) Math.min(((long) i3) * 8, 1073741824));
    }

    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return orderedPermutations(iterable, Ordering.natural());
    }

    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new PermutationCollection(ImmutableList.copyOf(collection));
    }

    public static boolean safeContains(Collection<?> collection, @CheckForNull Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static boolean safeRemove(Collection<?> collection, @CheckForNull Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static String toStringImpl(Collection<?> collection) {
        StringBuilder newStringBuilderForCollection = newStringBuilderForCollection(collection.size());
        newStringBuilderForCollection.append(AbstractJsonLexerKt.BEGIN_LIST);
        boolean z2 = true;
        for (Object next : collection) {
            if (!z2) {
                newStringBuilderForCollection.append(", ");
            }
            if (next == collection) {
                newStringBuilderForCollection.append("(this Collection)");
            } else {
                newStringBuilderForCollection.append(next);
            }
            z2 = false;
        }
        newStringBuilderForCollection.append(AbstractJsonLexerKt.END_LIST);
        return newStringBuilderForCollection.toString();
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new TransformedCollection(collection, function);
    }

    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(iterable, comparator);
    }
}
