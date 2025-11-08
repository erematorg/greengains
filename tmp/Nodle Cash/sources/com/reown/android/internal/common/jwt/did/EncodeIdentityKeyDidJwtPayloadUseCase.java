package com.reown.android.internal.common.jwt.did;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.android.internal.common.jwt.did.EncodeDidJwtPayloadUseCase;
import com.reown.foundation.util.jwt.JwtClaims;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/reown/android/internal/common/jwt/did/EncodeIdentityKeyDidJwtPayloadUseCase;", "Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase;", "Lcom/reown/android/internal/common/jwt/did/EncodeIdentityKeyDidJwtPayloadUseCase$IdentityKey;", "accountId", "Lcom/reown/android/internal/common/model/AccountId;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Ljava/lang/String;", "invoke", "params", "Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase$Params;", "IdentityKey", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EncodeIdentityKeyDidJwtPayloadUseCase implements EncodeDidJwtPayloadUseCase<IdentityKey> {
    @NotNull
    private final String accountId;

    public /* synthetic */ EncodeIdentityKeyDidJwtPayloadUseCase(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private EncodeIdentityKeyDidJwtPayloadUseCase(String str) {
        Intrinsics.checkNotNullParameter(str, "accountId");
        this.accountId = str;
    }

    @NotNull
    public IdentityKey invoke(@NotNull EncodeDidJwtPayloadUseCase.Params params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return new IdentityKey(params.getIssuer(), params.getKeyserverUrl(), params.getIssuedAt(), params.getExpiration(), JwtUtilsKt.encodeDidPkh(this.accountId), (String) null, 32, (DefaultConstructorMarker) null);
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0003\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lcom/reown/android/internal/common/jwt/did/EncodeIdentityKeyDidJwtPayloadUseCase$IdentityKey;", "Lcom/reown/foundation/util/jwt/JwtClaims;", "issuer", "", "audience", "issuedAt", "", "expiration", "pkh", "act", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;)V", "getIssuer", "()Ljava/lang/String;", "getAudience", "getIssuedAt", "()J", "getExpiration", "getPkh", "getAct", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class IdentityKey implements JwtClaims {
        @NotNull
        private final String act;
        @NotNull
        private final String audience;
        private final long expiration;
        private final long issuedAt;
        @NotNull
        private final String issuer;
        @NotNull
        private final String pkh;

        public IdentityKey(@NotNull @Json(name = "iss") String str, @NotNull @Json(name = "aud") String str2, @Json(name = "iat") long j2, @Json(name = "exp") long j3, @NotNull @Json(name = "pkh") String str3, @NotNull @Json(name = "act") String str4) {
            Intrinsics.checkNotNullParameter(str, "issuer");
            Intrinsics.checkNotNullParameter(str2, "audience");
            Intrinsics.checkNotNullParameter(str3, JwtUtilsKt.DID_METHOD_PKH);
            Intrinsics.checkNotNullParameter(str4, "act");
            this.issuer = str;
            this.audience = str2;
            this.issuedAt = j2;
            this.expiration = j3;
            this.pkh = str3;
            this.act = str4;
        }

        public static /* synthetic */ IdentityKey copy$default(IdentityKey identityKey, String str, String str2, long j2, long j3, String str3, String str4, int i3, Object obj) {
            IdentityKey identityKey2 = identityKey;
            return identityKey.copy((i3 & 1) != 0 ? identityKey2.issuer : str, (i3 & 2) != 0 ? identityKey2.audience : str2, (i3 & 4) != 0 ? identityKey2.issuedAt : j2, (i3 & 8) != 0 ? identityKey2.expiration : j3, (i3 & 16) != 0 ? identityKey2.pkh : str3, (i3 & 32) != 0 ? identityKey2.act : str4);
        }

        @NotNull
        public final String component1() {
            return this.issuer;
        }

        @NotNull
        public final String component2() {
            return this.audience;
        }

        public final long component3() {
            return this.issuedAt;
        }

        public final long component4() {
            return this.expiration;
        }

        @NotNull
        public final String component5() {
            return this.pkh;
        }

        @NotNull
        public final String component6() {
            return this.act;
        }

        @NotNull
        public final IdentityKey copy(@NotNull @Json(name = "iss") String str, @NotNull @Json(name = "aud") String str2, @Json(name = "iat") long j2, @Json(name = "exp") long j3, @NotNull @Json(name = "pkh") String str3, @NotNull @Json(name = "act") String str4) {
            Intrinsics.checkNotNullParameter(str, "issuer");
            Intrinsics.checkNotNullParameter(str2, "audience");
            String str5 = str3;
            Intrinsics.checkNotNullParameter(str5, JwtUtilsKt.DID_METHOD_PKH);
            String str6 = str4;
            Intrinsics.checkNotNullParameter(str6, "act");
            return new IdentityKey(str, str2, j2, j3, str5, str6);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IdentityKey)) {
                return false;
            }
            IdentityKey identityKey = (IdentityKey) obj;
            return Intrinsics.areEqual((Object) this.issuer, (Object) identityKey.issuer) && Intrinsics.areEqual((Object) this.audience, (Object) identityKey.audience) && this.issuedAt == identityKey.issuedAt && this.expiration == identityKey.expiration && Intrinsics.areEqual((Object) this.pkh, (Object) identityKey.pkh) && Intrinsics.areEqual((Object) this.act, (Object) identityKey.act);
        }

        @NotNull
        public final String getAct() {
            return this.act;
        }

        @NotNull
        public final String getAudience() {
            return this.audience;
        }

        public final long getExpiration() {
            return this.expiration;
        }

        public final long getIssuedAt() {
            return this.issuedAt;
        }

        @NotNull
        public String getIssuer() {
            return this.issuer;
        }

        @NotNull
        public final String getPkh() {
            return this.pkh;
        }

        public int hashCode() {
            return this.act.hashCode() + a.i(this.pkh, a.D(this.expiration, a.D(this.issuedAt, a.i(this.audience, this.issuer.hashCode() * 31, 31), 31), 31), 31);
        }

        @NotNull
        public String toString() {
            String str = this.issuer;
            String str2 = this.audience;
            long j2 = this.issuedAt;
            long j3 = this.expiration;
            String str3 = this.pkh;
            String str4 = this.act;
            StringBuilder l2 = C0118y.l("IdentityKey(issuer=", str, ", audience=", str2, ", issuedAt=");
            l2.append(j2);
            l2.append(", expiration=");
            l2.append(j3);
            l2.append(", pkh=");
            return android.support.v4.media.session.a.r(l2, str3, ", act=", str4, ")");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ IdentityKey(String str, String str2, long j2, long j3, String str3, String str4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, j2, j3, str3, (i3 & 32) != 0 ? "unregister_identity" : str4);
        }
    }
}
