package com.ionspin.kotlin.bignum.integer;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0000H\u0002J\u0006\u0010\u0004\u001a\u00020\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/Sign;", "", "(Ljava/lang/String;I)V", "not", "toInt", "", "POSITIVE", "NEGATIVE", "ZERO", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum Sign {
    POSITIVE,
    NEGATIVE,
    ZERO;

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] iArr = new int[Sign.values().length];
            iArr[Sign.POSITIVE.ordinal()] = 1;
            iArr[Sign.NEGATIVE.ordinal()] = 2;
            iArr[Sign.ZERO.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public final Sign not() {
        int i3 = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i3 == 1) {
            return NEGATIVE;
        }
        if (i3 == 2) {
            return POSITIVE;
        }
        if (i3 == 3) {
            return ZERO;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final int toInt() {
        int i3 = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i3 == 1) {
            return 1;
        }
        if (i3 == 2) {
            return -1;
        }
        if (i3 == 3) {
            return 0;
        }
        throw new NoWhenBranchMatchedException();
    }
}
