package com.google.android.gms.measurement.internal;

import A.a;
import android.os.Bundle;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;

public final class zzgh {
    private static final AtomicReference<String[]> zza = new AtomicReference<>();
    private static final AtomicReference<String[]> zzb = new AtomicReference<>();
    private static final AtomicReference<String[]> zzc = new AtomicReference<>();
    private final zzgg zzd;

    public zzgh(zzgg zzgg) {
        this.zzd = zzgg;
    }

    private final String zza(Object[] objArr) {
        String str;
        if (objArr == null) {
            return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder p2 = a.p("[");
        for (Bundle bundle : objArr) {
            if (bundle instanceof Bundle) {
                str = zza(bundle);
            } else {
                str = String.valueOf(bundle);
            }
            if (str != null) {
                if (p2.length() != 1) {
                    p2.append(", ");
                }
                p2.append(str);
            }
        }
        p2.append("]");
        return p2.toString();
    }

    public final String zzb(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : zza(str, zzje.zzb, zzje.zza, zzb);
    }

    public final String zzc(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : str.startsWith("_exp_") ? a.l("experiment_id(", str, ")") : zza(str, zzjh.zzb, zzjh.zza, zzc);
    }

    public final String zza(Bundle bundle) {
        String str;
        if (bundle == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return bundle.toString();
        }
        StringBuilder p2 = a.p("Bundle[{");
        for (String next : bundle.keySet()) {
            if (p2.length() != 8) {
                p2.append(", ");
            }
            p2.append(zzb(next));
            p2.append(StickyVariantProvider.KEY_VALUE_DELIMITER);
            Object obj = bundle.get(next);
            if (obj instanceof Bundle) {
                str = zza(new Object[]{obj});
            } else if (obj instanceof Object[]) {
                str = zza((Object[]) obj);
            } else if (obj instanceof ArrayList) {
                str = zza(((ArrayList) obj).toArray());
            } else {
                str = String.valueOf(obj);
            }
            p2.append(str);
        }
        p2.append("}]");
        return p2.toString();
    }

    public final String zza(zzbh zzbh) {
        String str = null;
        if (zzbh == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return zzbh.toString();
        }
        StringBuilder sb = new StringBuilder("origin=");
        sb.append(zzbh.zzc);
        sb.append(",name=");
        sb.append(zza(zzbh.zza));
        sb.append(",params=");
        zzbc zzbc = zzbh.zzb;
        if (zzbc != null) {
            if (!this.zzd.zza()) {
                str = zzbc.toString();
            } else {
                str = zza(zzbc.zzb());
            }
        }
        sb.append(str);
        return sb.toString();
    }

    public final String zza(String str) {
        if (str == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return str;
        }
        return zza(str, zzjf.zzc, zzjf.zza, zza);
    }

    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (Objects.equals(str, strArr[i3])) {
                synchronized (atomicReference) {
                    try {
                        String[] strArr3 = atomicReference.get();
                        if (strArr3 == null) {
                            strArr3 = new String[strArr2.length];
                            atomicReference.set(strArr3);
                        }
                        if (strArr3[i3] == null) {
                            strArr3[i3] = strArr2[i3] + "(" + strArr[i3] + ")";
                        }
                        str2 = strArr3[i3];
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return str2;
            }
        }
        return str;
    }
}
