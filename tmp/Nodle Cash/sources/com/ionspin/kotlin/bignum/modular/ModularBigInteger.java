package com.ionspin.kotlin.bignum.modular;

import com.ionspin.kotlin.bignum.BigNumber;
import com.ionspin.kotlin.bignum.ByteArraySerializable;
import com.ionspin.kotlin.bignum.CommonBigNumberOperations;
import com.ionspin.kotlin.bignum.ModularQuotientAndRemainder;
import com.ionspin.kotlin.bignum.NarrowingOperations;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import com.ionspin.kotlin.bignum.integer.Sign;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 Z2\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u0004:\u0001ZB%\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0000H\u0016J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0000H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0000H\u0002J\u001d\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0000J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0016J\u001c\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000#2\u0006\u0010\u0011\u001a\u00020\u0000H\u0016J\u0011\u0010$\u001a\u00020%2\u0006\u0010\u0011\u001a\u00020\u0000H\u0004J\u0010\u0010&\u001a\u00020'2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0013\u0010(\u001a\u00020\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010 H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00000\tH\u0016J\b\u0010,\u001a\u00020\u0000H\u0016J\u0010\u0010-\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0006\u0010.\u001a\u00020\u0000J\b\u0010/\u001a\u00020\u0017H\u0016J\u0010\u00100\u001a\u0002012\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u00102\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0016J\b\u00103\u001a\u00020\u0000H\u0016J\b\u00104\u001a\u000201H\u0016J\u000e\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u00020\u0006J\u000e\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u00020\u0000J\u0010\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u00020\u001eH\u0016J\u0010\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u000201H\u0016J\u0011\u00107\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0002J\u0010\u00108\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0016J\b\u00109\u001a\u00020\u0013H\u0016J\u0010\u0010:\u001a\u00020;2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010<\u001a\u00020\u001eH\u0016J\u0010\u0010=\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0016J\u0006\u0010>\u001a\u00020\u0006J\b\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020BH\u0016J\u0010\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u001eH\u0016J\u0010\u0010D\u001a\u00020B2\b\b\u0002\u0010C\u001a\u00020\u001eJ\u0018\u0010E\u001a\u00020FH\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bG\u0010HJ \u0010I\u001a\u00020J2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bK\u0010LJ \u0010M\u001a\u00020N2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bO\u0010PJ \u0010Q\u001a\u00020R2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bS\u0010TJ\t\u0010U\u001a\u00020\u0000H\u0002J \u0010V\u001a\u00020W2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bX\u0010YR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006["}, d2 = {"Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "Lcom/ionspin/kotlin/bignum/BigNumber;", "Lcom/ionspin/kotlin/bignum/CommonBigNumberOperations;", "Lcom/ionspin/kotlin/bignum/NarrowingOperations;", "Lcom/ionspin/kotlin/bignum/ByteArraySerializable;", "signedResidue", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "modulus", "creator", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;Lcom/ionspin/kotlin/bignum/integer/BigInteger;Lcom/ionspin/kotlin/bignum/BigNumber$Creator;)V", "getModulus", "()Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "residue", "getResidue", "abs", "add", "other", "assertSameModulo", "", "byteValue", "", "exactRequired", "", "checkIfDivisible", "checkIfDivisibleBoolean", "first", "second", "checkIfDivisibleBoolean$bignum", "compare", "", "compareTo", "", "divide", "divideAndRemainder", "Lkotlin/Pair;", "divrem", "Lcom/ionspin/kotlin/bignum/ModularQuotientAndRemainder;", "doubleValue", "", "equals", "floatValue", "", "getCreator", "getInstance", "intValue", "inverse", "isZero", "longValue", "", "multiply", "negate", "numberOfDecimalDigits", "pow", "exponent", "rem", "remainder", "secureOverwrite", "shortValue", "", "signum", "subtract", "toBigInteger", "toByteArray", "", "toString", "", "base", "toStringWithModulo", "toUByteArray", "Lkotlin/UByteArray;", "toUByteArray-TcUX1vc", "()[B", "ubyteValue", "Lkotlin/UByte;", "ubyteValue-Wa3L5BU", "(Z)B", "uintValue", "Lkotlin/UInt;", "uintValue-OGnWXxg", "(Z)I", "ulongValue", "Lkotlin/ULong;", "ulongValue-I7RO_PI", "(Z)J", "unaryMinus", "ushortValue", "Lkotlin/UShort;", "ushortValue-BwKQO78", "(Z)S", "Companion", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ModularBigInteger implements BigNumber<ModularBigInteger>, CommonBigNumberOperations<ModularBigInteger>, NarrowingOperations<ModularBigInteger>, ByteArraySerializable {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final BigNumber.Creator<ModularBigInteger> creator;
    @NotNull
    private final BigInteger modulus;
    @NotNull
    private final BigInteger residue;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\bJ\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\tJ\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\nJ\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u000bJ!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u000fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0012ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0015ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger$Companion;", "", "()V", "creatorForModulo", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "modulo", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "", "", "", "", "Lkotlin/UByte;", "creatorForModulo-7apg3OU", "(B)Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "Lkotlin/UInt;", "creatorForModulo-WZ4Q5Ns", "(I)Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "Lkotlin/ULong;", "creatorForModulo-VKZWuLQ", "(J)Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "Lkotlin/UShort;", "creatorForModulo-xj2QHRw", "(S)Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BigNumber.Creator<ModularBigInteger> creatorForModulo(long j2) {
            return creatorForModulo(BigInteger.Companion.fromLong(j2));
        }

        @NotNull
        /* renamed from: creatorForModulo-7apg3OU  reason: not valid java name */
        public final BigNumber.Creator<ModularBigInteger> m8694creatorForModulo7apg3OU(byte b3) {
            return creatorForModulo(BigInteger.Companion.m8308fromUByte7apg3OU(b3));
        }

        @NotNull
        /* renamed from: creatorForModulo-VKZWuLQ  reason: not valid java name */
        public final BigNumber.Creator<ModularBigInteger> m8695creatorForModuloVKZWuLQ(long j2) {
            return creatorForModulo(BigInteger.Companion.m8314fromULongVKZWuLQ(j2));
        }

        @NotNull
        /* renamed from: creatorForModulo-WZ4Q5Ns  reason: not valid java name */
        public final BigNumber.Creator<ModularBigInteger> m8696creatorForModuloWZ4Q5Ns(int i3) {
            return creatorForModulo(BigInteger.Companion.m8312fromUIntWZ4Q5Ns(i3));
        }

        @NotNull
        /* renamed from: creatorForModulo-xj2QHRw  reason: not valid java name */
        public final BigNumber.Creator<ModularBigInteger> m8697creatorForModuloxj2QHRw(short s3) {
            return creatorForModulo(BigInteger.Companion.m8316fromUShortxj2QHRw(s3));
        }

        private Companion() {
        }

        @NotNull
        public final BigNumber.Creator<ModularBigInteger> creatorForModulo(int i3) {
            return creatorForModulo(BigInteger.Companion.fromInt(i3));
        }

        @NotNull
        public final BigNumber.Creator<ModularBigInteger> creatorForModulo(short s3) {
            return creatorForModulo(BigInteger.Companion.fromShort(s3));
        }

        @NotNull
        public final BigNumber.Creator<ModularBigInteger> creatorForModulo(byte b3) {
            return creatorForModulo(BigInteger.Companion.fromByte(b3));
        }

        @NotNull
        public final BigNumber.Creator<ModularBigInteger> creatorForModulo(@NotNull BigInteger bigInteger) {
            Intrinsics.checkNotNullParameter(bigInteger, "modulo");
            return new ModularBigInteger$Companion$creatorForModulo$1(bigInteger);
        }
    }

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

    public /* synthetic */ ModularBigInteger(BigInteger bigInteger, BigInteger bigInteger2, BigNumber.Creator creator2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bigInteger, bigInteger2, creator2);
    }

    private final void assertSameModulo(ModularBigInteger modularBigInteger) {
        if (!Intrinsics.areEqual((Object) this.modulus, (Object) modularBigInteger.modulus)) {
            throw new RuntimeException("Different moduli! This " + this.modulus + "\n Other " + modularBigInteger.modulus);
        }
    }

    private final void checkIfDivisible(ModularBigInteger modularBigInteger) {
        if (!Intrinsics.areEqual((Object) modularBigInteger.residue.gcd(this.modulus), (Object) BigInteger.Companion.getONE())) {
            throw new ArithmeticException("BigInteger is not invertible. Operand and modulus are not relatively prime (coprime)");
        }
    }

    public static /* synthetic */ String toStringWithModulo$default(ModularBigInteger modularBigInteger, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = 10;
        }
        return modularBigInteger.toStringWithModulo(i3);
    }

    @NotNull
    public ModularBigInteger abs() {
        return this;
    }

    public byte byteValue(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl(127))) <= 0) {
            return this.residue.byteValue(z2);
        }
        throw new ArithmeticException("Cannot convert to byte and provide exact value");
    }

    public final boolean checkIfDivisibleBoolean$bignum(@NotNull ModularBigInteger modularBigInteger, @NotNull ModularBigInteger modularBigInteger2) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "first");
        Intrinsics.checkNotNullParameter(modularBigInteger2, "second");
        return Intrinsics.areEqual((Object) modularBigInteger2.residue.gcd(modularBigInteger.modulus), (Object) BigInteger.Companion.getONE());
    }

    public final int compare(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        assertSameModulo(modularBigInteger);
        return this.residue.compareTo(modularBigInteger.residue);
    }

    public int compareTo(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "other");
        if (obj instanceof ModularBigInteger) {
            return compare((ModularBigInteger) obj);
        }
        if (obj instanceof BigInteger) {
            return this.residue.compare((BigInteger) obj);
        }
        if (obj instanceof Long) {
            return compare(this.creator.fromLong(((Number) obj).longValue()));
        }
        if (obj instanceof Integer) {
            return compare(this.creator.fromInt(((Number) obj).intValue()));
        }
        if (obj instanceof Short) {
            return compare(this.creator.fromShort(((Number) obj).shortValue()));
        }
        if (obj instanceof Byte) {
            return compare(this.creator.fromByte(((Number) obj).byteValue()));
        }
        throw new RuntimeException(Intrinsics.stringPlus("Invalid comparison type for BigInteger: ", obj));
    }

    @NotNull
    public final ModularQuotientAndRemainder divrem(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        Pair<ModularBigInteger, ModularBigInteger> divideAndRemainder = divideAndRemainder(modularBigInteger);
        return new ModularQuotientAndRemainder(divideAndRemainder.getFirst(), divideAndRemainder.getSecond());
    }

    public double doubleValue(boolean z2) {
        return NarrowingOperations.DefaultImpls.doubleValue$default(this.residue, false, 1, (Object) null);
    }

    public boolean equals(@Nullable Object obj) {
        return obj != null && compareTo(obj) == 0;
    }

    public float floatValue(boolean z2) {
        return NarrowingOperations.DefaultImpls.floatValue$default(this.residue, false, 1, (Object) null);
    }

    @NotNull
    public BigNumber.Creator<ModularBigInteger> getCreator() {
        return this.creator;
    }

    @NotNull
    public ModularBigInteger getInstance() {
        return this;
    }

    @NotNull
    public final BigInteger getModulus() {
        return this.modulus;
    }

    @NotNull
    public final BigInteger getResidue() {
        return this.residue;
    }

    public int intValue(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl(Integer.MAX_VALUE))) <= 0) {
            return this.residue.intValue(z2);
        }
        throw new ArithmeticException("Cannot convert to int and provide exact value");
    }

    @NotNull
    public final ModularBigInteger inverse() {
        return new ModularBigInteger(this.residue.modInverse(this.modulus), this.modulus, this.creator);
    }

    public boolean isNegative() {
        return BigNumber.DefaultImpls.isNegative(this);
    }

    public boolean isPositive() {
        return BigNumber.DefaultImpls.isPositive(this);
    }

    public boolean isZero() {
        return this.residue.isZero();
    }

    public long longValue(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl((int) Long.MAX_VALUE))) <= 0) {
            return this.residue.longValue(z2);
        }
        throw new ArithmeticException("Cannot convert to long and provide exact value");
    }

    @NotNull
    public ModularBigInteger negate() {
        return this;
    }

    public long numberOfDecimalDigits() {
        return this.residue.numberOfDecimalDigits();
    }

    public void secureOverwrite() {
        this.residue.secureOverwrite();
    }

    public short shortValue(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl(32767))) <= 0) {
            return this.residue.shortValue(z2);
        }
        throw new ArithmeticException("Cannot convert to short and provide exact value");
    }

    public int signum() {
        return this.residue.signum();
    }

    @NotNull
    public final BigInteger toBigInteger() {
        return this.residue;
    }

    @NotNull
    public byte[] toByteArray() {
        return this.residue.toByteArray();
    }

    @NotNull
    public String toString() {
        return this.residue.toString();
    }

    @NotNull
    public final String toStringWithModulo(int i3) {
        return this.residue.toString(i3) + " mod " + this.modulus.toString(i3);
    }

    @NotNull
    /* renamed from: toUByteArray-TcUX1vc  reason: not valid java name */
    public byte[] m8689toUByteArrayTcUX1vc() {
        return this.residue.m8302toUByteArrayTcUX1vc();
    }

    /* renamed from: ubyteValue-Wa3L5BU  reason: not valid java name */
    public byte m8690ubyteValueWa3L5BU(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl(255))) <= 0) {
            return this.residue.m8303ubyteValueWa3L5BU(z2);
        }
        throw new ArithmeticException("Cannot convert to unsigned byte and provide exact value");
    }

    /* renamed from: uintValue-OGnWXxg  reason: not valid java name */
    public int m8691uintValueOGnWXxg(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(-1)) <= 0) {
            return this.residue.m8304uintValueOGnWXxg(z2);
        }
        throw new ArithmeticException("Cannot convert to unsigned int and provide exact value");
    }

    /* renamed from: ulongValue-I7RO_PI  reason: not valid java name */
    public long m8692ulongValueI7RO_PI(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl((int) -1))) <= 0) {
            return this.residue.m8305ulongValueI7RO_PI(z2);
        }
        throw new ArithmeticException("Cannot convert to unsigned long and provide exact value");
    }

    /* renamed from: ushortValue-BwKQO78  reason: not valid java name */
    public short m8693ushortValueBwKQO78(boolean z2) {
        if (!z2 || this.residue.compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl(65535))) <= 0) {
            return this.residue.m8306ushortValueBwKQO78(z2);
        }
        throw new ArithmeticException("Cannot convert to unsigned short and provide exact value");
    }

    private ModularBigInteger(BigInteger bigInteger, BigInteger bigInteger2, BigNumber.Creator<ModularBigInteger> creator2) {
        this.modulus = bigInteger2;
        this.creator = creator2;
        int i3 = WhenMappings.$EnumSwitchMapping$0[bigInteger.getSign$bignum().ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                bigInteger = (BigInteger) bigInteger.plus((BigNumber) bigInteger2);
            } else if (i3 == 3) {
                bigInteger = BigInteger.Companion.getZERO();
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        this.residue = bigInteger;
        if (bigInteger2.getSign$bignum() == Sign.NEGATIVE) {
            throw new ArithmeticException("Modulus must be a positive number");
        }
    }

    @NotNull
    public ModularBigInteger add(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        assertSameModulo(modularBigInteger);
        return new ModularBigInteger((BigInteger) ((BigInteger) this.residue.plus((BigNumber) modularBigInteger.residue)).rem((BigNumber) this.modulus), this.modulus, this.creator);
    }

    @NotNull
    public ModularBigInteger divide(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        assertSameModulo(modularBigInteger);
        return new ModularBigInteger((BigInteger) ((BigInteger) modularBigInteger.residue.modInverse(this.modulus).times((BigNumber) this.residue)).rem((BigNumber) this.modulus), this.modulus, this.creator);
    }

    @NotNull
    public Pair<ModularBigInteger, ModularBigInteger> divideAndRemainder(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        assertSameModulo(modularBigInteger);
        checkIfDivisible(modularBigInteger);
        BigInteger.QuotientAndRemainder divrem = this.residue.divrem(modularBigInteger.residue);
        return new Pair<>(new ModularBigInteger((BigInteger) divrem.getQuotient().rem((BigNumber) this.modulus), this.modulus, this.creator), new ModularBigInteger((BigInteger) divrem.getRemainder().rem((BigNumber) this.modulus), this.modulus, this.creator));
    }

    @NotNull
    public ModularBigInteger multiply(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        assertSameModulo(modularBigInteger);
        return new ModularBigInteger((BigInteger) ((BigInteger) this.residue.times((BigNumber) modularBigInteger.residue)).rem((BigNumber) this.modulus), this.modulus, this.creator);
    }

    @NotNull
    public ModularBigInteger remainder(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        assertSameModulo(modularBigInteger);
        checkIfDivisible(modularBigInteger);
        return new ModularBigInteger((BigInteger) ((BigInteger) this.residue.rem((BigNumber) modularBigInteger.residue)).rem((BigNumber) this.modulus), this.modulus, this.creator);
    }

    @NotNull
    public ModularBigInteger subtract(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        assertSameModulo(modularBigInteger);
        return new ModularBigInteger((BigInteger) ((BigInteger) this.residue.minus((BigNumber) modularBigInteger.residue)).rem((BigNumber) this.modulus), this.modulus, this.creator);
    }

    @NotNull
    public String toString(int i3) {
        return this.residue.toString(i3);
    }

    @NotNull
    public ModularBigInteger unaryMinus() {
        return negate();
    }

    @NotNull
    public final ModularBigInteger pow(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "exponent");
        return pow(modularBigInteger.residue);
    }

    @NotNull
    public final ModularBigInteger pow(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "exponent");
        BigInteger bigInteger2 = this.modulus;
        BigInteger.Companion companion = BigInteger.Companion;
        if (Intrinsics.areEqual((Object) bigInteger2, (Object) companion.getONE())) {
            return this.creator.getZERO();
        }
        BigInteger one = companion.getONE();
        BigInteger bigInteger3 = this.residue;
        while (bigInteger.compareTo(0) > 0) {
            if (Intrinsics.areEqual((Object) bigInteger.rem(2), (Object) BigInteger.Companion.getONE())) {
                one = (BigInteger) ((BigInteger) one.times((BigNumber) bigInteger3)).rem((BigNumber) this.modulus);
            }
            bigInteger = bigInteger.shr(1);
            bigInteger3 = (BigInteger) bigInteger3.pow(2).rem((BigNumber) this.modulus);
        }
        return new ModularBigInteger(one, this.modulus, this.creator);
    }

    @NotNull
    public ModularBigInteger div(byte b3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.div(this, b3);
    }

    @NotNull
    public ModularBigInteger minus(byte b3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, b3);
    }

    @NotNull
    public ModularBigInteger plus(byte b3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, b3);
    }

    @NotNull
    public ModularBigInteger rem(byte b3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, b3);
    }

    @NotNull
    public ModularBigInteger times(byte b3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.times(this, b3);
    }

    @NotNull
    public ModularBigInteger div(int i3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.div(this, i3);
    }

    @NotNull
    public ModularBigInteger minus(int i3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, i3);
    }

    @NotNull
    public ModularBigInteger plus(int i3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, i3);
    }

    @NotNull
    public ModularBigInteger rem(int i3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, i3);
    }

    @NotNull
    public ModularBigInteger times(int i3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.times(this, i3);
    }

    @NotNull
    public ModularBigInteger div(long j2) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.div(this, j2);
    }

    @NotNull
    public ModularBigInteger minus(long j2) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, j2);
    }

    @NotNull
    public ModularBigInteger plus(long j2) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, j2);
    }

    @NotNull
    public ModularBigInteger rem(long j2) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, j2);
    }

    @NotNull
    public ModularBigInteger times(long j2) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.times(this, j2);
    }

    @NotNull
    public ModularBigInteger div(@NotNull ModularBigInteger modularBigInteger) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.div(this, modularBigInteger);
    }

    @NotNull
    public ModularBigInteger minus(@NotNull ModularBigInteger modularBigInteger) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, modularBigInteger);
    }

    @NotNull
    public ModularBigInteger plus(@NotNull ModularBigInteger modularBigInteger) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, modularBigInteger);
    }

    @NotNull
    public ModularBigInteger rem(short s3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, s3);
    }

    @NotNull
    public ModularBigInteger times(@NotNull ModularBigInteger modularBigInteger) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.times(this, modularBigInteger);
    }

    @NotNull
    public ModularBigInteger div(short s3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.div(this, s3);
    }

    @NotNull
    public ModularBigInteger minus(short s3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, s3);
    }

    @NotNull
    public ModularBigInteger plus(short s3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, s3);
    }

    @NotNull
    public ModularBigInteger rem(@NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return remainder(modularBigInteger);
    }

    @NotNull
    public ModularBigInteger times(short s3) {
        return (ModularBigInteger) CommonBigNumberOperations.DefaultImpls.times(this, s3);
    }

    @NotNull
    public ModularBigInteger pow(long j2) {
        return new ModularBigInteger((BigInteger) this.residue.pow(j2).rem((BigNumber) this.modulus), this.modulus, this.creator);
    }

    @NotNull
    public ModularBigInteger pow(int i3) {
        return new ModularBigInteger((BigInteger) this.residue.pow(i3).rem((BigNumber) this.modulus), this.modulus, this.creator);
    }
}
