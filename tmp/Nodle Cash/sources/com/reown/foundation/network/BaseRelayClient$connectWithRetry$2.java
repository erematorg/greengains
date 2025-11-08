package com.reown.foundation.network;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$connectWithRetry$2", f = "BaseRelayClient.kt", i = {0}, l = {471}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
public final class BaseRelayClient$connectWithRetry$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $onConnected;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BaseRelayClient this$0;

    @SourceDebugExtension({"SMAP\nBaseRelayClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient$connectWithRetry$2$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,533:1\n20#2:534\n22#2:538\n50#3:535\n55#3:537\n106#4:536\n*S KotlinDebug\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient$connectWithRetry$2$1\n*L\n475#1:534\n475#1:538\n475#1:535\n475#1:537\n475#1:536\n*E\n"})
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/reown/foundation/network/ConnectionState;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$connectWithRetry$2$1", f = "BaseRelayClient.kt", i = {0}, l = {476}, m = "invokeSuspend", n = {"$this$withTimeout"}, s = {"L$0"})
    /* renamed from: com.reown.foundation.network.BaseRelayClient$connectWithRetry$2$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ConnectionState>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r02 = new AnonymousClass1(baseRelayClient, function1, function0, continuation);
            r02.L$0 = obj;
            return r02;
        }

        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<ConnectionState> take = FlowKt.take(baseRelayClient.getConnectionState$foundation(), 4);
                final BaseRelayClient baseRelayClient = baseRelayClient;
                final Function1<Throwable, Unit> function1 = function1;
                BaseRelayClient$connectWithRetry$2$1$invokeSuspend$$inlined$filter$1 baseRelayClient$connectWithRetry$2$1$invokeSuspend$$inlined$filter$1 = new BaseRelayClient$connectWithRetry$2$1$invokeSuspend$$inlined$filter$1(FlowKt.onEach(take, new Function2<ConnectionState, Continuation<? super Unit>, Object>((Continuation<? super AnonymousClass1>) null) {
                    /* synthetic */ Object L$0;
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 r02 = 

                        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                        public BaseRelayClient$connectWithRetry$2(Function1<? super Throwable, Unit> function1, BaseRelayClient baseRelayClient, Function0<Unit> function0, Continuation<? super BaseRelayClient$connectWithRetry$2> continuation) {
                            super(2, continuation);
                            this.$onFailure = function1;
                            this.this$0 = baseRelayClient;
                            this.$onConnected = function0;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            BaseRelayClient$connectWithRetry$2 baseRelayClient$connectWithRetry$2 = new BaseRelayClient$connectWithRetry$2(this.$onFailure, this.this$0, this.$onConnected, continuation);
                            baseRelayClient$connectWithRetry$2.L$0 = obj;
                            return baseRelayClient$connectWithRetry$2;
                        }

                        public final Object invokeSuspend(Object obj) {
                            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i3 = this.label;
                            if (i3 == 0) {
                                ResultKt.throwOnFailure(obj);
                                final BaseRelayClient baseRelayClient = this.this$0;
                                final Function1<Throwable, Unit> function1 = this.$onFailure;
                                final Function0<Unit> function0 = this.$onConnected;
                                AnonymousClass1 r8 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
                                this.L$0 = coroutineScope;
                                this.label = 1;
                                if (TimeoutKt.withTimeout(15000, r8, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else if (i3 == 1) {
                                try {
                                    ResultKt.throwOnFailure(obj);
                                } catch (TimeoutCancellationException e3) {
                                    this.$onFailure.invoke(e3);
                                    this.this$0.cancelJobIfActive(coroutineScope);
                                } catch (Exception e4) {
                                    if (!(e4 instanceof CancellationException)) {
                                        this.$onFailure.invoke(e4);
                                    }
                                }
                            } else {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }

                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((BaseRelayClient$connectWithRetry$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }
                    }
