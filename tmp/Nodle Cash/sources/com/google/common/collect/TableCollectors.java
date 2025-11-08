package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

@GwtCompatible
@IgnoreJRERequirement
@ElementTypesAreNonnullByDefault
final class TableCollectors {

    public static final class ImmutableTableCollectorState<R, C, V> {
        final List<MutableCell<R, C, V>> insertionOrder;
        final Table<R, C, MutableCell<R, C, V>> table;

        private ImmutableTableCollectorState() {
            this.insertionOrder = new ArrayList();
            this.table = HashBasedTable.create();
        }

        public ImmutableTableCollectorState<R, C, V> combine(ImmutableTableCollectorState<R, C, V> immutableTableCollectorState, BinaryOperator<V> binaryOperator) {
            for (MutableCell next : immutableTableCollectorState.insertionOrder) {
                put(next.getRowKey(), next.getColumnKey(), next.getValue(), binaryOperator);
            }
            return this;
        }

        public void put(R r2, C c3, V v2, BinaryOperator<V> binaryOperator) {
            MutableCell mutableCell = this.table.get(r2, c3);
            if (mutableCell == null) {
                MutableCell mutableCell2 = new MutableCell(r2, c3, v2);
                this.insertionOrder.add(mutableCell2);
                this.table.put(r2, c3, mutableCell2);
                return;
            }
            mutableCell.merge(v2, binaryOperator);
        }

        public ImmutableTable<R, C, V> toTable() {
            return ImmutableTable.copyOf(this.insertionOrder);
        }
    }

    @IgnoreJRERequirement
    public static final class MutableCell<R, C, V> extends Tables.AbstractCell<R, C, V> {
        private final C column;
        private final R row;
        private V value;

        public MutableCell(R r2, C c3, V v2) {
            this.row = Preconditions.checkNotNull(r2, "row");
            this.column = Preconditions.checkNotNull(c3, "column");
            this.value = Preconditions.checkNotNull(v2, "value");
        }

        public C getColumnKey() {
            return this.column;
        }

        public R getRowKey() {
            return this.row;
        }

        public V getValue() {
            return this.value;
        }

        public void merge(V v2, BinaryOperator<V> binaryOperator) {
            Preconditions.checkNotNull(v2, "value");
            this.value = Preconditions.checkNotNull(binaryOperator.apply(this.value, v2), "mergeFunction.apply");
        }
    }

    private TableCollectors() {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ImmutableTableCollectorState lambda$toImmutableTable$1() {
        return new ImmutableTableCollectorState();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$toTable$5(Object obj, Object obj2) {
        throw new IllegalStateException("Conflicting values " + obj + " and " + obj2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Table lambda$toTable$7(BinaryOperator binaryOperator, Table table, Table table2) {
        for (Table.Cell cell : table2.cellSet()) {
            mergeTables(table, cell.getRowKey(), cell.getColumnKey(), cell.getValue(), binaryOperator);
        }
        return table;
    }

    /* access modifiers changed from: private */
    public static <R, C, V> void mergeTables(Table<R, C, V> table, @ParametricNullness R r2, @ParametricNullness C c3, @ParametricNullness V v2, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(v2);
        V v3 = table.get(r2, c3);
        if (v3 == null) {
            table.put(r2, c3, v2);
            return;
        }
        Object apply = binaryOperator.apply(v3, v2);
        if (apply == null) {
            table.remove(r2, c3);
        } else {
            table.put(r2, c3, apply);
        }
    }

    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3) {
        Preconditions.checkNotNull(function, "rowFunction");
        Preconditions.checkNotNull(function2, "columnFunction");
        Preconditions.checkNotNull(function3, "valueFunction");
        return Collector.of(new j(1), new l(function, function2, function3), new g(1), new k(1), new Collector.Characteristics[0]);
    }

    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3, Supplier<I> supplier) {
        return toTable(function, function2, function3, new g(0), supplier);
    }

    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3, BinaryOperator<V> binaryOperator, Supplier<I> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(function3);
        Preconditions.checkNotNull(binaryOperator);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new h(function, function2, function3, binaryOperator, 0), new i(binaryOperator, 0), new Collector.Characteristics[0]);
    }

    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(function, "rowFunction");
        Preconditions.checkNotNull(function2, "columnFunction");
        Preconditions.checkNotNull(function3, "valueFunction");
        Preconditions.checkNotNull(binaryOperator, "mergeFunction");
        return Collector.of(new j(0), new h(function, function2, function3, binaryOperator, 1), new i(binaryOperator, 1), new k(0), new Collector.Characteristics[0]);
    }
}
