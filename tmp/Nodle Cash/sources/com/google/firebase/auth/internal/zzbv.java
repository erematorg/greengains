package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.browser.trusted.c;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p002firebaseauthapi.zzafm;
import com.google.android.gms.internal.p002firebaseauthapi.zzaft;
import com.google.android.gms.internal.p002firebaseauthapi.zzxy;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorGenerator;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbv {
    private Context zza;
    private String zzb;
    private SharedPreferences zzc = this.zza.getSharedPreferences(c.a("com.google.firebase.auth.api.Store.", this.zzb), 0);
    private Logger zzd = new Logger("StorageHelpers", new String[0]);

    public zzbv(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zza = context.getApplicationContext();
    }

    @Nullable
    private final String zzc(FirebaseUser firebaseUser) {
        boolean z2;
        JSONObject jSONObject = new JSONObject();
        if (!zzac.class.isAssignableFrom(firebaseUser.getClass())) {
            return null;
        }
        zzac zzac = (zzac) firebaseUser;
        try {
            jSONObject.put("cachedTokenState", zzac.zze());
            jSONObject.put("applicationName", zzac.zza().getName());
            jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
            if (zzac.zzj() != null) {
                JSONArray jSONArray = new JSONArray();
                List<zzy> zzj = zzac.zzj();
                int size = zzj.size();
                if (zzj.size() > 30) {
                    this.zzd.w("Provider user info list size larger than max size, truncating list to %d. Actual list size: %d", 30, Integer.valueOf(zzj.size()));
                    size = 30;
                }
                int i3 = 0;
                boolean z3 = false;
                while (true) {
                    z2 = true;
                    if (i3 >= size) {
                        break;
                    }
                    zzy zzy = zzj.get(i3);
                    if (zzy.getProviderId().equals("firebase")) {
                        z3 = true;
                    }
                    if (i3 == size - 1 && !z3) {
                        break;
                    }
                    jSONArray.put(zzy.zzb());
                    i3++;
                }
                if (!z3) {
                    int i4 = size - 1;
                    while (true) {
                        if (i4 >= zzj.size() || i4 < 0) {
                            z2 = z3;
                        } else {
                            zzy zzy2 = zzj.get(i4);
                            if (zzy2.getProviderId().equals("firebase")) {
                                jSONArray.put(zzy2.zzb());
                                break;
                            }
                            if (i4 == zzj.size() - 1) {
                                jSONArray.put(zzy2.zzb());
                            }
                            i4++;
                        }
                    }
                    z2 = z3;
                    if (!z2) {
                        this.zzd.w("Malformed user object! No Firebase Auth provider id found. Provider user info list size: %d, trimmed size: %d", Integer.valueOf(zzj.size()), Integer.valueOf(size));
                        if (zzj.size() < 5) {
                            StringBuilder sb = new StringBuilder("Provider user info list:\n");
                            for (zzy providerId : zzj) {
                                sb.append("Provider - " + providerId.getProviderId() + "\n");
                            }
                            this.zzd.w(sb.toString(), new Object[0]);
                        }
                    }
                }
                jSONObject.put("userInfos", jSONArray);
            }
            jSONObject.put("anonymous", zzac.isAnonymous());
            jSONObject.put("version", "2");
            if (zzac.getMetadata() != null) {
                jSONObject.put("userMetadata", ((zzae) zzac.getMetadata()).zza());
            }
            List<MultiFactorInfo> enrolledFactors = ((zzag) zzac.getMultiFactor()).getEnrolledFactors();
            if (enrolledFactors != null && !enrolledFactors.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i5 = 0; i5 < enrolledFactors.size(); i5++) {
                    jSONArray2.put(enrolledFactors.get(i5).toJson());
                }
                jSONObject.put("userMultiFactorInfo", jSONArray2);
            }
            List<zzaft> zzf = zzac.zzf();
            if (zzf != null && !zzf.isEmpty()) {
                JSONArray jSONArray3 = new JSONArray();
                for (int i6 = 0; i6 < zzf.size(); i6++) {
                    jSONArray3.put(zzaft.zza(zzf.get(i6)));
                }
                jSONObject.put("passkeyInfo", jSONArray3);
            }
            return jSONObject.toString();
        } catch (Exception e3) {
            this.zzd.wtf("Failed to turn object into JSON", e3, new Object[0]);
            throw new zzxy(e3);
        }
    }

    @Nullable
    public final FirebaseUser zza() {
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", (String) null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zza(jSONObject);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final void zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String zzc2 = zzc(firebaseUser);
        if (!TextUtils.isEmpty(zzc2)) {
            this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", zzc2).apply();
        }
    }

    @Nullable
    public final zzafm zza(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        SharedPreferences sharedPreferences = this.zzc;
        String uid = firebaseUser.getUid();
        String string = sharedPreferences.getString("com.google.firebase.auth.GET_TOKEN_RESPONSE." + uid, (String) null);
        if (string != null) {
            return zzafm.zzb(string);
        }
        return null;
    }

    @Nullable
    private final zzac zza(JSONObject jSONObject) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        Object obj;
        zzae zza2;
        try {
            String string = jSONObject.getString("cachedTokenState");
            String string2 = jSONObject.getString("applicationName");
            boolean z2 = jSONObject.getBoolean("anonymous");
            String str = "2";
            String string3 = jSONObject.getString("version");
            if (string3 != null) {
                str = string3;
            }
            JSONArray jSONArray3 = jSONObject.getJSONArray("userInfos");
            int length = jSONArray3.length();
            if (length == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i3 = 0; i3 < length; i3++) {
                arrayList.add(zzy.zza(jSONArray3.getString(i3)));
            }
            zzac zzac = new zzac(FirebaseApp.getInstance(string2), arrayList);
            if (!TextUtils.isEmpty(string)) {
                zzac.zza(zzafm.zzb(string));
            }
            if (!z2) {
                zzac.zzb();
            }
            zzac.zza(str);
            if (jSONObject.has("userMetadata") && (zza2 = zzae.zza(jSONObject.getJSONObject("userMetadata"))) != null) {
                zzac.zza(zza2);
            }
            if (jSONObject.has("userMultiFactorInfo") && (jSONArray2 = jSONObject.getJSONArray("userMultiFactorInfo")) != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                    JSONObject jSONObject2 = new JSONObject(jSONArray2.getString(i4));
                    String optString = jSONObject2.optString(MultiFactorInfo.FACTOR_ID_KEY);
                    if ("phone".equals(optString)) {
                        obj = PhoneMultiFactorInfo.zza(jSONObject2);
                    } else {
                        obj = Objects.equals(optString, TotpMultiFactorGenerator.FACTOR_ID) ? TotpMultiFactorInfo.zza(jSONObject2) : null;
                    }
                    arrayList2.add(obj);
                }
                zzac.zzc(arrayList2);
            }
            if (jSONObject.has("passkeyInfo") && (jSONArray = jSONObject.getJSONArray("passkeyInfo")) != null) {
                ArrayList arrayList3 = new ArrayList();
                for (int i5 = 0; i5 < jSONArray.length(); i5++) {
                    arrayList3.add(zzaft.zza(new JSONObject(jSONArray.getString(i5))));
                }
                zzac.zzb(arrayList3);
            }
            return zzac;
        } catch (zzxy | ArrayIndexOutOfBoundsException | IllegalArgumentException | JSONException e3) {
            this.zzd.wtf(e3);
            return null;
        }
    }

    public final void zza(String str) {
        this.zzc.edit().remove(str).apply();
    }

    public final void zza(FirebaseUser firebaseUser, zzafm zzafm) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzafm);
        this.zzc.edit().putString(c.a("com.google.firebase.auth.GET_TOKEN_RESPONSE.", firebaseUser.getUid()), zzafm.zzf()).apply();
    }
}
