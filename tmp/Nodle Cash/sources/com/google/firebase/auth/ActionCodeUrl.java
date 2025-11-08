package com.google.firebase.auth;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzau;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.HashMap;
import java.util.Set;

public class ActionCodeUrl {
    private static final zzau<String, Integer> zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    @Nullable
    private final String zze;
    @Nullable
    private final String zzf;
    @Nullable
    private final String zzg;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("recoverEmail", 2);
        hashMap.put("resetPassword", 0);
        hashMap.put("signIn", 4);
        hashMap.put("verifyEmail", 1);
        hashMap.put("verifyBeforeChangeEmail", 5);
        hashMap.put("revertSecondFactorAddition", 6);
        zza = zzau.zza(hashMap);
    }

    private ActionCodeUrl(String str) {
        String zza2 = zza(str, "apiKey");
        String zza3 = zza(str, "oobCode");
        String zza4 = zza(str, "mode");
        if (zza2 == null || zza3 == null || zza4 == null) {
            throw new IllegalArgumentException("apiKey, oobCode and mode are required in a valid action code URL");
        }
        this.zzb = Preconditions.checkNotEmpty(zza2);
        this.zzc = Preconditions.checkNotEmpty(zza3);
        this.zzd = Preconditions.checkNotEmpty(zza4);
        this.zze = zza(str, "continueUrl");
        this.zzf = zza(str, RemoteConfigConstants.RequestFieldKey.LANGUAGE_CODE);
        this.zzg = zza(str, "tenantId");
    }

    @Nullable
    public static ActionCodeUrl parseLink(@Nullable String str) {
        Preconditions.checkNotEmpty(str);
        try {
            return new ActionCodeUrl(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @NonNull
    public String getApiKey() {
        return this.zzb;
    }

    @Nullable
    public String getCode() {
        return this.zzc;
    }

    @Nullable
    public String getContinueUrl() {
        return this.zze;
    }

    @Nullable
    public String getLanguageCode() {
        return this.zzf;
    }

    public int getOperation() {
        zzau<String, Integer> zzau = zza;
        if (zzau.containsKey(this.zzd)) {
            return zzau.get(this.zzd).intValue();
        }
        return 3;
    }

    @Nullable
    public final String zza() {
        return this.zzg;
    }

    @Nullable
    private static String zza(String str, String str2) {
        Uri parse = Uri.parse(str);
        try {
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            if (queryParameterNames.contains(str2)) {
                return parse.getQueryParameter(str2);
            }
            if (queryParameterNames.contains("link")) {
                return Uri.parse(Preconditions.checkNotEmpty(parse.getQueryParameter("link"))).getQueryParameter(str2);
            }
            return null;
        } catch (NullPointerException | UnsupportedOperationException unused) {
            return null;
        }
    }
}
