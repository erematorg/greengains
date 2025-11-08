package com.ionspin.kotlin.bignum.integer;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0004\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/ComparisonWorkaround;", "", "()V", "isSpecialHandlingForFloatNeeded", "", "number", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ComparisonWorkaround {
    @NotNull
    public static final ComparisonWorkaround INSTANCE = new ComparisonWorkaround();

    private ComparisonWorkaround() {
    }

    public final boolean isSpecialHandlingForFloatNeeded(@NotNull Number number) {
        Intrinsics.checkNotNullParameter(number, "number");
        return false;
    }
}
