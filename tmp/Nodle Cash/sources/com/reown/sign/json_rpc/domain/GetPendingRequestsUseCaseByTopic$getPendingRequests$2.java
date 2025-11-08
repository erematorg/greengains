package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
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

@SourceDebugExtension({"SMAP\nGetPendingRequestsByTopicUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPendingRequestsByTopicUseCase.kt\ncom/reown/sign/json_rpc/domain/GetPendingRequestsUseCaseByTopic$getPendingRequests$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,26:1\n774#2:27\n865#2,2:28\n1617#2,9:30\n1869#2:39\n1870#2:42\n1626#2:43\n56#3:40\n1#4:41\n*S KotlinDebug\n*F\n+ 1 GetPendingRequestsByTopicUseCase.kt\ncom/reown/sign/json_rpc/domain/GetPendingRequestsUseCaseByTopic$getPendingRequests$2\n*L\n19#1:27\n19#1:28,2\n20#1:30,9\n20#1:39\n20#1:42\n20#1:43\n20#1:40\n20#1:41\n*E\n"})
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/common/model/Request;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.json_rpc.domain.GetPendingRequestsUseCaseByTopic$getPendingRequests$2", f = "GetPendingRequestsByTopicUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class GetPendingRequestsUseCaseByTopic$getPendingRequests$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Request<String>>>, Object> {
    final /* synthetic */ Topic $topic;
    int label;
    final /* synthetic */ GetPendingRequestsUseCaseByTopic this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetPendingRequestsUseCaseByTopic$getPendingRequests$2(GetPendingRequestsUseCaseByTopic getPendingRequestsUseCaseByTopic, Topic topic, Continuation<? super GetPendingRequestsUseCaseByTopic$getPendingRequests$2> continuation) {
        super(2, continuation);
        this.this$0 = getPendingRequestsUseCaseByTopic;
        this.$topic = topic;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GetPendingRequestsUseCaseByTopic$getPendingRequests$2(this.this$0, this.$topic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList<JsonRpcHistoryRecord> arrayList = new ArrayList<>();
            for (Object next : this.this$0.jsonRpcHistory.getListOfPendingRecordsByTopic(this.$topic)) {
                if (Intrinsics.areEqual((Object) ((JsonRpcHistoryRecord) next).getMethod(), (Object) JsonRpcMethod.WC_SESSION_REQUEST)) {
                    arrayList.add(next);
                }
            }
            GetPendingRequestsUseCaseByTopic getPendingRequestsUseCaseByTopic = this.this$0;
            ArrayList arrayList2 = new ArrayList();
            for (JsonRpcHistoryRecord jsonRpcHistoryRecord : arrayList) {
                JsonRpcSerializer access$getSerializer$p = getPendingRequestsUseCaseByTopic.serializer;
                String body = jsonRpcHistoryRecord.getBody();
                try {
                    Result.Companion companion = Result.Companion;
                    obj2 = Result.m8979constructorimpl(access$getSerializer$p.getMoshi().adapter(SignRpc.SessionRequest.class).fromJson(body));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
                }
                Request request = null;
                if (Result.m8985isFailureimpl(obj2)) {
                    obj2 = null;
                }
                SignRpc.SessionRequest sessionRequest = (SignRpc.SessionRequest) obj2;
                if (sessionRequest != null) {
                    request = JsonRpcMapperKt.toRequest(sessionRequest, jsonRpcHistoryRecord);
                }
                if (request != null) {
                    arrayList2.add(request);
                }
            }
            return arrayList2;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<Request<String>>> continuation) {
        return ((GetPendingRequestsUseCaseByTopic$getPendingRequests$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
