package com.reown.android.internal.common.model;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/reown/android/internal/common/model/Redirect;", "", "native", "", "universal", "linkMode", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getNative", "()Ljava/lang/String;", "getUniversal", "getLinkMode", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Redirect {
    private final boolean linkMode;
    @Nullable

    /* renamed from: native  reason: not valid java name */
    private final String f72native;
    @Nullable
    private final String universal;

    public Redirect() {
        this((String) null, (String) null, false, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Redirect copy$default(Redirect redirect, String str, String str2, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = redirect.f72native;
        }
        if ((i3 & 2) != 0) {
            str2 = redirect.universal;
        }
        if ((i3 & 4) != 0) {
            z2 = redirect.linkMode;
        }
        return redirect.copy(str, str2, z2);
    }

    @Nullable
    public final String component1() {
        return this.f72native;
    }

    @Nullable
    public final String component2() {
        return this.universal;
    }

    public final boolean component3() {
        return this.linkMode;
    }

    @NotNull
    public final Redirect copy(@Nullable @Json(name = "native") String str, @Nullable @Json(name = "universal") String str2, @Json(name = "linkMode") boolean z2) {
        return new Redirect(str, str2, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Redirect)) {
            return false;
        }
        Redirect redirect = (Redirect) obj;
        return Intrinsics.areEqual((Object) this.f72native, (Object) redirect.f72native) && Intrinsics.areEqual((Object) this.universal, (Object) redirect.universal) && this.linkMode == redirect.linkMode;
    }

    public final boolean getLinkMode() {
        return this.linkMode;
    }

    @Nullable
    public final String getNative() {
        return this.f72native;
    }

    @Nullable
    public final String getUniversal() {
        return this.universal;
    }

    public int hashCode() {
        String str = this.f72native;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.universal;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return Boolean.hashCode(this.linkMode) + ((hashCode + i3) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.f72native;
        String str2 = this.universal;
        return a.s(C0118y.l("Redirect(native=", str, ", universal=", str2, ", linkMode="), this.linkMode, ")");
    }

    public Redirect(@Nullable @Json(name = "native") String str, @Nullable @Json(name = "universal") String str2, @Json(name = "linkMode") boolean z2) {
        this.f72native = str;
        this.universal = str2;
        this.linkMode = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Redirect(String str, String str2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? false : z2);
    }
}
