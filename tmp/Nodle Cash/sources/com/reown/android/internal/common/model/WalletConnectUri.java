package com.reown.android.internal.common.model;

import android.support.v4.media.session.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010\u001a\u001a\u00020\tJ\b\u0010\u001b\u001a\u00020\tH\u0002J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0012J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tHÆ\u0003JP\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0004\b$\u0010%J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012¨\u0006,"}, d2 = {"Lcom/reown/android/internal/common/model/WalletConnectUri;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "symKey", "Lcom/reown/android/internal/common/model/SymmetricKey;", "relay", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "version", "", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "methods", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Lcom/reown/android/internal/common/model/RelayProtocolOptions;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getSymKey-C2wS6ak", "()Ljava/lang/String;", "Ljava/lang/String;", "getRelay", "()Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "getVersion", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "getMethods", "toAbsoluteString", "getQuery", "component1", "component2", "component2-C2wS6ak", "component3", "component4", "component5", "component6", "copy", "copy-lRgxgew", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Lcom/reown/android/internal/common/model/RelayProtocolOptions;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;)Lcom/reown/android/internal/common/model/WalletConnectUri;", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletConnectUri {
    @Nullable
    private final Expiry expiry;
    @Nullable
    private final String methods;
    @NotNull
    private final RelayProtocolOptions relay;
    @NotNull
    private final String symKey;
    @NotNull
    private final Topic topic;
    @NotNull
    private final String version;

    public /* synthetic */ WalletConnectUri(Topic topic2, String str, RelayProtocolOptions relayProtocolOptions, String str2, Expiry expiry2, String str3, DefaultConstructorMarker defaultConstructorMarker) {
        this(topic2, str, relayProtocolOptions, str2, expiry2, str3);
    }

    /* renamed from: copy-lRgxgew$default  reason: not valid java name */
    public static /* synthetic */ WalletConnectUri m8792copylRgxgew$default(WalletConnectUri walletConnectUri, Topic topic2, String str, RelayProtocolOptions relayProtocolOptions, String str2, Expiry expiry2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            topic2 = walletConnectUri.topic;
        }
        if ((i3 & 2) != 0) {
            str = walletConnectUri.symKey;
        }
        String str4 = str;
        if ((i3 & 4) != 0) {
            relayProtocolOptions = walletConnectUri.relay;
        }
        RelayProtocolOptions relayProtocolOptions2 = relayProtocolOptions;
        if ((i3 & 8) != 0) {
            str2 = walletConnectUri.version;
        }
        String str5 = str2;
        if ((i3 & 16) != 0) {
            expiry2 = walletConnectUri.expiry;
        }
        Expiry expiry3 = expiry2;
        if ((i3 & 32) != 0) {
            str3 = walletConnectUri.methods;
        }
        return walletConnectUri.m8794copylRgxgew(topic2, str4, relayProtocolOptions2, str5, expiry3, str3);
    }

    private final String getQuery() {
        String a2 = c.a("relay-protocol=", this.relay.getProtocol());
        if (this.relay.getData() != null) {
            a2 = a.n(a2, "&relay-data=", this.relay.getData());
        }
        Expiry expiry2 = this.expiry;
        if (expiry2 != null) {
            a2 = a2 + "&expiryTimestamp=" + expiry2.getSeconds();
        }
        String str = this.methods;
        return (str == null || str.length() == 0) ? a2 : a.n(a2, "&methods=", this.methods);
    }

    @NotNull
    public final Topic component1() {
        return this.topic;
    }

    @NotNull
    /* renamed from: component2-C2wS6ak  reason: not valid java name */
    public final String m8793component2C2wS6ak() {
        return this.symKey;
    }

    @NotNull
    public final RelayProtocolOptions component3() {
        return this.relay;
    }

    @NotNull
    public final String component4() {
        return this.version;
    }

    @Nullable
    public final Expiry component5() {
        return this.expiry;
    }

    @Nullable
    public final String component6() {
        return this.methods;
    }

    @NotNull
    /* renamed from: copy-lRgxgew  reason: not valid java name */
    public final WalletConnectUri m8794copylRgxgew(@NotNull Topic topic2, @NotNull String str, @NotNull RelayProtocolOptions relayProtocolOptions, @NotNull String str2, @Nullable Expiry expiry2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, "symKey");
        Intrinsics.checkNotNullParameter(relayProtocolOptions, "relay");
        Intrinsics.checkNotNullParameter(str2, "version");
        return new WalletConnectUri(topic2, str, relayProtocolOptions, str2, expiry2, str3, (DefaultConstructorMarker) null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WalletConnectUri)) {
            return false;
        }
        WalletConnectUri walletConnectUri = (WalletConnectUri) obj;
        return Intrinsics.areEqual((Object) this.topic, (Object) walletConnectUri.topic) && SymmetricKey.m8780equalsimpl0(this.symKey, walletConnectUri.symKey) && Intrinsics.areEqual((Object) this.relay, (Object) walletConnectUri.relay) && Intrinsics.areEqual((Object) this.version, (Object) walletConnectUri.version) && Intrinsics.areEqual((Object) this.expiry, (Object) walletConnectUri.expiry) && Intrinsics.areEqual((Object) this.methods, (Object) walletConnectUri.methods);
    }

    @Nullable
    public final Expiry getExpiry() {
        return this.expiry;
    }

    @Nullable
    public final String getMethods() {
        return this.methods;
    }

    @NotNull
    public final RelayProtocolOptions getRelay() {
        return this.relay;
    }

    @NotNull
    /* renamed from: getSymKey-C2wS6ak  reason: not valid java name */
    public final String m8795getSymKeyC2wS6ak() {
        return this.symKey;
    }

    @NotNull
    public final Topic getTopic() {
        return this.topic;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int r2 = SymmetricKey.m8782hashCodeimpl(this.symKey);
        int i3 = androidx.compose.animation.core.a.i(this.version, (this.relay.hashCode() + ((r2 + (this.topic.hashCode() * 31)) * 31)) * 31, 31);
        Expiry expiry2 = this.expiry;
        int i4 = 0;
        int hashCode = (i3 + (expiry2 == null ? 0 : expiry2.hashCode())) * 31;
        String str = this.methods;
        if (str != null) {
            i4 = str.hashCode();
        }
        return hashCode + i4;
    }

    @NotNull
    public final String toAbsoluteString() {
        String value = this.topic.getValue();
        String str = this.version;
        return C0118y.j(C0118y.l("wc:", value, "@", str, "?"), getQuery(), "&symKey=", this.symKey);
    }

    @NotNull
    public String toString() {
        Topic topic2 = this.topic;
        String r12 = SymmetricKey.m8783toStringimpl(this.symKey);
        RelayProtocolOptions relayProtocolOptions = this.relay;
        String str = this.version;
        Expiry expiry2 = this.expiry;
        String str2 = this.methods;
        return "WalletConnectUri(topic=" + topic2 + ", symKey=" + r12 + ", relay=" + relayProtocolOptions + ", version=" + str + ", expiry=" + expiry2 + ", methods=" + str2 + ")";
    }

    private WalletConnectUri(Topic topic2, String str, RelayProtocolOptions relayProtocolOptions, String str2, Expiry expiry2, String str3) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, "symKey");
        Intrinsics.checkNotNullParameter(relayProtocolOptions, "relay");
        Intrinsics.checkNotNullParameter(str2, "version");
        this.topic = topic2;
        this.symKey = str;
        this.relay = relayProtocolOptions;
        this.version = str2;
        this.expiry = expiry2;
        this.methods = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WalletConnectUri(Topic topic2, String str, RelayProtocolOptions relayProtocolOptions, String str2, Expiry expiry2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(topic2, str, relayProtocolOptions, (i3 & 8) != 0 ? "2" : str2, expiry2, str3, (DefaultConstructorMarker) null);
    }
}
