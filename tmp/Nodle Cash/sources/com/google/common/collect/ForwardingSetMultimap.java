package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingSetMultimap<K, V> extends ForwardingMultimap<K, V> implements SetMultimap<K, V> {
    public abstract SetMultimap<K, V> delegate();

    public Set<Map.Entry<K, V>> entries() {
        return delegate().entries();
    }

    public Set<V> get(@ParametricNullness K k2) {
        return delegate().get((Object) k2);
    }

    @CanIgnoreReturnValue
    public Set<V> removeAll(@CheckForNull Object obj) {
        return delegate().removeAll(obj);
    }

    @CanIgnoreReturnValue
    public Set<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
        return delegate().replaceValues((Object) k2, (Iterable) iterable);
    }
}
