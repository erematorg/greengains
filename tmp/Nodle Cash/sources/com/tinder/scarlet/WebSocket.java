package com.tinder.scarlet;

import androidx.compose.runtime.b;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u000e\u000fJ\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u0010"}, d2 = {"Lcom/tinder/scarlet/WebSocket;", "", "cancel", "", "close", "", "shutdownReason", "Lcom/tinder/scarlet/ShutdownReason;", "open", "Lcom/tinder/scarlet/Stream;", "Lcom/tinder/scarlet/WebSocket$Event;", "send", "message", "Lcom/tinder/scarlet/Message;", "Event", "Factory", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface WebSocket {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0005\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/tinder/scarlet/WebSocket$Event;", "", "()V", "OnConnectionClosed", "OnConnectionClosing", "OnConnectionFailed", "OnConnectionOpened", "OnMessageReceived", "Lcom/tinder/scarlet/WebSocket$Event$OnConnectionClosed;", "Lcom/tinder/scarlet/WebSocket$Event$OnConnectionClosing;", "Lcom/tinder/scarlet/WebSocket$Event$OnConnectionFailed;", "Lcom/tinder/scarlet/WebSocket$Event$OnConnectionOpened;", "Lcom/tinder/scarlet/WebSocket$Event$OnMessageReceived;", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class Event {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/tinder/scarlet/WebSocket$Event$OnConnectionClosed;", "Lcom/tinder/scarlet/WebSocket$Event;", "shutdownReason", "Lcom/tinder/scarlet/ShutdownReason;", "(Lcom/tinder/scarlet/ShutdownReason;)V", "getShutdownReason", "()Lcom/tinder/scarlet/ShutdownReason;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class OnConnectionClosed extends Event {
            @NotNull
            private final ShutdownReason shutdownReason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public OnConnectionClosed(@NotNull ShutdownReason shutdownReason2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                this.shutdownReason = shutdownReason2;
            }

            public static /* synthetic */ OnConnectionClosed copy$default(OnConnectionClosed onConnectionClosed, ShutdownReason shutdownReason2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    shutdownReason2 = onConnectionClosed.shutdownReason;
                }
                return onConnectionClosed.copy(shutdownReason2);
            }

            @NotNull
            public final ShutdownReason component1() {
                return this.shutdownReason;
            }

            @NotNull
            public final OnConnectionClosed copy(@NotNull ShutdownReason shutdownReason2) {
                Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                return new OnConnectionClosed(shutdownReason2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OnConnectionClosed) && Intrinsics.areEqual((Object) this.shutdownReason, (Object) ((OnConnectionClosed) obj).shutdownReason);
            }

            @NotNull
            public final ShutdownReason getShutdownReason() {
                return this.shutdownReason;
            }

            public int hashCode() {
                return this.shutdownReason.hashCode();
            }

            @NotNull
            public String toString() {
                return "OnConnectionClosed(shutdownReason=" + this.shutdownReason + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/tinder/scarlet/WebSocket$Event$OnConnectionClosing;", "Lcom/tinder/scarlet/WebSocket$Event;", "shutdownReason", "Lcom/tinder/scarlet/ShutdownReason;", "(Lcom/tinder/scarlet/ShutdownReason;)V", "getShutdownReason", "()Lcom/tinder/scarlet/ShutdownReason;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class OnConnectionClosing extends Event {
            @NotNull
            private final ShutdownReason shutdownReason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public OnConnectionClosing(@NotNull ShutdownReason shutdownReason2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                this.shutdownReason = shutdownReason2;
            }

            public static /* synthetic */ OnConnectionClosing copy$default(OnConnectionClosing onConnectionClosing, ShutdownReason shutdownReason2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    shutdownReason2 = onConnectionClosing.shutdownReason;
                }
                return onConnectionClosing.copy(shutdownReason2);
            }

            @NotNull
            public final ShutdownReason component1() {
                return this.shutdownReason;
            }

            @NotNull
            public final OnConnectionClosing copy(@NotNull ShutdownReason shutdownReason2) {
                Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                return new OnConnectionClosing(shutdownReason2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OnConnectionClosing) && Intrinsics.areEqual((Object) this.shutdownReason, (Object) ((OnConnectionClosing) obj).shutdownReason);
            }

            @NotNull
            public final ShutdownReason getShutdownReason() {
                return this.shutdownReason;
            }

            public int hashCode() {
                return this.shutdownReason.hashCode();
            }

            @NotNull
            public String toString() {
                return "OnConnectionClosing(shutdownReason=" + this.shutdownReason + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/tinder/scarlet/WebSocket$Event$OnConnectionFailed;", "Lcom/tinder/scarlet/WebSocket$Event;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class OnConnectionFailed extends Event {
            @NotNull
            private final Throwable throwable;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public OnConnectionFailed(@NotNull Throwable th) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(th, "throwable");
                this.throwable = th;
            }

            public static /* synthetic */ OnConnectionFailed copy$default(OnConnectionFailed onConnectionFailed, Throwable th, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    th = onConnectionFailed.throwable;
                }
                return onConnectionFailed.copy(th);
            }

            @NotNull
            public final Throwable component1() {
                return this.throwable;
            }

            @NotNull
            public final OnConnectionFailed copy(@NotNull Throwable th) {
                Intrinsics.checkNotNullParameter(th, "throwable");
                return new OnConnectionFailed(th);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OnConnectionFailed) && Intrinsics.areEqual((Object) this.throwable, (Object) ((OnConnectionFailed) obj).throwable);
            }

            @NotNull
            public final Throwable getThrowable() {
                return this.throwable;
            }

            public int hashCode() {
                return this.throwable.hashCode();
            }

            @NotNull
            public String toString() {
                return "OnConnectionFailed(throwable=" + this.throwable + ')';
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/tinder/scarlet/WebSocket$Event$OnConnectionOpened;", "WEB_SOCKET", "", "Lcom/tinder/scarlet/WebSocket$Event;", "webSocket", "(Ljava/lang/Object;)V", "getWebSocket", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/tinder/scarlet/WebSocket$Event$OnConnectionOpened;", "equals", "", "other", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class OnConnectionOpened<WEB_SOCKET> extends Event {
            @NotNull
            private final WEB_SOCKET webSocket;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public OnConnectionOpened(@NotNull WEB_SOCKET web_socket) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(web_socket, "webSocket");
                this.webSocket = web_socket;
            }

            public static /* synthetic */ OnConnectionOpened copy$default(OnConnectionOpened onConnectionOpened, WEB_SOCKET web_socket, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    web_socket = onConnectionOpened.webSocket;
                }
                return onConnectionOpened.copy(web_socket);
            }

            @NotNull
            public final WEB_SOCKET component1() {
                return this.webSocket;
            }

            @NotNull
            public final OnConnectionOpened<WEB_SOCKET> copy(@NotNull WEB_SOCKET web_socket) {
                Intrinsics.checkNotNullParameter(web_socket, "webSocket");
                return new OnConnectionOpened<>(web_socket);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OnConnectionOpened) && Intrinsics.areEqual((Object) this.webSocket, (Object) ((OnConnectionOpened) obj).webSocket);
            }

            @NotNull
            public final WEB_SOCKET getWebSocket() {
                return this.webSocket;
            }

            public int hashCode() {
                return this.webSocket.hashCode();
            }

            @NotNull
            public String toString() {
                return b.c(new StringBuilder("OnConnectionOpened(webSocket="), this.webSocket, ')');
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/tinder/scarlet/WebSocket$Event$OnMessageReceived;", "Lcom/tinder/scarlet/WebSocket$Event;", "message", "Lcom/tinder/scarlet/Message;", "(Lcom/tinder/scarlet/Message;)V", "getMessage", "()Lcom/tinder/scarlet/Message;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class OnMessageReceived extends Event {
            @NotNull
            private final Message message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public OnMessageReceived(@NotNull Message message2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(message2, PushMessagingService.KEY_MESSAGE);
                this.message = message2;
            }

            public static /* synthetic */ OnMessageReceived copy$default(OnMessageReceived onMessageReceived, Message message2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    message2 = onMessageReceived.message;
                }
                return onMessageReceived.copy(message2);
            }

            @NotNull
            public final Message component1() {
                return this.message;
            }

            @NotNull
            public final OnMessageReceived copy(@NotNull Message message2) {
                Intrinsics.checkNotNullParameter(message2, PushMessagingService.KEY_MESSAGE);
                return new OnMessageReceived(message2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OnMessageReceived) && Intrinsics.areEqual((Object) this.message, (Object) ((OnMessageReceived) obj).message);
            }

            @NotNull
            public final Message getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return "OnMessageReceived(message=" + this.message + ')';
            }
        }

        public /* synthetic */ Event(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Event() {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/tinder/scarlet/WebSocket$Factory;", "", "create", "Lcom/tinder/scarlet/WebSocket;", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Factory {
        @NotNull
        WebSocket create();
    }

    void cancel();

    boolean close(@NotNull ShutdownReason shutdownReason);

    @NotNull
    Stream<Event> open();

    boolean send(@NotNull Message message);
}
