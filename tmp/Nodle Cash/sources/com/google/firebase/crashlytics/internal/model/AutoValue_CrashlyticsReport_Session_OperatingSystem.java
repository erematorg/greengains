package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_OperatingSystem extends CrashlyticsReport.Session.OperatingSystem {
    private final String buildVersion;
    private final boolean jailbroken;
    private final int platform;
    private final String version;

    public static final class Builder extends CrashlyticsReport.Session.OperatingSystem.Builder {
        private String buildVersion;
        private boolean jailbroken;
        private int platform;
        private byte set$0;
        private String version;

        public CrashlyticsReport.Session.OperatingSystem build() {
            String str;
            String str2;
            if (this.set$0 == 3 && (str = this.version) != null && (str2 = this.buildVersion) != null) {
                return new AutoValue_CrashlyticsReport_Session_OperatingSystem(this.platform, str, str2, this.jailbroken);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" platform");
            }
            if (this.version == null) {
                sb.append(" version");
            }
            if (this.buildVersion == null) {
                sb.append(" buildVersion");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" jailbroken");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setBuildVersion(String str) {
            if (str != null) {
                this.buildVersion = str;
                return this;
            }
            throw new NullPointerException("Null buildVersion");
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setJailbroken(boolean z2) {
            this.jailbroken = z2;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setPlatform(int i3) {
            this.platform = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.OperatingSystem.Builder setVersion(String str) {
            if (str != null) {
                this.version = str;
                return this;
            }
            throw new NullPointerException("Null version");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.OperatingSystem)) {
            return false;
        }
        CrashlyticsReport.Session.OperatingSystem operatingSystem = (CrashlyticsReport.Session.OperatingSystem) obj;
        return this.platform == operatingSystem.getPlatform() && this.version.equals(operatingSystem.getVersion()) && this.buildVersion.equals(operatingSystem.getBuildVersion()) && this.jailbroken == operatingSystem.isJailbroken();
    }

    @NonNull
    public String getBuildVersion() {
        return this.buildVersion;
    }

    public int getPlatform() {
        return this.platform;
    }

    @NonNull
    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (this.jailbroken ? 1231 : 1237) ^ ((((((this.platform ^ 1000003) * 1000003) ^ this.version.hashCode()) * 1000003) ^ this.buildVersion.hashCode()) * 1000003);
    }

    public boolean isJailbroken() {
        return this.jailbroken;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OperatingSystem{platform=");
        sb.append(this.platform);
        sb.append(", version=");
        sb.append(this.version);
        sb.append(", buildVersion=");
        sb.append(this.buildVersion);
        sb.append(", jailbroken=");
        return android.support.v4.media.session.a.s(sb, this.jailbroken, StringSubstitutor.DEFAULT_VAR_END);
    }

    private AutoValue_CrashlyticsReport_Session_OperatingSystem(int i3, String str, String str2, boolean z2) {
        this.platform = i3;
        this.version = str;
        this.buildVersion = str2;
        this.jailbroken = z2;
    }
}
