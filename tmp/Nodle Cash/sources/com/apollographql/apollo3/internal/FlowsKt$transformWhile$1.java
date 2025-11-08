package com.apollographql.apollo3.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nflows.kt\nKotlin\n*S Kotlin\n*F\n+ 1 flows.kt\ncom/apollographql/apollo3/internal/FlowsKt$transformWhile$1\n+ 2 flows.kt\ncom/apollographql/apollo3/internal/FlowsKt\n*L\n1#1,52:1\n29#2,15:53\n*S KotlinDebug\n*F\n+ 1 flows.kt\ncom/apollographql/apollo3/internal/FlowsKt$transformWhile$1\n*L\n23#1:53,15\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.internal.FlowsKt$transformWhile$1", f = "flows.kt", i = {0}, l = {61}, m = "invokeSuspend", n = {"collector$iv"}, s = {"L$0"})
public final class FlowsKt$transformWhile$1 extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T> $this_transformWhile;
    final /* synthetic */ Function3<FlowCollector<? super R>, T, Continuation<? super Boolean>, Object> $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowsKt$transformWhile$1(Flow<? extends T> flow, Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Boolean>, ? extends Object> function3, Continuation<? super FlowsKt$transformWhile$1> continuation) {
        super(2, continuation);
        this.$this_transformWhile = flow;
        this.$transform = function3;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        FlowsKt$transformWhile$1 flowsKt$transformWhile$1 = new FlowsKt$transformWhile$1(this.$this_transformWhile, this.$transform, continuation);
        flowsKt$transformWhile$1.L$0 = obj;
        return flowsKt$transformWhile$1;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1 flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<T> flow = this.$this_transformWhile;
            FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1 flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$12 = new FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1(this.$transform, (FlowCollector) this.L$0);
            try {
                FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$2 flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$2 = new FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$2(flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$12);
                this.L$0 = flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$12;
                this.label = 1;
                if (flow.collect(flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (AbortFlowException e3) {
                e = e3;
                flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1 = flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$12;
                e.checkOwnership(flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1);
                return Unit.INSTANCE;
            }
        } else if (i3 == 1) {
            flowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1 = (FlowsKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (AbortFlowException e4) {
                e = e4;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super R> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((FlowsKt$transformWhile$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
