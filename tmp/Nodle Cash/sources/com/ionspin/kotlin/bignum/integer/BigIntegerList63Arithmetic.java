package com.ionspin.kotlin.bignum.integer;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ULong;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0013\bf\u0018\u00002\u00020\u0001J-\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J-\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J!\u0010\u0017\u001a\u00020\u00182\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0019\u001a\u00020\u001aH&ø\u0001\u0000J\u0019\u0010\u001b\u001a\u00020\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J'\u0010\u001d\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J?\u0010\u001e\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u001f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J\u0019\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010!\u001a\u00020\"H&ø\u0001\u0000J\u0019\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010$\u001a\u00020%H&ø\u0001\u0000J\u0019\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010'\u001a\u00020\u000eH&ø\u0001\u0000J\u0019\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010)\u001a\u00020\u001aH&ø\u0001\u0000J\u0019\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010+\u001a\u00020,H&ø\u0001\u0000J#\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010.\u001a\u00020/H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101J#\u00102\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010$\u001a\u000203H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00105J#\u00106\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u00107\u001a\u000208H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b9\u0010:J#\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010<\u001a\u00020\u0004H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b=\u0010>J#\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010@\u001a\u00020AH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bB\u0010CJ-\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J-\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J\u001f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J\u0019\u0010G\u001a\u00020\u001a2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J\u001d\u0010H\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0004H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bI\u0010JJ-\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J!\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u000eH&ø\u0001\u0000J'\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010Q\u001a\u00020\u001aH&ø\u0001\u0000J1\u0010R\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J/\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010T\u001a\u00020\u0018H&ø\u0001\u0000J'\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010V\u001a\u00020\u000eH&ø\u0001\u0000J'\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010V\u001a\u00020\u000eH&ø\u0001\u0000J1\u0010X\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J-\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J\u0019\u0010Z\u001a\u00020%2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J!\u0010[\u001a\u00020N2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010O\u001a\u00020\u000eH&ø\u0001\u0000J&\u0010\\\u001a\u0002032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b]\u0010^J\u0019\u0010_\u001a\u00020\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000J-\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006a"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigIntegerList63Arithmetic;", "", "ONE", "", "Lkotlin/ULong;", "getONE", "()Ljava/util/List;", "TEN", "getTEN", "TWO", "getTWO", "ZERO", "getZERO", "basePowerOfTwo", "", "getBasePowerOfTwo", "()I", "add", "first", "second", "and", "operand", "mask", "bitAt", "", "position", "", "bitLength", "value", "compare", "divide", "Lkotlin/Pair;", "fromByte", "byte", "", "fromByteArray", "source", "", "fromInt", "int", "fromLong", "long", "fromShort", "short", "", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-7apg3OU", "(B)Ljava/util/List;", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-GBYM_sE", "([B)Ljava/util/List;", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-WZ4Q5Ns", "(I)Ljava/util/List;", "fromULong", "uLong", "fromULong-VKZWuLQ", "(J)Ljava/util/List;", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-xj2QHRw", "(S)Ljava/util/List;", "gcd", "multiply", "not", "numberOfDecimalDigits", "numberOfLeadingZerosInAWord", "numberOfLeadingZerosInAWord-VKZWuLQ", "(J)I", "or", "parseForBase", "number", "", "base", "pow", "exponent", "reciprocal", "setBitAt", "bit", "shiftLeft", "places", "shiftRight", "sqrt", "subtract", "toByteArray", "toString", "toUByteArray", "toUByteArray-NTtOWj4", "(Ljava/util/List;)[B", "trailingZeroBits", "xor", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface BigIntegerList63Arithmetic {
    @NotNull
    List<ULong> add(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    @NotNull
    List<ULong> and(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    boolean bitAt(@NotNull List<ULong> list, long j2);

    int bitLength(@NotNull List<ULong> list);

    int compare(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    @NotNull
    Pair<List<ULong>, List<ULong>> divide(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    @NotNull
    List<ULong> fromByte(byte b3);

    @NotNull
    List<ULong> fromByteArray(@NotNull byte[] bArr);

    @NotNull
    List<ULong> fromInt(int i3);

    @NotNull
    List<ULong> fromLong(long j2);

    @NotNull
    List<ULong> fromShort(short s3);

    @NotNull
    /* renamed from: fromUByte-7apg3OU  reason: not valid java name */
    List<ULong> m8423fromUByte7apg3OU(byte b3);

    @NotNull
    /* renamed from: fromUByteArray-GBYM_sE  reason: not valid java name */
    List<ULong> m8424fromUByteArrayGBYM_sE(@NotNull byte[] bArr);

    @NotNull
    /* renamed from: fromUInt-WZ4Q5Ns  reason: not valid java name */
    List<ULong> m8425fromUIntWZ4Q5Ns(int i3);

    @NotNull
    /* renamed from: fromULong-VKZWuLQ  reason: not valid java name */
    List<ULong> m8426fromULongVKZWuLQ(long j2);

    @NotNull
    /* renamed from: fromUShort-xj2QHRw  reason: not valid java name */
    List<ULong> m8427fromUShortxj2QHRw(short s3);

    @NotNull
    List<ULong> gcd(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    int getBasePowerOfTwo();

    @NotNull
    List<ULong> getONE();

    @NotNull
    List<ULong> getTEN();

    @NotNull
    List<ULong> getTWO();

    @NotNull
    List<ULong> getZERO();

    @NotNull
    List<ULong> multiply(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    @NotNull
    List<ULong> not(@NotNull List<ULong> list);

    long numberOfDecimalDigits(@NotNull List<ULong> list);

    /* renamed from: numberOfLeadingZerosInAWord-VKZWuLQ  reason: not valid java name */
    int m8428numberOfLeadingZerosInAWordVKZWuLQ(long j2);

    @NotNull
    List<ULong> or(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    @NotNull
    List<ULong> parseForBase(@NotNull String str, int i3);

    @NotNull
    List<ULong> pow(@NotNull List<ULong> list, long j2);

    @NotNull
    Pair<List<ULong>, List<ULong>> reciprocal(@NotNull List<ULong> list);

    @NotNull
    List<ULong> setBitAt(@NotNull List<ULong> list, long j2, boolean z2);

    @NotNull
    List<ULong> shiftLeft(@NotNull List<ULong> list, int i3);

    @NotNull
    List<ULong> shiftRight(@NotNull List<ULong> list, int i3);

    @NotNull
    Pair<List<ULong>, List<ULong>> sqrt(@NotNull List<ULong> list);

    @NotNull
    List<ULong> subtract(@NotNull List<ULong> list, @NotNull List<ULong> list2);

    @NotNull
    byte[] toByteArray(@NotNull List<ULong> list);

    @NotNull
    String toString(@NotNull List<ULong> list, int i3);

    @NotNull
    /* renamed from: toUByteArray-NTtOWj4  reason: not valid java name */
    byte[] m8429toUByteArrayNTtOWj4(@NotNull List<ULong> list);

    int trailingZeroBits(@NotNull List<ULong> list);

    @NotNull
    List<ULong> xor(@NotNull List<ULong> list, @NotNull List<ULong> list2);
}
