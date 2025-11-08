package com.google.android.recaptcha.internal;

import java.lang.reflect.Proxy;
import org.jetbrains.annotations.NotNull;

public final class zzcz implements zzdd {
    @NotNull
    public static final zzcz zza = new zzcz();

    private zzcz() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        int length = zzpqArr.length;
        Object obj = null;
        if (length == 4 || length == 5) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != (zza2 instanceof Integer)) {
                zza2 = null;
            }
            Integer num = (Integer) zza2;
            if (num != null) {
                int intValue = num.intValue();
                Object zza3 = zzcj.zzc().zza(zzpqArr[1]);
                if (true != (zza3 instanceof Integer)) {
                    zza3 = null;
                }
                Integer num2 = (Integer) zza3;
                if (num2 != null) {
                    int intValue2 = num2.intValue();
                    Object zza4 = zzcj.zzc().zza(zzpqArr[2]);
                    if (true != (zza4 instanceof String)) {
                        zza4 = null;
                    }
                    String str = (String) zza4;
                    if (str != null) {
                        String zza5 = zzcj.zzh().zza(str);
                        Object zza6 = zzcj.zzc().zza(zzpqArr[3]);
                        if (true != (zza6 instanceof String)) {
                            zza6 = null;
                        }
                        String str2 = (String) zza6;
                        if (str2 != null) {
                            String zza7 = zzcj.zzh().zza(str2);
                            if (length == 5) {
                                obj = zzcj.zzc().zza(zzpqArr[4]);
                            }
                            zzcg zzcg = new zzcg(intValue2);
                            try {
                                Class zza8 = zzci.zza(zza5);
                                zzcj.zzc().zzf(intValue, Proxy.newProxyInstance(zza8.getClassLoader(), new Class[]{zza8}, new zzch(zzcg, zza7, obj)));
                                zzcj.zzc().zzf(i3, zzcg);
                            } catch (Exception e3) {
                                throw new zzae(6, 20, e3);
                            }
                        } else {
                            throw new zzae(4, 5, (Throwable) null);
                        }
                    } else {
                        throw new zzae(4, 5, (Throwable) null);
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
