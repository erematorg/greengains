package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingConcurrentMap<K, V> extends ForwardingMap<K, V> implements ConcurrentMap<K, V> {
    public abstract ConcurrentMap<K, V> delegate();

    @CheckForNull
    @CanIgnoreReturnValue
    public V putIfAbsent(K k2, V v2) {
        return delegate().putIfAbsent(k2, v2);
    }

    @CanIgnoreReturnValue
    public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return delegate().remove(obj, obj2);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V replace(K k2, V v2) {
        return delegate().replace(k2, v2);
    }

    @CanIgnoreReturnValue
    public boolean replace(K k2, V v2, V v3) {
        return delegate().replace(k2, v2, v3);
    }
}
