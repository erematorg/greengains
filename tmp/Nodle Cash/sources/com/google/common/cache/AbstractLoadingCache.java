package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class AbstractLoadingCache<K, V> extends AbstractCache<K, V> implements LoadingCache<K, V> {
    public final V apply(K k2) {
        return getUnchecked(k2);
    }

    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        for (Object next : iterable) {
            if (!newLinkedHashMap.containsKey(next)) {
                newLinkedHashMap.put(next, get(next));
            }
        }
        return ImmutableMap.copyOf(newLinkedHashMap);
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
        throw new UnsupportedOperationException();
    }
}
