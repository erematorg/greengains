package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import java.util.ArrayList;
import java.util.Iterator;
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

@SourceDebugExtension({"SMAP\nGetSessionProposalsUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetSessionProposalsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetSessionProposalsUseCase$getSessionProposals$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,19:1\n774#2:20\n865#2:21\n866#2:23\n1563#2:24\n1634#2,3:25\n1#3:22\n*S KotlinDebug\n*F\n+ 1 GetSessionProposalsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetSessionProposalsUseCase$getSessionProposals$2\n*L\n13#1:20\n13#1:21\n13#1:23\n13#1:24\n13#1:25,3\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.GetSessionProposalsUseCase$getSessionProposals$2", f = "GetSessionProposalsUseCase.kt", i = {}, l = {13}, m = "invokeSuspend", n = {}, s = {})
public final class GetSessionProposalsUseCase$getSessionProposals$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EngineDO.SessionProposal>>, Object> {
    int label;
    final /* synthetic */ GetSessionProposalsUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetSessionProposalsUseCase$getSessionProposals$2(GetSessionProposalsUseCase getSessionProposalsUseCase, Continuation<? super GetSessionProposalsUseCase$getSessionProposals$2> continuation) {
        super(2, continuation);
        this.this$0 = getSessionProposalsUseCase;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GetSessionProposalsUseCase$getSessionProposals$2(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ProposalStorageRepository access$getProposalStorageRepository$p = this.this$0.proposalStorageRepository;
            this.label = 1;
            obj = access$getProposalStorageRepository$p.getProposals$sign_release(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : (Iterable) obj) {
            Expiry expiry = ((ProposalVO) next).getExpiry();
            if (expiry != null ? !CoreValidator.INSTANCE.isExpired(expiry) : true) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(EngineMapperKt.toEngineDO((ProposalVO) it.next()));
        }
        return arrayList2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<EngineDO.SessionProposal>> continuation) {
        return ((GetSessionProposalsUseCase$getSessionProposals$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
