package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.sign.common.model.Request;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tHB¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequests;", "", "jsonRpcHistory", "Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "serializer", "Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "<init>", "(Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;)V", "invoke", "", "Lcom/reown/sign/common/model/Request;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetPendingSessionRequests {
    /* access modifiers changed from: private */
    @NotNull
    public final JsonRpcHistory jsonRpcHistory;
    /* access modifiers changed from: private */
    @NotNull
    public final JsonRpcSerializer serializer;

    public GetPendingSessionRequests(@NotNull JsonRpcHistory jsonRpcHistory2, @NotNull JsonRpcSerializer jsonRpcSerializer) {
        Intrinsics.checkNotNullParameter(jsonRpcHistory2, "jsonRpcHistory");
        Intrinsics.checkNotNullParameter(jsonRpcSerializer, "serializer");
        this.jsonRpcHistory = jsonRpcHistory2;
        this.serializer = jsonRpcSerializer;
    }

    @Nullable
    public final Object invoke(@NotNull Continuation<? super List<Request<String>>> continuation) {
        return SupervisorKt.supervisorScope(new GetPendingSessionRequests$invoke$2(this, (Continuation<? super GetPendingSessionRequests$invoke$2>) null), continuation);
    }
}
