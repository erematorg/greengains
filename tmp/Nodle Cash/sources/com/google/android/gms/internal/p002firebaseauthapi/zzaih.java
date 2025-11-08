package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaih  reason: invalid package */
final class zzaih extends zzaic {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private zzaig zzm;

    private final void zzaa() {
        int i3 = this.zzg + this.zzh;
        this.zzg = i3;
        int i4 = this.zzk + i3;
        int i5 = this.zzl;
        if (i4 > i5) {
            int i6 = i4 - i5;
            this.zzh = i6;
            this.zzg = i3 - i6;
            return;
        }
        this.zzh = 0;
    }

    private final byte zzv() throws IOException {
        if (this.zzi == this.zzg) {
            zzg(1);
        }
        byte[] bArr = this.zzf;
        int i3 = this.zzi;
        this.zzi = i3 + 1;
        return bArr[i3];
    }

    private final int zzw() throws IOException {
        int i3 = this.zzi;
        if (this.zzg - i3 < 4) {
            zzg(4);
            i3 = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i3 + 4;
        return (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16) | ((bArr[i3 + 3] & 255) << Ascii.CAN);
    }

    private final int zzx() throws IOException {
        byte b3;
        byte b4;
        int i3 = this.zzi;
        int i4 = this.zzg;
        if (i4 != i3) {
            byte[] bArr = this.zzf;
            int i5 = i3 + 1;
            byte b5 = bArr[i3];
            if (b5 >= 0) {
                this.zzi = i5;
                return b5;
            } else if (i4 - i5 >= 9) {
                int i6 = i3 + 2;
                byte b6 = (bArr[i5] << 7) ^ b5;
                if (b6 < 0) {
                    b3 = b6 ^ Byte.MIN_VALUE;
                } else {
                    int i7 = i3 + 3;
                    byte b7 = (bArr[i6] << Ascii.SO) ^ b6;
                    if (b7 >= 0) {
                        b4 = b7 ^ 16256;
                    } else {
                        int i8 = i3 + 4;
                        byte b8 = b7 ^ (bArr[i7] << Ascii.NAK);
                        if (b8 < 0) {
                            b3 = -2080896 ^ b8;
                        } else {
                            i7 = i3 + 5;
                            byte b9 = bArr[i8];
                            byte b10 = (b8 ^ (b9 << Ascii.FS)) ^ 266354560;
                            if (b9 < 0) {
                                i8 = i3 + 6;
                                if (bArr[i7] < 0) {
                                    i7 = i3 + 7;
                                    if (bArr[i8] < 0) {
                                        i8 = i3 + 8;
                                        if (bArr[i7] < 0) {
                                            i7 = i3 + 9;
                                            if (bArr[i8] < 0) {
                                                int i9 = i3 + 10;
                                                if (bArr[i7] >= 0) {
                                                    byte b11 = b10;
                                                    i6 = i9;
                                                    b3 = b11;
                                                }
                                            }
                                        }
                                    }
                                }
                                b3 = b10;
                            }
                            b4 = b10;
                        }
                        i6 = i8;
                    }
                    i6 = i7;
                }
                this.zzi = i6;
                return b3;
            }
        }
        return (int) zzm();
    }

    private final long zzy() throws IOException {
        int i3 = this.zzi;
        if (this.zzg - i3 < 8) {
            zzg(8);
            i3 = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i3 + 8;
        return ((((long) bArr[i3 + 7]) & 255) << 56) | (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48);
    }

    private final long zzz() throws IOException {
        long j2;
        long j3;
        long j4;
        int i3 = this.zzi;
        int i4 = this.zzg;
        if (i4 != i3) {
            byte[] bArr = this.zzf;
            int i5 = i3 + 1;
            byte b3 = bArr[i3];
            if (b3 >= 0) {
                this.zzi = i5;
                return (long) b3;
            } else if (i4 - i5 >= 9) {
                int i6 = i3 + 2;
                byte b4 = (bArr[i5] << 7) ^ b3;
                if (b4 < 0) {
                    j2 = (long) (b4 ^ Byte.MIN_VALUE);
                } else {
                    int i7 = i3 + 3;
                    byte b5 = (bArr[i6] << Ascii.SO) ^ b4;
                    if (b5 >= 0) {
                        j2 = (long) (b5 ^ 16256);
                        i6 = i7;
                    } else {
                        int i8 = i3 + 4;
                        byte b6 = b5 ^ (bArr[i7] << Ascii.NAK);
                        if (b6 < 0) {
                            i6 = i8;
                            j2 = (long) (-2080896 ^ b6);
                        } else {
                            long j5 = (long) b6;
                            int i9 = i3 + 5;
                            long j6 = j5 ^ (((long) bArr[i8]) << 28);
                            if (j6 >= 0) {
                                j4 = 266354560;
                            } else {
                                int i10 = i3 + 6;
                                long j7 = j6 ^ (((long) bArr[i9]) << 35);
                                if (j7 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    i9 = i3 + 7;
                                    j6 = j7 ^ (((long) bArr[i10]) << 42);
                                    if (j6 >= 0) {
                                        j4 = 4363953127296L;
                                    } else {
                                        i10 = i3 + 8;
                                        j7 = j6 ^ (((long) bArr[i9]) << 49);
                                        if (j7 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            i6 = i3 + 9;
                                            long j8 = (j7 ^ (((long) bArr[i10]) << 56)) ^ 71499008037633920L;
                                            if (j8 < 0) {
                                                int i11 = i3 + 10;
                                                if (((long) bArr[i6]) >= 0) {
                                                    i6 = i11;
                                                }
                                            }
                                            j2 = j8;
                                        }
                                    }
                                }
                                j2 = j7 ^ j3;
                                i6 = i10;
                            }
                            j2 = j6 ^ j4;
                        }
                    }
                }
                this.zzi = i6;
                return j2;
            }
        }
        return zzm();
    }

    public final double zza() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    public final float zzb() throws IOException {
        return Float.intBitsToFloat(zzw());
    }

    public final int zzc() {
        return this.zzk + this.zzi;
    }

    public final int zzd() throws IOException {
        return zzx();
    }

    public final int zze() throws IOException {
        return zzw();
    }

    public final int zzf() throws IOException {
        return zzx();
    }

    public final int zzg() throws IOException {
        return zzw();
    }

    public final int zzh() throws IOException {
        return zzaic.zza(zzx());
    }

    public final int zzi() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        int zzx = zzx();
        this.zzj = zzx;
        if ((zzx >>> 3) != 0) {
            return zzx;
        }
        throw zzajk.zzc();
    }

    public final int zzj() throws IOException {
        return zzx();
    }

    public final long zzk() throws IOException {
        return zzy();
    }

    public final long zzl() throws IOException {
        return zzz();
    }

    public final long zzm() throws IOException {
        long j2 = 0;
        for (int i3 = 0; i3 < 64; i3 += 7) {
            byte zzv = zzv();
            j2 |= ((long) (zzv & Byte.MAX_VALUE)) << i3;
            if ((zzv & 128) == 0) {
                return j2;
            }
        }
        throw zzajk.zze();
    }

    public final long zzn() throws IOException {
        return zzy();
    }

    public final long zzo() throws IOException {
        return zzaic.zza(zzz());
    }

    public final long zzp() throws IOException {
        return zzz();
    }

    public final zzaho zzq() throws IOException {
        int zzx = zzx();
        int i3 = this.zzg;
        int i4 = this.zzi;
        if (zzx <= i3 - i4 && zzx > 0) {
            zzaho zza = zzaho.zza(this.zzf, i4, zzx);
            this.zzi += zzx;
            return zza;
        } else if (zzx == 0) {
            return zzaho.zza;
        } else {
            if (zzx >= 0) {
                byte[] zzj2 = zzj(zzx);
                if (zzj2 != null) {
                    return zzaho.zza(zzj2);
                }
                int i5 = this.zzi;
                int i6 = this.zzg;
                int i7 = i6 - i5;
                this.zzk += i6;
                this.zzi = 0;
                this.zzg = 0;
                List<byte[]> zzf2 = zzf(zzx - i7);
                byte[] bArr = new byte[zzx];
                System.arraycopy(this.zzf, i5, bArr, 0, i7);
                for (byte[] next : zzf2) {
                    System.arraycopy(next, 0, bArr, i7, next.length);
                    i7 += next.length;
                }
                return zzaho.zzb(bArr);
            }
            throw zzajk.zzf();
        }
    }

    public final String zzr() throws IOException {
        int zzx = zzx();
        if (zzx > 0) {
            int i3 = this.zzg;
            int i4 = this.zzi;
            if (zzx <= i3 - i4) {
                String str = new String(this.zzf, i4, zzx, zzajh.zza);
                this.zzi += zzx;
                return str;
            }
        }
        if (zzx == 0) {
            return "";
        }
        if (zzx < 0) {
            throw zzajk.zzf();
        } else if (zzx > this.zzg) {
            return new String(zza(zzx, false), zzajh.zza);
        } else {
            zzg(zzx);
            String str2 = new String(this.zzf, this.zzi, zzx, zzajh.zza);
            this.zzi += zzx;
            return str2;
        }
    }

    public final String zzs() throws IOException {
        byte[] bArr;
        int zzx = zzx();
        int i3 = this.zzi;
        int i4 = this.zzg;
        if (zzx <= i4 - i3 && zzx > 0) {
            bArr = this.zzf;
            this.zzi = i3 + zzx;
        } else if (zzx == 0) {
            return "";
        } else {
            if (zzx >= 0) {
                i3 = 0;
                if (zzx <= i4) {
                    zzg(zzx);
                    bArr = this.zzf;
                    this.zzi = zzx;
                } else {
                    bArr = zza(zzx, false);
                }
            } else {
                throw zzajk.zzf();
            }
        }
        return zzamn.zzb(bArr, i3, zzx);
    }

    public final boolean zzt() throws IOException {
        return this.zzi == this.zzg && !zzi(1);
    }

    public final boolean zzu() throws IOException {
        return zzz() != 0;
    }

    private zzaih(InputStream inputStream, int i3) {
        super();
        this.zzl = Integer.MAX_VALUE;
        this.zzm = null;
        zzajh.zza(inputStream, "input");
        this.zze = inputStream;
        this.zzf = new byte[4096];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    private static int zza(InputStream inputStream) throws IOException {
        try {
            return inputStream.available();
        } catch (zzajk e3) {
            e3.zzj();
            throw e3;
        }
    }

    private final List<byte[]> zzf(int i3) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i3 > 0) {
            int min = Math.min(i3, 4096);
            byte[] bArr = new byte[min];
            int i4 = 0;
            while (i4 < min) {
                int read = this.zze.read(bArr, i4, min - i4);
                if (read != -1) {
                    this.zzk += read;
                    i4 += read;
                } else {
                    throw zzajk.zzi();
                }
            }
            i3 -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzg(int i3) throws IOException {
        if (zzi(i3)) {
            return;
        }
        if (i3 > (this.zzc - this.zzk) - this.zzi) {
            throw zzajk.zzh();
        }
        throw zzajk.zzi();
    }

    private final void zzh(int i3) throws IOException {
        int i4 = this.zzg;
        int i5 = this.zzi;
        if (i3 <= i4 - i5 && i3 >= 0) {
            this.zzi = i5 + i3;
        } else if (i3 >= 0) {
            int i6 = this.zzk;
            int i7 = i6 + i5 + i3;
            int i8 = this.zzl;
            if (i7 <= i8) {
                this.zzk = i6 + i5;
                int i9 = i4 - i5;
                this.zzg = 0;
                this.zzi = 0;
                while (i9 < i3) {
                    try {
                        long j2 = (long) (i3 - i9);
                        long zza = zza(this.zze, j2);
                        int i10 = (zza > 0 ? 1 : (zza == 0 ? 0 : -1));
                        if (i10 >= 0 && zza <= j2) {
                            if (i10 == 0) {
                                break;
                            }
                            i9 += (int) zza;
                        } else {
                            throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#skip returned invalid result: " + zza + "\nThe InputStream implementation is buggy.");
                        }
                    } catch (Throwable th) {
                        this.zzk += i9;
                        zzaa();
                        throw th;
                    }
                }
                this.zzk += i9;
                zzaa();
                if (i9 < i3) {
                    int i11 = this.zzg;
                    int i12 = i11 - this.zzi;
                    this.zzi = i11;
                    zzg(1);
                    while (true) {
                        int i13 = i3 - i12;
                        int i14 = this.zzg;
                        if (i13 > i14) {
                            i12 += i14;
                            this.zzi = i14;
                            zzg(1);
                        } else {
                            this.zzi = i13;
                            return;
                        }
                    }
                }
            } else {
                zzh((i8 - i6) - i5);
                throw zzajk.zzi();
            }
        } else {
            throw zzajk.zzf();
        }
    }

    private final byte[] zzj(int i3) throws IOException {
        if (i3 == 0) {
            return zzajh.zzb;
        }
        if (i3 >= 0) {
            int i4 = this.zzk;
            int i5 = this.zzi;
            int i6 = i4 + i5 + i3;
            if (i6 - this.zzc <= 0) {
                int i7 = this.zzl;
                if (i6 <= i7) {
                    int i8 = this.zzg - i5;
                    int i9 = i3 - i8;
                    if (i9 >= 4096 && i9 > zza(this.zze)) {
                        return null;
                    }
                    byte[] bArr = new byte[i3];
                    System.arraycopy(this.zzf, this.zzi, bArr, 0, i8);
                    this.zzk += this.zzg;
                    this.zzi = 0;
                    this.zzg = 0;
                    while (i8 < i3) {
                        int zza = zza(this.zze, bArr, i8, i3 - i8);
                        if (zza != -1) {
                            this.zzk += zza;
                            i8 += zza;
                        } else {
                            throw zzajk.zzi();
                        }
                    }
                    return bArr;
                }
                zzh((i7 - i4) - i5);
                throw zzajk.zzi();
            }
            throw zzajk.zzh();
        }
        throw zzajk.zzf();
    }

    public final int zzb(int i3) throws zzajk {
        if (i3 >= 0) {
            int i4 = this.zzk + this.zzi + i3;
            int i5 = this.zzl;
            if (i4 <= i5) {
                this.zzl = i4;
                zzaa();
                return i5;
            }
            throw zzajk.zzi();
        }
        throw zzajk.zzf();
    }

    public final void zzc(int i3) throws zzajk {
        if (this.zzj != i3) {
            throw zzajk.zzb();
        }
    }

    public final void zzd(int i3) {
        this.zzl = i3;
        zzaa();
    }

    public final boolean zze(int i3) throws IOException {
        int zzi2;
        int i4 = i3 & 7;
        int i5 = 0;
        if (i4 == 0) {
            if (this.zzg - this.zzi >= 10) {
                while (i5 < 10) {
                    byte[] bArr = this.zzf;
                    int i6 = this.zzi;
                    this.zzi = i6 + 1;
                    if (bArr[i6] < 0) {
                        i5++;
                    }
                }
                throw zzajk.zze();
            }
            while (i5 < 10) {
                if (zzv() < 0) {
                    i5++;
                }
            }
            throw zzajk.zze();
            return true;
        } else if (i4 == 1) {
            zzh(8);
            return true;
        } else if (i4 == 2) {
            zzh(zzx());
            return true;
        } else if (i4 == 3) {
            do {
                zzi2 = zzi();
                if (zzi2 == 0 || !zze(zzi2)) {
                    zzc(((i3 >>> 3) << 3) | 4);
                }
                zzi2 = zzi();
                break;
            } while (!zze(zzi2));
            zzc(((i3 >>> 3) << 3) | 4);
            return true;
        } else if (i4 == 4) {
            return false;
        } else {
            if (i4 == 5) {
                zzh(4);
                return true;
            }
            throw zzajk.zza();
        }
    }

    private static int zza(InputStream inputStream, byte[] bArr, int i3, int i4) throws IOException {
        try {
            return inputStream.read(bArr, i3, i4);
        } catch (zzajk e3) {
            e3.zzj();
            throw e3;
        }
    }

    private final boolean zzi(int i3) throws IOException {
        do {
            int i4 = this.zzi;
            int i5 = i4 + i3;
            int i6 = this.zzg;
            if (i5 > i6) {
                int i7 = this.zzc;
                int i8 = this.zzk;
                if (i3 > (i7 - i8) - i4 || i8 + i4 + i3 > this.zzl) {
                    return false;
                }
                if (i4 > 0) {
                    if (i6 > i4) {
                        byte[] bArr = this.zzf;
                        System.arraycopy(bArr, i4, bArr, 0, i6 - i4);
                    }
                    this.zzk += i4;
                    this.zzg -= i4;
                    this.zzi = 0;
                }
                InputStream inputStream = this.zze;
                byte[] bArr2 = this.zzf;
                int i9 = this.zzg;
                int zza = zza(inputStream, bArr2, i9, Math.min(bArr2.length - i9, (this.zzc - this.zzk) - i9));
                if (zza == 0 || zza < -1 || zza > this.zzf.length) {
                    throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#read(byte[]) returned invalid result: " + zza + "\nThe InputStream implementation is buggy.");
                } else if (zza <= 0) {
                    return false;
                } else {
                    this.zzg += zza;
                    zzaa();
                }
            } else {
                throw new IllegalStateException(C0118y.c(i3, "refillBuffer() called when ", " bytes were already available in buffer"));
            }
        } while (this.zzg < i3);
        return true;
    }

    private static long zza(InputStream inputStream, long j2) throws IOException {
        try {
            return inputStream.skip(j2);
        } catch (zzajk e3) {
            e3.zzj();
            throw e3;
        }
    }

    private final byte[] zza(int i3, boolean z2) throws IOException {
        byte[] zzj2 = zzj(i3);
        if (zzj2 != null) {
            return zzj2;
        }
        int i4 = this.zzi;
        int i5 = this.zzg;
        int i6 = i5 - i4;
        this.zzk += i5;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> zzf2 = zzf(i3 - i6);
        byte[] bArr = new byte[i3];
        System.arraycopy(this.zzf, i4, bArr, 0, i6);
        for (byte[] next : zzf2) {
            System.arraycopy(next, 0, bArr, i6, next.length);
            i6 += next.length;
        }
        return bArr;
    }
}
