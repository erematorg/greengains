package com.reown.android.internal.common.modal.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0001\u0010'\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006."}, d2 = {"Lcom/reown/android/internal/common/modal/data/network/model/WalletDTO;", "", "id", "", "name", "homePage", "imageId", "order", "mobileLink", "desktopLink", "webappLink", "appStore", "playStore", "linkMode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getHomePage", "getImageId", "getOrder", "getMobileLink", "getDesktopLink", "getWebappLink", "getAppStore", "getPlayStore", "getLinkMode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletDTO {
    @Nullable
    private final String appStore;
    @Nullable
    private final String desktopLink;
    @NotNull
    private final String homePage;
    @NotNull
    private final String id;
    @NotNull
    private final String imageId;
    @Nullable
    private final String linkMode;
    @Nullable
    private final String mobileLink;
    @NotNull
    private final String name;
    @NotNull
    private final String order;
    @Nullable
    private final String playStore;
    @Nullable
    private final String webappLink;

    public WalletDTO(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "name") String str2, @NotNull @Json(name = "homepage") String str3, @NotNull @Json(name = "image_id") String str4, @NotNull @Json(name = "order") String str5, @Nullable @Json(name = "mobile_link") String str6, @Nullable @Json(name = "desktop_link") String str7, @Nullable @Json(name = "webapp_link") String str8, @Nullable @Json(name = "app_store") String str9, @Nullable @Json(name = "play_store") String str10, @Nullable @Json(name = "link_mode") String str11) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "homePage");
        Intrinsics.checkNotNullParameter(str4, "imageId");
        Intrinsics.checkNotNullParameter(str5, "order");
        this.id = str;
        this.name = str2;
        this.homePage = str3;
        this.imageId = str4;
        this.order = str5;
        this.mobileLink = str6;
        this.desktopLink = str7;
        this.webappLink = str8;
        this.appStore = str9;
        this.playStore = str10;
        this.linkMode = str11;
    }

    public static /* synthetic */ WalletDTO copy$default(WalletDTO walletDTO, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i3, Object obj) {
        WalletDTO walletDTO2 = walletDTO;
        int i4 = i3;
        return walletDTO.copy((i4 & 1) != 0 ? walletDTO2.id : str, (i4 & 2) != 0 ? walletDTO2.name : str2, (i4 & 4) != 0 ? walletDTO2.homePage : str3, (i4 & 8) != 0 ? walletDTO2.imageId : str4, (i4 & 16) != 0 ? walletDTO2.order : str5, (i4 & 32) != 0 ? walletDTO2.mobileLink : str6, (i4 & 64) != 0 ? walletDTO2.desktopLink : str7, (i4 & 128) != 0 ? walletDTO2.webappLink : str8, (i4 & 256) != 0 ? walletDTO2.appStore : str9, (i4 & 512) != 0 ? walletDTO2.playStore : str10, (i4 & 1024) != 0 ? walletDTO2.linkMode : str11);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final String component10() {
        return this.playStore;
    }

    @Nullable
    public final String component11() {
        return this.linkMode;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.homePage;
    }

    @NotNull
    public final String component4() {
        return this.imageId;
    }

    @NotNull
    public final String component5() {
        return this.order;
    }

    @Nullable
    public final String component6() {
        return this.mobileLink;
    }

    @Nullable
    public final String component7() {
        return this.desktopLink;
    }

    @Nullable
    public final String component8() {
        return this.webappLink;
    }

    @Nullable
    public final String component9() {
        return this.appStore;
    }

    @NotNull
    public final WalletDTO copy(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "name") String str2, @NotNull @Json(name = "homepage") String str3, @NotNull @Json(name = "image_id") String str4, @NotNull @Json(name = "order") String str5, @Nullable @Json(name = "mobile_link") String str6, @Nullable @Json(name = "desktop_link") String str7, @Nullable @Json(name = "webapp_link") String str8, @Nullable @Json(name = "app_store") String str9, @Nullable @Json(name = "play_store") String str10, @Nullable @Json(name = "link_mode") String str11) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        String str12 = str3;
        Intrinsics.checkNotNullParameter(str12, "homePage");
        String str13 = str4;
        Intrinsics.checkNotNullParameter(str13, "imageId");
        String str14 = str5;
        Intrinsics.checkNotNullParameter(str14, "order");
        return new WalletDTO(str, str2, str12, str13, str14, str6, str7, str8, str9, str10, str11);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WalletDTO)) {
            return false;
        }
        WalletDTO walletDTO = (WalletDTO) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) walletDTO.id) && Intrinsics.areEqual((Object) this.name, (Object) walletDTO.name) && Intrinsics.areEqual((Object) this.homePage, (Object) walletDTO.homePage) && Intrinsics.areEqual((Object) this.imageId, (Object) walletDTO.imageId) && Intrinsics.areEqual((Object) this.order, (Object) walletDTO.order) && Intrinsics.areEqual((Object) this.mobileLink, (Object) walletDTO.mobileLink) && Intrinsics.areEqual((Object) this.desktopLink, (Object) walletDTO.desktopLink) && Intrinsics.areEqual((Object) this.webappLink, (Object) walletDTO.webappLink) && Intrinsics.areEqual((Object) this.appStore, (Object) walletDTO.appStore) && Intrinsics.areEqual((Object) this.playStore, (Object) walletDTO.playStore) && Intrinsics.areEqual((Object) this.linkMode, (Object) walletDTO.linkMode);
    }

    @Nullable
    public final String getAppStore() {
        return this.appStore;
    }

    @Nullable
    public final String getDesktopLink() {
        return this.desktopLink;
    }

    @NotNull
    public final String getHomePage() {
        return this.homePage;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getImageId() {
        return this.imageId;
    }

    @Nullable
    public final String getLinkMode() {
        return this.linkMode;
    }

    @Nullable
    public final String getMobileLink() {
        return this.mobileLink;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getOrder() {
        return this.order;
    }

    @Nullable
    public final String getPlayStore() {
        return this.playStore;
    }

    @Nullable
    public final String getWebappLink() {
        return this.webappLink;
    }

    public int hashCode() {
        int i3 = a.i(this.order, a.i(this.imageId, a.i(this.homePage, a.i(this.name, this.id.hashCode() * 31, 31), 31), 31), 31);
        String str = this.mobileLink;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.desktopLink;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.webappLink;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.appStore;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.playStore;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.linkMode;
        if (str6 != null) {
            i4 = str6.hashCode();
        }
        return hashCode5 + i4;
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.homePage;
        String str4 = this.imageId;
        String str5 = this.order;
        String str6 = this.mobileLink;
        String str7 = this.desktopLink;
        String str8 = this.webappLink;
        String str9 = this.appStore;
        String str10 = this.playStore;
        String str11 = this.linkMode;
        StringBuilder l2 = C0118y.l("WalletDTO(id=", str, ", name=", str2, ", homePage=");
        b.w(l2, str3, ", imageId=", str4, ", order=");
        b.w(l2, str5, ", mobileLink=", str6, ", desktopLink=");
        b.w(l2, str7, ", webappLink=", str8, ", appStore=");
        b.w(l2, str9, ", playStore=", str10, ", linkMode=");
        return A.a.n(l2, str11, ")");
    }
}
