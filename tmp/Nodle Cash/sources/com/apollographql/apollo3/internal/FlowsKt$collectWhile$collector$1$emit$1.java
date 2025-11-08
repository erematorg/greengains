package com.apollographql.apollo3.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nflows.kt\nKotlin\n*S Kotlin\n*F\n+ 1 flows.kt\ncom/apollographql/apollo3/internal/FlowsKt$collectWhile$collector$1$emit$1\n*L\n1#1,52:1\n*E\n"})
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1", f = "flows.kt", i = {0}, l = {31}, m = "emit", n = {"this"}, s = {"L$0"})
public final class FlowsKt$collectWhile$collector$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowsKt$collectWhile$collector$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowsKt$collectWhile$collector$1$emit$1(FlowsKt$collectWhile$collector$1 flowsKt$collectWhile$collector$1, Continuation continuation) {
        super(continuation);
        this.this$0 = flowsKt$collectWhile$collector$1;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
