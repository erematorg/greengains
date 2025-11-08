package com.google.android.recaptcha.internal;

import java.lang.reflect.Proxy;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class zzcy implements zzdd {
    @NotNull
    public static final zzcy zza = new zzcy();

    private zzcy() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        int i4;
        int length = zzpqArr.length;
        if (length == 4 || length == 5) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str != null) {
                Object zza3 = zzcj.zzc().zza(zzpqArr[1]);
                if (true != Objects.nonNull(zza3)) {
                    zza3 = null;
                }
                if (zza3 != null) {
                    Object zza4 = zzcj.zzc().zza(zzpqArr[2]);
                    if (true != (zza4 instanceof String)) {
                        zza4 = null;
                    }
                    String str2 = (String) zza4;
                    if (str2 != null) {
                        String zza5 = zzcj.zzh().zza(str2);
                        Object zza6 = zzcj.zzc().zza(zzpqArr[3]);
                        if (length == 5) {
                            Object zza7 = zzcj.zzc().zza(zzpqArr[4]);
                            if (true != (zza7 instanceof Integer)) {
                                zza7 = null;
                            }
                            Integer num = (Integer) zza7;
                            if (num != null) {
                                i4 = num.intValue();
                            } else {
                                throw new zzae(4, 5, (Throwable) null);
                            }
                        } else {
                            i4 = -1;
                        }
                        try {
                            if (zza3 instanceof String) {
                                zza3 = zzcj.zzh().zza((String) zza3);
                            }
                            Class zza8 = zzci.zza(zza3);
                            zzcj.zzc().zzf(i3, Proxy.newProxyInstance(zza8.getClassLoader(), new Class[]{zza8}, new zzcf(new zzcx(zzcj, str, i4), zza5, zza6)));
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
            throw new zzae(4, 3, (Throwable) null);
        }
    }
}
