package com.google.android.recaptcha.internal;

import A.a;
import java.io.IOException;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;
import org.objectweb.asm.signature.SignatureVisitor;

class zzfx extends zzfy {
    final zzft zzb;
    @CheckForNull
    final Character zzc;

    public zzfx(zzft zzft, @CheckForNull Character ch2) {
        this.zzb = zzft;
        if (ch2 == null || !zzft.zzd(SignatureVisitor.INSTANCEOF)) {
            this.zzc = ch2;
            return;
        }
        throw new IllegalArgumentException(zzfi.zza("Padding character %s was already in alphabet", ch2));
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzfx) {
            zzfx zzfx = (zzfx) obj;
            if (this.zzb.equals(zzfx.zzb)) {
                Character ch2 = this.zzc;
                Character ch3 = zzfx.zzc;
                if (ch2 != ch3) {
                    return ch2 != null && ch2.equals(ch3);
                }
                return true;
            }
        }
    }

    public final int hashCode() {
        Character ch2 = this.zzc;
        return this.zzb.hashCode() ^ (ch2 == null ? 0 : ch2.hashCode());
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

    public int zza(byte[] bArr, CharSequence charSequence) throws zzfw {
        zzft zzft;
        CharSequence zze = zze(charSequence);
        if (this.zzb.zzc(zze.length())) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < zze.length()) {
                long j2 = 0;
                int i5 = 0;
                int i6 = 0;
                while (true) {
                    zzft = this.zzb;
                    if (i5 >= zzft.zzc) {
                        break;
                    }
                    j2 <<= zzft.zzb;
                    if (i3 + i5 < zze.length()) {
                        j2 |= (long) this.zzb.zzb(zze.charAt(i6 + i3));
                        i6++;
                    }
                    i5++;
                }
                int i7 = zzft.zzd;
                int i8 = i6 * zzft.zzb;
                int i9 = (i7 - 1) * 8;
                while (i9 >= (i7 * 8) - i8) {
                    bArr[i4] = (byte) ((int) ((j2 >>> i9) & 255));
                    i9 -= 8;
                    i4++;
                }
                i3 += this.zzb.zzc;
            }
            return i4;
        }
        throw new zzfw(a.k("Invalid input length ", zze.length()));
    }

    public void zzb(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        int i5 = 0;
        zzff.zzd(0, i4, bArr.length);
        while (i5 < i4) {
            zzf(appendable, bArr, i5, Math.min(this.zzb.zzd, i4 - i5));
            i5 += this.zzb.zzd;
        }
    }

    public final int zzc(int i3) {
        return (int) (((((long) this.zzb.zzb) * ((long) i3)) + 7) / 8);
    }

    public final int zzd(int i3) {
        zzft zzft = this.zzb;
        return zzft.zzc * zzga.zza(i3, zzft.zzd, RoundingMode.CEILING);
    }

    public final CharSequence zze(CharSequence charSequence) {
        charSequence.getClass();
        if (this.zzc == null) {
            return charSequence;
        }
        int length = charSequence.length();
        do {
            length--;
            if (length < 0 || charSequence.charAt(length) != '=') {
            }
            length--;
            break;
        } while (charSequence.charAt(length) != '=');
        return charSequence.subSequence(0, length + 1);
    }

    public final void zzf(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
        zzff.zzd(i3, i3 + i4, bArr.length);
        int i5 = 0;
        zzff.zza(i4 <= this.zzb.zzd);
        long j2 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            j2 = (j2 | ((long) (bArr[i3 + i6] & 255))) << 8;
        }
        int i7 = (i4 + 1) * 8;
        zzft zzft = this.zzb;
        while (i5 < i4 * 8) {
            long j3 = j2 >>> ((i7 - zzft.zzb) - i5);
            zzft zzft2 = this.zzb;
            appendable.append(zzft2.zza(((int) j3) & zzft2.zza));
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

    public zzfx(String str, String str2, @CheckForNull Character ch2) {
        this(new zzft(str, str2.toCharArray()), ch2);
    }
}
