package com.appsamurai.storyly.data.managers.product;

import android.support.v4.media.session.a;
import androidx.annotation.Keep;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\b\b\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\"\u0010#J\u000f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0005HÆ\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\n\u001a\u00020\tHÆ\u0003J@\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\tHÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0011\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\f\u001a\u00020\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b\u001e\u0010\bR\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006$"}, d2 = {"Lcom/appsamurai/storyly/data/managers/product/STRCart;", "", "", "Lcom/appsamurai/storyly/data/managers/product/STRCartItem;", "component1", "", "component2", "component3", "()Ljava/lang/Float;", "", "component4", "items", "totalPrice", "oldTotalPrice", "currency", "copy", "(Ljava/util/List;FLjava/lang/Float;Ljava/lang/String;)Lcom/appsamurai/storyly/data/managers/product/STRCart;", "toString", "", "hashCode", "other", "", "equals", "Ljava/util/List;", "getItems", "()Ljava/util/List;", "F", "getTotalPrice", "()F", "Ljava/lang/Float;", "getOldTotalPrice", "Ljava/lang/String;", "getCurrency", "()Ljava/lang/String;", "<init>", "(Ljava/util/List;FLjava/lang/Float;Ljava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class STRCart {
    @NotNull
    private final String currency;
    @NotNull
    private final List<STRCartItem> items;
    @Nullable
    private final Float oldTotalPrice;
    private final float totalPrice;

    public STRCart(@NotNull List<STRCartItem> list, float f2, @Nullable Float f3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.CURRENCY);
        this.items = list;
        this.totalPrice = f2;
        this.oldTotalPrice = f3;
        this.currency = str;
    }

    public static /* synthetic */ STRCart copy$default(STRCart sTRCart, List<STRCartItem> list, float f2, Float f3, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = sTRCart.items;
        }
        if ((i3 & 2) != 0) {
            f2 = sTRCart.totalPrice;
        }
        if ((i3 & 4) != 0) {
            f3 = sTRCart.oldTotalPrice;
        }
        if ((i3 & 8) != 0) {
            str = sTRCart.currency;
        }
        return sTRCart.copy(list, f2, f3, str);
    }

    @NotNull
    public final List<STRCartItem> component1() {
        return this.items;
    }

    public final float component2() {
        return this.totalPrice;
    }

    @Nullable
    public final Float component3() {
        return this.oldTotalPrice;
    }

    @NotNull
    public final String component4() {
        return this.currency;
    }

    @NotNull
    public final STRCart copy(@NotNull List<STRCartItem> list, float f2, @Nullable Float f3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.CURRENCY);
        return new STRCart(list, f2, f3, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof STRCart)) {
            return false;
        }
        STRCart sTRCart = (STRCart) obj;
        return Intrinsics.areEqual((Object) this.items, (Object) sTRCart.items) && Intrinsics.areEqual((Object) Float.valueOf(this.totalPrice), (Object) Float.valueOf(sTRCart.totalPrice)) && Intrinsics.areEqual((Object) this.oldTotalPrice, (Object) sTRCart.oldTotalPrice) && Intrinsics.areEqual((Object) this.currency, (Object) sTRCart.currency);
    }

    @NotNull
    public final String getCurrency() {
        return this.currency;
    }

    @NotNull
    public final List<STRCartItem> getItems() {
        return this.items;
    }

    @Nullable
    public final Float getOldTotalPrice() {
        return this.oldTotalPrice;
    }

    public final float getTotalPrice() {
        return this.totalPrice;
    }

    public int hashCode() {
        int c3 = a.c(this.totalPrice, this.items.hashCode() * 31, 31);
        Float f2 = this.oldTotalPrice;
        return this.currency.hashCode() + ((c3 + (f2 == null ? 0 : f2.hashCode())) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("STRCart(items=");
        sb.append(this.items);
        sb.append(", totalPrice=");
        sb.append(this.totalPrice);
        sb.append(", oldTotalPrice=");
        sb.append(this.oldTotalPrice);
        sb.append(", currency=");
        return androidx.compose.animation.core.a.o(')', this.currency, sb);
    }
}
