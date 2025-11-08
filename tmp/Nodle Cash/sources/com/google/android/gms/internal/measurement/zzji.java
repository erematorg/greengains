package com.google.android.gms.internal.measurement;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.internal.measurement.zzjh;
import com.google.android.gms.internal.measurement.zzji;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzji<MessageType extends zzji<MessageType, BuilderType>, BuilderType extends zzjh<MessageType, BuilderType>> implements zzml {
    protected int zza = 0;

    public int zza(zznd zznd) {
        int zzby = zzby();
        if (zzby != -1) {
            return zzby;
        }
        int zza2 = zznd.zza(this);
        zzc(zza2);
        return zza2;
    }

    public int zzby() {
        throw new UnsupportedOperationException();
    }

    public final zzjs zzbz() {
        try {
            zzjx zzc = zzjs.zzc(zzcb());
            zza(zzc.zzb());
            return zzc.zza();
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a ByteString threw an IOException (should never happen)."), e3);
        }
    }

    public void zzc(int i3) {
        throw new UnsupportedOperationException();
    }

    public final byte[] zzca() {
        try {
            byte[] bArr = new byte[zzcb()];
            zzkl zzb = zzkl.zzb(bArr);
            zza(zzb);
            zzb.zzb();
            return bArr;
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a byte array threw an IOException (should never happen)."), e3);
        }
    }

    public static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzle.zza(iterable);
        if (iterable instanceof zzls) {
            List<?> zza2 = ((zzls) iterable).zza();
            zzls zzls = (zzls) list;
            int size = list.size();
            for (Object next : zza2) {
                if (next == null) {
                    String c3 = C0118y.c(zzls.size() - size, "Element at index ", " is null.");
                    for (int size2 = zzls.size() - 1; size2 >= size; size2--) {
                        zzls.remove(size2);
                    }
                    throw new NullPointerException(c3);
                } else if (next instanceof zzjs) {
                    zzls.zza((zzjs) next);
                } else if (next instanceof byte[]) {
                    zzls.zza(zzjs.zza((byte[]) next));
                } else {
                    zzls.add((String) next);
                }
            }
        } else if (iterable instanceof zzmx) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
            }
            int size3 = list.size();
            for (T next2 : iterable) {
                if (next2 == null) {
                    String c4 = C0118y.c(list.size() - size3, "Element at index ", " is null.");
                    for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                        list.remove(size4);
                    }
                    throw new NullPointerException(c4);
                }
                list.add(next2);
            }
        }
    }
}
