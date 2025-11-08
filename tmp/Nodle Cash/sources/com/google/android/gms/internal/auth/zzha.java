package com.google.android.gms.internal.auth;

import java.util.Arrays;

public final class zzha {
    private static final zzha zza = new zzha(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private boolean zze;

    private zzha(int i3, int[] iArr, Object[] objArr, boolean z2) {
        this.zzb = i3;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = z2;
    }

    public static zzha zza() {
        return zza;
    }

    public static zzha zzc(zzha zzha, zzha zzha2) {
        int i3 = zzha.zzb + zzha2.zzb;
        int[] copyOf = Arrays.copyOf(zzha.zzc, i3);
        System.arraycopy(zzha2.zzc, 0, copyOf, zzha.zzb, zzha2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzha.zzd, i3);
        System.arraycopy(zzha2.zzd, 0, copyOf2, zzha.zzb, zzha2.zzb);
        return new zzha(i3, copyOf, copyOf2, true);
    }

    public static zzha zzd() {
        return new zzha(0, new int[8], new Object[8], true);
    }

    private final void zzi(int i3) {
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
        if (obj == null || !(obj instanceof zzha)) {
            return false;
        }
        zzha zzha = (zzha) obj;
        int i3 = this.zzb;
        if (i3 == zzha.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzha.zzc;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzha.zzd;
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
        int i8 = (i4 * 31) + i6;
        Object[] objArr = this.zzd;
        int i9 = this.zzb;
        for (int i10 = 0; i10 < i9; i10++) {
            i5 = (i5 * 31) + objArr[i10].hashCode();
        }
        return (i8 * 31) + i5;
    }

    public final zzha zzb(zzha zzha) {
        if (zzha.equals(zza)) {
            return this;
        }
        zze();
        int i3 = this.zzb + zzha.zzb;
        zzi(i3);
        System.arraycopy(zzha.zzc, 0, this.zzc, this.zzb, zzha.zzb);
        System.arraycopy(zzha.zzd, 0, this.zzd, this.zzb, zzha.zzb);
        this.zzb = i3;
        return this;
    }

    public final void zze() {
        if (!this.zze) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzf() {
        if (this.zze) {
            this.zze = false;
        }
    }

    public final void zzg(StringBuilder sb, int i3) {
        for (int i4 = 0; i4 < this.zzb; i4++) {
            zzfz.zzb(sb, i3, String.valueOf(this.zzc[i4] >>> 3), this.zzd[i4]);
        }
    }

    public final void zzh(int i3, Object obj) {
        zze();
        zzi(this.zzb + 1);
        int[] iArr = this.zzc;
        int i4 = this.zzb;
        iArr[i4] = i3;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }

    private zzha() {
        this(0, new int[8], new Object[8], true);
    }
}
