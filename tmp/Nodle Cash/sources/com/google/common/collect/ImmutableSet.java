package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    static final int MAX_TABLE_SIZE = 1073741824;
    @RetainedWith
    @CheckForNull
    @LazyInit
    private transient ImmutableList<E> asList;

    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        private int hashCode;
        @CheckForNull
        @VisibleForTesting
        Object[] hashTable;

        public Builder() {
            super(4);
        }

        private void addDeduping(E e3) {
            Objects.requireNonNull(this.hashTable);
            int length = this.hashTable.length - 1;
            int hashCode2 = e3.hashCode();
            int smear = Hashing.smear(hashCode2);
            while (true) {
                int i3 = smear & length;
                Object[] objArr = this.hashTable;
                Object obj = objArr[i3];
                if (obj == null) {
                    objArr[i3] = e3;
                    this.hashCode += hashCode2;
                    super.add(e3);
                    return;
                } else if (!obj.equals(e3)) {
                    smear = i3 + 1;
                } else {
                    return;
                }
            }
        }

        @CanIgnoreReturnValue
        public Builder<E> combine(Builder<E> builder) {
            if (this.hashTable != null) {
                for (int i3 = 0; i3 < builder.size; i3++) {
                    Object obj = builder.contents[i3];
                    Objects.requireNonNull(obj);
                    add(obj);
                }
            } else {
                addAll(builder.contents, builder.size);
            }
            return this;
        }

        public Builder(int i3) {
            super(i3);
            this.hashTable = new Object[ImmutableSet.chooseTableSize(i3)];
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.google.common.collect.RegularImmutableSet} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.common.collect.ImmutableSet} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: com.google.common.collect.RegularImmutableSet} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.google.common.collect.RegularImmutableSet} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.common.collect.ImmutableSet<E> build() {
            /*
                r8 = this;
                int r0 = r8.size
                if (r0 == 0) goto L_0x005d
                r1 = 1
                if (r0 == r1) goto L_0x0050
                java.lang.Object[] r2 = r8.hashTable
                if (r2 == 0) goto L_0x003c
                int r0 = com.google.common.collect.ImmutableSet.chooseTableSize(r0)
                java.lang.Object[] r2 = r8.hashTable
                int r2 = r2.length
                if (r0 != r2) goto L_0x003c
                int r0 = r8.size
                java.lang.Object[] r2 = r8.contents
                int r2 = r2.length
                boolean r0 = com.google.common.collect.ImmutableSet.shouldTrim(r0, r2)
                if (r0 == 0) goto L_0x0029
                java.lang.Object[] r0 = r8.contents
                int r2 = r8.size
                java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)
            L_0x0027:
                r3 = r0
                goto L_0x002c
            L_0x0029:
                java.lang.Object[] r0 = r8.contents
                goto L_0x0027
            L_0x002c:
                com.google.common.collect.RegularImmutableSet r0 = new com.google.common.collect.RegularImmutableSet
                int r4 = r8.hashCode
                java.lang.Object[] r5 = r8.hashTable
                int r2 = r5.length
                int r6 = r2 + -1
                int r7 = r8.size
                r2 = r0
                r2.<init>(r3, r4, r5, r6, r7)
                goto L_0x004a
            L_0x003c:
                int r0 = r8.size
                java.lang.Object[] r2 = r8.contents
                com.google.common.collect.ImmutableSet r0 = com.google.common.collect.ImmutableSet.construct(r0, r2)
                int r2 = r0.size()
                r8.size = r2
            L_0x004a:
                r8.forceCopy = r1
                r1 = 0
                r8.hashTable = r1
                return r0
            L_0x0050:
                java.lang.Object[] r8 = r8.contents
                r0 = 0
                r8 = r8[r0]
                java.util.Objects.requireNonNull(r8)
                com.google.common.collect.ImmutableSet r8 = com.google.common.collect.ImmutableSet.of(r8)
                return r8
            L_0x005d:
                com.google.common.collect.ImmutableSet r8 = com.google.common.collect.ImmutableSet.of()
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableSet.Builder.build():com.google.common.collect.ImmutableSet");
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            Preconditions.checkNotNull(iterable);
            if (this.hashTable != null) {
                for (Object add : iterable) {
                    add((Object) add);
                }
            } else {
                super.addAll(iterable);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E e3) {
            Preconditions.checkNotNull(e3);
            if (this.hashTable == null || ImmutableSet.chooseTableSize(this.size) > this.hashTable.length) {
                this.hashTable = null;
                super.add(e3);
                return this;
            }
            addDeduping(e3);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            Preconditions.checkNotNull(it);
            while (it.hasNext()) {
                add((Object) it.next());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            if (this.hashTable != null) {
                for (E add : eArr) {
                    add((Object) add);
                }
            } else {
                super.add(eArr);
            }
            return this;
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
            return ImmutableSet.copyOf((E[]) this.elements);
        }
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    public static <E> Builder<E> builderWithExpectedSize(int i3) {
        CollectPreconditions.checkNonnegative(i3, "expectedSize");
        return new Builder<>(i3);
    }

    @VisibleForTesting
    public static int chooseTableSize(int i3) {
        int max = Math.max(i3, 2);
        boolean z2 = true;
        if (max < CUTOFF) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "collection too large");
        return 1073741824;
    }

    /* access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int i3, Object... objArr) {
        if (i3 == 0) {
            return of();
        }
        if (i3 != 1) {
            int chooseTableSize = chooseTableSize(i3);
            Object[] objArr2 = new Object[chooseTableSize];
            int i4 = chooseTableSize - 1;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                Object checkElementNotNull = ObjectArrays.checkElementNotNull(objArr[i7], i7);
                int hashCode = checkElementNotNull.hashCode();
                int smear = Hashing.smear(hashCode);
                while (true) {
                    int i8 = smear & i4;
                    Object obj = objArr2[i8];
                    if (obj == null) {
                        objArr[i6] = checkElementNotNull;
                        objArr2[i8] = checkElementNotNull;
                        i5 += hashCode;
                        i6++;
                        break;
                    } else if (obj.equals(checkElementNotNull)) {
                        break;
                    } else {
                        smear++;
                    }
                }
            }
            Arrays.fill(objArr, i6, i3, (Object) null);
            if (i6 == 1) {
                Object obj2 = objArr[0];
                Objects.requireNonNull(obj2);
                return new SingletonImmutableSet(obj2);
            } else if (chooseTableSize(i6) < chooseTableSize / 2) {
                return construct(i6, objArr);
            } else {
                if (shouldTrim(i6, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, i6);
                }
                return new RegularImmutableSet(objArr, i5, objArr2, i4, i6);
            }
        } else {
            Object obj3 = objArr[0];
            Objects.requireNonNull(obj3);
            return of(obj3);
        }
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: private */
    public static boolean shouldTrim(int i3, int i4) {
        return i3 < (i4 >> 1) + (i4 >> 2);
    }

    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> createAsList = createAsList();
        this.asList = createAsList;
        return createAsList;
    }

    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableSet) || !isHashCodeFast() || !((ImmutableSet) obj).isHashCodeFast() || hashCode() == obj.hashCode()) {
            return Sets.equalsImpl(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    public boolean isHashCodeFast() {
        return false;
    }

    public abstract UnmodifiableIterator<E> iterator();

    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> ImmutableSet<E> of(E e3) {
        return new SingletonImmutableSet(e3);
    }

    public static <E> ImmutableSet<E> of(E e3, E e4) {
        return construct(2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e3, E e4, E e5) {
        return construct(3, e3, e4, e5);
    }

    public static <E> ImmutableSet<E> of(E e3, E e4, E e5, E e6) {
        return construct(4, e3, e4, e5, e6);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    public static <E> ImmutableSet<E> of(E e3, E e4, E e5, E e6, E e7) {
        return construct(5, e3, e4, e5, e6, e7);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E... eArr) {
        Preconditions.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        int length = eArr.length + 6;
        Object[] objArr = new Object[length];
        objArr[0] = e3;
        objArr[1] = e4;
        objArr[2] = e5;
        objArr[3] = e6;
        objArr[4] = e7;
        objArr[5] = e8;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(length, objArr);
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return of();
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return of(next);
        }
        return new Builder().add((Object) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return of();
        }
        if (length != 1) {
            return construct(eArr.length, (Object[]) eArr.clone());
        }
        return of(eArr[0]);
    }
}
