package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamf  reason: invalid package */
public final class zzamf {
    private static final zzamf zza = new zzamf(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzamf() {
        this(0, new int[8], new Object[8], true);
    }

    public static zzamf zzc() {
        return zza;
    }

    public static zzamf zzd() {
        return new zzamf();
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
        if (obj == null || !(obj instanceof zzamf)) {
            return false;
        }
        zzamf zzamf = (zzamf) obj;
        int i3 = this.zzb;
        if (i3 == zzamf.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzamf.zzc;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzamf.zzd;
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
        int zze2;
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
                zze2 = zzaim.zze(i7, ((Long) this.zzd[i5]).longValue());
            } else if (i8 == 1) {
                zze2 = zzaim.zza(i7, ((Long) this.zzd[i5]).longValue());
            } else if (i8 == 2) {
                zze2 = zzaim.zza(i7, (zzaho) this.zzd[i5]);
            } else if (i8 == 3) {
                i4 = ((zzamf) this.zzd[i5]).zza() + (zzaim.zzi(i7) << 1) + i4;
            } else if (i8 == 5) {
                zze2 = zzaim.zzc(i7, ((Integer) this.zzd[i5]).intValue());
            } else {
                throw new IllegalStateException(zzajk.zza());
            }
            i4 = zze2 + i4;
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
            i4 += zzaim.zzb(this.zzc[i5] >>> 3, (zzaho) this.zzd[i5]);
        }
        this.zze = i4;
        return i4;
    }

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    private zzamf(int i3, int[] iArr, Object[] objArr, boolean z2) {
        this.zze = -1;
        this.zzb = i3;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z2;
    }

    public final void zzb(zzanc zzanc) throws IOException {
        if (this.zzb != 0) {
            if (zzanc.zza() == zzanf.zza) {
                for (int i3 = 0; i3 < this.zzb; i3++) {
                    zza(this.zzc[i3], this.zzd[i3], zzanc);
                }
                return;
            }
            for (int i4 = this.zzb - 1; i4 >= 0; i4--) {
                zza(this.zzc[i4], this.zzd[i4], zzanc);
            }
        }
    }

    public final zzamf zza(zzamf zzamf) {
        if (zzamf.equals(zza)) {
            return this;
        }
        zzf();
        int i3 = this.zzb + zzamf.zzb;
        zza(i3);
        System.arraycopy(zzamf.zzc, 0, this.zzc, this.zzb, zzamf.zzb);
        System.arraycopy(zzamf.zzd, 0, this.zzd, this.zzb, zzamf.zzb);
        this.zzb = i3;
        return this;
    }

    public static zzamf zza(zzamf zzamf, zzamf zzamf2) {
        int i3 = zzamf.zzb + zzamf2.zzb;
        int[] copyOf = Arrays.copyOf(zzamf.zzc, i3);
        System.arraycopy(zzamf2.zzc, 0, copyOf, zzamf.zzb, zzamf2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzamf.zzd, i3);
        System.arraycopy(zzamf2.zzd, 0, copyOf2, zzamf.zzb, zzamf2.zzb);
        return new zzamf(i3, copyOf, copyOf2, true);
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
            zzakq.zza(sb, i3, String.valueOf(this.zzc[i4] >>> 3), this.zzd[i4]);
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

    public final void zza(zzanc zzanc) throws IOException {
        if (zzanc.zza() == zzanf.zzb) {
            for (int i3 = this.zzb - 1; i3 >= 0; i3--) {
                zzanc.zza(this.zzc[i3] >>> 3, this.zzd[i3]);
            }
            return;
        }
        for (int i4 = 0; i4 < this.zzb; i4++) {
            zzanc.zza(this.zzc[i4] >>> 3, this.zzd[i4]);
        }
    }

    private static void zza(int i3, Object obj, zzanc zzanc) throws IOException {
        int i4 = i3 >>> 3;
        int i5 = i3 & 7;
        if (i5 == 0) {
            zzanc.zzb(i4, ((Long) obj).longValue());
        } else if (i5 == 1) {
            zzanc.zza(i4, ((Long) obj).longValue());
        } else if (i5 == 2) {
            zzanc.zza(i4, (zzaho) obj);
        } else if (i5 != 3) {
            if (i5 == 5) {
                zzanc.zzb(i4, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzajk.zza());
        } else if (zzanc.zza() == zzanf.zza) {
            zzanc.zzb(i4);
            ((zzamf) obj).zzb(zzanc);
            zzanc.zza(i4);
        } else {
            zzanc.zza(i4);
            ((zzamf) obj).zzb(zzanc);
            zzanc.zzb(i4);
        }
    }
}
