package com.google.firebase.installations;

import android.support.v4.media.session.a;
import androidx.annotation.NonNull;
import com.google.firebase.installations.InstallationTokenResult;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_InstallationTokenResult extends InstallationTokenResult {
    private final String token;
    private final long tokenCreationTimestamp;
    private final long tokenExpirationTimestamp;

    public static final class Builder extends InstallationTokenResult.Builder {
        private String token;
        private Long tokenCreationTimestamp;
        private Long tokenExpirationTimestamp;

        public InstallationTokenResult build() {
            String str = this.token == null ? " token" : "";
            if (this.tokenExpirationTimestamp == null) {
                str = a.m(str, " tokenExpirationTimestamp");
            }
            if (this.tokenCreationTimestamp == null) {
                str = a.m(str, " tokenCreationTimestamp");
            }
            if (str.isEmpty()) {
                return new AutoValue_InstallationTokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.tokenCreationTimestamp.longValue());
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        public InstallationTokenResult.Builder setToken(String str) {
            if (str != null) {
                this.token = str;
                return this;
            }
            throw new NullPointerException("Null token");
        }

        public InstallationTokenResult.Builder setTokenCreationTimestamp(long j2) {
            this.tokenCreationTimestamp = Long.valueOf(j2);
            return this;
        }

        public InstallationTokenResult.Builder setTokenExpirationTimestamp(long j2) {
            this.tokenExpirationTimestamp = Long.valueOf(j2);
            return this;
        }

        public Builder() {
        }

        private Builder(InstallationTokenResult installationTokenResult) {
            this.token = installationTokenResult.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(installationTokenResult.getTokenExpirationTimestamp());
            this.tokenCreationTimestamp = Long.valueOf(installationTokenResult.getTokenCreationTimestamp());
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InstallationTokenResult)) {
            return false;
        }
        InstallationTokenResult installationTokenResult = (InstallationTokenResult) obj;
        return this.token.equals(installationTokenResult.getToken()) && this.tokenExpirationTimestamp == installationTokenResult.getTokenExpirationTimestamp() && this.tokenCreationTimestamp == installationTokenResult.getTokenCreationTimestamp();
    }

    @NonNull
    public String getToken() {
        return this.token;
    }

    @NonNull
    public long getTokenCreationTimestamp() {
        return this.tokenCreationTimestamp;
    }

    @NonNull
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public int hashCode() {
        long j2 = this.tokenExpirationTimestamp;
        long j3 = this.tokenCreationTimestamp;
        return ((int) (j3 ^ (j3 >>> 32))) ^ ((((this.token.hashCode() ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003);
    }

    public InstallationTokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InstallationTokenResult{token=");
        sb.append(this.token);
        sb.append(", tokenExpirationTimestamp=");
        sb.append(this.tokenExpirationTimestamp);
        sb.append(", tokenCreationTimestamp=");
        return a.k(this.tokenCreationTimestamp, StringSubstitutor.DEFAULT_VAR_END, sb);
    }

    private AutoValue_InstallationTokenResult(String str, long j2, long j3) {
        this.token = str;
        this.tokenExpirationTimestamp = j2;
        this.tokenCreationTimestamp = j3;
    }
}
