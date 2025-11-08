package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;

public class PhoneMultiFactorGenerator {
    @NonNull
    public static final String FACTOR_ID = "phone";

    @NonNull
    public static PhoneMultiFactorAssertion getAssertion(@NonNull PhoneAuthCredential phoneAuthCredential) {
        Preconditions.checkNotNull(phoneAuthCredential);
        return new PhoneMultiFactorAssertion(phoneAuthCredential);
    }
}
