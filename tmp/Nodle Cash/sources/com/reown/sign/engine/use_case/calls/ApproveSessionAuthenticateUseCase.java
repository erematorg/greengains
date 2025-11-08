package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.common.signing.cacao.CacaoVerifier;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.json_rpc.domain.GetPendingSessionAuthenticateRequest;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001dJF\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001f0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u001f0(H@¢\u0006\u0002\u0010*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/ApproveSessionAuthenticateUseCase;", "Lcom/reown/sign/engine/use_case/calls/ApproveSessionAuthenticateUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "getPendingSessionAuthenticateRequest", "Lcom/reown/sign/json_rpc/domain/GetPendingSessionAuthenticateRequest;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "cacaoVerifier", "Lcom/reown/android/internal/common/signing/cacao/CacaoVerifier;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "selfAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "insertTelemetryEventUseCase", "Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "linkModeJsonRpcInteractor", "Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/json_rpc/domain/GetPendingSessionAuthenticateRequest;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/android/internal/common/signing/cacao/CacaoVerifier;Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;Lcom/reown/foundation/util/Logger;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/android/internal/common/model/AppMetaData;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;)V", "approveSessionAuthenticate", "", "id", "", "cacaos", "", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(JLjava/util/List;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApproveSessionAuthenticateUseCase implements ApproveSessionAuthenticateUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final CacaoVerifier cacaoVerifier;
    /* access modifiers changed from: private */
    @NotNull
    public final String clientId;
    /* access modifiers changed from: private */
    @NotNull
    public final KeyManagementRepository crypto;
    /* access modifiers changed from: private */
    @NotNull
    public final GetPendingSessionAuthenticateRequest getPendingSessionAuthenticateRequest;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertEventUseCase insertEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertTelemetryEventUseCase insertTelemetryEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final AppMetaData selfAppMetaData;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final VerifyContextStorageRepository verifyContextStorageRepository;

    public ApproveSessionAuthenticateUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull GetPendingSessionAuthenticateRequest getPendingSessionAuthenticateRequest2, @NotNull KeyManagementRepository keyManagementRepository, @NotNull CacaoVerifier cacaoVerifier2, @NotNull VerifyContextStorageRepository verifyContextStorageRepository2, @NotNull Logger logger2, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull AppMetaData appMetaData, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull InsertTelemetryEventUseCase insertTelemetryEventUseCase2, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(getPendingSessionAuthenticateRequest2, "getPendingSessionAuthenticateRequest");
        Intrinsics.checkNotNullParameter(keyManagementRepository, "crypto");
        Intrinsics.checkNotNullParameter(cacaoVerifier2, "cacaoVerifier");
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository2, "verifyContextStorageRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataStorageRepository");
        Intrinsics.checkNotNullParameter(appMetaData, "selfAppMetaData");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(insertTelemetryEventUseCase2, "insertTelemetryEventUseCase");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(linkModeJsonRpcInteractorInterface, "linkModeJsonRpcInteractor");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.getPendingSessionAuthenticateRequest = getPendingSessionAuthenticateRequest2;
        this.crypto = keyManagementRepository;
        this.cacaoVerifier = cacaoVerifier2;
        this.verifyContextStorageRepository = verifyContextStorageRepository2;
        this.logger = logger2;
        this.metadataStorageRepository = metadataStorageRepositoryInterface;
        this.selfAppMetaData = appMetaData;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.insertTelemetryEventUseCase = insertTelemetryEventUseCase2;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.linkModeJsonRpcInteractor = linkModeJsonRpcInteractorInterface;
    }

    @Nullable
    public Object approveSessionAuthenticate(long j2, @NotNull List<Cacao> list, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2(this, j2, function1, list, function0, (Continuation<? super ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
