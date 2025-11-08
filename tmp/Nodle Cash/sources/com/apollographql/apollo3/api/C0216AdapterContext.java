package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Executable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B)\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001e\u0010\f\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/AdapterContext;", "", "variables", "Lcom/apollographql/apollo3/api/Executable$Variables;", "mergedDeferredFragmentIds", "", "Lcom/apollographql/apollo3/api/DeferredFragmentIdentifier;", "serializeVariablesWithDefaultBooleanValues", "", "(Lcom/apollographql/apollo3/api/Executable$Variables;Ljava/util/Set;Z)V", "getSerializeVariablesWithDefaultBooleanValues", "()Z", "hasDeferredFragment", "path", "", "label", "", "newBuilder", "Lcom/apollographql/apollo3/api/AdapterContext$Builder;", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nAdapterContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AdapterContext.kt\ncom/apollographql/apollo3/api/AdapterContext\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,71:1\n526#2:72\n511#2,6:73\n*S KotlinDebug\n*F\n+ 1 AdapterContext.kt\ncom/apollographql/apollo3/api/AdapterContext\n*L\n23#1:72\n23#1:73,6\n*E\n"})
/* renamed from: com.apollographql.apollo3.api.AdapterContext  reason: case insensitive filesystem */
public final class C0216AdapterContext {
    @Nullable
    private final Set<DeferredFragmentIdentifier> mergedDeferredFragmentIds;
    private final boolean serializeVariablesWithDefaultBooleanValues;
    @Nullable
    private final Executable.Variables variables;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0003\u001a\u00020\u00002\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J\u0015\u0010\u0006\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ\u0010\u0010\t\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/api/AdapterContext$Builder;", "", "()V", "mergedDeferredFragmentIds", "", "Lcom/apollographql/apollo3/api/DeferredFragmentIdentifier;", "serializeVariablesWithDefaultBooleanValues", "", "Ljava/lang/Boolean;", "variables", "Lcom/apollographql/apollo3/api/Executable$Variables;", "build", "Lcom/apollographql/apollo3/api/AdapterContext;", "(Ljava/lang/Boolean;)Lcom/apollographql/apollo3/api/AdapterContext$Builder;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* renamed from: com.apollographql.apollo3.api.AdapterContext$Builder */
    public static final class Builder {
        @Nullable
        private Set<DeferredFragmentIdentifier> mergedDeferredFragmentIds;
        @Nullable
        private Boolean serializeVariablesWithDefaultBooleanValues;
        @Nullable
        private Executable.Variables variables;

        @NotNull
        public final C0216AdapterContext build() {
            return new C0216AdapterContext(this.variables, this.mergedDeferredFragmentIds, Intrinsics.areEqual((Object) this.serializeVariablesWithDefaultBooleanValues, (Object) Boolean.TRUE), (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Builder mergedDeferredFragmentIds(@Nullable Set<DeferredFragmentIdentifier> set) {
            this.mergedDeferredFragmentIds = set;
            return this;
        }

        @NotNull
        public final Builder serializeVariablesWithDefaultBooleanValues(@Nullable Boolean bool) {
            this.serializeVariablesWithDefaultBooleanValues = bool;
            return this;
        }

        @NotNull
        public final Builder variables(@Nullable Executable.Variables variables2) {
            this.variables = variables2;
            return this;
        }
    }

    public /* synthetic */ C0216AdapterContext(Executable.Variables variables2, Set set, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(variables2, set, z2);
    }

    public final boolean getSerializeVariablesWithDefaultBooleanValues() {
        return this.serializeVariablesWithDefaultBooleanValues;
    }

    public final boolean hasDeferredFragment(@NotNull List<? extends Object> list, @Nullable String str) {
        Intrinsics.checkNotNullParameter(list, "path");
        Set<DeferredFragmentIdentifier> set = this.mergedDeferredFragmentIds;
        if (set == null) {
            return true;
        }
        return set.contains(new DeferredFragmentIdentifier(list, str));
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder().variables(this.variables).mergedDeferredFragmentIds(this.mergedDeferredFragmentIds).serializeVariablesWithDefaultBooleanValues(Boolean.valueOf(this.serializeVariablesWithDefaultBooleanValues));
    }

    @NotNull
    public final Set<String> variables() {
        Executable.Variables variables2 = this.variables;
        if (variables2 == null) {
            return SetsKt.emptySet();
        }
        Map<String, Object> valueMap = variables2.getValueMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : valueMap.entrySet()) {
            if (Intrinsics.areEqual(next.getValue(), (Object) Boolean.FALSE)) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        return linkedHashMap.keySet();
    }

    private C0216AdapterContext(Executable.Variables variables2, Set<DeferredFragmentIdentifier> set, boolean z2) {
        this.variables = variables2;
        this.mergedDeferredFragmentIds = set;
        this.serializeVariablesWithDefaultBooleanValues = z2;
    }
}
