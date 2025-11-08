package com.reown.android.internal.common.explorer;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.explorer.ExplorerProtocol", f = "ExplorerProtocol.kt", i = {0, 0, 0, 0}, l = {19}, m = "getProjects-yxL6bBk", n = {"page", "entries", "isVerified", "isFeatured"}, s = {"I$0", "I$1", "Z$0", "Z$1"})
public final class ExplorerProtocol$getProjects$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ExplorerProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExplorerProtocol$getProjects$1(ExplorerProtocol explorerProtocol, Continuation<? super ExplorerProtocol$getProjects$1> continuation) {
        super(continuation);
        this.this$0 = explorerProtocol;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r6 = this.this$0.m8734getProjectsyxL6bBk(0, 0, false, false, this);
        return r6 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r6 : Result.m8978boximpl(r6);
    }
}
