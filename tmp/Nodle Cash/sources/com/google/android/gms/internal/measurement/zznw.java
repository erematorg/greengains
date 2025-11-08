package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

public final class zznw {
    private static final zznw zza = new zznw(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zznw() {
        this(0, new int[8], new Object[8], true);
    }

    public static zznw zzc() {
        return zza;
    }

    public static zznw zzd() {
        return new zznw();
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zznw)) {
            return false;
        }
        zznw zznw = (zznw) obj;
        int i3 = this.zzb;
        if (i3 == zznw.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zznw.zzc;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zznw.zzd;
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
        int i4 = (i3 + 527) * 31;
        int[] iArr = this.zzc;
        int i5 = 17;
        int i6 = 17;
        for (int i7 = 0; i7 < i3; i7++) {
            i6 = (i6 * 31) + iArr[i7];
        }
        int i8 = (i4 + i6) * 31;
        Object[] objArr = this.zzd;
        int i9 = this.zzb;
        for (int i10 = 0; i10 < i9; i10++) {
            i5 = (i5 * 31) + objArr[i10].hashCode();
        }
        return i8 + i5;
    }

    public final int zza() {
        int zzg;
        int i3 = this.zze;
        if (i3 != -1) {
            return i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzb; i5++) {
            int i6 = this.zzc[i5];
            int i7 = i6 >>> 3;
            int i8 = i6 & 7;
            if (i8 == 0) {
                zzg = zzkl.zzg(i7, ((Long) this.zzd[i5]).longValue());
            } else if (i8 == 1) {
                zzg = zzkl.zzc(i7, ((Long) this.zzd[i5]).longValue());
            } else if (i8 == 2) {
                zzg = zzkl.zzc(i7, (zzjs) this.zzd[i5]);
            } else if (i8 == 3) {
                i4 = ((zznw) this.zzd[i5]).zza() + (zzkl.zzi(i7) << 1) + i4;
            } else if (i8 == 5) {
                zzg = zzkl.zzf(i7, ((Integer) this.zzd[i5]).intValue());
            } else {
                throw new IllegalStateException(zzlk.zza());
            }
            i4 = zzg + i4;
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
            i4 += zzkl.zzd(this.zzc[i5] >>> 3, (zzjs) this.zzd[i5]);
        }
        this.zze = i4;
        return i4;
    }

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    private zznw(int i3, int[] iArr, Object[] objArr, boolean z2) {
        this.zze = -1;
        this.zzb = i3;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z2;
    }

    public final void zzb(zzos zzos) throws IOException {
        if (this.zzb != 0) {
            if (zzos.zza() == 1) {
                for (int i3 = 0; i3 < this.zzb; i3++) {
                    zza(this.zzc[i3], this.zzd[i3], zzos);
                }
                return;
            }
            for (int i4 = this.zzb - 1; i4 >= 0; i4--) {
                zza(this.zzc[i4], this.zzd[i4], zzos);
            }
        }
    }

    public final zznw zza(zznw zznw) {
        if (zznw.equals(zza)) {
            return this;
        }
        zzf();
        int i3 = this.zzb + zznw.zzb;
        zza(i3);
        System.arraycopy(zznw.zzc, 0, this.zzc, this.zzb, zznw.zzb);
        System.arraycopy(zznw.zzd, 0, this.zzd, this.zzb, zznw.zzb);
        this.zzb = i3;
        return this;
    }

    public static zznw zza(zznw zznw, zznw zznw2) {
        int i3 = zznw.zzb + zznw2.zzb;
        int[] copyOf = Arrays.copyOf(zznw.zzc, i3);
        System.arraycopy(zznw2.zzc, 0, copyOf, zznw.zzb, zznw2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zznw.zzd, i3);
        System.arraycopy(zznw2.zzd, 0, copyOf2, zznw.zzb, zznw2.zzb);
        return new zznw(i3, copyOf, copyOf2, true);
    }

    private final void zza(int i3) {
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

    public final void zza(StringBuilder sb, int i3) {
        for (int i4 = 0; i4 < this.zzb; i4++) {
            zzmm.zza(sb, i3, String.valueOf(this.zzc[i4] >>> 3), this.zzd[i4]);
        }
    }

    public final void zza(int i3, Object obj) {
        zzf();
        zza(this.zzb + 1);
        int[] iArr = this.zzc;
        int i4 = this.zzb;
        iArr[i4] = i3;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }

    public final void zza(zzos zzos) throws IOException {
        if (zzos.zza() == 2) {
            for (int i3 = this.zzb - 1; i3 >= 0; i3--) {
                zzos.zza(this.zzc[i3] >>> 3, this.zzd[i3]);
            }
            return;
        }
        for (int i4 = 0; i4 < this.zzb; i4++) {
            zzos.zza(this.zzc[i4] >>> 3, this.zzd[i4]);
        }
    }

    private static void zza(int i3, Object obj, zzos zzos) throws IOException {
        int i4 = i3 >>> 3;
        int i5 = i3 & 7;
        if (i5 == 0) {
            zzos.zzb(i4, ((Long) obj).longValue());
        } else if (i5 == 1) {
            zzos.zza(i4, ((Long) obj).longValue());
        } else if (i5 == 2) {
            zzos.zza(i4, (zzjs) obj);
        } else if (i5 != 3) {
            if (i5 == 5) {
                zzos.zzb(i4, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzlk.zza());
        } else if (zzos.zza() == 1) {
            zzos.zzb(i4);
            ((zznw) obj).zzb(zzos);
            zzos.zza(i4);
        } else {
            zzos.zza(i4);
            ((zznw) obj).zzb(zzos);
            zzos.zzb(i4);
        }
    }
}
