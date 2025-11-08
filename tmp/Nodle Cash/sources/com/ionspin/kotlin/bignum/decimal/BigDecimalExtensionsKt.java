package com.ionspin.kotlin.bignum.decimal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a'\u0010\r\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011\u001a'\u0010\r\u001a\u00020\u0001*\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0012\u001a'\u0010\r\u001a\u00020\u0001*\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0013\u001a'\u0010\r\u001a\u00020\u0001*\u00020\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0014\u001a'\u0010\r\u001a\u00020\u0001*\u00020\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0015\u001a'\u0010\r\u001a\u00020\u0001*\u00020\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0016\u001a'\u0010\r\u001a\u00020\u0001*\u00020\u00172\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0018\u001a\u001e\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u001a\u001e\u0010\u0019\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u001a\u001e\u0010\u0019\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u001a\u001e\u0010\u0019\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¨\u0006\u001b"}, d2 = {"div", "Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "", "other", "", "", "", "", "", "minus", "plus", "rem", "times", "toBigDecimal", "exponentModifier", "decimalMode", "Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;", "(BLjava/lang/Long;Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "(DLjava/lang/Long;Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "(FLjava/lang/Long;Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "(ILjava/lang/Long;Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "(JLjava/lang/Long;Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "(SLjava/lang/Long;Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "", "(Ljava/lang/String;Ljava/lang/Long;Lcom/ionspin/kotlin/bignum/decimal/DecimalMode;)Lcom/ionspin/kotlin/bignum/decimal/BigDecimal;", "toBigDecimalUsingSignificandAndExponent", "exponent", "bignum"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class BigDecimalExtensionsKt {
    @NotNull
    public static final BigDecimal div(long j2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(j2, (Long) null, (DecimalMode) null, 3, (Object) null).div(bigDecimal);
    }

    @NotNull
    public static final BigDecimal minus(long j2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(j2, (Long) null, (DecimalMode) null, 3, (Object) null).minus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal plus(long j2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(j2, (Long) null, (DecimalMode) null, 3, (Object) null).plus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal rem(long j2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(j2, (Long) null, (DecimalMode) null, 3, (Object) null).rem(bigDecimal);
    }

    @NotNull
    public static final BigDecimal times(long j2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(j2, (Long) null, (DecimalMode) null, 3, (Object) null).times(bigDecimal);
    }

    @NotNull
    public static final BigDecimal toBigDecimal(long j2, @Nullable Long l2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromLong(j2, decimalMode).moveDecimalPoint(l2 == null ? 0 : l2.longValue());
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(long j2, Long l2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            l2 = null;
        }
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimal(j2, l2, decimalMode);
    }

    @NotNull
    public static final BigDecimal toBigDecimalUsingSignificandAndExponent(long j2, long j3, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromLongWithExponent(j2, j3, decimalMode);
    }

    public static /* synthetic */ BigDecimal toBigDecimalUsingSignificandAndExponent$default(long j2, long j3, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimalUsingSignificandAndExponent(j2, j3, decimalMode);
    }

    @NotNull
    public static final BigDecimal div(int i3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(i3, (Long) null, (DecimalMode) null, 3, (Object) null).div(bigDecimal);
    }

    @NotNull
    public static final BigDecimal minus(int i3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(i3, (Long) null, (DecimalMode) null, 3, (Object) null).minus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal plus(int i3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(i3, (Long) null, (DecimalMode) null, 3, (Object) null).plus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal rem(int i3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(i3, (Long) null, (DecimalMode) null, 3, (Object) null).rem(bigDecimal);
    }

    @NotNull
    public static final BigDecimal times(int i3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(i3, (Long) null, (DecimalMode) null, 3, (Object) null).times(bigDecimal);
    }

    @NotNull
    public static final BigDecimal toBigDecimal(int i3, @Nullable Long l2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromInt(i3, decimalMode).moveDecimalPoint(l2 == null ? 0 : l2.longValue());
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(int i3, Long l2, DecimalMode decimalMode, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            l2 = null;
        }
        if ((i4 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimal(i3, l2, decimalMode);
    }

    @NotNull
    public static final BigDecimal toBigDecimalUsingSignificandAndExponent(int i3, long j2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromIntWithExponent(i3, j2, decimalMode);
    }

    public static /* synthetic */ BigDecimal toBigDecimalUsingSignificandAndExponent$default(int i3, long j2, DecimalMode decimalMode, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimalUsingSignificandAndExponent(i3, j2, decimalMode);
    }

    @NotNull
    public static final BigDecimal div(short s3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(s3, (Long) null, (DecimalMode) null, 3, (Object) null).div(bigDecimal);
    }

    @NotNull
    public static final BigDecimal minus(short s3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(s3, (Long) null, (DecimalMode) null, 3, (Object) null).minus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal plus(short s3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(s3, (Long) null, (DecimalMode) null, 3, (Object) null).plus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal rem(short s3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(s3, (Long) null, (DecimalMode) null, 3, (Object) null).rem(bigDecimal);
    }

    @NotNull
    public static final BigDecimal times(short s3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(s3, (Long) null, (DecimalMode) null, 3, (Object) null).times(bigDecimal);
    }

    @NotNull
    public static final BigDecimal toBigDecimal(short s3, @Nullable Long l2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromShort(s3, decimalMode).moveDecimalPoint(l2 == null ? 0 : l2.longValue());
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(short s3, Long l2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            l2 = null;
        }
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimal(s3, l2, decimalMode);
    }

    @NotNull
    public static final BigDecimal toBigDecimalUsingSignificandAndExponent(short s3, long j2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromShortWithExponent(s3, j2, decimalMode);
    }

    public static /* synthetic */ BigDecimal toBigDecimalUsingSignificandAndExponent$default(short s3, long j2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimalUsingSignificandAndExponent(s3, j2, decimalMode);
    }

    @NotNull
    public static final BigDecimal div(byte b3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(b3, (Long) null, (DecimalMode) null, 3, (Object) null).div(bigDecimal);
    }

    @NotNull
    public static final BigDecimal minus(byte b3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(b3, (Long) null, (DecimalMode) null, 3, (Object) null).minus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal plus(byte b3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(b3, (Long) null, (DecimalMode) null, 3, (Object) null).plus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal rem(byte b3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(b3, (Long) null, (DecimalMode) null, 3, (Object) null).rem(bigDecimal);
    }

    @NotNull
    public static final BigDecimal times(byte b3, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(b3, (Long) null, (DecimalMode) null, 3, (Object) null).times(bigDecimal);
    }

    @NotNull
    public static final BigDecimal toBigDecimal(byte b3, @Nullable Long l2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromByte(b3, decimalMode).moveDecimalPoint(l2 == null ? 0 : l2.longValue());
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(byte b3, Long l2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            l2 = null;
        }
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimal(b3, l2, decimalMode);
    }

    @NotNull
    public static final BigDecimal toBigDecimalUsingSignificandAndExponent(byte b3, long j2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromByteWithExponent(b3, j2, decimalMode);
    }

    public static /* synthetic */ BigDecimal toBigDecimalUsingSignificandAndExponent$default(byte b3, long j2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimalUsingSignificandAndExponent(b3, j2, decimalMode);
    }

    @NotNull
    public static final BigDecimal div(double d2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(d2, (Long) null, (DecimalMode) null, 3, (Object) null).div(bigDecimal);
    }

    @NotNull
    public static final BigDecimal minus(double d2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(d2, (Long) null, (DecimalMode) null, 3, (Object) null).minus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal plus(double d2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(d2, (Long) null, (DecimalMode) null, 3, (Object) null).plus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal rem(double d2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(d2, (Long) null, (DecimalMode) null, 3, (Object) null).rem(bigDecimal);
    }

    @NotNull
    public static final BigDecimal times(double d2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(d2, (Long) null, (DecimalMode) null, 3, (Object) null).times(bigDecimal);
    }

    @NotNull
    public static final BigDecimal toBigDecimal(@NotNull String str, @Nullable Long l2, @Nullable DecimalMode decimalMode) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return BigDecimal.Companion.parseStringWithMode(str, decimalMode).moveDecimalPoint(l2 == null ? 0 : l2.longValue());
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(String str, Long l2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            l2 = null;
        }
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimal(str, l2, decimalMode);
    }

    @NotNull
    public static final BigDecimal div(float f2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(f2, (Long) null, (DecimalMode) null, 3, (Object) null).div(bigDecimal);
    }

    @NotNull
    public static final BigDecimal minus(float f2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(f2, (Long) null, (DecimalMode) null, 3, (Object) null).minus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal plus(float f2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(f2, (Long) null, (DecimalMode) null, 3, (Object) null).plus(bigDecimal);
    }

    @NotNull
    public static final BigDecimal rem(float f2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(f2, (Long) null, (DecimalMode) null, 3, (Object) null).rem(bigDecimal);
    }

    @NotNull
    public static final BigDecimal times(float f2, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "other");
        return toBigDecimal$default(f2, (Long) null, (DecimalMode) null, 3, (Object) null).times(bigDecimal);
    }

    @NotNull
    public static final BigDecimal toBigDecimal(float f2, @Nullable Long l2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromFloat(f2, decimalMode).moveDecimalPoint(l2 == null ? 0 : l2.longValue());
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(float f2, Long l2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            l2 = null;
        }
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimal(f2, l2, decimalMode);
    }

    @NotNull
    public static final BigDecimal toBigDecimal(double d2, @Nullable Long l2, @Nullable DecimalMode decimalMode) {
        return BigDecimal.Companion.fromDouble(d2, decimalMode).moveDecimalPoint(l2 == null ? 0 : l2.longValue());
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(double d2, Long l2, DecimalMode decimalMode, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            l2 = null;
        }
        if ((i3 & 2) != 0) {
            decimalMode = null;
        }
        return toBigDecimal(d2, l2, decimalMode);
    }
}
