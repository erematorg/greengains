package com.pandulapeter.beagle.commonBase.model;

import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/pandulapeter/beagle/commonBase/model/CrashLogEntry;", "", "id", "", "exception", "stacktrace", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getException", "()Ljava/lang/String;", "getId", "getStacktrace", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "internal-common-base"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class CrashLogEntry {
    @NotNull
    private final String exception;
    @NotNull
    private final String id;
    @NotNull
    private final String stacktrace;
    private final long timestamp;

    public CrashLogEntry(@NotNull String str, @NotNull String str2, @NotNull String str3, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "exception");
        Intrinsics.checkNotNullParameter(str3, "stacktrace");
        this.id = str;
        this.exception = str2;
        this.stacktrace = str3;
        this.timestamp = j2;
    }

    public static /* synthetic */ CrashLogEntry copy$default(CrashLogEntry crashLogEntry, String str, String str2, String str3, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = crashLogEntry.id;
        }
        if ((i3 & 2) != 0) {
            str2 = crashLogEntry.exception;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            str3 = crashLogEntry.stacktrace;
        }
        String str5 = str3;
        if ((i3 & 8) != 0) {
            j2 = crashLogEntry.timestamp;
        }
        return crashLogEntry.copy(str, str4, str5, j2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.exception;
    }

    @NotNull
    public final String component3() {
        return this.stacktrace;
    }

    public final long component4() {
        return this.timestamp;
    }

    @NotNull
    public final CrashLogEntry copy(@NotNull String str, @NotNull String str2, @NotNull String str3, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "exception");
        Intrinsics.checkNotNullParameter(str3, "stacktrace");
        return new CrashLogEntry(str, str2, str3, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CrashLogEntry)) {
            return false;
        }
        CrashLogEntry crashLogEntry = (CrashLogEntry) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) crashLogEntry.id) && Intrinsics.areEqual((Object) this.exception, (Object) crashLogEntry.exception) && Intrinsics.areEqual((Object) this.stacktrace, (Object) crashLogEntry.stacktrace) && this.timestamp == crashLogEntry.timestamp;
    }

    @NotNull
    public final String getException() {
        return this.exception;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getStacktrace() {
        return this.stacktrace;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return Long.hashCode(this.timestamp) + a.i(this.stacktrace, a.i(this.exception, this.id.hashCode() * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("CrashLogEntry(id=");
        sb.append(this.id);
        sb.append(", exception=");
        sb.append(this.exception);
        sb.append(", stacktrace=");
        sb.append(this.stacktrace);
        sb.append(", timestamp=");
        return android.support.v4.media.session.a.q(sb, this.timestamp, ')');
    }
}
