package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import java.util.EnumMap;
import java.util.Map;
import org.objectweb.asm.signature.SignatureVisitor;

public final class zzjc {
    public static final zzjc zza = new zzjc((Boolean) null, (Boolean) null, 100);
    private final EnumMap<zza, zzjb> zzb;
    private final int zzc;

    public enum zza {
        AD_STORAGE("ad_storage"),
        ANALYTICS_STORAGE("analytics_storage"),
        AD_USER_DATA("ad_user_data"),
        AD_PERSONALIZATION("ad_personalization");
        
        public final String zze;

        private zza(String str) {
            this.zze = str;
        }
    }

    private zzjc(EnumMap<zza, zzjb> enumMap, int i3) {
        EnumMap<zza, zzjb> enumMap2 = new EnumMap<>(zza.class);
        this.zzb = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzc = i3;
    }

    public static boolean zza(int i3, int i4) {
        if (i3 == -20 && i4 == -30) {
            return true;
        }
        return (i3 == -30 && i4 == -20) || i3 == i4 || i3 < i4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjc)) {
            return false;
        }
        zzjc zzjc = (zzjc) obj;
        for (zza zza2 : zzjd.STORAGE.zzd) {
            if (this.zzb.get(zza2) != zzjc.zzb.get(zza2)) {
                return false;
            }
        }
        return this.zzc == zzjc.zzc;
    }

    public final int hashCode() {
        int i3 = this.zzc * 17;
        for (zzjb hashCode : this.zzb.values()) {
            i3 = (i3 * 31) + hashCode.hashCode();
        }
        return i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(zza(this.zzc));
        for (zza zza2 : zzjd.STORAGE.zzd) {
            sb.append(",");
            sb.append(zza2.zze);
            sb.append(StickyVariantProvider.KEY_VALUE_DELIMITER);
            zzjb zzjb = this.zzb.get(zza2);
            if (zzjb == null) {
                zzjb = zzjb.UNINITIALIZED;
            }
            sb.append(zzjb);
        }
        return sb.toString();
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry next : this.zzb.entrySet()) {
            String zzb2 = zzb((zzjb) next.getValue());
            if (zzb2 != null) {
                bundle.putString(((zza) next.getKey()).zze, zzb2);
            }
        }
        return bundle;
    }

    public final zzjb zzc() {
        zzjb zzjb = this.zzb.get(zza.AD_STORAGE);
        return zzjb == null ? zzjb.UNINITIALIZED : zzjb;
    }

    public final zzjb zzd() {
        zzjb zzjb = this.zzb.get(zza.ANALYTICS_STORAGE);
        return zzjb == null ? zzjb.UNINITIALIZED : zzjb;
    }

    public final Boolean zze() {
        zzjb zzjb = this.zzb.get(zza.AD_STORAGE);
        if (zzjb == null) {
            return null;
        }
        int ordinal = zzjb.ordinal();
        if (ordinal != 1) {
            if (ordinal == 2) {
                return Boolean.FALSE;
            }
            if (ordinal != 3) {
                return null;
            }
        }
        return Boolean.TRUE;
    }

    public final Boolean zzf() {
        zzjb zzjb = this.zzb.get(zza.ANALYTICS_STORAGE);
        if (zzjb == null) {
            return null;
        }
        int ordinal = zzjb.ordinal();
        if (ordinal != 1) {
            if (ordinal == 2) {
                return Boolean.FALSE;
            }
            if (ordinal != 3) {
                return null;
            }
        }
        return Boolean.TRUE;
    }

    public final String zzg() {
        int ordinal;
        StringBuilder sb = new StringBuilder("G1");
        for (zza zza2 : zzjd.STORAGE.zza()) {
            zzjb zzjb = this.zzb.get(zza2);
            char c3 = SignatureVisitor.SUPER;
            if (!(zzjb == null || (ordinal = zzjb.ordinal()) == 0)) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        c3 = '0';
                    } else if (ordinal != 3) {
                    }
                }
                c3 = '1';
            }
            sb.append(c3);
        }
        return sb.toString();
    }

    public final String zzh() {
        StringBuilder sb = new StringBuilder("G1");
        for (zza zza2 : zzjd.STORAGE.zza()) {
            sb.append(zza(this.zzb.get(zza2)));
        }
        return sb.toString();
    }

    public final boolean zzi() {
        return zza(zza.AD_STORAGE);
    }

    public final boolean zzj() {
        return zza(zza.ANALYTICS_STORAGE);
    }

    public final boolean zzk() {
        for (zzjb zzjb : this.zzb.values()) {
            if (zzjb != zzjb.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public static char zza(zzjb zzjb) {
        if (zzjb == null) {
            return SignatureVisitor.SUPER;
        }
        int ordinal = zzjb.ordinal();
        if (ordinal == 1) {
            return SignatureVisitor.EXTENDS;
        }
        if (ordinal == 2) {
            return '0';
        }
        if (ordinal != 3) {
            return SignatureVisitor.SUPER;
        }
        return '1';
    }

    public final int zza() {
        return this.zzc;
    }

    public final boolean zzc(zzjc zzjc) {
        return zzb(zzjc, (zza[]) this.zzb.keySet().toArray(new zza[0]));
    }

    public static zzjb zza(String str) {
        if (str == null) {
            return zzjb.UNINITIALIZED;
        }
        if (str.equals("granted")) {
            return zzjb.GRANTED;
        }
        if (str.equals("denied")) {
            return zzjb.DENIED;
        }
        return zzjb.UNINITIALIZED;
    }

    public zzjc(Boolean bool, Boolean bool2, int i3) {
        EnumMap<zza, zzjb> enumMap = new EnumMap<>(zza.class);
        this.zzb = enumMap;
        enumMap.put(zza.AD_STORAGE, zza(bool));
        enumMap.put(zza.ANALYTICS_STORAGE, zza(bool2));
        this.zzc = i3;
    }

    public static zzjc zzb(String str) {
        return zza(str, 100);
    }

    public final zzjc zzb(zzjc zzjc) {
        EnumMap enumMap = new EnumMap(zza.class);
        for (zza zza2 : zzjd.STORAGE.zzd) {
            zzjb zzjb = this.zzb.get(zza2);
            if (zzjb == zzjb.UNINITIALIZED) {
                zzjb = zzjc.zzb.get(zza2);
            }
            if (zzjb != null) {
                enumMap.put(zza2, zzjb);
            }
        }
        return new zzjc(enumMap, this.zzc);
    }

    public static zzjb zza(char c3) {
        if (c3 == '+') {
            return zzjb.POLICY;
        }
        if (c3 == '0') {
            return zzjb.DENIED;
        }
        if (c3 != '1') {
            return zzjb.UNINITIALIZED;
        }
        return zzjb.GRANTED;
    }

    public static String zzb(zzjb zzjb) {
        int ordinal = zzjb.ordinal();
        if (ordinal == 2) {
            return "denied";
        }
        if (ordinal != 3) {
            return null;
        }
        return "granted";
    }

    public static zzjb zza(Boolean bool) {
        if (bool == null) {
            return zzjb.UNINITIALIZED;
        }
        if (bool.booleanValue()) {
            return zzjb.GRANTED;
        }
        return zzjb.DENIED;
    }

    public final boolean zzb(zzjc zzjc, zza... zzaArr) {
        for (zza zza2 : zzaArr) {
            zzjb zzjb = this.zzb.get(zza2);
            zzjb zzjb2 = zzjc.zzb.get(zza2);
            zzjb zzjb3 = zzjb.DENIED;
            if (zzjb == zzjb3 && zzjb2 != zzjb3) {
                return true;
            }
        }
        return false;
    }

    public static zzjc zza(Bundle bundle, int i3) {
        if (bundle == null) {
            return new zzjc((Boolean) null, (Boolean) null, i3);
        }
        EnumMap enumMap = new EnumMap(zza.class);
        for (zza zza2 : zzjd.STORAGE.zzd) {
            enumMap.put(zza2, zza(bundle.getString(zza2.zze)));
        }
        return new zzjc(enumMap, i3);
    }

    public static zzjc zza(zzjb zzjb, zzjb zzjb2, int i3) {
        EnumMap enumMap = new EnumMap(zza.class);
        enumMap.put(zza.AD_STORAGE, zzjb);
        enumMap.put(zza.ANALYTICS_STORAGE, zzjb2);
        return new zzjc(enumMap, -10);
    }

    public static zzjc zza(String str, int i3) {
        EnumMap enumMap = new EnumMap(zza.class);
        if (str == null) {
            str = "";
        }
        zza[] zza2 = zzjd.STORAGE.zza();
        for (int i4 = 0; i4 < zza2.length; i4++) {
            zza zza3 = zza2[i4];
            int i5 = i4 + 2;
            if (i5 < str.length()) {
                enumMap.put(zza3, zza(str.charAt(i5)));
            } else {
                enumMap.put(zza3, zzjb.UNINITIALIZED);
            }
        }
        return new zzjc(enumMap, i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzjc zza(com.google.android.gms.measurement.internal.zzjc r9) {
        /*
            r8 = this;
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<com.google.android.gms.measurement.internal.zzjc$zza> r1 = com.google.android.gms.measurement.internal.zzjc.zza.class
            r0.<init>(r1)
            com.google.android.gms.measurement.internal.zzjd r1 = com.google.android.gms.measurement.internal.zzjd.STORAGE
            com.google.android.gms.measurement.internal.zzjc$zza[] r1 = r1.zzd
            int r2 = r1.length
            r3 = 0
        L_0x000f:
            if (r3 >= r2) goto L_0x004d
            r4 = r1[r3]
            java.util.EnumMap<com.google.android.gms.measurement.internal.zzjc$zza, com.google.android.gms.measurement.internal.zzjb> r5 = r8.zzb
            java.lang.Object r5 = r5.get(r4)
            com.google.android.gms.measurement.internal.zzjb r5 = (com.google.android.gms.measurement.internal.zzjb) r5
            java.util.EnumMap<com.google.android.gms.measurement.internal.zzjc$zza, com.google.android.gms.measurement.internal.zzjb> r6 = r9.zzb
            java.lang.Object r6 = r6.get(r4)
            com.google.android.gms.measurement.internal.zzjb r6 = (com.google.android.gms.measurement.internal.zzjb) r6
            if (r5 != 0) goto L_0x0026
            goto L_0x0035
        L_0x0026:
            if (r6 != 0) goto L_0x0029
            goto L_0x0045
        L_0x0029:
            com.google.android.gms.measurement.internal.zzjb r7 = com.google.android.gms.measurement.internal.zzjb.UNINITIALIZED
            if (r5 != r7) goto L_0x002e
            goto L_0x0035
        L_0x002e:
            if (r6 != r7) goto L_0x0031
            goto L_0x0045
        L_0x0031:
            com.google.android.gms.measurement.internal.zzjb r7 = com.google.android.gms.measurement.internal.zzjb.POLICY
            if (r5 != r7) goto L_0x0037
        L_0x0035:
            r5 = r6
            goto L_0x0045
        L_0x0037:
            if (r6 != r7) goto L_0x003a
            goto L_0x0045
        L_0x003a:
            com.google.android.gms.measurement.internal.zzjb r7 = com.google.android.gms.measurement.internal.zzjb.DENIED
            if (r5 == r7) goto L_0x0044
            if (r6 != r7) goto L_0x0041
            goto L_0x0044
        L_0x0041:
            com.google.android.gms.measurement.internal.zzjb r5 = com.google.android.gms.measurement.internal.zzjb.GRANTED
            goto L_0x0045
        L_0x0044:
            r5 = r7
        L_0x0045:
            if (r5 == 0) goto L_0x004a
            r0.put(r4, r5)
        L_0x004a:
            int r3 = r3 + 1
            goto L_0x000f
        L_0x004d:
            com.google.android.gms.measurement.internal.zzjc r8 = new com.google.android.gms.measurement.internal.zzjc
            r9 = 100
            r8.<init>(r0, r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjc.zza(com.google.android.gms.measurement.internal.zzjc):com.google.android.gms.measurement.internal.zzjc");
    }

    public static String zza(int i3) {
        if (i3 == -30) {
            return "TCF";
        }
        if (i3 == -20) {
            return "API";
        }
        if (i3 == -10) {
            return "MANIFEST";
        }
        if (i3 == 0) {
            return "1P_API";
        }
        if (i3 == 30) {
            return "1P_INIT";
        }
        if (i3 == 90) {
            return "REMOTE_CONFIG";
        }
        if (i3 != 100) {
            return "OTHER";
        }
        return "UNKNOWN";
    }

    public static String zza(Bundle bundle) {
        String string;
        zza[] zza2 = zzjd.STORAGE.zzd;
        int length = zza2.length;
        int i3 = 0;
        while (true) {
            Boolean bool = null;
            if (i3 >= length) {
                return null;
            }
            zza zza3 = zza2[i3];
            if (bundle.containsKey(zza3.zze) && (string = bundle.getString(zza3.zze)) != null) {
                if (string.equals("granted")) {
                    bool = Boolean.TRUE;
                } else if (string.equals("denied")) {
                    bool = Boolean.FALSE;
                }
                if (bool == null) {
                    return string;
                }
            }
            i3++;
        }
    }

    public final boolean zza(zzjc zzjc, zza... zzaArr) {
        for (zza zza2 : zzaArr) {
            if (!zzjc.zza(zza2) && zza(zza2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean zza(zza zza2) {
        return this.zzb.get(zza2) != zzjb.DENIED;
    }
}
