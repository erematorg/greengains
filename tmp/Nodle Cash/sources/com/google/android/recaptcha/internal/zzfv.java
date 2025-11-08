package com.google.android.recaptcha.internal;

import A.a;
import com.google.common.base.Ascii;
import java.io.IOException;
import okio.Utf8;

final class zzfv extends zzfx {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzfv(java.lang.String r2, java.lang.String r3, @javax.annotation.CheckForNull java.lang.Character r4) {
        /*
            r1 = this;
            com.google.android.recaptcha.internal.zzft r0 = new com.google.android.recaptcha.internal.zzft
            char[] r3 = r3.toCharArray()
            r0.<init>(r2, r3)
            r1.<init>(r0, r4)
            char[] r1 = r0.zzf
            int r1 = r1.length
            r2 = 64
            if (r1 != r2) goto L_0x0017
            r1 = 1
            goto L_0x0018
        L_0x0017:
            r1 = 0
        L_0x0018:
            com.google.android.recaptcha.internal.zzff.zza(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzfv.<init>(java.lang.String, java.lang.String, java.lang.Character):void");
    }

    public final int zza(byte[] bArr, CharSequence charSequence) throws zzfw {
        CharSequence zze = zze(charSequence);
        if (this.zzb.zzc(zze.length())) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < zze.length()) {
                int i5 = i4 + 1;
                int zzb = (this.zzb.zzb(zze.charAt(i3)) << 18) | (this.zzb.zzb(zze.charAt(i3 + 1)) << 12);
                bArr[i4] = (byte) (zzb >>> 16);
                int i6 = i3 + 2;
                if (i6 < zze.length()) {
                    int i7 = i3 + 3;
                    int zzb2 = zzb | (this.zzb.zzb(zze.charAt(i6)) << 6);
                    int i8 = i4 + 2;
                    bArr[i5] = (byte) ((zzb2 >>> 8) & 255);
                    if (i7 < zze.length()) {
                        i3 += 4;
                        i4 += 3;
                        bArr[i8] = (byte) ((zzb2 | this.zzb.zzb(zze.charAt(i7))) & 255);
                    } else {
                        i4 = i8;
                        i3 = i7;
                    }
                } else {
                    i3 = i6;
                    i4 = i5;
                }
            }
            return i4;
        }
        throw new zzfw(a.k("Invalid input length ", zze.length()));
    }

    public final void zzb(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        int i5 = 0;
        zzff.zzd(0, i4, bArr.length);
        for (int i6 = i4; i6 >= 3; i6 -= 3) {
            byte b3 = ((bArr[i5 + 1] & 255) << 8) | ((bArr[i5] & 255) << 16) | (bArr[i5 + 2] & 255);
            appendable.append(this.zzb.zza(b3 >>> Ascii.DC2));
            appendable.append(this.zzb.zza((b3 >>> Ascii.FF) & 63));
            appendable.append(this.zzb.zza((b3 >>> 6) & 63));
            appendable.append(this.zzb.zza(b3 & Utf8.REPLACEMENT_BYTE));
            i5 += 3;
        }
        if (i5 < i4) {
            zzf(appendable, bArr, i5, i4 - i5);
        }
    }
}
