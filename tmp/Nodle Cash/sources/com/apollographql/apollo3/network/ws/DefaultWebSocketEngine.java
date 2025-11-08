package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.http.HttpHeader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH@¢\u0006\u0002\u0010\rJ*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000eH@¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/network/ws/DefaultWebSocketEngine;", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "()V", "webSocketFactory", "Lokhttp3/WebSocket$Factory;", "(Lokhttp3/WebSocket$Factory;)V", "open", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "url", "", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nOkHttpWebSocketEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpWebSocketEngine.kt\ncom/apollographql/apollo3/network/ws/DefaultWebSocketEngine\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,123:1\n125#2:124\n152#2,3:125\n*S KotlinDebug\n*F\n+ 1 OkHttpWebSocketEngine.kt\ncom/apollographql/apollo3/network/ws/DefaultWebSocketEngine\n*L\n121#1:124\n121#1:125,3\n*E\n"})
public final class DefaultWebSocketEngine implements WebSocketEngine {
    @NotNull
    private final WebSocket.Factory webSocketFactory;

    public DefaultWebSocketEngine(@NotNull WebSocket.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "webSocketFactory");
        this.webSocketFactory = factory;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object open(@org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull java.util.List<com.apollographql.apollo3.api.http.HttpHeader> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.apollographql.apollo3.network.ws.WebSocketConnection> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1 r0 = (com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1 r0 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1
            r0.<init>(r6, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            okhttp3.WebSocket r6 = (okhttp3.WebSocket) r6
            java.lang.Object r7 = r0.L$0
            com.apollographql.apollo3.internal.ChannelWrapper r7 = (com.apollographql.apollo3.internal.ChannelWrapper) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x007c
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r9)
            com.apollographql.apollo3.internal.ChannelWrapper r9 = new com.apollographql.apollo3.internal.ChannelWrapper
            r2 = 2147483647(0x7fffffff, float:NaN)
            r4 = 6
            r5 = 0
            kotlinx.coroutines.channels.Channel r2 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r2, r5, r5, r4, r5)
            r9.<init>(r2)
            kotlinx.coroutines.CompletableDeferred r2 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r5, r3, r5)
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder
            r4.<init>()
            okhttp3.Request$Builder r7 = r4.url((java.lang.String) r7)
            okhttp3.Headers r8 = com.apollographql.apollo3.network.OkHttpExtensionsKt.toOkHttpHeaders(r8)
            okhttp3.Request$Builder r7 = r7.headers(r8)
            okhttp3.Request r7 = r7.build()
            okhttp3.WebSocket$Factory r6 = r6.webSocketFactory
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$webSocket$1 r8 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$webSocket$1
            r8.<init>(r2, r9)
            okhttp3.WebSocket r6 = r6.newWebSocket(r7, r8)
            r0.L$0 = r9
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.await(r0)
            if (r7 != r1) goto L_0x007b
            return r1
        L_0x007b:
            r7 = r9
        L_0x007c:
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$2 r8 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$2
            r8.<init>(r6)
            r7.setInvokeOnClose(r8)
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$3 r8 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$3
            r8.<init>(r7, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.DefaultWebSocketEngine.open(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public DefaultWebSocketEngine() {
        this(new OkHttpClient());
    }

    @Deprecated(message = "Use open(String, List<HttpHeader>) instead.", replaceWith = @ReplaceWith(expression = "open(url, headers.map { HttpHeader(it.key, it.value })", imports = {"com.apollographql.apollo3.api.http.HttpHeader"}))
    @Nullable
    public Object open(@NotNull String str, @NotNull Map<String, String> map, @NotNull Continuation<? super WebSocketConnection> continuation) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new HttpHeader((String) next.getKey(), (String) next.getValue()));
        }
        return open(str, (List<HttpHeader>) arrayList, continuation);
    }
}
