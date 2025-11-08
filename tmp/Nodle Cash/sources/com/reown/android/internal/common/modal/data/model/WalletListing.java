package com.reown.android.internal.common.modal.data.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/modal/data/model/WalletListing;", "", "page", "", "totalCount", "wallets", "", "Lcom/reown/android/internal/common/modal/data/model/Wallet;", "<init>", "(IILjava/util/List;)V", "getPage", "()I", "getTotalCount", "getWallets", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletListing {
    private final int page;
    private final int totalCount;
    @NotNull
    private final List<Wallet> wallets;

    public WalletListing(int i3, int i4, @NotNull List<Wallet> list) {
        Intrinsics.checkNotNullParameter(list, "wallets");
        this.page = i3;
        this.totalCount = i4;
        this.wallets = list;
    }

    public static /* synthetic */ WalletListing copy$default(WalletListing walletListing, int i3, int i4, List<Wallet> list, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i3 = walletListing.page;
        }
        if ((i5 & 2) != 0) {
            i4 = walletListing.totalCount;
        }
        if ((i5 & 4) != 0) {
            list = walletListing.wallets;
        }
        return walletListing.copy(i3, i4, list);
    }

    public final int component1() {
        return this.page;
    }

    public final int component2() {
        return this.totalCount;
    }

    @NotNull
    public final List<Wallet> component3() {
        return this.wallets;
    }

    @NotNull
    public final WalletListing copy(int i3, int i4, @NotNull List<Wallet> list) {
        Intrinsics.checkNotNullParameter(list, "wallets");
        return new WalletListing(i3, i4, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WalletListing)) {
            return false;
        }
        WalletListing walletListing = (WalletListing) obj;
        return this.page == walletListing.page && this.totalCount == walletListing.totalCount && Intrinsics.areEqual((Object) this.wallets, (Object) walletListing.wallets);
    }

    public final int getPage() {
        return this.page;
    }

    public final int getTotalCount() {
        return this.totalCount;
    }

    @NotNull
    public final List<Wallet> getWallets() {
        return this.wallets;
    }

    public int hashCode() {
        return this.wallets.hashCode() + a.c(this.totalCount, Integer.hashCode(this.page) * 31, 31);
    }

    @NotNull
    public String toString() {
        return C0118y.h(")", this.wallets, C0118y.k(this.page, this.totalCount, "WalletListing(page=", ", totalCount=", ", wallets="));
    }
}
