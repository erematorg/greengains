package com.reown.android.pulse.model;

import androidx.compose.animation.core.a;
import com.reown.android.internal.utils.Time;
import com.reown.android.pulse.model.properties.Props;
import com.reown.util.UtilFunctionsKt;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/reown/android/pulse/model/Event;", "", "eventId", "", "bundleId", "", "timestamp", "props", "Lcom/reown/android/pulse/model/properties/Props;", "<init>", "(JLjava/lang/String;JLcom/reown/android/pulse/model/properties/Props;)V", "getEventId", "()J", "getBundleId", "()Ljava/lang/String;", "getTimestamp", "getProps", "()Lcom/reown/android/pulse/model/properties/Props;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Event {
    @NotNull
    private final String bundleId;
    private final long eventId;
    @NotNull
    private final Props props;
    private final long timestamp;

    public Event(@Json(name = "eventId") long j2, @NotNull @Json(name = "bundleId") String str, @Json(name = "timestamp") long j3, @NotNull @Json(name = "props") Props props2) {
        Intrinsics.checkNotNullParameter(str, "bundleId");
        Intrinsics.checkNotNullParameter(props2, "props");
        this.eventId = j2;
        this.bundleId = str;
        this.timestamp = j3;
        this.props = props2;
    }

    public static /* synthetic */ Event copy$default(Event event, long j2, String str, long j3, Props props2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = event.eventId;
        }
        long j4 = j2;
        if ((i3 & 2) != 0) {
            str = event.bundleId;
        }
        String str2 = str;
        if ((i3 & 4) != 0) {
            j3 = event.timestamp;
        }
        long j5 = j3;
        if ((i3 & 8) != 0) {
            props2 = event.props;
        }
        return event.copy(j4, str2, j5, props2);
    }

    public final long component1() {
        return this.eventId;
    }

    @NotNull
    public final String component2() {
        return this.bundleId;
    }

    public final long component3() {
        return this.timestamp;
    }

    @NotNull
    public final Props component4() {
        return this.props;
    }

    @NotNull
    public final Event copy(@Json(name = "eventId") long j2, @NotNull @Json(name = "bundleId") String str, @Json(name = "timestamp") long j3, @NotNull @Json(name = "props") Props props2) {
        Intrinsics.checkNotNullParameter(str, "bundleId");
        Intrinsics.checkNotNullParameter(props2, "props");
        return new Event(j2, str, j3, props2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        return this.eventId == event.eventId && Intrinsics.areEqual((Object) this.bundleId, (Object) event.bundleId) && this.timestamp == event.timestamp && Intrinsics.areEqual((Object) this.props, (Object) event.props);
    }

    @NotNull
    public final String getBundleId() {
        return this.bundleId;
    }

    public final long getEventId() {
        return this.eventId;
    }

    @NotNull
    public final Props getProps() {
        return this.props;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return this.props.hashCode() + a.D(this.timestamp, a.i(this.bundleId, Long.hashCode(this.eventId) * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        long j2 = this.eventId;
        String str = this.bundleId;
        long j3 = this.timestamp;
        Props props2 = this.props;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "Event(eventId=", ", bundleId=", str);
        v2.append(", timestamp=");
        v2.append(j3);
        v2.append(", props=");
        v2.append(props2);
        v2.append(")");
        return v2.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Event(long j2, String str, long j3, Props props2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, str, (i3 & 4) != 0 ? Time.getCurrentTimeInSeconds() : j3, props2);
    }
}
