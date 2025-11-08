package com.google.android.gms.internal.mlkit_vision_barcode;

final class zzwb extends zzwg {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    public final zzwg zza(boolean z2) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    public final zzwg zzb(int i3) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzwg zzc(String str) {
        this.zza = str;
        return this;
    }

    public final zzwh zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zzwd(str, this.zzb, this.zzc, (zzwc) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" libraryName");
        }
        if ((this.zzd & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
