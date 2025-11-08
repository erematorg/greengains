package com.reown.android.internal.common.explorer.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.reown.android.pulse.model.ConnectionMethod;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BÓ\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0001\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\f\u001a\u00020\u0003\u0012\b\b\u0001\u0010\r\u001a\u00020\u000e\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0010\u0012\u0010\b\u0001\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\b\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0016\u0012\u000e\b\u0001\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\b\u0012\b\b\u0001\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0001\u0010\u001b\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u000eHÆ\u0003J\t\u0010@\u001a\u00020\u0010HÆ\u0003J\u0011\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\bHÆ\u0003J\t\u0010B\u001a\u00020\u0014HÆ\u0003J\t\u0010C\u001a\u00020\u0016HÆ\u0003J\u000f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00180\bHÆ\u0003J\t\u0010E\u001a\u00020\u001aHÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003JÕ\u0001\u0010G\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\u000e\b\u0003\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0003\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0003\u0010\u000b\u001a\u00020\u00032\b\b\u0003\u0010\f\u001a\u00020\u00032\b\b\u0003\u0010\r\u001a\u00020\u000e2\b\b\u0003\u0010\u000f\u001a\u00020\u00102\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\b2\b\b\u0003\u0010\u0013\u001a\u00020\u00142\b\b\u0003\u0010\u0015\u001a\u00020\u00162\u000e\b\u0003\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\b2\b\b\u0003\u0010\u0019\u001a\u00020\u001a2\b\b\u0003\u0010\u001b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010K\u001a\u00020LHÖ\u0001J\t\u0010M\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b-\u0010$R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\b¢\u0006\b\n\u0000\u001a\u0004\b2\u0010$R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001f¨\u0006N"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/ListingDTO;", "", "id", "", "name", "description", "homepage", "chains", "", "versions", "sdks", "appType", "imageId", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "app", "Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "injected", "Lcom/reown/android/internal/common/explorer/data/network/model/InjectedDTO;", "mobile", "Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;", "desktop", "Lcom/reown/android/internal/common/explorer/data/network/model/DesktopDTO;", "supportedStandards", "Lcom/reown/android/internal/common/explorer/data/network/model/SupportedStandardDTO;", "metadata", "Lcom/reown/android/internal/common/explorer/data/network/model/MetadataDTO;", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;Ljava/util/List;Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;Lcom/reown/android/internal/common/explorer/data/network/model/DesktopDTO;Ljava/util/List;Lcom/reown/android/internal/common/explorer/data/network/model/MetadataDTO;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getHomepage", "getChains", "()Ljava/util/List;", "getVersions", "getSdks", "getAppType", "getImageId", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "getApp", "()Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "getInjected", "getMobile", "()Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;", "getDesktop", "()Lcom/reown/android/internal/common/explorer/data/network/model/DesktopDTO;", "getSupportedStandards", "getMetadata", "()Lcom/reown/android/internal/common/explorer/data/network/model/MetadataDTO;", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ListingDTO {
    @NotNull

    /* renamed from: app  reason: collision with root package name */
    private final AppDTO f7325app;
    @NotNull
    private final String appType;
    @NotNull
    private final List<String> chains;
    @Nullable
    private final String description;
    @NotNull
    private final DesktopDTO desktop;
    @NotNull
    private final String homepage;
    @NotNull
    private final String id;
    @NotNull
    private final String imageId;
    @NotNull
    private final ImageUrlDTO imageUrl;
    @Nullable
    private final List<InjectedDTO> injected;
    @NotNull
    private final MetadataDTO metadata;
    @NotNull
    private final MobileDTO mobile;
    @NotNull
    private final String name;
    @NotNull
    private final List<String> sdks;
    @NotNull
    private final List<SupportedStandardDTO> supportedStandards;
    @NotNull
    private final String updatedAt;
    @NotNull
    private final List<String> versions;

    public ListingDTO(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "name") String str2, @Nullable @Json(name = "description") String str3, @NotNull @Json(name = "homepage") String str4, @NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "versions") List<String> list2, @NotNull @Json(name = "sdks") List<String> list3, @NotNull @Json(name = "app_type") String str5, @NotNull @Json(name = "image_id") String str6, @NotNull @Json(name = "image_url") ImageUrlDTO imageUrlDTO, @NotNull @Json(name = "app") AppDTO appDTO, @Nullable @Json(name = "injected") List<InjectedDTO> list4, @NotNull @Json(name = "mobile") MobileDTO mobileDTO, @NotNull @Json(name = "desktop") DesktopDTO desktopDTO, @NotNull @Json(name = "supported_standards") List<SupportedStandardDTO> list5, @NotNull @Json(name = "metadata") MetadataDTO metadataDTO, @NotNull @Json(name = "updatedAt") String str7) {
        String str8 = str;
        String str9 = str2;
        String str10 = str4;
        List<String> list6 = list;
        List<String> list7 = list2;
        List<String> list8 = list3;
        String str11 = str5;
        String str12 = str6;
        ImageUrlDTO imageUrlDTO2 = imageUrlDTO;
        AppDTO appDTO2 = appDTO;
        MobileDTO mobileDTO2 = mobileDTO;
        DesktopDTO desktopDTO2 = desktopDTO;
        List<SupportedStandardDTO> list9 = list5;
        MetadataDTO metadataDTO2 = metadataDTO;
        String str13 = str7;
        Intrinsics.checkNotNullParameter(str8, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str9, "name");
        Intrinsics.checkNotNullParameter(str10, "homepage");
        Intrinsics.checkNotNullParameter(list6, "chains");
        Intrinsics.checkNotNullParameter(list7, "versions");
        Intrinsics.checkNotNullParameter(list8, "sdks");
        Intrinsics.checkNotNullParameter(str11, "appType");
        Intrinsics.checkNotNullParameter(str12, "imageId");
        Intrinsics.checkNotNullParameter(imageUrlDTO2, "imageUrl");
        Intrinsics.checkNotNullParameter(appDTO2, "app");
        Intrinsics.checkNotNullParameter(mobileDTO2, ConnectionMethod.MOBILE);
        Intrinsics.checkNotNullParameter(desktopDTO2, "desktop");
        Intrinsics.checkNotNullParameter(list9, "supportedStandards");
        Intrinsics.checkNotNullParameter(metadataDTO2, TtmlNode.TAG_METADATA);
        Intrinsics.checkNotNullParameter(str13, "updatedAt");
        this.id = str8;
        this.name = str9;
        this.description = str3;
        this.homepage = str10;
        this.chains = list6;
        this.versions = list7;
        this.sdks = list8;
        this.appType = str11;
        this.imageId = str12;
        this.imageUrl = imageUrlDTO2;
        this.f7325app = appDTO2;
        this.injected = list4;
        this.mobile = mobileDTO2;
        this.desktop = desktopDTO2;
        this.supportedStandards = list9;
        this.metadata = metadataDTO2;
        this.updatedAt = str13;
    }

    public static /* synthetic */ ListingDTO copy$default(ListingDTO listingDTO, String str, String str2, String str3, String str4, List list, List list2, List list3, String str5, String str6, ImageUrlDTO imageUrlDTO, AppDTO appDTO, List list4, MobileDTO mobileDTO, DesktopDTO desktopDTO, List list5, MetadataDTO metadataDTO, String str7, int i3, Object obj) {
        ListingDTO listingDTO2 = listingDTO;
        int i4 = i3;
        return listingDTO.copy((i4 & 1) != 0 ? listingDTO2.id : str, (i4 & 2) != 0 ? listingDTO2.name : str2, (i4 & 4) != 0 ? listingDTO2.description : str3, (i4 & 8) != 0 ? listingDTO2.homepage : str4, (i4 & 16) != 0 ? listingDTO2.chains : list, (i4 & 32) != 0 ? listingDTO2.versions : list2, (i4 & 64) != 0 ? listingDTO2.sdks : list3, (i4 & 128) != 0 ? listingDTO2.appType : str5, (i4 & 256) != 0 ? listingDTO2.imageId : str6, (i4 & 512) != 0 ? listingDTO2.imageUrl : imageUrlDTO, (i4 & 1024) != 0 ? listingDTO2.f7325app : appDTO, (i4 & 2048) != 0 ? listingDTO2.injected : list4, (i4 & 4096) != 0 ? listingDTO2.mobile : mobileDTO, (i4 & 8192) != 0 ? listingDTO2.desktop : desktopDTO, (i4 & 16384) != 0 ? listingDTO2.supportedStandards : list5, (i4 & 32768) != 0 ? listingDTO2.metadata : metadataDTO, (i4 & 65536) != 0 ? listingDTO2.updatedAt : str7);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final ImageUrlDTO component10() {
        return this.imageUrl;
    }

    @NotNull
    public final AppDTO component11() {
        return this.f7325app;
    }

    @Nullable
    public final List<InjectedDTO> component12() {
        return this.injected;
    }

    @NotNull
    public final MobileDTO component13() {
        return this.mobile;
    }

    @NotNull
    public final DesktopDTO component14() {
        return this.desktop;
    }

    @NotNull
    public final List<SupportedStandardDTO> component15() {
        return this.supportedStandards;
    }

    @NotNull
    public final MetadataDTO component16() {
        return this.metadata;
    }

    @NotNull
    public final String component17() {
        return this.updatedAt;
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
        return this.homepage;
    }

    @NotNull
    public final List<String> component5() {
        return this.chains;
    }

    @NotNull
    public final List<String> component6() {
        return this.versions;
    }

    @NotNull
    public final List<String> component7() {
        return this.sdks;
    }

    @NotNull
    public final String component8() {
        return this.appType;
    }

    @NotNull
    public final String component9() {
        return this.imageId;
    }

    @NotNull
    public final ListingDTO copy(@NotNull @Json(name = "id") String str, @NotNull @Json(name = "name") String str2, @Nullable @Json(name = "description") String str3, @NotNull @Json(name = "homepage") String str4, @NotNull @Json(name = "chains") List<String> list, @NotNull @Json(name = "versions") List<String> list2, @NotNull @Json(name = "sdks") List<String> list3, @NotNull @Json(name = "app_type") String str5, @NotNull @Json(name = "image_id") String str6, @NotNull @Json(name = "image_url") ImageUrlDTO imageUrlDTO, @NotNull @Json(name = "app") AppDTO appDTO, @Nullable @Json(name = "injected") List<InjectedDTO> list4, @NotNull @Json(name = "mobile") MobileDTO mobileDTO, @NotNull @Json(name = "desktop") DesktopDTO desktopDTO, @NotNull @Json(name = "supported_standards") List<SupportedStandardDTO> list5, @NotNull @Json(name = "metadata") MetadataDTO metadataDTO, @NotNull @Json(name = "updatedAt") String str7) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str4, "homepage");
        Intrinsics.checkNotNullParameter(list, "chains");
        Intrinsics.checkNotNullParameter(list2, "versions");
        Intrinsics.checkNotNullParameter(list3, "sdks");
        Intrinsics.checkNotNullParameter(str5, "appType");
        Intrinsics.checkNotNullParameter(str6, "imageId");
        Intrinsics.checkNotNullParameter(imageUrlDTO, "imageUrl");
        Intrinsics.checkNotNullParameter(appDTO, "app");
        Intrinsics.checkNotNullParameter(mobileDTO, ConnectionMethod.MOBILE);
        Intrinsics.checkNotNullParameter(desktopDTO, "desktop");
        Intrinsics.checkNotNullParameter(list5, "supportedStandards");
        Intrinsics.checkNotNullParameter(metadataDTO, TtmlNode.TAG_METADATA);
        Intrinsics.checkNotNullParameter(str7, "updatedAt");
        return new ListingDTO(str8, str2, str3, str4, list, list2, list3, str5, str6, imageUrlDTO, appDTO, list4, mobileDTO, desktopDTO, list5, metadataDTO, str7);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListingDTO)) {
            return false;
        }
        ListingDTO listingDTO = (ListingDTO) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) listingDTO.id) && Intrinsics.areEqual((Object) this.name, (Object) listingDTO.name) && Intrinsics.areEqual((Object) this.description, (Object) listingDTO.description) && Intrinsics.areEqual((Object) this.homepage, (Object) listingDTO.homepage) && Intrinsics.areEqual((Object) this.chains, (Object) listingDTO.chains) && Intrinsics.areEqual((Object) this.versions, (Object) listingDTO.versions) && Intrinsics.areEqual((Object) this.sdks, (Object) listingDTO.sdks) && Intrinsics.areEqual((Object) this.appType, (Object) listingDTO.appType) && Intrinsics.areEqual((Object) this.imageId, (Object) listingDTO.imageId) && Intrinsics.areEqual((Object) this.imageUrl, (Object) listingDTO.imageUrl) && Intrinsics.areEqual((Object) this.f7325app, (Object) listingDTO.f7325app) && Intrinsics.areEqual((Object) this.injected, (Object) listingDTO.injected) && Intrinsics.areEqual((Object) this.mobile, (Object) listingDTO.mobile) && Intrinsics.areEqual((Object) this.desktop, (Object) listingDTO.desktop) && Intrinsics.areEqual((Object) this.supportedStandards, (Object) listingDTO.supportedStandards) && Intrinsics.areEqual((Object) this.metadata, (Object) listingDTO.metadata) && Intrinsics.areEqual((Object) this.updatedAt, (Object) listingDTO.updatedAt);
    }

    @NotNull
    public final AppDTO getApp() {
        return this.f7325app;
    }

    @NotNull
    public final String getAppType() {
        return this.appType;
    }

    @NotNull
    public final List<String> getChains() {
        return this.chains;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final DesktopDTO getDesktop() {
        return this.desktop;
    }

    @NotNull
    public final String getHomepage() {
        return this.homepage;
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
    public final ImageUrlDTO getImageUrl() {
        return this.imageUrl;
    }

    @Nullable
    public final List<InjectedDTO> getInjected() {
        return this.injected;
    }

    @NotNull
    public final MetadataDTO getMetadata() {
        return this.metadata;
    }

    @NotNull
    public final MobileDTO getMobile() {
        return this.mobile;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final List<String> getSdks() {
        return this.sdks;
    }

    @NotNull
    public final List<SupportedStandardDTO> getSupportedStandards() {
        return this.supportedStandards;
    }

    @NotNull
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    @NotNull
    public final List<String> getVersions() {
        return this.versions;
    }

    public int hashCode() {
        int i3 = a.i(this.name, this.id.hashCode() * 31, 31);
        String str = this.description;
        int i4 = 0;
        int hashCode = (this.f7325app.hashCode() + ((this.imageUrl.hashCode() + a.i(this.imageId, a.i(this.appType, a.j(this.sdks, a.j(this.versions, a.j(this.chains, a.i(this.homepage, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31), 31), 31), 31), 31), 31)) * 31)) * 31;
        List<InjectedDTO> list = this.injected;
        if (list != null) {
            i4 = list.hashCode();
        }
        int hashCode2 = this.mobile.hashCode();
        return this.updatedAt.hashCode() + ((this.metadata.hashCode() + a.j(this.supportedStandards, (this.desktop.hashCode() + ((hashCode2 + ((hashCode + i4) * 31)) * 31)) * 31, 31)) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.description;
        String str4 = this.homepage;
        List<String> list = this.chains;
        List<String> list2 = this.versions;
        List<String> list3 = this.sdks;
        String str5 = this.appType;
        String str6 = this.imageId;
        ImageUrlDTO imageUrlDTO = this.imageUrl;
        AppDTO appDTO = this.f7325app;
        List<InjectedDTO> list4 = this.injected;
        MobileDTO mobileDTO = this.mobile;
        DesktopDTO desktopDTO = this.desktop;
        List<SupportedStandardDTO> list5 = this.supportedStandards;
        MetadataDTO metadataDTO = this.metadata;
        String str7 = this.updatedAt;
        StringBuilder l2 = C0118y.l("ListingDTO(id=", str, ", name=", str2, ", description=");
        b.w(l2, str3, ", homepage=", str4, ", chains=");
        l2.append(list);
        l2.append(", versions=");
        l2.append(list2);
        l2.append(", sdks=");
        l2.append(list3);
        l2.append(", appType=");
        l2.append(str5);
        l2.append(", imageId=");
        l2.append(str6);
        l2.append(", imageUrl=");
        l2.append(imageUrlDTO);
        l2.append(", app=");
        l2.append(appDTO);
        l2.append(", injected=");
        l2.append(list4);
        l2.append(", mobile=");
        l2.append(mobileDTO);
        l2.append(", desktop=");
        l2.append(desktopDTO);
        l2.append(", supportedStandards=");
        l2.append(list5);
        l2.append(", metadata=");
        l2.append(metadataDTO);
        l2.append(", updatedAt=");
        return A.a.n(l2, str7, ")");
    }
}
