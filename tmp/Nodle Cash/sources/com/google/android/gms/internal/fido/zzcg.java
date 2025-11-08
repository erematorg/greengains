package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;
import org.objectweb.asm.signature.SignatureVisitor;

class zzcg extends zzch {
    @CheckForNull
    private volatile zzch zza;
    final zzcd zzb;
    @CheckForNull
    final Character zzc;

    public zzcg(zzcd zzcd, @CheckForNull Character ch2) {
        this.zzb = zzcd;
        if (ch2 == null || !zzcd.zzc(SignatureVisitor.INSTANCEOF)) {
            this.zzc = ch2;
            return;
        }
        throw new IllegalArgumentException(zzaq.zza("Padding character %s was already in alphabet", ch2));
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzcg) {
            zzcg zzcg = (zzcg) obj;
            if (this.zzb.equals(zzcg.zzb)) {
                Character ch2 = this.zzc;
                Character ch3 = zzcg.zzc;
                if (ch2 != ch3) {
                    return ch2 != null && ch2.equals(ch3);
                }
                return true;
            }
        }
    }

    public final int hashCode() {
        int hashCode = this.zzb.hashCode();
        Character ch2 = this.zzc;
        return (ch2 == null ? 0 : ch2.hashCode()) ^ hashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb);
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    public zzch zza(zzcd zzcd, @CheckForNull Character ch2) {
        return new zzcg(zzcd, ch2);
    }

    public void zzb(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        int i5 = 0;
        zzap.zze(0, i4, bArr.length);
        while (i5 < i4) {
            zze(appendable, bArr, i5, Math.min(this.zzb.zzd, i4 - i5));
            i5 += this.zzb.zzd;
        }
    }

    public final int zzc(int i3) {
        zzcd zzcd = this.zzb;
        return zzcj.zza(i3, zzcd.zzd, RoundingMode.CEILING) * zzcd.zzc;
    }

    public final zzch zzd() {
        zzch zzch = this.zza;
        if (zzch == null) {
            zzcd zzb2 = this.zzb.zzb();
            zzch = zzb2 == this.zzb ? this : zza(zzb2, this.zzc);
            this.zza = zzch;
        }
        return zzch;
    }

    public final void zze(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        zzap.zze(i3, i3 + i4, bArr.length);
        int i5 = 0;
        zzap.zzc(i4 <= this.zzb.zzd);
        long j2 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            j2 = (j2 | ((long) (bArr[i3 + i6] & 255))) << 8;
        }
        int i7 = ((i4 + 1) * 8) - this.zzb.zzb;
        while (i5 < i4 * 8) {
            zzcd zzcd = this.zzb;
            appendable.append(zzcd.zza(zzcd.zza & ((int) (j2 >>> (i7 - i5)))));
            i5 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i5 < this.zzb.zzd * 8) {
                this.zzc.getClass();
                appendable.append(SignatureVisitor.INSTANCEOF);
                i5 += this.zzb.zzb;
            }
        }
    }

    public zzcg(String str, String str2, @CheckForNull Character ch2) {
        this(new zzcd(str, str2.toCharArray()), ch2);
    }
}
