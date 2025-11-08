package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzgo extends AbstractMap {
    /* access modifiers changed from: private */
    public Object[] zza;
    /* access modifiers changed from: private */
    public int zzb;
    /* access modifiers changed from: private */
    public Map zzc = Collections.emptyMap();
    private boolean zzd;
    private volatile zzgm zze;
    private Map zzf = Collections.emptyMap();

    private zzgo() {
    }

    private final int zzl(Comparable comparable) {
        int i3 = this.zzb;
        int i4 = i3 - 1;
        int i5 = 0;
        if (i4 >= 0) {
            int compareTo = comparable.compareTo(((zzgi) this.zza[i4]).zza());
            if (compareTo > 0) {
                return -(i3 + 1);
            }
            if (compareTo == 0) {
                return i4;
            }
        }
        while (i5 <= i4) {
            int i6 = (i5 + i4) / 2;
            int compareTo2 = comparable.compareTo(((zzgi) this.zza[i6]).zza());
            if (compareTo2 < 0) {
                i4 = i6 - 1;
            } else if (compareTo2 <= 0) {
                return i6;
            } else {
                i5 = i6 + 1;
            }
        }
        return -(i5 + 1);
    }

    /* access modifiers changed from: private */
    public final Object zzm(int i3) {
        zzo();
        Object value = ((zzgi) this.zza[i3]).getValue();
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i3 + 1, objArr, i3, (this.zzb - i3) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator it = zzn().entrySet().iterator();
            Object[] objArr2 = this.zza;
            int i4 = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            objArr2[i4] = new zzgi(this, (Comparable) entry.getKey(), entry.getValue());
            this.zzb++;
            it.remove();
        }
        return value;
    }

    private final SortedMap zzn() {
        zzo();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    /* access modifiers changed from: private */
    public final void zzo() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzo();
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzl(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    public final Set entrySet() {
        if (this.zze == null) {
            this.zze = new zzgm(this, (zzgl) null);
        }
        return this.zze;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgo)) {
            return super.equals(obj);
        }
        zzgo zzgo = (zzgo) obj;
        int size = size();
        if (size != zzgo.size()) {
            return false;
        }
        int i3 = this.zzb;
        if (i3 != zzgo.zzb) {
            return entrySet().equals(zzgo.entrySet());
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!zzg(i4).equals(zzgo.zzg(i4))) {
                return false;
            }
        }
        if (i3 != size) {
            return this.zzc.equals(zzgo.zzc);
        }
        return true;
    }

    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zzl = zzl(comparable);
        return zzl >= 0 ? ((zzgi) this.zza[zzl]).getValue() : this.zzc.get(comparable);
    }

    public final int hashCode() {
        int i3 = this.zzb;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 += this.zza[i5].hashCode();
        }
        return this.zzc.size() > 0 ? this.zzc.hashCode() + i4 : i4;
    }

    public final Object remove(Object obj) {
        zzo();
        Comparable comparable = (Comparable) obj;
        int zzl = zzl(comparable);
        if (zzl >= 0) {
            return zzm(zzl);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    public final int size() {
        return this.zzc.size() + this.zzb;
    }

    public void zza() {
        if (!this.zzd) {
            this.zzc = this.zzc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzc);
            this.zzf = this.zzf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzf);
            this.zzd = true;
        }
    }

    public final int zzc() {
        return this.zzb;
    }

    public final Iterable zzd() {
        return this.zzc.isEmpty() ? Collections.emptySet() : this.zzc.entrySet();
    }

    /* renamed from: zzf */
    public final Object put(Comparable comparable, Object obj) {
        zzo();
        int zzl = zzl(comparable);
        if (zzl >= 0) {
            return ((zzgi) this.zza[zzl]).setValue(obj);
        }
        zzo();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i3 = -(zzl + 1);
        if (i3 >= 16) {
            return zzn().put(comparable, obj);
        }
        if (this.zzb == 16) {
            zzgi zzgi = (zzgi) this.zza[15];
            this.zzb = 15;
            zzn().put(zzgi.zza(), zzgi.getValue());
        }
        Object[] objArr = this.zza;
        int length = objArr.length;
        System.arraycopy(objArr, i3, objArr, i3 + 1, 15 - i3);
        this.zza[i3] = new zzgi(this, comparable, obj);
        this.zzb++;
        return null;
    }

    public final Map.Entry zzg(int i3) {
        if (i3 < this.zzb) {
            return (zzgi) this.zza[i3];
        }
        throw new ArrayIndexOutOfBoundsException(i3);
    }

    public final boolean zzj() {
        return this.zzd;
    }

    public /* synthetic */ zzgo(zzgn zzgn) {
    }
}
