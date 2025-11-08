package com.google.android.gms.internal.measurement;

import androidx.compose.animation.core.a;
import java.io.IOException;

class zzkb extends zzjz {
    protected final byte[] zzb;

    public zzkb(byte[] bArr) {
        super();
        bArr.getClass();
        this.zzb = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjs) || zzb() != ((zzjs) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (!(obj instanceof zzkb)) {
            return obj.equals(this);
        }
        zzkb zzkb = (zzkb) obj;
        int zza = zza();
        int zza2 = zzkb.zza();
        if (zza == 0 || zza2 == 0 || zza == zza2) {
            return zza(zzkb, 0, zzb());
        }
        return false;
    }

    public byte zza(int i3) {
        return this.zzb[i3];
    }

    public byte zzb(int i3) {
        return this.zzb[i3];
    }

    public int zzc() {
        return 0;
    }

    public final zzjs zza(int i3, int i4) {
        int zza = zzjs.zza(0, i4, zzb());
        if (zza == 0) {
            return zzjs.zza;
        }
        return new zzjw(this.zzb, zzc(), zza);
    }

    public final int zzb(int i3, int i4, int i5) {
        return zzle.zza(i3, this.zzb, zzc(), i5);
    }

    public int zzb() {
        return this.zzb.length;
    }

    public final void zza(zzjp zzjp) throws IOException {
        zzjp.zza(this.zzb, zzc(), zzb());
    }

    public final boolean zza(zzjs zzjs, int i3, int i4) {
        if (i4 > zzjs.zzb()) {
            int zzb2 = zzb();
            throw new IllegalArgumentException("Length too large: " + i4 + zzb2);
        } else if (i4 > zzjs.zzb()) {
            throw new IllegalArgumentException(a.r("Ran off end of other: 0, ", i4, ", ", zzjs.zzb()));
        } else if (!(zzjs instanceof zzkb)) {
            return zzjs.zza(0, i4).equals(zza(0, i4));
        } else {
            zzkb zzkb = (zzkb) zzjs;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzkb.zzb;
            int zzc = zzc() + i4;
            int zzc2 = zzc();
            int zzc3 = zzkb.zzc();
            while (zzc2 < zzc) {
                if (bArr[zzc2] != bArr2[zzc3]) {
                    return false;
                }
                zzc2++;
                zzc3++;
            }
            return true;
        }
    }
}
