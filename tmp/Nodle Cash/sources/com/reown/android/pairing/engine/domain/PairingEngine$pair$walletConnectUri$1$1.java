package com.reown.android.pairing.engine.domain;

import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.pulse.model.EventType;
import com.reown.android.pulse.model.properties.Properties;
import com.reown.android.pulse.model.properties.Props;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$pair$walletConnectUri$1$1", f = "PairingEngine.kt", i = {}, l = {159}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$pair$walletConnectUri$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PairingEngine $this_run;
    final /* synthetic */ List<String> $trace;
    int label;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$pair$walletConnectUri$1$1$1", f = "PairingEngine.kt", i = {}, l = {159}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.pairing.engine.domain.PairingEngine$pair$walletConnectUri$1$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(pairingEngine, list, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                InsertTelemetryEventUseCase access$getInsertTelemetryEventUseCase$p = pairingEngine.insertTelemetryEventUseCase;
                Props props = new Props((String) null, EventType.Error.MALFORMED_PAIRING_URI, new Properties((String) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, list, (String) null, (Long) null, (String) null, (String) null, (String) null, 4031, (DefaultConstructorMarker) null), 1, (DefaultConstructorMarker) null);
                this.label = 1;
                if (access$getInsertTelemetryEventUseCase$p.invoke(props, this) == coroutine_suspended) {
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
    public PairingEngine$pair$walletConnectUri$1$1(PairingEngine pairingEngine, List<String> list, Continuation<? super PairingEngine$pair$walletConnectUri$1$1> continuation) {
        super(2, continuation);
        this.$this_run = pairingEngine;
        this.$trace = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$pair$walletConnectUri$1$1(this.$this_run, this.$trace, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final PairingEngine pairingEngine = this.$this_run;
            final List<String> list = this.$trace;
            AnonymousClass1 r6 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r6, this) == coroutine_suspended) {
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
        return ((PairingEngine$pair$walletConnectUri$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
