package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

final class zzgd extends zzdf {
    static final int[] zza = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzc;
    /* access modifiers changed from: private */
    public final zzdf zzd;
    /* access modifiers changed from: private */
    public final zzdf zze;
    private final int zzf;
    private final int zzg;

    public static int zzc(int i3) {
        int[] iArr = zza;
        int length = iArr.length;
        if (i3 >= 47) {
            return Integer.MAX_VALUE;
        }
        return iArr[i3];
    }

    public static zzdf zzy(zzdf zzdf, zzdf zzdf2) {
        if (zzdf2.zzd() == 0) {
            return zzdf;
        }
        if (zzdf.zzd() == 0) {
            return zzdf2;
        }
        int zzd2 = zzdf2.zzd() + zzdf.zzd();
        if (zzd2 < 128) {
            return zzz(zzdf, zzdf2);
        }
        if (zzdf instanceof zzgd) {
            zzgd zzgd = (zzgd) zzdf;
            if (zzdf2.zzd() + zzgd.zze.zzd() < 128) {
                return new zzgd(zzgd.zzd, zzz(zzgd.zze, zzdf2));
            }
            if (zzgd.zzd.zzf() > zzgd.zze.zzf() && zzgd.zzg > zzdf2.zzf()) {
                return new zzgd(zzgd.zzd, new zzgd(zzgd.zze, zzdf2));
            }
        }
        return zzd2 >= zzc(Math.max(zzdf.zzf(), zzdf2.zzf()) + 1) ? new zzgd(zzdf, zzdf2) : zzfz.zza(new zzfz((zzfy) null), zzdf, zzdf2);
    }

    private static zzdf zzz(zzdf zzdf, zzdf zzdf2) {
        int zzd2 = zzdf.zzd();
        int zzd3 = zzdf2.zzd();
        byte[] bArr = new byte[(zzd2 + zzd3)];
        zzdf.zzv(bArr, 0, 0, zzd2);
        zzdf2.zzv(bArr, 0, zzd2, zzd3);
        return new zzde(bArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdf)) {
            return false;
        }
        zzdf zzdf = (zzdf) obj;
        if (this.zzc != zzdf.zzd()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int zzp = zzp();
        int zzp2 = zzdf.zzp();
        if (zzp != 0 && zzp2 != 0 && zzp != zzp2) {
            return false;
        }
        zzgb zzgb = new zzgb(this, (zzga) null);
        zzdd zza2 = zzgb.next();
        zzgb zzgb2 = new zzgb(zzdf, (zzga) null);
        zzdd zza3 = zzgb2.next();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int zzd2 = zza2.zzd() - i3;
            int zzd3 = zza3.zzd() - i4;
            int min = Math.min(zzd2, zzd3);
            if (!(i3 == 0 ? zza2.zzg(zza3, i4, min) : zza3.zzg(zza2, i3, min))) {
                return false;
            }
            i5 += min;
            int i6 = this.zzc;
            if (i5 < i6) {
                if (min == zzd2) {
                    zza2 = zzgb.next();
                    i3 = 0;
                } else {
                    i3 += min;
                }
                if (min == zzd3) {
                    zza3 = zzgb2.next();
                    i4 = 0;
                } else {
                    i4 += min;
                }
            } else if (i5 == i6) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzfx(this);
    }

    public final byte zza(int i3) {
        zzdf.zzu(i3, this.zzc);
        return zzb(i3);
    }

    public final byte zzb(int i3) {
        int i4 = this.zzf;
        return i3 < i4 ? this.zzd.zzb(i3) : this.zze.zzb(i3 - i4);
    }

    public final int zzd() {
        return this.zzc;
    }

    public final void zze(byte[] bArr, int i3, int i4, int i5) {
        int i6 = i3 + i5;
        int i7 = this.zzf;
        if (i6 <= i7) {
            this.zzd.zze(bArr, i3, i4, i5);
        } else if (i3 >= i7) {
            this.zze.zze(bArr, i3 - i7, i4, i5);
        } else {
            int i8 = i7 - i3;
            this.zzd.zze(bArr, i3, i4, i8);
            this.zze.zze(bArr, 0, i4 + i8, i5 - i8);
        }
    }

    public final int zzf() {
        return this.zzg;
    }

    public final boolean zzh() {
        return this.zzc >= zzc(this.zzg);
    }

    public final int zzi(int i3, int i4, int i5) {
        int i6 = i4 + i5;
        int i7 = this.zzf;
        if (i6 <= i7) {
            return this.zzd.zzi(i3, i4, i5);
        }
        if (i4 >= i7) {
            return this.zze.zzi(i3, i4 - i7, i5);
        }
        int i8 = i7 - i4;
        return this.zze.zzi(this.zzd.zzi(i3, i4, i8), 0, i5 - i8);
    }

    public final int zzj(int i3, int i4, int i5) {
        int i6 = i4 + i5;
        int i7 = this.zzf;
        if (i6 <= i7) {
            return this.zzd.zzj(i3, i4, i5);
        }
        if (i4 >= i7) {
            return this.zze.zzj(i3, i4 - i7, i5);
        }
        int i8 = i7 - i4;
        return this.zze.zzj(this.zzd.zzj(i3, i4, i8), 0, i5 - i8);
    }

    public final zzdf zzk(int i3, int i4) {
        int zzo = zzdf.zzo(i3, i4, this.zzc);
        if (zzo == 0) {
            return zzdf.zzb;
        }
        if (zzo == this.zzc) {
            return this;
        }
        int i5 = this.zzf;
        if (i4 <= i5) {
            return this.zzd.zzk(i3, i4);
        }
        if (i3 >= i5) {
            return this.zze.zzk(i3 - i5, i4 - i5);
        }
        zzdf zzdf = this.zzd;
        return new zzgd(zzdf.zzk(i3, zzdf.zzd()), this.zze.zzk(0, i4 - this.zzf));
    }

    public final String zzl(Charset charset) {
        return new String(zzw(), charset);
    }

    public final void zzm(zzcx zzcx) throws IOException {
        this.zzd.zzm(zzcx);
        this.zze.zzm(zzcx);
    }

    public final boolean zzn() {
        zzdf zzdf = this.zzd;
        zzdf zzdf2 = this.zze;
        return zzdf2.zzj(zzdf.zzj(0, 0, this.zzf), 0, zzdf2.zzd()) == 0;
    }

    public final zzdb zzq() {
        return new zzfx(this);
    }

    private zzgd(zzdf zzdf, zzdf zzdf2) {
        this.zzd = zzdf;
        this.zze = zzdf2;
        int zzd2 = zzdf.zzd();
        this.zzf = zzd2;
        this.zzc = zzdf2.zzd() + zzd2;
        this.zzg = Math.max(zzdf.zzf(), zzdf2.zzf()) + 1;
    }
}
