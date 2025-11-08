package com.google.android.libraries.identity.googleid;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.credentials.GetCustomCredentialOption;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 \f2\u00020\u0001:\u0002\u000b\fB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GetSignInWithGoogleOption;", "Landroidx/credentials/GetCustomCredentialOption;", "serverClientId", "", "hostedDomainFilter", "nonce", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHostedDomainFilter", "()Ljava/lang/String;", "getNonce", "getServerClientId", "Builder", "Companion", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GetSignInWithGoogleOption extends GetCustomCredentialOption {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final String zza;
    @Nullable
    private final String zzb;
    @Nullable
    private final String zzc;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0003J\u0010\u0010\n\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GetSignInWithGoogleOption$Builder;", "", "serverClientId", "", "(Ljava/lang/String;)V", "hostedDomainFilter", "nonce", "build", "Lcom/google/android/libraries/identity/googleid/GetSignInWithGoogleOption;", "setHostedDomainFilter", "setNonce", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        @NotNull
        private final String zza;
        @Nullable
        private String zzb;
        @Nullable
        private String zzc;

        public Builder(@NonNull String str) {
            Intrinsics.checkNotNullParameter(str, "serverClientId");
            this.zza = str;
        }

        @NotNull
        public final GetSignInWithGoogleOption build() {
            return new GetSignInWithGoogleOption(this.zza, this.zzb, this.zzc);
        }

        @NotNull
        public final Builder setHostedDomainFilter(@NonNull String str) {
            Intrinsics.checkNotNullParameter(str, "hostedDomainFilter");
            this.zzb = str;
            return this;
        }

        @NotNull
        public final Builder setNonce(@Nullable String str) {
            this.zzc = str;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J1\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0001¢\u0006\u0002\b\u0016R\u0016\u0010\u0003\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u0016\u0010\n\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002¨\u0006\u0017"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GetSignInWithGoogleOption$Companion;", "", "()V", "BUNDLE_KEY_AUTO_SELECT_ENABLED", "", "getBUNDLE_KEY_AUTO_SELECT_ENABLED$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_HOSTED_DOMAIN_FILTER", "getBUNDLE_KEY_HOSTED_DOMAIN_FILTER$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_NONCE", "getBUNDLE_KEY_NONCE$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_SERVER_CLIENT_ID", "getBUNDLE_KEY_SERVER_CLIENT_ID$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "createFrom", "Lcom/google/android/libraries/identity/googleid/GetSignInWithGoogleOption;", "data", "Landroid/os/Bundle;", "toBundle", "serverClientId", "hostedDomainFilter", "nonce", "autoSelectEnabled", "", "toBundle$java_com_google_android_libraries_identity_googleid_granule_granule", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public static final Bundle zza(@NonNull String str, @Nullable String str2, @Nullable String str3, boolean z2) {
            Intrinsics.checkNotNullParameter(str, "serverClientId");
            Bundle bundle = new Bundle();
            bundle.putString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_SERVER_CLIENT_ID", str);
            bundle.putString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_NONCE", str3);
            bundle.putString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_HOSTED_DOMAIN_FILTER", str2);
            bundle.putBoolean("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_AUTO_SELECT_ENABLED", true);
            bundle.putString(GoogleIdTokenCredential.BUNDLE_KEY_GOOGLE_ID_TOKEN_SUBTYPE, GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL);
            return bundle;
        }

        @JvmStatic
        @NotNull
        public final GetSignInWithGoogleOption createFrom(@NonNull Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                String string = bundle.getString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_SERVER_CLIENT_ID");
                Intrinsics.checkNotNull(string);
                return new GetSignInWithGoogleOption(string, bundle.getString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_HOSTED_DOMAIN_FILTER"), bundle.getString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_NONCE"));
            } catch (Exception e3) {
                throw new GoogleIdTokenParsingException(e3);
            }
        }

        public /* synthetic */ Companion(@NonNull DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetSignInWithGoogleOption(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        super(GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL, Companion.zza(str, str2, str3, true), Companion.zza(str, str2, str3, true), true, true, (Set) null, 32, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "serverClientId");
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        if (str.length() <= 0) {
            throw new IllegalArgumentException("serverClientId should not be empty");
        }
    }

    @JvmStatic
    @NotNull
    public static final GetSignInWithGoogleOption createFrom(@NonNull Bundle bundle) {
        return Companion.createFrom(bundle);
    }

    @androidx.annotation.Nullable
    public final String getHostedDomainFilter() {
        return this.zzb;
    }

    @androidx.annotation.Nullable
    public final String getNonce() {
        return this.zzc;
    }

    @NotNull
    public final String getServerClientId() {
        return this.zza;
    }
}
