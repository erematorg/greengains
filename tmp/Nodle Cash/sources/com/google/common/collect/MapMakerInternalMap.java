package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap.InternalEntry;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.CheckForNull;

@GwtIncompatible
@J2ktIncompatible
class MapMakerInternalMap<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final WeakValueReference<Object, Object, DummyInternalEntry> UNSET_WEAK_VALUE_REFERENCE = new WeakValueReference<Object, Object, DummyInternalEntry>() {
        public void clear() {
        }

        public WeakValueReference<Object, Object, DummyInternalEntry> copyFor(ReferenceQueue<Object> referenceQueue, DummyInternalEntry dummyInternalEntry) {
            return this;
        }

        @CheckForNull
        public Object get() {
            return null;
        }

        @CheckForNull
        public DummyInternalEntry getEntry() {
            return null;
        }
    };
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;
    final transient InternalEntryHelper<K, V, E, S> entryHelper;
    @CheckForNull
    @LazyInit
    transient Set<Map.Entry<K, V>> entrySet;
    final Equivalence<Object> keyEquivalence;
    @CheckForNull
    @LazyInit
    transient Set<K> keySet;
    final transient int segmentMask;
    final transient int segmentShift;
    final transient Segment<K, V, E, S>[] segments;
    @CheckForNull
    @LazyInit
    transient Collection<V> values;

    public static abstract class AbstractStrongKeyEntry<K, V, E extends InternalEntry<K, V, E>> implements InternalEntry<K, V, E> {
        final int hash;
        final K key;

        public AbstractStrongKeyEntry(K k2, int i3) {
            this.key = k2;
            this.hash = i3;
        }

        public final int getHash() {
            return this.hash;
        }

        public final K getKey() {
            return this.key;
        }

        @CheckForNull
        public E getNext() {
            return null;
        }
    }

    public static abstract class AbstractWeakKeyEntry<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<K> implements InternalEntry<K, V, E> {
        final int hash;

        public AbstractWeakKeyEntry(ReferenceQueue<K> referenceQueue, K k2, int i3) {
            super(k2, referenceQueue);
            this.hash = i3;
        }

        public final int getHash() {
            return this.hash;
        }

        public final K getKey() {
            return get();
        }

        @CheckForNull
        public E getNext() {
            return null;
        }
    }

    public static final class CleanupMapTask implements Runnable {
        final WeakReference<MapMakerInternalMap<?, ?, ?, ?>> mapReference;

        public CleanupMapTask(MapMakerInternalMap<?, ?, ?, ?> mapMakerInternalMap) {
            this.mapReference = new WeakReference<>(mapMakerInternalMap);
        }

        public void run() {
            MapMakerInternalMap mapMakerInternalMap = this.mapReference.get();
            if (mapMakerInternalMap != null) {
                for (Segment<K, V, E, S> runCleanup : mapMakerInternalMap.segments) {
                    runCleanup.runCleanup();
                }
                return;
            }
            throw new CancellationException();
        }
    }

    public static final class DummyInternalEntry implements InternalEntry<Object, Object, DummyInternalEntry> {
        private DummyInternalEntry() {
            throw new AssertionError();
        }

        public int getHash() {
            throw new AssertionError();
        }

        public Object getKey() {
            throw new AssertionError();
        }

        public Object getValue() {
            throw new AssertionError();
        }

        public DummyInternalEntry getNext() {
            throw new AssertionError();
        }
    }

    public final class EntryIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<Map.Entry<K, V>> {
        public EntryIterator(MapMakerInternalMap mapMakerInternalMap) {
            super();
        }

        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    public final class EntrySet extends SafeToArraySet<Map.Entry<K, V>> {
        public EntrySet() {
            super();
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
            r0 = r3.this$0.get((r0 = r4.getKey()));
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean contains(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x000f
                return r1
            L_0x000f:
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r0 = r2.get(r0)
                if (r0 == 0) goto L_0x0028
                com.google.common.collect.MapMakerInternalMap r3 = com.google.common.collect.MapMakerInternalMap.this
                com.google.common.base.Equivalence r3 = r3.valueEquivalence()
                java.lang.Object r4 = r4.getValue()
                boolean r3 = r3.equivalent(r4, r0)
                if (r3 == 0) goto L_0x0028
                r1 = 1
            L_0x0028:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.contains(java.lang.Object):boolean");
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator(MapMakerInternalMap.this);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
            r3 = (java.util.Map.Entry) r3;
            r0 = r3.getKey();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x001b
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r3 = r3.getValue()
                boolean r2 = r2.remove(r0, r3)
                if (r2 == 0) goto L_0x001b
                r1 = 1
            L_0x001b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.remove(java.lang.Object):boolean");
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    public abstract class HashIterator<T> implements Iterator<T> {
        @CheckForNull
        Segment<K, V, E, S> currentSegment;
        @CheckForNull
        AtomicReferenceArray<E> currentTable;
        @CheckForNull
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry lastReturned;
        @CheckForNull
        E nextEntry;
        @CheckForNull
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        public HashIterator() {
            this.nextSegmentIndex = MapMakerInternalMap.this.segments.length - 1;
            advance();
        }

        public final void advance() {
            this.nextExternal = null;
            if (!nextInChain() && !nextInTable()) {
                while (true) {
                    int i3 = this.nextSegmentIndex;
                    if (i3 >= 0) {
                        Segment<K, V, E, S>[] segmentArr = MapMakerInternalMap.this.segments;
                        this.nextSegmentIndex = i3 - 1;
                        Segment<K, V, E, S> segment = segmentArr[i3];
                        this.currentSegment = segment;
                        if (segment.count != 0) {
                            AtomicReferenceArray<E> atomicReferenceArray = this.currentSegment.table;
                            this.currentTable = atomicReferenceArray;
                            this.nextTableIndex = atomicReferenceArray.length() - 1;
                            if (nextInTable()) {
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* JADX INFO: finally extract failed */
        public boolean advanceTo(E e3) {
            try {
                Object key = e3.getKey();
                Object liveValue = MapMakerInternalMap.this.getLiveValue(e3);
                if (liveValue != null) {
                    this.nextExternal = new WriteThroughEntry(key, liveValue);
                    this.currentSegment.postReadCleanup();
                    return true;
                }
                this.currentSegment.postReadCleanup();
                return false;
            } catch (Throwable th) {
                this.currentSegment.postReadCleanup();
                throw th;
            }
        }

        public boolean hasNext() {
            return this.nextExternal != null;
        }

        public abstract T next();

        public MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextEntry() {
            MapMakerInternalMap<K, V, E, S>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry != null) {
                this.lastReturned = writeThroughEntry;
                advance();
                return this.lastReturned;
            }
            throw new NoSuchElementException();
        }

        public boolean nextInChain() {
            E e3 = this.nextEntry;
            if (e3 == null) {
                return false;
            }
            while (true) {
                this.nextEntry = e3.getNext();
                E e4 = this.nextEntry;
                if (e4 == null) {
                    return false;
                }
                if (advanceTo(e4)) {
                    return true;
                }
                e3 = this.nextEntry;
            }
        }

        public boolean nextInTable() {
            while (true) {
                int i3 = this.nextTableIndex;
                if (i3 < 0) {
                    return false;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i3 - 1;
                E e3 = (InternalEntry) atomicReferenceArray.get(i3);
                this.nextEntry = e3;
                if (e3 != null && (advanceTo(e3) || nextInChain())) {
                    return true;
                }
            }
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.lastReturned != null);
            MapMakerInternalMap.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    public interface InternalEntry<K, V, E extends InternalEntry<K, V, E>> {
        int getHash();

        K getKey();

        E getNext();

        V getValue();
    }

    public interface InternalEntryHelper<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> {
        E copy(S s3, E e3, @CheckForNull E e4);

        Strength keyStrength();

        E newEntry(S s3, K k2, int i3, @CheckForNull E e3);

        S newSegment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i3);

        void setValue(S s3, E e3, V v2);

        Strength valueStrength();
    }

    public final class KeyIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<K> {
        public KeyIterator(MapMakerInternalMap mapMakerInternalMap) {
            super();
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    public final class KeySet extends SafeToArraySet<K> {
        public KeySet() {
            super();
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsKey(obj);
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public Iterator<K> iterator() {
            return new KeyIterator(MapMakerInternalMap.this);
        }

        public boolean remove(Object obj) {
            return MapMakerInternalMap.this.remove(obj) != null;
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    public static abstract class SafeToArraySet<E> extends AbstractSet<E> {
        private SafeToArraySet() {
        }

        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return MapMakerInternalMap.toArrayList(this).toArray(tArr);
        }
    }

    public static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        public SerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i3, ConcurrentMap<K, V> concurrentMap) {
            super(strength, strength2, equivalence, equivalence2, i3, concurrentMap);
        }

        @J2ktIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).makeMap();
            readEntries(objectInputStream);
        }

        private Object readResolve() {
            return this.delegate;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }
    }

    public enum Strength {
        STRONG {
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        WEAK {
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        public abstract Equivalence<Object> defaultEquivalence();
    }

    public static class StrongKeyDummyValueEntry<K> extends AbstractStrongKeyEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> {

        public static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            public static <K> Helper<K> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public void setValue(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public StrongKeyDummyValueEntry<K> copy(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, @CheckForNull StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry2) {
                return newEntry(strongKeyDummyValueSegment, strongKeyDummyValueEntry.key, strongKeyDummyValueEntry.hash, strongKeyDummyValueEntry2);
            }

            public StrongKeyDummyValueEntry<K> newEntry(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, K k2, int i3, @CheckForNull StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
                if (strongKeyDummyValueEntry == null) {
                    return new StrongKeyDummyValueEntry<>(k2, i3);
                }
                return new LinkedStrongKeyDummyValueEntry(k2, i3, strongKeyDummyValueEntry);
            }

            public StrongKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i3) {
                return new StrongKeyDummyValueSegment<>(mapMakerInternalMap, i3);
            }
        }

        public static final class LinkedStrongKeyDummyValueEntry<K> extends StrongKeyDummyValueEntry<K> {
            private final StrongKeyDummyValueEntry<K> next;

            public LinkedStrongKeyDummyValueEntry(K k2, int i3, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
                super(k2, i3);
                this.next = strongKeyDummyValueEntry;
            }

            public /* bridge */ /* synthetic */ Object getValue() {
                return getValue();
            }

            public StrongKeyDummyValueEntry<K> getNext() {
                return this.next;
            }
        }

        private StrongKeyDummyValueEntry(K k2, int i3) {
            super(k2, i3);
        }

        public final MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }
    }

    public static final class StrongKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
        public StrongKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i3) {
            super(mapMakerInternalMap, i3);
        }

        public StrongKeyDummyValueSegment<K> self() {
            return this;
        }

        public StrongKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (StrongKeyDummyValueEntry) internalEntry;
        }
    }

    public static class StrongKeyStrongValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, StrongKeyStrongValueEntry<K, V>> {
        /* access modifiers changed from: private */
        @CheckForNull
        public volatile V value;

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, @CheckForNull StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry2) {
                StrongKeyStrongValueEntry<K, V> newEntry = newEntry(strongKeyStrongValueSegment, strongKeyStrongValueEntry.key, strongKeyStrongValueEntry.hash, strongKeyStrongValueEntry2);
                Object unused = newEntry.value = strongKeyStrongValueEntry.value;
                return newEntry;
            }

            public StrongKeyStrongValueEntry<K, V> newEntry(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, K k2, int i3, @CheckForNull StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
                if (strongKeyStrongValueEntry == null) {
                    return new StrongKeyStrongValueEntry<>(k2, i3);
                }
                return new LinkedStrongKeyStrongValueEntry(k2, i3, strongKeyStrongValueEntry);
            }

            public StrongKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i3) {
                return new StrongKeyStrongValueSegment<>(mapMakerInternalMap, i3);
            }

            public void setValue(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, V v2) {
                Object unused = strongKeyStrongValueEntry.value = v2;
            }
        }

        public static final class LinkedStrongKeyStrongValueEntry<K, V> extends StrongKeyStrongValueEntry<K, V> {
            private final StrongKeyStrongValueEntry<K, V> next;

            public LinkedStrongKeyStrongValueEntry(K k2, int i3, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
                super(k2, i3);
                this.next = strongKeyStrongValueEntry;
            }

            public StrongKeyStrongValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        @CheckForNull
        public final V getValue() {
            return this.value;
        }

        private StrongKeyStrongValueEntry(K k2, int i3) {
            super(k2, i3);
            this.value = null;
        }
    }

    public static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
        public StrongKeyStrongValueSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i3) {
            super(mapMakerInternalMap, i3);
        }

        public StrongKeyStrongValueSegment<K, V> self() {
            return this;
        }

        @CheckForNull
        public StrongKeyStrongValueEntry<K, V> castForTesting(@CheckForNull InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyStrongValueEntry) internalEntry;
        }
    }

    public static class StrongKeyWeakValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, StrongKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> valueReference;

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }

            @CheckForNull
            public StrongKeyWeakValueEntry<K, V> copy(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, @CheckForNull StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2) {
                if (Segment.isCollected(strongKeyWeakValueEntry)) {
                    return null;
                }
                StrongKeyWeakValueEntry<K, V> newEntry = newEntry(strongKeyWeakValueSegment, strongKeyWeakValueEntry.key, strongKeyWeakValueEntry.hash, strongKeyWeakValueEntry2);
                WeakValueReference unused = newEntry.valueReference = strongKeyWeakValueEntry.valueReference.copyFor(strongKeyWeakValueSegment.queueForValues, newEntry);
                return newEntry;
            }

            public StrongKeyWeakValueEntry<K, V> newEntry(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, K k2, int i3, @CheckForNull StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
                if (strongKeyWeakValueEntry == null) {
                    return new StrongKeyWeakValueEntry<>(k2, i3);
                }
                return new LinkedStrongKeyWeakValueEntry(k2, i3, strongKeyWeakValueEntry);
            }

            public StrongKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i3) {
                return new StrongKeyWeakValueSegment<>(mapMakerInternalMap, i3);
            }

            public void setValue(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, V v2) {
                WeakValueReference access$400 = strongKeyWeakValueEntry.valueReference;
                WeakValueReference unused = strongKeyWeakValueEntry.valueReference = new WeakValueReferenceImpl(strongKeyWeakValueSegment.queueForValues, v2, strongKeyWeakValueEntry);
                access$400.clear();
            }
        }

        public static final class LinkedStrongKeyWeakValueEntry<K, V> extends StrongKeyWeakValueEntry<K, V> {
            private final StrongKeyWeakValueEntry<K, V> next;

            public LinkedStrongKeyWeakValueEntry(K k2, int i3, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
                super(k2, i3);
                this.next = strongKeyWeakValueEntry;
            }

            public StrongKeyWeakValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        @CheckForNull
        public final V getValue() {
            return this.valueReference.get();
        }

        public final WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }

        private StrongKeyWeakValueEntry(K k2, int i3) {
            super(k2, i3);
            this.valueReference = MapMakerInternalMap.unsetWeakValueReference();
        }
    }

    public static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        public StrongKeyWeakValueSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i3) {
            super(mapMakerInternalMap, i3);
        }

        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForValues);
        }

        public void maybeDrainReferenceQueues() {
            drainValueReferenceQueue(this.queueForValues);
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v2) {
            return new WeakValueReferenceImpl(this.queueForValues, v2, castForTesting((InternalEntry) internalEntry));
        }

        public StrongKeyWeakValueSegment<K, V> self() {
            return this;
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            StrongKeyWeakValueEntry castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference access$400 = castForTesting.valueReference;
            WeakValueReference unused = castForTesting.valueReference = weakValueReference;
            access$400.clear();
        }

        @CheckForNull
        public StrongKeyWeakValueEntry<K, V> castForTesting(@CheckForNull InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyWeakValueEntry) internalEntry;
        }
    }

    public interface StrongValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
    }

    public final class ValueIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<V> {
        public ValueIterator(MapMakerInternalMap mapMakerInternalMap) {
            super();
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    public final class Values extends AbstractCollection<V> {
        public Values() {
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsValue(obj);
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public Iterator<V> iterator() {
            return new ValueIterator(MapMakerInternalMap.this);
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return MapMakerInternalMap.toArrayList(this).toArray(tArr);
        }
    }

    public static class WeakKeyDummyValueEntry<K> extends AbstractWeakKeyEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> {

        public static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            public static <K> Helper<K> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public void setValue(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            @CheckForNull
            public WeakKeyDummyValueEntry<K> copy(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, @CheckForNull WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry2) {
                K key = weakKeyDummyValueEntry.getKey();
                if (key == null) {
                    return null;
                }
                return newEntry(weakKeyDummyValueSegment, key, weakKeyDummyValueEntry.hash, weakKeyDummyValueEntry2);
            }

            public WeakKeyDummyValueEntry<K> newEntry(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, K k2, int i3, @CheckForNull WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
                if (weakKeyDummyValueEntry == null) {
                    return new WeakKeyDummyValueEntry<>(weakKeyDummyValueSegment.queueForKeys, k2, i3);
                }
                return new LinkedWeakKeyDummyValueEntry(weakKeyDummyValueSegment.queueForKeys, k2, i3, weakKeyDummyValueEntry);
            }

            public WeakKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i3) {
                return new WeakKeyDummyValueSegment<>(mapMakerInternalMap, i3);
            }
        }

        public static final class LinkedWeakKeyDummyValueEntry<K> extends WeakKeyDummyValueEntry<K> {
            private final WeakKeyDummyValueEntry<K> next;

            public /* bridge */ /* synthetic */ Object getValue() {
                return getValue();
            }

            private LinkedWeakKeyDummyValueEntry(ReferenceQueue<K> referenceQueue, K k2, int i3, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
                super(referenceQueue, k2, i3);
                this.next = weakKeyDummyValueEntry;
            }

            public WeakKeyDummyValueEntry<K> getNext() {
                return this.next;
            }
        }

        private WeakKeyDummyValueEntry(ReferenceQueue<K> referenceQueue, K k2, int i3) {
            super(referenceQueue, k2, i3);
        }

        public final MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }
    }

    public static final class WeakKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        public WeakKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i3) {
            super(mapMakerInternalMap, i3);
        }

        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }

        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        public WeakKeyDummyValueSegment<K> self() {
            return this;
        }

        public WeakKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (WeakKeyDummyValueEntry) internalEntry;
        }
    }

    public static class WeakKeyStrongValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, WeakKeyStrongValueEntry<K, V>> {
        /* access modifiers changed from: private */
        @CheckForNull
        public volatile V value;

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            @CheckForNull
            public WeakKeyStrongValueEntry<K, V> copy(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, @CheckForNull WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry2) {
                K key = weakKeyStrongValueEntry.getKey();
                if (key == null) {
                    return null;
                }
                WeakKeyStrongValueEntry<K, V> newEntry = newEntry(weakKeyStrongValueSegment, key, weakKeyStrongValueEntry.hash, weakKeyStrongValueEntry2);
                Object unused = newEntry.value = weakKeyStrongValueEntry.value;
                return newEntry;
            }

            public WeakKeyStrongValueEntry<K, V> newEntry(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, K k2, int i3, @CheckForNull WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
                if (weakKeyStrongValueEntry == null) {
                    return new WeakKeyStrongValueEntry<>(weakKeyStrongValueSegment.queueForKeys, k2, i3);
                }
                return new LinkedWeakKeyStrongValueEntry(weakKeyStrongValueSegment.queueForKeys, k2, i3, weakKeyStrongValueEntry);
            }

            public WeakKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i3) {
                return new WeakKeyStrongValueSegment<>(mapMakerInternalMap, i3);
            }

            public void setValue(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, V v2) {
                Object unused = weakKeyStrongValueEntry.value = v2;
            }
        }

        public static final class LinkedWeakKeyStrongValueEntry<K, V> extends WeakKeyStrongValueEntry<K, V> {
            private final WeakKeyStrongValueEntry<K, V> next;

            private LinkedWeakKeyStrongValueEntry(ReferenceQueue<K> referenceQueue, K k2, int i3, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
                super(referenceQueue, k2, i3);
                this.next = weakKeyStrongValueEntry;
            }

            public WeakKeyStrongValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        @CheckForNull
        public final V getValue() {
            return this.value;
        }

        private WeakKeyStrongValueEntry(ReferenceQueue<K> referenceQueue, K k2, int i3) {
            super(referenceQueue, k2, i3);
            this.value = null;
        }
    }

    public static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        public WeakKeyStrongValueSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i3) {
            super(mapMakerInternalMap, i3);
        }

        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }

        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        public WeakKeyStrongValueSegment<K, V> self() {
            return this;
        }

        public WeakKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyStrongValueEntry) internalEntry;
        }
    }

    public static class WeakKeyWeakValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, WeakKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> valueReference = MapMakerInternalMap.unsetWeakValueReference();

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }

            @CheckForNull
            public WeakKeyWeakValueEntry<K, V> copy(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, @CheckForNull WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2) {
                K key = weakKeyWeakValueEntry.getKey();
                if (key == null || Segment.isCollected(weakKeyWeakValueEntry)) {
                    return null;
                }
                WeakKeyWeakValueEntry<K, V> newEntry = newEntry(weakKeyWeakValueSegment, key, weakKeyWeakValueEntry.hash, weakKeyWeakValueEntry2);
                WeakValueReference unused = newEntry.valueReference = weakKeyWeakValueEntry.valueReference.copyFor(weakKeyWeakValueSegment.queueForValues, newEntry);
                return newEntry;
            }

            public WeakKeyWeakValueEntry<K, V> newEntry(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, K k2, int i3, @CheckForNull WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
                if (weakKeyWeakValueEntry == null) {
                    return new WeakKeyWeakValueEntry<>(weakKeyWeakValueSegment.queueForKeys, k2, i3);
                }
                return new LinkedWeakKeyWeakValueEntry(weakKeyWeakValueSegment.queueForKeys, k2, i3, weakKeyWeakValueEntry);
            }

            public WeakKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i3) {
                return new WeakKeyWeakValueSegment<>(mapMakerInternalMap, i3);
            }

            public void setValue(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, V v2) {
                WeakValueReference access$1400 = weakKeyWeakValueEntry.valueReference;
                WeakValueReference unused = weakKeyWeakValueEntry.valueReference = new WeakValueReferenceImpl(weakKeyWeakValueSegment.queueForValues, v2, weakKeyWeakValueEntry);
                access$1400.clear();
            }
        }

        public static final class LinkedWeakKeyWeakValueEntry<K, V> extends WeakKeyWeakValueEntry<K, V> {
            private final WeakKeyWeakValueEntry<K, V> next;

            public LinkedWeakKeyWeakValueEntry(ReferenceQueue<K> referenceQueue, K k2, int i3, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
                super(referenceQueue, k2, i3);
                this.next = weakKeyWeakValueEntry;
            }

            public WeakKeyWeakValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        public WeakKeyWeakValueEntry(ReferenceQueue<K> referenceQueue, K k2, int i3) {
            super(referenceQueue, k2, i3);
        }

        public final V getValue() {
            return this.valueReference.get();
        }

        public final WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }
    }

    public static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        public WeakKeyWeakValueSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i3) {
            super(mapMakerInternalMap, i3);
        }

        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }

        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
            drainValueReferenceQueue(this.queueForValues);
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v2) {
            return new WeakValueReferenceImpl(this.queueForValues, v2, castForTesting((InternalEntry) internalEntry));
        }

        public WeakKeyWeakValueSegment<K, V> self() {
            return this;
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            WeakKeyWeakValueEntry castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference access$1400 = castForTesting.valueReference;
            WeakValueReference unused = castForTesting.valueReference = weakValueReference;
            access$1400.clear();
        }

        @CheckForNull
        public WeakKeyWeakValueEntry<K, V> castForTesting(@CheckForNull InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyWeakValueEntry) internalEntry;
        }
    }

    public interface WeakValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
        WeakValueReference<K, V, E> getValueReference();
    }

    public interface WeakValueReference<K, V, E extends InternalEntry<K, V, E>> {
        void clear();

        WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e3);

        @CheckForNull
        V get();

        E getEntry();
    }

    public static final class WeakValueReferenceImpl<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<V> implements WeakValueReference<K, V, E> {
        @Weak
        final E entry;

        public WeakValueReferenceImpl(ReferenceQueue<V> referenceQueue, V v2, E e3) {
            super(v2, referenceQueue);
            this.entry = e3;
        }

        public WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e3) {
            return new WeakValueReferenceImpl(referenceQueue, get(), e3);
        }

        public E getEntry() {
            return this.entry;
        }
    }

    public final class WriteThroughEntry extends AbstractMapEntry<K, V> {
        final K key;
        V value;

        public WriteThroughEntry(K k2, V v2) {
            this.key = k2;
            this.value = v2;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode() ^ this.key.hashCode();
        }

        public V setValue(V v2) {
            V put = MapMakerInternalMap.this.put(this.key, v2);
            this.value = v2;
            return put;
        }
    }

    private MapMakerInternalMap(MapMaker mapMaker, InternalEntryHelper<K, V, E, S> internalEntryHelper) {
        this.concurrencyLevel = Math.min(mapMaker.getConcurrencyLevel(), 65536);
        this.keyEquivalence = mapMaker.getKeyEquivalence();
        this.entryHelper = internalEntryHelper;
        int min = Math.min(mapMaker.getInitialCapacity(), 1073741824);
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        int i6 = 1;
        while (i6 < this.concurrencyLevel) {
            i5++;
            i6 <<= 1;
        }
        this.segmentShift = 32 - i5;
        this.segmentMask = i6 - 1;
        this.segments = newSegmentArray(i6);
        int i7 = min / i6;
        while (i4 < (i6 * i7 < min ? i7 + 1 : i7)) {
            i4 <<= 1;
        }
        while (true) {
            Segment<K, V, E, S>[] segmentArr = this.segments;
            if (i3 < segmentArr.length) {
                segmentArr[i3] = createSegment(i4);
                i3++;
            } else {
                return;
            }
        }
    }

    public static <K, V> MapMakerInternalMap<K, V, ? extends InternalEntry<K, V, ?>, ?> create(MapMaker mapMaker) {
        Strength keyStrength = mapMaker.getKeyStrength();
        Strength strength = Strength.STRONG;
        if (keyStrength == strength && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyStrongValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == strength && mapMaker.getValueStrength() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyWeakValueEntry.Helper.instance());
        }
        Strength keyStrength2 = mapMaker.getKeyStrength();
        Strength strength2 = Strength.WEAK;
        if (keyStrength2 == strength2 && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyStrongValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == strength2 && mapMaker.getValueStrength() == strength2) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyWeakValueEntry.Helper.instance());
        }
        throw new AssertionError();
    }

    public static <K> MapMakerInternalMap<K, MapMaker.Dummy, ? extends InternalEntry<K, MapMaker.Dummy, ?>, ?> createWithDummyValues(MapMaker mapMaker) {
        Strength keyStrength = mapMaker.getKeyStrength();
        Strength strength = Strength.STRONG;
        if (keyStrength == strength && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyDummyValueEntry.Helper.instance());
        }
        Strength keyStrength2 = mapMaker.getKeyStrength();
        Strength strength2 = Strength.WEAK;
        if (keyStrength2 == strength2 && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyDummyValueEntry.Helper.instance());
        }
        if (mapMaker.getValueStrength() == strength2) {
            throw new IllegalArgumentException("Map cannot have both weak and dummy values");
        }
        throw new AssertionError();
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializationProxy");
    }

    public static int rehash(int i3) {
        int i4 = i3 + ((i3 << 15) ^ -12931);
        int i5 = i4 ^ (i4 >>> 10);
        int i6 = i5 + (i5 << 3);
        int i7 = i6 ^ (i6 >>> 6);
        int i8 = (i7 << 2) + (i7 << 14) + i7;
        return (i8 >>> 16) ^ i8;
    }

    /* access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    public static <K, V, E extends InternalEntry<K, V, E>> WeakValueReference<K, V, E> unsetWeakValueReference() {
        return UNSET_WEAK_VALUE_REFERENCE;
    }

    public void clear() {
        for (Segment<K, V, E, S> clear : this.segments) {
            clear.clear();
        }
    }

    public boolean containsKey(@CheckForNull Object obj) {
        if (obj == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).containsKey(obj, hash);
    }

    public boolean containsValue(@CheckForNull Object obj) {
        Object obj2 = obj;
        boolean z2 = false;
        if (obj2 == null) {
            return false;
        }
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j2 = -1;
        int i3 = 0;
        while (i3 < 3) {
            int length = segmentArr.length;
            long j3 = 0;
            int i4 = z2;
            while (i4 < length) {
                Segment<K, V, E, S> segment = segmentArr[i4];
                int i5 = segment.count;
                AtomicReferenceArray<E> atomicReferenceArray = segment.table;
                for (int i6 = z2; i6 < atomicReferenceArray.length(); i6++) {
                    for (InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i6); internalEntry != null; internalEntry = internalEntry.getNext()) {
                        V liveValue = segment.getLiveValue(internalEntry);
                        if (liveValue != null && valueEquivalence().equivalent(obj2, liveValue)) {
                            return true;
                        }
                    }
                }
                j3 += (long) segment.modCount;
                i4++;
                z2 = false;
            }
            if (j3 == j2) {
                return false;
            }
            i3++;
            j2 = j3;
            z2 = false;
        }
        return z2;
    }

    @VisibleForTesting
    public E copyEntry(E e3, E e4) {
        return segmentFor(e3.getHash()).copyEntry(e3, e4);
    }

    public Segment<K, V, E, S> createSegment(int i3) {
        return this.entryHelper.newSegment(this, i3);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).get(obj, hash);
    }

    @CheckForNull
    public E getEntry(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).getEntry(obj, hash);
    }

    @CheckForNull
    public V getLiveValue(E e3) {
        if (e3.getKey() == null) {
            return null;
        }
        return e3.getValue();
    }

    public int hash(Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    public boolean isEmpty() {
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j2 = 0;
        for (int i3 = 0; i3 < segmentArr.length; i3++) {
            if (segmentArr[i3].count != 0) {
                return false;
            }
            j2 += (long) segmentArr[i3].modCount;
        }
        if (j2 == 0) {
            return true;
        }
        for (int i4 = 0; i4 < segmentArr.length; i4++) {
            if (segmentArr[i4].count != 0) {
                return false;
            }
            j2 -= (long) segmentArr[i4].modCount;
        }
        return j2 == 0;
    }

    @VisibleForTesting
    public boolean isLiveForTesting(InternalEntry<K, V, ?> internalEntry) {
        return segmentFor(internalEntry.getHash()).getLiveValueForTesting(internalEntry) != null;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    @VisibleForTesting
    public Strength keyStrength() {
        return this.entryHelper.keyStrength();
    }

    public final Segment<K, V, E, S>[] newSegmentArray(int i3) {
        return new Segment[i3];
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V put(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int hash = hash(k2);
        return segmentFor(hash).put(k2, hash, v2, false);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V putIfAbsent(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int hash = hash(k2);
        return segmentFor(hash).put(k2, hash, v2, true);
    }

    public void reclaimKey(E e3) {
        int hash = e3.getHash();
        segmentFor(hash).reclaimKey(e3, hash);
    }

    public void reclaimValue(WeakValueReference<K, V, E> weakValueReference) {
        E entry = weakValueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, weakValueReference);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V remove(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).remove(obj, hash);
    }

    @CanIgnoreReturnValue
    public boolean replace(K k2, @CheckForNull V v2, V v3) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v3);
        if (v2 == null) {
            return false;
        }
        int hash = hash(k2);
        return segmentFor(hash).replace(k2, hash, v2, v3);
    }

    public Segment<K, V, E, S> segmentFor(int i3) {
        return this.segments[this.segmentMask & (i3 >>> this.segmentShift)];
    }

    public int size() {
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j2 = 0;
        for (Segment<K, V, E, S> segment : segmentArr) {
            j2 += (long) segment.count;
        }
        return Ints.saturatedCast(j2);
    }

    @VisibleForTesting
    public Equivalence<Object> valueEquivalence() {
        return this.entryHelper.valueStrength().defaultEquivalence();
    }

    @VisibleForTesting
    public Strength valueStrength() {
        return this.entryHelper.valueStrength();
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values2 = new Values();
        this.values = values2;
        return values2;
    }

    public Object writeReplace() {
        return new SerializationProxy(this.entryHelper.keyStrength(), this.entryHelper.valueStrength(), this.keyEquivalence, this.entryHelper.valueStrength().defaultEquivalence(), this.concurrencyLevel, this);
    }

    public static abstract class AbstractSerializationProxy<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap<K, V> delegate;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;

        public AbstractSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i3, ConcurrentMap<K, V> concurrentMap) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.concurrencyLevel = i3;
            this.delegate = concurrentMap;
        }

        @J2ktIncompatible
        public void readEntries(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            while (true) {
                Object readObject = objectInputStream.readObject();
                if (readObject != null) {
                    this.delegate.put(readObject, objectInputStream.readObject());
                } else {
                    return;
                }
            }
        }

        @J2ktIncompatible
        public MapMaker readMapMaker(ObjectInputStream objectInputStream) throws IOException {
            return new MapMaker().initialCapacity(objectInputStream.readInt()).setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).concurrencyLevel(this.concurrencyLevel);
        }

        public void writeMapTo(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeInt(this.delegate.size());
            for (Map.Entry next : this.delegate.entrySet()) {
                objectOutputStream.writeObject(next.getKey());
                objectOutputStream.writeObject(next.getValue());
            }
            objectOutputStream.writeObject((Object) null);
        }

        public ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }
    }

    @CanIgnoreReturnValue
    public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).remove(obj, hash, obj2);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V replace(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int hash = hash(k2);
        return segmentFor(hash).replace(k2, hash, v2);
    }

    public static abstract class Segment<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        volatile int count;
        @Weak
        final MapMakerInternalMap<K, V, E, S> map;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        @CheckForNull
        volatile AtomicReferenceArray<E> table;
        int threshold;

        public Segment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i3) {
            this.map = mapMakerInternalMap;
            initTable(newEntryArray(i3));
        }

        public static <K, V, E extends InternalEntry<K, V, E>> boolean isCollected(E e3) {
            return e3.getValue() == null;
        }

        public abstract E castForTesting(InternalEntry<K, V, ?> internalEntry);

        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    for (int i3 = 0; i3 < atomicReferenceArray.length(); i3++) {
                        atomicReferenceArray.set(i3, (Object) null);
                    }
                    maybeClearReferenceQueues();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                    unlock();
                } catch (Throwable th) {
                    unlock();
                    throw th;
                }
            }
        }

        public <T> void clearReferenceQueue(ReferenceQueue<T> referenceQueue) {
            do {
            } while (referenceQueue.poll() != null);
        }

        @CanIgnoreReturnValue
        public boolean clearValueForTesting(K k2, int i3, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else if (((WeakValueEntry) internalEntry2).getValueReference() == weakValueReference) {
                        atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                        return true;
                    } else {
                        unlock();
                        return false;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        public boolean containsKey(Object obj, int i3) {
            try {
                boolean z2 = false;
                if (this.count != 0) {
                    InternalEntry liveEntry = getLiveEntry(obj, i3);
                    if (!(liveEntry == null || liveEntry.getValue() == null)) {
                        z2 = true;
                    }
                    return z2;
                }
                postReadCleanup();
                return false;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX INFO: finally extract failed */
        @VisibleForTesting
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i3 = 0; i3 < length; i3++) {
                        for (InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i3); internalEntry != null; internalEntry = internalEntry.getNext()) {
                            Object liveValue = getLiveValue(internalEntry);
                            if (liveValue != null) {
                                if (this.map.valueEquivalence().equivalent(obj, liveValue)) {
                                    postReadCleanup();
                                    return true;
                                }
                            }
                        }
                    }
                }
                postReadCleanup();
                return false;
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        @CheckForNull
        public E copyEntry(E e3, E e4) {
            return this.map.entryHelper.copy(self(), e3, e4);
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: type inference failed for: r4v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E copyForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r3, @javax.annotation.CheckForNull com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r4) {
            /*
                r2 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r2.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r2.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r3 = r2.castForTesting(r3)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r2.castForTesting(r4)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r0.copy(r1, r3, r2)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.copyForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        @GuardedBy("this")
        public void drainKeyReferenceQueue(ReferenceQueue<K> referenceQueue) {
            int i3 = 0;
            do {
                Reference<? extends K> poll = referenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimKey((InternalEntry) poll);
                    i3++;
                } else {
                    return;
                }
            } while (i3 != 16);
        }

        @GuardedBy("this")
        public void drainValueReferenceQueue(ReferenceQueue<V> referenceQueue) {
            int i3 = 0;
            do {
                Reference<? extends V> poll = referenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimValue((WeakValueReference) poll);
                    i3++;
                } else {
                    return;
                }
            } while (i3 != 16);
        }

        @GuardedBy("this")
        public void expand() {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i3 = this.count;
                AtomicReferenceArray<E> newEntryArray = newEntryArray(length << 1);
                this.threshold = (newEntryArray.length() * 3) / 4;
                int length2 = newEntryArray.length() - 1;
                for (int i4 = 0; i4 < length; i4++) {
                    InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i4);
                    if (internalEntry != null) {
                        InternalEntry next = internalEntry.getNext();
                        int hash = internalEntry.getHash() & length2;
                        if (next == null) {
                            newEntryArray.set(hash, internalEntry);
                        } else {
                            InternalEntry internalEntry2 = internalEntry;
                            while (next != null) {
                                int hash2 = next.getHash() & length2;
                                if (hash2 != hash) {
                                    internalEntry2 = next;
                                    hash = hash2;
                                }
                                next = next.getNext();
                            }
                            newEntryArray.set(hash, internalEntry2);
                            while (internalEntry != internalEntry2) {
                                int hash3 = internalEntry.getHash() & length2;
                                InternalEntry copyEntry = copyEntry(internalEntry, (InternalEntry) newEntryArray.get(hash3));
                                if (copyEntry != null) {
                                    newEntryArray.set(hash3, copyEntry);
                                } else {
                                    i3--;
                                }
                                internalEntry = internalEntry.getNext();
                            }
                        }
                    }
                }
                this.table = newEntryArray;
                this.count = i3;
            }
        }

        /* JADX INFO: finally extract failed */
        @CheckForNull
        public V get(Object obj, int i3) {
            try {
                InternalEntry liveEntry = getLiveEntry(obj, i3);
                if (liveEntry == null) {
                    postReadCleanup();
                    return null;
                }
                V value = liveEntry.getValue();
                if (value == null) {
                    tryDrainReferenceQueues();
                }
                postReadCleanup();
                return value;
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        @CheckForNull
        public E getEntry(Object obj, int i3) {
            if (this.count == 0) {
                return null;
            }
            for (E first = getFirst(i3); first != null; first = first.getNext()) {
                if (first.getHash() == i3) {
                    Object key = first.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(obj, key)) {
                        return first;
                    }
                }
            }
            return null;
        }

        @CheckForNull
        public E getFirst(int i3) {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            return (InternalEntry) atomicReferenceArray.get(i3 & (atomicReferenceArray.length() - 1));
        }

        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            throw new AssertionError();
        }

        @CheckForNull
        public E getLiveEntry(Object obj, int i3) {
            return getEntry(obj, i3);
        }

        @CheckForNull
        public V getLiveValue(E e3) {
            if (e3.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V value = e3.getValue();
            if (value != null) {
                return value;
            }
            tryDrainReferenceQueues();
            return null;
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @javax.annotation.CheckForNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getLiveValueForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r1) {
            /*
                r0 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r0.castForTesting(r1)
                java.lang.Object r0 = r0.getLiveValue(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.getLiveValueForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry):java.lang.Object");
        }

        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            throw new AssertionError();
        }

        public WeakValueReference<K, V, E> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            throw new AssertionError();
        }

        public void initTable(AtomicReferenceArray<E> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            this.table = atomicReferenceArray;
        }

        public void maybeClearReferenceQueues() {
        }

        @GuardedBy("this")
        public void maybeDrainReferenceQueues() {
        }

        public AtomicReferenceArray<E> newEntryArray(int i3) {
            return new AtomicReferenceArray<>(i3);
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E newEntryForTesting(K r3, int r4, @javax.annotation.CheckForNull com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r5) {
            /*
                r2 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r2.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r2.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r2.castForTesting(r5)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r0.newEntry(r1, r3, r4, r2)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.newEntryForTesting(java.lang.Object, int, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        public WeakValueReference<K, V, E> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v2) {
            throw new AssertionError();
        }

        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                runCleanup();
            }
        }

        @GuardedBy("this")
        public void preWriteCleanup() {
            runLockedCleanup();
        }

        @CheckForNull
        public V put(K k2, int i3, V v2, boolean z2) {
            lock();
            try {
                preWriteCleanup();
                int i4 = this.count + 1;
                if (i4 > this.threshold) {
                    expand();
                    i4 = this.count + 1;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            this.modCount++;
                            setValue(internalEntry2, v2);
                            this.count = this.count;
                            unlock();
                            return null;
                        } else if (z2) {
                            unlock();
                            return value;
                        } else {
                            this.modCount++;
                            setValue(internalEntry2, v2);
                            unlock();
                            return value;
                        }
                    }
                }
                this.modCount++;
                E newEntry = this.map.entryHelper.newEntry(self(), k2, i3, internalEntry);
                setValue(newEntry, v2);
                atomicReferenceArray.set(length, newEntry);
                this.count = i4;
                unlock();
                return null;
            } catch (Throwable th) {
                unlock();
                throw th;
            }
        }

        @CanIgnoreReturnValue
        public boolean reclaimKey(E e3, int i3) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = i3 & (atomicReferenceArray.length() - 1);
                E e4 = (InternalEntry) atomicReferenceArray.get(length);
                for (E e5 = e4; e5 != null; e5 = e5.getNext()) {
                    if (e5 == e3) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(e4, e5));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        @CanIgnoreReturnValue
        public boolean reclaimValue(K k2, int i3, WeakValueReference<K, V, E> weakValueReference) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else if (((WeakValueEntry) internalEntry2).getValueReference() == weakValueReference) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                        this.count--;
                        return true;
                    } else {
                        unlock();
                        return false;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        @CheckForNull
        @CanIgnoreReturnValue
        public V remove(Object obj, int i3) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(obj, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            if (!isCollected(internalEntry2)) {
                                unlock();
                                return null;
                            }
                        }
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                        this.count--;
                        return value;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        @GuardedBy("this")
        public boolean removeEntryForTesting(E e3) {
            int hash = e3.getHash();
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = hash & (atomicReferenceArray.length() - 1);
            E e4 = (InternalEntry) atomicReferenceArray.get(length);
            for (E e5 = e4; e5 != null; e5 = e5.getNext()) {
                if (e5 == e3) {
                    this.modCount++;
                    atomicReferenceArray.set(length, removeFromChain(e4, e5));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        @CheckForNull
        @GuardedBy("this")
        public E removeFromChain(E e3, E e4) {
            int i3 = this.count;
            E next = e4.getNext();
            while (e3 != e4) {
                E copyEntry = copyEntry(e3, next);
                if (copyEntry != null) {
                    next = copyEntry;
                } else {
                    i3--;
                }
                e3 = e3.getNext();
            }
            this.count = i3;
            return next;
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: type inference failed for: r2v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: Unknown variable types count: 2 */
        @javax.annotation.CheckForNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E removeFromChainForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r1, com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r2) {
            /*
                r0 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r0.castForTesting(r1)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r0.castForTesting(r2)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r0 = r0.removeFromChain(r1, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.removeFromChainForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @com.google.errorprone.annotations.CanIgnoreReturnValue
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean removeTableEntryForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r1) {
            /*
                r0 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r0.castForTesting(r1)
                boolean r0 = r0.removeEntryForTesting(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.removeTableEntryForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry):boolean");
        }

        public boolean replace(K k2, int i3, V v2, V v3) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        Object value = internalEntry2.getValue();
                        if (value == null) {
                            if (isCollected(internalEntry2)) {
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                                this.count--;
                            }
                            return false;
                        } else if (this.map.valueEquivalence().equivalent(v2, value)) {
                            this.modCount++;
                            setValue(internalEntry2, v3);
                            unlock();
                            return true;
                        } else {
                            unlock();
                            return false;
                        }
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        public void runCleanup() {
            runLockedCleanup();
        }

        public void runLockedCleanup() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        public abstract S self();

        public void setTableEntryForTesting(int i3, InternalEntry<K, V, ?> internalEntry) {
            this.table.set(i3, castForTesting(internalEntry));
        }

        public void setValue(E e3, V v2) {
            this.map.entryHelper.setValue(self(), e3, v2);
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setValueForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r3, V r4) {
            /*
                r2 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r2.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r2.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r2.castForTesting(r3)
                r0.setValue(r1, r2, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.setValueForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, java.lang.Object):void");
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            throw new AssertionError();
        }

        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        public boolean remove(Object obj, int i3, Object obj2) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (true) {
                    boolean z2 = false;
                    if (internalEntry2 != null) {
                        Object key = internalEntry2.getKey();
                        if (internalEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(obj, key)) {
                            internalEntry2 = internalEntry2.getNext();
                        } else {
                            if (this.map.valueEquivalence().equivalent(obj2, internalEntry2.getValue())) {
                                z2 = true;
                            } else if (!isCollected(internalEntry2)) {
                                unlock();
                                return false;
                            }
                            this.modCount++;
                            atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                            this.count--;
                            return z2;
                        }
                    } else {
                        unlock();
                        return false;
                    }
                }
            } finally {
                unlock();
            }
        }

        @CheckForNull
        public V replace(K k2, int i3, V v2) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            if (isCollected(internalEntry2)) {
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                                this.count--;
                            }
                            return null;
                        }
                        this.modCount++;
                        setValue(internalEntry2, v2);
                        unlock();
                        return value;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }
    }
}
