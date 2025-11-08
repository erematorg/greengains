package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.network.http.HttpInterceptor;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0011B\u001d\b\u0017\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006B#\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH@¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/network/http/LoggingInterceptor;", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "log", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "level", "Lcom/apollographql/apollo3/network/http/LoggingInterceptor$Level;", "(Lcom/apollographql/apollo3/network/http/LoggingInterceptor$Level;Lkotlin/jvm/functions/Function1;)V", "intercept", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "chain", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Level", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nLoggingInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoggingInterceptor.kt\ncom/apollographql/apollo3/network/http/LoggingInterceptor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,135:1\n1855#2,2:136\n1855#2,2:138\n*S KotlinDebug\n*F\n+ 1 LoggingInterceptor.kt\ncom/apollographql/apollo3/network/http/LoggingInterceptor\n*L\n90#1:136,2\n115#1:138,2\n*E\n"})
public final class LoggingInterceptor implements HttpInterceptor {
    @NotNull
    private final Level level;
    @NotNull
    private final Function1<String, Unit> log;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/network/http/LoggingInterceptor$Level;", "", "(Ljava/lang/String;I)V", "NONE", "BASIC", "HEADERS", "BODY", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    @JvmOverloads
    public LoggingInterceptor() {
        this((Function1) null, 1, (DefaultConstructorMarker) null);
    }

