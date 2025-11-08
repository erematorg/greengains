package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class AuthCredential extends AbstractSafeParcelable {
    @NonNull
    public abstract String getProvider();

    @NonNull
    public abstract String getSignInMethod();

    @NonNull
    public abstract AuthCredential zza();
}
