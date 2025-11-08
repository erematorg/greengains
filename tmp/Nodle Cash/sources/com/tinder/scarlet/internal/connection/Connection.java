package com.tinder.scarlet.internal.connection;

import androidx.core.app.NotificationCompat;
import com.reown.android.push.notifications.PushMessagingService;
import com.tinder.StateMachine;
import com.tinder.scarlet.Event;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.Message;
import com.tinder.scarlet.Session;
import com.tinder.scarlet.SideEffect;
import com.tinder.scarlet.State;
import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.internal.connection.subscriber.LifecycleStateSubscriber;
import com.tinder.scarlet.internal.connection.subscriber.RetryTimerSubscriber;
import com.tinder.scarlet.internal.connection.subscriber.WebSocketEventSubscriber;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import com.tinder.scarlet.retry.BackoffStrategy;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/tinder/scarlet/internal/connection/Connection;", "", "stateManager", "Lcom/tinder/scarlet/internal/connection/Connection$StateManager;", "(Lcom/tinder/scarlet/internal/connection/Connection$StateManager;)V", "getStateManager", "()Lcom/tinder/scarlet/internal/connection/Connection$StateManager;", "observeEvent", "Lio/reactivex/Flowable;", "Lcom/tinder/scarlet/Event;", "send", "", "message", "Lcom/tinder/scarlet/Message;", "startForever", "", "Factory", "StateManager", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Connection {
    @NotNull
    private final StateManager stateManager;

    @SourceDebugExtension({"SMAP\nConnection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Connection.kt\ncom/tinder/scarlet/internal/connection/Connection$Factory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,245:1\n1#2:246\n*E\n"})
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\u00038BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/tinder/scarlet/internal/connection/Connection$Factory;", "", "lifecycle", "Lcom/tinder/scarlet/Lifecycle;", "webSocketFactory", "Lcom/tinder/scarlet/WebSocket$Factory;", "backoffStrategy", "Lcom/tinder/scarlet/retry/BackoffStrategy;", "scheduler", "Lio/reactivex/Scheduler;", "(Lcom/tinder/scarlet/Lifecycle;Lcom/tinder/scarlet/WebSocket$Factory;Lcom/tinder/scarlet/retry/BackoffStrategy;Lio/reactivex/Scheduler;)V", "sharedLifecycle", "getSharedLifecycle", "()Lcom/tinder/scarlet/Lifecycle;", "sharedLifecycle$delegate", "Lkotlin/Lazy;", "create", "Lcom/tinder/scarlet/internal/connection/Connection;", "createSharedLifecycle", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Factory {
        @NotNull
        private final BackoffStrategy backoffStrategy;
        @NotNull
        private final Lifecycle lifecycle;
        @NotNull
        private final Scheduler scheduler;
        @NotNull
        private final Lazy sharedLifecycle$delegate = LazyKt.lazy(new Connection$Factory$sharedLifecycle$2(this));
        @NotNull
        private final WebSocket.Factory webSocketFactory;

        public Factory(@NotNull Lifecycle lifecycle2, @NotNull WebSocket.Factory factory, @NotNull BackoffStrategy backoffStrategy2, @NotNull Scheduler scheduler2) {
            Intrinsics.checkNotNullParameter(lifecycle2, "lifecycle");
            Intrinsics.checkNotNullParameter(factory, "webSocketFactory");
            Intrinsics.checkNotNullParameter(backoffStrategy2, "backoffStrategy");
            Intrinsics.checkNotNullParameter(scheduler2, "scheduler");
            this.lifecycle = lifecycle2;
            this.webSocketFactory = factory;
            this.backoffStrategy = backoffStrategy2;
            this.scheduler = scheduler2;
        }

        /* access modifiers changed from: private */
        public final LifecycleRegistry createSharedLifecycle() {
            LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(0, 1, (DefaultConstructorMarker) null);
            this.lifecycle.subscribe(lifecycleRegistry);
            return lifecycleRegistry;
        }

        private final Lifecycle getSharedLifecycle() {
            return (Lifecycle) this.sharedLifecycle$delegate.getValue();
        }

        @NotNull
        public final Connection create() {
            return new Connection(new StateManager(getSharedLifecycle(), this.webSocketFactory, this.backoffStrategy, this.scheduler));
        }
    }

    @SourceDebugExtension({"SMAP\nConnection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Connection.kt\ncom/tinder/scarlet/internal/connection/Connection$StateManager\n+ 2 StateMachine.kt\ncom/tinder/StateMachine$Matcher$Companion\n*L\n1#1,245:1\n120#2:246\n120#2:247\n120#2:248\n*S KotlinDebug\n*F\n+ 1 Connection.kt\ncom/tinder/scarlet/internal/connection/Connection$StateManager\n*L\n219#1:246\n222#1:247\n224#1:248\n*E\n"})
    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rJ\u0018\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0\u001eH\u0002J\u0018\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0\u001eH\u0002J\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\"J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u001bH\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J\u0006\u0010*\u001a\u00020\u001bJ\u0018\u0010+\u001a\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030,0\u001eH\u0002J\f\u0010-\u001a\u00020\u001b*\u00020.H\u0002J\u0014\u0010/\u001a\u00020\u001b*\u0002002\u0006\u0010\u0013\u001a\u000201H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00190\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/tinder/scarlet/internal/connection/Connection$StateManager;", "", "lifecycle", "Lcom/tinder/scarlet/Lifecycle;", "webSocketFactory", "Lcom/tinder/scarlet/WebSocket$Factory;", "backoffStrategy", "Lcom/tinder/scarlet/retry/BackoffStrategy;", "scheduler", "Lio/reactivex/Scheduler;", "(Lcom/tinder/scarlet/Lifecycle;Lcom/tinder/scarlet/WebSocket$Factory;Lcom/tinder/scarlet/retry/BackoffStrategy;Lio/reactivex/Scheduler;)V", "eventProcessor", "Lio/reactivex/processors/PublishProcessor;", "Lcom/tinder/scarlet/Event;", "kotlin.jvm.PlatformType", "getLifecycle", "()Lcom/tinder/scarlet/Lifecycle;", "lifecycleStateSubscriber", "Lcom/tinder/scarlet/internal/connection/subscriber/LifecycleStateSubscriber;", "state", "Lcom/tinder/scarlet/State;", "getState", "()Lcom/tinder/scarlet/State;", "stateMachine", "Lcom/tinder/StateMachine;", "Lcom/tinder/scarlet/SideEffect;", "handleEvent", "", "event", "lifecycleStart", "Lcom/tinder/StateMachine$Matcher;", "Lcom/tinder/scarlet/Event$OnLifecycle$StateChange;", "lifecycleStop", "observeEvent", "Lio/reactivex/Flowable;", "open", "Lcom/tinder/scarlet/Session;", "requestNextLifecycleState", "scheduleRetry", "Lio/reactivex/disposables/Disposable;", "duration", "", "subscribe", "webSocketOpen", "Lcom/tinder/scarlet/Event$OnWebSocket$Event;", "cancelRetry", "Lcom/tinder/scarlet/State$WaitingToRetry;", "initiateShutdown", "Lcom/tinder/scarlet/State$Connected;", "Lcom/tinder/scarlet/Lifecycle$State;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class StateManager {
        /* access modifiers changed from: private */
        @NotNull
        public final BackoffStrategy backoffStrategy;
        /* access modifiers changed from: private */
        @NotNull
        public final PublishProcessor<Event> eventProcessor;
        @NotNull
        private final Lifecycle lifecycle;
        /* access modifiers changed from: private */
        @NotNull
        public final LifecycleStateSubscriber lifecycleStateSubscriber = new LifecycleStateSubscriber(this);
        @NotNull
        private final Scheduler scheduler;
        @NotNull
        private final StateMachine<State, Event, SideEffect> stateMachine;
        @NotNull
        private final WebSocket.Factory webSocketFactory;

        public StateManager(@NotNull Lifecycle lifecycle2, @NotNull WebSocket.Factory factory, @NotNull BackoffStrategy backoffStrategy2, @NotNull Scheduler scheduler2) {
            Intrinsics.checkNotNullParameter(lifecycle2, "lifecycle");
            Intrinsics.checkNotNullParameter(factory, "webSocketFactory");
            Intrinsics.checkNotNullParameter(backoffStrategy2, "backoffStrategy");
            Intrinsics.checkNotNullParameter(scheduler2, "scheduler");
            this.lifecycle = lifecycle2;
            this.webSocketFactory = factory;
            this.backoffStrategy = backoffStrategy2;
            this.scheduler = scheduler2;
            PublishProcessor<Event> create = PublishProcessor.create();
            Intrinsics.checkNotNullExpressionValue(create, "create<Event>()");
            this.eventProcessor = create;
            this.stateMachine = StateMachine.Companion.create(new Connection$StateManager$stateMachine$1(this));
        }

        /* access modifiers changed from: private */
        public final void cancelRetry(State.WaitingToRetry waitingToRetry) {
            waitingToRetry.getTimerDisposable$scarlet().dispose();
        }

        /* access modifiers changed from: private */
        public final void initiateShutdown(State.Connected connected, Lifecycle.State state) {
            if (state instanceof Lifecycle.State.Stopped.WithReason) {
                connected.getSession$scarlet().getWebSocket().close(((Lifecycle.State.Stopped.WithReason) state).getShutdownReason());
            } else if (Intrinsics.areEqual((Object) state, (Object) Lifecycle.State.Stopped.AndAborted.INSTANCE)) {
                connected.getSession$scarlet().getWebSocket().cancel();
            }
        }

        /* access modifiers changed from: private */
        public final StateMachine.Matcher<Event, Event.OnLifecycle.StateChange<?>> lifecycleStart() {
            return StateMachine.Matcher.Companion.any(Event.OnLifecycle.StateChange.class).where(Connection$StateManager$lifecycleStart$1.INSTANCE);
        }

        /* access modifiers changed from: private */
        public final StateMachine.Matcher<Event, Event.OnLifecycle.StateChange<?>> lifecycleStop() {
            return StateMachine.Matcher.Companion.any(Event.OnLifecycle.StateChange.class).where(Connection$StateManager$lifecycleStop$1.INSTANCE);
        }

        /* access modifiers changed from: private */
        public final Session open() {
            WebSocket create = this.webSocketFactory.create();
            WebSocketEventSubscriber webSocketEventSubscriber = new WebSocketEventSubscriber(this);
            Flowable.fromPublisher(create.open()).observeOn(this.scheduler).cast(WebSocket.Event.class).subscribe(webSocketEventSubscriber);
            return new Session(create, webSocketEventSubscriber);
        }

        /* access modifiers changed from: private */
        public final void requestNextLifecycleState() {
            this.lifecycleStateSubscriber.requestNext();
        }

        /* access modifiers changed from: private */
        public final Disposable scheduleRetry(long j2) {
            RetryTimerSubscriber retryTimerSubscriber = new RetryTimerSubscriber(this);
            Flowable.timer(j2, TimeUnit.MILLISECONDS, this.scheduler).onBackpressureBuffer().subscribe(retryTimerSubscriber);
            return retryTimerSubscriber;
        }

        /* access modifiers changed from: private */
        public final StateMachine.Matcher<Event, Event.OnWebSocket.C0066Event<?>> webSocketOpen() {
            return StateMachine.Matcher.Companion.any(Event.OnWebSocket.C0066Event.class).where(Connection$StateManager$webSocketOpen$1.INSTANCE);
        }

        @NotNull
        public final Lifecycle getLifecycle() {
            return this.lifecycle;
        }

        @NotNull
        public final State getState() {
            return this.stateMachine.getState();
        }

        public final void handleEvent(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            this.eventProcessor.onNext(event);
            this.stateMachine.transition(event);
        }

        @NotNull
        public final Flowable<Event> observeEvent() {
            Flowable<Event> onBackpressureBuffer = this.eventProcessor.onBackpressureBuffer();
            Intrinsics.checkNotNullExpressionValue(onBackpressureBuffer, "eventProcessor.onBackpressureBuffer()");
            return onBackpressureBuffer;
        }

        public final void subscribe() {
            this.lifecycle.subscribe(this.lifecycleStateSubscriber);
        }
    }

    public Connection(@NotNull StateManager stateManager2) {
        Intrinsics.checkNotNullParameter(stateManager2, "stateManager");
        this.stateManager = stateManager2;
    }

    @NotNull
    public final StateManager getStateManager() {
        return this.stateManager;
    }

    @NotNull
    public final Flowable<Event> observeEvent() {
        return this.stateManager.observeEvent();
    }

    public final boolean send(@NotNull Message message) {
        Intrinsics.checkNotNullParameter(message, PushMessagingService.KEY_MESSAGE);
        State state = this.stateManager.getState();
        if (state instanceof State.Connected) {
            return ((State.Connected) state).getSession$scarlet().getWebSocket().send(message);
        }
        return false;
    }

    public final void startForever() {
        this.stateManager.subscribe();
    }
}
