package com.google.android.gms.internal.fido;

import java.io.IOException;
import javax.annotation.CheckForNull;

final class zzce extends zzcg {
    final char[] zza;

    private zzce(zzcd zzcd) {
        super(zzcd, (Character) null);
        this.zza = new char[512];
        zzap.zzc(zzcd.zzf.length == 16);
        for (int i3 = 0; i3 < 256; i3++) {
            this.zza[i3] = zzcd.zza(i3 >>> 4);
            this.zza[i3 | 256] = zzcd.zza(i3 & 15);
        }
    }

    public final zzch zza(zzcd zzcd, @CheckForNull Character ch2) {
        return new zzce(zzcd);
    }

    public final void zzb(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        zzap.zze(0, i4, bArr.length);
        for (int i5 = 0; i5 < i4; i5++) {
            byte b3 = bArr[i5] & 255;
            appendable.append(this.zza[b3]);
            appendable.append(this.zza[b3 | 256]);
        }
    }

    public zzce(String str, String str2) {
        this(new zzcd("base16()", "0123456789ABCDEF".toCharArray()));
    }
}
