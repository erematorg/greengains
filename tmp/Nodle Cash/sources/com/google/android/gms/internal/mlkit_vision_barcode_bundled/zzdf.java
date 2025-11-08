package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.slf4j.Marker;

public abstract class zzdf implements Iterable, Serializable {
    public static final zzdf zzb = new zzde(zzep.zzb);
    private int zza = 0;

    static {
        int i3 = zzct.zza;
    }

    private static zzdf zzc(Iterator it, int i3) {
        if (i3 <= 0) {
            throw new IllegalArgumentException(C0118y.c(i3, "length (", ") must be >= 1"));
        } else if (i3 == 1) {
            return (zzdf) it.next();
        } else {
            int i4 = i3 >>> 1;
            zzdf zzc = zzc(it, i4);
            zzdf zzc2 = zzc(it, i3 - i4);
            if (Integer.MAX_VALUE - zzc.zzd() >= zzc2.zzd()) {
                return zzgd.zzy(zzc, zzc2);
            }
            throw new IllegalArgumentException(a.r("ByteString would be too long: ", zzc.zzd(), Marker.ANY_NON_NULL_MARKER, zzc2.zzd()));
        }
    }

    public static int zzo(int i3, int i4, int i5) {
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

    public static zzdf zzr(byte[] bArr, int i3, int i4) {
        zzo(i3, i3 + i4, bArr.length);
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i3, bArr2, 0, i4);
        return new zzde(bArr2);
    }

    public static zzdf zzs(InputStream inputStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i3 = 256;
        while (true) {
            byte[] bArr = new byte[i3];
            int i4 = 0;
            while (i4 < i3) {
                int read = inputStream.read(bArr, i4, i3 - i4);
                if (read == -1) {
                    break;
                }
                i4 += read;
            }
            zzdf zzr = i4 == 0 ? null : zzr(bArr, 0, i4);
            if (zzr == null) {
                break;
            }
            arrayList.add(zzr);
            i3 = Math.min(i3 + i3, 8192);
        }
        int size = arrayList.size();
        return size == 0 ? zzb : zzc(arrayList.iterator(), size);
    }

    public static void zzu(int i3, int i4) {
        if (((i4 - (i3 + 1)) | i3) >= 0) {
            return;
        }
        if (i3 < 0) {
            throw new ArrayIndexOutOfBoundsException(A.a.k("Index < 0: ", i3));
        }
        throw new ArrayIndexOutOfBoundsException(a.r("Index > length: ", i3, ", ", i4));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i3 = this.zza;
        if (i3 == 0) {
            int zzd = zzd();
            i3 = zzi(zzd, 0, zzd);
            if (i3 == 0) {
                i3 = 1;
            }
            this.zza = i3;
        }
        return i3;
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        return A.a.n(android.support.v4.media.session.a.x("<ByteString@", Integer.toHexString(System.identityHashCode(this)), " size=", zzd(), " contents=\""), zzd() <= 50 ? zzgq.zza(this) : zzgq.zza(zzk(0, 47)).concat("..."), "\">");
    }

    public abstract byte zza(int i3);

    public abstract byte zzb(int i3);

    public abstract int zzd();

    public abstract void zze(byte[] bArr, int i3, int i4, int i5);

    public abstract int zzf();

    public abstract boolean zzh();

    public abstract int zzi(int i3, int i4, int i5);

    public abstract int zzj(int i3, int i4, int i5);

    public abstract zzdf zzk(int i3, int i4);

    public abstract String zzl(Charset charset);

    public abstract void zzm(zzcx zzcx) throws IOException;

    public abstract boolean zzn();

    public final int zzp() {
        return this.zza;
    }

    /* renamed from: zzq */
    public zzdb iterator() {
        return new zzcy(this);
    }

    public final String zzt() {
        return zzd() == 0 ? "" : zzl(zzep.zza);
    }

    @Deprecated
    public final void zzv(byte[] bArr, int i3, int i4, int i5) {
        zzo(0, i5, zzd());
        zzo(i4, i4 + i5, bArr.length);
        if (i5 > 0) {
            zze(bArr, 0, i4, i5);
        }
    }

    public final byte[] zzw() {
        int zzd = zzd();
        if (zzd == 0) {
            return zzep.zzb;
        }
        byte[] bArr = new byte[zzd];
        zze(bArr, 0, 0, zzd);
        return bArr;
    }
}
