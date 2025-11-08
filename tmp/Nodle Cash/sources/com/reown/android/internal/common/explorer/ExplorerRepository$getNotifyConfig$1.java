package com.reown.android.internal.common.explorer;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.explorer.ExplorerRepository", f = "ExplorerRepository.kt", i = {0}, l = {69}, m = "getNotifyConfig", n = {"appDomain"}, s = {"L$0"})
public final class ExplorerRepository$getNotifyConfig$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ExplorerRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExplorerRepository$getNotifyConfig$1(ExplorerRepository explorerRepository, Continuation<? super ExplorerRepository$getNotifyConfig$1> continuation) {
        super(continuation);
        this.this$0 = explorerRepository;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getNotifyConfig((String) null, this);
    }
}
