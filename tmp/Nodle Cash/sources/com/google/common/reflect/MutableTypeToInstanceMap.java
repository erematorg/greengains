package com.google.common.reflect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class MutableTypeToInstanceMap<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
    private final Map<TypeToken<? extends B>, B> backingMap = Maps.newHashMap();

    public static final class UnmodifiableEntry<K, V> extends ForwardingMapEntry<K, V> {
        private final Map.Entry<K, V> delegate;

        private UnmodifiableEntry(Map.Entry<K, V> entry) {
            this.delegate = (Map.Entry) Preconditions.checkNotNull(entry);
        }

        public static /* synthetic */ UnmodifiableEntry a(Map.Entry entry) {
            return new UnmodifiableEntry(entry);
        }

        public static <K, V> Set<Map.Entry<K, V>> transformEntries(final Set<Map.Entry<K, V>> set) {
            return new ForwardingSet<Map.Entry<K, V>>() {
                public Iterator<Map.Entry<K, V>> iterator() {
                    return UnmodifiableEntry.transformEntries(super.iterator());
                }

                public Object[] toArray() {
                    return standardToArray();
                }

                public <T> T[] toArray(T[] tArr) {
                    return standardToArray(tArr);
                }

                public Set<Map.Entry<K, V>> delegate() {
                    return set;
                }
            };
        }

        @ParametricNullness
        public V setValue(@ParametricNullness V v2) {
            throw new UnsupportedOperationException();
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.google.common.base.Function, java.lang.Object] */
        /* access modifiers changed from: private */
        public static <K, V> Iterator<Map.Entry<K, V>> transformEntries(Iterator<Map.Entry<K, V>> it) {
            return Iterators.transform(it, new Object());
        }

        public Map.Entry<K, V> delegate() {
            return this.delegate;
        }
    }

    @CheckForNull
    private <T extends B> T trustedGet(TypeToken<T> typeToken) {
        return this.backingMap.get(typeToken);
    }

    @CheckForNull
    private <T extends B> T trustedPut(TypeToken<T> typeToken, @ParametricNullness T t2) {
        return this.backingMap.put(typeToken, t2);
    }

    public Set<Map.Entry<TypeToken<? extends B>, B>> entrySet() {
        return UnmodifiableEntry.transformEntries(super.entrySet());
    }

    @CheckForNull
    public <T extends B> T getInstance(Class<T> cls) {
        return trustedGet(TypeToken.of(cls));
    }

    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> cls, @ParametricNullness T t2) {
        return trustedPut(TypeToken.of(cls), t2);
    }

    public Map<TypeToken<? extends B>, B> delegate() {
        return this.backingMap;
    }

    @CheckForNull
    public <T extends B> T getInstance(TypeToken<T> typeToken) {
        return trustedGet(typeToken.rejectTypeVariables());
    }

    @CanIgnoreReturnValue
    @CheckForNull
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public B put(TypeToken<? extends B> typeToken, @ParametricNullness B b3) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(TypeToken<T> typeToken, @ParametricNullness T t2) {
        return trustedPut(typeToken.rejectTypeVariables(), t2);
    }
}
