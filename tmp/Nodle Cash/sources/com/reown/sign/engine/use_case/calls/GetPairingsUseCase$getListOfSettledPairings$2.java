package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.pairing.model.mapper.PairingMapperKt;
import com.reown.sign.engine.model.EngineDO;
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

@SourceDebugExtension({"SMAP\nGetPairingsUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPairingsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetPairingsUseCase$getListOfSettledPairings$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,20:1\n1563#2:21\n1634#2,3:22\n*S KotlinDebug\n*F\n+ 1 GetPairingsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetPairingsUseCase$getListOfSettledPairings$2\n*L\n11#1:21\n11#1:22,3\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/engine/model/EngineDO$PairingSettle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.GetPairingsUseCase$getListOfSettledPairings$2", f = "GetPairingsUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class GetPairingsUseCase$getListOfSettledPairings$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EngineDO.PairingSettle>>, Object> {
    int label;
    final /* synthetic */ GetPairingsUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetPairingsUseCase$getListOfSettledPairings$2(GetPairingsUseCase getPairingsUseCase, Continuation<? super GetPairingsUseCase$getListOfSettledPairings$2> continuation) {
        super(2, continuation);
        this.this$0 = getPairingsUseCase;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GetPairingsUseCase$getListOfSettledPairings$2(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Iterable<Core.Model.Pairing> pairings = this.this$0.pairingInterface.getPairings();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(pairings, 10));
            for (Core.Model.Pairing pairing : pairings) {
                Pairing pairing2 = PairingMapperKt.toPairing(pairing);
                arrayList.add(new EngineDO.PairingSettle(pairing2.getTopic(), pairing2.getPeerAppMetaData()));
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<EngineDO.PairingSettle>> continuation) {
        return ((GetPairingsUseCase$getListOfSettledPairings$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
