package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class CartesianList<E> extends AbstractList<List<E>> implements RandomAccess {
    /* access modifiers changed from: private */
    public final transient ImmutableList<List<E>> axes;
    private final transient int[] axesSizeProduct;

    public CartesianList(ImmutableList<List<E>> immutableList) {
        this.axes = immutableList;
        int[] iArr = new int[(immutableList.size() + 1)];
        iArr[immutableList.size()] = 1;
        try {
            for (int size = immutableList.size() - 1; size >= 0; size--) {
                iArr[size] = IntMath.checkedMultiply(iArr[size + 1], immutableList.get(size).size());
            }
            this.axesSizeProduct = iArr;
        } catch (ArithmeticException unused) {
            throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
        }
    }

    public static <E> List<List<E>> create(List<? extends List<? extends E>> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
        for (List copyOf : list) {
            ImmutableList copyOf2 = ImmutableList.copyOf(copyOf);
            if (copyOf2.isEmpty()) {
                return ImmutableList.of();
            }
            builder.add((Object) copyOf2);
        }
        return new CartesianList(builder.build());
    }

    /* access modifiers changed from: private */
    public int getAxisIndexForProductIndex(int i3, int i4) {
        return (i3 / this.axesSizeProduct[i4 + 1]) % this.axes.get(i4).size();
    }

    public boolean contains(@CheckForNull Object obj) {
        if (!(obj instanceof List)) {
            return false;
        }
        List<Object> list = (List) obj;
        if (list.size() != this.axes.size()) {
            return false;
        }
        int i3 = 0;
        for (Object contains : list) {
            if (!this.axes.get(i3).contains(contains)) {
                return false;
            }
            i3++;
        }
        return true;
    }

    public int indexOf(@CheckForNull Object obj) {
        if (!(obj instanceof List)) {
            return -1;
        }
        List list = (List) obj;
        if (list.size() != this.axes.size()) {
            return -1;
        }
        ListIterator listIterator = list.listIterator();
        int i3 = 0;
        while (listIterator.hasNext()) {
            int nextIndex = listIterator.nextIndex();
            int indexOf = this.axes.get(nextIndex).indexOf(listIterator.next());
            if (indexOf == -1) {
                return -1;
            }
            i3 += indexOf * this.axesSizeProduct[nextIndex + 1];
        }
        return i3;
    }

    public int lastIndexOf(@CheckForNull Object obj) {
        if (!(obj instanceof List)) {
            return -1;
        }
        List list = (List) obj;
        if (list.size() != this.axes.size()) {
            return -1;
        }
        ListIterator listIterator = list.listIterator();
        int i3 = 0;
        while (listIterator.hasNext()) {
            int nextIndex = listIterator.nextIndex();
            int lastIndexOf = this.axes.get(nextIndex).lastIndexOf(listIterator.next());
            if (lastIndexOf == -1) {
                return -1;
            }
            i3 += lastIndexOf * this.axesSizeProduct[nextIndex + 1];
        }
        return i3;
    }

    public int size() {
        return this.axesSizeProduct[0];
    }

    public ImmutableList<E> get(final int i3) {
        Preconditions.checkElementIndex(i3, size());
        return new ImmutableList<E>() {
            public E get(int i3) {
                Preconditions.checkElementIndex(i3, size());
                return ((List) CartesianList.this.axes.get(i3)).get(CartesianList.this.getAxisIndexForProductIndex(i3, i3));
            }

            public boolean isPartialView() {
                return true;
            }

            public int size() {
                return CartesianList.this.axes.size();
            }
        };
    }
}
