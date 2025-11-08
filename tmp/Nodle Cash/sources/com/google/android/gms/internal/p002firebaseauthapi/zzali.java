package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzali  reason: invalid package */
class zzali<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zza;
    /* access modifiers changed from: private */
    public List<zzalt> zzb;
    /* access modifiers changed from: private */
    public Map<K, V> zzc;
    private boolean zzd;
    private volatile zzalu zze;
    /* access modifiers changed from: private */
    public Map<K, V> zzf;
    private volatile zzalm zzg;

    private final SortedMap<K, V> zzf() {
        zzg();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    public void clear() {
        zzg();
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zze == null) {
            this.zze = new zzalu(this);
        }
        return this.zze;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzali)) {
            return super.equals(obj);
        }
        zzali zzali = (zzali) obj;
        int size = size();
        if (size != zzali.size()) {
            return false;
        }
        int zza2 = zza();
        if (zza2 != zzali.zza()) {
            return entrySet().equals(zzali.entrySet());
        }
        for (int i3 = 0; i3 < zza2; i3++) {
            if (!zzb(i3).equals(zzali.zzb(i3))) {
                return false;
            }
        }
        if (zza2 != size) {
            return this.zzc.equals(zzali.zzc);
        }
        return true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        return zza2 >= 0 ? this.zzb.get(zza2).getValue() : this.zzc.get(comparable);
    }

    public int hashCode() {
        int zza2 = zza();
        int i3 = 0;
        for (int i4 = 0; i4 < zza2; i4++) {
            i3 += this.zzb.get(i4).hashCode();
        }
        return this.zzc.size() > 0 ? i3 + this.zzc.hashCode() : i3;
    }

    public V remove(Object obj) {
        zzg();
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        if (zza2 >= 0) {
            return zzc(zza2);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    public int size() {
        return this.zzc.size() + this.zzb.size();
    }

    public final boolean zze() {
        return this.zzd;
    }

    private zzali(int i3) {
        this.zza = i3;
        this.zzb = Collections.emptyList();
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    public final V zzc(int i3) {
        zzg();
        V value = this.zzb.remove(i3).getValue();
        if (!this.zzc.isEmpty()) {
            Iterator it = zzf().entrySet().iterator();
            this.zzb.add(new zzalt(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    public final Iterable<Map.Entry<K, V>> zzb() {
        if (this.zzc.isEmpty()) {
            return zzalo.zza();
        }
        return this.zzc.entrySet();
    }

    public void zzd() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzd) {
            if (this.zzc.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzc);
            }
            this.zzc = map;
            if (this.zzf.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzf);
            }
            this.zzf = map2;
            this.zzd = true;
        }
    }

    private final int zza(K k2) {
        int i3;
        int size = this.zzb.size();
        int i4 = size - 1;
        if (i4 >= 0) {
            int compareTo = k2.compareTo((Comparable) this.zzb.get(i4).getKey());
            if (compareTo > 0) {
                i3 = size + 1;
                return -i3;
            } else if (compareTo == 0) {
                return i4;
            }
        }
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) / 2;
            int compareTo2 = k2.compareTo((Comparable) this.zzb.get(i6).getKey());
            if (compareTo2 < 0) {
                i4 = i6 - 1;
            } else if (compareTo2 <= 0) {
                return i6;
            } else {
                i5 = i6 + 1;
            }
        }
        i3 = i5 + 1;
        return -i3;
    }

    public final Map.Entry<K, V> zzb(int i3) {
        return this.zzb.get(i3);
    }

    public final int zza() {
        return this.zzb.size();
    }

    public static <FieldDescriptorType extends zzaiz<FieldDescriptorType>> zzali<FieldDescriptorType, Object> zza(int i3) {
        return new zzall(i3);
    }

    public final Set<Map.Entry<K, V>> zzc() {
        if (this.zzg == null) {
            this.zzg = new zzalm(this);
        }
        return this.zzg;
    }

    /* renamed from: zza */
    public final V put(K k2, V v2) {
        zzg();
        int zza2 = zza(k2);
        if (zza2 >= 0) {
            return this.zzb.get(zza2).setValue(v2);
        }
        zzg();
        if (this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        int i3 = -(zza2 + 1);
        if (i3 >= this.zza) {
            return zzf().put(k2, v2);
        }
        int size = this.zzb.size();
        int i4 = this.zza;
        if (size == i4) {
            zzalt remove = this.zzb.remove(i4 - 1);
            zzf().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzb.add(i3, new zzalt(this, k2, v2));
        return null;
    }
}
