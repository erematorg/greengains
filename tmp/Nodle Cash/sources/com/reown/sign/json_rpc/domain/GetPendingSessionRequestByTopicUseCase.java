package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.engine.model.EngineDO;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequestByTopicUseCase;", "Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequestByTopicUseCaseInterface;", "jsonRpcHistory", "Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "serializer", "Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "<init>", "(Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;)V", "getPendingSessionRequests", "", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "topic", "Lcom/reown/foundation/common/model/Topic;", "(Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetPendingSessionRequestByTopicUseCase implements GetPendingSessionRequestByTopicUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final JsonRpcHistory jsonRpcHistory;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final JsonRpcSerializer serializer;

    public GetPendingSessionRequestByTopicUseCase(@NotNull JsonRpcHistory jsonRpcHistory2, @NotNull JsonRpcSerializer jsonRpcSerializer, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface) {
        Intrinsics.checkNotNullParameter(jsonRpcHistory2, "jsonRpcHistory");
        Intrinsics.checkNotNullParameter(jsonRpcSerializer, "serializer");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataStorageRepository");
        this.jsonRpcHistory = jsonRpcHistory2;
        this.serializer = jsonRpcSerializer;
        this.metadataStorageRepository = metadataStorageRepositoryInterface;
    }

    @Nullable
    public Object getPendingSessionRequests(@NotNull Topic topic, @NotNull Continuation<? super List<EngineDO.SessionRequest>> continuation) {
        return SupervisorKt.supervisorScope(new GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2(this, topic, (Continuation<? super GetPendingSessionRequestByTopicUseCase$getPendingSessionRequests$2>) null), continuation);
    }
}
