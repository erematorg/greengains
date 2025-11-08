package com.google.firebase.auth;

import androidx.annotation.NonNull;

public class FirebaseAuthInvalidCredentialsException extends FirebaseAuthException {
    public FirebaseAuthInvalidCredentialsException(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }
}
