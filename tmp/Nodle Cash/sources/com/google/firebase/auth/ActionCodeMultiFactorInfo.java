package com.google.firebase.auth;

import androidx.annotation.NonNull;

public abstract class ActionCodeMultiFactorInfo extends ActionCodeInfo {
    @NonNull
    public abstract MultiFactorInfo getMultiFactorInfo();
}
