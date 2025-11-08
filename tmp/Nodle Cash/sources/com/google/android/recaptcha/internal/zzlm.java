package com.google.android.recaptcha.internal;

import androidx.constraintlayout.core.state.b;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import java.io.IOException;
import java.util.Arrays;

public final class zzlm {
    private static final zzlm zza = new zzlm(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzlm(int i3, int[] iArr, Object[] objArr, boolean z2) {
        this.zze = -1;
        this.zzb = i3;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z2;
    }

    public static zzlm zzc() {
        return zza;
    }

    public static zzlm zze(zzlm zzlm, zzlm zzlm2) {
        int i3 = zzlm.zzb + zzlm2.zzb;
        int[] copyOf = Arrays.copyOf(zzlm.zzc, i3);
        System.arraycopy(zzlm2.zzc, 0, copyOf, zzlm.zzb, zzlm2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzlm.zzd, i3);
        System.arraycopy(zzlm2.zzd, 0, copyOf2, zzlm.zzb, zzlm2.zzb);
        return new zzlm(i3, copyOf, copyOf2, true);
    }

    public static zzlm zzf() {
        return new zzlm(0, new int[8], new Object[8], true);
    }

    private final void zzm(int i3) {
        int[] iArr = this.zzc;
        if (i3 > iArr.length) {
            int i4 = this.zzb;
            int i5 = (i4 / 2) + i4;
            if (i5 >= i3) {
                i3 = i5;
            }
            if (i3 < 8) {
                i3 = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i3);
            this.zzd = Arrays.copyOf(this.zzd, i3);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzlm)) {
            return false;
        }
        zzlm zzlm = (zzlm) obj;
        int i3 = this.zzb;
        if (i3 == zzlm.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzlm.zzc;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzlm.zzd;
                    int i5 = this.zzb;
                    int i6 = 0;
                    while (i6 < i5) {
                        if (objArr[i6].equals(objArr2[i6])) {
                            i6++;
                        }
                    }
                    return true;
                } else if (iArr[i4] != iArr2[i4]) {
                    break;
                } else {
                    i4++;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i3 = this.zzb;
        int i4 = i3 + 527;
        int[] iArr = this.zzc;
        int i5 = 17;
        int i6 = 17;
        for (int i7 = 0; i7 < i3; i7++) {
            i6 = (i6 * 31) + iArr[i7];
        }
        int A2 = b.A(i4, 31, i6, 31);
        Object[] objArr = this.zzd;
        int i8 = this.zzb;
        for (int i9 = 0; i9 < i8; i9++) {
            i5 = (i5 * 31) + objArr[i9].hashCode();
        }
        return A2 + i5;
    }

    public final int zza() {
        int zzy;
        int i3 = this.zze;
        if (i3 != -1) {
            return i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzb; i5++) {
            int i6 = this.zzc[i5];
            int i7 = i6 >>> 3;
            int i8 = i6 & 7;
            if (i8 != 0) {
                if (i8 == 1) {
                    ((Long) this.zzd[i5]).getClass();
                    zzy = zzhh.zzy(i7 << 3) + 8;
                } else if (i8 == 2) {
                    int i9 = i7 << 3;
                    int i10 = zzhh.zzb;
                    int zzd2 = ((zzgw) this.zzd[i5]).zzd();
                    i4 = a.l(i9, zzhh.zzy(zzd2) + zzd2, i4);
                } else if (i8 == 3) {
                    int i11 = i7 << 3;
                    int i12 = zzhh.zzb;
                    int zza2 = ((zzlm) this.zzd[i5]).zza();
                    int zzy2 = zzhh.zzy(i11);
                    zzy = zzy2 + zzy2 + zza2;
                } else if (i8 == 5) {
                    ((Integer) this.zzd[i5]).getClass();
                    zzy = zzhh.zzy(i7 << 3) + 4;
                } else {
                    throw new IllegalStateException(zzje.zza());
                }
                i4 = zzy + i4;
            } else {
                i4 = a.l(i7 << 3, zzhh.zzz(((Long) this.zzd[i5]).longValue()), i4);
            }
        }
        this.zze = i4;
        return i4;
    }

    public final int zzb() {
        int i3 = this.zze;
        if (i3 != -1) {
            return i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzb; i5++) {
            int i6 = zzhh.zzb;
            int zzd2 = ((zzgw) this.zzd[i5]).zzd();
            int zzy = zzhh.zzy(zzd2) + zzd2;
            int zzy2 = zzhh.zzy(16);
            int zzy3 = zzhh.zzy(this.zzc[i5] >>> 3);
            int zzy4 = zzhh.zzy(8);
            i4 += zzhh.zzy(24) + zzy + zzy2 + zzy3 + zzy4 + zzy4;
        }
        this.zze = i4;
        return i4;
    }

    public final zzlm zzd(zzlm zzlm) {
        if (zzlm.equals(zza)) {
            return this;
        }
        zzg();
        int i3 = this.zzb + zzlm.zzb;
        zzm(i3);
        System.arraycopy(zzlm.zzc, 0, this.zzc, this.zzb, zzlm.zzb);
        System.arraycopy(zzlm.zzd, 0, this.zzd, this.zzb, zzlm.zzb);
        this.zzb = i3;
        return this;
    }

    public final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    public final void zzi(StringBuilder sb, int i3) {
        for (int i4 = 0; i4 < this.zzb; i4++) {
            zzkg.zzb(sb, i3, String.valueOf(this.zzc[i4] >>> 3), this.zzd[i4]);
        }
    }

    public final void zzj(int i3, Object obj) {
        zzg();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i4 = this.zzb;
        iArr[i4] = i3;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }

    public final void zzk(zzmd zzmd) throws IOException {
        for (int i3 = 0; i3 < this.zzb; i3++) {
            zzmd.zzw(this.zzc[i3] >>> 3, this.zzd[i3]);
        }
    }

    public final void zzl(zzmd zzmd) throws IOException {
        if (this.zzb != 0) {
            for (int i3 = 0; i3 < this.zzb; i3++) {
                int i4 = this.zzc[i3];
                Object obj = this.zzd[i3];
                int i5 = i4 & 7;
                int i6 = i4 >>> 3;
                if (i5 == 0) {
                    zzmd.zzt(i6, ((Long) obj).longValue());
                } else if (i5 == 1) {
                    zzmd.zzm(i6, ((Long) obj).longValue());
                } else if (i5 == 2) {
                    zzmd.zzd(i6, (zzgw) obj);
                } else if (i5 == 3) {
                    zzmd.zzF(i6);
                    ((zzlm) obj).zzl(zzmd);
                    zzmd.zzh(i6);
                } else if (i5 == 5) {
                    zzmd.zzk(i6, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzje.zza());
                }
            }
        }
    }

    private zzlm() {
        this(0, new int[8], new Object[8], true);
    }
}
