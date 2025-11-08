package com.reown.android.sdk.storage.data.dao;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b$\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00101J\u0013\u00102\u001a\u00020\u000b2\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\n\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015¨\u00067"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/GetPairingByTopic;", "", "topic", "", "expiry", "", "relay_protocol", "relay_data", "uri", "methods", "is_proposal_received", "", "name", "description", "url", "icons", "", "native", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getExpiry", "()J", "getRelay_protocol", "getRelay_data", "getUri", "getMethods", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getNative", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Lcom/reown/android/sdk/storage/data/dao/GetPairingByTopic;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetPairingByTopic {
    @Nullable
    private final String description;
    private final long expiry;
    @Nullable
    private final List<String> icons;
    @Nullable
    private final Boolean is_proposal_received;
    @NotNull
    private final String methods;
    @Nullable
    private final String name;
    @Nullable

    /* renamed from: native  reason: not valid java name */
    private final String f76native;
    @Nullable
    private final String relay_data;
    @NotNull
    private final String relay_protocol;
    @NotNull
    private final String topic;
    @NotNull
    private final String uri;
    @Nullable
    private final String url;

    public GetPairingByTopic(@NotNull String str, long j2, @NotNull String str2, @Nullable String str3, @NotNull String str4, @NotNull String str5, @Nullable Boolean bool, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list, @Nullable String str9) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, "relay_protocol");
        Intrinsics.checkNotNullParameter(str4, "uri");
        Intrinsics.checkNotNullParameter(str5, "methods");
        this.topic = str;
        this.expiry = j2;
        this.relay_protocol = str2;
        this.relay_data = str3;
        this.uri = str4;
        this.methods = str5;
        this.is_proposal_received = bool;
        this.name = str6;
        this.description = str7;
        this.url = str8;
        this.icons = list;
        this.f76native = str9;
    }

    public static /* synthetic */ GetPairingByTopic copy$default(GetPairingByTopic getPairingByTopic, String str, long j2, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, List list, String str9, int i3, Object obj) {
        GetPairingByTopic getPairingByTopic2 = getPairingByTopic;
        int i4 = i3;
        return getPairingByTopic.copy((i4 & 1) != 0 ? getPairingByTopic2.topic : str, (i4 & 2) != 0 ? getPairingByTopic2.expiry : j2, (i4 & 4) != 0 ? getPairingByTopic2.relay_protocol : str2, (i4 & 8) != 0 ? getPairingByTopic2.relay_data : str3, (i4 & 16) != 0 ? getPairingByTopic2.uri : str4, (i4 & 32) != 0 ? getPairingByTopic2.methods : str5, (i4 & 64) != 0 ? getPairingByTopic2.is_proposal_received : bool, (i4 & 128) != 0 ? getPairingByTopic2.name : str6, (i4 & 256) != 0 ? getPairingByTopic2.description : str7, (i4 & 512) != 0 ? getPairingByTopic2.url : str8, (i4 & 1024) != 0 ? getPairingByTopic2.icons : list, (i4 & 2048) != 0 ? getPairingByTopic2.f76native : str9);
    }

    @NotNull
    public final String component1() {
        return this.topic;
    }

    @Nullable
    public final String component10() {
        return this.url;
    }

    @Nullable
    public final List<String> component11() {
        return this.icons;
    }

    @Nullable
    public final String component12() {
        return this.f76native;
    }

    public final long component2() {
        return this.expiry;
    }

    @NotNull
    public final String component3() {
        return this.relay_protocol;
    }

    @Nullable
    public final String component4() {
        return this.relay_data;
    }

    @NotNull
    public final String component5() {
        return this.uri;
    }

    @NotNull
    public final String component6() {
        return this.methods;
    }

    @Nullable
    public final Boolean component7() {
        return this.is_proposal_received;
    }

    @Nullable
    public final String component8() {
        return this.name;
    }

    @Nullable
    public final String component9() {
        return this.description;
    }

    @NotNull
    public final GetPairingByTopic copy(@NotNull String str, long j2, @NotNull String str2, @Nullable String str3, @NotNull String str4, @NotNull String str5, @Nullable Boolean bool, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list, @Nullable String str9) {
        String str10 = str;
        Intrinsics.checkNotNullParameter(str10, PushMessagingService.KEY_TOPIC);
        String str11 = str2;
        Intrinsics.checkNotNullParameter(str11, "relay_protocol");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "uri");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "methods");
        return new GetPairingByTopic(str10, j2, str11, str3, str12, str13, bool, str6, str7, str8, list, str9);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetPairingByTopic)) {
            return false;
        }
        GetPairingByTopic getPairingByTopic = (GetPairingByTopic) obj;
        return Intrinsics.areEqual((Object) this.topic, (Object) getPairingByTopic.topic) && this.expiry == getPairingByTopic.expiry && Intrinsics.areEqual((Object) this.relay_protocol, (Object) getPairingByTopic.relay_protocol) && Intrinsics.areEqual((Object) this.relay_data, (Object) getPairingByTopic.relay_data) && Intrinsics.areEqual((Object) this.uri, (Object) getPairingByTopic.uri) && Intrinsics.areEqual((Object) this.methods, (Object) getPairingByTopic.methods) && Intrinsics.areEqual((Object) this.is_proposal_received, (Object) getPairingByTopic.is_proposal_received) && Intrinsics.areEqual((Object) this.name, (Object) getPairingByTopic.name) && Intrinsics.areEqual((Object) this.description, (Object) getPairingByTopic.description) && Intrinsics.areEqual((Object) this.url, (Object) getPairingByTopic.url) && Intrinsics.areEqual((Object) this.icons, (Object) getPairingByTopic.icons) && Intrinsics.areEqual((Object) this.f76native, (Object) getPairingByTopic.f76native);
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    public final long getExpiry() {
        return this.expiry;
    }

    @Nullable
    public final List<String> getIcons() {
        return this.icons;
    }

    @NotNull
    public final String getMethods() {
        return this.methods;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getNative() {
        return this.f76native;
    }

    @Nullable
    public final String getRelay_data() {
        return this.relay_data;
    }

    @NotNull
    public final String getRelay_protocol() {
        return this.relay_protocol;
    }

    @NotNull
    public final String getTopic() {
        return this.topic;
    }

    @NotNull
    public final String getUri() {
        return this.uri;
    }

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int i3 = a.i(this.relay_protocol, a.D(this.expiry, this.topic.hashCode() * 31, 31), 31);
        String str = this.relay_data;
        int i4 = 0;
        int i5 = a.i(this.methods, a.i(this.uri, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31), 31);
        Boolean bool = this.is_proposal_received;
        int hashCode = (i5 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.description;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.url;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<String> list = this.icons;
        int hashCode5 = (hashCode4 + (list == null ? 0 : list.hashCode())) * 31;
        String str5 = this.f76native;
        if (str5 != null) {
            i4 = str5.hashCode();
        }
        return hashCode5 + i4;
    }

    @Nullable
    public final Boolean is_proposal_received() {
        return this.is_proposal_received;
    }

    @NotNull
    public String toString() {
        String str = this.topic;
        long j2 = this.expiry;
        String str2 = this.relay_protocol;
        String str3 = this.relay_data;
        String str4 = this.uri;
        String str5 = this.methods;
        Boolean bool = this.is_proposal_received;
        String str6 = this.name;
        String str7 = this.description;
        String str8 = this.url;
        List<String> list = this.icons;
        String str9 = this.f76native;
        StringBuilder i3 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(j2, "GetPairingByTopic(topic=", str, ", expiry=");
        b.w(i3, ", relay_protocol=", str2, ", relay_data=", str3);
        b.w(i3, ", uri=", str4, ", methods=", str5);
        i3.append(", is_proposal_received=");
        i3.append(bool);
        i3.append(", name=");
        i3.append(str6);
        b.w(i3, ", description=", str7, ", url=", str8);
        i3.append(", icons=");
        i3.append(list);
        i3.append(", native=");
        i3.append(str9);
        i3.append(")");
        return i3.toString();
    }
}
