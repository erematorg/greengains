package com.google.firebase.auth.internal;

import com.google.firebase.auth.SignInMethodQueryResult;
import java.util.List;

public final class zzao implements SignInMethodQueryResult {
    private final List<String> zza;

    public zzao(List<String> list) {
        this.zza = list;
    }

    public final List<String> getSignInMethods() {
        return this.zza;
    }
}
