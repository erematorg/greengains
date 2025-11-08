package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.utils.Time;
import com.reown.android.pairing.model.PairingRpc;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$onPingSuccess$1", f = "PairingEngine.kt", i = {}, l = {409}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$onPingSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function1<String, Unit> $onSuccess;
    final /* synthetic */ PairingRpc.PairingPing $pingPayload;
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ PairingEngine this$0;

    @SourceDebugExtension({"SMAP\nPairingEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine$onPingSuccess$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,466:1\n17#2:467\n19#2:471\n46#3:468\n51#3:470\n105#4:469\n*S KotlinDebug\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine$onPingSuccess$1$1\n*L\n411#1:467\n411#1:471\n411#1:468\n411#1:470\n411#1:469\n*E\n"})
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$onPingSuccess$1$1", f = "PairingEngine.kt", i = {0}, l = {412}, m = "invokeSuspend", n = {"$this$withTimeout"}, s = {"L$0"})
    /* renamed from: com.reown.android.pairing.engine.domain.PairingEngine$onPingSuccess$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r02 = new AnonymousClass1(pairingEngine, pairingPing, function1, str, function12, continuation);
            r02.L$0 = obj;
            return r02;
        }

        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                PairingEngine$onPingSuccess$1$1$invokeSuspend$$inlined$filter$1 pairingEngine$onPingSuccess$1$1$invokeSuspend$$inlined$filter$1 = new PairingEngine$onPingSuccess$1$1$invokeSuspend$$inlined$filter$1(pairingEngine.jsonRpcInteractor.getPeerResponse(), pairingPing);
                final Function1<String, Unit> function1 = function1;
                final String str = str;
                final Function1<Throwable, Unit> function12 = function12;
                AnonymousClass2 r8 = new FlowCollector() {
                    public final Object emit(WCResponse wCResponse, Continuation<? super Unit> continuation) {
                        JsonRpcResponse response = wCResponse.getResponse();
                        if (response instanceof JsonRpcResponse.JsonRpcResult) {
                            CoroutineScopeKt.cancel$default(coroutineScope, (CancellationException) null, 1, (Object) null);
                            function1.invoke(str);
                        } else if (response instanceof JsonRpcResponse.JsonRpcError) {
                            CoroutineScopeKt.cancel$default(coroutineScope, (CancellationException) null, 1, (Object) null);
                            function12.invoke(new Throwable(((JsonRpcResponse.JsonRpcError) response).getErrorMessage()));
                        } else {
                            throw new NoWhenBranchMatchedException();
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (pairingEngine$onPingSuccess$1$1$invokeSuspend$$inlined$filter$1.collect(r8, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$onPingSuccess$1(Function1<? super Throwable, Unit> function1, PairingEngine pairingEngine, PairingRpc.PairingPing pairingPing, Function1<? super String, Unit> function12, String str, Continuation<? super PairingEngine$onPingSuccess$1> continuation) {
        super(2, continuation);
        this.$onFailure = function1;
        this.this$0 = pairingEngine;
        this.$pingPayload = pairingPing;
        this.$onSuccess = function12;
        this.$topic = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$onPingSuccess$1(this.$onFailure, this.this$0, this.$pingPayload, this.$onSuccess, this.$topic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            long millis = TimeUnit.SECONDS.toMillis(Time.getThirtySeconds());
            final PairingEngine pairingEngine = this.this$0;
            final PairingRpc.PairingPing pairingPing = this.$pingPayload;
            final Function1<String, Unit> function1 = this.$onSuccess;
            final String str = this.$topic;
            final Function1<Throwable, Unit> function12 = this.$onFailure;
            AnonymousClass1 r5 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (TimeoutKt.withTimeout(millis, r5, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (TimeoutCancellationException e3) {
                this.$onFailure.invoke(e3);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PairingEngine$onPingSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
