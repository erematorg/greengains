package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.json_rpc.model.JsonRpcMapperKt;
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
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nGetPendingSessionRequests.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPendingSessionRequests.kt\ncom/reown/sign/json_rpc/domain/GetPendingSessionRequests$invoke$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,20:1\n1617#2,9:21\n1869#2:30\n1870#2:33\n1626#2:34\n56#3:31\n1#4:32\n*S KotlinDebug\n*F\n+ 1 GetPendingSessionRequests.kt\ncom/reown/sign/json_rpc/domain/GetPendingSessionRequests$invoke$2\n*L\n18#1:21,9\n18#1:30\n18#1:33\n18#1:34\n18#1:31\n18#1:32\n*E\n"})
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/common/model/Request;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.json_rpc.domain.GetPendingSessionRequests$invoke$2", f = "GetPendingSessionRequests.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class GetPendingSessionRequests$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Request<String>>>, Object> {
    int label;
    final /* synthetic */ GetPendingSessionRequests this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetPendingSessionRequests$invoke$2(GetPendingSessionRequests getPendingSessionRequests, Continuation<? super GetPendingSessionRequests$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = getPendingSessionRequests;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GetPendingSessionRequests$invoke$2(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            GetPendingSessionRequests getPendingSessionRequests = this.this$0;
            ArrayList arrayList = new ArrayList();
            for (JsonRpcHistoryRecord jsonRpcHistoryRecord : this.this$0.jsonRpcHistory.getListOfPendingSessionRequests()) {
                JsonRpcSerializer access$getSerializer$p = getPendingSessionRequests.serializer;
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
                    arrayList.add(request);
                }
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<Request<String>>> continuation) {
        return ((GetPendingSessionRequests$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
