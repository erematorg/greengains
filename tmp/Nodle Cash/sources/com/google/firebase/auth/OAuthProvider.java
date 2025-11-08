package com.google.firebase.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzact;
import com.google.android.gms.internal.p002firebaseauthapi.zzaec;
import com.google.firebase.auth.internal.GenericIdpActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OAuthProvider extends FederatedAuthProvider {
    private final Bundle zza;

    public static class Builder {
        private final FirebaseAuth zza;
        @VisibleForTesting
        private final Bundle zzb;
        private final Bundle zzc;

        @NonNull
        public Builder addCustomParameter(@NonNull String str, @NonNull String str2) {
            this.zzc.putString(str, str2);
            return this;
        }

        @NonNull
        public Builder addCustomParameters(@NonNull Map<String, String> map) {
            for (Map.Entry next : map.entrySet()) {
                this.zzc.putString((String) next.getKey(), (String) next.getValue());
            }
            return this;
        }

        @NonNull
        public OAuthProvider build() {
            return new OAuthProvider(this.zzb);
        }

        @NonNull
        @KeepForSdk
        public List<String> getScopes() {
            ArrayList<String> stringArrayList = this.zzb.getStringArrayList("com.google.firebase.auth.KEY_PROVIDER_SCOPES");
            return stringArrayList != null ? stringArrayList : Collections.emptyList();
        }

        @NonNull
        public Builder setScopes(@NonNull List<String> list) {
            this.zzb.putStringArrayList("com.google.firebase.auth.KEY_PROVIDER_SCOPES", new ArrayList(list));
            return this;
        }

        private Builder(String str, FirebaseAuth firebaseAuth) {
            Bundle bundle = new Bundle();
            this.zzb = bundle;
            Bundle bundle2 = new Bundle();
            this.zzc = bundle2;
            this.zza = firebaseAuth;
            bundle.putString("com.google.firebase.auth.KEY_API_KEY", firebaseAuth.getApp().getOptions().getApiKey());
            bundle.putString("com.google.firebase.auth.KEY_PROVIDER_ID", str);
            bundle.putBundle("com.google.firebase.auth.KEY_PROVIDER_CUSTOM_PARAMS", bundle2);
            bundle.putString("com.google.firebase.auth.internal.CLIENT_VERSION", zzact.zza().zzb());
            bundle.putString("com.google.firebase.auth.KEY_TENANT_ID", firebaseAuth.getTenantId());
            bundle.putString("com.google.firebase.auth.KEY_FIREBASE_APP_NAME", firebaseAuth.getApp().getName());
            bundle.putString("com.google.firebase.auth.KEY_CUSTOM_AUTH_DOMAIN", firebaseAuth.getCustomAuthDomain());
        }
    }

    public static class CredentialBuilder {
        private final String zza;
        @Nullable
        private String zzb;
        @Nullable
        private String zzc;
        @Nullable
        private String zzd;

        @NonNull
        public AuthCredential build() {
            return zzf.zza(this.zza, this.zzb, this.zzc, this.zzd);
        }

        @KeepForSdk
        @Nullable
        public String getAccessToken() {
            return this.zzc;
        }

        @KeepForSdk
        @Nullable
        public String getIdToken() {
            return this.zzb;
        }

        @NonNull
        public CredentialBuilder setAccessToken(@Nullable String str) {
            this.zzc = str;
            return this;
        }

        @NonNull
        public CredentialBuilder setIdToken(@Nullable String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public CredentialBuilder setIdTokenWithRawNonce(@NonNull String str, @Nullable String str2) {
            this.zzb = str;
            this.zzd = str2;
            return this;
        }

        private CredentialBuilder(String str) {
            this.zza = str;
        }
    }

    @NonNull
    @Deprecated
    public static AuthCredential getCredential(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return zzf.zza(str, str2, str3);
    }

    @NonNull
    public static Builder newBuilder(@NonNull String str) {
        return newBuilder(str, FirebaseAuth.getInstance());
    }

    @NonNull
    public static CredentialBuilder newCredentialBuilder(@NonNull String str) {
        return new CredentialBuilder(Preconditions.checkNotEmpty(str));
    }

    @Nullable
    public String getProviderId() {
        Bundle bundle = this.zza;
        if (bundle == null) {
            return null;
        }
        return bundle.getString("com.google.firebase.auth.KEY_PROVIDER_ID");
    }

    public final void zza(@NonNull Activity activity) {
        Intent intent = new Intent("com.google.firebase.auth.internal.NONGMSCORE_LINK");
        intent.setClass(activity, GenericIdpActivity.class);
        intent.setPackage(activity.getPackageName());
        intent.putExtras(this.zza);
        activity.startActivity(intent);
    }

    public final void zzb(@NonNull Activity activity) {
        Intent intent = new Intent("com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE");
        intent.setClass(activity, GenericIdpActivity.class);
        intent.setPackage(activity.getPackageName());
        intent.putExtras(this.zza);
        activity.startActivity(intent);
    }

    public final void zzc(@NonNull Activity activity) {
        Intent intent = new Intent("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN");
        intent.setClass(activity, GenericIdpActivity.class);
        intent.setPackage(activity.getPackageName());
        intent.putExtras(this.zza);
        activity.startActivity(intent);
    }

    private OAuthProvider(Bundle bundle) {
        this.zza = bundle;
    }

    @NonNull
    public static Builder newBuilder(@NonNull String str, @NonNull FirebaseAuth firebaseAuth) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseAuth);
        if (!"facebook.com".equals(str) || zzaec.zza(firebaseAuth.getApp())) {
            return new Builder(str, firebaseAuth);
        }
        throw new IllegalArgumentException("Sign in with Facebook is not supported via this method; the Facebook TOS dictate that you must use the Facebook Android SDK for Facebook login.");
    }
}
