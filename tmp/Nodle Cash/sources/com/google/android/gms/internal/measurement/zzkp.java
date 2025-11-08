package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzlc;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzkp {
    static final zzkp zza = new zzkp(true);
    private static volatile boolean zzb = false;
    private static volatile zzkp zzc;
    private final Map<zza, zzlc.zzf<?, ?>> zzd;

    public static final class zza {
        private final Object zza;
        private final int zzb;

        public zza(Object obj, int i3) {
            this.zza = obj;
            this.zzb = i3;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza2 = (zza) obj;
            return this.zza == zza2.zza && this.zzb == zza2.zzb;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }
    }

    public zzkp() {
        this.zzd = new HashMap();
    }

    public static zzkp zza() {
        zzkp zzkp = zzc;
        if (zzkp != null) {
            return zzkp;
        }
        synchronized (zzkp.class) {
            try {
                zzkp zzkp2 = zzc;
                if (zzkp2 != null) {
                    return zzkp2;
                }
                zzkp zza2 = zzla.zza(zzkp.class);
                zzc = zza2;
                return zza2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private zzkp(boolean z2) {
        this.zzd = Collections.emptyMap();
    }

    public final <ContainingType extends zzml> zzlc.zzf<ContainingType, ?> zza(ContainingType containingtype, int i3) {
        return this.zzd.get(new zza(containingtype, i3));
    }
}
