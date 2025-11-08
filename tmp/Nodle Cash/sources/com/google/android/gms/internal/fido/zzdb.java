package com.google.android.gms.internal.fido;

final class zzdb extends zzdd {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze = Integer.MAX_VALUE;

    public /* synthetic */ zzdb(byte[] bArr, int i3, int i4, boolean z2, zzda zzda) {
        super((zzdc) null);
        this.zzb = bArr;
        this.zzc = 0;
    }

    public final int zza(int i3) throws zzdf {
        int i4 = this.zze;
        this.zze = 0;
        int i5 = this.zzc + this.zzd;
        this.zzc = i5;
        if (i5 > 0) {
            this.zzd = i5;
            this.zzc = 0;
        } else {
            this.zzd = 0;
        }
        return i4;
    }
}
