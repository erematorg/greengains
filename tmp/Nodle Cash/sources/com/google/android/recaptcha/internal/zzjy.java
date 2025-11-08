package com.google.android.recaptcha.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class zzjy extends LinkedHashMap {
    private static final zzjy zza;
    private boolean zzb = true;

    static {
        zzjy zzjy = new zzjy();
        zza = zzjy;
        zzjy.zzb = false;
    }

    private zzjy() {
    }

    public static zzjy zza() {
        return zza;
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = zzjc.zzd;
            int length = bArr.length;
            int zzb2 = zzjc.zzb(length, bArr, 0, length);
            if (zzb2 == 0) {
                return 1;
            }
            return zzb2;
        } else if (!(obj instanceof zziv)) {
            return obj.hashCode();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzg();
        super.clear();
    }

    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        boolean z2;
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                z2 = value.equals(obj2);
                continue;
            } else {
                z2 = Arrays.equals((byte[]) value, (byte[]) obj2);
                continue;
            }
            if (!z2) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i3 = 0;
        for (Map.Entry entry : entrySet()) {
            i3 += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return i3;
    }

    public final Object put(Object obj, Object obj2) {
        zzg();
        byte[] bArr = zzjc.zzd;
        obj.getClass();
        obj2.getClass();
        return super.put(obj, obj2);
    }

    public final void putAll(Map map) {
        zzg();
        for (Object next : map.keySet()) {
            byte[] bArr = zzjc.zzd;
            next.getClass();
            map.get(next).getClass();
        }
        super.putAll(map);
    }

    public final Object remove(Object obj) {
        zzg();
        return super.remove(obj);
    }

    public final zzjy zzb() {
        return isEmpty() ? new zzjy() : new zzjy(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzjy zzjy) {
        zzg();
        if (!zzjy.isEmpty()) {
            putAll(zzjy);
        }
    }

    public final boolean zze() {
        return this.zzb;
    }

    private zzjy(Map map) {
        super(map);
    }
}
