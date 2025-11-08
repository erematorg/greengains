package com.tinder.scarlet.lifecycle.android;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u000b\u001a\u00020\u00012\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\r\"\u00020\u0001H\u0001¢\u0006\u0002\u0010\u000eJ9\u0010\u000f\u001a\u00020\u00102.\u0010\u0011\u001a*\u0012\u000e\b\u0000\u0012\n \u0014*\u0004\u0018\u00010\u00130\u0013 \u0014*\u0014\u0012\u000e\b\u0000\u0012\n \u0014*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00120\u0012H\u0001R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/tinder/scarlet/lifecycle/android/ApplicationResumedLifecycle;", "Lcom/tinder/scarlet/Lifecycle;", "application", "Landroid/app/Application;", "lifecycleRegistry", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "(Landroid/app/Application;Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;)V", "job", "Lkotlinx/coroutines/CompletableJob;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "subscribe", "", "p0", "Lorg/reactivestreams/Subscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "kotlin.jvm.PlatformType", "ActivityLifecycleCallbacks", "scarlet-lifecycle-android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ApplicationResumedLifecycle implements Lifecycle {
    @NotNull
    private final CompletableJob job;
    /* access modifiers changed from: private */
    @NotNull
    public final LifecycleRegistry lifecycleRegistry;
    /* access modifiers changed from: private */
    @NotNull
    public CoroutineScope scope;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/tinder/scarlet/lifecycle/android/ApplicationResumedLifecycle$ActivityLifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "(Lcom/tinder/scarlet/lifecycle/android/ApplicationResumedLifecycle;)V", "isResumed", "", "()Z", "setResumed", "(Z)V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "scarlet-lifecycle-android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
            this.job = BuildersKt__Builders_commonKt.launch$default(ApplicationResumedLifecycle.this.scope, (CoroutineContext) null, (CoroutineStart) null, new ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1(this, ApplicationResumedLifecycle.this, (Continuation<? super ApplicationResumedLifecycle$ActivityLifecycleCallbacks$onActivityPaused$1>) null), 3, (Object) null);
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
            ApplicationResumedLifecycle.this.lifecycleRegistry.onNext((Lifecycle.State) Lifecycle.State.Started.INSTANCE);
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

    public ApplicationResumedLifecycle(@NotNull Application application, @NotNull LifecycleRegistry lifecycleRegistry2) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(lifecycleRegistry2, "lifecycleRegistry");
        this.lifecycleRegistry = lifecycleRegistry2;
        CompletableJob SupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.job = SupervisorJob$default;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorJob$default.plus(Dispatchers.getDefault()));
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks());
    }

    @NotNull
    public Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        return this.lifecycleRegistry.combineWith(lifecycleArr);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.lifecycleRegistry.subscribe(subscriber);
    }
}
