package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

final class zzdh extends zzdj {
    private int zzb = 0;
    private int zzc;
    private int zzd = Integer.MAX_VALUE;

    public /* synthetic */ zzdh(byte[] bArr, int i3, int i4, boolean z2, zzdg zzdg) {
        super((zzdi) null);
    }

    public final int zza(int i3) throws zzer {
        int i4 = this.zzd;
        this.zzd = 0;
        int i5 = this.zzb + this.zzc;
        this.zzb = i5;
        if (i5 > 0) {
            this.zzc = i5;
            this.zzb = 0;
        } else {
            this.zzc = 0;
        }
        return i4;
    }
}