    public void dispose() {
        HttpInterceptor.DefaultImpls.dispose(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object intercept(@org.jetbrains.annotations.NotNull com.apollographql.apollo3.api.http.HttpRequest r12, @org.jetbrains.annotations.NotNull com.apollographql.apollo3.network.http.HttpInterceptorChain r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.apollographql.apollo3.api.http.HttpResponse> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.apollographql.apollo3.network.http.LoggingInterceptor$intercept$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.apollographql.apollo3.network.http.LoggingInterceptor$intercept$1 r0 = (com.apollographql.apollo3.network.http.LoggingInterceptor$intercept$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.apollographql.apollo3.network.http.LoggingInterceptor$intercept$1 r0 = new com.apollographql.apollo3.network.http.LoggingInterceptor$intercept$1
            r0.<init>(r11, r14)
        L_0x0018:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "[end of headers]"
            java.lang.String r4 = ": "
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 == r6) goto L_0x0043
            if (r2 != r5) goto L_0x003b
            int r11 = r0.I$1
            int r12 = r0.I$0
            java.lang.Object r13 = r0.L$0
            com.apollographql.apollo3.network.http.LoggingInterceptor r13 = (com.apollographql.apollo3.network.http.LoggingInterceptor) r13
            kotlin.ResultKt.throwOnFailure(r14)
            r6 = r11
            r11 = r13
            goto L_0x011b
        L_0x003b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0059
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r14)
            com.apollographql.apollo3.network.http.LoggingInterceptor$Level r14 = r11.level
            com.apollographql.apollo3.network.http.LoggingInterceptor$Level r2 = com.apollographql.apollo3.network.http.LoggingInterceptor.Level.NONE
            if (r14 != r2) goto L_0x005a
            r0.label = r6
            java.lang.Object r14 = r13.proceed(r12, r0)
            if (r14 != r1) goto L_0x0059
            return r1
        L_0x0059:
            return r14
        L_0x005a:
            com.apollographql.apollo3.network.http.LoggingInterceptor$Level r2 = com.apollographql.apollo3.network.http.LoggingInterceptor.Level.HEADERS
            r7 = 0
            if (r14 == r2) goto L_0x0066
            com.apollographql.apollo3.network.http.LoggingInterceptor$Level r2 = com.apollographql.apollo3.network.http.LoggingInterceptor.Level.BODY
            if (r14 != r2) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            r2 = r7
            goto L_0x0067
        L_0x0066:
            r2 = r6
        L_0x0067:
            com.apollographql.apollo3.network.http.LoggingInterceptor$Level r8 = com.apollographql.apollo3.network.http.LoggingInterceptor.Level.BODY
            if (r14 != r8) goto L_0x006c
            goto L_0x006d
        L_0x006c:
            r6 = r7
        L_0x006d:
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r14 = r11.log
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            com.apollographql.apollo3.api.http.HttpMethod r8 = r12.getMethod()
            java.lang.String r8 = r8.name()
            r7.append(r8)
            r8 = 32
            r7.append(r8)
            java.lang.String r8 = r12.getUrl()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r14.invoke(r7)
            if (r2 == 0) goto L_0x00cf
            java.util.List r14 = r12.getHeaders()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.Iterator r14 = r14.iterator()
        L_0x009e:
            boolean r7 = r14.hasNext()
            if (r7 == 0) goto L_0x00ca
            java.lang.Object r7 = r14.next()
            com.apollographql.apollo3.api.http.HttpHeader r7 = (com.apollographql.apollo3.api.http.HttpHeader) r7
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r8 = r11.log
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = r7.getName()
            r9.append(r10)
            r9.append(r4)
            java.lang.String r7 = r7.getValue()
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r8.invoke(r7)
            goto L_0x009e
        L_0x00ca:
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r14 = r11.log
            r14.invoke(r3)
        L_0x00cf:
            com.apollographql.apollo3.api.http.HttpBody r14 = r12.getBody()
            if (r6 == 0) goto L_0x0104
            if (r14 != 0) goto L_0x00d8
            goto L_0x0104
        L_0x00d8:
            okio.Buffer r7 = new okio.Buffer
            r7.<init>()
            r14.writeTo(r7)
            okio.ByteString r7 = r7.readByteString()
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r8 = r11.log
            java.lang.String r9 = r7.utf8()
            r8.invoke(r9)
            r8 = 3
            r9 = 0
            com.apollographql.apollo3.api.http.HttpRequest$Builder r12 = com.apollographql.apollo3.api.http.HttpRequest.newBuilder$default(r12, r9, r9, r8, r9)
            com.apollographql.apollo3.api.http.ByteStringHttpBody r8 = new com.apollographql.apollo3.api.http.ByteStringHttpBody
            java.lang.String r14 = r14.getContentType()
            r8.<init>((java.lang.String) r14, (okio.ByteString) r7)
            com.apollographql.apollo3.api.http.HttpRequest$Builder r12 = r12.body(r8)
            com.apollographql.apollo3.api.http.HttpRequest r12 = r12.build()
        L_0x0104:
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r14 = r11.log
            java.lang.String r7 = ""
            r14.invoke(r7)
            r0.L$0 = r11
            r0.I$0 = r2
            r0.I$1 = r6
            r0.label = r5
            java.lang.Object r14 = r13.proceed(r12, r0)
            if (r14 != r1) goto L_0x011a
            return r1
        L_0x011a:
            r12 = r2
        L_0x011b:
            com.apollographql.apollo3.api.http.HttpResponse r14 = (com.apollographql.apollo3.api.http.HttpResponse) r14
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r13 = r11.log
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "HTTP: "
            r0.<init>(r1)
            int r1 = r14.getStatusCode()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r13.invoke(r0)
            if (r12 == 0) goto L_0x0171
            java.util.List r12 = r14.getHeaders()
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.Iterator r12 = r12.iterator()
        L_0x0140:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x016c
            java.lang.Object r13 = r12.next()
            com.apollographql.apollo3.api.http.HttpHeader r13 = (com.apollographql.apollo3.api.http.HttpHeader) r13
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r0 = r11.log
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r13.getName()
            r1.append(r2)
            r1.append(r4)
            java.lang.String r13 = r13.getValue()
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.invoke(r13)
            goto L_0x0140
        L_0x016c:
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r12 = r11.log
            r12.invoke(r3)
        L_0x0171:
            okio.BufferedSource r12 = r14.getBody()
            if (r6 == 0) goto L_0x01a0
            if (r12 != 0) goto L_0x017a
            goto L_0x01a0
        L_0x017a:
            okio.ByteString r12 = r12.readByteString()
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r11 = r11.log
            java.lang.String r13 = r12.utf8()
            r11.invoke(r13)
            com.apollographql.apollo3.api.http.HttpResponse$Builder r11 = new com.apollographql.apollo3.api.http.HttpResponse$Builder
            int r13 = r14.getStatusCode()
            r11.<init>(r13)
            com.apollographql.apollo3.api.http.HttpResponse$Builder r11 = r11.body((okio.ByteString) r12)
            java.util.List r12 = r14.getHeaders()
            com.apollographql.apollo3.api.http.HttpResponse$Builder r11 = r11.addHeaders(r12)
            com.apollographql.apollo3.api.http.HttpResponse r14 = r11.build()
        L_0x01a0:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.LoggingInterceptor.intercept(com.apollographql.apollo3.api.http.HttpRequest, com.apollographql.apollo3.network.http.HttpInterceptorChain, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public LoggingInterceptor(@NotNull Level level2, @NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(level2, FirebaseAnalytics.Param.LEVEL);
        Intrinsics.checkNotNullParameter(function1, "log");
        this.level = level2;
        this.log = function1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LoggingInterceptor(Level level2, Function1 function1, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(level2, (i3 & 2) != 0 ? AnonymousClass1.INSTANCE : function1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public LoggingInterceptor(@NotNull Function1<? super String, Unit> function1) {
        this(Level.BODY, function1);
        Intrinsics.checkNotNullParameter(function1, "log");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LoggingInterceptor(Function1 function1, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? AnonymousClass2.INSTANCE : function1);
    }
}
