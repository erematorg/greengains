package com.pandulapeter.beagle.commonBase.model;

import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\nHÆ\u0003JL\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/pandulapeter/beagle/commonBase/model/LifecycleLogEntry;", "", "id", "", "name", "simpleName", "eventType", "hasSavedInstanceState", "", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;J)V", "getEventType", "()Ljava/lang/String;", "getHasSavedInstanceState", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getId", "getName", "getSimpleName", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;J)Lcom/pandulapeter/beagle/commonBase/model/LifecycleLogEntry;", "equals", "other", "hashCode", "", "toString", "internal-common-base"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LifecycleLogEntry {
    @NotNull
    private final String eventType;
    @Nullable
    private final Boolean hasSavedInstanceState;
    @NotNull
    private final String id;
    @NotNull
    private final String name;
    @NotNull
    private final String simpleName;
    private final long timestamp;

    public LifecycleLogEntry(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable Boolean bool, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "simpleName");
        Intrinsics.checkNotNullParameter(str4, "eventType");
        this.id = str;
        this.name = str2;
        this.simpleName = str3;
        this.eventType = str4;
        this.hasSavedInstanceState = bool;
        this.timestamp = j2;
    }

    public static /* synthetic */ LifecycleLogEntry copy$default(LifecycleLogEntry lifecycleLogEntry, String str, String str2, String str3, String str4, Boolean bool, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = lifecycleLogEntry.id;
        }
        if ((i3 & 2) != 0) {
            str2 = lifecycleLogEntry.name;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            str3 = lifecycleLogEntry.simpleName;
        }
        String str6 = str3;
        if ((i3 & 8) != 0) {
            str4 = lifecycleLogEntry.eventType;
        }
        String str7 = str4;
        if ((i3 & 16) != 0) {
            bool = lifecycleLogEntry.hasSavedInstanceState;
        }
        Boolean bool2 = bool;
        if ((i3 & 32) != 0) {
            j2 = lifecycleLogEntry.timestamp;
        }
        return lifecycleLogEntry.copy(str, str5, str6, str7, bool2, j2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.simpleName;
    }

    @NotNull
    public final String component4() {
        return this.eventType;
    }

    @Nullable
    public final Boolean component5() {
        return this.hasSavedInstanceState;
    }

    public final long component6() {
        return this.timestamp;
    }

    @NotNull
    public final LifecycleLogEntry copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable Boolean bool, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "simpleName");
        Intrinsics.checkNotNullParameter(str4, "eventType");
        return new LifecycleLogEntry(str, str2, str3, str4, bool, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LifecycleLogEntry)) {
            return false;
        }
        LifecycleLogEntry lifecycleLogEntry = (LifecycleLogEntry) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) lifecycleLogEntry.id) && Intrinsics.areEqual((Object) this.name, (Object) lifecycleLogEntry.name) && Intrinsics.areEqual((Object) this.simpleName, (Object) lifecycleLogEntry.simpleName) && Intrinsics.areEqual((Object) this.eventType, (Object) lifecycleLogEntry.eventType) && Intrinsics.areEqual((Object) this.hasSavedInstanceState, (Object) lifecycleLogEntry.hasSavedInstanceState) && this.timestamp == lifecycleLogEntry.timestamp;
    }

    @NotNull
    public final String getEventType() {
        return this.eventType;
    }

    @Nullable
    public final Boolean getHasSavedInstanceState() {
        return this.hasSavedInstanceState;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getSimpleName() {
        return this.simpleName;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i3 = a.i(this.eventType, a.i(this.simpleName, a.i(this.name, this.id.hashCode() * 31, 31), 31), 31);
        Boolean bool = this.hasSavedInstanceState;
        return Long.hashCode(this.timestamp) + ((i3 + (bool == null ? 0 : bool.hashCode())) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("LifecycleLogEntry(id=");
        sb.append(this.id);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", simpleName=");
        sb.append(this.simpleName);
        sb.append(", eventType=");
        sb.append(this.eventType);
        sb.append(", hasSavedInstanceState=");
        sb.append(this.hasSavedInstanceState);
        sb.append(", timestamp=");
        return android.support.v4.media.session.a.q(sb, this.timestamp, ')');
    }
}
