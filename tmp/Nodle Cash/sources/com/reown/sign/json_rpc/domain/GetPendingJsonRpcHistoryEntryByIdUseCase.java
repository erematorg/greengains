package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.json_rpc.model.JsonRpcMapperKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reown/sign/json_rpc/domain/GetPendingJsonRpcHistoryEntryByIdUseCase;", "", "jsonRpcHistory", "Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "serializer", "Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "<init>", "(Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;)V", "invoke", "Lcom/reown/sign/common/model/Request;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "id", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetPendingJsonRpcHistoryEntryByIdUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPendingJsonRpcHistoryEntryByIdUseCase.kt\ncom/reown/sign/json_rpc/domain/GetPendingJsonRpcHistoryEntryByIdUseCase\n+ 2 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,29:1\n56#2:30\n1#3:31\n*S KotlinDebug\n*F\n+ 1 GetPendingJsonRpcHistoryEntryByIdUseCase.kt\ncom/reown/sign/json_rpc/domain/GetPendingJsonRpcHistoryEntryByIdUseCase\n*L\n21#1:30\n21#1:31\n*E\n"})
public final class GetPendingJsonRpcHistoryEntryByIdUseCase {
    @NotNull
    private final JsonRpcHistory jsonRpcHistory;
    @NotNull
    private final JsonRpcSerializer serializer;

    public GetPendingJsonRpcHistoryEntryByIdUseCase(@NotNull JsonRpcHistory jsonRpcHistory2, @NotNull JsonRpcSerializer jsonRpcSerializer) {
        Intrinsics.checkNotNullParameter(jsonRpcHistory2, "jsonRpcHistory");
        Intrinsics.checkNotNullParameter(jsonRpcSerializer, "serializer");
        this.jsonRpcHistory = jsonRpcHistory2;
        this.serializer = jsonRpcSerializer;
    }

    @Nullable
    public final Request<SignParams.SessionRequestParams> invoke(long j2) {
        Object obj;
        JsonRpcHistoryRecord pendingRecordById = this.jsonRpcHistory.getPendingRecordById(j2);
        if (pendingRecordById == null) {
            return null;
        }
        JsonRpcSerializer jsonRpcSerializer = this.serializer;
        String body = pendingRecordById.getBody();
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m8979constructorimpl(jsonRpcSerializer.getMoshi().adapter(SignRpc.SessionRequest.class).fromJson(body));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8985isFailureimpl(obj)) {
            obj = null;
        }
        SignRpc.SessionRequest sessionRequest = (SignRpc.SessionRequest) obj;
        if (sessionRequest != null) {
            return JsonRpcMapperKt.toRequest(pendingRecordById, sessionRequest.getParams());
        }
        return null;
    }
}
