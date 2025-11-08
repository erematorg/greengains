package com.reown.android.internal.common.explorer.data.model;

import android.net.Uri;
import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.android.pulse.model.ConnectionMethod;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B±\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\t\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\t\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0007HÆ\u0003J\u000f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u000fHÆ\u0003J\t\u0010B\u001a\u00020\u0011HÆ\u0003J\u0011\u0010C\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\tHÆ\u0003J\t\u0010D\u001a\u00020\u0015HÆ\u0003J\t\u0010E\u001a\u00020\u0017HÆ\u0003J\u000f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00190\tHÆ\u0003J\t\u0010G\u001a\u00020\u001bHÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003JÕ\u0001\u0010I\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u0003HÆ\u0001J\u0013\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010M\u001a\u00020NHÖ\u0001J\t\u0010O\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\t¢\u0006\b\n\u0000\u001a\u0004\b4\u0010&R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u0010\u001c\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010 ¨\u0006P"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/Listing;", "", "id", "", "name", "description", "homepage", "Landroid/net/Uri;", "chains", "", "versions", "sdks", "appType", "imageId", "imageUrl", "Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "app", "Lcom/reown/android/internal/common/explorer/data/model/App;", "injected", "Lcom/reown/android/internal/common/explorer/data/model/Injected;", "mobile", "Lcom/reown/android/internal/common/explorer/data/model/Mobile;", "desktop", "Lcom/reown/android/internal/common/explorer/data/model/Desktop;", "supportedStandards", "Lcom/reown/android/internal/common/explorer/data/model/SupportedStandard;", "metadata", "Lcom/reown/android/internal/common/explorer/data/model/Metadata;", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;Lcom/reown/android/internal/common/explorer/data/model/App;Ljava/util/List;Lcom/reown/android/internal/common/explorer/data/model/Mobile;Lcom/reown/android/internal/common/explorer/data/model/Desktop;Ljava/util/List;Lcom/reown/android/internal/common/explorer/data/model/Metadata;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getHomepage", "()Landroid/net/Uri;", "getChains", "()Ljava/util/List;", "getVersions", "getSdks", "getAppType", "getImageId", "getImageUrl", "()Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "getApp", "()Lcom/reown/android/internal/common/explorer/data/model/App;", "getInjected", "getMobile", "()Lcom/reown/android/internal/common/explorer/data/model/Mobile;", "getDesktop", "()Lcom/reown/android/internal/common/explorer/data/model/Desktop;", "getSupportedStandards", "getMetadata", "()Lcom/reown/android/internal/common/explorer/data/model/Metadata;", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Listing {
    @NotNull

    /* renamed from: app  reason: collision with root package name */
    private final App f7323app;
    @NotNull
    private final String appType;
    @NotNull
    private final List<String> chains;
    @Nullable
    private final String description;
    @NotNull
    private final Desktop desktop;
    @NotNull
    private final Uri homepage;
    @NotNull
    private final String id;
    @NotNull
    private final String imageId;
    @NotNull
    private final ImageUrl imageUrl;
    @Nullable
    private final List<Injected> injected;
    @NotNull
    private final Metadata metadata;
    @NotNull
    private final Mobile mobile;
    @NotNull
    private final String name;
    @NotNull
    private final List<String> sdks;
    @NotNull
    private final List<SupportedStandard> supportedStandards;
    @NotNull
    private final String updatedAt;
    @NotNull
    private final List<String> versions;

    public Listing(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull Uri uri, @NotNull List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull String str4, @NotNull String str5, @NotNull ImageUrl imageUrl2, @NotNull App app2, @Nullable List<Injected> list4, @NotNull Mobile mobile2, @NotNull Desktop desktop2, @NotNull List<SupportedStandard> list5, @NotNull Metadata metadata2, @NotNull String str6) {
        String str7 = str;
        String str8 = str2;
        Uri uri2 = uri;
        List<String> list6 = list;
        List<String> list7 = list2;
        List<String> list8 = list3;
        String str9 = str4;
        String str10 = str5;
        ImageUrl imageUrl3 = imageUrl2;
        App app3 = app2;
        Mobile mobile3 = mobile2;
        Desktop desktop3 = desktop2;
        List<SupportedStandard> list9 = list5;
        Metadata metadata3 = metadata2;
        String str11 = str6;
        Intrinsics.checkNotNullParameter(str7, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str8, "name");
        Intrinsics.checkNotNullParameter(uri2, "homepage");
        Intrinsics.checkNotNullParameter(list6, "chains");
        Intrinsics.checkNotNullParameter(list7, "versions");
        Intrinsics.checkNotNullParameter(list8, "sdks");
        Intrinsics.checkNotNullParameter(str9, "appType");
        Intrinsics.checkNotNullParameter(str10, "imageId");
        Intrinsics.checkNotNullParameter(imageUrl3, "imageUrl");
        Intrinsics.checkNotNullParameter(app3, "app");
        Intrinsics.checkNotNullParameter(mobile3, ConnectionMethod.MOBILE);
        Intrinsics.checkNotNullParameter(desktop3, "desktop");
        Intrinsics.checkNotNullParameter(list9, "supportedStandards");
        Intrinsics.checkNotNullParameter(metadata3, TtmlNode.TAG_METADATA);
        Intrinsics.checkNotNullParameter(str11, "updatedAt");
        this.id = str7;
        this.name = str8;
        this.description = str3;
        this.homepage = uri2;
        this.chains = list6;
        this.versions = list7;
        this.sdks = list8;
        this.appType = str9;
        this.imageId = str10;
        this.imageUrl = imageUrl3;
        this.f7323app = app3;
        this.injected = list4;
        this.mobile = mobile3;
        this.desktop = desktop3;
        this.supportedStandards = list9;
        this.metadata = metadata3;
        this.updatedAt = str11;
    }

    public static /* synthetic */ Listing copy$default(Listing listing, String str, String str2, String str3, Uri uri, List list, List list2, List list3, String str4, String str5, ImageUrl imageUrl2, App app2, List list4, Mobile mobile2, Desktop desktop2, List list5, Metadata metadata2, String str6, int i3, Object obj) {
        Listing listing2 = listing;
        int i4 = i3;
        return listing.copy((i4 & 1) != 0 ? listing2.id : str, (i4 & 2) != 0 ? listing2.name : str2, (i4 & 4) != 0 ? listing2.description : str3, (i4 & 8) != 0 ? listing2.homepage : uri, (i4 & 16) != 0 ? listing2.chains : list, (i4 & 32) != 0 ? listing2.versions : list2, (i4 & 64) != 0 ? listing2.sdks : list3, (i4 & 128) != 0 ? listing2.appType : str4, (i4 & 256) != 0 ? listing2.imageId : str5, (i4 & 512) != 0 ? listing2.imageUrl : imageUrl2, (i4 & 1024) != 0 ? listing2.f7323app : app2, (i4 & 2048) != 0 ? listing2.injected : list4, (i4 & 4096) != 0 ? listing2.mobile : mobile2, (i4 & 8192) != 0 ? listing2.desktop : desktop2, (i4 & 16384) != 0 ? listing2.supportedStandards : list5, (i4 & 32768) != 0 ? listing2.metadata : metadata2, (i4 & 65536) != 0 ? listing2.updatedAt : str6);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final ImageUrl component10() {
        return this.imageUrl;
    }

    @NotNull
    public final App component11() {
        return this.f7323app;
    }

    @Nullable
    public final List<Injected> component12() {
        return this.injected;
    }

    @NotNull
    public final Mobile component13() {
        return this.mobile;
    }

    @NotNull
    public final Desktop component14() {
        return this.desktop;
    }

    @NotNull
    public final List<SupportedStandard> component15() {
        return this.supportedStandards;
    }

    @NotNull
    public final Metadata component16() {
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
    public final Uri component4() {
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
    public final Listing copy(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull Uri uri, @NotNull List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull String str4, @NotNull String str5, @NotNull ImageUrl imageUrl2, @NotNull App app2, @Nullable List<Injected> list4, @NotNull Mobile mobile2, @NotNull Desktop desktop2, @NotNull List<SupportedStandard> list5, @NotNull Metadata metadata2, @NotNull String str6) {
        String str7 = str;
        Intrinsics.checkNotNullParameter(str7, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(uri, "homepage");
        Intrinsics.checkNotNullParameter(list, "chains");
        Intrinsics.checkNotNullParameter(list2, "versions");
        Intrinsics.checkNotNullParameter(list3, "sdks");
        Intrinsics.checkNotNullParameter(str4, "appType");
        Intrinsics.checkNotNullParameter(str5, "imageId");
        Intrinsics.checkNotNullParameter(imageUrl2, "imageUrl");
        Intrinsics.checkNotNullParameter(app2, "app");
        Intrinsics.checkNotNullParameter(mobile2, ConnectionMethod.MOBILE);
        Intrinsics.checkNotNullParameter(desktop2, "desktop");
        Intrinsics.checkNotNullParameter(list5, "supportedStandards");
        Intrinsics.checkNotNullParameter(metadata2, TtmlNode.TAG_METADATA);
        Intrinsics.checkNotNullParameter(str6, "updatedAt");
        return new Listing(str7, str2, str3, uri, list, list2, list3, str4, str5, imageUrl2, app2, list4, mobile2, desktop2, list5, metadata2, str6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Listing)) {
            return false;
        }
        Listing listing = (Listing) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) listing.id) && Intrinsics.areEqual((Object) this.name, (Object) listing.name) && Intrinsics.areEqual((Object) this.description, (Object) listing.description) && Intrinsics.areEqual((Object) this.homepage, (Object) listing.homepage) && Intrinsics.areEqual((Object) this.chains, (Object) listing.chains) && Intrinsics.areEqual((Object) this.versions, (Object) listing.versions) && Intrinsics.areEqual((Object) this.sdks, (Object) listing.sdks) && Intrinsics.areEqual((Object) this.appType, (Object) listing.appType) && Intrinsics.areEqual((Object) this.imageId, (Object) listing.imageId) && Intrinsics.areEqual((Object) this.imageUrl, (Object) listing.imageUrl) && Intrinsics.areEqual((Object) this.f7323app, (Object) listing.f7323app) && Intrinsics.areEqual((Object) this.injected, (Object) listing.injected) && Intrinsics.areEqual((Object) this.mobile, (Object) listing.mobile) && Intrinsics.areEqual((Object) this.desktop, (Object) listing.desktop) && Intrinsics.areEqual((Object) this.supportedStandards, (Object) listing.supportedStandards) && Intrinsics.areEqual((Object) this.metadata, (Object) listing.metadata) && Intrinsics.areEqual((Object) this.updatedAt, (Object) listing.updatedAt);
    }

    @NotNull
    public final App getApp() {
        return this.f7323app;
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
    public final Desktop getDesktop() {
        return this.desktop;
    }

    @NotNull
    public final Uri getHomepage() {
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
    public final ImageUrl getImageUrl() {
        return this.imageUrl;
    }

    @Nullable
    public final List<Injected> getInjected() {
        return this.injected;
    }

    @NotNull
    public final Metadata getMetadata() {
        return this.metadata;
    }

    @NotNull
    public final Mobile getMobile() {
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
    public final List<SupportedStandard> getSupportedStandards() {
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
        int hashCode = str == null ? 0 : str.hashCode();
        int hashCode2 = (this.f7323app.hashCode() + ((this.imageUrl.hashCode() + a.i(this.imageId, a.i(this.appType, a.j(this.sdks, a.j(this.versions, a.j(this.chains, (this.homepage.hashCode() + ((i3 + hashCode) * 31)) * 31, 31), 31), 31), 31), 31)) * 31)) * 31;
        List<Injected> list = this.injected;
        if (list != null) {
            i4 = list.hashCode();
        }
        int hashCode3 = this.mobile.hashCode();
        return this.updatedAt.hashCode() + ((this.metadata.hashCode() + a.j(this.supportedStandards, (this.desktop.hashCode() + ((hashCode3 + ((hashCode2 + i4) * 31)) * 31)) * 31, 31)) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.description;
        Uri uri = this.homepage;
        List<String> list = this.chains;
        List<String> list2 = this.versions;
        List<String> list3 = this.sdks;
        String str4 = this.appType;
        String str5 = this.imageId;
        ImageUrl imageUrl2 = this.imageUrl;
        App app2 = this.f7323app;
        List<Injected> list4 = this.injected;
        Mobile mobile2 = this.mobile;
        Desktop desktop2 = this.desktop;
        List<SupportedStandard> list5 = this.supportedStandards;
        Metadata metadata2 = this.metadata;
        String str6 = this.updatedAt;
        StringBuilder l2 = C0118y.l("Listing(id=", str, ", name=", str2, ", description=");
        l2.append(str3);
        l2.append(", homepage=");
        l2.append(uri);
        l2.append(", chains=");
        l2.append(list);
        l2.append(", versions=");
        l2.append(list2);
        l2.append(", sdks=");
        l2.append(list3);
        l2.append(", appType=");
        l2.append(str4);
        l2.append(", imageId=");
        l2.append(str5);
        l2.append(", imageUrl=");
        l2.append(imageUrl2);
        l2.append(", app=");
        l2.append(app2);
        l2.append(", injected=");
        l2.append(list4);
        l2.append(", mobile=");
        l2.append(mobile2);
        l2.append(", desktop=");
        l2.append(desktop2);
        l2.append(", supportedStandards=");
        l2.append(list5);
        l2.append(", metadata=");
        l2.append(metadata2);
        l2.append(", updatedAt=");
        return A.a.n(l2, str6, ")");
    }
}
