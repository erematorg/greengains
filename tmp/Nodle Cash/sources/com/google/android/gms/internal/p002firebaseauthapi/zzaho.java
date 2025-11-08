package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaho  reason: invalid package */
public abstract class zzaho implements Serializable, Iterable<Byte> {
    public static final zzaho zza = new zzaib(zzajh.zzb);
    private static final zzahv zzb = new zzaia();
    private static final Comparator<zzaho> zzc = new zzahq();
    private int zzd = 0;

    public static /* synthetic */ int zza(byte b3) {
        return b3 & 255;
    }

    public static zzaho zzb(byte[] bArr) {
        return new zzaib(bArr);
    }

    public static zzahx zzc(int i3) {
        return new zzahx(i3);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i3 = this.zzd;
        if (i3 == 0) {
            int zzb2 = zzb();
            i3 = zzb(zzb2, 0, zzb2);
            if (i3 == 0) {
                i3 = 1;
            }
            this.zzd = i3;
        }
        return i3;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzahr(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        return a.n(android.support.v4.media.session.a.x("<ByteString@", Integer.toHexString(System.identityHashCode(this)), " size=", zzb(), " contents=\""), zzb() <= 50 ? zzaly.zza(this) : android.support.v4.media.session.a.m(zzaly.zza(zza(0, 47)), "..."), "\">");
    }

    public abstract byte zza(int i3);

    public abstract zzaho zza(int i3, int i4);

    public abstract String zza(Charset charset);

    public abstract void zza(zzahp zzahp) throws IOException;

    public abstract void zza(byte[] bArr, int i3, int i4, int i5);

    public abstract byte zzb(int i3);

    public abstract int zzb();

    public abstract int zzb(int i3, int i4, int i5);

    public abstract zzaic zzc();

    public final String zzd() {
        return zzb() == 0 ? "" : zza(zzajh.zza);
    }

    public abstract boolean zze();

    public final byte[] zzf() {
        int zzb2 = zzb();
        if (zzb2 == 0) {
            return zzajh.zzb;
        }
        byte[] bArr = new byte[zzb2];
        zza(bArr, 0, 0, zzb2);
        return bArr;
    }

    public static int zza(int i3, int i4, int i5) {
        int i6 = i4 - i3;
        if ((i3 | i4 | i6 | (i5 - i4)) >= 0) {
            return i6;
        }
        if (i3 < 0) {
            throw new IndexOutOfBoundsException(C0118y.c(i3, "Beginning index: ", " < 0"));
        } else if (i4 < i3) {
            throw new IndexOutOfBoundsException(androidx.compose.animation.core.a.r("Beginning index larger than ending index: ", i3, ", ", i4));
        } else {
            throw new IndexOutOfBoundsException(androidx.compose.animation.core.a.r("End index: ", i4, " >= ", i5));
        }
    }

    public final int zza() {
        return this.zzd;
    }

    public static zzaho zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzaho zza(byte[] bArr, int i3, int i4) {
        zza(i3, i3 + i4, bArr.length);
        return new zzaib(zzb.zza(bArr, i3, i4));
    }

    public static zzaho zza(String str) {
        return new zzaib(str.getBytes(zzajh.zza));
    }
}
