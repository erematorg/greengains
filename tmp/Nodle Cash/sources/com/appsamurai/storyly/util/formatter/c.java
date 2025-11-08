package com.appsamurai.storyly.util.formatter;

import android.support.v4.media.session.a;
import io.nodle.cash.data.model.transaction.BridgePendingTransaction;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;

public final class c {
    @NotNull
    public static final String a(int i3) {
        int i4 = i3 / BridgePendingTransaction.BRIDGE_TRANSACTION_ETA;
        String stringPlus = Intrinsics.stringPlus(b(i4), "/");
        int i5 = i3 % BridgePendingTransaction.BRIDGE_TRANSACTION_ETA;
        String stringPlus2 = Intrinsics.stringPlus(b(i5 / 3600), ":");
        int i6 = i5 % 3600;
        String stringPlus3 = Intrinsics.stringPlus(b(i6 / 60), ":");
        String b3 = b(i6 % 60);
        StringBuilder sb = new StringBuilder();
        if (i4 <= 0) {
            stringPlus = "";
        }
        return a.r(sb, stringPlus, stringPlus2, stringPlus3, b3);
    }

    public static final String b(int i3) {
        return i3 < 10 ? Intrinsics.stringPlus(SchemaSymbols.ATTVAL_FALSE_0, Integer.valueOf(i3)) : String.valueOf(i3);
    }
}
