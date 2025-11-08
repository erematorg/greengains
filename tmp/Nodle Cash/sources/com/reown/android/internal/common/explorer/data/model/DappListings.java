package com.reown.android.internal.common.explorer.data.model;

import androidx.compose.animation.core.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/DappListings;", "", "listings", "", "Lcom/reown/android/internal/common/explorer/data/model/Listing;", "count", "", "total", "<init>", "(Ljava/util/List;II)V", "getListings", "()Ljava/util/List;", "getCount", "()I", "getTotal", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DappListings {
    private final int count;
    @NotNull
    private final List<Listing> listings;
    private final int total;

    public DappListings(@NotNull List<Listing> list, int i3, int i4) {
        Intrinsics.checkNotNullParameter(list, "listings");
        this.listings = list;
        this.count = i3;
        this.total = i4;
    }

    public static /* synthetic */ DappListings copy$default(DappListings dappListings, List<Listing> list, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            list = dappListings.listings;
        }
        if ((i5 & 2) != 0) {
            i3 = dappListings.count;
        }
        if ((i5 & 4) != 0) {
            i4 = dappListings.total;
        }
        return dappListings.copy(list, i3, i4);
    }

    @NotNull
    public final List<Listing> component1() {
        return this.listings;
    }

    public final int component2() {
        return this.count;
    }

    public final int component3() {
        return this.total;
    }

    @NotNull
    public final DappListings copy(@NotNull List<Listing> list, int i3, int i4) {
        Intrinsics.checkNotNullParameter(list, "listings");
        return new DappListings(list, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DappListings)) {
            return false;
        }
        DappListings dappListings = (DappListings) obj;
        return Intrinsics.areEqual((Object) this.listings, (Object) dappListings.listings) && this.count == dappListings.count && this.total == dappListings.total;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final List<Listing> getListings() {
        return this.listings;
    }

    public final int getTotal() {
        return this.total;
    }

    public int hashCode() {
        return Integer.hashCode(this.total) + a.c(this.count, this.listings.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        List<Listing> list = this.listings;
        int i3 = this.count;
        int i4 = this.total;
        StringBuilder sb = new StringBuilder("DappListings(listings=");
        sb.append(list);
        sb.append(", count=");
        sb.append(i3);
        sb.append(", total=");
        return A.a.m(sb, ")", i4);
    }
}
