package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.msgpack.core.MessagePack;

public final class zzdo extends zzdr {
    private final int zza;
    private final zzbg zzb;

    public zzdo(zzbg zzbg) throws zzdh {
        zzbg.getClass();
        this.zzb = zzbg;
        zzcb zzd = zzbg.entrySet().iterator();
        int i3 = 0;
        while (zzd.hasNext()) {
            Map.Entry entry = (Map.Entry) zzd.next();
            int zzb2 = ((zzdr) entry.getKey()).zzb();
            i3 = i3 < zzb2 ? zzb2 : i3;
            int zzb3 = ((zzdr) entry.getValue()).zzb();
            if (i3 < zzb3) {
                i3 = zzb3;
            }
        }
        int i4 = i3 + 1;
        this.zza = i4;
        if (i4 > 4) {
            throw new zzdh("Exceeded cutoff limit for max depth of cbor value");
        }
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        int compareTo;
        zzdr zzdr = (zzdr) obj;
        if (zzdr.zzd(MessagePack.Code.FIXSTR_PREFIX) != zzdr.zza()) {
            return zzdr.zzd(MessagePack.Code.FIXSTR_PREFIX) - zzdr.zza();
        }
        zzdo zzdo = (zzdo) zzdr;
        if (this.zzb.size() != zzdo.zzb.size()) {
            return this.zzb.size() - zzdo.zzb.size();
        }
        zzcb zzd = this.zzb.entrySet().iterator();
        zzcb zzd2 = zzdo.zzb.entrySet().iterator();
        do {
            if (!zzd.hasNext() && !zzd2.hasNext()) {
                return 0;
            }
            Map.Entry entry = (Map.Entry) zzd.next();
            Map.Entry entry2 = (Map.Entry) zzd2.next();
            int compareTo2 = ((zzdr) entry.getKey()).compareTo((zzdr) entry2.getKey());
            if (compareTo2 != 0) {
                return compareTo2;
            }
            compareTo = ((zzdr) entry.getValue()).compareTo((zzdr) entry2.getValue());
        } while (compareTo == 0);
        return compareTo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && zzdo.class == obj.getClass()) {
            return this.zzb.equals(((zzdo) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzdr.zzd(MessagePack.Code.FIXSTR_PREFIX)), this.zzb});
    }

    public final String toString() {
        if (this.zzb.isEmpty()) {
            return "{}";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        zzcb zzd = this.zzb.entrySet().iterator();
        while (zzd.hasNext()) {
            Map.Entry entry = (Map.Entry) zzd.next();
            linkedHashMap.put(((zzdr) entry.getKey()).toString().replace("\n", "\n  "), ((zzdr) entry.getValue()).toString().replace("\n", "\n  "));
        }
        zzag zza2 = zzag.zza(",\n  ");
        StringBuilder sb = new StringBuilder("{\n  ");
        try {
            zzaf.zza(sb, linkedHashMap.entrySet().iterator(), zza2, " : ");
            sb.append("\n}");
            return sb.toString();
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }

    public final int zza() {
        return zzdr.zzd(MessagePack.Code.FIXSTR_PREFIX);
    }

    public final int zzb() {
        return this.zza;
    }

    public final zzbg zzc() {
        return this.zzb;
    }
}
