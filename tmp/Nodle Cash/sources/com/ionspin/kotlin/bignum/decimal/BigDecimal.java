package com.ionspin.kotlin.bignum.decimal;

import androidx.collection.SieveCacheKt;
import androidx.compose.ui.autofill.a;
import com.ionspin.kotlin.bignum.BigNumber;
import com.ionspin.kotlin.bignum.CommonBigNumberOperations;
import com.ionspin.kotlin.bignum.NarrowingOperations;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import com.ionspin.kotlin.bignum.integer.BigIntegerExtensionsKt;
import com.ionspin.kotlin.bignum.integer.ComparisonWorkaround;
import com.ionspin.kotlin.bignum.integer.ConfigurationKt;
import com.ionspin.kotlin.bignum.integer.Sign;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0004\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u0000 \u00012\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004:\u0004\u0001\u0001B%\b\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010$\u001a\u00020\u0000H\u0016J\u0010\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0016J\u001a\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bJ*\u0010'\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0000H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020!H\u0016J\u0006\u0010.\u001a\u00020\u0000J\u0010\u0010/\u001a\u0002002\u0006\u0010-\u001a\u00020!H\u0002J\u000e\u00101\u001a\u0002022\u0006\u0010&\u001a\u00020\u0000J\u0011\u00103\u001a\u0002022\u0006\u0010&\u001a\u00020\u0005H\u0002J\u0018\u00104\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u00002\u0006\u00105\u001a\u000206H\u0002J&\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bJ\u0006\u00108\u001a\u00020\u0000J\u0011\u00109\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002J\u0010\u0010:\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0016J\u001a\u0010:\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bJ\u001c\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000<2\u0006\u0010&\u001a\u00020\u0000H\u0016J\u001d\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000<2\u0006\u0010&\u001a\u00020\u0000H\u0004J(\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000<2\u0006\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010>\u001a\u00020?2\u0006\u0010-\u001a\u00020!H\u0016J\u0013\u0010@\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010A\u001a\u00020B2\u0006\u0010-\u001a\u00020!H\u0016J\u0006\u0010C\u001a\u00020\u0000J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00000EH\u0016J\b\u0010F\u001a\u00020\u0000H\u0016J\u0010\u0010G\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\u0000H\u0002J\b\u0010I\u001a\u000202H\u0016J\u0006\u0010J\u001a\u00020\u0000J\u0010\u0010K\u001a\u0002022\u0006\u0010-\u001a\u00020!H\u0016J\u001c\u0010L\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0002J\u0006\u0010M\u001a\u00020!J\b\u0010N\u001a\u00020!H\u0016J\u0010\u0010O\u001a\u0002022\u0006\u0010P\u001a\u00020QH\u0002J\u0010\u0010R\u001a\u00020\t2\u0006\u0010-\u001a\u00020!H\u0016J\u0011\u0010S\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002J\u000e\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u000202J\u000e\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\tJ\u0010\u0010V\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0016J\u001a\u0010V\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bJ\b\u0010W\u001a\u00020\u0000H\u0016J\u0010\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020YH\u0002J\b\u0010[\u001a\u00020\tH\u0016J\u0018\u0010\\\u001a\u00020Y2\u0006\u0010Z\u001a\u00020Y2\u0006\u0010]\u001a\u000202H\u0002J\u0018\u0010^\u001a\u00020Y2\u0006\u0010Z\u001a\u00020Y2\u0006\u0010]\u001a\u000202H\u0002J\u0011\u0010_\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002J\u0010\u0010`\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u000202H\u0016J\u0010\u0010`\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u0011\u0010a\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002J\u001c\u0010a\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010b\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0016J\u0006\u0010c\u001a\u00020\u0000J\u0010\u0010d\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\u0000H\u0002J\u0010\u0010e\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u000bJ\u0018\u0010f\u001a\u00020\u00002\u0006\u0010g\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\u0018J\u0016\u0010h\u001a\u00020\u00002\u0006\u0010g\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\tJ\b\u0010i\u001a\u000200H\u0016J\u0010\u0010j\u001a\u00020k2\u0006\u0010-\u001a\u00020!H\u0016J\b\u0010l\u001a\u000202H\u0016J\u0010\u0010m\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0016J\u001a\u0010m\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bJ\u0011\u0010n\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002J\u0006\u0010o\u001a\u00020\u0007J\u0006\u0010p\u001a\u00020YJ\b\u0010q\u001a\u00020YH\u0016J\u0010\u0010q\u001a\u00020Y2\u0006\u0010r\u001a\u000202H\u0016J\u0006\u0010s\u001a\u00020YJ \u0010t\u001a\u00020u2\u0006\u0010-\u001a\u00020!H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bv\u0010wJ \u0010x\u001a\u00020y2\u0006\u0010-\u001a\u00020!H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bz\u0010{J \u0010|\u001a\u00020}2\u0006\u0010-\u001a\u00020!H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b~\u0010J\n\u0010\u0001\u001a\u00020\u0000H\u0002J$\u0010\u0001\u001a\u00030\u00012\u0006\u0010-\u001a\u00020!H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u0017\u0010n\u001a\u00020Y*\u00020\t2\b\u0010\u0001\u001a\u00030\u0001H\u0002R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u001d\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0001"}, d2 = {"Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "Lcom/ionspin/kotlin/bignum/BigNumber;", "Lcom/ionspin/kotlin/bignum/CommonBigNumberOperations;", "Lcom/ionspin/kotlin/bignum/NarrowingOperations;", "", "", "_significand", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "_exponent", "", "_decimalMode", "Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;", "(Lcom/ionspin/kotlin/bignum/integer/BigInteger;JLcom/ionspin/kotlin/bignum/decimal/DecimalMode;)V", "decimalMode", "getDecimalMode", "()Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;", "exponent", "getExponent", "()J", "precision", "getPrecision", "precisionLimit", "getPrecisionLimit", "roundingMode", "Lcom/ionspin/kotlin/bignum/decimal/RoundingMode;", "getRoundingMode", "()Lcom/ionspin/kotlin/bignum/decimal/RoundingMode;", "scale", "getScale", "significand", "getSignificand", "()Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "usingScale", "", "getUsingScale", "()Z", "abs", "add", "other", "bringSignificandToSameExponent", "Lkotlin/Triple;", "first", "second", "byteValue", "", "exactRequired", "ceil", "checkWholeness", "", "compare", "", "compareTo", "computeMode", "op", "Lcom/ionspin/kotlin/bignum/decimal/BigDecimal$ScaleOps;", "copy", "dec", "div", "divide", "divideAndRemainder", "Lkotlin/Pair;", "divrem", "doubleValue", "", "equals", "floatValue", "", "floor", "getCreator", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "getInstance", "getRidOfRadix", "bigDecimal", "hashCode", "inc", "intValue", "integerDiv", "isWholeNumber", "isZero", "javascriptNumberComparison", "number", "", "longValue", "minus", "moveDecimalPoint", "places", "multiply", "negate", "noExponentStringtoScientificNotation", "", "input", "numberOfDecimalDigits", "placeADotInString", "position", "placeADotInStringExpanded", "plus", "pow", "rem", "remainder", "removeScale", "removeTrailingZeroes", "roundSignificand", "roundToDigitPosition", "digitPosition", "roundToDigitPositionAfterDecimalPoint", "secureOverwrite", "shortValue", "", "signum", "subtract", "times", "toBigInteger", "toPlainString", "toString", "base", "toStringExpanded", "ubyteValue", "Lkotlin/UByte;", "ubyteValue-Wa3L5BU", "(Z)B", "uintValue", "Lkotlin/UInt;", "uintValue-OGnWXxg", "(Z)I", "ulongValue", "Lkotlin/ULong;", "ulongValue-I7RO_PI", "(Z)J", "unaryMinus", "ushortValue", "Lkotlin/UShort;", "ushortValue-BwKQO78", "(Z)S", "char", "", "Companion", "ScaleOps", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BigDecimal implements BigNumber<BigDecimal>, CommonBigNumberOperations<BigDecimal>, NarrowingOperations<BigDecimal>, Comparable<Object> {
    @NotNull
    public static final Companion Companion;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigDecimal ONE;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigDecimal TEN;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigDecimal TWO;
    /* access modifiers changed from: private */
    @NotNull
    public static final BigDecimal ZERO;
    @NotNull
    private static final double[] double10pow = {1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d, 1.0E22d};
    @NotNull
    private static final float[] float10pow = {1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f};
    @NotNull
    private static final BigDecimal leastSignificantDouble;
    @NotNull
    private static final BigDecimal leastSignificantFloat;
    @NotNull
    private static final BigDecimal maximumDouble;
    @NotNull
    private static final BigDecimal maximumFloat;
    /* access modifiers changed from: private */
    public static boolean useToStringExpanded;
    @Nullable
    private final DecimalMode decimalMode;
    private final long exponent;
    private final long precision;
    private final long precisionLimit;
    @NotNull
    private final RoundingMode roundingMode;
    private final long scale;
    @NotNull
    private final BigInteger significand;
    private final boolean usingScale;

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001pB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J \u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001dH\u0002J\u001a\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u00022\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u0010\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001dH\u0016J\u001a\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001d2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\"\u0010)\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u0010\u0010*\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,H\u0016J\u001a\u0010*\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u001a\u0010-\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\"\u0010.\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,2\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u001a\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u001a\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u0002042\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u0010\u00105\u001a\u00020\u00022\u0006\u00106\u001a\u000207H\u0016J\u001a\u00105\u001a\u00020\u00022\u0006\u00106\u001a\u0002072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u001a\u00108\u001a\u00020\u00022\u0006\u00106\u001a\u0002072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\"\u00109\u001a\u00020\u00022\u0006\u00106\u001a\u0002072\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u0010\u0010:\u001a\u00020\u00022\u0006\u0010;\u001a\u00020\u001fH\u0016J\u001a\u0010:\u001a\u00020\u00022\u0006\u0010;\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u001a\u0010<\u001a\u00020\u00022\u0006\u0010;\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\"\u0010=\u001a\u00020\u00022\u0006\u0010;\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u0010\u0010>\u001a\u00020\u00022\u0006\u0010?\u001a\u00020@H\u0016J\u001a\u0010>\u001a\u00020\u00022\u0006\u0010?\u001a\u00020@2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u001a\u0010A\u001a\u00020\u00022\u0006\u0010?\u001a\u00020@2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\"\u0010B\u001a\u00020\u00022\u0006\u0010?\u001a\u00020@2\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u001d\u0010C\u001a\u00020\u00022\u0006\u0010D\u001a\u00020EH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010GJ'\u0010C\u001a\u00020\u00022\u0006\u0010D\u001a\u00020E2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bH\u0010IJ\u001d\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020LH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bM\u0010NJ'\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020L2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bO\u0010PJ\u001d\u0010Q\u001a\u00020\u00022\u0006\u0010R\u001a\u00020SH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bT\u0010UJ'\u0010Q\u001a\u00020\u00022\u0006\u0010R\u001a\u00020S2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bV\u0010WJ\u001d\u0010X\u001a\u00020\u00022\u0006\u0010Y\u001a\u00020ZH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b[\u0010\\J'\u0010X\u001a\u00020\u00022\u0006\u0010Y\u001a\u00020Z2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b]\u0010^J\u001e\u0010_\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010`\u001a\u00020\u00022\u0006\u0010a\u001a\u00020bJ\u0018\u0010`\u001a\u00020\u00022\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u000207H\u0016J\u001a\u0010d\u001a\u00020\u00022\u0006\u0010e\u001a\u00020b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J&\u0010f\u001a\u00020!2\b\u0010g\u001a\u0004\u0018\u00010!2\b\u0010h\u001a\u0004\u0018\u00010!2\b\u0010i\u001a\u0004\u0018\u00010!H\u0002J \u0010j\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002J \u0010k\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J \u0010l\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010m\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\u0006\u0010n\u001a\u00020\u0016H\u0016J\u0018\u0010o\u001a\u00020\u00022\u0006\u00103\u001a\u0002042\u0006\u0010n\u001a\u00020\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006q"}, d2 = {"Lcom/ionspin/kotlin/bignum/decimal/BigDecimal$Companion;", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "()V", "ONE", "getONE", "()Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "TEN", "getTEN", "TWO", "getTWO", "ZERO", "getZERO", "double10pow", "", "float10pow", "", "leastSignificantDouble", "leastSignificantFloat", "maximumDouble", "maximumFloat", "useToStringExpanded", "", "getUseToStringExpanded", "()Z", "setUseToStringExpanded", "(Z)V", "applyScale", "significand", "Lcom/ionspin/kotlin/bignum/integer/BigInteger;", "exponent", "", "decimalMode", "Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;", "determineDecider", "Lcom/ionspin/kotlin/bignum/decimal/BigDecimal$Companion$SignificantDecider;", "discarded", "fromBigDecimal", "bigDecimal", "fromBigInteger", "bigInteger", "fromBigIntegerWithExponent", "fromByte", "byte", "", "fromByteAsSignificand", "fromByteWithExponent", "fromDouble", "double", "", "fromFloat", "float", "", "fromInt", "int", "", "fromIntAsSignificand", "fromIntWithExponent", "fromLong", "long", "fromLongAsSignificand", "fromLongWithExponent", "fromShort", "short", "", "fromShortAsSignificand", "fromShortWithExponent", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-7apg3OU", "(B)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "fromUByte-0ky7B_Q", "(BLcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-WZ4Q5Ns", "(I)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "fromUInt-qim9Vi0", "(ILcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "fromULong", "uLong", "Lkotlin/ULong;", "fromULong-VKZWuLQ", "(J)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "fromULong-4PLdz1A", "(JLcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-xj2QHRw", "(S)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "fromUShort-vckuEUM", "(SLcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "handleZeroRounding", "parseString", "string", "", "base", "parseStringWithMode", "floatingPointString", "resolveDecimalMode", "firstDecimalMode", "secondDecimalMode", "suppliedDecimalMode", "roundDiscarded", "roundOrDont", "roundSignificand", "tryFromDouble", "exactRequired", "tryFromFloat", "SignificantDecider", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion implements BigNumber.Creator<BigDecimal> {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/ionspin/kotlin/bignum/decimal/BigDecimal$Companion$SignificantDecider;", "", "(Ljava/lang/String;I)V", "FIVE", "LESS_THAN_FIVE", "MORE_THAN_FIVE", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public enum SignificantDecider {
            FIVE,
            LESS_THAN_FIVE,
            MORE_THAN_FIVE
        }

        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[RoundingMode.values().length];
                iArr[RoundingMode.AWAY_FROM_ZERO.ordinal()] = 1;
                iArr[RoundingMode.TOWARDS_ZERO.ordinal()] = 2;
                iArr[RoundingMode.CEILING.ordinal()] = 3;
                iArr[RoundingMode.FLOOR.ordinal()] = 4;
                iArr[RoundingMode.ROUND_HALF_AWAY_FROM_ZERO.ordinal()] = 5;
                iArr[RoundingMode.ROUND_HALF_TOWARDS_ZERO.ordinal()] = 6;
                iArr[RoundingMode.ROUND_HALF_CEILING.ordinal()] = 7;
                iArr[RoundingMode.ROUND_HALF_FLOOR.ordinal()] = 8;
                iArr[RoundingMode.ROUND_HALF_TO_EVEN.ordinal()] = 9;
                iArr[RoundingMode.ROUND_HALF_TO_ODD.ordinal()] = 10;
                iArr[RoundingMode.NONE.ordinal()] = 11;
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[Sign.values().length];
                iArr2[Sign.POSITIVE.ordinal()] = 1;
                iArr2[Sign.NEGATIVE.ordinal()] = 2;
                iArr2[Sign.ZERO.ordinal()] = 3;
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final BigDecimal applyScale(BigInteger bigInteger, long j2, DecimalMode decimalMode) {
            DecimalMode decimalMode2;
            long j3 = j2;
            if (!decimalMode.getUsingScale()) {
                return new BigDecimal(bigInteger, j2, decimalMode, (DefaultConstructorMarker) null);
            }
            int i3 = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
            if (i3 >= 0) {
                decimalMode2 = new DecimalMode(decimalMode.getScale() + j3 + 1, decimalMode.getRoundingMode(), 0, 4, (DefaultConstructorMarker) null);
            } else if (i3 < 0) {
                decimalMode2 = new DecimalMode(decimalMode.getScale() + 1, decimalMode.getRoundingMode(), 0, 4, (DefaultConstructorMarker) null);
            } else {
                throw new RuntimeException("Unexpected state");
            }
            DecimalMode decimalMode3 = decimalMode2;
            if (i3 >= 0) {
                return roundSignificand(bigInteger, j3, decimalMode3);
            }
            BigInteger bigInteger2 = bigInteger;
            BigDecimal bigDecimal = (BigDecimal) new BigDecimal(bigInteger, j2, (DecimalMode) null, 4, (DefaultConstructorMarker) null).plus(bigInteger.signum());
            return (BigDecimal) roundSignificand(bigDecimal.getSignificand(), bigDecimal.getExponent(), decimalMode3).minus(bigInteger.signum());
        }

        private final SignificantDecider determineDecider(BigInteger bigInteger) {
            BigInteger.Companion companion = BigInteger.Companion;
            BigInteger.QuotientAndRemainder divrem = bigInteger.divrem(companion.getTEN().pow(bigInteger.numberOfDecimalDigits() - 1));
            int intValue = divrem.getQuotient().abs().intValue(true);
            BigInteger abs = divrem.getRemainder().abs();
            if (intValue == 5) {
                return Intrinsics.areEqual((Object) abs, (Object) companion.getZERO()) ? SignificantDecider.FIVE : SignificantDecider.MORE_THAN_FIVE;
            }
            if (intValue > 5) {
                return SignificantDecider.MORE_THAN_FIVE;
            }
            if (intValue < 5) {
                return SignificantDecider.LESS_THAN_FIVE;
            }
            throw new RuntimeException("Couldn't determine decider");
        }

        public static /* synthetic */ BigDecimal fromBigDecimal$default(Companion companion, BigDecimal bigDecimal, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromBigDecimal(bigDecimal, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromBigInteger$default(Companion companion, BigInteger bigInteger, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromBigInteger(bigInteger, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromBigIntegerWithExponent$default(Companion companion, BigInteger bigInteger, long j2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                decimalMode = null;
            }
            return companion.fromBigIntegerWithExponent(bigInteger, j2, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromByte$default(Companion companion, byte b3, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromByte(b3, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromByteAsSignificand$default(Companion companion, byte b3, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromByteAsSignificand(b3, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromByteWithExponent$default(Companion companion, byte b3, long j2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                decimalMode = null;
            }
            return companion.fromByteWithExponent(b3, j2, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromDouble$default(Companion companion, double d2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromDouble(d2, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromFloat$default(Companion companion, float f2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromFloat(f2, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromInt$default(Companion companion, int i3, DecimalMode decimalMode, int i4, Object obj) {
            if ((i4 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromInt(i3, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromIntAsSignificand$default(Companion companion, int i3, DecimalMode decimalMode, int i4, Object obj) {
            if ((i4 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromIntAsSignificand(i3, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromIntWithExponent$default(Companion companion, int i3, long j2, DecimalMode decimalMode, int i4, Object obj) {
            if ((i4 & 4) != 0) {
                decimalMode = null;
            }
            return companion.fromIntWithExponent(i3, j2, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromLong$default(Companion companion, long j2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromLong(j2, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromLongAsSignificand$default(Companion companion, long j2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromLongAsSignificand(j2, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromLongWithExponent$default(Companion companion, long j2, long j3, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                decimalMode = null;
            }
            return companion.fromLongWithExponent(j2, j3, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromShort$default(Companion companion, short s3, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromShort(s3, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromShortAsSignificand$default(Companion companion, short s3, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.fromShortAsSignificand(s3, decimalMode);
        }

        public static /* synthetic */ BigDecimal fromShortWithExponent$default(Companion companion, short s3, long j2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                decimalMode = null;
            }
            return companion.fromShortWithExponent(s3, j2, decimalMode);
        }

        /* renamed from: fromUByte-0ky7B_Q$default  reason: not valid java name */
        public static /* synthetic */ BigDecimal m8284fromUByte0ky7B_Q$default(Companion companion, byte b3, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.m8288fromUByte0ky7B_Q(b3, decimalMode);
        }

        /* renamed from: fromUInt-qim9Vi0$default  reason: not valid java name */
        public static /* synthetic */ BigDecimal m8285fromUIntqim9Vi0$default(Companion companion, int i3, DecimalMode decimalMode, int i4, Object obj) {
            if ((i4 & 2) != 0) {
                decimalMode = null;
            }
            return companion.m8293fromUIntqim9Vi0(i3, decimalMode);
        }

        /* renamed from: fromULong-4PLdz1A$default  reason: not valid java name */
        public static /* synthetic */ BigDecimal m8286fromULong4PLdz1A$default(Companion companion, long j2, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.m8294fromULong4PLdz1A(j2, decimalMode);
        }

        /* renamed from: fromUShort-vckuEUM$default  reason: not valid java name */
        public static /* synthetic */ BigDecimal m8287fromUShortvckuEUM$default(Companion companion, short s3, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.m8297fromUShortvckuEUM(s3, decimalMode);
        }

        public static /* synthetic */ BigDecimal parseStringWithMode$default(Companion companion, String str, DecimalMode decimalMode, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                decimalMode = null;
            }
            return companion.parseStringWithMode(str, decimalMode);
        }

        /* access modifiers changed from: private */
        public final DecimalMode resolveDecimalMode(DecimalMode decimalMode, DecimalMode decimalMode2, DecimalMode decimalMode3) {
            if (decimalMode3 != null) {
                return decimalMode3;
            }
            if (decimalMode == null && decimalMode2 == null) {
                return new DecimalMode(0, (RoundingMode) null, 0, 7, (DefaultConstructorMarker) null);
            }
            if (decimalMode == null && decimalMode2 != null) {
                return decimalMode2;
            }
            if (decimalMode2 == null && decimalMode != null) {
                return decimalMode;
            }
            Intrinsics.checkNotNull(decimalMode);
            RoundingMode roundingMode = decimalMode.getRoundingMode();
            Intrinsics.checkNotNull(decimalMode2);
            if (roundingMode == decimalMode2.getRoundingMode()) {
                if (decimalMode.getDecimalPrecision() < decimalMode2.getDecimalPrecision()) {
                    decimalMode = decimalMode2;
                }
                return decimalMode;
            }
            throw new ArithmeticException("Different rounding modes! This: " + decimalMode.getRoundingMode() + " Other: " + decimalMode2.getRoundingMode());
        }

        /* access modifiers changed from: private */
        public final BigInteger roundDiscarded(BigInteger bigInteger, BigInteger bigInteger2, DecimalMode decimalMode) {
            Pair pair;
            long numberOfDecimalDigits = bigInteger.numberOfDecimalDigits() - decimalMode.getDecimalPrecision();
            if (numberOfDecimalDigits > 0) {
                BigInteger.QuotientAndRemainder divrem = bigInteger.divrem(BigInteger.Companion.getTEN().pow(numberOfDecimalDigits));
                pair = new Pair(divrem.getQuotient(), divrem.getRemainder());
            } else {
                pair = new Pair(bigInteger, bigInteger2);
            }
            BigInteger bigInteger3 = (BigInteger) pair.component1();
            BigInteger bigInteger4 = (BigInteger) pair.component2();
            BigInteger.Companion companion = BigInteger.Companion;
            Sign sign$bignum = Intrinsics.areEqual((Object) bigInteger, (Object) companion.getZERO()) ? bigInteger2.getSign$bignum() : bigInteger.getSign$bignum();
            if (bigInteger4.isZero()) {
                return bigInteger3;
            }
            SignificantDecider determineDecider = determineDecider(bigInteger4);
            switch (WhenMappings.$EnumSwitchMapping$0[decimalMode.getRoundingMode().ordinal()]) {
                case 1:
                    return sign$bignum == Sign.POSITIVE ? bigInteger3.inc() : bigInteger3.dec();
                case 3:
                    return sign$bignum == Sign.POSITIVE ? bigInteger3.inc() : bigInteger3;
                case 4:
                    return sign$bignum == Sign.POSITIVE ? bigInteger3 : bigInteger3.dec();
                case 5:
                    int i3 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                    return i3 != 1 ? (i3 == 2 && determineDecider != SignificantDecider.LESS_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider != SignificantDecider.LESS_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                case 6:
                    int i4 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                    return i4 != 1 ? (i4 == 2 && determineDecider == SignificantDecider.MORE_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider == SignificantDecider.MORE_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                case 7:
                    int i5 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                    return i5 != 1 ? (i5 == 2 && determineDecider == SignificantDecider.MORE_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider != SignificantDecider.LESS_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                case 8:
                    int i6 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                    return i6 != 1 ? (i6 == 2 && determineDecider != SignificantDecider.LESS_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider == SignificantDecider.MORE_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                case 9:
                    if (determineDecider == SignificantDecider.FIVE) {
                        if (Intrinsics.areEqual((Object) bigInteger.rem(2), (Object) companion.getONE())) {
                            int i7 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                            return i7 != 1 ? (i7 == 2 && determineDecider != SignificantDecider.LESS_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider != SignificantDecider.LESS_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                        }
                        int i8 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                        return i8 != 1 ? (i8 == 2 && determineDecider != SignificantDecider.LESS_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider == SignificantDecider.MORE_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                    } else if (determineDecider != SignificantDecider.MORE_THAN_FIVE) {
                        return bigInteger3;
                    } else {
                        if (sign$bignum == Sign.POSITIVE) {
                            bigInteger3 = bigInteger3.inc();
                        }
                        return sign$bignum == Sign.NEGATIVE ? bigInteger3.dec() : bigInteger3;
                    }
                case 10:
                    if (determineDecider == SignificantDecider.FIVE) {
                        if (Intrinsics.areEqual((Object) bigInteger.rem(2), (Object) companion.getZERO())) {
                            int i9 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                            return i9 != 1 ? (i9 == 2 && determineDecider != SignificantDecider.LESS_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider != SignificantDecider.LESS_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                        }
                        int i10 = WhenMappings.$EnumSwitchMapping$1[sign$bignum.ordinal()];
                        return i10 != 1 ? (i10 == 2 && determineDecider != SignificantDecider.LESS_THAN_FIVE) ? bigInteger3.dec() : bigInteger3 : determineDecider == SignificantDecider.MORE_THAN_FIVE ? bigInteger3.inc() : bigInteger3;
                    } else if (determineDecider != SignificantDecider.MORE_THAN_FIVE) {
                        return bigInteger3;
                    } else {
                        if (sign$bignum == Sign.POSITIVE) {
                            bigInteger3 = bigInteger3.inc();
                        }
                        return sign$bignum == Sign.NEGATIVE ? bigInteger3.dec() : bigInteger3;
                    }
                case 11:
                    throw new ArithmeticException("Non-terminating result of division operation. Specify decimalPrecision");
                default:
                    return bigInteger3;
            }
        }

        /* access modifiers changed from: private */
        public final BigDecimal roundOrDont(BigInteger bigInteger, long j2, DecimalMode decimalMode) {
            return decimalMode.isPrecisionUnlimited() ? new BigDecimal(bigInteger, j2, (DecimalMode) null, 4, (DefaultConstructorMarker) null) : roundSignificand(bigInteger, j2, decimalMode);
        }

        /* access modifiers changed from: private */
        public final BigDecimal roundSignificand(BigInteger bigInteger, long j2, DecimalMode decimalMode) {
            long j3;
            BigInteger.Companion companion = BigInteger.Companion;
            if (Intrinsics.areEqual((Object) bigInteger, (Object) companion.getZERO())) {
                return new BigDecimal(companion.getZERO(), j2, decimalMode, (DefaultConstructorMarker) null);
            }
            long numberOfDecimalDigits = bigInteger.numberOfDecimalDigits();
            if (decimalMode.getUsingScale()) {
                j3 = decimalMode.getScale() + decimalMode.getDecimalPrecision();
            } else {
                j3 = decimalMode.getDecimalPrecision();
            }
            int i3 = (j3 > numberOfDecimalDigits ? 1 : (j3 == numberOfDecimalDigits ? 0 : -1));
            if (i3 > 0) {
                return new BigDecimal((BigInteger) bigInteger.times((BigNumber) companion.getTEN().pow(j3 - numberOfDecimalDigits)), j2, decimalMode, (DefaultConstructorMarker) null);
            }
            if (i3 >= 0) {
                return new BigDecimal(bigInteger, j2, decimalMode, (DefaultConstructorMarker) null);
            }
            BigInteger.QuotientAndRemainder divrem = bigInteger.divrem(companion.getTEN().pow(numberOfDecimalDigits - j3));
            BigInteger remainder = divrem.getRemainder();
            if (Intrinsics.areEqual((Object) divrem.getRemainder(), (Object) companion.getZERO())) {
                return new BigDecimal(divrem.getQuotient(), j2, decimalMode, (DefaultConstructorMarker) null);
            }
            if (bigInteger.numberOfDecimalDigits() != divrem.getRemainder().numberOfDecimalDigits() + divrem.getQuotient().numberOfDecimalDigits()) {
                return handleZeroRounding(divrem.getQuotient(), j2, decimalMode);
            }
            BigInteger roundDiscarded = roundDiscarded(divrem.getQuotient(), remainder, decimalMode);
            return new BigDecimal(roundDiscarded, j2 + (roundDiscarded.numberOfDecimalDigits() - divrem.getQuotient().numberOfDecimalDigits()), decimalMode, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final BigDecimal fromBigDecimal(@NotNull BigDecimal bigDecimal, @Nullable DecimalMode decimalMode) {
            Intrinsics.checkNotNullParameter(bigDecimal, "bigDecimal");
            return new BigDecimal(bigDecimal.getSignificand(), bigDecimal.getExponent(), decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromBigIntegerWithExponent(@NotNull BigInteger bigInteger, long j2, @Nullable DecimalMode decimalMode) {
            Intrinsics.checkNotNullParameter(bigInteger, "bigInteger");
            return new BigDecimal(bigInteger, j2, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromByteAsSignificand(byte b3, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromByte(b3), 0, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromByteWithExponent(byte b3, long j2, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromByte(b3), j2, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromDouble(double d2, @Nullable DecimalMode decimalMode) {
            String str;
            String valueOf = String.valueOf(d2);
            if (!StringsKt__StringsKt.contains$default((CharSequence) valueOf, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR, false, 2, (Object) null) || StringsKt__StringsKt.contains((CharSequence) valueOf, 'E', true)) {
                return parseStringWithMode(valueOf, decimalMode).roundSignificand(decimalMode).roundSignificand(decimalMode);
            }
            int lastIndex = StringsKt.getLastIndex(valueOf);
            while (true) {
                if (lastIndex >= 0) {
                    if (valueOf.charAt(lastIndex) != '0') {
                        str = valueOf.substring(0, lastIndex + 1);
                        Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        break;
                    }
                    lastIndex--;
                } else {
                    str = "";
                    break;
                }
            }
            return parseStringWithMode(str, decimalMode).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromFloat(float f2, @Nullable DecimalMode decimalMode) {
            String str;
            String valueOf = String.valueOf(f2);
            if (!StringsKt__StringsKt.contains$default((CharSequence) valueOf, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR, false, 2, (Object) null) || StringsKt__StringsKt.contains((CharSequence) valueOf, 'E', true)) {
                return parseStringWithMode(valueOf, decimalMode).roundSignificand(decimalMode);
            }
            int lastIndex = StringsKt.getLastIndex(valueOf);
            while (true) {
                if (lastIndex >= 0) {
                    if (valueOf.charAt(lastIndex) != '0') {
                        str = valueOf.substring(0, lastIndex + 1);
                        Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        break;
                    }
                    lastIndex--;
                } else {
                    str = "";
                    break;
                }
            }
            return parseStringWithMode(str, decimalMode).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromIntAsSignificand(int i3, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromInt(i3), 0, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromIntWithExponent(int i3, long j2, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromInt(i3), j2, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromLongAsSignificand(long j2, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromLong(j2), 0, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromLongWithExponent(long j2, long j3, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromLong(j2), j3, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromShortAsSignificand(short s3, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromShort(s3), 0, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromShortWithExponent(short s3, long j2, @Nullable DecimalMode decimalMode) {
            return new BigDecimal(BigInteger.Companion.fromShort(s3), j2, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        /* renamed from: fromUByte-0ky7B_Q  reason: not valid java name */
        public final BigDecimal m8288fromUByte0ky7B_Q(byte b3, @Nullable DecimalMode decimalMode) {
            BigInteger r12 = BigInteger.Companion.m8308fromUByte7apg3OU(b3);
            return new BigDecimal(r12, r12.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        /* renamed from: fromUInt-qim9Vi0  reason: not valid java name */
        public final BigDecimal m8293fromUIntqim9Vi0(int i3, @Nullable DecimalMode decimalMode) {
            BigInteger r12 = BigInteger.Companion.m8312fromUIntWZ4Q5Ns(i3);
            return new BigDecimal(r12, r12.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        /* renamed from: fromULong-4PLdz1A  reason: not valid java name */
        public final BigDecimal m8294fromULong4PLdz1A(long j2, @Nullable DecimalMode decimalMode) {
            BigInteger r12 = BigInteger.Companion.m8314fromULongVKZWuLQ(j2);
            return new BigDecimal(r12, r12.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        /* renamed from: fromUShort-vckuEUM  reason: not valid java name */
        public final BigDecimal m8297fromUShortvckuEUM(short s3, @Nullable DecimalMode decimalMode) {
            BigInteger r12 = BigInteger.Companion.m8316fromUShortxj2QHRw(s3);
            return new BigDecimal(r12, r12.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        public final boolean getUseToStringExpanded() {
            return BigDecimal.useToStringExpanded;
        }

        @NotNull
        public final BigDecimal handleZeroRounding(@NotNull BigInteger bigInteger, long j2, @NotNull DecimalMode decimalMode) {
            BigDecimal bigDecimal;
            Intrinsics.checkNotNullParameter(bigInteger, "significand");
            Intrinsics.checkNotNullParameter(decimalMode, "decimalMode");
            if (bigInteger.getSign$bignum() == Sign.POSITIVE) {
                int i3 = WhenMappings.$EnumSwitchMapping$0[decimalMode.getRoundingMode().ordinal()];
                if (i3 != 1 && i3 != 3) {
                    return new BigDecimal(bigInteger, j2, decimalMode, (DefaultConstructorMarker) null);
                }
                BigInteger inc = bigInteger.inc();
                bigDecimal = new BigDecimal(inc, (inc.numberOfDecimalDigits() - bigInteger.numberOfDecimalDigits()) + j2, decimalMode, (DefaultConstructorMarker) null);
            } else if (bigInteger.getSign$bignum() != Sign.NEGATIVE) {
                return new BigDecimal(bigInteger, j2, decimalMode, (DefaultConstructorMarker) null);
            } else {
                int i4 = WhenMappings.$EnumSwitchMapping$0[decimalMode.getRoundingMode().ordinal()];
                if (i4 != 1 && i4 != 4) {
                    return new BigDecimal(bigInteger, j2, decimalMode, (DefaultConstructorMarker) null);
                }
                BigInteger dec = bigInteger.dec();
                bigDecimal = new BigDecimal(dec, (dec.numberOfDecimalDigits() - bigInteger.numberOfDecimalDigits()) + j2, decimalMode, (DefaultConstructorMarker) null);
            }
            return bigDecimal;
        }

        /* JADX WARNING: Removed duplicated region for block: B:122:0x024a  */
        /* JADX WARNING: Removed duplicated region for block: B:125:0x0250  */
        @org.jetbrains.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.ionspin.kotlin.bignum.decimal.BigDecimal parseStringWithMode(@org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.Nullable com.ionspin.kotlin.bignum.decimal.DecimalMode r18) {
            /*
                r16 = this;
                r0 = r17
                r1 = 2
                r2 = 0
                r3 = -1
                r4 = 1
                java.lang.String r5 = "floatingPointString"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r5)
                int r5 = r17.length()
                if (r5 != 0) goto L_0x0016
                com.ionspin.kotlin.bignum.decimal.BigDecimal r0 = r16.getZERO()
                return r0
            L_0x0016:
                boolean r5 = kotlin.text.StringsKt__StringsKt.contains((java.lang.CharSequence) r17, 'E', true)
                r6 = 6
                java.lang.String r7 = "Invalid (or unsupported) floating point number format: "
                java.lang.String r10 = "(this as java.lang.String).substring(startIndex)"
                r11 = 43
                java.lang.String r12 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
                java.lang.String r13 = "null cannot be cast to non-null type java.lang.String"
                r14 = 46
                r15 = 10
                r9 = 45
                if (r5 == 0) goto L_0x0181
                boolean r5 = kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r0, (char) r14, false, 2, (java.lang.Object) null)
                if (r5 != 0) goto L_0x0055
                char[] r5 = new char[r1]
                r5 = {69, 101} // fill-array
                java.util.List r5 = kotlin.text.StringsKt__StringsKt.split$default((java.lang.CharSequence) r0, (char[]) r5, false, (int) r2, (int) r6, (java.lang.Object) null)
                java.lang.Object r14 = r5.get(r2)
                java.lang.String r14 = (java.lang.String) r14
                java.lang.String r8 = "0E"
                java.lang.Object r5 = r5.get(r4)
                java.lang.String r5 = kotlin.jvm.internal.Intrinsics.stringPlus(r8, r5)
                java.lang.String[] r5 = new java.lang.String[]{r14, r5}
                java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
                goto L_0x005d
            L_0x0055:
                char[] r5 = new char[r4]
                r5[r2] = r14
                java.util.List r5 = kotlin.text.StringsKt__StringsKt.split$default((java.lang.CharSequence) r0, (char[]) r5, false, (int) r2, (int) r6, (java.lang.Object) null)
            L_0x005d:
                int r8 = r5.size()
                if (r8 != r1) goto L_0x0177
                char r7 = r0.charAt(r2)
                if (r7 == r9) goto L_0x0072
                char r7 = r0.charAt(r2)
                if (r7 != r11) goto L_0x0070
                goto L_0x0072
            L_0x0070:
                r7 = r2
                goto L_0x0073
            L_0x0072:
                r7 = r4
            L_0x0073:
                if (r7 == 0) goto L_0x0081
                char r0 = r0.charAt(r2)
                if (r0 != r9) goto L_0x007e
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
                goto L_0x0083
            L_0x007e:
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
                goto L_0x0083
            L_0x0081:
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            L_0x0083:
                java.lang.Object r8 = r5.get(r2)
                java.lang.String r8 = (java.lang.String) r8
                if (r8 == 0) goto L_0x0171
                java.lang.String r7 = r8.substring(r7)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r10)
                java.lang.Object r5 = r5.get(r4)
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5
                char[] r1 = new char[r1]
                r1 = {69, 101} // fill-array
                java.util.List r1 = kotlin.text.StringsKt__StringsKt.split$default((java.lang.CharSequence) r5, (char[]) r1, false, (int) r2, (int) r6, (java.lang.Object) null)
                java.lang.Object r5 = r1.get(r2)
                java.lang.String r5 = (java.lang.String) r5
                java.lang.Object r1 = r1.get(r4)
                java.lang.String r1 = (java.lang.String) r1
                char r6 = r1.charAt(r2)
                if (r6 == r9) goto L_0x00bc
                char r6 = r1.charAt(r2)
                if (r6 != r11) goto L_0x00ba
                goto L_0x00bc
            L_0x00ba:
                r6 = r2
                goto L_0x00bd
            L_0x00bc:
                r6 = r4
            L_0x00bd:
                char r8 = r1.charAt(r2)
                if (r8 != r9) goto L_0x00c6
                com.ionspin.kotlin.bignum.integer.Sign r8 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
                goto L_0x00c8
            L_0x00c6:
                com.ionspin.kotlin.bignum.integer.Sign r8 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            L_0x00c8:
                java.lang.String r1 = r1.substring(r6)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
                com.ionspin.kotlin.bignum.integer.Sign r6 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
                if (r8 != r6) goto L_0x00dc
                int r6 = kotlin.text.CharsKt.checkRadix(r15)
                long r8 = java.lang.Long.parseLong(r1, r6)
                goto L_0x00e6
            L_0x00dc:
                int r6 = kotlin.text.CharsKt.checkRadix(r15)
                long r8 = java.lang.Long.parseLong(r1, r6)
                long r10 = (long) r3
                long r8 = r8 * r10
            L_0x00e6:
                int r1 = r7.length()
                r6 = r2
            L_0x00eb:
                if (r6 >= r1) goto L_0x00f8
                char r10 = r7.charAt(r6)
                r11 = 48
                if (r10 == r11) goto L_0x00f6
                goto L_0x00f9
            L_0x00f6:
                int r6 = r6 + r4
                goto L_0x00eb
            L_0x00f8:
                r6 = r3
            L_0x00f9:
                if (r6 != r3) goto L_0x00fc
                r6 = r2
            L_0x00fc:
                int r1 = r5.length()
            L_0x0100:
                int r1 = r1 + r3
                if (r1 < 0) goto L_0x010c
                char r10 = r5.charAt(r1)
                r11 = 48
                if (r10 == r11) goto L_0x0100
                goto L_0x010d
            L_0x010c:
                r1 = r3
            L_0x010d:
                if (r1 != r3) goto L_0x0114
                int r1 = r5.length()
                int r1 = r1 - r4
            L_0x0114:
                int r3 = r7.length()
                java.lang.String r3 = r7.substring(r6, r3)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r12)
                int r1 = r1 + r4
                java.lang.String r1 = r5.substring(r2, r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r12)
                com.ionspin.kotlin.bignum.integer.BigInteger$Companion r2 = com.ionspin.kotlin.bignum.integer.BigInteger.Companion
                java.lang.String r4 = kotlin.jvm.internal.Intrinsics.stringPlus(r3, r1)
                com.ionspin.kotlin.bignum.integer.BigInteger r4 = r2.parseString((java.lang.String) r4, (int) r15)
                com.ionspin.kotlin.bignum.integer.BigInteger r2 = r2.getZERO()
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r2)
                if (r2 == 0) goto L_0x013d
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            L_0x013d:
                com.ionspin.kotlin.bignum.integer.Sign r2 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
                if (r0 != r2) goto L_0x0145
                com.ionspin.kotlin.bignum.integer.BigInteger r4 = r4.negate()
            L_0x0145:
                r11 = r4
                java.lang.String r0 = "0"
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)
                if (r0 != 0) goto L_0x0159
                int r0 = r3.length()
                long r0 = (long) r0
                long r8 = r8 + r0
                r2 = 1
            L_0x0156:
                long r8 = r8 - r2
                r12 = r8
                goto L_0x0167
            L_0x0159:
                r2 = 1
                int r0 = r1.length()
                long r0 = (long) r0
                long r4 = r11.numberOfDecimalDigits()
                long r0 = r0 - r4
                long r8 = r8 - r0
                goto L_0x0156
            L_0x0167:
                com.ionspin.kotlin.bignum.decimal.BigDecimal r0 = new com.ionspin.kotlin.bignum.decimal.BigDecimal
                r15 = 0
                r10 = r0
                r14 = r18
                r10.<init>(r11, r12, r14, r15)
                return r0
            L_0x0171:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException
                r0.<init>(r13)
                throw r0
            L_0x0177:
                java.lang.ArithmeticException r1 = new java.lang.ArithmeticException
                java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r7, r0)
                r1.<init>(r0)
                throw r1
            L_0x0181:
                boolean r5 = kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r0, (char) r14, false, 2, (java.lang.Object) null)
                if (r5 == 0) goto L_0x0270
                char[] r5 = new char[r4]
                r5[r2] = r14
                java.util.List r5 = kotlin.text.StringsKt__StringsKt.split$default((java.lang.CharSequence) r0, (char[]) r5, false, (int) r2, (int) r6, (java.lang.Object) null)
                int r6 = r5.size()
                if (r6 != r1) goto L_0x0266
                char r1 = r0.charAt(r2)
                if (r1 == r9) goto L_0x01a4
                char r1 = r0.charAt(r2)
                if (r1 != r11) goto L_0x01a2
                goto L_0x01a4
            L_0x01a2:
                r1 = r2
                goto L_0x01a5
            L_0x01a4:
                r1 = r4
            L_0x01a5:
                if (r1 == 0) goto L_0x01b3
                char r0 = r0.charAt(r2)
                if (r0 != r9) goto L_0x01b0
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
                goto L_0x01b5
            L_0x01b0:
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
                goto L_0x01b5
            L_0x01b3:
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.POSITIVE
            L_0x01b5:
                java.lang.Object r6 = r5.get(r2)
                java.lang.String r6 = (java.lang.String) r6
                if (r6 == 0) goto L_0x0260
                java.lang.String r1 = r6.substring(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
                java.lang.Object r5 = r5.get(r4)
                java.lang.String r5 = (java.lang.String) r5
                int r6 = r1.length()
                r7 = r2
            L_0x01cf:
                if (r7 >= r6) goto L_0x01dc
                char r8 = r1.charAt(r7)
                r9 = 48
                if (r8 == r9) goto L_0x01da
                goto L_0x01dd
            L_0x01da:
                int r7 = r7 + r4
                goto L_0x01cf
            L_0x01dc:
                r7 = r3
            L_0x01dd:
                if (r7 != r3) goto L_0x01e0
                r7 = r2
            L_0x01e0:
                int r6 = r5.length()
            L_0x01e4:
                int r6 = r6 + r3
                if (r6 < 0) goto L_0x01f0
                char r8 = r5.charAt(r6)
                r9 = 48
                if (r8 == r9) goto L_0x01e4
                goto L_0x01f1
            L_0x01f0:
                r6 = r3
            L_0x01f1:
                if (r6 != r3) goto L_0x01f8
                int r6 = r5.length()
                int r6 = r6 - r4
            L_0x01f8:
                int r8 = r1.length()
                java.lang.String r1 = r1.substring(r7, r8)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r12)
                int r6 = r6 + r4
                java.lang.String r5 = r5.substring(r2, r6)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r12)
                com.ionspin.kotlin.bignum.integer.BigInteger$Companion r6 = com.ionspin.kotlin.bignum.integer.BigInteger.Companion
                java.lang.String r7 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r5)
                com.ionspin.kotlin.bignum.integer.BigInteger r6 = r6.parseString((java.lang.String) r7, (int) r15)
                int r7 = r1.length()
                if (r7 <= 0) goto L_0x0229
                char r7 = r1.charAt(r2)
                r8 = 48
                if (r7 == r8) goto L_0x022b
                int r1 = r1.length()
                int r1 = r1 - r4
                goto L_0x023e
            L_0x0229:
                r8 = 48
            L_0x022b:
                int r1 = r5.length()
            L_0x022f:
                if (r2 >= r1) goto L_0x023a
                char r7 = r5.charAt(r2)
                if (r7 == r8) goto L_0x0238
                goto L_0x023b
            L_0x0238:
                int r2 = r2 + r4
                goto L_0x022f
            L_0x023a:
                r2 = r3
            L_0x023b:
                int r2 = r2 + r4
                int r1 = r2 * -1
            L_0x023e:
                com.ionspin.kotlin.bignum.integer.BigInteger$Companion r2 = com.ionspin.kotlin.bignum.integer.BigInteger.Companion
                com.ionspin.kotlin.bignum.integer.BigInteger r2 = r2.getZERO()
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r2)
                if (r2 == 0) goto L_0x024c
                com.ionspin.kotlin.bignum.integer.Sign r0 = com.ionspin.kotlin.bignum.integer.Sign.ZERO
            L_0x024c:
                com.ionspin.kotlin.bignum.integer.Sign r2 = com.ionspin.kotlin.bignum.integer.Sign.NEGATIVE
                if (r0 != r2) goto L_0x0254
                com.ionspin.kotlin.bignum.integer.BigInteger r6 = r6.negate()
            L_0x0254:
                r8 = r6
                com.ionspin.kotlin.bignum.decimal.BigDecimal r0 = new com.ionspin.kotlin.bignum.decimal.BigDecimal
                long r9 = (long) r1
                r12 = 0
                r7 = r0
                r11 = r18
                r7.<init>(r8, r9, r11, r12)
                return r0
            L_0x0260:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException
                r0.<init>(r13)
                throw r0
            L_0x0266:
                java.lang.ArithmeticException r1 = new java.lang.ArithmeticException
                java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r7, r0)
                r1.<init>(r0)
                throw r1
            L_0x0270:
                com.ionspin.kotlin.bignum.integer.BigInteger$Companion r1 = com.ionspin.kotlin.bignum.integer.BigInteger.Companion
                com.ionspin.kotlin.bignum.integer.BigInteger r3 = r1.parseString((java.lang.String) r0, (int) r15)
                com.ionspin.kotlin.bignum.decimal.BigDecimal r0 = new com.ionspin.kotlin.bignum.decimal.BigDecimal
                long r1 = r3.numberOfDecimalDigits()
                r4 = 1
                long r4 = r1 - r4
                r7 = 0
                r2 = r0
                r6 = r18
                r2.<init>(r3, r4, r6, r7)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ionspin.kotlin.bignum.decimal.BigDecimal.Companion.parseStringWithMode(java.lang.String, com.ionspin.kotlin.bignum.decimal.DecimalMode):com.ionspin.kotlin.bignum.decimal.BigDecimal");
        }

        public final void setUseToStringExpanded(boolean z2) {
            BigDecimal.useToStringExpanded = z2;
        }

        private Companion() {
        }

        @NotNull
        public final BigDecimal fromBigInteger(@NotNull BigInteger bigInteger, @Nullable DecimalMode decimalMode) {
            Intrinsics.checkNotNullParameter(bigInteger, "bigInteger");
            return new BigDecimal(bigInteger, bigInteger.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromByte(byte b3, @Nullable DecimalMode decimalMode) {
            BigInteger fromByte = BigInteger.Companion.fromByte(b3);
            return new BigDecimal(fromByte, fromByte.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromInt(int i3, @Nullable DecimalMode decimalMode) {
            BigInteger fromInt = BigInteger.Companion.fromInt(i3);
            return new BigDecimal(fromInt, fromInt.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromLong(long j2, @Nullable DecimalMode decimalMode) {
            BigInteger fromLong = BigInteger.Companion.fromLong(j2);
            return new BigDecimal(fromLong, fromLong.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        public final BigDecimal fromShort(short s3, @Nullable DecimalMode decimalMode) {
            BigInteger fromShort = BigInteger.Companion.fromShort(s3);
            return new BigDecimal(fromShort, fromShort.numberOfDecimalDigits() - 1, decimalMode, (DefaultConstructorMarker) null).roundSignificand(decimalMode);
        }

        @NotNull
        /* renamed from: fromUByte-7apg3OU  reason: not valid java name */
        public BigDecimal m8290fromUByte7apg3OU(byte b3) {
            return m8288fromUByte0ky7B_Q(b3, (DecimalMode) null);
        }

        @NotNull
        /* renamed from: fromUInt-WZ4Q5Ns  reason: not valid java name */
        public BigDecimal m8292fromUIntWZ4Q5Ns(int i3) {
            return m8293fromUIntqim9Vi0(i3, (DecimalMode) null);
        }

        @NotNull
        /* renamed from: fromULong-VKZWuLQ  reason: not valid java name */
        public BigDecimal m8296fromULongVKZWuLQ(long j2) {
            return m8294fromULong4PLdz1A(j2, (DecimalMode) null);
        }

        @NotNull
        /* renamed from: fromUShort-xj2QHRw  reason: not valid java name */
        public BigDecimal m8299fromUShortxj2QHRw(short s3) {
            return m8297fromUShortvckuEUM(s3, (DecimalMode) null);
        }

        @NotNull
        public BigDecimal getONE() {
            return BigDecimal.ONE;
        }

        @NotNull
        public BigDecimal getTEN() {
            return BigDecimal.TEN;
        }

        @NotNull
        public BigDecimal getTWO() {
            return BigDecimal.TWO;
        }

        @NotNull
        public BigDecimal getZERO() {
            return BigDecimal.ZERO;
        }

        @NotNull
        public BigDecimal parseString(@NotNull String str, int i3) {
            Intrinsics.checkNotNullParameter(str, "string");
            return parseStringWithMode(str, (DecimalMode) null);
        }

        @NotNull
        public BigDecimal tryFromDouble(double d2, boolean z2) {
            return fromDouble(d2, (DecimalMode) null);
        }

        @NotNull
        public BigDecimal tryFromFloat(float f2, boolean z2) {
            return fromFloat(f2, (DecimalMode) null);
        }

        @NotNull
        public final BigDecimal parseString(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "string");
            return parseStringWithMode$default(this, str, (DecimalMode) null, 2, (Object) null);
        }

        @NotNull
        public BigDecimal fromBigInteger(@NotNull BigInteger bigInteger) {
            Intrinsics.checkNotNullParameter(bigInteger, "bigInteger");
            return fromBigInteger(bigInteger, (DecimalMode) null);
        }

        @NotNull
        public BigDecimal fromByte(byte b3) {
            return fromByte(b3, (DecimalMode) null);
        }

        @NotNull
        public BigDecimal fromInt(int i3) {
            return fromInt(i3, (DecimalMode) null);
        }

        @NotNull
        public BigDecimal fromLong(long j2) {
            return fromLong(j2, (DecimalMode) null);
        }

        @NotNull
        public BigDecimal fromShort(short s3) {
            return fromShort(s3, (DecimalMode) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/ionspin/kotlin/bignum/decimal/BigDecimal$ScaleOps;", "", "(Ljava/lang/String;I)V", "Max", "Min", "Add", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public enum ScaleOps {
        Max,
        Min,
        Add
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScaleOps.values().length];
            iArr[ScaleOps.Max.ordinal()] = 1;
            iArr[ScaleOps.Min.ordinal()] = 2;
            iArr[ScaleOps.Add.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        Companion companion = new Companion((DefaultConstructorMarker) null);
        Companion = companion;
        BigInteger.Companion companion2 = BigInteger.Companion;
        ZERO = new BigDecimal(companion2.getZERO(), 0, (DecimalMode) null, 6, (DefaultConstructorMarker) null);
        ONE = new BigDecimal(companion2.getONE(), 0, (DecimalMode) null, 6, (DefaultConstructorMarker) null);
        TWO = new BigDecimal(companion2.getTWO(), 0, (DecimalMode) null, 6, (DefaultConstructorMarker) null);
        TEN = new BigDecimal(companion2.getTEN(), 1, (DecimalMode) null, 4, (DefaultConstructorMarker) null);
        maximumDouble = Companion.fromDouble$default(companion, Double.MAX_VALUE, (DecimalMode) null, 2, (Object) null);
        leastSignificantDouble = Companion.fromDouble$default(companion, Double.MIN_VALUE, (DecimalMode) null, 2, (Object) null);
        maximumFloat = Companion.fromFloat$default(companion, Float.MAX_VALUE, (DecimalMode) null, 2, (Object) null);
        leastSignificantFloat = Companion.fromFloat$default(companion, Float.MIN_VALUE, (DecimalMode) null, 2, (Object) null);
    }

    public /* synthetic */ BigDecimal(BigInteger bigInteger, long j2, DecimalMode decimalMode2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bigInteger, j2, decimalMode2);
    }

    public static /* synthetic */ BigDecimal add$default(BigDecimal bigDecimal, BigDecimal bigDecimal2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode2 = null;
        }
        return bigDecimal.add(bigDecimal2, decimalMode2);
    }

    private final Triple<BigInteger, BigInteger, Long> bringSignificandToSameExponent(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        BigDecimal bigDecimal3 = bigDecimal;
        BigDecimal bigDecimal4 = bigDecimal2;
        BigDecimal ridOfRadix = getRidOfRadix(bigDecimal);
        BigDecimal ridOfRadix2 = getRidOfRadix(bigDecimal4);
        long j2 = ridOfRadix.exponent;
        long j3 = ridOfRadix2.exponent;
        long j4 = bigDecimal3.exponent;
        long j5 = bigDecimal4.exponent;
        if (j4 > j5) {
            long j6 = j2 - j3;
            if (j6 >= 0) {
                return new Triple<>((BigInteger) ridOfRadix.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(j6)), bigDecimal4.significand, Long.valueOf(j3));
            }
            return new Triple<>(bigDecimal3.significand, (BigInteger) ridOfRadix2.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(j6 * ((long) -1))), Long.valueOf(j2));
        } else if (j4 < j5) {
            long j7 = j3 - j2;
            if (j7 < 0) {
                return new Triple<>((BigInteger) ridOfRadix.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(j7 * ((long) -1))), bigDecimal4.significand, Long.valueOf(j2));
            }
            return new Triple<>(bigDecimal3.significand, (BigInteger) ridOfRadix2.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(j7)), Long.valueOf(j2));
        } else if (j4 == j5) {
            long j8 = j2 - j3;
            int i3 = (j8 > 0 ? 1 : (j8 == 0 ? 0 : -1));
            if (i3 > 0) {
                return new Triple<>((BigInteger) bigDecimal3.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(j8)), bigDecimal4.significand, Long.valueOf(j2));
            }
            if (i3 < 0) {
                return new Triple<>(bigDecimal3.significand, (BigInteger) bigDecimal4.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(j8 * ((long) -1))), Long.valueOf(j2));
            } else if (Intrinsics.compare(j8, 0) == 0) {
                return new Triple<>(bigDecimal3.significand, bigDecimal4.significand, Long.valueOf(j2));
            } else {
                throw new RuntimeException(Intrinsics.stringPlus("Invalid delta: ", Long.valueOf(j8)));
            }
        } else {
            throw new RuntimeException("Invalid comparison state BigInteger: " + bigDecimal3.exponent + ", " + bigDecimal4.exponent);
        }
    }

    private final void checkWholeness(boolean z2) {
        if (z2 && !isWholeNumber()) {
            throw new ArithmeticException("Cannot convert to int and provide exact value");
        }
    }

    private final DecimalMode computeMode(BigDecimal bigDecimal, ScaleOps scaleOps) {
        DecimalMode decimalMode2;
        long j2;
        DecimalMode decimalMode3 = this.decimalMode;
        if (decimalMode3 == null || decimalMode3.isPrecisionUnlimited() || (decimalMode2 = bigDecimal.decimalMode) == null || decimalMode2.isPrecisionUnlimited()) {
            return DecimalMode.Companion.getDEFAULT();
        }
        long max = Math.max(this.decimalMode.getDecimalPrecision(), bigDecimal.decimalMode.getDecimalPrecision());
        RoundingMode roundingMode2 = this.decimalMode.getRoundingMode();
        if (!this.decimalMode.getUsingScale() || !bigDecimal.decimalMode.getUsingScale()) {
            j2 = -1;
        } else {
            int i3 = WhenMappings.$EnumSwitchMapping$0[scaleOps.ordinal()];
            if (i3 == 1) {
                j2 = Math.max(this.decimalMode.getScale(), bigDecimal.decimalMode.getScale());
            } else if (i3 == 2) {
                j2 = Math.min(this.decimalMode.getScale(), bigDecimal.decimalMode.getScale());
            } else if (i3 == 3) {
                j2 = bigDecimal.decimalMode.getScale() + this.decimalMode.getScale();
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        return new DecimalMode(max, roundingMode2, j2);
    }

    public static /* synthetic */ BigDecimal copy$default(BigDecimal bigDecimal, BigInteger bigInteger, long j2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            bigInteger = bigDecimal.significand;
        }
        if ((i3 & 2) != 0) {
            j2 = bigDecimal.exponent;
        }
        if ((i3 & 4) != 0) {
            decimalMode2 = bigDecimal.decimalMode;
        }
        return bigDecimal.copy(bigInteger, j2, decimalMode2);
    }

    public static /* synthetic */ BigDecimal divide$default(BigDecimal bigDecimal, BigDecimal bigDecimal2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode2 = null;
        }
        return bigDecimal.divide(bigDecimal2, decimalMode2);
    }

    private final Pair<BigDecimal, BigDecimal> divrem(BigDecimal bigDecimal, DecimalMode decimalMode2) {
        Companion companion = Companion;
        DecimalMode access$resolveDecimalMode = companion.resolveDecimalMode(this.decimalMode, bigDecimal.decimalMode, decimalMode2);
        long max = Math.max(this.exponent, bigDecimal.exponent);
        return new Pair<>(companion.roundOrDont((BigInteger) this.significand.div((BigNumber) bigDecimal.significand), max, access$resolveDecimalMode), companion.roundOrDont((BigInteger) this.significand.rem((BigNumber) bigDecimal.significand), max, access$resolveDecimalMode));
    }

    public static /* synthetic */ Pair divrem$default(BigDecimal bigDecimal, BigDecimal bigDecimal2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode2 = null;
        }
        return bigDecimal.divrem(bigDecimal2, decimalMode2);
    }

    private final BigDecimal getRidOfRadix(BigDecimal bigDecimal) {
        long numberOfDecimalDigits = bigDecimal.significand.numberOfDecimalDigits();
        return new BigDecimal(bigDecimal.significand, (bigDecimal.exponent - numberOfDecimalDigits) + 1, (DecimalMode) null, 4, (DefaultConstructorMarker) null);
    }

    private final BigDecimal integerDiv(BigDecimal bigDecimal, DecimalMode decimalMode2) {
        Companion companion = Companion;
        DecimalMode access$resolveDecimalMode = companion.resolveDecimalMode(this.decimalMode, bigDecimal.decimalMode, decimalMode2);
        return companion.roundOrDont((BigInteger) this.significand.div((BigNumber) bigDecimal.significand), this.exponent - bigDecimal.exponent, access$resolveDecimalMode);
    }

    public static /* synthetic */ BigDecimal integerDiv$default(BigDecimal bigDecimal, BigDecimal bigDecimal2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode2 = null;
        }
        return bigDecimal.integerDiv(bigDecimal2, decimalMode2);
    }

    private final int javascriptNumberComparison(Number number) {
        return number.floatValue() % ((float) 1) == 0.0f ? compare(Companion.fromLong(number.longValue())) : compare(BigDecimalExtensionsKt.toBigDecimal$default(number.floatValue(), (Long) null, (DecimalMode) null, 3, (Object) null));
    }

    public static /* synthetic */ BigDecimal multiply$default(BigDecimal bigDecimal, BigDecimal bigDecimal2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode2 = null;
        }
        return bigDecimal.multiply(bigDecimal2, decimalMode2);
    }

    private final String noExponentStringtoScientificNotation(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(placeADotInString(str, str.length() - 1));
        sb.append("E+");
        sb.append(str.length() - 1);
        return sb.toString();
    }

    private final String placeADotInString(String str, int i3) {
        String str2 = StringsKt.substring(str, RangesKt.until(0, str.length() - i3)) + ClassUtils.PACKAGE_SEPARATOR_CHAR + StringsKt.substring(str, RangesKt.until(str.length() - i3, str.length()));
        int lastIndex = StringsKt.getLastIndex(str2);
        while (lastIndex >= 0) {
            if (str2.charAt(lastIndex) == '0') {
                lastIndex--;
            } else {
                String substring = str2.substring(0, lastIndex + 1);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return "";
    }

    private final String placeADotInStringExpanded(String str, int i3) {
        String str2;
        String substring = StringsKt.substring(str, RangesKt.until(0, str.length() - i3));
        String substring2 = StringsKt.substring(str, RangesKt.until(str.length() - i3, str.length()));
        int lastIndex = StringsKt.getLastIndex(substring2);
        while (true) {
            if (lastIndex >= 0) {
                if (substring2.charAt(lastIndex) != '0') {
                    str2 = substring2.substring(0, lastIndex + 1);
                    Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    break;
                }
                lastIndex--;
            } else {
                str2 = "";
                break;
            }
        }
        if (str2.length() <= 0) {
            return substring;
        }
        return substring + ClassUtils.PACKAGE_SEPARATOR_CHAR + str2;
    }

    public static /* synthetic */ BigDecimal rem$default(BigDecimal bigDecimal, BigDecimal bigDecimal2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode2 = null;
        }
        return bigDecimal.rem(bigDecimal2, decimalMode2);
    }

    private final BigDecimal removeTrailingZeroes(BigDecimal bigDecimal) {
        if (Intrinsics.areEqual((Object) bigDecimal, (Object) ZERO)) {
            return this;
        }
        BigInteger bigInteger = bigDecimal.significand;
        BigInteger.QuotientAndRemainder quotientAndRemainder = new BigInteger.QuotientAndRemainder(bigInteger, BigInteger.Companion.getZERO());
        while (true) {
            BigInteger quotient = quotientAndRemainder.getQuotient();
            BigInteger.Companion companion = BigInteger.Companion;
            quotientAndRemainder = quotient.divrem(companion.getTEN());
            if (Intrinsics.areEqual((Object) quotientAndRemainder.getRemainder(), (Object) companion.getZERO())) {
                bigInteger = quotientAndRemainder.getQuotient();
            }
            BigInteger bigInteger2 = bigInteger;
            if (!Intrinsics.areEqual((Object) quotientAndRemainder.getRemainder(), (Object) companion.getZERO())) {
                return new BigDecimal(bigInteger2, bigDecimal.exponent, (DecimalMode) null, 4, (DefaultConstructorMarker) null);
            }
            bigInteger = bigInteger2;
        }
    }

    public static /* synthetic */ BigDecimal roundToDigitPosition$default(BigDecimal bigDecimal, long j2, RoundingMode roundingMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            roundingMode2 = bigDecimal.roundingMode;
        }
        return bigDecimal.roundToDigitPosition(j2, roundingMode2);
    }

    public static /* synthetic */ BigDecimal subtract$default(BigDecimal bigDecimal, BigDecimal bigDecimal2, DecimalMode decimalMode2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode2 = null;
        }
        return bigDecimal.subtract(bigDecimal2, decimalMode2);
    }

    public byte byteValue(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().byteValue(z2);
    }

    @NotNull
    public final BigDecimal ceil() {
        return roundSignificand(new DecimalMode(this.exponent + 1, RoundingMode.CEILING, 0, 4, (DefaultConstructorMarker) null));
    }

    public final int compare(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        if (this.exponent == bigDecimal.exponent && this.precision == bigDecimal.precision) {
            return this.significand.compare(bigDecimal.significand);
        }
        Triple<BigInteger, BigInteger, Long> bringSignificandToSameExponent = bringSignificandToSameExponent(this, bigDecimal);
        return bringSignificandToSameExponent.component1().compare(bringSignificandToSameExponent.component2());
    }

    public int compareTo(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "other");
        if (obj instanceof Number) {
            Number number = (Number) obj;
            if (ComparisonWorkaround.INSTANCE.isSpecialHandlingForFloatNeeded(number)) {
                return javascriptNumberComparison(number);
            }
        }
        if (obj instanceof BigDecimal) {
            return compare((BigDecimal) obj);
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
        if (obj instanceof Double) {
            return compare(Companion.fromDouble$default(Companion, ((Number) obj).doubleValue(), (DecimalMode) null, 2, (Object) null));
        }
        if (obj instanceof Float) {
            return compare(Companion.fromFloat$default(Companion, ((Number) obj).floatValue(), (DecimalMode) null, 2, (Object) null));
        }
        throw new RuntimeException(Intrinsics.stringPlus("Invalid comparison type for BigDecimal: ", Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName()));
    }

    @NotNull
    public final BigDecimal copy(@NotNull BigInteger bigInteger, long j2, @Nullable DecimalMode decimalMode2) {
        Intrinsics.checkNotNullParameter(bigInteger, "significand");
        return new BigDecimal(bigInteger, j2, decimalMode2);
    }

    @NotNull
    public final BigDecimal dec() {
        return (BigDecimal) minus(1);
    }

    public double doubleValue(boolean z2) {
        int i3;
        boolean z3 = z2;
        if (z3) {
            long j2 = this.exponent;
            boolean z4 = j2 >= -324 && j2 <= 308;
            if ((this.precision - j2) - 1 > 0) {
                int r2 = ConfigurationKt.getChosenArithmetic().m8363bitLengthQwZRm1k((j2 >= 0 ? (BigInteger) this.significand.div((BigNumber) BigInteger.Companion.getTEN().pow((this.precision - this.exponent) - 1)) : BigInteger.Companion.getZERO()).m8301getMagnitudeY2RjT0g$bignum());
                BigDecimal bigDecimal = new BigDecimal(this.significand.divrem(BigInteger.Companion.getTEN().pow((this.precision - this.exponent) - 1)).getRemainder(), -1, (DecimalMode) null, 4, (DefaultConstructorMarker) null);
                ArrayList arrayList = new ArrayList();
                int i4 = 0;
                while (!Intrinsics.areEqual((Object) bigDecimal, (Object) ZERO) && i4 <= 53) {
                    bigDecimal = (BigDecimal) bigDecimal.times(2);
                    int i5 = bigDecimal.compareTo(ONE) >= 0 ? 1 : 0;
                    arrayList.add(Integer.valueOf(i5));
                    if (i5 == 1) {
                        bigDecimal = bigDecimal.divrem(TEN).getSecond();
                    }
                    i4++;
                }
                i3 = arrayList.size() + r2;
            } else {
                i3 = ConfigurationKt.getChosenArithmetic().m8363bitLengthQwZRm1k(this.significand.m8301getMagnitudeY2RjT0g$bignum()) - ConfigurationKt.getChosenArithmetic().m8397trailingZeroBitsQwZRm1k(this.significand.m8301getMagnitudeY2RjT0g$bignum());
            }
            if (!(i3 > 53 ? false : z4)) {
                throw new ArithmeticException("Value cannot be narrowed to float");
            }
        }
        long j3 = (this.precision - 1) - this.exponent;
        long longValue = this.significand.longValue(z3);
        double d2 = (double) longValue;
        if (((long) d2) == longValue && j3 >= 0) {
            double[] dArr = double10pow;
            if (j3 < ((long) dArr.length)) {
                return (d2 / dArr[(int) j3]) * ((double) signum());
            }
        }
        return Double.parseDouble(toString());
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof BigDecimal ? compare((BigDecimal) obj) : obj instanceof Long ? compare(Companion.fromLong(((Number) obj).longValue())) : obj instanceof Integer ? compare(Companion.fromInt(((Number) obj).intValue())) : obj instanceof Short ? compare(Companion.fromShort(((Number) obj).shortValue())) : obj instanceof Byte ? compare(Companion.fromByte(((Number) obj).byteValue())) : obj instanceof Double ? compare(Companion.fromDouble$default(Companion, ((Number) obj).doubleValue(), (DecimalMode) null, 2, (Object) null)) : obj instanceof Float ? compare(Companion.fromFloat$default(Companion, ((Number) obj).floatValue(), (DecimalMode) null, 2, (Object) null)) : -1) == 0;
    }

    public float floatValue(boolean z2) {
        int i3;
        boolean z3 = z2;
        if (z3) {
            long j2 = this.exponent;
            boolean z4 = j2 >= -45 && j2 <= 38;
            if ((this.precision - j2) - 1 > 0) {
                int r2 = ConfigurationKt.getChosenArithmetic().m8363bitLengthQwZRm1k((j2 >= 0 ? (BigInteger) this.significand.div((BigNumber) BigInteger.Companion.getTEN().pow((this.precision - this.exponent) - 1)) : BigInteger.Companion.getZERO()).m8301getMagnitudeY2RjT0g$bignum());
                BigDecimal bigDecimal = new BigDecimal(this.significand.divrem(BigInteger.Companion.getTEN().pow((this.precision - this.exponent) - 1)).getRemainder(), -1, (DecimalMode) null, 4, (DefaultConstructorMarker) null);
                ArrayList arrayList = new ArrayList();
                int i4 = 0;
                while (!Intrinsics.areEqual((Object) bigDecimal, (Object) ZERO) && i4 <= 24) {
                    bigDecimal = (BigDecimal) bigDecimal.times(2);
                    int i5 = bigDecimal.compareTo(ONE) >= 0 ? 1 : 0;
                    arrayList.add(Integer.valueOf(i5));
                    if (i5 == 1) {
                        bigDecimal = bigDecimal.divrem(TEN).getSecond();
                    }
                    i4++;
                }
                i3 = arrayList.size() + r2;
            } else {
                i3 = ConfigurationKt.getChosenArithmetic().m8363bitLengthQwZRm1k(this.significand.m8301getMagnitudeY2RjT0g$bignum()) - ConfigurationKt.getChosenArithmetic().m8397trailingZeroBitsQwZRm1k(this.significand.m8301getMagnitudeY2RjT0g$bignum());
            }
            if (!(i3 > 24 ? false : z4)) {
                throw new ArithmeticException("Value cannot be narrowed to float");
            }
        }
        long j3 = (this.precision - 1) - this.exponent;
        long longValue = this.significand.longValue(z3);
        if (j3 >= 0) {
            float[] fArr = float10pow;
            if (j3 < ((long) fArr.length)) {
                return ((float) longValue) / fArr[(int) j3];
            }
        }
        return Float.parseFloat(toString());
    }

    @NotNull
    public final BigDecimal floor() {
        return roundSignificand(new DecimalMode(this.exponent + 1, RoundingMode.FLOOR, 0, 4, (DefaultConstructorMarker) null));
    }

    @NotNull
    public BigNumber.Creator<BigDecimal> getCreator() {
        return Companion;
    }

    @Nullable
    public final DecimalMode getDecimalMode() {
        return this.decimalMode;
    }

    public final long getExponent() {
        return this.exponent;
    }

    @NotNull
    public BigDecimal getInstance() {
        return this;
    }

    public final long getPrecision() {
        return this.precision;
    }

    public final long getPrecisionLimit() {
        return this.precisionLimit;
    }

    @NotNull
    public final RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    public final long getScale() {
        return this.scale;
    }

    @NotNull
    public final BigInteger getSignificand() {
        return this.significand;
    }

    public final boolean getUsingScale() {
        return this.usingScale;
    }

    public int hashCode() {
        if (Intrinsics.areEqual((Object) this, (Object) ZERO)) {
            return 0;
        }
        return Long.hashCode(this.exponent) + removeTrailingZeroes(this).significand.hashCode();
    }

    @NotNull
    public final BigDecimal inc() {
        return (BigDecimal) plus(1);
    }

    public int intValue(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().intValue(z2);
    }

    public boolean isNegative() {
        return BigNumber.DefaultImpls.isNegative(this);
    }

    public boolean isPositive() {
        return BigNumber.DefaultImpls.isPositive(this);
    }

    public final boolean isWholeNumber() {
        return abs().divrem(ONE).getSecond().isZero();
    }

    public boolean isZero() {
        return this.significand.isZero();
    }

    public long longValue(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().longValue(z2);
    }

    @NotNull
    public final BigDecimal moveDecimalPoint(int i3) {
        if (i3 == 0) {
            return this;
        }
        return copy$default(this, (BigInteger) null, this.exponent + ((long) i3), (DecimalMode) null, 5, (Object) null);
    }

    public long numberOfDecimalDigits() {
        long j2 = this.precision;
        long j3 = this.exponent;
        if (1 <= j3 && j3 < j2) {
            return j2;
        }
        if (j3 > 0 && j3 > j2) {
            return j3 + 1;
        }
        if (j3 > 0 && j3 == j2) {
            return j2 + 1;
        }
        if (j3 < 0) {
            return Math.abs(j3) + this.precision;
        }
        if (j3 == 0) {
            return removeTrailingZeroes(this).precision;
        }
        throw new RuntimeException("Invalid case when getting number of decimal digits");
    }

    @NotNull
    public final BigDecimal removeScale() {
        BigInteger bigInteger = this.significand;
        long j2 = this.exponent;
        DecimalMode decimalMode2 = this.decimalMode;
        long decimalPrecision = decimalMode2 == null ? 0 : decimalMode2.getDecimalPrecision();
        DecimalMode decimalMode3 = this.decimalMode;
        RoundingMode roundingMode2 = decimalMode3 == null ? null : decimalMode3.getRoundingMode();
        if (roundingMode2 == null) {
            roundingMode2 = RoundingMode.NONE;
        }
        return new BigDecimal(bigInteger, j2, new DecimalMode(decimalPrecision, roundingMode2, -1));
    }

    @NotNull
    public final BigDecimal roundSignificand(@Nullable DecimalMode decimalMode2) {
        return decimalMode2 == null ? this : Companion.roundSignificand(this.significand, this.exponent, decimalMode2);
    }

    @NotNull
    public final BigDecimal roundToDigitPosition(long j2, @NotNull RoundingMode roundingMode2) {
        Intrinsics.checkNotNullParameter(roundingMode2, "roundingMode");
        if (j2 != 0) {
            BigDecimal roundSignificand = this.exponent >= 0 ? roundSignificand(new DecimalMode(j2, roundingMode2, 0, 4, (DefaultConstructorMarker) null)) : (BigDecimal) ((BigDecimal) plus(signum())).roundSignificand(new DecimalMode(j2, roundingMode2, 0, 4, (DefaultConstructorMarker) null)).minus(signum());
            DecimalMode decimalMode2 = this.decimalMode;
            return decimalMode2 == null ? new BigDecimal(roundSignificand.significand, roundSignificand.exponent, (DecimalMode) null, 4, (DefaultConstructorMarker) null) : new BigDecimal(roundSignificand.significand, roundSignificand.exponent, decimalMode2);
        }
        throw new ArithmeticException("Rounding to 0 position is not supported");
    }

    @NotNull
    public final BigDecimal roundToDigitPositionAfterDecimalPoint(long j2, @NotNull RoundingMode roundingMode2) {
        BigDecimal bigDecimal;
        Intrinsics.checkNotNullParameter(roundingMode2, "roundingMode");
        if (j2 >= 0) {
            long j3 = this.exponent;
            if (j3 >= 0) {
                bigDecimal = roundToDigitPosition(j3 + j2 + 1, roundingMode2);
            } else if (j3 < 0) {
                bigDecimal = roundToDigitPosition(j2 + 1, roundingMode2);
            } else {
                throw new RuntimeException("Unexpected state");
            }
            DecimalMode decimalMode2 = this.decimalMode;
            return decimalMode2 == null ? new BigDecimal(bigDecimal.significand, bigDecimal.exponent, (DecimalMode) null, 4, (DefaultConstructorMarker) null) : new BigDecimal(bigDecimal.significand, bigDecimal.exponent, decimalMode2);
        }
        throw new ArithmeticException("This method doesn't support negative digit position");
    }

    @NotNull
    public final BigDecimal scale(long j2) {
        if (j2 >= 0) {
            return new BigDecimal(this.significand, this.exponent, this.decimalMode == null ? j2 == -1 ? DecimalMode.Companion.getDEFAULT() : new DecimalMode(0, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO, j2) : new DecimalMode(this.decimalMode.getDecimalPrecision() - this.decimalMode.getScale(), this.decimalMode.getRoundingMode(), j2));
        }
        throw new ArithmeticException("Negative Scale is unsupported.");
    }

    public void secureOverwrite() {
        this.significand.secureOverwrite();
    }

    public short shortValue(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().shortValue(z2);
    }

    public int signum() {
        return this.significand.signum();
    }

    @NotNull
    public final BigInteger toBigInteger() {
        long j2 = this.exponent;
        if (j2 < 0) {
            return BigInteger.Companion.getZERO();
        }
        long j3 = j2 - this.precision;
        int i3 = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
        return i3 > 0 ? (BigInteger) this.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(j3 + 1)) : i3 < 0 ? (BigInteger) this.significand.div((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(Math.abs(j3) - 1)) : this.significand;
    }

    @NotNull
    public final String toPlainString() {
        return toStringExpanded();
    }

    @NotNull
    public String toString(int i3) {
        if (i3 == 10) {
            return toString();
        }
        throw new RuntimeException("BigDecimal in base other than 10 is not supported yet");
    }

    @NotNull
    public final String toStringExpanded() {
        String str;
        if (Intrinsics.areEqual((Object) this, (Object) ZERO)) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
        long numberOfDecimalDigits = this.significand.numberOfDecimalDigits();
        if (this.exponent <= SieveCacheKt.NodeLinkMask) {
            String stringWithoutSign$bignum = this.significand.toStringWithoutSign$bignum(10);
            String str2 = this.significand.getSign$bignum() == Sign.NEGATIVE ? "-" : "";
            long j2 = this.exponent;
            if (j2 > 0) {
                long j3 = (j2 - numberOfDecimalDigits) + 1;
                str = j3 > 0 ? Intrinsics.stringPlus(stringWithoutSign$bignum, times(j3, '0')) : placeADotInStringExpanded(stringWithoutSign$bignum, (stringWithoutSign$bignum.length() - ((int) this.exponent)) - 1);
            } else if (j2 < 0) {
                int abs = Math.abs((int) j2);
                str = abs > 0 ? placeADotInStringExpanded(Intrinsics.stringPlus(times(Math.abs(this.exponent), '0'), stringWithoutSign$bignum), (stringWithoutSign$bignum.length() + abs) - 1) : placeADotInStringExpanded(stringWithoutSign$bignum, stringWithoutSign$bignum.length() - 1);
            } else if (j2 != 0) {
                throw new RuntimeException("Invalid state, please report a bug (Integer compareTo invalid)");
            } else if (numberOfDecimalDigits == 1) {
                return Intrinsics.stringPlus(str2, stringWithoutSign$bignum);
            } else {
                str = placeADotInStringExpanded(stringWithoutSign$bignum, stringWithoutSign$bignum.length() - 1);
            }
            return Intrinsics.stringPlus(str2, str);
        }
        throw new RuntimeException("Invalid toStringExpanded request (exponent > Int.MAX_VALUE)");
    }

    /* renamed from: ubyteValue-Wa3L5BU  reason: not valid java name */
    public byte m8280ubyteValueWa3L5BU(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().m8303ubyteValueWa3L5BU(z2);
    }

    /* renamed from: uintValue-OGnWXxg  reason: not valid java name */
    public int m8281uintValueOGnWXxg(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().m8304uintValueOGnWXxg(z2);
    }

    /* renamed from: ulongValue-I7RO_PI  reason: not valid java name */
    public long m8282ulongValueI7RO_PI(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().m8305ulongValueI7RO_PI(z2);
    }

    /* renamed from: ushortValue-BwKQO78  reason: not valid java name */
    public short m8283ushortValueBwKQO78(boolean z2) {
        checkWholeness(z2);
        return toBigInteger().m8306ushortValueBwKQO78(z2);
    }

    private BigDecimal(BigInteger bigInteger, long j2, DecimalMode decimalMode2) {
        if (decimalMode2 == null || !decimalMode2.getUsingScale()) {
            this.significand = bigInteger;
            this.precision = bigInteger.numberOfDecimalDigits();
            this.exponent = j2;
            this.decimalMode = decimalMode2;
        } else {
            BigDecimal access$applyScale = Companion.applyScale(bigInteger, j2, decimalMode2);
            if (!access$applyScale.isZero()) {
                BigInteger bigInteger2 = access$applyScale.significand;
                this.significand = bigInteger2;
                this.exponent = access$applyScale.exponent;
                long numberOfDecimalDigits = bigInteger2.numberOfDecimalDigits();
                this.precision = numberOfDecimalDigits;
                this.decimalMode = DecimalMode.copy$default(decimalMode2, numberOfDecimalDigits, (RoundingMode) null, 0, 6, (Object) null);
            } else {
                this.significand = access$applyScale.significand;
                this.exponent = (decimalMode2.getScale() + decimalMode2.getDecimalPrecision()) * access$applyScale.exponent;
                long scale2 = decimalMode2.getScale() + decimalMode2.getDecimalPrecision();
                this.precision = scale2;
                this.decimalMode = DecimalMode.copy$default(decimalMode2, scale2, (RoundingMode) null, 0, 6, (Object) null);
            }
        }
        DecimalMode decimalMode3 = this.decimalMode;
        this.precisionLimit = decimalMode3 == null ? 0 : decimalMode3.getDecimalPrecision();
        DecimalMode decimalMode4 = this.decimalMode;
        RoundingMode roundingMode2 = decimalMode4 == null ? null : decimalMode4.getRoundingMode();
        this.roundingMode = roundingMode2 == null ? RoundingMode.NONE : roundingMode2;
        DecimalMode decimalMode5 = this.decimalMode;
        long scale3 = decimalMode5 == null ? -1 : decimalMode5.getScale();
        this.scale = scale3;
        this.usingScale = scale3 >= 0;
    }

    @NotNull
    public BigDecimal abs() {
        return new BigDecimal(this.significand.abs(), this.exponent, this.decimalMode);
    }

    @NotNull
    public BigDecimal add(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return add(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public BigDecimal divide(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return divide(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public Pair<BigDecimal, BigDecimal> divideAndRemainder(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        DecimalMode decimalMode2 = this.decimalMode;
        if (decimalMode2 == null) {
            decimalMode2 = new DecimalMode(this.exponent + 1, RoundingMode.FLOOR, 0, 4, (DefaultConstructorMarker) null);
        }
        BigDecimal divide = divide(bigDecimal, decimalMode2);
        return new Pair<>(divide, minus(copy$default(divide, (BigInteger) null, 0, DecimalMode.Companion.getDEFAULT(), 3, (Object) null).times(bigDecimal)));
    }

    @NotNull
    public final BigDecimal moveDecimalPoint(long j2) {
        if (j2 == 0) {
            return this;
        }
        return copy$default(this, (BigInteger) null, this.exponent + j2, (DecimalMode) null, 5, (Object) null);
    }

    @NotNull
    public BigDecimal multiply(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return multiply(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public BigDecimal negate() {
        return new BigDecimal(this.significand.negate(), this.exponent, this.decimalMode);
    }

    @NotNull
    public BigDecimal remainder(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return divideAndRemainder(bigDecimal).getSecond();
    }

    @NotNull
    public BigDecimal subtract(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return subtract(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public BigDecimal unaryMinus() {
        return new BigDecimal(this.significand.negate(), this.exponent, (DecimalMode) null, 4, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final BigDecimal add(@NotNull BigDecimal bigDecimal, @Nullable DecimalMode decimalMode2) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        Companion companion = Companion;
        DecimalMode access$resolveDecimalMode = companion.resolveDecimalMode(this.decimalMode, bigDecimal.decimalMode, decimalMode2);
        BigDecimal bigDecimal2 = ZERO;
        if (Intrinsics.areEqual((Object) this, (Object) bigDecimal2)) {
            return companion.roundOrDont(bigDecimal.significand, bigDecimal.exponent, access$resolveDecimalMode);
        }
        if (Intrinsics.areEqual((Object) bigDecimal, (Object) bigDecimal2)) {
            return companion.roundOrDont(this.significand, this.exponent, access$resolveDecimalMode);
        }
        Triple<BigInteger, BigInteger, Long> bringSignificandToSameExponent = bringSignificandToSameExponent(this, bigDecimal);
        BigInteger component1 = bringSignificandToSameExponent.component1();
        BigInteger component2 = bringSignificandToSameExponent.component2();
        long numberOfDecimalDigits = component1.numberOfDecimalDigits();
        long numberOfDecimalDigits2 = component2.numberOfDecimalDigits();
        BigInteger bigInteger = (BigInteger) component1.plus((BigNumber) component2);
        long numberOfDecimalDigits3 = bigInteger.numberOfDecimalDigits();
        if (numberOfDecimalDigits <= numberOfDecimalDigits2) {
            numberOfDecimalDigits = numberOfDecimalDigits2;
        }
        long max = Math.max(this.exponent, bigDecimal.exponent) + (numberOfDecimalDigits3 - numberOfDecimalDigits);
        if (access$resolveDecimalMode.getUsingScale()) {
            return companion.roundOrDont(bigInteger, max, DecimalMode.copy$default(access$resolveDecimalMode, numberOfDecimalDigits3, (RoundingMode) null, 0, 6, (Object) null));
        }
        return companion.roundOrDont(bigInteger, max, access$resolveDecimalMode);
    }

    @NotNull
    public final BigDecimal divide(@NotNull BigDecimal bigDecimal, @Nullable DecimalMode decimalMode2) {
        BigInteger bigInteger;
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        Companion companion = Companion;
        DecimalMode access$resolveDecimalMode = companion.resolveDecimalMode(this.decimalMode, bigDecimal.decimalMode, decimalMode2);
        if (access$resolveDecimalMode.isPrecisionUnlimited()) {
            long j2 = this.exponent - bigDecimal.exponent;
            BigInteger bigInteger2 = this.significand;
            BigInteger.Companion companion2 = BigInteger.Companion;
            BigInteger bigInteger3 = (BigInteger) bigInteger2.times((BigNumber) companion2.getTEN().pow((bigDecimal.precision * ((long) 2)) + ((long) 6)));
            BigInteger.QuotientAndRemainder divrem = bigInteger3.divrem(bigDecimal.significand);
            BigInteger quotient = divrem.getQuotient();
            long numberOfDecimalDigits = (quotient.numberOfDecimalDigits() - bigInteger3.numberOfDecimalDigits()) + (bigDecimal.precision - 1);
            if (Intrinsics.areEqual((Object) divrem.getRemainder(), (Object) companion2.getZERO())) {
                return new BigDecimal(quotient, j2 + numberOfDecimalDigits, access$resolveDecimalMode);
            }
            throw new ArithmeticException("Non-terminating result of division operation (i.e. 1/3 = 0.3333... library needs to know when to stop and how to round up at that point). Specify decimalPrecision inside your decimal mode.");
        }
        long j3 = this.exponent - bigDecimal.exponent;
        long j4 = j3 - 1;
        long decimalPrecision = (access$resolveDecimalMode.getDecimalPrecision() - this.precision) + bigDecimal.precision;
        int i3 = (decimalPrecision > 0 ? 1 : (decimalPrecision == 0 ? 0 : -1));
        if (i3 > 0) {
            bigInteger = (BigInteger) this.significand.times((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(decimalPrecision));
        } else if (i3 < 0) {
            bigInteger = (BigInteger) this.significand.div((BigNumber) BigIntegerExtensionsKt.toBigInteger(10).pow(Math.abs(decimalPrecision)));
        } else {
            bigInteger = this.significand;
        }
        BigInteger.QuotientAndRemainder divrem2 = bigInteger.divrem(bigDecimal.significand);
        BigInteger quotient2 = divrem2.getQuotient();
        if (Intrinsics.areEqual((Object) quotient2, (Object) BigInteger.Companion.getZERO())) {
            j4 = j3 - 2;
        }
        long numberOfDecimalDigits2 = quotient2.numberOfDecimalDigits() - access$resolveDecimalMode.getDecimalPrecision();
        if (this.usingScale) {
            return new BigDecimal(companion.roundDiscarded(quotient2, divrem2.getRemainder(), access$resolveDecimalMode), j4 + numberOfDecimalDigits2, DecimalMode.copy$default(access$resolveDecimalMode, quotient2.numberOfDecimalDigits(), (RoundingMode) null, 0, 6, (Object) null));
        }
        return new BigDecimal(companion.roundDiscarded(quotient2, divrem2.getRemainder(), access$resolveDecimalMode), j4 + numberOfDecimalDigits2, access$resolveDecimalMode);
    }

    @NotNull
    public final BigDecimal multiply(@NotNull BigDecimal bigDecimal, @Nullable DecimalMode decimalMode2) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        Companion companion = Companion;
        DecimalMode access$resolveDecimalMode = companion.resolveDecimalMode(this.decimalMode, bigDecimal.decimalMode, decimalMode2);
        long numberOfDecimalDigits = this.significand.numberOfDecimalDigits();
        long numberOfDecimalDigits2 = bigDecimal.significand.numberOfDecimalDigits();
        BigInteger bigInteger = (BigInteger) this.significand.times((BigNumber) bigDecimal.significand);
        long numberOfDecimalDigits3 = bigInteger.numberOfDecimalDigits();
        long j2 = 1 + this.exponent + bigDecimal.exponent + (numberOfDecimalDigits3 - (numberOfDecimalDigits + numberOfDecimalDigits2));
        if (access$resolveDecimalMode.getUsingScale()) {
            return companion.roundOrDont(bigInteger, j2, DecimalMode.copy$default(access$resolveDecimalMode, numberOfDecimalDigits3, (RoundingMode) null, 0, 6, (Object) null));
        }
        return companion.roundOrDont(bigInteger, j2, access$resolveDecimalMode);
    }

    @NotNull
    public BigDecimal pow(int i3) {
        return pow((long) i3);
    }

    @NotNull
    public final BigDecimal subtract(@NotNull BigDecimal bigDecimal, @Nullable DecimalMode decimalMode2) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        Companion companion = Companion;
        DecimalMode access$resolveDecimalMode = companion.resolveDecimalMode(this.decimalMode, bigDecimal.decimalMode, decimalMode2);
        BigDecimal bigDecimal2 = ZERO;
        if (Intrinsics.areEqual((Object) this, (Object) bigDecimal2)) {
            return companion.roundOrDont(bigDecimal.significand.negate(), bigDecimal.exponent, access$resolveDecimalMode);
        }
        if (Intrinsics.areEqual((Object) bigDecimal, (Object) bigDecimal2)) {
            return companion.roundOrDont(this.significand, this.exponent, access$resolveDecimalMode);
        }
        Triple<BigInteger, BigInteger, Long> bringSignificandToSameExponent = bringSignificandToSameExponent(this, bigDecimal);
        BigInteger component1 = bringSignificandToSameExponent.component1();
        BigInteger component2 = bringSignificandToSameExponent.component2();
        long numberOfDecimalDigits = component1.numberOfDecimalDigits();
        long numberOfDecimalDigits2 = component2.numberOfDecimalDigits();
        BigInteger bigInteger = (BigInteger) component1.minus((BigNumber) component2);
        long numberOfDecimalDigits3 = bigInteger.numberOfDecimalDigits();
        if (numberOfDecimalDigits <= numberOfDecimalDigits2) {
            numberOfDecimalDigits = numberOfDecimalDigits2;
        }
        long max = Math.max(this.exponent, bigDecimal.exponent) + (numberOfDecimalDigits3 - numberOfDecimalDigits);
        if (this.usingScale) {
            return companion.roundOrDont(bigInteger, max, DecimalMode.copy$default(access$resolveDecimalMode, numberOfDecimalDigits3, (RoundingMode) null, 0, 6, (Object) null));
        }
        return companion.roundOrDont(bigInteger, max, access$resolveDecimalMode);
    }

    @NotNull
    public String toString() {
        String str;
        String str2;
        if (useToStringExpanded) {
            return toStringExpanded();
        }
        String bigInteger = this.significand.toString(10);
        int i3 = this.significand.compareTo(0) < 0 ? 2 : 1;
        String bigInteger2 = this.significand.toString();
        int lastIndex = StringsKt.getLastIndex(bigInteger2);
        while (true) {
            str = "";
            if (lastIndex >= 0) {
                if (bigInteger2.charAt(lastIndex) != '0') {
                    str2 = bigInteger2.substring(0, lastIndex + 1);
                    Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    break;
                }
                lastIndex--;
            } else {
                str2 = str;
                break;
            }
        }
        if (str2.length() <= 1) {
            str = SchemaSymbols.ATTVAL_FALSE_0;
        }
        long j2 = this.exponent;
        if (j2 > 0) {
            StringBuilder sb = new StringBuilder();
            a.o(sb, placeADotInString(bigInteger, bigInteger.length() - i3), str, "E+");
            sb.append(this.exponent);
            return sb.toString();
        } else if (j2 < 0) {
            return placeADotInString(bigInteger, bigInteger.length() - i3) + str + 'E' + this.exponent;
        } else if (j2 == 0) {
            return Intrinsics.stringPlus(placeADotInString(bigInteger, bigInteger.length() - i3), str);
        } else {
            throw new RuntimeException("Invalid state, please report a bug (Integer compareTo invalid)");
        }
    }

    @NotNull
    public BigDecimal pow(long j2) {
        BigDecimal bigDecimal;
        long j3 = 0;
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i3 > 0) {
            long j4 = j2 - 1;
            if (0 >= j4) {
                return this;
            }
            bigDecimal = this;
            do {
                j3++;
                bigDecimal = bigDecimal.times(this);
            } while (j3 < j4);
        } else if (i3 >= 0) {
            return ONE;
        } else {
            long abs = Math.abs(j2);
            if (0 > abs) {
                return this;
            }
            bigDecimal = this;
            while (true) {
                long j5 = j3 + 1;
                bigDecimal = bigDecimal.div(this);
                if (j3 == abs) {
                    break;
                }
                j3 = j5;
            }
        }
        return bigDecimal;
    }

    @NotNull
    public BigDecimal div(byte b3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.div(this, b3);
    }

    @NotNull
    public BigDecimal minus(byte b3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.minus(this, b3);
    }

    @NotNull
    public BigDecimal plus(byte b3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.plus(this, b3);
    }

    @NotNull
    public BigDecimal rem(byte b3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.rem(this, b3);
    }

    @NotNull
    public BigDecimal times(byte b3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.times(this, b3);
    }

    @NotNull
    public BigDecimal div(int i3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.div(this, i3);
    }

    @NotNull
    public BigDecimal minus(int i3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.minus(this, i3);
    }

    @NotNull
    public BigDecimal plus(int i3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.plus(this, i3);
    }

    @NotNull
    public BigDecimal rem(int i3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.rem(this, i3);
    }

    @NotNull
    public BigDecimal times(int i3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.times(this, i3);
    }

    @NotNull
    public BigDecimal div(long j2) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.div(this, j2);
    }

    @NotNull
    public BigDecimal minus(long j2) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.minus(this, j2);
    }

    @NotNull
    public BigDecimal plus(long j2) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.plus(this, j2);
    }

    @NotNull
    public BigDecimal rem(long j2) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.rem(this, j2);
    }

    @NotNull
    public BigDecimal times(long j2) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.times(this, j2);
    }

    @NotNull
    public BigDecimal div(short s3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.div(this, s3);
    }

    @NotNull
    public final Pair<BigDecimal, BigDecimal> divrem(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return divideAndRemainder(bigDecimal);
    }

    @NotNull
    public BigDecimal minus(short s3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.minus(this, s3);
    }

    @NotNull
    public BigDecimal plus(short s3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.plus(this, s3);
    }

    @NotNull
    public BigDecimal rem(short s3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.rem(this, s3);
    }

    @NotNull
    public BigDecimal times(short s3) {
        return (BigDecimal) CommonBigNumberOperations.DefaultImpls.times(this, s3);
    }

    private final BigDecimal rem(BigDecimal bigDecimal, DecimalMode decimalMode2) {
        Companion companion = Companion;
        DecimalMode access$resolveDecimalMode = companion.resolveDecimalMode(this.decimalMode, bigDecimal.decimalMode, decimalMode2);
        return companion.roundOrDont((BigInteger) this.significand.rem((BigNumber) bigDecimal.significand), this.exponent - bigDecimal.exponent, access$resolveDecimalMode);
    }

    @NotNull
    public BigDecimal div(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return divide(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public BigDecimal minus(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return subtract(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public BigDecimal plus(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return add(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public BigDecimal times(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return multiply(bigDecimal, computeMode(bigDecimal, ScaleOps.Max));
    }

    @NotNull
    public final String times(long j2, char c3) {
        if (j2 >= 0) {
            StringBuilder sb = new StringBuilder();
            while (j2 > 0) {
                sb.append(c3);
                j2--;
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
            return sb2;
        }
        throw new RuntimeException("Char cannot be multiplied with negative number");
    }

    @NotNull
    public BigDecimal rem(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return rem(bigDecimal, (DecimalMode) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BigDecimal(BigInteger bigInteger, long j2, DecimalMode decimalMode2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(bigInteger, (i3 & 2) != 0 ? 0 : j2, (i3 & 4) != 0 ? null : decimalMode2);
    }
}
