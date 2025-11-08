package com.reown.foundation.crypto.data.repository.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.foundation.util.jwt.JwtClaims;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/crypto/data/repository/model/IrnJwtClaims;", "Lcom/reown/foundation/util/jwt/JwtClaims;", "issuer", "", "subject", "audience", "issuedAt", "", "expiration", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getIssuer", "()Ljava/lang/String;", "getSubject", "getAudience", "getIssuedAt", "()J", "getExpiration", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrnJwtClaims implements JwtClaims {
    @NotNull
    private final String audience;
    private final long expiration;
    private final long issuedAt;
    @NotNull
    private final String issuer;
    @NotNull
    private final String subject;

    public IrnJwtClaims(@NotNull @Json(name = "iss") String str, @NotNull @Json(name = "sub") String str2, @NotNull @Json(name = "aud") String str3, @Json(name = "iat") long j2, @Json(name = "exp") long j3) {
        Intrinsics.checkNotNullParameter(str, "issuer");
        Intrinsics.checkNotNullParameter(str2, "subject");
        Intrinsics.checkNotNullParameter(str3, "audience");
        this.issuer = str;
        this.subject = str2;
        this.audience = str3;
        this.issuedAt = j2;
        this.expiration = j3;
    }

    public static /* synthetic */ IrnJwtClaims copy$default(IrnJwtClaims irnJwtClaims, String str, String str2, String str3, long j2, long j3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = irnJwtClaims.issuer;
        }
        if ((i3 & 2) != 0) {
            str2 = irnJwtClaims.subject;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            str3 = irnJwtClaims.audience;
        }
        String str5 = str3;
        if ((i3 & 8) != 0) {
            j2 = irnJwtClaims.issuedAt;
        }
        long j4 = j2;
        if ((i3 & 16) != 0) {
            j3 = irnJwtClaims.expiration;
        }
        return irnJwtClaims.copy(str, str4, str5, j4, j3);
    }

    @NotNull
    public final String component1() {
        return this.issuer;
    }

    @NotNull
    public final String component2() {
        return this.subject;
    }

    @NotNull
    public final String component3() {
        return this.audience;
    }

    public final long component4() {
        return this.issuedAt;
    }

    public final long component5() {
        return this.expiration;
    }

    @NotNull
    public final IrnJwtClaims copy(@NotNull @Json(name = "iss") String str, @NotNull @Json(name = "sub") String str2, @NotNull @Json(name = "aud") String str3, @Json(name = "iat") long j2, @Json(name = "exp") long j3) {
        Intrinsics.checkNotNullParameter(str, "issuer");
        Intrinsics.checkNotNullParameter(str2, "subject");
        Intrinsics.checkNotNullParameter(str3, "audience");
        return new IrnJwtClaims(str, str2, str3, j2, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IrnJwtClaims)) {
            return false;
        }
        IrnJwtClaims irnJwtClaims = (IrnJwtClaims) obj;
        return Intrinsics.areEqual((Object) this.issuer, (Object) irnJwtClaims.issuer) && Intrinsics.areEqual((Object) this.subject, (Object) irnJwtClaims.subject) && Intrinsics.areEqual((Object) this.audience, (Object) irnJwtClaims.audience) && this.issuedAt == irnJwtClaims.issuedAt && this.expiration == irnJwtClaims.expiration;
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
    public final String getSubject() {
        return this.subject;
    }

    public int hashCode() {
        return Long.hashCode(this.expiration) + a.D(this.issuedAt, a.i(this.audience, a.i(this.subject, this.issuer.hashCode() * 31, 31), 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.issuer;
        String str2 = this.subject;
        String str3 = this.audience;
        long j2 = this.issuedAt;
        long j3 = this.expiration;
        StringBuilder l2 = C0118y.l("IrnJwtClaims(issuer=", str, ", subject=", str2, ", audience=");
        l2.append(str3);
        l2.append(", issuedAt=");
        l2.append(j2);
        l2.append(", expiration=");
        l2.append(j3);
        l2.append(")");
        return l2.toString();
    }
}
