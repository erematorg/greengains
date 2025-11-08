package com.google.android.libraries.identity.googleid;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.credentials.GetCustomCredentialOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0010\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010\fR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013¨\u0006\u0019"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GetGoogleIdOption;", "Landroidx/credentials/GetCustomCredentialOption;", "serverClientId", "", "nonce", "filterByAuthorizedAccounts", "", "linkedServiceId", "idTokenDepositionScopes", "", "requestVerifiedPhoneNumber", "autoSelectEnabled", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/List;ZZ)V", "getAutoSelectEnabled", "()Z", "getFilterByAuthorizedAccounts", "getIdTokenDepositionScopes", "()Ljava/util/List;", "getLinkedServiceId", "()Ljava/lang/String;", "getNonce", "getRequestVerifiedPhoneNumber", "getServerClientId", "Builder", "Companion", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GetGoogleIdOption extends GetCustomCredentialOption {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final String zza;
    @Nullable
    private final String zzb;
    private final boolean zzc;
    @Nullable
    private final String zzd;
    @Nullable
    private final List zze;
    private final boolean zzf;
    private final boolean zzg;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0012\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GetGoogleIdOption$Builder;", "", "()V", "autoSelectEnabled", "", "filterByAuthorizedAccounts", "idTokenDepositionScopes", "", "", "linkedServiceId", "nonce", "requestVerifiedPhoneNumber", "serverClientId", "associateLinkedAccounts", "build", "Lcom/google/android/libraries/identity/googleid/GetGoogleIdOption;", "setAutoSelectEnabled", "setFilterByAuthorizedAccounts", "setNonce", "setRequestVerifiedPhoneNumber", "setServerClientId", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        @NotNull
        private String zza = "";
        @Nullable
        private String zzb;
        @Nullable
        private String zzc;
        private boolean zzd = true;
        private boolean zze;
        private boolean zzf;
        @Nullable
        private List zzg;

        @NotNull
        public final Builder associateLinkedAccounts(@NonNull String str, @Nullable List<String> list) {
            Intrinsics.checkNotNullParameter(str, "linkedServiceId");
            if (str.length() > 0) {
                this.zzb = str;
                this.zzg = list != null ? CollectionsKt.toList(list) : null;
                return this;
            }
            throw new IllegalArgumentException("linkedServiceId must be provided if you want to associate linked accounts.");
        }

        @NotNull
        public final GetGoogleIdOption build() {
            return new GetGoogleIdOption(this.zza, this.zzc, this.zzd, this.zzb, this.zzg, this.zze, this.zzf);
        }

        @NotNull
        public final Builder setAutoSelectEnabled(boolean z2) {
            this.zzf = z2;
            return this;
        }

        @NotNull
        public final Builder setFilterByAuthorizedAccounts(boolean z2) {
            this.zzd = z2;
            return this;
        }

        @NotNull
        public final Builder setNonce(@Nullable String str) {
            this.zzc = str;
            return this;
        }

        @NotNull
        public final Builder setRequestVerifiedPhoneNumber(boolean z2) {
            this.zze = z2;
            return this;
        }

        @NotNull
        public final Builder setServerClientId(@NonNull String str) {
            Intrinsics.checkNotNullParameter(str, "serverClientId");
            if (str.length() > 0) {
                this.zza = str;
                return this;
            }
            throw new IllegalArgumentException("serverClientId should not be empty");
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007JQ\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001aH\u0001¢\u0006\u0002\b R\u0016\u0010\u0003\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u0016\u0010\n\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u0016\u0010\f\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u0016\u0010\u000e\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002R\u0016\u0010\u0010\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0002¨\u0006!"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GetGoogleIdOption$Companion;", "", "()V", "BUNDLE_KEY_AUTO_SELECT_ENABLED", "", "getBUNDLE_KEY_AUTO_SELECT_ENABLED$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_FILTER_BY_AUTHORIZED_ACCOUNTS", "getBUNDLE_KEY_FILTER_BY_AUTHORIZED_ACCOUNTS$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_ID_TOKEN_DEPOSITION_SCOPES", "getBUNDLE_KEY_ID_TOKEN_DEPOSITION_SCOPES$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_LINKED_SERVICE_ID", "getBUNDLE_KEY_LINKED_SERVICE_ID$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_NONCE", "getBUNDLE_KEY_NONCE$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_REQUEST_VERIFIED_PHONE_NUMBER", "getBUNDLE_KEY_REQUEST_VERIFIED_PHONE_NUMBER$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_SERVER_CLIENT_ID", "getBUNDLE_KEY_SERVER_CLIENT_ID$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "createFrom", "Lcom/google/android/libraries/identity/googleid/GetGoogleIdOption;", "data", "Landroid/os/Bundle;", "toBundle", "serverClientId", "nonce", "filterByAuthorizedAccounts", "", "linkedServiceId", "idTokenDepositionScopes", "", "requestVerifiedPhoneNumber", "autoSelectEnabled", "toBundle$java_com_google_android_libraries_identity_googleid_granule_granule", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public static final Bundle zza(@NonNull String str, @Nullable String str2, boolean z2, @Nullable String str3, @Nullable List list, boolean z3, boolean z4) {
            Intrinsics.checkNotNullParameter(str, "serverClientId");
            Bundle bundle = new Bundle();
            bundle.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_SERVER_CLIENT_ID", str);
            bundle.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_NONCE", str2);
            bundle.putBoolean("com.google.android.libraries.identity.googleid.BUNDLE_KEY_FILTER_BY_AUTHORIZED_ACCOUNTS", z2);
            bundle.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_LINKED_SERVICE_ID", str3);
            bundle.putStringArrayList("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID_TOKEN_DEPOSITION_SCOPES", list == null ? null : new ArrayList(list));
            bundle.putBoolean("com.google.android.libraries.identity.googleid.BUNDLE_KEY_REQUEST_VERIFIED_PHONE_NUMBER", z3);
            bundle.putBoolean("com.google.android.libraries.identity.googleid.BUNDLE_KEY_AUTO_SELECT_ENABLED", z4);
            return bundle;
        }

        @JvmStatic
        @NotNull
        public final GetGoogleIdOption createFrom(@NonNull Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                String string = bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_SERVER_CLIENT_ID");
                Intrinsics.checkNotNull(string);
                return new GetGoogleIdOption(string, bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_NONCE"), bundle.getBoolean("com.google.android.libraries.identity.googleid.BUNDLE_KEY_FILTER_BY_AUTHORIZED_ACCOUNTS", true), bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_LINKED_SERVICE_ID"), bundle.getStringArrayList("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID_TOKEN_DEPOSITION_SCOPES"), bundle.getBoolean("com.google.android.libraries.identity.googleid.BUNDLE_KEY_REQUEST_VERIFIED_PHONE_NUMBER", false), bundle.getBoolean("com.google.android.libraries.identity.googleid.BUNDLE_KEY_AUTO_SELECT_ENABLED", false));
            } catch (Exception e3) {
                throw new GoogleIdTokenParsingException(e3);
            }
        }

        public /* synthetic */ Companion(@NonNull DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetGoogleIdOption(@NonNull String str, @Nullable String str2, boolean z2, @Nullable String str3, @Nullable List<String> list, boolean z3, boolean z4) {
        super(GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL, Companion.zza(str, str2, z2, str3, list, z3, z4), Companion.zza(str, str2, z2, str3, list, z3, z4), true, z4, (Set) null, 32, (DefaultConstructorMarker) null);
        boolean z5 = z2;
        boolean z6 = z3;
        Intrinsics.checkNotNullParameter(str, "serverClientId");
        this.zza = str;
        this.zzb = str2;
        this.zzc = z5;
        this.zzd = str3;
        this.zze = list;
        this.zzf = z6;
        this.zzg = z4;
        if (str.length() <= 0) {
            throw new IllegalArgumentException("serverClientId should not be empty");
        } else if (z5 && z6) {
            throw new IllegalArgumentException("filterByAuthorizedAccounts and requestVerifiedPhoneNumber must not both be true;  the Verified Phone Number feature only works in sign-ups.");
        }
    }

    @JvmStatic
    @NotNull
    public static final GetGoogleIdOption createFrom(@NonNull Bundle bundle) {
        return Companion.createFrom(bundle);
    }

    public final boolean getAutoSelectEnabled() {
        return this.zzg;
    }

    public final boolean getFilterByAuthorizedAccounts() {
        return this.zzc;
    }

    @androidx.annotation.Nullable
    public final List<String> getIdTokenDepositionScopes() {
        return this.zze;
    }

    @androidx.annotation.Nullable
    public final String getLinkedServiceId() {
        return this.zzd;
    }

    @androidx.annotation.Nullable
    public final String getNonce() {
        return this.zzb;
    }

    public final boolean getRequestVerifiedPhoneNumber() {
        return this.zzf;
    }

    @NotNull
    public final String getServerClientId() {
        return this.zza;
    }
}
