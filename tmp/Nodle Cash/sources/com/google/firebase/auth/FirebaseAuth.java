package com.google.firebase.auth;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzaak;
import com.google.android.gms.internal.p002firebaseauthapi.zzach;
import com.google.android.gms.internal.p002firebaseauthapi.zzaco;
import com.google.android.gms.internal.p002firebaseauthapi.zzacy;
import com.google.android.gms.internal.p002firebaseauthapi.zzads;
import com.google.android.gms.internal.p002firebaseauthapi.zzaec;
import com.google.android.gms.internal.p002firebaseauthapi.zzafi;
import com.google.android.gms.internal.p002firebaseauthapi.zzafm;
import com.google.android.gms.internal.p002firebaseauthapi.zzafn;
import com.google.android.gms.internal.p002firebaseauthapi.zzaft;
import com.google.android.gms.internal.p002firebaseauthapi.zzagd;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.auth.internal.zzaj;
import com.google.firebase.auth.internal.zzar;
import com.google.firebase.auth.internal.zzas;
import com.google.firebase.auth.internal.zzbf;
import com.google.firebase.auth.internal.zzbi;
import com.google.firebase.auth.internal.zzbu;
import com.google.firebase.auth.internal.zzbv;
import com.google.firebase.auth.internal.zzby;
import com.google.firebase.auth.internal.zzbz;
import com.google.firebase.auth.internal.zzcb;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzw;
import com.google.firebase.auth.internal.zzz;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

public class FirebaseAuth implements InternalAuthProvider {
    /* access modifiers changed from: private */
    public final FirebaseApp zza;
    /* access modifiers changed from: private */
    public final List<IdTokenListener> zzb;
    /* access modifiers changed from: private */
    public final List<com.google.firebase.auth.internal.IdTokenListener> zzc;
    /* access modifiers changed from: private */
    public final List<AuthStateListener> zzd;
    /* access modifiers changed from: private */
    public final zzaak zze;
    /* access modifiers changed from: private */
    @Nullable
    public FirebaseUser zzf;
    /* access modifiers changed from: private */
    public final zzz zzg;
    private final Object zzh;
    /* access modifiers changed from: private */
    public String zzi;
    private final Object zzj;
    /* access modifiers changed from: private */
    public String zzk;
    private zzbu zzl;
    private final RecaptchaAction zzm;
    private final RecaptchaAction zzn;
    private final RecaptchaAction zzo;
    private final zzbv zzp;
    private final zzcb zzq;
    private final com.google.firebase.auth.internal.zza zzr;
    private final Provider<InteropAppCheckTokenProvider> zzs;
    private final Provider<HeartBeatController> zzt;
    private zzby zzu;
    private final Executor zzv;
    private final Executor zzw;
    private final Executor zzx;
    private String zzy;

