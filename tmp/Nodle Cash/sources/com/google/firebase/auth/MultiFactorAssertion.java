package com.google.firebase.auth;

import androidx.annotation.NonNull;

public abstract class MultiFactorAssertion {
    @NonNull
    public abstract String getFactorId();
}
