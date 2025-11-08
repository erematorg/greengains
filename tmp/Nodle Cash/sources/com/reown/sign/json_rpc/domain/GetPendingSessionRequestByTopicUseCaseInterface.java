package com.reown.sign.json_rpc.domain;

import com.reown.foundation.common.model.Topic;
import com.reown.sign.engine.model.EngineDO;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H¦@¢\u0006\u0002\u0010\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequestByTopicUseCaseInterface;", "", "getPendingSessionRequests", "", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "topic", "Lcom/reown/foundation/common/model/Topic;", "(Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface GetPendingSessionRequestByTopicUseCaseInterface {
    @Nullable
    Object getPendingSessionRequests(@NotNull Topic topic, @NotNull Continuation<? super List<EngineDO.SessionRequest>> continuation);
}
