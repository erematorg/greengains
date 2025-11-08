package com.google.android.gms.internal.fido;

import com.google.common.base.Ascii;
import java.io.IOException;
import javax.annotation.CheckForNull;
import okio.Utf8;

final class zzcf extends zzcg {
    private zzcf(zzcd zzcd, @CheckForNull Character ch2) {
        super(zzcd, ch2);
        zzap.zzc(zzcd.zzf.length == 64);
    }

    public final zzch zza(zzcd zzcd, @CheckForNull Character ch2) {
        return new zzcf(zzcd, ch2);
    }

    public final void zzb(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        int i5 = 0;
        zzap.zze(0, i4, bArr.length);
        for (int i6 = i4; i6 >= 3; i6 -= 3) {
            byte b3 = ((bArr[i5 + 1] & 255) << 8) | ((bArr[i5] & 255) << 16) | (bArr[i5 + 2] & 255);
            appendable.append(this.zzb.zza(b3 >>> Ascii.DC2));
            appendable.append(this.zzb.zza((b3 >>> Ascii.FF) & 63));
            appendable.append(this.zzb.zza((b3 >>> 6) & 63));
            appendable.append(this.zzb.zza(b3 & Utf8.REPLACEMENT_BYTE));
            i5 += 3;
        }
        if (i5 < i4) {
            zze(appendable, bArr, i5, i4 - i5);
        }
    }

    public zzcf(String str, String str2, @CheckForNull Character ch2) {
        this(new zzcd(str, str2.toCharArray()), ch2);
    }
}
