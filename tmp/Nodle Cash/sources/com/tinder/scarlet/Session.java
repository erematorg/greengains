package com.tinder.scarlet;

import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/tinder/scarlet/Session;", "", "webSocket", "Lcom/tinder/scarlet/WebSocket;", "webSocketDisposable", "Lio/reactivex/disposables/Disposable;", "(Lcom/tinder/scarlet/WebSocket;Lio/reactivex/disposables/Disposable;)V", "getWebSocket", "()Lcom/tinder/scarlet/WebSocket;", "getWebSocketDisposable", "()Lio/reactivex/disposables/Disposable;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Session {
    @NotNull
    private final WebSocket webSocket;
    @NotNull
    private final Disposable webSocketDisposable;

    public Session(@NotNull WebSocket webSocket2, @NotNull Disposable disposable) {
        Intrinsics.checkNotNullParameter(webSocket2, "webSocket");
        Intrinsics.checkNotNullParameter(disposable, "webSocketDisposable");
        this.webSocket = webSocket2;
        this.webSocketDisposable = disposable;
    }

    public static /* synthetic */ Session copy$default(Session session, WebSocket webSocket2, Disposable disposable, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            webSocket2 = session.webSocket;
        }
        if ((i3 & 2) != 0) {
            disposable = session.webSocketDisposable;
        }
        return session.copy(webSocket2, disposable);
    }

    @NotNull
    public final WebSocket component1() {
        return this.webSocket;
    }

    @NotNull
    public final Disposable component2() {
        return this.webSocketDisposable;
    }

    @NotNull
    public final Session copy(@NotNull WebSocket webSocket2, @NotNull Disposable disposable) {
        Intrinsics.checkNotNullParameter(webSocket2, "webSocket");
        Intrinsics.checkNotNullParameter(disposable, "webSocketDisposable");
        return new Session(webSocket2, disposable);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Session)) {
            return false;
        }
        Session session = (Session) obj;
        return Intrinsics.areEqual((Object) this.webSocket, (Object) session.webSocket) && Intrinsics.areEqual((Object) this.webSocketDisposable, (Object) session.webSocketDisposable);
    }

    @NotNull
    public final WebSocket getWebSocket() {
        return this.webSocket;
    }

    @NotNull
    public final Disposable getWebSocketDisposable() {
        return this.webSocketDisposable;
    }

    public int hashCode() {
        return this.webSocketDisposable.hashCode() + (this.webSocket.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "Session(webSocket=" + this.webSocket + ", webSocketDisposable=" + this.webSocketDisposable + ')';
    }
}
