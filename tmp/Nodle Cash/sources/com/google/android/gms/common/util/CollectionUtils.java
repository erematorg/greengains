package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.compose.animation.core.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.InlineMe;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@KeepForSdk
public final class CollectionUtils {
    private CollectionUtils() {
    }

    @KeepForSdk
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        if (collection == null) {
            return true;
        }
        return collection.isEmpty();
    }

    @InlineMe(imports = {"java.util.Collections"}, replacement = "Collections.emptyList()")
    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    @NonNull
    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(@NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3, @NonNull K k4, @NonNull V v4) {
        Map zza = zza(3, false);
        zza.put(k2, v2);
        zza.put(k3, v3);
        zza.put(k4, v4);
        return Collections.unmodifiableMap(zza);
    }

    @NonNull
    @KeepForSdk
    public static <K, V> Map<K, V> mapOfKeyValueArrays(@NonNull K[] kArr, @NonNull V[] vArr) {
        int length = kArr.length;
        int length2 = vArr.length;
        if (length != length2) {
            throw new IllegalArgumentException(a.r("Key and values array lengths not equal: ", length, " != ", length2));
        } else if (length == 0) {
            return Collections.emptyMap();
        } else {
            if (length == 1) {
                return Collections.singletonMap(kArr[0], vArr[0]);
            }
            Map zza = zza(length, false);
            for (int i3 = 0; i3 < kArr.length; i3++) {
                zza.put(kArr[i3], vArr[i3]);
            }
            return Collections.unmodifiableMap(zza);
        }
    }

    @NonNull
    @KeepForSdk
    public static <T> Set<T> mutableSetOfWithSize(int i3) {
        return i3 == 0 ? new ArraySet() : zzb(i3, true);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(@NonNull T t2, @NonNull T t3, @NonNull T t4) {
        Set zzb = zzb(3, false);
        zzb.add(t2);
        zzb.add(t3);
        zzb.add(t4);
        return Collections.unmodifiableSet(zzb);
    }

    private static Map zza(int i3, boolean z2) {
        return i3 <= 256 ? new ArrayMap(i3) : new HashMap(i3, 1.0f);
    }

    private static Set zzb(int i3, boolean z2) {
        if (i3 <= (true != z2 ? 256 : 128)) {
            return new ArraySet(i3);
        }
        return new HashSet(i3, true != z2 ? 1.0f : 0.75f);
    }

    @InlineMe(imports = {"java.util.Collections"}, replacement = "Collections.singletonList(item)")
    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(@NonNull T t2) {
        return Collections.singletonList(t2);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(@NonNull T... tArr) {
        int length = tArr.length;
        if (length == 0) {
            return Collections.emptyList();
        }
        if (length != 1) {
            return Collections.unmodifiableList(Arrays.asList(tArr));
        }
        return Collections.singletonList(tArr[0]);
    }

    @NonNull
    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(@NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3, @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5, @NonNull K k6, @NonNull V v6, @NonNull K k7, @NonNull V v7) {
        Map zza = zza(6, false);
        zza.put(k2, v2);
        zza.put(k3, v3);
        zza.put(k4, v4);
        zza.put(k5, v5);
        zza.put(k6, v6);
        zza.put(k7, v7);
        return Collections.unmodifiableMap(zza);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(@NonNull T... tArr) {
        int length = tArr.length;
        if (length == 0) {
            return Collections.emptySet();
        }
        if (length == 1) {
            return Collections.singleton(tArr[0]);
        }
        if (length == 2) {
            T t2 = tArr[0];
            T t3 = tArr[1];
            Set zzb = zzb(2, false);
            zzb.add(t2);
            zzb.add(t3);
            return Collections.unmodifiableSet(zzb);
        } else if (length == 3) {
            return setOf(tArr[0], tArr[1], tArr[2]);
        } else {
            if (length != 4) {
                Set zzb2 = zzb(length, false);
                Collections.addAll(zzb2, tArr);
                return Collections.unmodifiableSet(zzb2);
            }
            T t4 = tArr[0];
            T t5 = tArr[1];
            T t6 = tArr[2];
            T t7 = tArr[3];
            Set zzb3 = zzb(4, false);
            zzb3.add(t4);
            zzb3.add(t5);
            zzb3.add(t6);
            zzb3.add(t7);
            return Collections.unmodifiableSet(zzb3);
        }
    }
}
