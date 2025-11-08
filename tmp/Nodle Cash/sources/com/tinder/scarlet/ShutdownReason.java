package com.tinder.scarlet;

import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/tinder/scarlet/ShutdownReason;", "", "code", "", "reason", "", "(ILjava/lang/String;)V", "getCode", "()I", "getReason", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ShutdownReason {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    @JvmField
    public static final ShutdownReason GRACEFUL = new ShutdownReason(1000, "Normal closure");
    @NotNull
    private static final String NORMAL_CLOSURE_REASON = "Normal closure";
    private static final int NORMAL_CLOSURE_STATUS_CODE = 1000;
    private final int code;
    @NotNull
    private final String reason;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/tinder/scarlet/ShutdownReason$Companion;", "", "()V", "GRACEFUL", "Lcom/tinder/scarlet/ShutdownReason;", "NORMAL_CLOSURE_REASON", "", "NORMAL_CLOSURE_STATUS_CODE", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public ShutdownReason(int i3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "reason");
        this.code = i3;
        this.reason = str;
    }

    public static /* synthetic */ ShutdownReason copy$default(ShutdownReason shutdownReason, int i3, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = shutdownReason.code;
        }
        if ((i4 & 2) != 0) {
            str = shutdownReason.reason;
        }
        return shutdownReason.copy(i3, str);
    }

    public final int component1() {
        return this.code;
    }

    @NotNull
    public final String component2() {
        return this.reason;
    }

    @NotNull
    public final ShutdownReason copy(int i3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "reason");
        return new ShutdownReason(i3, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShutdownReason)) {
            return false;
        }
        ShutdownReason shutdownReason = (ShutdownReason) obj;
        return this.code == shutdownReason.code && Intrinsics.areEqual((Object) this.reason, (Object) shutdownReason.reason);
    }

    public final int getCode() {
        return this.code;
    }

    @NotNull
    public final String getReason() {
        return this.reason;
    }

    public int hashCode() {
        return this.reason.hashCode() + (Integer.hashCode(this.code) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("ShutdownReason(code=");
        sb.append(this.code);
        sb.append(", reason=");
        return a.o(')', this.reason, sb);
    }
}
