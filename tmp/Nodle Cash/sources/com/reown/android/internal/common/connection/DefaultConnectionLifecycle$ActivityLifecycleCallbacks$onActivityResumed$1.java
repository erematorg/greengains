package com.reown.android.internal.common.connection;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.connection.DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1", f = "DefaultConnectionLifecycle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ DefaultConnectionLifecycle this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1(DefaultConnectionLifecycle defaultConnectionLifecycle, Continuation<? super DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1> continuation) {
        super(2, continuation);
        this.this$0 = defaultConnectionLifecycle;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0._onResume.setValue(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
