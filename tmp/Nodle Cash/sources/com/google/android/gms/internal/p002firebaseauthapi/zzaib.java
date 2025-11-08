package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.compose.animation.core.a;
import java.io.IOException;
import java.nio.charset.Charset;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaib  reason: invalid package */
class zzaib extends zzahz {
    protected final byte[] zzb;

    public zzaib(byte[] bArr) {
        super();
        bArr.getClass();
        this.zzb = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaho) || zzb() != ((zzaho) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (!(obj instanceof zzaib)) {
            return obj.equals(this);
        }
        zzaib zzaib = (zzaib) obj;
        int zza = zza();
        int zza2 = zzaib.zza();
        if (zza == 0 || zza2 == 0 || zza == zza2) {
            return zza(zzaib, 0, zzb());
        }
        return false;
    }

    public byte zza(int i3) {
        return this.zzb[i3];
    }

    public byte zzb(int i3) {
        return this.zzb[i3];
    }

    public final zzaic zzc() {
        return zzaic.zza(this.zzb, zzg(), zzb(), true);
    }

    public final boolean zze() {
        int zzg = zzg();
        return zzamn.zzc(this.zzb, zzg, zzb() + zzg);
    }

    public int zzg() {
        return 0;
    }

    public final zzaho zza(int i3, int i4) {
        int zza = zzaho.zza(0, i4, zzb());
        if (zza == 0) {
            return zzaho.zza;
        }
        return new zzahs(this.zzb, zzg(), zza);
    }

    public final int zzb(int i3, int i4, int i5) {
        return zzajh.zza(i3, this.zzb, zzg(), i5);
    }

    public int zzb() {
        return this.zzb.length;
    }

    public final String zza(Charset charset) {
        return new String(this.zzb, zzg(), zzb(), charset);
    }

    public void zza(byte[] bArr, int i3, int i4, int i5) {
        System.arraycopy(this.zzb, 0, bArr, 0, i5);
    }

    public final void zza(zzahp zzahp) throws IOException {
        zzahp.zza(this.zzb, zzg(), zzb());
    }

    public final boolean zza(zzaho zzaho, int i3, int i4) {
        if (i4 > zzaho.zzb()) {
            int zzb2 = zzb();
            throw new IllegalArgumentException("Length too large: " + i4 + zzb2);
        } else if (i4 > zzaho.zzb()) {
            throw new IllegalArgumentException(a.r("Ran off end of other: 0, ", i4, ", ", zzaho.zzb()));
        } else if (!(zzaho instanceof zzaib)) {
            return zzaho.zza(0, i4).equals(zza(0, i4));
        } else {
            zzaib zzaib = (zzaib) zzaho;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzaib.zzb;
            int zzg = zzg() + i4;
            int zzg2 = zzg();
            int zzg3 = zzaib.zzg();
            while (zzg2 < zzg) {
                if (bArr[zzg2] != bArr2[zzg3]) {
                    return false;
                }
                zzg2++;
                zzg3++;
            }
            return true;
        }
    }
}
