package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.zzjc;
import java.util.EnumMap;

final class zzai {
    private final EnumMap<zzjc.zza, zzal> zza;

    public zzai() {
        this.zza = new EnumMap<>(zzjc.zza.class);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("1");
        for (zzjc.zza zza2 : zzjc.zza.values()) {
            zzal zzal = this.zza.get(zza2);
            if (zzal == null) {
                zzal = zzal.UNSET;
            }
            sb.append(zzal.zzl);
        }
        return sb.toString();
    }

    public final zzal zza(zzjc.zza zza2) {
        zzal zzal = this.zza.get(zza2);
        return zzal == null ? zzal.UNSET : zzal;
    }

    private zzai(EnumMap<zzjc.zza, zzal> enumMap) {
        EnumMap<zzjc.zza, zzal> enumMap2 = new EnumMap<>(zzjc.zza.class);
        this.zza = enumMap2;
        enumMap2.putAll(enumMap);
    }

    public static zzai zza(String str) {
        EnumMap enumMap = new EnumMap(zzjc.zza.class);
        if (str.length() >= zzjc.zza.values().length) {
            int i3 = 0;
            if (str.charAt(0) == '1') {
                zzjc.zza[] values = zzjc.zza.values();
                int length = values.length;
                int i4 = 1;
                while (i3 < length) {
                    enumMap.put(values[i3], zzal.zza(str.charAt(i4)));
                    i3++;
                    i4++;
                }
                return new zzai(enumMap);
            }
        }
        return new zzai();
    }

    public final void zza(zzjc.zza zza2, int i3) {
        zzal zzal = zzal.UNSET;
        if (i3 != -30) {
            if (i3 != -20) {
                if (i3 == -10) {
                    zzal = zzal.MANIFEST;
                } else if (i3 != 0) {
                    if (i3 == 30) {
                        zzal = zzal.INITIALIZATION;
                    }
                }
            }
            zzal = zzal.API;
        } else {
            zzal = zzal.TCF;
        }
        this.zza.put(zza2, zzal);
    }

    public final void zza(zzjc.zza zza2, zzal zzal) {
        this.zza.put(zza2, zzal);
    }
}
