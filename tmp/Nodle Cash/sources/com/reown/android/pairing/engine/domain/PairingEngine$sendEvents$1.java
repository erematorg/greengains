package com.reown.android.pairing.engine.domain;

import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.SendBatchEventUseCase;
import com.reown.android.pulse.model.EventType;
import com.reown.android.pulse.model.properties.Properties;
import com.reown.android.pulse.model.properties.Props;
import com.reown.foundation.util.Logger;
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
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$sendEvents$1", f = "PairingEngine.kt", i = {}, l = {293}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$sendEvents$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PairingEngine this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$sendEvents$1$1", f = "PairingEngine.kt", i = {}, l = {295, 296}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.pairing.engine.domain.PairingEngine$sendEvents$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(pairingEngine, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                InsertEventUseCase access$getInsertEventUseCase$p = pairingEngine.insertEventUseCase;
                Props props = new Props(EventType.INIT, (String) null, new Properties((String) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, (List) null, (String) null, (Long) null, pairingEngine.clientId, (String) null, pairingEngine.userAgent, 1535, (DefaultConstructorMarker) null), 2, (DefaultConstructorMarker) null);
                this.label = 1;
                if (access$getInsertEventUseCase$p.invoke(props, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else if (i3 == 2) {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e3) {
                    Logger access$getLogger$p = pairingEngine.logger;
                    access$getLogger$p.error("Error when sending events: " + e3);
                }
                return Unit.INSTANCE;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            SendBatchEventUseCase access$getSendBatchEventUseCase$p = pairingEngine.sendBatchEventUseCase;
            this.label = 2;
            if (access$getSendBatchEventUseCase$p.invoke(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$sendEvents$1(PairingEngine pairingEngine, Continuation<? super PairingEngine$sendEvents$1> continuation) {
        super(2, continuation);
        this.this$0 = pairingEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$sendEvents$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final PairingEngine pairingEngine = this.this$0;
            AnonymousClass1 r5 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r5, this) == coroutine_suspended) {
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
        return ((PairingEngine$sendEvents$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
