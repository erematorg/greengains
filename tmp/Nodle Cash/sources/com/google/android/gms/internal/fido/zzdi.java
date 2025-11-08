package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;

public final class zzdi extends zzdr {
    private final zzaz zza;
    private final int zzb;

    public zzdi(zzaz zzaz) throws zzdh {
        zzaz.getClass();
        this.zza = zzaz;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            zzaz zzaz2 = this.zza;
            if (i3 >= zzaz2.size()) {
                break;
            }
            int zzb2 = ((zzdr) zzaz2.get(i3)).zzb();
            if (i4 < zzb2) {
                i4 = zzb2;
            }
            i3++;
        }
        int i5 = i4 + 1;
        this.zzb = i5;
        if (i5 > 4) {
            throw new zzdh("Exceeded cutoff limit for max depth of cbor value");
        }
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzdr zzdr = (zzdr) obj;
        if (zzdr.zzd(Byte.MIN_VALUE) != zzdr.zza()) {
            return zzdr.zzd(Byte.MIN_VALUE) - zzdr.zza();
        }
        zzdi zzdi = (zzdi) zzdr;
        zzaz zzaz = this.zza;
        int size = zzaz.size();
        zzaz zzaz2 = zzdi.zza;
        if (size != zzaz2.size()) {
            return zzaz.size() - zzaz2.size();
        }
        int i3 = 0;
        while (true) {
            zzaz zzaz3 = this.zza;
            if (i3 >= zzaz3.size()) {
                return 0;
            }
            int compareTo = ((zzdr) zzaz3.get(i3)).compareTo((zzdr) zzdi.zza.get(i3));
            if (compareTo != 0) {
                return compareTo;
            }
            i3++;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && zzdi.class == obj.getClass()) {
            return this.zza.equals(((zzdi) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzdr.zzd(Byte.MIN_VALUE)), this.zza});
    }

    public final String toString() {
        if (this.zza.isEmpty()) {
            return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
        ArrayList arrayList = new ArrayList();
        zzaz zzaz = this.zza;
        int size = zzaz.size();
        for (int i3 = 0; i3 < size; i3++) {
            arrayList.add(((zzdr) zzaz.get(i3)).toString().replace("\n", "\n  "));
        }
        zzag zza2 = zzag.zza(",\n  ");
        StringBuilder sb = new StringBuilder("[\n  ");
        try {
            zza2.zzb(sb, arrayList.iterator());
            sb.append("\n]");
            return sb.toString();
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }

    public final int zza() {
        return zzdr.zzd(Byte.MIN_VALUE);
    }

    public final int zzb() {
        return this.zzb;
    }
}
