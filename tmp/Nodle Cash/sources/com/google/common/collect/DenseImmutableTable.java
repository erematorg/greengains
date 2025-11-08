package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.Immutable;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@Immutable(containerOf = {"R", "C", "V"})
final class DenseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    private final int[] cellColumnIndices;
    private final int[] cellRowIndices;
    /* access modifiers changed from: private */
    public final int[] columnCounts;
    /* access modifiers changed from: private */
    public final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    /* access modifiers changed from: private */
    public final int[] rowCounts;
    /* access modifiers changed from: private */
    public final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
    /* access modifiers changed from: private */
    public final V[][] values;

    public final class Column extends ImmutableArrayMap<R, V> {
        private final int columnIndex;

        public Column(int i3) {
            super(DenseImmutableTable.this.columnCounts[i3]);
            this.columnIndex = i3;
        }

        @CheckForNull
        public V getValue(int i3) {
            return DenseImmutableTable.this.values[i3][this.columnIndex];
        }

        public boolean isPartialView() {
            return true;
        }

        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }
    }

    public final class ColumnMap extends ImmutableArrayMap<C, ImmutableMap<R, V>> {
        public boolean isPartialView() {
            return false;
        }

        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        private ColumnMap() {
            super(DenseImmutableTable.this.columnCounts.length);
        }

        public ImmutableMap<R, V> getValue(int i3) {
            return new Column(i3);
        }
    }

    public static abstract class ImmutableArrayMap<K, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
        private final int size;

        public ImmutableArrayMap(int i3) {
            this.size = i3;
        }

        private boolean isFull() {
            return this.size == keyToIndex().size();
        }

        public ImmutableSet<K> createKeySet() {
            return isFull() ? keyToIndex().keySet() : super.createKeySet();
        }

        public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIterator<Map.Entry<K, V>>() {
                private int index = -1;
                private final int maxIndex;

                {
                    this.maxIndex = ImmutableArrayMap.this.keyToIndex().size();
                }

                @CheckForNull
                public Map.Entry<K, V> computeNext() {
                    int i3 = this.index;
                    while (true) {
                        this.index = i3 + 1;
                        int i4 = this.index;
                        if (i4 >= this.maxIndex) {
                            return (Map.Entry) endOfData();
                        }
                        Object value = ImmutableArrayMap.this.getValue(i4);
                        if (value != null) {
                            return Maps.immutableEntry(ImmutableArrayMap.this.getKey(this.index), value);
                        }
                        i3 = this.index;
                    }
                }
            };
        }

        @CheckForNull
        public V get(@CheckForNull Object obj) {
            Integer num = (Integer) keyToIndex().get(obj);
            if (num == null) {
                return null;
            }
            return getValue(num.intValue());
        }

        public K getKey(int i3) {
            return keyToIndex().keySet().asList().get(i3);
        }

        @CheckForNull
        public abstract V getValue(int i3);

        public abstract ImmutableMap<K, Integer> keyToIndex();

        public int size() {
            return this.size;
        }
    }

    public final class Row extends ImmutableArrayMap<C, V> {
        private final int rowIndex;

        public Row(int i3) {
            super(DenseImmutableTable.this.rowCounts[i3]);
            this.rowIndex = i3;
        }

        @CheckForNull
        public V getValue(int i3) {
            return DenseImmutableTable.this.values[this.rowIndex][i3];
        }

        public boolean isPartialView() {
            return true;
        }

        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }
    }

    public final class RowMap extends ImmutableArrayMap<R, ImmutableMap<C, V>> {
        public boolean isPartialView() {
            return false;
        }

        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        private RowMap() {
            super(DenseImmutableTable.this.rowCounts.length);
        }

        public ImmutableMap<C, V> getValue(int i3) {
            return new Row(i3);
        }
    }

    public DenseImmutableTable(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        int size = immutableSet.size();
        int[] iArr = new int[2];
        iArr[1] = immutableSet2.size();
        iArr[0] = size;
        this.values = (Object[][]) Array.newInstance(Object.class, iArr);
        ImmutableMap<R, Integer> indexMap = Maps.indexMap(immutableSet);
        this.rowKeyToIndex = indexMap;
        ImmutableMap<C, Integer> indexMap2 = Maps.indexMap(immutableSet2);
        this.columnKeyToIndex = indexMap2;
        this.rowCounts = new int[indexMap.size()];
        this.columnCounts = new int[indexMap2.size()];
        int[] iArr2 = new int[immutableList.size()];
        int[] iArr3 = new int[immutableList.size()];
        for (int i3 = 0; i3 < immutableList.size(); i3++) {
            Table.Cell cell = immutableList.get(i3);
            Object rowKey = cell.getRowKey();
            Object columnKey = cell.getColumnKey();
            Integer num = this.rowKeyToIndex.get(rowKey);
            Objects.requireNonNull(num);
            int intValue = num.intValue();
            Integer num2 = this.columnKeyToIndex.get(columnKey);
            Objects.requireNonNull(num2);
            int intValue2 = num2.intValue();
            checkNoDuplicate(rowKey, columnKey, this.values[intValue][intValue2], cell.getValue());
            this.values[intValue][intValue2] = cell.getValue();
            int[] iArr4 = this.rowCounts;
            iArr4[intValue] = iArr4[intValue] + 1;
            int[] iArr5 = this.columnCounts;
            iArr5[intValue2] = iArr5[intValue2] + 1;
            iArr2[i3] = intValue;
            iArr3[i3] = intValue2;
        }
        this.cellRowIndices = iArr2;
        this.cellColumnIndices = iArr3;
        this.rowMap = new RowMap();
        this.columnMap = new ColumnMap();
    }

    public ImmutableTable.SerializedForm createSerializedForm() {
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, this.cellColumnIndices);
    }

    @CheckForNull
    public V get(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return this.values[num.intValue()][num2.intValue()];
    }

    public Table.Cell<R, C, V> getCell(int i3) {
        int i4 = this.cellRowIndices[i3];
        int i5 = this.cellColumnIndices[i3];
        Object obj = rowKeySet().asList().get(i4);
        Object obj2 = columnKeySet().asList().get(i5);
        V v2 = this.values[i4][i5];
        Objects.requireNonNull(v2);
        return ImmutableTable.cellOf(obj, obj2, v2);
    }

    public V getValue(int i3) {
        V v2 = this.values[this.cellRowIndices[i3]][this.cellColumnIndices[i3]];
        Objects.requireNonNull(v2);
        return v2;
    }

    public int size() {
        return this.cellRowIndices.length;
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.copyOf(this.columnMap);
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.copyOf(this.rowMap);
    }
}
