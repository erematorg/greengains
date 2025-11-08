package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH@¢\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetSessionsUseCase;", "Lcom/reown/sign/engine/use_case/calls/GetSessionsUseCaseInterface;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "selfAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "<init>", "(Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/android/internal/common/model/AppMetaData;)V", "getListOfSettledSessions", "", "Lcom/reown/sign/engine/model/EngineDO$Session;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetSessionsUseCase implements GetSessionsUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final AppMetaData selfAppMetaData;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;

    public GetSessionsUseCase(@NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull AppMetaData appMetaData) {
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataStorageRepository");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(appMetaData, "selfAppMetaData");
        this.metadataStorageRepository = metadataStorageRepositoryInterface;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.selfAppMetaData = appMetaData;
    }

    @Nullable
    public Object getListOfSettledSessions(@NotNull Continuation<? super List<EngineDO.Session>> continuation) {
        return SupervisorKt.supervisorScope(new GetSessionsUseCase$getListOfSettledSessions$2(this, (Continuation<? super GetSessionsUseCase$getListOfSettledSessions$2>) null), continuation);
    }
}
