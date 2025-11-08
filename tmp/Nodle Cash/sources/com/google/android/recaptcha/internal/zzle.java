package com.google.android.recaptcha.internal;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzle extends AbstractMap {
    private final int zza;
    /* access modifiers changed from: private */
    public List zzb = Collections.emptyList();
    /* access modifiers changed from: private */
    public Map zzc = Collections.emptyMap();
    private boolean zzd;
    private volatile zzlc zze;
    private Map zzf = Collections.emptyMap();

    public /* synthetic */ zzle(int i3, zzld zzld) {
        this.zza = i3;
    }

    private final int zzk(Comparable comparable) {
        int size = this.zzb.size();
        int i3 = size - 1;
        int i4 = 0;
        if (i3 >= 0) {
            int compareTo = comparable.compareTo(((zzky) this.zzb.get(i3)).zza());
            if (compareTo > 0) {
                return -(size + 1);
            }
            if (compareTo == 0) {
                return i3;
            }
        }
        while (i4 <= i3) {
            int i5 = (i4 + i3) / 2;
            int compareTo2 = comparable.compareTo(((zzky) this.zzb.get(i5)).zza());
            if (compareTo2 < 0) {
                i3 = i5 - 1;
            } else if (compareTo2 <= 0) {
                return i5;
            } else {
                i4 = i5 + 1;
            }
        }
        return -(i4 + 1);
    }

    /* access modifiers changed from: private */
    public final Object zzl(int i3) {
        zzn();
        Object value = ((zzky) this.zzb.remove(i3)).getValue();
        if (!this.zzc.isEmpty()) {
            Iterator it = zzm().entrySet().iterator();
            List list = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new zzky(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return value;
    }

    private final SortedMap zzm() {
        zzn();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    /* access modifiers changed from: private */
    public final void zzn() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzn();
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzk(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    public final Set entrySet() {
        if (this.zze == null) {
            this.zze = new zzlc(this, (zzlb) null);
        }
        return this.zze;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzle)) {
            return super.equals(obj);
        }
        zzle zzle = (zzle) obj;
        int size = size();
        if (size != zzle.size()) {
            return false;
        }
        int zzb2 = zzb();
        if (zzb2 != zzle.zzb()) {
            return entrySet().equals(zzle.entrySet());
        }
        for (int i3 = 0; i3 < zzb2; i3++) {
            if (!zzg(i3).equals(zzle.zzg(i3))) {
                return false;
            }
        }
        if (zzb2 != size) {
            return this.zzc.equals(zzle.zzc);
        }
        return true;
    }

    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zzk = zzk(comparable);
        return zzk >= 0 ? ((zzky) this.zzb.get(zzk)).getValue() : this.zzc.get(comparable);
    }

    public final int hashCode() {
        int zzb2 = zzb();
        int i3 = 0;
        for (int i4 = 0; i4 < zzb2; i4++) {
            i3 += ((zzky) this.zzb.get(i4)).hashCode();
        }
        return this.zzc.size() > 0 ? this.zzc.hashCode() + i3 : i3;
    }

    public final Object remove(Object obj) {
        zzn();
        Comparable comparable = (Comparable) obj;
        int zzk = zzk(comparable);
        if (zzk >= 0) {
            return zzl(zzk);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    public final int size() {
        return this.zzc.size() + this.zzb.size();
    }

    public void zza() {
        if (!this.zzd) {
            this.zzc = this.zzc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzc);
            this.zzf = this.zzf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzf);
            this.zzd = true;
        }
    }

    public final int zzb() {
        return this.zzb.size();
    }

    public final Iterable zzc() {
        return this.zzc.isEmpty() ? zzkx.zza() : this.zzc.entrySet();
    }

    /* renamed from: zze */
    public final Object put(Comparable comparable, Object obj) {
        zzn();
        int zzk = zzk(comparable);
        if (zzk >= 0) {
            return ((zzky) this.zzb.get(zzk)).setValue(obj);
        }
        zzn();
        if (this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        int i3 = -(zzk + 1);
        if (i3 >= this.zza) {
            return zzm().put(comparable, obj);
        }
        int size = this.zzb.size();
        int i4 = this.zza;
        if (size == i4) {
            zzky zzky = (zzky) this.zzb.remove(i4 - 1);
            zzm().put(zzky.zza(), zzky.getValue());
        }
        this.zzb.add(i3, new zzky(this, comparable, obj));
        return null;
    }

    public final Map.Entry zzg(int i3) {
        return (Map.Entry) this.zzb.get(i3);
    }

    public final boolean zzj() {
        return this.zzd;
    }
}
