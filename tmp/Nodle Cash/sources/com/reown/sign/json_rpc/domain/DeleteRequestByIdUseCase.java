package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bHB¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reown/sign/json_rpc/domain/DeleteRequestByIdUseCase;", "", "jsonRpcHistory", "Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "<init>", "(Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;)V", "invoke", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DeleteRequestByIdUseCase {
    /* access modifiers changed from: private */
    @NotNull
    public final JsonRpcHistory jsonRpcHistory;
    /* access modifiers changed from: private */
    @NotNull
    public final VerifyContextStorageRepository verifyContextStorageRepository;

    public DeleteRequestByIdUseCase(@NotNull JsonRpcHistory jsonRpcHistory2, @NotNull VerifyContextStorageRepository verifyContextStorageRepository2) {
        Intrinsics.checkNotNullParameter(jsonRpcHistory2, "jsonRpcHistory");
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository2, "verifyContextStorageRepository");
        this.jsonRpcHistory = jsonRpcHistory2;
        this.verifyContextStorageRepository = verifyContextStorageRepository2;
    }

    @Nullable
    public final Object invoke(long j2, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new DeleteRequestByIdUseCase$invoke$2(this, j2, (Continuation<? super DeleteRequestByIdUseCase$invoke$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
