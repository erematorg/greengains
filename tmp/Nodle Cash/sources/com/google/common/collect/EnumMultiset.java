package com.google.common.collect;

import androidx.collection.SieveCacheKt;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class EnumMultiset<E extends Enum<E>> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public transient int[] counts;
    private transient int distinctElements;
    /* access modifiers changed from: private */
    public transient E[] enumConstants;
    private transient long size;
    private transient Class<E> type;

    public abstract class Itr<T> implements Iterator<T> {
        int index = 0;
        int toRemove = -1;

        public Itr() {
        }

        public boolean hasNext() {
            while (this.index < EnumMultiset.this.enumConstants.length) {
                int[] access$100 = EnumMultiset.this.counts;
                int i3 = this.index;
                if (access$100[i3] > 0) {
                    return true;
                }
                this.index = i3 + 1;
            }
            return false;
        }

        public T next() {
            if (hasNext()) {
                T output = output(this.index);
                int i3 = this.index;
                this.toRemove = i3;
                this.index = i3 + 1;
                return output;
            }
            throw new NoSuchElementException();
        }

        public abstract T output(int i3);

        public void remove() {
            CollectPreconditions.checkRemove(this.toRemove >= 0);
            if (EnumMultiset.this.counts[this.toRemove] > 0) {
                EnumMultiset.access$210(EnumMultiset.this);
                EnumMultiset enumMultiset = EnumMultiset.this;
                EnumMultiset.access$322(enumMultiset, (long) enumMultiset.counts[this.toRemove]);
                EnumMultiset.this.counts[this.toRemove] = 0;
            }
            this.toRemove = -1;
        }
    }

    private EnumMultiset(Class<E> cls) {
        this.type = cls;
        Preconditions.checkArgument(cls.isEnum());
        E[] eArr = (Enum[]) cls.getEnumConstants();
        this.enumConstants = eArr;
        this.counts = new int[eArr.length];
    }

    public static /* synthetic */ int access$210(EnumMultiset enumMultiset) {
        int i3 = enumMultiset.distinctElements;
        enumMultiset.distinctElements = i3 - 1;
        return i3;
    }

    public static /* synthetic */ long access$322(EnumMultiset enumMultiset, long j2) {
        long j3 = enumMultiset.size - j2;
        enumMultiset.size = j3;
        return j3;
    }

    private void checkIsE(Object obj) {
        Preconditions.checkNotNull(obj);
        if (!isActuallyE(obj)) {
            throw new ClassCastException("Expected an " + this.type + " but got " + obj);
        }
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> cls) {
        return new EnumMultiset<>(cls);
    }

    private boolean isActuallyE(@CheckForNull Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        E e3 = (Enum) obj;
        int ordinal = e3.ordinal();
        E[] eArr = this.enumConstants;
        return ordinal < eArr.length && eArr[ordinal] == e3;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Object readObject = objectInputStream.readObject();
        Objects.requireNonNull(readObject);
        Class<E> cls = (Class) readObject;
        this.type = cls;
        E[] eArr = (Enum[]) cls.getEnumConstants();
        this.enumConstants = eArr;
        this.counts = new int[eArr.length];
        Serialization.populateMultiset(this, objectInputStream);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.type);
        Serialization.writeMultiset(this, objectOutputStream);
    }

    public void clear() {
        Arrays.fill(this.counts, 0);
        this.size = 0;
        this.distinctElements = 0;
    }

    public /* bridge */ /* synthetic */ boolean contains(@CheckForNull Object obj) {
        return super.contains(obj);
    }

    public int count(@CheckForNull Object obj) {
        if (obj == null || !isActuallyE(obj)) {
            return 0;
        }
        return this.counts[((Enum) obj).ordinal()];
    }

    public int distinctElements() {
        return this.distinctElements;
    }

    public Iterator<E> elementIterator() {
        return new EnumMultiset<E>.Itr<E>() {
            public E output(int i3) {
                return EnumMultiset.this.enumConstants[i3];
            }
        };
    }

    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new EnumMultiset<E>.Itr<Multiset.Entry<E>>() {
            public Multiset.Entry<E> output(final int i3) {
                return new Multisets.AbstractEntry<E>() {
                    public int getCount() {
                        return EnumMultiset.this.counts[i3];
                    }

                    public E getElement() {
                        return EnumMultiset.this.enumConstants[i3];
                    }
                };
            }
        };
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @CanIgnoreReturnValue
    public int remove(@CheckForNull Object obj, int i3) {
        if (obj == null || !isActuallyE(obj)) {
            return 0;
        }
        Enum enumR = (Enum) obj;
        CollectPreconditions.checkNonnegative(i3, "occurrences");
        if (i3 == 0) {
            return count(obj);
        }
        int ordinal = enumR.ordinal();
        int[] iArr = this.counts;
        int i4 = iArr[ordinal];
        if (i4 == 0) {
            return 0;
        }
        if (i4 <= i3) {
            iArr[ordinal] = 0;
            this.distinctElements--;
            this.size -= (long) i4;
        } else {
            iArr[ordinal] = i4 - i3;
            this.size -= (long) i3;
        }
        return i4;
    }

    public int size() {
        return Ints.saturatedCast(this.size);
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable) {
        Iterator<E> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "EnumMultiset constructor passed empty Iterable");
        EnumMultiset<E> enumMultiset = new EnumMultiset<>(((Enum) it.next()).getDeclaringClass());
        Iterables.addAll(enumMultiset, iterable);
        return enumMultiset;
    }

    @CanIgnoreReturnValue
    public int add(E e3, int i3) {
        checkIsE(e3);
        CollectPreconditions.checkNonnegative(i3, "occurrences");
        if (i3 == 0) {
            return count(e3);
        }
        int ordinal = e3.ordinal();
        int i4 = this.counts[ordinal];
        long j2 = (long) i3;
        long j3 = ((long) i4) + j2;
        Preconditions.checkArgument(j3 <= SieveCacheKt.NodeLinkMask, "too many occurrences: %s", j3);
        this.counts[ordinal] = (int) j3;
        if (i4 == 0) {
            this.distinctElements++;
        }
        this.size += j2;
        return i4;
    }

    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean setCount(@ParametricNullness Object obj, int i3, int i4) {
        return super.setCount(obj, i3, i4);
    }

    @CanIgnoreReturnValue
    public int setCount(E e3, int i3) {
        checkIsE(e3);
        CollectPreconditions.checkNonnegative(i3, "count");
        int ordinal = e3.ordinal();
        int[] iArr = this.counts;
        int i4 = iArr[ordinal];
        iArr[ordinal] = i3;
        this.size += (long) (i3 - i4);
        if (i4 == 0 && i3 > 0) {
            this.distinctElements++;
        } else if (i4 > 0 && i3 == 0) {
            this.distinctElements--;
        }
        return i4;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable, Class<E> cls) {
        EnumMultiset<E> create = create(cls);
        Iterables.addAll(create, iterable);
        return create;
    }
}
