package com.reown.android.internal.common.signing.cacao;

import A.a;
import androidx.annotation.Keep;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.reown.android.cacao.SignatureInterface;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001:\u0003\u001b\u001c\u001dB%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Cacao;", "", "header", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Header;", "payload", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "signature", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;", "<init>", "(Lcom/reown/android/internal/common/signing/cacao/Cacao$Header;Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;)V", "getHeader", "()Lcom/reown/android/internal/common/signing/cacao/Cacao$Header;", "getPayload", "()Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "getSignature", "()Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Signature", "Header", "Payload", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Cacao {
    @NotNull
    private final Header header;
    @NotNull
    private final Payload payload;
    @NotNull
    private final Signature signature;

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Cacao$Header;", "", "t", "", "<init>", "(Ljava/lang/String;)V", "getT", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Header {
        @NotNull

        /* renamed from: t  reason: collision with root package name */
        private final String f7343t;

        public Header(@NotNull @Json(name = "t") String str) {
            Intrinsics.checkNotNullParameter(str, "t");
            this.f7343t = str;
        }

        public static /* synthetic */ Header copy$default(Header header, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = header.f7343t;
            }
            return header.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.f7343t;
        }

        @NotNull
        public final Header copy(@NotNull @Json(name = "t") String str) {
            Intrinsics.checkNotNullParameter(str, "t");
            return new Header(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Header) && Intrinsics.areEqual((Object) this.f7343t, (Object) ((Header) obj).f7343t);
        }

        @NotNull
        public final String getT() {
            return this.f7343t;
        }

        public int hashCode() {
            return this.f7343t.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("Header(t=", this.f7343t, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 42\u00020\u0001:\u00014B\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0003J\u0001\u0010-\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0012R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u000e8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001d¨\u00065"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "", "iss", "", "domain", "aud", "version", "nonce", "iat", "nbf", "exp", "statement", "requestId", "resources", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getIss", "()Ljava/lang/String;", "getDomain", "getAud", "getVersion", "getNonce", "getIat", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "()Ljava/util/List;", "actionsString", "getActionsString", "methods", "getMethods", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Payload {
        @NotNull
        public static final String ATT_KEY = "att";
        @NotNull
        public static final String CURRENT_VERSION = "1";
        @NotNull
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        @NotNull
        public static final String ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
        @NotNull
        public static final String RECAPS_PREFIX = "urn:recap:";
        @NotNull
        private final String aud;
        @NotNull
        private final String domain;
        @Nullable
        private final String exp;
        @NotNull
        private final String iat;
        @NotNull
        private final String iss;
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
        private final String version;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload$Companion;", "", "<init>", "()V", "CURRENT_VERSION", "", "ISO_8601_PATTERN", "RECAPS_PREFIX", "ATT_KEY", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        public Payload(@NotNull @Json(name = "iss") String str, @NotNull @Json(name = "domain") String str2, @NotNull @Json(name = "aud") String str3, @NotNull @Json(name = "version") String str4, @NotNull @Json(name = "nonce") String str5, @NotNull @Json(name = "iat") String str6, @Nullable @Json(name = "nbf") String str7, @Nullable @Json(name = "exp") String str8, @Nullable @Json(name = "statement") String str9, @Nullable @Json(name = "requestId") String str10, @Nullable @Json(name = "resources") List<String> list) {
            Intrinsics.checkNotNullParameter(str, "iss");
            Intrinsics.checkNotNullParameter(str2, "domain");
            Intrinsics.checkNotNullParameter(str3, "aud");
            Intrinsics.checkNotNullParameter(str4, "version");
            Intrinsics.checkNotNullParameter(str5, "nonce");
            Intrinsics.checkNotNullParameter(str6, "iat");
            this.iss = str;
            this.domain = str2;
            this.aud = str3;
            this.version = str4;
            this.nonce = str5;
            this.iat = str6;
            this.nbf = str7;
            this.exp = str8;
            this.statement = str9;
            this.requestId = str10;
            this.resources = list;
        }

        public static /* synthetic */ Payload copy$default(Payload payload, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, List list, int i3, Object obj) {
            Payload payload2 = payload;
            int i4 = i3;
            return payload.copy((i4 & 1) != 0 ? payload2.iss : str, (i4 & 2) != 0 ? payload2.domain : str2, (i4 & 4) != 0 ? payload2.aud : str3, (i4 & 8) != 0 ? payload2.version : str4, (i4 & 16) != 0 ? payload2.nonce : str5, (i4 & 32) != 0 ? payload2.iat : str6, (i4 & 64) != 0 ? payload2.nbf : str7, (i4 & 128) != 0 ? payload2.exp : str8, (i4 & 256) != 0 ? payload2.statement : str9, (i4 & 512) != 0 ? payload2.requestId : str10, (i4 & 1024) != 0 ? payload2.resources : list);
        }

        @NotNull
        public final String component1() {
            return this.iss;
        }

        @Nullable
        public final String component10() {
            return this.requestId;
        }

        @Nullable
        public final List<String> component11() {
            return this.resources;
        }

        @NotNull
        public final String component2() {
            return this.domain;
        }

        @NotNull
        public final String component3() {
            return this.aud;
        }

        @NotNull
        public final String component4() {
            return this.version;
        }

        @NotNull
        public final String component5() {
            return this.nonce;
        }

        @NotNull
        public final String component6() {
            return this.iat;
        }

        @Nullable
        public final String component7() {
            return this.nbf;
        }

        @Nullable
        public final String component8() {
            return this.exp;
        }

        @Nullable
        public final String component9() {
            return this.statement;
        }

        @NotNull
        public final Payload copy(@NotNull @Json(name = "iss") String str, @NotNull @Json(name = "domain") String str2, @NotNull @Json(name = "aud") String str3, @NotNull @Json(name = "version") String str4, @NotNull @Json(name = "nonce") String str5, @NotNull @Json(name = "iat") String str6, @Nullable @Json(name = "nbf") String str7, @Nullable @Json(name = "exp") String str8, @Nullable @Json(name = "statement") String str9, @Nullable @Json(name = "requestId") String str10, @Nullable @Json(name = "resources") List<String> list) {
            Intrinsics.checkNotNullParameter(str, "iss");
            Intrinsics.checkNotNullParameter(str2, "domain");
            String str11 = str3;
            Intrinsics.checkNotNullParameter(str11, "aud");
            String str12 = str4;
            Intrinsics.checkNotNullParameter(str12, "version");
            String str13 = str5;
            Intrinsics.checkNotNullParameter(str13, "nonce");
            String str14 = str6;
            Intrinsics.checkNotNullParameter(str14, "iat");
            return new Payload(str, str2, str11, str12, str13, str14, str7, str8, str9, str10, list);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Payload)) {
                return false;
            }
            Payload payload = (Payload) obj;
            return Intrinsics.areEqual((Object) this.iss, (Object) payload.iss) && Intrinsics.areEqual((Object) this.domain, (Object) payload.domain) && Intrinsics.areEqual((Object) this.aud, (Object) payload.aud) && Intrinsics.areEqual((Object) this.version, (Object) payload.version) && Intrinsics.areEqual((Object) this.nonce, (Object) payload.nonce) && Intrinsics.areEqual((Object) this.iat, (Object) payload.iat) && Intrinsics.areEqual((Object) this.nbf, (Object) payload.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payload.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payload.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payload.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payload.resources);
        }

        @NotNull
        public final String getActionsString() throws Exception {
            return CacaoKt.getActionsString(this.resources);
        }

        @NotNull
        public final String getAud() {
            return this.aud;
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

        @NotNull
        public final String getIss() {
            return this.iss;
        }

        @NotNull
        public final List<String> getMethods() throws Exception {
            return UtilsKt.getMethods(this.resources);
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
        public final String getVersion() {
            return this.version;
        }

        public int hashCode() {
            int i3 = androidx.compose.animation.core.a.i(this.iat, androidx.compose.animation.core.a.i(this.nonce, androidx.compose.animation.core.a.i(this.version, androidx.compose.animation.core.a.i(this.aud, androidx.compose.animation.core.a.i(this.domain, this.iss.hashCode() * 31, 31), 31), 31), 31), 31);
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
            String str = this.iss;
            String str2 = this.domain;
            String str3 = this.aud;
            String str4 = this.version;
            String str5 = this.nonce;
            String str6 = this.iat;
            String str7 = this.nbf;
            String str8 = this.exp;
            String str9 = this.statement;
            String str10 = this.requestId;
            List<String> list = this.resources;
            StringBuilder l2 = C0118y.l("Payload(iss=", str, ", domain=", str2, ", aud=");
            b.w(l2, str3, ", version=", str4, ", nonce=");
            b.w(l2, str5, ", iat=", str6, ", nbf=");
            b.w(l2, str7, ", exp=", str8, ", statement=");
            b.w(l2, str9, ", requestId=", str10, ", resources=");
            return C0118y.h(")", list, l2);
        }
    }

    public Cacao(@NotNull @Json(name = "h") Header header2, @NotNull @Json(name = "p") Payload payload2, @NotNull @Json(name = "s") Signature signature2) {
        Intrinsics.checkNotNullParameter(header2, "header");
        Intrinsics.checkNotNullParameter(payload2, "payload");
        Intrinsics.checkNotNullParameter(signature2, "signature");
        this.header = header2;
        this.payload = payload2;
        this.signature = signature2;
    }

    public static /* synthetic */ Cacao copy$default(Cacao cacao, Header header2, Payload payload2, Signature signature2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            header2 = cacao.header;
        }
        if ((i3 & 2) != 0) {
            payload2 = cacao.payload;
        }
        if ((i3 & 4) != 0) {
            signature2 = cacao.signature;
        }
        return cacao.copy(header2, payload2, signature2);
    }

    @NotNull
    public final Header component1() {
        return this.header;
    }

    @NotNull
    public final Payload component2() {
        return this.payload;
    }

    @NotNull
    public final Signature component3() {
        return this.signature;
    }

    @NotNull
    public final Cacao copy(@NotNull @Json(name = "h") Header header2, @NotNull @Json(name = "p") Payload payload2, @NotNull @Json(name = "s") Signature signature2) {
        Intrinsics.checkNotNullParameter(header2, "header");
        Intrinsics.checkNotNullParameter(payload2, "payload");
        Intrinsics.checkNotNullParameter(signature2, "signature");
        return new Cacao(header2, payload2, signature2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cacao)) {
            return false;
        }
        Cacao cacao = (Cacao) obj;
        return Intrinsics.areEqual((Object) this.header, (Object) cacao.header) && Intrinsics.areEqual((Object) this.payload, (Object) cacao.payload) && Intrinsics.areEqual((Object) this.signature, (Object) cacao.signature);
    }

    @NotNull
    public final Header getHeader() {
        return this.header;
    }

    @NotNull
    public final Payload getPayload() {
        return this.payload;
    }

    @NotNull
    public final Signature getSignature() {
        return this.signature;
    }

    public int hashCode() {
        int hashCode = this.payload.hashCode();
        return this.signature.hashCode() + ((hashCode + (this.header.hashCode() * 31)) * 31);
    }

    @NotNull
    public String toString() {
        Header header2 = this.header;
        Payload payload2 = this.payload;
        Signature signature2 = this.signature;
        return "Cacao(header=" + header2 + ", payload=" + payload2 + ", signature=" + signature2 + ")";
    }

    @JsonClass(generateAdapter = true)
    @Keep
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;", "Lcom/reown/android/cacao/SignatureInterface;", "t", "", "s", "m", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getT", "()Ljava/lang/String;", "getS", "getM", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Signature implements SignatureInterface {
        @Nullable

        /* renamed from: m  reason: collision with root package name */
        private final String f7344m;
        @NotNull

        /* renamed from: s  reason: collision with root package name */
        private final String f7345s;
        @NotNull

        /* renamed from: t  reason: collision with root package name */
        private final String f7346t;

        public Signature(@NotNull @Json(name = "t") String str, @NotNull @Json(name = "s") String str2, @Nullable @Json(name = "m") String str3) {
            Intrinsics.checkNotNullParameter(str, "t");
            Intrinsics.checkNotNullParameter(str2, "s");
            this.f7346t = str;
            this.f7345s = str2;
            this.f7344m = str3;
        }

        public static /* synthetic */ Signature copy$default(Signature signature, String str, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = signature.f7346t;
            }
            if ((i3 & 2) != 0) {
                str2 = signature.f7345s;
            }
            if ((i3 & 4) != 0) {
                str3 = signature.f7344m;
            }
            return signature.copy(str, str2, str3);
        }

        @NotNull
        public final String component1() {
            return this.f7346t;
        }

        @NotNull
        public final String component2() {
            return this.f7345s;
        }

        @Nullable
        public final String component3() {
            return this.f7344m;
        }

        @NotNull
        public final Signature copy(@NotNull @Json(name = "t") String str, @NotNull @Json(name = "s") String str2, @Nullable @Json(name = "m") String str3) {
            Intrinsics.checkNotNullParameter(str, "t");
            Intrinsics.checkNotNullParameter(str2, "s");
            return new Signature(str, str2, str3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Signature)) {
                return false;
            }
            Signature signature = (Signature) obj;
            return Intrinsics.areEqual((Object) this.f7346t, (Object) signature.f7346t) && Intrinsics.areEqual((Object) this.f7345s, (Object) signature.f7345s) && Intrinsics.areEqual((Object) this.f7344m, (Object) signature.f7344m);
        }

        @Nullable
        public String getM() {
            return this.f7344m;
        }

        @NotNull
        public String getS() {
            return this.f7345s;
        }

        @NotNull
        public String getT() {
            return this.f7346t;
        }

        public int hashCode() {
            int i3 = androidx.compose.animation.core.a.i(this.f7345s, this.f7346t.hashCode() * 31, 31);
            String str = this.f7344m;
            return i3 + (str == null ? 0 : str.hashCode());
        }

        @NotNull
        public String toString() {
            String str = this.f7346t;
            String str2 = this.f7345s;
            return a.n(C0118y.l("Signature(t=", str, ", s=", str2, ", m="), this.f7344m, ")");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Signature(String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i3 & 4) != 0 ? null : str3);
        }
    }
}
