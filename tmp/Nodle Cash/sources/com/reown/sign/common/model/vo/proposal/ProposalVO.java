package com.reown.sign.common.model.vo.proposal;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.Redirect;
import com.reown.foundation.common.model.Topic;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B½\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e\u0012\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0007\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\u0007HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bHÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\u0015\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\u0015\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\u0017\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000eHÆ\u0003J\u0017\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000eHÆ\u0003J\t\u0010>\u001a\u00020\u0007HÆ\u0003J\t\u0010?\u001a\u00020\u0007HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0017HÆ\u0003JÝ\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e2\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÆ\u0001J\u0013\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010F\u001a\u00020GHÖ\u0001J\t\u0010H\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u001f\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u001f\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010/\u001a\u0002008F¢\u0006\u0006\u001a\u0004\b1\u00102¨\u0006I"}, d2 = {"Lcom/reown/sign/common/model/vo/proposal/ProposalVO;", "", "requestId", "", "pairingTopic", "Lcom/reown/foundation/common/model/Topic;", "name", "", "description", "url", "icons", "", "redirect", "requiredNamespaces", "", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "optionalNamespaces", "properties", "scopedProperties", "proposerPublicKey", "relayProtocol", "relayData", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "<init>", "(JLcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;)V", "getRequestId", "()J", "getPairingTopic", "()Lcom/reown/foundation/common/model/Topic;", "getName", "()Ljava/lang/String;", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getScopedProperties", "getProposerPublicKey", "getRelayProtocol", "getRelayData", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "appMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "getAppMetaData", "()Lcom/reown/android/internal/common/model/AppMetaData;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProposalVO {
    @NotNull
    private final String description;
    @Nullable
    private final Expiry expiry;
    @NotNull
    private final List<String> icons;
    @NotNull
    private final String name;
    @NotNull
    private final Map<String, Namespace.Proposal> optionalNamespaces;
    @NotNull
    private final Topic pairingTopic;
    @Nullable
    private final Map<String, String> properties;
    @NotNull
    private final String proposerPublicKey;
    @NotNull
    private final String redirect;
    @Nullable
    private final String relayData;
    @NotNull
    private final String relayProtocol;
    private final long requestId;
    @NotNull
    private final Map<String, Namespace.Proposal> requiredNamespaces;
    @Nullable
    private final Map<String, String> scopedProperties;
    @NotNull
    private final String url;

    public ProposalVO(long j2, @NotNull Topic topic, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list, @NotNull String str4, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @Nullable Map<String, String> map4, @NotNull String str5, @NotNull String str6, @Nullable String str7, @Nullable Expiry expiry2) {
        Topic topic2 = topic;
        String str8 = str;
        String str9 = str2;
        String str10 = str3;
        List<String> list2 = list;
        String str11 = str4;
        Map<String, Namespace.Proposal> map5 = map;
        Map<String, Namespace.Proposal> map6 = map2;
        String str12 = str5;
        String str13 = str6;
        Intrinsics.checkNotNullParameter(topic2, "pairingTopic");
        Intrinsics.checkNotNullParameter(str8, "name");
        Intrinsics.checkNotNullParameter(str9, "description");
        Intrinsics.checkNotNullParameter(str10, "url");
        Intrinsics.checkNotNullParameter(list2, "icons");
        Intrinsics.checkNotNullParameter(str11, "redirect");
        Intrinsics.checkNotNullParameter(map5, "requiredNamespaces");
        Intrinsics.checkNotNullParameter(map6, "optionalNamespaces");
        Intrinsics.checkNotNullParameter(str12, "proposerPublicKey");
        Intrinsics.checkNotNullParameter(str13, "relayProtocol");
        this.requestId = j2;
        this.pairingTopic = topic2;
        this.name = str8;
        this.description = str9;
        this.url = str10;
        this.icons = list2;
        this.redirect = str11;
        this.requiredNamespaces = map5;
        this.optionalNamespaces = map6;
        this.properties = map3;
        this.scopedProperties = map4;
        this.proposerPublicKey = str12;
        this.relayProtocol = str13;
        this.relayData = str7;
        this.expiry = expiry2;
    }

    public static /* synthetic */ ProposalVO copy$default(ProposalVO proposalVO, long j2, Topic topic, String str, String str2, String str3, List list, String str4, Map map, Map map2, Map map3, Map map4, String str5, String str6, String str7, Expiry expiry2, int i3, Object obj) {
        ProposalVO proposalVO2 = proposalVO;
        int i4 = i3;
        return proposalVO.copy((i4 & 1) != 0 ? proposalVO2.requestId : j2, (i4 & 2) != 0 ? proposalVO2.pairingTopic : topic, (i4 & 4) != 0 ? proposalVO2.name : str, (i4 & 8) != 0 ? proposalVO2.description : str2, (i4 & 16) != 0 ? proposalVO2.url : str3, (i4 & 32) != 0 ? proposalVO2.icons : list, (i4 & 64) != 0 ? proposalVO2.redirect : str4, (i4 & 128) != 0 ? proposalVO2.requiredNamespaces : map, (i4 & 256) != 0 ? proposalVO2.optionalNamespaces : map2, (i4 & 512) != 0 ? proposalVO2.properties : map3, (i4 & 1024) != 0 ? proposalVO2.scopedProperties : map4, (i4 & 2048) != 0 ? proposalVO2.proposerPublicKey : str5, (i4 & 4096) != 0 ? proposalVO2.relayProtocol : str6, (i4 & 8192) != 0 ? proposalVO2.relayData : str7, (i4 & 16384) != 0 ? proposalVO2.expiry : expiry2);
    }

    public final long component1() {
        return this.requestId;
    }

    @Nullable
    public final Map<String, String> component10() {
        return this.properties;
    }

    @Nullable
    public final Map<String, String> component11() {
        return this.scopedProperties;
    }

    @NotNull
    public final String component12() {
        return this.proposerPublicKey;
    }

    @NotNull
    public final String component13() {
        return this.relayProtocol;
    }

    @Nullable
    public final String component14() {
        return this.relayData;
    }

    @Nullable
    public final Expiry component15() {
        return this.expiry;
    }

    @NotNull
    public final Topic component2() {
        return this.pairingTopic;
    }

    @NotNull
    public final String component3() {
        return this.name;
    }

    @NotNull
    public final String component4() {
        return this.description;
    }

    @NotNull
    public final String component5() {
        return this.url;
    }

    @NotNull
    public final List<String> component6() {
        return this.icons;
    }

    @NotNull
    public final String component7() {
        return this.redirect;
    }

    @NotNull
    public final Map<String, Namespace.Proposal> component8() {
        return this.requiredNamespaces;
    }

    @NotNull
    public final Map<String, Namespace.Proposal> component9() {
        return this.optionalNamespaces;
    }

    @NotNull
    public final ProposalVO copy(long j2, @NotNull Topic topic, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list, @NotNull String str4, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @Nullable Map<String, String> map4, @NotNull String str5, @NotNull String str6, @Nullable String str7, @Nullable Expiry expiry2) {
        Intrinsics.checkNotNullParameter(topic, "pairingTopic");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "description");
        Intrinsics.checkNotNullParameter(str3, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        Intrinsics.checkNotNullParameter(str4, "redirect");
        Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
        Intrinsics.checkNotNullParameter(map2, "optionalNamespaces");
        Intrinsics.checkNotNullParameter(str5, "proposerPublicKey");
        Intrinsics.checkNotNullParameter(str6, "relayProtocol");
        return new ProposalVO(j2, topic, str, str2, str3, list, str4, map, map2, map3, map4, str5, str6, str7, expiry2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProposalVO)) {
            return false;
        }
        ProposalVO proposalVO = (ProposalVO) obj;
        return this.requestId == proposalVO.requestId && Intrinsics.areEqual((Object) this.pairingTopic, (Object) proposalVO.pairingTopic) && Intrinsics.areEqual((Object) this.name, (Object) proposalVO.name) && Intrinsics.areEqual((Object) this.description, (Object) proposalVO.description) && Intrinsics.areEqual((Object) this.url, (Object) proposalVO.url) && Intrinsics.areEqual((Object) this.icons, (Object) proposalVO.icons) && Intrinsics.areEqual((Object) this.redirect, (Object) proposalVO.redirect) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) proposalVO.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) proposalVO.optionalNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) proposalVO.properties) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) proposalVO.scopedProperties) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) proposalVO.proposerPublicKey) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) proposalVO.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) proposalVO.relayData) && Intrinsics.areEqual((Object) this.expiry, (Object) proposalVO.expiry);
    }

    @NotNull
    public final AppMetaData getAppMetaData() {
        return new AppMetaData(this.description, this.url, this.icons, this.name, new Redirect(this.redirect, (String) null, false, 6, (DefaultConstructorMarker) null), (String) null, 32, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final Expiry getExpiry() {
        return this.expiry;
    }

    @NotNull
    public final List<String> getIcons() {
        return this.icons;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
        return this.optionalNamespaces;
    }

    @NotNull
    public final Topic getPairingTopic() {
        return this.pairingTopic;
    }

    @Nullable
    public final Map<String, String> getProperties() {
        return this.properties;
    }

    @NotNull
    public final String getProposerPublicKey() {
        return this.proposerPublicKey;
    }

    @NotNull
    public final String getRedirect() {
        return this.redirect;
    }

    @Nullable
    public final String getRelayData() {
        return this.relayData;
    }

    @NotNull
    public final String getRelayProtocol() {
        return this.relayProtocol;
    }

    public final long getRequestId() {
        return this.requestId;
    }

    @NotNull
    public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
        return this.requiredNamespaces;
    }

    @Nullable
    public final Map<String, String> getScopedProperties() {
        return this.scopedProperties;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int d2 = b.d(this.optionalNamespaces, b.d(this.requiredNamespaces, a.i(this.redirect, a.j(this.icons, a.i(this.url, a.i(this.description, a.i(this.name, (this.pairingTopic.hashCode() + (Long.hashCode(this.requestId) * 31)) * 31, 31), 31), 31), 31), 31), 31), 31);
        Map<String, String> map = this.properties;
        int i3 = 0;
        int hashCode = (d2 + (map == null ? 0 : map.hashCode())) * 31;
        Map<String, String> map2 = this.scopedProperties;
        int i4 = a.i(this.relayProtocol, a.i(this.proposerPublicKey, (hashCode + (map2 == null ? 0 : map2.hashCode())) * 31, 31), 31);
        String str = this.relayData;
        int hashCode2 = (i4 + (str == null ? 0 : str.hashCode())) * 31;
        Expiry expiry2 = this.expiry;
        if (expiry2 != null) {
            i3 = expiry2.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        long j2 = this.requestId;
        Topic topic = this.pairingTopic;
        String str = this.name;
        String str2 = this.description;
        String str3 = this.url;
        List<String> list = this.icons;
        String str4 = this.redirect;
        Map<String, Namespace.Proposal> map = this.requiredNamespaces;
        Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
        Map<String, String> map3 = this.properties;
        Map<String, String> map4 = this.scopedProperties;
        String str5 = this.proposerPublicKey;
        String str6 = this.relayProtocol;
        String str7 = this.relayData;
        Expiry expiry2 = this.expiry;
        StringBuilder sb = new StringBuilder("ProposalVO(requestId=");
        sb.append(j2);
        sb.append(", pairingTopic=");
        sb.append(topic);
        b.w(sb, ", name=", str, ", description=", str2);
        sb.append(", url=");
        sb.append(str3);
        sb.append(", icons=");
        sb.append(list);
        sb.append(", redirect=");
        sb.append(str4);
        sb.append(", requiredNamespaces=");
        sb.append(map);
        sb.append(", optionalNamespaces=");
        sb.append(map2);
        sb.append(", properties=");
        sb.append(map3);
        sb.append(", scopedProperties=");
        sb.append(map4);
        sb.append(", proposerPublicKey=");
        sb.append(str5);
        b.w(sb, ", relayProtocol=", str6, ", relayData=", str7);
        sb.append(", expiry=");
        sb.append(expiry2);
        sb.append(")");
        return sb.toString();
    }
}
