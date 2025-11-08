package com.ionspin.kotlin.bignum.integer.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class VariousUtilKt$hexColumsPrint$1$1 extends Lambda implements Function1<String, CharSequence> {
    public static final VariousUtilKt$hexColumsPrint$1$1 INSTANCE = new VariousUtilKt$hexColumsPrint$1$1();

    public VariousUtilKt$hexColumsPrint$1$1() {
        super(1);
    }

    @NotNull
    public final CharSequence invoke(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        String upperCase = str.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
        return upperCase;
    }
}
