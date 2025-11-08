package com.reown.android.internal.common.modal.domain.usecase;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase", f = "GetWalletsUseCase.kt", i = {0, 0, 0, 0, 0}, l = {25}, m = "invoke", n = {"sdkType", "search", "excludeIds", "includeIds", "page"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
public final class GetWalletsUseCase$invoke$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GetWalletsUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetWalletsUseCase$invoke$1(GetWalletsUseCase getWalletsUseCase, Continuation<? super GetWalletsUseCase$invoke$1> continuation) {
        super(continuation);
        this.this$0 = getWalletsUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.invoke((String) null, 0, (String) null, (List<String>) null, (List<String>) null, this);
    }
}
