package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
final class Platform {
    private Platform() {
    }

    public static <T> T[] copy(Object[] objArr, int i3, int i4, T[] tArr) {
        return Arrays.copyOfRange(objArr, i3, i4, tArr.getClass());
    }

    public static <E extends Enum<E>> Class<E> getDeclaringClassOrObjectForJ2cl(E e3) {
        return e3.getDeclaringClass();
    }

    public static <T> T[] newArray(T[] tArr, int i3) {
        if (tArr.length != 0) {
            tArr = Arrays.copyOf(tArr, 0);
        }
        return Arrays.copyOf(tArr, i3);
    }

    public static <K, V> Map<K, V> newHashMapWithExpectedSize(int i3) {
        return CompactHashMap.createWithExpectedSize(i3);
    }

    public static <E> Set<E> newHashSetWithExpectedSize(int i3) {
        return CompactHashSet.createWithExpectedSize(i3);
    }

    public static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int i3) {
        return CompactLinkedHashMap.createWithExpectedSize(i3);
    }

    public static <E> Set<E> newLinkedHashSetWithExpectedSize(int i3) {
        return CompactLinkedHashSet.createWithExpectedSize(i3);
    }

    public static <E> Set<E> preservesInsertionOrderOnAddsSet() {
        return CompactHashSet.create();
    }

    public static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
        return CompactHashMap.create();
    }

    public static int reduceExponentIfGwt(int i3) {
        return i3;
    }

    public static int reduceIterationsIfGwt(int i3) {
        return i3;
    }

    @J2ktIncompatible
    public static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }
}
