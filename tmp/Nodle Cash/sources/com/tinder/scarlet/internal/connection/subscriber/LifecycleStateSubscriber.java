package com.tinder.scarlet.internal.connection.subscriber;

import com.tinder.scarlet.Event;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.internal.connection.Connection;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\tH\u0014J\u0006\u0010\u0011\u001a\u00020\tR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/tinder/scarlet/internal/connection/subscriber/LifecycleStateSubscriber;", "Lio/reactivex/subscribers/DisposableSubscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "stateManager", "Lcom/tinder/scarlet/internal/connection/Connection$StateManager;", "(Lcom/tinder/scarlet/internal/connection/Connection$StateManager;)V", "pendingRequestCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "onComplete", "", "onError", "", "throwable", "", "onNext", "lifecycleState", "onStart", "requestNext", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LifecycleStateSubscriber extends DisposableSubscriber<Lifecycle.State> {
    @NotNull
    private final AtomicInteger pendingRequestCount = new AtomicInteger();
    @NotNull
    private final Connection.StateManager stateManager;

    public LifecycleStateSubscriber(@NotNull Connection.StateManager stateManager2) {
        Intrinsics.checkNotNullParameter(stateManager2, "stateManager");
        this.stateManager = stateManager2;
    }

    public void onComplete() {
        this.stateManager.handleEvent(Event.OnLifecycle.Terminate.INSTANCE);
    }

    public void onStart() {
        request(1);
    }

    public final void requestNext() {
        if (this.pendingRequestCount.get() == 0) {
            this.pendingRequestCount.incrementAndGet();
            request(1);
        }
    }

    @NotNull
    public Void onError(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        throw th;
    }

    public void onNext(@NotNull Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "lifecycleState");
        if (this.pendingRequestCount.decrementAndGet() < 0) {
            this.pendingRequestCount.set(0);
        }
        this.stateManager.handleEvent(new Event.OnLifecycle.StateChange(state));
    }
}
