package com.google.android.gms.internal.measurement;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

public abstract class zzjs implements Serializable, Iterable<Byte> {
    public static final zzjs zza = new zzkb(zzle.zzb);
    private static final zzjv zzb = new zzke();
    private int zzc = 0;

    static {
        new zzju();
    }

    public static /* synthetic */ int zza(byte b3) {
        return b3 & 255;
    }

    public static zzjs zzb(byte[] bArr) {
        return new zzkb(bArr);
    }

    public static zzjx zzc(int i3) {
        return new zzjx(i3);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i3 = this.zzc;
        if (i3 == 0) {
            int zzb2 = zzb();
            i3 = zzb(zzb2, 0, zzb2);
            if (i3 == 0) {
                i3 = 1;
            }
            this.zzc = i3;
        }
        return i3;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzjr(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        return a.n(android.support.v4.media.session.a.x("<ByteString@", Integer.toHexString(System.identityHashCode(this)), " size=", zzb(), " contents=\""), zzb() <= 50 ? zznt.zza(this) : android.support.v4.media.session.a.m(zznt.zza(zza(0, 47)), "..."), "\">");
    }

    public abstract byte zza(int i3);

    public abstract zzjs zza(int i3, int i4);

    public abstract void zza(zzjp zzjp) throws IOException;

    public abstract byte zzb(int i3);

    public abstract int zzb();

    public abstract int zzb(int i3, int i4, int i5);

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
        return this.zzc;
    }

    public static zzjs zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzjs zza(byte[] bArr, int i3, int i4) {
        zza(i3, i3 + i4, bArr.length);
        return new zzkb(zzb.zza(bArr, i3, i4));
    }

    public static zzjs zza(String str) {
        return new zzkb(str.getBytes(zzle.zza));
    }
}
