package com.google.android.gms.internal.fido;

import A.a;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

final class zzcd {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    /* access modifiers changed from: private */
    public final char[] zzf;
    private final byte[] zzg;
    private final boolean zzh;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcd(java.lang.String r10, char[] r11) {
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
            com.google.android.gms.internal.fido.zzap.zzd(r7, r8, r5)
            byte r7 = r1[r5]
            if (r7 != r2) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r6 = r3
        L_0x0020:
            java.lang.String r7 = "Duplicate character: %s"
            com.google.android.gms.internal.fido.zzap.zzd(r6, r7, r5)
            byte r6 = (byte) r4
            r1[r5] = r6
            int r4 = r4 + 1
            goto L_0x000a
        L_0x002b:
            r9.<init>(r10, r11, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzcd.<init>(java.lang.String, char[]):void");
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzcd) {
            zzcd zzcd = (zzcd) obj;
            return this.zzh == zzcd.zzh && Arrays.equals(this.zzf, zzcd.zzf);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf) + (true != this.zzh ? 1237 : 1231);
    }

    public final String toString() {
        return this.zze;
    }

    public final char zza(int i3) {
        return this.zzf[i3];
    }

    public final zzcd zzb() {
        int i3;
        boolean z2;
        int i4 = 0;
        for (char zza2 : this.zzf) {
            if (zzad.zza(zza2)) {
                char[] cArr = this.zzf;
                int length = cArr.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        z2 = false;
                        break;
                    }
                    char c3 = cArr[i5];
                    if (c3 >= 'A' && c3 <= 'Z') {
                        z2 = true;
                        break;
                    }
                    i5++;
                }
                if (!z2) {
                    char[] cArr2 = new char[this.zzf.length];
                    while (true) {
                        char[] cArr3 = this.zzf;
                        if (i4 >= cArr3.length) {
                            break;
                        }
                        char c4 = cArr3[i4];
                        if (zzad.zza(c4)) {
                            c4 ^= ' ';
                        }
                        cArr2[i4] = (char) c4;
                        i4++;
                    }
                    zzcd zzcd = new zzcd(this.zze.concat(".upperCase()"), cArr2);
                    if (!this.zzh || zzcd.zzh) {
                        return zzcd;
                    }
                    byte[] bArr = zzcd.zzg;
                    byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                    for (i3 = 65; i3 <= 90; i3++) {
                        int i6 = i3 | 32;
                        byte[] bArr2 = zzcd.zzg;
                        byte b3 = bArr2[i3];
                        byte b4 = bArr2[i6];
                        if (b3 == -1) {
                            copyOf[i3] = b4;
                        } else {
                            char c5 = (char) i3;
                            char c6 = (char) i6;
                            if (b4 == -1) {
                                copyOf[i6] = b3;
                            } else {
                                throw new IllegalStateException(zzaq.zza("Can't ignoreCase() since '%s' and '%s' encode different values", Character.valueOf(c5), Character.valueOf(c6)));
                            }
                        }
                    }
                    return new zzcd(zzcd.zze.concat(".ignoreCase()"), zzcd.zzf, copyOf, true);
                }
                throw new IllegalStateException("Cannot call upperCase() on a mixed-case alphabet");
            }
        }
        return this;
    }

    public final boolean zzc(char c3) {
        byte[] bArr = this.zzg;
        return bArr.length > 61 && bArr[61] != -1;
    }

    private zzcd(String str, char[] cArr, byte[] bArr, boolean z2) {
        this.zze = str;
        cArr.getClass();
        this.zzf = cArr;
        try {
            int length = cArr.length;
            int zzb2 = zzcj.zzb(length, RoundingMode.UNNECESSARY);
            this.zzb = zzb2;
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(zzb2);
            int i3 = 1 << (3 - numberOfTrailingZeros);
            this.zzc = i3;
            this.zzd = zzb2 >> numberOfTrailingZeros;
            this.zza = length - 1;
            this.zzg = bArr;
            boolean[] zArr = new boolean[i3];
            for (int i4 = 0; i4 < this.zzd; i4++) {
                zArr[zzcj.zza(i4 * 8, this.zzb, RoundingMode.CEILING)] = true;
            }
            this.zzh = z2;
        } catch (ArithmeticException e3) {
            throw new IllegalArgumentException(a.k("Illegal alphabet length ", cArr.length), e3);
        }
    }
}
