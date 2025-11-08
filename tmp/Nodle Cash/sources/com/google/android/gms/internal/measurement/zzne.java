package com.google.android.gms.internal.measurement;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzne<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    /* access modifiers changed from: private */
    public Object[] zza;
    /* access modifiers changed from: private */
    public int zzb;
    /* access modifiers changed from: private */
    public Map<K, V> zzc;
    private boolean zzd;
    private volatile zznp zze;
    /* access modifiers changed from: private */
    public Map<K, V> zzf;

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
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
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
            this.zze = new zznp(this);
        }
        return this.zze;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzne)) {
            return super.equals(obj);
        }
        zzne zzne = (zzne) obj;
        int size = size();
        if (size != zzne.size()) {
            return false;
        }
        int i3 = this.zzb;
        if (i3 != zzne.zzb) {
            return entrySet().equals(zzne.entrySet());
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!zza(i4).equals(zzne.zza(i4))) {
                return false;
            }
        }
        if (i3 != size) {
            return this.zzc.equals(zzne.zzc);
        }
        return true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        return zza2 >= 0 ? ((zznk) this.zza[zza2]).getValue() : this.zzc.get(comparable);
    }

    public int hashCode() {
        int i3 = this.zzb;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 += this.zza[i5].hashCode();
        }
        return this.zzc.size() > 0 ? i4 + this.zzc.hashCode() : i4;
    }

    public V remove(Object obj) {
        zzg();
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        if (zza2 >= 0) {
            return zzb(zza2);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    public int size() {
        return this.zzc.size() + this.zzb;
    }

    private zzne() {
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    public final Iterable<Map.Entry<K, V>> zzb() {
        if (this.zzc.isEmpty()) {
            return Collections.emptySet();
        }
        return this.zzc.entrySet();
    }

    public final Set<Map.Entry<K, V>> zzc() {
        return new zzni(this);
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

    public final boolean zze() {
        return this.zzd;
    }

    private final int zza(K k2) {
        int i3;
        int i4 = this.zzb;
        int i5 = i4 - 1;
        if (i5 >= 0) {
            int compareTo = k2.compareTo((Comparable) ((zznk) this.zza[i5]).getKey());
            if (compareTo > 0) {
                i3 = i4 + 1;
                return -i3;
            } else if (compareTo == 0) {
                return i5;
            }
        }
        int i6 = 0;
        while (i6 <= i5) {
            int i7 = (i6 + i5) / 2;
            int compareTo2 = k2.compareTo((Comparable) ((zznk) this.zza[i7]).getKey());
            if (compareTo2 < 0) {
                i5 = i7 - 1;
            } else if (compareTo2 <= 0) {
                return i7;
            } else {
                i6 = i7 + 1;
            }
        }
        i3 = i6 + 1;
        return -i3;
    }

    /* access modifiers changed from: private */
    public final V zzb(int i3) {
        zzg();
        V value = ((zznk) this.zza[i3]).getValue();
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i3 + 1, objArr, i3, (this.zzb - i3) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator it = zzf().entrySet().iterator();
            this.zza[this.zzb] = new zznk(this, (Map.Entry) it.next());
            this.zzb++;
            it.remove();
        }
        return value;
    }

    public final int zza() {
        return this.zzb;
    }

    /* renamed from: zza */
    public final V put(K k2, V v2) {
        zzg();
        int zza2 = zza(k2);
        if (zza2 >= 0) {
            return ((zznk) this.zza[zza2]).setValue(v2);
        }
        zzg();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i3 = -(zza2 + 1);
        if (i3 >= 16) {
            return zzf().put(k2, v2);
        }
        int i4 = this.zzb;
        if (i4 == 16) {
            zznk zznk = (zznk) this.zza[15];
            this.zzb = i4 - 1;
            zzf().put((Comparable) zznk.getKey(), zznk.getValue());
        }
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i3, objArr, i3 + 1, (objArr.length - i3) - 1);
        this.zza[i3] = new zznk(this, k2, v2);
        this.zzb++;
        return null;
    }

    public final Map.Entry<K, V> zza(int i3) {
        if (i3 < this.zzb) {
            return (zznk) this.zza[i3];
        }
        throw new ArrayIndexOutOfBoundsException(i3);
    }
}
