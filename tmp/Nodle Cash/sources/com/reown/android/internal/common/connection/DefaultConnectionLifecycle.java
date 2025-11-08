package com.reown.android.internal.common.connection;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.reown.foundation.network.ConnectionLifecycle;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.ShutdownReason;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u001fB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\"\u0010\u0016\u001a\u00020\u00012\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0018\"\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0019J9\u0010\u001a\u001a\u00020\u00152.\u0010\u001b\u001a*\u0012\u000e\b\u0000\u0012\n \u001e*\u0004\u0018\u00010\u001d0\u001d \u001e*\u0014\u0012\u000e\b\u0000\u0012\n \u001e*\u0004\u0018\u00010\u001d0\u001d\u0018\u00010\u001c0\u001cH\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/reown/android/internal/common/connection/DefaultConnectionLifecycle;", "Lcom/tinder/scarlet/Lifecycle;", "Lcom/reown/foundation/network/ConnectionLifecycle;", "application", "Landroid/app/Application;", "lifecycleRegistry", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "<init>", "(Landroid/app/Application;Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;)V", "job", "Lkotlinx/coroutines/CompletableJob;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "_onResume", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "onResume", "Lkotlinx/coroutines/flow/StateFlow;", "getOnResume", "()Lkotlinx/coroutines/flow/StateFlow;", "reconnect", "", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "subscribe", "p0", "Lorg/reactivestreams/Subscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "kotlin.jvm.PlatformType", "ActivityLifecycleCallbacks", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultConnectionLifecycle implements Lifecycle, ConnectionLifecycle {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableStateFlow<Boolean> _onResume;
    @NotNull
    private final CompletableJob job;
    /* access modifiers changed from: private */
    @NotNull
    public final LifecycleRegistry lifecycleRegistry;
    @NotNull
    private final StateFlow<Boolean> onResume;
    /* access modifiers changed from: private */
    @NotNull
    public CoroutineScope scope;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u0018H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/reown/android/internal/common/connection/DefaultConnectionLifecycle$ActivityLifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "<init>", "(Lcom/reown/android/internal/common/connection/DefaultConnectionLifecycle;)V", "isResumed", "", "()Z", "setResumed", "(Z)V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "onActivityPaused", "", "activity", "Landroid/app/Activity;", "onActivityResumed", "onActivityStarted", "onActivityDestroyed", "onActivitySaveInstanceState", "outState", "Landroid/os/Bundle;", "onActivityStopped", "onActivityCreated", "savedInstanceState", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private boolean isResumed;
        @Nullable
        private Job job;

        public ActivityLifecycleCallbacks() {
        }

        @Nullable
        public final Job getJob() {
            return this.job;
        }

        public final boolean isResumed() {
            return this.isResumed;
        }

        public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public void onActivityDestroyed(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public void onActivityPaused(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.isResumed = false;
            this.job = BuildersKt__Builders_commonKt.launch$default(DefaultConnectionLifecycle.this.scope, (CoroutineContext) null, (CoroutineStart) null, new DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1(this, DefaultConnectionLifecycle.this, (Continuation<? super DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1>) null), 3, (Object) null);
        }

        public void onActivityResumed(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.isResumed = true;
            Job job2 = this.job;
            if (job2 != null && job2.isActive()) {
                Job job3 = this.job;
                if (job3 != null) {
                    Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
                }
                this.job = null;
            }
            Job unused = BuildersKt__Builders_commonKt.launch$default(DefaultConnectionLifecycle.this.scope, (CoroutineContext) null, (CoroutineStart) null, new DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1(DefaultConnectionLifecycle.this, (Continuation<? super DefaultConnectionLifecycle$ActivityLifecycleCallbacks$onActivityResumed$1>) null), 3, (Object) null);
        }

        public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle bundle) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(bundle, "outState");
        }

        public void onActivityStarted(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public void onActivityStopped(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public final void setJob(@Nullable Job job2) {
            this.job = job2;
        }

        public final void setResumed(boolean z2) {
            this.isResumed = z2;
        }
    }

    public DefaultConnectionLifecycle(@NotNull Application application, @NotNull LifecycleRegistry lifecycleRegistry2) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(lifecycleRegistry2, "lifecycleRegistry");
        this.lifecycleRegistry = lifecycleRegistry2;
        CompletableJob SupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.job = SupervisorJob$default;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorJob$default.plus(Dispatchers.getDefault()));
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._onResume = MutableStateFlow;
        this.onResume = FlowKt.asStateFlow(MutableStateFlow);
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks());
    }

    @NotNull
    public Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        return this.lifecycleRegistry.combineWith(lifecycleArr);
    }

    @NotNull
    public StateFlow<Boolean> getOnResume() {
        return this.onResume;
    }

    public void reconnect() {
        this.lifecycleRegistry.onNext((Lifecycle.State) new Lifecycle.State.Stopped.WithReason((ShutdownReason) null, 1, (DefaultConstructorMarker) null));
        this.lifecycleRegistry.onNext((Lifecycle.State) Lifecycle.State.Started.INSTANCE);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.lifecycleRegistry.subscribe(subscriber);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultConnectionLifecycle(Application application, LifecycleRegistry lifecycleRegistry2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(application, (i3 & 2) != 0 ? new LifecycleRegistry(0, 1, (DefaultConstructorMarker) null) : lifecycleRegistry2);
    }
}
