package com.reown.android.pulse.model.properties;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b(\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¤\u0001\u00102\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00103J\u0013\u00104\u001a\u00020\u00072\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u000207HÖ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0015¨\u00069"}, d2 = {"Lcom/reown/android/pulse/model/properties/Properties;", "", "message", "", "name", "method", "connected", "", "network", "platform", "trace", "", "topic", "correlationId", "", "clientId", "direction", "userAgent", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getName", "getMethod", "getConnected", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getNetwork", "getPlatform", "getTrace", "()Ljava/util/List;", "getTopic", "getCorrelationId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getClientId", "getDirection", "getUserAgent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/reown/android/pulse/model/properties/Properties;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Properties {
    @Nullable
    private final String clientId;
    @Nullable
    private final Boolean connected;
    @Nullable
    private final Long correlationId;
    @Nullable
    private final String direction;
    @Nullable
    private final String message;
    @Nullable
    private final String method;
    @Nullable
    private final String name;
    @Nullable
    private final String network;
    @Nullable
    private final String platform;
    @Nullable
    private final String topic;
    @Nullable
    private final List<String> trace;
    @Nullable
    private final String userAgent;

    public Properties() {
        this((String) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, (List) null, (String) null, (Long) null, (String) null, (String) null, (String) null, 4095, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Properties copy$default(Properties properties, String str, String str2, String str3, Boolean bool, String str4, String str5, List list, String str6, Long l2, String str7, String str8, String str9, int i3, Object obj) {
        Properties properties2 = properties;
        int i4 = i3;
        return properties.copy((i4 & 1) != 0 ? properties2.message : str, (i4 & 2) != 0 ? properties2.name : str2, (i4 & 4) != 0 ? properties2.method : str3, (i4 & 8) != 0 ? properties2.connected : bool, (i4 & 16) != 0 ? properties2.network : str4, (i4 & 32) != 0 ? properties2.platform : str5, (i4 & 64) != 0 ? properties2.trace : list, (i4 & 128) != 0 ? properties2.topic : str6, (i4 & 256) != 0 ? properties2.correlationId : l2, (i4 & 512) != 0 ? properties2.clientId : str7, (i4 & 1024) != 0 ? properties2.direction : str8, (i4 & 2048) != 0 ? properties2.userAgent : str9);
    }

    @Nullable
    public final String component1() {
        return this.message;
    }

    @Nullable
    public final String component10() {
        return this.clientId;
    }

    @Nullable
    public final String component11() {
        return this.direction;
    }

    @Nullable
    public final String component12() {
        return this.userAgent;
    }

    @Nullable
    public final String component2() {
        return this.name;
    }

    @Nullable
    public final String component3() {
        return this.method;
    }

    @Nullable
    public final Boolean component4() {
        return this.connected;
    }

    @Nullable
    public final String component5() {
        return this.network;
    }

    @Nullable
    public final String component6() {
        return this.platform;
    }

    @Nullable
    public final List<String> component7() {
        return this.trace;
    }

    @Nullable
    public final String component8() {
        return this.topic;
    }

    @Nullable
    public final Long component9() {
        return this.correlationId;
    }

    @NotNull
    public final Properties copy(@Nullable @Json(name = "message") String str, @Nullable @Json(name = "name") String str2, @Nullable @Json(name = "method") String str3, @Nullable @Json(name = "connected") Boolean bool, @Nullable @Json(name = "network") String str4, @Nullable @Json(name = "platform") String str5, @Nullable @Json(name = "trace") List<String> list, @Nullable @Json(name = "topic") String str6, @Nullable @Json(name = "correlation_id") Long l2, @Nullable @Json(name = "client_id") String str7, @Nullable @Json(name = "direction") String str8, @Nullable @Json(name = "user_agent") String str9) {
        return new Properties(str, str2, str3, bool, str4, str5, list, str6, l2, str7, str8, str9);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Properties)) {
            return false;
        }
        Properties properties = (Properties) obj;
        return Intrinsics.areEqual((Object) this.message, (Object) properties.message) && Intrinsics.areEqual((Object) this.name, (Object) properties.name) && Intrinsics.areEqual((Object) this.method, (Object) properties.method) && Intrinsics.areEqual((Object) this.connected, (Object) properties.connected) && Intrinsics.areEqual((Object) this.network, (Object) properties.network) && Intrinsics.areEqual((Object) this.platform, (Object) properties.platform) && Intrinsics.areEqual((Object) this.trace, (Object) properties.trace) && Intrinsics.areEqual((Object) this.topic, (Object) properties.topic) && Intrinsics.areEqual((Object) this.correlationId, (Object) properties.correlationId) && Intrinsics.areEqual((Object) this.clientId, (Object) properties.clientId) && Intrinsics.areEqual((Object) this.direction, (Object) properties.direction) && Intrinsics.areEqual((Object) this.userAgent, (Object) properties.userAgent);
    }

    @Nullable
    public final String getClientId() {
        return this.clientId;
    }

    @Nullable
    public final Boolean getConnected() {
        return this.connected;
    }

    @Nullable
    public final Long getCorrelationId() {
        return this.correlationId;
    }

    @Nullable
    public final String getDirection() {
        return this.direction;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getMethod() {
        return this.method;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getNetwork() {
        return this.network;
    }

    @Nullable
    public final String getPlatform() {
        return this.platform;
    }

    @Nullable
    public final String getTopic() {
        return this.topic;
    }

    @Nullable
    public final List<String> getTrace() {
        return this.trace;
    }

    @Nullable
    public final String getUserAgent() {
        return this.userAgent;
    }

    public int hashCode() {
        String str = this.message;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.method;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.connected;
        int hashCode4 = (hashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str4 = this.network;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.platform;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        List<String> list = this.trace;
        int hashCode7 = (hashCode6 + (list == null ? 0 : list.hashCode())) * 31;
        String str6 = this.topic;
        int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Long l2 = this.correlationId;
        int hashCode9 = (hashCode8 + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str7 = this.clientId;
        int hashCode10 = (hashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.direction;
        int hashCode11 = (hashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.userAgent;
        if (str9 != null) {
            i3 = str9.hashCode();
        }
        return hashCode11 + i3;
    }

    @NotNull
    public String toString() {
        String str = this.message;
        String str2 = this.name;
        String str3 = this.method;
        Boolean bool = this.connected;
        String str4 = this.network;
        String str5 = this.platform;
        List<String> list = this.trace;
        String str6 = this.topic;
        Long l2 = this.correlationId;
        String str7 = this.clientId;
        String str8 = this.direction;
        String str9 = this.userAgent;
        StringBuilder l3 = C0118y.l("Properties(message=", str, ", name=", str2, ", method=");
        l3.append(str3);
        l3.append(", connected=");
        l3.append(bool);
        l3.append(", network=");
        b.w(l3, str4, ", platform=", str5, ", trace=");
        l3.append(list);
        l3.append(", topic=");
        l3.append(str6);
        l3.append(", correlationId=");
        l3.append(l2);
        l3.append(", clientId=");
        l3.append(str7);
        l3.append(", direction=");
        return a.r(l3, str8, ", userAgent=", str9, ")");
    }

    public Properties(@Nullable @Json(name = "message") String str, @Nullable @Json(name = "name") String str2, @Nullable @Json(name = "method") String str3, @Nullable @Json(name = "connected") Boolean bool, @Nullable @Json(name = "network") String str4, @Nullable @Json(name = "platform") String str5, @Nullable @Json(name = "trace") List<String> list, @Nullable @Json(name = "topic") String str6, @Nullable @Json(name = "correlation_id") Long l2, @Nullable @Json(name = "client_id") String str7, @Nullable @Json(name = "direction") String str8, @Nullable @Json(name = "user_agent") String str9) {
        this.message = str;
        this.name = str2;
        this.method = str3;
        this.connected = bool;
        this.network = str4;
        this.platform = str5;
        this.trace = list;
        this.topic = str6;
        this.correlationId = l2;
        this.clientId = str7;
        this.direction = str8;
        this.userAgent = str9;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Properties(String str, String str2, String str3, Boolean bool, String str4, String str5, List list, String str6, Long l2, String str7, String str8, String str9, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? null : bool, (i3 & 16) != 0 ? null : str4, (i3 & 32) != 0 ? null : str5, (i3 & 64) != 0 ? null : list, (i3 & 128) != 0 ? null : str6, (i3 & 256) != 0 ? null : l2, (i3 & 512) != 0 ? null : str7, (i3 & 1024) != 0 ? null : str8, (i3 & 2048) != 0 ? null : str9);
    }
}
