package com.google.android.recaptcha.internal;

import androidx.camera.camera2.internal.C0118y;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

final class zzha extends zzhc {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl = Integer.MAX_VALUE;

    public /* synthetic */ zzha(InputStream inputStream, int i3, zzgz zzgz) {
        super((zzhb) null);
        byte[] bArr = zzjc.zzd;
        this.zze = inputStream;
        this.zzf = new byte[4096];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    private final List zzI(int i3) throws IOException {
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
                    throw zzje.zzj();
                }
            }
            i3 -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzJ() {
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

    private final void zzK(int i3) throws IOException {
        if (zzL(i3)) {
            return;
        }
        if (i3 > (Integer.MAX_VALUE - this.zzk) - this.zzi) {
            throw zzje.zzi();
        }
        throw zzje.zzj();
    }

    private final boolean zzL(int i3) throws IOException {
        int i4 = this.zzi;
        int i5 = i4 + i3;
        int i6 = this.zzg;
        if (i5 > i6) {
            int i7 = this.zzk;
            if (i3 > (Integer.MAX_VALUE - i7) - i4 || i7 + i4 + i3 > this.zzl) {
                return false;
            }
            if (i4 > 0) {
                if (i6 > i4) {
                    byte[] bArr = this.zzf;
                    System.arraycopy(bArr, i4, bArr, 0, i6 - i4);
                }
                i7 = this.zzk + i4;
                this.zzk = i7;
                i6 = this.zzg - i4;
                this.zzg = i6;
                this.zzi = 0;
            }
            try {
                int read = this.zze.read(this.zzf, i6, Math.min(4096 - i6, (Integer.MAX_VALUE - i7) - i6));
                if (read == 0 || read < -1 || read > 4096) {
                    throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read <= 0) {
                    return false;
                } else {
                    this.zzg += read;
                    zzJ();
                    if (this.zzg >= i3) {
                        return true;
                    }
                    return zzL(i3);
                }
            } catch (zzje e3) {
                e3.zzk();
                throw e3;
            }
        } else {
            throw new IllegalStateException(C0118y.c(i3, "refillBuffer() called when ", " bytes were already available in buffer"));
        }
    }

    private final byte[] zzM(int i3, boolean z2) throws IOException {
        byte[] zzN = zzN(i3);
        if (zzN != null) {
            return zzN;
        }
        int i4 = this.zzi;
        int i5 = this.zzg;
        int i6 = i5 - i4;
        this.zzk += i5;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> zzI = zzI(i3 - i6);
        byte[] bArr = new byte[i3];
        System.arraycopy(this.zzf, i4, bArr, 0, i6);
        for (byte[] bArr2 : zzI) {
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i6, length);
            i6 += length;
        }
        return bArr;
    }

    private final byte[] zzN(int i3) throws IOException {
        if (i3 == 0) {
            return zzjc.zzd;
        }
        if (i3 >= 0) {
            int i4 = this.zzk;
            int i5 = this.zzi;
            int i6 = i4 + i5 + i3;
            if (C.RATE_UNSET_INT + i6 <= 0) {
                int i7 = this.zzl;
                if (i6 <= i7) {
                    int i8 = this.zzg - i5;
                    int i9 = i3 - i8;
                    if (i9 >= 4096) {
                        try {
                            if (i9 > this.zze.available()) {
                                return null;
                            }
                        } catch (zzje e3) {
                            e3.zzk();
                            throw e3;
                        }
                    }
                    byte[] bArr = new byte[i3];
                    System.arraycopy(this.zzf, this.zzi, bArr, 0, i8);
                    this.zzk += this.zzg;
                    this.zzi = 0;
                    this.zzg = 0;
                    while (i8 < i3) {
                        try {
                            int read = this.zze.read(bArr, i8, i3 - i8);
                            if (read != -1) {
                                this.zzk += read;
                                i8 += read;
                            } else {
                                throw zzje.zzj();
                            }
                        } catch (zzje e4) {
                            e4.zzk();
                            throw e4;
                        }
                    }
                    return bArr;
                }
                zzB((i7 - i4) - i5);
                throw zzje.zzj();
            }
            throw zzje.zzi();
        }
        throw zzje.zzf();
    }

    public final void zzA(int i3) {
        this.zzl = i3;
        zzJ();
    }

