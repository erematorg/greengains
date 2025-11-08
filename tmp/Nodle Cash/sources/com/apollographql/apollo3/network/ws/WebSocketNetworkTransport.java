package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.internal.CloseableSingleThreadDispatcher;
import com.apollographql.apollo3.internal.DeferredJsonMerger;
import com.apollographql.apollo3.internal.FlowsKt;
import com.apollographql.apollo3.network.NetworkTransport;
import com.apollographql.apollo3.network.ws.SubscriptionWsProtocol;
import com.apollographql.apollo3.network.ws.WsProtocol;
import com.apollographql.apollo3.network.ws.internal.Dispose;
import com.apollographql.apollo3.network.ws.internal.Event;
import com.apollographql.apollo3.network.ws.internal.Message;
import com.apollographql.apollo3.network.ws.internal.NetworkError;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000«\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001 \u0018\u00002\u00020\u0001:\u0001<B\u0001\b\u0002\u0012\u001c\u0010\u0002\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u00129\u0010\u0010\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0012\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0011¢\u0006\u0002\u0010\u0017J\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0012J\b\u00101\u001a\u00020/H\u0016J,\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50403\"\b\b\u0000\u00105*\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u0002H508H\u0016J\u0016\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020\u001bH@¢\u0006\u0002\u0010;R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0004\n\u0002\u0010!R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001e0&X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000RC\u0010\u0010\u001a5\b\u0001\u0012\u0004\u0012\u00020\u0012\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0011X\u0004¢\u0006\u0004\n\u0002\u0010'R&\u0010\u0002\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003X\u0004¢\u0006\u0004\n\u0002\u0010(R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport;", "Lcom/apollographql/apollo3/network/NetworkTransport;", "serverUrl", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "webSocketEngine", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "idleTimeoutMillis", "", "protocolFactory", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "reopenWhen", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "attempt", "", "(Lkotlin/jvm/functions/Function1;Ljava/util/List;Lcom/apollographql/apollo3/network/ws/WebSocketEngine;JLcom/apollographql/apollo3/network/ws/WsProtocol$Factory;Lkotlin/jvm/functions/Function3;)V", "backgroundDispatcher", "Lcom/apollographql/apollo3/internal/CloseableSingleThreadDispatcher;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "listener", "com/apollographql/apollo3/network/ws/WebSocketNetworkTransport$listener$1", "Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$listener$1;", "messages", "Lkotlinx/coroutines/channels/Channel;", "Lcom/apollographql/apollo3/network/ws/internal/Message;", "mutableEvents", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function1;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "closeConnection", "", "reason", "dispose", "execute", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "supervise", "scope", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Builder", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nWebSocketNetworkTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketNetworkTransport.kt\ncom/apollographql/apollo3/network/ws/WebSocketNetworkTransport\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 4 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 5 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,448:1\n1855#2,2:449\n1747#2,3:451\n20#3:454\n22#3:458\n47#3:459\n49#3:463\n27#3:464\n29#3:468\n50#4:455\n55#4:457\n50#4:460\n55#4:462\n50#4:465\n55#4:467\n106#5:456\n106#5:461\n106#5:466\n*S KotlinDebug\n*F\n+ 1 WebSocketNetworkTransport.kt\ncom/apollographql/apollo3/network/ws/WebSocketNetworkTransport\n*L\n169#1:449,2\n196#1:451,3\n273#1:454\n273#1:458\n302#1:459\n302#1:463\n330#1:464\n330#1:468\n273#1:455\n273#1:457\n302#1:460\n302#1:462\n330#1:465\n330#1:467\n273#1:456\n302#1:461\n330#1:466\n*E\n"})
public final class WebSocketNetworkTransport implements NetworkTransport {
    /* access modifiers changed from: private */
    @NotNull
    public final CloseableSingleThreadDispatcher backgroundDispatcher;
    @NotNull
    private final CoroutineScope coroutineScope;
    @NotNull
    private final SharedFlow<Event> events;
    @NotNull
    private final List<HttpHeader> headers;
    /* access modifiers changed from: private */
    public final long idleTimeoutMillis;
    @NotNull
    private final WebSocketNetworkTransport$listener$1 listener;
    /* access modifiers changed from: private */
    @NotNull
    public final Channel<Message> messages;
    @NotNull
    private final MutableSharedFlow<Event> mutableEvents;
    @NotNull
    private final WsProtocol.Factory protocolFactory;
    @Nullable
    private final Function3<Throwable, Long, Continuation<? super Boolean>, Object> reopenWhen;
    @NotNull
    private final Function1<Continuation<? super String>, Object> serverUrl;
    @NotNull
    private final StateFlow<Integer> subscriptionCount;
    @NotNull
    private final WebSocketEngine webSocketEngine;

