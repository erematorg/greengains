package com.reown.android.internal.common.modal.data.model;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/modal/data/model/WalletAppData;", "", "id", "", "appPackage", "isInstalled", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getAppPackage", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletAppData {
    @Nullable
    private final String appPackage;
    @NotNull
    private final String id;
    private final boolean isInstalled;

    public WalletAppData(@NotNull String str, @Nullable String str2, boolean z2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.id = str;
        this.appPackage = str2;
        this.isInstalled = z2;
    }

    public static /* synthetic */ WalletAppData copy$default(WalletAppData walletAppData, String str, String str2, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = walletAppData.id;
        }
        if ((i3 & 2) != 0) {
            str2 = walletAppData.appPackage;
        }
        if ((i3 & 4) != 0) {
            z2 = walletAppData.isInstalled;
        }
        return walletAppData.copy(str, str2, z2);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final String component2() {
        return this.appPackage;
    }

    public final boolean component3() {
        return this.isInstalled;
    }

    @NotNull
    public final WalletAppData copy(@NotNull String str, @Nullable String str2, boolean z2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        return new WalletAppData(str, str2, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WalletAppData)) {
            return false;
        }
        WalletAppData walletAppData = (WalletAppData) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) walletAppData.id) && Intrinsics.areEqual((Object) this.appPackage, (Object) walletAppData.appPackage) && this.isInstalled == walletAppData.isInstalled;
    }

    @Nullable
    public final String getAppPackage() {
        return this.appPackage;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.appPackage;
        return Boolean.hashCode(this.isInstalled) + ((hashCode + (str == null ? 0 : str.hashCode())) * 31);
    }

    public final boolean isInstalled() {
        return this.isInstalled;
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.appPackage;
        return a.s(C0118y.l("WalletAppData(id=", str, ", appPackage=", str2, ", isInstalled="), this.isInstalled, ")");
    }
}
