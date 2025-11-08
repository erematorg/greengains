package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final RegularImmutableMultiset<Object> EMPTY = new RegularImmutableMultiset<>(ObjectCountHashMap.create());
    final transient ObjectCountHashMap<E> contents;
    @CheckForNull
    @LazyInit
    private transient ImmutableSet<E> elementSet;
    private final transient int size;

    public final class ElementSet extends IndexedImmutableSet<E> {
        private ElementSet() {
        }

        public boolean contains(@CheckForNull Object obj) {
            return RegularImmutableMultiset.this.contains(obj);
        }

        public E get(int i3) {
            return RegularImmutableMultiset.this.contents.getKey(i3);
        }

        public boolean isPartialView() {
            return true;
        }

        public int size() {
            return RegularImmutableMultiset.this.contents.size();
        }
    }

    @GwtIncompatible
    public static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final int[] counts;
        final Object[] elements;

        public SerializedForm(Multiset<? extends Object> multiset) {
            int size = multiset.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i3 = 0;
            for (Multiset.Entry next : multiset.entrySet()) {
                this.elements[i3] = next.getElement();
                this.counts[i3] = next.getCount();
                i3++;
            }
        }

        public Object readResolve() {
            ImmutableMultiset.Builder builder = new ImmutableMultiset.Builder(this.elements.length);
            int i3 = 0;
            while (true) {
                Object[] objArr = this.elements;
                if (i3 >= objArr.length) {
                    return builder.build();
                }
                builder.addCopies(objArr[i3], this.counts[i3]);
                i3++;
            }
        }
    }

    public RegularImmutableMultiset(ObjectCountHashMap<E> objectCountHashMap) {
        this.contents = objectCountHashMap;
        long j2 = 0;
        for (int i3 = 0; i3 < objectCountHashMap.size(); i3++) {
            j2 += (long) objectCountHashMap.getValue(i3);
        }
        this.size = Ints.saturatedCast(j2);
    }

    public int count(@CheckForNull Object obj) {
        return this.contents.get(obj);
    }

    public Multiset.Entry<E> getEntry(int i3) {
        return this.contents.getEntry(i3);
    }

    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.size;
    }

    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> immutableSet = this.elementSet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ElementSet elementSet2 = new ElementSet();
        this.elementSet = elementSet2;
        return elementSet2;
    }
}
