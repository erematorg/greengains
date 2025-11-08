package com.ionspin.kotlin.bignum.integer;

import com.ionspin.kotlin.bignum.integer.base63.array.BigInteger63Arithmetic;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005*\n\u0010\u0006\"\u00020\u00072\u00020\u0007*\n\u0010\b\"\u00020\t2\u00020\t¨\u0006\n"}, d2 = {"chosenArithmetic", "Lcom/ionspin/kotlin/bignum/integer/BigIntegerArithmetic;", "getChosenArithmetic$annotations", "()V", "getChosenArithmetic", "()Lcom/ionspin/kotlin/bignum/integer/BigIntegerArithmetic;", "Word", "Lkotlin/ULong;", "WordArray", "Lkotlin/ULongArray;", "bignum"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class ConfigurationKt {
    @NotNull
    private static final BigIntegerArithmetic chosenArithmetic = BigInteger63Arithmetic.INSTANCE;

    @NotNull
    public static final BigIntegerArithmetic getChosenArithmetic() {
        return chosenArithmetic;
    }

    public static /* synthetic */ void getChosenArithmetic$annotations() {
    }
}
