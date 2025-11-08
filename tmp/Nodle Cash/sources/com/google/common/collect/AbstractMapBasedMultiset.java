package com.google.common.collect;

import androidx.collection.SieveCacheKt;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    @J2ktIncompatible
    private static final long serialVersionUID = 0;
    transient ObjectCountHashMap<E> backingMap;
    transient long size;

    public abstract class Itr<T> implements Iterator<T> {
        int entryIndex;
        int expectedModCount;
        int toRemove = -1;

        public Itr() {
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.firstIndex();
            this.expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        }

        private void checkForConcurrentModification() {
            if (AbstractMapBasedMultiset.this.backingMap.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasNext() {
            checkForConcurrentModification();
            return this.entryIndex >= 0;
        }

        @ParametricNullness
        public T next() {
            if (hasNext()) {
                T result = result(this.entryIndex);
                int i3 = this.entryIndex;
                this.toRemove = i3;
                this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndex(i3);
                return result;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.toRemove != -1);
            AbstractMapBasedMultiset abstractMapBasedMultiset = AbstractMapBasedMultiset.this;
            abstractMapBasedMultiset.size -= (long) abstractMapBasedMultiset.backingMap.removeEntry(this.toRemove);
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndexAfterRemove(this.entryIndex, this.toRemove);
            this.toRemove = -1;
            this.expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        }

        @ParametricNullness
        public abstract T result(int i3);
    }

    public AbstractMapBasedMultiset(int i3) {
        this.backingMap = newBackingMap(i3);
    }

    @GwtIncompatible
    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readCount = Serialization.readCount(objectInputStream);
        this.backingMap = newBackingMap(3);
        Serialization.populateMultiset(this, objectInputStream, readCount);
    }

    @GwtIncompatible
    @J2ktIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMultiset(this, objectOutputStream);
    }

    @CanIgnoreReturnValue
    public final int add(@ParametricNullness E e3, int i3) {
        if (i3 == 0) {
            return count(e3);
        }
        boolean z2 = true;
        Preconditions.checkArgument(i3 > 0, "occurrences cannot be negative: %s", i3);
        int indexOf = this.backingMap.indexOf(e3);
        if (indexOf == -1) {
            this.backingMap.put(e3, i3);
            this.size += (long) i3;
            return 0;
        }
        int value = this.backingMap.getValue(indexOf);
        long j2 = (long) i3;
        long j3 = ((long) value) + j2;
        if (j3 > SieveCacheKt.NodeLinkMask) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "too many occurrences: %s", j3);
        this.backingMap.setValue(indexOf, (int) j3);
        this.size += j2;
        return value;
    }

    public void addTo(Multiset<? super E> multiset) {
        Preconditions.checkNotNull(multiset);
        int firstIndex = this.backingMap.firstIndex();
        while (firstIndex >= 0) {
            multiset.add(this.backingMap.getKey(firstIndex), this.backingMap.getValue(firstIndex));
            firstIndex = this.backingMap.nextIndex(firstIndex);
        }
    }

    public final void clear() {
        this.backingMap.clear();
        this.size = 0;
    }

    public final int count(@CheckForNull Object obj) {
        return this.backingMap.get(obj);
    }

    public final int distinctElements() {
        return this.backingMap.size();
    }

    public final Iterator<E> elementIterator() {
        return new AbstractMapBasedMultiset<E>.Itr<E>() {
            @ParametricNullness
            public E result(int i3) {
                return AbstractMapBasedMultiset.this.backingMap.getKey(i3);
            }
        };
    }

    public final Iterator<Multiset.Entry<E>> entryIterator() {
        return new AbstractMapBasedMultiset<E>.Itr<Multiset.Entry<E>>() {
            public Multiset.Entry<E> result(int i3) {
                return AbstractMapBasedMultiset.this.backingMap.getEntry(i3);
            }
        };
    }

    public final Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    public abstract ObjectCountHashMap<E> newBackingMap(int i3);

    @CanIgnoreReturnValue
    public final int remove(@CheckForNull Object obj, int i3) {
        if (i3 == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i3 > 0, "occurrences cannot be negative: %s", i3);
        int indexOf = this.backingMap.indexOf(obj);
        if (indexOf == -1) {
            return 0;
        }
        int value = this.backingMap.getValue(indexOf);
        if (value > i3) {
            this.backingMap.setValue(indexOf, value - i3);
        } else {
            this.backingMap.removeEntry(indexOf);
            i3 = value;
        }
        this.size -= (long) i3;
        return value;
    }

    @CanIgnoreReturnValue
    public final int setCount(@ParametricNullness E e3, int i3) {
        CollectPreconditions.checkNonnegative(i3, "count");
        ObjectCountHashMap<E> objectCountHashMap = this.backingMap;
        int remove = i3 == 0 ? objectCountHashMap.remove(e3) : objectCountHashMap.put(e3, i3);
        this.size += (long) (i3 - remove);
        return remove;
    }

    public final int size() {
        return Ints.saturatedCast(this.size);
    }

    public final boolean setCount(@ParametricNullness E e3, int i3, int i4) {
        CollectPreconditions.checkNonnegative(i3, "oldCount");
        CollectPreconditions.checkNonnegative(i4, "newCount");
        int indexOf = this.backingMap.indexOf(e3);
        if (indexOf == -1) {
            if (i3 != 0) {
                return false;
            }
            if (i4 > 0) {
                this.backingMap.put(e3, i4);
                this.size += (long) i4;
            }
            return true;
        } else if (this.backingMap.getValue(indexOf) != i3) {
            return false;
        } else {
            if (i4 == 0) {
                this.backingMap.removeEntry(indexOf);
                this.size -= (long) i3;
            } else {
                this.backingMap.setValue(indexOf, i4);
                this.size += (long) (i4 - i3);
            }
            return true;
        }
    }
}
