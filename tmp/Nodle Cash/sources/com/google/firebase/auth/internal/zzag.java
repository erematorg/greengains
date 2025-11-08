package com.google.firebase.auth.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import java.util.List;

public final class zzag extends MultiFactor {
    /* access modifiers changed from: private */
    public final zzac zza;

    public zzag(zzac zzac) {
        Preconditions.checkNotNull(zzac);
        this.zza = zzac;
    }

    public final Task<Void> enroll(MultiFactorAssertion multiFactorAssertion, @Nullable String str) {
        Preconditions.checkNotNull(multiFactorAssertion);
        zzac zzac = this.zza;
        return FirebaseAuth.getInstance(zzac.zza()).zza((FirebaseUser) zzac, multiFactorAssertion, str);
    }

    public final List<MultiFactorInfo> getEnrolledFactors() {
        return this.zza.zzi();
    }

    public final Task<MultiFactorSession> getSession() {
        return this.zza.getIdToken(false).continueWithTask(new zzaf(this));
    }

    public final Task<Void> unenroll(MultiFactorInfo multiFactorInfo) {
        Preconditions.checkNotNull(multiFactorInfo);
        return unenroll(multiFactorInfo.getUid());
    }

    public final Task<Void> unenroll(String str) {
        Preconditions.checkNotEmpty(str);
        zzac zzac = this.zza;
        return FirebaseAuth.getInstance(zzac.zza()).zza((FirebaseUser) zzac, str);
    }
}
