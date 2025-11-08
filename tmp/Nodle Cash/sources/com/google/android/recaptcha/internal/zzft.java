package com.google.android.recaptcha.internal;

import androidx.compose.animation.core.a;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

final class zzft {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    /* access modifiers changed from: private */
    public final char[] zzf;
    private final byte[] zzg;
    private final boolean[] zzh;
    private final boolean zzi;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzft(java.lang.String r10, char[] r11) {
        /*
            r9 = this;
            r0 = 128(0x80, float:1.794E-43)
            byte[] r1 = new byte[r0]
            r2 = -1
            java.util.Arrays.fill(r1, r2)
            r3 = 0
            r4 = r3
        L_0x000a:
            int r5 = r11.length
            if (r4 >= r5) goto L_0x002b
            char r5 = r11[r4]
            r6 = 1
            if (r5 >= r0) goto L_0x0014
            r7 = r6
            goto L_0x0015
        L_0x0014:
            r7 = r3
        L_0x0015:
            java.lang.String r8 = "Non-ASCII character: %s"
            com.google.android.recaptcha.internal.zzff.zzc(r7, r8, r5)
            byte r7 = r1[r5]
            if (r7 != r2) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r6 = r3
        L_0x0020:
            java.lang.String r7 = "Duplicate character: %s"
            com.google.android.recaptcha.internal.zzff.zzc(r6, r7, r5)
            byte r6 = (byte) r4
            r1[r5] = r6
            int r4 = r4 + 1
            goto L_0x000a
        L_0x002b:
            r9.<init>(r10, r11, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzft.<init>(java.lang.String, char[]):void");
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzft) {
            zzft zzft = (zzft) obj;
            boolean z2 = zzft.zzi;
            if (Arrays.equals(this.zzf, zzft.zzf)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf) + 1237;
    }

    public final String toString() {
        return this.zze;
    }

    public final char zza(int i3) {
        return this.zzf[i3];
    }

    public final int zzb(char c3) throws zzfw {
        if (c3 <= 127) {
            byte b3 = this.zzg[c3];
            if (b3 != -1) {
                return b3;
            }
            if (c3 <= ' ' || c3 == 127) {
                throw new zzfw("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(c3))));
            }
            throw new zzfw(a.p("Unrecognized character: ", c3));
        }
        throw new zzfw("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(c3))));
    }

    public final boolean zzc(int i3) {
        return this.zzh[i3 % this.zzc];
    }

    public final boolean zzd(char c3) {
        return this.zzg[61] != -1;
    }

    private zzft(String str, char[] cArr, byte[] bArr, boolean z2) {
        this.zze = str;
        cArr.getClass();
        this.zzf = cArr;
        try {
            int length = cArr.length;
            int zzb2 = zzga.zzb(length, RoundingMode.UNNECESSARY);
            this.zzb = zzb2;
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(zzb2);
            int i3 = 1 << (3 - numberOfTrailingZeros);
            this.zzc = i3;
            this.zzd = zzb2 >> numberOfTrailingZeros;
            this.zza = length - 1;
            this.zzg = bArr;
            boolean[] zArr = new boolean[i3];
            for (int i4 = 0; i4 < this.zzd; i4++) {
                zArr[zzga.zza(i4 * 8, this.zzb, RoundingMode.CEILING)] = true;
            }
            this.zzh = zArr;
            this.zzi = false;
        } catch (ArithmeticException e3) {
            throw new IllegalArgumentException(A.a.k("Illegal alphabet length ", cArr.length), e3);
        }
    }
}
