package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.InlineMe;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {
    private static final UnmodifiableListIterator<Object> EMPTY_ITR = new Itr(RegularImmutableList.EMPTY, 0);

    public static final class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            this(4);
        }

        @CanIgnoreReturnValue
        public Builder<E> combine(Builder<E> builder) {
            addAll(builder.contents, builder.size);
            return this;
        }

        public Builder(int i3) {
            super(i3);
        }

        public ImmutableList<E> build() {
            this.forceCopy = true;
            return ImmutableList.asImmutableList(this.contents, this.size);
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll(iterable);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E e3) {
            super.add(e3);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll(it);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add(eArr);
            return this;
        }
    }

    public static class Itr<E> extends AbstractIndexedListIterator<E> {
        private final ImmutableList<E> list;

        public Itr(ImmutableList<E> immutableList, int i3) {
            super(immutableList.size(), i3);
            this.list = immutableList;
        }

        public E get(int i3) {
            return this.list.get(i3);
        }
    }

    public static class ReverseImmutableList<E> extends ImmutableList<E> {
        private final transient ImmutableList<E> forwardList;

        public ReverseImmutableList(ImmutableList<E> immutableList) {
            this.forwardList = immutableList;
        }

        private int reverseIndex(int i3) {
            return (size() - 1) - i3;
        }

        private int reversePosition(int i3) {
            return size() - i3;
        }

        public boolean contains(@CheckForNull Object obj) {
            return this.forwardList.contains(obj);
        }

        public E get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return this.forwardList.get(reverseIndex(i3));
        }

        public int indexOf(@CheckForNull Object obj) {
            int lastIndexOf = this.forwardList.lastIndexOf(obj);
            if (lastIndexOf >= 0) {
                return reverseIndex(lastIndexOf);
            }
            return -1;
        }

        public boolean isPartialView() {
            return this.forwardList.isPartialView();
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return ImmutableList.super.iterator();
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int indexOf = this.forwardList.indexOf(obj);
            if (indexOf >= 0) {
                return reverseIndex(indexOf);
            }
            return -1;
        }

        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return ImmutableList.super.listIterator();
        }

        public ImmutableList<E> reverse() {
            return this.forwardList;
        }

        public int size() {
            return this.forwardList.size();
        }

        public /* bridge */ /* synthetic */ ListIterator listIterator(int i3) {
            return ImmutableList.super.listIterator(i3);
        }

        public ImmutableList<E> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            return this.forwardList.subList(reversePosition(i4), reversePosition(i3)).reverse();
        }
    }

    @J2ktIncompatible
    public static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        public Object readResolve() {
            return ImmutableList.copyOf((E[]) this.elements);
        }
    }

    public class SubList extends ImmutableList<E> {
        final transient int length;
        final transient int offset;

        public SubList(int i3, int i4) {
            this.offset = i3;
            this.length = i4;
        }

        public E get(int i3) {
            Preconditions.checkElementIndex(i3, this.length);
            return ImmutableList.this.get(i3 + this.offset);
        }

        @CheckForNull
        public Object[] internalArray() {
            return ImmutableList.this.internalArray();
        }

        public int internalArrayEnd() {
            return ImmutableList.this.internalArrayStart() + this.offset + this.length;
        }

        public int internalArrayStart() {
            return ImmutableList.this.internalArrayStart() + this.offset;
        }

        public boolean isPartialView() {
            return true;
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return ImmutableList.super.iterator();
        }

        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return ImmutableList.super.listIterator();
        }

        public int size() {
            return this.length;
        }

        public /* bridge */ /* synthetic */ ListIterator listIterator(int i3) {
            return ImmutableList.super.listIterator(i3);
        }

        public ImmutableList<E> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, this.length);
            ImmutableList immutableList = ImmutableList.this;
            int i5 = this.offset;
            return immutableList.subList(i3 + i5, i4 + i5);
        }
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] objArr) {
        return asImmutableList(objArr, objArr.length);
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    public static <E> Builder<E> builderWithExpectedSize(int i3) {
        CollectPreconditions.checkNonnegative(i3, "expectedSize");
        return new Builder<>(i3);
    }

    private static <E> ImmutableList<E> construct(Object... objArr) {
        return asImmutableList(ObjectArrays.checkElementsNotNull(objArr));
    }

    public static <E> ImmutableList<E> copyOf(Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    public static <E> ImmutableList<E> of() {
        return RegularImmutableList.EMPTY;
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static <E extends Comparable<? super E>> ImmutableList<E> sortedCopyOf(Iterable<? extends E> iterable) {
        Comparable[] comparableArr = (Comparable[]) Iterables.toArray(iterable, (T[]) new Comparable[0]);
        ObjectArrays.checkElementsNotNull(comparableArr);
        Arrays.sort(comparableArr);
        return asImmutableList(comparableArr);
    }

    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void add(int i3, E e3) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final boolean addAll(int i3, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @InlineMe(replacement = "this")
    @Deprecated
    public final ImmutableList<E> asList() {
        return this;
    }

    public boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    public int copyIntoArray(Object[] objArr, int i3) {
        int size = size();
        for (int i4 = 0; i4 < size; i4++) {
            objArr[i3 + i4] = get(i4);
        }
        return i3 + size;
    }

    public boolean equals(@CheckForNull Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    public int hashCode() {
        int size = size();
        int i3 = 1;
        for (int i4 = 0; i4 < size; i4++) {
            i3 = ~(~(get(i4).hashCode() + (i3 * 31)));
        }
        return i3;
    }

    public int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        return Lists.indexOfImpl(this, obj);
    }

    public int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        return Lists.lastIndexOfImpl(this, obj);
    }

    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final E remove(int i3) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> reverse() {
        return size() <= 1 ? this : new ReverseImmutableList(this);
    }

    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final E set(int i3, E e3) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> subListUnchecked(int i3, int i4) {
        return new SubList(i3, i4 - i3);
    }

    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] objArr, int i3) {
        if (i3 == 0) {
            return of();
        }
        return new RegularImmutableList(objArr, i3);
    }

    public static <E> ImmutableList<E> of(E e3) {
        return construct(e3);
    }

    public UnmodifiableIterator<E> iterator() {
        return listIterator();
    }

    public ImmutableList<E> subList(int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i4, size());
        int i5 = i4 - i3;
        if (i5 == size()) {
            return this;
        }
        if (i5 == 0) {
            return of();
        }
        return subListUnchecked(i3, i4);
    }

    public static <E> ImmutableList<E> of(E e3, E e4) {
        return construct(e3, e4);
    }

    public UnmodifiableListIterator<E> listIterator() {
        return listIterator(0);
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5) {
        return construct(e3, e4, e5);
    }

    public UnmodifiableListIterator<E> listIterator(int i3) {
        Preconditions.checkPositionIndex(i3, size());
        if (isEmpty()) {
            return EMPTY_ITR;
        }
        return new Itr(this, i3);
    }

    public static <E> ImmutableList<E> copyOf(Collection<? extends E> collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return construct(collection.toArray());
        }
        ImmutableList<E> asList = ((ImmutableCollection) collection).asList();
        return asList.isPartialView() ? asImmutableList(asList.toArray()) : asList;
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6) {
        return construct(e3, e4, e5, e6);
    }

    public static <E> ImmutableList<E> sortedCopyOf(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(comparator);
        Object[] array = Iterables.toArray(iterable);
        ObjectArrays.checkElementsNotNull(array);
        Arrays.sort(array, comparator);
        return asImmutableList(array);
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7) {
        return construct(e3, e4, e5, e6, e7);
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7, E e8) {
        return construct(e3, e4, e5, e6, e7, e8);
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        return construct(e3, e4, e5, e6, e7, e8, e9);
    }

    public static <E> ImmutableList<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return of();
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return of(next);
        }
        return new Builder().add((Object) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return construct(e3, e4, e5, e6, e7, e8, e9, e10);
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10, E e11) {
        return construct(e3, e4, e5, e6, e7, e8, e9, e10, e11);
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10, E e11, E e12) {
        return construct(e3, e4, e5, e6, e7, e8, e9, e10, e11, e12);
    }

    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10, E e11, E e12, E e13) {
        return construct(e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13);
    }

    @SafeVarargs
    public static <E> ImmutableList<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10, E e11, E e12, E e13, E e14, E... eArr) {
        E[] eArr2 = eArr;
        Preconditions.checkArgument(eArr2.length <= 2147483635, "the total number of elements must fit in an int");
        Object[] objArr = new Object[(eArr2.length + 12)];
        objArr[0] = e3;
        objArr[1] = e4;
        objArr[2] = e5;
        objArr[3] = e6;
        objArr[4] = e7;
        objArr[5] = e8;
        objArr[6] = e9;
        objArr[7] = e10;
        objArr[8] = e11;
        objArr[9] = e12;
        objArr[10] = e13;
        objArr[11] = e14;
        System.arraycopy(eArr2, 0, objArr, 12, eArr2.length);
        return construct(objArr);
    }

    public static <E> ImmutableList<E> copyOf(E[] eArr) {
        if (eArr.length == 0) {
            return of();
        }
        return construct((Object[]) eArr.clone());
    }
}