    public final void zzB(int i3) throws IOException {
        int i4 = this.zzg;
        int i5 = this.zzi;
        int i6 = i4 - i5;
        if (i3 <= i6 && i3 >= 0) {
            this.zzi = i5 + i3;
        } else if (i3 >= 0) {
            int i7 = this.zzk;
            int i8 = i7 + i5;
            int i9 = this.zzl;
            if (i8 + i3 <= i9) {
                this.zzk = i8;
                this.zzg = 0;
                this.zzi = 0;
                while (i6 < i3) {
                    try {
                        long j2 = (long) (i3 - i6);
                        long skip = this.zze.skip(j2);
                        int i10 = (skip > 0 ? 1 : (skip == 0 ? 0 : -1));
                        if (i10 < 0 || skip > j2) {
                            throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#skip returned invalid result: " + skip + "\nThe InputStream implementation is buggy.");
                        } else if (i10 == 0) {
                            break;
                        } else {
                            i6 += (int) skip;
                        }
                    } catch (zzje e3) {
                        e3.zzk();
                        throw e3;
                    } catch (Throwable th) {
                        this.zzk += i6;
                        zzJ();
                        throw th;
                    }
                }
                this.zzk += i6;
                zzJ();
                if (i6 < i3) {
                    int i11 = this.zzg;
                    int i12 = i11 - this.zzi;
                    this.zzi = i11;
                    zzK(1);
                    while (true) {
                        int i13 = i3 - i12;
                        int i14 = this.zzg;
                        if (i13 > i14) {
                            i12 += i14;
                            this.zzi = i14;
                            zzK(1);
                        } else {
                            this.zzi = i13;
                            return;
                        }
                    }
                }
            } else {
                zzB((i9 - i7) - i5);
                throw zzje.zzj();
            }
        } else {
            throw zzje.zzf();
        }
    }

