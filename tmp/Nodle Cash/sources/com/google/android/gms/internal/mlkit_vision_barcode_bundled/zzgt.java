package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.support.v4.media.session.a;
import androidx.constraintlayout.core.state.b;
import java.io.IOException;
import java.util.Arrays;

public final class zzgt {
    private static final zzgt zza = new zzgt(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzgt(int i3, int[] iArr, Object[] objArr, boolean z2) {
        this.zze = -1;
        this.zzb = i3;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z2;
    }

    public static zzgt zzc() {
        return zza;
    }

    public static zzgt zze(zzgt zzgt, zzgt zzgt2) {
        int i3 = zzgt.zzb + zzgt2.zzb;
        int[] copyOf = Arrays.copyOf(zzgt.zzc, i3);
        System.arraycopy(zzgt2.zzc, 0, copyOf, zzgt.zzb, zzgt2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzgt.zzd, i3);
        System.arraycopy(zzgt2.zzd, 0, copyOf2, zzgt.zzb, zzgt2.zzb);
        return new zzgt(i3, copyOf, copyOf2, true);
    }

    public static zzgt zzf() {
        return new zzgt(0, new int[8], new Object[8], true);
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
        if (obj == null || !(obj instanceof zzgt)) {
            return false;
        }
        zzgt zzgt = (zzgt) obj;
        int i3 = this.zzb;
        if (i3 == zzgt.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzgt.zzc;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzgt.zzd;
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
        int zzA;
        int zzB;
        int zzA2;
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
                    zzA2 = zzdn.zzA(i7 << 3) + 8;
                } else if (i8 == 2) {
                    int zzA3 = zzdn.zzA(i7 << 3);
                    int zzd2 = ((zzdf) this.zzd[i5]).zzd();
                    i4 = zzdn.zzA(zzd2) + zzd2 + zzA3 + i4;
                } else if (i8 == 3) {
                    int zzA4 = zzdn.zzA(i7 << 3);
                    zzA = zzA4 + zzA4;
                    zzB = ((zzgt) this.zzd[i5]).zza();
                } else if (i8 == 5) {
                    ((Integer) this.zzd[i5]).getClass();
                    zzA2 = zzdn.zzA(i7 << 3) + 4;
                } else {
                    throw new IllegalStateException(new zzeq("Protocol message tag had invalid wire type."));
                }
                i4 = zzA2 + i4;
            } else {
                int i9 = i7 << 3;
                long longValue = ((Long) this.zzd[i5]).longValue();
                zzA = zzdn.zzA(i9);
                zzB = zzdn.zzB(longValue);
            }
            i4 = zzB + zzA + i4;
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
            int zzA = zzdn.zzA(8);
            int zzA2 = zzdn.zzA(this.zzc[i5] >>> 3) + zzdn.zzA(16);
            int zzA3 = zzdn.zzA(24);
            int zzd2 = ((zzdf) this.zzd[i5]).zzd();
            i4 = a.D(zzA + zzA, zzA2, com.appsamurai.storyly.exoplayer2.core.source.chunk.a.b(zzd2, zzd2, zzA3), i4);
        }
        this.zze = i4;
        return i4;
    }

    public final zzgt zzd(zzgt zzgt) {
        if (zzgt.equals(zza)) {
            return this;
        }
        zzg();
        int i3 = this.zzb + zzgt.zzb;
        zzm(i3);
        System.arraycopy(zzgt.zzc, 0, this.zzc, this.zzb, zzgt.zzb);
        System.arraycopy(zzgt.zzd, 0, this.zzd, this.zzb, zzgt.zzb);
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
            zzfo.zzb(sb, i3, String.valueOf(this.zzc[i4] >>> 3), this.zzd[i4]);
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

    public final void zzk(zzhh zzhh) throws IOException {
        for (int i3 = 0; i3 < this.zzb; i3++) {
            zzhh.zzw(this.zzc[i3] >>> 3, this.zzd[i3]);
        }
    }

    public final void zzl(zzhh zzhh) throws IOException {
        if (this.zzb != 0) {
            for (int i3 = 0; i3 < this.zzb; i3++) {
                int i4 = this.zzc[i3];
                Object obj = this.zzd[i3];
                int i5 = i4 & 7;
                int i6 = i4 >>> 3;
                if (i5 == 0) {
                    zzhh.zzt(i6, ((Long) obj).longValue());
                } else if (i5 == 1) {
                    zzhh.zzm(i6, ((Long) obj).longValue());
                } else if (i5 == 2) {
                    zzhh.zzd(i6, (zzdf) obj);
                } else if (i5 == 3) {
                    zzhh.zzF(i6);
                    ((zzgt) obj).zzl(zzhh);
                    zzhh.zzh(i6);
                } else if (i5 == 5) {
                    zzhh.zzk(i6, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(new zzeq("Protocol message tag had invalid wire type."));
                }
            }
        }
    }

    private zzgt() {
        this(0, new int[8], new Object[8], true);
    }
}
