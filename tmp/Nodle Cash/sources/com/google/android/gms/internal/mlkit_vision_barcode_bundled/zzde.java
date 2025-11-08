package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.camera.camera2.internal.C0118y;
import java.io.IOException;
import java.nio.charset.Charset;

class zzde extends zzdd {
    protected final byte[] zza;

    public zzde(byte[] bArr) {
        super((zzdc) null);
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdf) || zzd() != ((zzdf) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzde)) {
            return obj.equals(this);
        }
        zzde zzde = (zzde) obj;
        int zzp = zzp();
        int zzp2 = zzde.zzp();
        if (zzp == 0 || zzp2 == 0 || zzp == zzp2) {
            return zzg(zzde, 0, zzd());
        }
        return false;
    }

    public byte zza(int i3) {
        return this.zza[i3];
    }

    public byte zzb(int i3) {
        return this.zza[i3];
    }

    public int zzc() {
        return 0;
    }

    public int zzd() {
        return this.zza.length;
    }

    public void zze(byte[] bArr, int i3, int i4, int i5) {
        System.arraycopy(this.zza, i3, bArr, i4, i5);
    }

    public final boolean zzg(zzdf zzdf, int i3, int i4) {
        if (i4 <= zzdf.zzd()) {
            int i5 = i3 + i4;
            if (i5 > zzdf.zzd()) {
                int zzd = zzdf.zzd();
                StringBuilder k2 = C0118y.k(i3, i4, "Ran off end of other: ", ", ", ", ");
                k2.append(zzd);
                throw new IllegalArgumentException(k2.toString());
            } else if (!(zzdf instanceof zzde)) {
                return zzdf.zzk(i3, i5).equals(zzk(0, i4));
            } else {
                zzde zzde = (zzde) zzdf;
                byte[] bArr = this.zza;
                byte[] bArr2 = zzde.zza;
                int zzc = zzc() + i4;
                int zzc2 = zzc();
                int zzc3 = zzde.zzc() + i3;
                while (zzc2 < zzc) {
                    if (bArr[zzc2] != bArr2[zzc3]) {
                        return false;
                    }
                    zzc2++;
                    zzc3++;
                }
                return true;
            }
        } else {
            int zzd2 = zzd();
            throw new IllegalArgumentException("Length too large: " + i4 + zzd2);
        }
    }

    public final int zzi(int i3, int i4, int i5) {
        return zzep.zzb(i3, this.zza, zzc() + i4, i5);
    }

    public final int zzj(int i3, int i4, int i5) {
        int zzc = zzc() + i4;
        return zzhe.zzf(i3, this.zza, zzc, i5 + zzc);
    }

    public final zzdf zzk(int i3, int i4) {
        int zzo = zzdf.zzo(i3, i4, zzd());
        return zzo == 0 ? zzdf.zzb : new zzda(this.zza, zzc() + i3, zzo);
    }

    public final String zzl(Charset charset) {
        return new String(this.zza, zzc(), zzd(), charset);
    }

    public final void zzm(zzcx zzcx) throws IOException {
        ((zzdk) zzcx).zzc(this.zza, zzc(), zzd());
    }

    public final boolean zzn() {
        int zzc = zzc();
        return zzhe.zzg(this.zza, zzc, zzd() + zzc);
    }
}
