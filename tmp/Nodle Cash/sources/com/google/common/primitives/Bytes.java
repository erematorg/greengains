package com.google.common.primitives;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class Bytes {

    @GwtCompatible
    public static class ByteArrayAsList extends AbstractList<Byte> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final byte[] array;
        final int end;
        final int start;

        public ByteArrayAsList(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Byte) && Bytes.indexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ByteArrayAsList)) {
                return super.equals(obj);
            }
            ByteArrayAsList byteArrayAsList = (ByteArrayAsList) obj;
            int size = size();
            if (byteArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != byteArrayAsList.array[byteArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Bytes.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Byte) || (access$000 = Bytes.indexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Byte) || (access$100 = Bytes.lastIndexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Byte> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            byte[] bArr = this.array;
            int i5 = this.start;
            return new ByteArrayAsList(bArr, i3 + i5, i5 + i4);
        }

        public byte[] toByteArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
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

        public ByteArrayAsList(byte[] bArr, int i3, int i4) {
            this.array = bArr;
            this.start = i3;
            this.end = i4;
        }

        public Byte get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Byte.valueOf(this.array[this.start + i3]);
        }

        public Byte set(int i3, Byte b3) {
            Preconditions.checkElementIndex(i3, size());
            byte[] bArr = this.array;
            int i4 = this.start;
            byte b4 = bArr[i4 + i3];
            bArr[i4 + i3] = ((Byte) Preconditions.checkNotNull(b3)).byteValue();
            return Byte.valueOf(b4);
        }
    }

    private Bytes() {
    }

    public static List<Byte> asList(byte... bArr) {
        return bArr.length == 0 ? Collections.emptyList() : new ByteArrayAsList(bArr);
    }

    public static byte[] concat(byte[]... bArr) {
        int i3 = 0;
        for (byte[] length : bArr) {
            i3 += length.length;
        }
        byte[] bArr2 = new byte[i3];
        int i4 = 0;
        for (byte[] bArr3 : bArr) {
            System.arraycopy(bArr3, 0, bArr2, i4, bArr3.length);
            i4 += bArr3.length;
        }
        return bArr2;
    }

    public static boolean contains(byte[] bArr, byte b3) {
        for (byte b4 : bArr) {
            if (b4 == b3) {
                return true;
            }
        }
        return false;
    }

    public static byte[] ensureCapacity(byte[] bArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return bArr.length < i3 ? Arrays.copyOf(bArr, i3 + i4) : bArr;
    }

    public static int hashCode(byte b3) {
        return b3;
    }

    public static int indexOf(byte[] bArr, byte b3) {
        return indexOf(bArr, b3, 0, bArr.length);
    }

    public static int lastIndexOf(byte[] bArr, byte b3) {
        return lastIndexOf(bArr, b3, 0, bArr.length);
    }

    public static void reverse(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        reverse(bArr, 0, bArr.length);
    }

    public static void rotate(byte[] bArr, int i3) {
        rotate(bArr, i3, 0, bArr.length);
    }

    public static byte[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ByteArrayAsList) {
            return ((ByteArrayAsList) collection).toByteArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        byte[] bArr = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i3] = ((Number) Preconditions.checkNotNull(array[i3])).byteValue();
        }
        return bArr;
    }

    /* access modifiers changed from: private */
    public static int indexOf(byte[] bArr, byte b3, int i3, int i4) {
        while (i3 < i4) {
            if (bArr[i3] == b3) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(byte[] bArr, byte b3, int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            if (bArr[i5] == b3) {
                return i5;
            }
        }
        return -1;
    }

    public static void rotate(byte[] bArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i4, i5, bArr.length);
        if (bArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(bArr, i4, i8);
                reverse(bArr, i8, i5);
                reverse(bArr, i4, i5);
            }
        }
    }

    public static int indexOf(byte[] bArr, byte[] bArr2) {
        Preconditions.checkNotNull(bArr, "array");
        Preconditions.checkNotNull(bArr2, TypedValues.AttributesType.S_TARGET);
        if (bArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (bArr.length - bArr2.length) + 1) {
            int i4 = 0;
            while (i4 < bArr2.length) {
                if (bArr[i3 + i4] != bArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(byte[] bArr, int i3, int i4) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i3, i4, bArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            byte b3 = bArr[i3];
            bArr[i3] = bArr[i5];
            bArr[i5] = b3;
            i3++;
        }
    }
}
