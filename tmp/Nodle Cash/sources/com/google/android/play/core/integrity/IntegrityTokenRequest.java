package com.google.android.play.core.integrity;

import android.net.Network;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public abstract class IntegrityTokenRequest {

    public static abstract class Builder {
        public abstract IntegrityTokenRequest build();

        public abstract Builder setCloudProjectNumber(long j2);

        public abstract Builder setNonce(String str);
    }

    public static Builder builder() {
        return new c();
    }

    @RequiresApi(api = 23)
    @Nullable
    public abstract Network a();

    @Nullable
    public abstract Long cloudProjectNumber();

    public abstract String nonce();
}
