package com.apollographql.apollo3.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nflows.kt\nKotlin\n*S Kotlin\n*F\n+ 1 flows.kt\ncom/apollographql/apollo3/internal/FlowsKt$collectWhile$2\n*L\n1#1,52:1\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "T", "it", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com/apollographql/apollo3/internal/FlowsKt$collectWhile$2"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$2<T> implements FlowCollector {
    final /* synthetic */ FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1 $collector;

    public FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$2(FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1 flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1) {
        this.$collector = flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1;
    }

    @Nullable
    public final Object emit(T t2, @NotNull Continuation<? super Unit> continuation) {
        Object emit = this.$collector.emit(t2, continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }
}
