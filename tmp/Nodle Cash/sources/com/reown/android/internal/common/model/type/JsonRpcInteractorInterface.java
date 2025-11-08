package com.reown.android.internal.common.model.type;

import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.WCResponse;
import kotlin.Metadata;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/model/type/JsonRpcInteractorInterface;", "", "clientSyncJsonRpc", "Lkotlinx/coroutines/flow/SharedFlow;", "Lcom/reown/android/internal/common/model/WCRequest;", "getClientSyncJsonRpc", "()Lkotlinx/coroutines/flow/SharedFlow;", "peerResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "getPeerResponse", "internalErrors", "Lcom/reown/android/internal/common/model/SDKError;", "getInternalErrors", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface JsonRpcInteractorInterface {
    @NotNull
    SharedFlow<WCRequest> getClientSyncJsonRpc();

    @NotNull
    SharedFlow<SDKError> getInternalErrors();

    @NotNull
    SharedFlow<WCResponse> getPeerResponse();
}
