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
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Floats extends FloatsMethodsForWeb {
    public static final int BYTES = 4;

    @GwtCompatible
    public static class FloatArrayAsList extends AbstractList<Float> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final float[] array;
        final int end;
        final int start;

        public FloatArrayAsList(float[] fArr) {
            this(fArr, 0, fArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Float) && Floats.indexOf(this.array, ((Float) obj).floatValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FloatArrayAsList)) {
                return super.equals(obj);
            }
            FloatArrayAsList floatArrayAsList = (FloatArrayAsList) obj;
            int size = size();
            if (floatArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != floatArrayAsList.array[floatArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Floats.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Float) || (access$000 = Floats.indexOf(this.array, ((Float) obj).floatValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Float) || (access$100 = Floats.lastIndexOf(this.array, ((Float) obj).floatValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Float> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            float[] fArr = this.array;
            int i5 = this.start;
            return new FloatArrayAsList(fArr, i3 + i5, i5 + i4);
        }

        public float[] toFloatArray() {
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

        public FloatArrayAsList(float[] fArr, int i3, int i4) {
            this.array = fArr;
            this.start = i3;
            this.end = i4;
        }

        public Float get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Float.valueOf(this.array[this.start + i3]);
        }

        public Float set(int i3, Float f2) {
            Preconditions.checkElementIndex(i3, size());
            float[] fArr = this.array;
            int i4 = this.start;
            float f3 = fArr[i4 + i3];
            fArr[i4 + i3] = ((Float) Preconditions.checkNotNull(f2)).floatValue();
            return Float.valueOf(f3);
        }
    }

    public static final class FloatConverter extends Converter<String, Float> implements Serializable {
        static final FloatConverter INSTANCE = new FloatConverter();
        private static final long serialVersionUID = 1;

        private FloatConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Floats.stringConverter()";
        }

        public String doBackward(Float f2) {
            return f2.toString();
        }

        public Float doForward(String str) {
            return Float.valueOf(str);
        }
    }

    public enum LexicographicalComparator implements Comparator<float[]> {
        INSTANCE;

        public String toString() {
            return "Floats.lexicographicalComparator()";
        }

        public int compare(float[] fArr, float[] fArr2) {
            int min = Math.min(fArr.length, fArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = Float.compare(fArr[i3], fArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return fArr.length - fArr2.length;
        }
    }

    private Floats() {
    }

    public static List<Float> asList(float... fArr) {
        return fArr.length == 0 ? Collections.emptyList() : new FloatArrayAsList(fArr);
    }

    public static int compare(float f2, float f3) {
        return Float.compare(f2, f3);
    }

    public static float[] concat(float[]... fArr) {
        int i3 = 0;
        for (float[] length : fArr) {
            i3 += length.length;
        }
        float[] fArr2 = new float[i3];
        int i4 = 0;
        for (float[] fArr3 : fArr) {
            System.arraycopy(fArr3, 0, fArr2, i4, fArr3.length);
            i4 += fArr3.length;
        }
        return fArr2;
    }

    public static float constrainToRange(float f2, float f3, float f4) {
        if (f3 <= f4) {
            return Math.min(Math.max(f2, f3), f4);
        }
        throw new IllegalArgumentException(Strings.lenientFormat("min (%s) must be less than or equal to max (%s)", Float.valueOf(f3), Float.valueOf(f4)));
    }

    public static boolean contains(float[] fArr, float f2) {
        for (float f3 : fArr) {
            if (f3 == f2) {
                return true;
            }
        }
        return false;
    }

    public static float[] ensureCapacity(float[] fArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return fArr.length < i3 ? Arrays.copyOf(fArr, i3 + i4) : fArr;
    }

    public static int hashCode(float f2) {
        return Float.valueOf(f2).hashCode();
    }

    public static int indexOf(float[] fArr, float f2) {
        return indexOf(fArr, f2, 0, fArr.length);
    }

    public static boolean isFinite(float f2) {
        return Float.NEGATIVE_INFINITY < f2 && f2 < Float.POSITIVE_INFINITY;
    }

    public static String join(String str, float... fArr) {
        Preconditions.checkNotNull(str);
        if (fArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(fArr.length * 12);
        sb.append(fArr[0]);
        for (int i3 = 1; i3 < fArr.length; i3++) {
            sb.append(str);
            sb.append(fArr[i3]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(float[] fArr, float f2) {
        return lastIndexOf(fArr, f2, 0, fArr.length);
    }

    public static Comparator<float[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static float max(float... fArr) {
        Preconditions.checkArgument(fArr.length > 0);
        float f2 = fArr[0];
        for (int i3 = 1; i3 < fArr.length; i3++) {
            f2 = Math.max(f2, fArr[i3]);
        }
        return f2;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static float min(float... fArr) {
        Preconditions.checkArgument(fArr.length > 0);
        float f2 = fArr[0];
        for (int i3 = 1; i3 < fArr.length; i3++) {
            f2 = Math.min(f2, fArr[i3]);
        }
        return f2;
    }

    public static void reverse(float[] fArr) {
        Preconditions.checkNotNull(fArr);
        reverse(fArr, 0, fArr.length);
    }

    public static void rotate(float[] fArr, int i3) {
        rotate(fArr, i3, 0, fArr.length);
    }

    public static void sortDescending(float[] fArr) {
        Preconditions.checkNotNull(fArr);
        sortDescending(fArr, 0, fArr.length);
    }

    public static Converter<String, Float> stringConverter() {
        return FloatConverter.INSTANCE;
    }

    public static float[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof FloatArrayAsList) {
            return ((FloatArrayAsList) collection).toFloatArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        float[] fArr = new float[length];
        for (int i3 = 0; i3 < length; i3++) {
            fArr[i3] = ((Number) Preconditions.checkNotNull(array[i3])).floatValue();
        }
        return fArr;
    }

    @CheckForNull
    @GwtIncompatible
    @J2ktIncompatible
    public static Float tryParse(String str) {
        if (!Doubles.FLOATING_POINT_PATTERN.matcher(str).matches()) {
            return null;
        }
        try {
            return Float.valueOf(Float.parseFloat(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static int indexOf(float[] fArr, float f2, int i3, int i4) {
        while (i3 < i4) {
            if (fArr[i3] == f2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(float[] fArr, float f2, int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            if (fArr[i5] == f2) {
                return i5;
            }
        }
        return -1;
    }

    public static void rotate(float[] fArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkPositionIndexes(i4, i5, fArr.length);
        if (fArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(fArr, i4, i8);
                reverse(fArr, i8, i5);
                reverse(fArr, i4, i5);
            }
        }
    }

    public static int indexOf(float[] fArr, float[] fArr2) {
        Preconditions.checkNotNull(fArr, "array");
        Preconditions.checkNotNull(fArr2, TypedValues.AttributesType.S_TARGET);
        if (fArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (fArr.length - fArr2.length) + 1) {
            int i4 = 0;
            while (i4 < fArr2.length) {
                if (fArr[i3 + i4] != fArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(float[] fArr, int i3, int i4) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkPositionIndexes(i3, i4, fArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            float f2 = fArr[i3];
            fArr[i3] = fArr[i5];
            fArr[i5] = f2;
            i3++;
        }
    }

    public static void sortDescending(float[] fArr, int i3, int i4) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkPositionIndexes(i3, i4, fArr.length);
        Arrays.sort(fArr, i3, i4);
        reverse(fArr, i3, i4);
    }
}
