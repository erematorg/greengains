package com.fasterxml.jackson.databind.util.internal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class PrivateMaxEntriesMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final long MAXIMUM_CAPACITY = 9223372034707292160L;
    static final int NCPU;
    static final int NUMBER_OF_READ_BUFFERS;
    static final int READ_BUFFERS_MASK;
    static final int READ_BUFFER_DRAIN_THRESHOLD = 8;
    static final int READ_BUFFER_INDEX_MASK = 15;
    static final int READ_BUFFER_SIZE = 16;
    static final int READ_BUFFER_THRESHOLD = 4;
    static final int WRITE_BUFFER_DRAIN_THRESHOLD = 16;
    static final long serialVersionUID = 1;
    final AtomicLong capacity;
    final int concurrencyLevel;
    final ConcurrentMap<K, Node<K, V>> data;
    final AtomicReference<DrainStatus> drainStatus;
    transient Set<Map.Entry<K, V>> entrySet;
    final LinkedDeque<Node<K, V>> evictionDeque;
    final Lock evictionLock;
    transient Set<K> keySet;
    final AtomicLongArray readBufferDrainAtWriteCount;
    final long[] readBufferReadCount;
    final AtomicLongArray readBufferWriteCount;
    final AtomicReferenceArray<Node<K, V>> readBuffers;
    transient Collection<V> values;
    final AtomicLong weightedSize;
    final Queue<Runnable> writeBuffer;

    public final class AddTask implements Runnable {
        final Node<K, V> node;
        final int weight;

        public AddTask(Node<K, V> node2, int i3) {
            this.weight = i3;
            this.node = node2;
        }

        public void run() {
            AtomicLong atomicLong = PrivateMaxEntriesMap.this.weightedSize;
            atomicLong.lazySet(atomicLong.get() + ((long) this.weight));
            if (((WeightedValue) this.node.get()).isAlive()) {
                PrivateMaxEntriesMap.this.evictionDeque.add(this.node);
                PrivateMaxEntriesMap.this.evict();
            }
        }
    }

    public static final class Builder<K, V> {
        static final int DEFAULT_CONCURRENCY_LEVEL = 16;
        static final int DEFAULT_INITIAL_CAPACITY = 16;
        long capacity = -1;
        int concurrencyLevel = 16;
        int initialCapacity = 16;

        public PrivateMaxEntriesMap<K, V> build() {
            PrivateMaxEntriesMap.checkState(this.capacity >= 0);
            return new PrivateMaxEntriesMap<>(this);
        }

        public Builder<K, V> concurrencyLevel(int i3) {
            PrivateMaxEntriesMap.checkArgument(i3 > 0);
            this.concurrencyLevel = i3;
            return this;
        }

        public Builder<K, V> initialCapacity(int i3) {
            PrivateMaxEntriesMap.checkArgument(i3 >= 0);
            this.initialCapacity = i3;
            return this;
        }

        public Builder<K, V> maximumCapacity(long j2) {
            PrivateMaxEntriesMap.checkArgument(j2 >= 0);
            this.capacity = j2;
            return this;
        }
    }

    public enum DrainStatus {
        IDLE {
            public boolean shouldDrainBuffers(boolean z2) {
                return !z2;
            }
        },
        REQUIRED {
            public boolean shouldDrainBuffers(boolean z2) {
                return true;
            }
        },
        PROCESSING {
            public boolean shouldDrainBuffers(boolean z2) {
                return false;
            }
        };

        public abstract boolean shouldDrainBuffers(boolean z2);
    }

    public final class EntryIterator implements Iterator<Map.Entry<K, V>> {
        Node<K, V> current;
        final Iterator<Node<K, V>> iterator;

        public EntryIterator() {
            this.iterator = PrivateMaxEntriesMap.this.data.values().iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public void remove() {
            PrivateMaxEntriesMap.checkState(this.current != null);
            PrivateMaxEntriesMap.this.remove(this.current.key);
            this.current = null;
        }

        public Map.Entry<K, V> next() {
            this.current = this.iterator.next();
            return new WriteThroughEntry(this.current);
        }
    }

    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        final PrivateMaxEntriesMap<K, V> map;

        public EntrySet() {
            this.map = PrivateMaxEntriesMap.this;
        }

        public void clear() {
            this.map.clear();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Node node = this.map.data.get(entry.getKey());
            return node != null && node.getValue().equals(entry.getValue());
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.map.remove(entry.getKey(), entry.getValue());
        }

        public int size() {
            return this.map.size();
        }

        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException("ConcurrentLinkedHashMap does not allow add to be called on entrySet()");
        }
    }

    public final class KeyIterator implements Iterator<K> {
        K current;
        final Iterator<K> iterator;

        public KeyIterator() {
            this.iterator = PrivateMaxEntriesMap.this.data.keySet().iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public K next() {
            K next = this.iterator.next();
            this.current = next;
            return next;
        }

        public void remove() {
            PrivateMaxEntriesMap.checkState(this.current != null);
            PrivateMaxEntriesMap.this.remove(this.current);
            this.current = null;
        }
    }

    public final class KeySet extends AbstractSet<K> {
        final PrivateMaxEntriesMap<K, V> map;

        public KeySet() {
            this.map = PrivateMaxEntriesMap.this;
        }

        public void clear() {
            this.map.clear();
        }

        public boolean contains(Object obj) {
            return PrivateMaxEntriesMap.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }

        public int size() {
            return this.map.size();
        }

        public Object[] toArray() {
            return this.map.data.keySet().toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return this.map.data.keySet().toArray(tArr);
        }
    }

    public static final class Node<K, V> extends AtomicReference<WeightedValue<V>> implements Linked<Node<K, V>> {
        final K key;
        Node<K, V> next;
        Node<K, V> prev;

        public Node(K k2, WeightedValue<V> weightedValue) {
            super(weightedValue);
            this.key = k2;
        }

        public V getValue() {
            return ((WeightedValue) get()).value;
        }

        public Node<K, V> getNext() {
            return this.next;
        }

        public Node<K, V> getPrevious() {
            return this.prev;
        }

        public void setNext(Node<K, V> node) {
            this.next = node;
        }

        public void setPrevious(Node<K, V> node) {
            this.prev = node;
        }
    }

    public final class RemovalTask implements Runnable {
        final Node<K, V> node;

        public RemovalTask(Node<K, V> node2) {
            this.node = node2;
        }

        public void run() {
            PrivateMaxEntriesMap.this.evictionDeque.remove(this.node);
            PrivateMaxEntriesMap.this.makeDead(this.node);
        }
    }

    public static final class SerializationProxy<K, V> implements Serializable {
        static final long serialVersionUID = 1;
        final long capacity;
        final int concurrencyLevel;
        final Map<K, V> data;

        public SerializationProxy(PrivateMaxEntriesMap<K, V> privateMaxEntriesMap) {
            this.concurrencyLevel = privateMaxEntriesMap.concurrencyLevel;
            this.data = new HashMap(privateMaxEntriesMap);
            this.capacity = privateMaxEntriesMap.capacity.get();
        }

        public Object readResolve() {
            PrivateMaxEntriesMap build = new Builder().maximumCapacity(this.capacity).build();
            build.putAll(this.data);
            return build;
        }
    }

    public final class UpdateTask implements Runnable {
        final Node<K, V> node;
        final int weightDifference;

        public UpdateTask(Node<K, V> node2, int i3) {
            this.weightDifference = i3;
            this.node = node2;
        }

        public void run() {
            AtomicLong atomicLong = PrivateMaxEntriesMap.this.weightedSize;
            atomicLong.lazySet(atomicLong.get() + ((long) this.weightDifference));
            PrivateMaxEntriesMap.this.applyRead(this.node);
            PrivateMaxEntriesMap.this.evict();
        }
    }

    public final class ValueIterator implements Iterator<V> {
        Node<K, V> current;
        final Iterator<Node<K, V>> iterator;

        public ValueIterator() {
            this.iterator = PrivateMaxEntriesMap.this.data.values().iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public V next() {
            Node<K, V> next = this.iterator.next();
            this.current = next;
            return next.getValue();
        }

        public void remove() {
            PrivateMaxEntriesMap.checkState(this.current != null);
            PrivateMaxEntriesMap.this.remove(this.current.key);
            this.current = null;
        }
    }

    public final class Values extends AbstractCollection<V> {
        public Values() {
        }

        public void clear() {
            PrivateMaxEntriesMap.this.clear();
        }

        public boolean contains(Object obj) {
            return PrivateMaxEntriesMap.this.containsValue(obj);
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return PrivateMaxEntriesMap.this.size();
        }
    }

    public static final class WeightedValue<V> {
        final V value;
        final int weight;

        public WeightedValue(V v2, int i3) {
            this.weight = i3;
            this.value = v2;
        }

        public boolean contains(Object obj) {
            V v2 = this.value;
            return obj == v2 || v2.equals(obj);
        }

        public boolean isAlive() {
            return this.weight > 0;
        }
    }

    public final class WriteThroughEntry extends AbstractMap.SimpleEntry<K, V> {
        static final long serialVersionUID = 1;

        public WriteThroughEntry(Node<K, V> node) {
            super(node.key, node.getValue());
        }

        public V setValue(V v2) {
            PrivateMaxEntriesMap.this.put(getKey(), v2);
            return super.setValue(v2);
        }

        public Object writeReplace() {
            return new AbstractMap.SimpleEntry(this);
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        NCPU = availableProcessors;
        int min = Math.min(4, ceilingNextPowerOfTwo(availableProcessors));
        NUMBER_OF_READ_BUFFERS = min;
        READ_BUFFERS_MASK = min - 1;
    }

    public static int ceilingNextPowerOfTwo(int i3) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i3 - 1));
    }

    public static void checkArgument(boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNotNull(Object obj) {
        obj.getClass();
    }

    public static void checkState(boolean z2) {
        if (!z2) {
            throw new IllegalStateException();
        }
    }

    private static int readBufferIndex(int i3, int i4) {
        return (i3 * 16) + i4;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    public void afterRead(Node<K, V> node) {
        int readBufferIndex = readBufferIndex();
        drainOnReadIfNeeded(readBufferIndex, recordRead(readBufferIndex, node));
    }

    public void afterWrite(Runnable runnable) {
        this.writeBuffer.add(runnable);
        this.drainStatus.lazySet(DrainStatus.REQUIRED);
        tryToDrainBuffers();
    }

    public void applyRead(Node<K, V> node) {
        if (this.evictionDeque.contains((Linked<?>) node)) {
            this.evictionDeque.moveToBack(node);
        }
    }

    public long capacity() {
        return this.capacity.get();
    }

    public void clear() {
        this.evictionLock.lock();
        while (true) {
            try {
                Node poll = this.evictionDeque.poll();
                if (poll == null) {
                    break;
                }
                this.data.remove(poll.key, poll);
                makeDead(poll);
            } catch (Throwable th) {
                this.evictionLock.unlock();
                throw th;
            }
        }
        for (int i3 = 0; i3 < this.readBuffers.length(); i3++) {
            this.readBuffers.lazySet(i3, (Object) null);
        }
        while (true) {
            Runnable poll2 = this.writeBuffer.poll();
            if (poll2 != null) {
                poll2.run();
            } else {
                this.evictionLock.unlock();
                return;
            }
        }
    }

    public boolean containsKey(Object obj) {
        return this.data.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        checkNotNull(obj);
        for (Node<K, V> value : this.data.values()) {
            if (value.getValue().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public void drainBuffers() {
        drainReadBuffers();
        drainWriteBuffer();
    }

    public void drainOnReadIfNeeded(int i3, long j2) {
        if (this.drainStatus.get().shouldDrainBuffers(j2 - this.readBufferDrainAtWriteCount.get(i3) < 4)) {
            tryToDrainBuffers();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
        r3 = readBufferIndex(r9, (int) (r8.readBufferReadCount[r9] & 15));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drainReadBuffer(int r9) {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicLongArray r0 = r8.readBufferWriteCount
            long r0 = r0.get(r9)
            r2 = 0
        L_0x0007:
            r3 = 8
            if (r2 >= r3) goto L_0x0037
            long[] r3 = r8.readBufferReadCount
            r3 = r3[r9]
            r5 = 15
            long r3 = r3 & r5
            int r3 = (int) r3
            int r3 = readBufferIndex(r9, r3)
            java.util.concurrent.atomic.AtomicReferenceArray<com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$Node<K, V>> r4 = r8.readBuffers
            java.lang.Object r4 = r4.get(r3)
            com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$Node r4 = (com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.Node) r4
            if (r4 != 0) goto L_0x0022
            goto L_0x0037
        L_0x0022:
            java.util.concurrent.atomic.AtomicReferenceArray<com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$Node<K, V>> r5 = r8.readBuffers
            r6 = 0
            r5.lazySet(r3, r6)
            r8.applyRead(r4)
            long[] r3 = r8.readBufferReadCount
            r4 = r3[r9]
            r6 = 1
            long r4 = r4 + r6
            r3[r9] = r4
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0037:
            java.util.concurrent.atomic.AtomicLongArray r8 = r8.readBufferDrainAtWriteCount
            r8.lazySet(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.drainReadBuffer(int):void");
    }

    public void drainReadBuffers() {
        int id = (int) Thread.currentThread().getId();
        int i3 = NUMBER_OF_READ_BUFFERS + id;
        while (id < i3) {
            drainReadBuffer(READ_BUFFERS_MASK & id);
            id++;
        }
    }

    public void drainWriteBuffer() {
        Runnable poll;
        for (int i3 = 0; i3 < 16 && (poll = this.writeBuffer.poll()) != null; i3++) {
            poll.run();
        }
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

    public void evict() {
        Node poll;
        while (hasOverflowed() && (poll = this.evictionDeque.poll()) != null) {
            this.data.remove(poll.key, poll);
            makeDead(poll);
        }
    }

    public V get(Object obj) {
        Node node = this.data.get(obj);
        if (node == null) {
            return null;
        }
        afterRead(node);
        return node.getValue();
    }

    public boolean hasOverflowed() {
        return this.weightedSize.get() > this.capacity.get();
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
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

    public void makeDead(Node<K, V> node) {
        WeightedValue weightedValue;
        do {
            weightedValue = (WeightedValue) node.get();
        } while (!node.compareAndSet(weightedValue, new WeightedValue(weightedValue.value, 0)));
        AtomicLong atomicLong = this.weightedSize;
        atomicLong.lazySet(atomicLong.get() - ((long) Math.abs(weightedValue.weight)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void makeRetired(com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.Node<K, V> r4) {
        /*
            r3 = this;
        L_0x0000:
            java.lang.Object r3 = r4.get()
            com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$WeightedValue r3 = (com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.WeightedValue) r3
            boolean r0 = r3.isAlive()
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$WeightedValue r0 = new com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$WeightedValue
            V r1 = r3.value
            int r2 = r3.weight
            int r2 = -r2
            r0.<init>(r1, r2)
            boolean r3 = r4.compareAndSet(r3, r0)
            if (r3 == 0) goto L_0x0000
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.makeRetired(com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$Node):void");
    }

    public V put(K k2, V v2) {
        return put(k2, v2, false);
    }

    public V putIfAbsent(K k2, V v2) {
        return put(k2, v2, true);
    }

    public long recordRead(int i3, Node<K, V> node) {
        long j2 = this.readBufferWriteCount.get(i3);
        this.readBufferWriteCount.lazySet(i3, 1 + j2);
        this.readBuffers.lazySet(readBufferIndex(i3, (int) (15 & j2)), node);
        return j2;
    }

    public V remove(Object obj) {
        Node remove = this.data.remove(obj);
        if (remove == null) {
            return null;
        }
        makeRetired(remove);
        afterWrite(new RemovalTask(remove));
        return remove.getValue();
    }

    public V replace(K k2, V v2) {
        WeightedValue weightedValue;
        checkNotNull(k2);
        checkNotNull(v2);
        WeightedValue weightedValue2 = new WeightedValue(v2, 1);
        Node node = this.data.get(k2);
        if (node == null) {
            return null;
        }
        do {
            weightedValue = (WeightedValue) node.get();
            if (!weightedValue.isAlive()) {
                return null;
            }
        } while (!node.compareAndSet(weightedValue, weightedValue2));
        int i3 = 1 - weightedValue.weight;
        if (i3 == 0) {
            afterRead(node);
        } else {
            afterWrite(new UpdateTask(node, i3));
        }
        return weightedValue.value;
    }

    public void setCapacity(long j2) {
        checkArgument(j2 >= 0);
        this.evictionLock.lock();
        try {
            this.capacity.lazySet(Math.min(j2, MAXIMUM_CAPACITY));
            drainBuffers();
            evict();
        } finally {
            this.evictionLock.unlock();
        }
    }

    public int size() {
        return this.data.size();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0030 A[LOOP:1: B:12:0x0030->B:15:0x003a, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0016 A[LOOP:0: B:5:0x0016->B:8:0x0021, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void tryToDrainBuffers() {
        /*
            r5 = this;
            java.util.concurrent.locks.Lock r0 = r5.evictionLock
            boolean r0 = r0.tryLock()
            if (r0 == 0) goto L_0x0043
            java.util.concurrent.atomic.AtomicReference<com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$DrainStatus> r0 = r5.drainStatus     // Catch:{ all -> 0x0029 }
            com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$DrainStatus r1 = com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus.PROCESSING     // Catch:{ all -> 0x0029 }
            r0.lazySet(r1)     // Catch:{ all -> 0x0029 }
            r5.drainBuffers()     // Catch:{ all -> 0x0029 }
            java.util.concurrent.atomic.AtomicReference<com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$DrainStatus> r0 = r5.drainStatus
            com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$DrainStatus r2 = com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus.IDLE
        L_0x0016:
            boolean r3 = r0.compareAndSet(r1, r2)
            if (r3 == 0) goto L_0x001d
            goto L_0x0023
        L_0x001d:
            java.lang.Object r3 = r0.get()
            if (r3 == r1) goto L_0x0016
        L_0x0023:
            java.util.concurrent.locks.Lock r5 = r5.evictionLock
            r5.unlock()
            goto L_0x0043
        L_0x0029:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicReference<com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$DrainStatus> r1 = r5.drainStatus
            com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$DrainStatus r2 = com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus.PROCESSING
            com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$DrainStatus r3 = com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus.IDLE
        L_0x0030:
            boolean r4 = r1.compareAndSet(r2, r3)
            if (r4 != 0) goto L_0x003d
            java.lang.Object r4 = r1.get()
            if (r4 != r2) goto L_0x003d
            goto L_0x0030
        L_0x003d:
            java.util.concurrent.locks.Lock r5 = r5.evictionLock
            r5.unlock()
            throw r0
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.tryToDrainBuffers():void");
    }

    public boolean tryToRetire(Node<K, V> node, WeightedValue<V> weightedValue) {
        if (weightedValue.isAlive()) {
            return node.compareAndSet(weightedValue, new WeightedValue(weightedValue.value, -weightedValue.weight));
        }
        return false;
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
        return new SerializationProxy(this);
    }

    private PrivateMaxEntriesMap(Builder<K, V> builder) {
        int i3 = builder.concurrencyLevel;
        this.concurrencyLevel = i3;
        this.capacity = new AtomicLong(Math.min(builder.capacity, MAXIMUM_CAPACITY));
        this.data = new ConcurrentHashMap(builder.initialCapacity, 0.75f, i3);
        this.evictionLock = new ReentrantLock();
        this.weightedSize = new AtomicLong();
        this.evictionDeque = new LinkedDeque<>();
        this.writeBuffer = new ConcurrentLinkedQueue();
        this.drainStatus = new AtomicReference<>(DrainStatus.IDLE);
        int i4 = NUMBER_OF_READ_BUFFERS;
        this.readBufferReadCount = new long[i4];
        this.readBufferWriteCount = new AtomicLongArray(i4);
        this.readBufferDrainAtWriteCount = new AtomicLongArray(i4);
        this.readBuffers = new AtomicReferenceArray<>(i4 * 16);
    }

    public static int readBufferIndex() {
        return ((int) Thread.currentThread().getId()) & READ_BUFFERS_MASK;
    }

    public V put(K k2, V v2, boolean z2) {
        checkNotNull(k2);
        checkNotNull(v2);
        WeightedValue weightedValue = new WeightedValue(v2, 1);
        Node node = new Node(k2, weightedValue);
        while (true) {
            Node putIfAbsent = this.data.putIfAbsent(node.key, node);
            if (putIfAbsent == null) {
                afterWrite(new AddTask(node, 1));
                return null;
            } else if (z2) {
                afterRead(putIfAbsent);
                return putIfAbsent.getValue();
            } else {
                while (true) {
                    WeightedValue weightedValue2 = (WeightedValue) putIfAbsent.get();
                    if (weightedValue2.isAlive()) {
                        if (putIfAbsent.compareAndSet(weightedValue2, weightedValue)) {
                            int i3 = 1 - weightedValue2.weight;
                            if (i3 == 0) {
                                afterRead(putIfAbsent);
                            } else {
                                afterWrite(new UpdateTask(putIfAbsent, i3));
                            }
                            return weightedValue2.value;
                        }
                    }
                }
            }
        }
    }

    public boolean remove(Object obj, Object obj2) {
        Node node = this.data.get(obj);
        if (node != null && obj2 != null) {
            WeightedValue weightedValue = (WeightedValue) node.get();
            while (true) {
                if (weightedValue.contains(obj2)) {
                    if (!tryToRetire(node, weightedValue)) {
                        weightedValue = (WeightedValue) node.get();
                        if (!weightedValue.isAlive()) {
                            break;
                        }
                    } else if (this.data.remove(obj, node)) {
                        afterWrite(new RemovalTask(node));
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public boolean replace(K k2, V v2, V v3) {
        WeightedValue weightedValue;
        checkNotNull(k2);
        checkNotNull(v2);
        checkNotNull(v3);
        WeightedValue weightedValue2 = new WeightedValue(v3, 1);
        Node node = this.data.get(k2);
        if (node == null) {
            return false;
        }
        do {
            weightedValue = (WeightedValue) node.get();
            if (!weightedValue.isAlive() || !weightedValue.contains(v2)) {
                return false;
            }
        } while (!node.compareAndSet(weightedValue, weightedValue2));
        int i3 = 1 - weightedValue.weight;
        if (i3 == 0) {
            afterRead(node);
        } else {
            afterWrite(new UpdateTask(node, i3));
        }
        return true;
    }
}
