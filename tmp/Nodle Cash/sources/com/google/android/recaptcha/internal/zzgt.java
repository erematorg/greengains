package com.google.android.recaptcha.internal;

import androidx.compose.animation.core.a;
import java.io.IOException;
import java.nio.charset.Charset;

class zzgt extends zzgs {
    protected final byte[] zza;

    public zzgt(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgw) || zzd() != ((zzgw) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzgt)) {
            return obj.equals(this);
        }
        zzgt zzgt = (zzgt) obj;
        int zzl = zzl();
        int zzl2 = zzgt.zzl();
        if (zzl != 0 && zzl2 != 0 && zzl != zzl2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzgt.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd <= zzgt.zzd()) {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzgt.zza;
            zzgt.zzc();
            int i3 = 0;
            int i4 = 0;
            while (i3 < zzd) {
                if (bArr[i3] != bArr2[i4]) {
                    return false;
                }
                i3++;
                i4++;
            }
            return true;
        } else {
            throw new IllegalArgumentException(a.r("Ran off end of other: 0, ", zzd, ", ", zzgt.zzd()));
        }
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
        System.arraycopy(this.zza, 0, bArr, 0, i5);
    }

    public final int zzf(int i3, int i4, int i5) {
        return zzjc.zzb(i3, this.zza, 0, i5);
    }

    public final zzgw zzg(int i3, int i4) {
        int zzk = zzgw.zzk(0, i4, zzd());
        return zzk == 0 ? zzgw.zzb : new zzgq(this.zza, 0, zzk);
    }

    public final String zzh(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    public final void zzi(zzgm zzgm) throws IOException {
        ((zzhe) zzgm).zzc(this.zza, 0, zzd());
    }

    public final boolean zzj() {
        return zzma.zzf(this.zza, 0, zzd());
    }
}
