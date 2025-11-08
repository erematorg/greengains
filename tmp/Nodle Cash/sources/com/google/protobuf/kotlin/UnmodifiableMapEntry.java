package com.google.protobuf.kotlin;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010&\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0000X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00028\u0001X\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/google/protobuf/kotlin/UnmodifiableMapEntry;", "K", "V", "", "delegate", "(Ljava/util/Map$Entry;)V", "key", "getKey", "()Ljava/lang/Object;", "value", "getValue", "java_kotlin-shared_runtime"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class UnmodifiableMapEntry<K, V> implements Map.Entry<K, V>, KMappedMarker {
    private final /* synthetic */ Map.Entry<K, V> $$delegate_0;

    public UnmodifiableMapEntry(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "delegate");
        this.$$delegate_0 = entry;
    }

    public K getKey() {
        return this.$$delegate_0.getKey();
    }

    public V getValue() {
        return this.$$delegate_0.getValue();
    }

    public V setValue(V v2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
