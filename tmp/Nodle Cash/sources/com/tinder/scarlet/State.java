package com.tinder.scarlet;

import android.support.v4.media.session.a;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0006\t\n\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/tinder/scarlet/State;", "", "()V", "Connected", "Connecting", "Destroyed", "Disconnected", "Disconnecting", "WaitingToRetry", "Lcom/tinder/scarlet/State$Connected;", "Lcom/tinder/scarlet/State$Connecting;", "Lcom/tinder/scarlet/State$Destroyed;", "Lcom/tinder/scarlet/State$Disconnected;", "Lcom/tinder/scarlet/State$Disconnecting;", "Lcom/tinder/scarlet/State$WaitingToRetry;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class State {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\bJ\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/tinder/scarlet/State$Connected;", "Lcom/tinder/scarlet/State;", "session", "Lcom/tinder/scarlet/Session;", "(Lcom/tinder/scarlet/Session;)V", "getSession$scarlet", "()Lcom/tinder/scarlet/Session;", "component1", "component1$scarlet", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Connected extends State {
        @NotNull
        private final Session session;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Connected(@NotNull Session session2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(session2, "session");
            this.session = session2;
        }

        public static /* synthetic */ Connected copy$default(Connected connected, Session session2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                session2 = connected.session;
            }
            return connected.copy(session2);
        }

        @NotNull
        public final Session component1$scarlet() {
            return this.session;
        }

        @NotNull
        public final Connected copy(@NotNull Session session2) {
            Intrinsics.checkNotNullParameter(session2, "session");
            return new Connected(session2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Connected) && Intrinsics.areEqual((Object) this.session, (Object) ((Connected) obj).session);
        }

        @NotNull
        public final Session getSession$scarlet() {
            return this.session;
        }

        public int hashCode() {
            return this.session.hashCode();
        }

        @NotNull
        public String toString() {
            return "Connected(session=" + this.session + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\fJ\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/tinder/scarlet/State$Connecting;", "Lcom/tinder/scarlet/State;", "session", "Lcom/tinder/scarlet/Session;", "retryCount", "", "(Lcom/tinder/scarlet/Session;I)V", "getRetryCount", "()I", "getSession$scarlet", "()Lcom/tinder/scarlet/Session;", "component1", "component1$scarlet", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Connecting extends State {
        private final int retryCount;
        @NotNull
        private final Session session;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Connecting(@NotNull Session session2, int i3) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(session2, "session");
            this.session = session2;
            this.retryCount = i3;
        }

        public static /* synthetic */ Connecting copy$default(Connecting connecting, Session session2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                session2 = connecting.session;
            }
            if ((i4 & 2) != 0) {
                i3 = connecting.retryCount;
            }
            return connecting.copy(session2, i3);
        }

        @NotNull
        public final Session component1$scarlet() {
            return this.session;
        }

        public final int component2() {
            return this.retryCount;
        }

        @NotNull
        public final Connecting copy(@NotNull Session session2, int i3) {
            Intrinsics.checkNotNullParameter(session2, "session");
            return new Connecting(session2, i3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Connecting)) {
                return false;
            }
            Connecting connecting = (Connecting) obj;
            return Intrinsics.areEqual((Object) this.session, (Object) connecting.session) && this.retryCount == connecting.retryCount;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }

        @NotNull
        public final Session getSession$scarlet() {
            return this.session;
        }

        public int hashCode() {
            return Integer.hashCode(this.retryCount) + (this.session.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("Connecting(session=");
            sb.append(this.session);
            sb.append(", retryCount=");
            return a.p(sb, this.retryCount, ')');
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/State$Destroyed;", "Lcom/tinder/scarlet/State;", "()V", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Destroyed extends State {
        @NotNull
        public static final Destroyed INSTANCE = new Destroyed();

        private Destroyed() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/State$Disconnected;", "Lcom/tinder/scarlet/State;", "()V", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Disconnected extends State {
        @NotNull
        public static final Disconnected INSTANCE = new Disconnected();

        private Disconnected() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/State$Disconnecting;", "Lcom/tinder/scarlet/State;", "()V", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Disconnecting extends State {
        @NotNull
        public static final Disconnecting INSTANCE = new Disconnecting();

        private Disconnecting() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\u0010J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/tinder/scarlet/State$WaitingToRetry;", "Lcom/tinder/scarlet/State;", "timerDisposable", "Lio/reactivex/disposables/Disposable;", "retryCount", "", "retryInMillis", "", "(Lio/reactivex/disposables/Disposable;IJ)V", "getRetryCount", "()I", "getRetryInMillis", "()J", "getTimerDisposable$scarlet", "()Lio/reactivex/disposables/Disposable;", "component1", "component1$scarlet", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WaitingToRetry extends State {
        private final int retryCount;
        private final long retryInMillis;
        @NotNull
        private final Disposable timerDisposable;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public WaitingToRetry(@NotNull Disposable disposable, int i3, long j2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(disposable, "timerDisposable");
            this.timerDisposable = disposable;
            this.retryCount = i3;
            this.retryInMillis = j2;
        }

        public static /* synthetic */ WaitingToRetry copy$default(WaitingToRetry waitingToRetry, Disposable disposable, int i3, long j2, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                disposable = waitingToRetry.timerDisposable;
            }
            if ((i4 & 2) != 0) {
                i3 = waitingToRetry.retryCount;
            }
            if ((i4 & 4) != 0) {
                j2 = waitingToRetry.retryInMillis;
            }
            return waitingToRetry.copy(disposable, i3, j2);
        }

        @NotNull
        public final Disposable component1$scarlet() {
            return this.timerDisposable;
        }

        public final int component2() {
            return this.retryCount;
        }

        public final long component3() {
            return this.retryInMillis;
        }

        @NotNull
        public final WaitingToRetry copy(@NotNull Disposable disposable, int i3, long j2) {
            Intrinsics.checkNotNullParameter(disposable, "timerDisposable");
            return new WaitingToRetry(disposable, i3, j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WaitingToRetry)) {
                return false;
            }
            WaitingToRetry waitingToRetry = (WaitingToRetry) obj;
            return Intrinsics.areEqual((Object) this.timerDisposable, (Object) waitingToRetry.timerDisposable) && this.retryCount == waitingToRetry.retryCount && this.retryInMillis == waitingToRetry.retryInMillis;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }

        public final long getRetryInMillis() {
            return this.retryInMillis;
        }

        @NotNull
        public final Disposable getTimerDisposable$scarlet() {
            return this.timerDisposable;
        }

        public int hashCode() {
            return Long.hashCode(this.retryInMillis) + androidx.compose.animation.core.a.c(this.retryCount, this.timerDisposable.hashCode() * 31, 31);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("WaitingToRetry(timerDisposable=");
            sb.append(this.timerDisposable);
            sb.append(", retryCount=");
            sb.append(this.retryCount);
            sb.append(", retryInMillis=");
            return a.q(sb, this.retryInMillis, ')');
        }
    }

    public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private State() {
    }
}
