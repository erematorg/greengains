package com.reown.android.internal.common.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006#"}, d2 = {"Lcom/reown/android/internal/common/model/AppMetaData;", "", "description", "", "url", "icons", "", "name", "redirect", "Lcom/reown/android/internal/common/model/Redirect;", "verifyUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/Redirect;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getUrl", "getIcons", "()Ljava/util/List;", "getName", "getRedirect", "()Lcom/reown/android/internal/common/model/Redirect;", "getVerifyUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppMetaData {
    @NotNull
    private final String description;
    @NotNull
    private final List<String> icons;
    @NotNull
    private final String name;
    @Nullable
    private final Redirect redirect;
    @NotNull
    private final String url;
    @Nullable
    private final String verifyUrl;

    public AppMetaData(@NotNull @Json(name = "description") String str, @NotNull @Json(name = "url") String str2, @NotNull @Json(name = "icons") List<String> list, @NotNull @Json(name = "name") String str3, @Nullable @Json(name = "redirect") Redirect redirect2, @Nullable @Json(name = "verifyUrl") String str4) {
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(str2, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        Intrinsics.checkNotNullParameter(str3, "name");
        this.description = str;
        this.url = str2;
        this.icons = list;
        this.name = str3;
        this.redirect = redirect2;
        this.verifyUrl = str4;
    }

    public static /* synthetic */ AppMetaData copy$default(AppMetaData appMetaData, String str, String str2, List<String> list, String str3, Redirect redirect2, String str4, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = appMetaData.description;
        }
        if ((i3 & 2) != 0) {
            str2 = appMetaData.url;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            list = appMetaData.icons;
        }
        List<String> list2 = list;
        if ((i3 & 8) != 0) {
            str3 = appMetaData.name;
        }
        String str6 = str3;
        if ((i3 & 16) != 0) {
            redirect2 = appMetaData.redirect;
        }
        Redirect redirect3 = redirect2;
        if ((i3 & 32) != 0) {
            str4 = appMetaData.verifyUrl;
        }
        return appMetaData.copy(str, str5, list2, str6, redirect3, str4);
    }

    @NotNull
    public final String component1() {
        return this.description;
    }

    @NotNull
    public final String component2() {
        return this.url;
    }

    @NotNull
    public final List<String> component3() {
        return this.icons;
    }

    @NotNull
    public final String component4() {
        return this.name;
    }

    @Nullable
    public final Redirect component5() {
        return this.redirect;
    }

    @Nullable
    public final String component6() {
        return this.verifyUrl;
    }

    @NotNull
    public final AppMetaData copy(@NotNull @Json(name = "description") String str, @NotNull @Json(name = "url") String str2, @NotNull @Json(name = "icons") List<String> list, @NotNull @Json(name = "name") String str3, @Nullable @Json(name = "redirect") Redirect redirect2, @Nullable @Json(name = "verifyUrl") String str4) {
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(str2, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        Intrinsics.checkNotNullParameter(str3, "name");
        return new AppMetaData(str, str2, list, str3, redirect2, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppMetaData)) {
            return false;
        }
        AppMetaData appMetaData = (AppMetaData) obj;
        return Intrinsics.areEqual((Object) this.description, (Object) appMetaData.description) && Intrinsics.areEqual((Object) this.url, (Object) appMetaData.url) && Intrinsics.areEqual((Object) this.icons, (Object) appMetaData.icons) && Intrinsics.areEqual((Object) this.name, (Object) appMetaData.name) && Intrinsics.areEqual((Object) this.redirect, (Object) appMetaData.redirect) && Intrinsics.areEqual((Object) this.verifyUrl, (Object) appMetaData.verifyUrl);
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final List<String> getIcons() {
        return this.icons;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final Redirect getRedirect() {
        return this.redirect;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @Nullable
    public final String getVerifyUrl() {
        return this.verifyUrl;
    }

    public int hashCode() {
        int i3 = a.i(this.name, a.j(this.icons, a.i(this.url, this.description.hashCode() * 31, 31), 31), 31);
        Redirect redirect2 = this.redirect;
        int i4 = 0;
        int hashCode = (i3 + (redirect2 == null ? 0 : redirect2.hashCode())) * 31;
        String str = this.verifyUrl;
        if (str != null) {
            i4 = str.hashCode();
        }
        return hashCode + i4;
    }

    @NotNull
    public String toString() {
        String str = this.description;
        String str2 = this.url;
        List<String> list = this.icons;
        String str3 = this.name;
        Redirect redirect2 = this.redirect;
        String str4 = this.verifyUrl;
        StringBuilder l2 = C0118y.l("AppMetaData(description=", str, ", url=", str2, ", icons=");
        l2.append(list);
        l2.append(", name=");
        l2.append(str3);
        l2.append(", redirect=");
        l2.append(redirect2);
        l2.append(", verifyUrl=");
        l2.append(str4);
        l2.append(")");
        return l2.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AppMetaData(String str, String str2, List list, String str3, Redirect redirect2, String str4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? "" : str2, list, str3, (i3 & 16) != 0 ? null : redirect2, (i3 & 32) != 0 ? null : str4);
    }
}
