package com.apollographql.apollo3.api;

import androidx.compose.ui.autofill.a;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0002R\u001f\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR+\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00078F@FX\u0002¢\u0006\u0012\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012*\u0004\b\r\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/api/ObjectBuilder;", "Lcom/apollographql/apollo3/api/BuilderScope;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "(Lcom/apollographql/apollo3/api/CustomScalarAdapters;)V", "__fields", "", "", "", "get__fields", "()Ljava/util/Map;", "<set-?>", "__typename", "get__typename$delegate", "(Lcom/apollographql/apollo3/api/ObjectBuilder;)Ljava/lang/Object;", "get__typename", "()Ljava/lang/String;", "set__typename", "(Ljava/lang/String;)V", "getCustomScalarAdapters", "()Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "set", "", "key", "value", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class ObjectBuilder implements BuilderScope {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {a.m(ObjectBuilder.class, "__typename", "get__typename()Ljava/lang/String;", 0)};
    @NotNull
    private final Map<String, Object> __fields = new LinkedHashMap();
    @NotNull
    private final CustomScalarAdapters customScalarAdapters;

    public ObjectBuilder(@NotNull CustomScalarAdapters customScalarAdapters2) {
        Intrinsics.checkNotNullParameter(customScalarAdapters2, "customScalarAdapters");
        this.customScalarAdapters = customScalarAdapters2;
    }

    private static Object get__typename$delegate(ObjectBuilder objectBuilder) {
        return objectBuilder.__fields;
    }

    @NotNull
    public CustomScalarAdapters getCustomScalarAdapters() {
        return this.customScalarAdapters;
    }

    @NotNull
    public final Map<String, Object> get__fields() {
        return this.__fields;
    }

    @NotNull
    public final String get__typename() {
        return (String) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(this.__fields, $$delegatedProperties[0].getName());
    }

    public final void set(@NotNull String str, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.__fields.put(str, obj);
    }

    public final void set__typename(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.__fields.put($$delegatedProperties[0].getName(), str);
    }
}
