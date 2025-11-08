package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.mpp.UtilsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.http.BatchingHttpInterceptor$intercept$3", f = "BatchingHttpInterceptor.kt", i = {}, l = {106, 107}, m = "invokeSuspend", n = {}, s = {})
public final class BatchingHttpInterceptor$intercept$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ BatchingHttpInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatchingHttpInterceptor$intercept$3(BatchingHttpInterceptor batchingHttpInterceptor, Continuation<? super BatchingHttpInterceptor$intercept$3> continuation) {
        super(2, continuation);
        this.this$0 = batchingHttpInterceptor;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new BatchingHttpInterceptor$intercept$3(this.this$0, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay((this.this$0.batchIntervalMillis - ((UtilsKt.currentTimeMillis() - this.this$0.creationTime) % this.this$0.batchIntervalMillis)) - 1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i3 == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        BatchingHttpInterceptor batchingHttpInterceptor = this.this$0;
        this.label = 2;
        if (batchingHttpInterceptor.executePendingRequests(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((BatchingHttpInterceptor$intercept$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
