package com.google.firebase.auth;

import androidx.annotation.NonNull;

public class ActionCodeInfo {
    @NonNull
    protected String email;

    @NonNull
    public String getEmail() {
        return this.email;
    }
}
