package com.pandulapeter.beagle.commonBase.model;

import androidx.compose.animation.core.a;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\nHÆ\u0003JI\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/pandulapeter/beagle/commonBase/model/LogEntry;", "", "id", "", "label", "message", "payload", "isPersisted", "", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJ)V", "getId", "()Ljava/lang/String;", "()Z", "getLabel", "getMessage", "getPayload", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "internal-common-base"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LogEntry {
    @NotNull
    private final String id;
    private final boolean isPersisted;
    @Nullable
    private final String label;
    @NotNull
    private final String message;
    @Nullable
    private final String payload;
    private final long timestamp;

    public LogEntry(@NotNull String str, @Nullable String str2, @NotNull String str3, @Nullable String str4, boolean z2, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_MESSAGE);
        this.id = str;
        this.label = str2;
        this.message = str3;
        this.payload = str4;
        this.isPersisted = z2;
        this.timestamp = j2;
    }

    public static /* synthetic */ LogEntry copy$default(LogEntry logEntry, String str, String str2, String str3, String str4, boolean z2, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = logEntry.id;
        }
        if ((i3 & 2) != 0) {
            str2 = logEntry.label;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            str3 = logEntry.message;
        }
        String str6 = str3;
        if ((i3 & 8) != 0) {
            str4 = logEntry.payload;
        }
        String str7 = str4;
        if ((i3 & 16) != 0) {
            z2 = logEntry.isPersisted;
        }
        boolean z3 = z2;
        if ((i3 & 32) != 0) {
            j2 = logEntry.timestamp;
        }
        return logEntry.copy(str, str5, str6, str7, z3, j2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final String component2() {
        return this.label;
    }

    @NotNull
    public final String component3() {
        return this.message;
    }

    @Nullable
    public final String component4() {
        return this.payload;
    }

    public final boolean component5() {
        return this.isPersisted;
    }

    public final long component6() {
        return this.timestamp;
    }

    @NotNull
    public final LogEntry copy(@NotNull String str, @Nullable String str2, @NotNull String str3, @Nullable String str4, boolean z2, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_MESSAGE);
        return new LogEntry(str, str2, str3, str4, z2, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogEntry)) {
            return false;
        }
        LogEntry logEntry = (LogEntry) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) logEntry.id) && Intrinsics.areEqual((Object) this.label, (Object) logEntry.label) && Intrinsics.areEqual((Object) this.message, (Object) logEntry.message) && Intrinsics.areEqual((Object) this.payload, (Object) logEntry.payload) && this.isPersisted == logEntry.isPersisted && this.timestamp == logEntry.timestamp;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final String getLabel() {
        return this.label;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getPayload() {
        return this.payload;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.label;
        int i3 = 0;
        int i4 = a.i(this.message, (hashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        String str2 = this.payload;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        int i5 = (i4 + i3) * 31;
        boolean z2 = this.isPersisted;
        if (z2) {
            z2 = true;
        }
        return Long.hashCode(this.timestamp) + ((i5 + (z2 ? 1 : 0)) * 31);
    }

    public final boolean isPersisted() {
        return this.isPersisted;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("LogEntry(id=");
        sb.append(this.id);
        sb.append(", label=");
        sb.append(this.label);
        sb.append(", message=");
        sb.append(this.message);
        sb.append(", payload=");
        sb.append(this.payload);
        sb.append(", isPersisted=");
        sb.append(this.isPersisted);
        sb.append(", timestamp=");
        return android.support.v4.media.session.a.q(sb, this.timestamp, ')');
    }
}
