package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.Constants;
import com.reown.android.push.notifications.PushMessagingService;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaek  reason: invalid package */
public class zzaek implements zzacu<zzaek> {
    private static final String zza = "com.google.android.gms.internal.firebase-auth-api.zzaek";
    private String zzb;
    private int zzc;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzaek zza(@NonNull String str) throws zzaah {
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(str).getString(Constants.IPC_BUNDLE_KEY_SEND_ERROR));
            this.zzc = jSONObject.getInt("code");
            this.zzb = jSONObject.getString(PushMessagingService.KEY_MESSAGE);
            return this;
        } catch (NullPointerException | JSONException e3) {
            String str2 = zza;
            String message = e3.getMessage();
            Log.e(str2, "Failed to parse error for string [" + str + "] with exception: " + message);
            throw new zzaah(a.l("Failed to parse error for string [", str, "]"), e3);
        }
    }

    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return !TextUtils.isEmpty(this.zzb);
    }
}
