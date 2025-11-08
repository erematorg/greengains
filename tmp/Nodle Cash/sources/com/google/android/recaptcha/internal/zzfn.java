package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Iterator;
import javax.annotation.CheckForNull;

public abstract class zzfn extends zzfo implements Collection {
    public boolean add(Object obj) {
        return zzc().add(obj);
    }

    public boolean addAll(Collection collection) {
        return zzc().addAll(collection);
    }

    public final void clear() {
        zzc().clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return zzc().contains(obj);
    }

    public final boolean containsAll(Collection collection) {
        return zzc().containsAll(collection);
    }

    public final boolean isEmpty() {
        return zzc().isEmpty();
    }

    public final Iterator iterator() {
        return zzc().iterator();
    }

    public final boolean remove(@CheckForNull Object obj) {
        return zzc().remove(obj);
    }

    public final boolean removeAll(Collection collection) {
        return zzc().removeAll(collection);
    }

    public final boolean retainAll(Collection collection) {
        return zzc().retainAll(collection);
    }

    public final int size() {
        return zzc().size();
    }

    public final Object[] toArray() {
        return zzc().toArray();
    }

    public /* bridge */ /* synthetic */ Object zzb() {
        throw null;
    }

    public abstract Collection zzc();

    public final Object[] toArray(Object[] objArr) {
        return zzc().toArray(objArr);
    }
}
