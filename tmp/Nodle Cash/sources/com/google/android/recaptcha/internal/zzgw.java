package com.google.android.recaptcha.internal;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

public abstract class zzgw implements Iterable, Serializable {
    private static final Comparator zza = new zzgo();
    public static final zzgw zzb = new zzgt(zzjc.zzd);
    private static final zzgv zzd = new zzgv((zzgu) null);
    private int zzc = 0;

    static {
        int i3 = zzgi.zza;
    }

    public static int zzk(int i3, int i4, int i5) {
        int i6 = i4 - i3;
        if ((i3 | i4 | i6 | (i5 - i4)) >= 0) {
            return i6;
        }
        if (i3 < 0) {
            throw new IndexOutOfBoundsException(C0118y.c(i3, "Beginning index: ", " < 0"));
        } else if (i4 < i3) {
            throw new IndexOutOfBoundsException(a.r("Beginning index larger than ending index: ", i3, ", ", i4));
        } else {
            throw new IndexOutOfBoundsException(a.r("End index: ", i4, " >= ", i5));
        }
    }

    public static zzgw zzm(byte[] bArr, int i3, int i4) {
        zzk(i3, i3 + i4, bArr.length);
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i3, bArr2, 0, i4);
        return new zzgt(bArr2);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i3 = this.zzc;
        if (i3 == 0) {
            int zzd2 = zzd();
            i3 = zzf(zzd2, 0, zzd2);
            if (i3 == 0) {
                i3 = 1;
            }
            this.zzc = i3;
        }
        return i3;
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzgn(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        return A.a.n(android.support.v4.media.session.a.x("<ByteString@", Integer.toHexString(System.identityHashCode(this)), " size=", zzd(), " contents=\""), zzd() <= 50 ? zzlg.zza(this) : zzlg.zza(zzg(0, 47)).concat("..."), "\">");
    }

    public abstract byte zza(int i3);

    public abstract byte zzb(int i3);

    public abstract int zzd();

    public abstract void zze(byte[] bArr, int i3, int i4, int i5);

    public abstract int zzf(int i3, int i4, int i5);

    public abstract zzgw zzg(int i3, int i4);

    public abstract String zzh(Charset charset);

    public abstract void zzi(zzgm zzgm) throws IOException;

    public abstract boolean zzj();

    public final int zzl() {
        return this.zzc;
    }

    public final String zzn(Charset charset) {
        return zzd() == 0 ? "" : zzh(charset);
    }

    public final byte[] zzo() {
        int zzd2 = zzd();
        if (zzd2 == 0) {
            return zzjc.zzd;
        }
        byte[] bArr = new byte[zzd2];
        zze(bArr, 0, 0, zzd2);
        return bArr;
    }
}
