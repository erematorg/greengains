package com.google.firebase.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class FirebaseAuthWeakPasswordException extends FirebaseAuthInvalidCredentialsException {
    @Nullable
    private final String zza;

    public FirebaseAuthWeakPasswordException(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        super(str, str2);
        this.zza = str3;
    }

    @Nullable
    public final String getReason() {
        return this.zza;
    }
}
