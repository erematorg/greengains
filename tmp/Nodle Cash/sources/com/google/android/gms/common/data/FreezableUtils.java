package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public final class FreezableUtils {
    @NonNull
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(@NonNull ArrayList<E> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>(arrayList.size());
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            arrayList2.add(((Freezable) arrayList.get(i3)).freeze());
        }
        return arrayList2;
    }

    @NonNull
    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(@NonNull Iterable<E> iterable) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (E freeze : iterable) {
            arrayList.add(freeze.freeze());
        }
        return arrayList;
    }

    @NonNull
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(@NonNull E[] eArr) {
        ArrayList<T> arrayList = new ArrayList<>(eArr.length);
        for (E freeze : eArr) {
            arrayList.add(freeze.freeze());
        }
        return arrayList;
    }
}
