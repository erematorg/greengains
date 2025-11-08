package com.google.android.gms.internal.fido;

import androidx.compose.animation.core.a;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

class zzcw extends zzcv {
    protected final byte[] zza;

    public zzcw(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcz) || zzd() != ((zzcz) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzcw)) {
            return obj.equals(this);
        }
        zzcw zzcw = (zzcw) obj;
        int zzk = zzk();
        int zzk2 = zzcw.zzk();
        if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzcw.zzd()) {
            int zzd2 = zzd();
            throw new IllegalArgumentException("Length too large: " + zzd + zzd2);
        } else if (zzd <= zzcw.zzd()) {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzcw.zza;
            int zzc = zzc() + zzd;
            int zzc2 = zzc();
            int zzc3 = zzcw.zzc();
            while (zzc2 < zzc) {
                if (bArr[zzc2] != bArr2[zzc3]) {
                    return false;
                }
                zzc2++;
                zzc3++;
            }
            return true;
        } else {
            throw new IllegalArgumentException(a.r("Ran off end of other: 0, ", zzd, ", ", zzcw.zzd()));
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
        byte[] bArr = this.zza;
        int zzc = zzc();
        byte[] bArr2 = zzde.zzd;
        for (int i6 = zzc; i6 < zzc + i5; i6++) {
            i3 = (i3 * 31) + bArr[i6];
        }
        return i3;
    }

    public final zzcz zzg(int i3, int i4) {
        int zzj = zzcz.zzj(i3, i4, zzd());
        return zzj == 0 ? zzcz.zzb : new zzct(this.zza, zzc() + i3, zzj);
    }

    public final InputStream zzh() {
        return new ByteArrayInputStream(this.zza, zzc(), zzd());
    }

    public final ByteBuffer zzi() {
        return ByteBuffer.wrap(this.zza, zzc(), zzd()).asReadOnlyBuffer();
    }
}
