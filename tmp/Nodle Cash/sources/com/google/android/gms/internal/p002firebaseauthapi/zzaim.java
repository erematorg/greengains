package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.browser.trusted.c;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaim  reason: invalid package */
public abstract class zzaim extends zzahp {
    private static final Logger zza = Logger.getLogger(zzaim.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzb = zzamm.zzc();
    zzaip zze;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaim$zza */
    public static abstract class zza extends zzaim {
        final byte[] zza;
        final int zzb;
        int zzc;
        int zzd;

        public zza(int i3) {
            super();
            if (i3 >= 0) {
                byte[] bArr = new byte[Math.max(i3, 20)];
                this.zza = bArr;
                this.zzb = bArr.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        public final int zza() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        public final void zzb(int i3) {
            if (zzaim.zzb) {
                long j2 = (long) this.zzc;
                while ((i3 & -128) != 0) {
                    byte[] bArr = this.zza;
                    int i4 = this.zzc;
                    this.zzc = i4 + 1;
                    zzamm.zza(bArr, (long) i4, (byte) (i3 | 128));
                    i3 >>>= 7;
                }
                byte[] bArr2 = this.zza;
                int i5 = this.zzc;
                this.zzc = i5 + 1;
                zzamm.zza(bArr2, (long) i5, (byte) i3);
                this.zzd += (int) (((long) this.zzc) - j2);
                return;
            }
            while ((i3 & -128) != 0) {
                byte[] bArr3 = this.zza;
                int i6 = this.zzc;
                this.zzc = i6 + 1;
                bArr3[i6] = (byte) (i3 | 128);
                this.zzd++;
                i3 >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i7 = this.zzc;
            this.zzc = i7 + 1;
            bArr4[i7] = (byte) i3;
            this.zzd++;
        }

        public final void zza(byte b3) {
            byte[] bArr = this.zza;
            int i3 = this.zzc;
            this.zzc = i3 + 1;
            bArr[i3] = b3;
            this.zzd++;
        }

        public final void zza(int i3) {
            byte[] bArr = this.zza;
            int i4 = this.zzc;
            int i5 = i4 + 1;
            this.zzc = i5;
            bArr[i4] = (byte) i3;
            int i6 = i4 + 2;
            this.zzc = i6;
            bArr[i5] = (byte) (i3 >> 8);
            int i7 = i4 + 3;
            this.zzc = i7;
            bArr[i6] = (byte) (i3 >> 16);
            this.zzc = i4 + 4;
            bArr[i7] = (byte) (i3 >>> 24);
            this.zzd += 4;
        }

        public final void zza(long j2) {
            byte[] bArr = this.zza;
            int i3 = this.zzc;
            int i4 = i3 + 1;
            this.zzc = i4;
            bArr[i3] = (byte) ((int) (j2 & 255));
            int i5 = i3 + 2;
            this.zzc = i5;
            bArr[i4] = (byte) ((int) ((j2 >> 8) & 255));
            int i6 = i3 + 3;
            this.zzc = i6;
            bArr[i5] = (byte) ((int) ((j2 >> 16) & 255));
            int i7 = i3 + 4;
            this.zzc = i7;
            bArr[i6] = (byte) ((int) (255 & (j2 >> 24)));
            int i8 = i3 + 5;
            this.zzc = i8;
            bArr[i7] = (byte) ((int) (j2 >> 32));
            int i9 = i3 + 6;
            this.zzc = i9;
            bArr[i8] = (byte) ((int) (j2 >> 40));
            int i10 = i3 + 7;
            this.zzc = i10;
            bArr[i9] = (byte) ((int) (j2 >> 48));
            this.zzc = i3 + 8;
            bArr[i10] = (byte) ((int) (j2 >> 56));
            this.zzd += 8;
        }

        public final void zzb(long j2) {
            if (zzaim.zzb) {
                long j3 = (long) this.zzc;
                while ((j2 & -128) != 0) {
                    byte[] bArr = this.zza;
                    int i3 = this.zzc;
                    this.zzc = i3 + 1;
                    zzamm.zza(bArr, (long) i3, (byte) (((int) j2) | 128));
                    j2 >>>= 7;
                }
                byte[] bArr2 = this.zza;
                int i4 = this.zzc;
                this.zzc = i4 + 1;
                zzamm.zza(bArr2, (long) i4, (byte) ((int) j2));
                this.zzd += (int) (((long) this.zzc) - j3);
                return;
            }
            while ((j2 & -128) != 0) {
                byte[] bArr3 = this.zza;
                int i5 = this.zzc;
                this.zzc = i5 + 1;
                bArr3[i5] = (byte) (((int) j2) | 128);
                this.zzd++;
                j2 >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i6 = this.zzc;
            this.zzc = i6 + 1;
            bArr4[i6] = (byte) ((int) j2);
            this.zzd++;
        }

        public final void zza(int i3, int i4) {
            zzb((i3 << 3) | i4);
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaim$zzb */
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

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaim$zzc */
    public static class zzc extends zzaim {
        private final byte[] zza;
        private final int zzb;
        private final int zzc;
        private int zzd;

        public zzc(byte[] bArr, int i3, int i4) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if (((bArr.length - i4) | i4) >= 0) {
                this.zza = bArr;
                this.zzb = 0;
                this.zzd = 0;
                this.zzc = i4;
            } else {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i4)}));
            }
        }

        public final int zza() {
            return this.zzc - this.zzd;
        }

        public final void zzb(byte b3) throws IOException {
            try {
                byte[] bArr = this.zza;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr[i3] = b3;
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
            }
        }

        public final void zzc() {
        }

        public final void zzd(int i3, zzaho zzaho) throws IOException {
            zzk(1, 3);
            zzl(2, i3);
            zzc(3, zzaho);
            zzk(1, 4);
        }

        public final void zzf(int i3, long j2) throws IOException {
            zzk(i3, 1);
            zzh(j2);
        }

        public final void zzh(int i3, int i4) throws IOException {
            zzk(i3, 5);
            zzk(i4);
        }

        public final void zzi(int i3, int i4) throws IOException {
            zzk(i3, 0);
            zzl(i4);
        }

        public final void zzj(long j2) throws IOException {
            if (!zzaim.zzb || zza() < 10) {
                while ((j2 & -128) != 0) {
                    byte[] bArr = this.zza;
                    int i3 = this.zzd;
                    this.zzd = i3 + 1;
                    bArr[i3] = (byte) (((int) j2) | 128);
                    j2 >>>= 7;
                }
                try {
                    byte[] bArr2 = this.zza;
                    int i4 = this.zzd;
                    this.zzd = i4 + 1;
                    bArr2[i4] = (byte) ((int) j2);
                } catch (IndexOutOfBoundsException e3) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
                }
            } else {
                while ((j2 & -128) != 0) {
                    byte[] bArr3 = this.zza;
                    int i5 = this.zzd;
                    this.zzd = i5 + 1;
                    zzamm.zza(bArr3, (long) i5, (byte) (((int) j2) | 128));
                    j2 >>>= 7;
                }
                byte[] bArr4 = this.zza;
                int i6 = this.zzd;
                this.zzd = i6 + 1;
                zzamm.zza(bArr4, (long) i6, (byte) ((int) j2));
            }
        }

        public final void zzk(int i3) throws IOException {
            try {
                byte[] bArr = this.zza;
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

        public final void zzl(int i3) throws IOException {
            if (i3 >= 0) {
                zzn(i3);
            } else {
                zzj((long) i3);
            }
        }

        public final void zzn(int i3) throws IOException {
            while ((i3 & -128) != 0) {
                byte[] bArr = this.zza;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr[i4] = (byte) (i3 | 128);
                i3 >>>= 7;
            }
            try {
                byte[] bArr2 = this.zza;
                int i5 = this.zzd;
                this.zzd = i5 + 1;
                bArr2[i5] = (byte) i3;
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
            }
        }

        private final void zzc(byte[] bArr, int i3, int i4) throws IOException {
            try {
                System.arraycopy(bArr, i3, this.zza, this.zzd, i4);
                this.zzd += i4;
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i4)}), e3);
            }
        }

