package com.ionspin.kotlin.bignum.integer.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import com.ionspin.kotlin.bignum.integer.Sign;
import com.ionspin.kotlin.bignum.integer.base32.BigInteger32Arithmetic;
import com.ionspin.kotlin.bignum.integer.base63.array.BigInteger63Arithmetic;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UnsignedKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.UCollectionsKt;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000b\u001a\u001b\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u0004\u001a\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\b\u001a\u001b\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u000b\u001a;\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0017\u0010\u001a\u001a\u00020\u001b*\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0017\u0010\u001e\u001a\u00020\u001b*\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u001d\u001a\u0012\u0010 \u001a\u00020!*\u00020\"2\u0006\u0010\u0012\u001a\u00020#\u001a\u0017\u0010$\u001a\u00020\u0001*\u00020%ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u001a\u0017\u0010$\u001a\u00020\u0001*\u00020\u001bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)\u001a\u0017\u0010*\u001a\u00020\u0001*\u00020%ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010'\u001a\u0017\u0010*\u001a\u00020\u0001*\u00020\u001bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010)\u001a\n\u0010-\u001a\u00020#*\u00020!\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"increment", "Lkotlin/UByteArray;", "byteString", "increment-GBYM_sE", "([B)[B", "Lkotlin/UIntArray;", "array", "increment--ajY-9A", "([I)[I", "Lkotlin/ULongArray;", "increment-QwZRm1k", "([J)[J", "invert", "invert-GBYM_sE", "invert--ajY-9A", "invert-QwZRm1k", "mirrorBytes", "", "source", "start", "", "end", "target", "targetStart", "mirrorBytes-rBRerf4", "([BII[BI)V", "fromBigEndianArrayToULong", "Lkotlin/ULong;", "fromBigEndianArrayToULong-GBYM_sE", "([B)J", "fromLittleEndianArrayToULong", "fromLittleEndianArrayToULong-GBYM_sE", "fromTwosComplementByteArray", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "Lcom/ionspin/kotlin/bignum/integer/BigInteger$Companion;", "", "toBigEndianUByteArray", "Lkotlin/UInt;", "toBigEndianUByteArray-WZ4Q5Ns", "(I)[B", "toBigEndianUByteArray-VKZWuLQ", "(J)[B", "toLittleEndianUByteArray", "toLittleEndianUByteArray-WZ4Q5Ns", "toLittleEndianUByteArray-VKZWuLQ", "toTwosComplementByteArray", "bignum"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class ConversionUtilsKt {
    /* renamed from: fromBigEndianArrayToULong-GBYM_sE  reason: not valid java name */
    public static final long m8674fromBigEndianArrayToULongGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "$this$fromBigEndianArrayToULong");
        if (UByteArray.m9056getSizeimpl(bArr) <= 8) {
            int length = bArr.length;
            int i3 = 0;
            long j2 = 0;
            int i4 = 0;
            while (i3 < length) {
                j2 = ULong.m9153constructorimpl(j2 | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) bArr[i3]) & 255) << (56 - (i4 * 8))));
                i3++;
                i4++;
            }
            return j2;
        }
        throw new RuntimeException("ore than 8 bytes in input, potential overflow");
    }

    /* renamed from: fromLittleEndianArrayToULong-GBYM_sE  reason: not valid java name */
    public static final long m8675fromLittleEndianArrayToULongGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "$this$fromLittleEndianArrayToULong");
        if (UByteArray.m9056getSizeimpl(bArr) <= 8) {
            int length = bArr.length;
            int i3 = 0;
            long j2 = 0;
            int i4 = 0;
            while (i3 < length) {
                j2 = ULong.m9153constructorimpl(j2 | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) bArr[i3]) & 255) << (i4 * 8)));
                i3++;
                i4++;
            }
            return j2;
        }
        throw new RuntimeException("More than 8 bytes in input, potential overflow");
    }

    @NotNull
    public static final BigInteger fromTwosComplementByteArray(@NotNull BigInteger.Companion companion, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(bArr, "source");
        if (bArr.length == 0) {
            return companion.getZERO();
        }
        if (bArr[0] >= 0) {
            return companion.fromByteArray(bArr, Sign.POSITIVE);
        }
        return BigInteger.Companion.m8309fromUByteArrayrto03Yo(m8677incrementGBYM_sE(m8680invertGBYM_sE(UByteArray.m9050constructorimpl(bArr))), Sign.NEGATIVE);
    }

    @NotNull
    /* renamed from: increment--ajY-9A  reason: not valid java name */
    public static final int[] m8676incrementajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "array");
        int length = iArr.length - 1;
        while (true) {
            if (length < 0) {
                length = -1;
                break;
            } else if (Integer.compareUnsigned(UInt.m9074constructorimpl(iArr[length]), -1) < 0) {
                break;
            } else {
                length--;
            }
        }
        if (length == -1) {
            return UIntArray.m9129constructorimpl(ArraysKt.plus(new int[]{1}, iArr));
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        int[] r3 = UIntArray.m9129constructorimpl(copyOf);
        UIntArray.m9139setVXSXFK8(r3, length, UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(r3, length) + 1));
        return r3;
    }

    @NotNull
    /* renamed from: increment-GBYM_sE  reason: not valid java name */
    public static final byte[] m8677incrementGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "byteString");
        int length = bArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                if (UnsignedKt.uintCompare(UInt.m9074constructorimpl(UByte.m8997constructorimpl(bArr[length]) & 255), 255) < 0) {
                    break;
                }
            } else {
                length = -1;
                break;
            }
        }
        if (length != -1) {
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
            byte[] r4 = UByteArray.m9050constructorimpl(copyOf);
            int r7 = UByteArray.m9056getSizeimpl(bArr) - 1;
            int i3 = length + 1;
            if (i3 <= r7) {
                while (true) {
                    int i4 = r7 - 1;
                    UByteArray.m9060setVurrAj0(r4, r7, (byte) 0);
                    if (r7 == i3) {
                        break;
                    }
                    r7 = i4;
                }
            }
            UByteArray.m9060setVurrAj0(r4, length, UByte.m8997constructorimpl((byte) (UByteArray.m9055getw2LRezQ(r4, length) + 1)));
            return r4;
        }
        return UByteArray.m9050constructorimpl(ArraysKt.plus(new byte[]{1}, bArr));
    }

    @NotNull
    /* renamed from: increment-QwZRm1k  reason: not valid java name */
    public static final long[] m8678incrementQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "array");
        int length = jArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                if (Long.compareUnsigned(ULong.m9153constructorimpl(jArr[length]), -1) < 0) {
                    break;
                }
            } else {
                length = -1;
                break;
            }
        }
        if (length != -1) {
            long[] copyOf = Arrays.copyOf(jArr, jArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
            long[] r6 = ULongArray.m9208constructorimpl(copyOf);
            ULongArray.m9218setk8EXiF4(r6, length, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r6, length) + 1));
            return r6;
        }
        return ULongArray.m9208constructorimpl(ArraysKt.plus(new long[]{1}, jArr));
    }

    @NotNull
    /* renamed from: invert--ajY-9A  reason: not valid java name */
    public static final int[] m8679invertajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "array");
        int r02 = ((BigInteger32Arithmetic.INSTANCE.m8445bitLengthWZ4Q5Ns(UIntArray.m9134getpVg5ArA(iArr, 0)) + 7) / 8) * 8;
        ArrayList arrayList = new ArrayList(UIntArray.m9135getSizeimpl(iArr));
        for (int i3 : iArr) {
            arrayList.add(UInt.m9068boximpl(UInt.m9074constructorimpl(~i3)));
        }
        int[] uIntArray = UCollectionsKt.toUIntArray(arrayList);
        UIntArray.m9139setVXSXFK8(uIntArray, 0, UInt.m9074constructorimpl(UInt.m9074constructorimpl(~UInt.m9074constructorimpl(-1 << r02)) & UIntArray.m9134getpVg5ArA(uIntArray, 0)));
        return uIntArray;
    }

    @NotNull
    /* renamed from: invert-GBYM_sE  reason: not valid java name */
    public static final byte[] m8680invertGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "array");
        ArrayList arrayList = new ArrayList(UByteArray.m9056getSizeimpl(bArr));
        for (byte b3 : bArr) {
            arrayList.add(UByte.m8991boximpl(UByte.m8997constructorimpl((byte) (~b3))));
        }
        return UCollectionsKt.toUByteArray(arrayList);
    }

    @NotNull
    /* renamed from: invert-QwZRm1k  reason: not valid java name */
    public static final long[] m8681invertQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "array");
        int r02 = ((BigInteger63Arithmetic.INSTANCE.m8578bitLengthVKZWuLQ(ULongArray.m9213getsVKNKU(jArr, 0)) + 7) / 8) * 8;
        ArrayList arrayList = new ArrayList(ULongArray.m9214getSizeimpl(jArr));
        for (long j2 : jArr) {
            arrayList.add(ULong.m9147boximpl(ULong.m9153constructorimpl(~j2)));
        }
        long[] uLongArray = UCollectionsKt.toULongArray(arrayList);
        ULongArray.m9218setk8EXiF4(uLongArray, 0, ULong.m9153constructorimpl(ULong.m9153constructorimpl(~ULong.m9153constructorimpl(-1 << r02)) & ULongArray.m9213getsVKNKU(uLongArray, 0)));
        return uLongArray;
    }

    /* renamed from: mirrorBytes-rBRerf4  reason: not valid java name */
    public static final void m8682mirrorBytesrBRerf4(@NotNull byte[] bArr, int i3, int i4, @NotNull byte[] bArr2, int i5) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        Intrinsics.checkNotNullParameter(bArr2, TypedValues.AttributesType.S_TARGET);
        int i6 = i4 - i3;
        if (i6 > 0) {
            int i7 = 0;
            while (true) {
                int i8 = i7 + 1;
                UByteArray.m9060setVurrAj0(bArr2, ((i5 + i6) - i7) - 1, UByteArray.m9055getw2LRezQ(bArr, i7 + i3));
                if (i8 < i6) {
                    i7 = i8;
                } else {
                    return;
                }
            }
        }
    }

    @NotNull
    /* renamed from: toBigEndianUByteArray-VKZWuLQ  reason: not valid java name */
    public static final byte[] m8683toBigEndianUByteArrayVKZWuLQ(long j2) {
        byte[] bArr = new byte[8];
        for (int i3 = 0; i3 < 8; i3++) {
            bArr[i3] = UByte.m8997constructorimpl((byte) ((int) ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 >>> (56 - (i3 * 8))) & 255)));
        }
        return UByteArray.m9050constructorimpl(bArr);
    }

    @NotNull
    /* renamed from: toBigEndianUByteArray-WZ4Q5Ns  reason: not valid java name */
    public static final byte[] m8684toBigEndianUByteArrayWZ4Q5Ns(int i3) {
        byte[] bArr = new byte[4];
        for (int i4 = 0; i4 < 4; i4++) {
            bArr[i4] = UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i3 >>> (24 - (i4 * 8))) & 255));
        }
        return UByteArray.m9050constructorimpl(bArr);
    }

    @NotNull
    /* renamed from: toLittleEndianUByteArray-VKZWuLQ  reason: not valid java name */
    public static final byte[] m8685toLittleEndianUByteArrayVKZWuLQ(long j2) {
        byte[] bArr = new byte[8];
        for (int i3 = 0; i3 < 8; i3++) {
            bArr[i3] = UByte.m8997constructorimpl((byte) ((int) ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 >>> (i3 * 8)) & 255)));
        }
        return UByteArray.m9050constructorimpl(bArr);
    }

    @NotNull
    /* renamed from: toLittleEndianUByteArray-WZ4Q5Ns  reason: not valid java name */
    public static final byte[] m8686toLittleEndianUByteArrayWZ4Q5Ns(int i3) {
        byte[] bArr = new byte[4];
        for (int i4 = 0; i4 < 4; i4++) {
            bArr[i4] = UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i3 >>> (i4 * 8)) & 255));
        }
        return UByteArray.m9050constructorimpl(bArr);
    }

    @NotNull
    public static final byte[] toTwosComplementByteArray(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "<this>");
        if (ULongArray.m9216isEmptyimpl(bigInteger.m8301getMagnitudeY2RjT0g$bignum())) {
            return new byte[]{0};
        } else if (bigInteger.getSign$bignum() == Sign.NEGATIVE) {
            if (ULongArray.m9214getSizeimpl(bigInteger.m8301getMagnitudeY2RjT0g$bignum()) == 1 && ULongArray.m9213getsVKNKU(bigInteger.m8301getMagnitudeY2RjT0g$bignum(), 0) == 1) {
                return new byte[]{-1};
            }
            byte[] r9 = m8677incrementGBYM_sE(m8680invertGBYM_sE(UByteArray.m9050constructorimpl(bigInteger.toByteArray())));
            int length = r9.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    i3 = -1;
                    break;
                } else if (r9[i3] != -1) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 == -1) {
                i3 = 0;
            }
            if (UInt.m9074constructorimpl(UInt.m9074constructorimpl(UInt.m9074constructorimpl(r9[i3]) & 255) >>> 7) == 1) {
                return ArraysKt.sliceArray(r9, RangesKt.until(i3, r9.length));
            }
            return ArraysKt.plus(new byte[]{-1}, ArraysKt.sliceArray(r9, RangesKt.until(i3, r9.length)));
        } else if (UArraysKt.m9546contentEqualslec5QzE(bigInteger.m8301getMagnitudeY2RjT0g$bignum(), BigInteger63Arithmetic.INSTANCE.m8623getZEROY2RjT0g())) {
            return new byte[]{0};
        } else {
            byte[] byteArray = bigInteger.toByteArray();
            int length2 = byteArray.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length2) {
                    i4 = -1;
                    break;
                } else if (UInt.m9074constructorimpl(byteArray[i4]) != 0) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 == -1) {
                i4 = 0;
            }
            if (UInt.m9074constructorimpl(UInt.m9074constructorimpl(UInt.m9074constructorimpl(byteArray[i4]) & 255) >>> 7) == 0) {
                return ArraysKt.sliceArray(byteArray, RangesKt.until(i4, byteArray.length));
            }
            return ArraysKt.plus(new byte[]{0}, ArraysKt.sliceArray(byteArray, RangesKt.until(i4, byteArray.length)));
        }
    }
}
