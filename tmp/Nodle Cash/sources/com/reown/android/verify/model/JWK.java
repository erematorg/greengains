package com.reown.android.verify.model;

import androidx.compose.animation.core.a;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JK\u0010\u001c\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u00032\b\b\u0003\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006\""}, d2 = {"Lcom/reown/android/verify/model/JWK;", "", "crv", "", "ext", "", "keyOps", "", "kty", "x", "y", "<init>", "(Ljava/lang/String;ZLjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCrv", "()Ljava/lang/String;", "getExt", "()Z", "getKeyOps", "()Ljava/util/List;", "getKty", "getX", "getY", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JWK {
    @NotNull
    private final String crv;
    private final boolean ext;
    @NotNull
    private final List<String> keyOps;
    @NotNull
    private final String kty;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    private final String f7435x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    private final String f7436y;

    public JWK(@NotNull @Json(name = "crv") String str, @Json(name = "ext") boolean z2, @NotNull @Json(name = "key_ops") List<String> list, @NotNull @Json(name = "kty") String str2, @NotNull @Json(name = "x") String str3, @NotNull @Json(name = "y") String str4) {
        Intrinsics.checkNotNullParameter(str, "crv");
        Intrinsics.checkNotNullParameter(list, "keyOps");
        Intrinsics.checkNotNullParameter(str2, "kty");
        Intrinsics.checkNotNullParameter(str3, "x");
        Intrinsics.checkNotNullParameter(str4, "y");
        this.crv = str;
        this.ext = z2;
        this.keyOps = list;
        this.kty = str2;
        this.f7435x = str3;
        this.f7436y = str4;
    }

    public static /* synthetic */ JWK copy$default(JWK jwk, String str, boolean z2, List<String> list, String str2, String str3, String str4, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = jwk.crv;
        }
        if ((i3 & 2) != 0) {
            z2 = jwk.ext;
        }
        boolean z3 = z2;
        if ((i3 & 4) != 0) {
            list = jwk.keyOps;
        }
        List<String> list2 = list;
        if ((i3 & 8) != 0) {
            str2 = jwk.kty;
        }
        String str5 = str2;
        if ((i3 & 16) != 0) {
            str3 = jwk.f7435x;
        }
        String str6 = str3;
        if ((i3 & 32) != 0) {
            str4 = jwk.f7436y;
        }
        return jwk.copy(str, z3, list2, str5, str6, str4);
    }

    @NotNull
    public final String component1() {
        return this.crv;
    }

    public final boolean component2() {
        return this.ext;
    }

    @NotNull
    public final List<String> component3() {
        return this.keyOps;
    }

    @NotNull
    public final String component4() {
        return this.kty;
    }

    @NotNull
    public final String component5() {
        return this.f7435x;
    }

    @NotNull
    public final String component6() {
        return this.f7436y;
    }

    @NotNull
    public final JWK copy(@NotNull @Json(name = "crv") String str, @Json(name = "ext") boolean z2, @NotNull @Json(name = "key_ops") List<String> list, @NotNull @Json(name = "kty") String str2, @NotNull @Json(name = "x") String str3, @NotNull @Json(name = "y") String str4) {
        Intrinsics.checkNotNullParameter(str, "crv");
        Intrinsics.checkNotNullParameter(list, "keyOps");
        Intrinsics.checkNotNullParameter(str2, "kty");
        Intrinsics.checkNotNullParameter(str3, "x");
        Intrinsics.checkNotNullParameter(str4, "y");
        return new JWK(str, z2, list, str2, str3, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JWK)) {
            return false;
        }
        JWK jwk = (JWK) obj;
        return Intrinsics.areEqual((Object) this.crv, (Object) jwk.crv) && this.ext == jwk.ext && Intrinsics.areEqual((Object) this.keyOps, (Object) jwk.keyOps) && Intrinsics.areEqual((Object) this.kty, (Object) jwk.kty) && Intrinsics.areEqual((Object) this.f7435x, (Object) jwk.f7435x) && Intrinsics.areEqual((Object) this.f7436y, (Object) jwk.f7436y);
    }

    @NotNull
    public final String getCrv() {
        return this.crv;
    }

    public final boolean getExt() {
        return this.ext;
    }

    @NotNull
    public final List<String> getKeyOps() {
        return this.keyOps;
    }

    @NotNull
    public final String getKty() {
        return this.kty;
    }

    @NotNull
    public final String getX() {
        return this.f7435x;
    }

    @NotNull
    public final String getY() {
        return this.f7436y;
    }

    public int hashCode() {
        return this.f7436y.hashCode() + a.i(this.f7435x, a.i(this.kty, a.j(this.keyOps, android.support.v4.media.session.a.f(this.ext, this.crv.hashCode() * 31, 31), 31), 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.crv;
        boolean z2 = this.ext;
        List<String> list = this.keyOps;
        String str2 = this.kty;
        String str3 = this.f7435x;
        String str4 = this.f7436y;
        StringBuilder sb = new StringBuilder("JWK(crv=");
        sb.append(str);
        sb.append(", ext=");
        sb.append(z2);
        sb.append(", keyOps=");
        sb.append(list);
        sb.append(", kty=");
        sb.append(str2);
        sb.append(", x=");
        return android.support.v4.media.session.a.r(sb, str3, ", y=", str4, ")");
    }
}
