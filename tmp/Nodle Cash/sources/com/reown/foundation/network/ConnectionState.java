package com.reown.foundation.network;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/ConnectionState;", "", "<init>", "()V", "Open", "Closed", "Idle", "Lcom/reown/foundation/network/ConnectionState$Closed;", "Lcom/reown/foundation/network/ConnectionState$Idle;", "Lcom/reown/foundation/network/ConnectionState$Open;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ConnectionState {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/foundation/network/ConnectionState$Closed;", "Lcom/reown/foundation/network/ConnectionState;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Closed extends ConnectionState {
        @NotNull
        private final Throwable throwable;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Closed(@NotNull Throwable th) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(th, "throwable");
            this.throwable = th;
        }

        public static /* synthetic */ Closed copy$default(Closed closed, Throwable th, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                th = closed.throwable;
            }
            return closed.copy(th);
        }

        @NotNull
        public final Throwable component1() {
            return this.throwable;
        }

        @NotNull
        public final Closed copy(@NotNull Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            return new Closed(th);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Closed) && Intrinsics.areEqual((Object) this.throwable, (Object) ((Closed) obj).throwable);
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
            return "Closed(throwable=" + th + ")";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/reown/foundation/network/ConnectionState$Idle;", "Lcom/reown/foundation/network/ConnectionState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Idle extends ConnectionState {
        @NotNull
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super((DefaultConstructorMarker) null);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || (obj instanceof Idle);
        }

        public int hashCode() {
            return -670455870;
        }

        @NotNull
        public String toString() {
            return "Idle";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/reown/foundation/network/ConnectionState$Open;", "Lcom/reown/foundation/network/ConnectionState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Open extends ConnectionState {
        @NotNull
        public static final Open INSTANCE = new Open();

        private Open() {
            super((DefaultConstructorMarker) null);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || (obj instanceof Open);
        }

        public int hashCode() {
            return -670265800;
        }

        @NotNull
        public String toString() {
            return "Open";
        }
    }

    public /* synthetic */ ConnectionState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ConnectionState() {
    }
}
