package com.google.firebase.auth.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.p002firebaseauthapi.zzafn;
import com.google.android.gms.internal.p002firebaseauthapi.zzah;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Marker;

public final class zzbu {
    Map<String, Task<RecaptchaTasksClient>> zza;
    FirebaseApp zzb;
    zzbp zzc;
    /* access modifiers changed from: private */
    @Nullable
    public zzafn zzd;
    private FirebaseAuth zze;

    public zzbu(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth) {
        this(firebaseApp, firebaseAuth, new zzbs());
    }

    @Nullable
    private final Task<RecaptchaTasksClient> zzb(String str) {
        return this.zza.get(str);
    }

    private static String zzc(@Nullable String str) {
        return zzah.zzc(str) ? Marker.ANY_MARKER : str;
    }

    private zzbu(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth, zzbp zzbp) {
        this.zza = new HashMap();
        this.zzb = firebaseApp;
        this.zze = firebaseAuth;
        this.zzc = zzbp;
    }

    public final Task<String> zza(@Nullable String str, Boolean bool, RecaptchaAction recaptchaAction) {
        String zzc2 = zzc(str);
        Task<RecaptchaTasksClient> zzb2 = zzb(zzc2);
        if (bool.booleanValue() || zzb2 == null) {
            zzb2 = zza(zzc2, bool);
        }
        return zzb2.continueWithTask(new zzbw(this, recaptchaAction));
    }

    public final Task<RecaptchaTasksClient> zza(@Nullable String str, Boolean bool) {
        Task<RecaptchaTasksClient> zzb2;
        String zzc2 = zzc(str);
        if (bool.booleanValue() || (zzb2 = zzb(zzc2)) == null) {
            return this.zze.zza("RECAPTCHA_ENTERPRISE").continueWithTask(new zzbt(this, zzc2));
        }
        return zzb2;
    }

    public final boolean zza(String str) {
        zzafn zzafn = this.zzd;
        return zzafn != null && zzafn.zzb(str);
    }
}
