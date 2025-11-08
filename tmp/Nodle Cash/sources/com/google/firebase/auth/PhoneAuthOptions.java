package com.google.firebase.auth;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzaj;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public final class PhoneAuthOptions {
    private final FirebaseAuth zza;
    private Long zzb;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks zzc;
    private Executor zzd;
    @Nullable
    private String zze;
    private Activity zzf;
    @Nullable
    private PhoneAuthProvider.ForceResendingToken zzg;
    @Nullable
    private MultiFactorSession zzh;
    @Nullable
    private PhoneMultiFactorInfo zzi;
    private boolean zzj;
    private boolean zzk;

    public static final class Builder {
        private final FirebaseAuth zza;
        private String zzb;
        private Long zzc;
        private PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd;
        private Executor zze;
        private Activity zzf;
        @Nullable
        private PhoneAuthProvider.ForceResendingToken zzg;
        private MultiFactorSession zzh;
        private PhoneMultiFactorInfo zzi;
        private boolean zzj;

        public Builder(@NonNull FirebaseAuth firebaseAuth) {
            this.zza = (FirebaseAuth) Preconditions.checkNotNull(firebaseAuth);
        }

        @NonNull
        public final PhoneAuthOptions build() {
            Preconditions.checkNotNull(this.zza, "FirebaseAuth instance cannot be null");
            Preconditions.checkNotNull(this.zzc, "You must specify an auto-retrieval timeout; please call #setTimeout()");
            Preconditions.checkNotNull(this.zzd, "You must specify callbacks on your PhoneAuthOptions. Please call #setCallbacks()");
            this.zze = this.zza.zzg();
            if (this.zzc.longValue() < 0 || this.zzc.longValue() > 120) {
                throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
            }
            MultiFactorSession multiFactorSession = this.zzh;
            boolean z2 = false;
            if (multiFactorSession == null) {
                Preconditions.checkNotEmpty(this.zzb, "The given phoneNumber is empty. Please set a non-empty phone number with #setPhoneNumber()");
                Preconditions.checkArgument(!this.zzj, "You cannot require sms validation without setting a multi-factor session.");
                if (this.zzi == null) {
                    z2 = true;
                }
                Preconditions.checkArgument(z2, "A phoneMultiFactorInfo must be set for second factor sign-in.");
            } else if (multiFactorSession == null || !((zzaj) multiFactorSession).zzd()) {
                Preconditions.checkArgument(this.zzi != null, "A phoneMultiFactorInfo must be set for second factor sign-in.");
                if (this.zzb == null) {
                    z2 = true;
                }
                Preconditions.checkArgument(z2, "A phone number must not be set for MFA sign-in. A PhoneMultiFactorInfo should be set instead.");
            } else {
                Preconditions.checkNotEmpty(this.zzb);
                if (this.zzi == null) {
                    z2 = true;
                }
                Preconditions.checkArgument(z2, "Invalid MultiFactorSession - use the getSession method in MultiFactorResolver to get a valid sign-in session.");
            }
            return new PhoneAuthOptions(this.zza, this.zzc, this.zzd, this.zze, this.zzb, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj);
        }

        @NonNull
        public final Builder requireSmsValidation(boolean z2) {
            this.zzj = z2;
            return this;
        }

        @NonNull
        public final Builder setActivity(@NonNull Activity activity) {
            this.zzf = activity;
            return this;
        }

        @NonNull
        public final Builder setCallbacks(@NonNull PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
            this.zzd = onVerificationStateChangedCallbacks;
            return this;
        }

        @NonNull
        public final Builder setForceResendingToken(@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            this.zzg = forceResendingToken;
            return this;
        }

        @NonNull
        public final Builder setMultiFactorHint(@NonNull PhoneMultiFactorInfo phoneMultiFactorInfo) {
            this.zzi = phoneMultiFactorInfo;
            return this;
        }

        @NonNull
        public final Builder setMultiFactorSession(@NonNull MultiFactorSession multiFactorSession) {
            this.zzh = multiFactorSession;
            return this;
        }

        @NonNull
        public final Builder setPhoneNumber(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public final Builder setTimeout(@NonNull Long l2, @NonNull TimeUnit timeUnit) {
            this.zzc = Long.valueOf(TimeUnit.SECONDS.convert(l2.longValue(), timeUnit));
            return this;
        }
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(FirebaseAuth.getInstance());
    }

    @Nullable
    public final Activity zza() {
        return this.zzf;
    }

    @NonNull
    public final FirebaseAuth zzb() {
        return this.zza;
    }

    @Nullable
    public final MultiFactorSession zzc() {
        return this.zzh;
    }

    @Nullable
    public final PhoneAuthProvider.ForceResendingToken zzd() {
        return this.zzg;
    }

    @NonNull
    public final PhoneAuthProvider.OnVerificationStateChangedCallbacks zze() {
        return this.zzc;
    }

    @Nullable
    public final PhoneMultiFactorInfo zzf() {
        return this.zzi;
    }

    @NonNull
    public final Long zzg() {
        return this.zzb;
    }

    @Nullable
    public final String zzh() {
        return this.zze;
    }

    @NonNull
    public final Executor zzi() {
        return this.zzd;
    }

    public final boolean zzj() {
        return this.zzk;
    }

    public final boolean zzk() {
        return this.zzj;
    }

    public final boolean zzl() {
        return this.zzh != null;
    }

    private PhoneAuthOptions(FirebaseAuth firebaseAuth, Long l2, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, @Nullable String str, @NonNull Activity activity, @Nullable PhoneAuthProvider.ForceResendingToken forceResendingToken, @Nullable MultiFactorSession multiFactorSession, @Nullable PhoneMultiFactorInfo phoneMultiFactorInfo, boolean z2) {
        this.zza = firebaseAuth;
        this.zze = str;
        this.zzb = l2;
        this.zzc = onVerificationStateChangedCallbacks;
        this.zzf = activity;
        this.zzd = executor;
        this.zzg = forceResendingToken;
        this.zzh = multiFactorSession;
        this.zzi = phoneMultiFactorInfo;
        this.zzj = z2;
    }

    @NonNull
    public static Builder newBuilder(@NonNull FirebaseAuth firebaseAuth) {
        return new Builder(firebaseAuth);
    }

    public final void zza(boolean z2) {
        this.zzk = true;
    }
}
