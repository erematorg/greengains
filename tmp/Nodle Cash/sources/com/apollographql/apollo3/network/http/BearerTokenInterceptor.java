package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.network.http.HttpInterceptor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH@¢\u0006\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/network/http/BearerTokenInterceptor;", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "tokenProvider", "Lcom/apollographql/apollo3/network/http/TokenProvider;", "(Lcom/apollographql/apollo3/network/http/TokenProvider;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "intercept", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "chain", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nBearerTokenInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BearerTokenInterceptor.kt\ncom/apollographql/apollo3/network/http/BearerTokenInterceptor\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,31:1\n107#2,10:32\n107#2,10:42\n*S KotlinDebug\n*F\n+ 1 BearerTokenInterceptor.kt\ncom/apollographql/apollo3/network/http/BearerTokenInterceptor\n*L\n19#1:32,10\n24#1:42,10\n*E\n"})
@Deprecated(message = "BearerTokenInterceptor was provided as an example but is too simple for most use cases.Define your own interceptor or take a look at https://www.apollographql.com/docs/kotlin/advanced/interceptors-http for more details.")
public final class BearerTokenInterceptor implements HttpInterceptor {
    @NotNull
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);
    @NotNull
    private final TokenProvider tokenProvider;

    public BearerTokenInterceptor(@NotNull TokenProvider tokenProvider2) {
        Intrinsics.checkNotNullParameter(tokenProvider2, "tokenProvider");
        this.tokenProvider = tokenProvider2;
    }

    public void dispose() {
        HttpInterceptor.DefaultImpls.dispose(this);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r8 = r12.tokenProvider;
        r0.L$0 = r12;
        r0.L$1 = r13;
        r0.L$2 = r14;
        r0.L$3 = r7;
        r0.L$4 = r2;
        r0.L$5 = r15;
        r0.label = 2;
        r8 = r8.currentToken(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00fa, code lost:
        if (r8 != r1) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00fc, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00fd, code lost:
        r10 = r8;
        r8 = r12;
        r12 = r15;
        r15 = r10;
        r11 = r7;
        r7 = r13;
        r13 = r2;
        r2 = r14;
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r15 = (java.lang.String) r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0108, code lost:
        r13.unlock((java.lang.Object) null);
        r12.element = r15;
        r12 = com.apollographql.apollo3.api.http.HttpRequest.newBuilder$default(r7, (com.apollographql.apollo3.api.http.HttpMethod) null, (java.lang.String) null, 3, (java.lang.Object) null);
        r12 = r12.addHeader(com.google.common.net.HttpHeaders.AUTHORIZATION, "Bearer " + ((java.lang.String) r14.element)).build();
        r0.L$0 = r8;
        r0.L$1 = r7;
        r0.L$2 = r2;
        r0.L$3 = r14;
        r0.L$4 = null;
        r0.L$5 = null;
        r0.label = 3;
        r15 = r2.proceed(r12, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x013b, code lost:
        if (r15 != r1) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x013d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x013e, code lost:
        r12 = r14;
        r13 = r2;
        r14 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0141, code lost:
        r15 = (com.apollographql.apollo3.api.http.HttpResponse) r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0149, code lost:
        if (r15.getStatusCode() != 401) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x014b, code lost:
        r15 = r8.mutex;
        r0.L$0 = r8;
        r0.L$1 = r14;
        r0.L$2 = r13;
        r0.L$3 = r12;
        r0.L$4 = r15;
        r0.L$5 = r12;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0160, code lost:
        if (r15.lock((java.lang.Object) null, r0) != r1) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0162, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0163, code lost:
        r2 = r13;
        r7 = r14;
        r13 = r15;
        r14 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r0.L$0 = r7;
        r0.L$1 = r2;
        r0.L$2 = r14;
        r0.L$3 = r13;
        r0.L$4 = r12;
        r0.L$5 = null;
        r0.label = 5;
        r15 = r8.tokenProvider.refreshToken((java.lang.String) r14.element, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0180, code lost:
        if (r15 != r1) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0182, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0183, code lost:
        r15 = (java.lang.String) r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0185, code lost:
        r13.unlock((java.lang.Object) null);
        r12.element = r15;
        r12 = com.apollographql.apollo3.api.http.HttpRequest.newBuilder$default(r7, (com.apollographql.apollo3.api.http.HttpMethod) null, (java.lang.String) null, 3, (java.lang.Object) null);
        r12 = r12.addHeader(com.google.common.net.HttpHeaders.AUTHORIZATION, "Bearer " + ((java.lang.String) r14.element)).build();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.L$4 = null;
        r0.label = 6;
        r15 = r2.proceed(r12, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01b7, code lost:
        if (r15 != r1) goto L_0x01ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01b9, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01ba, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01bf, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01c0, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01c1, code lost:
        r13 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01c2, code lost:
        r13.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01c5, code lost:
        throw r12;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object intercept(@org.jetbrains.annotations.NotNull com.apollographql.apollo3.api.http.HttpRequest r13, @org.jetbrains.annotations.NotNull com.apollographql.apollo3.network.http.HttpInterceptorChain r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.apollographql.apollo3.api.http.HttpResponse> r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof com.apollographql.apollo3.network.http.BearerTokenInterceptor$intercept$1
            if (r0 == 0) goto L_0x0013
            r0 = r15
            com.apollographql.apollo3.network.http.BearerTokenInterceptor$intercept$1 r0 = (com.apollographql.apollo3.network.http.BearerTokenInterceptor$intercept$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.apollographql.apollo3.network.http.BearerTokenInterceptor$intercept$1 r0 = new com.apollographql.apollo3.network.http.BearerTokenInterceptor$intercept$1
            r0.<init>(r12, r15)
        L_0x0018:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "Bearer "
            java.lang.String r4 = "Authorization"
            r5 = 3
            r6 = 0
            switch(r2) {
                case 0: goto L_0x00c8;
                case 1: goto L_0x00a5;
                case 2: goto L_0x0085;
                case 3: goto L_0x006f;
                case 4: goto L_0x0052;
                case 5: goto L_0x0036;
                case 6: goto L_0x0031;
                default: goto L_0x0029;
            }
        L_0x0029:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x01ba
        L_0x0036:
            java.lang.Object r12 = r0.L$4
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r0.L$3
            kotlinx.coroutines.sync.Mutex r13 = (kotlinx.coroutines.sync.Mutex) r13
            java.lang.Object r14 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r14 = (kotlin.jvm.internal.Ref.ObjectRef) r14
            java.lang.Object r2 = r0.L$1
            com.apollographql.apollo3.network.http.HttpInterceptorChain r2 = (com.apollographql.apollo3.network.http.HttpInterceptorChain) r2
            java.lang.Object r7 = r0.L$0
            com.apollographql.apollo3.api.http.HttpRequest r7 = (com.apollographql.apollo3.api.http.HttpRequest) r7
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x004f }
            goto L_0x0183
        L_0x004f:
            r12 = move-exception
            goto L_0x01bb
        L_0x0052:
            java.lang.Object r12 = r0.L$5
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.sync.Mutex r13 = (kotlinx.coroutines.sync.Mutex) r13
            java.lang.Object r14 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r14 = (kotlin.jvm.internal.Ref.ObjectRef) r14
            java.lang.Object r2 = r0.L$2
            com.apollographql.apollo3.network.http.HttpInterceptorChain r2 = (com.apollographql.apollo3.network.http.HttpInterceptorChain) r2
            java.lang.Object r7 = r0.L$1
            com.apollographql.apollo3.api.http.HttpRequest r7 = (com.apollographql.apollo3.api.http.HttpRequest) r7
            java.lang.Object r8 = r0.L$0
            com.apollographql.apollo3.network.http.BearerTokenInterceptor r8 = (com.apollographql.apollo3.network.http.BearerTokenInterceptor) r8
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0167
        L_0x006f:
            java.lang.Object r12 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r0.L$2
            com.apollographql.apollo3.network.http.HttpInterceptorChain r13 = (com.apollographql.apollo3.network.http.HttpInterceptorChain) r13
            java.lang.Object r14 = r0.L$1
            com.apollographql.apollo3.api.http.HttpRequest r14 = (com.apollographql.apollo3.api.http.HttpRequest) r14
            java.lang.Object r2 = r0.L$0
            com.apollographql.apollo3.network.http.BearerTokenInterceptor r2 = (com.apollographql.apollo3.network.http.BearerTokenInterceptor) r2
            kotlin.ResultKt.throwOnFailure(r15)
            r8 = r2
            goto L_0x0141
        L_0x0085:
            java.lang.Object r12 = r0.L$5
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.sync.Mutex r13 = (kotlinx.coroutines.sync.Mutex) r13
            java.lang.Object r14 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r14 = (kotlin.jvm.internal.Ref.ObjectRef) r14
            java.lang.Object r2 = r0.L$2
            com.apollographql.apollo3.network.http.HttpInterceptorChain r2 = (com.apollographql.apollo3.network.http.HttpInterceptorChain) r2
            java.lang.Object r7 = r0.L$1
            com.apollographql.apollo3.api.http.HttpRequest r7 = (com.apollographql.apollo3.api.http.HttpRequest) r7
            java.lang.Object r8 = r0.L$0
            com.apollographql.apollo3.network.http.BearerTokenInterceptor r8 = (com.apollographql.apollo3.network.http.BearerTokenInterceptor) r8
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x00a2 }
            goto L_0x0106
        L_0x00a2:
            r12 = move-exception
            goto L_0x01c2
        L_0x00a5:
            java.lang.Object r12 = r0.L$5
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.sync.Mutex r13 = (kotlinx.coroutines.sync.Mutex) r13
            java.lang.Object r14 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r14 = (kotlin.jvm.internal.Ref.ObjectRef) r14
            java.lang.Object r2 = r0.L$2
            com.apollographql.apollo3.network.http.HttpInterceptorChain r2 = (com.apollographql.apollo3.network.http.HttpInterceptorChain) r2
            java.lang.Object r7 = r0.L$1
            com.apollographql.apollo3.api.http.HttpRequest r7 = (com.apollographql.apollo3.api.http.HttpRequest) r7
            java.lang.Object r8 = r0.L$0
            com.apollographql.apollo3.network.http.BearerTokenInterceptor r8 = (com.apollographql.apollo3.network.http.BearerTokenInterceptor) r8
            kotlin.ResultKt.throwOnFailure(r15)
            r15 = r12
            r12 = r8
            r10 = r2
            r2 = r13
            r13 = r7
            r7 = r14
            r14 = r10
            goto L_0x00e5
        L_0x00c8:
            kotlin.jvm.internal.Ref$ObjectRef r15 = androidx.compose.animation.core.a.w(r15)
            kotlinx.coroutines.sync.Mutex r2 = r12.mutex
            r0.L$0 = r12
            r0.L$1 = r13
            r0.L$2 = r14
            r0.L$3 = r15
            r0.L$4 = r2
            r0.L$5 = r15
            r7 = 1
            r0.label = r7
            java.lang.Object r7 = r2.lock(r6, r0)
            if (r7 != r1) goto L_0x00e4
            return r1
        L_0x00e4:
            r7 = r15
        L_0x00e5:
            com.apollographql.apollo3.network.http.TokenProvider r8 = r12.tokenProvider     // Catch:{ all -> 0x01c0 }
            r0.L$0 = r12     // Catch:{ all -> 0x01c0 }
            r0.L$1 = r13     // Catch:{ all -> 0x01c0 }
            r0.L$2 = r14     // Catch:{ all -> 0x01c0 }
            r0.L$3 = r7     // Catch:{ all -> 0x01c0 }
            r0.L$4 = r2     // Catch:{ all -> 0x01c0 }
            r0.L$5 = r15     // Catch:{ all -> 0x01c0 }
            r9 = 2
            r0.label = r9     // Catch:{ all -> 0x01c0 }
            java.lang.Object r8 = r8.currentToken(r0)     // Catch:{ all -> 0x01c0 }
            if (r8 != r1) goto L_0x00fd
            return r1
        L_0x00fd:
            r10 = r8
            r8 = r12
            r12 = r15
            r15 = r10
            r11 = r7
            r7 = r13
            r13 = r2
            r2 = r14
            r14 = r11
        L_0x0106:
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x00a2 }
            r13.unlock(r6)
            r12.element = r15
            com.apollographql.apollo3.api.http.HttpRequest$Builder r12 = com.apollographql.apollo3.api.http.HttpRequest.newBuilder$default(r7, r6, r6, r5, r6)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r3)
            T r15 = r14.element
            java.lang.String r15 = (java.lang.String) r15
            r13.append(r15)
            java.lang.String r13 = r13.toString()
            com.apollographql.apollo3.api.http.HttpRequest$Builder r12 = r12.addHeader(r4, r13)
            com.apollographql.apollo3.api.http.HttpRequest r12 = r12.build()
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r14
            r0.L$4 = r6
            r0.L$5 = r6
            r0.label = r5
            java.lang.Object r15 = r2.proceed(r12, r0)
            if (r15 != r1) goto L_0x013e
            return r1
        L_0x013e:
            r12 = r14
            r13 = r2
            r14 = r7
        L_0x0141:
            com.apollographql.apollo3.api.http.HttpResponse r15 = (com.apollographql.apollo3.api.http.HttpResponse) r15
            int r2 = r15.getStatusCode()
            r7 = 401(0x191, float:5.62E-43)
            if (r2 != r7) goto L_0x01bf
            kotlinx.coroutines.sync.Mutex r15 = r8.mutex
            r0.L$0 = r8
            r0.L$1 = r14
            r0.L$2 = r13
            r0.L$3 = r12
            r0.L$4 = r15
            r0.L$5 = r12
            r2 = 4
            r0.label = r2
            java.lang.Object r2 = r15.lock(r6, r0)
            if (r2 != r1) goto L_0x0163
            return r1
        L_0x0163:
            r2 = r13
            r7 = r14
            r13 = r15
            r14 = r12
        L_0x0167:
            com.apollographql.apollo3.network.http.TokenProvider r15 = r8.tokenProvider     // Catch:{ all -> 0x004f }
            T r8 = r14.element     // Catch:{ all -> 0x004f }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x004f }
            r0.L$0 = r7     // Catch:{ all -> 0x004f }
            r0.L$1 = r2     // Catch:{ all -> 0x004f }
            r0.L$2 = r14     // Catch:{ all -> 0x004f }
            r0.L$3 = r13     // Catch:{ all -> 0x004f }
            r0.L$4 = r12     // Catch:{ all -> 0x004f }
            r0.L$5 = r6     // Catch:{ all -> 0x004f }
            r9 = 5
            r0.label = r9     // Catch:{ all -> 0x004f }
            java.lang.Object r15 = r15.refreshToken(r8, r0)     // Catch:{ all -> 0x004f }
            if (r15 != r1) goto L_0x0183
            return r1
        L_0x0183:
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x004f }
            r13.unlock(r6)
            r12.element = r15
            com.apollographql.apollo3.api.http.HttpRequest$Builder r12 = com.apollographql.apollo3.api.http.HttpRequest.newBuilder$default(r7, r6, r6, r5, r6)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r3)
            T r14 = r14.element
            java.lang.String r14 = (java.lang.String) r14
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            com.apollographql.apollo3.api.http.HttpRequest$Builder r12 = r12.addHeader(r4, r13)
            com.apollographql.apollo3.api.http.HttpRequest r12 = r12.build()
            r0.L$0 = r6
            r0.L$1 = r6
            r0.L$2 = r6
            r0.L$3 = r6
            r0.L$4 = r6
            r13 = 6
            r0.label = r13
            java.lang.Object r15 = r2.proceed(r12, r0)
            if (r15 != r1) goto L_0x01ba
            return r1
        L_0x01ba:
            return r15
        L_0x01bb:
            r13.unlock(r6)
            throw r12
        L_0x01bf:
            return r15
        L_0x01c0:
            r12 = move-exception
            r13 = r2
        L_0x01c2:
            r13.unlock(r6)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.BearerTokenInterceptor.intercept(com.apollographql.apollo3.api.http.HttpRequest, com.apollographql.apollo3.network.http.HttpInterceptorChain, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
