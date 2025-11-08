package com.reown.android.sdk.storage.data.dao;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u001aJl\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0019R\u0015\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\r\u0010\u001a¨\u0006,"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/Pairing;", "", "id", "", "topic", "", "expiry", "relay_protocol", "relay_data", "uri", "methods", "is_active", "", "is_proposal_received", "<init>", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Boolean;)V", "getId", "()J", "getTopic", "()Ljava/lang/String;", "getExpiry", "getRelay_protocol", "getRelay_data", "getUri", "getMethods", "()Z", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Boolean;)Lcom/reown/android/sdk/storage/data/dao/Pairing;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Pairing {
    private final long expiry;
    private final long id;
    private final boolean is_active;
    @Nullable
    private final Boolean is_proposal_received;
    @NotNull
    private final String methods;
    @Nullable
    private final String relay_data;
    @NotNull
    private final String relay_protocol;
    @NotNull
    private final String topic;
    @NotNull
    private final String uri;

    public Pairing(long j2, @NotNull String str, long j3, @NotNull String str2, @Nullable String str3, @NotNull String str4, @NotNull String str5, boolean z2, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, "relay_protocol");
        Intrinsics.checkNotNullParameter(str4, "uri");
        Intrinsics.checkNotNullParameter(str5, "methods");
        this.id = j2;
        this.topic = str;
        this.expiry = j3;
        this.relay_protocol = str2;
        this.relay_data = str3;
        this.uri = str4;
        this.methods = str5;
        this.is_active = z2;
        this.is_proposal_received = bool;
    }

    public static /* synthetic */ Pairing copy$default(Pairing pairing, long j2, String str, long j3, String str2, String str3, String str4, String str5, boolean z2, Boolean bool, int i3, Object obj) {
        Pairing pairing2 = pairing;
        int i4 = i3;
        return pairing.copy((i4 & 1) != 0 ? pairing2.id : j2, (i4 & 2) != 0 ? pairing2.topic : str, (i4 & 4) != 0 ? pairing2.expiry : j3, (i4 & 8) != 0 ? pairing2.relay_protocol : str2, (i4 & 16) != 0 ? pairing2.relay_data : str3, (i4 & 32) != 0 ? pairing2.uri : str4, (i4 & 64) != 0 ? pairing2.methods : str5, (i4 & 128) != 0 ? pairing2.is_active : z2, (i4 & 256) != 0 ? pairing2.is_proposal_received : bool);
    }

    public final long component1() {
        return this.id;
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

    @NotNull
    public final String component6() {
        return this.uri;
    }

    @NotNull
    public final String component7() {
        return this.methods;
    }

    public final boolean component8() {
        return this.is_active;
    }

    @Nullable
    public final Boolean component9() {
        return this.is_proposal_received;
    }

    @NotNull
    public final Pairing copy(long j2, @NotNull String str, long j3, @NotNull String str2, @Nullable String str3, @NotNull String str4, @NotNull String str5, boolean z2, @Nullable Boolean bool) {
        String str6 = str;
        Intrinsics.checkNotNullParameter(str6, PushMessagingService.KEY_TOPIC);
        String str7 = str2;
        Intrinsics.checkNotNullParameter(str7, "relay_protocol");
        String str8 = str4;
        Intrinsics.checkNotNullParameter(str8, "uri");
        String str9 = str5;
        Intrinsics.checkNotNullParameter(str9, "methods");
        return new Pairing(j2, str6, j3, str7, str3, str8, str9, z2, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pairing)) {
            return false;
        }
        Pairing pairing = (Pairing) obj;
        return this.id == pairing.id && Intrinsics.areEqual((Object) this.topic, (Object) pairing.topic) && this.expiry == pairing.expiry && Intrinsics.areEqual((Object) this.relay_protocol, (Object) pairing.relay_protocol) && Intrinsics.areEqual((Object) this.relay_data, (Object) pairing.relay_data) && Intrinsics.areEqual((Object) this.uri, (Object) pairing.uri) && Intrinsics.areEqual((Object) this.methods, (Object) pairing.methods) && this.is_active == pairing.is_active && Intrinsics.areEqual((Object) this.is_proposal_received, (Object) pairing.is_proposal_received);
    }

    public final long getExpiry() {
        return this.expiry;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getMethods() {
        return this.methods;
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

    public int hashCode() {
        int i3 = a.i(this.relay_protocol, a.D(this.expiry, a.i(this.topic, Long.hashCode(this.id) * 31, 31), 31), 31);
        String str = this.relay_data;
        int i4 = 0;
        int f2 = android.support.v4.media.session.a.f(this.is_active, a.i(this.methods, a.i(this.uri, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31), 31), 31);
        Boolean bool = this.is_proposal_received;
        if (bool != null) {
            i4 = bool.hashCode();
        }
        return f2 + i4;
    }

    public final boolean is_active() {
        return this.is_active;
    }

    @Nullable
    public final Boolean is_proposal_received() {
        return this.is_proposal_received;
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        String str = this.topic;
        long j3 = this.expiry;
        String str2 = this.relay_protocol;
        String str3 = this.relay_data;
        String str4 = this.uri;
        String str5 = this.methods;
        boolean z2 = this.is_active;
        Boolean bool = this.is_proposal_received;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "Pairing(id=", ", topic=", str);
        v2.append(", expiry=");
        v2.append(j3);
        v2.append(", relay_protocol=");
        b.w(v2, str2, ", relay_data=", str3, ", uri=");
        b.w(v2, str4, ", methods=", str5, ", is_active=");
        v2.append(z2);
        v2.append(", is_proposal_received=");
        v2.append(bool);
        v2.append(")");
        return v2.toString();
    }
}
