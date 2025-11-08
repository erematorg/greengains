package com.google.firebase.auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Map;

public interface AdditionalUserInfo extends SafeParcelable {
    @Nullable
    Map<String, Object> getProfile();

    @Nullable
    String getProviderId();

    @Nullable
    String getUsername();

    boolean isNewUser();
}
