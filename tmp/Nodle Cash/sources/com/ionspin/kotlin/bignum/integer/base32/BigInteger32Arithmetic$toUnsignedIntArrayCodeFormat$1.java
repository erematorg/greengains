package com.ionspin.kotlin.bignum.integer.base32;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lkotlin/UInt;"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class BigInteger32Arithmetic$toUnsignedIntArrayCodeFormat$1 extends Lambda implements Function1<UInt, CharSequence> {
    public static final BigInteger32Arithmetic$toUnsignedIntArrayCodeFormat$1 INSTANCE = new BigInteger32Arithmetic$toUnsignedIntArrayCodeFormat$1();

    public BigInteger32Arithmetic$toUnsignedIntArrayCodeFormat$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m8526invokeWZ4Q5Ns(((UInt) obj).m9126unboximpl());
    }

    @NotNull
    /* renamed from: invoke-WZ4Q5Ns  reason: not valid java name */
    public final CharSequence m8526invokeWZ4Q5Ns(int i3) {
        return Intrinsics.stringPlus(Integer.toUnsignedString(i3), "U");
    }
}
