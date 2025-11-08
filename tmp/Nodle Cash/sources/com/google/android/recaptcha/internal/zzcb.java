package com.google.android.recaptcha.internal;

import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzcb {
    @NotNull
    public static final zzcb zza = new zzcb();
    @Nullable
    private static Set zzb;
    @Nullable
    private static Set zzc;
    @Nullable
    private static Long zzd;
    private static int zze;

    private zzcb() {
    }

    public static final void zza(@NotNull zznz zznz) {
        zzb = CollectionsKt.toSet(zznz.zzf().zzi());
        zzc = CollectionsKt.toSet(zznz.zzg().zzi());
    }

    public static final boolean zzb(@NotNull String str) {
        Set set = zzb;
        if (set == null || zzc == null) {
            if (zzd == null) {
                zzd = Long.valueOf(System.currentTimeMillis());
            }
            zze++;
            return true;
        }
        Intrinsics.checkNotNull(set, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
        if (set.isEmpty()) {
            return true;
        }
        Set set2 = zzc;
        Intrinsics.checkNotNull(set2, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
        if (zzc(str, set2)) {
            return false;
        }
        return zzc(str, set);
    }

    private static final boolean zzc(String str, Set set) {
        String str2 = "";
        for (String valueOf : StringsKt__StringsKt.split$default((CharSequence) str, new char[]{ClassUtils.PACKAGE_SEPARATOR_CHAR}, false, 0, 6, (Object) null)) {
            String concat = str2.concat(String.valueOf(valueOf));
            if (set.contains(concat)) {
                return true;
            }
            str2 = concat.concat(JwtUtilsKt.JWT_DELIMITER);
        }
        return false;
    }
}
