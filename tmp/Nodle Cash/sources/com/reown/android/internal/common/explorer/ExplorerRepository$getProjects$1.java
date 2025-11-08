package com.reown.android.internal.common.explorer;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.explorer.ExplorerRepository", f = "ExplorerRepository.kt", i = {0, 0, 0, 0}, l = {57}, m = "getProjects", n = {"page", "entries", "isVerified", "isFeatured"}, s = {"I$0", "I$1", "Z$0", "Z$1"})
public final class ExplorerRepository$getProjects$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ExplorerRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExplorerRepository$getProjects$1(ExplorerRepository explorerRepository, Continuation<? super ExplorerRepository$getProjects$1> continuation) {
        super(continuation);
        this.this$0 = explorerRepository;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getProjects(0, 0, false, false, this);
    }
}
