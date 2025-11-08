package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
class StandardTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    @GwtTransient
    final Map<R, Map<C, V>> backingMap;
    @CheckForNull
    @LazyInit
    private transient Set<C> columnKeySet;
    @CheckForNull
    @LazyInit
    private transient StandardTable<R, C, V>.ColumnMap columnMap;
    @GwtTransient
    final Supplier<? extends Map<C, V>> factory;
    @CheckForNull
    @LazyInit
    private transient Map<R, Map<C, V>> rowMap;

    public class CellIterator implements Iterator<Table.Cell<R, C, V>> {
        Iterator<Map.Entry<C, V>> columnIterator;
        @CheckForNull
        Map.Entry<R, Map<C, V>> rowEntry;
        final Iterator<Map.Entry<R, Map<C, V>>> rowIterator;

        private CellIterator() {
            this.rowIterator = StandardTable.this.backingMap.entrySet().iterator();
            this.columnIterator = Iterators.emptyModifiableIterator();
        }

        public boolean hasNext() {
            return this.rowIterator.hasNext() || this.columnIterator.hasNext();
        }

        public void remove() {
            this.columnIterator.remove();
            Map.Entry<R, Map<C, V>> entry = this.rowEntry;
            Objects.requireNonNull(entry);
            if (((Map) entry.getValue()).isEmpty()) {
                this.rowIterator.remove();
                this.rowEntry = null;
            }
        }

        public Table.Cell<R, C, V> next() {
            if (!this.columnIterator.hasNext()) {
                Map.Entry<R, Map<C, V>> next = this.rowIterator.next();
                this.rowEntry = next;
                this.columnIterator = next.getValue().entrySet().iterator();
            }
            Objects.requireNonNull(this.rowEntry);
            Map.Entry next2 = this.columnIterator.next();
            return Tables.immutableCell(this.rowEntry.getKey(), next2.getKey(), next2.getValue());
        }
    }

    public class Column extends Maps.ViewCachingAbstractMap<R, V> {
        final C columnKey;

        public class EntrySet extends Sets.ImprovedAbstractSet<Map.Entry<R, V>> {
            private EntrySet() {
            }

            public void clear() {
                Column.this.removeFromColumnIf(Predicates.alwaysTrue());
            }

            public boolean contains(@CheckForNull Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return StandardTable.this.containsMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
            }

            public boolean isEmpty() {
                Column column = Column.this;
                return !StandardTable.this.containsColumn(column.columnKey);
            }

            public Iterator<Map.Entry<R, V>> iterator() {
                return new EntrySetIterator();
            }

            public boolean remove(@CheckForNull Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return StandardTable.this.removeMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
            }

            public boolean retainAll(Collection<?> collection) {
                return Column.this.removeFromColumnIf(Predicates.not(Predicates.in(collection)));
            }

            public int size() {
                int i3 = 0;
                for (Map<C, V> containsKey : StandardTable.this.backingMap.values()) {
                    if (containsKey.containsKey(Column.this.columnKey)) {
                        i3++;
                    }
                }
                return i3;
            }
        }

        public class EntrySetIterator extends AbstractIterator<Map.Entry<R, V>> {
            final Iterator<Map.Entry<R, Map<C, V>>> iterator;

            private EntrySetIterator() {
                this.iterator = StandardTable.this.backingMap.entrySet().iterator();
            }

            @CheckForNull
            public Map.Entry<R, V> computeNext() {
                while (this.iterator.hasNext()) {
                    final Map.Entry next = this.iterator.next();
                    if (((Map) next.getValue()).containsKey(Column.this.columnKey)) {
                        return new AbstractMapEntry<R, V>() {
                            public R getKey() {
                                return next.getKey();
                            }

                            public V getValue() {
                                return ((Map) next.getValue()).get(Column.this.columnKey);
                            }

                            public V setValue(V v2) {
                                return NullnessCasts.uncheckedCastNullableTToT(((Map) next.getValue()).put(Column.this.columnKey, Preconditions.checkNotNull(v2)));
                            }
                        };
                    }
                }
                return (Map.Entry) endOfData();
            }
        }

        public class KeySet extends Maps.KeySet<R, V> {
            public KeySet() {
                super(Column.this);
            }

            public boolean contains(@CheckForNull Object obj) {
                Column column = Column.this;
                return StandardTable.this.contains(obj, column.columnKey);
            }

            public boolean remove(@CheckForNull Object obj) {
                Column column = Column.this;
                return StandardTable.this.remove(obj, column.columnKey) != null;
            }

            public boolean retainAll(Collection<?> collection) {
                return Column.this.removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
            }
        }

        public class Values extends Maps.Values<R, V> {
            public Values() {
                super(Column.this);
            }

            public boolean remove(@CheckForNull Object obj) {
                return obj != null && Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
            }

            public boolean removeAll(Collection<?> collection) {
                return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
            }

            public boolean retainAll(Collection<?> collection) {
                return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
            }
        }

        public Column(C c3) {
            this.columnKey = Preconditions.checkNotNull(c3);
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return StandardTable.this.contains(obj, this.columnKey);
        }

        public Set<Map.Entry<R, V>> createEntrySet() {
            return new EntrySet();
        }

        public Set<R> createKeySet() {
            return new KeySet();
        }

        public Collection<V> createValues() {
            return new Values();
        }

        @CheckForNull
        public V get(@CheckForNull Object obj) {
            return StandardTable.this.get(obj, this.columnKey);
        }

        @CheckForNull
        public V put(R r2, V v2) {
            return StandardTable.this.put(r2, this.columnKey, v2);
        }

        @CheckForNull
        public V remove(@CheckForNull Object obj) {
            return StandardTable.this.remove(obj, this.columnKey);
        }

        @CanIgnoreReturnValue
        public boolean removeFromColumnIf(Predicate<? super Map.Entry<R, V>> predicate) {
            Iterator<Map.Entry<R, Map<C, V>>> it = StandardTable.this.backingMap.entrySet().iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                Map.Entry next = it.next();
                Map map = (Map) next.getValue();
                Object obj = map.get(this.columnKey);
                if (obj != null && predicate.apply(Maps.immutableEntry(next.getKey(), obj))) {
                    map.remove(this.columnKey);
                    if (map.isEmpty()) {
                        it.remove();
                    }
                    z2 = true;
                }
            }
            return z2;
        }
    }

    public class ColumnKeyIterator extends AbstractIterator<C> {
        Iterator<Map.Entry<C, V>> entryIterator;
        final Iterator<Map<C, V>> mapIterator;
        final Map<C, V> seen;

        private ColumnKeyIterator() {
            this.seen = (Map) StandardTable.this.factory.get();
            this.mapIterator = StandardTable.this.backingMap.values().iterator();
            this.entryIterator = Iterators.emptyIterator();
        }

        @CheckForNull
        public C computeNext() {
            while (true) {
                if (this.entryIterator.hasNext()) {
                    Map.Entry next = this.entryIterator.next();
                    if (!this.seen.containsKey(next.getKey())) {
                        this.seen.put(next.getKey(), next.getValue());
                        return next.getKey();
                    }
                } else if (!this.mapIterator.hasNext()) {
                    return endOfData();
                } else {
                    this.entryIterator = this.mapIterator.next().entrySet().iterator();
                }
            }
        }
    }

    public class ColumnKeySet extends StandardTable<R, C, V>.TableSet<C> {
        private ColumnKeySet() {
            super();
        }

        public boolean contains(@CheckForNull Object obj) {
            return StandardTable.this.containsColumn(obj);
        }

        public Iterator<C> iterator() {
            return StandardTable.this.createColumnKeyIterator();
        }

        public boolean remove(@CheckForNull Object obj) {
            boolean z2 = false;
            if (obj == null) {
                return false;
            }
            Iterator<Map<C, V>> it = StandardTable.this.backingMap.values().iterator();
            while (it.hasNext()) {
                Map next = it.next();
                if (next.keySet().remove(obj)) {
                    if (next.isEmpty()) {
                        it.remove();
                    }
                    z2 = true;
                }
            }
            return z2;
        }

        public boolean removeAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            Iterator<Map<C, V>> it = StandardTable.this.backingMap.values().iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                Map next = it.next();
                if (Iterators.removeAll(next.keySet().iterator(), collection)) {
                    if (next.isEmpty()) {
                        it.remove();
                    }
                    z2 = true;
                }
            }
            return z2;
        }

        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            Iterator<Map<C, V>> it = StandardTable.this.backingMap.values().iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                Map next = it.next();
                if (next.keySet().retainAll(collection)) {
                    if (next.isEmpty()) {
                        it.remove();
                    }
                    z2 = true;
                }
            }
            return z2;
        }

        public int size() {
            return Iterators.size(iterator());
        }
    }

    public class ColumnMap extends Maps.ViewCachingAbstractMap<C, Map<R, V>> {

        public class ColumnMapEntrySet extends StandardTable<R, C, V>.TableSet<Map.Entry<C, Map<R, V>>> {
            public ColumnMapEntrySet() {
                super();
            }

            public boolean contains(@CheckForNull Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (!StandardTable.this.containsColumn(entry.getKey())) {
                    return false;
                }
                Map map = ColumnMap.this.get(entry.getKey());
                Objects.requireNonNull(map);
                return map.equals(entry.getValue());
            }

            public Iterator<Map.Entry<C, Map<R, V>>> iterator() {
                return Maps.asMapEntryIterator(StandardTable.this.columnKeySet(), new Function<C, Map<R, V>>() {
                    public Map<R, V> apply(C c3) {
                        return StandardTable.this.column(c3);
                    }
                });
            }

            public boolean remove(@CheckForNull Object obj) {
                if (!contains(obj) || !(obj instanceof Map.Entry)) {
                    return false;
                }
                Map unused = StandardTable.this.removeColumn(((Map.Entry) obj).getKey());
                return true;
            }

            public boolean removeAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                return Sets.removeAllImpl((Set<?>) this, collection.iterator());
            }

            public boolean retainAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z2 = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!collection.contains(Maps.immutableEntry(next, StandardTable.this.column(next)))) {
                        Map unused = StandardTable.this.removeColumn(next);
                        z2 = true;
                    }
                }
                return z2;
            }

            public int size() {
                return StandardTable.this.columnKeySet().size();
            }
        }

        public class ColumnMapValues extends Maps.Values<C, Map<R, V>> {
            public ColumnMapValues() {
                super(ColumnMap.this);
            }

            public boolean remove(@CheckForNull Object obj) {
                for (Map.Entry entry : ColumnMap.this.entrySet()) {
                    if (((Map) entry.getValue()).equals(obj)) {
                        Map unused = StandardTable.this.removeColumn(entry.getKey());
                        return true;
                    }
                }
                return false;
            }

            public boolean removeAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z2 = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (collection.contains(StandardTable.this.column(next))) {
                        Map unused = StandardTable.this.removeColumn(next);
                        z2 = true;
                    }
                }
                return z2;
            }

            public boolean retainAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z2 = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!collection.contains(StandardTable.this.column(next))) {
                        Map unused = StandardTable.this.removeColumn(next);
                        z2 = true;
                    }
                }
                return z2;
            }
        }

        private ColumnMap() {
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return StandardTable.this.containsColumn(obj);
        }

        public Set<Map.Entry<C, Map<R, V>>> createEntrySet() {
            return new ColumnMapEntrySet();
        }

        public Collection<Map<R, V>> createValues() {
            return new ColumnMapValues();
        }

        public Set<C> keySet() {
            return StandardTable.this.columnKeySet();
        }

        @CheckForNull
        public Map<R, V> get(@CheckForNull Object obj) {
            if (!StandardTable.this.containsColumn(obj)) {
                return null;
            }
            StandardTable standardTable = StandardTable.this;
            Objects.requireNonNull(obj);
            return standardTable.column(obj);
        }

        @CheckForNull
        public Map<R, V> remove(@CheckForNull Object obj) {
            if (StandardTable.this.containsColumn(obj)) {
                return StandardTable.this.removeColumn(obj);
            }
            return null;
        }
    }

    public class Row extends Maps.IteratorBasedAbstractMap<C, V> {
        @CheckForNull
        Map<C, V> backingRowMap;
        final R rowKey;

        public Row(R r2) {
            this.rowKey = Preconditions.checkNotNull(r2);
        }

        public void clear() {
            updateBackingRowMapField();
            Map<C, V> map = this.backingRowMap;
            if (map != null) {
                map.clear();
            }
            maintainEmptyInvariant();
        }

        @CheckForNull
        public Map<C, V> computeBackingRowMap() {
            return StandardTable.this.backingMap.get(this.rowKey);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
            r0 = r0.backingRowMap;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean containsKey(@javax.annotation.CheckForNull java.lang.Object r1) {
            /*
                r0 = this;
                r0.updateBackingRowMapField()
                if (r1 == 0) goto L_0x0011
                java.util.Map<C, V> r0 = r0.backingRowMap
                if (r0 == 0) goto L_0x0011
                boolean r0 = com.google.common.collect.Maps.safeContainsKey(r0, r1)
                if (r0 == 0) goto L_0x0011
                r0 = 1
                goto L_0x0012
            L_0x0011:
                r0 = 0
            L_0x0012:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.StandardTable.Row.containsKey(java.lang.Object):boolean");
        }

        public Iterator<Map.Entry<C, V>> entryIterator() {
            updateBackingRowMapField();
            Map<C, V> map = this.backingRowMap;
            if (map == null) {
                return Iterators.emptyModifiableIterator();
            }
            final Iterator<Map.Entry<C, V>> it = map.entrySet().iterator();
            return new Iterator<Map.Entry<C, V>>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public void remove() {
                    it.remove();
                    Row.this.maintainEmptyInvariant();
                }

                public Map.Entry<C, V> next() {
                    return Row.this.wrapEntry((Map.Entry) it.next());
                }
            };
        }

        @CheckForNull
        public V get(@CheckForNull Object obj) {
            Map<C, V> map;
            updateBackingRowMapField();
            if (obj == null || (map = this.backingRowMap) == null) {
                return null;
            }
            return Maps.safeGet(map, obj);
        }

        public void maintainEmptyInvariant() {
            updateBackingRowMapField();
            Map<C, V> map = this.backingRowMap;
            if (map != null && map.isEmpty()) {
                StandardTable.this.backingMap.remove(this.rowKey);
                this.backingRowMap = null;
            }
        }

        @CheckForNull
        public V put(C c3, V v2) {
            Preconditions.checkNotNull(c3);
            Preconditions.checkNotNull(v2);
            Map<C, V> map = this.backingRowMap;
            return (map == null || map.isEmpty()) ? StandardTable.this.put(this.rowKey, c3, v2) : this.backingRowMap.put(c3, v2);
        }

        @CheckForNull
        public V remove(@CheckForNull Object obj) {
            updateBackingRowMapField();
            Map<C, V> map = this.backingRowMap;
            if (map == null) {
                return null;
            }
            V safeRemove = Maps.safeRemove(map, obj);
            maintainEmptyInvariant();
            return safeRemove;
        }

        public int size() {
            updateBackingRowMapField();
            Map<C, V> map = this.backingRowMap;
            if (map == null) {
                return 0;
            }
            return map.size();
        }

        public final void updateBackingRowMapField() {
            Map<C, V> map = this.backingRowMap;
            if (map == null || (map.isEmpty() && StandardTable.this.backingMap.containsKey(this.rowKey))) {
                this.backingRowMap = computeBackingRowMap();
            }
        }

        public Map.Entry<C, V> wrapEntry(final Map.Entry<C, V> entry) {
            return new ForwardingMapEntry<C, V>(this) {
                public boolean equals(@CheckForNull Object obj) {
                    return standardEquals(obj);
                }

                public V setValue(V v2) {
                    return super.setValue(Preconditions.checkNotNull(v2));
                }

                public Map.Entry<C, V> delegate() {
                    return entry;
                }
            };
        }
    }

    public class RowMap extends Maps.ViewCachingAbstractMap<R, Map<C, V>> {

        public class EntrySet extends StandardTable<R, C, V>.TableSet<Map.Entry<R, Map<C, V>>> {
            public EntrySet() {
                super();
            }

            public boolean contains(@CheckForNull Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return entry.getKey() != null && (entry.getValue() instanceof Map) && Collections2.safeContains(StandardTable.this.backingMap.entrySet(), entry);
            }

            public Iterator<Map.Entry<R, Map<C, V>>> iterator() {
                return Maps.asMapEntryIterator(StandardTable.this.backingMap.keySet(), new Function<R, Map<C, V>>() {
                    public Map<C, V> apply(R r2) {
                        return StandardTable.this.row(r2);
                    }
                });
            }

            public boolean remove(@CheckForNull Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return entry.getKey() != null && (entry.getValue() instanceof Map) && StandardTable.this.backingMap.entrySet().remove(entry);
            }

            public int size() {
                return StandardTable.this.backingMap.size();
            }
        }

        public RowMap() {
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return StandardTable.this.containsRow(obj);
        }

        public Set<Map.Entry<R, Map<C, V>>> createEntrySet() {
            return new EntrySet();
        }

        @CheckForNull
        public Map<C, V> get(@CheckForNull Object obj) {
            if (!StandardTable.this.containsRow(obj)) {
                return null;
            }
            StandardTable standardTable = StandardTable.this;
            Objects.requireNonNull(obj);
            return standardTable.row(obj);
        }

        @CheckForNull
        public Map<C, V> remove(@CheckForNull Object obj) {
            if (obj == null) {
                return null;
            }
            return StandardTable.this.backingMap.remove(obj);
        }
    }

    public abstract class TableSet<T> extends Sets.ImprovedAbstractSet<T> {
        private TableSet() {
        }

        public void clear() {
            StandardTable.this.backingMap.clear();
        }

        public boolean isEmpty() {
            return StandardTable.this.backingMap.isEmpty();
        }
    }

    public StandardTable(Map<R, Map<C, V>> map, Supplier<? extends Map<C, V>> supplier) {
        this.backingMap = map;
        this.factory = supplier;
    }

    /* access modifiers changed from: private */
    public boolean containsMapping(@CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        return obj3 != null && obj3.equals(get(obj, obj2));
    }

    private Map<C, V> getOrCreate(R r2) {
        Map<C, V> map = this.backingMap.get(r2);
        if (map != null) {
            return map;
        }
        Map<C, V> map2 = (Map) this.factory.get();
        this.backingMap.put(r2, map2);
        return map2;
    }

    /* access modifiers changed from: private */
    @CanIgnoreReturnValue
    public Map<R, V> removeColumn(@CheckForNull Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Map.Entry<R, Map<C, V>>> it = this.backingMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            Object remove = ((Map) next.getValue()).remove(obj);
            if (remove != null) {
                linkedHashMap.put(next.getKey(), remove);
                if (((Map) next.getValue()).isEmpty()) {
                    it.remove();
                }
            }
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: private */
    public boolean removeMapping(@CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!containsMapping(obj, obj2, obj3)) {
            return false;
        }
        remove(obj, obj2);
        return true;
    }

    public Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new CellIterator();
    }

    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    public void clear() {
        this.backingMap.clear();
    }

    public Map<R, V> column(C c3) {
        return new Column(c3);
    }

    public Set<C> columnKeySet() {
        Set<C> set = this.columnKeySet;
        if (set != null) {
            return set;
        }
        ColumnKeySet columnKeySet2 = new ColumnKeySet();
        this.columnKeySet = columnKeySet2;
        return columnKeySet2;
    }

    public Map<C, Map<R, V>> columnMap() {
        StandardTable<R, C, V>.ColumnMap columnMap2 = this.columnMap;
        if (columnMap2 != null) {
            return columnMap2;
        }
        StandardTable<R, C, V>.ColumnMap columnMap3 = new ColumnMap();
        this.columnMap = columnMap3;
        return columnMap3;
    }

    public boolean contains(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return (obj == null || obj2 == null || !super.contains(obj, obj2)) ? false : true;
    }

    public boolean containsColumn(@CheckForNull Object obj) {
        if (obj == null) {
            return false;
        }
        for (Map<C, V> safeContainsKey : this.backingMap.values()) {
            if (Maps.safeContainsKey(safeContainsKey, obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRow(@CheckForNull Object obj) {
        return obj != null && Maps.safeContainsKey(this.backingMap, obj);
    }

    public boolean containsValue(@CheckForNull Object obj) {
        return obj != null && super.containsValue(obj);
    }

    public Iterator<C> createColumnKeyIterator() {
        return new ColumnKeyIterator();
    }

    public Map<R, Map<C, V>> createRowMap() {
        return new RowMap();
    }

    @CheckForNull
    public V get(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj == null || obj2 == null) {
            return null;
        }
        return super.get(obj, obj2);
    }

    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V put(R r2, C c3, V v2) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(c3);
        Preconditions.checkNotNull(v2);
        return getOrCreate(r2).put(c3, v2);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Map map;
        if (obj == null || obj2 == null || (map = (Map) Maps.safeGet(this.backingMap, obj)) == null) {
            return null;
        }
        V remove = map.remove(obj2);
        if (map.isEmpty()) {
            this.backingMap.remove(obj);
        }
        return remove;
    }

    public Map<C, V> row(R r2) {
        return new Row(r2);
    }

    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> map = this.rowMap;
        if (map != null) {
            return map;
        }
        Map<R, Map<C, V>> createRowMap = createRowMap();
        this.rowMap = createRowMap;
        return createRowMap;
    }

    public int size() {
        int i3 = 0;
        for (Map<C, V> size : this.backingMap.values()) {
            i3 += size.size();
        }
        return i3;
    }

    public Collection<V> values() {
        return super.values();
    }
}
