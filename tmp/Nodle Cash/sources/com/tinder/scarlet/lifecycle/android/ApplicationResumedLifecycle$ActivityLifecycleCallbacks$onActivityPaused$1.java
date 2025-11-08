package com.tinder.scarlet.lifecycle.android;

import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.ShutdownReason;
import com.tinder.scarlet.lifecycle.android.ApplicationResumedLifecycle;
import java.util.concurrent.TimeUnit;
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
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.tinder.scarlet.lifecycle.android.ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1", f = "ApplicationResumedLifecycle.kt", i = {}, l = {41}, m = "invokeSuspend", n = {}, s = {})
public final class ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ApplicationResumedLifecycle.ActivityLifecycleCallbacks this$0;
    final /* synthetic */ ApplicationResumedLifecycle this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1(ApplicationResumedLifecycle.ActivityLifecycleCallbacks activityLifecycleCallbacks, ApplicationResumedLifecycle applicationResumedLifecycle, Continuation<? super ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1> continuation) {
        super(2, continuation);
        this.this$0 = activityLifecycleCallbacks;
        this.this$1 = applicationResumedLifecycle;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1(this.this$0, this.this$1, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
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
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
