package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeInfo;

public final class zzp extends ActionCodeInfo {
    public zzp(String str) {
        this.email = Preconditions.checkNotEmpty(str);
    }
}
