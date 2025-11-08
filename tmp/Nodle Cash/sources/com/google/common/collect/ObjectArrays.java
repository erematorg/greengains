package com.google.common.collect;

import A.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class ObjectArrays {
    private ObjectArrays() {
    }

    @CanIgnoreReturnValue
    public static Object checkElementNotNull(@CheckForNull Object obj, int i3) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(a.k("at index ", i3));
    }

    @CanIgnoreReturnValue
    public static Object[] checkElementsNotNull(Object... objArr) {
        checkElementsNotNull(objArr, objArr.length);
        return objArr;
    }

    @GwtIncompatible
    public static <T> T[] concat(T[] tArr, T[] tArr2, Class<T> cls) {
        T[] newArray = newArray(cls, tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, newArray, 0, tArr.length);
        System.arraycopy(tArr2, 0, newArray, tArr.length, tArr2.length);
        return newArray;
    }

    public static Object[] copyAsObjectArray(Object[] objArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, objArr.length);
        if (i4 == 0) {
            return new Object[0];
        }
        Object[] objArr2 = new Object[i4];
        System.arraycopy(objArr, i3, objArr2, 0, i4);
        return objArr2;
    }

    @CanIgnoreReturnValue
    private static Object[] fillArray(Iterable<?> iterable, Object[] objArr) {
        int i3 = 0;
        for (Object obj : iterable) {
            objArr[i3] = obj;
            i3++;
        }
        return objArr;
    }

    @GwtIncompatible
    public static <T> T[] newArray(Class<T> cls, int i3) {
        return (Object[]) Array.newInstance(cls, i3);
    }

    public static void swap(Object[] objArr, int i3, int i4) {
        Object obj = objArr[i3];
        objArr[i3] = objArr[i4];
        objArr[i4] = obj;
    }

    public static <T> T[] toArrayImpl(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = newArray(tArr, size);
        }
        fillArray(collection, tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    @CanIgnoreReturnValue
    public static Object[] checkElementsNotNull(Object[] objArr, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            checkElementNotNull(objArr[i4], i4);
        }
        return objArr;
    }

    public static <T> T[] newArray(T[] tArr, int i3) {
        return Platform.newArray(tArr, i3);
    }

    public static <T> T[] concat(@ParametricNullness T t2, T[] tArr) {
        T[] newArray = newArray(tArr, tArr.length + 1);
        newArray[0] = t2;
        System.arraycopy(tArr, 0, newArray, 1, tArr.length);
        return newArray;
    }

    public static <T> T[] concat(T[] tArr, @ParametricNullness T t2) {
        T[] copyOf = Arrays.copyOf(tArr, tArr.length + 1);
        copyOf[tArr.length] = t2;
        return copyOf;
    }

    public static <T> T[] toArrayImpl(Object[] objArr, int i3, int i4, T[] tArr) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, objArr.length);
        if (tArr.length < i4) {
            tArr = newArray(tArr, i4);
        } else if (tArr.length > i4) {
            tArr[i4] = null;
        }
        System.arraycopy(objArr, i3, tArr, 0, i4);
        return tArr;
    }

    public static Object[] toArrayImpl(Collection<?> collection) {
        return fillArray(collection, new Object[collection.size()]);
    }
}
