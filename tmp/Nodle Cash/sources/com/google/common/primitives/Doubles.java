package com.google.common.primitives;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Marker;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Doubles extends DoublesMethodsForWeb {
    public static final int BYTES = 8;
    @GwtIncompatible
    @J2ktIncompatible
    static final Pattern FLOATING_POINT_PATTERN = fpPattern();

    @GwtCompatible
    public static class DoubleArrayAsList extends AbstractList<Double> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final double[] array;
        final int end;
        final int start;

        public DoubleArrayAsList(double[] dArr) {
            this(dArr, 0, dArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Double) && Doubles.indexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DoubleArrayAsList)) {
                return super.equals(obj);
            }
            DoubleArrayAsList doubleArrayAsList = (DoubleArrayAsList) obj;
            int size = size();
            if (doubleArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != doubleArrayAsList.array[doubleArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Doubles.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Double) || (access$000 = Doubles.indexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Double) || (access$100 = Doubles.lastIndexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Double> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            double[] dArr = this.array;
            int i5 = this.start;
            return new DoubleArrayAsList(dArr, i3 + i5, i5 + i4);
        }

        public double[] toDoubleArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 12);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.array[this.start]);
            int i3 = this.start;
            while (true) {
                i3++;
                if (i3 < this.end) {
                    sb.append(", ");
                    sb.append(this.array[i3]);
                } else {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
            }
        }

        public DoubleArrayAsList(double[] dArr, int i3, int i4) {
            this.array = dArr;
            this.start = i3;
            this.end = i4;
        }

        public Double get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Double.valueOf(this.array[this.start + i3]);
        }

        public Double set(int i3, Double d2) {
            Preconditions.checkElementIndex(i3, size());
            double[] dArr = this.array;
            int i4 = this.start;
            double d3 = dArr[i4 + i3];
            dArr[i4 + i3] = ((Double) Preconditions.checkNotNull(d2)).doubleValue();
            return Double.valueOf(d3);
        }
    }

    public static final class DoubleConverter extends Converter<String, Double> implements Serializable {
        static final DoubleConverter INSTANCE = new DoubleConverter();
        private static final long serialVersionUID = 1;

        private DoubleConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Doubles.stringConverter()";
        }

        public String doBackward(Double d2) {
            return d2.toString();
        }

        public Double doForward(String str) {
            return Double.valueOf(str);
        }
    }

    public enum LexicographicalComparator implements Comparator<double[]> {
        INSTANCE;

        public String toString() {
            return "Doubles.lexicographicalComparator()";
        }

        public int compare(double[] dArr, double[] dArr2) {
            int min = Math.min(dArr.length, dArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = Double.compare(dArr[i3], dArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return dArr.length - dArr2.length;
        }
    }

    private Doubles() {
    }

    public static List<Double> asList(double... dArr) {
        return dArr.length == 0 ? Collections.emptyList() : new DoubleArrayAsList(dArr);
    }

    public static int compare(double d2, double d3) {
        return Double.compare(d2, d3);
    }

    public static double[] concat(double[]... dArr) {
        int i3 = 0;
        for (double[] length : dArr) {
            i3 += length.length;
        }
        double[] dArr2 = new double[i3];
        int i4 = 0;
        for (double[] dArr3 : dArr) {
            System.arraycopy(dArr3, 0, dArr2, i4, dArr3.length);
            i4 += dArr3.length;
        }
        return dArr2;
    }

    public static double constrainToRange(double d2, double d3, double d4) {
        if (d3 <= d4) {
            return Math.min(Math.max(d2, d3), d4);
        }
        throw new IllegalArgumentException(Strings.lenientFormat("min (%s) must be less than or equal to max (%s)", Double.valueOf(d3), Double.valueOf(d4)));
    }

    public static boolean contains(double[] dArr, double d2) {
        for (double d3 : dArr) {
            if (d3 == d2) {
                return true;
            }
        }
        return false;
    }

    public static double[] ensureCapacity(double[] dArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return dArr.length < i3 ? Arrays.copyOf(dArr, i3 + i4) : dArr;
    }

    @GwtIncompatible
    private static Pattern fpPattern() {
        return Pattern.compile("[+-]?(?:NaN|Infinity|(?:\\d+#(?:\\.\\d*#)?|\\.\\d+#)(?:[eE][+-]?\\d+#)?[fFdD]?|0[xX](?:[0-9a-fA-F]+#(?:\\.[0-9a-fA-F]*#)?|\\.[0-9a-fA-F]+#)[pP][+-]?\\d+#[fFdD]?)".replace("#", Marker.ANY_NON_NULL_MARKER));
    }

    public static int hashCode(double d2) {
        return Double.valueOf(d2).hashCode();
    }

    public static int indexOf(double[] dArr, double d2) {
        return indexOf(dArr, d2, 0, dArr.length);
    }

    public static boolean isFinite(double d2) {
        return Double.NEGATIVE_INFINITY < d2 && d2 < Double.POSITIVE_INFINITY;
    }

    public static String join(String str, double... dArr) {
        Preconditions.checkNotNull(str);
        if (dArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(dArr.length * 12);
        sb.append(dArr[0]);
        for (int i3 = 1; i3 < dArr.length; i3++) {
            sb.append(str);
            sb.append(dArr[i3]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(double[] dArr, double d2) {
        return lastIndexOf(dArr, d2, 0, dArr.length);
    }

    public static Comparator<double[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static double max(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double d2 = dArr[0];
        for (int i3 = 1; i3 < dArr.length; i3++) {
            d2 = Math.max(d2, dArr[i3]);
        }
        return d2;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static double min(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double d2 = dArr[0];
        for (int i3 = 1; i3 < dArr.length; i3++) {
            d2 = Math.min(d2, dArr[i3]);
        }
        return d2;
    }

    public static void reverse(double[] dArr) {
        Preconditions.checkNotNull(dArr);
        reverse(dArr, 0, dArr.length);
    }

    public static void rotate(double[] dArr, int i3) {
        rotate(dArr, i3, 0, dArr.length);
    }

    public static void sortDescending(double[] dArr) {
        Preconditions.checkNotNull(dArr);
        sortDescending(dArr, 0, dArr.length);
    }

    public static Converter<String, Double> stringConverter() {
        return DoubleConverter.INSTANCE;
    }

    public static double[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof DoubleArrayAsList) {
            return ((DoubleArrayAsList) collection).toDoubleArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        double[] dArr = new double[length];
        for (int i3 = 0; i3 < length; i3++) {
            dArr[i3] = ((Number) Preconditions.checkNotNull(array[i3])).doubleValue();
        }
        return dArr;
    }

    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    public static Double tryParse(String str) {
        if (!FLOATING_POINT_PATTERN.matcher(str).matches()) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static int indexOf(double[] dArr, double d2, int i3, int i4) {
        while (i3 < i4) {
            if (dArr[i3] == d2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(double[] dArr, double d2, int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            if (dArr[i5] == d2) {
                return i5;
            }
        }
        return -1;
    }

    public static void rotate(double[] dArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(dArr);
        Preconditions.checkPositionIndexes(i4, i5, dArr.length);
        if (dArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(dArr, i4, i8);
                reverse(dArr, i8, i5);
                reverse(dArr, i4, i5);
            }
        }
    }

    public static int indexOf(double[] dArr, double[] dArr2) {
        Preconditions.checkNotNull(dArr, "array");
        Preconditions.checkNotNull(dArr2, TypedValues.AttributesType.S_TARGET);
        if (dArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (dArr.length - dArr2.length) + 1) {
            int i4 = 0;
            while (i4 < dArr2.length) {
                if (dArr[i3 + i4] != dArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(double[] dArr, int i3, int i4) {
        Preconditions.checkNotNull(dArr);
        Preconditions.checkPositionIndexes(i3, i4, dArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            double d2 = dArr[i3];
            dArr[i3] = dArr[i5];
            dArr[i5] = d2;
            i3++;
        }
    }

    public static void sortDescending(double[] dArr, int i3, int i4) {
        Preconditions.checkNotNull(dArr);
        Preconditions.checkPositionIndexes(i3, i4, dArr.length);
        Arrays.sort(dArr, i3, i4);
        reverse(dArr, i3, i4);
    }
}
