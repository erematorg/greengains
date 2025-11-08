package com.apollographql.apollo3.api;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bJ'\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0002\u0010\fR\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo3/api/ImmutableMapBuilder;", "K", "V", "", "()V", "map", "", "build", "", "put", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/apollographql/apollo3/api/ImmutableMapBuilder;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ImmutableMapBuilder<K, V> {
    @NotNull
    private final Map<K, V> map = new LinkedHashMap();

    @NotNull
    public final Map<K, V> build() {
        return this.map;
    }

    @NotNull
    public final ImmutableMapBuilder<K, V> put(K k2, V v2) {
        this.map.put(k2, v2);
        return this;
    }
}
