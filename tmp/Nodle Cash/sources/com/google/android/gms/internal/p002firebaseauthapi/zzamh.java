package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamh  reason: invalid package */
public final class zzamh extends AbstractList<String> implements zzajv, RandomAccess {
    /* access modifiers changed from: private */
    public final zzajv zza;

    public zzamh(zzajv zzajv) {
        this.zza = zzajv;
    }

    public final /* synthetic */ Object get(int i3) {
        return (String) this.zza.get(i3);
    }

    public final Iterator<String> iterator() {
        return new zzamj(this);
    }

    public final ListIterator<String> listIterator(int i3) {
        return new zzamk(this, i3);
    }

    public final int size() {
        return this.zza.size();
    }

    public final Object zzb(int i3) {
        return this.zza.zzb(i3);
    }

    public final zzajv zzd() {
        return this;
    }

    public final List<?> zze() {
        return this.zza.zze();
    }

    public final void zza(zzaho zzaho) {
        throw new UnsupportedOperationException();
    }
}
