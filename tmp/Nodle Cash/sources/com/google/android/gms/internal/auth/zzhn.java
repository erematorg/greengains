package com.google.android.gms.internal.auth;

final class zzhn {
    public static final /* synthetic */ int zza = 0;
    private static final zzhl zzb = new zzhm();

    static {
        if (zzhj.zzu() && zzhj.zzv()) {
            int i3 = zzds.zza;
        }
    }

    public static /* bridge */ /* synthetic */ int zza(byte[] bArr, int i3, int i4) {
        int i5 = i4 - i3;
        byte b3 = bArr[i3 - 1];
        if (i5 != 0) {
            if (i5 == 1) {
                byte b4 = bArr[i3];
                if (b3 <= -12 && b4 <= -65) {
                    return b3 ^ (b4 << 8);
                }
            } else if (i5 == 2) {
                byte b5 = bArr[i3];
                byte b6 = bArr[i3 + 1];
                if (b3 <= -12 && b5 <= -65 && b6 <= -65) {
                    return ((b5 << 8) ^ b3) ^ (b6 << 16);
                }
            } else {
                throw new AssertionError();
            }
        } else if (b3 <= -12) {
            return b3;
        }
        return -1;
    }

    public static boolean zzb(byte[] bArr) {
        return zzb.zzb(bArr, 0, bArr.length);
    }

    public static boolean zzc(byte[] bArr, int i3, int i4) {
        return zzb.zzb(bArr, i3, i4);
    }
}
