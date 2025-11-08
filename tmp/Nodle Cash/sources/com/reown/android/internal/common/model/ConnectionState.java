package com.reown.android.internal.common.model;

import A.a;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0015B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/model/ConnectionState;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "isAvailable", "", "reason", "Lcom/reown/android/internal/common/model/ConnectionState$Reason;", "<init>", "(ZLcom/reown/android/internal/common/model/ConnectionState$Reason;)V", "()Z", "getReason", "()Lcom/reown/android/internal/common/model/ConnectionState$Reason;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Reason", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConnectionState implements EngineEvent {
    private final boolean isAvailable;
    @Nullable
    private final Reason reason;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/internal/common/model/ConnectionState$Reason;", "", "<init>", "()V", "ConnectionClosed", "ConnectionFailed", "Lcom/reown/android/internal/common/model/ConnectionState$Reason$ConnectionClosed;", "Lcom/reown/android/internal/common/model/ConnectionState$Reason$ConnectionFailed;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Reason {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/ConnectionState$Reason$ConnectionClosed;", "Lcom/reown/android/internal/common/model/ConnectionState$Reason;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ConnectionClosed extends Reason {
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ConnectionClosed(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ ConnectionClosed copy$default(ConnectionClosed connectionClosed, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = connectionClosed.message;
                }
                return connectionClosed.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final ConnectionClosed copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new ConnectionClosed(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ConnectionClosed) && Intrinsics.areEqual((Object) this.message, (Object) ((ConnectionClosed) obj).message);
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("ConnectionClosed(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/internal/common/model/ConnectionState$Reason$ConnectionFailed;", "Lcom/reown/android/internal/common/model/ConnectionState$Reason;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ConnectionFailed extends Reason {
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

        public /* synthetic */ Reason(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Reason() {
        }
    }

    public ConnectionState(boolean z2, @Nullable Reason reason2) {
        this.isAvailable = z2;
        this.reason = reason2;
    }

    public static /* synthetic */ ConnectionState copy$default(ConnectionState connectionState, boolean z2, Reason reason2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z2 = connectionState.isAvailable;
        }
        if ((i3 & 2) != 0) {
            reason2 = connectionState.reason;
        }
        return connectionState.copy(z2, reason2);
    }

    public final boolean component1() {
        return this.isAvailable;
    }

    @Nullable
    public final Reason component2() {
        return this.reason;
    }

    @NotNull
    public final ConnectionState copy(boolean z2, @Nullable Reason reason2) {
        return new ConnectionState(z2, reason2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConnectionState)) {
            return false;
        }
        ConnectionState connectionState = (ConnectionState) obj;
        return this.isAvailable == connectionState.isAvailable && Intrinsics.areEqual((Object) this.reason, (Object) connectionState.reason);
    }

    @Nullable
    public final Reason getReason() {
        return this.reason;
    }

    public int hashCode() {
        int hashCode = Boolean.hashCode(this.isAvailable) * 31;
        Reason reason2 = this.reason;
        return hashCode + (reason2 == null ? 0 : reason2.hashCode());
    }

    public final boolean isAvailable() {
        return this.isAvailable;
    }

    @NotNull
    public String toString() {
        boolean z2 = this.isAvailable;
        Reason reason2 = this.reason;
        return "ConnectionState(isAvailable=" + z2 + ", reason=" + reason2 + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ConnectionState(boolean z2, Reason reason2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(z2, (i3 & 2) != 0 ? null : reason2);
    }
}
