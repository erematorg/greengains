package com.apollographql.apollo3.api;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.api.C0216AdapterContext;
import com.apollographql.apollo3.api.Executable;
import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.internal.Version2CustomTypeAdapterToAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB/\b\u0002\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0013J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0005\"\b\b\u0000\u0010\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u001aH\u0007R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "customScalarAdapters", "", "", "Lcom/apollographql/apollo3/api/Adapter;", "adapterContext", "Lcom/apollographql/apollo3/api/AdapterContext;", "unsafe", "", "(Ljava/util/Map;Lcom/apollographql/apollo3/api/AdapterContext;Z)V", "getAdapterContext", "()Lcom/apollographql/apollo3/api/AdapterContext;", "adaptersMap", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "newBuilder", "Lcom/apollographql/apollo3/api/CustomScalarAdapters$Builder;", "responseAdapterFor", "T", "", "customScalar", "Lcom/apollographql/apollo3/api/CustomScalarType;", "variables", "", "Builder", "Key", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class CustomScalarAdapters implements ExecutionContext.Element {
    @NotNull
    @JvmField
    public static final CustomScalarAdapters Empty = new Builder().build();
    @NotNull
    public static final Key Key = new Key((DefaultConstructorMarker) null);
    @NotNull
    @JvmField
    public static final CustomScalarAdapters PassThrough = new Builder().unsafe(true).build();
    @NotNull
    private final C0216AdapterContext adapterContext;
    /* access modifiers changed from: private */
    @NotNull
    public final Map<String, Adapter<?>> adaptersMap;
    private final boolean unsafe;

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\"\u0010\u000b\u001a\u00020\u0000\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\f0\bJ$\u0010\u000b\u001a\u00020\u0000\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u0011H\u0007J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/apollographql/apollo3/api/CustomScalarAdapters$Builder;", "", "()V", "adapterContext", "Lcom/apollographql/apollo3/api/AdapterContext;", "adaptersMap", "", "", "Lcom/apollographql/apollo3/api/Adapter;", "unsafe", "", "add", "T", "customScalarType", "Lcom/apollographql/apollo3/api/CustomScalarType;", "customScalarAdapter", "customTypeAdapter", "Lcom/apollographql/apollo3/api/CustomTypeAdapter;", "addAll", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "build", "clear", "", "variables", "Lcom/apollographql/apollo3/api/Executable$Variables;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @NotNull
        private C0216AdapterContext adapterContext = new C0216AdapterContext.Builder().build();
        @NotNull
        private final Map<String, Adapter<?>> adaptersMap = new LinkedHashMap();
        private boolean unsafe;

        @NotNull
        public final Builder adapterContext(@NotNull C0216AdapterContext adapterContext2) {
            Intrinsics.checkNotNullParameter(adapterContext2, "adapterContext");
            this.adapterContext = adapterContext2;
            return this;
        }

        @NotNull
        public final <T> Builder add(@NotNull CustomScalarType customScalarType, @NotNull Adapter<T> adapter) {
            Intrinsics.checkNotNullParameter(customScalarType, "customScalarType");
            Intrinsics.checkNotNullParameter(adapter, "customScalarAdapter");
            this.adaptersMap.put(customScalarType.getName(), adapter);
            return this;
        }

        @NotNull
        public final Builder addAll(@NotNull CustomScalarAdapters customScalarAdapters) {
            Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
            this.adaptersMap.putAll(customScalarAdapters.adaptersMap);
            return this;
        }

        @NotNull
        public final CustomScalarAdapters build() {
            return new CustomScalarAdapters(this.adaptersMap, this.adapterContext, this.unsafe, (DefaultConstructorMarker) null);
        }

        public final void clear() {
            this.adaptersMap.clear();
        }

        @ApolloExperimental
        @NotNull
        public final Builder unsafe(boolean z2) {
            this.unsafe = z2;
            return this;
        }

        @NotNull
        @Deprecated(message = "Use AdapterContext.Builder.variables() instead")
        public final Builder variables(@NotNull Executable.Variables variables) {
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.adapterContext = this.adapterContext.newBuilder().variables(variables).build();
            return this;
        }

        @NotNull
        @Deprecated(message = "Used for backward compatibility with 2.x")
        public final <T> Builder add(@NotNull CustomScalarType customScalarType, @NotNull CustomTypeAdapter<T> customTypeAdapter) {
            Intrinsics.checkNotNullParameter(customScalarType, "customScalarType");
            Intrinsics.checkNotNullParameter(customTypeAdapter, "customTypeAdapter");
            this.adaptersMap.put(customScalarType.getName(), new Version2CustomTypeAdapterToAdapter(customTypeAdapter));
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00020\u00028\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/api/CustomScalarAdapters$Key;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "()V", "Empty", "PassThrough", "getPassThrough$annotations", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Key implements ExecutionContext.Key<CustomScalarAdapters> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @ApolloExperimental
        public static /* synthetic */ void getPassThrough$annotations() {
        }

        private Key() {
        }
    }

    public /* synthetic */ CustomScalarAdapters(Map map, C0216AdapterContext adapterContext2, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, adapterContext2, z2);
    }

    public <R> R fold(R r2, @NotNull Function2<? super R, ? super ExecutionContext.Element, ? extends R> function2) {
        return ExecutionContext.Element.DefaultImpls.fold(this, r2, function2);
    }

    @Nullable
    public <E extends ExecutionContext.Element> E get(@NotNull ExecutionContext.Key<E> key) {
        return ExecutionContext.Element.DefaultImpls.get(this, key);
    }

    @NotNull
    public final C0216AdapterContext getAdapterContext() {
        return this.adapterContext;
    }

    @NotNull
    public ExecutionContext.Key<?> getKey() {
        return Key;
    }

    @NotNull
    public ExecutionContext minusKey(@NotNull ExecutionContext.Key<?> key) {
        return ExecutionContext.Element.DefaultImpls.minusKey(this, key);
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder().addAll(this);
    }

    @NotNull
    public ExecutionContext plus(@NotNull ExecutionContext executionContext) {
        return ExecutionContext.Element.DefaultImpls.plus(this, executionContext);
    }

    @NotNull
    public final <T> Adapter<T> responseAdapterFor(@NotNull CustomScalarType customScalarType) {
        Adapter adapter;
        Intrinsics.checkNotNullParameter(customScalarType, "customScalar");
        if (this.adaptersMap.get(customScalarType.getName()) != null) {
            adapter = this.adaptersMap.get(customScalarType.getName());
        } else if (Intrinsics.areEqual((Object) customScalarType.getClassName(), (Object) "com.apollographql.apollo3.api.Upload")) {
            adapter = Adapters.UploadAdapter;
        } else if (CollectionsKt.listOf("kotlin.String", "java.lang.String").contains(customScalarType.getClassName())) {
            adapter = Adapters.StringAdapter;
        } else if (CollectionsKt.listOf("kotlin.Boolean", "java.lang.Boolean").contains(customScalarType.getClassName())) {
            adapter = Adapters.BooleanAdapter;
        } else if (CollectionsKt.listOf("kotlin.Int", "java.lang.Int").contains(customScalarType.getClassName())) {
            adapter = Adapters.IntAdapter;
        } else if (CollectionsKt.listOf("kotlin.Double", "java.lang.Double").contains(customScalarType.getClassName())) {
            adapter = Adapters.DoubleAdapter;
        } else if (CollectionsKt.listOf("kotlin.Long", "java.lang.Long").contains(customScalarType.getClassName())) {
            adapter = Adapters.LongAdapter;
        } else if (CollectionsKt.listOf("kotlin.Float", "java.lang.Float").contains(customScalarType.getClassName())) {
            adapter = Adapters.FloatAdapter;
        } else if (CollectionsKt.listOf("kotlin.Any", "java.lang.Object").contains(customScalarType.getClassName())) {
            adapter = Adapters.AnyAdapter;
        } else if (this.unsafe) {
            adapter = new PassThroughAdapter();
        } else {
            throw new IllegalStateException(("Can't map GraphQL type: `" + customScalarType.getName() + "` to: `" + customScalarType.getClassName() + "`. Did you forget to add a CustomScalarAdapter?").toString());
        }
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type com.apollographql.apollo3.api.Adapter<T of com.apollographql.apollo3.api.CustomScalarAdapters.responseAdapterFor>");
        return adapter;
    }

    @NotNull
    @Deprecated(message = "Use adapterContext.variables() instead", replaceWith = @ReplaceWith(expression = "adapterContext.variables()", imports = {}))
    public final Set<String> variables() {
        return this.adapterContext.variables();
    }

    private CustomScalarAdapters(Map<String, ? extends Adapter<?>> map, C0216AdapterContext adapterContext2, boolean z2) {
        this.adapterContext = adapterContext2;
        this.unsafe = z2;
        this.adaptersMap = map;
    }
}
