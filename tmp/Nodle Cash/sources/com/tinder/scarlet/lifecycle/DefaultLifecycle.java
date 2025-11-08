package com.tinder.scarlet.lifecycle;

import com.tinder.scarlet.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u00020\u00012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0007\"\u00020\u0001H\u0001¢\u0006\u0002\u0010\bJ9\u0010\t\u001a\u00020\n2.\u0010\u000b\u001a*\u0012\u000e\b\u0000\u0012\n \u000e*\u0004\u0018\u00010\r0\r \u000e*\u0014\u0012\u000e\b\u0000\u0012\n \u000e*\u0004\u0018\u00010\r0\r\u0018\u00010\f0\fH\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/tinder/scarlet/lifecycle/DefaultLifecycle;", "Lcom/tinder/scarlet/Lifecycle;", "lifecycleRegistry", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "(Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;)V", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "subscribe", "", "p0", "Lorg/reactivestreams/Subscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "kotlin.jvm.PlatformType", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultLifecycle implements Lifecycle {
    @NotNull
    private final LifecycleRegistry lifecycleRegistry;

    public DefaultLifecycle() {
        this((LifecycleRegistry) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    public Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        return this.lifecycleRegistry.combineWith(lifecycleArr);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.lifecycleRegistry.subscribe(subscriber);
    }

    public DefaultLifecycle(@NotNull LifecycleRegistry lifecycleRegistry2) {
        Intrinsics.checkNotNullParameter(lifecycleRegistry2, "lifecycleRegistry");
        this.lifecycleRegistry = lifecycleRegistry2;
        lifecycleRegistry2.onNext((Lifecycle.State) Lifecycle.State.Started.INSTANCE);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultLifecycle(LifecycleRegistry lifecycleRegistry2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? new LifecycleRegistry(0, 1, (DefaultConstructorMarker) null) : lifecycleRegistry2);
    }
}
