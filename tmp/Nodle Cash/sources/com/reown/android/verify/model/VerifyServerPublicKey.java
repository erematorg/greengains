package com.reown.android.verify.model;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/android/verify/model/VerifyServerPublicKey;", "", "jwk", "Lcom/reown/android/verify/model/JWK;", "expiresAt", "", "<init>", "(Lcom/reown/android/verify/model/JWK;J)V", "getJwk", "()Lcom/reown/android/verify/model/JWK;", "getExpiresAt", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyServerPublicKey {
    private final long expiresAt;
    @NotNull
    private final JWK jwk;

    public VerifyServerPublicKey(@NotNull @Json(name = "publicKey") JWK jwk2, @Json(name = "expiresAt") long j2) {
        Intrinsics.checkNotNullParameter(jwk2, "jwk");
        this.jwk = jwk2;
        this.expiresAt = j2;
    }

    public static /* synthetic */ VerifyServerPublicKey copy$default(VerifyServerPublicKey verifyServerPublicKey, JWK jwk2, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            jwk2 = verifyServerPublicKey.jwk;
        }
        if ((i3 & 2) != 0) {
            j2 = verifyServerPublicKey.expiresAt;
        }
        return verifyServerPublicKey.copy(jwk2, j2);
    }

    @NotNull
    public final JWK component1() {
        return this.jwk;
    }

    public final long component2() {
        return this.expiresAt;
    }

    @NotNull
    public final VerifyServerPublicKey copy(@NotNull @Json(name = "publicKey") JWK jwk2, @Json(name = "expiresAt") long j2) {
        Intrinsics.checkNotNullParameter(jwk2, "jwk");
        return new VerifyServerPublicKey(jwk2, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VerifyServerPublicKey)) {
            return false;
        }
        VerifyServerPublicKey verifyServerPublicKey = (VerifyServerPublicKey) obj;
        return Intrinsics.areEqual((Object) this.jwk, (Object) verifyServerPublicKey.jwk) && this.expiresAt == verifyServerPublicKey.expiresAt;
    }

    public final long getExpiresAt() {
        return this.expiresAt;
    }

    @NotNull
    public final JWK getJwk() {
        return this.jwk;
    }

    public int hashCode() {
        return Long.hashCode(this.expiresAt) + (this.jwk.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        JWK jwk2 = this.jwk;
        long j2 = this.expiresAt;
        return "VerifyServerPublicKey(jwk=" + jwk2 + ", expiresAt=" + j2 + ")";
    }
}
