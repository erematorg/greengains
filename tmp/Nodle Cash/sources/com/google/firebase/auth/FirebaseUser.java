package com.google.firebase.auth;

import android.app.Activity;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.p002firebaseauthapi.zzafm;
import com.google.android.gms.internal.p002firebaseauthapi.zzaft;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.List;

public abstract class FirebaseUser extends AbstractSafeParcelable implements UserInfo {
    @NonNull
    public Task<Void> delete() {
        return FirebaseAuth.getInstance(zza()).zza(this);
    }

    @Nullable
    public abstract String getDisplayName();

    @Nullable
    public abstract String getEmail();

    @NonNull
    public Task<GetTokenResult> getIdToken(boolean z2) {
        return FirebaseAuth.getInstance(zza()).zza(this, z2);
    }

    @Nullable
    public abstract FirebaseUserMetadata getMetadata();

    @NonNull
    public abstract MultiFactor getMultiFactor();

    @Nullable
    public abstract String getPhoneNumber();

    @Nullable
    public abstract Uri getPhotoUrl();

    @NonNull
    public abstract List<? extends UserInfo> getProviderData();

    @NonNull
    public abstract String getProviderId();

    @Nullable
    public abstract String getTenantId();

    @NonNull
    public abstract String getUid();

    public abstract boolean isAnonymous();

    @NonNull
    public Task<AuthResult> linkWithCredential(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zza()).zza(this, authCredential);
    }

    @NonNull
    public Task<Void> reauthenticate(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zza()).zzb(this, authCredential);
    }

    @NonNull
    public Task<AuthResult> reauthenticateAndRetrieveData(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zza()).zzc(this, authCredential);
    }

    @NonNull
    public Task<Void> reload() {
        return FirebaseAuth.getInstance(zza()).zzb(this);
    }

    @NonNull
    public Task<Void> sendEmailVerification() {
        return FirebaseAuth.getInstance(zza()).zza(this, false).continueWithTask(new zzai(this));
    }

    @NonNull
    public Task<AuthResult> startActivityForLinkWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zza()).zza(activity, federatedAuthProvider, this);
    }

    @NonNull
    public Task<AuthResult> startActivityForReauthenticateWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zza()).zzb(activity, federatedAuthProvider, this);
    }

    @NonNull
    public Task<AuthResult> unlink(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zza()).zzb(this, str);
    }

    @NonNull
    @Deprecated
    public Task<Void> updateEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zza()).zzc(this, str);
    }

    @NonNull
    public Task<Void> updatePassword(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zza()).zzd(this, str);
    }

    @NonNull
    public Task<Void> updatePhoneNumber(@NonNull PhoneAuthCredential phoneAuthCredential) {
        return FirebaseAuth.getInstance(zza()).zza(this, phoneAuthCredential);
    }

    @NonNull
    public Task<Void> updateProfile(@NonNull UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(userProfileChangeRequest);
        return FirebaseAuth.getInstance(zza()).zza(this, userProfileChangeRequest);
    }

    @NonNull
    public Task<Void> verifyBeforeUpdateEmail(@NonNull String str) {
        return verifyBeforeUpdateEmail(str, (ActionCodeSettings) null);
    }

    @NonNull
    public abstract FirebaseApp zza();

    @NonNull
    public abstract FirebaseUser zza(@NonNull List<? extends UserInfo> list);

    public abstract void zza(@NonNull zzafm zzafm);

    @NonNull
    public abstract FirebaseUser zzb();

    public abstract void zzb(@Nullable List<zzaft> list);

    @NonNull
    public abstract zzafm zzc();

    public abstract void zzc(@NonNull List<MultiFactorInfo> list);

    @NonNull
    public abstract String zzd();

    @NonNull
    public abstract String zze();

    @NonNull
    public abstract List<zzaft> zzf();

    @Nullable
    public abstract List<String> zzg();

    @NonNull
    public Task<Void> verifyBeforeUpdateEmail(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zza()).zza(this, false).continueWithTask(new zzaj(this, str, actionCodeSettings));
    }

    @NonNull
    public Task<Void> sendEmailVerification(@NonNull ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zza()).zza(this, false).continueWithTask(new zzah(this, actionCodeSettings));
    }
}
