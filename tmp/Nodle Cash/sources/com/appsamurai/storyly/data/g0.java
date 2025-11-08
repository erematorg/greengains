package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.util.formatter.b;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class g0 extends a0 {
    @NotNull
    public String a() {
        return "Go to Checkout";
    }

    @Nullable
    public final STRProductItem b() {
        Map<j, List<STRProductItem>> map;
        Collection<List<STRProductItem>> values;
        List list;
        m c3 = c();
        if (c3 == null || (map = c3.f3859a) == null || (values = map.values()) == null || (list = (List) CollectionsKt.firstOrNull(values)) == null) {
            return null;
        }
        return (STRProductItem) CollectionsKt.firstOrNull(list);
    }

    @Nullable
    public m c() {
        return null;
    }

    @NotNull
    public String d() {
        return "Add to Cart";
    }

    @NotNull
    public String e() {
        return "Continue with Stories";
    }

    @NotNull
    public String f() {
        return "Go to Cart";
    }

    @NotNull
    public String g() {
        return "Added to your Cart successfully";
    }

    @NotNull
    public String h() {
        return "Total";
    }

    public boolean i() {
        return true;
    }

    public boolean j() {
        return true;
    }

    @NotNull
    public final String a(@Nullable STRProductItem sTRProductItem, @Nullable String str) {
        if (sTRProductItem == null) {
            return str == null ? "" : str;
        }
        if (str == null || str.length() == 0) {
            return "";
        }
        return sTRProductItem.getTitle();
    }

    @NotNull
    public final String a(@Nullable STRProductItem sTRProductItem, @Nullable b bVar, @Nullable String str) {
        String a2;
        if (sTRProductItem == null) {
            return str == null ? "" : str;
        }
        if (str == null || str.length() == 0 || !sTRProductItem.hasSpecialPrice$storyly_release() || bVar == null || (a2 = bVar.a(Float.valueOf(sTRProductItem.getPrice()), sTRProductItem.getCurrency())) == null) {
            return "";
        }
        return a2;
    }

    @NotNull
    public final String b(@Nullable STRProductItem sTRProductItem, @Nullable b bVar, @Nullable String str) {
        if (sTRProductItem == null) {
            return str == null ? "" : str;
        }
        if (str == null || str.length() == 0 || bVar == null) {
            return "";
        }
        Float salesPrice = sTRProductItem.getSalesPrice();
        String a2 = bVar.a(Float.valueOf(salesPrice == null ? sTRProductItem.getPrice() : salesPrice.floatValue()), sTRProductItem.getCurrency());
        if (a2 == null) {
            return "";
        }
        return a2;
    }
}
