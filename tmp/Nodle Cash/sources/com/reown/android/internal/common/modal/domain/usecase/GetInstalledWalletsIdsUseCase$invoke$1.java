package com.reown.android.internal.common.modal.domain.usecase;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase", f = "GetAndroidDataUseCaseInterface.kt", i = {0}, l = {14}, m = "invoke", n = {"sdkType"}, s = {"L$0"})
public final class GetInstalledWalletsIdsUseCase$invoke$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GetInstalledWalletsIdsUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetInstalledWalletsIdsUseCase$invoke$1(GetInstalledWalletsIdsUseCase getInstalledWalletsIdsUseCase, Continuation<? super GetInstalledWalletsIdsUseCase$invoke$1> continuation) {
        super(continuation);
        this.this$0 = getInstalledWalletsIdsUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.invoke((String) null, this);
    }
}
