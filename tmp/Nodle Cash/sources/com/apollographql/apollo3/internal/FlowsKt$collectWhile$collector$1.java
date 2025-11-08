package com.apollographql.apollo3.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nflows.kt\nKotlin\n*S Kotlin\n*F\n+ 1 flows.kt\ncom/apollographql/apollo3/internal/FlowsKt$collectWhile$collector$1\n*L\n1#1,52:1\n*E\n"})
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"com/apollographql/apollo3/internal/FlowsKt$collectWhile$collector$1", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class FlowsKt$collectWhile$collector$1 implements FlowCollector<T> {
    final /* synthetic */ Function2<T, Continuation<? super Boolean>, Object> $predicate;

    public FlowsKt$collectWhile$collector$1(Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        this.$predicate = function2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(T r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1$emit$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1$emit$1 r0 = (com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1$emit$1 r0 = new com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1$emit$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            java.lang.Object r4 = r0.L$0
            com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1 r4 = (com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0045
        L_0x002d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.functions.Function2<T, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r6 = r4.$predicate
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r6.invoke(r5, r0)
            if (r6 != r1) goto L_0x0045
            return r1
        L_0x0045:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r5 = r6.booleanValue()
            if (r5 == 0) goto L_0x0050
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L_0x0050:
            com.apollographql.apollo3.internal.AbortFlowException r5 = new com.apollographql.apollo3.internal.AbortFlowException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.internal.FlowsKt$collectWhile$collector$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public Object emit$$forInline(T t2, @NotNull Continuation<? super Unit> continuation) {
        InlineMarker.mark(4);
        new FlowsKt$collectWhile$collector$1$emit$1(this, continuation);
        InlineMarker.mark(5);
        if (((Boolean) this.$predicate.invoke(t2, continuation)).booleanValue()) {
            return Unit.INSTANCE;
        }
        throw new AbortFlowException(this);
    }
}
