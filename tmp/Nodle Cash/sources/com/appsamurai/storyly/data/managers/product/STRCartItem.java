package com.appsamurai.storyly.data.managers.product;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u001f\u0010 J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\t\u0010\bJ<\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u000b\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\u001c\u001a\u0004\b\u001d\u0010\bR\u001b\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u001c\u001a\u0004\b\u001e\u0010\b¨\u0006!"}, d2 = {"Lcom/appsamurai/storyly/data/managers/product/STRCartItem;", "", "Lcom/appsamurai/storyly/data/managers/product/STRProductItem;", "component1", "", "component2", "", "component3", "()Ljava/lang/Float;", "component4", "item", "quantity", "totalPrice", "oldTotalPrice", "copy", "(Lcom/appsamurai/storyly/data/managers/product/STRProductItem;ILjava/lang/Float;Ljava/lang/Float;)Lcom/appsamurai/storyly/data/managers/product/STRCartItem;", "", "toString", "hashCode", "other", "", "equals", "Lcom/appsamurai/storyly/data/managers/product/STRProductItem;", "getItem", "()Lcom/appsamurai/storyly/data/managers/product/STRProductItem;", "I", "getQuantity", "()I", "Ljava/lang/Float;", "getTotalPrice", "getOldTotalPrice", "<init>", "(Lcom/appsamurai/storyly/data/managers/product/STRProductItem;ILjava/lang/Float;Ljava/lang/Float;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class STRCartItem {
    @NotNull
    private final STRProductItem item;
    @Nullable
    private final Float oldTotalPrice;
    private final int quantity;
    @Nullable
    private final Float totalPrice;

    public STRCartItem(@NotNull STRProductItem sTRProductItem, int i3, @Nullable Float f2, @Nullable Float f3) {
        Intrinsics.checkNotNullParameter(sTRProductItem, "item");
        this.item = sTRProductItem;
        this.quantity = i3;
        this.totalPrice = f2;
        this.oldTotalPrice = f3;
    }

    public static /* synthetic */ STRCartItem copy$default(STRCartItem sTRCartItem, STRProductItem sTRProductItem, int i3, Float f2, Float f3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            sTRProductItem = sTRCartItem.item;
        }
        if ((i4 & 2) != 0) {
            i3 = sTRCartItem.quantity;
        }
        if ((i4 & 4) != 0) {
            f2 = sTRCartItem.totalPrice;
        }
        if ((i4 & 8) != 0) {
            f3 = sTRCartItem.oldTotalPrice;
        }
        return sTRCartItem.copy(sTRProductItem, i3, f2, f3);
    }

    @NotNull
    public final STRProductItem component1() {
        return this.item;
    }

    public final int component2() {
        return this.quantity;
    }

    @Nullable
    public final Float component3() {
        return this.totalPrice;
    }

    @Nullable
    public final Float component4() {
        return this.oldTotalPrice;
    }

    @NotNull
    public final STRCartItem copy(@NotNull STRProductItem sTRProductItem, int i3, @Nullable Float f2, @Nullable Float f3) {
        Intrinsics.checkNotNullParameter(sTRProductItem, "item");
        return new STRCartItem(sTRProductItem, i3, f2, f3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof STRCartItem)) {
            return false;
        }
        STRCartItem sTRCartItem = (STRCartItem) obj;
        return Intrinsics.areEqual((Object) this.item, (Object) sTRCartItem.item) && this.quantity == sTRCartItem.quantity && Intrinsics.areEqual((Object) this.totalPrice, (Object) sTRCartItem.totalPrice) && Intrinsics.areEqual((Object) this.oldTotalPrice, (Object) sTRCartItem.oldTotalPrice);
    }

    @NotNull
    public final STRProductItem getItem() {
        return this.item;
    }

    @Nullable
    public final Float getOldTotalPrice() {
        return this.oldTotalPrice;
    }

    public final int getQuantity() {
        return this.quantity;
    }

    @Nullable
    public final Float getTotalPrice() {
        return this.totalPrice;
    }

    public int hashCode() {
        int c3 = a.c(this.quantity, this.item.hashCode() * 31, 31);
        Float f2 = this.totalPrice;
        int i3 = 0;
        int hashCode = (c3 + (f2 == null ? 0 : f2.hashCode())) * 31;
        Float f3 = this.oldTotalPrice;
        if (f3 != null) {
            i3 = f3.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        return "STRCartItem(item=" + this.item + ", quantity=" + this.quantity + ", totalPrice=" + this.totalPrice + ", oldTotalPrice=" + this.oldTotalPrice + ')';
    }
}
