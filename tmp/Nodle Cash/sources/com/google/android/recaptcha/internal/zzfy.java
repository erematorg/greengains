package com.google.android.recaptcha.internal;

import java.io.IOException;
import org.objectweb.asm.signature.SignatureVisitor;

public abstract class zzfy {
    private static final zzfy zza;
    private static final zzfy zzb;
    private static final zzfy zzc;
    private static final zzfy zzd;
    private static final zzfy zze = new zzfu("base16()", "0123456789ABCDEF");

    static {
        Character valueOf = Character.valueOf(SignatureVisitor.INSTANCEOF);
        zza = new zzfv("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", valueOf);
        zzb = new zzfv("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", valueOf);
        zzc = new zzfx("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", valueOf);
        zzd = new zzfx("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", valueOf);
    }

    public static zzfy zzg() {
        return zza;
    }

    public static zzfy zzh() {
        return zzb;
    }

    public abstract int zza(byte[] bArr, CharSequence charSequence) throws zzfw;

    public abstract void zzb(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException;

    public abstract int zzc(int i3);

    public abstract int zzd(int i3);

    public CharSequence zze(CharSequence charSequence) {
        throw null;
    }

    public final String zzi(byte[] bArr, int i3, int i4) {
        zzff.zzd(0, i4, bArr.length);
        StringBuilder sb = new StringBuilder(zzd(i4));
        try {
            zzb(sb, bArr, 0, i4);
            return sb.toString();
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }

    public final byte[] zzj(CharSequence charSequence) {
        try {
            CharSequence zze2 = zze(charSequence);
            int zzc2 = zzc(zze2.length());
            byte[] bArr = new byte[zzc2];
            int zza2 = zza(bArr, zze2);
            if (zza2 == zzc2) {
                return bArr;
            }
            byte[] bArr2 = new byte[zza2];
            System.arraycopy(bArr, 0, bArr2, 0, zza2);
            return bArr2;
        } catch (zzfw e3) {
            throw new IllegalArgumentException(e3);
        }
    }
}
