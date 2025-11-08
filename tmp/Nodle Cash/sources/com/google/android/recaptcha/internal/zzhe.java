package com.google.android.recaptcha.internal;

import java.io.IOException;

final class zzhe extends zzhh {
    private final byte[] zzc;
    private final int zzd;
    private int zze;

    public zzhe(byte[] bArr, int i3, int i4) {
        super((zzhg) null);
        int length = bArr.length;
        if (((length - i4) | i4) >= 0) {
            this.zzc = bArr;
            this.zze = 0;
            this.zzd = i4;
            return;
        }
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i4)}));
    }

    public final int zza() {
        return this.zzd - this.zze;
    }

    public final void zzb(byte b3) throws IOException {
        try {
            byte[] bArr = this.zzc;
            int i3 = this.zze;
            this.zze = i3 + 1;
            bArr[i3] = b3;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzhf(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e3);
        }
    }

    public final void zzc(byte[] bArr, int i3, int i4) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zzc, this.zze, i4);
            this.zze += i4;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzhf(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(i4)}), e3);
        }
    }

    public final void zzd(int i3, boolean z2) throws IOException {
        zzq(i3 << 3);
        zzb(z2 ? (byte) 1 : 0);
    }

    public final void zze(int i3, zzgw zzgw) throws IOException {
        zzq((i3 << 3) | 2);
        zzq(zzgw.zzd());
        zzgw.zzi(this);
    }

    public final void zzf(int i3, int i4) throws IOException {
        zzq((i3 << 3) | 5);
        zzg(i4);
    }

    public final void zzg(int i3) throws IOException {
        try {
            byte[] bArr = this.zzc;
            int i4 = this.zze;
            int i5 = i4 + 1;
            this.zze = i5;
            bArr[i4] = (byte) (i3 & 255);
            int i6 = i4 + 2;
            this.zze = i6;
            bArr[i5] = (byte) ((i3 >> 8) & 255);
            int i7 = i4 + 3;
            this.zze = i7;
            bArr[i6] = (byte) ((i3 >> 16) & 255);
            this.zze = i4 + 4;
            bArr[i7] = (byte) ((i3 >> 24) & 255);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzhf(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e3);
        }
    }

    public final void zzh(int i3, long j2) throws IOException {
        zzq((i3 << 3) | 1);
        zzi(j2);
    }

    public final void zzi(long j2) throws IOException {
        try {
            byte[] bArr = this.zzc;
            int i3 = this.zze;
            int i4 = i3 + 1;
            this.zze = i4;
            bArr[i3] = (byte) (((int) j2) & 255);
            int i5 = i3 + 2;
            this.zze = i5;
            bArr[i4] = (byte) (((int) (j2 >> 8)) & 255);
            int i6 = i3 + 3;
            this.zze = i6;
            bArr[i5] = (byte) (((int) (j2 >> 16)) & 255);
            int i7 = i3 + 4;
            this.zze = i7;
            bArr[i6] = (byte) (((int) (j2 >> 24)) & 255);
            int i8 = i3 + 5;
            this.zze = i8;
            bArr[i7] = (byte) (((int) (j2 >> 32)) & 255);
            int i9 = i3 + 6;
            this.zze = i9;
            bArr[i8] = (byte) (((int) (j2 >> 40)) & 255);
            int i10 = i3 + 7;
            this.zze = i10;
            bArr[i9] = (byte) (((int) (j2 >> 48)) & 255);
            this.zze = i3 + 8;
            bArr[i10] = (byte) (((int) (j2 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzhf(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e3);
        }
    }

    public final void zzj(int i3, int i4) throws IOException {
        zzq(i3 << 3);
        zzk(i4);
    }

    public final void zzk(int i3) throws IOException {
        if (i3 >= 0) {
            zzq(i3);
        } else {
            zzs((long) i3);
        }
    }

    public final void zzl(byte[] bArr, int i3, int i4) throws IOException {
        zzc(bArr, 0, i4);
    }

    public final void zzm(int i3, String str) throws IOException {
        zzq((i3 << 3) | 2);
        zzn(str);
    }

    public final void zzn(String str) throws IOException {
        int i3 = this.zze;
        try {
            int zzy = zzhh.zzy(str.length() * 3);
            int zzy2 = zzhh.zzy(str.length());
            if (zzy2 == zzy) {
                int i4 = i3 + zzy2;
                this.zze = i4;
                int zzb = zzma.zzb(str, this.zzc, i4, this.zzd - i4);
                this.zze = i3;
                zzq((zzb - i3) - zzy2);
                this.zze = zzb;
                return;
            }
            zzq(zzma.zzc(str));
            byte[] bArr = this.zzc;
            int i5 = this.zze;
            this.zze = zzma.zzb(str, bArr, i5, this.zzd - i5);
        } catch (zzlz e3) {
            this.zze = i3;
            zzC(str, e3);
        } catch (IndexOutOfBoundsException e4) {
            throw new zzhf(e4);
        }
    }

    public final void zzo(int i3, int i4) throws IOException {
        zzq((i3 << 3) | i4);
    }

    public final void zzp(int i3, int i4) throws IOException {
        zzq(i3 << 3);
        zzq(i4);
    }

    public final void zzq(int i3) throws IOException {
        while ((i3 & -128) != 0) {
            byte[] bArr = this.zzc;
            int i4 = this.zze;
            this.zze = i4 + 1;
            bArr[i4] = (byte) ((i3 & 127) | 128);
            i3 >>>= 7;
        }
        try {
            byte[] bArr2 = this.zzc;
            int i5 = this.zze;
            this.zze = i5 + 1;
            bArr2[i5] = (byte) i3;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzhf(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e3);
        }
    }

    public final void zzr(int i3, long j2) throws IOException {
        zzq(i3 << 3);
        zzs(j2);
    }

    public final void zzs(long j2) throws IOException {
        if (!zzhh.zzd || this.zzd - this.zze < 10) {
            while ((j2 & -128) != 0) {
                byte[] bArr = this.zzc;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr[i3] = (byte) ((((int) j2) & 127) | 128);
                j2 >>>= 7;
            }
            try {
                byte[] bArr2 = this.zzc;
                int i4 = this.zze;
                this.zze = i4 + 1;
                bArr2[i4] = (byte) ((int) j2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzhf(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e3);
            }
        } else {
            while (true) {
                int i5 = (int) j2;
                if ((j2 & -128) == 0) {
                    byte[] bArr3 = this.zzc;
                    int i6 = this.zze;
                    this.zze = i6 + 1;
                    zzlv.zzn(bArr3, (long) i6, (byte) i5);
                    return;
                }
                byte[] bArr4 = this.zzc;
                int i7 = this.zze;
                this.zze = i7 + 1;
                zzlv.zzn(bArr4, (long) i7, (byte) ((i5 & 127) | 128));
                j2 >>>= 7;
            }
        }
    }
}
