package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@KeepForSdk
public final class ArrayUtils {
    private ArrayUtils() {
    }

    @NonNull
    @KeepForSdk
    public static <T> T[] concat(@NonNull T[]... tArr) {
        if (tArr.length == 0) {
            return (Object[]) Array.newInstance(tArr.getClass(), 0);
        }
        int i3 = 0;
        for (T[] length : tArr) {
            i3 += length.length;
        }
        T[] copyOf = Arrays.copyOf(tArr[0], i3);
        int length2 = tArr[0].length;
        for (int i4 = 1; i4 < tArr.length; i4++) {
            T[] tArr2 = tArr[i4];
            int length3 = tArr2.length;
            System.arraycopy(tArr2, 0, copyOf, length2, length3);
            length2 += length3;
        }
        return copyOf;
    }

    @NonNull
    @KeepForSdk
    public static byte[] concatByteArrays(@NonNull byte[]... bArr) {
        if (bArr.length == 0) {
            return new byte[0];
        }
        int i3 = 0;
        for (byte[] length : bArr) {
            i3 += length.length;
        }
        byte[] copyOf = Arrays.copyOf(bArr[0], i3);
        int length2 = bArr[0].length;
        for (int i4 = 1; i4 < bArr.length; i4++) {
            byte[] bArr2 = bArr[i4];
            int length3 = bArr2.length;
            System.arraycopy(bArr2, 0, copyOf, length2, length3);
            length2 += length3;
        }
        return copyOf;
    }

    @KeepForSdk
    public static boolean contains(@Nullable int[] iArr, int i3) {
        if (iArr != null) {
            for (int i4 : iArr) {
                if (i4 == i3) {
                    return true;
                }
            }
        }
        return false;
    }

    @NonNull
    @KeepForSdk
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    @KeepForSdk
    @Nullable
    public static <T> T[] removeAll(@NonNull T[] tArr, @NonNull T... tArr2) {
        int length;
        int i3;
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || (length = tArr2.length) == 0) {
            return Arrays.copyOf(tArr, tArr.length);
        }
        T[] tArr3 = (Object[]) Array.newInstance(tArr2.getClass().getComponentType(), r3);
        if (length == 1) {
            i3 = 0;
            for (T t2 : tArr) {
                if (!Objects.equal(tArr2[0], t2)) {
                    tArr3[i3] = t2;
                    i3++;
                }
            }
        } else {
            int i4 = 0;
            for (T t3 : tArr) {
                if (!contains(tArr2, t3)) {
                    tArr3[i4] = t3;
                    i4++;
                }
            }
            i3 = i4;
        }
        if (tArr3 == null) {
            return null;
        }
        return i3 == tArr3.length ? tArr3 : Arrays.copyOf(tArr3, i3);
    }

    @NonNull
    @KeepForSdk
    public static <T> ArrayList<T> toArrayList(@NonNull T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>(r0);
        for (T add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    @NonNull
    @KeepForSdk
    public static int[] toPrimitiveArray(@Nullable Collection<Integer> collection) {
        int i3 = 0;
        if (collection == null || collection.isEmpty()) {
            return new int[0];
        }
        int[] iArr = new int[collection.size()];
        for (Integer intValue : collection) {
            iArr[i3] = intValue.intValue();
            i3++;
        }
        return iArr;
    }

    @KeepForSdk
    @Nullable
    public static Integer[] toWrapperArray(@Nullable int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        Integer[] numArr = new Integer[length];
        for (int i3 = 0; i3 < length; i3++) {
            numArr[i3] = Integer.valueOf(iArr[i3]);
        }
        return numArr;
    }

    @KeepForSdk
    public static void writeArray(@NonNull StringBuilder sb, @NonNull double[] dArr) {
        int length = dArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(dArr[i3]);
        }
    }

    @KeepForSdk
    public static void writeStringArray(@NonNull StringBuilder sb, @NonNull String[] strArr) {
        int length = strArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(strArr[i3]);
            sb.append("\"");
        }
    }

    @KeepForSdk
    public static <T> boolean contains(@NonNull T[] tArr, @Nullable T t2) {
        int length = tArr != null ? tArr.length : 0;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            } else if (!Objects.equal(tArr[i3], t2)) {
                i3++;
            } else if (i3 >= 0) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static void writeArray(@NonNull StringBuilder sb, @NonNull float[] fArr) {
        int length = fArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(fArr[i3]);
        }
    }

    @KeepForSdk
    public static void writeArray(@NonNull StringBuilder sb, @NonNull int[] iArr) {
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(iArr[i3]);
        }
    }

    @KeepForSdk
    public static void writeArray(@NonNull StringBuilder sb, @NonNull long[] jArr) {
        int length = jArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(jArr[i3]);
        }
    }

    @KeepForSdk
    public static <T> void writeArray(@NonNull StringBuilder sb, @NonNull T[] tArr) {
        int length = tArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(tArr[i3]);
        }
    }

    @KeepForSdk
    public static void writeArray(@NonNull StringBuilder sb, @NonNull boolean[] zArr) {
        int length = zArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(zArr[i3]);
        }
    }
}
