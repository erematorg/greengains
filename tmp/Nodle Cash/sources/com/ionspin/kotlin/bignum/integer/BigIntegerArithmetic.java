package com.ionspin.kotlin.bignum.integer;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ULongArray;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b%\bf\u0018\u00002\u00020\u0001J%\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0017J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!J\u001d\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J%\u0010&\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(J1\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010,J \u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020/H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b0\u00101J \u00102\u001a\u00020\u00032\u0006\u00103\u001a\u000204H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b5\u00106J \u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b9\u0010:J \u0010;\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u001fH&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b=\u0010>J \u0010?\u001a\u00020\u00032\u0006\u0010@\u001a\u00020AH&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bB\u0010CJ\u001d\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bG\u00101J\u001d\u0010H\u001a\u00020\u00032\u0006\u00103\u001a\u00020IH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bJ\u00106J\u001d\u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020MH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bN\u0010:J\u001d\u0010O\u001a\u00020\u00032\u0006\u0010P\u001a\u00020QH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bR\u0010>J\u001d\u0010S\u001a\u00020\u00032\u0006\u0010T\u001a\u00020UH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bV\u0010CJ%\u0010W\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bX\u0010\u0017J%\u0010Y\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bZ\u0010\u0017J\u001d\u0010[\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\\\u0010]J\u001d\u0010^\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b_\u0010`J\u001d\u0010a\u001a\u00020\u00102\u0006\u0010#\u001a\u00020QH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bb\u0010cJ%\u0010d\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\be\u0010\u0017J(\u0010f\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bj\u0010kJ%\u0010l\u001a\u00020\u00032\u0006\u0010i\u001a\u00020\u00032\u0006\u0010m\u001a\u00020\u001fH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bn\u0010oJ)\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bq\u0010rJ-\u0010s\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010t\u001a\u00020\u001dH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bu\u0010vJ%\u0010w\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010x\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\by\u0010zJ%\u0010{\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010x\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b|\u0010zJ)\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b~\u0010rJ&\u0010\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010\u0017J \u0010\u0001\u001a\u0002042\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J(\u0010\u0001\u001a\u00020h2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010i\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J \u0010\u0001\u001a\u00020I2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u001f\u0010\u0001\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010%J'\u0010\u0001\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010\u0017R\u001b\u0010\u0002\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001b\u0010\u0006\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001b\u0010\b\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u001b\u0010\n\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0005R\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0001"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigIntegerArithmetic;", "", "ONE", "Lkotlin/ULongArray;", "getONE-Y2RjT0g", "()[J", "TEN", "getTEN-Y2RjT0g", "TWO", "getTWO-Y2RjT0g", "ZERO", "getZERO-Y2RjT0g", "_emitLongArray", "", "get_emitLongArray", "basePowerOfTwo", "", "getBasePowerOfTwo", "()I", "add", "first", "second", "add-j68ebKY", "([J[J)[J", "and", "operand", "mask", "and-j68ebKY", "bitAt", "", "position", "", "bitAt-tBf0fek", "([JJ)Z", "bitLength", "value", "bitLength-QwZRm1k", "([J)I", "compare", "compare-GR1PJdc", "([J[J)I", "divide", "Lkotlin/Pair;", "divide-GR1PJdc", "([J[J)Lkotlin/Pair;", "fromByte", "byte", "", "fromByte-DHQ6RzY", "(B)[J", "fromByteArray", "source", "", "fromByteArray-DHQ6RzY", "([B)[J", "fromInt", "int", "fromInt-DHQ6RzY", "(I)[J", "fromLong", "long", "fromLong-DHQ6RzY", "(J)[J", "fromShort", "short", "", "fromShort-DHQ6RzY", "(S)[J", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-ab45Ak8", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-S4Jqe-A", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-kOc6_GI", "fromULong", "uLong", "Lkotlin/ULong;", "fromULong--GCcj4Q", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-jOPi9CM", "gcd", "gcd-j68ebKY", "multiply", "multiply-j68ebKY", "not", "not-JIhQxVY", "([J)[J", "numberOfDecimalDigits", "numberOfDecimalDigits-QwZRm1k", "([J)J", "numberOfLeadingZerosInAWord", "numberOfLeadingZerosInAWord-VKZWuLQ", "(J)I", "or", "or-j68ebKY", "parseForBase", "number", "", "base", "parseForBase-_llDaS8", "(Ljava/lang/String;I)[J", "pow", "exponent", "pow-GERUpyg", "([JJ)[J", "reciprocal", "reciprocal-QwZRm1k", "([J)Lkotlin/Pair;", "setBitAt", "bit", "setBitAt-v3PXmpk", "([JJZ)[J", "shiftLeft", "places", "shiftLeft-GERUpyg", "([JI)[J", "shiftRight", "shiftRight-GERUpyg", "sqrt", "sqrt-QwZRm1k", "subtract", "subtract-j68ebKY", "toByteArray", "toByteArray-QwZRm1k", "([J)[B", "toString", "toString-tBf0fek", "([JI)Ljava/lang/String;", "toUByteArray", "toUByteArray-cMszsnM", "trailingZeroBits", "trailingZeroBits-QwZRm1k", "xor", "xor-j68ebKY", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface BigIntegerArithmetic {
    @NotNull
    /* renamed from: add-j68ebKY  reason: not valid java name */
    long[] m8360addj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2);

    @NotNull
    /* renamed from: and-j68ebKY  reason: not valid java name */
    long[] m8361andj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2);

    /* renamed from: bitAt-tBf0fek  reason: not valid java name */
    boolean m8362bitAttBf0fek(@NotNull long[] jArr, long j2);

    /* renamed from: bitLength-QwZRm1k  reason: not valid java name */
    int m8363bitLengthQwZRm1k(@NotNull long[] jArr);

    /* renamed from: compare-GR1PJdc  reason: not valid java name */
    int m8364compareGR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2);

    @NotNull
    /* renamed from: divide-GR1PJdc  reason: not valid java name */
    Pair<ULongArray, ULongArray> m8365divideGR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2);

    @NotNull
    /* renamed from: fromByte-DHQ6RzY  reason: not valid java name */
    long[] m8366fromByteDHQ6RzY(byte b3);

    @NotNull
    /* renamed from: fromByteArray-DHQ6RzY  reason: not valid java name */
    long[] m8367fromByteArrayDHQ6RzY(@NotNull byte[] bArr);

    @NotNull
    /* renamed from: fromInt-DHQ6RzY  reason: not valid java name */
    long[] m8368fromIntDHQ6RzY(int i3);

    @NotNull
    /* renamed from: fromLong-DHQ6RzY  reason: not valid java name */
    long[] m8369fromLongDHQ6RzY(long j2);

    @NotNull
    /* renamed from: fromShort-DHQ6RzY  reason: not valid java name */
    long[] m8370fromShortDHQ6RzY(short s3);

    @NotNull
    /* renamed from: fromUByte-ab45Ak8  reason: not valid java name */
    long[] m8371fromUByteab45Ak8(byte b3);

    @NotNull
    /* renamed from: fromUByteArray-S4Jqe-A  reason: not valid java name */
    long[] m8372fromUByteArrayS4JqeA(@NotNull byte[] bArr);

    @NotNull
    /* renamed from: fromUInt-kOc6_GI  reason: not valid java name */
    long[] m8373fromUIntkOc6_GI(int i3);

    @NotNull
    /* renamed from: fromULong--GCcj4Q  reason: not valid java name */
    long[] m8374fromULongGCcj4Q(long j2);

    @NotNull
    /* renamed from: fromUShort-jOPi9CM  reason: not valid java name */
    long[] m8375fromUShortjOPi9CM(short s3);

    @NotNull
    /* renamed from: gcd-j68ebKY  reason: not valid java name */
    long[] m8376gcdj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2);

    int getBasePowerOfTwo();

    @NotNull
    /* renamed from: getONE-Y2RjT0g  reason: not valid java name */
    long[] m8377getONEY2RjT0g();

    @NotNull
    /* renamed from: getTEN-Y2RjT0g  reason: not valid java name */
    long[] m8378getTENY2RjT0g();

    @NotNull
    /* renamed from: getTWO-Y2RjT0g  reason: not valid java name */
    long[] m8379getTWOY2RjT0g();

    @NotNull
    /* renamed from: getZERO-Y2RjT0g  reason: not valid java name */
    long[] m8380getZEROY2RjT0g();

    @NotNull
    long[] get_emitLongArray();

    @NotNull
    /* renamed from: multiply-j68ebKY  reason: not valid java name */
    long[] m8381multiplyj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2);

    @NotNull
    /* renamed from: not-JIhQxVY  reason: not valid java name */
    long[] m8382notJIhQxVY(@NotNull long[] jArr);

    /* renamed from: numberOfDecimalDigits-QwZRm1k  reason: not valid java name */
    long m8383numberOfDecimalDigitsQwZRm1k(@NotNull long[] jArr);

    /* renamed from: numberOfLeadingZerosInAWord-VKZWuLQ  reason: not valid java name */
    int m8384numberOfLeadingZerosInAWordVKZWuLQ(long j2);

    @NotNull
    /* renamed from: or-j68ebKY  reason: not valid java name */
    long[] m8385orj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2);

    @NotNull
    /* renamed from: parseForBase-_llDaS8  reason: not valid java name */
    long[] m8386parseForBase_llDaS8(@NotNull String str, int i3);

    @NotNull
    /* renamed from: pow-GERUpyg  reason: not valid java name */
    long[] m8387powGERUpyg(@NotNull long[] jArr, long j2);

    @NotNull
    /* renamed from: reciprocal-QwZRm1k  reason: not valid java name */
    Pair<ULongArray, ULongArray> m8388reciprocalQwZRm1k(@NotNull long[] jArr);

    @NotNull
    /* renamed from: setBitAt-v3PXmpk  reason: not valid java name */
    long[] m8389setBitAtv3PXmpk(@NotNull long[] jArr, long j2, boolean z2);

    @NotNull
    /* renamed from: shiftLeft-GERUpyg  reason: not valid java name */
    long[] m8390shiftLeftGERUpyg(@NotNull long[] jArr, int i3);

    @NotNull
    /* renamed from: shiftRight-GERUpyg  reason: not valid java name */
    long[] m8391shiftRightGERUpyg(@NotNull long[] jArr, int i3);

    @NotNull
    /* renamed from: sqrt-QwZRm1k  reason: not valid java name */
    Pair<ULongArray, ULongArray> m8392sqrtQwZRm1k(@NotNull long[] jArr);

    @NotNull
    /* renamed from: subtract-j68ebKY  reason: not valid java name */
    long[] m8393subtractj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2);

    @NotNull
    /* renamed from: toByteArray-QwZRm1k  reason: not valid java name */
    byte[] m8394toByteArrayQwZRm1k(@NotNull long[] jArr);

    @NotNull
    /* renamed from: toString-tBf0fek  reason: not valid java name */
    String m8395toStringtBf0fek(@NotNull long[] jArr, int i3);

    @NotNull
    /* renamed from: toUByteArray-cMszsnM  reason: not valid java name */
    byte[] m8396toUByteArraycMszsnM(@NotNull long[] jArr);

    /* renamed from: trailingZeroBits-QwZRm1k  reason: not valid java name */
    int m8397trailingZeroBitsQwZRm1k(@NotNull long[] jArr);

    @NotNull
    /* renamed from: xor-j68ebKY  reason: not valid java name */
    long[] m8398xorj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2);
}