    public final boolean zzC() throws IOException {
        return this.zzi == this.zzg && !zzL(1);
    }

    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final boolean zzE(int i3) throws IOException {
        int zzm;
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
                throw zzje.zze();
            }
            while (i5 < 10) {
                if (zza() < 0) {
                    i5++;
                }
            }
            throw zzje.zze();
            return true;
        } else if (i4 == 1) {
            zzB(8);
            return true;
        } else if (i4 == 2) {
            zzB(zzj());
            return true;
        } else if (i4 == 3) {
            do {
                zzm = zzm();
                if (zzm == 0 || !zzE(zzm)) {
                    zzz(((i3 >>> 3) << 3) | 4);
                }
                zzm = zzm();
                break;
            } while (!zzE(zzm));
            zzz(((i3 >>> 3) << 3) | 4);
            return true;
        } else if (i4 == 4) {
            return false;
        } else {
            if (i4 == 5) {
                zzB(4);
                return true;
            }
            throw zzje.zza();
        }
    }

    public final byte zza() throws IOException {
        if (this.zzi == this.zzg) {
            zzK(1);
        }
        byte[] bArr = this.zzf;
        int i3 = this.zzi;
        this.zzi = i3 + 1;
        return bArr[i3];
    }

    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    public final int zzd() {
        return this.zzk + this.zzi;
    }

    public final int zze(int i3) throws zzje {
        if (i3 >= 0) {
            int i4 = this.zzk + this.zzi;
            int i5 = this.zzl;
            int i6 = i3 + i4;
            if (i6 <= i5) {
                this.zzl = i6;
                zzJ();
                return i5;
            }
            throw zzje.zzj();
        }
        throw zzje.zzf();
    }

    public final int zzf() throws IOException {
        return zzj();
    }

    public final int zzg() throws IOException {
        return zzi();
    }

    public final int zzh() throws IOException {
        return zzj();
    }

    public final int zzi() throws IOException {
        int i3 = this.zzi;
        if (this.zzg - i3 < 4) {
            zzK(4);
            i3 = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i3 + 4;
        return (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16) | ((bArr[i3 + 3] & 255) << Ascii.CAN);
    }

    public final int zzj() throws IOException {
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
        return (int) zzs();
    }

    public final int zzk() throws IOException {
        return zzi();
    }

    public final int zzl() throws IOException {
        return zzhc.zzF(zzj());
    }

    public final int zzm() throws IOException {
        if (zzC()) {
            this.zzj = 0;
            return 0;
        }
        int zzj2 = zzj();
        this.zzj = zzj2;
        if ((zzj2 >>> 3) != 0) {
            return zzj2;
        }
        throw zzje.zzc();
    }

    public final int zzn() throws IOException {
        return zzj();
    }

    public final long zzo() throws IOException {
        return zzq();
    }

    public final long zzp() throws IOException {
        return zzr();
    }

    public final long zzq() throws IOException {
        int i3 = this.zzi;
        if (this.zzg - i3 < 8) {
            zzK(8);
            i3 = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i3 + 8;
        long j2 = (long) bArr[i3];
        long j3 = (long) bArr[i3 + 2];
        long j4 = (long) bArr[i3 + 3];
        long j5 = (long) bArr[i3 + 4];
        long j6 = (long) bArr[i3 + 5];
        long j7 = (long) bArr[i3 + 6];
        long j8 = (j2 & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((j3 & 255) << 16) | ((j4 & 255) << 24);
        long j9 = (j7 & 255) << 48;
        return ((((long) bArr[i3 + 7]) & 255) << 56) | j9 | j8 | ((j5 & 255) << 32) | ((j6 & 255) << 40);
    }

    public final long zzr() throws IOException {
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
                    } else {
                        int i8 = i3 + 4;
                        byte b6 = b5 ^ (bArr[i7] << Ascii.NAK);
                        if (b6 < 0) {
                            i6 = i8;
                            j2 = (long) (-2080896 ^ b6);
                        } else {
                            i7 = i3 + 5;
                            long j5 = (((long) bArr[i8]) << 28) ^ ((long) b6);
                            if (j5 >= 0) {
                                j2 = j5 ^ 266354560;
                            } else {
                                i6 = i3 + 6;
                                long j6 = (((long) bArr[i7]) << 35) ^ j5;
                                if (j6 < 0) {
                                    j4 = -34093383808L;
                                } else {
                                    int i9 = i3 + 7;
                                    long j7 = j6 ^ (((long) bArr[i6]) << 42);
                                    if (j7 >= 0) {
                                        j3 = j7 ^ 4363953127296L;
                                    } else {
                                        i6 = i3 + 8;
                                        j6 = j7 ^ (((long) bArr[i9]) << 49);
                                        if (j6 < 0) {
                                            j4 = -558586000294016L;
                                        } else {
                                            i9 = i3 + 9;
                                            long j8 = (j6 ^ (((long) bArr[i6]) << 56)) ^ 71499008037633920L;
                                            if (j8 < 0) {
                                                i6 = i3 + 10;
                                                if (((long) bArr[i9]) >= 0) {
                                                    j2 = j8;
                                                }
                                            } else {
                                                j3 = j8;
                                            }
                                        }
                                    }
                                    i6 = i9;
                                }
                                j2 = j6 ^ j4;
                            }
                        }
                    }
                    i6 = i7;
                }
                this.zzi = i6;
                return j2;
            }
        }
        return zzs();
    }

    public final long zzs() throws IOException {
        long j2 = 0;
        for (int i3 = 0; i3 < 64; i3 += 7) {
            byte zza = zza();
            j2 |= ((long) (zza & Byte.MAX_VALUE)) << i3;
            if ((zza & 128) == 0) {
                return j2;
            }
        }
        throw zzje.zze();
    }

    public final long zzt() throws IOException {
        return zzq();
    }

    public final long zzu() throws IOException {
        return zzhc.zzG(zzr());
    }

    public final long zzv() throws IOException {
        return zzr();
    }

    public final zzgw zzw() throws IOException {
        int zzj2 = zzj();
        int i3 = this.zzg;
        int i4 = this.zzi;
        if (zzj2 <= i3 - i4 && zzj2 > 0) {
            zzgw zzm = zzgw.zzm(this.zzf, i4, zzj2);
            this.zzi += zzj2;
            return zzm;
        } else if (zzj2 == 0) {
            return zzgw.zzb;
        } else {
            byte[] zzN = zzN(zzj2);
            if (zzN != null) {
                return zzgw.zzm(zzN, 0, zzN.length);
            }
            int i5 = this.zzi;
            int i6 = this.zzg;
            int i7 = i6 - i5;
            this.zzk += i6;
            this.zzi = 0;
            this.zzg = 0;
            List<byte[]> zzI = zzI(zzj2 - i7);
            byte[] bArr = new byte[zzj2];
            System.arraycopy(this.zzf, i5, bArr, 0, i7);
            for (byte[] bArr2 : zzI) {
                int length = bArr2.length;
                System.arraycopy(bArr2, 0, bArr, i7, length);
                i7 += length;
            }
            return new zzgt(bArr);
        }
    }

    public final String zzx() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0) {
            int i3 = this.zzg;
            int i4 = this.zzi;
            if (zzj2 <= i3 - i4) {
                String str = new String(this.zzf, i4, zzj2, zzjc.zzb);
                this.zzi += zzj2;
                return str;
            }
        }
        if (zzj2 == 0) {
            return "";
        }
        if (zzj2 > this.zzg) {
            return new String(zzM(zzj2, false), zzjc.zzb);
        }
        zzK(zzj2);
        String str2 = new String(this.zzf, this.zzi, zzj2, zzjc.zzb);
        this.zzi += zzj2;
        return str2;
    }

    public final String zzy() throws IOException {
        byte[] bArr;
        int zzj2 = zzj();
        int i3 = this.zzi;
        int i4 = this.zzg;
        if (zzj2 <= i4 - i3 && zzj2 > 0) {
            bArr = this.zzf;
            this.zzi = i3 + zzj2;
        } else if (zzj2 == 0) {
            return "";
        } else {
            i3 = 0;
            if (zzj2 <= i4) {
                zzK(zzj2);
                bArr = this.zzf;
                this.zzi = zzj2;
            } else {
                bArr = zzM(zzj2, false);
            }
        }
        return zzma.zzd(bArr, i3, zzj2);
    }

    public final void zzz(int i3) throws zzje {
        if (this.zzj != i3) {
            throw zzje.zzb();
        }
    }
}
