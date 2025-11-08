package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.Comparable;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class DiscreteDomain<C extends Comparable> {
    final boolean supportsFastOffset;

    public static final class BigIntegerDomain extends DiscreteDomain<BigInteger> implements Serializable {
        /* access modifiers changed from: private */
        public static final BigIntegerDomain INSTANCE = new BigIntegerDomain();
        private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
        private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        private static final long serialVersionUID = 0;

        public BigIntegerDomain() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.bigIntegers()";
        }

        public long distance(BigInteger bigInteger, BigInteger bigInteger2) {
            return bigInteger2.subtract(bigInteger).max(MIN_LONG).min(MAX_LONG).longValue();
        }

        public BigInteger next(BigInteger bigInteger) {
            return bigInteger.add(BigInteger.ONE);
        }

        public BigInteger offset(BigInteger bigInteger, long j2) {
            CollectPreconditions.checkNonnegative(j2, "distance");
            return bigInteger.add(BigInteger.valueOf(j2));
        }

        public BigInteger previous(BigInteger bigInteger) {
            return bigInteger.subtract(BigInteger.ONE);
        }
    }

    public static final class IntegerDomain extends DiscreteDomain<Integer> implements Serializable {
        /* access modifiers changed from: private */
        public static final IntegerDomain INSTANCE = new IntegerDomain();
        private static final long serialVersionUID = 0;

        public IntegerDomain() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.integers()";
        }

        public long distance(Integer num, Integer num2) {
            return ((long) num2.intValue()) - ((long) num.intValue());
        }

        public Integer maxValue() {
            return Integer.MAX_VALUE;
        }

        public Integer minValue() {
            return Integer.MIN_VALUE;
        }

        @CheckForNull
        public Integer next(Integer num) {
            int intValue = num.intValue();
            if (intValue == Integer.MAX_VALUE) {
                return null;
            }
            return Integer.valueOf(intValue + 1);
        }

        public Integer offset(Integer num, long j2) {
            CollectPreconditions.checkNonnegative(j2, "distance");
            return Integer.valueOf(Ints.checkedCast(num.longValue() + j2));
        }

        @CheckForNull
        public Integer previous(Integer num) {
            int intValue = num.intValue();
            if (intValue == Integer.MIN_VALUE) {
                return null;
            }
            return Integer.valueOf(intValue - 1);
        }
    }

    public static final class LongDomain extends DiscreteDomain<Long> implements Serializable {
        /* access modifiers changed from: private */
        public static final LongDomain INSTANCE = new LongDomain();
        private static final long serialVersionUID = 0;

        public LongDomain() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.longs()";
        }

        public long distance(Long l2, Long l3) {
            long longValue = l3.longValue() - l2.longValue();
            if (l3.longValue() > l2.longValue() && longValue < 0) {
                return Long.MAX_VALUE;
            }
            if (l3.longValue() >= l2.longValue() || longValue <= 0) {
                return longValue;
            }
            return Long.MIN_VALUE;
        }

        public Long maxValue() {
            return Long.MAX_VALUE;
        }

        public Long minValue() {
            return Long.MIN_VALUE;
        }

        @CheckForNull
        public Long next(Long l2) {
            long longValue = l2.longValue();
            if (longValue == Long.MAX_VALUE) {
                return null;
            }
            return Long.valueOf(longValue + 1);
        }

        public Long offset(Long l2, long j2) {
            CollectPreconditions.checkNonnegative(j2, "distance");
            long longValue = l2.longValue() + j2;
            if (longValue < 0) {
                Preconditions.checkArgument(l2.longValue() < 0, "overflow");
            }
            return Long.valueOf(longValue);
        }

        @CheckForNull
        public Long previous(Long l2) {
            long longValue = l2.longValue();
            if (longValue == Long.MIN_VALUE) {
                return null;
            }
            return Long.valueOf(longValue - 1);
        }
    }

    public static DiscreteDomain<BigInteger> bigIntegers() {
        return BigIntegerDomain.INSTANCE;
    }

    public static DiscreteDomain<Integer> integers() {
        return IntegerDomain.INSTANCE;
    }

    public static DiscreteDomain<Long> longs() {
        return LongDomain.INSTANCE;
    }

    public abstract long distance(C c3, C c4);

    @CanIgnoreReturnValue
    public C maxValue() {
        throw new NoSuchElementException();
    }

    @CanIgnoreReturnValue
    public C minValue() {
        throw new NoSuchElementException();
    }

    @CheckForNull
    public abstract C next(C c3);

    public C offset(C c3, long j2) {
        CollectPreconditions.checkNonnegative(j2, "distance");
        long j3 = 0;
        C c4 = c3;
        while (j3 < j2) {
            c4 = next(c4);
            if (c4 != null) {
                j3++;
            } else {
                throw new IllegalArgumentException("overflowed computing offset(" + c3 + ", " + j2 + ")");
            }
        }
        return c4;
    }

    @CheckForNull
    public abstract C previous(C c3);

    public DiscreteDomain() {
        this(false);
    }

    private DiscreteDomain(boolean z2) {
        this.supportsFastOffset = z2;
    }
}
