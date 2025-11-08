package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class zzmf<K, V> extends LinkedHashMap<K, V> {
    private static final zzmf<?, ?> zza;
    private boolean zzb = true;

    static {
        zzmf<?, ?> zzmf = new zzmf<>();
        zza = zzmf;
        zzmf.zzb = false;
    }

    private zzmf() {
    }

    private static int zza(Object obj) {
        if (obj instanceof byte[]) {
            return zzle.zza((byte[]) obj);
        }
        if (!(obj instanceof zzlh)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    private final void zze() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zze();
        super.clear();
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof java.util.Map
            r1 = 0
            if (r0 == 0) goto L_0x005d
            java.util.Map r6 = (java.util.Map) r6
            r0 = 1
            if (r5 == r6) goto L_0x0059
            int r2 = r5.size()
            int r3 = r6.size()
            if (r2 == r3) goto L_0x0016
        L_0x0014:
            r5 = r1
            goto L_0x005a
        L_0x0016:
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x001e:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L_0x0059
            java.lang.Object r2 = r5.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            boolean r3 = r6.containsKey(r3)
            if (r3 != 0) goto L_0x0035
            goto L_0x0014
        L_0x0035:
            java.lang.Object r3 = r2.getValue()
            java.lang.Object r2 = r2.getKey()
            java.lang.Object r2 = r6.get(r2)
            boolean r4 = r3 instanceof byte[]
            if (r4 == 0) goto L_0x0052
            boolean r4 = r2 instanceof byte[]
            if (r4 == 0) goto L_0x0052
            byte[] r3 = (byte[]) r3
            byte[] r2 = (byte[]) r2
            boolean r2 = java.util.Arrays.equals(r3, r2)
            goto L_0x0056
        L_0x0052:
            boolean r2 = r3.equals(r2)
        L_0x0056:
            if (r2 != 0) goto L_0x001e
            goto L_0x0014
        L_0x0059:
            r5 = r0
        L_0x005a:
            if (r5 == 0) goto L_0x005d
            return r0
        L_0x005d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzmf.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i3 = 0;
        for (Map.Entry entry : entrySet()) {
            i3 += zza(entry.getValue()) ^ zza(entry.getKey());
        }
        return i3;
    }

    public final V put(K k2, V v2) {
        zze();
        zzle.zza(k2);
        zzle.zza(v2);
        return super.put(k2, v2);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zze();
        for (Object next : map.keySet()) {
            zzle.zza(next);
            zzle.zza(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        zze();
        return super.remove(obj);
    }

    public final zzmf<K, V> zzb() {
        return isEmpty() ? new zzmf<>() : new zzmf<>(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final boolean zzd() {
        return this.zzb;
    }

    private zzmf(Map<K, V> map) {
        super(map);
    }

    public static <K, V> zzmf<K, V> zza() {
        return zza;
    }

    public final void zza(zzmf<K, V> zzmf) {
        zze();
        if (!zzmf.isEmpty()) {
            putAll(zzmf);
        }
    }
}
