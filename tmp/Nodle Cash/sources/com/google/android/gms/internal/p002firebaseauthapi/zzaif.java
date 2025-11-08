package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaif  reason: invalid package */
final class zzaif extends zzaic {
    private final byte[] zze;
    private final boolean zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;

    private final void zzaa() {
        int i3 = this.zzg + this.zzh;
        this.zzg = i3;
        int i4 = i3 - this.zzj;
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
        int i3 = this.zzi;
        if (i3 != this.zzg) {
            byte[] bArr = this.zze;
            this.zzi = i3 + 1;
            return bArr[i3];
        }
        throw zzajk.zzi();
    }

    private final int zzw() throws IOException {
        int i3 = this.zzi;
        if (this.zzg - i3 >= 4) {
            byte[] bArr = this.zze;
            this.zzi = i3 + 4;
            return (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16) | ((bArr[i3 + 3] & 255) << Ascii.CAN);
        }
        throw zzajk.zzi();
    }

    private final int zzx() throws IOException {
        byte b3;
        byte b4;
        int i3 = this.zzi;
        int i4 = this.zzg;
        if (i4 != i3) {
            byte[] bArr = this.zze;
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
        if (this.zzg - i3 >= 8) {
            byte[] bArr = this.zze;
            this.zzi = i3 + 8;
            return ((((long) bArr[i3 + 7]) & 255) << 56) | (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48);
        }
        throw zzajk.zzi();
    }

    private final long zzz() throws IOException {
        long j2;
        long j3;
        long j4;
        int i3 = this.zzi;
        int i4 = this.zzg;
        if (i4 != i3) {
            byte[] bArr = this.zze;
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
        return this.zzi - this.zzj;
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
            this.zzk = 0;
            return 0;
        }
        int zzx = zzx();
        this.zzk = zzx;
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
        byte[] bArr;
        int zzx = zzx();
        if (zzx > 0) {
            int i3 = this.zzg;
            int i4 = this.zzi;
            if (zzx <= i3 - i4) {
                zzaho zza = zzaho.zza(this.zze, i4, zzx);
                this.zzi += zzx;
                return zza;
            }
        }
        if (zzx == 0) {
            return zzaho.zza;
        }
        if (zzx > 0) {
            int i5 = this.zzg;
            int i6 = this.zzi;
            if (zzx <= i5 - i6) {
                int i7 = zzx + i6;
                this.zzi = i7;
                bArr = Arrays.copyOfRange(this.zze, i6, i7);
                return zzaho.zzb(bArr);
            }
        }
        if (zzx > 0) {
            throw zzajk.zzi();
        } else if (zzx == 0) {
            bArr = zzajh.zzb;
            return zzaho.zzb(bArr);
        } else {
            throw zzajk.zzf();
        }
    }

    public final String zzr() throws IOException {
        int zzx = zzx();
        if (zzx > 0) {
            int i3 = this.zzg;
            int i4 = this.zzi;
            if (zzx <= i3 - i4) {
                String str = new String(this.zze, i4, zzx, zzajh.zza);
                this.zzi += zzx;
                return str;
            }
        }
        if (zzx == 0) {
            return "";
        }
        if (zzx < 0) {
            throw zzajk.zzf();
        }
        throw zzajk.zzi();
    }

    public final String zzs() throws IOException {
        int zzx = zzx();
        if (zzx > 0) {
            int i3 = this.zzg;
            int i4 = this.zzi;
            if (zzx <= i3 - i4) {
                String zzb = zzamn.zzb(this.zze, i4, zzx);
                this.zzi += zzx;
                return zzb;
            }
        }
        if (zzx == 0) {
            return "";
        }
        if (zzx <= 0) {
            throw zzajk.zzf();
        }
        throw zzajk.zzi();
    }

    public final boolean zzt() throws IOException {
        return this.zzi == this.zzg;
    }

    public final boolean zzu() throws IOException {
        return zzz() != 0;
    }

    private zzaif(byte[] bArr, int i3, int i4, boolean z2) {
        super();
        this.zzl = Integer.MAX_VALUE;
        this.zze = bArr;
        this.zzg = i4 + i3;
        this.zzi = i3;
        this.zzj = i3;
        this.zzf = z2;
    }

    private final void zzf(int i3) throws IOException {
        if (i3 >= 0) {
            int i4 = this.zzg;
            int i5 = this.zzi;
            if (i3 <= i4 - i5) {
                this.zzi = i5 + i3;
                return;
            }
        }
        if (i3 < 0) {
            throw zzajk.zzf();
        }
        throw zzajk.zzi();
    }

    public final int zzb(int i3) throws zzajk {
        if (i3 >= 0) {
            int zzc = i3 + zzc();
            if (zzc >= 0) {
                int i4 = this.zzl;
                if (zzc <= i4) {
                    this.zzl = zzc;
                    zzaa();
                    return i4;
                }
                throw zzajk.zzi();
            }
            throw zzajk.zzg();
        }
        throw zzajk.zzf();
    }

    public final void zzc(int i3) throws zzajk {
        if (this.zzk != i3) {
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
                    byte[] bArr = this.zze;
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
            zzf(8);
            return true;
        } else if (i4 == 2) {
            zzf(zzx());
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
                zzf(4);
                return true;
            }
            throw zzajk.zza();
        }
    }
}
