package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
class StandardRowSortedTable<R, C, V> extends StandardTable<R, C, V> implements RowSortedTable<R, C, V> {
    private static final long serialVersionUID = 0;

    public class RowSortedMap extends StandardTable<R, C, V>.RowMap implements SortedMap<R, Map<C, V>> {
        private RowSortedMap() {
            super();
        }

        @CheckForNull
        public Comparator<? super R> comparator() {
            return StandardRowSortedTable.this.sortedBackingMap().comparator();
        }

        public R firstKey() {
            return StandardRowSortedTable.this.sortedBackingMap().firstKey();
        }

        public SortedMap<R, Map<C, V>> headMap(R r2) {
            Preconditions.checkNotNull(r2);
            return new StandardRowSortedTable(StandardRowSortedTable.this.sortedBackingMap().headMap(r2), StandardRowSortedTable.this.factory).rowMap();
        }

        public R lastKey() {
            return StandardRowSortedTable.this.sortedBackingMap().lastKey();
        }

        public SortedMap<R, Map<C, V>> subMap(R r2, R r3) {
            Preconditions.checkNotNull(r2);
            Preconditions.checkNotNull(r3);
            return new StandardRowSortedTable(StandardRowSortedTable.this.sortedBackingMap().subMap(r2, r3), StandardRowSortedTable.this.factory).rowMap();
        }

        public SortedMap<R, Map<C, V>> tailMap(R r2) {
            Preconditions.checkNotNull(r2);
            return new StandardRowSortedTable(StandardRowSortedTable.this.sortedBackingMap().tailMap(r2), StandardRowSortedTable.this.factory).rowMap();
        }

        public SortedSet<R> createKeySet() {
            return new Maps.SortedKeySet(this);
        }

        public SortedSet<R> keySet() {
            return (SortedSet) super.keySet();
        }
    }

    public StandardRowSortedTable(SortedMap<R, Map<C, V>> sortedMap, Supplier<? extends Map<C, V>> supplier) {
        super(sortedMap, supplier);
    }

    /* access modifiers changed from: private */
    public SortedMap<R, Map<C, V>> sortedBackingMap() {
        return (SortedMap) this.backingMap;
    }

    public SortedMap<R, Map<C, V>> createRowMap() {
        return new RowSortedMap();
    }

    public SortedSet<R> rowKeySet() {
        return (SortedSet) rowMap().keySet();
    }

    public SortedMap<R, Map<C, V>> rowMap() {
        return (SortedMap) super.rowMap();
    }
}
