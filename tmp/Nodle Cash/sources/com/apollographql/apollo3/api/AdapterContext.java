package com.apollographql.apollo3.api;

import com.apollographql.apollo3.annotations.ApolloInternal;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007¨\u0006\u0005"}, d2 = {"withDeferredFragmentIds", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "deferredFragmentIds", "", "Lcom/apollographql/apollo3/api/DeferredFragmentIdentifier;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@JvmName(name = "-AdapterContext")
/* renamed from: com.apollographql.apollo3.api.-AdapterContext  reason: invalid class name */
public final class AdapterContext {
    @NotNull
    @ApolloInternal
    public static final CustomScalarAdapters withDeferredFragmentIds(@NotNull CustomScalarAdapters customScalarAdapters, @NotNull Set<DeferredFragmentIdentifier> set) {
        Intrinsics.checkNotNullParameter(customScalarAdapters, "<this>");
        Intrinsics.checkNotNullParameter(set, "deferredFragmentIds");
        return customScalarAdapters.newBuilder().adapterContext(customScalarAdapters.getAdapterContext().newBuilder().mergedDeferredFragmentIds(set).build()).build();
    }
}
