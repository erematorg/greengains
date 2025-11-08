package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeEmailInfo;

public final class zzq extends ActionCodeEmailInfo {
    private final String zza;

    public zzq(String str, String str2) {
        this.email = Preconditions.checkNotEmpty(str);
        this.zza = Preconditions.checkNotEmpty(str2);
    }

    public final String getPreviousEmail() {
        return this.zza;
    }
}
