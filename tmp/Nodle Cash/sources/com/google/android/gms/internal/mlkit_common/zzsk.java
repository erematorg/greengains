package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

public final class zzsk implements zzry {
    private final zzmw zza;
    private zzqt zzb = new zzqt();

    private zzsk(zzmw zzmw, int i3) {
        this.zza = zzmw;
        zzsv.zza();
    }

    public static zzry zzf(zzmw zzmw) {
        return new zzsk(zzmw, 0);
    }

    public static zzry zzg() {
        return new zzsk(new zzmw(), 0);
    }

    public final zzry zza(zzmv zzmv) {
        this.zza.zzf(zzmv);
        return this;
    }

    public final zzry zzb(zznc zznc) {
        this.zza.zzi(zznc);
        return this;
    }

    public final zzry zzc(zzqt zzqt) {
        this.zzb = zzqt;
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = r1.zzk();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzd() {
        /*
            r1 = this;
            com.google.android.gms.internal.mlkit_common.zzmw r1 = r1.zza
            com.google.android.gms.internal.mlkit_common.zzmy r1 = r1.zzk()
            com.google.android.gms.internal.mlkit_common.zzqv r1 = r1.zzf()
            if (r1 == 0) goto L_0x0024
            java.lang.String r0 = r1.zzk()
            if (r0 == 0) goto L_0x0024
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0019
            goto L_0x0024
        L_0x0019:
            java.lang.String r1 = r1.zzk()
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            return r1
        L_0x0024:
            java.lang.String r1 = "NA"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_common.zzsk.zzd():java.lang.String");
    }

    public final byte[] zze(int i3, boolean z2) {
        this.zzb.zzf(Boolean.valueOf(1 == (i3 ^ 1)));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzsv.zza();
            if (i3 == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzkr.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzmy zzk = this.zza.zzk();
            zzbg zzbg = new zzbg();
            zzkr.zza.configure(zzbg);
            return zzbg.zza().zza(zzk);
        } catch (UnsupportedEncodingException e3) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e3);
        }
    }
}
