package com.ionspin.kotlin.bignum.modular;

import com.ionspin.kotlin.bignum.BigNumber;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import com.ionspin.kotlin.bignum.integer.Sign;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000w\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001d\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001dH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010$J\u001d\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)J\u001d\u0010*\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.J\u0018\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0014H\u0016J\u0018\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0016J\u0018\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020:2\u0006\u00106\u001a\u000207H\u0016J\f\u0010;\u001a\u00020\u000e*\u00020\u000eH\u0002R\u0014\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0005\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"com/ionspin/kotlin/bignum/modular/ModularBigInteger$Companion$creatorForModulo$1", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "ONE", "getONE", "()Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "TEN", "getTEN", "TWO", "getTWO", "ZERO", "getZERO", "fromBigInteger", "bigInteger", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "fromByte", "byte", "", "fromInt", "int", "", "fromLong", "long", "", "fromShort", "short", "", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-7apg3OU", "(B)Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-WZ4Q5Ns", "(I)Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "fromULong", "uLong", "Lkotlin/ULong;", "fromULong-VKZWuLQ", "(J)Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-xj2QHRw", "(S)Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "parseString", "string", "", "base", "tryFromDouble", "double", "", "exactRequired", "", "tryFromFloat", "float", "", "prep", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ModularBigInteger$Companion$creatorForModulo$1 implements BigNumber.Creator<ModularBigInteger> {
    final /* synthetic */ BigInteger $modulo;
    @NotNull
    private final ModularBigInteger ONE;
    @NotNull
    private final ModularBigInteger TEN;
    @NotNull
    private final ModularBigInteger TWO;
    @NotNull
    private final ModularBigInteger ZERO;

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Sign.values().length];
            iArr[Sign.POSITIVE.ordinal()] = 1;
            iArr[Sign.NEGATIVE.ordinal()] = 2;
            iArr[Sign.ZERO.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ModularBigInteger$Companion$creatorForModulo$1(BigInteger bigInteger) {
        this.$modulo = bigInteger;
        BigInteger.Companion companion = BigInteger.Companion;
        this.ZERO = new ModularBigInteger(companion.getZERO(), bigInteger, this, (DefaultConstructorMarker) null);
        this.ONE = new ModularBigInteger(companion.getONE(), bigInteger, this, (DefaultConstructorMarker) null);
        this.TWO = new ModularBigInteger(companion.getTWO(), bigInteger, this, (DefaultConstructorMarker) null);
        this.TEN = new ModularBigInteger(companion.getTEN(), bigInteger, this, (DefaultConstructorMarker) null);
    }

    private final BigInteger prep(BigInteger bigInteger) {
        BigInteger bigInteger2 = (BigInteger) bigInteger.rem((BigNumber) this.$modulo);
        int i3 = WhenMappings.$EnumSwitchMapping$0[bigInteger2.getSign$bignum().ordinal()];
        if (i3 == 1) {
            return bigInteger2;
        }
        if (i3 == 2) {
            return (BigInteger) bigInteger2.plus((BigNumber) this.$modulo);
        }
        if (i3 == 3) {
            return BigInteger.Companion.getZERO();
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public ModularBigInteger fromBigInteger(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "bigInteger");
        return new ModularBigInteger(prep(bigInteger), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    public ModularBigInteger fromByte(byte b3) {
        return new ModularBigInteger(prep(BigInteger.Companion.fromByte(b3)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    public ModularBigInteger fromInt(int i3) {
        return new ModularBigInteger(prep(BigInteger.Companion.fromInt(i3)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    public ModularBigInteger fromLong(long j2) {
        return new ModularBigInteger(prep(BigInteger.Companion.fromLong(j2)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    public ModularBigInteger fromShort(short s3) {
        return new ModularBigInteger(prep(BigInteger.Companion.fromShort(s3)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: fromUByte-7apg3OU  reason: not valid java name */
    public ModularBigInteger m8699fromUByte7apg3OU(byte b3) {
        return new ModularBigInteger(prep(BigInteger.Companion.m8308fromUByte7apg3OU(b3)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: fromUInt-WZ4Q5Ns  reason: not valid java name */
    public ModularBigInteger m8701fromUIntWZ4Q5Ns(int i3) {
        return new ModularBigInteger(prep(BigInteger.Companion.m8312fromUIntWZ4Q5Ns(i3)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: fromULong-VKZWuLQ  reason: not valid java name */
    public ModularBigInteger m8703fromULongVKZWuLQ(long j2) {
        return new ModularBigInteger(prep(BigInteger.Companion.m8314fromULongVKZWuLQ(j2)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: fromUShort-xj2QHRw  reason: not valid java name */
    public ModularBigInteger m8705fromUShortxj2QHRw(short s3) {
        return new ModularBigInteger(prep(BigInteger.Companion.m8316fromUShortxj2QHRw(s3)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    public ModularBigInteger getONE() {
        return this.ONE;
    }

    @NotNull
    public ModularBigInteger getTEN() {
        return this.TEN;
    }

    @NotNull
    public ModularBigInteger getTWO() {
        return this.TWO;
    }

    @NotNull
    public ModularBigInteger getZERO() {
        return this.ZERO;
    }

    @NotNull
    public ModularBigInteger parseString(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, "string");
        return new ModularBigInteger(prep(BigInteger.Companion.parseString(str, i3)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    public ModularBigInteger tryFromDouble(double d2, boolean z2) {
        return new ModularBigInteger(prep(BigInteger.Companion.tryFromDouble(d2, z2)), this.$modulo, this, (DefaultConstructorMarker) null);
    }

    @NotNull
    public ModularBigInteger tryFromFloat(float f2, boolean z2) {
        return new ModularBigInteger(prep(BigInteger.Companion.tryFromFloat(f2, z2)), this.$modulo, this, (DefaultConstructorMarker) null);
    }
}
