package com.reown.android.internal.common.model;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.internal.common.model.type.Sequence;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010B3\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0015B\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u0016¢\u0006\u0004\b\u000f\u0010\u0017J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\tHÆ\u0003J_\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010-\u001a\u00020\r2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\tHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\"R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001f¨\u00063"}, d2 = {"Lcom/reown/android/internal/common/model/Pairing;", "Lcom/reown/android/internal/common/model/type/Sequence;", "topic", "Lcom/reown/foundation/common/model/Topic;", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "peerAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "relayProtocol", "", "relayData", "uri", "isProposalReceived", "", "methods", "<init>", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/Expiry;Lcom/reown/android/internal/common/model/AppMetaData;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "relay", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "symmetricKey", "Lcom/reown/android/internal/common/model/SymmetricKey;", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/RelayProtocolOptions;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Lcom/reown/android/internal/common/model/WalletConnectUri;", "(Lcom/reown/android/internal/common/model/WalletConnectUri;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "getPeerAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "getRelayProtocol", "()Ljava/lang/String;", "getRelayData", "getUri", "()Z", "getMethods", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Pairing implements Sequence {
    @NotNull
    private final Expiry expiry;
    private final boolean isProposalReceived;
    @Nullable
    private final String methods;
    @Nullable
    private final AppMetaData peerAppMetaData;
    @Nullable
    private final String relayData;
    @NotNull
    private final String relayProtocol;
    @NotNull
    private final Topic topic;
    @NotNull
    private final String uri;

    public /* synthetic */ Pairing(Topic topic2, RelayProtocolOptions relayProtocolOptions, String str, Expiry expiry2, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(topic2, relayProtocolOptions, str, expiry2, str2);
    }

    public static /* synthetic */ Pairing copy$default(Pairing pairing, Topic topic2, Expiry expiry2, AppMetaData appMetaData, String str, String str2, String str3, boolean z2, String str4, int i3, Object obj) {
        Pairing pairing2 = pairing;
        int i4 = i3;
        return pairing.copy((i4 & 1) != 0 ? pairing2.topic : topic2, (i4 & 2) != 0 ? pairing2.expiry : expiry2, (i4 & 4) != 0 ? pairing2.peerAppMetaData : appMetaData, (i4 & 8) != 0 ? pairing2.relayProtocol : str, (i4 & 16) != 0 ? pairing2.relayData : str2, (i4 & 32) != 0 ? pairing2.uri : str3, (i4 & 64) != 0 ? pairing2.isProposalReceived : z2, (i4 & 128) != 0 ? pairing2.methods : str4);
    }

    @NotNull
    public final Topic component1() {
        return this.topic;
    }

    @NotNull
    public final Expiry component2() {
        return this.expiry;
    }

    @Nullable
    public final AppMetaData component3() {
        return this.peerAppMetaData;
    }

    @NotNull
    public final String component4() {
        return this.relayProtocol;
    }

    @Nullable
    public final String component5() {
        return this.relayData;
    }

    @NotNull
    public final String component6() {
        return this.uri;
    }

    public final boolean component7() {
        return this.isProposalReceived;
    }

    @Nullable
    public final String component8() {
        return this.methods;
    }

    @NotNull
    public final Pairing copy(@NotNull Topic topic2, @NotNull Expiry expiry2, @Nullable AppMetaData appMetaData, @NotNull String str, @Nullable String str2, @NotNull String str3, boolean z2, @Nullable String str4) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(expiry2, "expiry");
        Intrinsics.checkNotNullParameter(str, "relayProtocol");
        String str5 = str3;
        Intrinsics.checkNotNullParameter(str5, "uri");
        return new Pairing(topic2, expiry2, appMetaData, str, str2, str5, z2, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pairing)) {
            return false;
        }
        Pairing pairing = (Pairing) obj;
        return Intrinsics.areEqual((Object) this.topic, (Object) pairing.topic) && Intrinsics.areEqual((Object) this.expiry, (Object) pairing.expiry) && Intrinsics.areEqual((Object) this.peerAppMetaData, (Object) pairing.peerAppMetaData) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) pairing.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) pairing.relayData) && Intrinsics.areEqual((Object) this.uri, (Object) pairing.uri) && this.isProposalReceived == pairing.isProposalReceived && Intrinsics.areEqual((Object) this.methods, (Object) pairing.methods);
    }

    @NotNull
    public Expiry getExpiry() {
        return this.expiry;
    }

    @Nullable
    public final String getMethods() {
        return this.methods;
    }

    @Nullable
    public final AppMetaData getPeerAppMetaData() {
        return this.peerAppMetaData;
    }

    @Nullable
    public final String getRelayData() {
        return this.relayData;
    }

    @NotNull
    public final String getRelayProtocol() {
        return this.relayProtocol;
    }

    @NotNull
    public Topic getTopic() {
        return this.topic;
    }

    @NotNull
    public final String getUri() {
        return this.uri;
    }

    public int hashCode() {
        int hashCode = (this.expiry.hashCode() + (this.topic.hashCode() * 31)) * 31;
        AppMetaData appMetaData = this.peerAppMetaData;
        int i3 = 0;
        int i4 = a.i(this.relayProtocol, (hashCode + (appMetaData == null ? 0 : appMetaData.hashCode())) * 31, 31);
        String str = this.relayData;
        int f2 = android.support.v4.media.session.a.f(this.isProposalReceived, a.i(this.uri, (i4 + (str == null ? 0 : str.hashCode())) * 31, 31), 31);
        String str2 = this.methods;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return f2 + i3;
    }

    public final boolean isProposalReceived() {
        return this.isProposalReceived;
    }

    @NotNull
    public String toString() {
        Topic topic2 = this.topic;
        Expiry expiry2 = this.expiry;
        AppMetaData appMetaData = this.peerAppMetaData;
        String str = this.relayProtocol;
        String str2 = this.relayData;
        String str3 = this.uri;
        boolean z2 = this.isProposalReceived;
        String str4 = this.methods;
        StringBuilder sb = new StringBuilder("Pairing(topic=");
        sb.append(topic2);
        sb.append(", expiry=");
        sb.append(expiry2);
        sb.append(", peerAppMetaData=");
        sb.append(appMetaData);
        sb.append(", relayProtocol=");
        sb.append(str);
        sb.append(", relayData=");
        b.w(sb, str2, ", uri=", str3, ", isProposalReceived=");
        sb.append(z2);
        sb.append(", methods=");
        sb.append(str4);
        sb.append(")");
        return sb.toString();
    }

    public Pairing(@NotNull Topic topic2, @NotNull Expiry expiry2, @Nullable AppMetaData appMetaData, @NotNull String str, @Nullable String str2, @NotNull String str3, boolean z2, @Nullable String str4) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(expiry2, "expiry");
        Intrinsics.checkNotNullParameter(str, "relayProtocol");
        Intrinsics.checkNotNullParameter(str3, "uri");
        this.topic = topic2;
        this.expiry = expiry2;
        this.peerAppMetaData = appMetaData;
        this.relayProtocol = str;
        this.relayData = str2;
        this.uri = str3;
        this.isProposalReceived = z2;
        this.methods = str4;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Pairing(com.reown.foundation.common.model.Topic r13, com.reown.android.internal.common.model.Expiry r14, com.reown.android.internal.common.model.AppMetaData r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, boolean r19, java.lang.String r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r6 = r2
            goto L_0x000a
        L_0x0009:
            r6 = r15
        L_0x000a:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0011
            r1 = 0
            r10 = r1
            goto L_0x0013
        L_0x0011:
            r10 = r19
        L_0x0013:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0019
            r11 = r2
            goto L_0x001b
        L_0x0019:
            r11 = r20
        L_0x001b:
            r3 = r12
            r4 = r13
            r5 = r14
            r7 = r16
            r8 = r17
            r9 = r18
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.model.Pairing.<init>(com.reown.foundation.common.model.Topic, com.reown.android.internal.common.model.Expiry, com.reown.android.internal.common.model.AppMetaData, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Pairing(com.reown.foundation.common.model.Topic r16, com.reown.android.internal.common.model.RelayProtocolOptions r17, java.lang.String r18, com.reown.android.internal.common.model.Expiry r19, java.lang.String r20) {
        /*
            r15 = this;
            java.lang.String r0 = "topic"
            r10 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "relay"
            r4 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "symmetricKey"
            r3 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "expiry"
            r11 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = r17.getProtocol()
            java.lang.String r12 = r17.getData()
            com.reown.android.internal.common.model.WalletConnectUri r13 = new com.reown.android.internal.common.model.WalletConnectUri
            r8 = 8
            r9 = 0
            r5 = 0
            r1 = r13
            r2 = r16
            r6 = r19
            r7 = r20
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            java.lang.String r7 = r13.toAbsoluteString()
            r13 = 68
            r14 = 0
            r4 = 0
            r8 = 0
            r1 = r15
            r3 = r19
            r5 = r0
            r6 = r12
            r9 = r20
            r10 = r13
            r11 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.model.Pairing.<init>(com.reown.foundation.common.model.Topic, com.reown.android.internal.common.model.RelayProtocolOptions, java.lang.String, com.reown.android.internal.common.model.Expiry, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Pairing(@org.jetbrains.annotations.NotNull com.reown.android.internal.common.model.WalletConnectUri r13) {
        /*
            r12 = this;
            java.lang.String r0 = "uri"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            com.reown.foundation.common.model.Topic r2 = r13.getTopic()
            com.reown.android.internal.common.model.Expiry r0 = r13.getExpiry()
            if (r0 != 0) goto L_0x0018
            com.reown.android.internal.common.model.Expiry r0 = new com.reown.android.internal.common.model.Expiry
            long r3 = com.reown.android.pairing.model.Expiration.getPairingExpiry()
            r0.<init>(r3)
        L_0x0018:
            r3 = r0
            com.reown.android.internal.common.model.RelayProtocolOptions r0 = r13.getRelay()
            java.lang.String r5 = r0.getProtocol()
            com.reown.android.internal.common.model.RelayProtocolOptions r0 = r13.getRelay()
            java.lang.String r6 = r0.getData()
            java.lang.String r7 = r13.toAbsoluteString()
            java.lang.String r9 = r13.getMethods()
            r10 = 68
            r11 = 0
            r4 = 0
            r8 = 0
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.model.Pairing.<init>(com.reown.android.internal.common.model.WalletConnectUri):void");
    }
}
