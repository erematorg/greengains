package com.google.android.gms.internal.fido;

public final class zzdt {
    private final byte zza;
    private final byte zzb;

    public zzdt(int i3) {
        this.zza = (byte) (i3 & 224);
        this.zzb = (byte) (i3 & 31);
    }

    public final byte zza() {
        return this.zzb;
    }

    public final byte zzb() {
        return this.zza;
    }

    public final int zzc() {
        return (this.zza >> 5) & 7;
    }
}
