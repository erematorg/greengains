package com.google.common.cache;

import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final Queue<?> DISCARDING_QUEUE = new AbstractQueue<Object>() {
        public Iterator<Object> iterator() {
            return ImmutableSet.of().iterator();
        }

        public boolean offer(Object obj) {
            return true;
        }

        @CheckForNull
        public Object peek() {
            return null;
        }

        @CheckForNull
        public Object poll() {
            return null;
        }

        public int size() {
            return 0;
        }
    };
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final ValueReference<Object, Object> UNSET = new ValueReference<Object, Object>() {
        public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, @CheckForNull Object obj, ReferenceEntry<Object, Object> referenceEntry) {
            return this;
        }

        @CheckForNull
        public Object get() {
            return null;
        }

        @CheckForNull
        public ReferenceEntry<Object, Object> getEntry() {
            return null;
        }

        public int getWeight() {
            return 0;
        }

        public boolean isActive() {
            return false;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(Object obj) {
        }

        @CheckForNull
        public Object waitForValue() {
            return null;
        }
    };
    static final Logger logger = Logger.getLogger(LocalCache.class.getName());
    final int concurrencyLevel;
    @CheckForNull
    final CacheLoader<? super K, V> defaultLoader;
    final EntryFactory entryFactory;
    @RetainedWith
    @CheckForNull
    @LazyInit
    Set<Map.Entry<K, V>> entrySet;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;
    final AbstractCache.StatsCounter globalStatsCounter;
    final Equivalence<Object> keyEquivalence;
    @RetainedWith
    @CheckForNull
    @LazyInit
    Set<K> keySet;
    final Strength keyStrength;
    final long maxWeight;
    final long refreshNanos;
    final RemovalListener<K, V> removalListener;
    final Queue<RemovalNotification<K, V>> removalNotificationQueue;
    final int segmentMask;
    final int segmentShift;
    final Segment<K, V>[] segments;
    final Ticker ticker;
    final Equivalence<Object> valueEquivalence;
    final Strength valueStrength;
    @RetainedWith
    @CheckForNull
    @LazyInit
    Collection<V> values;
    final Weigher<K, V> weigher;

    public abstract class AbstractCacheSet<T> extends AbstractSet<T> {
        public AbstractCacheSet() {
        }

        public void clear() {
            LocalCache.this.clear();
        }

        public boolean isEmpty() {
            return LocalCache.this.isEmpty();
        }

        public int size() {
            return LocalCache.this.size();
        }

        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        public <E> E[] toArray(E[] eArr) {
            return LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    public static abstract class AbstractReferenceEntry<K, V> implements ReferenceEntry<K, V> {
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        public int getHash() {
            throw new UnsupportedOperationException();
        }

        public K getKey() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j2) {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }

        public void setWriteTime(long j2) {
            throw new UnsupportedOperationException();
        }
    }

    public static final class AccessQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>(this) {
            @Weak
            ReferenceEntry<K, V> nextAccess = this;
            @Weak
            ReferenceEntry<K, V> previousAccess = this;

            public long getAccessTime() {
                return Long.MAX_VALUE;
            }

            public ReferenceEntry<K, V> getNextInAccessQueue() {
                return this.nextAccess;
            }

            public ReferenceEntry<K, V> getPreviousInAccessQueue() {
                return this.previousAccess;
            }

            public void setAccessTime(long j2) {
            }

            public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.nextAccess = referenceEntry;
            }

            public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.previousAccess = referenceEntry;
            }
        };

        public void clear() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (nextInAccessQueue != referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue2 = nextInAccessQueue.getNextInAccessQueue();
                    LocalCache.nullifyAccessOrder(nextInAccessQueue);
                    nextInAccessQueue = nextInAccessQueue2;
                } else {
                    referenceEntry.setNextInAccessQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousInAccessQueue(referenceEntry2);
                    return;
                }
            }
        }

        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInAccessQueue() != NullEntry.INSTANCE;
        }

        public boolean isEmpty() {
            return this.head.getNextInAccessQueue() == this.head;
        }

        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
                @CheckForNull
                public ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
                    if (nextInAccessQueue == AccessQueue.this.head) {
                        return null;
                    }
                    return nextInAccessQueue;
                }
            };
        }

        @CanIgnoreReturnValue
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry previousInAccessQueue = referenceEntry.getPreviousInAccessQueue();
            ReferenceEntry nextInAccessQueue = referenceEntry.getNextInAccessQueue();
            LocalCache.connectAccessOrder(previousInAccessQueue, nextInAccessQueue);
            LocalCache.nullifyAccessOrder(referenceEntry);
            return nextInAccessQueue != NullEntry.INSTANCE;
        }

        public int size() {
            int i3 = 0;
            for (ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue(); nextInAccessQueue != this.head; nextInAccessQueue = nextInAccessQueue.getNextInAccessQueue()) {
                i3++;
            }
            return i3;
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.connectAccessOrder(referenceEntry.getPreviousInAccessQueue(), referenceEntry.getNextInAccessQueue());
            LocalCache.connectAccessOrder(this.head.getPreviousInAccessQueue(), referenceEntry);
            LocalCache.connectAccessOrder(referenceEntry, this.head);
            return true;
        }

        @CheckForNull
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            if (nextInAccessQueue == this.head) {
                return null;
            }
            return nextInAccessQueue;
        }

        @CheckForNull
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            if (nextInAccessQueue == this.head) {
                return null;
            }
            remove(nextInAccessQueue);
            return nextInAccessQueue;
        }
    }

    public enum EntryFactory {
        STRONG {
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new StrongEntry(k2, i3, referenceEntry);
            }
        },
        STRONG_ACCESS {
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k2) {
                ReferenceEntry<K, V> copyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2, k2);
                copyAccessEntry(referenceEntry, copyEntry);
                return copyEntry;
            }

            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessEntry(k2, i3, referenceEntry);
            }
        },
        STRONG_WRITE {
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k2) {
                ReferenceEntry<K, V> copyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2, k2);
                copyWriteEntry(referenceEntry, copyEntry);
                return copyEntry;
            }

            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new StrongWriteEntry(k2, i3, referenceEntry);
            }
        },
        STRONG_ACCESS_WRITE {
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k2) {
                ReferenceEntry<K, V> copyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2, k2);
                copyAccessEntry(referenceEntry, copyEntry);
                copyWriteEntry(referenceEntry, copyEntry);
                return copyEntry;
            }

            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessWriteEntry(k2, i3, referenceEntry);
            }
        },
        WEAK {
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new WeakEntry(segment.keyReferenceQueue, k2, i3, referenceEntry);
            }
        },
        WEAK_ACCESS {
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k2) {
                ReferenceEntry<K, V> copyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2, k2);
                copyAccessEntry(referenceEntry, copyEntry);
                return copyEntry;
            }

            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessEntry(segment.keyReferenceQueue, k2, i3, referenceEntry);
            }
        },
        WEAK_WRITE {
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k2) {
                ReferenceEntry<K, V> copyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2, k2);
                copyWriteEntry(referenceEntry, copyEntry);
                return copyEntry;
            }

            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new WeakWriteEntry(segment.keyReferenceQueue, k2, i3, referenceEntry);
            }
        },
        WEAK_ACCESS_WRITE {
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k2) {
                ReferenceEntry<K, V> copyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2, k2);
                copyAccessEntry(referenceEntry, copyEntry);
                copyWriteEntry(referenceEntry, copyEntry);
                return copyEntry;
            }

            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessWriteEntry(segment.keyReferenceQueue, k2, i3, referenceEntry);
            }
        };
        
        static final int ACCESS_MASK = 1;
        static final int WEAK_MASK = 4;
        static final int WRITE_MASK = 2;
        static final EntryFactory[] factories = null;

        static {
            AnonymousClass1 r02;
            AnonymousClass2 r12;
            AnonymousClass3 r3;
            AnonymousClass4 r5;
            AnonymousClass5 r7;
            AnonymousClass6 r9;
            AnonymousClass7 r11;
            AnonymousClass8 r13;
            factories = new EntryFactory[]{r02, r12, r3, r5, r7, r9, r11, r13};
        }

        public static EntryFactory getFactory(Strength strength, boolean z2, boolean z3) {
            char c3 = 0;
            boolean z4 = (strength == Strength.WEAK ? (char) 4 : 0) | z2;
            if (z3) {
                c3 = 2;
            }
            return factories[z4 | c3];
        }

        public <K, V> void copyAccessEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setAccessTime(referenceEntry.getAccessTime());
            LocalCache.connectAccessOrder(referenceEntry.getPreviousInAccessQueue(), referenceEntry2);
            LocalCache.connectAccessOrder(referenceEntry2, referenceEntry.getNextInAccessQueue());
            LocalCache.nullifyAccessOrder(referenceEntry);
        }

        public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k2) {
            return newEntry(segment, k2, referenceEntry.getHash(), referenceEntry2);
        }

        public <K, V> void copyWriteEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setWriteTime(referenceEntry.getWriteTime());
            LocalCache.connectWriteOrder(referenceEntry.getPreviousInWriteQueue(), referenceEntry2);
            LocalCache.connectWriteOrder(referenceEntry2, referenceEntry.getNextInWriteQueue());
            LocalCache.nullifyWriteOrder(referenceEntry);
        }

        public abstract <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry);
    }

    public final class EntryIterator extends LocalCache<K, V>.HashIterator<Map.Entry<K, V>> {
        public EntryIterator(LocalCache localCache) {
            super();
        }

        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    public final class EntrySet extends LocalCache<K, V>.AbstractCacheSet<Map.Entry<K, V>> {
        public EntrySet() {
            super();
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
                com.google.common.cache.LocalCache r2 = com.google.common.cache.LocalCache.this
                java.lang.Object r0 = r2.get(r0)
                if (r0 == 0) goto L_0x0026
                com.google.common.cache.LocalCache r3 = com.google.common.cache.LocalCache.this
                com.google.common.base.Equivalence<java.lang.Object> r3 = r3.valueEquivalence
                java.lang.Object r4 = r4.getValue()
                boolean r3 = r3.equivalent(r4, r0)
                if (r3 == 0) goto L_0x0026
                r1 = 1
            L_0x0026:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.EntrySet.contains(java.lang.Object):boolean");
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator(LocalCache.this);
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
                com.google.common.cache.LocalCache r2 = com.google.common.cache.LocalCache.this
                java.lang.Object r3 = r3.getValue()
                boolean r2 = r2.remove(r0, r3)
                if (r2 == 0) goto L_0x001b
                r1 = 1
            L_0x001b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.EntrySet.remove(java.lang.Object):boolean");
        }
    }

    public abstract class HashIterator<T> implements Iterator<T> {
        @CheckForNull
        Segment<K, V> currentSegment;
        @CheckForNull
        AtomicReferenceArray<ReferenceEntry<K, V>> currentTable;
        @CheckForNull
        LocalCache<K, V>.WriteThroughEntry lastReturned;
        @CheckForNull
        ReferenceEntry<K, V> nextEntry;
        @CheckForNull
        LocalCache<K, V>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        public HashIterator() {
            this.nextSegmentIndex = LocalCache.this.segments.length - 1;
            advance();
        }

        public final void advance() {
            this.nextExternal = null;
            if (!nextInChain() && !nextInTable()) {
                while (true) {
                    int i3 = this.nextSegmentIndex;
                    if (i3 >= 0) {
                        Segment<K, V>[] segmentArr = LocalCache.this.segments;
                        this.nextSegmentIndex = i3 - 1;
                        Segment<K, V> segment = segmentArr[i3];
                        this.currentSegment = segment;
                        if (segment.count != 0) {
                            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.currentSegment.table;
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
        public boolean advanceTo(ReferenceEntry<K, V> referenceEntry) {
            try {
                long read = LocalCache.this.ticker.read();
                K key = referenceEntry.getKey();
                V liveValue = LocalCache.this.getLiveValue(referenceEntry, read);
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

        public LocalCache<K, V>.WriteThroughEntry nextEntry() {
            LocalCache<K, V>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry != null) {
                this.lastReturned = writeThroughEntry;
                advance();
                return this.lastReturned;
            }
            throw new NoSuchElementException();
        }

        public boolean nextInChain() {
            ReferenceEntry<K, V> referenceEntry = this.nextEntry;
            if (referenceEntry == null) {
                return false;
            }
            while (true) {
                this.nextEntry = referenceEntry.getNext();
                ReferenceEntry<K, V> referenceEntry2 = this.nextEntry;
                if (referenceEntry2 == null) {
                    return false;
                }
                if (advanceTo(referenceEntry2)) {
                    return true;
                }
                referenceEntry = this.nextEntry;
            }
        }

        public boolean nextInTable() {
            while (true) {
                int i3 = this.nextTableIndex;
                if (i3 < 0) {
                    return false;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i3 - 1;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(i3);
                this.nextEntry = referenceEntry;
                if (referenceEntry != null && (advanceTo(referenceEntry) || nextInChain())) {
                    return true;
                }
            }
        }

        public void remove() {
            Preconditions.checkState(this.lastReturned != null);
            LocalCache.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    public final class KeyIterator extends LocalCache<K, V>.HashIterator<K> {
        public KeyIterator(LocalCache localCache) {
            super();
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    public final class KeySet extends LocalCache<K, V>.AbstractCacheSet<K> {
        public KeySet() {
            super();
        }

        public boolean contains(Object obj) {
            return LocalCache.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new KeyIterator(LocalCache.this);
        }

        public boolean remove(Object obj) {
            return LocalCache.this.remove(obj) != null;
        }
    }

    public static final class LoadingSerializationProxy<K, V> extends ManualSerializationProxy<K, V> implements LoadingCache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        @CheckForNull
        transient LoadingCache<K, V> autoDelegate;

        public LoadingSerializationProxy(LocalCache<K, V> localCache) {
            super(localCache);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.autoDelegate = recreateCacheBuilder().build(this.loader);
        }

        private Object readResolve() {
            return this.autoDelegate;
        }

        public V apply(K k2) {
            return this.autoDelegate.apply(k2);
        }

        public V get(K k2) throws ExecutionException {
            return this.autoDelegate.get(k2);
        }

        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return this.autoDelegate.getAll(iterable);
        }

        public V getUnchecked(K k2) {
            return this.autoDelegate.getUnchecked(k2);
        }

        public void refresh(K k2) {
            this.autoDelegate.refresh(k2);
        }
    }

    public static class LoadingValueReference<K, V> implements ValueReference<K, V> {
        final SettableFuture<V> futureValue;
        volatile ValueReference<K, V> oldValue;
        final Stopwatch stopwatch;

        public LoadingValueReference() {
            this(LocalCache.unset());
        }

        private ListenableFuture<V> fullyFailedFuture(Throwable th) {
            return Futures.immediateFailedFuture(th);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Object lambda$loadFuture$0(Object obj) {
            set(obj);
            return obj;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @CheckForNull V v2, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public long elapsedNanos() {
            return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
        }

        public V get() {
            return this.oldValue.get();
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> getOldValue() {
            return this.oldValue;
        }

        public int getWeight() {
            return this.oldValue.getWeight();
        }

        public boolean isActive() {
            return this.oldValue.isActive();
        }

        public boolean isLoading() {
            return true;
        }

        public ListenableFuture<V> loadFuture(K k2, CacheLoader<? super K, V> cacheLoader) {
            try {
                this.stopwatch.start();
                V v2 = this.oldValue.get();
                if (v2 == null) {
                    V load = cacheLoader.load(k2);
                    return set(load) ? this.futureValue : Futures.immediateFuture(load);
                }
                ListenableFuture<V> reload = cacheLoader.reload(k2, v2);
                return reload == null ? Futures.immediateFuture(null) : Futures.transform(reload, new b(this), MoreExecutors.directExecutor());
            } catch (Throwable th) {
                ListenableFuture<V> fullyFailedFuture = setException(th) ? this.futureValue : fullyFailedFuture(th);
                if (th instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return fullyFailedFuture;
            }
        }

        public void notifyNewValue(@CheckForNull V v2) {
            if (v2 != null) {
                set(v2);
            } else {
                this.oldValue = LocalCache.unset();
            }
        }

        @CanIgnoreReturnValue
        public boolean set(@CheckForNull V v2) {
            return this.futureValue.set(v2);
        }

        @CanIgnoreReturnValue
        public boolean setException(Throwable th) {
            return this.futureValue.setException(th);
        }

        public V waitForValue() throws ExecutionException {
            return Uninterruptibles.getUninterruptibly(this.futureValue);
        }

        public LoadingValueReference(ValueReference<K, V> valueReference) {
            this.futureValue = SettableFuture.create();
            this.stopwatch = Stopwatch.createUnstarted();
            this.oldValue = valueReference;
        }
    }

    public static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;

        public LocalLoadingCache(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
            super();
        }

        private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
            throw new InvalidObjectException("Use LoadingSerializationProxy");
        }

        public final V apply(K k2) {
            return getUnchecked(k2);
        }

        public V get(K k2) throws ExecutionException {
            return this.localCache.getOrLoad(k2);
        }

        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return this.localCache.getAll(iterable);
        }

        @CanIgnoreReturnValue
        public V getUnchecked(K k2) {
            try {
                return get(k2);
            } catch (ExecutionException e3) {
                throw new UncheckedExecutionException(e3.getCause());
            }
        }

        public void refresh(K k2) {
            this.localCache.refresh(k2);
        }

        public Object writeReplace() {
            return new LoadingSerializationProxy(this.localCache);
        }
    }

    public static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        final LocalCache<K, V> localCache;

        private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
            throw new InvalidObjectException("Use ManualSerializationProxy");
        }

        public ConcurrentMap<K, V> asMap() {
            return this.localCache;
        }

        public void cleanUp() {
            this.localCache.cleanUp();
        }

        public V get(K k2, final Callable<? extends V> callable) throws ExecutionException {
            Preconditions.checkNotNull(callable);
            return this.localCache.get(k2, new CacheLoader<Object, V>(this) {
                public V load(Object obj) throws Exception {
                    return callable.call();
                }
            });
        }

        public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
            return this.localCache.getAllPresent(iterable);
        }

        @CheckForNull
        public V getIfPresent(Object obj) {
            return this.localCache.getIfPresent(obj);
        }

        public void invalidate(Object obj) {
            Preconditions.checkNotNull(obj);
            this.localCache.remove(obj);
        }

        public void invalidateAll(Iterable<?> iterable) {
            this.localCache.invalidateAll(iterable);
        }

        public void put(K k2, V v2) {
            this.localCache.put(k2, v2);
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            this.localCache.putAll(map);
        }

        public long size() {
            return this.localCache.longSize();
        }

        public CacheStats stats() {
            AbstractCache.SimpleStatsCounter simpleStatsCounter = new AbstractCache.SimpleStatsCounter();
            simpleStatsCounter.incrementBy(this.localCache.globalStatsCounter);
            for (Segment<K, V> segment : this.localCache.segments) {
                simpleStatsCounter.incrementBy(segment.statsCounter);
            }
            return simpleStatsCounter.snapshot();
        }

        public Object writeReplace() {
            return new ManualSerializationProxy(this.localCache);
        }

        public LocalManualCache(CacheBuilder<? super K, ? super V> cacheBuilder) {
            this(new LocalCache(cacheBuilder, (CacheLoader) null));
        }

        public void invalidateAll() {
            this.localCache.clear();
        }

        private LocalManualCache(LocalCache<K, V> localCache2) {
            this.localCache = localCache2;
        }
    }

    public static class ManualSerializationProxy<K, V> extends ForwardingCache<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        final int concurrencyLevel;
        @CheckForNull
        transient Cache<K, V> delegate;
        final long expireAfterAccessNanos;
        final long expireAfterWriteNanos;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final CacheLoader<? super K, V> loader;
        final long maxWeight;
        final RemovalListener<? super K, ? super V> removalListener;
        @CheckForNull
        final Ticker ticker;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;
        final Weigher<K, V> weigher;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ManualSerializationProxy(com.google.common.cache.LocalCache<K, V> r17) {
            /*
                r16 = this;
                r0 = r17
                com.google.common.cache.LocalCache$Strength r1 = r0.keyStrength
                com.google.common.cache.LocalCache$Strength r2 = r0.valueStrength
                com.google.common.base.Equivalence<java.lang.Object> r3 = r0.keyEquivalence
                com.google.common.base.Equivalence<java.lang.Object> r4 = r0.valueEquivalence
                long r5 = r0.expireAfterWriteNanos
                long r7 = r0.expireAfterAccessNanos
                long r9 = r0.maxWeight
                com.google.common.cache.Weigher<K, V> r11 = r0.weigher
                int r12 = r0.concurrencyLevel
                com.google.common.cache.RemovalListener<K, V> r13 = r0.removalListener
                com.google.common.base.Ticker r14 = r0.ticker
                com.google.common.cache.CacheLoader<? super K, V> r15 = r0.defaultLoader
                r0 = r16
                r0.<init>(r1, r2, r3, r4, r5, r7, r9, r11, r12, r13, r14, r15)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.ManualSerializationProxy.<init>(com.google.common.cache.LocalCache):void");
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = recreateCacheBuilder().build();
        }

        private Object readResolve() {
            return this.delegate;
        }

        public CacheBuilder<K, V> recreateCacheBuilder() {
            CacheBuilder<K1, V1> removalListener2 = CacheBuilder.newBuilder().setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).valueEquivalence(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel).removalListener(this.removalListener);
            removalListener2.strictParsing = false;
            long j2 = this.expireAfterWriteNanos;
            if (j2 > 0) {
                removalListener2.expireAfterWrite(j2, TimeUnit.NANOSECONDS);
            }
            long j3 = this.expireAfterAccessNanos;
            if (j3 > 0) {
                removalListener2.expireAfterAccess(j3, TimeUnit.NANOSECONDS);
            }
            Weigher<K, V> weigher2 = this.weigher;
            if (weigher2 != CacheBuilder.OneWeigher.INSTANCE) {
                removalListener2.weigher(weigher2);
                long j4 = this.maxWeight;
                if (j4 != -1) {
                    removalListener2.maximumWeight(j4);
                }
            } else {
                long j5 = this.maxWeight;
                if (j5 != -1) {
                    removalListener2.maximumSize(j5);
                }
            }
            Ticker ticker2 = this.ticker;
            if (ticker2 != null) {
                removalListener2.ticker(ticker2);
            }
            return removalListener2;
        }

        private ManualSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j2, long j3, long j4, Weigher<K, V> weigher2, int i3, RemovalListener<? super K, ? super V> removalListener2, Ticker ticker2, CacheLoader<? super K, V> cacheLoader) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.expireAfterWriteNanos = j2;
            this.expireAfterAccessNanos = j3;
            this.maxWeight = j4;
            this.weigher = weigher2;
            this.concurrencyLevel = i3;
            this.removalListener = removalListener2;
            this.ticker = (ticker2 == Ticker.systemTicker() || ticker2 == CacheBuilder.NULL_TICKER) ? null : ticker2;
            this.loader = cacheLoader;
        }

        public Cache<K, V> delegate() {
            return this.delegate;
        }
    }

    public enum NullEntry implements ReferenceEntry<Object, Object> {
        INSTANCE;

        public long getAccessTime() {
            return 0;
        }

        public int getHash() {
            return 0;
        }

        @CheckForNull
        public Object getKey() {
            return null;
        }

        @CheckForNull
        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        public ReferenceEntry<Object, Object> getNextInAccessQueue() {
            return this;
        }

        public ReferenceEntry<Object, Object> getNextInWriteQueue() {
            return this;
        }

        public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        @CheckForNull
        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        public long getWriteTime() {
            return 0;
        }

        public void setAccessTime(long j2) {
        }

        public void setNextInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setNextInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }

        public void setWriteTime(long j2) {
        }
    }

    public static class SoftValueReference<K, V> extends SoftReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        public SoftValueReference(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry) {
            super(v2, referenceQueue);
            this.entry = referenceEntry;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry) {
            return new SoftValueReference(referenceQueue, v2, referenceEntry);
        }

        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        public int getWeight() {
            return 1;
        }

        public boolean isActive() {
            return true;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(V v2) {
        }

        public V waitForValue() {
            return get();
        }
    }

    public enum Strength {
        STRONG {
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }

            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v2, int i3) {
                return i3 == 1 ? new StrongValueReference(v2) : new WeightedStrongValueReference(v2, i3);
            }
        },
        SOFT {
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }

            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v2, int i3) {
                return i3 == 1 ? new SoftValueReference(segment.valueReferenceQueue, v2, referenceEntry) : new WeightedSoftValueReference(segment.valueReferenceQueue, v2, referenceEntry, i3);
            }
        },
        WEAK {
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }

            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v2, int i3) {
                return i3 == 1 ? new WeakValueReference(segment.valueReferenceQueue, v2, referenceEntry) : new WeightedWeakValueReference(segment.valueReferenceQueue, v2, referenceEntry, i3);
            }
        };

        public abstract Equivalence<Object> defaultEquivalence();

        public abstract <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v2, int i3);
    }

    public static final class StrongAccessEntry<K, V> extends StrongEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        @Weak
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();

        public StrongAccessEntry(K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            super(k2, i3, referenceEntry);
        }

        public long getAccessTime() {
            return this.accessTime;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        public void setAccessTime(long j2) {
            this.accessTime = j2;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }
    }

    public static final class StrongAccessWriteEntry<K, V> extends StrongEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        @Weak
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();
        volatile long writeTime = Long.MAX_VALUE;

        public StrongAccessWriteEntry(K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            super(k2, i3, referenceEntry);
        }

        public long getAccessTime() {
            return this.accessTime;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        public long getWriteTime() {
            return this.writeTime;
        }

        public void setAccessTime(long j2) {
            this.accessTime = j2;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        public void setWriteTime(long j2) {
            this.writeTime = j2;
        }
    }

    public static class StrongEntry<K, V> extends AbstractReferenceEntry<K, V> {
        final int hash;
        final K key;
        @CheckForNull
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = LocalCache.unset();

        public StrongEntry(K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            this.key = k2;
            this.hash = i3;
            this.next = referenceEntry;
        }

        public int getHash() {
            return this.hash;
        }

        public K getKey() {
            return this.key;
        }

        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }

        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        public void setValueReference(ValueReference<K, V> valueReference2) {
            this.valueReference = valueReference2;
        }
    }

    public static class StrongValueReference<K, V> implements ValueReference<K, V> {
        final V referent;

        public StrongValueReference(V v2) {
            this.referent = v2;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public V get() {
            return this.referent;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public int getWeight() {
            return 1;
        }

        public boolean isActive() {
            return true;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(V v2) {
        }

        public V waitForValue() {
            return get();
        }
    }

    public static final class StrongWriteEntry<K, V> extends StrongEntry<K, V> {
        @Weak
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();
        volatile long writeTime = Long.MAX_VALUE;

        public StrongWriteEntry(K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            super(k2, i3, referenceEntry);
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        public long getWriteTime() {
            return this.writeTime;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        public void setWriteTime(long j2) {
            this.writeTime = j2;
        }
    }

    public final class ValueIterator extends LocalCache<K, V>.HashIterator<V> {
        public ValueIterator(LocalCache localCache) {
            super();
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    public interface ValueReference<K, V> {
        ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @CheckForNull V v2, ReferenceEntry<K, V> referenceEntry);

        @CheckForNull
        V get();

        @CheckForNull
        ReferenceEntry<K, V> getEntry();

        int getWeight();

        boolean isActive();

        boolean isLoading();

        void notifyNewValue(@CheckForNull V v2);

        V waitForValue() throws ExecutionException;
    }

    public final class Values extends AbstractCollection<V> {
        public Values() {
        }

        public void clear() {
            LocalCache.this.clear();
        }

        public boolean contains(Object obj) {
            return LocalCache.this.containsValue(obj);
        }

        public boolean isEmpty() {
            return LocalCache.this.isEmpty();
        }

        public Iterator<V> iterator() {
            return new ValueIterator(LocalCache.this);
        }

        public int size() {
            return LocalCache.this.size();
        }

        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        public <E> E[] toArray(E[] eArr) {
            return LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    public static final class WeakAccessEntry<K, V> extends WeakEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        @Weak
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();

        public WeakAccessEntry(ReferenceQueue<K> referenceQueue, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k2, i3, referenceEntry);
        }

        public long getAccessTime() {
            return this.accessTime;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        public void setAccessTime(long j2) {
            this.accessTime = j2;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }
    }

    public static final class WeakAccessWriteEntry<K, V> extends WeakEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        @Weak
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();
        volatile long writeTime = Long.MAX_VALUE;

        public WeakAccessWriteEntry(ReferenceQueue<K> referenceQueue, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k2, i3, referenceEntry);
        }

        public long getAccessTime() {
            return this.accessTime;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        public long getWriteTime() {
            return this.writeTime;
        }

        public void setAccessTime(long j2) {
            this.accessTime = j2;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        public void setWriteTime(long j2) {
            this.writeTime = j2;
        }
    }

    public static class WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {
        final int hash;
        @CheckForNull
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = LocalCache.unset();

        public WeakEntry(ReferenceQueue<K> referenceQueue, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            super(k2, referenceQueue);
            this.hash = i3;
            this.next = referenceEntry;
        }

        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        public int getHash() {
            return this.hash;
        }

        public K getKey() {
            return get();
        }

        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j2) {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setValueReference(ValueReference<K, V> valueReference2) {
            this.valueReference = valueReference2;
        }

        public void setWriteTime(long j2) {
            throw new UnsupportedOperationException();
        }
    }

    public static class WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        public WeakValueReference(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry) {
            super(v2, referenceQueue);
            this.entry = referenceEntry;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry) {
            return new WeakValueReference(referenceQueue, v2, referenceEntry);
        }

        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        public int getWeight() {
            return 1;
        }

        public boolean isActive() {
            return true;
        }

        public boolean isLoading() {
            return false;
        }

        public void notifyNewValue(V v2) {
        }

        public V waitForValue() {
            return get();
        }
    }

    public static final class WeakWriteEntry<K, V> extends WeakEntry<K, V> {
        @Weak
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        @Weak
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();
        volatile long writeTime = Long.MAX_VALUE;

        public WeakWriteEntry(ReferenceQueue<K> referenceQueue, K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k2, i3, referenceEntry);
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        public long getWriteTime() {
            return this.writeTime;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        public void setWriteTime(long j2) {
            this.writeTime = j2;
        }
    }

    public static final class WeightedSoftValueReference<K, V> extends SoftValueReference<K, V> {
        final int weight;

        public WeightedSoftValueReference(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry, int i3) {
            super(referenceQueue, v2, referenceEntry);
            this.weight = i3;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedSoftValueReference(referenceQueue, v2, referenceEntry, this.weight);
        }

        public int getWeight() {
            return this.weight;
        }
    }

    public static final class WeightedStrongValueReference<K, V> extends StrongValueReference<K, V> {
        final int weight;

        public WeightedStrongValueReference(V v2, int i3) {
            super(v2);
            this.weight = i3;
        }

        public int getWeight() {
            return this.weight;
        }
    }

    public static final class WeightedWeakValueReference<K, V> extends WeakValueReference<K, V> {
        final int weight;

        public WeightedWeakValueReference(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry, int i3) {
            super(referenceQueue, v2, referenceEntry);
            this.weight = i3;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v2, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedWeakValueReference(referenceQueue, v2, referenceEntry, this.weight);
        }

        public int getWeight() {
            return this.weight;
        }
    }

    public static final class WriteQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>(this) {
            @Weak
            ReferenceEntry<K, V> nextWrite = this;
            @Weak
            ReferenceEntry<K, V> previousWrite = this;

            public ReferenceEntry<K, V> getNextInWriteQueue() {
                return this.nextWrite;
            }

            public ReferenceEntry<K, V> getPreviousInWriteQueue() {
                return this.previousWrite;
            }

            public long getWriteTime() {
                return Long.MAX_VALUE;
            }

            public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.nextWrite = referenceEntry;
            }

            public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.previousWrite = referenceEntry;
            }

            public void setWriteTime(long j2) {
            }
        };

        public void clear() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (nextInWriteQueue != referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue2 = nextInWriteQueue.getNextInWriteQueue();
                    LocalCache.nullifyWriteOrder(nextInWriteQueue);
                    nextInWriteQueue = nextInWriteQueue2;
                } else {
                    referenceEntry.setNextInWriteQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousInWriteQueue(referenceEntry2);
                    return;
                }
            }
        }

        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInWriteQueue() != NullEntry.INSTANCE;
        }

        public boolean isEmpty() {
            return this.head.getNextInWriteQueue() == this.head;
        }

        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
                @CheckForNull
                public ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
                    if (nextInWriteQueue == WriteQueue.this.head) {
                        return null;
                    }
                    return nextInWriteQueue;
                }
            };
        }

        @CanIgnoreReturnValue
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry previousInWriteQueue = referenceEntry.getPreviousInWriteQueue();
            ReferenceEntry nextInWriteQueue = referenceEntry.getNextInWriteQueue();
            LocalCache.connectWriteOrder(previousInWriteQueue, nextInWriteQueue);
            LocalCache.nullifyWriteOrder(referenceEntry);
            return nextInWriteQueue != NullEntry.INSTANCE;
        }

        public int size() {
            int i3 = 0;
            for (ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue(); nextInWriteQueue != this.head; nextInWriteQueue = nextInWriteQueue.getNextInWriteQueue()) {
                i3++;
            }
            return i3;
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.connectWriteOrder(referenceEntry.getPreviousInWriteQueue(), referenceEntry.getNextInWriteQueue());
            LocalCache.connectWriteOrder(this.head.getPreviousInWriteQueue(), referenceEntry);
            LocalCache.connectWriteOrder(referenceEntry, this.head);
            return true;
        }

        @CheckForNull
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            if (nextInWriteQueue == this.head) {
                return null;
            }
            return nextInWriteQueue;
        }

        @CheckForNull
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            if (nextInWriteQueue == this.head) {
                return null;
            }
            remove(nextInWriteQueue);
            return nextInWriteQueue;
        }
    }

    public final class WriteThroughEntry implements Map.Entry<K, V> {
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
            V put = LocalCache.this.put(this.key, v2);
            this.value = v2;
            return put;
        }

        public String toString() {
            return getKey() + StickyVariantProvider.KEY_VALUE_DELIMITER + getValue();
        }
    }

    public LocalCache(CacheBuilder<? super K, ? super V> cacheBuilder, @CheckForNull CacheLoader<? super K, V> cacheLoader) {
        this.concurrencyLevel = Math.min(cacheBuilder.getConcurrencyLevel(), 65536);
        Strength keyStrength2 = cacheBuilder.getKeyStrength();
        this.keyStrength = keyStrength2;
        this.valueStrength = cacheBuilder.getValueStrength();
        this.keyEquivalence = cacheBuilder.getKeyEquivalence();
        this.valueEquivalence = cacheBuilder.getValueEquivalence();
        long maximumWeight = cacheBuilder.getMaximumWeight();
        this.maxWeight = maximumWeight;
        this.weigher = cacheBuilder.getWeigher();
        this.expireAfterAccessNanos = cacheBuilder.getExpireAfterAccessNanos();
        this.expireAfterWriteNanos = cacheBuilder.getExpireAfterWriteNanos();
        this.refreshNanos = cacheBuilder.getRefreshNanos();
        RemovalListener<K1, V1> removalListener2 = cacheBuilder.getRemovalListener();
        this.removalListener = removalListener2;
        this.removalNotificationQueue = removalListener2 == CacheBuilder.NullListener.INSTANCE ? discardingQueue() : new ConcurrentLinkedQueue<>();
        this.ticker = cacheBuilder.getTicker(recordsTime());
        this.entryFactory = EntryFactory.getFactory(keyStrength2, usesAccessEntries(), usesWriteEntries());
        this.globalStatsCounter = (AbstractCache.StatsCounter) cacheBuilder.getStatsCounterSupplier().get();
        this.defaultLoader = cacheLoader;
        int min = Math.min(cacheBuilder.getInitialCapacity(), 1073741824);
        if (evictsBySize() && !customWeigher()) {
            min = (int) Math.min((long) min, maximumWeight);
        }
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        int i6 = 1;
        while (i6 < this.concurrencyLevel && (!evictsBySize() || ((long) i6) * 20 <= this.maxWeight)) {
            i5++;
            i6 <<= 1;
        }
        this.segmentShift = 32 - i5;
        this.segmentMask = i6 - 1;
        this.segments = newSegmentArray(i6);
        int i7 = min / i6;
        while (i4 < (i7 * i6 < min ? i7 + 1 : i7)) {
            i4 <<= 1;
        }
        if (evictsBySize()) {
            long j2 = this.maxWeight;
            long j3 = (long) i6;
            long j4 = (j2 / j3) + 1;
            long j5 = j2 % j3;
            while (true) {
                Segment<K, V>[] segmentArr = this.segments;
                if (i3 < segmentArr.length) {
                    if (((long) i3) == j5) {
                        j4--;
                    }
                    segmentArr[i3] = createSegment(i4, j4, (AbstractCache.StatsCounter) cacheBuilder.getStatsCounterSupplier().get());
                    i3++;
                } else {
                    return;
                }
            }
        } else {
            while (true) {
                Segment<K, V>[] segmentArr2 = this.segments;
                if (i3 < segmentArr2.length) {
                    segmentArr2[i3] = createSegment(i4, -1, (AbstractCache.StatsCounter) cacheBuilder.getStatsCounterSupplier().get());
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public static <K, V> void connectAccessOrder(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInAccessQueue(referenceEntry2);
        referenceEntry2.setPreviousInAccessQueue(referenceEntry);
    }

    public static <K, V> void connectWriteOrder(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInWriteQueue(referenceEntry2);
        referenceEntry2.setPreviousInWriteQueue(referenceEntry);
    }

    public static <E> Queue<E> discardingQueue() {
        return DISCARDING_QUEUE;
    }

    public static <K, V> ReferenceEntry<K, V> nullEntry() {
        return NullEntry.INSTANCE;
    }

    public static <K, V> void nullifyAccessOrder(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry nullEntry = nullEntry();
        referenceEntry.setNextInAccessQueue(nullEntry);
        referenceEntry.setPreviousInAccessQueue(nullEntry);
    }

    public static <K, V> void nullifyWriteOrder(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry nullEntry = nullEntry();
        referenceEntry.setNextInWriteQueue(nullEntry);
        referenceEntry.setPreviousInWriteQueue(nullEntry);
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

    public static <K, V> ValueReference<K, V> unset() {
        return UNSET;
    }

    public void cleanUp() {
        for (Segment<K, V> cleanUp : this.segments) {
            cleanUp.cleanUp();
        }
    }

    public void clear() {
        for (Segment<K, V> clear : this.segments) {
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
        long read = this.ticker.read();
        Segment<K, V>[] segmentArr = this.segments;
        long j2 = -1;
        int i3 = 0;
        while (i3 < 3) {
            int length = segmentArr.length;
            long j3 = 0;
            int i4 = z2;
            while (i4 < length) {
                Segment<K, V> segment = segmentArr[i4];
                int i5 = segment.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = segment.table;
                for (int i6 = z2; i6 < atomicReferenceArray.length(); i6++) {
                    ReferenceEntry referenceEntry = atomicReferenceArray.get(i6);
                    while (referenceEntry != null) {
                        Segment<K, V>[] segmentArr2 = segmentArr;
                        V liveValue = segment.getLiveValue(referenceEntry, read);
                        long j4 = read;
                        if (liveValue != null && this.valueEquivalence.equivalent(obj2, liveValue)) {
                            return true;
                        }
                        referenceEntry = referenceEntry.getNext();
                        segmentArr = segmentArr2;
                        read = j4;
                    }
                    long j5 = read;
                    Segment<K, V>[] segmentArr3 = segmentArr;
                }
                Segment<K, V>[] segmentArr4 = segmentArr;
                j3 += (long) segment.modCount;
                i4++;
                read = read;
                z2 = false;
            }
            long j6 = read;
            Segment<K, V>[] segmentArr5 = segmentArr;
            if (j3 == j2) {
                return false;
            }
            i3++;
            j2 = j3;
            segmentArr = segmentArr5;
            read = j6;
            z2 = false;
        }
        return z2;
    }

    @VisibleForTesting
    public ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        return segmentFor(referenceEntry.getHash()).copyEntry(referenceEntry, referenceEntry2);
    }

    public Segment<K, V> createSegment(int i3, long j2, AbstractCache.StatsCounter statsCounter) {
        return new Segment(this, i3, j2, statsCounter);
    }

    public boolean customWeigher() {
        return this.weigher != CacheBuilder.OneWeigher.INSTANCE;
    }

    @GwtIncompatible
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    public boolean evictsBySize() {
        return this.maxWeight >= 0;
    }

    public boolean expires() {
        return expiresAfterWrite() || expiresAfterAccess();
    }

    public boolean expiresAfterAccess() {
        return this.expireAfterAccessNanos > 0;
    }

    public boolean expiresAfterWrite() {
        return this.expireAfterWriteNanos > 0;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V get(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).get(obj, hash);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:23|24|(2:27|25)|39) */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r8 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0079, code lost:
        if (r8.hasNext() != false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        r1 = r8.next();
        r3 = r3 - 1;
        r0.put(r1, get(r1, r7.defaultLoader));
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0071 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.collect.ImmutableMap<K, V> getAll(java.lang.Iterable<? extends K> r8) throws java.util.concurrent.ExecutionException {
        /*
            r7 = this;
            java.util.LinkedHashMap r0 = com.google.common.collect.Maps.newLinkedHashMap()
            java.util.LinkedHashSet r1 = com.google.common.collect.Sets.newLinkedHashSet()
            java.util.Iterator r8 = r8.iterator()
            r2 = 0
            r3 = r2
        L_0x000e:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x0030
            java.lang.Object r4 = r8.next()
            java.lang.Object r5 = r7.get(r4)
            boolean r6 = r0.containsKey(r4)
            if (r6 != 0) goto L_0x000e
            r0.put(r4, r5)
            if (r5 != 0) goto L_0x002d
            int r3 = r3 + 1
            r1.add(r4)
            goto L_0x000e
        L_0x002d:
            int r2 = r2 + 1
            goto L_0x000e
        L_0x0030:
            boolean r8 = r1.isEmpty()     // Catch:{ all -> 0x0058 }
            if (r8 != 0) goto L_0x008b
            java.util.Set r8 = java.util.Collections.unmodifiableSet(r1)     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            com.google.common.cache.CacheLoader<? super K, V> r4 = r7.defaultLoader     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            java.util.Map r8 = r7.loadAll(r8, r4)     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            java.util.Iterator r4 = r1.iterator()     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
        L_0x0044:
            boolean r5 = r4.hasNext()     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            if (r5 == 0) goto L_0x008b
            java.lang.Object r5 = r4.next()     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            java.lang.Object r6 = r8.get(r5)     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            if (r6 == 0) goto L_0x005a
            r0.put(r5, r6)     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            goto L_0x0044
        L_0x0058:
            r8 = move-exception
            goto L_0x009a
        L_0x005a:
            com.google.common.cache.CacheLoader$InvalidCacheLoadException r8 = new com.google.common.cache.CacheLoader$InvalidCacheLoadException     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            r4.<init>()     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            java.lang.String r6 = "loadAll failed to return a value for "
            r4.append(r6)     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            r4.append(r5)     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            java.lang.String r4 = r4.toString()     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            r8.<init>(r4)     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
            throw r8     // Catch:{ UnsupportedLoadingOperationException -> 0x0071 }
        L_0x0071:
            java.util.Iterator r8 = r1.iterator()     // Catch:{ all -> 0x0058 }
        L_0x0075:
            boolean r1 = r8.hasNext()     // Catch:{ all -> 0x0058 }
            if (r1 == 0) goto L_0x008b
            java.lang.Object r1 = r8.next()     // Catch:{ all -> 0x0058 }
            int r3 = r3 + -1
            com.google.common.cache.CacheLoader<? super K, V> r4 = r7.defaultLoader     // Catch:{ all -> 0x0058 }
            java.lang.Object r4 = r7.get(r1, r4)     // Catch:{ all -> 0x0058 }
            r0.put(r1, r4)     // Catch:{ all -> 0x0058 }
            goto L_0x0075
        L_0x008b:
            com.google.common.collect.ImmutableMap r8 = com.google.common.collect.ImmutableMap.copyOf(r0)     // Catch:{ all -> 0x0058 }
            com.google.common.cache.AbstractCache$StatsCounter r0 = r7.globalStatsCounter
            r0.recordHits(r2)
            com.google.common.cache.AbstractCache$StatsCounter r7 = r7.globalStatsCounter
            r7.recordMisses(r3)
            return r8
        L_0x009a:
            com.google.common.cache.AbstractCache$StatsCounter r0 = r7.globalStatsCounter
            r0.recordHits(r2)
            com.google.common.cache.AbstractCache$StatsCounter r7 = r7.globalStatsCounter
            r7.recordMisses(r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.getAll(java.lang.Iterable):com.google.common.collect.ImmutableMap");
    }

    public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int i3 = 0;
        int i4 = 0;
        for (Object next : iterable) {
            Object obj = get(next);
            if (obj == null) {
                i4++;
            } else {
                builder.put(next, obj);
                i3++;
            }
        }
        this.globalStatsCounter.recordHits(i3);
        this.globalStatsCounter.recordMisses(i4);
        return builder.buildKeepingLast();
    }

    @CheckForNull
    public ReferenceEntry<K, V> getEntry(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).getEntry(obj, hash);
    }

    @CheckForNull
    public V getIfPresent(Object obj) {
        int hash = hash(Preconditions.checkNotNull(obj));
        V v2 = segmentFor(hash).get(obj, hash);
        if (v2 == null) {
            this.globalStatsCounter.recordMisses(1);
        } else {
            this.globalStatsCounter.recordHits(1);
        }
        return v2;
    }

    @CheckForNull
    public V getLiveValue(ReferenceEntry<K, V> referenceEntry, long j2) {
        V v2;
        if (referenceEntry.getKey() == null || (v2 = referenceEntry.getValueReference().get()) == null || isExpired(referenceEntry, j2)) {
            return null;
        }
        return v2;
    }

    @CheckForNull
    public V getOrDefault(@CheckForNull Object obj, @CheckForNull V v2) {
        V v3 = get(obj);
        return v3 != null ? v3 : v2;
    }

    public V getOrLoad(K k2) throws ExecutionException {
        return get(k2, this.defaultLoader);
    }

    public int hash(@CheckForNull Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    public void invalidateAll(Iterable<?> iterable) {
        for (Object remove : iterable) {
            remove(remove);
        }
    }

    public boolean isEmpty() {
        Segment<K, V>[] segmentArr = this.segments;
        long j2 = 0;
        for (Segment<K, V> segment : segmentArr) {
            if (segment.count != 0) {
                return false;
            }
            j2 += (long) segment.modCount;
        }
        if (j2 == 0) {
            return true;
        }
        for (Segment<K, V> segment2 : segmentArr) {
            if (segment2.count != 0) {
                return false;
            }
            j2 -= (long) segment2.modCount;
        }
        return j2 == 0;
    }

    public boolean isExpired(ReferenceEntry<K, V> referenceEntry, long j2) {
        Preconditions.checkNotNull(referenceEntry);
        if (!expiresAfterAccess() || j2 - referenceEntry.getAccessTime() < this.expireAfterAccessNanos) {
            return expiresAfterWrite() && j2 - referenceEntry.getWriteTime() >= this.expireAfterWriteNanos;
        }
        return true;
    }

    @VisibleForTesting
    public boolean isLive(ReferenceEntry<K, V> referenceEntry, long j2) {
        return segmentFor(referenceEntry.getHash()).getLiveValue(referenceEntry, j2) != null;
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

    @CheckForNull
    public Map<K, V> loadAll(Set<? extends K> set, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        Preconditions.checkNotNull(cacheLoader);
        Preconditions.checkNotNull(set);
        Stopwatch createStarted = Stopwatch.createStarted();
        boolean z2 = true;
        boolean z3 = false;
        try {
            Map<? super K, V> loadAll = cacheLoader.loadAll(set);
            if (loadAll != null) {
                createStarted.stop();
                for (Map.Entry next : loadAll.entrySet()) {
                    Object key = next.getKey();
                    Object value = next.getValue();
                    if (key == null || value == null) {
                        z3 = true;
                    } else {
                        put(key, value);
                    }
                }
                if (!z3) {
                    this.globalStatsCounter.recordLoadSuccess(createStarted.elapsed(TimeUnit.NANOSECONDS));
                    return loadAll;
                }
                this.globalStatsCounter.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
                throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null keys or values from loadAll");
            }
            this.globalStatsCounter.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
            throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null map from loadAll");
        } catch (CacheLoader.UnsupportedLoadingOperationException e3) {
            throw e3;
        } catch (InterruptedException e4) {
            Thread.currentThread().interrupt();
            throw new ExecutionException(e4);
        } catch (RuntimeException e5) {
            throw new UncheckedExecutionException((Throwable) e5);
        } catch (Exception e6) {
            throw new ExecutionException(e6);
        } catch (Error e7) {
            throw new ExecutionError(e7);
        } catch (Throwable th) {
            th = th;
        }
        if (!z2) {
            this.globalStatsCounter.recordLoadException(createStarted.elapsed(TimeUnit.NANOSECONDS));
        }
        throw th;
    }

    public long longSize() {
        long j2 = 0;
        for (Segment<K, V> segment : this.segments) {
            j2 += (long) Math.max(0, segment.count);
        }
        return j2;
    }

    @VisibleForTesting
    public ReferenceEntry<K, V> newEntry(K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
        Segment segmentFor = segmentFor(i3);
        segmentFor.lock();
        try {
            return segmentFor.newEntry(k2, i3, referenceEntry);
        } finally {
            segmentFor.unlock();
        }
    }

    public final Segment<K, V>[] newSegmentArray(int i3) {
        return new Segment[i3];
    }

    @VisibleForTesting
    public ValueReference<K, V> newValueReference(ReferenceEntry<K, V> referenceEntry, V v2, int i3) {
        return this.valueStrength.referenceValue(segmentFor(referenceEntry.getHash()), referenceEntry, Preconditions.checkNotNull(v2), i3);
    }

    public void processPendingNotifications() {
        while (true) {
            RemovalNotification poll = this.removalNotificationQueue.poll();
            if (poll != null) {
                try {
                    this.removalListener.onRemoval(poll);
                } catch (Throwable th) {
                    logger.log(Level.WARNING, "Exception thrown by removal listener", th);
                }
            } else {
                return;
            }
        }
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
    public V putIfAbsent(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int hash = hash(k2);
        return segmentFor(hash).put(k2, hash, v2, true);
    }

    public void reclaimKey(ReferenceEntry<K, V> referenceEntry) {
        int hash = referenceEntry.getHash();
        segmentFor(hash).reclaimKey(referenceEntry, hash);
    }

    public void reclaimValue(ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
    }

    public boolean recordsAccess() {
        return expiresAfterAccess();
    }

    public boolean recordsTime() {
        return recordsWrite() || recordsAccess();
    }

    public boolean recordsWrite() {
        return expiresAfterWrite() || refreshes();
    }

    public void refresh(K k2) {
        int hash = hash(Preconditions.checkNotNull(k2));
        segmentFor(hash).refresh(k2, hash, this.defaultLoader, false);
    }

    public boolean refreshes() {
        return this.refreshNanos > 0;
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

    public Segment<K, V> segmentFor(int i3) {
        return this.segments[this.segmentMask & (i3 >>> this.segmentShift)];
    }

    public int size() {
        return Ints.saturatedCast(longSize());
    }

    public boolean usesAccessEntries() {
        return usesAccessQueue() || recordsAccess();
    }

    public boolean usesAccessQueue() {
        return expiresAfterAccess() || evictsBySize();
    }

    public boolean usesKeyReferences() {
        return this.keyStrength != Strength.STRONG;
    }

    public boolean usesValueReferences() {
        return this.valueStrength != Strength.STRONG;
    }

    public boolean usesWriteEntries() {
        return usesWriteQueue() || recordsWrite();
    }

    public boolean usesWriteQueue() {
        return expiresAfterWrite();
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

    @CanIgnoreReturnValue
    public V get(K k2, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        int hash = hash(Preconditions.checkNotNull(k2));
        return segmentFor(hash).get(k2, hash, cacheLoader);
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

    public static class Segment<K, V> extends ReentrantLock {
        @GuardedBy("this")
        final Queue<ReferenceEntry<K, V>> accessQueue;
        volatile int count;
        @CheckForNull
        final ReferenceQueue<K> keyReferenceQueue;
        @Weak
        final LocalCache<K, V> map;
        final long maxSegmentWeight;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        final Queue<ReferenceEntry<K, V>> recencyQueue;
        final AbstractCache.StatsCounter statsCounter;
        @CheckForNull
        volatile AtomicReferenceArray<ReferenceEntry<K, V>> table;
        int threshold;
        @GuardedBy("this")
        long totalWeight;
        @CheckForNull
        final ReferenceQueue<V> valueReferenceQueue;
        @GuardedBy("this")
        final Queue<ReferenceEntry<K, V>> writeQueue;

        public Segment(LocalCache<K, V> localCache, int i3, long j2, AbstractCache.StatsCounter statsCounter2) {
            this.map = localCache;
            this.maxSegmentWeight = j2;
            this.statsCounter = (AbstractCache.StatsCounter) Preconditions.checkNotNull(statsCounter2);
            initTable(newEntryArray(i3));
            ReferenceQueue<V> referenceQueue = null;
            this.keyReferenceQueue = localCache.usesKeyReferences() ? new ReferenceQueue<>() : null;
            this.valueReferenceQueue = localCache.usesValueReferences() ? new ReferenceQueue<>() : referenceQueue;
            this.recencyQueue = localCache.usesAccessQueue() ? new ConcurrentLinkedQueue<>() : LocalCache.discardingQueue();
            this.writeQueue = localCache.usesWriteQueue() ? new WriteQueue<>() : LocalCache.discardingQueue();
            this.accessQueue = localCache.usesAccessQueue() ? new AccessQueue<>() : LocalCache.discardingQueue();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadAsync$0(Object obj, int i3, LoadingValueReference loadingValueReference, ListenableFuture listenableFuture) {
            try {
                getAndRecordStats(obj, i3, loadingValueReference, listenableFuture);
            } catch (Throwable th) {
                LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", th);
                loadingValueReference.setException(th);
            }
        }

        public void cleanUp() {
            runLockedCleanup(this.map.ticker.read());
            runUnlockedCleanup();
        }

        public void clear() {
            RemovalCause removalCause;
            if (this.count != 0) {
                lock();
                try {
                    preWriteCleanup(this.map.ticker.read());
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                    for (int i3 = 0; i3 < atomicReferenceArray.length(); i3++) {
                        for (ReferenceEntry referenceEntry = atomicReferenceArray.get(i3); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                            if (referenceEntry.getValueReference().isActive()) {
                                Object key = referenceEntry.getKey();
                                Object obj = referenceEntry.getValueReference().get();
                                if (key != null) {
                                    if (obj != null) {
                                        removalCause = RemovalCause.EXPLICIT;
                                        enqueueNotification(key, referenceEntry.getHash(), obj, referenceEntry.getValueReference().getWeight(), removalCause);
                                    }
                                }
                                removalCause = RemovalCause.COLLECTED;
                                enqueueNotification(key, referenceEntry.getHash(), obj, referenceEntry.getValueReference().getWeight(), removalCause);
                            }
                        }
                    }
                    for (int i4 = 0; i4 < atomicReferenceArray.length(); i4++) {
                        atomicReferenceArray.set(i4, (Object) null);
                    }
                    clearReferenceQueues();
                    this.writeQueue.clear();
                    this.accessQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                    unlock();
                    postWriteCleanup();
                } catch (Throwable th) {
                    unlock();
                    postWriteCleanup();
                    throw th;
                }
            }
        }

        public void clearKeyReferenceQueue() {
            do {
            } while (this.keyReferenceQueue.poll() != null);
        }

        public void clearReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                clearKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                clearValueReferenceQueue();
            }
        }

        public void clearValueReferenceQueue() {
            do {
            } while (this.valueReferenceQueue.poll() != null);
        }

        public boolean containsKey(Object obj, int i3) {
            try {
                boolean z2 = false;
                if (this.count != 0) {
                    ReferenceEntry liveEntry = getLiveEntry(obj, i3, this.map.ticker.read());
                    if (liveEntry == null) {
                        return false;
                    }
                    if (liveEntry.getValueReference().get() != null) {
                        z2 = true;
                    }
                    postReadCleanup();
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
                    long read = this.map.ticker.read();
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i3 = 0; i3 < length; i3++) {
                        for (ReferenceEntry referenceEntry = atomicReferenceArray.get(i3); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                            Object liveValue = getLiveValue(referenceEntry, read);
                            if (liveValue != null) {
                                if (this.map.valueEquivalence.equivalent(obj, liveValue)) {
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
        @GuardedBy("this")
        public ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            K key = referenceEntry.getKey();
            if (key == null) {
                return null;
            }
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            V v2 = valueReference.get();
            if (v2 == null && valueReference.isActive()) {
                return null;
            }
            ReferenceEntry<K, V> copyEntry = this.map.entryFactory.copyEntry(this, referenceEntry, referenceEntry2, key);
            copyEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, v2, copyEntry));
            return copyEntry;
        }

        @GuardedBy("this")
        public void drainKeyReferenceQueue() {
            int i3 = 0;
            do {
                Reference<? extends K> poll = this.keyReferenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimKey((ReferenceEntry) poll);
                    i3++;
                } else {
                    return;
                }
            } while (i3 != 16);
        }

        @GuardedBy("this")
        public void drainRecencyQueue() {
            while (true) {
                ReferenceEntry poll = this.recencyQueue.poll();
                if (poll == null) {
                    return;
                }
                if (this.accessQueue.contains(poll)) {
                    this.accessQueue.add(poll);
                }
            }
        }

        @GuardedBy("this")
        public void drainReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                drainKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                drainValueReferenceQueue();
            }
        }

        @GuardedBy("this")
        public void drainValueReferenceQueue() {
            int i3 = 0;
            do {
                Reference<? extends V> poll = this.valueReferenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimValue((ValueReference) poll);
                    i3++;
                } else {
                    return;
                }
            } while (i3 != 16);
        }

        @GuardedBy("this")
        public void enqueueNotification(@CheckForNull K k2, int i3, @CheckForNull V v2, int i4, RemovalCause removalCause) {
            this.totalWeight -= (long) i4;
            if (removalCause.wasEvicted()) {
                this.statsCounter.recordEviction();
            }
            if (this.map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE) {
                this.map.removalNotificationQueue.offer(RemovalNotification.create(k2, v2, removalCause));
            }
        }

        @GuardedBy("this")
        public void evictEntries(ReferenceEntry<K, V> referenceEntry) {
            if (this.map.evictsBySize()) {
                drainRecencyQueue();
                if (((long) referenceEntry.getValueReference().getWeight()) <= this.maxSegmentWeight || removeEntry(referenceEntry, referenceEntry.getHash(), RemovalCause.SIZE)) {
                    while (this.totalWeight > this.maxSegmentWeight) {
                        ReferenceEntry nextEvictable = getNextEvictable();
                        if (!removeEntry(nextEvictable, nextEvictable.getHash(), RemovalCause.SIZE)) {
                            throw new AssertionError();
                        }
                    }
                    return;
                }
                throw new AssertionError();
            }
        }

        @GuardedBy("this")
        public void expand() {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i3 = this.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray = newEntryArray(length << 1);
                this.threshold = (newEntryArray.length() * 3) / 4;
                int length2 = newEntryArray.length() - 1;
                for (int i4 = 0; i4 < length; i4++) {
                    ReferenceEntry referenceEntry = atomicReferenceArray.get(i4);
                    if (referenceEntry != null) {
                        ReferenceEntry next = referenceEntry.getNext();
                        int hash = referenceEntry.getHash() & length2;
                        if (next == null) {
                            newEntryArray.set(hash, referenceEntry);
                        } else {
                            ReferenceEntry referenceEntry2 = referenceEntry;
                            while (next != null) {
                                int hash2 = next.getHash() & length2;
                                if (hash2 != hash) {
                                    referenceEntry2 = next;
                                    hash = hash2;
                                }
                                next = next.getNext();
                            }
                            newEntryArray.set(hash, referenceEntry2);
                            while (referenceEntry != referenceEntry2) {
                                int hash3 = referenceEntry.getHash() & length2;
                                ReferenceEntry copyEntry = copyEntry(referenceEntry, newEntryArray.get(hash3));
                                if (copyEntry != null) {
                                    newEntryArray.set(hash3, copyEntry);
                                } else {
                                    removeCollectedEntry(referenceEntry);
                                    i3--;
                                }
                                referenceEntry = referenceEntry.getNext();
                            }
                        }
                    }
                }
                this.table = newEntryArray;
                this.count = i3;
            }
        }

        @GuardedBy("this")
        public void expireEntries(long j2) {
            ReferenceEntry peek;
            ReferenceEntry peek2;
            drainRecencyQueue();
            do {
                peek = this.writeQueue.peek();
                if (peek == null || !this.map.isExpired(peek, j2)) {
                    do {
                        peek2 = this.accessQueue.peek();
                        if (peek2 == null || !this.map.isExpired(peek2, j2)) {
                            return;
                        }
                    } while (removeEntry(peek2, peek2.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (removeEntry(peek, peek.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        @CanIgnoreReturnValue
        public V get(K k2, int i3, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            ReferenceEntry entry;
            Preconditions.checkNotNull(k2);
            Preconditions.checkNotNull(cacheLoader);
            try {
                if (!(this.count == 0 || (entry = getEntry(k2, i3)) == null)) {
                    long read = this.map.ticker.read();
                    Object liveValue = getLiveValue(entry, read);
                    if (liveValue != null) {
                        recordRead(entry, read);
                        this.statsCounter.recordHits(1);
                        V scheduleRefresh = scheduleRefresh(entry, k2, i3, liveValue, read, cacheLoader);
                        postReadCleanup();
                        return scheduleRefresh;
                    }
                    ValueReference valueReference = entry.getValueReference();
                    if (valueReference.isLoading()) {
                        V waitForLoadingValue = waitForLoadingValue(entry, k2, valueReference);
                        postReadCleanup();
                        return waitForLoadingValue;
                    }
                }
                V lockedGetOrLoad = lockedGetOrLoad(k2, i3, cacheLoader);
                postReadCleanup();
                return lockedGetOrLoad;
            } catch (ExecutionException e3) {
                Throwable cause = e3.getCause();
                if (cause instanceof Error) {
                    throw new ExecutionError((Error) cause);
                } else if (cause instanceof RuntimeException) {
                    throw new UncheckedExecutionException(cause);
                } else {
                    throw e3;
                }
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
        @com.google.errorprone.annotations.CanIgnoreReturnValue
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getAndRecordStats(K r4, int r5, com.google.common.cache.LocalCache.LoadingValueReference<K, V> r6, com.google.common.util.concurrent.ListenableFuture<V> r7) throws java.util.concurrent.ExecutionException {
            /*
                r3 = this;
                java.lang.String r0 = "CacheLoader returned null for key "
                java.lang.Object r7 = com.google.common.util.concurrent.Uninterruptibles.getUninterruptibly(r7)     // Catch:{ all -> 0x002e }
                if (r7 == 0) goto L_0x0017
                com.google.common.cache.AbstractCache$StatsCounter r0 = r3.statsCounter     // Catch:{ all -> 0x0015 }
                long r1 = r6.elapsedNanos()     // Catch:{ all -> 0x0015 }
                r0.recordLoadSuccess(r1)     // Catch:{ all -> 0x0015 }
                r3.storeLoadedValue(r4, r5, r6, r7)     // Catch:{ all -> 0x0015 }
                return r7
            L_0x0015:
                r0 = move-exception
                goto L_0x0030
            L_0x0017:
                com.google.common.cache.CacheLoader$InvalidCacheLoadException r1 = new com.google.common.cache.CacheLoader$InvalidCacheLoadException     // Catch:{ all -> 0x0015 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0015 }
                r2.<init>(r0)     // Catch:{ all -> 0x0015 }
                r2.append(r4)     // Catch:{ all -> 0x0015 }
                java.lang.String r0 = "."
                r2.append(r0)     // Catch:{ all -> 0x0015 }
                java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0015 }
                r1.<init>(r0)     // Catch:{ all -> 0x0015 }
                throw r1     // Catch:{ all -> 0x0015 }
            L_0x002e:
                r0 = move-exception
                r7 = 0
            L_0x0030:
                if (r7 != 0) goto L_0x003e
                com.google.common.cache.AbstractCache$StatsCounter r7 = r3.statsCounter
                long r1 = r6.elapsedNanos()
                r7.recordLoadException(r1)
                r3.removeLoadingValue(r4, r5, r6)
            L_0x003e:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.getAndRecordStats(java.lang.Object, int, com.google.common.cache.LocalCache$LoadingValueReference, com.google.common.util.concurrent.ListenableFuture):java.lang.Object");
        }

        @CheckForNull
        public ReferenceEntry<K, V> getEntry(Object obj, int i3) {
            for (ReferenceEntry<K, V> first = getFirst(i3); first != null; first = first.getNext()) {
                if (first.getHash() == i3) {
                    K key = first.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(obj, key)) {
                        return first;
                    }
                }
            }
            return null;
        }

        public ReferenceEntry<K, V> getFirst(int i3) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            return atomicReferenceArray.get(i3 & (atomicReferenceArray.length() - 1));
        }

        @CheckForNull
        public ReferenceEntry<K, V> getLiveEntry(Object obj, int i3, long j2) {
            ReferenceEntry<K, V> entry = getEntry(obj, i3);
            if (entry == null) {
                return null;
            }
            if (!this.map.isExpired(entry, j2)) {
                return entry;
            }
            tryExpireEntries(j2);
            return null;
        }

        public V getLiveValue(ReferenceEntry<K, V> referenceEntry, long j2) {
            if (referenceEntry.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v2 = referenceEntry.getValueReference().get();
            if (v2 == null) {
                tryDrainReferenceQueues();
                return null;
            } else if (!this.map.isExpired(referenceEntry, j2)) {
                return v2;
            } else {
                tryExpireEntries(j2);
                return null;
            }
        }

        @GuardedBy("this")
        public ReferenceEntry<K, V> getNextEvictable() {
            for (ReferenceEntry<K, V> next : this.accessQueue) {
                if (next.getValueReference().getWeight() > 0) {
                    return next;
                }
            }
            throw new AssertionError();
        }

        public void initTable(AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            if (!this.map.customWeigher()) {
                int i3 = this.threshold;
                if (((long) i3) == this.maxSegmentWeight) {
                    this.threshold = i3 + 1;
                }
            }
            this.table = atomicReferenceArray;
        }

        /* JADX INFO: finally extract failed */
        @CheckForNull
        public LoadingValueReference<K, V> insertLoadingValueReference(K k2, int i3, boolean z2) {
            lock();
            try {
                long read = this.map.ticker.read();
                preWriteCleanup(read);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (referenceEntry2 != null) {
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference = referenceEntry2.getValueReference();
                        if (!valueReference.isLoading()) {
                            if (!z2 || read - referenceEntry2.getWriteTime() >= this.map.refreshNanos) {
                                this.modCount++;
                                LoadingValueReference<K, V> loadingValueReference = new LoadingValueReference<>(valueReference);
                                referenceEntry2.setValueReference(loadingValueReference);
                                unlock();
                                postWriteCleanup();
                                return loadingValueReference;
                            }
                        }
                        unlock();
                        postWriteCleanup();
                        return null;
                    }
                }
                this.modCount++;
                LoadingValueReference<K, V> loadingValueReference2 = new LoadingValueReference<>();
                ReferenceEntry newEntry = newEntry(k2, i3, referenceEntry);
                newEntry.setValueReference(loadingValueReference2);
                atomicReferenceArray.set(length, newEntry);
                unlock();
                postWriteCleanup();
                return loadingValueReference2;
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
                throw th;
            }
        }

        public ListenableFuture<V> loadAsync(K k2, int i3, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> cacheLoader) {
            ListenableFuture<V> loadFuture = loadingValueReference.loadFuture(k2, cacheLoader);
            loadFuture.addListener(new c(this, k2, i3, loadingValueReference, loadFuture), MoreExecutors.directExecutor());
            return loadFuture;
        }

        public V loadSync(K k2, int i3, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            return getAndRecordStats(k2, i3, loadingValueReference, loadingValueReference.loadFuture(k2, cacheLoader));
        }

        public V lockedGetOrLoad(K k2, int i3, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            LoadingValueReference loadingValueReference;
            boolean z2;
            ValueReference valueReference;
            V loadSync;
            K k3 = k2;
            int i4 = i3;
            lock();
            try {
                long read = this.map.ticker.read();
                preWriteCleanup(read);
                int i5 = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i4 & (atomicReferenceArray.length() - 1);
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    loadingValueReference = null;
                    if (referenceEntry2 == null) {
                        z2 = true;
                        valueReference = null;
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i4 || key == null || !this.map.keyEquivalence.equivalent(k3, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference2 = referenceEntry2.getValueReference();
                        if (valueReference2.isLoading()) {
                            z2 = false;
                        } else {
                            V v2 = valueReference2.get();
                            if (v2 == null) {
                                enqueueNotification(key, i3, v2, valueReference2.getWeight(), RemovalCause.COLLECTED);
                            } else if (this.map.isExpired(referenceEntry2, read)) {
                                enqueueNotification(key, i3, v2, valueReference2.getWeight(), RemovalCause.EXPIRED);
                            } else {
                                recordLockedRead(referenceEntry2, read);
                                this.statsCounter.recordHits(1);
                                unlock();
                                postWriteCleanup();
                                return v2;
                            }
                            this.writeQueue.remove(referenceEntry2);
                            this.accessQueue.remove(referenceEntry2);
                            this.count = i5;
                            z2 = true;
                        }
                        valueReference = valueReference2;
                    }
                }
                if (z2) {
                    loadingValueReference = new LoadingValueReference();
                    if (referenceEntry2 == null) {
                        referenceEntry2 = newEntry(k3, i4, referenceEntry);
                        referenceEntry2.setValueReference(loadingValueReference);
                        atomicReferenceArray.set(length, referenceEntry2);
                    } else {
                        referenceEntry2.setValueReference(loadingValueReference);
                    }
                }
                if (!z2) {
                    return waitForLoadingValue(referenceEntry2, k3, valueReference);
                }
                try {
                    synchronized (referenceEntry2) {
                        loadSync = loadSync(k3, i4, loadingValueReference, cacheLoader);
                    }
                    this.statsCounter.recordMisses(1);
                    return loadSync;
                } catch (Throwable th) {
                    this.statsCounter.recordMisses(1);
                    throw th;
                }
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        @GuardedBy("this")
        public ReferenceEntry<K, V> newEntry(K k2, int i3, @CheckForNull ReferenceEntry<K, V> referenceEntry) {
            return this.map.entryFactory.newEntry(this, Preconditions.checkNotNull(k2), i3, referenceEntry);
        }

        public AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray(int i3) {
            return new AtomicReferenceArray<>(i3);
        }

        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                cleanUp();
            }
        }

        public void postWriteCleanup() {
            runUnlockedCleanup();
        }

        @GuardedBy("this")
        public void preWriteCleanup(long j2) {
            runLockedCleanup(j2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
            r1 = r12.getValueReference();
            r10 = r1.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
            if (r10 != null) goto L_0x0094;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
            r7.modCount++;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
            if (r1.isActive() == false) goto L_0x007b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
            enqueueNotification(r15, r16, r10, r1.getWeight(), com.google.common.cache.RemovalCause.COLLECTED);
            setValue(r12, r15, r17, r8);
            r0 = r7.count;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x007b, code lost:
            setValue(r12, r15, r17, r8);
            r0 = r7.count + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0088, code lost:
            r7.count = r0;
            evictEntries(r12);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0094, code lost:
            if (r18 == false) goto L_0x00a0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            recordLockedRead(r12, r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
            return r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            r7.modCount++;
            enqueueNotification(r15, r16, r10, r1.getWeight(), com.google.common.cache.RemovalCause.REPLACED);
            setValue(r12, r15, r17, r8);
            evictEntries(r12);
         */
        @javax.annotation.CheckForNull
        @com.google.errorprone.annotations.CanIgnoreReturnValue
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V put(K r15, int r16, V r17, boolean r18) {
            /*
                r14 = this;
                r7 = r14
                r0 = r15
                r3 = r16
                r14.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r7.map     // Catch:{ all -> 0x001e }
                com.google.common.base.Ticker r1 = r1.ticker     // Catch:{ all -> 0x001e }
                long r8 = r1.read()     // Catch:{ all -> 0x001e }
                r14.preWriteCleanup(r8)     // Catch:{ all -> 0x001e }
                int r1 = r7.count     // Catch:{ all -> 0x001e }
                int r1 = r1 + 1
                int r2 = r7.threshold     // Catch:{ all -> 0x001e }
                if (r1 <= r2) goto L_0x0021
                r14.expand()     // Catch:{ all -> 0x001e }
                goto L_0x0021
            L_0x001e:
                r0 = move-exception
                goto L_0x00e7
            L_0x0021:
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.ReferenceEntry<K, V>> r10 = r7.table     // Catch:{ all -> 0x001e }
                int r1 = r10.length()     // Catch:{ all -> 0x001e }
                int r1 = r1 + -1
                r11 = r3 & r1
                java.lang.Object r1 = r10.get(r11)     // Catch:{ all -> 0x001e }
                com.google.common.cache.ReferenceEntry r1 = (com.google.common.cache.ReferenceEntry) r1     // Catch:{ all -> 0x001e }
                r12 = r1
            L_0x0032:
                r13 = 0
                if (r12 == 0) goto L_0x00c7
                java.lang.Object r2 = r12.getKey()     // Catch:{ all -> 0x001e }
                int r4 = r12.getHash()     // Catch:{ all -> 0x001e }
                if (r4 != r3) goto L_0x00c1
                if (r2 == 0) goto L_0x00c1
                com.google.common.cache.LocalCache<K, V> r4 = r7.map     // Catch:{ all -> 0x001e }
                com.google.common.base.Equivalence<java.lang.Object> r4 = r4.keyEquivalence     // Catch:{ all -> 0x001e }
                boolean r2 = r4.equivalent(r15, r2)     // Catch:{ all -> 0x001e }
                if (r2 == 0) goto L_0x00c1
                com.google.common.cache.LocalCache$ValueReference r1 = r12.getValueReference()     // Catch:{ all -> 0x001e }
                java.lang.Object r10 = r1.get()     // Catch:{ all -> 0x001e }
                if (r10 != 0) goto L_0x0094
                int r2 = r7.modCount     // Catch:{ all -> 0x001e }
                int r2 = r2 + 1
                r7.modCount = r2     // Catch:{ all -> 0x001e }
                boolean r2 = r1.isActive()     // Catch:{ all -> 0x001e }
                if (r2 == 0) goto L_0x007b
                int r5 = r1.getWeight()     // Catch:{ all -> 0x001e }
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x001e }
                r1 = r14
                r2 = r15
                r3 = r16
                r4 = r10
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x001e }
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x001e }
                int r0 = r7.count     // Catch:{ all -> 0x001e }
                goto L_0x0088
            L_0x007b:
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x001e }
                int r0 = r7.count     // Catch:{ all -> 0x001e }
                int r0 = r0 + 1
            L_0x0088:
                r7.count = r0     // Catch:{ all -> 0x001e }
                r14.evictEntries(r12)     // Catch:{ all -> 0x001e }
            L_0x008d:
                r14.unlock()
                r14.postWriteCleanup()
                return r13
            L_0x0094:
                if (r18 == 0) goto L_0x00a0
                r14.recordLockedRead(r12, r8)     // Catch:{ all -> 0x001e }
            L_0x0099:
                r14.unlock()
                r14.postWriteCleanup()
                return r10
            L_0x00a0:
                int r2 = r7.modCount     // Catch:{ all -> 0x001e }
                int r2 = r2 + 1
                r7.modCount = r2     // Catch:{ all -> 0x001e }
                int r5 = r1.getWeight()     // Catch:{ all -> 0x001e }
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x001e }
                r1 = r14
                r2 = r15
                r3 = r16
                r4 = r10
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x001e }
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x001e }
                r14.evictEntries(r12)     // Catch:{ all -> 0x001e }
                goto L_0x0099
            L_0x00c1:
                com.google.common.cache.ReferenceEntry r12 = r12.getNext()     // Catch:{ all -> 0x001e }
                goto L_0x0032
            L_0x00c7:
                int r2 = r7.modCount     // Catch:{ all -> 0x001e }
                int r2 = r2 + 1
                r7.modCount = r2     // Catch:{ all -> 0x001e }
                com.google.common.cache.ReferenceEntry r12 = r14.newEntry(r15, r3, r1)     // Catch:{ all -> 0x001e }
                r1 = r14
                r2 = r12
                r3 = r15
                r4 = r17
                r5 = r8
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x001e }
                r10.set(r11, r12)     // Catch:{ all -> 0x001e }
                int r0 = r7.count     // Catch:{ all -> 0x001e }
                int r0 = r0 + 1
                r7.count = r0     // Catch:{ all -> 0x001e }
                r14.evictEntries(r12)     // Catch:{ all -> 0x001e }
                goto L_0x008d
            L_0x00e7:
                r14.unlock()
                r14.postWriteCleanup()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.put(java.lang.Object, int, java.lang.Object, boolean):java.lang.Object");
        }

        @CanIgnoreReturnValue
        public boolean reclaimKey(ReferenceEntry<K, V> referenceEntry, int i3) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> referenceEntry3 = referenceEntry2; referenceEntry3 != null; referenceEntry3 = referenceEntry3.getNext()) {
                    if (referenceEntry3 == referenceEntry) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(referenceEntry2, referenceEntry3, referenceEntry3.getKey(), i3, referenceEntry3.getValueReference().get(), referenceEntry3.getValueReference(), RemovalCause.COLLECTED));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        @CanIgnoreReturnValue
        public boolean reclaimValue(K k2, int i3, ValueReference<K, V> valueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (referenceEntry2 != null) {
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else if (referenceEntry2.getValueReference() == valueReference) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(referenceEntry, referenceEntry2, key, i3, valueReference.get(), valueReference, RemovalCause.COLLECTED));
                        this.count--;
                        return true;
                    } else {
                        unlock();
                        if (!isHeldByCurrentThread()) {
                            postWriteCleanup();
                        }
                        return false;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
            }
        }

        @GuardedBy("this")
        public void recordLockedRead(ReferenceEntry<K, V> referenceEntry, long j2) {
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j2);
            }
            this.accessQueue.add(referenceEntry);
        }

        public void recordRead(ReferenceEntry<K, V> referenceEntry, long j2) {
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j2);
            }
            this.recencyQueue.add(referenceEntry);
        }

        @GuardedBy("this")
        public void recordWrite(ReferenceEntry<K, V> referenceEntry, int i3, long j2) {
            drainRecencyQueue();
            this.totalWeight += (long) i3;
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j2);
            }
            if (this.map.recordsWrite()) {
                referenceEntry.setWriteTime(j2);
            }
            this.accessQueue.add(referenceEntry);
            this.writeQueue.add(referenceEntry);
        }

        @CheckForNull
        @CanIgnoreReturnValue
        public V refresh(K k2, int i3, CacheLoader<? super K, V> cacheLoader, boolean z2) {
            LoadingValueReference insertLoadingValueReference = insertLoadingValueReference(k2, i3, z2);
            if (insertLoadingValueReference == null) {
                return null;
            }
            ListenableFuture<V> loadAsync = loadAsync(k2, i3, insertLoadingValueReference, cacheLoader);
            if (loadAsync.isDone()) {
                try {
                    return Uninterruptibles.getUninterruptibly(loadAsync);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        @CheckForNull
        public V remove(Object obj, int i3) {
            RemovalCause removalCause;
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(obj, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference = referenceEntry2.getValueReference();
                        V v2 = valueReference.get();
                        if (v2 != null) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else if (valueReference.isActive()) {
                            removalCause = RemovalCause.COLLECTED;
                        }
                        RemovalCause removalCause2 = removalCause;
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(referenceEntry, referenceEntry2, key, i3, v2, valueReference, removalCause2));
                        this.count--;
                        unlock();
                        postWriteCleanup();
                        return v2;
                    }
                }
                unlock();
                postWriteCleanup();
                return null;
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
                throw th;
            }
        }

        @GuardedBy("this")
        public void removeCollectedEntry(ReferenceEntry<K, V> referenceEntry) {
            enqueueNotification(referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry.getValueReference().get(), referenceEntry.getValueReference().getWeight(), RemovalCause.COLLECTED);
            this.writeQueue.remove(referenceEntry);
            this.accessQueue.remove(referenceEntry);
        }

        @CanIgnoreReturnValue
        @VisibleForTesting
        @GuardedBy("this")
        public boolean removeEntry(ReferenceEntry<K, V> referenceEntry, int i3, RemovalCause removalCause) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            int length = (atomicReferenceArray.length() - 1) & i3;
            ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
            for (ReferenceEntry<K, V> referenceEntry3 = referenceEntry2; referenceEntry3 != null; referenceEntry3 = referenceEntry3.getNext()) {
                if (referenceEntry3 == referenceEntry) {
                    this.modCount++;
                    atomicReferenceArray.set(length, removeValueFromChain(referenceEntry2, referenceEntry3, referenceEntry3.getKey(), i3, referenceEntry3.getValueReference().get(), referenceEntry3.getValueReference(), removalCause));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        @CheckForNull
        @GuardedBy("this")
        public ReferenceEntry<K, V> removeEntryFromChain(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            int i3 = this.count;
            ReferenceEntry<K, V> next = referenceEntry2.getNext();
            while (referenceEntry != referenceEntry2) {
                ReferenceEntry<K, V> copyEntry = copyEntry(referenceEntry, next);
                if (copyEntry != null) {
                    next = copyEntry;
                } else {
                    removeCollectedEntry(referenceEntry);
                    i3--;
                }
                referenceEntry = referenceEntry.getNext();
            }
            this.count = i3;
            return next;
        }

        @CanIgnoreReturnValue
        public boolean removeLoadingValue(K k2, int i3, LoadingValueReference<K, V> loadingValueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i3;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(k2, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else if (referenceEntry2.getValueReference() == loadingValueReference) {
                        if (loadingValueReference.isActive()) {
                            referenceEntry2.setValueReference(loadingValueReference.getOldValue());
                        } else {
                            atomicReferenceArray.set(length, removeEntryFromChain(referenceEntry, referenceEntry2));
                        }
                        return true;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        @CheckForNull
        @GuardedBy("this")
        public ReferenceEntry<K, V> removeValueFromChain(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, @CheckForNull K k2, int i3, V v2, ValueReference<K, V> valueReference, RemovalCause removalCause) {
            enqueueNotification(k2, i3, v2, valueReference.getWeight(), removalCause);
            this.writeQueue.remove(referenceEntry2);
            this.accessQueue.remove(referenceEntry2);
            if (!valueReference.isLoading()) {
                return removeEntryFromChain(referenceEntry, referenceEntry2);
            }
            valueReference.notifyNewValue(null);
            return referenceEntry;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
            r16 = r13.getValueReference();
            r6 = r16.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0047, code lost:
            if (r6 != null) goto L_0x0073;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x004d, code lost:
            if (r16.isActive() == false) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
            r9.modCount++;
            r10.set(r12, removeValueFromChain(r2, r13, r4, r19, r6, r16, com.google.common.cache.RemovalCause.COLLECTED));
            r9.count--;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x007d, code lost:
            if (r9.map.valueEquivalence.equivalent(r20, r6) == false) goto L_0x00aa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x007f, code lost:
            r9.modCount++;
            enqueueNotification(r18, r19, r6, r16.getWeight(), com.google.common.cache.RemovalCause.REPLACED);
            setValue(r13, r18, r21, r7);
            evictEntries(r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00a3, code lost:
            unlock();
            postWriteCleanup();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a9, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            recordLockedRead(r13, r7);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean replace(K r18, int r19, V r20, V r21) {
            /*
                r17 = this;
                r9 = r17
                r0 = r19
                r17.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r9.map     // Catch:{ all -> 0x006a }
                com.google.common.base.Ticker r1 = r1.ticker     // Catch:{ all -> 0x006a }
                long r7 = r1.read()     // Catch:{ all -> 0x006a }
                r9.preWriteCleanup(r7)     // Catch:{ all -> 0x006a }
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.ReferenceEntry<K, V>> r10 = r9.table     // Catch:{ all -> 0x006a }
                int r1 = r10.length()     // Catch:{ all -> 0x006a }
                r11 = 1
                int r1 = r1 - r11
                r12 = r0 & r1
                java.lang.Object r1 = r10.get(r12)     // Catch:{ all -> 0x006a }
                r2 = r1
                com.google.common.cache.ReferenceEntry r2 = (com.google.common.cache.ReferenceEntry) r2     // Catch:{ all -> 0x006a }
                r13 = r2
            L_0x0024:
                r14 = 0
                if (r13 == 0) goto L_0x006c
                java.lang.Object r4 = r13.getKey()     // Catch:{ all -> 0x006a }
                int r1 = r13.getHash()     // Catch:{ all -> 0x006a }
                if (r1 != r0) goto L_0x00b1
                if (r4 == 0) goto L_0x00b1
                com.google.common.cache.LocalCache<K, V> r1 = r9.map     // Catch:{ all -> 0x006a }
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.keyEquivalence     // Catch:{ all -> 0x006a }
                r15 = r18
                boolean r1 = r1.equivalent(r15, r4)     // Catch:{ all -> 0x006a }
                if (r1 == 0) goto L_0x00ae
                com.google.common.cache.LocalCache$ValueReference r16 = r13.getValueReference()     // Catch:{ all -> 0x006a }
                java.lang.Object r6 = r16.get()     // Catch:{ all -> 0x006a }
                if (r6 != 0) goto L_0x0073
                boolean r1 = r16.isActive()     // Catch:{ all -> 0x006a }
                if (r1 == 0) goto L_0x006c
                int r1 = r9.modCount     // Catch:{ all -> 0x006a }
                int r1 = r1 + r11
                r9.modCount = r1     // Catch:{ all -> 0x006a }
                com.google.common.cache.RemovalCause r8 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x006a }
                r1 = r17
                r3 = r13
                r5 = r19
                r7 = r16
                com.google.common.cache.ReferenceEntry r0 = r1.removeValueFromChain(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x006a }
                int r1 = r9.count     // Catch:{ all -> 0x006a }
                int r1 = r1 - r11
                r10.set(r12, r0)     // Catch:{ all -> 0x006a }
                r9.count = r1     // Catch:{ all -> 0x006a }
                goto L_0x006c
            L_0x006a:
                r0 = move-exception
                goto L_0x00ba
            L_0x006c:
                r17.unlock()
                r17.postWriteCleanup()
                return r14
            L_0x0073:
                com.google.common.cache.LocalCache<K, V> r1 = r9.map     // Catch:{ all -> 0x006a }
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.valueEquivalence     // Catch:{ all -> 0x006a }
                r3 = r20
                boolean r1 = r1.equivalent(r3, r6)     // Catch:{ all -> 0x006a }
                if (r1 == 0) goto L_0x00aa
                int r1 = r9.modCount     // Catch:{ all -> 0x006a }
                int r1 = r1 + r11
                r9.modCount = r1     // Catch:{ all -> 0x006a }
                int r5 = r16.getWeight()     // Catch:{ all -> 0x006a }
                com.google.common.cache.RemovalCause r10 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x006a }
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r6
                r6 = r10
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x006a }
                r1 = r17
                r2 = r13
                r3 = r18
                r4 = r21
                r5 = r7
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x006a }
                r9.evictEntries(r13)     // Catch:{ all -> 0x006a }
                r17.unlock()
                r17.postWriteCleanup()
                return r11
            L_0x00aa:
                r9.recordLockedRead(r13, r7)     // Catch:{ all -> 0x006a }
                goto L_0x006c
            L_0x00ae:
                r3 = r20
                goto L_0x00b4
            L_0x00b1:
                r15 = r18
                goto L_0x00ae
            L_0x00b4:
                com.google.common.cache.ReferenceEntry r13 = r13.getNext()     // Catch:{ all -> 0x006a }
                goto L_0x0024
            L_0x00ba:
                r17.unlock()
                r17.postWriteCleanup()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.replace(java.lang.Object, int, java.lang.Object, java.lang.Object):boolean");
        }

        public void runLockedCleanup(long j2) {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                    expireEntries(j2);
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        public void runUnlockedCleanup() {
            if (!isHeldByCurrentThread()) {
                this.map.processPendingNotifications();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
            r2 = refresh(r4, r5, r9, true);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V scheduleRefresh(com.google.common.cache.ReferenceEntry<K, V> r3, K r4, int r5, V r6, long r7, com.google.common.cache.CacheLoader<? super K, V> r9) {
            /*
                r2 = this;
                com.google.common.cache.LocalCache<K, V> r0 = r2.map
                boolean r0 = r0.refreshes()
                if (r0 == 0) goto L_0x0027
                long r0 = r3.getWriteTime()
                long r7 = r7 - r0
                com.google.common.cache.LocalCache<K, V> r0 = r2.map
                long r0 = r0.refreshNanos
                int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r7 <= 0) goto L_0x0027
                com.google.common.cache.LocalCache$ValueReference r3 = r3.getValueReference()
                boolean r3 = r3.isLoading()
                if (r3 != 0) goto L_0x0027
                r3 = 1
                java.lang.Object r2 = r2.refresh(r4, r5, r9, r3)
                if (r2 == 0) goto L_0x0027
                return r2
            L_0x0027:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.scheduleRefresh(com.google.common.cache.ReferenceEntry, java.lang.Object, int, java.lang.Object, long, com.google.common.cache.CacheLoader):java.lang.Object");
        }

        @GuardedBy("this")
        public void setValue(ReferenceEntry<K, V> referenceEntry, K k2, V v2, long j2) {
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            int weigh = this.map.weigher.weigh(k2, v2);
            Preconditions.checkState(weigh >= 0, "Weights must be non-negative");
            referenceEntry.setValueReference(this.map.valueStrength.referenceValue(this, referenceEntry, v2, weigh));
            recordWrite(referenceEntry, weigh, j2);
            valueReference.notifyNewValue(v2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
            r1 = r14.getValueReference();
            r4 = r1.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0058, code lost:
            if (r18 == r1) goto L_0x0076;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
            if (r4 != null) goto L_0x0061;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
            if (r1 == com.google.common.cache.LocalCache.UNSET) goto L_0x0061;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0061, code lost:
            enqueueNotification(r16, r17, r19, 0, com.google.common.cache.RemovalCause.REPLACED);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x006e, code lost:
            unlock();
            postWriteCleanup();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r7.modCount++;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x007f, code lost:
            if (r18.isActive() == false) goto L_0x0098;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
            if (r4 != null) goto L_0x0087;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
            r1 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0087, code lost:
            r1 = com.google.common.cache.RemovalCause.REPLACED;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x008a, code lost:
            enqueueNotification(r16, r17, r4, r18.getWeight(), r1);
            r11 = r11 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0098, code lost:
            setValue(r14, r16, r19, r8);
            r7.count = r11;
            evictEntries(r14);
         */
        @com.google.errorprone.annotations.CanIgnoreReturnValue
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean storeLoadedValue(K r16, int r17, com.google.common.cache.LocalCache.LoadingValueReference<K, V> r18, V r19) {
            /*
                r15 = this;
                r7 = r15
                r0 = r16
                r3 = r17
                r15.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r7.map     // Catch:{ all -> 0x0023 }
                com.google.common.base.Ticker r1 = r1.ticker     // Catch:{ all -> 0x0023 }
                long r8 = r1.read()     // Catch:{ all -> 0x0023 }
                r15.preWriteCleanup(r8)     // Catch:{ all -> 0x0023 }
                int r1 = r7.count     // Catch:{ all -> 0x0023 }
                r10 = 1
                int r1 = r1 + r10
                int r2 = r7.threshold     // Catch:{ all -> 0x0023 }
                if (r1 <= r2) goto L_0x0021
                r15.expand()     // Catch:{ all -> 0x0023 }
                int r1 = r7.count     // Catch:{ all -> 0x0023 }
                int r1 = r1 + r10
            L_0x0021:
                r11 = r1
                goto L_0x0026
            L_0x0023:
                r0 = move-exception
                goto L_0x00d1
            L_0x0026:
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.ReferenceEntry<K, V>> r12 = r7.table     // Catch:{ all -> 0x0023 }
                int r1 = r12.length()     // Catch:{ all -> 0x0023 }
                int r1 = r1 - r10
                r13 = r3 & r1
                java.lang.Object r1 = r12.get(r13)     // Catch:{ all -> 0x0023 }
                com.google.common.cache.ReferenceEntry r1 = (com.google.common.cache.ReferenceEntry) r1     // Catch:{ all -> 0x0023 }
                r14 = r1
            L_0x0036:
                if (r14 == 0) goto L_0x00b5
                java.lang.Object r2 = r14.getKey()     // Catch:{ all -> 0x0023 }
                int r4 = r14.getHash()     // Catch:{ all -> 0x0023 }
                if (r4 != r3) goto L_0x00ae
                if (r2 == 0) goto L_0x00ae
                com.google.common.cache.LocalCache<K, V> r4 = r7.map     // Catch:{ all -> 0x0023 }
                com.google.common.base.Equivalence<java.lang.Object> r4 = r4.keyEquivalence     // Catch:{ all -> 0x0023 }
                boolean r2 = r4.equivalent(r0, r2)     // Catch:{ all -> 0x0023 }
                if (r2 == 0) goto L_0x00ae
                com.google.common.cache.LocalCache$ValueReference r1 = r14.getValueReference()     // Catch:{ all -> 0x0023 }
                java.lang.Object r4 = r1.get()     // Catch:{ all -> 0x0023 }
                r2 = r18
                if (r2 == r1) goto L_0x0076
                if (r4 != 0) goto L_0x0061
                com.google.common.cache.LocalCache$ValueReference<java.lang.Object, java.lang.Object> r5 = com.google.common.cache.LocalCache.UNSET     // Catch:{ all -> 0x0023 }
                if (r1 == r5) goto L_0x0061
                goto L_0x0076
            L_0x0061:
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x0023 }
                r5 = 0
                r1 = r15
                r2 = r16
                r3 = r17
                r4 = r19
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0023 }
                r15.unlock()
                r15.postWriteCleanup()
                r0 = 0
                return r0
            L_0x0076:
                int r1 = r7.modCount     // Catch:{ all -> 0x0023 }
                int r1 = r1 + r10
                r7.modCount = r1     // Catch:{ all -> 0x0023 }
                boolean r1 = r18.isActive()     // Catch:{ all -> 0x0023 }
                if (r1 == 0) goto L_0x0098
                if (r4 != 0) goto L_0x0087
                com.google.common.cache.RemovalCause r1 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x0023 }
            L_0x0085:
                r6 = r1
                goto L_0x008a
            L_0x0087:
                com.google.common.cache.RemovalCause r1 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x0023 }
                goto L_0x0085
            L_0x008a:
                int r5 = r18.getWeight()     // Catch:{ all -> 0x0023 }
                r1 = r15
                r2 = r16
                r3 = r17
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0023 }
                int r11 = r11 + -1
            L_0x0098:
                r1 = r15
                r2 = r14
                r3 = r16
                r4 = r19
                r5 = r8
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x0023 }
                r7.count = r11     // Catch:{ all -> 0x0023 }
                r15.evictEntries(r14)     // Catch:{ all -> 0x0023 }
            L_0x00a7:
                r15.unlock()
                r15.postWriteCleanup()
                return r10
            L_0x00ae:
                r2 = r18
                com.google.common.cache.ReferenceEntry r14 = r14.getNext()     // Catch:{ all -> 0x0023 }
                goto L_0x0036
            L_0x00b5:
                int r2 = r7.modCount     // Catch:{ all -> 0x0023 }
                int r2 = r2 + r10
                r7.modCount = r2     // Catch:{ all -> 0x0023 }
                com.google.common.cache.ReferenceEntry r14 = r15.newEntry(r0, r3, r1)     // Catch:{ all -> 0x0023 }
                r1 = r15
                r2 = r14
                r3 = r16
                r4 = r19
                r5 = r8
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x0023 }
                r12.set(r13, r14)     // Catch:{ all -> 0x0023 }
                r7.count = r11     // Catch:{ all -> 0x0023 }
                r15.evictEntries(r14)     // Catch:{ all -> 0x0023 }
                goto L_0x00a7
            L_0x00d1:
                r15.unlock()
                r15.postWriteCleanup()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.storeLoadedValue(java.lang.Object, int, com.google.common.cache.LocalCache$LoadingValueReference, java.lang.Object):boolean");
        }

        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        public void tryExpireEntries(long j2) {
            if (tryLock()) {
                try {
                    expireEntries(j2);
                } finally {
                    unlock();
                }
            }
        }

        public V waitForLoadingValue(ReferenceEntry<K, V> referenceEntry, K k2, ValueReference<K, V> valueReference) throws ExecutionException {
            if (valueReference.isLoading()) {
                Preconditions.checkState(!Thread.holdsLock(referenceEntry), "Recursive load of: %s", (Object) k2);
                try {
                    V waitForValue = valueReference.waitForValue();
                    if (waitForValue != null) {
                        recordRead(referenceEntry, this.map.ticker.read());
                        return waitForValue;
                    }
                    throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + k2 + JwtUtilsKt.JWT_DELIMITER);
                } finally {
                    this.statsCounter.recordMisses(1);
                }
            } else {
                throw new AssertionError();
            }
        }

        @CheckForNull
        public V get(Object obj, int i3) {
            try {
                if (this.count != 0) {
                    long read = this.map.ticker.read();
                    ReferenceEntry liveEntry = getLiveEntry(obj, i3, read);
                    if (liveEntry == null) {
                        return null;
                    }
                    Object obj2 = liveEntry.getValueReference().get();
                    if (obj2 != null) {
                        recordRead(liveEntry, read);
                        V scheduleRefresh = scheduleRefresh(liveEntry, liveEntry.getKey(), i3, obj2, read, this.map.defaultLoader);
                        postReadCleanup();
                        return scheduleRefresh;
                    }
                    tryDrainReferenceQueues();
                }
                postReadCleanup();
                return null;
            } finally {
                postReadCleanup();
            }
        }

        public boolean remove(Object obj, int i3, Object obj2) {
            RemovalCause removalCause;
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                boolean z2 = true;
                int length = (atomicReferenceArray.length() - 1) & i3;
                ReferenceEntry referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry referenceEntry2 = referenceEntry;
                while (true) {
                    if (referenceEntry2 == null) {
                        break;
                    }
                    Object key = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() != i3 || key == null || !this.map.keyEquivalence.equivalent(obj, key)) {
                        referenceEntry2 = referenceEntry2.getNext();
                    } else {
                        ValueReference valueReference = referenceEntry2.getValueReference();
                        Object obj3 = valueReference.get();
                        if (this.map.valueEquivalence.equivalent(obj2, obj3)) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else if (obj3 == null && valueReference.isActive()) {
                            removalCause = RemovalCause.COLLECTED;
                        }
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(referenceEntry, referenceEntry2, key, i3, obj3, valueReference, removalCause));
                        this.count--;
                        if (removalCause != RemovalCause.EXPLICIT) {
                            z2 = false;
                        }
                        unlock();
                        postWriteCleanup();
                        return z2;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
                throw th;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
            r15 = r12.getValueReference();
            r16 = r15.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0047, code lost:
            if (r16 != null) goto L_0x0076;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x004d, code lost:
            if (r15.isActive() == false) goto L_0x006f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
            r9.modCount++;
            r10.set(r11, removeValueFromChain(r2, r12, r4, r19, r16, r15, com.google.common.cache.RemovalCause.COLLECTED));
            r9.count--;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r9.modCount++;
            enqueueNotification(r18, r19, r16, r15.getWeight(), com.google.common.cache.RemovalCause.REPLACED);
            setValue(r12, r18, r20, r7);
            evictEntries(r12);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x009b, code lost:
            unlock();
            postWriteCleanup();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a1, code lost:
            return r16;
         */
        @javax.annotation.CheckForNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V replace(K r18, int r19, V r20) {
            /*
                r17 = this;
                r9 = r17
                r0 = r19
                r17.lock()
                com.google.common.cache.LocalCache<K, V> r1 = r9.map     // Catch:{ all -> 0x006d }
                com.google.common.base.Ticker r1 = r1.ticker     // Catch:{ all -> 0x006d }
                long r7 = r1.read()     // Catch:{ all -> 0x006d }
                r9.preWriteCleanup(r7)     // Catch:{ all -> 0x006d }
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.ReferenceEntry<K, V>> r10 = r9.table     // Catch:{ all -> 0x006d }
                int r1 = r10.length()     // Catch:{ all -> 0x006d }
                int r1 = r1 + -1
                r11 = r0 & r1
                java.lang.Object r1 = r10.get(r11)     // Catch:{ all -> 0x006d }
                r2 = r1
                com.google.common.cache.ReferenceEntry r2 = (com.google.common.cache.ReferenceEntry) r2     // Catch:{ all -> 0x006d }
                r12 = r2
            L_0x0024:
                r13 = 0
                if (r12 == 0) goto L_0x006f
                java.lang.Object r4 = r12.getKey()     // Catch:{ all -> 0x006d }
                int r1 = r12.getHash()     // Catch:{ all -> 0x006d }
                if (r1 != r0) goto L_0x00a2
                if (r4 == 0) goto L_0x00a2
                com.google.common.cache.LocalCache<K, V> r1 = r9.map     // Catch:{ all -> 0x006d }
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.keyEquivalence     // Catch:{ all -> 0x006d }
                r14 = r18
                boolean r1 = r1.equivalent(r14, r4)     // Catch:{ all -> 0x006d }
                if (r1 == 0) goto L_0x00a4
                com.google.common.cache.LocalCache$ValueReference r15 = r12.getValueReference()     // Catch:{ all -> 0x006d }
                java.lang.Object r16 = r15.get()     // Catch:{ all -> 0x006d }
                if (r16 != 0) goto L_0x0076
                boolean r1 = r15.isActive()     // Catch:{ all -> 0x006d }
                if (r1 == 0) goto L_0x006f
                int r1 = r9.modCount     // Catch:{ all -> 0x006d }
                int r1 = r1 + 1
                r9.modCount = r1     // Catch:{ all -> 0x006d }
                com.google.common.cache.RemovalCause r8 = com.google.common.cache.RemovalCause.COLLECTED     // Catch:{ all -> 0x006d }
                r1 = r17
                r3 = r12
                r5 = r19
                r6 = r16
                r7 = r15
                com.google.common.cache.ReferenceEntry r0 = r1.removeValueFromChain(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x006d }
                int r1 = r9.count     // Catch:{ all -> 0x006d }
                int r1 = r1 + -1
                r10.set(r11, r0)     // Catch:{ all -> 0x006d }
                r9.count = r1     // Catch:{ all -> 0x006d }
                goto L_0x006f
            L_0x006d:
                r0 = move-exception
                goto L_0x00aa
            L_0x006f:
                r17.unlock()
                r17.postWriteCleanup()
                return r13
            L_0x0076:
                int r1 = r9.modCount     // Catch:{ all -> 0x006d }
                int r1 = r1 + 1
                r9.modCount = r1     // Catch:{ all -> 0x006d }
                int r5 = r15.getWeight()     // Catch:{ all -> 0x006d }
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.REPLACED     // Catch:{ all -> 0x006d }
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r16
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x006d }
                r1 = r17
                r2 = r12
                r3 = r18
                r4 = r20
                r5 = r7
                r1.setValue(r2, r3, r4, r5)     // Catch:{ all -> 0x006d }
                r9.evictEntries(r12)     // Catch:{ all -> 0x006d }
                r17.unlock()
                r17.postWriteCleanup()
                return r16
            L_0x00a2:
                r14 = r18
            L_0x00a4:
                com.google.common.cache.ReferenceEntry r12 = r12.getNext()     // Catch:{ all -> 0x006d }
                goto L_0x0024
            L_0x00aa:
                r17.unlock()
                r17.postWriteCleanup()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.replace(java.lang.Object, int, java.lang.Object):java.lang.Object");
        }
    }
}
