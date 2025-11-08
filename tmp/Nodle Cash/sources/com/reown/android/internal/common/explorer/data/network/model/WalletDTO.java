package com.reown.android.internal.common.explorer.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.pulse.model.ConnectionMethod;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JQ\u0010\u001f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006&"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/WalletDTO;", "", "id", "", "name", "description", "homePage", "imageId", "mobile", "Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;", "app", "Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getHomePage", "getImageId", "getMobile", "()Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;", "getApp", "()Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletDTO {
    @NotNull

    /* renamed from: app  reason: collision with root package name */
    private final AppDTO f7326app;
    @Nullable
    private final String description;
    @NotNull
    private final String homePage;
    @NotNull
    private final String id;
    @NotNull
    private final String imageId;
    @NotNull
    private final MobileDTO mobile;
    @NotNull
    private final String name;

    public WalletDTO(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "name") String str2, @Nullable @Json(name = "description") String str3, @NotNull @Json(name = "homepage") String str4, @NotNull @Json(name = "image_id") String str5, @NotNull @Json(name = "mobile") MobileDTO mobileDTO, @NotNull @Json(name = "app") AppDTO appDTO) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str4, "homePage");
        Intrinsics.checkNotNullParameter(str5, "imageId");
        Intrinsics.checkNotNullParameter(mobileDTO, ConnectionMethod.MOBILE);
        Intrinsics.checkNotNullParameter(appDTO, "app");
        this.id = str;
        this.name = str2;
        this.description = str3;
        this.homePage = str4;
        this.imageId = str5;
        this.mobile = mobileDTO;
        this.f7326app = appDTO;
    }

    public static /* synthetic */ WalletDTO copy$default(WalletDTO walletDTO, String str, String str2, String str3, String str4, String str5, MobileDTO mobileDTO, AppDTO appDTO, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = walletDTO.id;
        }
        if ((i3 & 2) != 0) {
            str2 = walletDTO.name;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            str3 = walletDTO.description;
        }
        String str7 = str3;
        if ((i3 & 8) != 0) {
            str4 = walletDTO.homePage;
        }
        String str8 = str4;
        if ((i3 & 16) != 0) {
            str5 = walletDTO.imageId;
        }
        String str9 = str5;
        if ((i3 & 32) != 0) {
            mobileDTO = walletDTO.mobile;
        }
        MobileDTO mobileDTO2 = mobileDTO;
        if ((i3 & 64) != 0) {
            appDTO = walletDTO.f7326app;
        }
        return walletDTO.copy(str, str6, str7, str8, str9, mobileDTO2, appDTO);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @Nullable
    public final String component3() {
        return this.description;
    }

    @NotNull
    public final String component4() {
        return this.homePage;
    }

    @NotNull
    public final String component5() {
        return this.imageId;
    }

    @NotNull
    public final MobileDTO component6() {
        return this.mobile;
    }

    @NotNull
    public final AppDTO component7() {
        return this.f7326app;
    }

    @NotNull
    public final WalletDTO copy(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "name") String str2, @Nullable @Json(name = "description") String str3, @NotNull @Json(name = "homepage") String str4, @NotNull @Json(name = "image_id") String str5, @NotNull @Json(name = "mobile") MobileDTO mobileDTO, @NotNull @Json(name = "app") AppDTO appDTO) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str4, "homePage");
        Intrinsics.checkNotNullParameter(str5, "imageId");
        Intrinsics.checkNotNullParameter(mobileDTO, ConnectionMethod.MOBILE);
        Intrinsics.checkNotNullParameter(appDTO, "app");
        return new WalletDTO(str, str2, str3, str4, str5, mobileDTO, appDTO);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WalletDTO)) {
            return false;
        }
        WalletDTO walletDTO = (WalletDTO) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) walletDTO.id) && Intrinsics.areEqual((Object) this.name, (Object) walletDTO.name) && Intrinsics.areEqual((Object) this.description, (Object) walletDTO.description) && Intrinsics.areEqual((Object) this.homePage, (Object) walletDTO.homePage) && Intrinsics.areEqual((Object) this.imageId, (Object) walletDTO.imageId) && Intrinsics.areEqual((Object) this.mobile, (Object) walletDTO.mobile) && Intrinsics.areEqual((Object) this.f7326app, (Object) walletDTO.f7326app);
    }

    @NotNull
    public final AppDTO getApp() {
        return this.f7326app;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
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

    @NotNull
    public final MobileDTO getMobile() {
        return this.mobile;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int i3 = a.i(this.name, this.id.hashCode() * 31, 31);
        String str = this.description;
        return this.f7326app.hashCode() + ((this.mobile.hashCode() + a.i(this.imageId, a.i(this.homePage, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31), 31)) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.description;
        String str4 = this.homePage;
        String str5 = this.imageId;
        MobileDTO mobileDTO = this.mobile;
        AppDTO appDTO = this.f7326app;
        StringBuilder l2 = C0118y.l("WalletDTO(id=", str, ", name=", str2, ", description=");
        b.w(l2, str3, ", homePage=", str4, ", imageId=");
        l2.append(str5);
        l2.append(", mobile=");
        l2.append(mobileDTO);
        l2.append(", app=");
        l2.append(appDTO);
        l2.append(")");
        return l2.toString();
    }
}
