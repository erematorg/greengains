package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajh  reason: invalid package */
public final class zzajh {
    static final Charset zza = Charset.forName("UTF-8");
    public static final byte[] zzb;
    private static final Charset zzc = Charset.forName("US-ASCII");
    private static final Charset zzd = Charset.forName("ISO-8859-1");
    private static final ByteBuffer zze;
    private static final zzaic zzf;

    static {
        byte[] bArr = new byte[0];
        zzb = bArr;
        zze = ByteBuffer.wrap(bArr);
        zzf = zzaic.zza(bArr, 0, bArr.length, false);
    }

    public static int zza(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    public static String zzb(byte[] bArr) {
        return new String(bArr, zza);
    }

    public static boolean zzc(byte[] bArr) {
        return zzamn.zza(bArr);
    }

    public static int zza(boolean z2) {
        return z2 ? 1231 : 1237;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        int zza2 = zza(length, bArr, 0, length);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5) {
        for (int i6 = i4; i6 < i4 + i5; i6++) {
            i3 = (i3 * 31) + bArr[i6];
        }
        return i3;
    }

    public static <T> T zza(T t2) {
        t2.getClass();
        return t2;
    }

    public static <T> T zza(T t2, String str) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(str);
    }

    public static boolean zza(zzakp zzakp) {
        boolean z2 = zzakp instanceof zzahg;
        return false;
    }
}
