package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;

final class zzdk extends zzdn {
    private final byte[] zzb;
    private final int zzc;
    private int zzd;

    public zzdk(byte[] bArr, int i3, int i4) {
        super((zzdm) null);
        int length = bArr.length;
        if (((length - i4) | i4) >= 0) {
            this.zzb = bArr;
            this.zzd = 0;
            this.zzc = i4;
            return;
        }
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i4)}));
    }

    public final int zza() {
        return this.zzc - this.zzd;
    }

    public final void zzb(byte b3) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i3 = this.zzd;
            this.zzd = i3 + 1;
            bArr[i3] = b3;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdl(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
        }
    }

    public final void zzc(byte[] bArr, int i3, int i4) throws IOException {
        try {
            System.arraycopy(bArr, i3, this.zzb, this.zzd, i4);
            this.zzd += i4;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdl(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i4)}), e3);
        }
    }

    public final void zzd(int i3, boolean z2) throws IOException {
        zzt(i3 << 3);
        zzb(z2 ? (byte) 1 : 0);
    }

    public final void zze(int i3, zzdf zzdf) throws IOException {
        zzt((i3 << 3) | 2);
        zzt(zzdf.zzd());
        zzdf.zzm(this);
    }

    public final void zzf(int i3, int i4) throws IOException {
        zzt((i3 << 3) | 5);
        zzg(i4);
    }

    public final void zzg(int i3) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i4 = this.zzd;
            int i5 = i4 + 1;
            this.zzd = i5;
            bArr[i4] = (byte) (i3 & 255);
            int i6 = i4 + 2;
            this.zzd = i6;
            bArr[i5] = (byte) ((i3 >> 8) & 255);
            int i7 = i4 + 3;
            this.zzd = i7;
            bArr[i6] = (byte) ((i3 >> 16) & 255);
            this.zzd = i4 + 4;
            bArr[i7] = (byte) ((i3 >> 24) & 255);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdl(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
        }
    }

    public final void zzh(int i3, long j2) throws IOException {
        zzt((i3 << 3) | 1);
        zzi(j2);
    }

    public final void zzi(long j2) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i3 = this.zzd;
            int i4 = i3 + 1;
            this.zzd = i4;
            bArr[i3] = (byte) (((int) j2) & 255);
            int i5 = i3 + 2;
            this.zzd = i5;
            bArr[i4] = (byte) (((int) (j2 >> 8)) & 255);
            int i6 = i3 + 3;
            this.zzd = i6;
            bArr[i5] = (byte) (((int) (j2 >> 16)) & 255);
            int i7 = i3 + 4;
            this.zzd = i7;
            bArr[i6] = (byte) (((int) (j2 >> 24)) & 255);
            int i8 = i3 + 5;
            this.zzd = i8;
            bArr[i7] = (byte) (((int) (j2 >> 32)) & 255);
            int i9 = i3 + 6;
            this.zzd = i9;
            bArr[i8] = (byte) (((int) (j2 >> 40)) & 255);
            int i10 = i3 + 7;
            this.zzd = i10;
            bArr[i9] = (byte) (((int) (j2 >> 48)) & 255);
            this.zzd = i3 + 8;
            bArr[i10] = (byte) (((int) (j2 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdl(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
        }
    }

    public final void zzj(int i3, int i4) throws IOException {
        zzt(i3 << 3);
        zzk(i4);
    }

    public final void zzk(int i3) throws IOException {
        if (i3 >= 0) {
            zzt(i3);
        } else {
            zzv((long) i3);
        }
    }

    public final void zzl(byte[] bArr, int i3, int i4) throws IOException {
        zzc(bArr, 0, i4);
    }

    public final void zzm(int i3, zzfm zzfm, zzge zzge) throws IOException {
        zzt((i3 << 3) | 2);
        zzt(((zzcq) zzfm).zzB(zzge));
        zzge.zzi(zzfm, this.zza);
    }

    public final void zzn(int i3, zzfm zzfm) throws IOException {
        zzt(11);
        zzs(2, i3);
        zzt(26);
        zzt(zzfm.zzF());
        zzfm.zzab(this);
        zzt(12);
    }

    public final void zzo(int i3, zzdf zzdf) throws IOException {
        zzt(11);
        zzs(2, i3);
        zze(3, zzdf);
        zzt(12);
    }

    public final void zzp(int i3, String str) throws IOException {
        zzt((i3 << 3) | 2);
        zzq(str);
    }

    public final void zzq(String str) throws IOException {
        int i3 = this.zzd;
        try {
            int zzA = zzdn.zzA(str.length() * 3);
            int zzA2 = zzdn.zzA(str.length());
            if (zzA2 == zzA) {
                int i4 = i3 + zzA2;
                this.zzd = i4;
                int zzd2 = zzhe.zzd(str, this.zzb, i4, this.zzc - i4);
                this.zzd = i3;
                zzt((zzd2 - i3) - zzA2);
                this.zzd = zzd2;
                return;
            }
            zzt(zzhe.zze(str));
            byte[] bArr = this.zzb;
            int i5 = this.zzd;
            this.zzd = zzhe.zzd(str, bArr, i5, this.zzc - i5);
        } catch (zzhd e3) {
            this.zzd = i3;
            zzD(str, e3);
        } catch (IndexOutOfBoundsException e4) {
            throw new zzdl(e4);
        }
    }

    public final void zzr(int i3, int i4) throws IOException {
        zzt((i3 << 3) | i4);
    }

    public final void zzs(int i3, int i4) throws IOException {
        zzt(i3 << 3);
        zzt(i4);
    }

    public final void zzt(int i3) throws IOException {
        while ((i3 & -128) != 0) {
            byte[] bArr = this.zzb;
            int i4 = this.zzd;
            this.zzd = i4 + 1;
            bArr[i4] = (byte) ((i3 | 128) & 255);
            i3 >>>= 7;
        }
        try {
            byte[] bArr2 = this.zzb;
            int i5 = this.zzd;
            this.zzd = i5 + 1;
            bArr2[i5] = (byte) i3;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdl(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
        }
    }

    public final void zzu(int i3, long j2) throws IOException {
        zzt(i3 << 3);
        zzv(j2);
    }

    public final void zzv(long j2) throws IOException {
        if (!zzdn.zzc || this.zzc - this.zzd < 10) {
            while ((j2 & -128) != 0) {
                byte[] bArr = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr[i3] = (byte) ((((int) j2) | 128) & 255);
                j2 >>>= 7;
            }
            try {
                byte[] bArr2 = this.zzb;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr2[i4] = (byte) ((int) j2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzdl(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e3);
            }
        } else {
            while (true) {
                int i5 = (int) j2;
                if ((j2 & -128) == 0) {
                    byte[] bArr3 = this.zzb;
                    int i6 = this.zzd;
                    this.zzd = i6 + 1;
                    zzgz.zzn(bArr3, (long) i6, (byte) i5);
                    return;
                }
                byte[] bArr4 = this.zzb;
                int i7 = this.zzd;
                this.zzd = i7 + 1;
                zzgz.zzn(bArr4, (long) i7, (byte) ((i5 | 128) & 255));
                j2 >>>= 7;
            }
        }
    }
}
