package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport extends CrashlyticsReport {
    private final CrashlyticsReport.ApplicationExitInfo appExitInfo;
    private final String appQualitySessionId;
    private final String buildVersion;
    private final String displayVersion;
    private final String firebaseAuthenticationToken;
    private final String firebaseInstallationId;
    private final String gmpAppId;
    private final String installationUuid;
    private final CrashlyticsReport.FilesPayload ndkPayload;
    private final int platform;
    private final String sdkVersion;
    private final CrashlyticsReport.Session session;

    public static final class Builder extends CrashlyticsReport.Builder {
        private CrashlyticsReport.ApplicationExitInfo appExitInfo;
        private String appQualitySessionId;
        private String buildVersion;
        private String displayVersion;
        private String firebaseAuthenticationToken;
        private String firebaseInstallationId;
        private String gmpAppId;
        private String installationUuid;
        private CrashlyticsReport.FilesPayload ndkPayload;
        private int platform;
        private String sdkVersion;
        private CrashlyticsReport.Session session;
        private byte set$0;

        public CrashlyticsReport build() {
            if (this.set$0 == 1 && this.sdkVersion != null && this.gmpAppId != null && this.installationUuid != null && this.buildVersion != null && this.displayVersion != null) {
                return new AutoValue_CrashlyticsReport(this.sdkVersion, this.gmpAppId, this.platform, this.installationUuid, this.firebaseInstallationId, this.firebaseAuthenticationToken, this.appQualitySessionId, this.buildVersion, this.displayVersion, this.session, this.ndkPayload, this.appExitInfo);
            }
            StringBuilder sb = new StringBuilder();
            if (this.sdkVersion == null) {
                sb.append(" sdkVersion");
            }
            if (this.gmpAppId == null) {
                sb.append(" gmpAppId");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" platform");
            }
            if (this.installationUuid == null) {
                sb.append(" installationUuid");
            }
            if (this.buildVersion == null) {
                sb.append(" buildVersion");
            }
            if (this.displayVersion == null) {
                sb.append(" displayVersion");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Builder setAppExitInfo(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
            this.appExitInfo = applicationExitInfo;
            return this;
        }

        public CrashlyticsReport.Builder setAppQualitySessionId(@Nullable String str) {
            this.appQualitySessionId = str;
            return this;
        }

        public CrashlyticsReport.Builder setBuildVersion(String str) {
            if (str != null) {
                this.buildVersion = str;
                return this;
            }
            throw new NullPointerException("Null buildVersion");
        }

        public CrashlyticsReport.Builder setDisplayVersion(String str) {
            if (str != null) {
                this.displayVersion = str;
                return this;
            }
            throw new NullPointerException("Null displayVersion");
        }

        public CrashlyticsReport.Builder setFirebaseAuthenticationToken(@Nullable String str) {
            this.firebaseAuthenticationToken = str;
            return this;
        }

        public CrashlyticsReport.Builder setFirebaseInstallationId(@Nullable String str) {
            this.firebaseInstallationId = str;
            return this;
        }

        public CrashlyticsReport.Builder setGmpAppId(String str) {
            if (str != null) {
                this.gmpAppId = str;
                return this;
            }
            throw new NullPointerException("Null gmpAppId");
        }

        public CrashlyticsReport.Builder setInstallationUuid(String str) {
            if (str != null) {
                this.installationUuid = str;
                return this;
            }
            throw new NullPointerException("Null installationUuid");
        }

        public CrashlyticsReport.Builder setNdkPayload(CrashlyticsReport.FilesPayload filesPayload) {
            this.ndkPayload = filesPayload;
            return this;
        }

        public CrashlyticsReport.Builder setPlatform(int i3) {
            this.platform = i3;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Builder setSdkVersion(String str) {
            if (str != null) {
                this.sdkVersion = str;
                return this;
            }
            throw new NullPointerException("Null sdkVersion");
        }

        public CrashlyticsReport.Builder setSession(CrashlyticsReport.Session session2) {
            this.session = session2;
            return this;
        }

        public Builder() {
        }

        private Builder(CrashlyticsReport crashlyticsReport) {
            this.sdkVersion = crashlyticsReport.getSdkVersion();
            this.gmpAppId = crashlyticsReport.getGmpAppId();
            this.platform = crashlyticsReport.getPlatform();
            this.installationUuid = crashlyticsReport.getInstallationUuid();
            this.firebaseInstallationId = crashlyticsReport.getFirebaseInstallationId();
            this.firebaseAuthenticationToken = crashlyticsReport.getFirebaseAuthenticationToken();
            this.appQualitySessionId = crashlyticsReport.getAppQualitySessionId();
            this.buildVersion = crashlyticsReport.getBuildVersion();
            this.displayVersion = crashlyticsReport.getDisplayVersion();
            this.session = crashlyticsReport.getSession();
            this.ndkPayload = crashlyticsReport.getNdkPayload();
            this.appExitInfo = crashlyticsReport.getAppExitInfo();
            this.set$0 = 1;
        }
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        CrashlyticsReport.Session session2;
        CrashlyticsReport.FilesPayload filesPayload;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport)) {
            return false;
        }
        CrashlyticsReport crashlyticsReport = (CrashlyticsReport) obj;
        if (this.sdkVersion.equals(crashlyticsReport.getSdkVersion()) && this.gmpAppId.equals(crashlyticsReport.getGmpAppId()) && this.platform == crashlyticsReport.getPlatform() && this.installationUuid.equals(crashlyticsReport.getInstallationUuid()) && ((str = this.firebaseInstallationId) != null ? str.equals(crashlyticsReport.getFirebaseInstallationId()) : crashlyticsReport.getFirebaseInstallationId() == null) && ((str2 = this.firebaseAuthenticationToken) != null ? str2.equals(crashlyticsReport.getFirebaseAuthenticationToken()) : crashlyticsReport.getFirebaseAuthenticationToken() == null) && ((str3 = this.appQualitySessionId) != null ? str3.equals(crashlyticsReport.getAppQualitySessionId()) : crashlyticsReport.getAppQualitySessionId() == null) && this.buildVersion.equals(crashlyticsReport.getBuildVersion()) && this.displayVersion.equals(crashlyticsReport.getDisplayVersion()) && ((session2 = this.session) != null ? session2.equals(crashlyticsReport.getSession()) : crashlyticsReport.getSession() == null) && ((filesPayload = this.ndkPayload) != null ? filesPayload.equals(crashlyticsReport.getNdkPayload()) : crashlyticsReport.getNdkPayload() == null)) {
            CrashlyticsReport.ApplicationExitInfo applicationExitInfo = this.appExitInfo;
            if (applicationExitInfo == null) {
                if (crashlyticsReport.getAppExitInfo() == null) {
                    return true;
                }
            } else if (applicationExitInfo.equals(crashlyticsReport.getAppExitInfo())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public CrashlyticsReport.ApplicationExitInfo getAppExitInfo() {
        return this.appExitInfo;
    }

    @Nullable
    public String getAppQualitySessionId() {
        return this.appQualitySessionId;
    }

    @NonNull
    public String getBuildVersion() {
        return this.buildVersion;
    }

    @NonNull
    public String getDisplayVersion() {
        return this.displayVersion;
    }

    @Nullable
    public String getFirebaseAuthenticationToken() {
        return this.firebaseAuthenticationToken;
    }

    @Nullable
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    @NonNull
    public String getGmpAppId() {
        return this.gmpAppId;
    }

    @NonNull
    public String getInstallationUuid() {
        return this.installationUuid;
    }

    @Nullable
    public CrashlyticsReport.FilesPayload getNdkPayload() {
        return this.ndkPayload;
    }

    public int getPlatform() {
        return this.platform;
    }

    @NonNull
    public String getSdkVersion() {
        return this.sdkVersion;
    }

    @Nullable
    public CrashlyticsReport.Session getSession() {
        return this.session;
    }

    public int hashCode() {
        int hashCode = (((((((this.sdkVersion.hashCode() ^ 1000003) * 1000003) ^ this.gmpAppId.hashCode()) * 1000003) ^ this.platform) * 1000003) ^ this.installationUuid.hashCode()) * 1000003;
        String str = this.firebaseInstallationId;
        int i3 = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.firebaseAuthenticationToken;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.appQualitySessionId;
        int hashCode4 = (((((hashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003) ^ this.buildVersion.hashCode()) * 1000003) ^ this.displayVersion.hashCode()) * 1000003;
        CrashlyticsReport.Session session2 = this.session;
        int hashCode5 = (hashCode4 ^ (session2 == null ? 0 : session2.hashCode())) * 1000003;
        CrashlyticsReport.FilesPayload filesPayload = this.ndkPayload;
        int hashCode6 = (hashCode5 ^ (filesPayload == null ? 0 : filesPayload.hashCode())) * 1000003;
        CrashlyticsReport.ApplicationExitInfo applicationExitInfo = this.appExitInfo;
        if (applicationExitInfo != null) {
            i3 = applicationExitInfo.hashCode();
        }
        return hashCode6 ^ i3;
    }

    public CrashlyticsReport.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        return "CrashlyticsReport{sdkVersion=" + this.sdkVersion + ", gmpAppId=" + this.gmpAppId + ", platform=" + this.platform + ", installationUuid=" + this.installationUuid + ", firebaseInstallationId=" + this.firebaseInstallationId + ", firebaseAuthenticationToken=" + this.firebaseAuthenticationToken + ", appQualitySessionId=" + this.appQualitySessionId + ", buildVersion=" + this.buildVersion + ", displayVersion=" + this.displayVersion + ", session=" + this.session + ", ndkPayload=" + this.ndkPayload + ", appExitInfo=" + this.appExitInfo + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_CrashlyticsReport(String str, String str2, int i3, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, String str7, String str8, @Nullable CrashlyticsReport.Session session2, @Nullable CrashlyticsReport.FilesPayload filesPayload, @Nullable CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        this.sdkVersion = str;
        this.gmpAppId = str2;
        this.platform = i3;
        this.installationUuid = str3;
        this.firebaseInstallationId = str4;
        this.firebaseAuthenticationToken = str5;
        this.appQualitySessionId = str6;
        this.buildVersion = str7;
        this.displayVersion = str8;
        this.session = session2;
        this.ndkPayload = filesPayload;
        this.appExitInfo = applicationExitInfo;
    }
}
