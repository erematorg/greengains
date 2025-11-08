package com.reown.android.internal.common.json_rpc.model;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.json_rpc.domain.relay.Subscription;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.sync.ClientJsonRpc;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0000\u001a$\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¨\u0006\u0011"}, d2 = {"toWCResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "Lcom/reown/android/internal/common/json_rpc/model/JsonRpcHistoryRecord;", "result", "Lcom/reown/android/internal/common/JsonRpcResponse;", "params", "Lcom/reown/android/internal/common/model/type/ClientParams;", "toRelay", "Lcom/reown/foundation/network/model/Relay$Model$IrnParams;", "Lcom/reown/android/internal/common/model/IrnParams;", "toWCRequest", "Lcom/reown/android/internal/common/model/WCRequest;", "Lcom/reown/android/internal/common/json_rpc/domain/relay/Subscription;", "clientJsonRpc", "Lcom/reown/android/internal/common/model/sync/ClientJsonRpc;", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class JsonRpcMapperKt {
    @NotNull
    public static final WCRequest toWCRequest(@NotNull Subscription subscription, @NotNull ClientJsonRpc clientJsonRpc, @NotNull ClientParams clientParams, @NotNull TransportType transportType) {
        Subscription subscription2 = subscription;
        Intrinsics.checkNotNullParameter(subscription, "<this>");
        ClientJsonRpc clientJsonRpc2 = clientJsonRpc;
        Intrinsics.checkNotNullParameter(clientJsonRpc, "clientJsonRpc");
        ClientParams clientParams2 = clientParams;
        Intrinsics.checkNotNullParameter(clientParams2, "params");
        TransportType transportType2 = transportType;
        Intrinsics.checkNotNullParameter(transportType2, "transportType");
        return new WCRequest(subscription.getTopic(), clientJsonRpc.getId(), clientJsonRpc.getMethod(), clientParams2, subscription.getDecryptedMessage(), subscription.getPublishedAt(), subscription.getEncryptedMessage(), subscription.getAttestation(), transportType2);
    }

    public static final /* synthetic */ WCResponse toWCResponse(JsonRpcHistoryRecord jsonRpcHistoryRecord, JsonRpcResponse jsonRpcResponse, ClientParams clientParams) {
        Intrinsics.checkNotNullParameter(jsonRpcHistoryRecord, "<this>");
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "result");
        Intrinsics.checkNotNullParameter(clientParams, "params");
        return new WCResponse(new Topic(jsonRpcHistoryRecord.getTopic()), jsonRpcHistoryRecord.getMethod(), jsonRpcResponse, clientParams);
    }
}
