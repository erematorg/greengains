package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.state.b;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.internal.p002firebaseauthapi.zzack;
import com.google.android.gms.internal.p002firebaseauthapi.zzacl;
import com.google.android.gms.internal.p002firebaseauthapi.zzacm;
import com.google.android.gms.internal.p002firebaseauthapi.zzaec;
import com.google.android.gms.internal.p002firebaseauthapi.zzags;
import com.google.android.gms.internal.p002firebaseauthapi.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.inject.Provider;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

@KeepName
public class GenericIdpActivity extends FragmentActivity implements zzacm {
    private static long zzb;
    private static final zzcb zzc = zzcb.zzc();
    private boolean zzd = false;

    private final void zzb() {
        zzb = 0;
        this.zzd = false;
        Intent intent = new Intent();
        intent.putExtra("com.google.firebase.auth.internal.EXTRA_CANCELED", true);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!zza(intent)) {
            zzbi.zza((Context) this, zzal.zza("WEB_CONTEXT_CANCELED"));
        } else {
            zzc.zza((Context) this);
        }
        finish();
    }

    public void onCreate(@NonNull Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(action) || "android.intent.action.VIEW".equals(action)) {
            long currentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
            if (currentTimeMillis - zzb < 30000) {
                Log.e("GenericIdpActivity", "Could not start operation - already in progress");
                return;
            }
            zzb = currentTimeMillis;
            if (bundle != null) {
                this.zzd = bundle.getBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN");
                return;
            }
            return;
        }
        b.u("Could not do operation - unknown action: ", action, "GenericIdpActivity");
        zzb();
    }

    public void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public void onResume() {
        super.onResume();
        if ("android.intent.action.VIEW".equals(getIntent().getAction())) {
            Intent intent = getIntent();
            if (intent.hasExtra("firebaseError")) {
                zza(zzcc.zza(intent.getStringExtra("firebaseError")));
            } else if (!intent.hasExtra("link") || !intent.hasExtra("eventId")) {
                zzb();
            } else {
                String stringExtra = intent.getStringExtra("link");
                String stringExtra2 = intent.getStringExtra("eventId");
                String packageName = getPackageName();
                boolean booleanExtra = intent.getBooleanExtra("encryptionEnabled", true);
                zzo zza = zzl.zza().zza(this, packageName, stringExtra2);
                if (zza == null) {
                    zzb();
                }
                if (booleanExtra) {
                    stringExtra = zzn.zza(getApplicationContext(), FirebaseApp.getInstance(zza.zza()).getPersistenceKey()).zza(stringExtra);
                }
                zzags zzags = new zzags(zza, stringExtra);
                String zze = zza.zze();
                String zzb2 = zza.zzb();
                zzags.zzb(zze);
                if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(zzb2)) {
                    zzb = 0;
                    this.zzd = false;
                    Intent intent2 = new Intent();
                    SafeParcelableSerializer.serializeToIntentExtra(zzags, intent2, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST");
                    intent2.putExtra("com.google.firebase.auth.internal.OPERATION", zzb2);
                    intent2.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
                    if (!zza(intent2)) {
                        zzbi.zza(getApplicationContext(), zzags, zzb2, zze);
                    } else {
                        zzc.zza((Context) this);
                    }
                    finish();
                    return;
                }
                b.u("unsupported operation: ", zzb2, "GenericIdpActivity");
                zzb();
            }
        } else if (!this.zzd) {
            String packageName2 = getPackageName();
            try {
                String lowerCase = Hex.bytesToStringUppercase(AndroidUtilsLight.getPackageCertificateHashBytes(this, packageName2)).toLowerCase(Locale.US);
                FirebaseApp instance = FirebaseApp.getInstance(getIntent().getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME"));
                FirebaseAuth instance2 = FirebaseAuth.getInstance(instance);
                if (!zzaec.zza(instance)) {
                    new zzack(packageName2, lowerCase, getIntent(), instance, this).executeOnExecutor(instance2.zze(), new Void[0]);
                } else {
                    zza(zza(Uri.parse(zzaec.zza(instance.getOptions().getApiKey())).buildUpon(), getIntent(), packageName2, lowerCase).build(), packageName2, instance2.zzc());
                }
            } catch (PackageManager.NameNotFoundException e3) {
                String valueOf = String.valueOf(e3);
                Log.e("GenericIdpActivity", "Could not get package signature: " + packageName2 + StringUtils.SPACE + valueOf);
                zzacl.zzb(this, packageName2);
            }
            this.zzd = true;
        } else {
            zzb();
        }
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN", this.zzd);
    }

    @NonNull
    public final Context zza() {
        return getApplicationContext();
    }

    @Nullable
    private final Uri.Builder zza(Uri.Builder builder, Intent intent, String str, String str2) {
        Uri.Builder builder2 = builder;
        Intent intent2 = intent;
        String stringExtra = intent2.getStringExtra("com.google.firebase.auth.KEY_API_KEY");
        String stringExtra2 = intent2.getStringExtra("com.google.firebase.auth.KEY_PROVIDER_ID");
        String stringExtra3 = intent2.getStringExtra("com.google.firebase.auth.KEY_TENANT_ID");
        String stringExtra4 = intent2.getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME");
        ArrayList<String> stringArrayListExtra = intent2.getStringArrayListExtra("com.google.firebase.auth.KEY_PROVIDER_SCOPES");
        String join = (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) ? null : TextUtils.join(",", stringArrayListExtra);
        String zza = zza(intent2.getBundleExtra("com.google.firebase.auth.KEY_PROVIDER_CUSTOM_PARAMS"));
        String uuid = UUID.randomUUID().toString();
        String zza2 = zzacl.zza(this, UUID.randomUUID().toString());
        String action = intent.getAction();
        String stringExtra5 = intent2.getStringExtra("com.google.firebase.auth.internal.CLIENT_VERSION");
        String str3 = stringExtra;
        String str4 = zza2;
        String str5 = uuid;
        String str6 = zza;
        String str7 = join;
        zzl.zza().zza(getApplicationContext(), str, uuid, zza2, action, stringExtra2, stringExtra3, stringExtra4);
        String zza3 = zzn.zza(getApplicationContext(), FirebaseApp.getInstance(stringExtra4).getPersistenceKey()).zza();
        if (TextUtils.isEmpty(zza3)) {
            Log.e("GenericIdpActivity", "Could not generate an encryption key for Generic IDP - cancelling flow.");
            zza(zzal.zza("Failed to generate/retrieve public encryption key for Generic IDP flow."));
            return null;
        } else if (str4 == null) {
            return null;
        } else {
            Uri.Builder appendQueryParameter = builder2.appendQueryParameter("eid", TtmlNode.TAG_P);
            appendQueryParameter.appendQueryParameter("v", "X" + stringExtra5).appendQueryParameter("authType", "signInWithRedirect").appendQueryParameter("apiKey", str3).appendQueryParameter("providerId", stringExtra2).appendQueryParameter("sessionId", str4).appendQueryParameter("eventId", str5).appendQueryParameter("apn", str).appendQueryParameter("sha1Cert", str2).appendQueryParameter("publicKey", zza3);
            if (!TextUtils.isEmpty(str7)) {
                builder2.appendQueryParameter("scopes", str7);
            }
            if (!TextUtils.isEmpty(str6)) {
                builder2.appendQueryParameter("customParameters", str6);
            }
            if (!TextUtils.isEmpty(stringExtra3)) {
                builder2.appendQueryParameter("tid", stringExtra3);
            }
            return builder2;
        }
    }

    @Nullable
    public final Uri.Builder zza(@NonNull Intent intent, @NonNull String str, @NonNull String str2) {
        return zza(new Uri.Builder().scheme(Constants.SCHEME).appendPath("__").appendPath("auth").appendPath("handler"), intent, str, str2);
    }

    public static /* synthetic */ Uri zza(Uri uri, Task task) throws Exception {
        Uri.Builder buildUpon = uri.buildUpon();
        if (task.isSuccessful()) {
            AppCheckTokenResult appCheckTokenResult = (AppCheckTokenResult) task.getResult();
            if (appCheckTokenResult.getError() != null) {
                Log.w("GenericIdpActivity", "Error getting App Check token; using placeholder token instead. Error: ".concat(String.valueOf(appCheckTokenResult.getError())));
            }
            String token = appCheckTokenResult.getToken();
            buildUpon.fragment("fac=" + token);
        } else {
            b.u("Unexpected error getting App Check token: ", task.getException().getMessage(), "GenericIdpActivity");
        }
        return buildUpon.build();
    }

    @NonNull
    public final String zza(@NonNull String str) {
        return zzaec.zzb(str);
    }

    @Nullable
    private static String zza(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (String next : bundle.keySet()) {
                String string = bundle.getString(next);
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put(next, string);
                }
            }
        } catch (JSONException unused) {
            Log.e("GenericIdpActivity", "Unexpected JSON exception when serializing developer specified custom params");
        }
        return jSONObject.toString();
    }

    @Nullable
    public final HttpURLConnection zza(@NonNull URL url) {
        try {
            return (HttpURLConnection) zzb.zza().zza(url, "client-firebase-auth-api");
        } catch (IOException unused) {
            Log.e("GenericIdpActivity", "Error generating URL connection");
            return null;
        }
    }

    private final void zza(Status status) {
        zzb = 0;
        this.zzd = false;
        Intent intent = new Intent();
        zzcc.zza(intent, status);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!zza(intent)) {
            zzbi.zza(getApplicationContext(), status);
        } else {
            zzc.zza((Context) this);
        }
        finish();
    }

    public final void zza(@NonNull String str, @Nullable Status status) {
        if (status == null) {
            zzb();
        } else {
            zza(status);
        }
    }

    public final void zza(@NonNull Uri uri, @NonNull String str, @NonNull Provider<InteropAppCheckTokenProvider> provider) {
        Task<TContinuationResult> task;
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = provider.get();
        if (interopAppCheckTokenProvider != null) {
            task = interopAppCheckTokenProvider.getToken(false).continueWith(new zzbc(uri));
        } else {
            task = Tasks.forResult(uri);
        }
        task.addOnCompleteListener(new zzbd(this, str));
    }

    private final boolean zza(Intent intent) {
        return LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
