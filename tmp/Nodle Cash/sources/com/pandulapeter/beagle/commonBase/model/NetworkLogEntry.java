package com.pandulapeter.beagle.commonBase.model;

import androidx.compose.animation.core.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010 \u001a\u00020\u000bHÆ\u0003J^\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\u00052\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014¨\u0006("}, d2 = {"Lcom/pandulapeter/beagle/commonBase/model/NetworkLogEntry;", "", "id", "", "isOutgoing", "", "url", "payload", "headers", "", "duration", "", "timestamp", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;J)V", "getDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getHeaders", "()Ljava/util/List;", "getId", "()Ljava/lang/String;", "()Z", "getPayload", "getTimestamp", "()J", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;J)Lcom/pandulapeter/beagle/commonBase/model/NetworkLogEntry;", "equals", "other", "hashCode", "", "toString", "internal-common-base"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NetworkLogEntry {
    @Nullable
    private final Long duration;
    @NotNull
    private final List<String> headers;
    @NotNull
    private final String id;
    private final boolean isOutgoing;
    @Nullable
    private final String payload;
    private final long timestamp;
    @NotNull
    private final String url;

    public NetworkLogEntry(@NotNull String str, boolean z2, @NotNull String str2, @Nullable String str3, @NotNull List<String> list, @Nullable Long l2, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "url");
        Intrinsics.checkNotNullParameter(list, "headers");
        this.id = str;
        this.isOutgoing = z2;
        this.url = str2;
        this.payload = str3;
        this.headers = list;
        this.duration = l2;
        this.timestamp = j2;
    }

    public static /* synthetic */ NetworkLogEntry copy$default(NetworkLogEntry networkLogEntry, String str, boolean z2, String str2, String str3, List list, Long l2, long j2, int i3, Object obj) {
        NetworkLogEntry networkLogEntry2 = networkLogEntry;
        return networkLogEntry.copy((i3 & 1) != 0 ? networkLogEntry2.id : str, (i3 & 2) != 0 ? networkLogEntry2.isOutgoing : z2, (i3 & 4) != 0 ? networkLogEntry2.url : str2, (i3 & 8) != 0 ? networkLogEntry2.payload : str3, (i3 & 16) != 0 ? networkLogEntry2.headers : list, (i3 & 32) != 0 ? networkLogEntry2.duration : l2, (i3 & 64) != 0 ? networkLogEntry2.timestamp : j2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    public final boolean component2() {
        return this.isOutgoing;
    }

    @NotNull
    public final String component3() {
        return this.url;
    }

    @Nullable
    public final String component4() {
        return this.payload;
    }

    @NotNull
    public final List<String> component5() {
        return this.headers;
    }

    @Nullable
    public final Long component6() {
        return this.duration;
    }

    public final long component7() {
        return this.timestamp;
    }

    @NotNull
    public final NetworkLogEntry copy(@NotNull String str, boolean z2, @NotNull String str2, @Nullable String str3, @NotNull List<String> list, @Nullable Long l2, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "url");
        Intrinsics.checkNotNullParameter(list, "headers");
        return new NetworkLogEntry(str, z2, str2, str3, list, l2, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkLogEntry)) {
            return false;
        }
        NetworkLogEntry networkLogEntry = (NetworkLogEntry) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) networkLogEntry.id) && this.isOutgoing == networkLogEntry.isOutgoing && Intrinsics.areEqual((Object) this.url, (Object) networkLogEntry.url) && Intrinsics.areEqual((Object) this.payload, (Object) networkLogEntry.payload) && Intrinsics.areEqual((Object) this.headers, (Object) networkLogEntry.headers) && Intrinsics.areEqual((Object) this.duration, (Object) networkLogEntry.duration) && this.timestamp == networkLogEntry.timestamp;
    }

    @Nullable
    public final Long getDuration() {
        return this.duration;
    }

    @NotNull
    public final List<String> getHeaders() {
        return this.headers;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final String getPayload() {
        return this.payload;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        boolean z2 = this.isOutgoing;
        if (z2) {
            z2 = true;
        }
        int i3 = a.i(this.url, (hashCode + (z2 ? 1 : 0)) * 31, 31);
        String str = this.payload;
        int i4 = 0;
        int j2 = a.j(this.headers, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31);
        Long l2 = this.duration;
        if (l2 != null) {
            i4 = l2.hashCode();
        }
        return Long.hashCode(this.timestamp) + ((j2 + i4) * 31);
    }

    public final boolean isOutgoing() {
        return this.isOutgoing;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("NetworkLogEntry(id=");
        sb.append(this.id);
        sb.append(", isOutgoing=");
        sb.append(this.isOutgoing);
        sb.append(", url=");
        sb.append(this.url);
        sb.append(", payload=");
        sb.append(this.payload);
        sb.append(", headers=");
        sb.append(this.headers);
        sb.append(", duration=");
        sb.append(this.duration);
        sb.append(", timestamp=");
        return android.support.v4.media.session.a.q(sb, this.timestamp, ')');
    }
}
