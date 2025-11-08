package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u000f\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H@¢\u0006\u0002\u0010\u0019R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/apollographql/apollo3/network/http/BatchingHttpEngine;", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "delegate", "batchIntervalMillis", "", "maxBatchSize", "", "exposeErrorBody", "", "(Lcom/apollographql/apollo3/network/http/HttpEngine;JIZ)V", "batchingHttpInterceptor", "Lcom/apollographql/apollo3/network/http/BatchingHttpInterceptor;", "getDelegate", "()Lcom/apollographql/apollo3/network/http/HttpEngine;", "engineInterceptor", "com/apollographql/apollo3/network/http/BatchingHttpEngine$engineInterceptor$1", "Lcom/apollographql/apollo3/network/http/BatchingHttpEngine$engineInterceptor$1;", "interceptorChain", "Lcom/apollographql/apollo3/network/http/DefaultHttpInterceptorChain;", "dispose", "", "execute", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "Use ApolloClient.Builder.batching instead")
public final class BatchingHttpEngine implements HttpEngine {
    @NotNull
    private final BatchingHttpInterceptor batchingHttpInterceptor;
    @NotNull
    private final HttpEngine delegate;
    @NotNull
    private final BatchingHttpEngine$engineInterceptor$1 engineInterceptor;
    @NotNull
    private final DefaultHttpInterceptorChain interceptorChain;

    @JvmOverloads
    public BatchingHttpEngine() {
        this((HttpEngine) null, 0, 0, false, 15, (DefaultConstructorMarker) null);
    }

    public void dispose() {
        this.delegate.dispose();
    }

    @Nullable
    public Object execute(@NotNull HttpRequest httpRequest, @NotNull Continuation<? super HttpResponse> continuation) {
        return this.batchingHttpInterceptor.intercept(httpRequest, this.interceptorChain, continuation);
    }

    @NotNull
    public final HttpEngine getDelegate() {
        return this.delegate;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BatchingHttpEngine(@NotNull HttpEngine httpEngine) {
        this(httpEngine, 0, 0, false, 14, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(httpEngine, "delegate");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BatchingHttpEngine(@NotNull HttpEngine httpEngine, long j2) {
        this(httpEngine, j2, 0, false, 12, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(httpEngine, "delegate");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BatchingHttpEngine(@NotNull HttpEngine httpEngine, long j2, int i3) {
        this(httpEngine, j2, i3, false, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(httpEngine, "delegate");
    }

    @JvmOverloads
    public BatchingHttpEngine(@NotNull HttpEngine httpEngine, long j2, int i3, boolean z2) {
        Intrinsics.checkNotNullParameter(httpEngine, "delegate");
        this.delegate = httpEngine;
        this.batchingHttpInterceptor = new BatchingHttpInterceptor(j2, i3, z2);
        BatchingHttpEngine$engineInterceptor$1 batchingHttpEngine$engineInterceptor$1 = new BatchingHttpEngine$engineInterceptor$1(this);
        this.engineInterceptor = batchingHttpEngine$engineInterceptor$1;
        this.interceptorChain = new DefaultHttpInterceptorChain(CollectionsKt.listOf(batchingHttpEngine$engineInterceptor$1), 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BatchingHttpEngine(HttpEngine httpEngine, long j2, int i3, boolean z2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? new DefaultHttpEngine(0, 1, (DefaultConstructorMarker) null) : httpEngine, (i4 & 2) != 0 ? 10 : j2, (i4 & 4) != 0 ? 10 : i3, (i4 & 8) != 0 ? false : z2);
    }
}
