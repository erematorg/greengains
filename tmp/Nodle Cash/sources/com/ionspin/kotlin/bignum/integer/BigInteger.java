package com.ionspin.kotlin.bignum.integer;

import androidx.camera.camera2.internal.C0118y;
import androidx.exifinterface.media.ExifInterface;
import com.ionspin.kotlin.bignum.BigNumber;
import com.ionspin.kotlin.bignum.BitwiseCapable;
import com.ionspin.kotlin.bignum.ByteArrayDeserializable;
import com.ionspin.kotlin.bignum.ByteArraySerializable;
import com.ionspin.kotlin.bignum.CommonBigNumberOperations;
import com.ionspin.kotlin.bignum.NarrowingOperations;
import com.ionspin.kotlin.bignum.decimal.BigDecimal;
import com.ionspin.kotlin.bignum.decimal.DecimalMode;
import com.ionspin.kotlin.bignum.integer.base63.array.BigInteger63Arithmetic;
import com.ionspin.kotlin.bignum.modular.ModularBigInteger;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.ClosedRange;
import kotlin.reflect.KClass;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0004\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00012\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\b\u0012\u0004\u0012\u00020\u00000\u00042\b\u0012\u0004\u0012\u00020\u00060\u00052\u00020\u0007:\n\u0001\u0001\u0001\u0001\u0001B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u000f\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB\u000f\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010B\u000f\b\u0016\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013B\u001e\b\u0000\u0012\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\b\u0010*\u001a\u00020\u0000H\u0016J\u0010\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0016J\u0011\u0010-\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0004J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\tH\u0016J\u0010\u00101\u001a\u00020\u00122\u0006\u00102\u001a\u00020/H\u0016J\u000e\u00103\u001a\u00020\f2\u0006\u0010,\u001a\u00020\u0000J\"\u00104\u001a\u00020\f2\u0006\u00105\u001a\u0002062\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\f08J\"\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020;2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\f08J\u0011\u0010<\u001a\u00020\f2\u0006\u0010,\u001a\u00020\u0006H\u0002J\b\u0010=\u001a\u00020\u0000H\u0002J\t\u0010>\u001a\u00020\u0000H\u0002J\u0010\u0010?\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0016J\u001c\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000A2\u0006\u0010,\u001a\u00020\u0000H\u0016J\u0011\u0010B\u001a\u00020C2\u0006\u0010,\u001a\u00020\u0000H\u0004J\u0010\u0010D\u001a\u0002062\u0006\u00102\u001a\u00020/H\u0016J\u0013\u0010E\u001a\u00020/2\b\u0010,\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010F\u001a\u00020;2\u0006\u00102\u001a\u00020/H\u0016J\u000e\u0010G\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000J\u000e\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000IH\u0016J\b\u0010J\u001a\u00020\u0000H\u0016J\b\u0010K\u001a\u00020\fH\u0016J\t\u0010L\u001a\u00020\u0000H\u0002J\u0010\u0010M\u001a\u00020\f2\u0006\u00102\u001a\u00020/H\u0016J!\u0010N\u001a\u00020/2\n\u0010O\u001a\u00060\u0015j\u0002`\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bP\u0010QJ\b\u0010R\u001a\u00020/H\u0016J\u0010\u0010S\u001a\u00020\f2\u0006\u0010T\u001a\u00020UH\u0002J\u0010\u0010V\u001a\u00020\t2\u0006\u00102\u001a\u00020/H\u0016J\u0011\u0010W\u001a\u00020\u00002\u0006\u0010X\u001a\u00020\u0000H\u0004J\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010X\u001a\u00020\u0000J\u0010\u0010Z\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0016J\u0010\u0010[\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0002J\b\u0010\\\u001a\u00020\u0000H\u0016J\b\u0010]\u001a\u00020\u0000H\u0016J\b\u0010^\u001a\u00020\tH\u0016J\u0011\u0010_\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0004J\u000e\u0010`\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u0000J\u0010\u0010`\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\fH\u0016J\u0010\u0010`\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\tH\u0016J\u0011\u0010b\u001a\u00020c2\u0006\u0010,\u001a\u00020\u0000H\u0002J\u0010\u0010d\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0016J\b\u0010e\u001a\u00020fH\u0016J\u0018\u0010g\u001a\u00020\u00002\u0006\u00100\u001a\u00020\t2\u0006\u0010h\u001a\u00020/H\u0016J\u0011\u0010i\u001a\u00020\u00002\u0006\u0010j\u001a\u00020\fH\u0004J\u0010\u0010k\u001a\u00020\u000f2\u0006\u00102\u001a\u00020/H\u0016J\u0011\u0010l\u001a\u00020\u00002\u0006\u0010j\u001a\u00020\fH\u0004J\b\u0010m\u001a\u00020\fH\u0016J\u0006\u0010n\u001a\u00020\u0000J\u0006\u0010o\u001a\u00020pJ\u0010\u0010q\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0016J\u0011\u0010r\u001a\u00020%2\u0006\u0010s\u001a\u00020tH\u0002J\b\u0010u\u001a\u00020vH\u0016J\u000e\u0010w\u001a\u00020x2\u0006\u0010X\u001a\u00020\u0000J\b\u0010y\u001a\u00020%H\u0016J\u0010\u0010y\u001a\u00020%2\u0006\u0010z\u001a\u00020\fH\u0016J\u0015\u0010{\u001a\u00020%2\u0006\u0010z\u001a\u00020\fH\u0000¢\u0006\u0002\b|J\u0019\u0010}\u001a\u00020~H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0005\b\u0010\u0001J$\u0010\u0001\u001a\u00030\u00012\u0006\u00102\u001a\u00020/H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J$\u0010\u0001\u001a\u00030\u00012\u0006\u00102\u001a\u00020/H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J$\u0010\u0001\u001a\u00030\u00012\u0006\u00102\u001a\u00020/H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\n\u0010\u0001\u001a\u00020\u0000H\u0002J$\u0010\u0001\u001a\u00030\u00012\u0006\u00102\u001a\u00020/H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u0012\u0010\u0001\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0000H\u0004R#\u0010\u001a\u001a\u00060\u0015j\u0002`\u0016X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001e\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0001"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "Lcom/ionspin/kotlin/bignum/BigNumber;", "Lcom/ionspin/kotlin/bignum/CommonBigNumberOperations;", "Lcom/ionspin/kotlin/bignum/NarrowingOperations;", "Lcom/ionspin/kotlin/bignum/BitwiseCapable;", "", "", "Lcom/ionspin/kotlin/bignum/ByteArraySerializable;", "long", "", "(J)V", "int", "", "(I)V", "short", "", "(S)V", "byte", "", "(B)V", "wordArray", "Lkotlin/ULongArray;", "Lcom/ionspin/kotlin/bignum/integer/WordArray;", "requestedSign", "Lcom/ionspin/kotlin/bignum/integer/Sign;", "([JLcom/ionspin/kotlin/bignum/integer/Sign;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "magnitude", "getMagnitude-Y2RjT0g$bignum", "()[J", "[J", "numberOfWords", "getNumberOfWords", "()I", "sign", "getSign$bignum", "()Lcom/ionspin/kotlin/bignum/integer/Sign;", "stringRepresentation", "", "getStringRepresentation", "()Ljava/lang/String;", "setStringRepresentation", "(Ljava/lang/String;)V", "abs", "add", "other", "and", "bitAt", "", "position", "byteValue", "exactRequired", "compare", "compareDoubleAndBigInt", "double", "", "comparisonBlock", "Lkotlin/Function1;", "compareFloatAndBigInt", "float", "", "compareTo", "d1reciprocalRecursive", "dec", "divide", "divideAndRemainder", "Lkotlin/Pair;", "divrem", "Lcom/ionspin/kotlin/bignum/integer/BigInteger$QuotientAndRemainder;", "doubleValue", "equals", "floatValue", "gcd", "getCreator", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "getInstance", "hashCode", "inc", "intValue", "isResultZero", "resultMagnitude", "isResultZero-QwZRm1k", "([J)Z", "isZero", "javascriptNumberComparison", "number", "", "longValue", "mod", "modulo", "modInverse", "multiply", "naiveGcd", "negate", "not", "numberOfDecimalDigits", "or", "pow", "exponent", "rangeTo", "Lcom/ionspin/kotlin/bignum/integer/BigInteger$BigIntegerRange;", "remainder", "secureOverwrite", "", "setBitAt", "bit", "shl", "places", "shortValue", "shr", "signum", "sqrt", "sqrtAndRemainder", "Lcom/ionspin/kotlin/bignum/integer/BigInteger$SqareRootAndRemainder;", "subtract", "times", "char", "", "toByteArray", "", "toModularBigInteger", "Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "toString", "base", "toStringWithoutSign", "toStringWithoutSign$bignum", "toUByteArray", "Lkotlin/UByteArray;", "toUByteArray-TcUX1vc", "()[B", "ubyteValue", "Lkotlin/UByte;", "ubyteValue-Wa3L5BU", "(Z)B", "uintValue", "Lkotlin/UInt;", "uintValue-OGnWXxg", "(Z)I", "ulongValue", "Lkotlin/ULong;", "ulongValue-I7RO_PI", "(Z)J", "unaryMinus", "ushortValue", "Lkotlin/UShort;", "ushortValue-BwKQO78", "(Z)S", "xor", "BigIntegerIterator", "BigIntegerRange", "Companion", "QuotientAndRemainder", "SqareRootAndRemainder", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BigInteger implements BigNumber<BigInteger>, CommonBigNumberOperations<BigInteger>, NarrowingOperations<BigInteger>, BitwiseCapable<BigInteger>, Comparable<Object>, ByteArraySerializable {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final double LOG_10_OF_2 = Math.log10(2.0d);
    /* access modifiers changed from: private */
    @NotNull
    public static final BigInteger ONE;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigInteger TEN;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigInteger TWO;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigInteger ZERO;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigIntegerArithmetic arithmetic;
    @NotNull
    private final long[] magnitude;
    private final int numberOfWords;
    @NotNull
    private final Sign sign;
    @Nullable
    private String stringRepresentation;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\t\u0010\u0007\u001a\u00020\bH\u0002J\t\u0010\t\u001a\u00020\u0002H\u0002R\u000e\u0010\u0006\u001a\u00020\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigInteger$BigIntegerIterator;", "", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "start", "endInclusive", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;Lcom/ionspin/kotlin/bignum/integer/BigInteger;)V", "current", "hasNext", "", "next", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class BigIntegerIterator implements Iterator<BigInteger>, KMappedMarker {
        @NotNull
        private BigInteger current;
        @NotNull
        private final BigInteger endInclusive;

        public BigIntegerIterator(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
            Intrinsics.checkNotNullParameter(bigInteger, TtmlNode.START);
            Intrinsics.checkNotNullParameter(bigInteger2, "endInclusive");
            this.endInclusive = bigInteger2;
            this.current = bigInteger;
        }

        public boolean hasNext() {
            return this.current.compareTo(this.endInclusive) <= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @NotNull
        public BigInteger next() {
            BigInteger bigInteger = this.current;
            this.current = bigInteger.inc();
            return bigInteger;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0007\n\u0002\u0010(\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0002R\u0014\u0010\u0005\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\f"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigInteger$BigIntegerRange;", "Lkotlin/ranges/ClosedRange;", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "", "start", "endInclusive", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;Lcom/ionspin/kotlin/bignum/integer/BigInteger;)V", "getEndInclusive", "()Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "getStart", "iterator", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class BigIntegerRange implements ClosedRange<BigInteger>, Iterable<BigInteger>, KMappedMarker {
        @NotNull
        private final BigInteger endInclusive;
        @NotNull
        private final BigInteger start;

        public BigIntegerRange(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
            Intrinsics.checkNotNullParameter(bigInteger, TtmlNode.START);
            Intrinsics.checkNotNullParameter(bigInteger2, "endInclusive");
            this.start = bigInteger;
            this.endInclusive = bigInteger2;
        }

        public boolean contains(@NotNull BigInteger bigInteger) {
            return ClosedRange.DefaultImpls.contains(this, bigInteger);
        }

        @NotNull
        public BigInteger getEndInclusive() {
            return this.endInclusive;
        }

        @NotNull
        public BigInteger getStart() {
            return this.start;
        }

        public boolean isEmpty() {
            return ClosedRange.DefaultImpls.isEmpty(this);
        }

        @NotNull
        public Iterator<BigInteger> iterator() {
            return new BigIntegerIterator(getStart(), getEndInclusive());
        }
    }

    @Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u00032\b\u0012\u0004\u0012\u00020\u00020\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u001f\u0010\u0015\u001a\u00020\u0016\"\u0006\b\u0000\u0010\u0017\u0018\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\bJ\u0010\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0016J\u0010\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0016H\u0016J\u0010\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+H\u0016J\u001d\u0010,\u001a\u00020\u00022\u0006\u0010-\u001a\u00020.H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u00100J%\u00101\u001a\u00020\u00022\u0006\u0010 \u001a\u0002022\u0006\u0010\"\u001a\u00020\u0016H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u00104J\u001d\u00105\u001a\u00020\u00022\u0006\u00106\u001a\u000207H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b8\u00109J\u001d\u0010:\u001a\u00020\u00022\u0006\u0010;\u001a\u00020<H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b=\u0010>J\u001d\u0010?\u001a\u00020\u00022\u0006\u0010@\u001a\u00020AH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bB\u0010CJ)\u0010D\u001a\u00020\u00022\n\u0010E\u001a\u00060Fj\u0002`G2\u0006\u0010\"\u001a\u00020\u0016H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bH\u0010IJ\u0018\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010L\u001a\u00020\u0002H\u0016J\u0018\u0010M\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010L\u001a\u00020\u0002H\u0016J\u0018\u0010N\u001a\u00020\u00022\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020%H\u0016J\u0018\u0010R\u001a\u00020\u00022\u0006\u0010S\u001a\u00020\u00072\u0006\u0010T\u001a\u00020UH\u0016J\u0018\u0010V\u001a\u00020\u00022\u0006\u0010W\u001a\u00020X2\u0006\u0010T\u001a\u00020UH\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006Y"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigInteger$Companion;", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "Lcom/ionspin/kotlin/bignum/BigNumber$Util;", "Lcom/ionspin/kotlin/bignum/ByteArrayDeserializable;", "()V", "LOG_10_OF_2", "", "getLOG_10_OF_2", "()D", "ONE", "getONE", "()Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "TEN", "getTEN", "TWO", "getTWO", "ZERO", "getZERO", "arithmetic", "Lcom/ionspin/kotlin/bignum/integer/BigIntegerArithmetic;", "determinSignFromNumber", "Lcom/ionspin/kotlin/bignum/integer/Sign;", "T", "number", "", "fromBigInteger", "bigInteger", "fromByte", "byte", "", "fromByteArray", "source", "", "sign", "fromInt", "int", "", "fromLong", "long", "", "fromShort", "short", "", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-7apg3OU", "(B)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-rto03Yo", "([BLcom/ionspin/kotlin/bignum/integer/Sign;)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-WZ4Q5Ns", "(I)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "fromULong", "uLong", "Lkotlin/ULong;", "fromULong-VKZWuLQ", "(J)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-xj2QHRw", "(S)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "fromWordArray", "wordArray", "Lkotlin/ULongArray;", "Lcom/ionspin/kotlin/bignum/integer/WordArray;", "fromWordArray-tBf0fek$bignum", "([JLcom/ionspin/kotlin/bignum/integer/Sign;)Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "max", "first", "second", "min", "parseString", "string", "", "base", "tryFromDouble", "double", "exactRequired", "", "tryFromFloat", "float", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion implements BigNumber.Creator<BigInteger>, BigNumber.Util<BigInteger>, ByteArrayDeserializable<BigInteger> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final /* synthetic */ Sign determinSignFromNumber(Comparable comparable) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Class<Object> cls = Object.class;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(cls);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                Long l2 = (Long) comparable;
                Number number = (Number) comparable;
                return number.longValue() < 0 ? Sign.NEGATIVE : number.longValue() > 0 ? Sign.POSITIVE : Sign.ZERO;
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                Integer num = (Integer) comparable;
                Number number2 = (Number) comparable;
                return number2.intValue() < 0 ? Sign.NEGATIVE : number2.intValue() > 0 ? Sign.POSITIVE : Sign.ZERO;
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Short.TYPE))) {
                Short sh = (Short) comparable;
                Number number3 = (Number) comparable;
                return number3.shortValue() < 0 ? Sign.NEGATIVE : number3.shortValue() > 0 ? Sign.POSITIVE : Sign.ZERO;
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Byte.TYPE))) {
                Byte b3 = (Byte) comparable;
                Number number4 = (Number) comparable;
                return number4.byteValue() < 0 ? Sign.NEGATIVE : number4.byteValue() > 0 ? Sign.POSITIVE : Sign.ZERO;
            } else {
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                throw new RuntimeException(Intrinsics.stringPlus("Unsupported type ", Reflection.getOrCreateKotlinClass(cls)));
            }
        }

        @NotNull
        public BigInteger fromBigInteger(@NotNull BigInteger bigInteger) {
            Intrinsics.checkNotNullParameter(bigInteger, "bigInteger");
            return bigInteger;
        }

        @NotNull
        /* renamed from: fromWordArray-tBf0fek$bignum  reason: not valid java name */
        public final BigInteger m8317fromWordArraytBf0fek$bignum(@NotNull long[] jArr, @NotNull Sign sign) {
            Intrinsics.checkNotNullParameter(jArr, "wordArray");
            Intrinsics.checkNotNullParameter(sign, "sign");
            return new BigInteger(jArr, sign, (DefaultConstructorMarker) null);
        }

        public final double getLOG_10_OF_2() {
            return BigInteger.LOG_10_OF_2;
        }

        private Companion() {
        }

        @NotNull
        public BigInteger fromByte(byte b3) {
            return new BigInteger(b3);
        }

        @NotNull
        public BigInteger fromByteArray(@NotNull byte[] bArr, @NotNull Sign sign) {
            Intrinsics.checkNotNullParameter(bArr, "source");
            Intrinsics.checkNotNullParameter(sign, "sign");
            return new BigInteger(BigInteger.arithmetic.m8367fromByteArrayDHQ6RzY(bArr), sign, (DefaultConstructorMarker) null);
        }

        @NotNull
        public BigInteger fromInt(int i3) {
            return new BigInteger(i3);
        }

        @NotNull
        public BigInteger fromLong(long j2) {
            return new BigInteger(j2);
        }

        @NotNull
        public BigInteger fromShort(short s3) {
            return new BigInteger(s3);
        }

        @NotNull
        /* renamed from: fromUByte-7apg3OU  reason: not valid java name */
        public BigInteger m8308fromUByte7apg3OU(byte b3) {
            return new BigInteger(BigInteger.arithmetic.m8371fromUByteab45Ak8(b3), Sign.POSITIVE, (DefaultConstructorMarker) null);
        }

        @NotNull
        /* renamed from: fromUByteArray-rto03Yo  reason: not valid java name */
        public BigInteger m8310fromUByteArrayrto03Yo(@NotNull byte[] bArr, @NotNull Sign sign) {
            Intrinsics.checkNotNullParameter(bArr, "source");
            Intrinsics.checkNotNullParameter(sign, "sign");
            return new BigInteger(BigInteger.arithmetic.m8372fromUByteArrayS4JqeA(bArr), sign, (DefaultConstructorMarker) null);
        }

        @NotNull
        /* renamed from: fromUInt-WZ4Q5Ns  reason: not valid java name */
        public BigInteger m8312fromUIntWZ4Q5Ns(int i3) {
            return new BigInteger(BigInteger.arithmetic.m8373fromUIntkOc6_GI(i3), Sign.POSITIVE, (DefaultConstructorMarker) null);
        }

        @NotNull
        /* renamed from: fromULong-VKZWuLQ  reason: not valid java name */
        public BigInteger m8314fromULongVKZWuLQ(long j2) {
            return new BigInteger(BigInteger.arithmetic.m8374fromULongGCcj4Q(j2), Sign.POSITIVE, (DefaultConstructorMarker) null);
        }

        @NotNull
        /* renamed from: fromUShort-xj2QHRw  reason: not valid java name */
        public BigInteger m8316fromUShortxj2QHRw(short s3) {
            return new BigInteger(BigInteger.arithmetic.m8375fromUShortjOPi9CM(s3), Sign.POSITIVE, (DefaultConstructorMarker) null);
        }

        @NotNull
        public BigInteger getONE() {
            return BigInteger.ONE;
        }

        @NotNull
        public BigInteger getTEN() {
            return BigInteger.TEN;
        }

        @NotNull
        public BigInteger getTWO() {
            return BigInteger.TWO;
        }

        @NotNull
        public BigInteger getZERO() {
            return BigInteger.ZERO;
        }

        @NotNull
        public BigInteger max(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
            Intrinsics.checkNotNullParameter(bigInteger, "first");
            Intrinsics.checkNotNullParameter(bigInteger2, "second");
            return bigInteger.compareTo(bigInteger2) > 0 ? bigInteger : bigInteger2;
        }

        @NotNull
        public BigInteger min(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
            Intrinsics.checkNotNullParameter(bigInteger, "first");
            Intrinsics.checkNotNullParameter(bigInteger2, "second");
            return bigInteger.compareTo(bigInteger2) < 0 ? bigInteger : bigInteger2;
        }

        @NotNull
        public BigInteger parseString(@NotNull String str, int i3) {
            Sign sign;
            Intrinsics.checkNotNullParameter(str, "string");
            if (i3 < 2 || i3 > 36) {
                throw new NumberFormatException(C0118y.c(i3, "Unsupported base: ", ". Supported base range is from 2 to 36"));
            } else if (StringsKt__StringsKt.contains$default((CharSequence) str, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR, false, 2, (Object) null)) {
                BigDecimal parseString = BigDecimal.Companion.parseString(str);
                if (parseString.minus(parseString.floor()).compareTo(0) <= 0) {
                    return parseString.toBigInteger();
                }
                throw new NumberFormatException("Supplied string is decimal, which cannot be converted to BigInteger without precision loss.");
            } else if (str.charAt(0) == '-' || str.charAt(0) == '+') {
                if (str.length() != 1) {
                    if (str.charAt(0) == '-') {
                        sign = Sign.NEGATIVE;
                    } else {
                        sign = Sign.POSITIVE;
                    }
                    if (str.length() == 2 && str.charAt(1) == '0') {
                        return getZERO();
                    }
                    BigIntegerArithmetic access$getArithmetic$cp = BigInteger.arithmetic;
                    String substring = str.substring(1, str.length());
                    Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    return new BigInteger(access$getArithmetic$cp.m8386parseForBase_llDaS8(substring, i3), sign, (DefaultConstructorMarker) null);
                }
                throw new NumberFormatException(Intrinsics.stringPlus("Invalid big integer: ", str));
            } else if (str.length() == 1 && str.charAt(0) == '0') {
                return getZERO();
            } else {
                return new BigInteger(BigInteger.arithmetic.m8386parseForBase_llDaS8(str, i3), Sign.POSITIVE, (DefaultConstructorMarker) null);
            }
        }

        @NotNull
        public BigInteger tryFromDouble(double d2, boolean z2) {
            double floor = d2 - Math.floor(d2);
            BigDecimal fromDouble = BigDecimal.Companion.fromDouble(Math.floor(d2), (DecimalMode) null);
            if (!z2 || floor <= 0.0d) {
                return fromDouble.toBigInteger();
            }
            throw new ArithmeticException("Cant create BigInteger without precision loss, and exact  value was required");
        }

        @NotNull
        public BigInteger tryFromFloat(float f2, boolean z2) {
            double d2 = (double) f2;
            float floor = f2 - ((float) Math.floor(d2));
            BigDecimal fromFloat = BigDecimal.Companion.fromFloat((float) Math.floor(d2), (DecimalMode) null);
            if (!z2 || floor <= 0.0f) {
                return fromFloat.toBigInteger();
            }
            throw new ArithmeticException("Cant create BigInteger without precision loss, and exact  value was required");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigInteger$QuotientAndRemainder;", "", "quotient", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "remainder", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;Lcom/ionspin/kotlin/bignum/integer/BigInteger;)V", "getQuotient", "()Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "getRemainder", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class QuotientAndRemainder {
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

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/BigInteger$SqareRootAndRemainder;", "", "squareRoot", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "remainder", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;Lcom/ionspin/kotlin/bignum/integer/BigInteger;)V", "getRemainder", "()Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "getSquareRoot", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class SqareRootAndRemainder {
        @NotNull
        private final BigInteger remainder;
        @NotNull
        private final BigInteger squareRoot;

        public SqareRootAndRemainder(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
            Intrinsics.checkNotNullParameter(bigInteger, "squareRoot");
            Intrinsics.checkNotNullParameter(bigInteger2, "remainder");
            this.squareRoot = bigInteger;
            this.remainder = bigInteger2;
        }

        public static /* synthetic */ SqareRootAndRemainder copy$default(SqareRootAndRemainder sqareRootAndRemainder, BigInteger bigInteger, BigInteger bigInteger2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                bigInteger = sqareRootAndRemainder.squareRoot;
            }
            if ((i3 & 2) != 0) {
                bigInteger2 = sqareRootAndRemainder.remainder;
            }
            return sqareRootAndRemainder.copy(bigInteger, bigInteger2);
        }

        @NotNull
        public final BigInteger component1() {
            return this.squareRoot;
        }

        @NotNull
        public final BigInteger component2() {
            return this.remainder;
        }

        @NotNull
        public final SqareRootAndRemainder copy(@NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
            Intrinsics.checkNotNullParameter(bigInteger, "squareRoot");
            Intrinsics.checkNotNullParameter(bigInteger2, "remainder");
            return new SqareRootAndRemainder(bigInteger, bigInteger2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SqareRootAndRemainder)) {
                return false;
            }
            SqareRootAndRemainder sqareRootAndRemainder = (SqareRootAndRemainder) obj;
            return Intrinsics.areEqual((Object) this.squareRoot, (Object) sqareRootAndRemainder.squareRoot) && Intrinsics.areEqual((Object) this.remainder, (Object) sqareRootAndRemainder.remainder);
        }

        @NotNull
        public final BigInteger getRemainder() {
            return this.remainder;
        }

        @NotNull
        public final BigInteger getSquareRoot() {
            return this.squareRoot;
        }

        public int hashCode() {
            return this.remainder.hashCode() + (this.squareRoot.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return "SqareRootAndRemainder(squareRoot=" + this.squareRoot + ", remainder=" + this.remainder + ')';
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

    static {
        BigIntegerArithmetic chosenArithmetic = ConfigurationKt.getChosenArithmetic();
        arithmetic = chosenArithmetic;
        ZERO = new BigInteger(chosenArithmetic.m8380getZEROY2RjT0g(), Sign.ZERO, (DefaultConstructorMarker) null);
        long[] r3 = chosenArithmetic.m8377getONEY2RjT0g();
        Sign sign2 = Sign.POSITIVE;
        ONE = new BigInteger(r3, sign2, (DefaultConstructorMarker) null);
        TWO = new BigInteger(chosenArithmetic.m8379getTWOY2RjT0g(), sign2, (DefaultConstructorMarker) null);
        TEN = new BigInteger(chosenArithmetic.m8378getTENY2RjT0g(), sign2, (DefaultConstructorMarker) null);
    }

    public /* synthetic */ BigInteger(long[] jArr, Sign sign2, DefaultConstructorMarker defaultConstructorMarker) {
        this(jArr, sign2);
    }

    private final BigInteger d1reciprocalRecursive() {
        return new BigInteger(arithmetic.m8388reciprocalQwZRm1k(m8301getMagnitudeY2RjT0g$bignum()).getFirst().m9222unboximpl(), this.sign, (DefaultConstructorMarker) null);
    }

    /* renamed from: isResultZero-QwZRm1k  reason: not valid java name */
    private final boolean m8300isResultZeroQwZRm1k(long[] jArr) {
        BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
        return bigIntegerArithmetic.m8364compareGR1PJdc(jArr, bigIntegerArithmetic.m8380getZEROY2RjT0g()) == 0;
    }

    private final int javascriptNumberComparison(Number number) {
        return number.floatValue() % ((float) 1) == 0.0f ? compare(Companion.fromLong(number.longValue())) : compareFloatAndBigInt(number.floatValue(), new BigInteger$javascriptNumberComparison$1(this));
    }

    private final BigInteger naiveGcd(BigInteger bigInteger) {
        while (true) {
            BigInteger bigInteger2 = bigInteger;
            BigInteger bigInteger3 = this;
            this = bigInteger2;
            if (Intrinsics.areEqual((Object) this, (Object) ZERO)) {
                return bigInteger3;
            }
            bigInteger = (BigInteger) bigInteger3.rem((BigNumber) this);
        }
    }

    public boolean bitAt(long j2) {
        return arithmetic.m8362bitAttBf0fek(m8301getMagnitudeY2RjT0g$bignum(), j2);
    }

    public byte byteValue(boolean z2) {
        if (!z2 || (compareTo(Byte.MAX_VALUE) <= 0 && compareTo(Byte.MIN_VALUE) >= 0)) {
            return (byte) (signum() * ((byte) ((int) ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0))));
        }
        throw new ArithmeticException("Cannot convert to byte and provide exact value");
    }

    public final int compare(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        if (isZero() && bigInteger.isZero()) {
            return 0;
        }
        if (bigInteger.isZero() && this.sign == Sign.POSITIVE) {
            return 1;
        }
        if (bigInteger.isZero() && this.sign == Sign.NEGATIVE) {
            return -1;
        }
        if (isZero() && bigInteger.sign == Sign.POSITIVE) {
            return -1;
        }
        if (isZero() && bigInteger.sign == Sign.NEGATIVE) {
            return 1;
        }
        Sign sign2 = this.sign;
        if (sign2 != bigInteger.sign) {
            return sign2 == Sign.POSITIVE ? 1 : -1;
        }
        int r02 = arithmetic.m8364compareGR1PJdc(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum());
        Sign sign3 = this.sign;
        Sign sign4 = Sign.NEGATIVE;
        return (sign3 == sign4 && bigInteger.sign == sign4) ? r02 * -1 : r02;
    }

    public final int compareDoubleAndBigInt(double d2, @NotNull Function1<? super BigInteger, Integer> function1) {
        Intrinsics.checkNotNullParameter(function1, "comparisonBlock");
        double floor = Math.floor(d2);
        double d3 = (double) 1;
        if (d2 % d3 == 0.0d) {
            return function1.invoke(BigNumber.Creator.DefaultImpls.tryFromDouble$default(Companion, floor, false, 2, (Object) null)).intValue();
        }
        int intValue = function1.invoke(BigNumber.Creator.DefaultImpls.tryFromDouble$default(Companion, floor + d3, false, 2, (Object) null)).intValue();
        if (intValue == 0) {
            return 1;
        }
        return intValue;
    }

    public final int compareFloatAndBigInt(float f2, @NotNull Function1<? super BigInteger, Integer> function1) {
        Intrinsics.checkNotNullParameter(function1, "comparisonBlock");
        float floor = (float) Math.floor((double) f2);
        float f3 = (float) 1;
        if (f2 % f3 == 0.0f) {
            return function1.invoke(BigNumber.Creator.DefaultImpls.tryFromFloat$default(Companion, floor, false, 2, (Object) null)).intValue();
        }
        int intValue = function1.invoke(BigNumber.Creator.DefaultImpls.tryFromFloat$default(Companion, floor + f3, false, 2, (Object) null)).intValue();
        if (intValue == 0) {
            return 1;
        }
        return intValue;
    }

    public int compareTo(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "other");
        if (obj instanceof Number) {
            Number number = (Number) obj;
            if (ComparisonWorkaround.INSTANCE.isSpecialHandlingForFloatNeeded(number)) {
                return javascriptNumberComparison(number);
            }
        }
        if (obj instanceof BigInteger) {
            return compare((BigInteger) obj);
        }
        if (obj instanceof Long) {
            return compare(Companion.fromLong(((Number) obj).longValue()));
        }
        if (obj instanceof Integer) {
            return compare(Companion.fromInt(((Number) obj).intValue()));
        }
        if (obj instanceof Short) {
            return compare(Companion.fromShort(((Number) obj).shortValue()));
        }
        if (obj instanceof Byte) {
            return compare(Companion.fromByte(((Number) obj).byteValue()));
        }
        if (obj instanceof ULong) {
            return compare(Companion.m8314fromULongVKZWuLQ(((ULong) obj).m9205unboximpl()));
        }
        if (obj instanceof UInt) {
            return compare(Companion.m8312fromUIntWZ4Q5Ns(((UInt) obj).m9126unboximpl()));
        }
        if (obj instanceof UShort) {
            return compare(Companion.m8316fromUShortxj2QHRw(((UShort) obj).m9310unboximpl()));
        }
        if (obj instanceof UByte) {
            return compare(Companion.m8308fromUByte7apg3OU(((UByte) obj).m9047unboximpl()));
        }
        if (obj instanceof Float) {
            return compareFloatAndBigInt(((Number) obj).floatValue(), new BigInteger$compareTo$1(this));
        }
        if (obj instanceof Double) {
            return compareDoubleAndBigInt(((Number) obj).doubleValue(), new BigInteger$compareTo$2(this));
        }
        throw new RuntimeException(Intrinsics.stringPlus("Invalid comparison type for BigInteger: ", Reflection.getOrCreateKotlinClass(obj.getClass())));
    }

    @NotNull
    public final BigInteger dec() {
        return (BigInteger) minus((BigNumber) ONE);
    }

    @NotNull
    public final QuotientAndRemainder divrem(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        Pair<BigInteger, BigInteger> divideAndRemainder = divideAndRemainder(bigInteger);
        return new QuotientAndRemainder(divideAndRemainder.getFirst(), divideAndRemainder.getSecond());
    }

    public double doubleValue(boolean z2) {
        if (!z2 || abs().compareTo(Double.valueOf(Double.MAX_VALUE)) <= 0) {
            return Double.parseDouble(toString());
        }
        throw new ArithmeticException("Cannot convert to float and provide exact value");
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof BigInteger ? compare((BigInteger) obj) : obj instanceof Long ? compare(Companion.fromLong(((Number) obj).longValue())) : obj instanceof Integer ? compare(Companion.fromInt(((Number) obj).intValue())) : obj instanceof Short ? compare(Companion.fromShort(((Number) obj).shortValue())) : obj instanceof Byte ? compare(Companion.fromByte(((Number) obj).byteValue())) : obj instanceof ULong ? compare(Companion.m8314fromULongVKZWuLQ(((ULong) obj).m9205unboximpl())) : obj instanceof UInt ? compare(Companion.m8312fromUIntWZ4Q5Ns(((UInt) obj).m9126unboximpl())) : obj instanceof UShort ? compare(Companion.m8316fromUShortxj2QHRw(((UShort) obj).m9310unboximpl())) : obj instanceof UByte ? compare(Companion.m8308fromUByte7apg3OU(((UByte) obj).m9047unboximpl())) : -1) == 0;
    }

    public float floatValue(boolean z2) {
        if (!z2 || abs().compareTo(Float.valueOf(Float.MAX_VALUE)) <= 0) {
            return Float.parseFloat(toString());
        }
        throw new ArithmeticException("Cannot convert to float and provide exact value");
    }

    @NotNull
    public final BigInteger gcd(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return new BigInteger(arithmetic.m8376gcdj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), Sign.POSITIVE, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigNumber.Creator<BigInteger> getCreator() {
        return Companion;
    }

    @NotNull
    public BigInteger getInstance() {
        return this;
    }

    @NotNull
    /* renamed from: getMagnitude-Y2RjT0g$bignum  reason: not valid java name */
    public final long[] m8301getMagnitudeY2RjT0g$bignum() {
        return this.magnitude;
    }

    public final int getNumberOfWords() {
        return this.numberOfWords;
    }

    @NotNull
    public final Sign getSign$bignum() {
        return this.sign;
    }

    @Nullable
    public final String getStringRepresentation() {
        return this.stringRepresentation;
    }

    public int hashCode() {
        int i3 = 0;
        for (long r4 : m8301getMagnitudeY2RjT0g$bignum()) {
            i3 += ULong.m9165hashCodeimpl(r4);
        }
        return this.sign.hashCode() + i3;
    }

    @NotNull
    public final BigInteger inc() {
        return (BigInteger) plus((BigNumber) ONE);
    }

    public int intValue(boolean z2) {
        if (!z2 || (compareTo(Integer.MAX_VALUE) <= 0 && compareTo(Integer.MIN_VALUE) >= 0)) {
            return signum() * ((int) ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0));
        }
        throw new ArithmeticException("Cannot convert to int and provide exact value");
    }

    public boolean isNegative() {
        return BigNumber.DefaultImpls.isNegative(this);
    }

    public boolean isPositive() {
        return BigNumber.DefaultImpls.isPositive(this);
    }

    public boolean isZero() {
        return this.sign == Sign.ZERO || ConfigurationKt.getChosenArithmetic().m8364compareGR1PJdc(m8301getMagnitudeY2RjT0g$bignum(), ConfigurationKt.getChosenArithmetic().m8380getZEROY2RjT0g()) == 0;
    }

    public long longValue(boolean z2) {
        if (z2 && (compareTo(Long.MAX_VALUE) > 0 || compareTo(Long.MIN_VALUE) < 0)) {
            throw new ArithmeticException("Cannot convert to long and provide exact value");
        } else if (ULongArray.m9214getSizeimpl(m8301getMagnitudeY2RjT0g$bignum()) <= 1) {
            return ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0);
        } else {
            return (ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0) | ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 1) << 63)) * ((long) signum());
        }
    }

    @NotNull
    public final BigInteger mod(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "modulo");
        BigInteger bigInteger2 = (BigInteger) rem((BigNumber) bigInteger);
        return bigInteger2.compareTo(0) < 0 ? (BigInteger) bigInteger2.plus((BigNumber) bigInteger) : bigInteger2;
    }

    @NotNull
    public final BigInteger modInverse(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "modulo");
        BigInteger gcd = gcd(bigInteger);
        BigInteger bigInteger2 = ONE;
        if (Intrinsics.areEqual((Object) gcd, (Object) bigInteger2)) {
            BigInteger bigInteger3 = ZERO;
            BigInteger bigInteger4 = bigInteger;
            BigInteger bigInteger5 = this;
            BigInteger bigInteger6 = bigInteger4;
            while (!Intrinsics.areEqual((Object) bigInteger6, (Object) ZERO)) {
                QuotientAndRemainder divrem = bigInteger5.divrem(bigInteger6);
                BigInteger component1 = divrem.component1();
                BigInteger component2 = divrem.component2();
                bigInteger5 = bigInteger6;
                bigInteger6 = component2;
                BigInteger bigInteger7 = (BigInteger) bigInteger2.minus(component1.times((BigNumber) bigInteger3));
                bigInteger2 = bigInteger3;
                bigInteger3 = bigInteger7;
            }
            return bigInteger2;
        }
        throw new ArithmeticException("BigInteger is not invertible. This and modulus are not relatively prime (coprime)");
    }

    public long numberOfDecimalDigits() {
        if (isZero()) {
            return 1;
        }
        int ceil = (int) Math.ceil(((double) (arithmetic.m8363bitLengthQwZRm1k(m8301getMagnitudeY2RjT0g$bignum()) - 1)) * LOG_10_OF_2);
        BigInteger bigInteger = (BigInteger) div((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(ceil));
        long j2 = 0;
        while (bigInteger.compareTo(0) != 0) {
            bigInteger = (BigInteger) bigInteger.div(10);
            j2++;
        }
        return j2 + ((long) ceil);
    }

    @NotNull
    public final BigIntegerRange rangeTo(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return new BigIntegerRange(this, bigInteger);
    }

    public void secureOverwrite() {
        int r02 = ULongArray.m9214getSizeimpl(m8301getMagnitudeY2RjT0g$bignum());
        if (r02 > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                ULongArray.m9218setk8EXiF4(m8301getMagnitudeY2RjT0g$bignum(), i3, 0);
                if (i4 < r02) {
                    i3 = i4;
                } else {
                    return;
                }
            }
        }
    }

    public final void setStringRepresentation(@Nullable String str) {
        this.stringRepresentation = str;
    }

    public short shortValue(boolean z2) {
        if (!z2 || (compareTo(Short.valueOf(ShortCompanionObject.MAX_VALUE)) <= 0 && compareTo(Short.valueOf(ShortCompanionObject.MIN_VALUE)) >= 0)) {
            return (short) (signum() * ((short) ((int) ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0))));
        }
        throw new ArithmeticException("Cannot convert to short and provide exact value");
    }

    public int signum() {
        int i3 = WhenMappings.$EnumSwitchMapping$0[this.sign.ordinal()];
        if (i3 == 1) {
            return 1;
        }
        if (i3 == 2) {
            return -1;
        }
        if (i3 == 3) {
            return 0;
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public final BigInteger sqrt() {
        return new BigInteger(arithmetic.m8392sqrtQwZRm1k(m8301getMagnitudeY2RjT0g$bignum()).getFirst().m9222unboximpl(), this.sign, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final SqareRootAndRemainder sqrtAndRemainder() {
        BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
        return new SqareRootAndRemainder(new BigInteger(bigIntegerArithmetic.m8392sqrtQwZRm1k(m8301getMagnitudeY2RjT0g$bignum()).getFirst().m9222unboximpl(), this.sign, (DefaultConstructorMarker) null), new BigInteger(bigIntegerArithmetic.m8392sqrtQwZRm1k(m8301getMagnitudeY2RjT0g$bignum()).getSecond().m9222unboximpl(), this.sign, (DefaultConstructorMarker) null));
    }

    @NotNull
    public byte[] toByteArray() {
        return arithmetic.m8394toByteArrayQwZRm1k(m8301getMagnitudeY2RjT0g$bignum());
    }

    @NotNull
    public final ModularBigInteger toModularBigInteger(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "modulo");
        return ModularBigInteger.Companion.creatorForModulo(bigInteger).fromBigInteger(this);
    }

    @NotNull
    public String toString() {
        return toString(10);
    }

    @NotNull
    public final String toStringWithoutSign$bignum(int i3) {
        return arithmetic.m8395toStringtBf0fek(m8301getMagnitudeY2RjT0g$bignum(), i3);
    }

    @NotNull
    /* renamed from: toUByteArray-TcUX1vc  reason: not valid java name */
    public byte[] m8302toUByteArrayTcUX1vc() {
        return arithmetic.m8396toUByteArraycMszsnM(m8301getMagnitudeY2RjT0g$bignum());
    }

    /* renamed from: ubyteValue-Wa3L5BU  reason: not valid java name */
    public byte m8303ubyteValueWa3L5BU(boolean z2) {
        if (!z2 || (compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl(255))) <= 0 && !isNegative())) {
            return UByte.m8997constructorimpl((byte) ((int) ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0)));
        }
        throw new ArithmeticException("Cannot convert to unsigned byte and provide exact value");
    }

    /* renamed from: uintValue-OGnWXxg  reason: not valid java name */
    public int m8304uintValueOGnWXxg(boolean z2) {
        if (!z2 || (compareTo(UInt.m9068boximpl(-1)) <= 0 && !isNegative())) {
            return UInt.m9074constructorimpl((int) ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0));
        }
        throw new ArithmeticException("Cannot convert to unsigned int and provide exact value");
    }

    /* renamed from: ulongValue-I7RO_PI  reason: not valid java name */
    public long m8305ulongValueI7RO_PI(boolean z2) {
        if (z2 && (compareTo(ULong.m9147boximpl(-1)) > 0 || isNegative())) {
            throw new ArithmeticException("Cannot convert to unsigned long and provide exact value");
        } else if (ULongArray.m9214getSizeimpl(m8301getMagnitudeY2RjT0g$bignum()) <= 1) {
            return ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0);
        } else {
            return ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0) | ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 1) << 63));
        }
    }

    /* renamed from: ushortValue-BwKQO78  reason: not valid java name */
    public short m8306ushortValueBwKQO78(boolean z2) {
        if ((!z2 || compareTo(UInt.m9068boximpl(UInt.m9074constructorimpl(65535))) <= 0) && !isNegative()) {
            return UShort.m9260constructorimpl((short) ((int) ULongArray.m9213getsVKNKU(m8301getMagnitudeY2RjT0g$bignum(), 0)));
        }
        throw new ArithmeticException("Cannot convert to unsigned short and provide exact value");
    }

    private BigInteger(long[] jArr, Sign sign2) {
        Sign sign3 = Sign.ZERO;
        if (sign2 != sign3 || m8300isResultZeroQwZRm1k(jArr)) {
            this.magnitude = BigInteger63Arithmetic.INSTANCE.m8649removeLeadingZerosJIhQxVY(jArr);
            this.sign = m8300isResultZeroQwZRm1k(m8301getMagnitudeY2RjT0g$bignum()) ? sign3 : sign2;
            this.numberOfWords = ULongArray.m9214getSizeimpl(m8301getMagnitudeY2RjT0g$bignum());
            return;
        }
        throw new IllegalArgumentException("sign should be Sign.ZERO iff magnitude has a value of 0");
    }

    @NotNull
    public BigInteger abs() {
        return new BigInteger(m8301getMagnitudeY2RjT0g$bignum(), Sign.POSITIVE, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger add(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
        int r12 = bigIntegerArithmetic.m8364compareGR1PJdc(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum());
        if (bigInteger.sign == this.sign) {
            return new BigInteger(bigIntegerArithmetic.m8360addj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), this.sign, (DefaultConstructorMarker) null);
        }
        if (r12 > 0) {
            return new BigInteger(bigIntegerArithmetic.m8393subtractj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), this.sign, (DefaultConstructorMarker) null);
        }
        if (r12 < 0) {
            return new BigInteger(bigIntegerArithmetic.m8393subtractj68ebKY(bigInteger.m8301getMagnitudeY2RjT0g$bignum(), m8301getMagnitudeY2RjT0g$bignum()), bigInteger.sign, (DefaultConstructorMarker) null);
        }
        return ZERO;
    }

    @NotNull
    public BigInteger and(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return new BigInteger(arithmetic.m8361andj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), this.sign, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger divide(@NotNull BigInteger bigInteger) {
        Sign sign2;
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        if (!bigInteger.isZero()) {
            BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
            long[] r12 = bigIntegerArithmetic.m8365divideGR1PJdc(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()).getFirst().m9222unboximpl();
            if (ULongArray.m9212equalsimpl0(r12, bigIntegerArithmetic.m8380getZEROY2RjT0g())) {
                return ZERO;
            }
            if (this.sign != bigInteger.sign) {
                sign2 = Sign.NEGATIVE;
            } else {
                sign2 = Sign.POSITIVE;
            }
            return new BigInteger(r12, sign2, (DefaultConstructorMarker) null);
        }
        throw new ArithmeticException("Division by zero! " + this + " / " + bigInteger);
    }

    @NotNull
    public Pair<BigInteger, BigInteger> divideAndRemainder(@NotNull BigInteger bigInteger) {
        Sign sign2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        if (!bigInteger.isZero()) {
            if (this.sign != bigInteger.sign) {
                sign2 = Sign.NEGATIVE;
            } else {
                sign2 = Sign.POSITIVE;
            }
            BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
            Pair<ULongArray, ULongArray> r6 = bigIntegerArithmetic.m8365divideGR1PJdc(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum());
            if (ULongArray.m9212equalsimpl0(r6.getFirst().m9222unboximpl(), bigIntegerArithmetic.m8380getZEROY2RjT0g())) {
                bigInteger2 = ZERO;
            } else {
                bigInteger2 = new BigInteger(r6.getFirst().m9222unboximpl(), sign2, (DefaultConstructorMarker) null);
            }
            if (ULongArray.m9212equalsimpl0(r6.getSecond().m9222unboximpl(), bigIntegerArithmetic.m8380getZEROY2RjT0g())) {
                bigInteger3 = ZERO;
            } else {
                bigInteger3 = new BigInteger(r6.getSecond().m9222unboximpl(), this.sign, (DefaultConstructorMarker) null);
            }
            return new Pair<>(bigInteger2, bigInteger3);
        }
        throw new ArithmeticException("Division by zero! " + this + " / " + bigInteger);
    }

    @NotNull
    public BigInteger multiply(@NotNull BigInteger bigInteger) {
        Sign sign2;
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        if (isZero() || bigInteger.isZero()) {
            return ZERO;
        }
        if (Intrinsics.areEqual((Object) bigInteger, (Object) ONE)) {
            return this;
        }
        if (this.sign != bigInteger.sign) {
            sign2 = Sign.NEGATIVE;
        } else {
            sign2 = Sign.POSITIVE;
        }
        if (sign2 == Sign.POSITIVE) {
            return new BigInteger(arithmetic.m8381multiplyj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), sign2, (DefaultConstructorMarker) null);
        }
        return new BigInteger(arithmetic.m8381multiplyj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), sign2, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger negate() {
        return new BigInteger(m8301getMagnitudeY2RjT0g$bignum(), this.sign.not(), (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger not() {
        return new BigInteger(arithmetic.m8382notJIhQxVY(m8301getMagnitudeY2RjT0g$bignum()), this.sign, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger or(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        return new BigInteger(arithmetic.m8385orj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), this.sign, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger remainder(@NotNull BigInteger bigInteger) {
        Sign sign2;
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        if (!bigInteger.isZero()) {
            if (this.sign != bigInteger.sign) {
                sign2 = Sign.NEGATIVE;
            } else {
                sign2 = Sign.POSITIVE;
            }
            BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
            long[] r3 = bigIntegerArithmetic.m8365divideGR1PJdc(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()).getSecond().m9222unboximpl();
            if (ULongArray.m9212equalsimpl0(r3, bigIntegerArithmetic.m8380getZEROY2RjT0g())) {
                sign2 = Sign.ZERO;
            }
            return new BigInteger(r3, sign2, (DefaultConstructorMarker) null);
        }
        throw new ArithmeticException("Division by zero! " + this + " / " + bigInteger);
    }

    @NotNull
    public BigInteger setBitAt(long j2, boolean z2) {
        return new BigInteger(arithmetic.m8389setBitAtv3PXmpk(m8301getMagnitudeY2RjT0g$bignum(), j2, z2), this.sign, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger shl(int i3) {
        return new BigInteger(arithmetic.m8390shiftLeftGERUpyg(m8301getMagnitudeY2RjT0g$bignum(), i3), this.sign, (DefaultConstructorMarker) null);
    }

    @NotNull
    public BigInteger shr(int i3) {
        BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
        BigInteger bigInteger = new BigInteger(bigIntegerArithmetic.m8391shiftRightGERUpyg(m8301getMagnitudeY2RjT0g$bignum(), i3), this.sign, (DefaultConstructorMarker) null);
        return ULongArray.m9212equalsimpl0(bigInteger.m8301getMagnitudeY2RjT0g$bignum(), bigIntegerArithmetic.m8380getZEROY2RjT0g()) ? ZERO : bigInteger;
    }

    @NotNull
    public BigInteger subtract(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        BigIntegerArithmetic bigIntegerArithmetic = arithmetic;
        int r12 = bigIntegerArithmetic.m8364compareGR1PJdc(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum());
        BigInteger bigInteger2 = ZERO;
        if (Intrinsics.areEqual((Object) this, (Object) bigInteger2)) {
            return bigInteger.negate();
        }
        if (Intrinsics.areEqual((Object) bigInteger, (Object) bigInteger2)) {
            return this;
        }
        if (bigInteger.sign != this.sign) {
            return new BigInteger(bigIntegerArithmetic.m8360addj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), this.sign, (DefaultConstructorMarker) null);
        }
        if (r12 > 0) {
            return new BigInteger(bigIntegerArithmetic.m8393subtractj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum()), this.sign, (DefaultConstructorMarker) null);
        }
        return r12 < 0 ? new BigInteger(bigIntegerArithmetic.m8393subtractj68ebKY(bigInteger.m8301getMagnitudeY2RjT0g$bignum(), m8301getMagnitudeY2RjT0g$bignum()), this.sign.not(), (DefaultConstructorMarker) null) : bigInteger2;
    }

    @NotNull
    public String toString(int i3) {
        String str;
        if (this.sign == Sign.NEGATIVE) {
            str = "-";
        } else {
            str = "";
        }
        return Intrinsics.stringPlus(str, toStringWithoutSign$bignum(i3));
    }

    @NotNull
    public BigInteger unaryMinus() {
        return negate();
    }

    @NotNull
    public BigInteger xor(@NotNull BigInteger bigInteger) {
        Sign sign2;
        Intrinsics.checkNotNullParameter(bigInteger, "other");
        long[] r02 = arithmetic.m8398xorj68ebKY(m8301getMagnitudeY2RjT0g$bignum(), bigInteger.m8301getMagnitudeY2RjT0g$bignum());
        if (bigInteger.isNegative() ^ isNegative()) {
            sign2 = Sign.NEGATIVE;
        } else if (m8300isResultZeroQwZRm1k(r02)) {
            sign2 = Sign.ZERO;
        } else {
            sign2 = Sign.POSITIVE;
        }
        return new BigInteger(r02, sign2, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final BigInteger pow(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "exponent");
        if (bigInteger.compareTo(Long.MAX_VALUE) <= 0) {
            return pow(ULongArray.m9213getsVKNKU(bigInteger.m8301getMagnitudeY2RjT0g$bignum(), 0));
        }
        BigInteger bigInteger2 = ONE;
        while (bigInteger.compareTo(0) > 0) {
            bigInteger = bigInteger.dec();
            bigInteger2 = (BigInteger) bigInteger2.times((BigNumber) this);
        }
        return bigInteger2;
    }

    @NotNull
    public BigInteger div(byte b3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.div(this, b3);
    }

    @NotNull
    public BigInteger minus(byte b3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, b3);
    }

    @NotNull
    public BigInteger plus(byte b3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, b3);
    }

    @NotNull
    public BigInteger rem(byte b3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, b3);
    }

    @NotNull
    public BigInteger times(byte b3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.times(this, b3);
    }

    @NotNull
    public BigInteger div(int i3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.div(this, i3);
    }

    @NotNull
    public BigInteger minus(int i3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, i3);
    }

    @NotNull
    public BigInteger plus(int i3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, i3);
    }

    @NotNull
    public BigInteger rem(int i3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, i3);
    }

    @NotNull
    public BigInteger times(int i3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.times(this, i3);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BigInteger(long r6) {
        /*
            r5 = this;
            com.ionspin.kotlin.bignum.integer.BigIntegerArithmetic r0 = arithmetic
            long[] r0 = r0.m8369fromLongDHQ6RzY(r6)
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            java.lang.Class<java.lang.Long> r7 = java.lang.Long.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            java.lang.Class r2 = java.lang.Long.TYPE
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x003a
            long r1 = r6.longValue()
            r3 = 0
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 >= 0) goto L_0x002a
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x002a:
            long r6 = r6.longValue()
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0036
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x0036:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x003a:
            java.lang.Class r2 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x005e
            r7 = r6
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r6.intValue()
            if (r7 >= 0) goto L_0x0052
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0052:
            int r6 = r6.intValue()
            if (r6 <= 0) goto L_0x005b
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x005b:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x005e:
            java.lang.Class r2 = java.lang.Short.TYPE
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x0082
            r7 = r6
            java.lang.Short r7 = (java.lang.Short) r7
            short r7 = r6.shortValue()
            if (r7 >= 0) goto L_0x0076
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0076:
            short r6 = r6.shortValue()
            if (r6 <= 0) goto L_0x007f
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x007f:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x0082:
            java.lang.Class r2 = java.lang.Byte.TYPE
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x00aa
            r7 = r6
            java.lang.Byte r7 = (java.lang.Byte) r7
            byte r7 = r6.byteValue()
            if (r7 >= 0) goto L_0x009a
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x009a:
            byte r6 = r6.byteValue()
            if (r6 <= 0) goto L_0x00a3
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x00a3:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
        L_0x00a5:
            r7 = 0
            r5.<init>(r0, r6, r7)
            return
        L_0x00aa:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "Unsupported type "
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r7)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ionspin.kotlin.bignum.integer.BigInteger.<init>(long):void");
    }

    @NotNull
    public BigInteger div(long j2) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.div(this, j2);
    }

    @NotNull
    public BigInteger minus(long j2) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, j2);
    }

    @NotNull
    public BigInteger plus(long j2) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, j2);
    }

    @NotNull
    public BigInteger rem(long j2) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, j2);
    }

    @NotNull
    public BigInteger times(long j2) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.times(this, j2);
    }

    @NotNull
    public BigInteger div(@NotNull BigInteger bigInteger) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.div(this, bigInteger);
    }

    @NotNull
    public BigInteger minus(@NotNull BigInteger bigInteger) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, bigInteger);
    }

    @NotNull
    public BigInteger plus(@NotNull BigInteger bigInteger) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, bigInteger);
    }

    @NotNull
    public BigInteger pow(long j2) {
        if (j2 >= 0) {
            BigInteger bigInteger = ZERO;
            if (Intrinsics.areEqual((Object) this, (Object) bigInteger)) {
                return bigInteger;
            }
            BigInteger bigInteger2 = ONE;
            if (Intrinsics.areEqual((Object) this, (Object) bigInteger2)) {
                return bigInteger2;
            }
            Sign sign2 = this.sign;
            Sign sign3 = Sign.NEGATIVE;
            if (sign2 != sign3) {
                sign3 = Sign.POSITIVE;
            } else if (j2 % ((long) 2) == 0) {
                sign3 = Sign.POSITIVE;
            }
            return new BigInteger(arithmetic.m8387powGERUpyg(m8301getMagnitudeY2RjT0g$bignum(), j2), sign3, (DefaultConstructorMarker) null);
        }
        throw new ArithmeticException("Negative exponent not supported with BigInteger");
    }

    @NotNull
    public BigInteger rem(@NotNull BigInteger bigInteger) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, bigInteger);
    }

    @NotNull
    public BigInteger times(@NotNull BigInteger bigInteger) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.times(this, bigInteger);
    }

    @NotNull
    public BigInteger div(short s3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.div(this, s3);
    }

    @NotNull
    public BigInteger minus(short s3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.minus(this, s3);
    }

    @NotNull
    public BigInteger plus(short s3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.plus(this, s3);
    }

    @NotNull
    public BigInteger rem(short s3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.rem(this, s3);
    }

    @NotNull
    public BigInteger times(short s3) {
        return (BigInteger) CommonBigNumberOperations.DefaultImpls.times(this, s3);
    }

    @NotNull
    public final String times(char c3) {
        if (compareTo(0) >= 0) {
            StringBuilder sb = new StringBuilder();
            while (this.compareTo(0) > 0) {
                sb.append(c3);
                this = this.dec();
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
            return sb2;
        }
        throw new RuntimeException("Char cannot be multiplied with negative number");
    }

    @NotNull
    public BigInteger pow(int i3) {
        return pow((long) i3);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BigInteger(int r6) {
        /*
            r5 = this;
            com.ionspin.kotlin.bignum.integer.BigIntegerArithmetic r0 = arithmetic
            long[] r0 = r0.m8368fromIntDHQ6RzY(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Class<java.lang.Integer> r1 = java.lang.Integer.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            java.lang.Class r3 = java.lang.Long.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x003d
            r1 = r6
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r6.longValue()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x002d
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x002d:
            long r1 = r6.longValue()
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0039
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x0039:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x003d:
            java.lang.Class r3 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x005e
            int r1 = r6.intValue()
            if (r1 >= 0) goto L_0x0052
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0052:
            int r6 = r6.intValue()
            if (r6 <= 0) goto L_0x005b
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x005b:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x005e:
            java.lang.Class r3 = java.lang.Short.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0082
            r1 = r6
            java.lang.Short r1 = (java.lang.Short) r1
            short r1 = r6.shortValue()
            if (r1 >= 0) goto L_0x0076
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0076:
            short r6 = r6.shortValue()
            if (r6 <= 0) goto L_0x007f
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x007f:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x0082:
            java.lang.Class r3 = java.lang.Byte.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x00aa
            r1 = r6
            java.lang.Byte r1 = (java.lang.Byte) r1
            byte r1 = r6.byteValue()
            if (r1 >= 0) goto L_0x009a
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x009a:
            byte r6 = r6.byteValue()
            if (r6 <= 0) goto L_0x00a3
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x00a3:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
        L_0x00a5:
            r1 = 0
            r5.<init>(r0, r6, r1)
            return
        L_0x00aa:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "Unsupported type "
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r0)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ionspin.kotlin.bignum.integer.BigInteger.<init>(int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BigInteger(short r6) {
        /*
            r5 = this;
            com.ionspin.kotlin.bignum.integer.BigIntegerArithmetic r0 = arithmetic
            long[] r0 = r0.m8370fromShortDHQ6RzY(r6)
            java.lang.Short r6 = java.lang.Short.valueOf(r6)
            java.lang.Class<java.lang.Short> r1 = java.lang.Short.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            java.lang.Class r3 = java.lang.Long.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x003d
            r1 = r6
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r6.longValue()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x002d
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x002d:
            long r1 = r6.longValue()
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0039
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x0039:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x003d:
            java.lang.Class r3 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0061
            r1 = r6
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r6.intValue()
            if (r1 >= 0) goto L_0x0055
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0055:
            int r6 = r6.intValue()
            if (r6 <= 0) goto L_0x005e
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x005e:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x0061:
            java.lang.Class r3 = java.lang.Short.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0082
            short r1 = r6.shortValue()
            if (r1 >= 0) goto L_0x0076
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0076:
            short r6 = r6.shortValue()
            if (r6 <= 0) goto L_0x007f
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x007f:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x0082:
            java.lang.Class r3 = java.lang.Byte.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x00aa
            r1 = r6
            java.lang.Byte r1 = (java.lang.Byte) r1
            byte r1 = r6.byteValue()
            if (r1 >= 0) goto L_0x009a
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x009a:
            byte r6 = r6.byteValue()
            if (r6 <= 0) goto L_0x00a3
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x00a3:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
        L_0x00a5:
            r1 = 0
            r5.<init>(r0, r6, r1)
            return
        L_0x00aa:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "Unsupported type "
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r0)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ionspin.kotlin.bignum.integer.BigInteger.<init>(short):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BigInteger(byte r6) {
        /*
            r5 = this;
            com.ionspin.kotlin.bignum.integer.BigIntegerArithmetic r0 = arithmetic
            long[] r0 = r0.m8366fromByteDHQ6RzY(r6)
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            java.lang.Class<java.lang.Byte> r1 = java.lang.Byte.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            java.lang.Class r3 = java.lang.Long.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x003d
            r1 = r6
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r6.longValue()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x002d
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x002d:
            long r1 = r6.longValue()
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0039
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x0039:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x003d:
            java.lang.Class r3 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0061
            r1 = r6
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r6.intValue()
            if (r1 >= 0) goto L_0x0055
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0055:
            int r6 = r6.intValue()
            if (r6 <= 0) goto L_0x005e
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x005e:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x0061:
            java.lang.Class r3 = java.lang.Short.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0085
            r1 = r6
            java.lang.Short r1 = (java.lang.Short) r1
            short r1 = r6.shortValue()
            if (r1 >= 0) goto L_0x0079
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x0079:
            short r6 = r6.shortValue()
            if (r6 <= 0) goto L_0x0082
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x0082:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            goto L_0x00a5
        L_0x0085:
            java.lang.Class r3 = java.lang.Byte.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x00aa
            byte r1 = r6.byteValue()
            if (r1 >= 0) goto L_0x009a
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
            goto L_0x00a5
        L_0x009a:
            byte r6 = r6.byteValue()
            if (r6 <= 0) goto L_0x00a3
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            goto L_0x00a5
        L_0x00a3:
            com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
        L_0x00a5:
            r1 = 0
            r5.<init>(r0, r6, r1)
            return
        L_0x00aa:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "Unsupported type "
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r0)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ionspin.kotlin.bignum.integer.BigInteger.<init>(byte):void");
    }
}
