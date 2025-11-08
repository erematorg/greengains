package com.google.firebase.auth;

import androidx.annotation.NonNull;

public abstract class ActionCodeEmailInfo extends ActionCodeInfo {
    @NonNull
    public String getEmail() {
        return super.getEmail();
    }

    @NonNull
    public abstract String getPreviousEmail();
}