    public interface AuthStateListener {
        void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    public interface IdTokenListener {
        void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    @VisibleForTesting
    public class zza implements zzi {
        public zza() {
        }

        public final void zza(zzafm zzafm, FirebaseUser firebaseUser) {
            Preconditions.checkNotNull(zzafm);
            Preconditions.checkNotNull(firebaseUser);
            firebaseUser.zza(zzafm);
            FirebaseAuth.this.zza(firebaseUser, zzafm, true);
        }
    }

    public class zzc extends zza implements zzar, zzi {
        public zzc(FirebaseAuth firebaseAuth) {
            super();
        }

        public final void zza(Status status) {
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FirebaseAuth(@androidx.annotation.NonNull com.google.firebase.FirebaseApp r13, @androidx.annotation.NonNull com.google.firebase.inject.Provider<com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider> r14, @androidx.annotation.NonNull com.google.firebase.inject.Provider<com.google.firebase.heartbeatinfo.HeartBeatController> r15, @com.google.firebase.annotations.concurrent.Background @androidx.annotation.NonNull java.util.concurrent.Executor r16, @androidx.annotation.NonNull @com.google.firebase.annotations.concurrent.Blocking java.util.concurrent.Executor r17, @com.google.firebase.annotations.concurrent.Lightweight @androidx.annotation.NonNull java.util.concurrent.Executor r18, @com.google.firebase.annotations.concurrent.Lightweight @androidx.annotation.NonNull java.util.concurrent.ScheduledExecutorService r19, @androidx.annotation.NonNull @com.google.firebase.annotations.concurrent.UiThread java.util.concurrent.Executor r20) {
        /*
            r12 = this;
            com.google.android.gms.internal.firebase-auth-api.zzaak r2 = new com.google.android.gms.internal.firebase-auth-api.zzaak
            r1 = r13
            r9 = r17
            r0 = r19
            r2.<init>(r13, r9, r0)
            com.google.firebase.auth.internal.zzbv r3 = new com.google.firebase.auth.internal.zzbv
            android.content.Context r0 = r13.getApplicationContext()
            java.lang.String r4 = r13.getPersistenceKey()
            r3.<init>(r0, r4)
            com.google.firebase.auth.internal.zzcb r4 = com.google.firebase.auth.internal.zzcb.zzc()
            com.google.firebase.auth.internal.zza r5 = com.google.firebase.auth.internal.zza.zza()
            r0 = r12
            r6 = r14
            r7 = r15
            r8 = r16
            r10 = r18
            r11 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.<init>(com.google.firebase.FirebaseApp, com.google.firebase.inject.Provider, com.google.firebase.inject.Provider, java.util.concurrent.Executor, java.util.concurrent.Executor, java.util.concurrent.Executor, java.util.concurrent.ScheduledExecutorService, java.util.concurrent.Executor):void");
    }

    @NonNull
    @Keep
    public static FirebaseAuth getInstance() {
        return (FirebaseAuth) FirebaseApp.getInstance().get(FirebaseAuth.class);
    }

    @VisibleForTesting
    private final synchronized zzby zzj() {
        return zzj(this);
    }

    public void addAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzd.add(authStateListener);
        this.zzx.execute(new zzv(this, authStateListener));
    }

    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzb.add(idTokenListener);
        this.zzx.execute(new zzi(this, idTokenListener));
    }

    @NonNull
    public Task<Void> applyActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zza(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<ActionCodeResult> checkActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzb(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<Void> confirmPasswordReset(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zza(this.zza, str, str2, this.zzk);
    }

    @NonNull
    public Task<AuthResult> createUserWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return new zzo(this, str, str2).zza(this, this.zzk, this.zzo, "EMAIL_PASSWORD_PROVIDER");
    }

    @NonNull
    @Deprecated
    public Task<SignInMethodQueryResult> fetchSignInMethodsForEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzc(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<GetTokenResult> getAccessToken(boolean z2) {
        return zza(this.zzf, z2);
    }

    @NonNull
    public FirebaseApp getApp() {
        return this.zza;
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return this.zzf;
    }

    @Nullable
    public String getCustomAuthDomain() {
        return this.zzy;
    }

    @NonNull
    public FirebaseAuthSettings getFirebaseAuthSettings() {
        return this.zzg;
    }

    @Nullable
    public String getLanguageCode() {
        String str;
        synchronized (this.zzh) {
            str = this.zzi;
        }
        return str;
    }

    @Nullable
    public Task<AuthResult> getPendingAuthResult() {
        return this.zzq.zza();
    }

    @Nullable
    public String getTenantId() {
        String str;
        synchronized (this.zzj) {
            str = this.zzk;
        }
        return str;
    }

    @Nullable
    public String getUid() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getUid();
    }

    @NonNull
    public Task<Void> initializeRecaptchaConfig() {
        if (this.zzl == null) {
            this.zzl = new zzbu(this.zza, this);
        }
        return this.zzl.zza(this.zzk, Boolean.FALSE).continueWithTask(new zzab(this));
    }

    public boolean isSignInWithEmailLink(@NonNull String str) {
        return EmailAuthCredential.zza(str);
    }

    public void removeAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzd.remove(authStateListener);
    }

    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzb.remove(idTokenListener);
    }

    @NonNull
    public Task<Void> revokeAccessToken(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        FirebaseUser currentUser = getCurrentUser();
        Preconditions.checkNotNull(currentUser);
        return currentUser.getIdToken(false).continueWithTask(new zzx(this, str));
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return sendPasswordResetEmail(str, (ActionCodeSettings) null);
    }

    @NonNull
    public Task<Void> sendSignInLinkToEmail(@NonNull String str, @NonNull ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(actionCodeSettings);
        if (actionCodeSettings.canHandleCodeInApp()) {
            String str2 = this.zzi;
            if (str2 != null) {
                actionCodeSettings.zza(str2);
            }
            return new zzq(this, str, actionCodeSettings).zza(this, this.zzk, this.zzm, "EMAIL_PASSWORD_PROVIDER");
        }
        throw new IllegalArgumentException("You must set canHandleCodeInApp in your ActionCodeSettings to true for Email-Link Sign-in.");
    }

    public void setCustomAuthDomain(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        if (str.startsWith("chrome-extension://")) {
            this.zzy = str;
            return;
        }
        try {
            this.zzy = (String) Preconditions.checkNotNull(new URI(str.contains("://") ? str : "http://".concat(str)).getHost());
        } catch (URISyntaxException e3) {
            if (Log.isLoggable("FirebaseAuth", 4)) {
                String message = e3.getMessage();
                Log.i("FirebaseAuth", "Error parsing URL: '" + str + "', " + message);
            }
            this.zzy = str;
        }
    }

    @NonNull
    public Task<Void> setFirebaseUIVersion(@Nullable String str) {
        return this.zze.zza(str);
    }

    public void setLanguageCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzh) {
            this.zzi = str;
        }
    }

    public void setTenantId(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzj) {
            this.zzk = str;
        }
    }

    @NonNull
    public Task<AuthResult> signInAnonymously() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null || !firebaseUser.isAnonymous()) {
            return this.zze.zza(this.zza, (zzi) new zza(), this.zzk);
        }
        zzac zzac = (zzac) this.zzf;
        zzac.zza(false);
        return Tasks.forResult(new zzw(zzac));
    }

    @NonNull
    public Task<AuthResult> signInWithCredential(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if (emailAuthCredential.zzf()) {
                return zzb(Preconditions.checkNotEmpty(emailAuthCredential.zze())) ? Tasks.forException(zzach.zza(new Status(17072))) : zza(emailAuthCredential, (FirebaseUser) null, false);
            }
            return zza(emailAuthCredential.zzc(), (String) Preconditions.checkNotNull(emailAuthCredential.zzd()), this.zzk, (FirebaseUser) null, false);
        } else if (!(zza2 instanceof PhoneAuthCredential)) {
            return this.zze.zza(this.zza, zza2, this.zzk, (zzi) new zza());
        } else {
            return this.zze.zza(this.zza, (PhoneAuthCredential) zza2, this.zzk, (zzi) new zza());
        }
    }

    @NonNull
    public Task<AuthResult> signInWithCustomToken(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zza(this.zza, str, this.zzk, (zzi) new zza());
    }

    @NonNull
    public Task<AuthResult> signInWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return zza(str, str2, this.zzk, (FirebaseUser) null, false);
    }

    @NonNull
    public Task<AuthResult> signInWithEmailLink(@NonNull String str, @NonNull String str2) {
        return signInWithCredential(EmailAuthProvider.getCredentialWithLink(str, str2));
    }

    public void signOut() {
        zzh();
        zzby zzby = this.zzu;
        if (zzby != null) {
            zzby.zza();
        }
    }

    @NonNull
    public Task<AuthResult> startActivityForSignInWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzq.zza(activity, taskCompletionSource, this)) {
            return Tasks.forException(zzach.zza(new Status(17057)));
        }
        zzbi.zza(activity.getApplicationContext(), this);
        federatedAuthProvider.zzc(activity);
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.firebase.auth.internal.zzbz, com.google.firebase.auth.FirebaseAuth$zzc] */
    @NonNull
    public Task<Void> updateCurrentUser(@NonNull FirebaseUser firebaseUser) {
        String str;
        if (firebaseUser != null) {
            String tenantId = firebaseUser.getTenantId();
            if ((tenantId != null && !tenantId.equals(this.zzk)) || ((str = this.zzk) != null && !str.equals(tenantId))) {
                return Tasks.forException(zzach.zza(new Status(17072)));
            }
            String apiKey = firebaseUser.zza().getOptions().getApiKey();
            String apiKey2 = this.zza.getOptions().getApiKey();
            if (!firebaseUser.zzc().zzg() || !apiKey2.equals(apiKey)) {
                return zza(firebaseUser, (zzbz) new zzc(this));
            }
            zza(zzac.zza(this.zza, firebaseUser), firebaseUser.zzc(), true);
            return Tasks.forResult(null);
        }
        throw new IllegalArgumentException("Cannot update current user with null user!");
    }

    public void useAppLanguage() {
        synchronized (this.zzh) {
            this.zzi = zzacy.zza();
        }
    }

    public void useEmulator(@NonNull String str, int i3) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(i3 >= 0 && i3 <= 65535, "Port number must be in the range 0-65535");
        zzaec.zza(this.zza, str, i3);
    }

    @NonNull
    public Task<String> verifyPasswordResetCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzd(this.zza, str, this.zzk);
    }

    private static zzby zzj(FirebaseAuth firebaseAuth) {
        if (firebaseAuth.zzu == null) {
            firebaseAuth.zzu = new zzby((FirebaseApp) Preconditions.checkNotNull(firebaseAuth.zza));
        }
        return firebaseAuth.zzu;
    }

    @KeepForSdk
    public void removeIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.remove(idTokenListener);
        zzj().zza(this.zzc.size());
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    /* JADX WARNING: type inference failed for: r9v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return zza(firebaseUser, emailAuthCredential, false);
            }
            if (zzb(Preconditions.checkNotEmpty(emailAuthCredential.zze()))) {
                return Tasks.forException(zzach.zza(new Status(17072)));
            }
            return zza(firebaseUser, emailAuthCredential, true);
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zza(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, (zzbz) new zzb());
        } else {
            return this.zze.zzb(this.zza, firebaseUser, zza2, firebaseUser.getTenantId(), (zzbz) new zzb());
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    /* JADX WARNING: type inference failed for: r9v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<AuthResult> zzc(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return zza(emailAuthCredential.zzc(), Preconditions.checkNotEmpty(emailAuthCredential.zzd()), firebaseUser.getTenantId(), firebaseUser, true);
            } else if (zzb(Preconditions.checkNotEmpty(emailAuthCredential.zze()))) {
                return Tasks.forException(zzach.zza(new Status(17072)));
            } else {
                return zza(emailAuthCredential, firebaseUser, true);
            }
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zzb(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, (zzbz) new zzb());
        } else {
            return this.zze.zzc(this.zza, firebaseUser, zza2, firebaseUser.getTenantId(), new zzb());
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<Void> zzd(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzd(this.zza, firebaseUser, str, new zzb());
    }

    @NonNull
    public final Executor zze() {
        return this.zzv;
    }

    @NonNull
    public final Executor zzf() {
        return this.zzw;
    }

    @NonNull
    public final Executor zzg() {
        return this.zzx;
    }

    public final void zzh() {
        Preconditions.checkNotNull(this.zzp);
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser != null) {
            zzbv zzbv = this.zzp;
            Preconditions.checkNotNull(firebaseUser);
            String uid = firebaseUser.getUid();
            zzbv.zza("com.google.firebase.auth.GET_TOKEN_RESPONSE." + uid);
            this.zzf = null;
        }
        this.zzp.zza("com.google.firebase.auth.FIREBASE_USER");
        zzb(this, (FirebaseUser) null);
        zza(this, (FirebaseUser) null);
    }

    @VisibleForTesting
    public final boolean zzi() {
        return zzaco.zza(getApp().getApplicationContext());
    }

    @NonNull
    @Keep
    public static FirebaseAuth getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseAuth) firebaseApp.get(FirebaseAuth.class);
    }

    @KeepForSdk
    public void addIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.add(idTokenListener);
        zzj().zza(this.zzc.size());
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zzb();
        }
        String str2 = this.zzi;
        if (str2 != null) {
            actionCodeSettings.zza(str2);
        }
        actionCodeSettings.zza(1);
        return new zzr(this, str, actionCodeSettings).zza(this, this.zzk, this.zzm, "EMAIL_PASSWORD_PROVIDER");
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zza(firebaseUser, (zzas) new zzs(this, firebaseUser));
    }

    @VisibleForTesting
    public class zzb implements zzar, zzi {
        public zzb() {
        }

        public final void zza(zzafm zzafm, FirebaseUser firebaseUser) {
            Preconditions.checkNotNull(zzafm);
            Preconditions.checkNotNull(firebaseUser);
            firebaseUser.zza(zzafm);
            FirebaseAuth.this.zza(firebaseUser, zzafm, true, true);
        }

        public final void zza(Status status) {
            if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005 || status.getStatusCode() == 17091) {
                FirebaseAuth.this.signOut();
            }
        }
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull MultiFactorAssertion multiFactorAssertion, @Nullable String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(multiFactorAssertion);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            return this.zze.zza(this.zza, (PhoneMultiFactorAssertion) multiFactorAssertion, firebaseUser, str, (zzi) new zza());
        } else if (!(multiFactorAssertion instanceof TotpMultiFactorAssertion)) {
            return Tasks.forException(zzach.zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR)));
        } else {
            return this.zze.zza(this.zza, (TotpMultiFactorAssertion) multiFactorAssertion, firebaseUser, str, this.zzk, (zzi) new zza());
        }
    }

    @NonNull
    public final Provider<HeartBeatController> zzd() {
        return this.zzt;
    }

    @VisibleForTesting
    private FirebaseAuth(FirebaseApp firebaseApp, zzaak zzaak, zzbv zzbv, zzcb zzcb, com.google.firebase.auth.internal.zza zza2, Provider<InteropAppCheckTokenProvider> provider, Provider<HeartBeatController> provider2, @Background Executor executor, @Blocking Executor executor2, @Lightweight Executor executor3, @UiThread Executor executor4) {
        zzafm zza3;
        this.zzb = new CopyOnWriteArrayList();
        this.zzc = new CopyOnWriteArrayList();
        this.zzd = new CopyOnWriteArrayList();
        this.zzh = new Object();
        this.zzj = new Object();
        this.zzm = RecaptchaAction.custom("getOobCode");
        this.zzn = RecaptchaAction.custom("signInWithPassword");
        this.zzo = RecaptchaAction.custom("signUpPassword");
        this.zza = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zze = (zzaak) Preconditions.checkNotNull(zzaak);
        zzbv zzbv2 = (zzbv) Preconditions.checkNotNull(zzbv);
        this.zzp = zzbv2;
        this.zzg = new zzz();
        zzcb zzcb2 = (zzcb) Preconditions.checkNotNull(zzcb);
        this.zzq = zzcb2;
        this.zzr = (com.google.firebase.auth.internal.zza) Preconditions.checkNotNull(zza2);
        this.zzs = provider;
        this.zzt = provider2;
        this.zzv = executor2;
        this.zzw = executor3;
        this.zzx = executor4;
        FirebaseUser zza4 = zzbv2.zza();
        this.zzf = zza4;
        if (!(zza4 == null || (zza3 = zzbv2.zza(zza4)) == null)) {
            zza(this, this.zzf, zza3, false, false);
        }
        zzcb2.zza(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.zzy, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<GetTokenResult> zza(@Nullable FirebaseUser firebaseUser, boolean z2) {
        if (firebaseUser == null) {
            return Tasks.forException(zzach.zza(new Status(FirebaseError.ERROR_NO_SIGNED_IN_USER)));
        }
        zzafm zzc2 = firebaseUser.zzc();
        if (!zzc2.zzg() || z2) {
            return this.zze.zza(this.zza, firebaseUser, zzc2.zzd(), (zzbz) new zzy(this));
        }
        return Tasks.forResult(zzbf.zza(zzc2.zzc()));
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser) {
        return zza(firebaseUser, (zzbz) new zzb());
    }

    @NonNull
    public final Task<AuthResult> zzb(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzq.zza(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzach.zza(new Status(17057)));
        }
        zzbi.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzb(activity);
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<Void> zzc(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzc(this.zza, firebaseUser, str, new zzb());
    }

    @NonNull
    public final Task<zzafi> zza() {
        return this.zze.zza();
    }

    @NonNull
    public final Task<zzafn> zza(@NonNull String str) {
        return this.zze.zza(this.zzk, str);
    }

    @NonNull
    public final Provider<InteropAppCheckTokenProvider> zzc() {
        return this.zzs;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<AuthResult> zza(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        if (authCredential instanceof EmailAuthCredential) {
            return new zzp(this, firebaseUser, (EmailAuthCredential) authCredential.zza()).zza(this, firebaseUser.getTenantId(), this.zzo, "EMAIL_PASSWORD_PROVIDER");
        }
        return this.zze.zza(this.zza, firebaseUser, authCredential.zza(), (String) null, (zzbz) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<AuthResult> zzb(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzb(this.zza, firebaseUser, str, new zzb());
    }

    private final Task<Void> zza(FirebaseUser firebaseUser, zzbz zzbz) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zza(this.zza, firebaseUser, zzbz);
    }

    public final synchronized zzbu zzb() {
        return this.zzl;
    }

    private static void zzb(FirebaseAuth firebaseAuth, @Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            Log.d("FirebaseAuth", "Notifying id token listeners about user ( " + uid + " ).");
        } else {
            Log.d("FirebaseAuth", "Notifying id token listeners about a sign-out event.");
        }
        firebaseAuth.zzx.execute(new zzw(firebaseAuth, new InternalTokenResult(firebaseUser != null ? firebaseUser.zzd() : null)));
    }

    public final Task<AuthResult> zza(MultiFactorAssertion multiFactorAssertion, zzaj zzaj, @Nullable FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzaj);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            return this.zze.zza(this.zza, firebaseUser, (PhoneMultiFactorAssertion) multiFactorAssertion, Preconditions.checkNotEmpty(zzaj.zzc()), (zzi) new zza());
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            return this.zze.zza(this.zza, firebaseUser, (TotpMultiFactorAssertion) multiFactorAssertion, Preconditions.checkNotEmpty(zzaj.zzc()), this.zzk, (zzi) new zza());
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
    }

    private final boolean zzb(String str) {
        ActionCodeUrl parseLink = ActionCodeUrl.parseLink(str);
        return parseLink != null && !TextUtils.equals(this.zzk, parseLink.zza());
    }

    @NonNull
    public final Task<Void> zza(@Nullable ActionCodeSettings actionCodeSettings, @NonNull String str) {
        Preconditions.checkNotEmpty(str);
        if (this.zzi != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.zzb();
            }
            actionCodeSettings.zza(this.zzi);
        }
        return this.zze.zza(this.zza, actionCodeSettings, str);
    }

    @NonNull
    public final Task<AuthResult> zza(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzq.zza(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzach.zza(new Status(17057)));
        }
        zzbi.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zza(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<TotpSecret> zza(zzaj zzaj) {
        Preconditions.checkNotNull(zzaj);
        return this.zze.zza(zzaj, this.zzk).continueWithTask(new zzu(this));
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zza(this.zza, firebaseUser, str, this.zzk, (zzbz) new zzb()).continueWithTask(new zzt(this));
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull PhoneAuthCredential phoneAuthCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(phoneAuthCredential);
        return this.zze.zza(this.zza, firebaseUser, (PhoneAuthCredential) phoneAuthCredential.zza(), (zzbz) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(userProfileChangeRequest);
        return this.zze.zza(this.zza, firebaseUser, userProfileChangeRequest, (zzbz) new zzb());
    }

    @NonNull
    public final Task<Void> zza(@NonNull String str, @NonNull String str2, @Nullable ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zzb();
        }
        String str3 = this.zzi;
        if (str3 != null) {
            actionCodeSettings.zza(str3);
        }
        return this.zze.zza(str, str2, actionCodeSettings);
    }

    private final Task<Void> zza(FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, boolean z2) {
        return new zzac(this, z2, firebaseUser, emailAuthCredential).zza(this, this.zzk, z2 ? this.zzm : this.zzn, "EMAIL_PASSWORD_PROVIDER");
    }

    private final Task<AuthResult> zza(String str, String str2, @Nullable String str3, @Nullable FirebaseUser firebaseUser, boolean z2) {
        return new zzaa(this, str, z2, firebaseUser, str2, str3).zza(this, str3, this.zzn, "EMAIL_PASSWORD_PROVIDER");
    }

    private final Task<AuthResult> zza(EmailAuthCredential emailAuthCredential, @Nullable FirebaseUser firebaseUser, boolean z2) {
        return new zzad(this, z2, firebaseUser, emailAuthCredential).zza(this, this.zzk, this.zzm, "EMAIL_PASSWORD_PROVIDER");
    }

    @VisibleForTesting
    public final PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(PhoneAuthOptions phoneAuthOptions, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        if (phoneAuthOptions.zzj()) {
            return onVerificationStateChangedCallbacks;
        }
        return new zzm(this, phoneAuthOptions, onVerificationStateChangedCallbacks);
    }

    /* access modifiers changed from: private */
    public final PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(@Nullable String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        return (!this.zzg.zzd() || str == null || !str.equals(this.zzg.zza())) ? onVerificationStateChangedCallbacks : new zzn(this, onVerificationStateChangedCallbacks);
    }

    public static void zza(@NonNull FirebaseException firebaseException, @NonNull PhoneAuthOptions phoneAuthOptions, @NonNull String str) {
        Log.e("FirebaseAuth", "Invoking verification failure callback for phone number/uid - " + str);
        phoneAuthOptions.zzi().execute(new zzj(zzads.zza(str, phoneAuthOptions.zze(), (zzacz) null), firebaseException));
    }

    public final synchronized void zza(zzbu zzbu) {
        this.zzl = zzbu;
    }

    public final void zza(FirebaseUser firebaseUser, zzafm zzafm, boolean z2) {
        zza(firebaseUser, zzafm, true, false);
    }

    @VisibleForTesting
    public final void zza(FirebaseUser firebaseUser, zzafm zzafm, boolean z2, boolean z3) {
        zza(this, firebaseUser, zzafm, true, z3);
    }

    private static void zza(FirebaseAuth firebaseAuth, @Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            Log.d("FirebaseAuth", "Notifying auth state listeners about user ( " + uid + " ).");
        } else {
            Log.d("FirebaseAuth", "Notifying auth state listeners about a sign-out event.");
        }
        firebaseAuth.zzx.execute(new zzz(firebaseAuth));
    }

    @VisibleForTesting
    private static void zza(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser, zzafm zzafm, boolean z2, boolean z3) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzafm);
        boolean z4 = false;
        boolean z5 = true;
        boolean z6 = firebaseAuth.zzf != null && firebaseUser.getUid().equals(firebaseAuth.zzf.getUid());
        if (z6 || !z3) {
            FirebaseUser firebaseUser2 = firebaseAuth.zzf;
            if (firebaseUser2 == null) {
                z4 = true;
            } else {
                boolean z7 = !z6 || !firebaseUser2.zzc().zzc().equals(zzafm.zzc());
                if (!z6) {
                    z4 = true;
                }
                z5 = z7;
            }
            Preconditions.checkNotNull(firebaseUser);
            if (firebaseAuth.zzf == null || !firebaseUser.getUid().equals(firebaseAuth.getUid())) {
                firebaseAuth.zzf = firebaseUser;
            } else {
                firebaseAuth.zzf.zza(firebaseUser.getProviderData());
                if (!firebaseUser.isAnonymous()) {
                    firebaseAuth.zzf.zzb();
                }
                List<MultiFactorInfo> enrolledFactors = firebaseUser.getMultiFactor().getEnrolledFactors();
                List<zzaft> zzf2 = firebaseUser.zzf();
                firebaseAuth.zzf.zzc(enrolledFactors);
                firebaseAuth.zzf.zzb(zzf2);
            }
            if (z2) {
                firebaseAuth.zzp.zzb(firebaseAuth.zzf);
            }
            if (z5) {
                FirebaseUser firebaseUser3 = firebaseAuth.zzf;
                if (firebaseUser3 != null) {
                    firebaseUser3.zza(zzafm);
                }
                zzb(firebaseAuth, firebaseAuth.zzf);
            }
            if (z4) {
                zza(firebaseAuth, firebaseAuth.zzf);
            }
            if (z2) {
                firebaseAuth.zzp.zza(firebaseUser, zzafm);
            }
            FirebaseUser firebaseUser4 = firebaseAuth.zzf;
            if (firebaseUser4 != null) {
                zzj(firebaseAuth).zza(firebaseUser4.zzc());
            }
        }
    }

