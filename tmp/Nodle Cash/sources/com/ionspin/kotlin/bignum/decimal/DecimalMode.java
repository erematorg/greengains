package com.ionspin.kotlin.bignum.decimal;

import android.support.v4.media.session.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0010\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;", "", "decimalPrecision", "", "roundingMode", "Lcom/ionspin/kotlin/bignum/decimal/RoundingMode;", "scale", "(JLcom/ionspin/kotlin/bignum/decimal/RoundingMode;J)V", "getDecimalPrecision", "()J", "isPrecisionUnlimited", "", "()Z", "getRoundingMode", "()Lcom/ionspin/kotlin/bignum/decimal/RoundingMode;", "getScale", "usingScale", "getUsingScale", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DecimalMode {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final DecimalMode DEFAULT = new DecimalMode(0, (RoundingMode) null, 0, 7, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final DecimalMode US_CURRENCY = new DecimalMode(30, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO, 2);
    private final long decimalPrecision;
    private final boolean isPrecisionUnlimited;
    @NotNull
    private final RoundingMode roundingMode;
    private final long scale;
    private final boolean usingScale;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/ionspin/kotlin/bignum/decimal/DecimalMode$Companion;", "", "()V", "DEFAULT", "Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;", "getDEFAULT", "()Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;", "US_CURRENCY", "getUS_CURRENCY", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final DecimalMode getDEFAULT() {
            return DecimalMode.DEFAULT;
        }

        @NotNull
        public final DecimalMode getUS_CURRENCY() {
            return DecimalMode.US_CURRENCY;
        }

        private Companion() {
        }
    }

    public DecimalMode() {
        this(0, (RoundingMode) null, 0, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DecimalMode copy$default(DecimalMode decimalMode, long j2, RoundingMode roundingMode2, long j3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = decimalMode.decimalPrecision;
        }
        long j4 = j2;
        if ((i3 & 2) != 0) {
            roundingMode2 = decimalMode.roundingMode;
        }
        RoundingMode roundingMode3 = roundingMode2;
        if ((i3 & 4) != 0) {
            j3 = decimalMode.scale;
        }
        return decimalMode.copy(j4, roundingMode3, j3);
    }

    public final long component1() {
        return this.decimalPrecision;
    }

    @NotNull
    public final RoundingMode component2() {
        return this.roundingMode;
    }

    public final long component3() {
        return this.scale;
    }

    @NotNull
    public final DecimalMode copy(long j2, @NotNull RoundingMode roundingMode2, long j3) {
        Intrinsics.checkNotNullParameter(roundingMode2, "roundingMode");
        return new DecimalMode(j2, roundingMode2, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecimalMode)) {
            return false;
        }
        DecimalMode decimalMode = (DecimalMode) obj;
        return this.decimalPrecision == decimalMode.decimalPrecision && this.roundingMode == decimalMode.roundingMode && this.scale == decimalMode.scale;
    }

    public final long getDecimalPrecision() {
        return this.decimalPrecision;
    }

    @NotNull
    public final RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    public final long getScale() {
        return this.scale;
    }

    public final boolean getUsingScale() {
        return this.usingScale;
    }

    public int hashCode() {
        int hashCode = this.roundingMode.hashCode();
        return Long.hashCode(this.scale) + ((hashCode + (Long.hashCode(this.decimalPrecision) * 31)) * 31);
    }

    public final boolean isPrecisionUnlimited() {
        return this.isPrecisionUnlimited;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("DecimalMode(decimalPrecision=");
        sb.append(this.decimalPrecision);
        sb.append(", roundingMode=");
        sb.append(this.roundingMode);
        sb.append(", scale=");
        return a.q(sb, this.scale, ')');
    }

    public DecimalMode(long j2, @NotNull RoundingMode roundingMode2, long j3) {
        Intrinsics.checkNotNullParameter(roundingMode2, "roundingMode");
        this.decimalPrecision = j2;
        this.roundingMode = roundingMode2;
        this.scale = j3;
        boolean z2 = false;
        this.isPrecisionUnlimited = j2 == 0;
        z2 = j3 >= 0 ? true : z2;
        this.usingScale = z2;
        if (!z2 && j2 == 0 && roundingMode2 != RoundingMode.NONE) {
            throw new ArithmeticException("Rounding mode with 0 digits precision.");
        } else if (j3 < -1) {
            throw new ArithmeticException("Negative Scale is unsupported.");
        } else if (z2 && roundingMode2 == RoundingMode.NONE) {
            throw new ArithmeticException(androidx.compose.ui.autofill.a.j("Scale of ", j3, " digits to the right of the decimal requires a RoundingMode that is not NONE."));
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DecimalMode(long j2, RoundingMode roundingMode2, long j3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : j2, (i3 & 2) != 0 ? RoundingMode.NONE : roundingMode2, (i3 & 4) != 0 ? -1 : j3);
    }
}
