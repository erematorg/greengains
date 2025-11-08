package com.google.common.primitives;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Shorts extends ShortsMethodsForWeb {
    public static final int BYTES = 2;
    public static final short MAX_POWER_OF_TWO = 16384;

    public enum LexicographicalComparator implements Comparator<short[]> {
        INSTANCE;

        public String toString() {
            return "Shorts.lexicographicalComparator()";
        }

        public int compare(short[] sArr, short[] sArr2) {
            int min = Math.min(sArr.length, sArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = Shorts.compare(sArr[i3], sArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return sArr.length - sArr2.length;
        }
    }

    @GwtCompatible
    public static class ShortArrayAsList extends AbstractList<Short> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final short[] array;
        final int end;
        final int start;

        public ShortArrayAsList(short[] sArr) {
            this(sArr, 0, sArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Short) && Shorts.indexOf(this.array, ((Short) obj).shortValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ShortArrayAsList)) {
                return super.equals(obj);
            }
            ShortArrayAsList shortArrayAsList = (ShortArrayAsList) obj;
            int size = size();
            if (shortArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != shortArrayAsList.array[shortArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Shorts.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Short) || (access$000 = Shorts.indexOf(this.array, ((Short) obj).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Short) || (access$100 = Shorts.lastIndexOf(this.array, ((Short) obj).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Short> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            short[] sArr = this.array;
            int i5 = this.start;
            return new ShortArrayAsList(sArr, i3 + i5, i5 + i4);
        }

        public short[] toShortArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 6);
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

        public ShortArrayAsList(short[] sArr, int i3, int i4) {
            this.array = sArr;
            this.start = i3;
            this.end = i4;
        }

        public Short get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Short.valueOf(this.array[this.start + i3]);
        }

        public Short set(int i3, Short sh) {
            Preconditions.checkElementIndex(i3, size());
            short[] sArr = this.array;
            int i4 = this.start;
            short s3 = sArr[i4 + i3];
            sArr[i4 + i3] = ((Short) Preconditions.checkNotNull(sh)).shortValue();
            return Short.valueOf(s3);
        }
    }

    public static final class ShortConverter extends Converter<String, Short> implements Serializable {
        static final ShortConverter INSTANCE = new ShortConverter();
        private static final long serialVersionUID = 1;

        private ShortConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Shorts.stringConverter()";
        }

        public String doBackward(Short sh) {
            return sh.toString();
        }

        public Short doForward(String str) {
            return Short.decode(str);
        }
    }

    private Shorts() {
    }

    public static List<Short> asList(short... sArr) {
        return sArr.length == 0 ? Collections.emptyList() : new ShortArrayAsList(sArr);
    }

    public static short checkedCast(long j2) {
        short s3 = (short) ((int) j2);
        Preconditions.checkArgument(((long) s3) == j2, "Out of range: %s", j2);
        return s3;
    }

    public static int compare(short s3, short s4) {
        return s3 - s4;
    }

    public static short[] concat(short[]... sArr) {
        int i3 = 0;
        for (short[] length : sArr) {
            i3 += length.length;
        }
        short[] sArr2 = new short[i3];
        int i4 = 0;
        for (short[] sArr3 : sArr) {
            System.arraycopy(sArr3, 0, sArr2, i4, sArr3.length);
            i4 += sArr3.length;
        }
        return sArr2;
    }

    public static short constrainToRange(short s3, short s4, short s5) {
        Preconditions.checkArgument(s4 <= s5, "min (%s) must be less than or equal to max (%s)", (int) s4, (int) s5);
        return s3 < s4 ? s4 : s3 < s5 ? s3 : s5;
    }

    public static boolean contains(short[] sArr, short s3) {
        for (short s4 : sArr) {
            if (s4 == s3) {
                return true;
            }
        }
        return false;
    }

    public static short[] ensureCapacity(short[] sArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return sArr.length < i3 ? Arrays.copyOf(sArr, i3 + i4) : sArr;
    }

    @GwtIncompatible
    public static short fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static short fromBytes(byte b3, byte b4) {
        return (short) ((b3 << 8) | (b4 & 255));
    }

    public static int hashCode(short s3) {
        return s3;
    }

    public static int indexOf(short[] sArr, short s3) {
        return indexOf(sArr, s3, 0, sArr.length);
    }

    public static String join(String str, short... sArr) {
        Preconditions.checkNotNull(str);
        if (sArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(sArr.length * 6);
        sb.append(sArr[0]);
        for (int i3 = 1; i3 < sArr.length; i3++) {
            sb.append(str);
            sb.append(sArr[i3]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(short[] sArr, short s3) {
        return lastIndexOf(sArr, s3, 0, sArr.length);
    }

    public static Comparator<short[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static short max(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s3 = sArr[0];
        for (int i3 = 1; i3 < sArr.length; i3++) {
            short s4 = sArr[i3];
            if (s4 > s3) {
                s3 = s4;
            }
        }
        return s3;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static short min(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s3 = sArr[0];
        for (int i3 = 1; i3 < sArr.length; i3++) {
            short s4 = sArr[i3];
            if (s4 < s3) {
                s3 = s4;
            }
        }
        return s3;
    }

    public static void reverse(short[] sArr) {
        Preconditions.checkNotNull(sArr);
        reverse(sArr, 0, sArr.length);
    }

    public static void rotate(short[] sArr, int i3) {
        rotate(sArr, i3, 0, sArr.length);
    }

    public static short saturatedCast(long j2) {
        return j2 > 32767 ? ShortCompanionObject.MAX_VALUE : j2 < -32768 ? ShortCompanionObject.MIN_VALUE : (short) ((int) j2);
    }

    public static void sortDescending(short[] sArr) {
        Preconditions.checkNotNull(sArr);
        sortDescending(sArr, 0, sArr.length);
    }

    public static Converter<String, Short> stringConverter() {
        return ShortConverter.INSTANCE;
    }

    public static short[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ShortArrayAsList) {
            return ((ShortArrayAsList) collection).toShortArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        short[] sArr = new short[length];
        for (int i3 = 0; i3 < length; i3++) {
            sArr[i3] = ((Number) Preconditions.checkNotNull(array[i3])).shortValue();
        }
        return sArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(short s3) {
        return new byte[]{(byte) (s3 >> 8), (byte) s3};
    }

    /* access modifiers changed from: private */
    public static int indexOf(short[] sArr, short s3, int i3, int i4) {
        while (i3 < i4) {
            if (sArr[i3] == s3) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(short[] sArr, short s3, int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            if (sArr[i5] == s3) {
                return i5;
            }
        }
        return -1;
    }

    public static void rotate(short[] sArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(sArr);
        Preconditions.checkPositionIndexes(i4, i5, sArr.length);
        if (sArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(sArr, i4, i8);
                reverse(sArr, i8, i5);
                reverse(sArr, i4, i5);
            }
        }
    }

    public static int indexOf(short[] sArr, short[] sArr2) {
        Preconditions.checkNotNull(sArr, "array");
        Preconditions.checkNotNull(sArr2, TypedValues.AttributesType.S_TARGET);
        if (sArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (sArr.length - sArr2.length) + 1) {
            int i4 = 0;
            while (i4 < sArr2.length) {
                if (sArr[i3 + i4] != sArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(short[] sArr, int i3, int i4) {
        Preconditions.checkNotNull(sArr);
        Preconditions.checkPositionIndexes(i3, i4, sArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            short s3 = sArr[i3];
            sArr[i3] = sArr[i5];
            sArr[i5] = s3;
            i3++;
        }
    }

    public static void sortDescending(short[] sArr, int i3, int i4) {
        Preconditions.checkNotNull(sArr);
        Preconditions.checkPositionIndexes(i3, i4, sArr.length);
        Arrays.sort(sArr, i3, i4);
        reverse(sArr, i3, i4);
    }
}
