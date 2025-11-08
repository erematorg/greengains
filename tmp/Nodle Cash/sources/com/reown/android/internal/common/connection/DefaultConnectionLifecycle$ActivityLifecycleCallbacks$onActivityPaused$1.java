package com.reown.android.internal.common.connection;

import com.reown.android.internal.common.connection.DefaultConnectionLifecycle;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.ShutdownReason;
import java.util.concurrent.TimeUnit;
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
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.connection.DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1", f = "DefaultConnectionLifecycle.kt", i = {}, l = {50}, m = "invokeSuspend", n = {}, s = {})
public final class DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ DefaultConnectionLifecycle.ActivityLifecycleCallbacks this$0;
    final /* synthetic */ DefaultConnectionLifecycle this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1(DefaultConnectionLifecycle.ActivityLifecycleCallbacks activityLifecycleCallbacks, DefaultConnectionLifecycle defaultConnectionLifecycle, Continuation<? super DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1> continuation) {
        super(2, continuation);
        this.this$0 = activityLifecycleCallbacks;
        this.this$1 = defaultConnectionLifecycle;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1(this.this$0, this.this$1, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            long millis = TimeUnit.SECONDS.toMillis(30);
            this.label = 1;
            if (DelayKt.delay(millis, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (!this.this$0.isResumed()) {
            this.this$1.lifecycleRegistry.onNext((Lifecycle.State) new Lifecycle.State.Stopped.WithReason(new ShutdownReason(1000, "App is paused")));
            this.this$0.setJob((Job) null);
            this.this$1._onResume.setValue(Boxing.boxBoolean(false));
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
