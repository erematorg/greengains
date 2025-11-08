package com.google.android.gms.internal.measurement;

import androidx.browser.trusted.c;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzkl extends zzjp {
    private static final Logger zzb = Logger.getLogger(zzkl.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzny.zzc();
    zzko zza;

    public static class zza extends zzkl {
        private final byte[] zzb;
        private final int zzc;
        private int zzd;

        public zza(byte[] bArr, int i3, int i4) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if (((bArr.length - i4) | i4) >= 0) {
                this.zzb = bArr;
                this.zzd = 0;
                this.zzc = i4;
            } else {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i4)}));
            }
        }

        private final void zzc(byte[] bArr, int i3, int i4) throws IOException {
            try {
                System.arraycopy(bArr, i3, this.zzb, this.zzd, i4);
                this.zzd += i4;
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i4)}), e3);
            }
        }

        public final int zza() {
            return this.zzc - this.zzd;
        }

        public final void zzb(byte[] bArr, int i3, int i4) throws IOException {
            zzc(i4);
            zzc(bArr, 0, i4);
        }

        public final void zzd(int i3, int i4) throws IOException {
            zzc(i3, 0);
            zzc(i4);
        }

        public final void zza(byte b3) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr[i3] = b3;
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
            }
        }

        public final void zzb(int i3, int i4) throws IOException {
            zzc(i3, 0);
            zzb(i4);
        }

        public final void zza(int i3, boolean z2) throws IOException {
            zzc(i3, 0);
            zza(z2 ? (byte) 1 : 0);
        }

        public final void zzb(int i3) throws IOException {
            if (i3 >= 0) {
                zzc(i3);
            } else {
                zzb((long) i3);
            }
        }

        public final void zzc(int i3, int i4) throws IOException {
            zzc((i3 << 3) | i4);
        }

        public final void zzc(int i3) throws IOException {
            while ((i3 & -128) != 0) {
                byte[] bArr = this.zzb;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr[i4] = (byte) (i3 | 128);
                i3 >>>= 7;
            }
            try {
                byte[] bArr2 = this.zzb;
                int i5 = this.zzd;
                this.zzd = i5 + 1;
                bArr2[i5] = (byte) i3;
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
            }
        }

        public final void zza(int i3, zzjs zzjs) throws IOException {
            zzc(i3, 2);
            zza(zzjs);
        }

        public final void zzb(int i3, zzjs zzjs) throws IOException {
            zzc(1, 3);
            zzd(2, i3);
            zza(3, zzjs);
            zzc(1, 4);
        }

        public final void zza(zzjs zzjs) throws IOException {
            zzc(zzjs.zzb());
            zzjs.zza((zzjp) this);
        }

        public final void zza(int i3, int i4) throws IOException {
            zzc(i3, 5);
            zza(i4);
        }

        public final void zzb(int i3, long j2) throws IOException {
            zzc(i3, 0);
            zzb(j2);
        }

        public final void zza(int i3) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i4 = this.zzd;
                int i5 = i4 + 1;
                this.zzd = i5;
                bArr[i4] = (byte) i3;
                int i6 = i4 + 2;
                this.zzd = i6;
                bArr[i5] = (byte) (i3 >> 8);
                int i7 = i4 + 3;
                this.zzd = i7;
                bArr[i6] = (byte) (i3 >> 16);
                this.zzd = i4 + 4;
                bArr[i7] = (byte) (i3 >>> 24);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
            }
        }

        public final void zzb(long j2) throws IOException {
            if (!zzkl.zzc || zza() < 10) {
                while ((j2 & -128) != 0) {
                    byte[] bArr = this.zzb;
                    int i3 = this.zzd;
                    this.zzd = i3 + 1;
                    bArr[i3] = (byte) (((int) j2) | 128);
                    j2 >>>= 7;
                }
                try {
                    byte[] bArr2 = this.zzb;
                    int i4 = this.zzd;
                    this.zzd = i4 + 1;
                    bArr2[i4] = (byte) ((int) j2);
                } catch (IndexOutOfBoundsException e3) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
                }
            } else {
                while ((j2 & -128) != 0) {
                    byte[] bArr3 = this.zzb;
                    int i5 = this.zzd;
                    this.zzd = i5 + 1;
                    zzny.zza(bArr3, (long) i5, (byte) (((int) j2) | 128));
                    j2 >>>= 7;
                }
                byte[] bArr4 = this.zzb;
                int i6 = this.zzd;
                this.zzd = i6 + 1;
                zzny.zza(bArr4, (long) i6, (byte) ((int) j2));
            }
        }

        public final void zza(int i3, long j2) throws IOException {
            zzc(i3, 1);
            zza(j2);
        }

        public final void zza(long j2) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i3 = this.zzd;
                int i4 = i3 + 1;
                this.zzd = i4;
                bArr[i3] = (byte) ((int) j2);
                int i5 = i3 + 2;
                this.zzd = i5;
                bArr[i4] = (byte) ((int) (j2 >> 8));
                int i6 = i3 + 3;
                this.zzd = i6;
                bArr[i5] = (byte) ((int) (j2 >> 16));
                int i7 = i3 + 4;
                this.zzd = i7;
                bArr[i6] = (byte) ((int) (j2 >> 24));
                int i8 = i3 + 5;
                this.zzd = i8;
                bArr[i7] = (byte) ((int) (j2 >> 32));
                int i9 = i3 + 6;
                this.zzd = i9;
                bArr[i8] = (byte) ((int) (j2 >> 40));
                int i10 = i3 + 7;
                this.zzd = i10;
                bArr[i9] = (byte) ((int) (j2 >> 48));
                this.zzd = i3 + 8;
                bArr[i10] = (byte) ((int) (j2 >> 56));
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
            }
        }

        public final void zza(byte[] bArr, int i3, int i4) throws IOException {
            zzc(bArr, i3, i4);
        }

        public final void zza(int i3, zzml zzml, zznd zznd) throws IOException {
            zzc(i3, 2);
            zzc(((zzji) zzml).zza(zznd));
            zznd.zza(zzml, (zzos) this.zza);
        }

        public final void zza(zzml zzml) throws IOException {
            zzc(zzml.zzcb());
            zzml.zza(this);
        }

        public final void zza(int i3, zzml zzml) throws IOException {
            zzc(1, 3);
            zzd(2, i3);
            zzc(3, 2);
            zza(zzml);
            zzc(1, 4);
        }

        public final void zza(int i3, String str) throws IOException {
            zzc(i3, 2);
            zza(str);
        }

        public final void zza(String str) throws IOException {
            int i3 = this.zzd;
            try {
                int zzj = zzkl.zzj(str.length() * 3);
                int zzj2 = zzkl.zzj(str.length());
                if (zzj2 == zzj) {
                    int i4 = i3 + zzj2;
                    this.zzd = i4;
                    int zza = zzoc.zza(str, this.zzb, i4, zza());
                    this.zzd = i3;
                    zzc((zza - i3) - zzj2);
                    this.zzd = zza;
                    return;
                }
                zzc(zzoc.zza(str));
                this.zzd = zzoc.zza(str, this.zzb, this.zzd, zza());
            } catch (zzog e3) {
                this.zzd = i3;
                zza(str, e3);
            } catch (IndexOutOfBoundsException e4) {
                throw new zzb(e4);
            }
        }
    }

    public static class zzb extends IOException {
        public zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        public zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        public zzb(String str, Throwable th) {
            super(c.a("CodedOutputStream was writing to a flat byte array and ran out of space.: ", str), th);
        }
    }

    public static int zza(double d2) {
        return 8;
    }

    public static int zzb(int i3, boolean z2) {
        return zzj(i3 << 3) + 1;
    }

    public static int zzc(long j2) {
        return 8;
    }

    public static int zzd(int i3) {
        return zzg((long) i3);
    }

    public static int zze(int i3) {
        return 4;
    }

    public static int zzf(int i3, int i4) {
        return zzj(i3 << 3) + 4;
    }

    public static int zzg(int i3) {
        return 4;
    }

    public static int zzh(int i3, int i4) {
        return zzj(i3 << 3) + 4;
    }

    private static long zzi(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    public static int zzj(int i3, int i4) {
        return zzj(i4) + zzj(i3 << 3);
    }

    private static int zzl(int i3) {
        return (i3 >> 31) ^ (i3 << 1);
    }

    public abstract int zza();

    public abstract void zza(byte b3) throws IOException;

    public abstract void zza(int i3) throws IOException;

    public abstract void zza(int i3, int i4) throws IOException;

    public abstract void zza(int i3, long j2) throws IOException;

    public abstract void zza(int i3, zzjs zzjs) throws IOException;

    public abstract void zza(int i3, zzml zzml) throws IOException;

    public abstract void zza(int i3, zzml zzml, zznd zznd) throws IOException;

    public abstract void zza(int i3, String str) throws IOException;

    public abstract void zza(int i3, boolean z2) throws IOException;

    public abstract void zza(long j2) throws IOException;

    public abstract void zza(zzjs zzjs) throws IOException;

    public abstract void zza(zzml zzml) throws IOException;

    public abstract void zza(String str) throws IOException;

    public abstract void zzb(int i3) throws IOException;

    public abstract void zzb(int i3, int i4) throws IOException;

    public abstract void zzb(int i3, long j2) throws IOException;

    public abstract void zzb(int i3, zzjs zzjs) throws IOException;

    public abstract void zzb(long j2) throws IOException;

    public abstract void zzb(byte[] bArr, int i3, int i4) throws IOException;

    public abstract void zzc(int i3) throws IOException;

    public abstract void zzc(int i3, int i4) throws IOException;

    public abstract void zzd(int i3, int i4) throws IOException;

    public final void zzk(int i3, int i4) throws IOException {
        zzd(i3, zzl(i4));
    }

    private zzkl() {
    }

    public static int zza(float f2) {
        return 4;
    }

    public static int zzb(zzjs zzjs) {
        int zzb2 = zzjs.zzb();
        return zzj(zzb2) + zzb2;
    }

    public static int zzd(int i3, long j2) {
        return zzg(j2) + zzj(i3 << 3);
    }

    public static int zze(long j2) {
        return 8;
    }

    public static int zzf(int i3) {
        return zzg((long) i3);
    }

    public static int zzg(int i3, int i4) {
        return zzg((long) i4) + zzj(i3 << 3);
    }

    public static int zzh(int i3) {
        return zzj(zzl(i3));
    }

    public static int zzi(int i3, int i4) {
        return zzj(zzl(i4)) + zzj(i3 << 3);
    }

    public final void zzk(int i3) throws IOException {
        zzc(zzl(i3));
    }

    public static int zza(boolean z2) {
        return 1;
    }

    public static int zzc(int i3, zzjs zzjs) {
        int zzj = zzj(i3 << 3);
        int zzb2 = zzjs.zzb();
        return zzj(zzb2) + zzb2 + zzj;
    }

    public static int zze(int i3, int i4) {
        return zzg((long) i4) + zzj(i3 << 3);
    }

    public static int zzf(int i3, long j2) {
        return zzg(zzi(j2)) + zzj(i3 << 3);
    }

    public static int zzj(int i3) {
        return (352 - (Integer.numberOfLeadingZeros(i3) * 9)) >>> 6;
    }

    public final void zzh(int i3, long j2) throws IOException {
        zzb(i3, zzi(j2));
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        return zzj(length) + length;
    }

    @Deprecated
    public static int zzb(int i3, zzml zzml, zznd zznd) {
        return ((zzji) zzml).zza(zznd) + (zzj(i3 << 3) << 1);
    }

    public static int zzd(long j2) {
        return zzg(j2);
    }

    public static int zzg(int i3, long j2) {
        return zzg(j2) + zzj(i3 << 3);
    }

    public static int zzi(int i3) {
        return zzj(i3 << 3);
    }

    public final void zzh(long j2) throws IOException {
        zzb(zzi(j2));
    }

    public static int zzd(int i3, zzjs zzjs) {
        int zzj = zzj(2, i3);
        return zzc(3, zzjs) + zzj + (zzj(8) << 1);
    }

    public static int zze(int i3, long j2) {
        return zzj(i3 << 3) + 8;
    }

    public static int zzf(long j2) {
        return zzg(zzi(j2));
    }

    public static int zza(int i3, double d2) {
        return zzj(i3 << 3) + 8;
    }

    @Deprecated
    public static int zzb(zzml zzml) {
        return zzml.zzcb();
    }

    public static int zzc(int i3, long j2) {
        return zzj(i3 << 3) + 8;
    }

    public static int zzg(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    public static int zza(int i3, float f2) {
        return zzj(i3 << 3) + 4;
    }

    public static int zzb(int i3, zzlt zzlt) {
        int zzj = zzj(i3 << 3);
        int zza2 = zzlt.zza();
        return zzj(zza2) + zza2 + zzj;
    }

    public static int zzc(int i3, zzml zzml, zznd zznd) {
        return zza(zzml, zznd) + zzj(i3 << 3);
    }

    public static int zza(int i3, zzlt zzlt) {
        int zzj = zzj(2, i3);
        return zzb(3, zzlt) + zzj + (zzj(8) << 1);
    }

    public static int zzc(zzml zzml) {
        int zzcb = zzml.zzcb();
        return zzj(zzcb) + zzcb;
    }

    public static int zzb(int i3, zzml zzml) {
        return zzc(zzml) + zzj(24) + zzj(2, i3) + (zzj(8) << 1);
    }

    public static int zza(zzlt zzlt) {
        int zza2 = zzlt.zza();
        return zzj(zza2) + zza2;
    }

    public static int zza(zzml zzml, zznd zznd) {
        int zza2 = ((zzji) zzml).zza(zznd);
        return zzj(zza2) + zza2;
    }

    public static int zzb(int i3, String str) {
        return zzb(str) + zzj(i3 << 3);
    }

    public final void zza(String str, zzog zzog) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzog);
        byte[] bytes = str.getBytes(zzle.zza);
        try {
            zzc(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzb(e3);
        }
    }

    public static int zzb(String str) {
        int i3;
        try {
            i3 = zzoc.zza(str);
        } catch (zzog unused) {
            i3 = str.getBytes(zzle.zza).length;
        }
        return zzj(i3) + i3;
    }

    public static zzkl zzb(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzb(boolean z2) throws IOException {
        zza(z2 ? (byte) 1 : 0);
    }

    public final void zzb(int i3, double d2) throws IOException {
        zza(i3, Double.doubleToRawLongBits(d2));
    }

    public final void zzb(double d2) throws IOException {
        zza(Double.doubleToRawLongBits(d2));
    }

    public final void zzb(int i3, float f2) throws IOException {
        zza(i3, Float.floatToRawIntBits(f2));
    }

    public final void zzb(float f2) throws IOException {
        zza(Float.floatToRawIntBits(f2));
    }
}
