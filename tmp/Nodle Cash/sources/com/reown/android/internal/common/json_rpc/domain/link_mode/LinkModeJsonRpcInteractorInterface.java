package com.reown.android.internal.common.json_rpc.domain.link_mode;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.type.JsonRpcClientSync;
import com.reown.android.internal.common.model.type.JsonRpcInteractorInterface;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH&J6\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\tH&¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "Lcom/reown/android/internal/common/model/type/JsonRpcInteractorInterface;", "triggerRequest", "", "payload", "Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "topic", "Lcom/reown/foundation/common/model/Topic;", "appLink", "", "envelopeType", "Lcom/reown/android/internal/common/model/EnvelopeType;", "triggerResponse", "response", "Lcom/reown/android/internal/common/JsonRpcResponse;", "participants", "Lcom/reown/android/internal/common/model/Participants;", "dispatchEnvelope", "url", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LinkModeJsonRpcInteractorInterface extends JsonRpcInteractorInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void triggerRequest$default(LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface, JsonRpcClientSync jsonRpcClientSync, Topic topic, String str, EnvelopeType envelopeType, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 8) != 0) {
                envelopeType = EnvelopeType.ZERO;
            }
            linkModeJsonRpcInteractorInterface.triggerRequest(jsonRpcClientSync, topic, str, envelopeType);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: triggerRequest");
    }

    static /* synthetic */ void triggerResponse$default(LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface, Topic topic, JsonRpcResponse jsonRpcResponse, String str, Participants participants, EnvelopeType envelopeType, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 8) != 0) {
                participants = null;
            }
            Participants participants2 = participants;
            if ((i3 & 16) != 0) {
                envelopeType = EnvelopeType.ZERO;
            }
            linkModeJsonRpcInteractorInterface.triggerResponse(topic, jsonRpcResponse, str, participants2, envelopeType);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: triggerResponse");
    }

    void dispatchEnvelope(@NotNull String str);

    void triggerRequest(@NotNull JsonRpcClientSync<?> jsonRpcClientSync, @NotNull Topic topic, @NotNull String str, @NotNull EnvelopeType envelopeType);

    void triggerResponse(@NotNull Topic topic, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull String str, @Nullable Participants participants, @NotNull EnvelopeType envelopeType);
}
