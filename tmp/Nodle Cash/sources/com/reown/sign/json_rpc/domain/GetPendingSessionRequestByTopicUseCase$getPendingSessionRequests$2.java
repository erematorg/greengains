package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.json_rpc.model.JsonRpcMapperKt;
import com.reown.sign.json_rpc.model.JsonRpcMethod;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nGetPendingSessionRequestByTopicUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPendingSessionRequestByTopicUseCase.kt\ncom/reown/sign/json_rpc/domain/GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,33:1\n774#2:34\n865#2,2:35\n1617#2,9:37\n1869#2:46\n1870#2:50\n1626#2:51\n56#3:47\n1#4:48\n1#4:49\n*S KotlinDebug\n*F\n+ 1 GetPendingSessionRequestByTopicUseCase.kt\ncom/reown/sign/json_rpc/domain/GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2\n*L\n23#1:34\n23#1:35,2\n24#1:37,9\n24#1:46\n24#1:50\n24#1:51\n25#1:47\n25#1:48\n24#1:49\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.json_rpc.domain.GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2", f = "GetPendingSessionRequestByTopicUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EngineDO.SessionRequest>>, Object> {
    final /* synthetic */ Topic $topic;
    int label;
    final /* synthetic */ GetPendingSessionRequestByTopicUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2(GetPendingSessionRequestByTopicUseCase getPendingSessionRequestByTopicUseCase, Topic topic, Continuation<? super GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2> continuation) {
        super(2, continuation);
        this.this$0 = getPendingSessionRequestByTopicUseCase;
        this.$topic = topic;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2(this.this$0, this.$topic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Request request;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList<JsonRpcHistoryRecord> arrayList = new ArrayList<>();
            for (Object next : this.this$0.jsonRpcHistory.getListOfPendingRecordsByTopic(this.$topic)) {
                if (Intrinsics.areEqual((Object) ((JsonRpcHistoryRecord) next).getMethod(), (Object) JsonRpcMethod.WC_SESSION_REQUEST)) {
                    arrayList.add(next);
                }
            }
            GetPendingSessionRequestByTopicUseCase getPendingSessionRequestByTopicUseCase = this.this$0;
            ArrayList arrayList2 = new ArrayList();
            for (JsonRpcHistoryRecord jsonRpcHistoryRecord : arrayList) {
                JsonRpcSerializer access$getSerializer$p = getPendingSessionRequestByTopicUseCase.serializer;
                String body = jsonRpcHistoryRecord.getBody();
                try {
                    Result.Companion companion = Result.Companion;
                    obj2 = Result.m8979constructorimpl(access$getSerializer$p.getMoshi().adapter(SignRpc.SessionRequest.class).fromJson(body));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
                }
                EngineDO.SessionRequest sessionRequest = null;
                if (Result.m8985isFailureimpl(obj2)) {
                    obj2 = null;
                }
                SignRpc.SessionRequest sessionRequest2 = (SignRpc.SessionRequest) obj2;
                if (!(sessionRequest2 == null || (request = JsonRpcMapperKt.toRequest(sessionRequest2, jsonRpcHistoryRecord)) == null)) {
                    sessionRequest = Intrinsics.checkNotNullParameter(request, "<this>");
                }
                if (sessionRequest != null) {
                    arrayList2.add(sessionRequest);
                }
            }
            return arrayList2;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<EngineDO.SessionRequest>> continuation) {
        return ((GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
