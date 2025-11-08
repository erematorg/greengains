package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$isPairingStateWatcher$1", f = "PairingEngine.kt", i = {}, l = {344}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$isPairingStateWatcher$1 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PairingEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$isPairingStateWatcher$1(PairingEngine pairingEngine, Continuation<? super PairingEngine$isPairingStateWatcher$1> continuation) {
        super(2, continuation);
        this.this$0 = pairingEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$isPairingStateWatcher$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            PairingStorageRepositoryInterface access$getPairingRepository$p = this.this$0.pairingRepository;
            this.label = 1;
            obj = access$getPairingRepository$p.getListOfPairingsWithoutRequestReceived(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                this.this$0.logger.error((Throwable) e3);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (!((List) obj).isEmpty()) {
            this.this$0._isPairingStateFlow.compareAndSet(Boxing.boxBoolean(false), Boxing.boxBoolean(true));
        } else {
            this.this$0._isPairingStateFlow.compareAndSet(Boxing.boxBoolean(true), Boxing.boxBoolean(false));
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
        return ((PairingEngine$isPairingStateWatcher$1) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
