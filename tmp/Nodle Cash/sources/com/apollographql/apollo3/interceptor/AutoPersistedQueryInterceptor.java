package com.apollographql.apollo3.interceptor;

import com.apollographql.apollo3.AutoPersistedQueryInfo;
import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Error;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J4\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\b0\u0007\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012H\u0002J\u0018\u0010\u0014\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012H\u0002J*\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b\u0000\u0010\t*\u00020\n*\b\u0012\u0004\u0012\u0002H\t0\b2\u0006\u0010\u0016\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/apollographql/apollo3/interceptor/AutoPersistedQueryInterceptor;", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "httpMethodForHashedQueries", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethodForDocumentQueries", "(Lcom/apollographql/apollo3/api/http/HttpMethod;Lcom/apollographql/apollo3/api/http/HttpMethod;)V", "intercept", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "chain", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptorChain;", "isPersistedQueryNotFound", "", "errors", "", "Lcom/apollographql/apollo3/api/Error;", "isPersistedQueryNotSupported", "withAutoPersistedQueryInfo", "hit", "Companion", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nAutoPersistedQueryInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AutoPersistedQueryInterceptor.kt\ncom/apollographql/apollo3/interceptor/AutoPersistedQueryInterceptor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,79:1\n1747#2,3:80\n1747#2,3:83\n*S KotlinDebug\n*F\n+ 1 AutoPersistedQueryInterceptor.kt\ncom/apollographql/apollo3/interceptor/AutoPersistedQueryInterceptor\n*L\n67#1:80,3\n71#1:83,3\n*E\n"})
public final class AutoPersistedQueryInterceptor implements ApolloInterceptor {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final String PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED = "PersistedQueryNotSupported";
    @NotNull
    private static final String PROTOCOL_NEGOTIATION_ERROR_QUERY_NOT_FOUND = "PersistedQueryNotFound";
    /* access modifiers changed from: private */
    @NotNull
    public final HttpMethod httpMethodForDocumentQueries;
    @NotNull
    private final HttpMethod httpMethodForHashedQueries;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/interceptor/AutoPersistedQueryInterceptor$Companion;", "", "()V", "PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED", "", "PROTOCOL_NEGOTIATION_ERROR_QUERY_NOT_FOUND", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public AutoPersistedQueryInterceptor(@NotNull HttpMethod httpMethod, @NotNull HttpMethod httpMethod2) {
        Intrinsics.checkNotNullParameter(httpMethod, "httpMethodForHashedQueries");
        Intrinsics.checkNotNullParameter(httpMethod2, "httpMethodForDocumentQueries");
        this.httpMethodForHashedQueries = httpMethod;
        this.httpMethodForDocumentQueries = httpMethod2;
    }

    /* access modifiers changed from: private */
    public final boolean isPersistedQueryNotFound(List<Error> list) {
        if (list == null) {
            return false;
        }
        Iterable<Error> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (Error message : iterable) {
            if (StringsKt__StringsJVMKt.equals(message.getMessage(), PROTOCOL_NEGOTIATION_ERROR_QUERY_NOT_FOUND, true)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final boolean isPersistedQueryNotSupported(List<Error> list) {
        if (list == null) {
            return false;
        }
        Iterable<Error> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (Error message : iterable) {
            if (StringsKt__StringsJVMKt.equals(message.getMessage(), PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED, true)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final <D extends Operation.Data> ApolloResponse<D> withAutoPersistedQueryInfo(ApolloResponse<D> apolloResponse, boolean z2) {
        return apolloResponse.newBuilder().addExecutionContext(new AutoPersistedQueryInfo(z2)).build();
    }

    @NotNull
    public <D extends Operation.Data> Flow<ApolloResponse<D>> intercept(@NotNull ApolloRequest<D> apolloRequest, @NotNull ApolloInterceptorChain apolloInterceptorChain) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        Intrinsics.checkNotNullParameter(apolloInterceptorChain, "chain");
        Boolean enableAutoPersistedQueries = apolloRequest.getEnableAutoPersistedQueries();
        if (!(enableAutoPersistedQueries != null ? enableAutoPersistedQueries.booleanValue() : true)) {
            return apolloInterceptorChain.proceed(apolloRequest);
        }
        boolean z2 = apolloRequest.getOperation() instanceof Mutation;
        return FlowKt.flow(new AutoPersistedQueryInterceptor$intercept$1(apolloInterceptorChain, apolloRequest.newBuilder().httpMethod(z2 ? HttpMethod.Post : this.httpMethodForHashedQueries).sendDocument(Boolean.FALSE).sendApqExtensions(Boolean.TRUE).build(), this, z2, (Continuation<? super AutoPersistedQueryInterceptor$intercept$1>) null));
    }
}
