package com.reown.android.internal.common.model;

import androidx.compose.animation.core.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010(\u001a\u00020\u000fHÆ\u0003Je\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00060"}, d2 = {"Lcom/reown/android/internal/common/model/WCRequest;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "id", "", "method", "", "params", "Lcom/reown/android/internal/common/model/type/ClientParams;", "message", "publishedAt", "encryptedMessage", "attestation", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "<init>", "(Lcom/reown/foundation/common/model/Topic;JLjava/lang/String;Lcom/reown/android/internal/common/model/type/ClientParams;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/TransportType;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getId", "()J", "getMethod", "()Ljava/lang/String;", "getParams", "()Lcom/reown/android/internal/common/model/type/ClientParams;", "getMessage", "getPublishedAt", "getEncryptedMessage", "getAttestation", "getTransportType", "()Lcom/reown/android/internal/common/model/TransportType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WCRequest {
    @Nullable
    private final String attestation;
    @NotNull
    private final String encryptedMessage;
    private final long id;
    @NotNull
    private final String message;
    @NotNull
    private final String method;
    @NotNull
    private final ClientParams params;
    private final long publishedAt;
    @NotNull
    private final Topic topic;
    @NotNull
    private final TransportType transportType;

    public WCRequest(@NotNull Topic topic2, long j2, @NotNull String str, @NotNull ClientParams clientParams, @NotNull String str2, long j3, @NotNull String str3, @Nullable String str4, @NotNull TransportType transportType2) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(clientParams, "params");
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(str3, "encryptedMessage");
        Intrinsics.checkNotNullParameter(transportType2, "transportType");
        this.topic = topic2;
        this.id = j2;
        this.method = str;
        this.params = clientParams;
        this.message = str2;
        this.publishedAt = j3;
        this.encryptedMessage = str3;
        this.attestation = str4;
        this.transportType = transportType2;
    }

    public static /* synthetic */ WCRequest copy$default(WCRequest wCRequest, Topic topic2, long j2, String str, ClientParams clientParams, String str2, long j3, String str3, String str4, TransportType transportType2, int i3, Object obj) {
        WCRequest wCRequest2 = wCRequest;
        int i4 = i3;
        return wCRequest.copy((i4 & 1) != 0 ? wCRequest2.topic : topic2, (i4 & 2) != 0 ? wCRequest2.id : j2, (i4 & 4) != 0 ? wCRequest2.method : str, (i4 & 8) != 0 ? wCRequest2.params : clientParams, (i4 & 16) != 0 ? wCRequest2.message : str2, (i4 & 32) != 0 ? wCRequest2.publishedAt : j3, (i4 & 64) != 0 ? wCRequest2.encryptedMessage : str3, (i4 & 128) != 0 ? wCRequest2.attestation : str4, (i4 & 256) != 0 ? wCRequest2.transportType : transportType2);
    }

    @NotNull
    public final Topic component1() {
        return this.topic;
    }

    public final long component2() {
        return this.id;
    }

    @NotNull
    public final String component3() {
        return this.method;
    }

    @NotNull
    public final ClientParams component4() {
        return this.params;
    }

    @NotNull
    public final String component5() {
        return this.message;
    }

    public final long component6() {
        return this.publishedAt;
    }

    @NotNull
    public final String component7() {
        return this.encryptedMessage;
    }

    @Nullable
    public final String component8() {
        return this.attestation;
    }

    @NotNull
    public final TransportType component9() {
        return this.transportType;
    }

    @NotNull
    public final WCRequest copy(@NotNull Topic topic2, long j2, @NotNull String str, @NotNull ClientParams clientParams, @NotNull String str2, long j3, @NotNull String str3, @Nullable String str4, @NotNull TransportType transportType2) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        String str5 = str;
        Intrinsics.checkNotNullParameter(str5, FirebaseAnalytics.Param.METHOD);
        ClientParams clientParams2 = clientParams;
        Intrinsics.checkNotNullParameter(clientParams2, "params");
        String str6 = str2;
        Intrinsics.checkNotNullParameter(str6, PushMessagingService.KEY_MESSAGE);
        String str7 = str3;
        Intrinsics.checkNotNullParameter(str7, "encryptedMessage");
        TransportType transportType3 = transportType2;
        Intrinsics.checkNotNullParameter(transportType3, "transportType");
        return new WCRequest(topic2, j2, str5, clientParams2, str6, j3, str7, str4, transportType3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WCRequest)) {
            return false;
        }
        WCRequest wCRequest = (WCRequest) obj;
        return Intrinsics.areEqual((Object) this.topic, (Object) wCRequest.topic) && this.id == wCRequest.id && Intrinsics.areEqual((Object) this.method, (Object) wCRequest.method) && Intrinsics.areEqual((Object) this.params, (Object) wCRequest.params) && Intrinsics.areEqual((Object) this.message, (Object) wCRequest.message) && this.publishedAt == wCRequest.publishedAt && Intrinsics.areEqual((Object) this.encryptedMessage, (Object) wCRequest.encryptedMessage) && Intrinsics.areEqual((Object) this.attestation, (Object) wCRequest.attestation) && this.transportType == wCRequest.transportType;
    }

    @Nullable
    public final String getAttestation() {
        return this.attestation;
    }

    @NotNull
    public final String getEncryptedMessage() {
        return this.encryptedMessage;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @NotNull
    public final ClientParams getParams() {
        return this.params;
    }

    public final long getPublishedAt() {
        return this.publishedAt;
    }

    @NotNull
    public final Topic getTopic() {
        return this.topic;
    }

    @NotNull
    public final TransportType getTransportType() {
        return this.transportType;
    }

    public int hashCode() {
        int i3 = a.i(this.encryptedMessage, a.D(this.publishedAt, a.i(this.message, (this.params.hashCode() + a.i(this.method, a.D(this.id, this.topic.hashCode() * 31, 31), 31)) * 31, 31), 31), 31);
        String str = this.attestation;
        return this.transportType.hashCode() + ((i3 + (str == null ? 0 : str.hashCode())) * 31);
    }

    @NotNull
    public String toString() {
        Topic topic2 = this.topic;
        long j2 = this.id;
        String str = this.method;
        ClientParams clientParams = this.params;
        String str2 = this.message;
        long j3 = this.publishedAt;
        String str3 = this.encryptedMessage;
        String str4 = this.attestation;
        TransportType transportType2 = this.transportType;
        StringBuilder sb = new StringBuilder("WCRequest(topic=");
        sb.append(topic2);
        sb.append(", id=");
        sb.append(j2);
        sb.append(", method=");
        sb.append(str);
        sb.append(", params=");
        sb.append(clientParams);
        androidx.compose.ui.autofill.a.o(sb, ", message=", str2, ", publishedAt=");
        sb.append(j3);
        sb.append(", encryptedMessage=");
        sb.append(str3);
        sb.append(", attestation=");
        sb.append(str4);
        sb.append(", transportType=");
        sb.append(transportType2);
        sb.append(")");
        return sb.toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ WCRequest(com.reown.foundation.common.model.Topic r15, long r16, java.lang.String r18, com.reown.android.internal.common.model.type.ClientParams r19, java.lang.String r20, long r21, java.lang.String r23, java.lang.String r24, com.reown.android.internal.common.model.TransportType r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r14 = this;
            r0 = r26
            r1 = r0 & 16
            if (r1 == 0) goto L_0x000e
            kotlin.jvm.internal.StringCompanionObject r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, "<this>")
            r8 = r1
            goto L_0x0010
        L_0x000e:
            r8 = r20
        L_0x0010:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0018
            r1 = 0
            r9 = r1
            goto L_0x001a
        L_0x0018:
            r9 = r21
        L_0x001a:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0026
            kotlin.jvm.internal.StringCompanionObject r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, "<this>")
            r11 = r1
            goto L_0x0028
        L_0x0026:
            r11 = r23
        L_0x0028:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x002f
            r0 = 0
            r12 = r0
            goto L_0x0031
        L_0x002f:
            r12 = r24
        L_0x0031:
            r2 = r14
            r3 = r15
            r4 = r16
            r6 = r18
            r7 = r19
            r13 = r25
            r2.<init>(r3, r4, r6, r7, r8, r9, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.model.WCRequest.<init>(com.reown.foundation.common.model.Topic, long, java.lang.String, com.reown.android.internal.common.model.type.ClientParams, java.lang.String, long, java.lang.String, java.lang.String, com.reown.android.internal.common.model.TransportType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
