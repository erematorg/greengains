package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nPairingEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine$pairingsExpiryWatcher$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,466:1\n2756#2:467\n1#3:468\n*S KotlinDebug\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine$pairingsExpiryWatcher$1\n*L\n333#1:467\n333#1:468\n*E\n"})
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$pairingsExpiryWatcher$1", f = "PairingEngine.kt", i = {}, l = {332}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$pairingsExpiryWatcher$1 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PairingEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$pairingsExpiryWatcher$1(PairingEngine pairingEngine, Continuation<? super PairingEngine$pairingsExpiryWatcher$1> continuation) {
        super(2, continuation);
        this.this$0 = pairingEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$pairingsExpiryWatcher$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            PairingStorageRepositoryInterface access$getPairingRepository$p = this.this$0.pairingRepository;
            this.label = 1;
            obj = access$getPairingRepository$p.getListOfPairings(this);
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
        PairingEngine pairingEngine = this.this$0;
        for (Pairing access$isNotExpired : (Iterable) obj) {
            boolean unused = pairingEngine.isNotExpired(access$isNotExpired);
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
        return ((PairingEngine$pairingsExpiryWatcher$1) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
