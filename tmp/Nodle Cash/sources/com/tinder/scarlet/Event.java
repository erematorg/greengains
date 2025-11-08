package com.tinder.scarlet;

import androidx.core.app.NotificationCompat;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.WebSocket;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/Event;", "", "()V", "OnLifecycle", "OnRetry", "OnStateChange", "OnWebSocket", "Lcom/tinder/scarlet/Event$OnLifecycle;", "Lcom/tinder/scarlet/Event$OnRetry;", "Lcom/tinder/scarlet/Event$OnStateChange;", "Lcom/tinder/scarlet/Event$OnWebSocket;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Event {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/Event$OnLifecycle;", "Lcom/tinder/scarlet/Event;", "()V", "StateChange", "Terminate", "Lcom/tinder/scarlet/Event$OnLifecycle$StateChange;", "Lcom/tinder/scarlet/Event$OnLifecycle$Terminate;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class OnLifecycle extends Event {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/tinder/scarlet/Event$OnLifecycle$StateChange;", "T", "Lcom/tinder/scarlet/Lifecycle$State;", "Lcom/tinder/scarlet/Event$OnLifecycle;", "state", "(Lcom/tinder/scarlet/Lifecycle$State;)V", "getState", "()Lcom/tinder/scarlet/Lifecycle$State;", "Lcom/tinder/scarlet/Lifecycle$State;", "component1", "copy", "(Lcom/tinder/scarlet/Lifecycle$State;)Lcom/tinder/scarlet/Event$OnLifecycle$StateChange;", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class StateChange<T extends Lifecycle.State> extends OnLifecycle {
            @NotNull
            private final T state;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public StateChange(@NotNull T t2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(t2, RemoteConfigConstants.ResponseFieldKey.STATE);
                this.state = t2;
            }

            public static /* synthetic */ StateChange copy$default(StateChange stateChange, T t2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    t2 = stateChange.state;
                }
                return stateChange.copy(t2);
            }

            @NotNull
            public final T component1() {
                return this.state;
            }

            @NotNull
            public final StateChange<T> copy(@NotNull T t2) {
                Intrinsics.checkNotNullParameter(t2, RemoteConfigConstants.ResponseFieldKey.STATE);
                return new StateChange<>(t2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof StateChange) && Intrinsics.areEqual((Object) this.state, (Object) ((StateChange) obj).state);
            }

            @NotNull
            public final T getState() {
                return this.state;
            }

            public int hashCode() {
                return this.state.hashCode();
            }

            @NotNull
            public String toString() {
                return "StateChange(state=" + this.state + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/Event$OnLifecycle$Terminate;", "Lcom/tinder/scarlet/Event$OnLifecycle;", "()V", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Terminate extends OnLifecycle {
            @NotNull
            public static final Terminate INSTANCE = new Terminate();

            private Terminate() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ OnLifecycle(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private OnLifecycle() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/Event$OnRetry;", "Lcom/tinder/scarlet/Event;", "()V", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class OnRetry extends Event {
        @NotNull
        public static final OnRetry INSTANCE = new OnRetry();

        private OnRetry() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/tinder/scarlet/Event$OnStateChange;", "T", "Lcom/tinder/scarlet/State;", "Lcom/tinder/scarlet/Event;", "state", "(Lcom/tinder/scarlet/State;)V", "getState", "()Lcom/tinder/scarlet/State;", "Lcom/tinder/scarlet/State;", "component1", "copy", "(Lcom/tinder/scarlet/State;)Lcom/tinder/scarlet/Event$OnStateChange;", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class OnStateChange<T extends State> extends Event {
        @NotNull
        private final T state;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OnStateChange(@NotNull T t2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(t2, RemoteConfigConstants.ResponseFieldKey.STATE);
            this.state = t2;
        }

        public static /* synthetic */ OnStateChange copy$default(OnStateChange onStateChange, T t2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                t2 = onStateChange.state;
            }
            return onStateChange.copy(t2);
        }

        @NotNull
        public final T component1() {
            return this.state;
        }

        @NotNull
        public final OnStateChange<T> copy(@NotNull T t2) {
            Intrinsics.checkNotNullParameter(t2, RemoteConfigConstants.ResponseFieldKey.STATE);
            return new OnStateChange<>(t2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnStateChange) && Intrinsics.areEqual((Object) this.state, (Object) ((OnStateChange) obj).state);
        }

        @NotNull
        public final T getState() {
            return this.state;
        }

        public int hashCode() {
            return this.state.hashCode();
        }

        @NotNull
        public String toString() {
            return "OnStateChange(state=" + this.state + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/Event$OnWebSocket;", "Lcom/tinder/scarlet/Event;", "()V", "Event", "Terminate", "Lcom/tinder/scarlet/Event$OnWebSocket$Event;", "Lcom/tinder/scarlet/Event$OnWebSocket$Terminate;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class OnWebSocket extends Event {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/tinder/scarlet/Event$OnWebSocket$Event;", "T", "Lcom/tinder/scarlet/WebSocket$Event;", "Lcom/tinder/scarlet/Event$OnWebSocket;", "event", "(Lcom/tinder/scarlet/WebSocket$Event;)V", "getEvent", "()Lcom/tinder/scarlet/WebSocket$Event;", "Lcom/tinder/scarlet/WebSocket$Event;", "component1", "copy", "(Lcom/tinder/scarlet/WebSocket$Event;)Lcom/tinder/scarlet/Event$OnWebSocket$Event;", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* renamed from: com.tinder.scarlet.Event$OnWebSocket$Event  reason: collision with other inner class name */
        public static final class C0066Event<T extends WebSocket.Event> extends OnWebSocket {
            @NotNull
            private final T event;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0066Event(@NotNull T t2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(t2, NotificationCompat.CATEGORY_EVENT);
                this.event = t2;
            }

            public static /* synthetic */ C0066Event copy$default(C0066Event event2, T t2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    t2 = event2.event;
                }
                return event2.copy(t2);
            }

            @NotNull
            public final T component1() {
                return this.event;
            }

            @NotNull
            public final C0066Event<T> copy(@NotNull T t2) {
                Intrinsics.checkNotNullParameter(t2, NotificationCompat.CATEGORY_EVENT);
                return new C0066Event<>(t2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof C0066Event) && Intrinsics.areEqual((Object) this.event, (Object) ((C0066Event) obj).event);
            }

            @NotNull
            public final T getEvent() {
                return this.event;
            }

            public int hashCode() {
                return this.event.hashCode();
            }

            @NotNull
            public String toString() {
                return "Event(event=" + this.event + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/Event$OnWebSocket$Terminate;", "Lcom/tinder/scarlet/Event$OnWebSocket;", "()V", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Terminate extends OnWebSocket {
            @NotNull
            public static final Terminate INSTANCE = new Terminate();

            private Terminate() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ OnWebSocket(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private OnWebSocket() {
            super((DefaultConstructorMarker) null);
        }
    }

    public /* synthetic */ Event(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Event() {
    }
}
