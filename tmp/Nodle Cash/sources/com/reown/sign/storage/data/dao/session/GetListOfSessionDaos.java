package com.reown.sign.storage.data.dao.session;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010/\u001a\u00020\rHÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\u0017\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u0017\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010HÆ\u0003J¯\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010HÆ\u0001J\u0013\u00105\u001a\u00020\r2\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u000208HÖ\u0001J\t\u00109\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010 R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u001f\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001f\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#¨\u0006:"}, d2 = {"Lcom/reown/sign/storage/data/dao/session/GetListOfSessionDaos;", "", "id", "", "topic", "", "expiry", "relay_protocol", "relay_data", "controller_key", "self_participant", "peer_participant", "is_acknowledged", "", "pairingTopic", "properties", "", "transport_type", "Lcom/reown/android/internal/common/model/TransportType;", "scoped_properties", "<init>", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/Map;Lcom/reown/android/internal/common/model/TransportType;Ljava/util/Map;)V", "getId", "()J", "getTopic", "()Ljava/lang/String;", "getExpiry", "getRelay_protocol", "getRelay_data", "getController_key", "getSelf_participant", "getPeer_participant", "()Z", "getPairingTopic", "getProperties", "()Ljava/util/Map;", "getTransport_type", "()Lcom/reown/android/internal/common/model/TransportType;", "getScoped_properties", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetListOfSessionDaos {
    @Nullable
    private final String controller_key;
    private final long expiry;
    private final long id;
    private final boolean is_acknowledged;
    @NotNull
    private final String pairingTopic;
    @Nullable
    private final String peer_participant;
    @Nullable
    private final Map<String, String> properties;
    @Nullable
    private final String relay_data;
    @NotNull
    private final String relay_protocol;
    @Nullable
    private final Map<String, String> scoped_properties;
    @NotNull
    private final String self_participant;
    @NotNull
    private final String topic;
    @Nullable
    private final TransportType transport_type;

    public GetListOfSessionDaos(long j2, @NotNull String str, long j3, @NotNull String str2, @Nullable String str3, @Nullable String str4, @NotNull String str5, @Nullable String str6, boolean z2, @NotNull String str7, @Nullable Map<String, String> map, @Nullable TransportType transportType, @Nullable Map<String, String> map2) {
        String str8 = str5;
        String str9 = str7;
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, "relay_protocol");
        Intrinsics.checkNotNullParameter(str8, "self_participant");
        Intrinsics.checkNotNullParameter(str9, "pairingTopic");
        this.id = j2;
        this.topic = str;
        this.expiry = j3;
        this.relay_protocol = str2;
        this.relay_data = str3;
        this.controller_key = str4;
        this.self_participant = str8;
        this.peer_participant = str6;
        this.is_acknowledged = z2;
        this.pairingTopic = str9;
        this.properties = map;
        this.transport_type = transportType;
        this.scoped_properties = map2;
    }

    public static /* synthetic */ GetListOfSessionDaos copy$default(GetListOfSessionDaos getListOfSessionDaos, long j2, String str, long j3, String str2, String str3, String str4, String str5, String str6, boolean z2, String str7, Map map, TransportType transportType, Map map2, int i3, Object obj) {
        GetListOfSessionDaos getListOfSessionDaos2 = getListOfSessionDaos;
        int i4 = i3;
        return getListOfSessionDaos.copy((i4 & 1) != 0 ? getListOfSessionDaos2.id : j2, (i4 & 2) != 0 ? getListOfSessionDaos2.topic : str, (i4 & 4) != 0 ? getListOfSessionDaos2.expiry : j3, (i4 & 8) != 0 ? getListOfSessionDaos2.relay_protocol : str2, (i4 & 16) != 0 ? getListOfSessionDaos2.relay_data : str3, (i4 & 32) != 0 ? getListOfSessionDaos2.controller_key : str4, (i4 & 64) != 0 ? getListOfSessionDaos2.self_participant : str5, (i4 & 128) != 0 ? getListOfSessionDaos2.peer_participant : str6, (i4 & 256) != 0 ? getListOfSessionDaos2.is_acknowledged : z2, (i4 & 512) != 0 ? getListOfSessionDaos2.pairingTopic : str7, (i4 & 1024) != 0 ? getListOfSessionDaos2.properties : map, (i4 & 2048) != 0 ? getListOfSessionDaos2.transport_type : transportType, (i4 & 4096) != 0 ? getListOfSessionDaos2.scoped_properties : map2);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component10() {
        return this.pairingTopic;
    }

    @Nullable
    public final Map<String, String> component11() {
        return this.properties;
    }

    @Nullable
    public final TransportType component12() {
        return this.transport_type;
    }

    @Nullable
    public final Map<String, String> component13() {
        return this.scoped_properties;
    }

    @NotNull
    public final String component2() {
        return this.topic;
    }

    public final long component3() {
        return this.expiry;
    }

    @NotNull
    public final String component4() {
        return this.relay_protocol;
    }

    @Nullable
    public final String component5() {
        return this.relay_data;
    }

    @Nullable
    public final String component6() {
        return this.controller_key;
    }

    @NotNull
    public final String component7() {
        return this.self_participant;
    }

    @Nullable
    public final String component8() {
        return this.peer_participant;
    }

    public final boolean component9() {
        return this.is_acknowledged;
    }

    @NotNull
    public final GetListOfSessionDaos copy(long j2, @NotNull String str, long j3, @NotNull String str2, @Nullable String str3, @Nullable String str4, @NotNull String str5, @Nullable String str6, boolean z2, @NotNull String str7, @Nullable Map<String, String> map, @Nullable TransportType transportType, @Nullable Map<String, String> map2) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, PushMessagingService.KEY_TOPIC);
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "relay_protocol");
        String str10 = str5;
        Intrinsics.checkNotNullParameter(str10, "self_participant");
        String str11 = str7;
        Intrinsics.checkNotNullParameter(str11, "pairingTopic");
        return new GetListOfSessionDaos(j2, str8, j3, str9, str3, str4, str10, str6, z2, str11, map, transportType, map2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetListOfSessionDaos)) {
            return false;
        }
        GetListOfSessionDaos getListOfSessionDaos = (GetListOfSessionDaos) obj;
        return this.id == getListOfSessionDaos.id && Intrinsics.areEqual((Object) this.topic, (Object) getListOfSessionDaos.topic) && this.expiry == getListOfSessionDaos.expiry && Intrinsics.areEqual((Object) this.relay_protocol, (Object) getListOfSessionDaos.relay_protocol) && Intrinsics.areEqual((Object) this.relay_data, (Object) getListOfSessionDaos.relay_data) && Intrinsics.areEqual((Object) this.controller_key, (Object) getListOfSessionDaos.controller_key) && Intrinsics.areEqual((Object) this.self_participant, (Object) getListOfSessionDaos.self_participant) && Intrinsics.areEqual((Object) this.peer_participant, (Object) getListOfSessionDaos.peer_participant) && this.is_acknowledged == getListOfSessionDaos.is_acknowledged && Intrinsics.areEqual((Object) this.pairingTopic, (Object) getListOfSessionDaos.pairingTopic) && Intrinsics.areEqual((Object) this.properties, (Object) getListOfSessionDaos.properties) && this.transport_type == getListOfSessionDaos.transport_type && Intrinsics.areEqual((Object) this.scoped_properties, (Object) getListOfSessionDaos.scoped_properties);
    }

    @Nullable
    public final String getController_key() {
        return this.controller_key;
    }

    public final long getExpiry() {
        return this.expiry;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getPairingTopic() {
        return this.pairingTopic;
    }

    @Nullable
    public final String getPeer_participant() {
        return this.peer_participant;
    }

    @Nullable
    public final Map<String, String> getProperties() {
        return this.properties;
    }

    @Nullable
    public final String getRelay_data() {
        return this.relay_data;
    }

    @NotNull
    public final String getRelay_protocol() {
        return this.relay_protocol;
    }

    @Nullable
    public final Map<String, String> getScoped_properties() {
        return this.scoped_properties;
    }

    @NotNull
    public final String getSelf_participant() {
        return this.self_participant;
    }

    @NotNull
    public final String getTopic() {
        return this.topic;
    }

    @Nullable
    public final TransportType getTransport_type() {
        return this.transport_type;
    }

    public int hashCode() {
        int i3 = a.i(this.relay_protocol, a.D(this.expiry, a.i(this.topic, Long.hashCode(this.id) * 31, 31), 31), 31);
        String str = this.relay_data;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.controller_key;
        int i5 = a.i(this.self_participant, (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31);
        String str3 = this.peer_participant;
        int i6 = a.i(this.pairingTopic, android.support.v4.media.session.a.f(this.is_acknowledged, (i5 + (str3 == null ? 0 : str3.hashCode())) * 31, 31), 31);
        Map<String, String> map = this.properties;
        int hashCode2 = (i6 + (map == null ? 0 : map.hashCode())) * 31;
        TransportType transportType = this.transport_type;
        int hashCode3 = (hashCode2 + (transportType == null ? 0 : transportType.hashCode())) * 31;
        Map<String, String> map2 = this.scoped_properties;
        if (map2 != null) {
            i4 = map2.hashCode();
        }
        return hashCode3 + i4;
    }

    public final boolean is_acknowledged() {
        return this.is_acknowledged;
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        String str = this.topic;
        long j3 = this.expiry;
        String str2 = this.relay_protocol;
        String str3 = this.relay_data;
        String str4 = this.controller_key;
        String str5 = this.self_participant;
        String str6 = this.peer_participant;
        boolean z2 = this.is_acknowledged;
        String str7 = this.pairingTopic;
        Map<String, String> map = this.properties;
        TransportType transportType = this.transport_type;
        Map<String, String> map2 = this.scoped_properties;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "GetListOfSessionDaos(id=", ", topic=", str);
        v2.append(", expiry=");
        v2.append(j3);
        v2.append(", relay_protocol=");
        b.w(v2, str2, ", relay_data=", str3, ", controller_key=");
        b.w(v2, str4, ", self_participant=", str5, ", peer_participant=");
        v2.append(str6);
        v2.append(", is_acknowledged=");
        v2.append(z2);
        v2.append(", pairingTopic=");
        v2.append(str7);
        v2.append(", properties=");
        v2.append(map);
        v2.append(", transport_type=");
        v2.append(transportType);
        v2.append(", scoped_properties=");
        v2.append(map2);
        v2.append(")");
        return v2.toString();
    }
}
