package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

public final class zzws implements zzwe {
    private final zzrd zza;
    private zzvb zzb = new zzvb();
    private final int zzc;

    private zzws(zzrd zzrd, int i3) {
        this.zza = zzrd;
        zzxb.zza();
        this.zzc = i3;
    }

    public static zzwe zzf(zzrd zzrd) {
        return new zzws(zzrd, 0);
    }

    public static zzwe zzg(zzrd zzrd, int i3) {
        return new zzws(zzrd, 1);
    }

    public final int zza() {
        return this.zzc;
    }

    public final zzwe zzb(zzrc zzrc) {
        this.zza.zzf(zzrc);
        return this;
    }

    public final zzwe zzc(zzvb zzvb) {
        this.zzb = zzvb;
        return this;
    }

    public final String zzd() {
        zzvd zzg = this.zza.zzk().zzg();
        return (zzg == null || zzba.zzc(zzg.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzg.zzk());
    }

    public final byte[] zze(int i3, boolean z2) {
        this.zzb.zzf(Boolean.valueOf(1 == (i3 ^ 1)));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzxb.zza();
            if (i3 == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzox.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzrf zzk = this.zza.zzk();
            zzfi zzfi = new zzfi();
            zzox.zza.configure(zzfi);
            return zzfi.zza().zza(zzk);
        } catch (UnsupportedEncodingException e3) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e3);
        }
    }
}
