package com.appsamurai.storyly.util.formatter;

import android.content.Context;
import androidx.core.os.ConfigurationCompat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a extends b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6339a;

    public a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6339a = context;
    }

    @Nullable
    public String a(@Nullable Float f2, @Nullable String str) {
        try {
            if (Intrinsics.areEqual(f2, 0.0f)) {
                return null;
            }
            if (f2 == null) {
                return null;
            }
            Context context = this.f6339a;
            Intrinsics.checkNotNullParameter(context, "<this>");
            Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
            Intrinsics.checkNotNullExpressionValue(locale, "getLocales(resources.configuration).get(0)");
            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
            currencyInstance.setCurrency(Currency.getInstance(str));
            return currencyInstance.format(f2);
        } catch (Exception unused) {
            return "";
        }
    }
}
