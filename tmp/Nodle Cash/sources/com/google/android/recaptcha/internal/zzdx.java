package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;

public final class zzdx implements zzdd {
    @NotNull
    public static final zzdx zza = new zzdx();

    private zzdx() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        if (zzpqArr.length == 2) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != (zza2 instanceof int[])) {
                zza2 = null;
            }
            int[] iArr = (int[]) zza2;
            if (iArr != null) {
                Object zza3 = zzcj.zzc().zza(zzpqArr[1]);
                if (true != (zza3 instanceof String)) {
                    zza3 = null;
                }
                String str = (String) zza3;
                if (str != null) {
                    zzck zzc = zzcj.zzc();
                    StringBuilder sb = new StringBuilder();
                    try {
                        for (int charAt : iArr) {
                            sb.append(str.charAt(charAt));
                        }
                        zzc.zzf(i3, sb.toString());
                    } catch (Exception e3) {
                        throw new zzae(4, 22, e3);
                    }
                } else {
                    throw new zzae(4, 5, (Throwable) null);
                }
            } else {
                throw new zzae(4, 5, (Throwable) null);
            }
        } else {
            throw new zzae(4, 3, (Throwable) null);
        }
    }
}
