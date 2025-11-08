package com.reown.android.internal.common.modal.data.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b&\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010/\u001a\u00020\rHÆ\u0003Ju\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u00101\u001a\u00020\r2\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011R\u001a\u0010\u001d\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001fR\u0011\u0010\"\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b#\u0010\u001aR\u0011\u0010$\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b%\u0010\u001a¨\u00066"}, d2 = {"Lcom/reown/android/internal/common/modal/data/model/Wallet;", "", "id", "", "name", "homePage", "imageUrl", "order", "mobileLink", "playStore", "webAppLink", "linkMode", "isRecommended", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getName", "getHomePage", "getImageUrl", "getOrder", "getMobileLink", "getPlayStore", "getWebAppLink", "getLinkMode", "()Z", "appPackage", "getAppPackage", "isRecent", "setRecent", "(Z)V", "isWalletInstalled", "setWalletInstalled", "hasMobileWallet", "getHasMobileWallet", "hasWebApp", "getHasWebApp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Wallet {
    @Nullable
    private final String appPackage;
    @NotNull
    private final String homePage;
    @NotNull
    private final String id;
    @NotNull
    private final String imageUrl;
    private boolean isRecent;
    private final boolean isRecommended;
    private boolean isWalletInstalled;
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
    private final String webAppLink;

    public Wallet(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, boolean z2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "homePage");
        Intrinsics.checkNotNullParameter(str4, "imageUrl");
        Intrinsics.checkNotNullParameter(str5, "order");
        this.id = str;
        this.name = str2;
        this.homePage = str3;
        this.imageUrl = str4;
        this.order = str5;
        this.mobileLink = str6;
        this.playStore = str7;
        this.webAppLink = str8;
        this.linkMode = str9;
        this.isRecommended = z2;
        this.appPackage = str7 != null ? WalletKt.extractPackage(str7) : null;
    }

    public static /* synthetic */ Wallet copy$default(Wallet wallet, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z2, int i3, Object obj) {
        Wallet wallet2 = wallet;
        int i4 = i3;
        return wallet.copy((i4 & 1) != 0 ? wallet2.id : str, (i4 & 2) != 0 ? wallet2.name : str2, (i4 & 4) != 0 ? wallet2.homePage : str3, (i4 & 8) != 0 ? wallet2.imageUrl : str4, (i4 & 16) != 0 ? wallet2.order : str5, (i4 & 32) != 0 ? wallet2.mobileLink : str6, (i4 & 64) != 0 ? wallet2.playStore : str7, (i4 & 128) != 0 ? wallet2.webAppLink : str8, (i4 & 256) != 0 ? wallet2.linkMode : str9, (i4 & 512) != 0 ? wallet2.isRecommended : z2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    public final boolean component10() {
        return this.isRecommended;
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
        return this.imageUrl;
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
        return this.playStore;
    }

    @Nullable
    public final String component8() {
        return this.webAppLink;
    }

    @Nullable
    public final String component9() {
        return this.linkMode;
    }

    @NotNull
    public final Wallet copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, boolean z2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "homePage");
        String str10 = str4;
        Intrinsics.checkNotNullParameter(str10, "imageUrl");
        String str11 = str5;
        Intrinsics.checkNotNullParameter(str11, "order");
        return new Wallet(str, str2, str3, str10, str11, str6, str7, str8, str9, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Wallet)) {
            return false;
        }
        Wallet wallet = (Wallet) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) wallet.id) && Intrinsics.areEqual((Object) this.name, (Object) wallet.name) && Intrinsics.areEqual((Object) this.homePage, (Object) wallet.homePage) && Intrinsics.areEqual((Object) this.imageUrl, (Object) wallet.imageUrl) && Intrinsics.areEqual((Object) this.order, (Object) wallet.order) && Intrinsics.areEqual((Object) this.mobileLink, (Object) wallet.mobileLink) && Intrinsics.areEqual((Object) this.playStore, (Object) wallet.playStore) && Intrinsics.areEqual((Object) this.webAppLink, (Object) wallet.webAppLink) && Intrinsics.areEqual((Object) this.linkMode, (Object) wallet.linkMode) && this.isRecommended == wallet.isRecommended;
    }

    @Nullable
    public final String getAppPackage() {
        return this.appPackage;
    }

    public final boolean getHasMobileWallet() {
        return this.mobileLink != null;
    }

    public final boolean getHasWebApp() {
        return this.webAppLink != null;
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
    public final String getImageUrl() {
        return this.imageUrl;
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
    public final String getWebAppLink() {
        return this.webAppLink;
    }

    public int hashCode() {
        int i3 = a.i(this.order, a.i(this.imageUrl, a.i(this.homePage, a.i(this.name, this.id.hashCode() * 31, 31), 31), 31), 31);
        String str = this.mobileLink;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.playStore;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.webAppLink;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.linkMode;
        if (str4 != null) {
            i4 = str4.hashCode();
        }
        return Boolean.hashCode(this.isRecommended) + ((hashCode3 + i4) * 31);
    }

    public final boolean isRecent() {
        return this.isRecent;
    }

    public final boolean isRecommended() {
        return this.isRecommended;
    }

    public final boolean isWalletInstalled() {
        return this.isWalletInstalled;
    }

    public final void setRecent(boolean z2) {
        this.isRecent = z2;
    }

    public final void setWalletInstalled(boolean z2) {
        this.isWalletInstalled = z2;
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.homePage;
        String str4 = this.imageUrl;
        String str5 = this.order;
        String str6 = this.mobileLink;
        String str7 = this.playStore;
        String str8 = this.webAppLink;
        String str9 = this.linkMode;
        boolean z2 = this.isRecommended;
        StringBuilder l2 = C0118y.l("Wallet(id=", str, ", name=", str2, ", homePage=");
        b.w(l2, str3, ", imageUrl=", str4, ", order=");
        b.w(l2, str5, ", mobileLink=", str6, ", playStore=");
        b.w(l2, str7, ", webAppLink=", str8, ", linkMode=");
        l2.append(str9);
        l2.append(", isRecommended=");
        l2.append(z2);
        l2.append(")");
        return l2.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Wallet(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, (i3 & 512) != 0 ? false : z2);
    }
}
