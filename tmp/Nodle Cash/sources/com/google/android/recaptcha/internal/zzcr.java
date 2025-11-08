package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;

public final class zzcr implements zzdd {
    @NotNull
    public static final zzcr zza = new zzcr();

    private zzcr() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        if (zzpqArr.length == 2) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str != null) {
                Object zza3 = zzcj.zzc().zza(zzpqArr[1]);
                if (zza3 == null) {
                    throw new zzae(4, 4, (Throwable) null);
                } else if (!(zza3 instanceof Integer) && !(zza3 instanceof Short) && !(zza3 instanceof Byte) && !(zza3 instanceof Long) && !(zza3 instanceof Double) && !(zza3 instanceof Float) && !(zza3 instanceof Boolean) && !(zza3 instanceof Character) && !(zza3 instanceof String)) {
                    throw new zzae(4, 7, (Throwable) null);
                } else {
                    zzcj.zzi().zzb(str, zza3.toString());
                }
            } else {
                throw new zzae(4, 5, (Throwable) null);
            }
        } else {
            throw new zzae(4, 3, (Throwable) null);
        }
    }
}
