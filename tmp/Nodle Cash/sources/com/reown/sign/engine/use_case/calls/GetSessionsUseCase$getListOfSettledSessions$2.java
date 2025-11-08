package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.TransportType;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import com.reown.utils.UtilFunctionsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

@SourceDebugExtension({"SMAP\nGetSessionsUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetSessionsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetSessionsUseCase$getListOfSettledSessions$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,28:1\n774#2:29\n865#2,2:30\n1563#2:32\n1634#2,3:33\n1563#2:36\n1634#2,3:37\n*S KotlinDebug\n*F\n+ 1 GetSessionsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetSessionsUseCase$getListOfSettledSessions$2\n*L\n20#1:29\n20#1:30,2\n21#1:32\n21#1:33,3\n22#1:36\n22#1:37,3\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/engine/model/EngineDO$Session;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.GetSessionsUseCase$getListOfSettledSessions$2", f = "GetSessionsUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class GetSessionsUseCase$getListOfSettledSessions$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EngineDO.Session>>, Object> {
    int label;
    final /* synthetic */ GetSessionsUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetSessionsUseCase$getListOfSettledSessions$2(GetSessionsUseCase getSessionsUseCase, Continuation<? super GetSessionsUseCase$getListOfSettledSessions$2> continuation) {
        super(2, continuation);
        this.this$0 = getSessionsUseCase;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GetSessionsUseCase$getListOfSettledSessions$2(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList();
            for (Object next : this.this$0.sessionStorageRepository.getListOfSessionVOsWithoutMetadata()) {
                SessionVO sessionVO = (SessionVO) next;
                if (sessionVO.isAcknowledged() && UtilFunctionsKt.isSequenceValid(sessionVO.getExpiry())) {
                    arrayList.add(next);
                }
            }
            GetSessionsUseCase getSessionsUseCase = this.this$0;
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                SessionVO sessionVO2 = (SessionVO) it.next();
                arrayList2.add(SessionVO.m8871copypMwxKLQ$default(sessionVO2, (Topic) null, (Expiry) null, (String) null, (String) null, (String) null, (String) null, getSessionsUseCase.selfAppMetaData, (String) null, getSessionsUseCase.metadataStorageRepository.getByTopicAndType(sessionVO2.getTopic(), AppMetaDataType.PEER), (Map) null, (Map) null, (Map) null, (Map) null, (Map) null, false, (String) null, (TransportType) null, 130751, (Object) null));
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(EngineMapperKt.toEngineDO((SessionVO) it2.next()));
            }
            return arrayList3;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<EngineDO.Session>> continuation) {
        return ((GetSessionsUseCase$getListOfSettledSessions$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
