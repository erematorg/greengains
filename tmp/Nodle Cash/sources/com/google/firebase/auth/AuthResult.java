package com.google.firebase.auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public interface AuthResult extends SafeParcelable {
    @Nullable
    AdditionalUserInfo getAdditionalUserInfo();

    @Nullable
    AuthCredential getCredential();

    @Nullable
    FirebaseUser getUser();
}
