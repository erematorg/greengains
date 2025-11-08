package com.tinder.scarlet.lifecycle.android;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\"\u00020\u0001H\u0001¢\u0006\u0002\u0010\nJ9\u0010\u000b\u001a\u00020\f2.\u0010\r\u001a*\u0012\u000e\b\u0000\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f \u0010*\u0014\u0012\u000e\b\u0000\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010\u000e0\u000eH\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/tinder/scarlet/lifecycle/android/ServiceStartedLifecycle;", "Lcom/tinder/scarlet/Lifecycle;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleRegistry", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "(Landroidx/lifecycle/LifecycleOwner;Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;)V", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "subscribe", "", "p0", "Lorg/reactivestreams/Subscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "kotlin.jvm.PlatformType", "ALifecycleObserver", "scarlet-lifecycle-android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ServiceStartedLifecycle implements Lifecycle {
    @NotNull
    private final LifecycleOwner lifecycleOwner;
    /* access modifiers changed from: private */
    @NotNull
    public final LifecycleRegistry lifecycleRegistry;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0004H\u0007J\b\u0010\u0006\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/lifecycle/android/ServiceStartedLifecycle$ALifecycleObserver;", "Landroidx/lifecycle/LifecycleObserver;", "(Lcom/tinder/scarlet/lifecycle/android/ServiceStartedLifecycle;)V", "onDestroy", "", "onResume", "onStop", "scarlet-lifecycle-android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class ALifecycleObserver implements LifecycleObserver {
        public ALifecycleObserver() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public final void onDestroy() {
            ServiceStartedLifecycle.this.lifecycleRegistry.onComplete();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public final void onResume() {
            ServiceStartedLifecycle.this.lifecycleRegistry.onNext((Lifecycle.State) Lifecycle.State.Started.INSTANCE);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public final void onStop() {
            ServiceStartedLifecycle.this.lifecycleRegistry.onNext((Lifecycle.State) Lifecycle.State.Stopped.AndAborted.INSTANCE);
        }
    }

    public ServiceStartedLifecycle(@NotNull LifecycleOwner lifecycleOwner2, @NotNull LifecycleRegistry lifecycleRegistry2) {
        Intrinsics.checkNotNullParameter(lifecycleOwner2, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(lifecycleRegistry2, "lifecycleRegistry");
        this.lifecycleOwner = lifecycleOwner2;
        this.lifecycleRegistry = lifecycleRegistry2;
        lifecycleOwner2.getLifecycle().addObserver(new ALifecycleObserver());
    }

    @NotNull
    public com.tinder.scarlet.Lifecycle combineWith(@NotNull com.tinder.scarlet.Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        return this.lifecycleRegistry.combineWith(lifecycleArr);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.lifecycleRegistry.subscribe(subscriber);
    }
}