    public static void zza(@NonNull PhoneAuthOptions phoneAuthOptions) {
        String str;
        String str2;
        if (phoneAuthOptions.zzl()) {
            FirebaseAuth zzb2 = phoneAuthOptions.zzb();
            if (((zzaj) Preconditions.checkNotNull(phoneAuthOptions.zzc())).zzd()) {
                str2 = Preconditions.checkNotEmpty(phoneAuthOptions.zzh());
                str = str2;
            } else {
                PhoneMultiFactorInfo phoneMultiFactorInfo = (PhoneMultiFactorInfo) Preconditions.checkNotNull(phoneAuthOptions.zzf());
                String checkNotEmpty = Preconditions.checkNotEmpty(phoneMultiFactorInfo.getUid());
                str2 = phoneMultiFactorInfo.getPhoneNumber();
                str = checkNotEmpty;
            }
            if (phoneAuthOptions.zzd() == null || !zzads.zza(str, phoneAuthOptions.zze(), phoneAuthOptions.zza(), phoneAuthOptions.zzi())) {
                zzb2.zzr.zza(zzb2, str2, phoneAuthOptions.zza(), zzb2.zzi(), phoneAuthOptions.zzj()).addOnCompleteListener(new zzk(zzb2, phoneAuthOptions, str));
                return;
            }
            return;
        }
        FirebaseAuth zzb3 = phoneAuthOptions.zzb();
        String checkNotEmpty2 = Preconditions.checkNotEmpty(phoneAuthOptions.zzh());
        if (phoneAuthOptions.zzd() != null || !zzads.zza(checkNotEmpty2, phoneAuthOptions.zze(), phoneAuthOptions.zza(), phoneAuthOptions.zzi())) {
            zzb3.zzr.zza(zzb3, checkNotEmpty2, phoneAuthOptions.zza(), zzb3.zzi(), phoneAuthOptions.zzj()).addOnCompleteListener(new zzl(zzb3, phoneAuthOptions, checkNotEmpty2));
        }
    }

    public final void zza(@NonNull PhoneAuthOptions phoneAuthOptions, @Nullable String str, @Nullable String str2) {
        long longValue = phoneAuthOptions.zzg().longValue();
        if (longValue < 0 || longValue > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        String checkNotEmpty = Preconditions.checkNotEmpty(phoneAuthOptions.zzh());
        zzagd zzagd = new zzagd(checkNotEmpty, longValue, phoneAuthOptions.zzd() != null, this.zzi, this.zzk, str, str2, zzi());
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zza2 = zza(checkNotEmpty, phoneAuthOptions.zze());
        this.zze.zza(this.zza, zzagd, TextUtils.isEmpty(str) ? zza(phoneAuthOptions, zza2) : zza2, phoneAuthOptions.zza(), phoneAuthOptions.zzi());
    }
}
