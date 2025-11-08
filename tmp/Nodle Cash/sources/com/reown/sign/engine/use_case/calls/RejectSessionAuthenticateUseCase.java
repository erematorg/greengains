package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.json_rpc.domain.GetPendingSessionAuthenticateRequest;
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

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J@\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00150\u001cH@¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/RejectSessionAuthenticateUseCase;", "Lcom/reown/sign/engine/use_case/calls/RejectSessionAuthenticateUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "getPendingSessionAuthenticateRequest", "Lcom/reown/sign/json_rpc/domain/GetPendingSessionAuthenticateRequest;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "linkModeJsonRpcInteractor", "Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/json_rpc/domain/GetPendingSessionAuthenticateRequest;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/foundation/util/Logger;)V", "rejectSessionAuthenticate", "", "id", "", "reason", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(JLjava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RejectSessionAuthenticateUseCase implements RejectSessionAuthenticateUseCaseInterface {
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
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final VerifyContextStorageRepository verifyContextStorageRepository;

    public RejectSessionAuthenticateUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull GetPendingSessionAuthenticateRequest getPendingSessionAuthenticateRequest2, @NotNull KeyManagementRepository keyManagementRepository, @NotNull VerifyContextStorageRepository verifyContextStorageRepository2, @NotNull LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(getPendingSessionAuthenticateRequest2, "getPendingSessionAuthenticateRequest");
        Intrinsics.checkNotNullParameter(keyManagementRepository, "crypto");
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository2, "verifyContextStorageRepository");
        Intrinsics.checkNotNullParameter(linkModeJsonRpcInteractorInterface, "linkModeJsonRpcInteractor");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.getPendingSessionAuthenticateRequest = getPendingSessionAuthenticateRequest2;
        this.crypto = keyManagementRepository;
        this.verifyContextStorageRepository = verifyContextStorageRepository2;
        this.linkModeJsonRpcInteractor = linkModeJsonRpcInteractorInterface;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.logger = logger2;
    }

    @Nullable
    public Object rejectSessionAuthenticate(long j2, @NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2(this, j2, function1, str, function0, (Continuation<? super RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
