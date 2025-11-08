package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzjc;
import com.squareup.moshi.Json;
import java.util.HashMap;
import java.util.Map;
import org.apache.xerces.impl.xs.SchemaSymbols;

public final class zzni {
    private static final String[] zza = {"GoogleConsent", "gdprApplies", "EnableAdvertiserConsentMode", "PolicyVersion", "PurposeConsents", "CmpSdkID"};
    private final Map<String, String> zzb;

    private zzni(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        this.zzb = hashMap;
        hashMap.putAll(map);
    }

    private static int zza(SharedPreferences sharedPreferences, String str) {
        try {
            return sharedPreferences.getInt(str, -1);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    private static String zzb(SharedPreferences sharedPreferences, String str) {
        try {
            return sharedPreferences.getString(str, Json.UNSET_NAME);
        } catch (ClassCastException unused) {
            return Json.UNSET_NAME;
        }
    }

    private final int zzd() {
        try {
            String str = this.zzb.get("CmpSdkID");
            if (!TextUtils.isEmpty(str)) {
                return Integer.parseInt(str);
            }
            return -1;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private final int zze() {
        try {
            String str = this.zzb.get("PolicyVersion");
            if (!TextUtils.isEmpty(str)) {
                return Integer.parseInt(str);
            }
            return -1;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzni)) {
            return false;
        }
        return zzc().equalsIgnoreCase(((zzni) obj).zzc());
    }

    public final int hashCode() {
        return zzc().hashCode();
    }

    public final String toString() {
        return zzc();
    }

    public final String zzc() {
        StringBuilder sb = new StringBuilder();
        for (String str : zza) {
            if (this.zzb.containsKey(str)) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(str);
                sb.append(StickyVariantProvider.KEY_VALUE_DELIMITER);
                sb.append(this.zzb.get(str));
            }
        }
        return sb.toString();
    }

    public final Bundle zza() {
        String str;
        String str2;
        if (!"1".equals(this.zzb.get("GoogleConsent")) || !"1".equals(this.zzb.get("gdprApplies")) || !"1".equals(this.zzb.get("EnableAdvertiserConsentMode"))) {
            return Bundle.EMPTY;
        }
        int zze = zze();
        if (zze < 0) {
            return Bundle.EMPTY;
        }
        String str3 = this.zzb.get("PurposeConsents");
        if (TextUtils.isEmpty(str3)) {
            return Bundle.EMPTY;
        }
        Bundle bundle = new Bundle();
        String str4 = "denied";
        if (str3.length() > 0) {
            String str5 = zzjc.zza.AD_STORAGE.zze;
            if (str3.charAt(0) == '1') {
                str2 = "granted";
            } else {
                str2 = str4;
            }
            bundle.putString(str5, str2);
        }
        if (str3.length() > 3) {
            String str6 = zzjc.zza.AD_PERSONALIZATION.zze;
            if (str3.charAt(2) == '1' && str3.charAt(3) == '1') {
                str = "granted";
            } else {
                str = str4;
            }
            bundle.putString(str6, str);
        }
        if (str3.length() > 6 && zze >= 4) {
            String str7 = zzjc.zza.AD_USER_DATA.zze;
            if (str3.charAt(0) == '1' && str3.charAt(6) == '1') {
                str4 = "granted";
            }
            bundle.putString(str7, str4);
        }
        return bundle;
    }

    public final String zzb() {
        StringBuilder sb = new StringBuilder("1");
        int zzd = zzd();
        if (zzd < 0 || zzd > 4095) {
            sb.append("00");
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt((zzd >> 6) & 63));
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(zzd & 63));
        }
        int zze = zze();
        if (zze < 0 || zze > 63) {
            sb.append(SchemaSymbols.ATTVAL_FALSE_0);
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(zze));
        }
        Preconditions.checkArgument(true);
        int i3 = "1".equals(this.zzb.get("gdprApplies")) ? 2 : 0;
        int i4 = i3 | 4;
        if ("1".equals(this.zzb.get("EnableAdvertiserConsentMode"))) {
            i4 = i3 | 12;
        }
        sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i4));
        return sb.toString();
    }

    public static zzni zza(SharedPreferences sharedPreferences) {
        HashMap hashMap = new HashMap();
        String zzb2 = zzb(sharedPreferences, "IABTCF_VendorConsents");
        if (!Json.UNSET_NAME.equals(zzb2) && zzb2.length() > 754) {
            hashMap.put("GoogleConsent", String.valueOf(zzb2.charAt(754)));
        }
        int zza2 = zza(sharedPreferences, "IABTCF_gdprApplies");
        if (zza2 != -1) {
            hashMap.put("gdprApplies", String.valueOf(zza2));
        }
        int zza3 = zza(sharedPreferences, "IABTCF_EnableAdvertiserConsentMode");
        if (zza3 != -1) {
            hashMap.put("EnableAdvertiserConsentMode", String.valueOf(zza3));
        }
        int zza4 = zza(sharedPreferences, "IABTCF_PolicyVersion");
        if (zza4 != -1) {
            hashMap.put("PolicyVersion", String.valueOf(zza4));
        }
        String zzb3 = zzb(sharedPreferences, "IABTCF_PurposeConsents");
        if (!Json.UNSET_NAME.equals(zzb3)) {
            hashMap.put("PurposeConsents", zzb3);
        }
        int zza5 = zza(sharedPreferences, "IABTCF_CmpSdkID");
        if (zza5 != -1) {
            hashMap.put("CmpSdkID", String.valueOf(zza5));
        }
        return new zzni(hashMap);
    }

    public static String zza(String str, boolean z2) {
        if (!z2 || str.length() <= 4) {
            return str;
        }
        char[] charArray = str.toCharArray();
        int i3 = 1;
        while (true) {
            if (i3 >= 64) {
                i3 = 0;
                break;
            } else if (charArray[4] == "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i3)) {
                break;
            } else {
                i3++;
            }
        }
        charArray[4] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(1 | i3);
        return String.valueOf(charArray);
    }
}
