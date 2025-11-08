package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import com.apollographql.apollo3.api.json.BufferedSourceJsonReader;
import com.apollographql.apollo3.api.json.JsonWriters;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0002$%B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\fH¦@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011H&J\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011H@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\fH@¢\u0006\u0002\u0010\u000eJ&\u0010\u0015\u001a\u00020\f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0004J\u001e\u0010\u0018\u001a\u00020\f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011H\u0004J\u001e\u0010\u0019\u001a\u00020\f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011H\u0004J \u0010\u001a\u001a\u00020\f\"\b\b\u0000\u0010\u001b*\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001eH&J \u0010\u001f\u001a\u00020\f\"\b\b\u0000\u0010\u001b*\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001eH&J\u001a\u0010 \u001a\u00020!*\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011H\u0004J\u001c\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0011*\u00020\u0012H\u0004J\u001a\u0010#\u001a\u00020\u0012*\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011H\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006&"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WsProtocol;", "", "webSocketConnection", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "listener", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "(Lcom/apollographql/apollo3/network/ws/WebSocketConnection;Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;)V", "getListener", "()Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "getWebSocketConnection", "()Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "close", "", "connectionInit", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleServerMessage", "messageMap", "", "", "receiveMessageMap", "run", "sendMessageMap", "frameType", "Lcom/apollographql/apollo3/network/ws/WsFrameType;", "sendMessageMapBinary", "sendMessageMapText", "startOperation", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "stopOperation", "toByteString", "Lokio/ByteString;", "toMessageMap", "toUtf8", "Factory", "Listener", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nWsProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WsProtocol.kt\ncom/apollographql/apollo3/network/ws/WsProtocol\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n*L\n1#1,184:1\n78#2,8:185\n67#2,8:193\n*S KotlinDebug\n*F\n+ 1 WsProtocol.kt\ncom/apollographql/apollo3/network/ws/WsProtocol\n*L\n85#1:185,8\n89#1:193,8\n*E\n"})
public abstract class WsProtocol {
    @NotNull
    private final Listener listener;
    @NotNull
    private final WebSocketConnection webSocketConnection;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "", "name", "", "getName", "()Ljava/lang/String;", "create", "Lcom/apollographql/apollo3/network/ws/WsProtocol;", "webSocketConnection", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "listener", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Factory {
        @NotNull
        WsProtocol create(@NotNull WebSocketConnection webSocketConnection, @NotNull Listener listener, @NotNull CoroutineScope coroutineScope);

        @NotNull
        String getName();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J(\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005H&J&\u0010\r\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H&¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "", "generalError", "", "payload", "", "", "networkError", "cause", "", "operationComplete", "id", "operationError", "operationResponse", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Listener {
        void generalError(@Nullable Map<String, ? extends Object> map);

        void networkError(@NotNull Throwable th);

        void operationComplete(@NotNull String str);

        void operationError(@NotNull String str, @Nullable Map<String, ? extends Object> map);

        void operationResponse(@NotNull String str, @NotNull Map<String, ? extends Object> map);
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.apollographql.apollo3.network.ws.WsFrameType[] r0 = com.apollographql.apollo3.network.ws.WsFrameType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.apollographql.apollo3.network.ws.WsFrameType r1 = com.apollographql.apollo3.network.ws.WsFrameType.Text     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.apollographql.apollo3.network.ws.WsFrameType r1 = com.apollographql.apollo3.network.ws.WsFrameType.Binary     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WsProtocol.WhenMappings.<clinit>():void");
        }
    }

    public WsProtocol(@NotNull WebSocketConnection webSocketConnection2, @NotNull Listener listener2) {
        Intrinsics.checkNotNullParameter(webSocketConnection2, "webSocketConnection");
        Intrinsics.checkNotNullParameter(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.webSocketConnection = webSocketConnection2;
        this.listener = listener2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        throw r4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[ExcHandler: CancellationException (r4v7 'e' java.util.concurrent.CancellationException A[CUSTOM_DECLARE]), Splitter:B:17:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.Object run$suspendImpl(com.apollographql.apollo3.network.ws.WsProtocol r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof com.apollographql.apollo3.network.ws.WsProtocol$run$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            com.apollographql.apollo3.network.ws.WsProtocol$run$1 r0 = (com.apollographql.apollo3.network.ws.WsProtocol$run$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.apollographql.apollo3.network.ws.WsProtocol$run$1 r0 = new com.apollographql.apollo3.network.ws.WsProtocol$run$1
            r0.<init>(r4, r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r4 = r0.L$1
            com.apollographql.apollo3.network.ws.WsProtocol r4 = (com.apollographql.apollo3.network.ws.WsProtocol) r4
            java.lang.Object r2 = r0.L$0
            com.apollographql.apollo3.network.ws.WsProtocol r2 = (com.apollographql.apollo3.network.ws.WsProtocol) r2
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ CancellationException -> 0x0033, Exception -> 0x0031 }
            goto L_0x004e
        L_0x0031:
            r4 = move-exception
            goto L_0x0058
        L_0x0033:
            r4 = move-exception
            goto L_0x0060
        L_0x0035:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r5)
        L_0x0040:
            r0.L$0 = r4     // Catch:{ CancellationException -> 0x0033, Exception -> 0x0055 }
            r0.L$1 = r4     // Catch:{ CancellationException -> 0x0033, Exception -> 0x0055 }
            r0.label = r3     // Catch:{ CancellationException -> 0x0033, Exception -> 0x0055 }
            java.lang.Object r5 = r4.receiveMessageMap(r0)     // Catch:{ CancellationException -> 0x0033, Exception -> 0x0055 }
            if (r5 != r1) goto L_0x004d
            return r1
        L_0x004d:
            r2 = r4
        L_0x004e:
            java.util.Map r5 = (java.util.Map) r5     // Catch:{ CancellationException -> 0x0033, Exception -> 0x0031 }
            r4.handleServerMessage(r5)     // Catch:{ CancellationException -> 0x0033, Exception -> 0x0031 }
            r4 = r2
            goto L_0x0040
        L_0x0055:
            r5 = move-exception
            r2 = r4
            r4 = r5
        L_0x0058:
            com.apollographql.apollo3.network.ws.WsProtocol$Listener r5 = r2.listener
            r5.networkError(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L_0x0060:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WsProtocol.run$suspendImpl(com.apollographql.apollo3.network.ws.WsProtocol, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void close() {
        this.webSocketConnection.close();
    }

    @Nullable
    public abstract Object connectionInit(@NotNull Continuation<? super Unit> continuation);

    @NotNull
    public final Listener getListener() {
        return this.listener;
    }

    @NotNull
    public final WebSocketConnection getWebSocketConnection() {
        return this.webSocketConnection;
    }

    public abstract void handleServerMessage(@NotNull Map<String, ? extends Object> map);

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object receiveMessageMap(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, ? extends java.lang.Object>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1 r0 = (com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1 r0 = new com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1
            r0.<init>(r4, r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r4 = r0.L$1
            com.apollographql.apollo3.network.ws.WsProtocol r4 = (com.apollographql.apollo3.network.ws.WsProtocol) r4
            java.lang.Object r2 = r0.L$0
            com.apollographql.apollo3.network.ws.WsProtocol r2 = (com.apollographql.apollo3.network.ws.WsProtocol) r2
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x004c
        L_0x0031:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r5)
        L_0x003c:
            com.apollographql.apollo3.network.ws.WebSocketConnection r5 = r4.webSocketConnection
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r5 = r5.receive(r0)
            if (r5 != r1) goto L_0x004b
            return r1
        L_0x004b:
            r2 = r4
        L_0x004c:
            java.lang.String r5 = (java.lang.String) r5
            java.util.Map r4 = r4.toMessageMap(r5)
            if (r4 == 0) goto L_0x0055
            return r4
        L_0x0055:
            r4 = r2
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WsProtocol.receiveMessageMap(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public Object run(@NotNull Continuation<? super Unit> continuation) {
        return run$suspendImpl(this, continuation);
    }

    public final void sendMessageMap(@NotNull Map<String, ? extends Object> map, @NotNull WsFrameType wsFrameType) {
        Intrinsics.checkNotNullParameter(map, "messageMap");
        Intrinsics.checkNotNullParameter(wsFrameType, "frameType");
        int i3 = WhenMappings.$EnumSwitchMapping$0[wsFrameType.ordinal()];
        if (i3 == 1) {
            sendMessageMapText(map);
        } else if (i3 == 2) {
            sendMessageMapBinary(map);
        }
    }

    public final void sendMessageMapBinary(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "messageMap");
        this.webSocketConnection.send(toByteString(map));
    }

    public final void sendMessageMapText(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "messageMap");
        this.webSocketConnection.send(toUtf8(map));
    }

    public abstract <D extends Operation.Data> void startOperation(@NotNull ApolloRequest<D> apolloRequest);

    public abstract <D extends Operation.Data> void stopOperation(@NotNull ApolloRequest<D> apolloRequest);

    @NotNull
    public final ByteString toByteString(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Buffer buffer = new Buffer();
        JsonWriters.writeAny(new BufferedSinkJsonWriter(buffer, (String) null), map);
        return buffer.readByteString();
    }

    @Nullable
    public final Map<String, Object> toMessageMap(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            Object fromJson = Adapters.AnyAdapter.fromJson(new BufferedSourceJsonReader(new Buffer().writeUtf8(str)), CustomScalarAdapters.Empty);
            if (fromJson instanceof Map) {
                return (Map) fromJson;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @NotNull
    public final String toUtf8(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Buffer buffer = new Buffer();
        JsonWriters.writeAny(new BufferedSinkJsonWriter(buffer, (String) null), map);
        return buffer.readUtf8();
    }
}
