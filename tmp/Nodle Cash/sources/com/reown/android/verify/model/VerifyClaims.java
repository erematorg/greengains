package com.reown.android.verify.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003JB\u0010\u0019\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0005\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013¨\u0006 "}, d2 = {"Lcom/reown/android/verify/model/VerifyClaims;", "", "origin", "", "id", "isScam", "", "expiration", "", "isVerified", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;JZ)V", "getOrigin", "()Ljava/lang/String;", "getId", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getExpiration", "()J", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;JZ)Lcom/reown/android/verify/model/VerifyClaims;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyClaims {
    private final long expiration;
    @NotNull
    private final String id;
    @Nullable
    private final Boolean isScam;
    private final boolean isVerified;
    @NotNull
    private final String origin;

    public VerifyClaims(@NotNull @Json(name = "origin") String str, @NotNull @Json(name = "id") String str2, @Nullable @Json(name = "isScam") Boolean bool, @Json(name = "exp") long j2, @Json(name = "isVerified") boolean z2) {
        Intrinsics.checkNotNullParameter(str, "origin");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.ATTR_ID);
        this.origin = str;
        this.id = str2;
        this.isScam = bool;
        this.expiration = j2;
        this.isVerified = z2;
    }

    public static /* synthetic */ VerifyClaims copy$default(VerifyClaims verifyClaims, String str, String str2, Boolean bool, long j2, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = verifyClaims.origin;
        }
        if ((i3 & 2) != 0) {
            str2 = verifyClaims.id;
        }
        String str3 = str2;
        if ((i3 & 4) != 0) {
            bool = verifyClaims.isScam;
        }
        Boolean bool2 = bool;
        if ((i3 & 8) != 0) {
            j2 = verifyClaims.expiration;
        }
        long j3 = j2;
        if ((i3 & 16) != 0) {
            z2 = verifyClaims.isVerified;
        }
        return verifyClaims.copy(str, str3, bool2, j3, z2);
    }

    @NotNull
    public final String component1() {
        return this.origin;
    }

    @NotNull
    public final String component2() {
        return this.id;
    }

    @Nullable
    public final Boolean component3() {
        return this.isScam;
    }

    public final long component4() {
        return this.expiration;
    }

    public final boolean component5() {
        return this.isVerified;
    }

    @NotNull
    public final VerifyClaims copy(@NotNull @Json(name = "origin") String str, @NotNull @Json(name = "id") String str2, @Nullable @Json(name = "isScam") Boolean bool, @Json(name = "exp") long j2, @Json(name = "isVerified") boolean z2) {
        Intrinsics.checkNotNullParameter(str, "origin");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.ATTR_ID);
        return new VerifyClaims(str, str2, bool, j2, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VerifyClaims)) {
            return false;
        }
        VerifyClaims verifyClaims = (VerifyClaims) obj;
        return Intrinsics.areEqual((Object) this.origin, (Object) verifyClaims.origin) && Intrinsics.areEqual((Object) this.id, (Object) verifyClaims.id) && Intrinsics.areEqual((Object) this.isScam, (Object) verifyClaims.isScam) && this.expiration == verifyClaims.expiration && this.isVerified == verifyClaims.isVerified;
    }

    public final long getExpiration() {
        return this.expiration;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getOrigin() {
        return this.origin;
    }

    public int hashCode() {
        int i3 = a.i(this.id, this.origin.hashCode() * 31, 31);
        Boolean bool = this.isScam;
        return Boolean.hashCode(this.isVerified) + a.D(this.expiration, (i3 + (bool == null ? 0 : bool.hashCode())) * 31, 31);
    }

    @Nullable
    public final Boolean isScam() {
        return this.isScam;
    }

    public final boolean isVerified() {
        return this.isVerified;
    }

    @NotNull
    public String toString() {
        String str = this.origin;
        String str2 = this.id;
        Boolean bool = this.isScam;
        long j2 = this.expiration;
        boolean z2 = this.isVerified;
        StringBuilder l2 = C0118y.l("VerifyClaims(origin=", str, ", id=", str2, ", isScam=");
        l2.append(bool);
        l2.append(", expiration=");
        l2.append(j2);
        l2.append(", isVerified=");
        l2.append(z2);
        l2.append(")");
        return l2.toString();
    }
}
