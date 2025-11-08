package com.reown.sign.common.model.vo.clientsync.common;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0001\u0010,\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u00032\b\b\u0003\u0010\n\u001a\u00020\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015¨\u00063"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;", "", "type", "", "chains", "", "domain", "aud", "nonce", "version", "iat", "nbf", "exp", "statement", "requestId", "resources", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getType", "()Ljava/lang/String;", "getChains", "()Ljava/util/List;", "getDomain", "getAud", "getNonce", "getVersion", "getIat", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PayloadParams {
    @NotNull
    private final String aud;
    @NotNull
    private final List<String> chains;
    @NotNull
    private final String domain;
    @Nullable
    private final String exp;
    @NotNull
    private final String iat;
    @Nullable
    private final String nbf;
    @NotNull
    private final String nonce;
    @Nullable
    private final String requestId;
    @Nullable
    private final List<String> resources;
    @Nullable
    private final String statement;
    @NotNull
    private final String type;
    @NotNull
    private final String version;

    public PayloadParams(@NotNull @Json(name = "type") String str, @NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "domain") String str2, @NotNull @Json(name = "aud") String str3, @NotNull @Json(name = "nonce") String str4, @NotNull @Json(name = "version") String str5, @NotNull @Json(name = "iat") String str6, @Nullable @Json(name = "nbf") String str7, @Nullable @Json(name = "exp") String str8, @Nullable @Json(name = "statement") String str9, @Nullable @Json(name = "requestId") String str10, @Nullable @Json(name = "resources") List<String> list2) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(list, "chains");
        Intrinsics.checkNotNullParameter(str2, "domain");
        Intrinsics.checkNotNullParameter(str3, "aud");
        Intrinsics.checkNotNullParameter(str4, "nonce");
        Intrinsics.checkNotNullParameter(str5, "version");
        Intrinsics.checkNotNullParameter(str6, "iat");
        this.type = str;
        this.chains = list;
        this.domain = str2;
        this.aud = str3;
        this.nonce = str4;
        this.version = str5;
        this.iat = str6;
        this.nbf = str7;
        this.exp = str8;
        this.statement = str9;
        this.requestId = str10;
        this.resources = list2;
    }

    public static /* synthetic */ PayloadParams copy$default(PayloadParams payloadParams, String str, List list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, List list2, int i3, Object obj) {
        PayloadParams payloadParams2 = payloadParams;
        int i4 = i3;
        return payloadParams.copy((i4 & 1) != 0 ? payloadParams2.type : str, (i4 & 2) != 0 ? payloadParams2.chains : list, (i4 & 4) != 0 ? payloadParams2.domain : str2, (i4 & 8) != 0 ? payloadParams2.aud : str3, (i4 & 16) != 0 ? payloadParams2.nonce : str4, (i4 & 32) != 0 ? payloadParams2.version : str5, (i4 & 64) != 0 ? payloadParams2.iat : str6, (i4 & 128) != 0 ? payloadParams2.nbf : str7, (i4 & 256) != 0 ? payloadParams2.exp : str8, (i4 & 512) != 0 ? payloadParams2.statement : str9, (i4 & 1024) != 0 ? payloadParams2.requestId : str10, (i4 & 2048) != 0 ? payloadParams2.resources : list2);
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    @Nullable
    public final String component10() {
        return this.statement;
    }

    @Nullable
    public final String component11() {
        return this.requestId;
    }

    @Nullable
    public final List<String> component12() {
        return this.resources;
    }

    @NotNull
    public final List<String> component2() {
        return this.chains;
    }

    @NotNull
    public final String component3() {
        return this.domain;
    }

    @NotNull
    public final String component4() {
        return this.aud;
    }

    @NotNull
    public final String component5() {
        return this.nonce;
    }

    @NotNull
    public final String component6() {
        return this.version;
    }

    @NotNull
    public final String component7() {
        return this.iat;
    }

    @Nullable
    public final String component8() {
        return this.nbf;
    }

    @Nullable
    public final String component9() {
        return this.exp;
    }

    @NotNull
    public final PayloadParams copy(@NotNull @Json(name = "type") String str, @NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "domain") String str2, @NotNull @Json(name = "aud") String str3, @NotNull @Json(name = "nonce") String str4, @NotNull @Json(name = "version") String str5, @NotNull @Json(name = "iat") String str6, @Nullable @Json(name = "nbf") String str7, @Nullable @Json(name = "exp") String str8, @Nullable @Json(name = "statement") String str9, @Nullable @Json(name = "requestId") String str10, @Nullable @Json(name = "resources") List<String> list2) {
        Intrinsics.checkNotNullParameter(str, "type");
        List<String> list3 = list;
        Intrinsics.checkNotNullParameter(list3, "chains");
        String str11 = str2;
        Intrinsics.checkNotNullParameter(str11, "domain");
        String str12 = str3;
        Intrinsics.checkNotNullParameter(str12, "aud");
        String str13 = str4;
        Intrinsics.checkNotNullParameter(str13, "nonce");
        String str14 = str5;
        Intrinsics.checkNotNullParameter(str14, "version");
        String str15 = str6;
        Intrinsics.checkNotNullParameter(str15, "iat");
        return new PayloadParams(str, list3, str11, str12, str13, str14, str15, str7, str8, str9, str10, list2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PayloadParams)) {
            return false;
        }
        PayloadParams payloadParams = (PayloadParams) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) payloadParams.type) && Intrinsics.areEqual((Object) this.chains, (Object) payloadParams.chains) && Intrinsics.areEqual((Object) this.domain, (Object) payloadParams.domain) && Intrinsics.areEqual((Object) this.aud, (Object) payloadParams.aud) && Intrinsics.areEqual((Object) this.nonce, (Object) payloadParams.nonce) && Intrinsics.areEqual((Object) this.version, (Object) payloadParams.version) && Intrinsics.areEqual((Object) this.iat, (Object) payloadParams.iat) && Intrinsics.areEqual((Object) this.nbf, (Object) payloadParams.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payloadParams.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payloadParams.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payloadParams.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payloadParams.resources);
    }

    @NotNull
    public final String getAud() {
        return this.aud;
    }

    @NotNull
    public final List<String> getChains() {
        return this.chains;
    }

    @NotNull
    public final String getDomain() {
        return this.domain;
    }

    @Nullable
    public final String getExp() {
        return this.exp;
    }

    @NotNull
    public final String getIat() {
        return this.iat;
    }

    @Nullable
    public final String getNbf() {
        return this.nbf;
    }

    @NotNull
    public final String getNonce() {
        return this.nonce;
    }

    @Nullable
    public final String getRequestId() {
        return this.requestId;
    }

    @Nullable
    public final List<String> getResources() {
        return this.resources;
    }

    @Nullable
    public final String getStatement() {
        return this.statement;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i3 = a.i(this.iat, a.i(this.version, a.i(this.nonce, a.i(this.aud, a.i(this.domain, a.j(this.chains, this.type.hashCode() * 31, 31), 31), 31), 31), 31), 31);
        String str = this.nbf;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.exp;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.statement;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.requestId;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<String> list = this.resources;
        if (list != null) {
            i4 = list.hashCode();
        }
        return hashCode4 + i4;
    }

    @NotNull
    public String toString() {
        String str = this.type;
        List<String> list = this.chains;
        String str2 = this.domain;
        String str3 = this.aud;
        String str4 = this.nonce;
        String str5 = this.version;
        String str6 = this.iat;
        String str7 = this.nbf;
        String str8 = this.exp;
        String str9 = this.statement;
        String str10 = this.requestId;
        List<String> list2 = this.resources;
        StringBuilder sb = new StringBuilder("PayloadParams(type=");
        sb.append(str);
        sb.append(", chains=");
        sb.append(list);
        sb.append(", domain=");
        b.w(sb, str2, ", aud=", str3, ", nonce=");
        b.w(sb, str4, ", version=", str5, ", iat=");
        b.w(sb, str6, ", nbf=", str7, ", exp=");
        b.w(sb, str8, ", statement=", str9, ", requestId=");
        sb.append(str10);
        sb.append(", resources=");
        sb.append(list2);
        sb.append(")");
        return sb.toString();
    }
}
