package com.apollographql.apollo3.api.test;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.api.CompiledType;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@ApolloExperimental
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH&Jk\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142:\u0010\u0015\u001a\u001e\u0012\u001a\b\u0001\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r0\u00170\u0016\"\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r0\u0017¢\u0006\u0002\u0010\u0018R\"\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/api/test/MapBuilder;", "", "()V", "__map", "", "", "get__map", "()Ljava/util/Map;", "__shouldBeAssignedFields", "", "get__shouldBeAssignedFields", "()Ljava/util/Set;", "build", "", "resolve", "T", "responseName", "type", "Lcom/apollographql/apollo3/api/CompiledType;", "enumValues", "", "ctors", "", "Lkotlin/Function0;", "(Ljava/lang/String;Lcom/apollographql/apollo3/api/CompiledType;Ljava/util/List;[Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@ApolloTestBuilderMarker
public abstract class MapBuilder {
    @NotNull
    private final Map<String, Object> __map = new LinkedHashMap();
    @NotNull
    private final Set<String> __shouldBeAssignedFields = new LinkedHashSet();

    @NotNull
    public abstract Map<String, Object> build();

    @NotNull
    public final Map<String, Object> get__map() {
        return this.__map;
    }

    @NotNull
    public final Set<String> get__shouldBeAssignedFields() {
        return this.__shouldBeAssignedFields;
    }

    public final <T> T resolve(@NotNull String str, @NotNull CompiledType compiledType, @NotNull List<String> list, @NotNull Function0<? extends Map<String, ? extends Object>>... function0Arr) {
        Intrinsics.checkNotNullParameter(str, "responseName");
        Intrinsics.checkNotNullParameter(compiledType, "type");
        Intrinsics.checkNotNullParameter(list, "enumValues");
        Intrinsics.checkNotNullParameter(function0Arr, "ctors");
        if (this.__map.containsKey(str)) {
            return this.__map.get(str);
        }
        if (!this.__shouldBeAssignedFields.contains(str)) {
            TestResolver currentTestResolver = TestResolverKt.getCurrentTestResolver();
            if (currentTestResolver != null) {
                return currentTestResolver.resolve(str, compiledType, list, function0Arr);
            }
            throw new IllegalStateException("No TestResolver found, wrap with withTestResolver() {}");
        }
        throw new IllegalStateException(("Builder function was called but its result was not assigned to the corresponding field `" + str + "` which is certainly a mistake").toString());
    }
}
