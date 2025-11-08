package com.apollographql.apollo3.api.json.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0001¢\u0006\u0002\b\u0003\u001a\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0001H\u0001¢\u0006\u0002\b\u0006\u001a\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0001¢\u0006\u0002\b\u0007\u001a\u0011\u0010\b\u001a\u00020\u0002*\u00020\u0001H\u0001¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"toDoubleExact", "", "", "-LongToDoubleExact", "toIntExact", "", "-DoubleToIntExact", "-LongToIntExact", "toLongExact", "-DoubleToLongExact", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class UtilsKt {
    @JvmName(name = "-DoubleToIntExact")
    /* renamed from: -DoubleToIntExact  reason: not valid java name */
    public static final int m8214DoubleToIntExact(double d2) {
        int i3 = (int) d2;
        if (((double) i3) == d2) {
            return i3;
        }
        throw new IllegalStateException((d2 + " cannot be converted to Int").toString());
    }

    @JvmName(name = "-DoubleToLongExact")
    /* renamed from: -DoubleToLongExact  reason: not valid java name */
    public static final long m8215DoubleToLongExact(double d2) {
        long j2 = (long) d2;
        if (((double) j2) == d2) {
            return j2;
        }
        throw new IllegalStateException((d2 + " cannot be converted to Long").toString());
    }

    @JvmName(name = "-LongToDoubleExact")
    /* renamed from: -LongToDoubleExact  reason: not valid java name */
    public static final double m8216LongToDoubleExact(long j2) {
        double d2 = (double) j2;
        if (((long) d2) == j2) {
            return d2;
        }
        throw new IllegalStateException((j2 + " cannot be converted to Double").toString());
    }

    @JvmName(name = "-LongToIntExact")
    /* renamed from: -LongToIntExact  reason: not valid java name */
    public static final int m8217LongToIntExact(long j2) {
        int i3 = (int) j2;
        if (((long) i3) == j2) {
            return i3;
        }
        throw new IllegalStateException((j2 + " cannot be converted to Int").toString());
    }
}
