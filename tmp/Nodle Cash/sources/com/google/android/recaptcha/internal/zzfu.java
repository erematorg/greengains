package com.google.android.recaptcha.internal;

import A.a;
import java.io.IOException;

final class zzfu extends zzfx {
    final char[] zza = new char[512];

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzfu(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            com.google.android.recaptcha.internal.zzft r4 = new com.google.android.recaptcha.internal.zzft
            java.lang.String r5 = "base16()"
            java.lang.String r0 = "0123456789ABCDEF"
            char[] r0 = r0.toCharArray()
            r4.<init>(r5, r0)
            r5 = 0
            r3.<init>(r4, r5)
            r5 = 512(0x200, float:7.175E-43)
            char[] r5 = new char[r5]
            r3.zza = r5
            char[] r5 = r4.zzf
            int r5 = r5.length
            r0 = 16
            r1 = 0
            if (r5 != r0) goto L_0x0023
            r5 = 1
            goto L_0x0024
        L_0x0023:
            r5 = r1
        L_0x0024:
            com.google.android.recaptcha.internal.zzff.zza(r5)
        L_0x0027:
            r5 = 256(0x100, float:3.59E-43)
            if (r1 >= r5) goto L_0x0044
            char[] r5 = r3.zza
            int r0 = r1 >>> 4
            char r0 = r4.zza(r0)
            r5[r1] = r0
            char[] r5 = r3.zza
            r0 = r1 | 256(0x100, float:3.59E-43)
            r2 = r1 & 15
            char r2 = r4.zza(r2)
            r5[r0] = r2
            int r1 = r1 + 1
            goto L_0x0027
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzfu.<init>(java.lang.String, java.lang.String):void");
    }

    public final int zza(byte[] bArr, CharSequence charSequence) throws zzfw {
        if (charSequence.length() % 2 != 1) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < charSequence.length()) {
                bArr[i4] = (byte) ((this.zzb.zzb(charSequence.charAt(i3)) << 4) | this.zzb.zzb(charSequence.charAt(i3 + 1)));
                i3 += 2;
                i4++;
            }
            return i4;
        }
        throw new zzfw(a.k("Invalid input length ", charSequence.length()));
    }

    public final void zzb(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        zzff.zzd(0, i4, bArr.length);
        for (int i5 = 0; i5 < i4; i5++) {
            byte b3 = bArr[i5] & 255;
            appendable.append(this.zza[b3]);
            appendable.append(this.zza[b3 | 256]);
        }
    }
}
