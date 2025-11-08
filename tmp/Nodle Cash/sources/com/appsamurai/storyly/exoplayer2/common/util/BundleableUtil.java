package com.appsamurai.storyly.exoplayer2.common.util;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class BundleableUtil {
    private BundleableUtil() {
    }

    public static void ensureClassLoader(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader((ClassLoader) Util.castNonNull(BundleableUtil.class.getClassLoader()));
        }
    }

    public static <T extends Bundleable> ImmutableList<T> fromBundleList(Bundleable.Creator<T> creator, List<Bundle> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i3 = 0; i3 < list.size(); i3++) {
            builder.add((Object) creator.fromBundle((Bundle) Assertions.checkNotNull(list.get(i3))));
        }
        return builder.build();
    }

    public static <T extends Bundleable> SparseArray<T> fromBundleSparseArray(Bundleable.Creator<T> creator, SparseArray<Bundle> sparseArray) {
        SparseArray<T> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            sparseArray2.put(sparseArray.keyAt(i3), creator.fromBundle(sparseArray.valueAt(i3)));
        }
        return sparseArray2;
    }

    public static <T extends Bundleable> ArrayList<Bundle> toBundleArrayList(Collection<T> collection) {
        ArrayList<Bundle> arrayList = new ArrayList<>(collection.size());
        for (T bundle : collection) {
            arrayList.add(bundle.toBundle());
        }
        return arrayList;
    }

    public static <T extends Bundleable> ImmutableList<Bundle> toBundleList(List<T> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i3 = 0; i3 < list.size(); i3++) {
            builder.add((Object) ((Bundleable) list.get(i3)).toBundle());
        }
        return builder.build();
    }

    public static <T extends Bundleable> SparseArray<Bundle> toBundleSparseArray(SparseArray<T> sparseArray) {
        SparseArray<Bundle> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            sparseArray2.put(sparseArray.keyAt(i3), ((Bundleable) sparseArray.valueAt(i3)).toBundle());
        }
        return sparseArray2;
    }
}
