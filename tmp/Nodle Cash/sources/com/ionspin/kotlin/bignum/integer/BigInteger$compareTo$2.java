package com.ionspin.kotlin.bignum.integer;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class BigInteger$compareTo$2 extends Lambda implements Function1<BigInteger, Integer> {
    final /* synthetic */ BigInteger this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BigInteger$compareTo$2(BigInteger bigInteger) {
        super(1);
        this.this$0 = bigInteger;
    }

    public final int invoke(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "it");
        return this.this$0.compare(bigInteger);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Integer.valueOf(invoke((BigInteger) obj));
    }
}
