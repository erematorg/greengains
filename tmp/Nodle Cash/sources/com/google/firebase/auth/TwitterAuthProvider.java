package com.google.firebase.auth;

import androidx.annotation.NonNull;

public class TwitterAuthProvider {
    @NonNull
    public static final String PROVIDER_ID = "twitter.com";
    @NonNull
    public static final String TWITTER_SIGN_IN_METHOD = "twitter.com";

    private TwitterAuthProvider() {
    }

    @NonNull
    public static AuthCredential getCredential(@NonNull String str, @NonNull String str2) {
        return new TwitterAuthCredential(str, str2);
    }
}
