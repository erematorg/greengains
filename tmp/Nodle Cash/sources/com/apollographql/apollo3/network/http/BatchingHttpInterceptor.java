package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.ApolloCall;
import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.internal.CloseableSingleThreadDispatcher;
import com.apollographql.apollo3.mpp.UtilsKt;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0002 !B%\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\u0017H@¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/apollographql/apollo3/network/http/BatchingHttpInterceptor;", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "batchIntervalMillis", "", "maxBatchSize", "", "exposeErrorBody", "", "(JIZ)V", "creationTime", "dispatcher", "Lcom/apollographql/apollo3/internal/CloseableSingleThreadDispatcher;", "disposed", "interceptorChain", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "pendingRequests", "", "Lcom/apollographql/apollo3/network/http/BatchingHttpInterceptor$PendingRequest;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "dispose", "", "executePendingRequests", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "intercept", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "chain", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "PendingRequest", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nBatchingHttpInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BatchingHttpInterceptor.kt\ncom/apollographql/apollo3/network/http/BatchingHttpInterceptor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 4 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n*L\n1#1,245:1\n766#2:246\n857#2,2:247\n1549#2:269\n1620#2,3:270\n1549#2:273\n1620#2,3:274\n2661#2,7:277\n766#2:284\n857#2,2:285\n1549#2:287\n1620#2,2:288\n1622#2:298\n1855#2,2:299\n1864#2,3:301\n107#3,10:249\n107#3,10:259\n78#4,8:290\n*S KotlinDebug\n*F\n+ 1 BatchingHttpInterceptor.kt\ncom/apollographql/apollo3/network/http/BatchingHttpInterceptor\n*L\n89#1:246\n89#1:247,2\n127#1:269\n127#1:270,3\n129#1:273\n129#1:274,3\n129#1:277,7\n133#1:284\n133#1:285,2\n190#1:287\n190#1:288,2\n190#1:298\n207#1:299,2\n212#1:301,3\n97#1:249,10\n115#1:259,10\n194#1:290,8\n*E\n"})
public final class BatchingHttpInterceptor implements HttpInterceptor {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final long batchIntervalMillis;
    /* access modifiers changed from: private */
    public final long creationTime;
    @NotNull
    private final CloseableSingleThreadDispatcher dispatcher;
    private boolean disposed;
    private final boolean exposeErrorBody;
    @Nullable
    private HttpInterceptorChain interceptorChain;
    private final int maxBatchSize;
    @NotNull
    private final Mutex mutex;
    @NotNull
    private final List<PendingRequest> pendingRequests;
    @NotNull
    private final CoroutineScope scope;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/network/http/BatchingHttpInterceptor$Companion;", "", "()V", "configureApolloCall", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "apolloCall", "Lcom/apollographql/apollo3/ApolloCall;", "canBeBatched", "", "configureApolloClientBuilder", "apolloClientBuilder", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final <D extends Operation.Data> void configureApolloCall(@NotNull ApolloCall<D> apolloCall, boolean z2) {
            Intrinsics.checkNotNullParameter(apolloCall, "apolloCall");
            apolloCall.canBeBatched(Boolean.valueOf(z2));
        }

        @JvmStatic
        public final void configureApolloClientBuilder(@NotNull ApolloClient.Builder builder, boolean z2) {
            Intrinsics.checkNotNullParameter(builder, "apolloClientBuilder");
            builder.canBeBatched(Boolean.valueOf(z2));
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/network/http/BatchingHttpInterceptor$PendingRequest;", "", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;)V", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "getDeferred", "()Lkotlinx/coroutines/CompletableDeferred;", "getRequest", "()Lcom/apollographql/apollo3/api/http/HttpRequest;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class PendingRequest {
        @NotNull
        private final CompletableDeferred<HttpResponse> deferred = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        @NotNull
        private final HttpRequest request;

        public PendingRequest(@NotNull HttpRequest httpRequest) {
            Intrinsics.checkNotNullParameter(httpRequest, "request");
            this.request = httpRequest;
        }

        @NotNull
        public final CompletableDeferred<HttpResponse> getDeferred() {
            return this.deferred;
        }

        @NotNull
        public final HttpRequest getRequest() {
            return this.request;
        }
    }

    @JvmOverloads
    public BatchingHttpInterceptor() {
        this(0, 0, false, 7, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final <D extends Operation.Data> void configureApolloCall(@NotNull ApolloCall<D> apolloCall, boolean z2) {
        Companion.configureApolloCall(apolloCall, z2);
    }

    @JvmStatic
    public static final void configureApolloClientBuilder(@NotNull ApolloClient.Builder builder, boolean z2) {
        Companion.configureApolloClientBuilder(builder, z2);
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: kotlin.jvm.internal.Ref$ObjectRef} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v41, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.util.List} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x027c  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0182 A[Catch:{ Exception -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0223 A[Catch:{ Exception -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object executePendingRequests(kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            boolean r2 = r1 instanceof com.apollographql.apollo3.network.http.BatchingHttpInterceptor$executePendingRequests$1
            if (r2 == 0) goto L_0x0017
            r2 = r1
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$executePendingRequests$1 r2 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor$executePendingRequests$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$executePendingRequests$1 r2 = new com.apollographql.apollo3.network.http.BatchingHttpInterceptor$executePendingRequests$1
            r2.<init>(r0, r1)
        L_0x001c:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            r6 = 200(0xc8, float:2.8E-43)
            r7 = 2
            r8 = 0
            if (r4 == 0) goto L_0x005b
            if (r4 == r5) goto L_0x004d
            if (r4 != r7) goto L_0x0045
            java.lang.Object r0 = r2.L$2
            r3 = r0
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
            java.lang.Object r0 = r2.L$1
            r4 = r0
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r0 = r2.L$0
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor r0 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor) r0
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ Exception -> 0x0042 }
            goto L_0x0176
        L_0x0042:
            r0 = move-exception
            goto L_0x0267
        L_0x0045:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004d:
            java.lang.Object r0 = r2.L$1
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r4 = r2.L$0
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor r4 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r0
            r0 = r4
            goto L_0x006d
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.sync.Mutex r1 = r0.mutex
            r2.L$0 = r0
            r2.L$1 = r1
            r2.label = r5
            java.lang.Object r4 = r1.lock(r8, r2)
            if (r4 != r3) goto L_0x006d
            return r3
        L_0x006d:
            java.util.List<com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest> r4 = r0.pendingRequests     // Catch:{ all -> 0x02df }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ all -> 0x02df }
            java.util.List r4 = kotlin.collections.CollectionsKt.toList(r4)     // Catch:{ all -> 0x02df }
            java.util.List<com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest> r5 = r0.pendingRequests     // Catch:{ all -> 0x02df }
            r5.clear()     // Catch:{ all -> 0x02df }
            r1.unlock(r8)
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L_0x0086
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0086:
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r4)
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r1 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor.PendingRequest) r1
            com.apollographql.apollo3.api.http.HttpRequest r1 = r1.getRequest()
            r5 = r4
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r9 = new java.util.ArrayList
            int r10 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r5, 10)
            r9.<init>(r10)
            java.util.Iterator r10 = r5.iterator()
        L_0x00a0:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00c2
            java.lang.Object r11 = r10.next()
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r11 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor.PendingRequest) r11
            com.apollographql.apollo3.api.http.HttpRequest r11 = r11.getRequest()
            com.apollographql.apollo3.api.http.HttpBody r11 = r11.getBody()
            if (r11 == 0) goto L_0x00ba
            r9.add(r11)
            goto L_0x00a0
        L_0x00ba:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "empty body while batching queries"
            r0.<init>(r1)
            throw r0
        L_0x00c2:
            java.util.ArrayList r10 = new java.util.ArrayList
            int r11 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r5, 10)
            r10.<init>(r11)
            java.util.Iterator r5 = r5.iterator()
        L_0x00cf:
            boolean r11 = r5.hasNext()
            if (r11 == 0) goto L_0x00e7
            java.lang.Object r11 = r5.next()
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r11 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor.PendingRequest) r11
            com.apollographql.apollo3.api.http.HttpRequest r11 = r11.getRequest()
            java.util.List r11 = r11.getHeaders()
            r10.add(r11)
            goto L_0x00cf
        L_0x00e7:
            java.util.Iterator r5 = r10.iterator()
            boolean r10 = r5.hasNext()
            if (r10 == 0) goto L_0x02d7
            java.lang.Object r10 = r5.next()
        L_0x00f5:
            boolean r11 = r5.hasNext()
            if (r11 == 0) goto L_0x0118
            java.lang.Object r11 = r5.next()
            java.util.List r11 = (java.util.List) r11
            java.util.List r10 = (java.util.List) r10
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Set r11 = kotlin.collections.CollectionsKt.toSet(r11)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Set r10 = kotlin.collections.CollectionsKt.intersect(r10, r11)
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.List r10 = kotlin.collections.CollectionsKt.toList(r10)
            goto L_0x00f5
        L_0x0118:
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r10 = r10.iterator()
        L_0x0123:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0140
            java.lang.Object r11 = r10.next()
            r12 = r11
            com.apollographql.apollo3.api.http.HttpHeader r12 = (com.apollographql.apollo3.api.http.HttpHeader) r12
            java.lang.String r12 = r12.getName()
            java.lang.String r13 = "X-APOLLO-CAN-BE-BATCHED"
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r12 != 0) goto L_0x0123
            r5.add(r11)
            goto L_0x0123
        L_0x0140:
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$executePendingRequests$body$1 r10 = new com.apollographql.apollo3.network.http.BatchingHttpInterceptor$executePendingRequests$body$1
            r10.<init>(r9)
            com.apollographql.apollo3.api.http.HttpRequest$Builder r9 = new com.apollographql.apollo3.api.http.HttpRequest$Builder
            com.apollographql.apollo3.api.http.HttpMethod r11 = com.apollographql.apollo3.api.http.HttpMethod.Post
            java.lang.String r1 = r1.getUrl()
            r9.<init>(r11, r1)
            com.apollographql.apollo3.api.http.HttpRequest$Builder r1 = r9.body(r10)
            com.apollographql.apollo3.api.http.HttpRequest$Builder r1 = r1.headers(r5)
            com.apollographql.apollo3.api.http.HttpRequest r1 = r1.build()
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            com.apollographql.apollo3.network.http.HttpInterceptorChain r9 = r0.interceptorChain     // Catch:{ Exception -> 0x0265 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch:{ Exception -> 0x0265 }
            r2.L$0 = r0     // Catch:{ Exception -> 0x0265 }
            r2.L$1 = r4     // Catch:{ Exception -> 0x0265 }
            r2.L$2 = r5     // Catch:{ Exception -> 0x0265 }
            r2.label = r7     // Catch:{ Exception -> 0x0265 }
            java.lang.Object r1 = r9.proceed(r1, r2)     // Catch:{ Exception -> 0x0265 }
            if (r1 != r3) goto L_0x0175
            return r3
        L_0x0175:
            r3 = r5
        L_0x0176:
            com.apollographql.apollo3.api.http.HttpResponse r1 = (com.apollographql.apollo3.api.http.HttpResponse) r1     // Catch:{ Exception -> 0x0042 }
            int r2 = r1.getStatusCode()     // Catch:{ Exception -> 0x0042 }
            if (r6 > r2) goto L_0x0223
            r5 = 300(0x12c, float:4.2E-43)
            if (r2 >= r5) goto L_0x0223
            okio.BufferedSource r0 = r1.getBody()     // Catch:{ Exception -> 0x0042 }
            if (r0 == 0) goto L_0x021b
            com.apollographql.apollo3.api.Adapter<java.lang.Object> r1 = com.apollographql.apollo3.api.Adapters.AnyAdapter     // Catch:{ Exception -> 0x0042 }
            com.apollographql.apollo3.api.json.BufferedSourceJsonReader r2 = new com.apollographql.apollo3.api.json.BufferedSourceJsonReader     // Catch:{ Exception -> 0x0042 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0042 }
            com.apollographql.apollo3.api.CustomScalarAdapters r0 = com.apollographql.apollo3.api.CustomScalarAdapters.Empty     // Catch:{ Exception -> 0x0042 }
            java.lang.Object r0 = r1.fromJson(r2, r0)     // Catch:{ Exception -> 0x0042 }
            boolean r1 = r0 instanceof java.util.List     // Catch:{ Exception -> 0x0042 }
            if (r1 == 0) goto L_0x0213
            r1 = r0
            java.util.List r1 = (java.util.List) r1     // Catch:{ Exception -> 0x0042 }
            int r1 = r1.size()     // Catch:{ Exception -> 0x0042 }
            int r2 = r4.size()     // Catch:{ Exception -> 0x0042 }
            if (r1 != r2) goto L_0x01e5
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ Exception -> 0x0042 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x0042 }
            int r2 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r0, 10)     // Catch:{ Exception -> 0x0042 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0042 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0042 }
        L_0x01b5:
            boolean r2 = r0.hasNext()     // Catch:{ Exception -> 0x0042 }
            if (r2 == 0) goto L_0x01e2
            java.lang.Object r2 = r0.next()     // Catch:{ Exception -> 0x0042 }
            if (r2 == 0) goto L_0x01da
            okio.Buffer r5 = new okio.Buffer     // Catch:{ Exception -> 0x0042 }
            r5.<init>()     // Catch:{ Exception -> 0x0042 }
            com.apollographql.apollo3.api.json.BufferedSinkJsonWriter r9 = new com.apollographql.apollo3.api.json.BufferedSinkJsonWriter     // Catch:{ Exception -> 0x0042 }
            r9.<init>(r5, r8)     // Catch:{ Exception -> 0x0042 }
            com.apollographql.apollo3.api.Adapter<java.lang.Object> r10 = com.apollographql.apollo3.api.Adapters.AnyAdapter     // Catch:{ Exception -> 0x0042 }
            com.apollographql.apollo3.api.CustomScalarAdapters r11 = com.apollographql.apollo3.api.CustomScalarAdapters.Empty     // Catch:{ Exception -> 0x0042 }
            r10.toJson(r9, r11, r2)     // Catch:{ Exception -> 0x0042 }
            okio.ByteString r2 = r5.readByteString()     // Catch:{ Exception -> 0x0042 }
            r1.add(r2)     // Catch:{ Exception -> 0x0042 }
            goto L_0x01b5
        L_0x01da:
            com.apollographql.apollo3.exception.ApolloException r0 = new com.apollographql.apollo3.exception.ApolloException     // Catch:{ Exception -> 0x0042 }
            java.lang.String r1 = "batched query response contains a null item"
            r0.<init>(r1, r8, r7, r8)     // Catch:{ Exception -> 0x0042 }
            throw r0     // Catch:{ Exception -> 0x0042 }
        L_0x01e2:
            r8 = r1
            goto L_0x0278
        L_0x01e5:
            com.apollographql.apollo3.exception.ApolloException r1 = new com.apollographql.apollo3.exception.ApolloException     // Catch:{ Exception -> 0x0042 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0042 }
            r2.<init>()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r5 = "batched query response count ("
            r2.append(r5)     // Catch:{ Exception -> 0x0042 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ Exception -> 0x0042 }
            int r0 = r0.size()     // Catch:{ Exception -> 0x0042 }
            r2.append(r0)     // Catch:{ Exception -> 0x0042 }
            java.lang.String r0 = ") does not match the requested queries ("
            r2.append(r0)     // Catch:{ Exception -> 0x0042 }
            int r0 = r4.size()     // Catch:{ Exception -> 0x0042 }
            r2.append(r0)     // Catch:{ Exception -> 0x0042 }
            r0 = 41
            r2.append(r0)     // Catch:{ Exception -> 0x0042 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0042 }
            r1.<init>(r0, r8, r7, r8)     // Catch:{ Exception -> 0x0042 }
            throw r1     // Catch:{ Exception -> 0x0042 }
        L_0x0213:
            com.apollographql.apollo3.exception.ApolloException r0 = new com.apollographql.apollo3.exception.ApolloException     // Catch:{ Exception -> 0x0042 }
            java.lang.String r1 = "batched query response is not a list when executing batched query"
            r0.<init>(r1, r8, r7, r8)     // Catch:{ Exception -> 0x0042 }
            throw r0     // Catch:{ Exception -> 0x0042 }
        L_0x021b:
            com.apollographql.apollo3.exception.ApolloException r0 = new com.apollographql.apollo3.exception.ApolloException     // Catch:{ Exception -> 0x0042 }
            java.lang.String r1 = "null body when executing batched query"
            r0.<init>(r1, r8, r7, r8)     // Catch:{ Exception -> 0x0042 }
            throw r0     // Catch:{ Exception -> 0x0042 }
        L_0x0223:
            boolean r0 = r0.exposeErrorBody     // Catch:{ Exception -> 0x0042 }
            if (r0 != 0) goto L_0x0232
            okio.BufferedSource r0 = r1.getBody()     // Catch:{ Exception -> 0x0042 }
            if (r0 == 0) goto L_0x0230
            r0.close()     // Catch:{ Exception -> 0x0042 }
        L_0x0230:
            r12 = r8
            goto L_0x0237
        L_0x0232:
            okio.BufferedSource r0 = r1.getBody()     // Catch:{ Exception -> 0x0042 }
            r12 = r0
        L_0x0237:
            com.apollographql.apollo3.exception.ApolloHttpException r0 = new com.apollographql.apollo3.exception.ApolloHttpException     // Catch:{ Exception -> 0x0042 }
            int r10 = r1.getStatusCode()     // Catch:{ Exception -> 0x0042 }
            java.util.List r11 = r1.getHeaders()     // Catch:{ Exception -> 0x0042 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0042 }
            r2.<init>()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r5 = "HTTP error "
            r2.append(r5)     // Catch:{ Exception -> 0x0042 }
            int r1 = r1.getStatusCode()     // Catch:{ Exception -> 0x0042 }
            r2.append(r1)     // Catch:{ Exception -> 0x0042 }
            java.lang.String r1 = " while executing batched query"
            r2.append(r1)     // Catch:{ Exception -> 0x0042 }
            java.lang.String r13 = r2.toString()     // Catch:{ Exception -> 0x0042 }
            r16 = 0
            r14 = 0
            r15 = 16
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x0042 }
            throw r0     // Catch:{ Exception -> 0x0042 }
        L_0x0265:
            r0 = move-exception
            r3 = r5
        L_0x0267:
            boolean r1 = r0 instanceof com.apollographql.apollo3.exception.ApolloException
            if (r1 == 0) goto L_0x026e
            com.apollographql.apollo3.exception.ApolloException r0 = (com.apollographql.apollo3.exception.ApolloException) r0
            goto L_0x0276
        L_0x026e:
            com.apollographql.apollo3.exception.ApolloException r1 = new com.apollographql.apollo3.exception.ApolloException
            java.lang.String r2 = "batched query failed with exception"
            r1.<init>(r2, r0)
            r0 = r1
        L_0x0276:
            r3.element = r0
        L_0x0278:
            T r0 = r3.element
            if (r0 == 0) goto L_0x029d
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r0 = r4.iterator()
        L_0x0282:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x029a
            java.lang.Object r1 = r0.next()
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r1 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor.PendingRequest) r1
            kotlinx.coroutines.CompletableDeferred r1 = r1.getDeferred()
            T r2 = r3.element
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r1.completeExceptionally(r2)
            goto L_0x0282
        L_0x029a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x029d:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            java.util.Iterator r0 = r8.iterator()
            r1 = 0
        L_0x02a5:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x02d4
            java.lang.Object r2 = r0.next()
            int r3 = r1 + 1
            if (r1 >= 0) goto L_0x02b6
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x02b6:
            okio.ByteString r2 = (okio.ByteString) r2
            java.lang.Object r1 = r4.get(r1)
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r1 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor.PendingRequest) r1
            kotlinx.coroutines.CompletableDeferred r1 = r1.getDeferred()
            com.apollographql.apollo3.api.http.HttpResponse$Builder r5 = new com.apollographql.apollo3.api.http.HttpResponse$Builder
            r5.<init>(r6)
            com.apollographql.apollo3.api.http.HttpResponse$Builder r2 = r5.body((okio.ByteString) r2)
            com.apollographql.apollo3.api.http.HttpResponse r2 = r2.build()
            r1.complete(r2)
            r1 = r3
            goto L_0x02a5
        L_0x02d4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02d7:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Empty collection can't be reduced."
            r0.<init>(r1)
            throw r0
        L_0x02df:
            r0 = move-exception
            r1.unlock(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.BatchingHttpInterceptor.executePendingRequests(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void dispose() {
        if (!this.disposed) {
            this.interceptorChain = null;
            CoroutineScopeKt.cancel$default(this.scope, (CancellationException) null, 1, (Object) null);
            this.dispatcher.close();
            this.disposed = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0125 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0126 A[PHI: r3 
      PHI: (r3v2 java.lang.Object) = (r3v3 java.lang.Object), (r3v1 java.lang.Object) binds: [B:50:0x0123, B:12:0x0037] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object intercept(@org.jetbrains.annotations.NotNull com.apollographql.apollo3.api.http.HttpRequest r20, @org.jetbrains.annotations.NotNull com.apollographql.apollo3.network.http.HttpInterceptorChain r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.apollographql.apollo3.api.http.HttpResponse> r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            boolean r4 = r3 instanceof com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$1
            if (r4 == 0) goto L_0x001b
            r4 = r3
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$1 r4 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = r5 & r6
            if (r7 == 0) goto L_0x001b
            int r5 = r5 - r6
            r4.label = r5
            goto L_0x0020
        L_0x001b:
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$1 r4 = new com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$1
            r4.<init>(r0, r3)
        L_0x0020:
            java.lang.Object r3 = r4.result
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r4.label
            r7 = 4
            r8 = 2
            r9 = 3
            r10 = 1
            r11 = 0
            if (r6 == 0) goto L_0x0067
            if (r6 == r10) goto L_0x0063
            if (r6 == r8) goto L_0x004d
            if (r6 == r9) goto L_0x0044
            if (r6 != r7) goto L_0x003c
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x0126
        L_0x003c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0044:
            java.lang.Object r0 = r4.L$0
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r0 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor.PendingRequest) r0
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x0101
        L_0x004d:
            java.lang.Object r0 = r4.L$2
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r1 = r4.L$1
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r1 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor.PendingRequest) r1
            java.lang.Object r2 = r4.L$0
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor r2 = (com.apollographql.apollo3.network.http.BatchingHttpInterceptor) r2
            kotlin.ResultKt.throwOnFailure(r3)
            r18 = r2
            r2 = r0
            r0 = r18
            goto L_0x00db
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x00bd
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r3)
            java.util.List r3 = r20.getHeaders()
            java.lang.String r6 = "X-APOLLO-CAN-BE-BATCHED"
            java.lang.String r3 = com.apollographql.apollo3.api.http.HttpHeaders.valueOf(r3, r6)
            if (r3 == 0) goto L_0x007b
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            goto L_0x007c
        L_0x007b:
            r3 = r10
        L_0x007c:
            if (r3 != 0) goto L_0x00be
            com.apollographql.apollo3.api.http.HttpRequest$Builder r0 = com.apollographql.apollo3.api.http.HttpRequest.newBuilder$default(r1, r11, r11, r9, r11)
            java.util.List r1 = r20.getHeaders()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0091:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x00ac
            java.lang.Object r7 = r1.next()
            r8 = r7
            com.apollographql.apollo3.api.http.HttpHeader r8 = (com.apollographql.apollo3.api.http.HttpHeader) r8
            java.lang.String r8 = r8.getName()
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r6)
            if (r8 != 0) goto L_0x0091
            r3.add(r7)
            goto L_0x0091
        L_0x00ac:
            com.apollographql.apollo3.api.http.HttpRequest$Builder r0 = r0.addHeaders(r3)
            com.apollographql.apollo3.api.http.HttpRequest r0 = r0.build()
            r4.label = r10
            java.lang.Object r3 = r2.proceed(r0, r4)
            if (r3 != r5) goto L_0x00bd
            return r5
        L_0x00bd:
            return r3
        L_0x00be:
            r0.interceptorChain = r2
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest r2 = new com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest
            r2.<init>(r1)
            kotlinx.coroutines.sync.Mutex r1 = r0.mutex
            r4.L$0 = r0
            r4.L$1 = r2
            r4.L$2 = r1
            r4.label = r8
            java.lang.Object r3 = r1.lock(r11, r4)
            if (r3 != r5) goto L_0x00d6
            return r5
        L_0x00d6:
            r18 = r2
            r2 = r1
            r1 = r18
        L_0x00db:
            java.util.List<com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest> r3 = r0.pendingRequests     // Catch:{ all -> 0x0127 }
            r3.add(r1)     // Catch:{ all -> 0x0127 }
            java.util.List<com.apollographql.apollo3.network.http.BatchingHttpInterceptor$PendingRequest> r3 = r0.pendingRequests     // Catch:{ all -> 0x0127 }
            int r3 = r3.size()     // Catch:{ all -> 0x0127 }
            int r6 = r0.maxBatchSize     // Catch:{ all -> 0x0127 }
            if (r3 < r6) goto L_0x00eb
            goto L_0x00ec
        L_0x00eb:
            r10 = 0
        L_0x00ec:
            r2.unlock(r11)
            if (r10 == 0) goto L_0x0103
            r4.L$0 = r1
            r4.L$1 = r11
            r4.L$2 = r11
            r4.label = r9
            java.lang.Object r0 = r0.executePendingRequests(r4)
            if (r0 != r5) goto L_0x0100
            return r5
        L_0x0100:
            r0 = r1
        L_0x0101:
            r1 = r0
            goto L_0x0113
        L_0x0103:
            kotlinx.coroutines.CoroutineScope r12 = r0.scope
            com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$3 r15 = new com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$3
            r15.<init>(r0, r11)
            r16 = 3
            r17 = 0
            r13 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)
        L_0x0113:
            kotlinx.coroutines.CompletableDeferred r0 = r1.getDeferred()
            r4.L$0 = r11
            r4.L$1 = r11
            r4.L$2 = r11
            r4.label = r7
            java.lang.Object r3 = r0.await(r4)
            if (r3 != r5) goto L_0x0126
            return r5
        L_0x0126:
            return r3
        L_0x0127:
            r0 = move-exception
            r2.unlock(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.BatchingHttpInterceptor.intercept(com.apollographql.apollo3.api.http.HttpRequest, com.apollographql.apollo3.network.http.HttpInterceptorChain, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @JvmOverloads
    public BatchingHttpInterceptor(long j2) {
        this(j2, 0, false, 6, (DefaultConstructorMarker) null);
    }

    @JvmOverloads
    public BatchingHttpInterceptor(long j2, int i3) {
        this(j2, i3, false, 4, (DefaultConstructorMarker) null);
    }

    @JvmOverloads
    public BatchingHttpInterceptor(long j2, int i3, boolean z2) {
        this.batchIntervalMillis = j2;
        this.maxBatchSize = i3;
        this.exposeErrorBody = z2;
        this.creationTime = UtilsKt.currentTimeMillis();
        CloseableSingleThreadDispatcher closeableSingleThreadDispatcher = new CloseableSingleThreadDispatcher();
        this.dispatcher = closeableSingleThreadDispatcher;
        this.scope = CoroutineScopeKt.CoroutineScope(closeableSingleThreadDispatcher.getCoroutineDispatcher());
        this.mutex = MutexKt.Mutex$default(false, 1, (Object) null);
        this.pendingRequests = new ArrayList();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BatchingHttpInterceptor(long j2, int i3, boolean z2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 10 : j2, (i4 & 2) != 0 ? 10 : i3, (i4 & 4) != 0 ? false : z2);
    }
}
