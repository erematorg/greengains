package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;
import androidx.compose.ui.autofill.a;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;

public class FirebaseRemoteConfigSettings {
    private final long fetchTimeoutInSeconds;
    private final long minimumFetchInterval;

    public static class Builder {
        /* access modifiers changed from: private */
        public long fetchTimeoutInSeconds = 60;
        /* access modifiers changed from: private */
        public long minimumFetchInterval = ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS;

        @NonNull
        public FirebaseRemoteConfigSettings build() {
            return new FirebaseRemoteConfigSettings(this);
        }

        public long getFetchTimeoutInSeconds() {
            return this.fetchTimeoutInSeconds;
        }

        public long getMinimumFetchIntervalInSeconds() {
            return this.minimumFetchInterval;
        }

        @NonNull
        public Builder setFetchTimeoutInSeconds(long j2) throws IllegalArgumentException {
            if (j2 >= 0) {
                this.fetchTimeoutInSeconds = j2;
                return this;
            }
            throw new IllegalArgumentException(String.format("Fetch connection timeout has to be a non-negative number. %d is an invalid argument", new Object[]{Long.valueOf(j2)}));
        }

        @NonNull
        public Builder setMinimumFetchIntervalInSeconds(long j2) {
            if (j2 >= 0) {
                this.minimumFetchInterval = j2;
                return this;
            }
            throw new IllegalArgumentException(a.j("Minimum interval between fetches has to be a non-negative number. ", j2, " is an invalid argument"));
        }
    }

    public long getFetchTimeoutInSeconds() {
        return this.fetchTimeoutInSeconds;
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.minimumFetchInterval;
    }

    @NonNull
    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.setFetchTimeoutInSeconds(getFetchTimeoutInSeconds());
        builder.setMinimumFetchIntervalInSeconds(getMinimumFetchIntervalInSeconds());
        return builder;
    }

    private FirebaseRemoteConfigSettings(Builder builder) {
        this.fetchTimeoutInSeconds = builder.fetchTimeoutInSeconds;
        this.minimumFetchInterval = builder.minimumFetchInterval;
    }
}
