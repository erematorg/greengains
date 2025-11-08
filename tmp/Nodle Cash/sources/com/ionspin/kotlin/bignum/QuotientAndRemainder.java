package com.ionspin.kotlin.bignum;

import com.ionspin.kotlin.bignum.integer.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/ionspin/kotlin/bignum/QuotientAndRemainder;", "", "quotient", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "remainder", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;Lcom/ionspin/kotlin/bignum/integer/BigInteger;)V", "getQuotient", "()Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "getRemainder", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class QuotientAndRemainder {
    @NotNull
    private final BigInteger quotient;
    @NotNull
    private final BigInteger remainder;

    public QuotientAndRemainder(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
        Intrinsics.checkNotNullParameter(bigInteger, "quotient");
        Intrinsics.checkNotNullParameter(bigInteger2, "remainder");
        this.quotient = bigInteger;
        this.remainder = bigInteger2;
    }

    public static /* synthetic */ QuotientAndRemainder copy$default(QuotientAndRemainder quotientAndRemainder, BigInteger bigInteger, BigInteger bigInteger2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            bigInteger = quotientAndRemainder.quotient;
        }
        if ((i3 & 2) != 0) {
            bigInteger2 = quotientAndRemainder.remainder;
        }
        return quotientAndRemainder.copy(bigInteger, bigInteger2);
    }

    @NotNull
    public final BigInteger component1() {
        return this.quotient;
    }

    @NotNull
    public final BigInteger component2() {
        return this.remainder;
    }

    @NotNull
    public final QuotientAndRemainder copy(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
        Intrinsics.checkNotNullParameter(bigInteger, "quotient");
        Intrinsics.checkNotNullParameter(bigInteger2, "remainder");
        return new QuotientAndRemainder(bigInteger, bigInteger2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuotientAndRemainder)) {
            return false;
        }
        QuotientAndRemainder quotientAndRemainder = (QuotientAndRemainder) obj;
        return Intrinsics.areEqual((Object) this.quotient, (Object) quotientAndRemainder.quotient) && Intrinsics.areEqual((Object) this.remainder, (Object) quotientAndRemainder.remainder);
    }

    @NotNull
    public final BigInteger getQuotient() {
        return this.quotient;
    }

    @NotNull
    public final BigInteger getRemainder() {
        return this.remainder;
    }

    public int hashCode() {
        return this.remainder.hashCode() + (this.quotient.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "QuotientAndRemainder(quotient=" + this.quotient + ", remainder=" + this.remainder + ')';
    }
}
