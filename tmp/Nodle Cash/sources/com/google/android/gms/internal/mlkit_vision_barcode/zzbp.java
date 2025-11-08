package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.List;
import java.util.ListIterator;

final class zzbp extends zzbn implements ListIterator {
    final /* synthetic */ zzbq zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbp(zzbq zzbq) {
        super(zzbq);
        this.zzd = zzbq;
    }

    public final void add(Object obj) {
        boolean isEmpty = this.zzd.isEmpty();
        zza();
        ((ListIterator) this.zza).add(obj);
        zzbr zzbr = this.zzd.zzf;
        zzbr.zzb = zzbr.zzb + 1;
        if (isEmpty) {
            this.zzd.zza();
        }
    }

    public final boolean hasPrevious() {
        zza();
        return ((ListIterator) this.zza).hasPrevious();
    }

    public final int nextIndex() {
        zza();
        return ((ListIterator) this.zza).nextIndex();
    }

    public final Object previous() {
        zza();
        return ((ListIterator) this.zza).previous();
    }

    public final int previousIndex() {
        zza();
        return ((ListIterator) this.zza).previousIndex();
    }

    public final void set(Object obj) {
        zza();
        ((ListIterator) this.zza).set(obj);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbp(zzbq zzbq, int i3) {
        super(zzbq, ((List) zzbq.zzb).listIterator(i3));
        this.zzd = zzbq;
    }
}
