package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

final class zzgy extends zzhc {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj = Integer.MAX_VALUE;

    public /* synthetic */ zzgy(byte[] bArr, int i3, int i4, boolean z2, zzgx zzgx) {
        super((zzhb) null);
        this.zze = bArr;
        this.zzf = 0;
        this.zzh = 0;
    }

    private final void zzI() {
        int i3 = this.zzf + this.zzg;
        this.zzf = i3;
        int i4 = this.zzj;
        if (i3 > i4) {
            int i5 = i3 - i4;
            this.zzg = i5;
            this.zzf = i3 - i5;
            return;
        }
        this.zzg = 0;
    }

    public final void zzA(int i3) {
        this.zzj = i3;
        zzI();
    }

    public final void zzB(int i3) throws IOException {
        if (i3 >= 0) {
            int i4 = this.zzf;
            int i5 = this.zzh;
            if (i3 <= i4 - i5) {
                this.zzh = i5 + i3;
                return;
            }
        }
        if (i3 < 0) {
            throw zzje.zzf();
        }
        throw zzje.zzj();
    }

    public final boolean zzC() throws IOException {
        return this.zzh == this.zzf;
    }

    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final boolean zzE(int i3) throws IOException {
        int zzm;
        int i4 = i3 & 7;
        int i5 = 0;
        if (i4 == 0) {
            if (this.zzf - this.zzh >= 10) {
                while (i5 < 10) {
                    byte[] bArr = this.zze;
                    int i6 = this.zzh;
                    this.zzh = i6 + 1;
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
        int i3 = this.zzh;
        if (i3 != this.zzf) {
            byte[] bArr = this.zze;
            this.zzh = i3 + 1;
            return bArr[i3];
        }
        throw zzje.zzj();
    }

    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    public final int zzd() {
        return this.zzh;
    }

    public final int zze(int i3) throws zzje {
        if (i3 >= 0) {
            int i4 = i3 + this.zzh;
            if (i4 >= 0) {
                int i5 = this.zzj;
                if (i4 <= i5) {
                    this.zzj = i4;
                    zzI();
                    return i5;
                }
                throw zzje.zzj();
            }
            throw zzje.zzg();
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
        int i3 = this.zzh;
        if (this.zzf - i3 >= 4) {
            byte[] bArr = this.zze;
            this.zzh = i3 + 4;
            return (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16) | ((bArr[i3 + 3] & 255) << Ascii.CAN);
        }
        throw zzje.zzj();
    }

    public final int zzj() throws IOException {
        byte b3;
        byte b4;
        int i3 = this.zzh;
        int i4 = this.zzf;
        if (i4 != i3) {
            byte[] bArr = this.zze;
            int i5 = i3 + 1;
            byte b5 = bArr[i3];
            if (b5 >= 0) {
                this.zzh = i5;
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
                this.zzh = i6;
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
            this.zzi = 0;
            return 0;
        }
        int zzj2 = zzj();
        this.zzi = zzj2;
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
        int i3 = this.zzh;
        if (this.zzf - i3 >= 8) {
            byte[] bArr = this.zze;
            this.zzh = i3 + 8;
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
        throw zzje.zzj();
    }

    public final long zzr() throws IOException {
        long j2;
        long j3;
        long j4;
        int i3 = this.zzh;
        int i4 = this.zzf;
        if (i4 != i3) {
            byte[] bArr = this.zze;
            int i5 = i3 + 1;
            byte b3 = bArr[i3];
            if (b3 >= 0) {
                this.zzh = i5;
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
                this.zzh = i6;
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
        if (zzj2 > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (zzj2 <= i3 - i4) {
                zzgw zzm = zzgw.zzm(this.zze, i4, zzj2);
                this.zzh += zzj2;
                return zzm;
            }
        }
        if (zzj2 == 0) {
            return zzgw.zzb;
        }
        if (zzj2 > 0) {
            int i5 = this.zzf;
            int i6 = this.zzh;
            if (zzj2 <= i5 - i6) {
                int i7 = zzj2 + i6;
                this.zzh = i7;
                return new zzgt(Arrays.copyOfRange(this.zze, i6, i7));
            }
        }
        if (zzj2 <= 0) {
            throw zzje.zzf();
        }
        throw zzje.zzj();
    }

    public final String zzx() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (zzj2 <= i3 - i4) {
                String str = new String(this.zze, i4, zzj2, zzjc.zzb);
                this.zzh += zzj2;
                return str;
            }
        }
        if (zzj2 == 0) {
            return "";
        }
        if (zzj2 < 0) {
            throw zzje.zzf();
        }
        throw zzje.zzj();
    }

    public final String zzy() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (zzj2 <= i3 - i4) {
                String zzd = zzma.zzd(this.zze, i4, zzj2);
                this.zzh += zzj2;
                return zzd;
            }
        }
        if (zzj2 == 0) {
            return "";
        }
        if (zzj2 <= 0) {
            throw zzje.zzf();
        }
        throw zzje.zzj();
    }

    public final void zzz(int i3) throws zzje {
        if (this.zzi != i3) {
            throw zzje.zzb();
        }
    }
}
