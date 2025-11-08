package com.ionspin.kotlin.bignum.integer;

import com.ionspin.kotlin.bignum.Endianness;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UByte;
import kotlin.UIntArray;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J%\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0017J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!J\u001d\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J%\u0010&\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(J1\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010,J \u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020/H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b0\u00101J\u001f\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002030*2\u0006\u00104\u001a\u000205H&ø\u0001\u0000J \u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b8\u00109J \u0010:\u001a\u00020\u00032\u0006\u0010;\u001a\u00020\u001fH&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b<\u0010=J \u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020@H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bA\u0010BJ\u001d\u0010C\u001a\u00020\u00032\u0006\u0010D\u001a\u00020EH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u00101J)\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002030*2\u0006\u00104\u001a\u00020HH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bI\u0010JJ\u001d\u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020MH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bN\u00109J\u001d\u0010O\u001a\u00020\u00032\u0006\u0010P\u001a\u00020QH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bR\u0010=J\u001d\u0010S\u001a\u00020\u00032\u0006\u0010T\u001a\u00020UH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bV\u0010BJ%\u0010W\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bX\u0010\u0017J%\u0010Y\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bZ\u0010\u0017J\u001d\u0010[\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\\\u0010]J\u001d\u0010^\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b_\u0010`J\u001d\u0010a\u001a\u00020\u00102\u0006\u0010#\u001a\u00020MH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bb\u0010cJ%\u0010d\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\be\u0010\u0017J(\u0010f\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bj\u0010kJ%\u0010l\u001a\u00020\u00032\u0006\u0010i\u001a\u00020\u00032\u0006\u0010m\u001a\u00020\u001fH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bn\u0010oJ)\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bq\u0010rJ-\u0010s\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010t\u001a\u00020\u001dH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bu\u0010vJ%\u0010w\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010x\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\by\u0010zJ%\u0010{\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010x\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b|\u0010zJ)\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b~\u0010rJ&\u0010\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010\u0017J \u0010\u0001\u001a\u0002052\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J(\u0010\u0001\u001a\u00020h2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010i\u001a\u00020\u0010H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J \u0010\u0001\u001a\u00020H2\u0006\u0010\u0019\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J3\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020E0\u00012\u0006\u0010\u0019\u001a\u00020\u00032\n\b\u0002\u0010\u0001\u001a\u00030\u0001H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J,\u0010\u0001\u001a\u00020H2\u0006\u0010\u0019\u001a\u00020\u00032\n\b\u0002\u0010\u0001\u001a\u00030\u0001H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u001f\u0010\u0001\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010%J'\u0010\u0001\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010\u0017R\u001b\u0010\u0002\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001b\u0010\u0006\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001b\u0010\b\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u001b\u0010\n\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0005R\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0001"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigInteger32ArithmeticInterface;", "", "ONE", "Lkotlin/UIntArray;", "getONE--hP7Qyg", "()[I", "TEN", "getTEN--hP7Qyg", "TWO", "getTWO--hP7Qyg", "ZERO", "getZERO--hP7Qyg", "_emitIntArray", "", "get_emitIntArray", "basePowerOfTwo", "", "getBasePowerOfTwo", "()I", "add", "first", "second", "add-0-0sMy4", "([I[I)[I", "and", "operand", "mask", "and-0-0sMy4", "bitAt", "", "position", "", "bitAt-LpG4sQ0", "([IJ)Z", "bitLength", "value", "bitLength--ajY-9A", "([I)I", "compare", "compare-Ynv0uTE", "([I[I)I", "divide", "Lkotlin/Pair;", "divide-Ynv0uTE", "([I[I)Lkotlin/Pair;", "fromByte", "byte", "", "fromByte-g_c56RQ", "(B)[I", "fromByteArray", "Lcom/ionspin/kotlin/bignum/integer/Sign;", "source", "", "fromInt", "int", "fromInt-g_c56RQ", "(I)[I", "fromLong", "long", "fromLong-g_c56RQ", "(J)[I", "fromShort", "short", "", "fromShort-g_c56RQ", "(S)[I", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-W6sApTE", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-GBYM_sE", "([B)Lkotlin/Pair;", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-Ezf8eIQ", "fromULong", "uLong", "Lkotlin/ULong;", "fromULong-owt3UmA", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-y3OBVxU", "gcd", "gcd-0-0sMy4", "multiply", "multiply-0-0sMy4", "not", "not-hkIa6DI", "([I)[I", "numberOfDecimalDigits", "numberOfDecimalDigits--ajY-9A", "([I)J", "numberOfLeadingZerosInAWord", "numberOfLeadingZerosInAWord-WZ4Q5Ns", "(I)I", "or", "or-0-0sMy4", "parseForBase", "number", "", "base", "parseForBase-g-PCqec", "(Ljava/lang/String;I)[I", "pow", "exponent", "pow-Wj2uyrI", "([IJ)[I", "reciprocal", "reciprocal--ajY-9A", "([I)Lkotlin/Pair;", "setBitAt", "bit", "setBitAt-WiAKJ7k", "([IJZ)[I", "shiftLeft", "places", "shiftLeft-Wj2uyrI", "([II)[I", "shiftRight", "shiftRight-Wj2uyrI", "sqrt", "sqrt--ajY-9A", "subtract", "subtract-0-0sMy4", "toByteArray", "toByteArray--ajY-9A", "([I)[B", "toString", "toString-LpG4sQ0", "([II)Ljava/lang/String;", "toUByteArray", "toUByteArray-CMMTdXw", "toUIntArrayRepresentedAsTypedUByteArray", "", "endianness", "Lcom/ionspin/kotlin/bignum/Endianness;", "toUIntArrayRepresentedAsTypedUByteArray-LpG4sQ0", "([ILcom/ionspin/kotlin/bignum/Endianness;)[Lkotlin/UByte;", "toUIntArrayRepresentedAsUByteArray", "toUIntArrayRepresentedAsUByteArray-1NjfPbc", "([ILcom/ionspin/kotlin/bignum/Endianness;)[B", "trailingZeroBits", "trailingZeroBits--ajY-9A", "xor", "xor-0-0sMy4", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface BigInteger32ArithmeticInterface {

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        /* renamed from: toUIntArrayRepresentedAsTypedUByteArray-LpG4sQ0$default  reason: not valid java name */
        public static /* synthetic */ UByte[] m8358toUIntArrayRepresentedAsTypedUByteArrayLpG4sQ0$default(BigInteger32ArithmeticInterface bigInteger32ArithmeticInterface, int[] iArr, Endianness endianness, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 2) != 0) {
                    endianness = Endianness.BIG;
                }
                return bigInteger32ArithmeticInterface.m8354toUIntArrayRepresentedAsTypedUByteArrayLpG4sQ0(iArr, endianness);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toUIntArrayRepresentedAsTypedUByteArray-LpG4sQ0");
        }

        /* renamed from: toUIntArrayRepresentedAsUByteArray-1NjfPbc$default  reason: not valid java name */
        public static /* synthetic */ byte[] m8359toUIntArrayRepresentedAsUByteArray1NjfPbc$default(BigInteger32ArithmeticInterface bigInteger32ArithmeticInterface, int[] iArr, Endianness endianness, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 2) != 0) {
                    endianness = Endianness.BIG;
                }
                return bigInteger32ArithmeticInterface.m8355toUIntArrayRepresentedAsUByteArray1NjfPbc(iArr, endianness);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toUIntArrayRepresentedAsUByteArray-1NjfPbc");
        }
    }

    @NotNull
    /* renamed from: add-0-0sMy4  reason: not valid java name */
    int[] m8318add00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2);

    @NotNull
    /* renamed from: and-0-0sMy4  reason: not valid java name */
    int[] m8319and00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2);

    /* renamed from: bitAt-LpG4sQ0  reason: not valid java name */
    boolean m8320bitAtLpG4sQ0(@NotNull int[] iArr, long j2);

    /* renamed from: bitLength--ajY-9A  reason: not valid java name */
    int m8321bitLengthajY9A(@NotNull int[] iArr);

    /* renamed from: compare-Ynv0uTE  reason: not valid java name */
    int m8322compareYnv0uTE(@NotNull int[] iArr, @NotNull int[] iArr2);

    @NotNull
    /* renamed from: divide-Ynv0uTE  reason: not valid java name */
    Pair<UIntArray, UIntArray> m8323divideYnv0uTE(@NotNull int[] iArr, @NotNull int[] iArr2);

    @NotNull
    /* renamed from: fromByte-g_c56RQ  reason: not valid java name */
    int[] m8324fromByteg_c56RQ(byte b3);

    @NotNull
    Pair<UIntArray, Sign> fromByteArray(@NotNull byte[] bArr);

    @NotNull
    /* renamed from: fromInt-g_c56RQ  reason: not valid java name */
    int[] m8325fromIntg_c56RQ(int i3);

    @NotNull
    /* renamed from: fromLong-g_c56RQ  reason: not valid java name */
    int[] m8326fromLongg_c56RQ(long j2);

    @NotNull
    /* renamed from: fromShort-g_c56RQ  reason: not valid java name */
    int[] m8327fromShortg_c56RQ(short s3);

    @NotNull
    /* renamed from: fromUByte-W6sApTE  reason: not valid java name */
    int[] m8328fromUByteW6sApTE(byte b3);

    @NotNull
    /* renamed from: fromUByteArray-GBYM_sE  reason: not valid java name */
    Pair<UIntArray, Sign> m8329fromUByteArrayGBYM_sE(@NotNull byte[] bArr);

    @NotNull
    /* renamed from: fromUInt-Ezf8eIQ  reason: not valid java name */
    int[] m8330fromUIntEzf8eIQ(int i3);

    @NotNull
    /* renamed from: fromULong-owt3UmA  reason: not valid java name */
    int[] m8331fromULongowt3UmA(long j2);

    @NotNull
    /* renamed from: fromUShort-y3OBVxU  reason: not valid java name */
    int[] m8332fromUShorty3OBVxU(short s3);

    @NotNull
    /* renamed from: gcd-0-0sMy4  reason: not valid java name */
    int[] m8333gcd00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2);

    int getBasePowerOfTwo();

    @NotNull
    /* renamed from: getONE--hP7Qyg  reason: not valid java name */
    int[] m8334getONEhP7Qyg();

    @NotNull
    /* renamed from: getTEN--hP7Qyg  reason: not valid java name */
    int[] m8335getTENhP7Qyg();

    @NotNull
    /* renamed from: getTWO--hP7Qyg  reason: not valid java name */
    int[] m8336getTWOhP7Qyg();

    @NotNull
    /* renamed from: getZERO--hP7Qyg  reason: not valid java name */
    int[] m8337getZEROhP7Qyg();

    @NotNull
    int[] get_emitIntArray();

    @NotNull
    /* renamed from: multiply-0-0sMy4  reason: not valid java name */
    int[] m8338multiply00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2);

    @NotNull
    /* renamed from: not-hkIa6DI  reason: not valid java name */
    int[] m8339nothkIa6DI(@NotNull int[] iArr);

    /* renamed from: numberOfDecimalDigits--ajY-9A  reason: not valid java name */
    long m8340numberOfDecimalDigitsajY9A(@NotNull int[] iArr);

    /* renamed from: numberOfLeadingZerosInAWord-WZ4Q5Ns  reason: not valid java name */
    int m8341numberOfLeadingZerosInAWordWZ4Q5Ns(int i3);

    @NotNull
    /* renamed from: or-0-0sMy4  reason: not valid java name */
    int[] m8342or00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2);

    @NotNull
    /* renamed from: parseForBase-g-PCqec  reason: not valid java name */
    int[] m8343parseForBasegPCqec(@NotNull String str, int i3);

    @NotNull
    /* renamed from: pow-Wj2uyrI  reason: not valid java name */
    int[] m8344powWj2uyrI(@NotNull int[] iArr, long j2);

    @NotNull
    /* renamed from: reciprocal--ajY-9A  reason: not valid java name */
    Pair<UIntArray, UIntArray> m8345reciprocalajY9A(@NotNull int[] iArr);

    @NotNull
    /* renamed from: setBitAt-WiAKJ7k  reason: not valid java name */
    int[] m8346setBitAtWiAKJ7k(@NotNull int[] iArr, long j2, boolean z2);

    @NotNull
    /* renamed from: shiftLeft-Wj2uyrI  reason: not valid java name */
    int[] m8347shiftLeftWj2uyrI(@NotNull int[] iArr, int i3);

    @NotNull
    /* renamed from: shiftRight-Wj2uyrI  reason: not valid java name */
    int[] m8348shiftRightWj2uyrI(@NotNull int[] iArr, int i3);

    @NotNull
    /* renamed from: sqrt--ajY-9A  reason: not valid java name */
    Pair<UIntArray, UIntArray> m8349sqrtajY9A(@NotNull int[] iArr);

    @NotNull
    /* renamed from: subtract-0-0sMy4  reason: not valid java name */
    int[] m8350subtract00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2);

    @NotNull
    /* renamed from: toByteArray--ajY-9A  reason: not valid java name */
    byte[] m8351toByteArrayajY9A(@NotNull int[] iArr);

    @NotNull
    /* renamed from: toString-LpG4sQ0  reason: not valid java name */
    String m8352toStringLpG4sQ0(@NotNull int[] iArr, int i3);

    @NotNull
    /* renamed from: toUByteArray-CMMTdXw  reason: not valid java name */
    byte[] m8353toUByteArrayCMMTdXw(@NotNull int[] iArr);

    @NotNull
    /* renamed from: toUIntArrayRepresentedAsTypedUByteArray-LpG4sQ0  reason: not valid java name */
    UByte[] m8354toUIntArrayRepresentedAsTypedUByteArrayLpG4sQ0(@NotNull int[] iArr, @NotNull Endianness endianness);

    @NotNull
    /* renamed from: toUIntArrayRepresentedAsUByteArray-1NjfPbc  reason: not valid java name */
    byte[] m8355toUIntArrayRepresentedAsUByteArray1NjfPbc(@NotNull int[] iArr, @NotNull Endianness endianness);

    /* renamed from: trailingZeroBits--ajY-9A  reason: not valid java name */
    int m8356trailingZeroBitsajY9A(@NotNull int[] iArr);

    @NotNull
    /* renamed from: xor-0-0sMy4  reason: not valid java name */
    int[] m8357xor00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2);
}