    @SourceDebugExtension({"SMAP\nWebSocketNetworkTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketNetworkTransport.kt\ncom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$1\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,448:1\n52#2,18:449\n*S KotlinDebug\n*F\n+ 1 WebSocketNetworkTransport.kt\ncom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$1\n*L\n97#1:449,18\n*E\n"})
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$1", f = "WebSocketNetworkTransport.kt", i = {0}, l = {98}, m = "invokeSuspend", n = {"$this$use$iv"}, s = {"L$0"})
    /* renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ WebSocketNetworkTransport this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass1 r02 = new AnonymousClass1(this.this$0, continuation);
            r02.L$0 = obj;
            return r02;
        }

        /* JADX WARNING: Removed duplicated region for block: B:21:0x0043 A[SYNTHETIC, Splitter:B:21:0x0043] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0051  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0057  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L_0x001e
                if (r1 != r2) goto L_0x0016
                java.lang.Object r6 = r6.L$0
                java.io.Closeable r6 = (java.io.Closeable) r6
                kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0014 }
                goto L_0x0039
            L_0x0014:
                r7 = move-exception
                goto L_0x003e
            L_0x0016:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x001e:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
                com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r1 = r6.this$0
                com.apollographql.apollo3.internal.CloseableSingleThreadDispatcher r1 = r1.backgroundDispatcher
                com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r4 = r6.this$0
                r6.L$0 = r1     // Catch:{ all -> 0x003c }
                r6.label = r2     // Catch:{ all -> 0x003c }
                java.lang.Object r6 = r4.supervise(r7, r6)     // Catch:{ all -> 0x003c }
                if (r6 != r0) goto L_0x0038
                return r0
            L_0x0038:
                r6 = r1
            L_0x0039:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0014 }
                goto L_0x0041
            L_0x003c:
                r7 = move-exception
                r6 = r1
            L_0x003e:
                r5 = r3
                r3 = r7
                r7 = r5
            L_0x0041:
                if (r6 == 0) goto L_0x004f
                r6.close()     // Catch:{ all -> 0x0047 }
                goto L_0x004f
            L_0x0047:
                r6 = move-exception
                if (r3 != 0) goto L_0x004c
                r3 = r6
                goto L_0x004f
            L_0x004c:
                kotlin.ExceptionsKt.addSuppressed(r3, r6)
            L_0x004f:
                if (r3 != 0) goto L_0x0057
                kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L_0x0057:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0016J\u0014\u0010\u001c\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u001dJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0014\u0010\u0003\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u001dJ\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010!\u001a\u00020\u00002\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0015H\u0007JF\u0010\u000b\u001a\u00020\u000029\u0010\u000b\u001a5\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f¢\u0006\u0002\u0010\"J+\u0010\u0014\u001a\u00020\u00002\u001e\u0010\u0014\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0015¢\u0006\u0002\u0010#J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000RC\u0010\u000b\u001a5\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0013R(\u0010\u0014\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0015X\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$Builder;", "", "()V", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "idleTimeoutMillis", "", "Ljava/lang/Long;", "protocolFactory", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "reopenWhen", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "attempt", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function3;", "serverUrl", "Lkotlin/Function1;", "", "Lkotlin/jvm/functions/Function1;", "webSocketEngine", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "addHeader", "value", "addHeaders", "", "build", "Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport;", "protocol", "reconnectWhen", "(Lkotlin/jvm/functions/Function3;)Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$Builder;", "(Lkotlin/jvm/functions/Function1;)Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$Builder;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @NotNull
        private List<HttpHeader> headers = new ArrayList();
        @Nullable
        private Long idleTimeoutMillis;
        @Nullable
        private WsProtocol.Factory protocolFactory;
        @Nullable
        private Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> reopenWhen;
        @Nullable
        private Function1<? super Continuation<? super String>, ? extends Object> serverUrl;
        @Nullable
        private WebSocketEngine webSocketEngine;

        @NotNull
        public final Builder addHeader(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "value");
            this.headers.add(new HttpHeader(str, str2));
            return this;
        }

        @NotNull
        public final Builder addHeaders(@NotNull List<HttpHeader> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.headers.addAll(list);
            return this;
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.apollographql.apollo3.network.ws.WebSocketNetworkTransport build() {
            /*
                r17 = this;
                r0 = r17
                com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r9 = new com.apollographql.apollo3.network.ws.WebSocketNetworkTransport
                kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super java.lang.String>, ? extends java.lang.Object> r1 = r0.serverUrl
                if (r1 == 0) goto L_0x0038
                java.util.List<com.apollographql.apollo3.api.http.HttpHeader> r2 = r0.headers
                com.apollographql.apollo3.network.ws.WebSocketEngine r3 = r0.webSocketEngine
                if (r3 != 0) goto L_0x0013
                com.apollographql.apollo3.network.ws.DefaultWebSocketEngine r3 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine
                r3.<init>()
            L_0x0013:
                java.lang.Long r4 = r0.idleTimeoutMillis
                if (r4 == 0) goto L_0x001c
                long r4 = r4.longValue()
                goto L_0x001f
            L_0x001c:
                r4 = 60000(0xea60, double:2.9644E-319)
            L_0x001f:
                com.apollographql.apollo3.network.ws.WsProtocol$Factory r6 = r0.protocolFactory
                if (r6 != 0) goto L_0x0030
                com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory r6 = new com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory
                r15 = 7
                r16 = 0
                r11 = 0
                r13 = 0
                r14 = 0
                r10 = r6
                r10.<init>(r11, r13, r14, r15, r16)
            L_0x0030:
                kotlin.jvm.functions.Function3<? super java.lang.Throwable, ? super java.lang.Long, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r7 = r0.reopenWhen
                r8 = 0
                r0 = r9
                r0.<init>(r1, r2, r3, r4, r6, r7, r8)
                return r9
            L_0x0038:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "No serverUrl specified"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport.Builder.build():com.apollographql.apollo3.network.ws.WebSocketNetworkTransport");
        }

        @NotNull
        public final Builder headers(@NotNull List<HttpHeader> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.headers.clear();
            this.headers.addAll(list);
            return this;
        }

        @NotNull
        public final Builder idleTimeoutMillis(long j2) {
            this.idleTimeoutMillis = Long.valueOf(j2);
            return this;
        }

        @NotNull
        public final Builder protocol(@NotNull WsProtocol.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "protocolFactory");
            this.protocolFactory = factory;
            return this;
        }

        @NotNull
        @Deprecated(message = "Use reopenWhen(reopenWhen: (suspend (Throwable, attempt: Long) -> Boolean))")
        public final Builder reconnectWhen(@Nullable Function1<? super Throwable, Boolean> function1) {
            WebSocketNetworkTransport$Builder$reconnectWhen$1$1$adaptedLambda$1 webSocketNetworkTransport$Builder$reconnectWhen$1$1$adaptedLambda$1 = null;
            if (function1 != null) {
                webSocketNetworkTransport$Builder$reconnectWhen$1$1$adaptedLambda$1 = new WebSocketNetworkTransport$Builder$reconnectWhen$1$1$adaptedLambda$1(function1, (Continuation<? super WebSocketNetworkTransport$Builder$reconnectWhen$1$1$adaptedLambda$1>) null);
            }
            this.reopenWhen = webSocketNetworkTransport$Builder$reconnectWhen$1$1$adaptedLambda$1;
            return this;
        }

        @NotNull
        public final Builder reopenWhen(@Nullable Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function3) {
            this.reopenWhen = function3;
            return this;
        }

        @NotNull
        public final Builder serverUrl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "serverUrl");
            this.serverUrl = new WebSocketNetworkTransport$Builder$serverUrl$1$1(str, (Continuation<? super WebSocketNetworkTransport$Builder$serverUrl$1$1>) null);
            return this;
        }

        @NotNull
        public final Builder webSocketEngine(@NotNull WebSocketEngine webSocketEngine2) {
            Intrinsics.checkNotNullParameter(webSocketEngine2, "webSocketEngine");
            this.webSocketEngine = webSocketEngine2;
            return this;
        }

        @NotNull
        public final Builder serverUrl(@Nullable Function1<? super Continuation<? super String>, ? extends Object> function1) {
            this.serverUrl = function1;
            return this;
        }
    }

    public /* synthetic */ WebSocketNetworkTransport(Function1 function1, List list, WebSocketEngine webSocketEngine2, long j2, WsProtocol.Factory factory, Function3 function3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, list, webSocketEngine2, j2, factory, function3);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object supervise(kotlinx.coroutines.CoroutineScope r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) {
        /*
            r26 = this;
            r0 = r28
            boolean r1 = r0 instanceof com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$1 r1 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0017
            int r2 = r2 - r3
            r1.label = r2
            r2 = r26
            goto L_0x001e
        L_0x0017:
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$1 r1 = new com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$1
            r2 = r26
            r1.<init>(r2, r0)
        L_0x001e:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r6 = 1
            r9 = 0
            switch(r4) {
                case 0: goto L_0x01aa;
                case 1: goto L_0x0183;
                case 2: goto L_0x0160;
                case 3: goto L_0x0137;
                case 4: goto L_0x0101;
                case 5: goto L_0x00cd;
                case 6: goto L_0x00a3;
                case 7: goto L_0x0078;
                case 8: goto L_0x0052;
                case 9: goto L_0x0033;
                default: goto L_0x002b;
            }
        L_0x002b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0033:
            long r10 = r1.J$0
            java.lang.Object r2 = r1.L$5
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r4 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r12 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            java.lang.Object r15 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r15 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r15
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x04b6
        L_0x0052:
            long r10 = r1.J$0
            java.lang.Object r2 = r1.L$5
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r4 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r12 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            java.lang.Object r15 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r15 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r15
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r2
            r7 = r4
            r5 = r12
            r0 = r14
            r2 = r15
        L_0x0074:
            r4 = r1
            r1 = r13
            goto L_0x03f6
        L_0x0078:
            long r10 = r1.J$0
            java.lang.Object r2 = r1.L$6
            com.apollographql.apollo3.network.ws.internal.Message r2 = (com.apollographql.apollo3.network.ws.internal.Message) r2
            java.lang.Object r4 = r1.L$5
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r12 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r14 = (kotlin.jvm.internal.Ref.ObjectRef) r14
            java.lang.Object r15 = r1.L$1
            kotlinx.coroutines.CoroutineScope r15 = (kotlinx.coroutines.CoroutineScope) r15
            java.lang.Object r5 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r5 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r5
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x009b }
            goto L_0x03a2
        L_0x009b:
            r0 = move-exception
            r2 = r4
            r6 = r9
            r4 = r12
            r12 = r13
            r13 = r14
            goto L_0x03cb
        L_0x00a3:
            long r4 = r1.J$0
            java.lang.Object r2 = r1.L$5
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.CoroutineScope r13 = (kotlinx.coroutines.CoroutineScope) r13
            java.lang.Object r14 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r14 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r14
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r2
            r7 = r10
            r0 = r13
            r2 = r14
        L_0x00c4:
            r22 = r4
            r4 = r1
            r5 = r11
            r1 = r12
            r10 = r22
            goto L_0x03f6
        L_0x00cd:
            long r4 = r1.J$0
            java.lang.Object r2 = r1.L$6
            com.apollographql.apollo3.network.ws.internal.Message r2 = (com.apollographql.apollo3.network.ws.internal.Message) r2
            java.lang.Object r10 = r1.L$5
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            java.lang.Object r15 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r15 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r15
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x00fa }
            r22 = r4
            r4 = r10
            r5 = r15
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r10 = r22
            goto L_0x0379
        L_0x00fa:
            r0 = move-exception
            r2 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            goto L_0x0416
        L_0x0101:
            long r4 = r1.J$0
            java.lang.Object r2 = r1.L$7
            com.apollographql.apollo3.network.ws.WebSocketEngine r2 = (com.apollographql.apollo3.network.ws.WebSocketEngine) r2
            java.lang.Object r10 = r1.L$6
            com.apollographql.apollo3.network.ws.internal.Message r10 = (com.apollographql.apollo3.network.ws.internal.Message) r10
            java.lang.Object r11 = r1.L$5
            java.util.Map r11 = (java.util.Map) r11
            java.lang.Object r12 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r14 = (kotlin.jvm.internal.Ref.ObjectRef) r14
            java.lang.Object r15 = r1.L$1
            kotlinx.coroutines.CoroutineScope r15 = (kotlinx.coroutines.CoroutineScope) r15
            java.lang.Object r7 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r7 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r7
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x012e }
            r22 = r15
            r15 = r7
            r7 = r14
            r14 = r22
            goto L_0x02fd
        L_0x012e:
            r0 = move-exception
            r2 = r11
            r10 = r12
            r11 = r13
            r12 = r14
            r14 = r15
            r15 = r7
            goto L_0x0416
        L_0x0137:
            long r4 = r1.J$0
            java.lang.Object r2 = r1.L$5
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r7 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            java.lang.Object r12 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r12 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
            r22 = r4
            r4 = r1
            r5 = r8
            r1 = r10
            r8 = r2
            r10 = r22
            r2 = r12
            goto L_0x0272
        L_0x0160:
            long r4 = r1.J$0
            java.lang.Object r2 = r1.L$6
            com.apollographql.apollo3.network.ws.internal.Message r2 = (com.apollographql.apollo3.network.ws.internal.Message) r2
            java.lang.Object r7 = r1.L$5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r8 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            java.lang.Object r13 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r13 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r13
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x022f
        L_0x0183:
            long r4 = r1.J$0
            java.lang.Object r2 = r1.L$5
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r7 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            java.lang.Object r12 = r1.L$0
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport r12 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r22 = r4
            r4 = r1
            r5 = r8
            r1 = r10
            r8 = r2
            r2 = r12
        L_0x01a7:
            r12 = r22
            goto L_0x01e6
        L_0x01aa:
            kotlin.jvm.internal.Ref$ObjectRef r0 = androidx.compose.animation.core.a.w(r0)
            kotlin.jvm.internal.Ref$ObjectRef r4 = new kotlin.jvm.internal.Ref$ObjectRef
            r4.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap
            r7.<init>()
            r8 = r7
            r10 = 0
            r7 = r5
            r5 = r4
            r4 = r1
            r1 = r0
            r0 = r27
        L_0x01c6:
            kotlinx.coroutines.channels.Channel<com.apollographql.apollo3.network.ws.internal.Message> r12 = r2.messages
            r4.L$0 = r2
            r4.L$1 = r0
            r4.L$2 = r1
            r4.L$3 = r5
            r4.L$4 = r7
            r4.L$5 = r8
            r4.L$6 = r9
            r4.J$0 = r10
            r4.label = r6
            java.lang.Object r12 = r12.receive(r4)
            if (r12 != r3) goto L_0x01e1
            return r3
        L_0x01e1:
            r22 = r10
            r11 = r0
            r0 = r12
            goto L_0x01a7
        L_0x01e6:
            r10 = r0
            com.apollographql.apollo3.network.ws.internal.Message r10 = (com.apollographql.apollo3.network.ws.internal.Message) r10
            boolean r0 = r10 instanceof com.apollographql.apollo3.network.ws.internal.Event
            if (r0 == 0) goto L_0x02a7
            boolean r0 = r10 instanceof com.apollographql.apollo3.network.ws.internal.NetworkError
            if (r0 == 0) goto L_0x027f
            supervise$closeProtocol(r7, r5, r1)
            kotlin.jvm.functions.Function3<java.lang.Throwable, java.lang.Long, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r0 = r2.reopenWhen
            if (r0 == 0) goto L_0x024b
            r14 = r10
            com.apollographql.apollo3.network.ws.internal.NetworkError r14 = (com.apollographql.apollo3.network.ws.internal.NetworkError) r14
            java.lang.Throwable r14 = r14.getCause()
            java.lang.Long r15 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)
            r4.L$0 = r2
            r4.L$1 = r11
            r4.L$2 = r1
            r4.L$3 = r5
            r4.L$4 = r7
            r4.L$5 = r8
            r4.L$6 = r10
            r4.J$0 = r12
            r9 = 2
            r4.label = r9
            java.lang.Object r0 = r0.invoke(r14, r15, r4)
            if (r0 != r3) goto L_0x021d
            return r3
        L_0x021d:
            r22 = r11
            r11 = r1
            r1 = r4
            r23 = r12
            r13 = r2
            r2 = r10
            r12 = r22
            r10 = r5
            r4 = r23
            r25 = r8
            r8 = r7
            r7 = r25
        L_0x022f:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != r6) goto L_0x0248
            r9 = r6
            r0 = r12
        L_0x0239:
            r22 = r4
            r4 = r1
            r5 = r10
            r1 = r11
            r10 = r2
            r2 = r13
            r12 = r22
            r24 = r8
            r8 = r7
            r7 = r24
            goto L_0x024d
        L_0x0248:
            r0 = r12
            r9 = 0
            goto L_0x0239
        L_0x024b:
            r0 = r11
            r9 = 0
        L_0x024d:
            if (r9 == 0) goto L_0x0275
            r9 = 1
            long r9 = r9 + r12
            kotlinx.coroutines.channels.Channel<com.apollographql.apollo3.network.ws.internal.Message> r11 = r2.messages
            com.apollographql.apollo3.network.ws.internal.RestartConnection r12 = com.apollographql.apollo3.network.ws.internal.RestartConnection.INSTANCE
            r4.L$0 = r2
            r4.L$1 = r0
            r4.L$2 = r1
            r4.L$3 = r5
            r4.L$4 = r7
            r4.L$5 = r8
            r13 = 0
            r4.L$6 = r13
            r4.J$0 = r9
            r13 = 3
            r4.label = r13
            java.lang.Object r11 = r11.send(r12, r4)
            if (r11 != r3) goto L_0x0271
            return r3
        L_0x0271:
            r10 = r9
        L_0x0272:
            r9 = 0
            goto L_0x01c6
        L_0x0275:
            kotlinx.coroutines.flow.MutableSharedFlow<com.apollographql.apollo3.network.ws.internal.Event> r9 = r2.mutableEvents
            r9.tryEmit(r10)
        L_0x027a:
            r9 = 0
            r10 = 0
            goto L_0x01c6
        L_0x027f:
            boolean r0 = r10 instanceof com.apollographql.apollo3.network.ws.internal.ConnectionReEstablished
            if (r0 == 0) goto L_0x02a1
            java.util.Collection r0 = r8.values()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x028d:
            boolean r9 = r0.hasNext()
            if (r9 == 0) goto L_0x029f
            java.lang.Object r9 = r0.next()
            com.apollographql.apollo3.network.ws.internal.StartOperation r9 = (com.apollographql.apollo3.network.ws.internal.StartOperation) r9
            kotlinx.coroutines.channels.Channel<com.apollographql.apollo3.network.ws.internal.Message> r10 = r2.messages
            r10.m10510trySendJP2dKIU(r9)
            goto L_0x028d
        L_0x029f:
            r0 = r11
            goto L_0x027a
        L_0x02a1:
            kotlinx.coroutines.flow.MutableSharedFlow<com.apollographql.apollo3.network.ws.internal.Event> r0 = r2.mutableEvents
            r0.tryEmit(r10)
            goto L_0x029f
        L_0x02a7:
            boolean r0 = r10 instanceof com.apollographql.apollo3.network.ws.internal.Command
            if (r0 == 0) goto L_0x02ca
            boolean r0 = r10 instanceof com.apollographql.apollo3.network.ws.internal.Dispose
            if (r0 == 0) goto L_0x02b5
            supervise$closeProtocol(r7, r5, r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02b5:
            T r0 = r7.element
            if (r0 != 0) goto L_0x0440
            boolean r0 = r10 instanceof com.apollographql.apollo3.network.ws.internal.StopOperation
            if (r0 == 0) goto L_0x02cd
            com.apollographql.apollo3.network.ws.internal.StopOperation r10 = (com.apollographql.apollo3.network.ws.internal.StopOperation) r10
            com.apollographql.apollo3.api.ApolloRequest r0 = r10.getRequest()
            java.util.UUID r0 = r0.getRequestUuid()
            r8.remove(r0)
        L_0x02ca:
            r9 = 0
            goto L_0x0505
        L_0x02cd:
            com.apollographql.apollo3.network.ws.WebSocketEngine r0 = r2.webSocketEngine     // Catch:{ Exception -> 0x040a }
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super java.lang.String>, java.lang.Object> r9 = r2.serverUrl     // Catch:{ Exception -> 0x040a }
            r4.L$0 = r2     // Catch:{ Exception -> 0x040a }
            r4.L$1 = r11     // Catch:{ Exception -> 0x040a }
            r4.L$2 = r1     // Catch:{ Exception -> 0x040a }
            r4.L$3 = r5     // Catch:{ Exception -> 0x040a }
            r4.L$4 = r7     // Catch:{ Exception -> 0x040a }
            r4.L$5 = r8     // Catch:{ Exception -> 0x040a }
            r4.L$6 = r10     // Catch:{ Exception -> 0x040a }
            r4.L$7 = r0     // Catch:{ Exception -> 0x040a }
            r4.J$0 = r12     // Catch:{ Exception -> 0x040a }
            r14 = 4
            r4.label = r14     // Catch:{ Exception -> 0x040a }
            java.lang.Object r9 = r9.invoke(r4)     // Catch:{ Exception -> 0x040a }
            if (r9 != r3) goto L_0x02ed
            return r3
        L_0x02ed:
            r15 = r2
            r14 = r11
            r2 = r0
            r11 = r8
            r0 = r9
            r22 = r7
            r7 = r1
            r1 = r4
            r23 = r12
            r13 = r5
            r12 = r22
            r4 = r23
        L_0x02fd:
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0313 }
            java.util.List<com.apollographql.apollo3.api.http.HttpHeader> r8 = r15.headers     // Catch:{ Exception -> 0x0313 }
            java.lang.Iterable r8 = (java.lang.Iterable) r8     // Catch:{ Exception -> 0x0313 }
            boolean r9 = r8 instanceof java.util.Collection     // Catch:{ Exception -> 0x0313 }
            java.lang.String r6 = "Sec-WebSocket-Protocol"
            if (r9 == 0) goto L_0x031a
            r9 = r8
            java.util.Collection r9 = (java.util.Collection) r9     // Catch:{ Exception -> 0x0313 }
            boolean r9 = r9.isEmpty()     // Catch:{ Exception -> 0x0313 }
            if (r9 == 0) goto L_0x031a
            goto L_0x0339
        L_0x0313:
            r0 = move-exception
        L_0x0314:
            r2 = r11
            r10 = r12
            r11 = r13
            r12 = r7
            goto L_0x0416
        L_0x031a:
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x0313 }
        L_0x031e:
            boolean r9 = r8.hasNext()     // Catch:{ Exception -> 0x0313 }
            if (r9 == 0) goto L_0x0339
            java.lang.Object r9 = r8.next()     // Catch:{ Exception -> 0x0313 }
            com.apollographql.apollo3.api.http.HttpHeader r9 = (com.apollographql.apollo3.api.http.HttpHeader) r9     // Catch:{ Exception -> 0x0313 }
            java.lang.String r9 = r9.getName()     // Catch:{ Exception -> 0x0313 }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0313 }
            if (r9 == 0) goto L_0x031e
            java.util.List<com.apollographql.apollo3.api.http.HttpHeader> r6 = r15.headers     // Catch:{ Exception -> 0x0313 }
            r16 = r3
            goto L_0x034e
        L_0x0339:
            java.util.List<com.apollographql.apollo3.api.http.HttpHeader> r8 = r15.headers     // Catch:{ Exception -> 0x0313 }
            java.util.Collection r8 = (java.util.Collection) r8     // Catch:{ Exception -> 0x0313 }
            com.apollographql.apollo3.api.http.HttpHeader r9 = new com.apollographql.apollo3.api.http.HttpHeader     // Catch:{ Exception -> 0x0313 }
            r16 = r3
            com.apollographql.apollo3.network.ws.WsProtocol$Factory r3 = r15.protocolFactory     // Catch:{ Exception -> 0x0405 }
            java.lang.String r3 = r3.getName()     // Catch:{ Exception -> 0x0405 }
            r9.<init>(r6, r3)     // Catch:{ Exception -> 0x0405 }
            java.util.List r6 = kotlin.collections.CollectionsKt.plus(r8, r9)     // Catch:{ Exception -> 0x0405 }
        L_0x034e:
            r1.L$0 = r15     // Catch:{ Exception -> 0x0405 }
            r1.L$1 = r14     // Catch:{ Exception -> 0x0405 }
            r1.L$2 = r7     // Catch:{ Exception -> 0x0405 }
            r1.L$3 = r13     // Catch:{ Exception -> 0x0405 }
            r1.L$4 = r12     // Catch:{ Exception -> 0x0405 }
            r1.L$5 = r11     // Catch:{ Exception -> 0x0405 }
            r1.L$6 = r10     // Catch:{ Exception -> 0x0405 }
            r3 = 0
            r1.L$7 = r3     // Catch:{ Exception -> 0x0405 }
            r1.J$0 = r4     // Catch:{ Exception -> 0x0405 }
            r3 = 5
            r1.label = r3     // Catch:{ Exception -> 0x0405 }
            java.lang.Object r0 = r2.open((java.lang.String) r0, (java.util.List<com.apollographql.apollo3.api.http.HttpHeader>) r6, (kotlin.coroutines.Continuation<? super com.apollographql.apollo3.network.ws.WebSocketConnection>) r1)     // Catch:{ Exception -> 0x0405 }
            r3 = r16
            if (r0 != r3) goto L_0x036d
            return r3
        L_0x036d:
            r2 = r10
            r22 = r14
            r14 = r7
            r23 = r4
            r4 = r11
            r10 = r23
            r5 = r15
            r15 = r22
        L_0x0379:
            com.apollographql.apollo3.network.ws.WebSocketConnection r0 = (com.apollographql.apollo3.network.ws.WebSocketConnection) r0     // Catch:{ Exception -> 0x03f9 }
            com.apollographql.apollo3.network.ws.WsProtocol$Factory r6 = r5.protocolFactory
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$listener$1 r7 = r5.listener
            com.apollographql.apollo3.network.ws.WsProtocol r0 = r6.create(r0, r7, r15)
            r12.element = r0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x03c5 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x03c5 }
            r1.L$1 = r15     // Catch:{ Exception -> 0x03c5 }
            r1.L$2 = r14     // Catch:{ Exception -> 0x03c5 }
            r1.L$3 = r13     // Catch:{ Exception -> 0x03c5 }
            r1.L$4 = r12     // Catch:{ Exception -> 0x03c5 }
            r1.L$5 = r4     // Catch:{ Exception -> 0x03c5 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x03c5 }
            r1.J$0 = r10     // Catch:{ Exception -> 0x03c5 }
            r6 = 7
            r1.label = r6     // Catch:{ Exception -> 0x03c5 }
            java.lang.Object r0 = r0.connectionInit(r1)     // Catch:{ Exception -> 0x03c5 }
            if (r0 != r3) goto L_0x03a2
            return r3
        L_0x03a2:
            kotlinx.coroutines.CoroutineStart r18 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$3 r0 = new com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$3
            r6 = 0
            r0.<init>(r12, r6)
            r20 = 1
            r21 = 0
            r17 = 0
            r16 = r15
            r19 = r0
            kotlinx.coroutines.Job r0 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r16, r17, r18, r19, r20, r21)
            r13.element = r0
            r0 = r5
            r5 = r13
            r22 = r10
            r10 = r2
            r2 = r4
            r4 = r12
            r12 = r22
            goto L_0x0446
        L_0x03c5:
            r0 = move-exception
            r2 = r4
            r4 = r12
            r12 = r13
            r13 = r14
            r6 = 0
        L_0x03cb:
            r4.element = r6
            kotlinx.coroutines.channels.Channel<com.apollographql.apollo3.network.ws.internal.Message> r7 = r5.messages
            com.apollographql.apollo3.network.ws.internal.NetworkError r8 = new com.apollographql.apollo3.network.ws.internal.NetworkError
            r8.<init>(r0)
            r1.L$0 = r5
            r1.L$1 = r15
            r1.L$2 = r13
            r1.L$3 = r12
            r1.L$4 = r4
            r1.L$5 = r2
            r1.L$6 = r6
            r1.J$0 = r10
            r0 = 8
            r1.label = r0
            java.lang.Object r0 = r7.send(r8, r1)
            if (r0 != r3) goto L_0x03ef
            return r3
        L_0x03ef:
            r8 = r2
            r7 = r4
            r2 = r5
            r5 = r12
            r0 = r15
            goto L_0x0074
        L_0x03f6:
            r6 = 1
            goto L_0x0272
        L_0x03f9:
            r0 = move-exception
            r2 = r4
            r22 = r15
            r15 = r5
            r4 = r10
            r10 = r12
            r11 = r13
            r12 = r14
            r14 = r22
            goto L_0x0416
        L_0x0405:
            r0 = move-exception
            r3 = r16
            goto L_0x0314
        L_0x040a:
            r0 = move-exception
            r15 = r2
            r10 = r7
            r2 = r8
            r14 = r11
            r11 = r5
            r22 = r12
            r12 = r1
            r1 = r4
            r4 = r22
        L_0x0416:
            kotlinx.coroutines.channels.Channel<com.apollographql.apollo3.network.ws.internal.Message> r6 = r15.messages
            com.apollographql.apollo3.network.ws.internal.NetworkError r7 = new com.apollographql.apollo3.network.ws.internal.NetworkError
            r7.<init>(r0)
            r1.L$0 = r15
            r1.L$1 = r14
            r1.L$2 = r12
            r1.L$3 = r11
            r1.L$4 = r10
            r1.L$5 = r2
            r8 = 0
            r1.L$6 = r8
            r1.L$7 = r8
            r1.J$0 = r4
            r0 = 6
            r1.label = r0
            java.lang.Object r0 = r6.send(r7, r1)
            if (r0 != r3) goto L_0x043a
            return r3
        L_0x043a:
            r8 = r2
            r7 = r10
            r0 = r14
            r2 = r15
            goto L_0x00c4
        L_0x0440:
            r14 = r1
            r0 = r2
            r1 = r4
            r4 = r7
            r2 = r8
            r15 = r11
        L_0x0446:
            r6 = r10
            com.apollographql.apollo3.network.ws.internal.Command r6 = (com.apollographql.apollo3.network.ws.internal.Command) r6
            boolean r7 = r6 instanceof com.apollographql.apollo3.network.ws.internal.StartOperation
            if (r7 == 0) goto L_0x046a
            r6 = r10
            com.apollographql.apollo3.network.ws.internal.StartOperation r6 = (com.apollographql.apollo3.network.ws.internal.StartOperation) r6
            com.apollographql.apollo3.api.ApolloRequest r7 = r6.getRequest()
            java.util.UUID r7 = r7.getRequestUuid()
            r2.put(r7, r10)
            T r7 = r4.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            com.apollographql.apollo3.network.ws.WsProtocol r7 = (com.apollographql.apollo3.network.ws.WsProtocol) r7
            com.apollographql.apollo3.api.ApolloRequest r6 = r6.getRequest()
            r7.startOperation(r6)
            goto L_0x04c1
        L_0x046a:
            boolean r7 = r6 instanceof com.apollographql.apollo3.network.ws.internal.StopOperation
            if (r7 == 0) goto L_0x048a
            com.apollographql.apollo3.network.ws.internal.StopOperation r10 = (com.apollographql.apollo3.network.ws.internal.StopOperation) r10
            com.apollographql.apollo3.api.ApolloRequest r6 = r10.getRequest()
            java.util.UUID r6 = r6.getRequestUuid()
            r2.remove(r6)
            T r6 = r4.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            com.apollographql.apollo3.network.ws.WsProtocol r6 = (com.apollographql.apollo3.network.ws.WsProtocol) r6
            com.apollographql.apollo3.api.ApolloRequest r7 = r10.getRequest()
            r6.stopOperation(r7)
            goto L_0x04c1
        L_0x048a:
            boolean r6 = r6 instanceof com.apollographql.apollo3.network.ws.internal.RestartConnection
            if (r6 == 0) goto L_0x04c1
            kotlinx.coroutines.channels.Channel<com.apollographql.apollo3.network.ws.internal.Message> r6 = r0.messages
            com.apollographql.apollo3.network.ws.internal.ConnectionReEstablished r7 = new com.apollographql.apollo3.network.ws.internal.ConnectionReEstablished
            r7.<init>()
            r1.L$0 = r0
            r1.L$1 = r15
            r1.L$2 = r14
            r1.L$3 = r5
            r1.L$4 = r4
            r1.L$5 = r2
            r8 = 0
            r1.L$6 = r8
            r1.J$0 = r12
            r8 = 9
            r1.label = r8
            java.lang.Object r6 = r6.send(r7, r1)
            if (r6 != r3) goto L_0x04b1
            return r3
        L_0x04b1:
            r10 = r12
            r13 = r14
            r14 = r15
            r15 = r0
            r12 = r5
        L_0x04b6:
            r0 = r14
            r22 = r10
            r10 = r1
            r11 = r4
            r1 = r13
            r13 = r2
            r2 = r15
            r14 = r22
            goto L_0x04cc
        L_0x04c1:
            r10 = r1
            r11 = r4
            r1 = r14
            r22 = r2
            r2 = r0
            r0 = r15
            r14 = r12
            r13 = r22
            r12 = r5
        L_0x04cc:
            boolean r4 = r13.isEmpty()
            if (r4 == 0) goto L_0x04f1
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$4 r19 = new com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$4
            r9 = 0
            r4 = r19
            r5 = r2
            r6 = r11
            r7 = r12
            r8 = r1
            r4.<init>(r5, r6, r7, r8, r9)
            r20 = 3
            r21 = 0
            r17 = 0
            r18 = 0
            r16 = r0
            kotlinx.coroutines.Job r4 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r16, r17, r18, r19, r20, r21)
            r1.element = r4
            r6 = 1
            r9 = 0
            goto L_0x04fe
        L_0x04f1:
            T r4 = r1.element
            kotlinx.coroutines.Job r4 = (kotlinx.coroutines.Job) r4
            r6 = 1
            r9 = 0
            if (r4 == 0) goto L_0x04fc
            kotlinx.coroutines.Job.DefaultImpls.cancel$default((kotlinx.coroutines.Job) r4, (java.util.concurrent.CancellationException) r9, (int) r6, (java.lang.Object) r9)
        L_0x04fc:
            r1.element = r9
        L_0x04fe:
            r4 = r10
            r7 = r11
            r5 = r12
            r8 = r13
            r10 = r14
            goto L_0x01c6
        L_0x0505:
            r0 = r11
            r10 = r12
            goto L_0x01c6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport.supervise(kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void supervise$closeProtocol(Ref.ObjectRef<WsProtocol> objectRef, Ref.ObjectRef<Job> objectRef2, Ref.ObjectRef<Job> objectRef3) {
        WsProtocol wsProtocol = (WsProtocol) objectRef.element;
        if (wsProtocol != null) {
            wsProtocol.close();
        }
        objectRef.element = null;
        Job job = (Job) objectRef2.element;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        objectRef2.element = null;
        Job job2 = (Job) objectRef3.element;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        objectRef3.element = null;
    }

    public final void closeConnection(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "reason");
        this.messages.m10510trySendJP2dKIU(new NetworkError(th));
    }

    public void dispose() {
        this.messages.m10510trySendJP2dKIU(Dispose.INSTANCE);
    }

    @NotNull
    public <D extends Operation.Data> Flow<ApolloResponse<D>> execute(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        DeferredJsonMerger deferredJsonMerger = new DeferredJsonMerger();
        return FlowKt.onCompletion(new WebSocketNetworkTransport$execute$$inlined$filterNot$1(new WebSocketNetworkTransport$execute$$inlined$map$1(FlowsKt.transformWhile(new WebSocketNetworkTransport$execute$$inlined$filter$1(FlowKt.onSubscription(this.events, new WebSocketNetworkTransport$execute$1(this, apolloRequest, (Continuation<? super WebSocketNetworkTransport$execute$1>) null)), apolloRequest), new WebSocketNetworkTransport$execute$3(apolloRequest, (Continuation<? super WebSocketNetworkTransport$execute$3>) null)), apolloRequest, deferredJsonMerger), deferredJsonMerger), new WebSocketNetworkTransport$execute$6(this, apolloRequest, (Continuation<? super WebSocketNetworkTransport$execute$6>) null));
    }

    @NotNull
    public final StateFlow<Integer> getSubscriptionCount() {
        return this.subscriptionCount;
    }

    private WebSocketNetworkTransport(Function1<? super Continuation<? super String>, ? extends Object> function1, List<HttpHeader> list, WebSocketEngine webSocketEngine2, long j2, WsProtocol.Factory factory, Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        this.serverUrl = function1;
        this.headers = list;
        this.webSocketEngine = webSocketEngine2;
        this.idleTimeoutMillis = j2;
        this.protocolFactory = factory;
        this.reopenWhen = function3;
        this.messages = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        MutableSharedFlow<Event> MutableSharedFlow = SharedFlowKt.MutableSharedFlow(0, Integer.MAX_VALUE, BufferOverflow.SUSPEND);
        this.mutableEvents = MutableSharedFlow;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow);
        this.subscriptionCount = MutableSharedFlow.getSubscriptionCount();
        CloseableSingleThreadDispatcher closeableSingleThreadDispatcher = new CloseableSingleThreadDispatcher();
        this.backgroundDispatcher = closeableSingleThreadDispatcher;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(closeableSingleThreadDispatcher.getCoroutineDispatcher());
        this.coroutineScope = CoroutineScope;
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation<? super AnonymousClass1>) null), 3, (Object) null);
        this.listener = new WebSocketNetworkTransport$listener$1(this);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WebSocketNetworkTransport(Function1 function1, List list, WebSocketEngine webSocketEngine2, long j2, WsProtocol.Factory factory, Function3 function3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, list, (i3 & 4) != 0 ? new DefaultWebSocketEngine() : webSocketEngine2, (i3 & 8) != 0 ? 60000 : j2, (i3 & 16) != 0 ? new SubscriptionWsProtocol.Factory(0, (Function1) null, (WsFrameType) null, 7, (DefaultConstructorMarker) null) : factory, function3);
    }
}
