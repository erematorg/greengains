package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.a;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment extends CrashlyticsReport.Session.Event.RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant;
    private final long templateVersion;

    public static final class Builder extends CrashlyticsReport.Session.Event.RolloutAssignment.Builder {
        private String parameterKey;
        private String parameterValue;
        private CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant;
        private byte set$0;
        private long templateVersion;

        public CrashlyticsReport.Session.Event.RolloutAssignment build() {
            CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant2;
            String str;
            String str2;
            if (this.set$0 == 1 && (rolloutVariant2 = this.rolloutVariant) != null && (str = this.parameterKey) != null && (str2 = this.parameterValue) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment(rolloutVariant2, str, str2, this.templateVersion);
            }
            StringBuilder sb = new StringBuilder();
            if (this.rolloutVariant == null) {
                sb.append(" rolloutVariant");
            }
            if (this.parameterKey == null) {
                sb.append(" parameterKey");
            }
            if (this.parameterValue == null) {
                sb.append(" parameterValue");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" templateVersion");
            }
            throw new IllegalStateException(a.e("Missing required properties:", sb));
        }

        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setParameterKey(String str) {
            if (str != null) {
                this.parameterKey = str;
                return this;
            }
            throw new NullPointerException("Null parameterKey");
        }

        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setParameterValue(String str) {
            if (str != null) {
                this.parameterValue = str;
                return this;
            }
            throw new NullPointerException("Null parameterValue");
        }

        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setRolloutVariant(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant2) {
            if (rolloutVariant2 != null) {
                this.rolloutVariant = rolloutVariant2;
                return this;
            }
            throw new NullPointerException("Null rolloutVariant");
        }

        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setTemplateVersion(long j2) {
            this.templateVersion = j2;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.RolloutAssignment)) {
            return false;
        }
        CrashlyticsReport.Session.Event.RolloutAssignment rolloutAssignment = (CrashlyticsReport.Session.Event.RolloutAssignment) obj;
        return this.rolloutVariant.equals(rolloutAssignment.getRolloutVariant()) && this.parameterKey.equals(rolloutAssignment.getParameterKey()) && this.parameterValue.equals(rolloutAssignment.getParameterValue()) && this.templateVersion == rolloutAssignment.getTemplateVersion();
    }

    @NonNull
    public String getParameterKey() {
        return this.parameterKey;
    }

    @NonNull
    public String getParameterValue() {
        return this.parameterValue;
    }

    @NonNull
    public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant getRolloutVariant() {
        return this.rolloutVariant;
    }

    @NonNull
    public long getTemplateVersion() {
        return this.templateVersion;
    }

    public int hashCode() {
        long j2 = this.templateVersion;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((((((this.rolloutVariant.hashCode() ^ 1000003) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("RolloutAssignment{rolloutVariant=");
        sb.append(this.rolloutVariant);
        sb.append(", parameterKey=");
        sb.append(this.parameterKey);
        sb.append(", parameterValue=");
        sb.append(this.parameterValue);
        sb.append(", templateVersion=");
        return android.support.v4.media.session.a.k(this.templateVersion, StringSubstitutor.DEFAULT_VAR_END, sb);
    }

    private AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant2, String str, String str2, long j2) {
        this.rolloutVariant = rolloutVariant2;
        this.parameterKey = str;
        this.parameterValue = str2;
        this.templateVersion = j2;
    }
}
