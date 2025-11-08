package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.commons.lang3.StringUtils;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class ArrayTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    private final V[][] array;
    /* access modifiers changed from: private */
    public final ImmutableMap<C, Integer> columnKeyToIndex;
    /* access modifiers changed from: private */
    public final ImmutableList<C> columnList;
    @CheckForNull
    @LazyInit
    private transient ArrayTable<R, C, V>.ColumnMap columnMap;
    /* access modifiers changed from: private */
    public final ImmutableMap<R, Integer> rowKeyToIndex;
    /* access modifiers changed from: private */
    public final ImmutableList<R> rowList;
    @CheckForNull
    @LazyInit
    private transient ArrayTable<R, C, V>.RowMap rowMap;

    public static abstract class ArrayMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> {
        private final ImmutableMap<K, Integer> keyIndex;

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return this.keyIndex.containsKey(obj);
        }

        public Iterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIndexedListIterator<Map.Entry<K, V>>(size()) {
                public Map.Entry<K, V> get(int i3) {
                    return ArrayMap.this.getEntry(i3);
                }
            };
        }

        @CheckForNull
        public V get(@CheckForNull Object obj) {
            Integer num = this.keyIndex.get(obj);
            if (num == null) {
                return null;
            }
            return getValue(num.intValue());
        }

        public Map.Entry<K, V> getEntry(final int i3) {
            Preconditions.checkElementIndex(i3, size());
            return new AbstractMapEntry<K, V>() {
                public K getKey() {
                    return ArrayMap.this.getKey(i3);
                }

                @ParametricNullness
                public V getValue() {
                    return ArrayMap.this.getValue(i3);
                }

                @ParametricNullness
                public V setValue(@ParametricNullness V v2) {
                    return ArrayMap.this.setValue(i3, v2);
                }
            };
        }

        public K getKey(int i3) {
            return this.keyIndex.keySet().asList().get(i3);
        }

        public abstract String getKeyRole();

        @ParametricNullness
        public abstract V getValue(int i3);

        public boolean isEmpty() {
            return this.keyIndex.isEmpty();
        }

        public Set<K> keySet() {
            return this.keyIndex.keySet();
        }

        @CheckForNull
        public V put(K k2, @ParametricNullness V v2) {
            Integer num = this.keyIndex.get(k2);
            if (num != null) {
                return setValue(num.intValue(), v2);
            }
            throw new IllegalArgumentException(getKeyRole() + StringUtils.SPACE + k2 + " not in " + this.keyIndex.keySet());
        }

        @CheckForNull
        public V remove(@CheckForNull Object obj) {
            throw new UnsupportedOperationException();
        }

        @ParametricNullness
        public abstract V setValue(int i3, @ParametricNullness V v2);

        public int size() {
            return this.keyIndex.size();
        }

        private ArrayMap(ImmutableMap<K, Integer> immutableMap) {
            this.keyIndex = immutableMap;
        }
    }

    public class Column extends ArrayMap<R, V> {
        final int columnIndex;

        public Column(int i3) {
            super(ArrayTable.this.rowKeyToIndex);
            this.columnIndex = i3;
        }

        public String getKeyRole() {
            return "Row";
        }

        @CheckForNull
        public V getValue(int i3) {
            return ArrayTable.this.at(i3, this.columnIndex);
        }

        @CheckForNull
        public V setValue(int i3, @CheckForNull V v2) {
            return ArrayTable.this.set(i3, this.columnIndex, v2);
        }
    }

    public class ColumnMap extends ArrayMap<C, Map<R, V>> {
        public String getKeyRole() {
            return "Column";
        }

        private ColumnMap() {
            super(ArrayTable.this.columnKeyToIndex);
        }

        public Map<R, V> getValue(int i3) {
            return new Column(i3);
        }

        @CheckForNull
        public Map<R, V> put(C c3, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }

        public Map<R, V> setValue(int i3, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    public class Row extends ArrayMap<C, V> {
        final int rowIndex;

        public Row(int i3) {
            super(ArrayTable.this.columnKeyToIndex);
            this.rowIndex = i3;
        }

        public String getKeyRole() {
            return "Column";
        }

        @CheckForNull
        public V getValue(int i3) {
            return ArrayTable.this.at(this.rowIndex, i3);
        }

        @CheckForNull
        public V setValue(int i3, @CheckForNull V v2) {
            return ArrayTable.this.set(this.rowIndex, i3, v2);
        }
    }

    public class RowMap extends ArrayMap<R, Map<C, V>> {
        public String getKeyRole() {
            return "Row";
        }

        private RowMap() {
            super(ArrayTable.this.rowKeyToIndex);
        }

        public Map<C, V> getValue(int i3) {
            return new Row(i3);
        }

        @CheckForNull
        public Map<C, V> put(R r2, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        public Map<C, V> setValue(int i3, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    private ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        ImmutableList<R> copyOf = ImmutableList.copyOf(iterable);
        this.rowList = copyOf;
        ImmutableList<C> copyOf2 = ImmutableList.copyOf(iterable2);
        this.columnList = copyOf2;
        Preconditions.checkArgument(copyOf.isEmpty() == copyOf2.isEmpty());
        this.rowKeyToIndex = Maps.indexMap(copyOf);
        this.columnKeyToIndex = Maps.indexMap(copyOf2);
        int size = copyOf.size();
        int[] iArr = new int[2];
        iArr[1] = copyOf2.size();
        iArr[0] = size;
        this.array = (Object[][]) Array.newInstance(Object.class, iArr);
        eraseAll();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        return new ArrayTable<>(iterable, iterable2);
    }

    /* access modifiers changed from: private */
    public Table.Cell<R, C, V> getCell(int i3) {
        return new Tables.AbstractCell<R, C, V>(i3) {
            final int columnIndex;
            final int rowIndex;
            final /* synthetic */ int val$index;

            {
                this.val$index = r3;
                this.rowIndex = r3 / ArrayTable.this.columnList.size();
                this.columnIndex = r3 % ArrayTable.this.columnList.size();
            }

            public C getColumnKey() {
                return ArrayTable.this.columnList.get(this.columnIndex);
            }

            public R getRowKey() {
                return ArrayTable.this.rowList.get(this.rowIndex);
            }

            @CheckForNull
            public V getValue() {
                return ArrayTable.this.at(this.rowIndex, this.columnIndex);
            }
        };
    }

    /* access modifiers changed from: private */
    @CheckForNull
    public V getValue(int i3) {
        return at(i3 / this.columnList.size(), i3 % this.columnList.size());
    }

    @CheckForNull
    public V at(int i3, int i4) {
        Preconditions.checkElementIndex(i3, this.rowList.size());
        Preconditions.checkElementIndex(i4, this.columnList.size());
        return this.array[i3][i4];
    }

    public Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new AbstractIndexedListIterator<Table.Cell<R, C, V>>(size()) {
            public Table.Cell<R, C, V> get(int i3) {
                return ArrayTable.this.getCell(i3);
            }
        };
    }

    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Map<R, V> column(C c3) {
        Preconditions.checkNotNull(c3);
        Integer num = this.columnKeyToIndex.get(c3);
        return num == null ? Collections.emptyMap() : new Column(num.intValue());
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    public Map<C, Map<R, V>> columnMap() {
        ArrayTable<R, C, V>.ColumnMap columnMap2 = this.columnMap;
        if (columnMap2 != null) {
            return columnMap2;
        }
        ArrayTable<R, C, V>.ColumnMap columnMap3 = new ColumnMap();
        this.columnMap = columnMap3;
        return columnMap3;
    }

    public boolean contains(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return containsRow(obj) && containsColumn(obj2);
    }

    public boolean containsColumn(@CheckForNull Object obj) {
        return this.columnKeyToIndex.containsKey(obj);
    }

    public boolean containsRow(@CheckForNull Object obj) {
        return this.rowKeyToIndex.containsKey(obj);
    }

    public boolean containsValue(@CheckForNull Object obj) {
        for (V[] vArr : this.array) {
            for (V equal : r7[r2]) {
                if (Objects.equal(obj, equal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V erase(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return set(num.intValue(), num2.intValue(), (Object) null);
    }

    public void eraseAll() {
        for (V[] fill : this.array) {
            Arrays.fill(fill, (Object) null);
        }
    }

    @CheckForNull
    public V get(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return at(num.intValue(), num2.intValue());
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public boolean isEmpty() {
        return this.rowList.isEmpty() || this.columnList.isEmpty();
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V put(R r2, C c3, @CheckForNull V v2) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(c3);
        Integer num = this.rowKeyToIndex.get(r2);
        boolean z2 = false;
        Preconditions.checkArgument(num != null, "Row %s not in %s", (Object) r2, (Object) this.rowList);
        Integer num2 = this.columnKeyToIndex.get(c3);
        if (num2 != null) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Column %s not in %s", (Object) c3, (Object) this.columnList);
        return set(num.intValue(), num2.intValue(), v2);
    }

    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @CanIgnoreReturnValue
    @CheckForNull
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public V remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
        throw new UnsupportedOperationException();
    }

    public Map<C, V> row(R r2) {
        Preconditions.checkNotNull(r2);
        Integer num = this.rowKeyToIndex.get(r2);
        return num == null ? Collections.emptyMap() : new Row(num.intValue());
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    public Map<R, Map<C, V>> rowMap() {
        ArrayTable<R, C, V>.RowMap rowMap2 = this.rowMap;
        if (rowMap2 != null) {
            return rowMap2;
        }
        ArrayTable<R, C, V>.RowMap rowMap3 = new RowMap();
        this.rowMap = rowMap3;
        return rowMap3;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V set(int i3, int i4, @CheckForNull V v2) {
        Preconditions.checkElementIndex(i3, this.rowList.size());
        Preconditions.checkElementIndex(i4, this.columnList.size());
        V[] vArr = this.array[i3];
        V v3 = vArr[i4];
        vArr[i4] = v2;
        return v3;
    }

    public int size() {
        return this.columnList.size() * this.rowList.size();
    }

    @GwtIncompatible
    public V[][] toArray(Class<V> cls) {
        V[][] vArr = (Object[][]) Array.newInstance(cls, new int[]{this.rowList.size(), this.columnList.size()});
        for (int i3 = 0; i3 < this.rowList.size(); i3++) {
            V[] vArr2 = this.array[i3];
            System.arraycopy(vArr2, 0, vArr[i3], 0, vArr2.length);
        }
        return vArr;
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public Collection<V> values() {
        return super.values();
    }

    public Iterator<V> valuesIterator() {
        return new AbstractIndexedListIterator<V>(size()) {
            @CheckForNull
            public V get(int i3) {
                return ArrayTable.this.getValue(i3);
            }
        };
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, ? extends V> table) {
        if (table instanceof ArrayTable) {
            return new ArrayTable<>((ArrayTable) table);
        }
        return new ArrayTable<>(table);
    }

    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    private ArrayTable(Table<R, C, ? extends V> table) {
        this(table.rowKeySet(), table.columnKeySet());
        putAll(table);
    }

    private ArrayTable(ArrayTable<R, C, V> arrayTable) {
        ImmutableList<R> immutableList = arrayTable.rowList;
        this.rowList = immutableList;
        ImmutableList<C> immutableList2 = arrayTable.columnList;
        this.columnList = immutableList2;
        this.rowKeyToIndex = arrayTable.rowKeyToIndex;
        this.columnKeyToIndex = arrayTable.columnKeyToIndex;
        int size = immutableList.size();
        int[] iArr = new int[2];
        iArr[1] = immutableList2.size();
        iArr[0] = size;
        V[][] vArr = (Object[][]) Array.newInstance(Object.class, iArr);
        this.array = vArr;
        for (int i3 = 0; i3 < this.rowList.size(); i3++) {
            V[] vArr2 = arrayTable.array[i3];
            System.arraycopy(vArr2, 0, vArr[i3], 0, vArr2.length);
        }
    }
}