        public final void zza(byte[] bArr, int i3, int i4) throws IOException {
            zzc(bArr, i3, i4);
        }

        public final void zzh(long j2) throws IOException {
            try {
                byte[] bArr = this.zza;
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

        public final void zzl(int i3, int i4) throws IOException {
            zzk(i3, 0);
            zzn(i4);
        }

        public final void zzb(int i3, boolean z2) throws IOException {
            zzk(i3, 0);
            zzb(z2 ? (byte) 1 : 0);
        }

        public final void zzb(byte[] bArr, int i3, int i4) throws IOException {
            zzn(i4);
            zzc(bArr, 0, i4);
        }

        public final void zzc(int i3, zzaho zzaho) throws IOException {
            zzk(i3, 2);
            zzb(zzaho);
        }

        public final void zzk(int i3, int i4) throws IOException {
            zzn((i3 << 3) | i4);
        }

        public final void zzb(zzaho zzaho) throws IOException {
            zzn(zzaho.zzb());
            zzaho.zza((zzahp) this);
        }

        public final void zzc(int i3, zzakp zzakp, zzalh zzalh) throws IOException {
            zzk(i3, 2);
            zzn(((zzahf) zzakp).zza(zzalh));
            zzalh.zza(zzakp, (zzanc) this.zze);
        }

        public final void zzb(zzakp zzakp, zzalh zzalh) throws IOException {
            zzn(((zzahf) zzakp).zza(zzalh));
            zzalh.zza(zzakp, (zzanc) this.zze);
        }

        public final void zzc(zzakp zzakp) throws IOException {
            zzn(zzakp.zzl());
            zzakp.zza(this);
        }

        public final void zzb(int i3, zzakp zzakp) throws IOException {
            zzk(1, 3);
            zzl(2, i3);
            zzk(3, 2);
            zzc(zzakp);
            zzk(1, 4);
        }

        public final void zzh(int i3, long j2) throws IOException {
            zzk(i3, 0);
            zzj(j2);
        }

        public final void zzb(int i3, String str) throws IOException {
            zzk(i3, 2);
            zzb(str);
        }

        public final void zzb(String str) throws IOException {
            int i3 = this.zzd;
            try {
                int zzj = zzaim.zzj(str.length() * 3);
                int zzj2 = zzaim.zzj(str.length());
                if (zzj2 == zzj) {
                    int i4 = i3 + zzj2;
                    this.zzd = i4;
                    int zza2 = zzamn.zza(str, this.zza, i4, zza());
                    this.zzd = i3;
                    zzn((zza2 - i3) - zzj2);
                    this.zzd = zza2;
                    return;
                }
                zzn(zzamn.zza(str));
                this.zzd = zzamn.zza(str, this.zza, this.zzd, zza());
            } catch (zzamq e3) {
                this.zzd = i3;
                zza(str, e3);
            } catch (IndexOutOfBoundsException e4) {
                throw new zzb(e4);
            }
        }
    }

    public static int zza(double d2) {
        return 8;
    }

    public static int zzb(int i3, int i4) {
        return zzg((long) i4) + zzj(i3 << 3);
    }

    public static int zzc(long j2) {
        return 8;
    }

    public static int zzd(int i3) {
        return 4;
    }

    public static int zze(long j2) {
        return 8;
    }

    public static int zzf(int i3) {
        if (i3 > 4096) {
            return 4096;
        }
        return i3;
    }

    public static int zzg(int i3) {
        return 4;
    }

    public static int zzh(int i3) {
        return zzj(zza(i3));
    }

    public static int zzi(int i3) {
        return zzj(i3 << 3);
    }

    public static int zzj(int i3) {
        return (352 - (Integer.numberOfLeadingZeros(i3) * 9)) >>> 6;
    }

    public abstract int zza();

    public abstract void zzb(byte b3) throws IOException;

    public abstract void zzb(int i3, zzakp zzakp) throws IOException;

    public abstract void zzb(int i3, String str) throws IOException;

    public abstract void zzb(int i3, boolean z2) throws IOException;

    public abstract void zzb(zzaho zzaho) throws IOException;

    public abstract void zzb(zzakp zzakp, zzalh zzalh) throws IOException;

    public abstract void zzb(String str) throws IOException;

    public abstract void zzb(byte[] bArr, int i3, int i4) throws IOException;

    public abstract void zzc() throws IOException;

    public abstract void zzc(int i3, zzaho zzaho) throws IOException;

    public abstract void zzc(int i3, zzakp zzakp, zzalh zzalh) throws IOException;

    public abstract void zzc(zzakp zzakp) throws IOException;

    public abstract void zzd(int i3, zzaho zzaho) throws IOException;

    public abstract void zzf(int i3, long j2) throws IOException;

    public abstract void zzh(int i3, int i4) throws IOException;

    public abstract void zzh(int i3, long j2) throws IOException;

    public abstract void zzh(long j2) throws IOException;

    public abstract void zzi(int i3, int i4) throws IOException;

    public abstract void zzj(long j2) throws IOException;

    public abstract void zzk(int i3) throws IOException;

    public abstract void zzk(int i3, int i4) throws IOException;

    public abstract void zzl(int i3) throws IOException;

    public abstract void zzl(int i3, int i4) throws IOException;

    public final void zzm(int i3) throws IOException {
        zzn(zza(i3));
    }

    public abstract void zzn(int i3) throws IOException;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaim$zzd */
    public static final class zzd extends zza {
        private final OutputStream zzf;

        public zzd(OutputStream outputStream, int i3) {
            super(i3);
            if (outputStream != null) {
                this.zzf = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }

        private final void zze() throws IOException {
            this.zzf.write(this.zza, 0, this.zzc);
            this.zzc = 0;
        }

        private final void zzo(int i3) throws IOException {
            if (this.zzb - this.zzc < i3) {
                zze();
            }
        }

        public final void zza(byte[] bArr, int i3, int i4) throws IOException {
            zzc(bArr, i3, i4);
        }

        public final void zzb(byte b3) throws IOException {
            if (this.zzc == this.zzb) {
                zze();
            }
            zza(b3);
        }

        public final void zzc() throws IOException {
            if (this.zzc > 0) {
                zze();
            }
        }

        public final void zzd(int i3, zzaho zzaho) throws IOException {
            zzk(1, 3);
            zzl(2, i3);
            zzc(3, zzaho);
            zzk(1, 4);
        }

        public final void zzf(int i3, long j2) throws IOException {
            zzo(18);
            zza(i3, 1);
            zza(j2);
        }

        public final void zzh(int i3, int i4) throws IOException {
            zzo(14);
            zza(i3, 5);
            zza(i4);
        }

        public final void zzi(int i3, int i4) throws IOException {
            zzo(20);
            zza(i3, 0);
            if (i4 >= 0) {
                zzb(i4);
            } else {
                zzb((long) i4);
            }
        }

        public final void zzj(long j2) throws IOException {
            zzo(10);
            zzb(j2);
        }

        public final void zzk(int i3) throws IOException {
            zzo(4);
            zza(i3);
        }

        public final void zzl(int i3) throws IOException {
            if (i3 >= 0) {
                zzn(i3);
            } else {
                zzj((long) i3);
            }
        }

        public final void zzn(int i3) throws IOException {
            zzo(5);
            zzb(i3);
        }

        private final void zzc(byte[] bArr, int i3, int i4) throws IOException {
            int i5 = this.zzb;
            int i6 = this.zzc;
            if (i5 - i6 >= i4) {
                System.arraycopy(bArr, i3, this.zza, i6, i4);
                this.zzc += i4;
            } else {
                int i7 = i5 - i6;
                System.arraycopy(bArr, i3, this.zza, i6, i7);
                int i8 = i3 + i7;
                i4 -= i7;
                this.zzc = this.zzb;
                this.zzd += i7;
                zze();
                if (i4 <= this.zzb) {
                    System.arraycopy(bArr, i8, this.zza, 0, i4);
                    this.zzc = i4;
                } else {
                    this.zzf.write(bArr, i8, i4);
                }
            }
            this.zzd += i4;
        }

        public final void zzk(int i3, int i4) throws IOException {
            zzn((i3 << 3) | i4);
        }

        public final void zzl(int i3, int i4) throws IOException {
            zzo(20);
            zza(i3, 0);
            zzb(i4);
        }

        public final void zzb(int i3, boolean z2) throws IOException {
            zzo(11);
            zza(i3, 0);
            zza(z2 ? (byte) 1 : 0);
        }

        public final void zzh(long j2) throws IOException {
            zzo(8);
            zza(j2);
        }

        public final void zzh(int i3, long j2) throws IOException {
            zzo(20);
            zza(i3, 0);
            zzb(j2);
        }

        public final void zzb(byte[] bArr, int i3, int i4) throws IOException {
            zzn(i4);
            zzc(bArr, 0, i4);
        }

        public final void zzb(zzaho zzaho) throws IOException {
            zzn(zzaho.zzb());
            zzaho.zza((zzahp) this);
        }

        public final void zzb(zzakp zzakp, zzalh zzalh) throws IOException {
            zzn(((zzahf) zzakp).zza(zzalh));
            zzalh.zza(zzakp, (zzanc) this.zze);
        }

        public final void zzb(int i3, zzakp zzakp) throws IOException {
            zzk(1, 3);
            zzl(2, i3);
            zzk(3, 2);
            zzc(zzakp);
            zzk(1, 4);
        }

        public final void zzc(int i3, zzaho zzaho) throws IOException {
            zzk(i3, 2);
            zzb(zzaho);
        }

        public final void zzc(int i3, zzakp zzakp, zzalh zzalh) throws IOException {
            zzk(i3, 2);
            zzb(zzakp, zzalh);
        }

        public final void zzb(int i3, String str) throws IOException {
            zzk(i3, 2);
            zzb(str);
        }

        public final void zzc(zzakp zzakp) throws IOException {
            zzn(zzakp.zzl());
            zzakp.zza(this);
        }

        public final void zzb(String str) throws IOException {
            int i3;
            int i4;
            try {
                int length = str.length() * 3;
                int zzj = zzaim.zzj(length);
                int i5 = zzj + length;
                int i6 = this.zzb;
                if (i5 > i6) {
                    byte[] bArr = new byte[length];
                    int zza = zzamn.zza(str, bArr, 0, length);
                    zzn(zza);
                    zza(bArr, 0, zza);
                    return;
                }
                if (i5 > i6 - this.zzc) {
                    zze();
                }
                int zzj2 = zzaim.zzj(str.length());
                i3 = this.zzc;
                if (zzj2 == zzj) {
                    int i7 = i3 + zzj2;
                    this.zzc = i7;
                    int zza2 = zzamn.zza(str, this.zza, i7, this.zzb - i7);
                    this.zzc = i3;
                    i4 = (zza2 - i3) - zzj2;
                    zzb(i4);
                    this.zzc = zza2;
                } else {
                    i4 = zzamn.zza(str);
                    zzb(i4);
                    this.zzc = zzamn.zza(str, this.zza, this.zzc, i4);
                }
                this.zzd += i4;
            } catch (zzamq e3) {
                this.zzd -= this.zzc - i3;
                this.zzc = i3;
                throw e3;
            } catch (ArrayIndexOutOfBoundsException e4) {
                throw new zzb(e4);
            } catch (zzamq e5) {
                zza(str, e5);
            }
        }
    }

    private zzaim() {
    }

    public static int zza(float f2) {
        return 4;
    }

    public static int zzc(int i3) {
        return zzg((long) i3);
    }

    public static int zze(int i3) {
        return zzg((long) i3);
    }

    public static int zzf(int i3, int i4) {
        return zzj(zza(i4)) + zzj(i3 << 3);
    }

    public static int zzg(int i3, int i4) {
        return zzj(i4) + zzj(i3 << 3);
    }

    public final void zzi(long j2) throws IOException {
        zzj(zza(j2));
    }

    public final void zzj(int i3, int i4) throws IOException {
        zzl(i3, zza(i4));
    }

    private static int zza(int i3) {
        return (i3 >> 31) ^ (i3 << 1);
    }

    public static int zzb(int i3, long j2) {
        return zzg(j2) + zzj(i3 << 3);
    }

    public static int zzc(int i3, int i4) {
        return zzj(i3 << 3) + 4;
    }

    public static int zzd(int i3, int i4) {
        return zzg((long) i4) + zzj(i3 << 3);
    }

    public static int zze(int i3, int i4) {
        return zzj(i3 << 3) + 4;
    }

    public static int zza(boolean z2) {
        return 1;
    }

    public static int zzc(int i3, long j2) {
        return zzj(i3 << 3) + 8;
    }

    public static int zze(int i3, long j2) {
        return zzg(j2) + zzj(i3 << 3);
    }

    public static int zzf(long j2) {
        return zzg(zza(j2));
    }

    public static int zzg(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    private static long zza(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    public static int zzb(int i3, zzajt zzajt) {
        int zzj = zzj(i3 << 3);
        int zzb2 = zzajt.zzb();
        return zzj(zzb2) + zzb2 + zzj;
    }

    public static int zzd(long j2) {
        return zzg(j2);
    }

    public final void zzg(int i3, long j2) throws IOException {
        zzh(i3, zza(j2));
    }

    public static int zza(int i3, boolean z2) {
        return zzj(i3 << 3) + 1;
    }

    public static int zzd(int i3, long j2) {
        return zzg(zza(j2)) + zzj(i3 << 3);
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        return zzj(length) + length;
    }

    public static int zzb(int i3, zzakp zzakp, zzalh zzalh) {
        return zza(zzakp, zzalh) + zzj(i3 << 3);
    }

    public static int zza(int i3, zzaho zzaho) {
        int zzj = zzj(i3 << 3);
        int zzb2 = zzaho.zzb();
        return zzj(zzb2) + zzb2 + zzj;
    }

    public static int zzb(zzakp zzakp) {
        int zzl = zzakp.zzl();
        return zzj(zzl) + zzl;
    }

    public static int zza(zzaho zzaho) {
        int zzb2 = zzaho.zzb();
        return zzj(zzb2) + zzb2;
    }

    public static int zzb(int i3, zzaho zzaho) {
        int zzg = zzg(2, i3);
        return zza(3, zzaho) + zzg + (zzj(8) << 1);
    }

    public static int zza(int i3, double d2) {
        return zzj(i3 << 3) + 8;
    }

    public static int zza(int i3, long j2) {
        return zzj(i3 << 3) + 8;
    }

    public static zzaim zzb(byte[] bArr) {
        return new zzc(bArr, 0, bArr.length);
    }

    public static int zza(int i3, float f2) {
        return zzj(i3 << 3) + 4;
    }

    @Deprecated
    public static int zza(int i3, zzakp zzakp, zzalh zzalh) {
        return ((zzahf) zzakp).zza(zzalh) + (zzj(i3 << 3) << 1);
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    @Deprecated
    public static int zza(zzakp zzakp) {
        return zzakp.zzl();
    }

    public final void zzb(boolean z2) throws IOException {
        zzb(z2 ? (byte) 1 : 0);
    }

    public static int zza(int i3, zzajt zzajt) {
        int zzg = zzg(2, i3);
        return zzb(3, zzajt) + zzg + (zzj(8) << 1);
    }

    public final void zzb(int i3, double d2) throws IOException {
        zzf(i3, Double.doubleToRawLongBits(d2));
    }

    public final void zzb(double d2) throws IOException {
        zzh(Double.doubleToRawLongBits(d2));
    }

    public final void zzb(int i3, float f2) throws IOException {
        zzh(i3, Float.floatToRawIntBits(f2));
    }

    public static int zza(zzajt zzajt) {
        int zzb2 = zzajt.zzb();
        return zzj(zzb2) + zzb2;
    }

    public final void zzb(float f2) throws IOException {
        zzk(Float.floatToRawIntBits(f2));
    }

    public static int zza(int i3, zzakp zzakp) {
        return zzb(zzakp) + zzj(24) + zzg(2, i3) + (zzj(8) << 1);
    }

    public static int zza(zzakp zzakp, zzalh zzalh) {
        int zza2 = ((zzahf) zzakp).zza(zzalh);
        return zzj(zza2) + zza2;
    }

    public static int zza(int i3, String str) {
        return zza(str) + zzj(i3 << 3);
    }

    public static int zza(String str) {
        int i3;
        try {
            i3 = zzamn.zza(str);
        } catch (zzamq unused) {
            i3 = str.getBytes(zzajh.zza).length;
        }
        return zzj(i3) + i3;
    }

    public static zzaim zza(OutputStream outputStream, int i3) {
        return new zzd(outputStream, i3);
    }

    public final void zza(String str, zzamq zzamq) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzamq);
        byte[] bytes = str.getBytes(zzajh.zza);
        try {
            zzn(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzb(e3);
        }
    }
}
