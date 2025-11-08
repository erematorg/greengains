package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.android.gms.measurement.internal.zzjc;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class zzaz {
    private static final zzaz zza = new zzaz((Boolean) null, 100);
    private final int zzb;
    private final String zzc;
    private final Boolean zzd;
    private final String zze;
    private final EnumMap<zzjc.zza, zzjb> zzf;

    public zzaz(Boolean bool, int i3) {
        this(bool, i3, (Boolean) null, (String) null);
    }

    private final String zzh() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        for (zzjc.zza zza2 : zzjd.DMA.zza()) {
            sb.append(":");
            sb.append(zzjc.zza(this.zzf.get(zza2)));
        }
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzaz)) {
            return false;
        }
        zzaz zzaz = (zzaz) obj;
        if (this.zzc.equalsIgnoreCase(zzaz.zzc) && Objects.equals(this.zzd, zzaz.zzd)) {
            return Objects.equals(this.zze, zzaz.zze);
        }
        return false;
    }

    public final int hashCode() {
        Boolean bool = this.zzd;
        int i3 = bool == null ? 3 : bool == Boolean.TRUE ? 7 : 13;
        String str = this.zze;
        return ((str == null ? 17 : str.hashCode()) * 137) + (i3 * 29) + this.zzc.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(zzjc.zza(this.zzb));
        for (zzjc.zza zza2 : zzjd.DMA.zza()) {
            sb.append(",");
            sb.append(zza2.zze);
            sb.append(StickyVariantProvider.KEY_VALUE_DELIMITER);
            zzjb zzjb = this.zzf.get(zza2);
            if (zzjb == null) {
                sb.append("uninitialized");
            } else {
                int i3 = zzay.zza[zzjb.ordinal()];
                if (i3 == 1) {
                    sb.append("uninitialized");
                } else if (i3 == 2) {
                    sb.append("eu_consent_policy");
                } else if (i3 == 3) {
                    sb.append("denied");
                } else if (i3 == 4) {
                    sb.append("granted");
                }
            }
        }
        if (this.zzd != null) {
            sb.append(",isDmaRegion=");
            sb.append(this.zzd);
        }
        if (this.zze != null) {
            sb.append(",cpsDisplayStr=");
            sb.append(this.zze);
        }
        return sb.toString();
    }

    public final int zza() {
        return this.zzb;
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry next : this.zzf.entrySet()) {
            String zzb2 = zzjc.zzb((zzjb) next.getValue());
            if (zzb2 != null) {
                bundle.putString(((zzjc.zza) next.getKey()).zze, zzb2);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            bundle.putString("is_dma_region", bool.toString());
        }
        String str = this.zze;
        if (str != null) {
            bundle.putString("cps_display_str", str);
        }
        return bundle;
    }

    public final zzjb zzc() {
        zzjb zzjb = this.zzf.get(zzjc.zza.AD_USER_DATA);
        return zzjb == null ? zzjb.UNINITIALIZED : zzjb;
    }

    public final Boolean zzd() {
        return this.zzd;
    }

    public final String zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzc;
    }

    public final boolean zzg() {
        for (zzjb zzjb : this.zzf.values()) {
            if (zzjb != zzjb.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public zzaz(Boolean bool, int i3, Boolean bool2, String str) {
        EnumMap<zzjc.zza, zzjb> enumMap = new EnumMap<>(zzjc.zza.class);
        this.zzf = enumMap;
        enumMap.put(zzjc.zza.AD_USER_DATA, zzjc.zza(bool));
        this.zzb = i3;
        this.zzc = zzh();
        this.zzd = bool2;
        this.zze = str;
    }

    public static zzaz zza(Bundle bundle, int i3) {
        Boolean bool = null;
        if (bundle == null) {
            return new zzaz((Boolean) null, i3);
        }
        EnumMap enumMap = new EnumMap(zzjc.zza.class);
        for (zzjc.zza zza2 : zzjd.DMA.zza()) {
            enumMap.put(zza2, zzjc.zza(bundle.getString(zza2.zze)));
        }
        if (bundle.containsKey("is_dma_region")) {
            bool = Boolean.valueOf(bundle.getString("is_dma_region"));
        }
        return new zzaz((EnumMap<zzjc.zza, zzjb>) enumMap, i3, bool, bundle.getString("cps_display_str"));
    }

    private zzaz(EnumMap<zzjc.zza, zzjb> enumMap, int i3, Boolean bool, String str) {
        EnumMap<zzjc.zza, zzjb> enumMap2 = new EnumMap<>(zzjc.zza.class);
        this.zzf = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzb = i3;
        this.zzc = zzh();
        this.zzd = bool;
        this.zze = str;
    }

    public static zzaz zza(zzjb zzjb, int i3) {
        EnumMap enumMap = new EnumMap(zzjc.zza.class);
        enumMap.put(zzjc.zza.AD_USER_DATA, zzjb);
        return new zzaz((EnumMap<zzjc.zza, zzjb>) enumMap, -10, (Boolean) null, (String) null);
    }

    public static zzaz zza(String str) {
        if (str == null || str.length() <= 0) {
            return zza;
        }
        String[] split = str.split(":");
        int parseInt = Integer.parseInt(split[0]);
        EnumMap enumMap = new EnumMap(zzjc.zza.class);
        zzjc.zza[] zza2 = zzjd.DMA.zza();
        int length = zza2.length;
        int i3 = 1;
        int i4 = 0;
        while (i4 < length) {
            enumMap.put(zza2[i4], zzjc.zza(split[i3].charAt(0)));
            i4++;
            i3++;
        }
        return new zzaz((EnumMap<zzjc.zza, zzjb>) enumMap, parseInt, (Boolean) null, (String) null);
    }

    public static Boolean zza(Bundle bundle) {
        zzjb zza2;
        if (bundle == null || (zza2 = zzjc.zza(bundle.getString("ad_personalization"))) == null) {
            return null;
        }
        int i3 = zzay.zza[zza2.ordinal()];
        if (i3 == 3) {
            return Boolean.FALSE;
        }
        if (i3 != 4) {
            return null;
        }
        return Boolean.TRUE;
    }
}
