package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajs  reason: invalid package */
public final class zzajs extends zzahi<String> implements zzajv, RandomAccess {
    private static final zzajs zza;
    @Deprecated
    private static final zzajv zzb;
    private final List<Object> zzc;

    static {
        zzajs zzajs = new zzajs(false);
        zza = zzajs;
        zzb = zzajs;
    }

    public zzajs() {
        this(10);
    }

    public final /* synthetic */ void add(int i3, Object obj) {
        zza();
        this.zzc.add(i3, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i3) {
        Object obj = this.zzc.get(i3);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaho) {
            zzaho zzaho = (zzaho) obj;
            String zzd = zzaho.zzd();
            if (zzaho.zze()) {
                this.zzc.set(i3, zzd);
            }
            return zzd;
        }
        byte[] bArr = (byte[]) obj;
        String zzb2 = zzajh.zzb(bArr);
        if (zzajh.zzc(bArr)) {
            this.zzc.set(i3, zzb2);
        }
        return zzb2;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i3) {
        zza();
        Object remove = this.zzc.remove(i3);
        this.modCount++;
        return zza(remove);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i3, Object obj) {
        zza();
        return zza(this.zzc.set(i3, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final /* synthetic */ zzajl zza(int i3) {
        if (i3 >= size()) {
            ArrayList arrayList = new ArrayList(i3);
            arrayList.addAll(this.zzc);
            return new zzajs((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final Object zzb(int i3) {
        return this.zzc.get(i3);
    }

    public final /* bridge */ /* synthetic */ boolean zzc() {
        return super.zzc();
    }

    public final zzajv zzd() {
        return zzc() ? new zzamh(this) : this;
    }

    public final List<?> zze() {
        return Collections.unmodifiableList(this.zzc);
    }

    public zzajs(int i3) {
        this((ArrayList<Object>) new ArrayList(i3));
    }

    public final boolean addAll(int i3, Collection<? extends String> collection) {
        zza();
        if (collection instanceof zzajv) {
            collection = ((zzajv) collection).zze();
        }
        boolean addAll = this.zzc.addAll(i3, collection);
        this.modCount++;
        return addAll;
    }

    private zzajs(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    private zzajs(boolean z2) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    private static String zza(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaho) {
            return ((zzaho) obj).zzd();
        }
        return zzajh.zzb((byte[]) obj);
    }

    public final void zza(zzaho zzaho) {
        zza();
        this.zzc.add(zzaho);
        this.modCount++;
    }
}
