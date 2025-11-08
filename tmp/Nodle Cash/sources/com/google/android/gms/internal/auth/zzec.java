package com.google.android.gms.internal.auth;

import androidx.compose.animation.core.a;
import java.nio.charset.Charset;

class zzec extends zzeb {
    protected final byte[] zza;

    public zzec(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzef) || zzd() != ((zzef) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzec)) {
            return obj.equals(this);
        }
        zzec zzec = (zzec) obj;
        int zzj = zzj();
        int zzj2 = zzec.zzj();
        if (zzj != 0 && zzj2 != 0 && zzj != zzj2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzec.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd <= zzec.zzd()) {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzec.zza;
            zzec.zzc();
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
            throw new IllegalArgumentException(a.r("Ran off end of other: 0, ", zzd, ", ", zzec.zzd()));
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

    public final int zze(int i3, int i4, int i5) {
        return zzfa.zzb(i3, this.zza, 0, i5);
    }

    public final zzef zzf(int i3, int i4) {
        int zzi = zzef.zzi(0, i4, zzd());
        return zzi == 0 ? zzef.zzb : new zzdz(this.zza, 0, zzi);
    }

    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    public final boolean zzh() {
        return zzhn.zzc(this.zza, 0, zzd());
    }
}
