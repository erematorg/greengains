package com.apollographql.apollo3.api.test;

import A.a;
import com.apollographql.apollo3.annotations.ApolloExperimental;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@ApolloExperimental
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\"\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002¢\u0006\u0002\u0010\rJ*\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0011R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/api/test/StubbedProperty;", "T", "", "map", "", "", "responseName", "(Ljava/util/Map;Ljava/lang/String;)V", "getValue", "mapBuilder", "Lcom/apollographql/apollo3/api/test/MapBuilder;", "property", "Lkotlin/reflect/KProperty;", "(Lcom/apollographql/apollo3/api/test/MapBuilder;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Lcom/apollographql/apollo3/api/test/MapBuilder;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class StubbedProperty<T> {
    @NotNull
    private final Map<String, Object> map;
    @NotNull
    private final String responseName;

    public StubbedProperty(@NotNull Map<String, Object> map2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(map2, "map");
        Intrinsics.checkNotNullParameter(str, "responseName");
        this.map = map2;
        this.responseName = str;
    }

    public final T getValue(@NotNull MapBuilder mapBuilder, @NotNull KProperty<?> kProperty) {
        Intrinsics.checkNotNullParameter(mapBuilder, "mapBuilder");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        if (this.map.containsKey(this.responseName)) {
            return this.map.get(this.responseName);
        }
        throw new IllegalStateException(a.n(new StringBuilder("Property "), this.responseName, " is not set").toString());
    }

    public final void setValue(@NotNull MapBuilder mapBuilder, @NotNull KProperty<?> kProperty, T t2) {
        Intrinsics.checkNotNullParameter(mapBuilder, "mapBuilder");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        this.map.put(this.responseName, t2);
    }
}
