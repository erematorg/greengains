package com.ionspin.kotlin.bignum;

import com.ionspin.kotlin.bignum.BigNumber;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002:\u0002'(J\r\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0002H&J\u0015\u0010\u000e\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000bJ!\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0002H¦\u0002J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H&J\b\u0010\u0015\u001a\u00020\u0004H&J\u0015\u0010\u0016\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000bJ\r\u0010\u0017\u001a\u00028\u0000H&¢\u0006\u0002\u0010\bJ\b\u0010\u0018\u001a\u00020\u0019H&J\u0015\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\rH&¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0019H&¢\u0006\u0002\u0010\u001dJ\u0015\u0010\u001e\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000bJ\b\u0010\u001f\u001a\u00020 H&J\b\u0010!\u001a\u00020\rH&J\u0015\u0010\"\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000bJ\b\u0010#\u001a\u00020$H&J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\rH&J\u000e\u0010&\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006)"}, d2 = {"Lcom/ionspin/kotlin/bignum/BigNumber;", "BigType", "", "isNegative", "", "()Z", "isPositive", "abs", "()Lcom/ionspin/kotlin/bignum/BigNumber;", "add", "other", "(Lcom/ionspin/kotlin/bignum/BigNumber;)Lcom/ionspin/kotlin/bignum/BigNumber;", "compareTo", "", "divide", "divideAndRemainder", "Lkotlin/Pair;", "(Lcom/ionspin/kotlin/bignum/BigNumber;)Lkotlin/Pair;", "equals", "getCreator", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "isZero", "multiply", "negate", "numberOfDecimalDigits", "", "pow", "exponent", "(I)Lcom/ionspin/kotlin/bignum/BigNumber;", "(J)Lcom/ionspin/kotlin/bignum/BigNumber;", "remainder", "secureOverwrite", "", "signum", "subtract", "toString", "", "base", "unaryMinus", "Creator", "Util", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface BigNumber<BigType extends BigNumber<BigType>> {

    @Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0015\u0010\f\u001a\u00028\u00012\u0006\u0010\r\u001a\u00020\u000eH&¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0010\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u0012H&¢\u0006\u0002\u0010\u0013J\u0015\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0015\u001a\u00020\u0016H&¢\u0006\u0002\u0010\u0017J\u0015\u0010\u0018\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u001aH&¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001c\u001a\u00028\u00012\u0006\u0010\u001d\u001a\u00020\u001eH&¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u00028\u00012\u0006\u0010!\u001a\u00020\"H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0013J\u001d\u0010$\u001a\u00028\u00012\u0006\u0010%\u001a\u00020&H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010\u0017J\u001d\u0010(\u001a\u00028\u00012\u0006\u0010)\u001a\u00020*H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010\u001bJ\u001d\u0010,\u001a\u00028\u00012\u0006\u0010-\u001a\u00020.H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u0010\u001fJ\u001f\u00100\u001a\u00028\u00012\u0006\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u00020\u0016H&¢\u0006\u0002\u00104J\u001f\u00105\u001a\u00028\u00012\u0006\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u000209H&¢\u0006\u0002\u0010:J\u001f\u0010;\u001a\u00028\u00012\u0006\u0010<\u001a\u00020=2\b\b\u0002\u00108\u001a\u000209H&¢\u0006\u0002\u0010>R\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006?"}, d2 = {"Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "BigType", "", "ONE", "getONE", "()Ljava/lang/Object;", "TEN", "getTEN", "TWO", "getTWO", "ZERO", "getZERO", "fromBigInteger", "bigInteger", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;)Ljava/lang/Object;", "fromByte", "byte", "", "(B)Ljava/lang/Object;", "fromInt", "int", "", "(I)Ljava/lang/Object;", "fromLong", "long", "", "(J)Ljava/lang/Object;", "fromShort", "short", "", "(S)Ljava/lang/Object;", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-7apg3OU", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-WZ4Q5Ns", "fromULong", "uLong", "Lkotlin/ULong;", "fromULong-VKZWuLQ", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-xj2QHRw", "parseString", "string", "", "base", "(Ljava/lang/String;I)Ljava/lang/Object;", "tryFromDouble", "double", "", "exactRequired", "", "(DZ)Ljava/lang/Object;", "tryFromFloat", "float", "", "(FZ)Ljava/lang/Object;", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Creator<BigType> {

        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Object parseString$default(Creator creator, String str, int i3, int i4, Object obj) {
                if (obj == null) {
                    if ((i4 & 2) != 0) {
                        i3 = 10;
                    }
                    return creator.parseString(str, i3);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: parseString");
            }

            public static /* synthetic */ Object tryFromDouble$default(Creator creator, double d2, boolean z2, int i3, Object obj) {
                if (obj == null) {
                    if ((i3 & 2) != 0) {
                        z2 = false;
                    }
                    return creator.tryFromDouble(d2, z2);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryFromDouble");
            }

            public static /* synthetic */ Object tryFromFloat$default(Creator creator, float f2, boolean z2, int i3, Object obj) {
                if (obj == null) {
                    if ((i3 & 2) != 0) {
                        z2 = false;
                    }
                    return creator.tryFromFloat(f2, z2);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryFromFloat");
            }
        }

        BigType fromBigInteger(@NotNull BigInteger bigInteger);

        BigType fromByte(byte b3);

        BigType fromInt(int i3);

        BigType fromLong(long j2);

        BigType fromShort(short s3);

        /* renamed from: fromUByte-7apg3OU  reason: not valid java name */
        BigType m8266fromUByte7apg3OU(byte b3);

        /* renamed from: fromUInt-WZ4Q5Ns  reason: not valid java name */
        BigType m8267fromUIntWZ4Q5Ns(int i3);

        /* renamed from: fromULong-VKZWuLQ  reason: not valid java name */
        BigType m8268fromULongVKZWuLQ(long j2);

        /* renamed from: fromUShort-xj2QHRw  reason: not valid java name */
        BigType m8269fromUShortxj2QHRw(short s3);

        BigType getONE();

        BigType getTEN();

        BigType getTWO();

        BigType getZERO();

        BigType parseString(@NotNull String str, int i3);

        BigType tryFromDouble(double d2, boolean z2);

        BigType tryFromFloat(float f2, boolean z2);
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static <BigType extends BigNumber<BigType>> boolean isNegative(@NotNull BigNumber<BigType> bigNumber) {
            Intrinsics.checkNotNullParameter(bigNumber, "this");
            return bigNumber.signum() < 0;
        }

        public static <BigType extends BigNumber<BigType>> boolean isPositive(@NotNull BigNumber<BigType> bigNumber) {
            Intrinsics.checkNotNullParameter(bigNumber, "this");
            return bigNumber.signum() > 0;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u00028\u00012\u0006\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0007\u001a\u00028\u00012\u0006\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/ionspin/kotlin/bignum/BigNumber$Util;", "BigType", "", "max", "first", "second", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "min", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Util<BigType> {
        BigType max(BigType bigtype, BigType bigtype2);

        BigType min(BigType bigtype, BigType bigtype2);
    }

    @NotNull
    BigType abs();

    @NotNull
    BigType add(@NotNull BigType bigtype);

    int compareTo(@NotNull Object obj);

    @NotNull
    BigType divide(@NotNull BigType bigtype);

    @NotNull
    Pair<BigType, BigType> divideAndRemainder(@NotNull BigType bigtype);

    boolean equals(@Nullable Object obj);

    @NotNull
    Creator<BigType> getCreator();

    boolean isNegative();

    boolean isPositive();

    boolean isZero();

    @NotNull
    BigType multiply(@NotNull BigType bigtype);

    @NotNull
    BigType negate();

    long numberOfDecimalDigits();

    @NotNull
    BigType pow(int i3);

    @NotNull
    BigType pow(long j2);

    @NotNull
    BigType remainder(@NotNull BigType bigtype);

    void secureOverwrite();

    int signum();

    @NotNull
    BigType subtract(@NotNull BigType bigtype);

    @NotNull
    String toString();

    @NotNull
    String toString(int i3);

    @NotNull
    BigType unaryMinus();
}
