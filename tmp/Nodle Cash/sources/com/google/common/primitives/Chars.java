package com.google.common.primitives;

import androidx.compose.ui.autofill.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
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
import kotlin.jvm.internal.CharCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Chars {
    public static final int BYTES = 2;

    @GwtCompatible
    public static class CharArrayAsList extends AbstractList<Character> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final char[] array;
        final int end;
        final int start;

        public CharArrayAsList(char[] cArr) {
            this(cArr, 0, cArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Character) && Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CharArrayAsList)) {
                return super.equals(obj);
            }
            CharArrayAsList charArrayAsList = (CharArrayAsList) obj;
            int size = size();
            if (charArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != charArrayAsList.array[charArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Chars.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Character) || (access$000 = Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Character) || (access$100 = Chars.lastIndexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Character> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            char[] cArr = this.array;
            int i5 = this.start;
            return new CharArrayAsList(cArr, i3 + i5, i5 + i4);
        }

        public char[] toCharArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 3);
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

        public CharArrayAsList(char[] cArr, int i3, int i4) {
            this.array = cArr;
            this.start = i3;
            this.end = i4;
        }

        public Character get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Character.valueOf(this.array[this.start + i3]);
        }

        public Character set(int i3, Character ch2) {
            Preconditions.checkElementIndex(i3, size());
            char[] cArr = this.array;
            int i4 = this.start;
            char c3 = cArr[i4 + i3];
            cArr[i4 + i3] = ((Character) Preconditions.checkNotNull(ch2)).charValue();
            return Character.valueOf(c3);
        }
    }

    public enum LexicographicalComparator implements Comparator<char[]> {
        INSTANCE;

        public String toString() {
            return "Chars.lexicographicalComparator()";
        }

        public int compare(char[] cArr, char[] cArr2) {
            int min = Math.min(cArr.length, cArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = Chars.compare(cArr[i3], cArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return cArr.length - cArr2.length;
        }
    }

    private Chars() {
    }

    public static List<Character> asList(char... cArr) {
        return cArr.length == 0 ? Collections.emptyList() : new CharArrayAsList(cArr);
    }

    public static char checkedCast(long j2) {
        char c3 = (char) ((int) j2);
        Preconditions.checkArgument(((long) c3) == j2, "Out of range: %s", j2);
        return c3;
    }

    public static int compare(char c3, char c4) {
        return c3 - c4;
    }

    public static char[] concat(char[]... cArr) {
        int i3 = 0;
        for (char[] length : cArr) {
            i3 += length.length;
        }
        char[] cArr2 = new char[i3];
        int i4 = 0;
        for (char[] cArr3 : cArr) {
            System.arraycopy(cArr3, 0, cArr2, i4, cArr3.length);
            i4 += cArr3.length;
        }
        return cArr2;
    }

    public static char constrainToRange(char c3, char c4, char c5) {
        Preconditions.checkArgument(c4 <= c5, "min (%s) must be less than or equal to max (%s)", c4, c5);
        return c3 < c4 ? c4 : c3 < c5 ? c3 : c5;
    }

    public static boolean contains(char[] cArr, char c3) {
        for (char c4 : cArr) {
            if (c4 == c3) {
                return true;
            }
        }
        return false;
    }

    public static char[] ensureCapacity(char[] cArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return cArr.length < i3 ? Arrays.copyOf(cArr, i3 + i4) : cArr;
    }

    @GwtIncompatible
    public static char fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static char fromBytes(byte b3, byte b4) {
        return (char) ((b3 << 8) | (b4 & 255));
    }

    public static int hashCode(char c3) {
        return c3;
    }

    public static int indexOf(char[] cArr, char c3) {
        return indexOf(cArr, c3, 0, cArr.length);
    }

    public static String join(String str, char... cArr) {
        Preconditions.checkNotNull(str);
        int length = cArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(a.c(length, 1, str.length(), length));
        sb.append(cArr[0]);
        for (int i3 = 1; i3 < length; i3++) {
            sb.append(str);
            sb.append(cArr[i3]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(char[] cArr, char c3) {
        return lastIndexOf(cArr, c3, 0, cArr.length);
    }

    public static Comparator<char[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static char max(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c3 = cArr[0];
        for (int i3 = 1; i3 < cArr.length; i3++) {
            char c4 = cArr[i3];
            if (c4 > c3) {
                c3 = c4;
            }
        }
        return c3;
    }

    public static char min(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c3 = cArr[0];
        for (int i3 = 1; i3 < cArr.length; i3++) {
            char c4 = cArr[i3];
            if (c4 < c3) {
                c3 = c4;
            }
        }
        return c3;
    }

    public static void reverse(char[] cArr) {
        Preconditions.checkNotNull(cArr);
        reverse(cArr, 0, cArr.length);
    }

    public static void rotate(char[] cArr, int i3) {
        rotate(cArr, i3, 0, cArr.length);
    }

    public static char saturatedCast(long j2) {
        if (j2 > 65535) {
            return CharCompanionObject.MAX_VALUE;
        }
        if (j2 < 0) {
            return 0;
        }
        return (char) ((int) j2);
    }

    public static void sortDescending(char[] cArr) {
        Preconditions.checkNotNull(cArr);
        sortDescending(cArr, 0, cArr.length);
    }

    public static char[] toArray(Collection<Character> collection) {
        if (collection instanceof CharArrayAsList) {
            return ((CharArrayAsList) collection).toCharArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        char[] cArr = new char[length];
        for (int i3 = 0; i3 < length; i3++) {
            cArr[i3] = ((Character) Preconditions.checkNotNull(array[i3])).charValue();
        }
        return cArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(char c3) {
        return new byte[]{(byte) (c3 >> 8), (byte) c3};
    }

    /* access modifiers changed from: private */
    public static int indexOf(char[] cArr, char c3, int i3, int i4) {
        while (i3 < i4) {
            if (cArr[i3] == c3) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(char[] cArr, char c3, int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            if (cArr[i5] == c3) {
                return i5;
            }
        }
        return -1;
    }

    public static void rotate(char[] cArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(cArr);
        Preconditions.checkPositionIndexes(i4, i5, cArr.length);
        if (cArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(cArr, i4, i8);
                reverse(cArr, i8, i5);
                reverse(cArr, i4, i5);
            }
        }
    }

    public static int indexOf(char[] cArr, char[] cArr2) {
        Preconditions.checkNotNull(cArr, "array");
        Preconditions.checkNotNull(cArr2, TypedValues.AttributesType.S_TARGET);
        if (cArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (cArr.length - cArr2.length) + 1) {
            int i4 = 0;
            while (i4 < cArr2.length) {
                if (cArr[i3 + i4] != cArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(char[] cArr, int i3, int i4) {
        Preconditions.checkNotNull(cArr);
        Preconditions.checkPositionIndexes(i3, i4, cArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            char c3 = cArr[i3];
            cArr[i3] = cArr[i5];
            cArr[i5] = c3;
            i3++;
        }
    }

    public static void sortDescending(char[] cArr, int i3, int i4) {
        Preconditions.checkNotNull(cArr);
        Preconditions.checkPositionIndexes(i3, i4, cArr.length);
        Arrays.sort(cArr, i3, i4);
        reverse(cArr, i3, i4);
    }
}
