package com.google.android.recaptcha.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
public final class zzlq extends AbstractList implements RandomAccess, zzjm {
    /* access modifiers changed from: private */
    public final zzjm zza;

    public zzlq(zzjm zzjm) {
        this.zza = zzjm;
    }

    public final /* bridge */ /* synthetic */ Object get(int i3) {
        return ((zzjl) this.zza).get(i3);
    }

    public final Iterator iterator() {
        return new zzlp(this);
    }

    public final ListIterator listIterator(int i3) {
        return new zzlo(this, i3);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzjm zze() {
        return this;
    }

    public final Object zzf(int i3) {
        return this.zza.zzf(i3);
    }

    public final List zzh() {
        return this.zza.zzh();
    }

    public final void zzi(zzgw zzgw) {
        throw new UnsupportedOperationException();
    }
}
