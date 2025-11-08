package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@DoNotMock("Use ImmutableTable, HashBasedTable, or another implementation")
public interface Table<R, C, V> {

    public interface Cell<R, C, V> {
        boolean equals(@CheckForNull Object obj);

        @ParametricNullness
        C getColumnKey();

        @ParametricNullness
        R getRowKey();

        @ParametricNullness
        V getValue();

        int hashCode();
    }

    Set<Cell<R, C, V>> cellSet();

    void clear();

    Map<R, V> column(@ParametricNullness C c3);

    Set<C> columnKeySet();

    Map<C, Map<R, V>> columnMap();

    boolean contains(@CompatibleWith("R") @CheckForNull Object obj, @CompatibleWith("C") @CheckForNull Object obj2);

    boolean containsColumn(@CompatibleWith("C") @CheckForNull Object obj);

    boolean containsRow(@CompatibleWith("R") @CheckForNull Object obj);

    boolean containsValue(@CompatibleWith("V") @CheckForNull Object obj);

    boolean equals(@CheckForNull Object obj);

    @CheckForNull
    V get(@CompatibleWith("R") @CheckForNull Object obj, @CompatibleWith("C") @CheckForNull Object obj2);

    int hashCode();

    boolean isEmpty();

    @CheckForNull
    @CanIgnoreReturnValue
    V put(@ParametricNullness R r2, @ParametricNullness C c3, @ParametricNullness V v2);

    void putAll(Table<? extends R, ? extends C, ? extends V> table);

    @CheckForNull
    @CanIgnoreReturnValue
    V remove(@CompatibleWith("R") @CheckForNull Object obj, @CompatibleWith("C") @CheckForNull Object obj2);

    Map<C, V> row(@ParametricNullness R r2);

    Set<R> rowKeySet();

    Map<R, Map<C, V>> rowMap();

    int size();

    Collection<V> values();
}
