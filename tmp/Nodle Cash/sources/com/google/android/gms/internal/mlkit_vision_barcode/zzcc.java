package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

final class zzcc extends AbstractSet {
    final /* synthetic */ zzci zza;

    public zzcc(zzci zzci) {
        this.zza = zzci;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int zzd = this.zza.zzw(entry.getKey());
            return zzd != -1 && zzax.zza(zzci.zzj(this.zza, zzd), entry.getValue());
        }
    }

    public final Iterator iterator() {
        zzci zzci = this.zza;
        Map zzl = zzci.zzl();
        return zzl != null ? zzl.entrySet().iterator() : new zzca(zzci);
    }

    public final boolean remove(@CheckForNull Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        zzci zzci = this.zza;
        if (zzci.zzr()) {
            return false;
        }
        int zzc = zzci.zzv();
        Object key = entry.getKey();
        Object value = entry.getValue();
        zzci zzci2 = this.zza;
        int zzb = zzcj.zzb(key, value, zzc, zzci.zzi(zzci2), zzci2.zzA(), zzci2.zzB(), zzci2.zzC());
        if (zzb == -1) {
            return false;
        }
        this.zza.zzq(zzb, zzc);
        zzci zzci3 = this.zza;
        zzci3.zzg = zzci3.zzg - 1;
        this.zza.zzo();
        return true;
    }

    public final int size() {
        return this.zza.size();
    }
}
