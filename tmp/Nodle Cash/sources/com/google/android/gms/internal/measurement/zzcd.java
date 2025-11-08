package com.google.android.gms.internal.measurement;

final class zzcd extends zzcl {
    private String zza;
    private boolean zzb;
    private zzco zzc;
    private byte zzd;

    public final zzcl zza(String str) {
        this.zza = str;
        return this;
    }

    public final zzcl zzb(boolean z2) {
        this.zzb = z2;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzcl zza(zzco zzco) {
        if (zzco != null) {
            this.zzc = zzco;
            return this;
        }
        throw new NullPointerException("Null filePurpose");
    }

    public final zzcl zza(boolean z2) {
        this.zzd = (byte) (this.zzd | 1);
        return this;
    }

    public final zzcm zza() {
        if (this.zzd == 3 && this.zza != null && this.zzc != null) {
            return new zzce(this.zza, false, this.zzb, (zzcc) null, (zzcb) null, this.zzc, (zzcg) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" fileOwner");
        }
        if ((this.zzd & 1) == 0) {
            sb.append(" hasDifferentDmaOwner");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" skipChecks");
        }
        if (this.zzc == null) {
            sb.append(" filePurpose");
        }
        throw new IllegalStateException("Missing required properties:".concat(String.valueOf(sb)));
    }
}
