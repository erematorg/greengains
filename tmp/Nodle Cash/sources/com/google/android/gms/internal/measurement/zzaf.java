package com.google.android.gms.internal.measurement;

import A.a;
import com.adjust.sdk.Constants;
import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class zzaf implements zzak, zzaq, Iterable<zzaq> {
    @VisibleForTesting
    private final SortedMap<Integer, zzaq> zza;
    @VisibleForTesting
    private final Map<String, zzaq> zzb;

    public zzaf() {
        this.zza = new TreeMap();
        this.zzb = new TreeMap();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaf)) {
            return false;
        }
        zzaf zzaf = (zzaf) obj;
        if (zzb() != zzaf.zzb()) {
            return false;
        }
        if (this.zza.isEmpty()) {
            return zzaf.zza.isEmpty();
        }
        for (int intValue = this.zza.firstKey().intValue(); intValue <= this.zza.lastKey().intValue(); intValue++) {
            if (!zza(intValue).equals(zzaf.zza(intValue))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return this.zza.hashCode() * 31;
    }

    public final Iterator<zzaq> iterator() {
        return new zzah(this);
    }

    public final String toString() {
        return zzb(",");
    }

    public final int zza() {
        return this.zza.size();
    }

    public final int zzb() {
        if (this.zza.isEmpty()) {
            return 0;
        }
        return this.zza.lastKey().intValue() + 1;
    }

    public final zzaq zzc() {
        zzaf zzaf = new zzaf();
        for (Map.Entry next : this.zza.entrySet()) {
            if (next.getValue() instanceof zzak) {
                zzaf.zza.put((Integer) next.getKey(), (zzaq) next.getValue());
            } else {
                zzaf.zza.put((Integer) next.getKey(), ((zzaq) next.getValue()).zzc());
            }
        }
        return zzaf;
    }

    public final Boolean zzd() {
        return Boolean.TRUE;
    }

    public final Double zze() {
        return this.zza.size() == 1 ? zza(0).zze() : this.zza.size() <= 0 ? Double.valueOf(0.0d) : Double.valueOf(Double.NaN);
    }

    public final String zzf() {
        return toString();
    }

    public final Iterator<Integer> zzg() {
        return this.zza.keySet().iterator();
    }

    public final Iterator<zzaq> zzh() {
        return new zzae(this, this.zza.keySet().iterator(), this.zzb.keySet().iterator());
    }

    public final List<zzaq> zzi() {
        ArrayList arrayList = new ArrayList(zzb());
        for (int i3 = 0; i3 < zzb(); i3++) {
            arrayList.add(zza(i3));
        }
        return arrayList;
    }

    public final void zzj() {
        this.zza.clear();
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        if ("concat".equals(str) || "every".equals(str) || "filter".equals(str) || "forEach".equals(str) || "indexOf".equals(str) || "join".equals(str) || "lastIndexOf".equals(str) || "map".equals(str) || "pop".equals(str) || Constants.PUSH.equals(str) || "reduce".equals(str) || "reduceRight".equals(str) || "reverse".equals(str) || "shift".equals(str) || "slice".equals(str) || "some".equals(str) || "sort".equals(str) || "splice".equals(str) || "toString".equals(str) || "unshift".equals(str)) {
            return zzbe.zza(str, this, zzh, list);
        }
        return zzan.zza(this, new zzas(str), zzh, list);
    }

    public final String zzb(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        if (!this.zza.isEmpty()) {
            for (int i3 = 0; i3 < zzb(); i3++) {
                zzaq zza2 = zza(i3);
                sb.append(str);
                if (!(zza2 instanceof zzax) && !(zza2 instanceof zzao)) {
                    sb.append(zza2.zzf());
                }
            }
            sb.delete(0, str.length());
        }
        return sb.toString();
    }

    public zzaf(List<zzaq> list) {
        this();
        if (list != null) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzb(i3, list.get(i3));
            }
        }
    }

    public final boolean zzc(int i3) {
        if (i3 >= 0 && i3 <= this.zza.lastKey().intValue()) {
            return this.zza.containsKey(Integer.valueOf(i3));
        }
        throw new IndexOutOfBoundsException(a.k("Out of bounds index: ", i3));
    }

    public zzaf(zzaq... zzaqArr) {
        this((List<zzaq>) Arrays.asList(zzaqArr));
    }

    public final void zzb(int i3) {
        int intValue = this.zza.lastKey().intValue();
        if (i3 <= intValue && i3 >= 0) {
            this.zza.remove(Integer.valueOf(i3));
            if (i3 == intValue) {
                int i4 = i3 - 1;
                if (!this.zza.containsKey(Integer.valueOf(i4)) && i4 >= 0) {
                    this.zza.put(Integer.valueOf(i4), zzaq.zzc);
                    return;
                }
                return;
            }
            while (true) {
                i3++;
                if (i3 <= this.zza.lastKey().intValue()) {
                    zzaq zzaq = this.zza.get(Integer.valueOf(i3));
                    if (zzaq != null) {
                        this.zza.put(Integer.valueOf(i3 - 1), zzaq);
                        this.zza.remove(Integer.valueOf(i3));
                    }
                } else {
                    return;
                }
            }
        }
    }

    public final boolean zzc(String str) {
        return "length".equals(str) || this.zzb.containsKey(str);
    }

    @RequiresNonNull({"elements"})
    public final void zzb(int i3, zzaq zzaq) {
        if (i3 > 32468) {
            throw new IllegalStateException("Array too large");
        } else if (i3 < 0) {
            throw new IndexOutOfBoundsException(a.k("Out of bounds index: ", i3));
        } else if (zzaq == null) {
            this.zza.remove(Integer.valueOf(i3));
        } else {
            this.zza.put(Integer.valueOf(i3), zzaq);
        }
    }

    public final zzaq zza(int i3) {
        zzaq zzaq;
        if (i3 >= zzb()) {
            throw new IndexOutOfBoundsException("Attempting to get element outside of current array");
        } else if (!zzc(i3) || (zzaq = this.zza.get(Integer.valueOf(i3))) == null) {
            return zzaq.zzc;
        } else {
            return zzaq;
        }
    }

    public final zzaq zza(String str) {
        zzaq zzaq;
        if ("length".equals(str)) {
            return new zzai(Double.valueOf((double) zzb()));
        }
        if (!zzc(str) || (zzaq = this.zzb.get(str)) == null) {
            return zzaq.zzc;
        }
        return zzaq;
    }

    public final void zza(zzaq zzaq) {
        zzb(zzb(), zzaq);
    }

    public final void zza(int i3, zzaq zzaq) {
        if (i3 < 0) {
            throw new IllegalArgumentException(a.k("Invalid value index: ", i3));
        } else if (i3 >= zzb()) {
            zzb(i3, zzaq);
        } else {
            for (int intValue = this.zza.lastKey().intValue(); intValue >= i3; intValue--) {
                zzaq zzaq2 = this.zza.get(Integer.valueOf(intValue));
                if (zzaq2 != null) {
                    zzb(intValue + 1, zzaq2);
                    this.zza.remove(Integer.valueOf(intValue));
                }
            }
            zzb(i3, zzaq);
        }
    }

    public final void zza(String str, zzaq zzaq) {
        if (zzaq == null) {
            this.zzb.remove(str);
        } else {
            this.zzb.put(str, zzaq);
        }
    }
}
