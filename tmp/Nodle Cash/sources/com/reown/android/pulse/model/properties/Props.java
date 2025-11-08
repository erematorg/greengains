package com.reown.android.pulse.model.properties;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.core.app.NotificationCompat;
import com.reown.android.pulse.model.EventType;
import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/reown/android/pulse/model/properties/Props;", "", "event", "", "type", "properties", "Lcom/reown/android/pulse/model/properties/Properties;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/pulse/model/properties/Properties;)V", "getEvent", "()Ljava/lang/String;", "getType", "getProperties", "()Lcom/reown/android/pulse/model/properties/Properties;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Props {
    @NotNull
    private final String event;
    @Nullable
    private final Properties properties;
    @NotNull
    private final String type;

    public Props() {
        this((String) null, (String) null, (Properties) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Props copy$default(Props props, String str, String str2, Properties properties2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = props.event;
        }
        if ((i3 & 2) != 0) {
            str2 = props.type;
        }
        if ((i3 & 4) != 0) {
            properties2 = props.properties;
        }
        return props.copy(str, str2, properties2);
    }

    @NotNull
    public final String component1() {
        return this.event;
    }

    @NotNull
    public final String component2() {
        return this.type;
    }

    @Nullable
    public final Properties component3() {
        return this.properties;
    }

    @NotNull
    public final Props copy(@NotNull @Json(name = "event") String str, @NotNull @Json(name = "type") String str2, @Nullable @Json(name = "properties") Properties properties2) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(str2, "type");
        return new Props(str, str2, properties2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Props)) {
            return false;
        }
        Props props = (Props) obj;
        return Intrinsics.areEqual((Object) this.event, (Object) props.event) && Intrinsics.areEqual((Object) this.type, (Object) props.type) && Intrinsics.areEqual((Object) this.properties, (Object) props.properties);
    }

    @NotNull
    public final String getEvent() {
        return this.event;
    }

    @Nullable
    public final Properties getProperties() {
        return this.properties;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int i3 = a.i(this.type, this.event.hashCode() * 31, 31);
        Properties properties2 = this.properties;
        return i3 + (properties2 == null ? 0 : properties2.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.event;
        String str2 = this.type;
        Properties properties2 = this.properties;
        StringBuilder l2 = C0118y.l("Props(event=", str, ", type=", str2, ", properties=");
        l2.append(properties2);
        l2.append(")");
        return l2.toString();
    }

    public Props(@NotNull @Json(name = "event") String str, @NotNull @Json(name = "type") String str2, @Nullable @Json(name = "properties") Properties properties2) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(str2, "type");
        this.event = str;
        this.type = str2;
        this.properties = properties2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Props(String str, String str2, Properties properties2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? EventType.ERROR : str, (i3 & 2) != 0 ? Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>") : str2, (i3 & 4) != 0 ? null : properties2);
    }
}
