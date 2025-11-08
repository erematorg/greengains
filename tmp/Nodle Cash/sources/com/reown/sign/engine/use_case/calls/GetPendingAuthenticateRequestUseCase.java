package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.json_rpc.model.JsonRpcMapperKt;
import com.reown.sign.json_rpc.model.JsonRpcMethod;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH@¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetPendingAuthenticateRequestUseCase;", "Lcom/reown/sign/engine/use_case/calls/GetPendingAuthenticateRequestUseCaseInterface;", "jsonRpcHistory", "Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "serializer", "Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "<init>", "(Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;)V", "getPendingAuthenticateRequests", "", "Lcom/reown/sign/common/model/Request;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetPendingAuthenticateRequestUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPendingAuthenticateRequestUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetPendingAuthenticateRequestUseCase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,24:1\n774#2:25\n865#2,2:26\n1617#2,9:28\n1869#2:37\n1870#2:40\n1626#2:41\n56#3:38\n1#4:39\n*S KotlinDebug\n*F\n+ 1 GetPendingAuthenticateRequestUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetPendingAuthenticateRequestUseCase\n*L\n17#1:25\n17#1:26,2\n18#1:28,9\n18#1:37\n18#1:40\n18#1:41\n18#1:38\n18#1:39\n*E\n"})
public final class GetPendingAuthenticateRequestUseCase implements GetPendingAuthenticateRequestUseCaseInterface {
    @NotNull
    private final JsonRpcHistory jsonRpcHistory;
    @NotNull
    private final JsonRpcSerializer serializer;

    public GetPendingAuthenticateRequestUseCase(@NotNull JsonRpcHistory jsonRpcHistory2, @NotNull JsonRpcSerializer jsonRpcSerializer) {
        Intrinsics.checkNotNullParameter(jsonRpcHistory2, "jsonRpcHistory");
        Intrinsics.checkNotNullParameter(jsonRpcSerializer, "serializer");
        this.jsonRpcHistory = jsonRpcHistory2;
        this.serializer = jsonRpcSerializer;
    }

    @Nullable
    public Object getPendingAuthenticateRequests(@NotNull Continuation<? super List<Request<SignParams.SessionAuthenticateParams>>> continuation) {
        Object obj;
        ArrayList<JsonRpcHistoryRecord> arrayList = new ArrayList<>();
        for (Object next : this.jsonRpcHistory.getListOfPendingRecords()) {
            if (Intrinsics.areEqual((Object) ((JsonRpcHistoryRecord) next).getMethod(), (Object) JsonRpcMethod.WC_SESSION_AUTHENTICATE)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (JsonRpcHistoryRecord jsonRpcHistoryRecord : arrayList) {
            JsonRpcSerializer jsonRpcSerializer = this.serializer;
            String body = jsonRpcHistoryRecord.getBody();
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.m8979constructorimpl(jsonRpcSerializer.getMoshi().adapter(SignRpc.SessionAuthenticate.class).fromJson(body));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
            }
            Request request = null;
            if (Result.m8985isFailureimpl(obj)) {
                obj = null;
            }
            SignRpc.SessionAuthenticate sessionAuthenticate = (SignRpc.SessionAuthenticate) obj;
            if (sessionAuthenticate != null) {
                request = JsonRpcMapperKt.toRequest(sessionAuthenticate, jsonRpcHistoryRecord);
            }
            if (request != null) {
                arrayList2.add(request);
            }
        }
        return arrayList2;
    }
}
