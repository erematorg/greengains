package com.reown.android.pairing.engine.domain;

import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nPairingEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine$getPairings$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,466:1\n774#2:467\n865#2,2:468\n*S KotlinDebug\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine$getPairings$1\n*L\n270#1:467\n270#1:468,2\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/android/internal/common/model/Pairing;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$getPairings$1", f = "PairingEngine.kt", i = {}, l = {270}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$getPairings$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Pairing>>, Object> {
    int label;
    final /* synthetic */ PairingEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$getPairings$1(PairingEngine pairingEngine, Continuation<? super PairingEngine$getPairings$1> continuation) {
        super(2, continuation);
        this.this$0 = pairingEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$getPairings$1(this.this$0, continuation);
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
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PairingEngine pairingEngine = this.this$0;
        ArrayList arrayList = new ArrayList();
        for (Object next : (Iterable) obj) {
            if (pairingEngine.isNotExpired((Pairing) next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<Pairing>> continuation) {
        return ((PairingEngine$getPairings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
