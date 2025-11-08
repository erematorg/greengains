package com.reown.sign.json_rpc.domain;

import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.Request;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\b¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/reown/sign/json_rpc/domain/GetPendingRequestsUseCaseByTopicInterface;", "", "getPendingRequests", "", "Lcom/reown/sign/common/model/Request;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "(Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface GetPendingRequestsUseCaseByTopicInterface {
    @Nullable
    Object getPendingRequests(@NotNull Topic topic, @NotNull Continuation<? super List<Request<String>>> continuation);
}
