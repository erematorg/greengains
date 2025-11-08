package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

final class zzda extends zzde {
    private final int zzc;
    private final int zzd;

    public zzda(byte[] bArr, int i3, int i4) {
        super(bArr);
        zzdf.zzo(i3, i3 + i4, bArr.length);
        this.zzc = i3;
        this.zzd = i4;
    }

    public final byte zza(int i3) {
        zzdf.zzu(i3, this.zzd);
        return this.zza[this.zzc + i3];
    }

    public final byte zzb(int i3) {
        return this.zza[this.zzc + i3];
    }

    public final int zzc() {
        return this.zzc;
    }

    public final int zzd() {
        return this.zzd;
    }

    public final void zze(byte[] bArr, int i3, int i4, int i5) {
        System.arraycopy(this.zza, this.zzc + i3, bArr, i4, i5);
    }
}
