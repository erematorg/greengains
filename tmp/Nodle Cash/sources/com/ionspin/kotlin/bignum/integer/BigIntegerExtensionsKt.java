package com.ionspin.kotlin.bignum.integer;

import com.ionspin.kotlin.bignum.BigNumber;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\n\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\"\u0010\u0013\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\t\u001a\"\u0010\u0013\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\f\u001a\"\u0010\u0013\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u000f\u001a\"\u0010\u0013\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0012\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\"\u0010\u0018\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\t\u001a\"\u0010\u0018\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\f\u001a\"\u0010\u0018\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u000f\u001a\"\u0010\u0018\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u0012\u001a\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\"\u0010\u001d\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\t\u001a\"\u0010\u001d\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\f\u001a\"\u0010\u001d\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010\u000f\u001a\"\u0010\u001d\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\u0012\u001a\u0015\u0010\"\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\"\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\"\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\"\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\"\u0010\"\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\t\u001a\"\u0010\"\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\f\u001a\"\u0010\"\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u000f\u001a\"\u0010\"\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0012\u001a\n\u0010'\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010'\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010'\u001a\u00020\u0001*\u00020\u0005\u001a\n\u0010'\u001a\u00020\u0001*\u00020\u0006\u001a\u0014\u0010'\u001a\u00020\u0001*\u00020(2\b\b\u0002\u0010)\u001a\u00020\u0004\u001a\u0017\u0010'\u001a\u00020\u0001*\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+\u001a\u0017\u0010'\u001a\u00020\u0001*\u00020\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a\u0017\u0010'\u001a\u00020\u0001*\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a\u0017\u0010'\u001a\u00020\u0001*\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"div", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "", "other", "", "", "", "Lkotlin/UByte;", "div-0ky7B_Q", "(BLcom/ionspin/kotlin/bignum/integer/BigInteger;)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "Lkotlin/UInt;", "div-qim9Vi0", "(ILcom/ionspin/kotlin/bignum/integer/BigInteger;)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "Lkotlin/ULong;", "div-4PLdz1A", "(JLcom/ionspin/kotlin/bignum/integer/BigInteger;)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "Lkotlin/UShort;", "div-vckuEUM", "(SLcom/ionspin/kotlin/bignum/integer/BigInteger;)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "minus", "minus-0ky7B_Q", "minus-qim9Vi0", "minus-4PLdz1A", "minus-vckuEUM", "plus", "plus-0ky7B_Q", "plus-qim9Vi0", "plus-4PLdz1A", "plus-vckuEUM", "rem", "rem-0ky7B_Q", "rem-qim9Vi0", "rem-4PLdz1A", "rem-vckuEUM", "times", "times-0ky7B_Q", "times-qim9Vi0", "times-4PLdz1A", "times-vckuEUM", "toBigInteger", "", "base", "toBigInteger-7apg3OU", "(B)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "toBigInteger-WZ4Q5Ns", "(I)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "toBigInteger-VKZWuLQ", "(J)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "toBigInteger-xj2QHRw", "(S)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "bignum"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class BigIntegerExtensionsKt {
    @NotNull
    public static final BigInteger div(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(j2).div((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: div-0ky7B_Q  reason: not valid java name */
    public static final BigInteger m8399div0ky7B_Q(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8419toBigInteger7apg3OU(b3).div((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: div-4PLdz1A  reason: not valid java name */
    public static final BigInteger m8400div4PLdz1A(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8420toBigIntegerVKZWuLQ(j2).div((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: div-qim9Vi0  reason: not valid java name */
    public static final BigInteger m8401divqim9Vi0(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8421toBigIntegerWZ4Q5Ns(i3).div((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: div-vckuEUM  reason: not valid java name */
    public static final BigInteger m8402divvckuEUM(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8422toBigIntegerxj2QHRw(s3).div((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger minus(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(j2).minus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: minus-0ky7B_Q  reason: not valid java name */
    public static final BigInteger m8403minus0ky7B_Q(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8419toBigInteger7apg3OU(b3).minus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: minus-4PLdz1A  reason: not valid java name */
    public static final BigInteger m8404minus4PLdz1A(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8420toBigIntegerVKZWuLQ(j2).minus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: minus-qim9Vi0  reason: not valid java name */
    public static final BigInteger m8405minusqim9Vi0(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8421toBigIntegerWZ4Q5Ns(i3).minus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: minus-vckuEUM  reason: not valid java name */
    public static final BigInteger m8406minusvckuEUM(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8422toBigIntegerxj2QHRw(s3).minus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger plus(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(j2).plus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: plus-0ky7B_Q  reason: not valid java name */
    public static final BigInteger m8407plus0ky7B_Q(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8419toBigInteger7apg3OU(b3).plus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: plus-4PLdz1A  reason: not valid java name */
    public static final BigInteger m8408plus4PLdz1A(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8420toBigIntegerVKZWuLQ(j2).plus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: plus-qim9Vi0  reason: not valid java name */
    public static final BigInteger m8409plusqim9Vi0(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8421toBigIntegerWZ4Q5Ns(i3).plus((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: plus-vckuEUM  reason: not valid java name */
    public static final BigInteger m8410plusvckuEUM(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8422toBigIntegerxj2QHRw(s3).plus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger rem(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(j2).rem((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: rem-0ky7B_Q  reason: not valid java name */
    public static final BigInteger m8411rem0ky7B_Q(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8419toBigInteger7apg3OU(b3).rem((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: rem-4PLdz1A  reason: not valid java name */
    public static final BigInteger m8412rem4PLdz1A(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8420toBigIntegerVKZWuLQ(j2).rem((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: rem-qim9Vi0  reason: not valid java name */
    public static final BigInteger m8413remqim9Vi0(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8421toBigIntegerWZ4Q5Ns(i3).rem((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: rem-vckuEUM  reason: not valid java name */
    public static final BigInteger m8414remvckuEUM(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8422toBigIntegerxj2QHRw(s3).rem((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger times(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(j2).times((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: times-0ky7B_Q  reason: not valid java name */
    public static final BigInteger m8415times0ky7B_Q(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8419toBigInteger7apg3OU(b3).times((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: times-4PLdz1A  reason: not valid java name */
    public static final BigInteger m8416times4PLdz1A(long j2, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8420toBigIntegerVKZWuLQ(j2).times((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: times-qim9Vi0  reason: not valid java name */
    public static final BigInteger m8417timesqim9Vi0(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8421toBigIntegerWZ4Q5Ns(i3).times((BigNumber) bigInteger);
    }

    @NotNull
    /* renamed from: times-vckuEUM  reason: not valid java name */
    public static final BigInteger m8418timesvckuEUM(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) m8422toBigIntegerxj2QHRw(s3).times((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger toBigInteger(long j2) {
        return BigInteger.Companion.fromLong(j2);
    }

    public static /* synthetic */ BigInteger toBigInteger$default(String str, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = 10;
        }
        return toBigInteger(str, i3);
    }

    @NotNull
    /* renamed from: toBigInteger-7apg3OU  reason: not valid java name */
    public static final BigInteger m8419toBigInteger7apg3OU(byte b3) {
        return BigInteger.Companion.m8308fromUByte7apg3OU(b3);
    }

    @NotNull
    /* renamed from: toBigInteger-VKZWuLQ  reason: not valid java name */
    public static final BigInteger m8420toBigIntegerVKZWuLQ(long j2) {
        return BigInteger.Companion.m8314fromULongVKZWuLQ(j2);
    }

    @NotNull
    /* renamed from: toBigInteger-WZ4Q5Ns  reason: not valid java name */
    public static final BigInteger m8421toBigIntegerWZ4Q5Ns(int i3) {
        return BigInteger.Companion.m8312fromUIntWZ4Q5Ns(i3);
    }

    @NotNull
    /* renamed from: toBigInteger-xj2QHRw  reason: not valid java name */
    public static final BigInteger m8422toBigIntegerxj2QHRw(short s3) {
        return BigInteger.Companion.m8316fromUShortxj2QHRw(s3);
    }

    @NotNull
    public static final BigInteger div(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(i3).div((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger minus(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(i3).minus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger plus(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(i3).plus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger rem(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(i3).rem((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger times(int i3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(i3).times((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger toBigInteger(int i3) {
        return BigInteger.Companion.fromInt(i3);
    }

    @NotNull
    public static final BigInteger div(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(s3).div((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger minus(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(s3).minus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger plus(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(s3).plus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger rem(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(s3).rem((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger times(short s3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(s3).times((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger toBigInteger(short s3) {
        return BigInteger.Companion.fromShort(s3);
    }

    @NotNull
    public static final BigInteger div(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(b3).div((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger minus(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(b3).minus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger plus(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(b3).plus((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger rem(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(b3).rem((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger times(byte b3, @NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return (BigInteger) toBigInteger(b3).times((BigNumber) bigInteger);
    }

    @NotNull
    public static final BigInteger toBigInteger(byte b3) {
        return BigInteger.Companion.fromByte(b3);
    }

    @NotNull
    public static final BigInteger toBigInteger(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return BigInteger.Companion.parseString(str, i3);
    }
}
