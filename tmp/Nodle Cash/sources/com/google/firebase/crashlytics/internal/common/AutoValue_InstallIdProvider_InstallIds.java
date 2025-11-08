package com.google.firebase.crashlytics.internal.common;

import A.a;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.common.InstallIdProvider;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_InstallIdProvider_InstallIds extends InstallIdProvider.InstallIds {
    private final String crashlyticsInstallId;
    private final String firebaseAuthenticationToken;
    private final String firebaseInstallationId;

    public AutoValue_InstallIdProvider_InstallIds(String str, @Nullable String str2, @Nullable String str3) {
        if (str != null) {
            this.crashlyticsInstallId = str;
            this.firebaseInstallationId = str2;
            this.firebaseAuthenticationToken = str3;
            return;
        }
        throw new NullPointerException("Null crashlyticsInstallId");
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InstallIdProvider.InstallIds)) {
            return false;
        }
        InstallIdProvider.InstallIds installIds = (InstallIdProvider.InstallIds) obj;
        if (this.crashlyticsInstallId.equals(installIds.getCrashlyticsInstallId()) && ((str = this.firebaseInstallationId) != null ? str.equals(installIds.getFirebaseInstallationId()) : installIds.getFirebaseInstallationId() == null)) {
            String str2 = this.firebaseAuthenticationToken;
            if (str2 == null) {
                if (installIds.getFirebaseAuthenticationToken() == null) {
                    return true;
                }
            } else if (str2.equals(installIds.getFirebaseAuthenticationToken())) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public String getCrashlyticsInstallId() {
        return this.crashlyticsInstallId;
    }

    @Nullable
    public String getFirebaseAuthenticationToken() {
        return this.firebaseAuthenticationToken;
    }

    @Nullable
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    public int hashCode() {
        int hashCode = (this.crashlyticsInstallId.hashCode() ^ 1000003) * 1000003;
        String str = this.firebaseInstallationId;
        int i3 = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.firebaseAuthenticationToken;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode2 ^ i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("InstallIds{crashlyticsInstallId=");
        sb.append(this.crashlyticsInstallId);
        sb.append(", firebaseInstallationId=");
        sb.append(this.firebaseInstallationId);
        sb.append(", firebaseAuthenticationToken=");
        return a.n(sb, this.firebaseAuthenticationToken, StringSubstitutor.DEFAULT_VAR_END);
    }
}
