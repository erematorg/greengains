package com.reown.android.relay;

import A.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/relay/WSSConnectionState;", "", "<init>", "()V", "Connected", "Disconnected", "Lcom/reown/android/relay/WSSConnectionState$Connected;", "Lcom/reown/android/relay/WSSConnectionState$Disconnected;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class WSSConnectionState {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/reown/android/relay/WSSConnectionState$Connected;", "Lcom/reown/android/relay/WSSConnectionState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Connected extends WSSConnectionState {
        @NotNull
        public static final Connected INSTANCE = new Connected();

        private Connected() {
            super((DefaultConstructorMarker) null);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || (obj instanceof Connected);
        }

        public int hashCode() {
            return 873344283;
        }

        @NotNull
        public String toString() {
            return "Connected";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/relay/WSSConnectionState$Disconnected;", "Lcom/reown/android/relay/WSSConnectionState;", "<init>", "()V", "ConnectionFailed", "ConnectionClosed", "Lcom/reown/android/relay/WSSConnectionState$Disconnected$ConnectionClosed;", "Lcom/reown/android/relay/WSSConnectionState$Disconnected$ConnectionFailed;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Disconnected extends WSSConnectionState {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/relay/WSSConnectionState$Disconnected$ConnectionClosed;", "Lcom/reown/android/relay/WSSConnectionState$Disconnected;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ConnectionClosed extends Disconnected {
            @Nullable
            private final String message;

            public ConnectionClosed() {
                this((String) null, 1, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ ConnectionClosed copy$default(ConnectionClosed connectionClosed, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = connectionClosed.message;
                }
                return connectionClosed.copy(str);
            }

            @Nullable
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final ConnectionClosed copy(@Nullable String str) {
                return new ConnectionClosed(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ConnectionClosed) && Intrinsics.areEqual((Object) this.message, (Object) ((ConnectionClosed) obj).message);
            }

            @Nullable
            public final String getMessage() {
                return this.message;
            }

            public int hashCode() {
                String str = this.message;
                if (str == null) {
                    return 0;
                }
                return str.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("ConnectionClosed(message=", this.message, ")");
            }

            public ConnectionClosed(@Nullable String str) {
                super((DefaultConstructorMarker) null);
                this.message = str;
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ ConnectionClosed(String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? null : str);
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/relay/WSSConnectionState$Disconnected$ConnectionFailed;", "Lcom/reown/android/relay/WSSConnectionState$Disconnected;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ConnectionFailed extends Disconnected {
            @NotNull
            private final Throwable throwable;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ConnectionFailed(@NotNull Throwable th) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(th, "throwable");
                this.throwable = th;
            }

            public static /* synthetic */ ConnectionFailed copy$default(ConnectionFailed connectionFailed, Throwable th, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    th = connectionFailed.throwable;
                }
                return connectionFailed.copy(th);
            }

            @NotNull
            public final Throwable component1() {
                return this.throwable;
            }

            @NotNull
            public final ConnectionFailed copy(@NotNull Throwable th) {
                Intrinsics.checkNotNullParameter(th, "throwable");
                return new ConnectionFailed(th);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ConnectionFailed) && Intrinsics.areEqual((Object) this.throwable, (Object) ((ConnectionFailed) obj).throwable);
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
                Throwable th = this.throwable;
                return "ConnectionFailed(throwable=" + th + ")";
            }
        }

        public /* synthetic */ Disconnected(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Disconnected() {
            super((DefaultConstructorMarker) null);
        }
    }

    public /* synthetic */ WSSConnectionState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private WSSConnectionState() {
    }
}
