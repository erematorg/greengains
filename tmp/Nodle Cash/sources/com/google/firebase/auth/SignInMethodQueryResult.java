package com.google.firebase.auth;

import androidx.annotation.Nullable;
import java.util.List;

public interface SignInMethodQueryResult {
    @Nullable
    List<String> getSignInMethods();
}
